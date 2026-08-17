package javax.lang.model.element;

import javax.lang.model.UnknownEntityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnknownElementException extends UnknownEntityException {
    private static final long serialVersionUID = 269;
    private transient Element element;
    private transient Object parameter;

    public UnknownElementException(Element element, Object obj) {
        super("Unknown element: \"" + element + "\"");
        this.element = element;
        this.parameter = obj;
    }

    public Object getArgument() {
        return this.parameter;
    }

    public Element getUnknownElement() {
        return this.element;
    }
}
