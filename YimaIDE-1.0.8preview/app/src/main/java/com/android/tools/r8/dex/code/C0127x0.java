package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.C0127x0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.jqi;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0127x0 extends P2 {
    public static final /* synthetic */ boolean i = true;
    public final int f;
    public final long g;
    public final short[] h;

    public C0127x0(int i2, A1 a1) {
        super(i2, a1);
        char cB = (char) (a1.b() & 65535);
        this.f = cB;
        long jB = (((char) (a1.b() & 65535)) & 65535) | ((((long) ((char) (65535 & a1.b()))) & 65535) << 16);
        this.g = jB;
        if (!i && ((long) cB) * jB >= 2147483647L) {
            x1f.a();
            throw null;
        }
        this.h = new short[((int) ((jB * ((long) cB)) + 1)) / 2];
        int i3 = 0;
        while (true) {
            short[] sArr = this.h;
            if (i3 >= sArr.length) {
                return;
            }
            sArr[i3] = (short) a1.b();
            i3++;
        }
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        int i2;
        StringBuilder sb = new StringBuilder("    .array-data ");
        sb.append(Wf0.a(this.f, 1));
        sb.append("  # ");
        sb.append(this.f);
        sb.append("\n");
        int i3 = this.f;
        int i4 = 0;
        if (i3 == 1) {
            for (int i5 = 0; i5 < this.h.length; i5++) {
                for (int i6 = 0; i6 < 2; i6++) {
                    int i7 = (this.h[i5] >> (i6 * 8)) & 255;
                    if ((i5 * 2) + i6 < this.g) {
                        sb.append("      ");
                        sb.append(Wf0.a(i7, 2));
                        sb.append("  # ");
                        sb.append(i7);
                        sb.append("\n");
                    }
                }
            }
        } else {
            if (!i && i3 != 2 && i3 != 4 && i3 != 8) {
                x1f.a();
                return null;
            }
            loop2: while (true) {
                long j = 0;
                do {
                    short[] sArr = this.h;
                    if (i4 >= sArr.length) {
                        break loop2;
                    }
                    long unsignedLong = Short.toUnsignedLong(sArr[i4]);
                    i2 = this.f;
                    j |= unsignedLong << ((i4 % (i2 / 2)) * 16);
                    i4++;
                } while ((i4 * 2) % i2 != 0);
                sb.append("      ");
                sb.append(Wf0.a(this.f * 2, j));
                sb.append("  # ");
                sb.append(j);
                sb.append("\n");
            }
        }
        sb.append("    .end array-data");
        return sb.toString();
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        return super.b(c1581ga0) + "[FillArrayPayload], width: " + this.f + ", size:  " + this.g;
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        int i2 = (this.f + 17036887) * 31;
        long j = this.g;
        return Arrays.hashCode(this.h) + ((i2 + ((int) (j ^ (j >>> 32)))) * 31);
    }

    @Override // com.android.tools.r8.dex.code.J, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int t() {
        return this.h.length + 4;
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        jqi jqiVar = new jqi();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        jqiVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public C0127x0(int i2, long j, short[] sArr) {
        this.f = i2;
        this.g = j;
        this.h = sArr;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: kqi
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C0127x0) obj).f;
            }
        }).a(new ToLongFunction() { // from class: lqi
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((C0127x0) obj).g;
            }
        }).k(new Function() { // from class: mqi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0127x0) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (C0127x0) abstractC0138z1, new jqi());
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(3, 0, shortBuffer);
        shortBuffer.put((short) this.f);
        AbstractC0138z1.a(this.g, shortBuffer);
        for (short s : this.h) {
            shortBuffer.put(s);
        }
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
    }
}
