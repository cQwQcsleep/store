package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.RecordComponentElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_26)
public class SimpleElementVisitor14<R, P> extends SimpleElementVisitor9<R, P> {
    public SimpleElementVisitor14() {
        super(null);
    }

    public R visitRecordComponent(RecordComponentElement recordComponentElement, P p) {
        return (R) defaultAction(recordComponentElement, p);
    }

    public SimpleElementVisitor14(R r) {
        super(r);
    }
}
