package javax.lang.model.element;

import javax.lang.model.UnknownEntityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnknownAnnotationValueException extends UnknownEntityException {
    private static final long serialVersionUID = 269;
    private transient AnnotationValue av;
    private transient Object parameter;

    public UnknownAnnotationValueException(AnnotationValue annotationValue, Object obj) {
        super("Unknown annotation value: \"" + annotationValue + "\"");
        this.av = annotationValue;
        this.parameter = obj;
    }

    public Object getArgument() {
        return this.parameter;
    }

    public AnnotationValue getUnknownAnnotationValue() {
        return this.av;
    }
}
