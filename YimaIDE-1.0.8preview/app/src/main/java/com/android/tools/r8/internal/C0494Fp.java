package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.f63;
import defpackage.g3c;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0494Fp {
    public static final C0494Fp d = new C0494Fp(0);
    public boolean b;
    public boolean c = false;
    public final Dc0 a = new Dc0(16);

    public C0494Fp(int i) {
        a();
    }

    public static void a(C0767Qd c0767Qd, Mm0 mm0, Object obj) throws IOException {
        switch (mm0.ordinal()) {
            case 0:
                double dDoubleValue = ((Double) obj).doubleValue();
                c0767Qd.getClass();
                c0767Qd.c(Double.doubleToRawLongBits(dDoubleValue));
                break;
            case 1:
                float fFloatValue = ((Float) obj).floatValue();
                c0767Qd.getClass();
                c0767Qd.f(Float.floatToRawIntBits(fFloatValue));
                break;
            case 2:
                c0767Qd.d(((Long) obj).longValue());
                break;
            case XmlPullParser.END_TAG /* 3 */:
                c0767Qd.d(((Long) obj).longValue());
                break;
            case 4:
                c0767Qd.d(((Integer) obj).intValue());
                break;
            case XmlPullParser.CDSECT /* 5 */:
                c0767Qd.c(((Long) obj).longValue());
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                c0767Qd.f(((Integer) obj).intValue());
                break;
            case 7:
                c0767Qd.e(((Boolean) obj).booleanValue() ? 1 : 0);
                break;
            case 8:
                c0767Qd.getClass();
                byte[] bytes = ((String) obj).getBytes("UTF-8");
                c0767Qd.g(bytes.length);
                c0767Qd.a(bytes);
                break;
            case 9:
                c0767Qd.getClass();
                ((L0) obj).a(c0767Qd);
                break;
            case XmlPullParser.DOCDECL /* 10 */:
                L0 l0 = (L0) obj;
                c0767Qd.getClass();
                c0767Qd.g(l0.c());
                l0.a(c0767Qd);
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                if (!(obj instanceof T7)) {
                    byte[] bArr = (byte[]) obj;
                    c0767Qd.getClass();
                    c0767Qd.g(bArr.length);
                    c0767Qd.a(bArr);
                } else {
                    T7 t7 = (T7) obj;
                    c0767Qd.getClass();
                    c0767Qd.g(t7.size());
                    c0767Qd.a(t7);
                }
                break;
            case 12:
                c0767Qd.g(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof ZA)) {
                    c0767Qd.d(((Integer) obj).intValue());
                } else {
                    c0767Qd.d(((ZA) obj).a());
                }
                break;
            case 14:
                c0767Qd.f(((Integer) obj).intValue());
                break;
            case 15:
                c0767Qd.c(((Long) obj).longValue());
                break;
            case Fcntl.S_IWGRP /* 16 */:
                int iIntValue = ((Integer) obj).intValue();
                c0767Qd.g((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                c0767Qd.d(C0767Qd.b(((Long) obj).longValue()));
                break;
        }
    }

    public final void b(Map.Entry entry) {
        C0677Mr c0677Mr = (C0677Mr) entry.getKey();
        Object value = entry.getValue();
        if (c0677Mr.d) {
            Object arrayList = this.a.get(c0677Mr);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            for (Object obj : (List) value) {
                List list = (List) arrayList;
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    byte[] bArr2 = new byte[bArr.length];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    obj = bArr2;
                }
                list.add(obj);
            }
            this.a.put(c0677Mr, arrayList);
            return;
        }
        Om0 om0 = c0677Mr.c.b;
        Om0 om1 = Om0.k;
        Dc0 dc0 = this.a;
        if (om0 != om1) {
            if (value instanceof byte[]) {
                byte[] bArr3 = (byte[]) value;
                byte[] bArr4 = new byte[bArr3.length];
                System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                value = bArr4;
            }
            dc0.put(c0677Mr, value);
            return;
        }
        Object obj2 = dc0.get(c0677Mr);
        if (obj2 != null) {
            this.a.put(c0677Mr, ((L0) obj2).e().a((AbstractC0729Or) ((L0) value)).c());
            return;
        }
        Dc0 dc1 = this.a;
        if (value instanceof byte[]) {
            byte[] bArr5 = (byte[]) value;
            byte[] bArr6 = new byte[bArr5.length];
            System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
            value = bArr6;
        }
        dc1.put(c0677Mr, value);
    }

    public final void c(C0677Mr c0677Mr, Object obj) {
        if (!c0677Mr.d) {
            b(c0677Mr.c, obj);
        } else {
            if (!(obj instanceof List)) {
                w01.a("Wrong object type used with protocol message reflection.");
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b(c0677Mr.c, it.next());
            }
            obj = arrayList;
        }
        this.a.put(c0677Mr, obj);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C0494Fp m10clone() {
        Dc0 dc0;
        C0494Fp c0494Fp = new C0494Fp();
        int i = 0;
        while (true) {
            int size = this.a.c.size();
            dc0 = this.a;
            if (i >= size) {
                break;
            }
            Map.Entry entry = (Map.Entry) dc0.c.get(i);
            c0494Fp.c((C0677Mr) entry.getKey(), entry.getValue());
            i++;
        }
        for (Map.Entry entry2 : dc0.d.isEmpty() ? Jc0.b : dc0.d.entrySet()) {
            c0494Fp.c((C0677Mr) entry2.getKey(), entry2.getValue());
        }
        c0494Fp.c = this.c;
        return c0494Fp;
    }

    public C0494Fp() {
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0022  */
    public static void b(Mm0 mm0, Object obj) {
        obj.getClass();
        boolean z = false;
        switch (mm0.b.ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case XmlPullParser.END_TAG /* 3 */:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case XmlPullParser.CDSECT /* 5 */:
                z = obj instanceof String;
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                if ((obj instanceof T7) || (obj instanceof byte[])) {
                    z = true;
                }
                break;
            case 7:
                if ((obj instanceof Integer) || (obj instanceof ZA)) {
                    z = true;
                }
                break;
            case 8:
                z = obj instanceof L0;
                break;
        }
        if (z) {
            return;
        }
        w01.a("Wrong object type used with protocol message reflection.");
    }

    public static int b(C0677Mr c0677Mr, Object obj) {
        int iC;
        int iA;
        Mm0 mm0 = c0677Mr.c;
        int i = c0677Mr.b;
        if (c0677Mr.d) {
            int iA2 = 0;
            if (c0677Mr.e) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iA2 += a(mm0, it.next());
                }
                iC = C0767Qd.c(i) + iA2;
                iA = C0767Qd.b(iA2);
            } else {
                for (Object obj2 : (List) obj) {
                    int iC2 = C0767Qd.c(i);
                    if (mm0 == Mm0.f) {
                        iC2 *= 2;
                    }
                    iA2 += a(mm0, obj2) + iC2;
                }
                return iA2;
            }
        } else {
            iC = C0767Qd.c(i);
            if (mm0 == Mm0.f) {
                iC *= 2;
            }
            iA = a(mm0, obj);
        }
        return iA + iC;
    }

    public static boolean a(Map.Entry entry) {
        C0677Mr c0677Mr = (C0677Mr) entry.getKey();
        if (c0677Mr.c.b != Om0.k) {
            return true;
        }
        if (c0677Mr.d) {
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                if (!((L0) it.next()).a()) {
                    return false;
                }
            }
            return true;
        }
        Object value = entry.getValue();
        if (value instanceof L0) {
            return ((L0) value).a();
        }
        w01.a("Wrong object type used with protocol message reflection.");
        return false;
    }

    public static Object a(C0638Ld c0638Ld, Mm0 mm0) {
        switch (mm0.ordinal()) {
            case 0:
                return Double.valueOf(Double.longBitsToDouble(c0638Ld.e()));
            case 1:
                return Float.valueOf(Float.intBitsToFloat(c0638Ld.d()));
            case 2:
                return Long.valueOf(c0638Ld.g());
            case XmlPullParser.END_TAG /* 3 */:
                return Long.valueOf(c0638Ld.g());
            case 4:
                return Integer.valueOf(c0638Ld.f());
            case XmlPullParser.CDSECT /* 5 */:
                return Long.valueOf(c0638Ld.e());
            case XmlPullParser.ENTITY_REF /* 6 */:
                return Integer.valueOf(c0638Ld.d());
            case 7:
                return Boolean.valueOf(c0638Ld.g() != 0);
            case 8:
                int iF = c0638Ld.f();
                int i = c0638Ld.b;
                int i2 = c0638Ld.d;
                if (iF > i - i2 || iF <= 0) {
                    if (iF == 0) {
                        return XmlPullParser.NO_NAMESPACE;
                    }
                    return new String(c0638Ld.c(iF), "UTF-8");
                }
                String str = new String(c0638Ld.a, i2, iF, "UTF-8");
                c0638Ld.d += iF;
                return str;
            case 9:
                w01.a("readPrimitiveField() cannot handle nested groups.");
                return null;
            case XmlPullParser.DOCDECL /* 10 */:
                w01.a("readPrimitiveField() cannot handle embedded messages.");
                return null;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return c0638Ld.b();
            case 12:
                return Integer.valueOf(c0638Ld.f());
            case 13:
                w01.a("readPrimitiveField() cannot handle enums.");
                return null;
            case 14:
                return Integer.valueOf(c0638Ld.d());
            case 15:
                return Long.valueOf(c0638Ld.e());
            case Fcntl.S_IWGRP /* 16 */:
                int iF2 = c0638Ld.f();
                return Integer.valueOf((-(iF2 & 1)) ^ (iF2 >>> 1));
            case 17:
                long jG = c0638Ld.g();
                return Long.valueOf((-(jG & 1)) ^ (jG >>> 1));
            default:
                f63.a("There is no way to get here, but the compiler thinks otherwise.");
                return null;
        }
    }

    public final void a() {
        if (this.b) {
            return;
        }
        Dc0 dc0 = this.a;
        if (!dc0.e) {
            for (int i = 0; i < dc0.c.size(); i++) {
                Map.Entry entry = (Map.Entry) dc0.c.get(i);
                if (((C0677Mr) entry.getKey()).d) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
            for (Map.Entry entry2 : dc0.d.isEmpty() ? Jc0.b : dc0.d.entrySet()) {
                if (((C0677Mr) entry2.getKey()).d) {
                    entry2.setValue(Collections.unmodifiableList((List) entry2.getValue()));
                }
            }
        }
        if (!dc0.e) {
            dc0.d = dc0.d.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(dc0.d);
            dc0.e = true;
        }
        this.b = true;
    }

    public final void a(C0677Mr c0677Mr, Object obj) {
        List arrayList;
        if (c0677Mr.d) {
            b(c0677Mr.c, obj);
            Object obj2 = this.a.get(c0677Mr);
            if (obj2 == null) {
                arrayList = new ArrayList();
                this.a.put(c0677Mr, arrayList);
            } else {
                arrayList = (List) obj2;
            }
            arrayList.add(obj);
            return;
        }
        w01.a("addRepeatedField() can only be called on repeated fields.");
    }

    public static int a(Mm0 mm0, Object obj) {
        int iC;
        int iB;
        switch (mm0.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                return 8;
            case 1:
                ((Float) obj).getClass();
                return 4;
            case 2:
                return C0767Qd.a(((Long) obj).longValue());
            case XmlPullParser.END_TAG /* 3 */:
                return C0767Qd.a(((Long) obj).longValue());
            case 4:
                int iIntValue = ((Integer) obj).intValue();
                if (iIntValue >= 0) {
                    return C0767Qd.b(iIntValue);
                }
                return 10;
            case XmlPullParser.CDSECT /* 5 */:
                ((Long) obj).getClass();
                return 8;
            case XmlPullParser.ENTITY_REF /* 6 */:
                ((Integer) obj).getClass();
                return 4;
            case 7:
                ((Boolean) obj).getClass();
                return 1;
            case 8:
                try {
                    byte[] bytes = ((String) obj).getBytes("UTF-8");
                    return C0767Qd.b(bytes.length) + bytes.length;
                } catch (UnsupportedEncodingException e) {
                    g3c.a("UTF-8 not supported.", e);
                    return 0;
                }
            case 9:
                return ((L0) obj).c();
            case XmlPullParser.DOCDECL /* 10 */:
                iC = ((L0) obj).c();
                iB = C0767Qd.b(iC);
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                if (obj instanceof T7) {
                    T7 t7 = (T7) obj;
                    iC = C0767Qd.b(t7.size());
                    iB = t7.size();
                } else {
                    byte[] bArr = (byte[]) obj;
                    return C0767Qd.b(bArr.length) + bArr.length;
                }
                break;
            case 12:
                return C0767Qd.b(((Integer) obj).intValue());
            case 13:
                if (obj instanceof ZA) {
                    return C0767Qd.a(((ZA) obj).a());
                }
                return C0767Qd.a(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).getClass();
                return 4;
            case 15:
                ((Long) obj).getClass();
                return 8;
            case Fcntl.S_IWGRP /* 16 */:
                int iIntValue2 = ((Integer) obj).intValue();
                return C0767Qd.b((iIntValue2 >> 31) ^ (iIntValue2 << 1));
            case 17:
                return C0767Qd.a(C0767Qd.b(((Long) obj).longValue()));
            default:
                f63.a("There is no way to get here, but the compiler thinks otherwise.");
                return 0;
        }
        return iB + iC;
    }
}
