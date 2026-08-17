package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ModuleElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public abstract class AbstractElementVisitor9<R, P> extends AbstractElementVisitor8<R, P> {
    public abstract R visitModule(ModuleElement moduleElement, P p);
}
