package cz.nigol.obec.exceptions;

public class UploadFailedException extends Exception {
    private static final long serialVersionUID = 1L;

    public UploadFailedException(Throwable t) {
        super(t);
    }
}
