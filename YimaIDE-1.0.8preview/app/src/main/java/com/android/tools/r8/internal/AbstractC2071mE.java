package com.android.tools.r8.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2071mE {
    public static final C1491fW a(Lr lr, Um0 um0) throws IOException {
        int i;
        String[] strArr;
        KB.c(um0, "c");
        C1814jE c1814jE = um0.a;
        KB.a((Object) c1814jE, "null cannot be cast to non-null type org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable");
        C0389Bo c0389Bo = AbstractC1644hE.a;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C1473fE c1473fE = C1473fE.h;
        C1049aE c1049aE = new C1049aE();
        ArrayList arrayList = c1814jE.b;
        ArrayList arrayList2 = new ArrayList(AbstractC2015le.a((Iterable) arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C1387eE c1387eEE = ((C1219cE) it.next()).e();
            if (!c1387eEE.a()) {
                defpackage.bk.a();
                return null;
            }
            arrayList2.add(c1387eEE);
        }
        if ((c1049aE.c & 1) != 1) {
            c1049aE.d = new ArrayList(c1049aE.d);
            c1049aE.c |= 1;
        }
        AbstractC0574Ir.a(arrayList2, c1049aE.d);
        LinkedHashSet linkedHashSet = c1814jE.d;
        int i2 = 2;
        if ((c1049aE.c & 2) != 2) {
            c1049aE.e = new ArrayList(c1049aE.e);
            c1049aE.c |= 2;
        }
        AbstractC0574Ir.a(linkedHashSet, c1049aE.e);
        C1473fE c1473fEE = c1049aE.e();
        if (!c1473fEE.a()) {
            defpackage.bk.a();
            return null;
        }
        int iC = c1473fEE.c();
        int iB = C0767Qd.b(iC) + iC;
        if (iB > 4096) {
            iB = 4096;
        }
        C0767Qd c0767Qd = new C0767Qd(byteArrayOutputStream, new byte[iB]);
        c0767Qd.g(iC);
        c1473fEE.a(c0767Qd);
        c0767Qd.a();
        int iC2 = lr.c();
        C0767Qd c0767Qd2 = new C0767Qd(byteArrayOutputStream, new byte[iC2 <= 4096 ? iC2 : 4096]);
        lr.a(c0767Qd2);
        c0767Qd2.a();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray == null) {
            y6.a(0);
            throw null;
        }
        if (y6.a) {
            int length = ((byteArray.length * 8) + 6) / 7;
            byte[] bArr = new byte[length];
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                i = length - 1;
                if (i3 >= i) {
                    break;
                }
                if (i4 == 0) {
                    bArr[i3] = (byte) (byteArray[i5] & 127);
                    i4 = 7;
                } else {
                    int i6 = (byteArray[i5] & 255) >>> i4;
                    int i7 = (i4 + 7) & 7;
                    i5++;
                    bArr[i3] = (byte) (i6 + ((byteArray[i5] & ((1 << i7) - 1)) << (8 - i4)));
                    i4 = i7;
                }
                i3++;
            }
            if (length > 0) {
                boolean z = y6.b;
                if (!z && i4 == 0) {
                    x01.a("The last chunk cannot start from the input byte since otherwise at least one bit will remain unprocessed");
                    return null;
                }
                if (!z && i5 != byteArray.length - 1) {
                    StringBuilder sbA = Ni0.a(i5, "The last 7-bit chunk should be encoded from the last input byte: ", " != ");
                    sbA.append(byteArray.length - 1);
                    throw new AssertionError(sbA.toString());
                }
                bArr[i] = (byte) ((byteArray[i5] & 255) >>> i4);
            }
            for (int i8 = 0; i8 < length; i8++) {
                bArr[i8] = (byte) ((bArr[i8] + 1) & 127);
            }
            ArrayList arrayList3 = new ArrayList();
            int i9 = 0;
            boolean z2 = false;
            for (int i10 = 0; i10 < length; i10++) {
                if (i2 >= 65534) {
                    if (!y6.b && i2 > 65535) {
                        x01.a(CX.a(i2, "Produced strings cannot contain more than 65535 bytes: "));
                        return null;
                    }
                    String str = new String(bArr, i9, i10 - i9);
                    if (z2) {
                        arrayList3.add(str);
                    } else {
                        arrayList3.add("\uffff".concat(str));
                        z2 = true;
                    }
                    i2 = 0;
                    i9 = i10;
                }
                i2 = bArr[i10] == 0 ? i2 + 2 : i2 + 1;
            }
            if (i2 >= 0) {
                arrayList3.add(new String(bArr, i9, length - i9));
            }
            strArr = (String[]) arrayList3.toArray(new String[arrayList3.size()]);
            if (strArr == null) {
                y6.a(6);
                throw null;
            }
        } else {
            ArrayList arrayList4 = new ArrayList(1);
            StringBuilder sb = new StringBuilder();
            sb.append((char) 0);
            int length2 = byteArray.length;
            for (int i11 = 0; i11 < length2; i11++) {
                byte b = byteArray[i11];
                sb.append((char) (b & 255));
                i2 = (1 > b || b >= 128) ? i2 + 2 : i2 + 1;
                if (i2 >= 65534) {
                    arrayList4.add(sb.toString());
                    sb.setLength(0);
                    i2 = 0;
                }
            }
            if (sb.length() != 0) {
                arrayList4.add(sb.toString());
            }
            strArr = (String[]) arrayList4.toArray(new String[0]);
            if (strArr == null) {
                y6.a(1);
                throw null;
            }
        }
        return new C1491fW(strArr, c1814jE.a.toArray(new String[0]));
    }
}
