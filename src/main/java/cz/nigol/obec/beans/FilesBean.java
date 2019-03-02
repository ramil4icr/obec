package cz.nigol.obec.beans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import cz.nigol.obec.entities.FileMetadata;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.exceptions.UploadFailedException;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.qualifiers.PathToWebapp;
import cz.nigol.obec.services.FileMetadataService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class FilesBean implements Serializable {
    private static final long serialVersionUID = -8530001074117001306L;
    @Inject
    private FileMetadataService fileMetadataService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    @Inject
    private Log log;
    @Inject
    @PathToWebapp
    private String path;
    private List<FileMetadata> files;
    private FileMetadata file;

    @PostConstruct
    public void init() {
        user = userService.getUserById(user.getId());
        files = fileMetadataService.getByUser(user);
    }

    public String endOfPath(String path) {
        return path.substring(path.lastIndexOf('/') + 1, path.length()).split("~")[1];
    }

    public void newFile() {
        file = new FileMetadata();
    }

    public void handleUpload(FileUploadEvent event) {
        file.setCreatedAt(new Date());
        file.setUser(user);
        UploadedFile uploadedFile = event.getFile();
        try {
            file.setPath(preparePath(uploadedFile));
            file = fileMetadataService.save(file, path, uploadedFile.getContents());
        } catch (UploadFailedException | NoSuchAlgorithmException e) {
            log.error(file.getPath(), e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Chyba", "Chyba při nahrávání souboru."));
        }
        file = null;
        init();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uloženo",  "Soubor byl uložen."));
    }

    private String preparePath(UploadedFile uploadedFile)
        throws NoSuchAlgorithmException {
        Date date = new Date();
        String msString = String.valueOf(date.getTime());
        String folder = msString.substring(msString.length() - 1);
        String fileName = uploadedFile.getFileName();
        String open = user.getId() + msString + fileName;
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashBytes = digest.digest(open.getBytes());
        return "/upload/" + folder + "/" + String.valueOf(hashBytes) + "~" + fileName;
    }

    /**
     * @return the files
     */
    public List<FileMetadata> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<FileMetadata> files) {
        this.files = files;
    }

    /**
     * @return the file
     */
    public FileMetadata getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(FileMetadata file) {
        this.file = file;
    }}


