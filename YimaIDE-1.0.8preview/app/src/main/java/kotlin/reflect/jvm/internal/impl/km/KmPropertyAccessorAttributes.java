package kotlin.reflect.jvm.internal.impl.km;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class KmPropertyAccessorAttributes {
    private final List<KmAnnotation> annotations;
    private int flags;

    public KmPropertyAccessorAttributes(int i) {
        this.flags = i;
        this.annotations = new ArrayList(0);
    }

    public final List<KmAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final int getFlags$org_jetbrains_kotlin_kotlin_metadata() {
        return this.flags;
    }

    public final void setFlags$org_jetbrains_kotlin_kotlin_metadata(int i) {
        this.flags = i;
    }

    public KmPropertyAccessorAttributes() {
        this(0);
    }
}
