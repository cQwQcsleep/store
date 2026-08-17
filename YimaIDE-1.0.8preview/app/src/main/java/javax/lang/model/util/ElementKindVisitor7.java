package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ElementKindVisitor7<R, P> extends ElementKindVisitor6<R, P> {
    @Deprecated
    public ElementKindVisitor7() {
        super(null);
    }

    @Override // javax.lang.model.util.ElementKindVisitor6
    public R visitVariableAsResourceVariable(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    @Deprecated
    public ElementKindVisitor7(R r) {
        super(r);
    }
}
