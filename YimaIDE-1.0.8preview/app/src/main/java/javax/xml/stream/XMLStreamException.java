package javax.xml.stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLStreamException extends Exception {
    private static final long serialVersionUID = 2018819321811497362L;
    protected Location location;
    protected Throwable nested;

    public XMLStreamException(String str, Location location, Throwable th) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\nMessage: " + str);
        this.nested = th;
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public Throwable getNestedException() {
        return this.nested;
    }

    public XMLStreamException(String str) {
        super(str);
    }

    public XMLStreamException(Throwable th) {
        super(th);
        this.nested = th;
    }

    public XMLStreamException(String str, Throwable th) {
        super(str, th);
        this.nested = th;
    }

    public XMLStreamException() {
    }

    public XMLStreamException(String str, Location location) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\nMessage: " + str);
        this.location = location;
    }
}
