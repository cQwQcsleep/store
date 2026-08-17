package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.Z0;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.c6g;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Z0 extends L {
    public static final /* synthetic */ boolean i = true;
    public final short f;
    public final char g;
    public final com.android.tools.r8.graph.X3 h;

    public Z0(int i2, int i3, com.android.tools.r8.graph.X3 x3) {
        boolean z = i;
        if (!z && (i2 < 0 || i2 > 65535)) {
            x1f.a();
            throw null;
        }
        if (!z && (i3 < 0 || i3 > 255)) {
            x1f.a();
            throw null;
        }
        this.g = (char) i2;
        this.f = (short) i3;
        this.h = x3;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        StringBuilder sb = new StringBuilder("{ v");
        char c = this.g;
        sb.append((int) c);
        if (this.f != 1) {
            sb.append(" .. v");
            sb.append((c + this.f) - 1);
        }
        sb.append(" }, ");
        sb.append(this.h.l0());
        return a(sb.toString());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        StringBuilder sb = new StringBuilder("{ v");
        char c = this.g;
        sb.append((int) c);
        if (this.f != 1) {
            sb.append(" .. v");
            sb.append((c + this.f) - 1);
        }
        sb.append(" } ");
        sb.append(c1581ga0.a(this.h));
        return b(sb.toString());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ (((this.g << 24) | (this.h.hashCode() << 4)) | this.f);
    }

    public Z0(int i2, A1 a1, com.android.tools.r8.graph.X3[] x3Arr) {
        super(a1);
        this.f = (short) i2;
        this.h = x3Arr[(char) (a1.b() & 65535)];
        this.g = (char) (a1.b() & 65535);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: d6g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Z0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: e6g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Z0) obj).g;
            }
        }).e(new Function() { // from class: f6g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Z0) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        c6g c6gVar = new c6g();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        c6gVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (Z0) abstractC0138z1, new c6g());
    }
}
