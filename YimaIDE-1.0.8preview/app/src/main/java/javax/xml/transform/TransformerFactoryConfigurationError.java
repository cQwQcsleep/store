package javax.xml.transform;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TransformerFactoryConfigurationError extends Error {
    private static final long serialVersionUID = -6527718720676281516L;
    private Exception exception;

    public TransformerFactoryConfigurationError(Exception exc) {
        super(exc.toString());
        this.exception = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.exception;
    }

    public Exception getException() {
        return this.exception;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Exception exc;
        String message = super.getMessage();
        return (message != null || (exc = this.exception) == null) ? message : exc.getMessage();
    }

    public TransformerFactoryConfigurationError(String str) {
        super(str);
        this.exception = null;
    }

    public TransformerFactoryConfigurationError() {
        this.exception = null;
    }

    public TransformerFactoryConfigurationError(Exception exc, String str) {
        super(str);
        this.exception = exc;
    }
}
