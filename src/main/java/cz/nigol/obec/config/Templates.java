package cz.nigol.obec.config;

public class Templates {
    public static final String ANNOUNCEMENT_SUBJ =
        "Hlášení obecního rozhlasu";
    public static final String ANNOUNCEMENT =
        "<html><body><h1>Hlášení obecního rozhlasu</h1>" + 
        "<p>VARIABLE1</p><br><br>" +
        "<p style='font-size: small'>Tento email dostáváte, protože jste se přihlásil(a) k odběru hlášení. " +
        "Pokud již nechcete tato hlášení dostávat, <a href='https://www.trsice.cz/odhlaseni.jsf?r=VARIABLE2'>klikněte pro odhlášení</a>.</p>" +
        "</body></html>";
}