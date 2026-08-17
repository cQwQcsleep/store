package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class D2 extends J2 {
    public final String b;
    public List c;

    public D2(String str) {
        this(0, str);
        if (getClass() == D2.class) {
            return;
        }
        g33.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (this.c == null) {
            this.c = new ArrayList(this.b != null ? 2 : 1);
        }
        if (this.b != null) {
            this.c.add(str);
        }
        int i = 0;
        if (obj instanceof byte[]) {
            List list = this.c;
            byte[] bArr = (byte[]) obj;
            ArrayList arrayList = new ArrayList(bArr.length);
            int length = bArr.length;
            while (i < length) {
                arrayList.add(Byte.valueOf(bArr[i]));
                i++;
            }
            list.add(arrayList);
            return;
        }
        if (obj instanceof boolean[]) {
            List list2 = this.c;
            boolean[] zArr = (boolean[]) obj;
            ArrayList arrayList2 = new ArrayList(zArr.length);
            int length2 = zArr.length;
            while (i < length2) {
                arrayList2.add(Boolean.valueOf(zArr[i]));
                i++;
            }
            list2.add(arrayList2);
            return;
        }
        if (obj instanceof short[]) {
            List list3 = this.c;
            short[] sArr = (short[]) obj;
            ArrayList arrayList3 = new ArrayList(sArr.length);
            int length3 = sArr.length;
            while (i < length3) {
                arrayList3.add(Short.valueOf(sArr[i]));
                i++;
            }
            list3.add(arrayList3);
            return;
        }
        if (obj instanceof char[]) {
            List list4 = this.c;
            char[] cArr = (char[]) obj;
            ArrayList arrayList4 = new ArrayList(cArr.length);
            int length4 = cArr.length;
            while (i < length4) {
                arrayList4.add(Character.valueOf(cArr[i]));
                i++;
            }
            list4.add(arrayList4);
            return;
        }
        if (obj instanceof int[]) {
            this.c.add(AbstractC2287ol0.a((int[]) obj));
            return;
        }
        if (obj instanceof long[]) {
            List list5 = this.c;
            long[] jArr = (long[]) obj;
            ArrayList arrayList5 = new ArrayList(jArr.length);
            int length5 = jArr.length;
            while (i < length5) {
                arrayList5.add(Long.valueOf(jArr[i]));
                i++;
            }
            list5.add(arrayList5);
            return;
        }
        if (obj instanceof float[]) {
            List list6 = this.c;
            float[] fArr = (float[]) obj;
            ArrayList arrayList6 = new ArrayList(fArr.length);
            int length6 = fArr.length;
            while (i < length6) {
                arrayList6.add(Float.valueOf(fArr[i]));
                i++;
            }
            list6.add(arrayList6);
            return;
        }
        boolean z = obj instanceof double[];
        List list7 = this.c;
        if (!z) {
            list7.add(obj);
            return;
        }
        double[] dArr = (double[]) obj;
        ArrayList arrayList7 = new ArrayList(dArr.length);
        int length7 = dArr.length;
        while (i < length7) {
            arrayList7.add(Double.valueOf(dArr[i]));
            i++;
        }
        list7.add(arrayList7);
    }

    public D2(ArrayList arrayList) {
        super(null);
        this.c = arrayList;
    }

    public D2(int i, String str) {
        super(null);
        this.b = str;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        if (this.c == null) {
            this.c = new ArrayList(this.b != null ? 2 : 1);
        }
        if (this.b != null) {
            this.c.add(str);
        }
        this.c.add(new String[]{str2, str3});
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        if (this.c == null) {
            this.c = new ArrayList(this.b != null ? 2 : 1);
        }
        if (this.b != null) {
            this.c.add(str);
        }
        D2 d2 = new D2(str2);
        this.c.add(d2);
        return d2;
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        if (this.c == null) {
            this.c = new ArrayList(this.b != null ? 2 : 1);
        }
        if (this.b != null) {
            this.c.add(str);
        }
        ArrayList arrayList = new ArrayList();
        this.c.add(arrayList);
        return new D2(arrayList);
    }

    public final void a(J2 j2) {
        if (j2 != null) {
            List list = this.c;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i += 2) {
                    a(j2, (String) this.c.get(i), this.c.get(i + 1));
                }
            }
            j2.a();
        }
    }

    public static void a(J2 j2, String str, Object obj) {
        if (j2 != null) {
            if (obj instanceof String[]) {
                String[] strArr = (String[]) obj;
                j2.a(str, strArr[0], strArr[1]);
                return;
            }
            if (obj instanceof D2) {
                D2 d2 = (D2) obj;
                d2.a(j2.a(str, d2.b));
                return;
            }
            if (obj instanceof List) {
                J2 j2A = j2.a(str);
                if (j2A != null) {
                    List list = (List) obj;
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        a(j2A, (String) null, list.get(i));
                    }
                    j2A.a();
                    return;
                }
                return;
            }
            j2.a(obj, str);
        }
    }
}
