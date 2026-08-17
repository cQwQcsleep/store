package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.NoType;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public class TypeKindVisitor9<R, P> extends TypeKindVisitor8<R, P> {
    public TypeKindVisitor9() {
        super(null);
    }

    public R visitNoTypeAsModule(NoType noType, P p) {
        return (R) defaultAction(noType, p);
    }

    public TypeKindVisitor9(R r) {
        super(r);
    }
}
