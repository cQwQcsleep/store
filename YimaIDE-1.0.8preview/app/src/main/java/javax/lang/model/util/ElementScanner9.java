package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ModuleElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public class ElementScanner9<R, P> extends ElementScanner8<R, P> {
    public ElementScanner9() {
        super(null);
    }

    @Override // javax.lang.model.util.AbstractElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitModule(ModuleElement moduleElement, P p) {
        return scan(moduleElement.getEnclosedElements(), p);
    }

    public ElementScanner9(R r) {
        super(r);
    }
}
