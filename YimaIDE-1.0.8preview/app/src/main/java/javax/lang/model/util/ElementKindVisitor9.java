package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ModuleElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public class ElementKindVisitor9<R, P> extends ElementKindVisitor8<R, P> {
    public ElementKindVisitor9() {
        super(null);
    }

    public R visitModule(ModuleElement moduleElement, P p) {
        return (R) defaultAction(moduleElement, p);
    }

    public ElementKindVisitor9(R r) {
        super(r);
    }
}
