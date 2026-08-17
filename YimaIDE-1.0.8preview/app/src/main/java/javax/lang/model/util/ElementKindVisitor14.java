package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_26)
public class ElementKindVisitor14<R, P> extends ElementKindVisitor9<R, P> {
    public ElementKindVisitor14() {
        super(null);
    }

    public R visitRecordComponent(RecordComponentElement recordComponentElement, P p) {
        return (R) defaultAction(recordComponentElement, p);
    }

    public R visitTypeAsRecord(TypeElement typeElement, P p) {
        return (R) defaultAction(typeElement, p);
    }

    public R visitVariableAsBindingVariable(VariableElement variableElement, P p) {
        return (R) defaultAction(variableElement, p);
    }

    public ElementKindVisitor14(R r) {
        super(r);
    }
}
