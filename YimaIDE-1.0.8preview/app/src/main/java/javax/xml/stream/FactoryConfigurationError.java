package javax.xml.stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FactoryConfigurationError extends Error {
    private static final long serialVersionUID = -2994412584589975744L;
    Exception nested;

    public FactoryConfigurationError(Exception exc) {
        this.nested = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.nested;
    }

    public Exception getException() {
        return this.nested;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message != null) {
            return message;
        }
        Exception exc = this.nested;
        return (exc == null || (message = exc.getMessage()) != null) ? message : this.nested.getClass().toString();
    }

    public FactoryConfigurationError() {
    }

    public FactoryConfigurationError(Exception exc, String str) {
        super(str);
        this.nested = exc;
    }

    public FactoryConfigurationError(String str, Exception exc) {
        super(str);
        this.nested = exc;
    }

    public FactoryConfigurationError(String str) {
        super(str);
    }
}
