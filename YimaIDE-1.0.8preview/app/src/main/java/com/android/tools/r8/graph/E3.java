package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B3;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0929Wj;
import java.lang.reflect.GenericSignatureFormatError;
import java.nio.CharBuffer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E3 {
    public static final /* synthetic */ boolean g = true;
    public char a;
    public String b;
    public boolean c;
    public char[] d;
    public int e;
    public final B1 f;

    public E3(B1 b1) {
        this.f = b1;
    }

    public final void a(Consumer consumer) {
        if (this.a != '<') {
            return;
        }
        f();
        while (true) {
            char c = this.a;
            if (c == '>' || c <= 0) {
                break;
            }
            e();
            if (!g && this.b == null) {
                x1f.a();
                return;
            }
            String str = this.b;
            a(':');
            B3.e eVarC = B3.e;
            char c2 = this.a;
            if (c2 == 'L' || c2 == '[' || c2 == 'T') {
                eVarC = c();
            }
            C0473Eu c0473EuG = null;
            while (this.a == ':') {
                if (c0473EuG == null) {
                    c0473EuG = AbstractC0551Hu.g();
                }
                f();
                c0473EuG.a(c());
            }
            consumer.accept(c0473EuG == null ? new B3.f(str, eVarC, B3.b) : new B3.f(str, eVarC, c0473EuG.a()));
        }
        a('>');
    }

    public final B3.c b() {
        B3.e eVarA;
        B3.e eVarA2;
        B3.e eVarA3;
        B3.e eVarA4;
        a('L');
        StringBuilder sb = new StringBuilder();
        e();
        if (!g && this.b == null) {
            x1f.a();
            return null;
        }
        while (true) {
            char c = this.a;
            String str = this.b;
            if (c != '/') {
                sb.append(str);
                I2 i2E = this.f.e(C0929Wj.l(sb.toString()));
                C0473Eu c0473EuG = AbstractC0551Hu.g();
                if (this.a == '<') {
                    f();
                    char c2 = this.a;
                    if (c2 == '*') {
                        f();
                        eVarA3 = F3.b;
                    } else if (c2 == '+') {
                        f();
                        eVarA3 = c().a(B3.k.e);
                    } else if (c2 == '-') {
                        f();
                        eVarA3 = c().a(B3.k.d);
                    } else {
                        eVarA3 = c().a(B3.k.c);
                    }
                    c0473EuG.a(eVarA3);
                    while (true) {
                        char c3 = this.a;
                        if (c3 == '>' || c3 <= 0) {
                            break;
                        }
                        if (c3 == '*') {
                            f();
                            eVarA4 = F3.b;
                        } else if (c3 == '+') {
                            f();
                            eVarA4 = c().a(B3.k.e);
                        } else if (c3 == '-') {
                            f();
                            eVarA4 = c().a(B3.k.d);
                        } else {
                            eVarA4 = c().a(B3.k.c);
                        }
                        c0473EuG.a(eVarA4);
                    }
                    a('>');
                }
                AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
                if (abstractC0551HuA.isEmpty()) {
                    abstractC0551HuA = B3.b;
                }
                B3.c cVar = new B3.c(i2E, abstractC0551HuA, null, B3.k.b);
                B3.c cVar2 = null;
                while (this.a == '.') {
                    f();
                    e();
                    if (!g && this.b == null) {
                        x1f.a();
                        return null;
                    }
                    i2E = a(this.b, i2E);
                    C0473Eu c0473EuG2 = AbstractC0551Hu.g();
                    if (this.a == '<') {
                        f();
                        char c4 = this.a;
                        if (c4 == '*') {
                            f();
                            eVarA = F3.b;
                        } else if (c4 == '+') {
                            f();
                            eVarA = c().a(B3.k.e);
                        } else if (c4 == '-') {
                            f();
                            eVarA = c().a(B3.k.d);
                        } else {
                            eVarA = c().a(B3.k.c);
                        }
                        c0473EuG2.a(eVarA);
                        while (true) {
                            char c5 = this.a;
                            if (c5 == '>' || c5 <= 0) {
                                break;
                            }
                            if (c5 == '*') {
                                f();
                                eVarA2 = F3.b;
                            } else if (c5 == '+') {
                                f();
                                eVarA2 = c().a(B3.k.e);
                            } else if (c5 == '-') {
                                f();
                                eVarA2 = c().a(B3.k.d);
                            } else {
                                eVarA2 = c().a(B3.k.c);
                            }
                            c0473EuG2.a(eVarA2);
                        }
                        a('>');
                    }
                    AbstractC0551Hu abstractC0551HuA2 = c0473EuG2.a();
                    if (abstractC0551HuA2.isEmpty()) {
                        abstractC0551HuA2 = B3.b;
                    }
                    cVar2 = new B3.c(i2E, abstractC0551HuA2, cVar, B3.k.b);
                    cVar = cVar2;
                }
                a(';');
                return cVar2 != null ? cVar2 : cVar;
            }
            sb.append(str);
            sb.append(this.a);
            f();
            e();
            if (!g && this.b == null) {
                x1f.a();
                return null;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0030  */
    public final B3.e c() {
        B3.i iVarC;
        char c = this.a;
        if (c == 'L') {
            return b();
        }
        if (c == 'T') {
            a('T');
            e();
            if (g || this.b != null) {
                a(';');
                return new B3.j(this.b, B3.k.b);
            }
            x1f.a();
            return null;
        }
        if (c != '[') {
            a(this.e, "Expected L, [ or T");
            throw null;
        }
        f();
        char c2 = this.a;
        if (c2 != 'F' && c2 != 'S' && c2 != 'Z' && c2 != 'I' && c2 != 'J') {
            switch (c2) {
                case 'B':
                case 'C':
                case 'D':
                    C3 c3 = new C3(this.f.e(String.valueOf(c2)));
                    f();
                    iVarC = c3;
                    break;
                default:
                    iVarC = c();
                    break;
            }
        } else {
            C3 c4 = new C3(this.f.e(String.valueOf(c2)));
            f();
            iVarC = c4;
        }
        B3.a aVarF = iVarC.f();
        aVarF.getClass();
        return aVarF;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0039  */
    /* JADX WARN: Code duplicated, block: B:25:0x006d  */
    public final B3.g d() {
        B3.h hVar;
        B3.i iVarC;
        Object objC;
        final C0473Eu c0473EuG = AbstractC0551Hu.g();
        a(new Consumer() { // from class: f34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0473EuG.a((B3.f) obj);
            }
        });
        a('(');
        C0473Eu c0473EuG2 = AbstractC0551Hu.g();
        while (true) {
            char c = this.a;
            if (c != ')' && c > 0) {
                if (c != 'F' && c != 'S' && c != 'Z' && c != 'I' && c != 'J') {
                    switch (c) {
                        case 'B':
                        case 'C':
                        case 'D':
                            C3 c3 = new C3(this.f.e(String.valueOf(c)));
                            f();
                            objC = c3;
                            break;
                        default:
                            objC = c();
                            break;
                    }
                } else {
                    C3 c4 = new C3(this.f.e(String.valueOf(c)));
                    f();
                    objC = c4;
                }
                c0473EuG2.a(objC);
            }
        }
        a(')');
        char c2 = this.a;
        if (c2 != 'V') {
            if (c2 != 'F' && c2 != 'S' && c2 != 'Z' && c2 != 'I' && c2 != 'J') {
                switch (c2) {
                    case 'B':
                    case 'C':
                    case 'D':
                        C3 c5 = new C3(this.f.e(String.valueOf(c2)));
                        f();
                        iVarC = c5;
                        break;
                    default:
                        iVarC = c();
                        break;
                }
            } else {
                C3 c6 = new C3(this.f.e(String.valueOf(c2)));
                f();
                iVarC = c6;
            }
            hVar = new B3.h(iVarC);
        } else {
            f();
            hVar = B3.h.b;
        }
        C0473Eu c0473EuG3 = AbstractC0551Hu.g();
        if (this.a == '^') {
            do {
                f();
                if (this.a == 'T') {
                    a('T');
                    e();
                    if (!g && this.b == null) {
                        x1f.a();
                        return null;
                    }
                    a(';');
                    c0473EuG3.a(new B3.j(this.b, B3.k.b));
                } else {
                    c0473EuG3.a(b());
                }
            } while (this.a == '^');
        }
        return new B3.g(c0473EuG.a(), c0473EuG2.a(), hVar, c0473EuG3.a());
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1093)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public final void e() {
        /*
            r8 = this;
            boolean r0 = r8.c
            r1 = 0
            if (r0 != 0) goto L79
            int r0 = r8.e
            char[] r2 = r8.d
            int r2 = r2.length
            if (r0 >= r2) goto L79
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r2 = 32
            r0.<init>(r2)
            char r2 = r8.a
            r3 = 0
            r4 = 46
            r5 = 1
            if (r2 == r4) goto L6d
            r6 = 47
            if (r2 == r6) goto L6d
            switch(r2) {
                case 58: goto L6d;
                case 59: goto L6d;
                case 60: goto L6d;
                default: goto L22;
            }
        L22:
            r0.append(r2)
            char[] r1 = r8.d
            boolean r2 = com.android.tools.r8.graph.E3.g
            if (r2 != 0) goto L32
            if (r1 == 0) goto L2e
            goto L32
        L2e:
            x1f.a()
            return
        L32:
            int r2 = r8.e
            char r2 = r1[r2]
            r7 = 97
            if (r2 < r7) goto L3e
            r7 = 122(0x7a, float:1.71E-43)
            if (r2 <= r7) goto L4d
        L3e:
            r7 = 65
            if (r2 < r7) goto L46
            r7 = 90
            if (r2 <= r7) goto L4d
        L46:
            if (r2 == r4) goto L63
            if (r2 == r6) goto L63
            switch(r2) {
                case 58: goto L63;
                case 59: goto L63;
                case 60: goto L63;
                default: goto L4d;
            }
        L4d:
            r0.append(r2)
            int r2 = r8.e
            int r2 = r2 + r5
            r8.e = r2
            int r7 = r1.length
            if (r2 != r7) goto L32
            java.lang.String r0 = r0.toString()
            r8.b = r0
            r8.a = r3
            r8.c = r5
            return
        L63:
            java.lang.String r0 = r0.toString()
            r8.b = r0
            r8.f()
            return
        L6d:
            r8.a = r3
            r8.c = r5
            int r0 = r8.e
            java.lang.String r2 = "Unexpected"
            r8.a(r0, r2)
            throw r1
        L79:
            int r0 = r8.e
            java.lang.String r2 = "Unexpected end of signature"
            r8.a(r0, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.tools.r8.graph.E3.e():void");
    }

    public final void f() {
        if (this.c) {
            a(this.e, "Unexpected end of signature");
            throw null;
        }
        if (!g && this.d == null) {
            x1f.a();
            return;
        }
        int i = this.e;
        char[] cArr = this.d;
        if (i < cArr.length) {
            this.a = cArr[i];
            this.e = i + 1;
        } else {
            this.a = (char) 0;
            this.c = true;
        }
    }

    public final I2 a(String str, I2 i2) {
        if (i2 == null) {
            return null;
        }
        if (!g && !i2.M0()) {
            x1f.a();
            return null;
        }
        String strZ0 = i2.Z0();
        return this.f.e(C0929Wj.l(C0929Wj.i(strZ0) + "$" + str));
    }

    public final B3.b a() {
        B3.b bVar = B3.b.d;
        final D3 d3 = new D3();
        a(new Consumer() { // from class: g34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                d3.a((B3.f) obj);
            }
        });
        d3.b = b();
        while (this.a > 0) {
            d3.c.add(b());
        }
        return d3.a(this.f);
    }

    public final B3.g c(String str) {
        try {
            this.d = str.toCharArray();
            this.c = false;
            this.e = 0;
            this.a = (char) 0;
            this.b = null;
            f();
            return d();
        } catch (GenericSignatureFormatError e) {
            throw e;
        } catch (Throwable th) {
            GenericSignatureFormatError genericSignatureFormatError = new GenericSignatureFormatError("Unknown error parsing method signature: " + th.getMessage());
            genericSignatureFormatError.addSuppressed(th);
            throw genericSignatureFormatError;
        }
    }

    public final B3.b a(String str) {
        try {
            this.d = str.toCharArray();
            this.c = false;
            this.e = 0;
            this.a = (char) 0;
            this.b = null;
            f();
            return a();
        } catch (GenericSignatureFormatError e) {
            throw e;
        } catch (Throwable th) {
            GenericSignatureFormatError genericSignatureFormatError = new GenericSignatureFormatError("Unknown error parsing class signature: " + th.getMessage());
            genericSignatureFormatError.addSuppressed(th);
            throw genericSignatureFormatError;
        }
    }

    public final void a(char c) {
        if (!this.c) {
            if (this.a == c) {
                f();
                return;
            }
            a(this.e - 1, "Expected " + c);
            throw null;
        }
        a(this.e, "Unexpected end of signature");
        throw null;
    }

    public final void a(int i, String str) {
        String str2 = CharBuffer.allocate(i).toString().replace((char) 0, ' ') + "^";
        throw new GenericSignatureFormatError(str + " at position " + (i + 1) + System.lineSeparator() + String.valueOf(this.d) + System.lineSeparator() + str2);
    }

    public final B3.e b(String str) {
        try {
            this.d = str.toCharArray();
            this.c = false;
            this.e = 0;
            this.a = (char) 0;
            this.b = null;
            f();
            return c();
        } catch (GenericSignatureFormatError e) {
            throw e;
        } catch (Throwable th) {
            GenericSignatureFormatError genericSignatureFormatError = new GenericSignatureFormatError("Unknown error parsing field signature: " + th.getMessage());
            genericSignatureFormatError.addSuppressed(th);
            throw genericSignatureFormatError;
        }
    }
}
