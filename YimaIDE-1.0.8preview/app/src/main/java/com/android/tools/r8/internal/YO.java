package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YO extends XO {
    public static final int[] b0 = {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -4, -3, -4, -3, -3, -3, -3, -1, -2, 1, 1, 1, 2, 2, 2, 0, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -2, -1, -2, -1, -2, 0, 1, 0, 1, -1, -1, 0, 0, 1, 1, -1, 0, -1, 0, 0, 0, -3, -1, -1, -3, -3, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, 0, 1, 0, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0};
    public L2 A;
    public L2 B;
    public int C;
    public L2[] D;
    public int E;
    public L2[] F;
    public L2 G;
    public L2 H;
    public X7 I;
    public int J;
    public X7 K;
    public H4 L;
    public final int M;
    public final WI N;
    public WI O;
    public WI P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int[] U;
    public int[] V;
    public boolean W;
    public boolean X;
    public int Y;
    public int Z;
    public int a0;
    public final Ag0 c;
    public final int d;
    public final int e;
    public final String f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public final X7 k;
    public C0523Gs l;
    public C0523Gs m;
    public int n;
    public X7 o;
    public int p;
    public X7 q;
    public int r;
    public X7 s;
    public int t;
    public X7 u;
    public L2 v;
    public L2 w;
    public final int x;
    public final int[] y;
    public final int z;

    public YO(Ag0 ag0, int i, String str, String str2, String str3, String[] strArr, int i2) {
        super(589824, null);
        this.k = new X7();
        this.c = ag0;
        this.d = "<init>".equals(str) ? 262144 | i : i;
        this.e = ag0.a(str);
        this.f = str;
        this.g = ag0.a(str2);
        this.h = str2;
        this.z = str3 == null ? 0 : ag0.a(str3);
        if (strArr == null || strArr.length <= 0) {
            this.x = 0;
            this.y = null;
        } else {
            int length = strArr.length;
            this.x = length;
            this.y = new int[length];
            for (int i3 = 0; i3 < this.x; i3++) {
                this.y[i3] = ag0.a(7, strArr[i3]).a;
            }
        }
        this.M = i2;
        if (i2 != 0) {
            int iC = C3050xi0.c(str2) >> 2;
            iC = (i & 8) != 0 ? iC - 1 : iC;
            this.j = iC;
            this.S = iC;
            WI wi = new WI();
            this.N = wi;
            a(wi);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        int i5 = 3;
        int i6 = this.M;
        if (i6 == 4) {
            return;
        }
        if (i6 == 3) {
            WI wi = this.P;
            C0962Xq c0962Xq = wi.k;
            if (c0962Xq == null) {
                C1765ih c1765ih = new C1765ih(wi);
                wi.k = c1765ih;
                c1765ih.a(this.c, this.d, this.h, i2);
                this.P.k.a(this);
            } else {
                if (i == -1) {
                    Ag0 ag0 = this.c;
                    int i7 = 0;
                    for (int i8 = 0; i8 < i2; i8++) {
                        int i9 = i7 + 1;
                        c0962Xq.b[i7] = C0962Xq.a(ag0, objArr[i8]);
                        if (objArr[i8] == 4 || objArr[i8] == 3) {
                            i7 += 2;
                            c0962Xq.b[i9] = 4194304;
                        } else {
                            i7 = i9;
                        }
                    }
                    while (true) {
                        int[] iArr = c0962Xq.b;
                        if (i7 >= iArr.length) {
                            break;
                        }
                        iArr[i7] = 4194304;
                        i7++;
                    }
                    int i10 = 0;
                    for (int i11 = 0; i11 < i3; i11++) {
                        if (objArr2[i11] == 4 || objArr2[i11] == 3) {
                            i10++;
                        }
                    }
                    c0962Xq.c = new int[i3 + i10];
                    int i12 = 0;
                    for (int i13 = 0; i13 < i3; i13++) {
                        int i14 = i12 + 1;
                        c0962Xq.c[i12] = C0962Xq.a(ag0, objArr2[i13]);
                        if (objArr2[i13] == 4 || objArr2[i13] == 3) {
                            i12 += 2;
                            c0962Xq.c[i14] = 4194304;
                        } else {
                            i12 = i14;
                        }
                    }
                    c0962Xq.g = (short) 0;
                    c0962Xq.h = 0;
                }
                this.P.k.a(this);
            }
        } else if (i == -1) {
            if (this.U == null) {
                int iC = C3050xi0.c(this.h) >> 2;
                C0962Xq c0962Xq2 = new C0962Xq(new WI());
                c0962Xq2.a(this.c, this.d, this.h, iC);
                c0962Xq2.a(this);
            }
            this.S = i2;
            a(this.k.b, i2, i3);
            int i15 = 0;
            while (i15 < i2) {
                this.V[i5] = C0962Xq.a(this.c, objArr[i15]);
                i15++;
                i5++;
            }
            int i16 = 0;
            while (i16 < i3) {
                this.V[i5] = C0962Xq.a(this.c, objArr2[i16]);
                i16++;
                i5++;
            }
            e();
        } else {
            if (this.c.c < 50) {
                w01.a("Class versions V1_5 or less must use F_NEW frames.");
                return;
            }
            if (this.u == null) {
                this.u = new X7();
                i4 = this.k.b;
            } else {
                i4 = (this.k.b - this.T) - 1;
                if (i4 < 0) {
                    if (i == 3) {
                        return;
                    }
                    g33.a();
                    return;
                }
            }
            if (i == 0) {
                this.S = i2;
                this.u.b(255).d(i4).d(i2);
                for (int i17 = 0; i17 < i2; i17++) {
                    b(objArr[i17]);
                }
                this.u.d(i3);
                for (int i18 = 0; i18 < i3; i18++) {
                    b(objArr2[i18]);
                }
            } else if (i == 1) {
                this.S += i2;
                this.u.b(i2 + 251).d(i4);
                for (int i19 = 0; i19 < i2; i19++) {
                    b(objArr[i19]);
                }
            } else if (i == 2) {
                this.S -= i2;
                this.u.b(251 - i2).d(i4);
            } else if (i == 3) {
                X7 x7 = this.u;
                if (i4 < 64) {
                    x7.b(i4);
                } else {
                    x7.b(251).d(i4);
                }
            } else {
                if (i != 4) {
                    j2d.a();
                    return;
                }
                X7 x8 = this.u;
                if (i4 < 64) {
                    x8.b(i4 + 64);
                } else {
                    x8.b(247).d(i4);
                }
                b(objArr2[0]);
            }
            this.T = this.k.b;
            this.t++;
        }
        if (this.M == 2) {
            this.Q = i3;
            for (int i20 = 0; i20 < i3; i20++) {
                if (objArr2[i20] == 4 || objArr2[i20] == 3) {
                    this.Q++;
                }
            }
            int i21 = this.Q;
            if (i21 > this.R) {
                this.R = i21;
            }
        }
        this.i = Math.max(this.i, i3);
        this.j = Math.max(this.j, this.S);
    }

    public final void b(Object obj) {
        if (obj instanceof Integer) {
            this.u.b(((Integer) obj).intValue());
            return;
        }
        boolean z = obj instanceof String;
        X7 x7 = this.u;
        if (z) {
            x7.b(7).d(this.c.a(7, (String) obj).a);
            return;
        }
        x7.b(8);
        WI wi = (WI) obj;
        X7 x8 = this.u;
        if ((wi.b & 4) == 0) {
            wi.a(0, 805306368, x8.b);
        }
        x8.d(wi.e);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, int i2) {
        WI wi;
        X7 x7;
        int i3 = this.M;
        if (i3 == 4) {
            C0523Gs c0523Gs = this.l;
            while (true) {
                if (c0523Gs == null) {
                    break;
                }
                String str = c0523Gs.e;
                int iB = 8388608 | this.c.b(str != null ? str : "java/lang/Throwable");
                WI wiA = c0523Gs.c.a();
                wiA.b = (short) (wiA.b | 2);
                WI wiA2 = c0523Gs.b.a();
                for (WI wiA3 = c0523Gs.a.a(); wiA3 != wiA2; wiA3 = wiA3.l) {
                    wiA3.m = new C0517Gm(iB, wiA, wiA3.m);
                }
                c0523Gs = c0523Gs.f;
            }
            C0962Xq c0962Xq = this.N.k;
            c0962Xq.a(this.c, this.d, this.h, this.j);
            c0962Xq.a(this);
            WI wi2 = this.N;
            wi2.n = WI.o;
            int iMax = 0;
            while (wi2 != WI.o) {
                WI wi3 = wi2.n;
                wi2.n = null;
                wi2.b = (short) (wi2.b | 8);
                int length = wi2.k.c.length + wi2.i;
                if (length > iMax) {
                    iMax = length;
                }
                for (C0517Gm c0517Gm = wi2.m; c0517Gm != null; c0517Gm = c0517Gm.c) {
                    WI wiA4 = c0517Gm.b.a();
                    if (wi2.k.a(this.c, wiA4.k, c0517Gm.a) && wiA4.n == null) {
                        wiA4.n = wi3;
                        wi3 = wiA4;
                    }
                }
                wi2 = wi3;
            }
            for (WI wi4 = this.N; wi4 != null; wi4 = wi4.l) {
                if ((wi4.b & 10) == 10) {
                    wi4.k.a(this);
                }
                if ((wi4.b & 8) == 0) {
                    WI wi5 = wi4.l;
                    int i4 = wi4.e;
                    int i5 = (wi5 == null ? this.k.b : wi5.e) - 1;
                    if (i5 >= i4) {
                        int i6 = i4;
                        while (true) {
                            x7 = this.k;
                            if (i6 >= i5) {
                                break;
                            }
                            x7.a[i6] = 0;
                            i6++;
                        }
                        x7.a[i5] = -65;
                        a(i4, 0, 1);
                        this.V[3] = this.c.b("java/lang/Throwable") | 8388608;
                        e();
                        this.l = C0523Gs.a(this.l, wi4, wi5);
                        iMax = Math.max(iMax, 1);
                    }
                }
            }
            this.i = iMax;
            return;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                this.i = this.R;
                return;
            } else {
                this.i = i;
                this.j = i2;
                return;
            }
        }
        for (C0523Gs c0523Gs2 = this.l; c0523Gs2 != null; c0523Gs2 = c0523Gs2.f) {
            WI wi6 = c0523Gs2.c;
            WI wi7 = c0523Gs2.b;
            for (WI wi8 = c0523Gs2.a; wi8 != wi7; wi8 = wi8.l) {
                int i7 = wi8.b & 16;
                C0517Gm c0517Gm2 = wi8.m;
                if (i7 == 0) {
                    wi8.m = new C0517Gm(Integer.MAX_VALUE, wi6, c0517Gm2);
                } else {
                    C0517Gm c0517Gm3 = c0517Gm2.c;
                    c0517Gm3.c = new C0517Gm(Integer.MAX_VALUE, wi6, c0517Gm3.c);
                }
            }
        }
        if (this.W) {
            WI wi9 = this.N;
            wi9.n = WI.o;
            while (wi9 != WI.o) {
                WI wi10 = wi9.n;
                wi9.n = null;
                if (wi9.j == 0) {
                    wi9.j = (short) 1;
                    for (C0517Gm c0517Gm4 = wi9.m; c0517Gm4 != null; c0517Gm4 = c0517Gm4.c) {
                        if ((wi9.b & 16) == 0 || c0517Gm4 != wi9.m.c) {
                            WI wi11 = c0517Gm4.b;
                            if (wi11.n == null) {
                                wi11.n = wi10;
                                wi10 = wi11;
                            }
                        }
                    }
                }
                wi9 = wi10;
            }
            short s = 1;
            short s2 = 1;
            while (true) {
                wi = this.N;
                if (s > s2) {
                    break;
                }
                while (wi != null) {
                    if ((wi.b & 16) != 0 && wi.j == s) {
                        WI wi12 = wi.m.c.b;
                        if (wi12.j == 0) {
                            s2 = (short) (s2 + 1);
                            wi12.n = WI.o;
                            while (wi12 != WI.o) {
                                WI wi13 = wi12.n;
                                wi12.n = null;
                                if (wi12.j == 0) {
                                    wi12.j = s2;
                                    for (C0517Gm c0517Gm5 = wi12.m; c0517Gm5 != null; c0517Gm5 = c0517Gm5.c) {
                                        if ((wi12.b & 16) == 0 || c0517Gm5 != wi12.m.c) {
                                            WI wi14 = c0517Gm5.b;
                                            if (wi14.n == null) {
                                                wi14.n = wi13;
                                                wi13 = wi14;
                                            }
                                        }
                                    }
                                }
                                wi12 = wi13;
                            }
                        }
                    }
                    wi = wi.l;
                }
                s = (short) (s + 1);
            }
            while (wi != null) {
                if ((wi.b & 16) != 0) {
                    WI wi15 = wi.m.c.b;
                    WI wi16 = WI.o;
                    wi15.n = wi16;
                    while (wi15 != WI.o) {
                        WI wi17 = wi15.n;
                        wi15.n = wi16;
                        if ((wi15.b & 64) != 0 && wi15.j != wi.j) {
                            wi15.m = new C0517Gm(wi15.h, wi.m.b, wi15.m);
                        }
                        for (C0517Gm c0517Gm6 = wi15.m; c0517Gm6 != null; c0517Gm6 = c0517Gm6.c) {
                            if ((wi15.b & 16) == 0 || c0517Gm6 != wi15.m.c) {
                                WI wi18 = c0517Gm6.b;
                                if (wi18.n == null) {
                                    wi18.n = wi17;
                                    wi17 = wi18;
                                }
                            }
                        }
                        wi16 = wi15;
                        wi15 = wi17;
                    }
                    while (wi16 != WI.o) {
                        WI wi19 = wi16.n;
                        wi16.n = null;
                        wi16 = wi19;
                    }
                }
                wi = wi.l;
            }
        }
        WI wi20 = this.N;
        wi20.n = WI.o;
        int i8 = this.i;
        while (wi20 != WI.o) {
            WI wi21 = wi20.n;
            short s3 = wi20.g;
            int i9 = wi20.i + s3;
            if (i9 > i8) {
                i8 = i9;
            }
            C0517Gm c0517Gm7 = wi20.m;
            if ((wi20.b & 16) != 0) {
                c0517Gm7 = c0517Gm7.c;
            }
            wi20 = wi21;
            while (c0517Gm7 != null) {
                WI wi22 = c0517Gm7.b;
                if (wi22.n == null) {
                    int i10 = c0517Gm7.a;
                    wi22.g = (short) (i10 == Integer.MAX_VALUE ? 1 : i10 + s3);
                    wi22.n = wi20;
                    wi20 = wi22;
                }
                c0517Gm7 = c0517Gm7.c;
            }
        }
        this.i = i8;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void d(int i, int i2) {
        X7 x7 = this.k;
        this.Y = x7.b;
        if (i2 < 4 && i != 169) {
            x7.b((i < 54 ? ((i - 21) << 2) + 26 : ((i - 54) << 2) + 59) + i2);
        } else if (i2 >= 256) {
            x7.b(196).c(i, i2);
        } else {
            x7.a(i, i2);
        }
        WI wi = this.P;
        if (wi != null) {
            int i3 = this.M;
            if (i3 == 4 || i3 == 3) {
                wi.k.a(i, i2, (C3130yg0) null, (Ag0) null);
            } else if (i == 169) {
                wi.b = (short) (wi.b | 64);
                wi.h = (short) this.Q;
                d();
            } else {
                int i4 = this.Q + b0[i];
                if (i4 > this.R) {
                    this.R = i4;
                }
                this.Q = i4;
            }
        }
        int i5 = this.M;
        if (i5 != 0) {
            int i6 = (i == 22 || i == 24 || i == 55 || i == 57) ? i2 + 2 : i2 + 1;
            if (i6 > this.j) {
                this.j = i6;
            }
        }
        if (i < 54 || i5 != 4 || this.l == null) {
            return;
        }
        a(new WI());
    }

    public final void e() {
        char c;
        int i;
        int[] iArr = this.U;
        if (iArr != null) {
            if (this.u == null) {
                this.u = new X7();
            }
            int[] iArr2 = this.V;
            boolean z = true;
            int i2 = iArr2[1];
            int i3 = iArr2[2];
            int i4 = 0;
            int i5 = 3;
            if (this.c.c < 50) {
                this.u.d(iArr2[0]).d(i2);
                int i6 = i2 + 3;
                e(3, i6);
                this.u.d(i3);
                e(i6, i3 + i6);
            } else {
                int i7 = this.t == 0 ? iArr2[0] : (iArr2[0] - iArr[0]) - 1;
                int i8 = iArr[1];
                int i9 = i2 - i8;
                if (i3 == 0) {
                    switch (i9) {
                        case -3:
                        case -2:
                        case -1:
                            c = 248;
                            break;
                        case 0:
                            c = i7 >= 64 ? (char) 251 : (char) 0;
                            break;
                        case 1:
                        case 2:
                        case XmlPullParser.END_TAG /* 3 */:
                            c = 252;
                            break;
                        default:
                            c = 255;
                            break;
                    }
                } else if (i9 == 0 && i3 == 1) {
                    c = i7 < 63 ? '@' : (char) 247;
                } else {
                    c = 255;
                }
                if (c != 255) {
                    int i10 = 3;
                    while (true) {
                        if (i4 >= i8 || i4 >= i2) {
                            i = i5;
                        } else {
                            boolean z2 = z;
                            i = i5;
                            if (this.V[i10] != this.U[i10]) {
                                c = 255;
                            } else {
                                i10++;
                                i4++;
                                z = z2;
                                i5 = i;
                            }
                        }
                    }
                } else {
                    i = i5;
                }
                if (c == 0) {
                    this.u.b(i7);
                } else if (c == '@') {
                    this.u.b(i7 + 64);
                    e(i2 + 3, i2 + 4);
                } else if (c == 247) {
                    this.u.b(247).d(i7);
                    e(i2 + 3, i2 + 4);
                } else if (c == 248) {
                    this.u.b(i9 + 251).d(i7);
                } else if (c != 251) {
                    X7 x7 = this.u;
                    if (c != 252) {
                        x7.b(255).d(i7).d(i2);
                        int i11 = i2 + 3;
                        e(i, i11);
                        this.u.d(i3);
                        e(i11, i3 + i11);
                    } else {
                        int i12 = i;
                        x7.b(i9 + 251).d(i7);
                        e(i8 + i12, i2 + i12);
                    }
                } else {
                    this.u.b(251).d(i7);
                }
            }
            this.t++;
        }
        this.U = this.V;
        this.V = null;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, String str) {
        if (this.K == null) {
            this.K = new X7();
        }
        this.J++;
        this.K.d(str == null ? 0 : this.c.a(str)).d(i);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, int i2) {
        X7 x7 = this.k;
        this.Y = x7.b;
        if (i == 17) {
            x7.c(i, i2);
        } else {
            x7.a(i, i2);
        }
        WI wi = this.P;
        if (wi != null) {
            int i3 = this.M;
            if (i3 == 4 || i3 == 3) {
                wi.k.a(i, i2, (C3130yg0) null, (Ag0) null);
            } else if (i != 188) {
                int i4 = this.Q + 1;
                if (i4 > this.R) {
                    this.R = i4;
                }
                this.Q = i4;
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 b(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2A = L2.a(ag0, i, c3052xj0, str, this.v);
            this.v = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, i, c3052xj0, str, this.w);
        this.w = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, WI wi) {
        if (this.o == null) {
            this.o = new X7();
        }
        this.n++;
        this.o.d(wi.e);
        this.o.d(i);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b() {
    }

    public final void d() {
        int i = this.M;
        if (i != 4) {
            if (i == 1) {
                this.P.i = (short) this.R;
                this.P = null;
                return;
            }
            return;
        }
        WI wi = new WI();
        wi.k = new C0962Xq(wi);
        X7 x7 = this.k;
        wi.a(x7.a, this.u, x7.b);
        this.O.l = wi;
        this.O = wi;
        this.P = null;
    }

    public final void e(int i, int i2) {
        while (i < i2) {
            Ag0 ag0 = this.c;
            int i3 = this.V[i];
            X7 x7 = this.u;
            int i4 = ((-67108864) & i3) >> 26;
            if (i4 == 0) {
                int i5 = i3 & 1048575;
                int i6 = i3 & 62914560;
                if (i6 == 4194304) {
                    x7.b(i5);
                } else if (i6 == 8388608) {
                    x7.b(7).d(ag0.a(7, ag0.l[i5].e).a);
                } else if (i6 == 12582912) {
                    x7.b(8).d((int) ag0.l[i5].f);
                } else if (i6 == 16777216) {
                    x7.b(8);
                    WI wi = ag0.n[(int) ag0.l[i5].f].b;
                    if ((wi.b & 4) == 0) {
                        wi.a(0, 805306368, x7.b);
                    }
                    x7.d(wi.e);
                } else {
                    x1f.a();
                    return;
                }
            } else {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int i7 = i4 - 1;
                    if (i4 > 0) {
                        sb.append('[');
                        i4 = i7;
                    } else {
                        if ((i3 & 62914560) == 8388608) {
                            sb.append('L');
                            sb.append(ag0.l[i3 & 1048575].e);
                            sb.append(';');
                        } else {
                            int i8 = i3 & 1048575;
                            if (i8 == 1) {
                                sb.append('I');
                            } else if (i8 == 2) {
                                sb.append('F');
                            } else if (i8 == 3) {
                                sb.append('D');
                            } else if (i8 != 4) {
                                switch (i8) {
                                    case 9:
                                        sb.append('Z');
                                        break;
                                    case XmlPullParser.DOCDECL /* 10 */:
                                        sb.append('B');
                                        break;
                                    case AndroidSdkVersion.HONEYCOMB /* 11 */:
                                        sb.append('C');
                                        break;
                                    case 12:
                                        sb.append('S');
                                        break;
                                    default:
                                        x1f.a();
                                        return;
                                }
                            } else {
                                sb.append('J');
                            }
                        }
                        x7.b(7).d(ag0.a(7, sb.toString()).a);
                    }
                }
            }
            i++;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(String str, boolean z) {
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2A = L2.a(ag0, str, this.A);
            this.A = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, str, this.B);
        this.B = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, boolean z) {
        if (z) {
            this.C = i;
        } else {
            this.E = i;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, String str, boolean z) {
        if (z) {
            if (this.D == null) {
                this.D = new L2[C3050xi0.a(this.h)];
            }
            L2[] l2Arr = this.D;
            L2 l2A = L2.a(this.c, str, l2Arr[i]);
            l2Arr[i] = l2A;
            return l2A;
        }
        if (this.F == null) {
            this.F = new L2[C3050xi0.a(this.h)];
        }
        L2[] l2Arr2 = this.F;
        L2 l2A2 = L2.a(this.c, str, l2Arr2[i]);
        l2Arr2[i] = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(H4 h4) {
        h4.getClass();
        h4.c = this.L;
        this.L = h4;
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a() {
        X7 x7 = new X7();
        this.I = x7;
        return new L2(this.c, false, x7, null);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i) {
        X7 x7 = this.k;
        this.Y = x7.b;
        x7.b(i);
        WI wi = this.P;
        if (wi != null) {
            int i2 = this.M;
            if (i2 != 4 && i2 != 3) {
                int i3 = this.Q + b0[i];
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            } else {
                wi.k.a(i, 0, (C3130yg0) null, (Ag0) null);
            }
            if ((i < 172 || i > 177) && i != 191) {
                return;
            }
            d();
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0046 A[PHI: r4
      0x0046: PHI (r4v7 int) = (r4v2 int), (r4v2 int), (r4v8 int) binds: [B:20:0x003f, B:21:0x0041, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:29:0x004f A[PHI: r4
      0x004f: PHI (r4v3 int) = (r4v1 int), (r4v4 int), (r4v4 int) binds: [B:27:0x004c, B:16:0x0038, B:17:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3) {
        int i2;
        this.Y = this.k.b;
        C3130yg0 c3130yg0A = this.c.a(9, str, str2, str3);
        this.k.c(i, c3130yg0A.a);
        WI wi = this.P;
        if (wi != null) {
            int i3 = this.M;
            int i4 = 0;
            if (i3 != 4 && i3 != 3) {
                char cCharAt = str3.charAt(0);
                switch (i) {
                    case 178:
                        i2 = this.Q;
                        if (cCharAt == 'D' || cCharAt == 'J') {
                            i4 = 2;
                        } else {
                            i4 = 1;
                        }
                        break;
                    case 179:
                        i2 = this.Q;
                        if (cCharAt == 'D' || cCharAt == 'J') {
                            i4 = -2;
                        } else {
                            i4 = -1;
                        }
                        break;
                    case 180:
                        i2 = this.Q;
                        if (cCharAt == 'D' || cCharAt == 'J') {
                            i4 = 1;
                        }
                        break;
                    default:
                        i2 = this.Q;
                        if (cCharAt == 'D' || cCharAt == 'J') {
                            i4 = -3;
                        } else {
                            i4 = -2;
                        }
                        break;
                }
                int i5 = i2 + i4;
                if (i5 > this.R) {
                    this.R = i5;
                }
                this.Q = i5;
                return;
            }
            wi.k.a(i, 0, c3130yg0A, this.c);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3, boolean z) {
        this.Y = this.k.b;
        Ag0 ag0 = this.c;
        ag0.getClass();
        C3130yg0 c3130yg0A = ag0.a(z ? 11 : 10, str, str2, str3);
        X7 x7 = this.k;
        if (i == 185) {
            X7 x7C = x7.c(185, c3130yg0A.a);
            if (c3130yg0A.g == 0) {
                c3130yg0A.g = C3050xi0.c(c3130yg0A.e);
            }
            x7C.a(c3130yg0A.g >> 2, 0);
        } else {
            x7.c(i, c3130yg0A.a);
        }
        WI wi = this.P;
        if (wi != null) {
            int i2 = this.M;
            if (i2 != 4 && i2 != 3) {
                if (c3130yg0A.g == 0) {
                    c3130yg0A.g = C3050xi0.c(c3130yg0A.e);
                }
                int i3 = c3130yg0A.g;
                int i4 = (i3 & 3) - (i3 >> 2);
                int i5 = this.Q;
                int i6 = i == 184 ? i5 + i4 + 1 : i5 + i4;
                if (i6 > this.R) {
                    this.R = i6;
                }
                this.Q = i6;
                return;
            }
            wi.k.a(i, 0, c3130yg0A, this.c);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        this.Y = this.k.b;
        Ag0 ag0 = this.c;
        C3130yg0 c3130yg0A = ag0.a(18, ag0.a(c0497Fs, objArr).a, str, str2);
        this.k.c(186, c3130yg0A.a);
        this.k.d(0);
        WI wi = this.P;
        if (wi != null) {
            int i = this.M;
            if (i != 4 && i != 3) {
                if (c3130yg0A.g == 0) {
                    c3130yg0A.g = C3050xi0.c(c3130yg0A.e);
                }
                int i2 = c3130yg0A.g;
                int i3 = this.Q + ((i2 & 3) - (i2 >> 2)) + 1;
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
                return;
            }
            wi.k.a(186, 0, c3130yg0A, this.c);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 c(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2A = L2.a(ag0, i, c3052xj0, str, this.G);
            this.G = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, i, c3052xj0, str, this.H);
        this.H = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, WI wi) {
        boolean z;
        X7 x7 = this.k;
        int i2 = x7.b;
        this.Y = i2;
        int i3 = i >= 200 ? i - 33 : i;
        if ((wi.b & 4) == 0 || wi.e - i2 >= -32768) {
            if (i3 != i) {
                x7.b(i);
                X7 x8 = this.k;
                wi.a(x8, x8.b - 1, true);
            } else {
                x7.b(i3);
                X7 x9 = this.k;
                wi.a(x9, x9.b - 1, false);
            }
            z = false;
        } else {
            if (i3 == 167) {
                x7.b(200);
            } else {
                if (i3 == 168) {
                    x7.b(201);
                } else {
                    x7.b(i3 >= 198 ? i3 ^ 1 : ((i3 + 1) ^ 1) - 1);
                    this.k.d(8);
                    this.k.b(220);
                    this.X = true;
                    z = true;
                }
                X7 x10 = this.k;
                wi.a(x10, x10.b - 1, true);
            }
            z = false;
            X7 x11 = this.k;
            wi.a(x11, x11.b - 1, true);
        }
        WI wi2 = this.P;
        if (wi2 != null) {
            int i4 = this.M;
            WI wi3 = null;
            if (i4 == 4) {
                wi2.k.a(i3, 0, (C3130yg0) null, (Ag0) null);
                WI wiA = wi.a();
                wiA.b = (short) (wiA.b | 2);
                c(0, wi);
                if (i3 != 167) {
                    wi3 = new WI();
                }
            } else if (i4 == 3) {
                wi2.k.a(i3, 0, (C3130yg0) null, (Ag0) null);
            } else if (i4 == 2) {
                this.Q += b0[i3];
            } else if (i3 == 168) {
                short s = wi.b;
                if ((s & 32) == 0) {
                    wi.b = (short) (s | 32);
                    this.W = true;
                }
                wi2.b = (short) (wi2.b | 16);
                c(this.Q + 1, wi);
                wi3 = new WI();
            } else {
                int i5 = this.Q + b0[i3];
                this.Q = i5;
                c(i5, wi);
            }
            if (wi3 != null) {
                if (z) {
                    wi3.b = (short) (wi3.b | 2);
                }
                a(wi3);
            }
            if (i3 == 167) {
                d();
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, String str) {
        this.Y = this.k.b;
        C3130yg0 c3130yg0A = this.c.a(7, str);
        this.k.c(i, c3130yg0A.a);
        WI wi = this.P;
        if (wi != null) {
            int i2 = this.M;
            if (i2 == 4 || i2 == 3) {
                wi.k.a(i, this.Y, c3130yg0A, this.c);
            } else if (i == 187) {
                int i3 = this.Q + 1;
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c() {
    }

    public final void c(int i, WI wi) {
        WI wi2 = this.P;
        wi2.m = new C0517Gm(i, wi, wi2.m);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi) {
        boolean z = this.X;
        X7 x7 = this.k;
        this.X = z | wi.a(x7.a, this.u, x7.b);
        short s = wi.b;
        if ((s & 1) != 0) {
            return;
        }
        int i = this.M;
        if (i == 4) {
            WI wi2 = this.P;
            if (wi2 != null) {
                if (wi.e == wi2.e) {
                    wi2.b = (short) (wi2.b | (s & 2));
                    wi.k = wi2.k;
                    return;
                }
                c(0, wi);
            }
            WI wi3 = this.O;
            if (wi3 != null) {
                if (wi.e == wi3.e) {
                    wi3.b = (short) (wi3.b | (wi.b & 2));
                    wi.k = wi3.k;
                    this.P = wi3;
                    return;
                }
                wi3.l = wi;
            }
            this.O = wi;
            this.P = wi;
            wi.k = new C0962Xq(wi);
            return;
        }
        if (i == 3) {
            WI wi4 = this.P;
            if (wi4 == null) {
                this.P = wi;
                return;
            } else {
                wi4.k.a = wi;
                return;
            }
        }
        if (i == 1) {
            WI wi5 = this.P;
            if (wi5 != null) {
                wi5.i = (short) this.R;
                c(this.Q, wi);
            }
            this.P = wi;
            this.Q = 0;
            this.R = 0;
            WI wi6 = this.O;
            if (wi6 != null) {
                wi6.l = wi;
            }
            this.O = wi;
            return;
        }
        if (i == 2 && this.P == null) {
            this.P = wi;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(Object obj) {
        char cCharAt;
        this.Y = this.k.b;
        C3130yg0 c3130yg0A = this.c.a(obj);
        int i = c3130yg0A.a;
        int i2 = c3130yg0A.b;
        boolean z = i2 == 5 || i2 == 6 || (i2 == 17 && ((cCharAt = c3130yg0A.e.charAt(0)) == 'J' || cCharAt == 'D'));
        if (z) {
            this.k.c(20, i);
        } else {
            X7 x7 = this.k;
            if (i >= 256) {
                x7.c(19, i);
            } else {
                x7.a(18, i);
            }
        }
        WI wi = this.P;
        if (wi != null) {
            int i3 = this.M;
            if (i3 != 4 && i3 != 3) {
                int i4 = this.Q + (z ? 2 : 1);
                if (i4 > this.R) {
                    this.R = i4;
                }
                this.Q = i4;
                return;
            }
            wi.k.a(18, 0, c3130yg0A, this.c);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2) {
        int i3;
        int i4;
        X7 x7 = this.k;
        this.Y = x7.b;
        if (i <= 255 && i2 <= 127 && i2 >= -128) {
            x7.b(132).a(i, i2);
        } else {
            x7.b(196).c(132, i).d(i2);
        }
        WI wi = this.P;
        if (wi != null && ((i4 = this.M) == 4 || i4 == 3)) {
            wi.k.a(132, i, (C3130yg0) null, (Ag0) null);
        }
        if (this.M == 0 || (i3 = i + 1) <= this.j) {
            return;
        }
        this.j = i3;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, WI wi, WI... wiArr) {
        X7 x7 = this.k;
        this.Y = x7.b;
        x7.b(170).a((byte[]) null, 0, (4 - (this.k.b % 4)) % 4);
        wi.a(this.k, this.Y, true);
        this.k.c(i).c(i2);
        for (WI wi2 : wiArr) {
            wi2.a(this.k, this.Y, true);
        }
        a(wi, wiArr);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, int[] iArr, WI[] wiArr) {
        X7 x7 = this.k;
        this.Y = x7.b;
        x7.b(171).a((byte[]) null, 0, (4 - (this.k.b % 4)) % 4);
        wi.a(this.k, this.Y, true);
        this.k.c(wiArr.length);
        for (int i = 0; i < wiArr.length; i++) {
            this.k.c(iArr[i]);
            wiArr[i].a(this.k, this.Y, true);
        }
        a(wi, wiArr);
    }

    public final void a(WI wi, WI[] wiArr) {
        WI wi2 = this.P;
        if (wi2 != null) {
            int i = this.M;
            if (i == 4) {
                wi2.k.a(171, 0, (C3130yg0) null, (Ag0) null);
                c(0, wi);
                WI wiA = wi.a();
                wiA.b = (short) (wiA.b | 2);
                for (WI wi3 : wiArr) {
                    c(0, wi3);
                    WI wiA2 = wi3.a();
                    wiA2.b = (short) (wiA2.b | 2);
                }
            } else if (i == 1) {
                int i2 = this.Q - 1;
                this.Q = i2;
                c(i2, wi);
                for (WI wi4 : wiArr) {
                    c(this.Q, wi4);
                }
            }
            d();
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str) {
        this.Y = this.k.b;
        C3130yg0 c3130yg0A = this.c.a(7, str);
        this.k.c(197, c3130yg0A.a).b(i);
        WI wi = this.P;
        if (wi != null) {
            int i2 = this.M;
            if (i2 != 4 && i2 != 3) {
                this.Q = (1 - i) + this.Q;
            } else {
                wi.k.a(197, i, c3130yg0A, this.c);
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2A = L2.a(ag0, (i & (-16776961)) | (this.Y << 8), c3052xj0, str, this.v);
            this.v = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, (i & (-16776961)) | (this.Y << 8), c3052xj0, str, this.w);
        this.w = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, WI wi2, WI wi3, String str) {
        C0523Gs c0523Gs = new C0523Gs(wi, wi2, wi3, str != null ? this.c.a(7, str).a : 0, str);
        if (this.l == null) {
            this.l = c0523Gs;
        } else {
            this.m.f = c0523Gs;
        }
        this.m = c0523Gs;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, String str3, WI wi, WI wi2, int i) {
        if (str3 != null) {
            if (this.s == null) {
                this.s = new X7();
            }
            this.r++;
            this.s.d(wi.e).d(wi2.e - wi.e).d(this.c.a(str)).d(this.c.a(str3)).d(i);
        }
        if (this.q == null) {
            this.q = new X7();
        }
        this.p++;
        this.q.d(wi.e).d(wi2.e - wi.e).d(this.c.a(str)).d(this.c.a(str2)).d(i);
        if (this.M != 0) {
            char cCharAt = str2.charAt(0);
            int i2 = i + ((cCharAt == 'J' || cCharAt == 'D') ? 2 : 1);
            if (i2 > this.j) {
                this.j = i2;
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, WI[] wiArr, WI[] wiArr2, int[] iArr, String str, boolean z) {
        X7 x7 = new X7();
        x7.b(i >>> 24).d(wiArr.length);
        for (int i2 = 0; i2 < wiArr.length; i2++) {
            x7.d(wiArr[i2].e).d(wiArr2[i2].e - wiArr[i2].e).d(iArr[i2]);
        }
        if (c3052xj0 == null) {
            x7.b(0);
        } else {
            byte[] bArr = c3052xj0.a;
            int i3 = c3052xj0.b;
            x7.a(bArr, i3, (bArr[i3] * 2) + 1);
        }
        x7.d(this.c.a(str)).d(0);
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2 = new L2(ag0, true, x7, this.v);
            this.v = l2;
            return l2;
        }
        L2 l3 = new L2(ag0, true, x7, this.w);
        this.w = l3;
        return l3;
    }

    public final void a(int i, int i2, int i3) {
        int i4 = i2 + 3 + i3;
        int[] iArr = this.V;
        if (iArr == null || iArr.length < i4) {
            this.V = new int[i4];
        }
        int[] iArr2 = this.V;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
    }
}
