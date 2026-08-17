package com.android.tools.r8.shaking;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.n3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3432n3 extends Y2 {
    public final EnumC3447q3 r;
    public final C3437o3 s;

    public C3432n3(Origin origin, Position position, String str, List list, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, List list2, K3 k3, boolean z2, List list3, EnumC3447q3 enumC3447q3, C3437o3 c3437o3) {
        super(origin, position, str, list, c3470v2, c3470v3, z, o2, f2, list2, k3, z2, list3);
        this.r = enumC3447q3;
        this.s = c3437o3;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final String C() {
        return this.s.toString();
    }

    @Override // com.android.tools.r8.shaking.Y2
    public String D() {
        return this.r.toString();
    }

    public C3437o3 F() {
        return this.s;
    }

    public EnumC3447q3 G() {
        return this.r;
    }

    @Override // com.android.tools.r8.shaking.Y2, com.android.tools.r8.shaking.I2
    public boolean equals(Object obj) {
        if (!(obj instanceof C3432n3)) {
            return false;
        }
        C3432n3 c3432n3 = (C3432n3) obj;
        if (this.r == c3432n3.r && this.s.equals(c3432n3.s)) {
            return super.equals(c3432n3);
        }
        return false;
    }

    @Override // com.android.tools.r8.shaking.Y2, com.android.tools.r8.shaking.I2
    public int hashCode() {
        return ((this.s.hashCode() + (this.r.hashCode() * 3)) * 3) + super.hashCode();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.shaking.n3$a */
    public static abstract class a<C extends C3432n3, B extends a<C, B>> extends I2.a<C, B> {
        public EnumC3447q3 p;
        public final C3437o3.a q = C3437o3.a();

        public final a a(Consumer consumer) {
            consumer.accept(this.q);
            return (a) c();
        }

        public B a(EnumC3447q3 enumC3447q3) {
            this.p = enumC3447q3;
            return (B) c();
        }
    }
}
