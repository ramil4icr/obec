package cz.nigol.obec.services.impl;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cz.nigol.obec.entities.Settings;
import cz.nigol.obec.entities.interfaces.RssItem;
import cz.nigol.obec.qualifiers.CurrentSettings;
import cz.nigol.obec.services.RssService;

@Stateless
public class RssServiceImpl implements RssService {
    @Inject
    private Log log;
    @Inject
    @CurrentSettings
    private Settings settings;

    @Override
    public void generateRss(List<RssItem> items, String url, String title, OutputStream outputStream) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            prepareRssDocument(document, items, url, title);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException  e) {
            log.error(e);
        }
    }

    private void prepareRssDocument(Document document, List<RssItem> items, String url, String title) {
        Element rootElement = document.createElement("rss");
        rootElement.setAttribute("version", "2.0");
        Element element = document.createElement("channel");
        rootElement.appendChild(element);
        prepareRssHeader(element, document, title);
        prepareRssItems(element, document, items, url);
        document.appendChild(rootElement);
    }

    private void prepareRssItems(Element root, Document document, List<RssItem> items, String url) {
        items.stream()
            .forEach(a -> {
                Element element = document.createElement("item");
                try {
                    prepareOneRssItem(element, document, a, url);
                } catch (DOMException | UnsupportedEncodingException e) {
                    log.error(e);
                }
                root.appendChild(element);
            });
    }

    private void prepareOneRssItem(Element root, Document document, RssItem item, String url)
        throws DOMException, UnsupportedEncodingException {
        Element element = document.createElement("title");
        element.appendChild(document.createTextNode(item.getLabel()));
        root.appendChild(element);
        element = document.createElement("link");
        String itemUrl = url + item.getGuid();
        element.appendChild(document.createTextNode(itemUrl));
        root.appendChild(element);
        element = document.createElement("guid");
        element.appendChild(document.createTextNode(item.getGuid()));
        root.appendChild(element);
        element = document.createElement("description");
        element.appendChild(document.createTextNode(item.getDescription()));
        root.appendChild(element);
        element = document.createElement("pubDate");
        DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy");
        element.appendChild(document.createTextNode(formatter.format(item.getDate()) 
                    + " 00:00:00 GMT"));
        root.appendChild(element);
    }

    private void prepareRssHeader(Element root, Document document, String title) {
        Element element = document.createElement("title");
        element.appendChild(document.createTextNode(title));
        root.appendChild(element);
        element = document.createElement("link");
        element.appendChild(document.createTextNode(settings.getBaseUrl()));
        root.appendChild(element);
        element = document.createElement("description");
        element.appendChild(document.createTextNode(title));
        root.appendChild(element);
    }
}
