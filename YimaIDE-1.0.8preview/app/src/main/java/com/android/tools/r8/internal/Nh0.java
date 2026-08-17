package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Nh0 extends XO {
    public final mY c;

    public Nh0(Xg0 xg0) {
        super(589824, null);
        this.c = xg0;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, String str3, WI wi, WI wi2, int i) {
        int i2;
        boolean z;
        boolean z2;
        char cCharAt;
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("LOCALVARIABLE ");
        sb.append(str);
        sb.append(' ');
        int i3 = 1;
        xg0.a(1, str2);
        ((mY) xg0).b.append(' ');
        xg0.a(wi);
        ((mY) xg0).b.append(' ');
        xg0.a(wi2);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(' ');
        sb2.append(i);
        sb2.append('\n');
        if (str3 != null) {
            ((mY) xg0).b.append(xg0.g);
            xg0.a(2, str3);
            ((mY) xg0).b.append(xg0.g);
            Ph0 ph0 = new Ph0();
            int length = str3.length();
            if (str3.charAt(0) == '<') {
                int i4 = 2;
                while (true) {
                    int iIndexOf = str3.indexOf(58, i4);
                    String strSubstring = str3.substring(i4 - i3, iIndexOf);
                    StringBuilder sb3 = ph0.b;
                    sb3.append(ph0.e ? ", " : "<");
                    sb3.append(strSubstring);
                    ph0.e = true;
                    ph0.f = false;
                    int iA = iIndexOf + 1;
                    char cCharAt2 = str3.charAt(iA);
                    if (cCharAt2 == 'L' || cCharAt2 == '[' || cCharAt2 == 'T') {
                        ph0.k = " extends ";
                        ph0.j *= 2;
                        iA = Db0.a(str3, iA, ph0);
                    }
                    while (true) {
                        i4 = iA + 1;
                        cCharAt = str3.charAt(iA);
                        if (cCharAt != ':') {
                            break;
                        }
                        ph0.k = ph0.f ? ", " : " extends ";
                        ph0.f = true;
                        ph0.j *= 2;
                        iA = Db0.a(str3, i4, ph0);
                    }
                    if (cCharAt == '>') {
                        break;
                    } else {
                        i3 = 1;
                    }
                }
                i2 = i4;
            } else {
                i2 = 0;
            }
            if (str3.charAt(i2) == '(') {
                int iA2 = i2 + 1;
                while (true) {
                    char cCharAt3 = str3.charAt(iA2);
                    z = ph0.e;
                    if (cCharAt3 == ')') {
                        break;
                    }
                    if (z) {
                        ph0.b.append('>');
                        ph0.e = false;
                    }
                    boolean z3 = ph0.g;
                    StringBuilder sb4 = ph0.b;
                    if (z3) {
                        sb4.append(", ");
                    } else {
                        sb4.append('(');
                        ph0.g = true;
                    }
                    ph0.j *= 2;
                    iA2 = Db0.a(str3, iA2, ph0);
                }
                int i5 = iA2 + 1;
                if (z) {
                    ph0.b.append('>');
                    z2 = false;
                    ph0.e = false;
                } else {
                    z2 = false;
                }
                if (ph0.g) {
                    ph0.g = z2;
                } else {
                    ph0.b.append('(');
                }
                ph0.b.append(')');
                StringBuilder sb5 = new StringBuilder();
                ph0.c = sb5;
                int iA3 = Db0.a(str3, i5, new Ph0(sb5));
                while (iA3 < length) {
                    int i6 = iA3 + 1;
                    StringBuilder sb6 = ph0.d;
                    if (sb6 == null) {
                        ph0.d = new StringBuilder();
                    } else {
                        sb6.append(", ");
                    }
                    iA3 = Db0.a(str3, i6, new Ph0(ph0.d));
                }
            } else {
                if (ph0.e) {
                    ph0.b.append('>');
                    ph0.e = false;
                }
                ph0.k = " extends ";
                ph0.j *= 2;
                int iA4 = Db0.a(str3, i2, ph0);
                while (iA4 < length) {
                    if (ph0.h) {
                        ph0.k = ", ";
                    } else {
                        ph0.k = ph0.a ? " extends " : " implements ";
                        ph0.h = true;
                    }
                    ph0.j *= 2;
                    iA4 = Db0.a(str3, iA4, ph0);
                }
            }
            ((mY) xg0).b.append("// declaration: ");
            StringBuilder sb7 = ph0.c;
            if ((sb7 == null ? null : sb7.toString()) != null) {
                StringBuilder sb8 = ((mY) xg0).b;
                StringBuilder sb9 = ph0.c;
                sb8.append(sb9 == null ? null : sb9.toString());
                ((mY) xg0).b.append(' ');
            }
            ((mY) xg0).b.append(str);
            ((mY) xg0).b.append(ph0.b.toString());
            StringBuilder sb10 = ph0.d;
            if ((sb10 == null ? null : sb10.toString()) != null) {
                StringBuilder sb11 = ((mY) xg0).b;
                sb11.append(" throws ");
                StringBuilder sb12 = ph0.d;
                sb11.append(sb12 != null ? sb12.toString() : null);
            }
            ((mY) xg0).b.append('\n');
        }
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(str, str2, str3, wi, wi2, i);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, String str) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("// parameter ");
        if ((i & 1) != 0) {
            ((mY) xg0).b.append("public ");
        }
        if ((i & 2) != 0) {
            ((mY) xg0).b.append("private ");
        }
        if ((i & 4) != 0) {
            ((mY) xg0).b.append("protected ");
        }
        if ((i & 16) != 0) {
            ((mY) xg0).b.append("final ");
        }
        if ((i & 8) != 0) {
            ((mY) xg0).b.append("static ");
        }
        if ((i & 32) != 0) {
            ((mY) xg0).b.append("synchronized ");
        }
        if ((i & 64) != 0) {
            ((mY) xg0).b.append("volatile ");
        }
        if ((i & 128) != 0) {
            ((mY) xg0).b.append("transient ");
        }
        if ((i & Fcntl.S_ISGID) != 0) {
            ((mY) xg0).b.append("abstract ");
        }
        if ((i & Fcntl.S_ISUID) != 0) {
            ((mY) xg0).b.append("strictfp ");
        }
        if ((i & 4096) != 0) {
            ((mY) xg0).b.append("synthetic ");
        }
        if ((32768 & i) != 0) {
            ((mY) xg0).b.append("mandated ");
        }
        if ((i & 16384) != 0) {
            ((mY) xg0).b.append("enum ");
        }
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(' ');
        sb2.append(str == null ? "<no name>" : str);
        sb2.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.b(i, str);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, int i2) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("MAXSTACK = ");
        sb.append(i);
        sb.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        ((mY) xg0).b.setLength(0);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(xg0.g);
        sb2.append("MAXLOCALS = ");
        sb2.append(i2);
        sb2.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.c(i, i2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void d(int i, int i2) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append(mY.d[i]);
        sb.append(' ');
        sb.append(i2);
        sb.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.d(i, i2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, String str) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append(mY.d[i]);
        sb.append(' ');
        xg0.a(0, str);
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.c(i, str);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 c(int i, C3052xj0 c3052xj0, String str, boolean z) {
        return new Mh0(super.c(i, c3052xj0, str, z), ((Xg0) this.c).a(i, c3052xj0, str, z));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c() {
        this.c.getClass();
        super.c();
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b() {
        this.c.getClass();
        super.b();
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, int i2) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append(mY.d[i]);
        sb.append(' ');
        sb.append(i == 188 ? mY.e[i2] : Integer.toString(i2));
        sb.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.b(i, i2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 b(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("TRYCATCHBLOCK @");
        xg0.a(1, str);
        ((mY) xg0).b.append('(');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        ((mY) xg0).b.setLength(0);
        ((mY) xg0).b.append(") : ");
        xg0.a(i);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(", ");
        sb2.append(c3052xj0);
        ((mY) xg0).b.append(z ? "\n" : " // invisible\n");
        return new Mh0(super.b(i, c3052xj0, str, z), xg0.a(((mY) xg0).b.toString()));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, WI wi) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("LINENUMBER ");
        sb.append(i);
        sb.append(' ');
        xg0.a(wi);
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.b(i, wi);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(H4 h4) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.f);
        sb.append("ATTRIBUTE ");
        xg0.a(-1, h4.a);
        ((mY) xg0).b.append(" : unknown\n");
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(h4);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a() {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).c.add(xg0.g + "default=");
        return new Mh0(super.a(), xg0.a("\n"));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, boolean z) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("// annotable parameter count: ");
        ((mY) xg0).b.append(i);
        ((mY) xg0).b.append(z ? " (visible)\n" : " (invisible)\n");
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i, z);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, String str, boolean z) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append('@');
        xg0.a(1, str);
        ((mY) xg0).b.append('(');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        ((mY) xg0).b.setLength(0);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(z ? ") // parameter " : ") // invisible, parameter ");
        sb2.append(i);
        sb2.append('\n');
        return new Mh0(super.a(i, str, z), xg0.a(((mY) xg0).b.toString()));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        ((mY) xg0).b.append(xg0.i);
        ((mY) xg0).b.append("FRAME ");
        if (i == -1 || i == 0) {
            ((mY) xg0).b.append("FULL [");
            xg0.a(i2, objArr);
            ((mY) xg0).b.append("] [");
            xg0.a(i3, objArr2);
            ((mY) xg0).b.append(']');
        } else if (i == 1) {
            ((mY) xg0).b.append("APPEND [");
            xg0.a(i2, objArr);
            ((mY) xg0).b.append(']');
        } else if (i == 2) {
            StringBuilder sb = ((mY) xg0).b;
            sb.append("CHOP ");
            sb.append(i2);
        } else if (i == 3) {
            ((mY) xg0).b.append("SAME");
        } else if (i == 4) {
            ((mY) xg0).b.append("SAME1 ");
            xg0.a(1, objArr2);
        } else {
            j2d.a();
            return;
        }
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i, i2, objArr, i3, objArr2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append(mY.d[i]);
        sb.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append(mY.d[i]);
        sb.append(' ');
        xg0.a(0, str);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append('.');
        sb2.append(str2);
        sb2.append(" : ");
        xg0.a(1, str3);
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i, str, str2, str3);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3, boolean z) {
        mY mYVar = this.c;
        if (mYVar.a < 327680) {
            if (z == (i == 185)) {
                mYVar.a(i, str, str2, str3, i == 185);
            } else {
                w01.a("INVOKESPECIAL/STATIC on interfaces require ASM5");
                return;
            }
        } else {
            i = i;
            str = str;
            str2 = str2;
            str3 = str3;
            mYVar.a(i, str, str2, str3, z);
        }
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, str, str2, str3, z);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("INVOKEDYNAMIC");
        sb.append(' ');
        ((mY) xg0).b.append(str);
        xg0.a(3, str2);
        ((mY) xg0).b.append(" [");
        ((mY) xg0).b.append('\n');
        ((mY) xg0).b.append(xg0.h);
        xg0.a(c0497Fs);
        ((mY) xg0).b.append('\n');
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(xg0.h);
        sb2.append("// arguments:");
        int length = objArr.length;
        StringBuilder sb3 = ((mY) xg0).b;
        if (length == 0) {
            sb3.append(" none");
        } else {
            sb3.append('\n');
            for (Object obj : objArr) {
                ((mY) xg0).b.append(xg0.h);
                if (obj instanceof String) {
                    mY.a(((mY) xg0).b, (String) obj);
                } else if (obj instanceof C3050xi0) {
                    C3050xi0 c3050xi0 = (C3050xi0) obj;
                    if (c3050xi0.c() == 11) {
                        xg0.a(3, c3050xi0.b());
                    } else {
                        StringBuilder sb4 = ((mY) xg0).b;
                        sb4.append(c3050xi0.a());
                        sb4.append(".class");
                    }
                } else if (obj instanceof C0497Fs) {
                    xg0.a((C0497Fs) obj);
                } else {
                    ((mY) xg0).b.append(obj);
                }
                ((mY) xg0).b.append(", \n");
            }
            StringBuilder sb5 = ((mY) xg0).b;
            sb5.setLength(sb5.length() - 3);
        }
        ((mY) xg0).b.append('\n');
        StringBuilder sb6 = ((mY) xg0).b;
        sb6.append(xg0.g);
        sb6.append("]\n");
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(str, str2, c0497Fs, objArr);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, WI wi) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append(mY.d[i]);
        sb.append(' ');
        xg0.a(wi);
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i, wi);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        ((mY) xg0).b.append(xg0.i);
        xg0.a(wi);
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(wi);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(Object obj) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("LDC ");
        if (obj instanceof String) {
            mY.a(((mY) xg0).b, (String) obj);
        } else {
            boolean z = obj instanceof C3050xi0;
            StringBuilder sb2 = ((mY) xg0).b;
            if (z) {
                sb2.append(((C3050xi0) obj).b());
                sb2.append(".class");
            } else {
                sb2.append(obj);
            }
        }
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(obj);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("IINC ");
        sb.append(i);
        sb.append(' ');
        sb.append(i2);
        sb.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i, i2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, WI wi, WI... wiArr) {
        Xg0 xg0 = (Xg0) this.c;
        int i3 = 0;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("TABLESWITCH\n");
        while (true) {
            int length = wiArr.length;
            StringBuilder sb2 = ((mY) xg0).b;
            if (i3 < length) {
                sb2.append(xg0.h);
                sb2.append(i + i3);
                sb2.append(": ");
                xg0.a(wiArr[i3]);
                ((mY) xg0).b.append('\n');
                i3++;
            } else {
                sb2.append(xg0.h);
                sb2.append("default: ");
                xg0.a(wi);
                ((mY) xg0).b.append('\n');
                ((mY) xg0).c.add(((mY) xg0).b.toString());
                super.a(i, i2, wi, wiArr);
                return;
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, int[] iArr, WI[] wiArr) {
        Xg0 xg0 = (Xg0) this.c;
        int i = 0;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("LOOKUPSWITCH\n");
        while (true) {
            int length = wiArr.length;
            StringBuilder sb2 = ((mY) xg0).b;
            if (i < length) {
                sb2.append(xg0.h);
                sb2.append(iArr[i]);
                sb2.append(": ");
                xg0.a(wiArr[i]);
                ((mY) xg0).b.append('\n');
                i++;
            } else {
                sb2.append(xg0.h);
                sb2.append("default: ");
                xg0.a(wi);
                ((mY) xg0).b.append('\n');
                ((mY) xg0).c.add(((mY) xg0).b.toString());
                super.a(wi, iArr, wiArr);
                return;
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("MULTIANEWARRAY ");
        xg0.a(1, str);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(' ');
        sb2.append(i);
        sb2.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(i, str);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        return new Mh0(super.a(i, c3052xj0, str, z), ((Xg0) this.c).a(i, c3052xj0, str, z));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, WI wi2, WI wi3, String str) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.g);
        sb.append("TRYCATCHBLOCK ");
        xg0.a(wi);
        ((mY) xg0).b.append(' ');
        xg0.a(wi2);
        ((mY) xg0).b.append(' ');
        xg0.a(wi3);
        ((mY) xg0).b.append(' ');
        xg0.a(0, str);
        ((mY) xg0).b.append('\n');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(wi, wi2, wi3, str);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(String str, boolean z) {
        Xg0 xg0 = (Xg0) this.c;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb = ((mY) xg0).b;
        sb.append(xg0.f);
        sb.append('@');
        xg0.a(1, str);
        ((mY) xg0).b.append('(');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        return new Mh0(super.a(str, z), xg0.a(z ? ")\n" : ") // invisible\n"));
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, WI[] wiArr, WI[] wiArr2, int[] iArr, String str, boolean z) {
        StringBuilder sb;
        Xg0 xg0 = (Xg0) this.c;
        int i2 = 0;
        ((mY) xg0).b.setLength(0);
        StringBuilder sb2 = ((mY) xg0).b;
        sb2.append(xg0.g);
        sb2.append("LOCALVARIABLE @");
        xg0.a(1, str);
        ((mY) xg0).b.append('(');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        ((mY) xg0).b.setLength(0);
        ((mY) xg0).b.append(") : ");
        xg0.a(i);
        StringBuilder sb3 = ((mY) xg0).b;
        sb3.append(", ");
        sb3.append(c3052xj0);
        while (true) {
            int length = wiArr.length;
            sb = ((mY) xg0).b;
            if (i2 >= length) {
                break;
            }
            sb.append(" [ ");
            xg0.a(wiArr[i2]);
            ((mY) xg0).b.append(" - ");
            xg0.a(wiArr2[i2]);
            StringBuilder sb4 = ((mY) xg0).b;
            sb4.append(" - ");
            sb4.append(iArr[i2]);
            sb4.append(" ]");
            i2++;
        }
        sb.append(z ? "\n" : " // invisible\n");
        return new Mh0(super.a(i, c3052xj0, wiArr, wiArr2, iArr, str, z), xg0.a(((mY) xg0).b.toString()));
    }
}
