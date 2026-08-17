package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.RecordComponentElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_26)
public abstract class AbstractElementVisitor14<R, P> extends AbstractElementVisitor9<R, P> {
    public abstract R visitRecordComponent(RecordComponentElement recordComponentElement, P p);
}
