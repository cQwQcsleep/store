package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ElementScanner7<R, P> extends ElementScanner6<R, P> {
    @Deprecated
    public ElementScanner7() {
        super(null);
    }

    @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
    public R visitVariable(VariableElement variableElement, P p) {
        return scan(variableElement.getEnclosedElements(), p);
    }

    @Deprecated
    public ElementScanner7(R r) {
        super(r);
    }
}
