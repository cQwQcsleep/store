package javax.xml.parsers;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FactoryConfigurationError extends Error {
    private static final long serialVersionUID = -827108682472263355L;
    private Exception exception;

    public FactoryConfigurationError(Exception exc) {
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

    public FactoryConfigurationError(String str) {
        super(str);
        this.exception = null;
    }

    public FactoryConfigurationError() {
        this.exception = null;
    }

    public FactoryConfigurationError(Exception exc, String str) {
        super(str);
        this.exception = exc;
    }
}
