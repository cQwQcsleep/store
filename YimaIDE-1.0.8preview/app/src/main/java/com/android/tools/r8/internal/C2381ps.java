package com.android.tools.r8.internal;

import java.lang.reflect.GenericSignatureFormatError;
import java.nio.CharBuffer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ps, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2381ps<T> {
    public static final /* synthetic */ boolean g = true;
    public final InterfaceC2295os a;
    public char b;
    public String c;
    public boolean d;
    public char[] e;
    public int f;

    public C2381ps(InterfaceC2295os<T> interfaceC2295os) {
        this.a = interfaceC2295os;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(InterfaceC2295os.a aVar) {
        char c;
        char c2;
        this.a.a(this.b);
        a('L');
        StringBuilder sb = new StringBuilder();
        b();
        if (!g && this.c == null) {
            x1f.a();
            return;
        }
        while (true) {
            char c3 = this.b;
            String str = this.c;
            if (c3 == '/') {
                sb.append(str);
                sb.append(this.b);
                c();
                b();
                if (!g && this.c == null) {
                    x1f.a();
                    return;
                }
            } else {
                sb.append(str);
                Object objA = this.a.a(sb.toString(), aVar);
                char c4 = this.b;
                if (c4 == '<') {
                    this.a.a(c4);
                    c();
                    char c5 = this.b;
                    if (c5 == '*') {
                        this.a.a(c5);
                        c();
                    } else if (c5 == '+' || c5 == '-') {
                        this.a.a(c5);
                        c();
                        b(InterfaceC2295os.a.c);
                    } else {
                        b(InterfaceC2295os.a.c);
                    }
                    while (true) {
                        c2 = this.b;
                        if (c2 == '>' || c2 <= 0) {
                            break;
                        }
                        if (c2 == '*') {
                            this.a.a(c2);
                            c();
                        } else if (c2 == '+') {
                            this.a.a(c2);
                            c();
                            b(InterfaceC2295os.a.c);
                        } else if (c2 == '-') {
                            this.a.a(c2);
                            c();
                            b(InterfaceC2295os.a.c);
                        } else {
                            b(InterfaceC2295os.a.c);
                        }
                    }
                    this.a.a(c2);
                    a('>');
                }
                while (true) {
                    char c6 = this.b;
                    InterfaceC2295os interfaceC2295os = this.a;
                    if (c6 != '.') {
                        interfaceC2295os.a(c6);
                        a(';');
                        return;
                    }
                    interfaceC2295os.a(c6);
                    c();
                    b();
                    if (!g && this.c == null) {
                        x1f.a();
                        return;
                    }
                    objA = this.a.a(objA, this.c);
                    char c7 = this.b;
                    if (c7 == '<') {
                        this.a.a(c7);
                        c();
                        char c8 = this.b;
                        if (c8 == '*') {
                            this.a.a(c8);
                            c();
                        } else if (c8 == '+' || c8 == '-') {
                            this.a.a(c8);
                            c();
                            b(InterfaceC2295os.a.c);
                        } else {
                            b(InterfaceC2295os.a.c);
                        }
                        while (true) {
                            c = this.b;
                            if (c == '>' || c <= 0) {
                                break;
                            }
                            if (c == '*') {
                                this.a.a(c);
                                c();
                            } else if (c == '+') {
                                this.a.a(c);
                                c();
                                b(InterfaceC2295os.a.c);
                            } else if (c == '-') {
                                this.a.a(c);
                                c();
                                b(InterfaceC2295os.a.c);
                            } else {
                                b(InterfaceC2295os.a.c);
                            }
                        }
                        this.a.a(c);
                        a('>');
                    }
                }
            }
        }
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
    public final void b() {
        /*
            r8 = this;
            boolean r0 = r8.d
            r1 = 0
            if (r0 != 0) goto L79
            int r0 = r8.f
            char[] r2 = r8.e
            int r2 = r2.length
            if (r0 >= r2) goto L79
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r2 = 32
            r0.<init>(r2)
            char r2 = r8.b
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
            char[] r1 = r8.e
            boolean r2 = com.android.tools.r8.internal.C2381ps.g
            if (r2 != 0) goto L32
            if (r1 == 0) goto L2e
            goto L32
        L2e:
            x1f.a()
            return
        L32:
            int r2 = r8.f
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
            int r2 = r8.f
            int r2 = r2 + r5
            r8.f = r2
            int r7 = r1.length
            if (r2 != r7) goto L32
            java.lang.String r0 = r0.toString()
            r8.c = r0
            r8.b = r3
            r8.d = r5
            return
        L63:
            java.lang.String r0 = r0.toString()
            r8.c = r0
            r8.c()
            return
        L6d:
            r8.b = r3
            r8.d = r5
            int r0 = r8.f
            java.lang.String r2 = "Unexpected"
            r8.a(r0, r2)
            throw r1
        L79:
            int r0 = r8.f
            java.lang.String r2 = "Unexpected end of signature"
            r8.a(r0, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.tools.r8.internal.C2381ps.b():void");
    }

    public void c(String str) {
        try {
            this.a.b();
            this.e = str.toCharArray();
            this.d = false;
            this.f = 0;
            this.b = (char) 0;
            this.c = null;
            c();
            a();
            this.a.a();
        } catch (GenericSignatureFormatError e) {
            throw e;
        } catch (Throwable th) {
            GenericSignatureFormatError genericSignatureFormatError = new GenericSignatureFormatError("Unknown error parsing method signature: " + th.getMessage());
            genericSignatureFormatError.addSuppressed(th);
            throw genericSignatureFormatError;
        }
    }

    public final void d() {
        b();
        if (!g && this.c == null) {
            x1f.a();
            return;
        }
        this.a.a(this.c);
        this.a.a(this.b);
        a(':');
        char c = this.b;
        if (c == 'L' || c == '[' || c == 'T') {
            b(InterfaceC2295os.a.d);
        }
        while (true) {
            char c2 = this.b;
            if (c2 != ':') {
                return;
            }
            this.a.a(c2);
            c();
            b(InterfaceC2295os.a.d);
        }
    }

    public final void c() {
        if (!this.d) {
            if (!g && this.e == null) {
                x1f.a();
                return;
            }
            int i = this.f;
            char[] cArr = this.e;
            if (i < cArr.length) {
                this.b = cArr[i];
                this.f = i + 1;
                return;
            } else {
                this.b = (char) 0;
                this.d = true;
                return;
            }
        }
        a(this.f, "Unexpected end of signature");
        throw null;
    }

    public final void b(InterfaceC2295os.a aVar) {
        char c = this.b;
        if (c == 'L') {
            a(aVar);
            return;
        }
        if (c == 'T') {
            this.a.a(c);
            a('T');
            b();
            if (!g && this.c == null) {
                x1f.a();
                return;
            }
            this.a.a(this.c);
            this.a.a(this.b);
            a(';');
            return;
        }
        if (c == '[') {
            this.a.a(c);
            c();
            char c2 = this.b;
            if (c2 != 'F' && c2 != 'S' && c2 != 'Z' && c2 != 'I' && c2 != 'J') {
                switch (c2) {
                    case 'B':
                    case 'C':
                    case 'D':
                        break;
                    default:
                        b(aVar);
                        return;
                }
            }
            this.a.a(c2);
            c();
            return;
        }
        a(this.f, "Expected L, [ or T");
        throw null;
    }

    public void b(String str) {
        try {
            this.a.b();
            this.e = str.toCharArray();
            this.d = false;
            this.f = 0;
            this.b = (char) 0;
            this.c = null;
            c();
            b(InterfaceC2295os.a.d);
            this.a.a();
        } catch (GenericSignatureFormatError e) {
            throw e;
        } catch (Throwable th) {
            GenericSignatureFormatError genericSignatureFormatError = new GenericSignatureFormatError("Unknown error parsing field signature: " + th.getMessage());
            genericSignatureFormatError.addSuppressed(th);
            throw genericSignatureFormatError;
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0080  */
    public final void a() {
        char c;
        char c2;
        char c3 = this.b;
        if (c3 == '<') {
            this.a.a(c3);
            c();
            d();
            while (true) {
                c2 = this.b;
                if (c2 == '>' || c2 <= 0) {
                    break;
                } else {
                    d();
                }
            }
            this.a.a(c2);
            a('>');
        }
        this.a.a(this.b);
        a('(');
        while (true) {
            c = this.b;
            if (c != ')' && c > 0) {
                InterfaceC2295os.a aVar = InterfaceC2295os.a.d;
                if (c != 'F' && c != 'S' && c != 'Z' && c != 'I' && c != 'J') {
                    switch (c) {
                        case 'B':
                        case 'C':
                        case 'D':
                            break;
                        default:
                            b(aVar);
                            continue;
                    }
                }
                this.a.a(c);
                c();
            }
        }
        this.a.a(c);
        a(')');
        char c4 = this.b;
        if (c4 != 'V') {
            InterfaceC2295os.a aVar2 = InterfaceC2295os.a.d;
            if (c4 == 'F' || c4 == 'S' || c4 == 'Z' || c4 == 'I' || c4 == 'J') {
                this.a.a(c4);
                c();
            } else {
                switch (c4) {
                    case 'B':
                    case 'C':
                    case 'D':
                        this.a.a(c4);
                        c();
                        break;
                    default:
                        b(aVar2);
                        break;
                }
            }
        } else {
            this.a.a(c4);
            c();
        }
        if (this.b == '^') {
            do {
                this.a.a(this.b);
                c();
                char c5 = this.b;
                if (c5 == 'T') {
                    this.a.a(c5);
                    a('T');
                    b();
                    if (!g && this.c == null) {
                        x1f.a();
                        return;
                    } else {
                        this.a.a(this.c);
                        this.a.a(this.b);
                        a(';');
                    }
                } else {
                    a(InterfaceC2295os.a.d);
                }
            } while (this.b == '^');
        }
    }

    public void a(String str) {
        char c;
        try {
            this.a.b();
            this.e = str.toCharArray();
            this.d = false;
            this.f = 0;
            this.b = (char) 0;
            this.c = null;
            c();
            char c2 = this.b;
            if (c2 == '<') {
                this.a.a(c2);
                c();
                d();
                while (true) {
                    c = this.b;
                    if (c == '>' || c <= 0) {
                        break;
                    } else {
                        d();
                    }
                }
                this.a.a(c);
                a('>');
            }
            a(InterfaceC2295os.a.b);
            while (this.b > 0) {
                a(InterfaceC2295os.a.b);
            }
            this.a.a();
        } catch (GenericSignatureFormatError e) {
            throw e;
        } catch (Throwable th) {
            GenericSignatureFormatError genericSignatureFormatError = new GenericSignatureFormatError("Unknown error parsing class signature: " + th.getMessage());
            genericSignatureFormatError.addSuppressed(th);
            throw genericSignatureFormatError;
        }
    }

    public final void a(char c) {
        if (!this.d) {
            if (this.b == c) {
                c();
                return;
            }
            a(this.f - 1, "Expected " + c);
            throw null;
        }
        a(this.f, "Unexpected end of signature");
        throw null;
    }

    public final void a(int i, String str) {
        String str2 = CharBuffer.allocate(i).toString().replace((char) 0, ' ') + "^";
        throw new GenericSignatureFormatError(str + " at position " + (i + 1) + "\n" + String.valueOf(this.e) + "\n" + str2);
    }
}
