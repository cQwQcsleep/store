package com.android.tools.r8.graph.proto;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3122yc0;
import com.android.tools.r8.internal.AbstractC3148ys;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class k extends b {
    public static final /* synthetic */ boolean f = true;
    public final I2 b;
    public final I2 c;
    public final I2 d;
    public final AbstractC3122yc0 e;

    public k(I2 i2, I2 i3, I2 i4, AbstractC3122yc0 abstractC3122yc0) {
        this.b = i4;
        this.c = i2;
        this.d = i3;
        this.e = abstractC3122yc0;
    }

    public static a e() {
        return new a();
    }

    public final k a(k kVar) {
        boolean z = f;
        if (!z && f().W0()) {
            x1f.a();
            return null;
        }
        if (z || f() == kVar.g()) {
            return new k(g(), kVar.f(), this.b, kVar.e);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.graph.proto.b
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final k a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        I2 i2 = this.b;
        I2 i2C = i2 != null ? abstractC3148ys.c(abstractC3148ys2, i2) : null;
        I2 i2C2 = abstractC3148ys.c(abstractC3148ys2, this.d);
        AbstractC3122yc0 abstractC3122yc0 = this.e;
        AbstractC3122yc0 abstractC3122yc0B = abstractC3122yc0 != null ? abstractC3122yc0.b(c0333y, i2C2, abstractC3148ys, abstractC3148ys2) : null;
        return (i2C == this.b && i2C2 == this.d && abstractC3122yc0B == this.e) ? this : new k(this.c, i2C2, i2C, abstractC3122yc0B);
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            k kVar = (k) obj;
            if (this.c == kVar.c && this.d == kVar.d && Objects.equals(this.e, kVar.e)) {
                return true;
            }
        }
        return false;
    }

    public I2 f() {
        return this.d;
    }

    public I2 g() {
        return this.c;
    }

    public final int hashCode() {
        return Objects.hash(this.c, this.d, this.e);
    }

    public static class a {
        public I2 a;
        public I2 b;
        public I2 c;
        public AbstractC3122yc0 d;

        public k a() {
            return new k(this.b, this.c, this.a, this.d);
        }

        public a b(I2 i2) {
            this.b = i2;
            return this;
        }

        public a a(I2 i2) {
            this.c = i2;
            return this;
        }

        public final a a(boolean z, Consumer consumer) {
            if (z) {
                consumer.accept(this);
            }
            return this;
        }
    }

    @Override // com.android.tools.r8.graph.proto.b
    public final k b() {
        return this;
    }

    @Override // com.android.tools.r8.graph.proto.b
    public final b a(b bVar) {
        if (bVar.c()) {
            return bVar;
        }
        if (!f && !(bVar instanceof k)) {
            x1f.a();
            return null;
        }
        return a(bVar.b());
    }
}
