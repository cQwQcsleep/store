package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ModuleElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public class SimpleElementVisitor9<R, P> extends SimpleElementVisitor8<R, P> {
    public SimpleElementVisitor9() {
        super(null);
    }

    public R visitModule(ModuleElement moduleElement, P p) {
        return (R) defaultAction(moduleElement, p);
    }

    public SimpleElementVisitor9(R r) {
        super(r);
    }
}
