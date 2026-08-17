package com.android.tools.r8.graph.proto;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3122yc0;
import com.android.tools.r8.internal.AbstractC3148ys;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class g extends com.android.tools.r8.graph.proto.b {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC3122yc0 b;
    public final I2 c;

    public static abstract class b<B extends b<B>> {
        public AbstractC3122yc0 a;
        public I2 b;
    }

    public g(AbstractC3122yc0 abstractC3122yc0, I2 i2) {
        if (!d && i2 == null) {
            x1f.a();
            throw null;
        }
        this.b = abstractC3122yc0;
        this.c = i2;
    }

    public static a e() {
        return new a();
    }

    @Override // com.android.tools.r8.graph.proto.b
    public final com.android.tools.r8.graph.proto.b a(com.android.tools.r8.graph.proto.b bVar) {
        if (d) {
            return this;
        }
        x01.a("Once the argument is removed one cannot modify it any further.");
        return null;
    }

    @Override // com.android.tools.r8.graph.proto.b
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public g a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        I2 i2E = abstractC3148ys.c(abstractC3148ys2, this.c);
        AbstractC3122yc0 abstractC3122yc0C = g() ? this.b.b(c0333y, i2E, abstractC3148ys, abstractC3148ys2) : null;
        return (abstractC3122yc0C == this.b && i2E == this.c) ? this : new g(abstractC3122yc0C, i2E);
    }

    @Override // com.android.tools.r8.graph.proto.b
    public final boolean c() {
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            g gVar = (g) obj;
            if (this.c == gVar.c && Objects.equals(this.b, gVar.b)) {
                return true;
            }
        }
        return false;
    }

    public I2 f() {
        return this.c;
    }

    public boolean g() {
        return this.b != null;
    }

    public int hashCode() {
        return Objects.hash(this.b, this.c);
    }

    public static class a extends b<a> {
        public g a() {
            return new g(this.a, this.b);
        }

        public final b b() {
            return this;
        }

        public b a(I2 i2) {
            this.b = i2;
            return b();
        }

        public b a(AbstractC3122yc0 abstractC3122yc0) {
            this.a = abstractC3122yc0;
            return b();
        }
    }

    @Override // com.android.tools.r8.graph.proto.b
    public final g a() {
        return this;
    }
}
