package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟ۨ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0022 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f46short = {3269, 3274, 3264, 3286, 3275, 3277, 3264, 3210, 3271, 3275, 3274, 3280, 3265, 3274, 3280, 3210, 3303, 3275, 3274, 3280, 3265, 3292, 3280};

    /* renamed from: ۟۟۟۠, reason: not valid java name and contains not printable characters */
    public static String m411() {
        return C0053.m577(m412(), 0, C0052.f91 ^ (-183), 3236);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* renamed from: ۟ۢۦۣۧ, reason: not valid java name and contains not printable characters */
    public static short[] m412() {
        int i;
        int i2;
        String strDecode;
        int iM230 = C0002.m230(NPStringFog.decode("B5EFB6C5B5C4"));
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D5B6FEB5C0");
            String strDecode3 = "ۧۦۧ";
            switch (iM230) {
                case 56568:
                    sArr = null;
                    iM230 = C0043.m455(strDecode3);
                case 1746847:
                case 1748828:
                    if (C0002.m259() >= 0) {
                        C0052.m520();
                        iM230 = C0043.m455(NPStringFog.decode("B5D4B6C3B5C7"));
                    } else {
                        iM230 = (C0001.f1 % C0002.f2) ^ 1748909;
                    }
                case 1746848:
                    if (C0001.m192() <= 0) {
                        if (C0052.f91 * (C0002.f2 + 9426) >= 0) {
                            C0043.m456();
                            strDecode = NPStringFog.decode("B5D6B6C1B5FE");
                        } else {
                            strDecode = NPStringFog.decode("B5EFB6C4B5C1");
                        }
                        iM230 = C0053.m562(strDecode);
                    } else {
                        i = C0053.f92 % C0052.f91;
                        i2 = 1751595;
                        iM230 = i + i2;
                    }
                case 1746874:
                    sArr2 = f46short;
                    if (C0052.m520() <= 0) {
                        C0002.m259();
                        iM230 = C0052.m503("ۧۦۧ");
                    } else {
                        iM230 = C0001.m190(strDecode2);
                    }
                case 1748894:
                    break;
                case 1749764:
                    if (C0043.m456() <= 0) {
                        C0052.m520();
                        strDecode3 = NPStringFog.decode("B5D8B6C3B5C5");
                        iM230 = C0043.m455(strDecode3);
                    } else {
                        i = C0053.f92 + C0001.f1;
                        i2 = 1747307;
                        iM230 = i + i2;
                    }
                case 1751591:
                    if (C0001.f1 <= 0) {
                        C0052.f91 = 32;
                    }
                    iM230 = C0052.m503(NPStringFog.decode("B5D8B6C1"));
                case 1752455:
                    if (C0001.f1 <= 0) {
                        strDecode2 = NPStringFog.decode("B5D2B6C4B5C6");
                        sArr = sArr2;
                        iM230 = C0001.m190(strDecode2);
                    } else {
                        iM230 = (C0052.f91 % C0043.f73) ^ (-1748800);
                        sArr = sArr2;
                    }
                case 1753445:
                    i = C0053.f92 % C0052.f91;
                    i2 = 1751595;
                    iM230 = i + i2;
                case 1754600:
                    if (C0053.f92 >= 0) {
                        C0001.f1 = 61;
                        iM230 = C0053.m562(strDecode2);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D1B6C7B5C0");
                        iM230 = C0001.m190(strDecode2);
                    }
            }
            return sArr;
        }
    }
}
