package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L2 extends J2 {
    public final Ag0 b;
    public final boolean c;
    public final X7 d;
    public final int e;
    public int f;
    public final L2 g;
    public final L2 h;

    public L2(Ag0 ag0, boolean z, X7 x7, L2 l2) {
        super(null);
        this.b = ag0;
        this.c = z;
        this.d = x7;
        int i = x7.b;
        this.e = i == 0 ? -1 : i - 2;
        this.g = l2;
        if (l2 != null) {
            l2.h = this;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        this.f++;
        if (this.c) {
            this.d.d(this.b.a(str));
        }
        if (obj instanceof String) {
            this.d.c(115, this.b.a((String) obj));
            return;
        }
        if (obj instanceof Byte) {
            this.d.c(66, this.b.a(3, (int) ((Byte) obj).byteValue()).a);
            return;
        }
        if (obj instanceof Boolean) {
            this.d.c(90, this.b.a(3, ((Boolean) obj).booleanValue() ? 1 : 0).a);
            return;
        }
        if (obj instanceof Character) {
            this.d.c(67, this.b.a(3, (int) ((Character) obj).charValue()).a);
            return;
        }
        if (obj instanceof Short) {
            this.d.c(83, this.b.a(3, (int) ((Short) obj).shortValue()).a);
            return;
        }
        if (obj instanceof C3050xi0) {
            this.d.c(99, this.b.a(((C3050xi0) obj).b()));
            return;
        }
        int i = 0;
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            this.d.c(91, bArr.length);
            int length = bArr.length;
            while (i < length) {
                this.d.c(66, this.b.a(3, (int) bArr[i]).a);
                i++;
            }
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            this.d.c(91, zArr.length);
            int length2 = zArr.length;
            while (i < length2) {
                this.d.c(90, this.b.a(3, zArr[i] ? 1 : 0).a);
                i++;
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            this.d.c(91, sArr.length);
            int length3 = sArr.length;
            while (i < length3) {
                this.d.c(83, this.b.a(3, (int) sArr[i]).a);
                i++;
            }
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            this.d.c(91, cArr.length);
            int length4 = cArr.length;
            while (i < length4) {
                this.d.c(67, this.b.a(3, (int) cArr[i]).a);
                i++;
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            this.d.c(91, iArr.length);
            int length5 = iArr.length;
            while (i < length5) {
                this.d.c(73, this.b.a(3, iArr[i]).a);
                i++;
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            this.d.c(91, jArr.length);
            int length6 = jArr.length;
            while (i < length6) {
                this.d.c(74, this.b.a(5, jArr[i]).a);
                i++;
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            this.d.c(91, fArr.length);
            int length7 = fArr.length;
            while (i < length7) {
                float f = fArr[i];
                X7 x7 = this.d;
                Ag0 ag0 = this.b;
                ag0.getClass();
                x7.c(70, ag0.a(4, Float.floatToRawIntBits(f)).a);
                i++;
            }
            return;
        }
        if (!(obj instanceof double[])) {
            C3130yg0 c3130yg0A = this.b.a(obj);
            this.d.c(".s.IFJDCS".charAt(c3130yg0A.b), c3130yg0A.a);
            return;
        }
        double[] dArr = (double[]) obj;
        this.d.c(91, dArr.length);
        int length8 = dArr.length;
        while (i < length8) {
            double d = dArr[i];
            X7 x8 = this.d;
            Ag0 ag1 = this.b;
            ag1.getClass();
            x8.c(68, ag1.a(6, Double.doubleToRawLongBits(d)).a);
            i++;
        }
    }

    public final int b(String str) {
        if (str != null) {
            this.b.a(str);
        }
        int i = 8;
        while (this != null) {
            i += this.d.b;
            this = this.g;
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:11:0x001f  */
    /* JADX WARN: Code duplicated, block: B:12:0x0029  */
    public static L2 a(Ag0 ag0, int i, C3052xj0 c3052xj0, String str, L2 l2) {
        X7 x7 = new X7();
        int i2 = i >>> 24;
        if (i2 != 0 && i2 != 1) {
            switch (i2) {
                case Fcntl.S_IWGRP /* 16 */:
                case 17:
                case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                case AndroidSdkVersion.M /* 23 */:
                    x7.c(i2, (i & 16776960) >> 8);
                    break;
                case AndroidSdkVersion.KITKAT /* 19 */:
                case 20:
                case AndroidSdkVersion.LOLLIPOP /* 21 */:
                    x7.b(i2);
                    break;
                case 22:
                    x7.d(i >>> 16);
                    break;
                default:
                    switch (i2) {
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                            x7.c(i2, (i & 16776960) >> 8);
                            break;
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                            x7.c(i);
                            break;
                        default:
                            j2d.a();
                            return null;
                    }
                    break;
            }
        } else {
            x7.d(i >>> 16);
        }
        if (c3052xj0 == null) {
            x7.b(0);
        } else {
            byte[] bArr = c3052xj0.a;
            int i3 = c3052xj0.b;
            x7.a(bArr, i3, (bArr[i3] * 2) + 1);
        }
        x7.d(ag0.a(str)).d(0);
        return new L2(ag0, true, x7, l2);
    }

    public static L2 a(Ag0 ag0, String str, L2 l2) {
        X7 x7 = new X7();
        x7.d(ag0.a(str)).d(0);
        return new L2(ag0, true, x7, l2);
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        this.f++;
        if (this.c) {
            this.d.d(this.b.a(str));
        }
        this.d.c(101, this.b.a(str2)).d(this.b.a(str3));
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        this.f++;
        if (this.c) {
            this.d.d(this.b.a(str));
        }
        this.d.c(64, this.b.a(str2)).d(0);
        return new L2(this.b, true, this.d, null);
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        this.f++;
        if (this.c) {
            this.d.d(this.b.a(str));
        }
        this.d.c(91, 0);
        return new L2(this.b, false, this.d, null);
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        int i = this.e;
        if (i != -1) {
            byte[] bArr = this.d.a;
            int i2 = this.f;
            bArr[i] = (byte) (i2 >>> 8);
            bArr[i + 1] = (byte) i2;
        }
    }

    public static int a(L2 l2, L2 l3, L2 l4, L2 l5) {
        int iB = l2 != null ? l2.b("RuntimeVisibleAnnotations") : 0;
        if (l3 != null) {
            iB += l3.b("RuntimeInvisibleAnnotations");
        }
        if (l4 != null) {
            iB += l4.b("RuntimeVisibleTypeAnnotations");
        }
        return l5 != null ? l5.b("RuntimeInvisibleTypeAnnotations") + iB : iB;
    }

    public final void a(int i, X7 x7) {
        int i2 = 2;
        L2 l2 = null;
        int i3 = 0;
        while (this != null) {
            this.a();
            i2 += this.d.b;
            i3++;
            l2 = this;
            this = this.g;
        }
        x7.d(i);
        x7.c(i2);
        x7.d(i3);
        while (l2 != null) {
            X7 x8 = l2.d;
            x7.a(x8.a, 0, x8.b);
            l2 = l2.h;
        }
    }

    public static void a(Ag0 ag0, L2 l2, L2 l3, L2 l4, L2 l5, X7 x7) {
        if (l2 != null) {
            l2.a(ag0.a("RuntimeVisibleAnnotations"), x7);
        }
        if (l3 != null) {
            l3.a(ag0.a("RuntimeInvisibleAnnotations"), x7);
        }
        if (l4 != null) {
            l4.a(ag0.a("RuntimeVisibleTypeAnnotations"), x7);
        }
        if (l5 != null) {
            l5.a(ag0.a("RuntimeInvisibleTypeAnnotations"), x7);
        }
    }

    public static void a(int i, L2[] l2Arr, int i2, X7 x7) {
        int iB = (i2 * 2) + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            L2 l2 = l2Arr[i3];
            iB += l2 == null ? 0 : l2.b(null) - 8;
        }
        x7.d(i);
        x7.c(iB);
        x7.b(i2);
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = 0;
            L2 l3 = null;
            for (L2 l4 = l2Arr[i4]; l4 != null; l4 = l4.g) {
                l4.a();
                i5++;
                l3 = l4;
            }
            x7.d(i5);
            while (l3 != null) {
                X7 x8 = l3.d;
                x7.a(x8.a, 0, x8.b);
                l3 = l3.h;
            }
        }
    }
}
