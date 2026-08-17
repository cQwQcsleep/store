package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class S10 {
    public static final AH a(C2861vZ c2861vZ, InterfaceC2425qQ interfaceC2425qQ) {
        KB.c(c2861vZ, "<this>");
        KB.c(interfaceC2425qQ, "strings");
        boolean zBooleanValue = AbstractC2805uq.O.a(c2861vZ.n).booleanValue();
        EnumC2776uZ enumC2776uZ = c2861vZ.d;
        if (zBooleanValue) {
            int i = enumC2776uZ != null ? R10.a[enumC2776uZ.ordinal()] : -1;
            if (i == 1) {
                return new NH((byte) c2861vZ.e);
            }
            if (i == 2) {
                return new QH((short) c2861vZ.e);
            }
            if (i == 3) {
                return new OH((int) c2861vZ.e);
            }
            if (i == 4) {
                return new PH(c2861vZ.e);
            }
            f2f.a("Cannot read value of unsigned type: ", c2861vZ.d);
            return null;
        }
        switch (enumC2776uZ != null ? R10.a[enumC2776uZ.ordinal()] : -1) {
            case -1:
                return null;
            case 0:
            default:
                throw new HR();
            case 1:
                return new CH((byte) c2861vZ.e);
            case 2:
                return new LH((short) c2861vZ.e);
            case XmlPullParser.END_TAG /* 3 */:
                return new HH((int) c2861vZ.e);
            case 4:
                return new KH(c2861vZ.e);
            case XmlPullParser.CDSECT /* 5 */:
                return new DH((char) c2861vZ.e);
            case XmlPullParser.ENTITY_REF /* 6 */:
                return new GH(c2861vZ.f);
            case 7:
                return new EH(c2861vZ.g);
            case 8:
                return new BH(c2861vZ.e != 0);
            case 9:
                return new MH(((ND) interfaceC2425qQ).a(c2861vZ.h));
            case XmlPullParser.DOCDECL /* 10 */:
                String strA = a(interfaceC2425qQ, c2861vZ.i);
                int i2 = c2861vZ.m;
                if (i2 != 0) {
                    return new C3184zH(i2, strA);
                }
                KB.c(strA, "className");
                return new IH(0, strA);
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return new FH(a(interfaceC2425qQ, c2861vZ.i), ((ND) interfaceC2425qQ).a(c2861vZ.j));
            case 12:
                C3117yZ c3117yZ = c2861vZ.k;
                KB.b(c3117yZ, "getAnnotation(...)");
                return new C3099yH(a(c3117yZ, interfaceC2425qQ));
            case 13:
                List<C2861vZ> list = c2861vZ.l;
                KB.b(list, "getArrayElementList(...)");
                ArrayList arrayList = new ArrayList();
                for (C2861vZ c2861vZ2 : list) {
                    KB.a(c2861vZ2);
                    AH ahA = a(c2861vZ2, interfaceC2425qQ);
                    if (ahA != null) {
                        arrayList.add(ahA);
                    }
                }
                return new AH.a(arrayList);
        }
    }

    public static final String a(InterfaceC2425qQ interfaceC2425qQ, int i) {
        KB.c(interfaceC2425qQ, "<this>");
        ND nd = (ND) interfaceC2425qQ;
        String strA = nd.a(i);
        return nd.b.contains(Integer.valueOf(i)) ? ".".concat(strA) : strA;
    }

    public static final C3015xH a(C3117yZ c3117yZ, InterfaceC2425qQ interfaceC2425qQ) {
        KB.c(c3117yZ, "<this>");
        KB.c(interfaceC2425qQ, "strings");
        String strA = a(interfaceC2425qQ, c3117yZ.d);
        List<C2947wZ> list = c3117yZ.e;
        KB.b(list, "getArgumentList(...)");
        ArrayList arrayList = new ArrayList();
        for (C2947wZ c2947wZ : list) {
            C2861vZ c2861vZ = c2947wZ.e;
            KB.b(c2861vZ, "getValue(...)");
            AH ahA = a(c2861vZ, interfaceC2425qQ);
            C1491fW c1491fW = ahA != null ? new C1491fW(((ND) interfaceC2425qQ).a(c2947wZ.d), ahA) : null;
            if (c1491fW != null) {
                arrayList.add(c1491fW);
            }
        }
        return new C3015xH(strA, AbstractC1823jN.a(arrayList));
    }
}
