package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Mh0 extends J2 {
    public final mY b;

    public Mh0(J2 j2, Xg0 xg0) {
        super(j2);
        this.b = xg0;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        Xg0 xg0 = (Xg0) this.b;
        xg0.b(str);
        if (obj instanceof String) {
            mY.a(((mY) xg0).b, (String) obj);
        } else if (obj instanceof C3050xi0) {
            StringBuilder sb = ((mY) xg0).b;
            sb.append(((C3050xi0) obj).a());
            sb.append(".class");
        } else if (obj instanceof Byte) {
            byte bByteValue = ((Byte) obj).byteValue();
            StringBuilder sb2 = ((mY) xg0).b;
            sb2.append("(byte)");
            sb2.append((int) bByteValue);
        } else if (obj instanceof Boolean) {
            ((mY) xg0).b.append(((Boolean) obj).booleanValue());
        } else if (obj instanceof Short) {
            short sShortValue = ((Short) obj).shortValue();
            StringBuilder sb3 = ((mY) xg0).b;
            sb3.append("(short)");
            sb3.append((int) sShortValue);
        } else if (obj instanceof Character) {
            char cCharValue = ((Character) obj).charValue();
            StringBuilder sb4 = ((mY) xg0).b;
            sb4.append("(char)");
            sb4.append((int) cCharValue);
        } else if (obj instanceof Integer) {
            ((mY) xg0).b.append(((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            float fFloatValue = ((Float) obj).floatValue();
            StringBuilder sb5 = ((mY) xg0).b;
            sb5.append(fFloatValue);
            sb5.append('F');
        } else if (obj instanceof Long) {
            long jLongValue = ((Long) obj).longValue();
            StringBuilder sb6 = ((mY) xg0).b;
            sb6.append(jLongValue);
            sb6.append('L');
        } else if (obj instanceof Double) {
            double dDoubleValue = ((Double) obj).doubleValue();
            StringBuilder sb7 = ((mY) xg0).b;
            sb7.append(dDoubleValue);
            sb7.append('D');
        } else if (obj.getClass().isArray()) {
            ((mY) xg0).b.append('{');
            int i = 0;
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                while (i < bArr.length) {
                    xg0.b(i);
                    byte b = bArr[i];
                    StringBuilder sb8 = ((mY) xg0).b;
                    sb8.append("(byte)");
                    sb8.append((int) b);
                    i++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                while (i < zArr.length) {
                    xg0.b(i);
                    ((mY) xg0).b.append(zArr[i]);
                    i++;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                while (i < sArr.length) {
                    xg0.b(i);
                    short s = sArr[i];
                    StringBuilder sb9 = ((mY) xg0).b;
                    sb9.append("(short)");
                    sb9.append((int) s);
                    i++;
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                while (i < cArr.length) {
                    xg0.b(i);
                    char c = cArr[i];
                    StringBuilder sb10 = ((mY) xg0).b;
                    sb10.append("(char)");
                    sb10.append((int) c);
                    i++;
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                while (i < iArr.length) {
                    xg0.b(i);
                    ((mY) xg0).b.append(iArr[i]);
                    i++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                while (i < jArr.length) {
                    xg0.b(i);
                    long j = jArr[i];
                    StringBuilder sb11 = ((mY) xg0).b;
                    sb11.append(j);
                    sb11.append('L');
                    i++;
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                while (i < fArr.length) {
                    xg0.b(i);
                    float f = fArr[i];
                    StringBuilder sb12 = ((mY) xg0).b;
                    sb12.append(f);
                    sb12.append('F');
                    i++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                while (i < dArr.length) {
                    xg0.b(i);
                    double d = dArr[i];
                    StringBuilder sb13 = ((mY) xg0).b;
                    sb13.append(d);
                    sb13.append('D');
                    i++;
                }
            }
            ((mY) xg0).b.append('}');
        }
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(obj, str);
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        Xg0 xg0 = (Xg0) this.b;
        xg0.b(str);
        xg0.a(1, str2);
        StringBuilder sb = ((mY) xg0).b;
        sb.append('.');
        sb.append(str3);
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        super.a(str, str2, str3);
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        Xg0 xg0 = (Xg0) this.b;
        xg0.b(str);
        ((mY) xg0).b.append('@');
        xg0.a(1, str2);
        ((mY) xg0).b.append('(');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        return new Mh0(super.a(str, str2), xg0.a(")"));
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        Xg0 xg0 = (Xg0) this.b;
        xg0.b(str);
        ((mY) xg0).b.append('{');
        ((mY) xg0).c.add(((mY) xg0).b.toString());
        return new Mh0(super.a(str), xg0.a("}"));
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        this.b.getClass();
        super.a();
    }
}
