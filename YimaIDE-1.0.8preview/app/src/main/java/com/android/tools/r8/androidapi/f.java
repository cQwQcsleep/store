package com.android.tools.r8.androidapi;

import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.utils.structural.k;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface f extends k<f> {
    public static final /* synthetic */ int a = 0;

    static {
        boolean z = e.a;
    }

    default boolean E() {
        return false;
    }

    default boolean G() {
        return false;
    }

    default a Y() {
        return null;
    }

    AbstractC2173nV a(EnumC3077y2 enumC3077y2);

    default boolean a(f fVar) {
        boolean z = e.a;
        if (!z && (G() || fVar.G())) {
            x01.a("Cannot compute relationship for not set");
            return false;
        }
        if (fVar.E()) {
            return false;
        }
        if (E()) {
            return true;
        }
        if (z || (v() && fVar.v())) {
            return Y().a().d(fVar.Y().a());
        }
        x1f.a();
        return false;
    }

    AbstractC2173nV b(EnumC3077y2 enumC3077y2);

    @Override // com.android.tools.r8.utils.structural.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    default boolean isEqualTo(f fVar) {
        return equals(fVar);
    }

    default boolean c(f fVar) {
        if (e.a || !(G() || fVar.G())) {
            return fVar.equals(this) || a(fVar);
        }
        x01.a("Cannot compute relationship for not set");
        return false;
    }

    AbstractC2173nV d(f fVar);

    default f e(f fVar) {
        return c(fVar) ? this : fVar;
    }

    default boolean v() {
        return false;
    }

    public static class a implements f {
        public static final a c = new a(EnumC3077y2.K);
        public static final /* synthetic */ boolean d = true;
        public final EnumC3077y2 b;

        public a(EnumC3077y2 enumC3077y2) {
            this.b = enumC3077y2;
        }

        @Override // com.android.tools.r8.androidapi.f
        public final a Y() {
            return this;
        }

        @Override // com.android.tools.r8.androidapi.f
        public final AbstractC2173nV a(EnumC3077y2 enumC3077y2) {
            return AbstractC2173nV.a(this.b.b(enumC3077y2));
        }

        @Override // com.android.tools.r8.androidapi.f
        public final AbstractC2173nV b(EnumC3077y2 enumC3077y2) {
            return AbstractC2173nV.a(this.b.d(enumC3077y2));
        }

        @Override // com.android.tools.r8.androidapi.f
        public final AbstractC2173nV d(f fVar) {
            if (fVar.v()) {
                return AbstractC2173nV.a(this.b.b(fVar.Y().a()));
            }
            if (d || fVar.E()) {
                return AbstractC2173nV.c;
            }
            x01.a("Cannot compute relationship for not set");
            return null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && this.b == ((a) obj).b;
        }

        public final int hashCode() {
            return Objects.hash(this.b);
        }

        public final String toString() {
            return this.b.toString();
        }

        @Override // com.android.tools.r8.androidapi.f
        public final boolean v() {
            return true;
        }

        public EnumC3077y2 a() {
            return this.b;
        }
    }
}
