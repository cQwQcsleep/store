package javax.lang.model.type;

import javax.lang.model.UnknownEntityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnknownTypeException extends UnknownEntityException {
    private static final long serialVersionUID = 269;
    private transient Object parameter;
    private transient TypeMirror type;

    public UnknownTypeException(TypeMirror typeMirror, Object obj) {
        super("Unknown type: \"" + typeMirror + "\"");
        this.type = typeMirror;
        this.parameter = obj;
    }

    public Object getArgument() {
        return this.parameter;
    }

    public TypeMirror getUnknownType() {
        return this.type;
    }
}
