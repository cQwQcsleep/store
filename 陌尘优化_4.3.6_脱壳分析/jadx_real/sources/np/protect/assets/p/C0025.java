package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0025 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f49short = {2424, 2413, 2413, 2424, 2426, 2417, 2395, 2424, 2410, 2428, 2394, 2422, 2423, 2413, 2428, 2401, 2413};

    /* renamed from: ۟۟۟۠ۡ, reason: not valid java name and contains not printable characters */
    public static String m417() {
        return C0043.m454(m418(), 0, C0052.f91 ^ (-177), 2329);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:60:0x008b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0097 A[SYNTHETIC] */
    /* renamed from: ۥۡۡۨ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m418() {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5D5B6C0B5C2");
        int iM190 = C0001.m190(strDecode2);
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5EFB6C4B5C2");
                String strDecode4 = "ۥ۟ۥ";
                switch (iM190) {
                    case 1746756:
                        if (C0053.m574() >= 0) {
                            C0002.m259();
                            iM190 = C0053.m562(strDecode3);
                        } else {
                            iM190 = (C0053.f92 + C0052.f91) ^ (-1752645);
                        }
                    case 1746877:
                        sArr = f49short;
                        iM190 = C0043.m455(NPStringFog.decode("B5EFB6C0B5C7"));
                    case 1747686:
                    case 1750723:
                        iM190 = C0053.m562(strDecode4);
                    case 1748614:
                        if ((C0002.f2 ^ (C0001.f1 / (-2169))) < 0) {
                            strDecode = NPStringFog.decode("B5D7B6C9B5C1");
                            iM190 = C0002.m230(strDecode);
                        } else {
                            i = C0052.f91 - C0052.f91;
                            i2 = 1752581;
                            iM190 = i + i2;
                        }
                    case 1750570:
                        if (C0052.m520() <= 0) {
                            strDecode = NPStringFog.decode("B5D4B6C4B5C7");
                            iM190 = C0002.m230(strDecode);
                        } else {
                            strDecode4 = NPStringFog.decode("B5D0B6C1B5C7");
                            iM190 = C0053.m562(strDecode4);
                        }
                    case 1751685:
                        if (C0002.m259() >= 0) {
                            C0002.m259();
                            strDecode4 = NPStringFog.decode("B5EFB6C3");
                        } else {
                            strDecode4 = strDecode2;
                        }
                        iM190 = C0053.m562(strDecode4);
                    case 1752459:
                        break;
                    case 1752519:
                        if (C0001.m192() <= 0) {
                            if (C0002.f2 >= 0) {
                                C0002.m259();
                                strDecode3 = "ۥ۟ۥ";
                            }
                            iM190 = C0043.m455(strDecode3);
                        } else if ((C0002.f2 ^ (C0001.f1 / (-2169))) < 0) {
                        }
                        break;
                    case 1752581:
                        if (C0002.m259() >= 0) {
                            C0001.f1 = 5;
                            iM190 = C0002.m230(strDecode2);
                        } else {
                            i = C0053.f92 - C0001.f1;
                            i2 = 1753783;
                            iM190 = i + i2;
                        }
                    case 1752614:
                        if (C0052.f91 - (C0052.f91 % (-8138)) != 0) {
                            C0053.m574();
                        }
                        iM190 = C0052.m503(NPStringFog.decode("B5D3B6C1B5C6"));
                        sArr2 = null;
                }
                return sArr2;
            }
        }
    }
}
