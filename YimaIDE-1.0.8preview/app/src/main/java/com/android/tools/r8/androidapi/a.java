package com.android.tools.r8.androidapi;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.EnumC3077y2;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class a {
    public final f.a[] a = new f.a[EnumC3077y2.M.d() + 1];

    public a() {
        for (EnumC3077y2 enumC3077y2 : EnumC3077y2.values()) {
            if (enumC3077y2 != EnumC3077y2.K) {
                this.a[enumC3077y2.d()] = new f.a(enumC3077y2);
            }
        }
    }

    public static a a(C0333y<?> c0333y) {
        return c0333y.M().a().a ? new C0001a(c0333y) : new b();
    }

    public abstract f a(F2 f2, f fVar);

    public abstract f a(AbstractC0287r2 abstractC0287r2);

    public abstract f a(Iterable iterable);

    public abstract boolean a();

    public void b() {
    }

    /* JADX INFO: renamed from: com.android.tools.r8.androidapi.a$a, reason: collision with other inner class name */
    public static class C0001a extends a {
        public final c b;
        public final f.a c;
        public final C2742u50 d;

        public C0001a(C0333y c0333y) {
            f.a aVar;
            this.b = c.a(c0333y, this);
            EnumC3077y2 enumC3077y2F = c0333y.M().F();
            if (enumC3077y2F == EnumC3077y2.K) {
                int i = f.a;
                aVar = f.a.c;
            } else {
                aVar = this.a[enumC3077y2F.d()];
            }
            this.c = aVar;
            this.d = c0333y.M().i;
        }

        @Override // com.android.tools.r8.androidapi.a
        public final f a(Iterable iterable) {
            h hVar = h.b;
            f fVarE = this.c;
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                I2 i2 = (I2) it.next();
                c cVar = this.b;
                if (!c.g) {
                    cVar.getClass();
                    if (fVarE.G()) {
                        x1f.a();
                        return null;
                    }
                }
                fVarE = cVar.a(i2, hVar, false).e(fVarE);
            }
            return fVarE;
        }

        @Override // com.android.tools.r8.androidapi.a
        public final void b() {
            this.b.e.forEach(new Consumer() { // from class: uag
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b((F2) obj);
                }
            });
        }

        public final /* synthetic */ void b(F2 f2) {
            this.d.warning(new AndroidApiUnknownReferenceDiagnostic(f2));
        }

        @Override // com.android.tools.r8.androidapi.a
        public final boolean a() {
            return true;
        }

        @Override // com.android.tools.r8.androidapi.a
        public final f a(F2 f2, f fVar) {
            return this.b.a(f2, fVar, false);
        }

        @Override // com.android.tools.r8.androidapi.a
        public final f a(AbstractC0287r2 abstractC0287r2) {
            return this.b.a(abstractC0287r2, h.b, true);
        }
    }

    public f a(F2 f2) {
        int i = f.a;
        return a(f2, h.b);
    }

    public f a(C2752uB c2752uB) {
        if (c2752uB.F() == EnumC3077y2.K) {
            int i = f.a;
            return f.a.c;
        }
        return new f.a(c2752uB.F());
    }
}
