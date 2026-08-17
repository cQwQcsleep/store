package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.do4;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0636Lb extends XO {
    public static final int[] q = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 0, 0, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 6, 6, 6, 6, 0, 4, 2, 4, 1, 1, 4, 4, 1, 1, 0, 0, 7, 7};
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public boolean h;
    public boolean i;
    public int j;
    public final Map k;
    public final HashSet l;
    public int m;
    public int n;
    public int o;
    public final ArrayList p;

    public C0636Lb(int i, XO xo, Map map) {
        super(i, xo);
        this.m = -1;
        this.k = map;
        this.l = new HashSet();
        this.p = new ArrayList();
    }

    public static int a(int i, String str, int i2, boolean z) {
        if (str == null || i2 >= str.length()) {
            w01.a("Invalid type descriptor (must not be null or empty)");
            return 0;
        }
        char cCharAt = str.charAt(i2);
        if (cCharAt != 'F') {
            if (cCharAt == 'L') {
                int iIndexOf = str.indexOf(59, i2);
                if (i2 == -1 || iIndexOf - i2 < 2) {
                    w01.a("Invalid descriptor: ".concat(str));
                    return 0;
                }
                try {
                    a(i, str.substring(i2 + 1, iIndexOf), (String) null);
                    return iIndexOf + 1;
                } catch (IllegalArgumentException e) {
                    nrd.a("Invalid descriptor: ".concat(str), e);
                    return 0;
                }
            }
            if (cCharAt != 'S') {
                if (cCharAt == 'V') {
                    if (z) {
                        return i2 + 1;
                    }
                    w01.a("Invalid descriptor: ".concat(str));
                    return 0;
                }
                if (cCharAt != 'I' && cCharAt != 'J' && cCharAt != 'Z') {
                    if (cCharAt == '[') {
                        do {
                            i2++;
                            if (i2 >= str.length()) {
                                break;
                            }
                        } while (str.charAt(i2) == '[');
                        if (i2 < str.length()) {
                            return a(i, str, i2, false);
                        }
                        w01.a("Invalid descriptor: ".concat(str));
                        return 0;
                    }
                    switch (cCharAt) {
                        case 'B':
                        case 'C':
                        case 'D':
                            break;
                        default:
                            w01.a("Invalid descriptor: ".concat(str));
                            return 0;
                    }
                }
            }
        }
        return i2 + 1;
    }

    public static void e(int i, String str) {
        int iA;
        if (str == null || str.length() == 0) {
            w01.a("Invalid method descriptor (must not be null or empty)");
            return;
        }
        if (str.charAt(0) != '(' || str.length() < 3) {
            w01.a("Invalid descriptor: ".concat(str));
            return;
        }
        if (str.charAt(1) != ')') {
            iA = 1;
            do {
                if (str.charAt(iA) == 'V') {
                    w01.a("Invalid descriptor: ".concat(str));
                    return;
                } else {
                    iA = a(i, str, iA, false);
                    if (iA >= str.length()) {
                        break;
                    }
                }
            } while (str.charAt(iA) != ')');
        } else {
            iA = 1;
        }
        if (a(i, str, iA + 1, true) == str.length()) {
            return;
        }
        w01.a("Invalid descriptor: ".concat(str));
    }

    public static void f(int i, String str) {
        if (i < 0 || i > 65535) {
            do4.a(str, " (must be an unsigned short): ", i);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, int i2) {
        d();
        f();
        e(i, 2);
        if (i != 16) {
            if (i != 17) {
                if (i != 188) {
                    x1f.a();
                    return;
                } else if (i2 < 4 || i2 > 11) {
                    w01.a(CX.a(i2, "Invalid operand (must be an array type code T_...): "));
                    return;
                }
            } else if (i2 < -32768 || i2 > 32767) {
                w01.a(CX.a(i2, "Invalid operand (must be a signed short): "));
                return;
            }
        } else if (i2 < -128 || i2 > 127) {
            w01.a(CX.a(i2, "Invalid operand (must be a signed byte): "));
            return;
        }
        super.b(i, i2);
        this.j++;
    }

    public final void c(Object obj) {
        if (obj instanceof C3050xi0) {
            int iC = ((C3050xi0) obj).c();
            if (iC != 10 && iC != 9 && iC != 11) {
                w01.a("Illegal LDC constant value");
                return;
            }
            if (iC != 11 && (this.c & 65535) < 49) {
                w01.a("ldc of a constant class requires at least version 1.5");
                return;
            } else {
                if (iC != 11 || (this.c & 65535) >= 51) {
                    return;
                }
                w01.a("ldc of a method type requires at least version 1.7");
                return;
            }
        }
        if (obj instanceof C0497Fs) {
            int i = this.c;
            if ((65535 & i) < 51) {
                w01.a("ldc of a Handle requires at least version 1.7");
                return;
            }
            C0497Fs c0497Fs = (C0497Fs) obj;
            int i2 = c0497Fs.a;
            if (i2 < 1 || i2 > 9) {
                w01.a(CX.a(i2, "invalid handle tag "));
                return;
            }
            b(i, c0497Fs.b, "handle owner");
            int i3 = this.c;
            if (i2 <= 4) {
                d(i3, c0497Fs.d);
            } else {
                e(i3, c0497Fs.d);
            }
            String str = c0497Fs.c;
            if ("<init>".equals(str) && i2 == 8) {
                return;
            }
            c(this.c, str, "handle name");
            return;
        }
        if (!(obj instanceof C2190ng)) {
            if ((obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String)) {
                return;
            }
            aca.a("Invalid constant: ", obj);
            return;
        }
        int i4 = this.c;
        if ((i4 & 65535) < 55) {
            w01.a("ldc of a ConstantDynamic requires at least version 11");
            return;
        }
        C2190ng c2190ng = (C2190ng) obj;
        c(i4, c2190ng.a, "constant dynamic name");
        d(this.c, c2190ng.b);
        c(c2190ng.c);
        int length = c2190ng.d.length;
        for (int i5 = 0; i5 < length; i5++) {
            c(c2190ng.d[i5]);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void d(int i, int i2) {
        d();
        f();
        e(i, 3);
        f(i2, "Invalid local variable index");
        super.d(i, i2);
        this.j++;
    }

    public final void f() {
        if (this.h) {
            k2d.a("Cannot visit instructions after visitMaxs has been called.");
        }
    }

    public final void d() {
        if (this.g) {
            return;
        }
        k2d.a("Cannot visit instructions before visitCode has been called.");
    }

    public static void d(int i, String str) {
        if (a(i, str, 0, false) == str.length()) {
            return;
        }
        w01.a("Invalid descriptor: ".concat(str));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b() {
        if ((this.d & Fcntl.S_ISGID) == 0) {
            this.g = true;
            super.b();
        } else {
            c41.a("Abstract methods cannot have code");
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, String str) {
        if (str != null) {
            a(this.c, str, 0, -1, TypeBlock.NAME_name);
        }
        AbstractC0480Fb.a(i, 36880);
        super.b(i, str);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 b(int i, C3052xj0 c3052xj0, String str, boolean z) {
        d();
        f();
        int i2 = i >>> 24;
        if (i2 == 66) {
            AbstractC0480Fb.a(i);
            d(this.c, str);
            return new C0376Bb(super.b(i, c3052xj0, str, z));
        }
        w01.a(F40.a("Invalid type reference sort 0x", Integer.toHexString(i2)));
        return null;
    }

    public static void e(int i, int i2) {
        String str;
        if (i >= 0 && i <= 199) {
            if (q[i] == i2) {
                return;
            }
            StringBuilder sbA = Ni0.a(i, "Invalid combination of opcode and method: ", ", ");
            switch (i2) {
                case 1:
                    str = "VISIT_INSN";
                    break;
                case 2:
                    str = "VISIT_INT_INSN";
                    break;
                case XmlPullParser.END_TAG /* 3 */:
                    str = "VISIT_VAR_INSN";
                    break;
                case 4:
                    str = "VISIT_TYPE_INSN";
                    break;
                case XmlPullParser.CDSECT /* 5 */:
                    str = "VISIT_FIELD_INSN";
                    break;
                case XmlPullParser.ENTITY_REF /* 6 */:
                    str = "VISIT_METHOD_INSN";
                    break;
                case 7:
                    str = "VISIT_JUMP_INSN";
                    break;
                default:
                    str = "null";
                    break;
            }
            sbA.append(str);
            throw new IllegalArgumentException(sbA.toString());
        }
        w01.a(CX.a(i, "Invalid opcode: "));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, WI wi) {
        d();
        f();
        f(i, "Invalid line number");
        a(wi, true, "start label");
        super.b(i, wi);
    }

    public final void e() {
        if (this.i) {
            k2d.a("Cannot visit elements after visitEnd has been called.");
        }
    }

    public final void b(Object obj) {
        if (obj == 0 || obj == 1 || obj == 2 || obj == 4 || obj == 3 || obj == 5 || obj == 6) {
            return;
        }
        if (obj instanceof String) {
            b(this.c, (String) obj, "Invalid stack frame value");
        } else if (obj instanceof WI) {
            a((WI) obj, false, "label");
        } else {
            aca.a("Invalid stack frame value: ", obj);
        }
    }

    public static void b(int i, String str, String str2) {
        if (str != null && str.length() != 0) {
            if (str.charAt(0) == '[') {
                d(i, str);
                return;
            } else {
                a(i, str, str2);
                return;
            }
        }
        w01.a(C40.a("Invalid ", str2, " (must not be null or empty)"));
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a() {
        e();
        return new C0376Bb(super.a(), 0);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, boolean z) {
        e();
        if (z) {
            this.e = i;
        } else {
            this.f = i;
        }
        super.a(i, z);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, String str, boolean z) {
        int i2;
        int i3;
        e();
        if ((z && (i3 = this.e) > 0 && i >= i3) || (!z && (i2 = this.f) > 0 && i >= i2)) {
            w01.a("Invalid parameter index");
            return null;
        }
        d(this.c, str);
        return new C0376Bb(super.a(i, str, z));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(H4 h4) {
        e();
        if (h4 != null) {
            super.a(h4);
        } else {
            w01.a("Invalid attribute (must not be null)");
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0034 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0036  */
    /* JADX WARN: Code duplicated, block: B:24:0x0038  */
    /* JADX WARN: Code duplicated, block: B:33:0x0049 A[LOOP:0: B:32:0x0047->B:33:0x0049, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x0061 A[LOOP:1: B:41:0x005f->B:42:0x0061, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:0x006b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0071  */
    /* JADX WARN: Code duplicated, block: B:55:0x008f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0097  */
    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        int i5;
        int i6;
        int i7 = this.j;
        if (i7 != this.m) {
            this.m = i7;
            if (i != -1 && i != 0) {
                i4 = 3;
                if (i == 1 || i == 2) {
                    i5 = 0;
                } else if (i == 3) {
                    i4 = 0;
                } else if (i != 4) {
                    w01.a(CX.a(i, "Invalid frame type "));
                    return;
                } else {
                    i4 = 0;
                    i5 = 1;
                }
                if (i2 <= i4) {
                    dn0.a("Invalid numLocal=", i2, " for frame type ", i);
                    return;
                }
                if (i3 <= i5) {
                    if (i != 2) {
                        if (i2 <= 0 && (objArr == null || objArr.length < i2)) {
                            w01.a("Array local[] is shorter than numLocal");
                            return;
                        }
                        for (i6 = 0; i6 < i2; i6++) {
                            b(objArr[i6]);
                        }
                    }
                    if (i3 <= 0 && (objArr2 == null || objArr2.length < i3)) {
                        w01.a("Array stack[] is shorter than numStack");
                        return;
                    }
                    for (int i8 = 0; i8 < i3; i8++) {
                        b(objArr2[i8]);
                    }
                    if (i == -1) {
                        this.n++;
                    } else {
                        this.o++;
                    }
                    if (this.n <= 0 && this.o > 0) {
                        w01.a("Expanded and compressed frames must not be mixed.");
                        return;
                    } else {
                        super.a(i, i2, objArr, i3, objArr2);
                        return;
                    }
                }
                dn0.a("Invalid numStack=", i3, " for frame type ", i);
                return;
            }
            i4 = Integer.MAX_VALUE;
            i5 = i4;
            if (i2 <= i4) {
                dn0.a("Invalid numLocal=", i2, " for frame type ", i);
                return;
            }
            if (i3 <= i5) {
                if (i != 2) {
                    if (i2 <= 0) {
                    }
                    while (i6 < i2) {
                        b(objArr[i6]);
                    }
                }
                if (i3 <= 0) {
                }
                while (i8 < i3) {
                    b(objArr2[i8]);
                }
                if (i == -1) {
                    this.n++;
                } else {
                    this.o++;
                }
                if (this.n <= 0) {
                }
                super.a(i, i2, objArr, i3, objArr2);
                return;
            }
            dn0.a("Invalid numStack=", i3, " for frame type ", i);
            return;
        }
        k2d.a("At most one frame can be visited at a given code location.");
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i) {
        d();
        f();
        e(i, 1);
        super.a(i);
        this.j++;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3) {
        d();
        f();
        e(i, 5);
        b(this.c, str, "owner");
        a(this.c, str2, 0, -1, TypeBlock.NAME_name);
        d(this.c, str3);
        super.a(i, str, str2, str3);
        this.j++;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3, boolean z) {
        if (this.a < 327680 && (i & Fcntl.S_IRUSR) == 0) {
            super.a(i, str, str2, str3, z);
            return;
        }
        int i2 = i & (-257);
        d();
        f();
        e(i2, 6);
        if (i2 != 183 || !"<init>".equals(str2)) {
            c(this.c, str2, TypeBlock.NAME_name);
        }
        b(this.c, str, "owner");
        e(this.c, str3);
        if (i2 == 182 && z) {
            w01.a("INVOKEVIRTUAL can't be used with interfaces");
            return;
        }
        if (i2 == 185 && !z) {
            w01.a("INVOKEINTERFACE can't be used with classes");
            return;
        }
        if (i2 == 183 && z && (this.c & 65535) < 52) {
            w01.a("INVOKESPECIAL can't be used with interfaces prior to Java 8");
        } else {
            super.a(i, str, str2, str3, z);
            this.j++;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        d();
        f();
        c(this.c, str, TypeBlock.NAME_name);
        e(this.c, str2);
        int i = c0497Fs.a;
        if (i != 6 && i != 8) {
            w01.a(CX.a(i, "invalid handle tag "));
            return;
        }
        for (Object obj : objArr) {
            c(obj);
        }
        super.a(str, str2, c0497Fs, objArr);
        this.j++;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, String str) {
        d();
        f();
        e(i, 4);
        b(this.c, str, "type");
        if (i == 187 && str.charAt(0) == '[') {
            w01.a("NEW cannot be used to create arrays: ".concat(str));
        } else {
            super.c(i, str);
            this.j++;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, WI wi) {
        d();
        f();
        e(i, 7);
        a(wi, false, "label");
        super.a(i, wi);
        this.j++;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, int i2) {
        d();
        f();
        this.h = true;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            if (this.k.get((WI) it.next()) == null) {
                k2d.a("Undefined label used");
                return;
            }
        }
        for (int i3 = 0; i3 < this.p.size(); i3 += 2) {
            if (((Integer) this.k.get(this.p.get(i3 + 1))).intValue() <= ((Integer) this.k.get(this.p.get(i3))).intValue()) {
                k2d.a("Empty try catch block handler range");
                return;
            }
        }
        f(i, "Invalid max stack");
        f(i2, "Invalid max locals");
        super.c(i, i2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi) {
        d();
        f();
        a(wi, false, "label");
        if (this.k.get(wi) == null) {
            this.k.put(wi, Integer.valueOf(this.j));
            super.a(wi);
        } else {
            k2d.a("Already visited label");
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(Object obj) {
        d();
        f();
        c(obj);
        super.a(obj);
        this.j++;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c() {
        e();
        this.i = true;
        super.c();
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2) {
        d();
        f();
        f(i, "Invalid local variable index");
        if (i2 >= -32768 && i2 <= 32767) {
            super.a(i, i2);
            this.j++;
        } else {
            w01.a(CX.a(i2, "Invalid increment (must be a signed short): "));
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 c(int i, C3052xj0 c3052xj0, String str, boolean z) {
        e();
        int i2 = i >>> 24;
        if (i2 != 1 && i2 != 18 && i2 != 20 && i2 != 21 && i2 != 22 && i2 != 23) {
            w01.a(F40.a("Invalid type reference sort 0x", Integer.toHexString(i2)));
            return null;
        }
        AbstractC0480Fb.a(i);
        d(this.c, str);
        return new C0376Bb(super.c(i, c3052xj0, str, z));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, WI wi, WI... wiArr) {
        d();
        f();
        if (i2 >= i) {
            a(wi, false, "default label");
            if (wiArr.length == (i2 - i) + 1) {
                for (int i3 = 0; i3 < wiArr.length; i3++) {
                    a(wiArr[i3], false, "label at index " + i3);
                }
                super.a(i, i2, wi, wiArr);
                this.j++;
                return;
            }
            w01.a("There must be max - min + 1 labels");
            return;
        }
        dn0.a("Max = ", i2, " must be greater than or equal to min = ", i);
    }

    public static void c(int i, String str, String str2) {
        if (str == null || str.length() == 0) {
            w01.a(C40.a("Invalid ", str2, " (must not be null or empty)"));
            return;
        }
        int iOffsetByCodePoints = 0;
        if ((i & 65535) >= 49) {
            while (iOffsetByCodePoints < str.length()) {
                if (".;[/<>".indexOf(str.codePointAt(iOffsetByCodePoints)) == -1) {
                    iOffsetByCodePoints = str.offsetByCodePoints(iOffsetByCodePoints, 1);
                } else {
                    h0f.a("Invalid ", str2, " (must be a valid unqualified name): ", str);
                    return;
                }
            }
            return;
        }
        while (iOffsetByCodePoints < str.length()) {
            if (iOffsetByCodePoints == 0) {
                if (Character.isJavaIdentifierStart(str.codePointAt(iOffsetByCodePoints))) {
                    iOffsetByCodePoints = str.offsetByCodePoints(iOffsetByCodePoints, 1);
                } else {
                    h0f.a("Invalid ", str2, " (must be a '<init>', '<clinit>' or a valid Java identifier): ", str);
                    return;
                }
            } else if (Character.isJavaIdentifierPart(str.codePointAt(iOffsetByCodePoints))) {
                iOffsetByCodePoints = str.offsetByCodePoints(iOffsetByCodePoints, 1);
            } else {
                h0f.a("Invalid ", str2, " (must be a '<init>', '<clinit>' or a valid Java identifier): ", str);
                return;
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, int[] iArr, WI[] wiArr) {
        f();
        d();
        a(wi, false, "default label");
        if (iArr != null && iArr.length == wiArr.length) {
            for (int i = 0; i < wiArr.length; i++) {
                a(wiArr[i], false, "label at index " + i);
            }
            super.a(wi, iArr, wiArr);
            this.j++;
            return;
        }
        w01.a("There must be the same number of keys and labels");
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str) {
        d();
        f();
        d(this.c, str);
        if (str.charAt(0) != '[') {
            w01.a("Invalid descriptor (must be an array type descriptor): ".concat(str));
            return;
        }
        if (i >= 1) {
            if (i <= str.lastIndexOf(91) + 1) {
                super.a(i, str);
                this.j++;
                return;
            } else {
                w01.a(CX.a(i, "Invalid dimensions (must not be greater than numDimensions(descriptor)): "));
                return;
            }
        }
        w01.a(CX.a(i, "Invalid dimensions (must be greater than 0): "));
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        d();
        f();
        int i2 = i >>> 24;
        if (i2 != 67 && i2 != 68 && i2 != 69 && i2 != 70 && i2 != 71 && i2 != 72 && i2 != 73 && i2 != 74 && i2 != 75) {
            w01.a(F40.a("Invalid type reference sort 0x", Integer.toHexString(i2)));
            return null;
        }
        AbstractC0480Fb.a(i);
        d(this.c, str);
        return new C0376Bb(super.a(i, c3052xj0, str, z));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, WI wi2, WI wi3, String str) {
        d();
        f();
        a(wi, false, "start label");
        a(wi2, false, "end label");
        a(wi3, false, "handler label");
        if (this.k.get(wi) == null && this.k.get(wi2) == null && this.k.get(wi3) == null) {
            if (str != null) {
                b(this.c, str, "type");
            }
            super.a(wi, wi2, wi3, str);
            this.p.add(wi);
            this.p.add(wi2);
            return;
        }
        k2d.a("Try catch blocks must be visited before their labels");
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, String str3, WI wi, WI wi2, int i) {
        d();
        f();
        a(this.c, str, 0, -1, TypeBlock.NAME_name);
        d(this.c, str2);
        if (str3 != null) {
            AbstractC0480Fb.d(str3);
        }
        a(wi, true, "start label");
        a(wi2, true, "end label");
        f(i, "Invalid local variable index");
        if (((Integer) this.k.get(wi2)).intValue() >= ((Integer) this.k.get(wi)).intValue()) {
            super.a(str, str2, str3, wi, wi2, i);
        } else {
            w01.a("Invalid start and end labels (end must be greater than start)");
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, WI[] wiArr, WI[] wiArr2, int[] iArr, String str, boolean z) {
        d();
        f();
        int i2 = i >>> 24;
        if (i2 != 64 && i2 != 65) {
            w01.a(F40.a("Invalid type reference sort 0x", Integer.toHexString(i2)));
            return null;
        }
        AbstractC0480Fb.a(i);
        d(this.c, str);
        if (wiArr != null && wiArr2 != null && iArr != null && wiArr2.length == wiArr.length && iArr.length == wiArr.length) {
            for (int i3 = 0; i3 < wiArr.length; i3++) {
                a(wiArr[i3], true, "start label");
                a(wiArr2[i3], true, "end label");
                f(iArr[i3], "Invalid local variable index");
                if (((Integer) this.k.get(wiArr2[i3])).intValue() < ((Integer) this.k.get(wiArr[i3])).intValue()) {
                    w01.a("Invalid start and end labels (end must be greater than start)");
                    return null;
                }
            }
            return super.a(i, c3052xj0, wiArr, wiArr2, iArr, str, z);
        }
        w01.a("Invalid start, end and index arrays (must be non null and of identical length");
        return null;
    }

    public static void a(int i, String str, int i2, int i3, String str2) {
        if (str == null || (i3 != -1 ? i3 <= i2 : str.length() <= i2)) {
            w01.a(C40.a("Invalid ", str2, " (must not be null or empty)"));
            return;
        }
        if (i3 == -1) {
            i3 = str.length();
        }
        if ((i & 65535) >= 49) {
            while (i2 < i3) {
                if (".;[/".indexOf(str.codePointAt(i2)) == -1) {
                    i2 = str.offsetByCodePoints(i2, 1);
                } else {
                    h0f.a("Invalid ", str2, " (must not contain . ; [ or /): ", str);
                    return;
                }
            }
            return;
        }
        int iOffsetByCodePoints = i2;
        while (iOffsetByCodePoints < i3) {
            if (iOffsetByCodePoints == i2) {
                if (Character.isJavaIdentifierStart(str.codePointAt(iOffsetByCodePoints))) {
                    iOffsetByCodePoints = str.offsetByCodePoints(iOffsetByCodePoints, 1);
                } else {
                    h0f.a("Invalid ", str2, " (must be a valid Java identifier): ", str);
                    return;
                }
            } else if (Character.isJavaIdentifierPart(str.codePointAt(iOffsetByCodePoints))) {
                iOffsetByCodePoints = str.offsetByCodePoints(iOffsetByCodePoints, 1);
            } else {
                h0f.a("Invalid ", str2, " (must be a valid Java identifier): ", str);
                return;
            }
        }
    }

    public static void a(int i, String str, String str2) {
        int i2 = 0;
        while (true) {
            try {
                int iIndexOf = str.indexOf(47, i2 + 1);
                if (iIndexOf != -1) {
                    a(i, str, i2, iIndexOf, (String) null);
                    i2 = iIndexOf + 1;
                } else {
                    a(i, str, i2, str.length(), (String) null);
                    return;
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid " + str2 + " (must be an internal class name): " + str, e);
            }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(String str, boolean z) {
        e();
        d(this.c, str);
        return new C0376Bb(super.a(str, z));
    }

    public final void a(WI wi, boolean z, String str) {
        if (wi != null) {
            if (z && this.k.get(wi) == null) {
                w01.a(C40.a("Invalid ", str, " (must be visited first)"));
                return;
            } else {
                this.l.add(wi);
                return;
            }
        }
        w01.a(C40.a("Invalid ", str, " (must not be null)"));
    }
}
