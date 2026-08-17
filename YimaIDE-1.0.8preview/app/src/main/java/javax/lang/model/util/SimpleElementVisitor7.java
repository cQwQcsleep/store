package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SimpleElementVisitor7<R, P> extends SimpleElementVisitor6<R, P> {
    @Deprecated
    public SimpleElementVisitor7() {
        super((Object) null);
    }

    public R visitVariable(VariableElement variableElement, P p) {
        return (R) defaultAction(variableElement, p);
    }

    @Deprecated
    public SimpleElementVisitor7(R r) {
        super(r);
    }
}
