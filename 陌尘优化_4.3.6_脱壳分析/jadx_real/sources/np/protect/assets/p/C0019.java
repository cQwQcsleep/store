package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟ۥ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0019 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f43short = {2450, 2551, 2502, 2502, 2548, 2527, 2520, 2514, 2546, 2519, 2498, 2519};

    /* renamed from: ۟۟۟۟ۦ, reason: not valid java name and contains not printable characters */
    public static String m405() {
        return C0043.m454(m406(), 0, C0043.f73 ^ (-171), 2486);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0090 A[SYNTHETIC] */
    /* renamed from: ۢۢ۟ۡ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m406() {
        String strDecode;
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode2;
        int iM503 = C0052.m503(NPStringFog.decode("B5D7B6C5B5C9"));
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                strDecode = NPStringFog.decode("B5D0B6FEB5C9");
                String strDecode3 = "۠ۥۧ";
                switch (iM503) {
                    case 56418:
                        if (C0053.f92 >= 0) {
                            break;
                        }
                        sArr2 = sArr;
                        strDecode2 = NPStringFog.decode("B5D1B6C9B5C9");
                        iM503 = C0052.m503(strDecode2);
                        break;
                    case 1746880:
                    case 1747657:
                        if ((C0043.f73 ^ (C0002.f2 + 7131)) >= 0) {
                            C0053.f92 = 98;
                            strDecode3 = NPStringFog.decode("B5D2B6C7B5C2");
                            iM503 = C0052.m503(strDecode3);
                        } else {
                            i = C0053.f92 + C0043.f73;
                            i2 = 1749878;
                            iM503 = i + i2;
                        }
                    case 1747842:
                        i3 = C0052.f91 / C0043.f73;
                        i4 = 1755560;
                        iM503 = i3 ^ i4;
                    case 1748896:
                        if (C0001.f1 <= 0) {
                            C0001.m192();
                            iM503 = C0052.m503(strDecode3);
                        } else {
                            iM503 = C0001.m190(strDecode);
                        }
                    case 1748897:
                        break;
                    case 1751716:
                        if (C0052.f91 + (C0043.f73 ^ 1875) < 0) {
                            C0052.m520();
                            iM503 = C0002.m230(NPStringFog.decode("B5D7B6C9B5FE"));
                        } else {
                            iM503 = C0052.m503(strDecode3);
                        }
                    case 1754539:
                        if (C0053.m574() <= 0) {
                            int i5 = C0001.f1;
                            strDecode2 = NPStringFog.decode("B5D8B6C6B5C2");
                            iM503 = C0052.m503(strDecode2);
                        } else if (C0052.f91 + (C0043.f73 ^ 1875) < 0) {
                        }
                        break;
                    case 1755523:
                        if ((C0052.f91 ^ (C0052.f91 % (-8398))) != 0) {
                            C0043.m456();
                            iM503 = C0001.m190(NPStringFog.decode("B5D4B6C3B5C6"));
                        } else {
                            i3 = C0053.f92 % C0001.f1;
                            i4 = -1754573;
                            iM503 = i3 ^ i4;
                        }
                    case 1755560:
                        if (C0053.f92 >= 0) {
                            C0002.f2 = 26;
                            sArr2 = null;
                            strDecode2 = NPStringFog.decode("B5D1B6C9B5C9");
                            iM503 = C0052.m503(strDecode2);
                        } else {
                            iM503 = (C0052.f91 - C0002.f2) ^ (-1748982);
                            sArr2 = null;
                        }
                    case 1755588:
                        sArr = f43short;
                        if (C0001.f1 * (C0002.f2 ^ 1002) >= 0) {
                            C0053.f92 = 72;
                            iM503 = C0053.m562(NPStringFog.decode("B5D8B6C4B5C1"));
                        } else {
                            i = C0002.f2 / C0001.f1;
                            i2 = 56418;
                            iM503 = i + i2;
                        }
                }
                return sArr2;
            }
            C0043.m456();
            iM503 = C0001.m190(strDecode);
        }
    }
}
