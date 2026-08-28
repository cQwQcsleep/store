package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠ۢ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0027 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f51short = {2930, 2933, 2941, 2932};

    /* renamed from: ۣ۟۟۟۠, reason: not valid java name and contains not printable characters */
    public static String m421() {
        return C0002.m242(m422(), 0, C0001.f1 ^ 359, 2843);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0060 A[SYNTHETIC] */
    /* renamed from: ۣ۟ۦۡۢ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m422() {
        int i;
        int i2;
        String strDecode = NPStringFog.decode("B5D6B6C6B5C3");
        int iM455 = C0043.m455(strDecode);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5EFB6C1B5C4");
            switch (iM455) {
                case 56536:
                    strDecode2 = NPStringFog.decode("B5EFB6C2B5C3");
                    sArr2 = sArr;
                    iM455 = C0043.m455(strDecode2);
                case 1746724:
                    sArr = f51short;
                    if (C0053.m574() >= 0) {
                        C0001.f1 = 79;
                        iM455 = C0002.m230(NPStringFog.decode("B5EFB6C5B5FE"));
                    } else {
                        i = C0043.f73 / C0053.f92;
                        i2 = 56536;
                        iM455 = i + i2;
                    }
                case 1746814:
                    break;
                case 1746842:
                case 1753512:
                    i = C0053.f92 / C0043.f73;
                    i2 = 1746810;
                    iM455 = i + i2;
                case 1746849:
                    if (C0002.f2 >= 0) {
                        C0002.f2 = 55;
                        strDecode2 = NPStringFog.decode("B5D5B6C2B5C6");
                    } else {
                        strDecode2 = strDecode;
                    }
                    iM455 = C0043.m455(strDecode2);
                case 1748765:
                    if (C0053.f92 / (C0001.f1 * 5453) != 0) {
                        C0052.f91 = 27;
                        iM455 = C0001.m190(NPStringFog.decode("B5D1B6C5B5C1"));
                    } else {
                        iM455 = (C0001.f1 * C0053.f92) + 2043408;
                    }
                    sArr2 = null;
                case 1750819:
                    i = C0052.f91 + C0043.f73;
                    i2 = 1749094;
                    iM455 = i + i2;
                case 1752739:
                    if ((C0053.f92 | (C0001.f1 / 292)) < 0) {
                        C0052.f91 = 91;
                        iM455 = C0001.m190(NPStringFog.decode("B5D1B6FEB5C1"));
                    } else {
                        strDecode2 = NPStringFog.decode("B5D3B6C9B5C9");
                        iM455 = C0043.m455(strDecode2);
                    }
                case 1753665:
                    if (C0052.m520() >= 0) {
                        if (C0043.f73 >= 0) {
                            C0001.m192();
                            iM455 = C0043.m455(NPStringFog.decode("B5EFB6C5B5C7"));
                        } else {
                            iM455 = C0043.m455(strDecode2);
                        }
                    } else if ((C0053.f92 | (C0001.f1 / 292)) < 0) {
                    }
                    break;
                case 1754438:
                    if ((C0001.f1 ^ (C0053.f92 / (-5544))) <= 0) {
                        C0001.f1 = 74;
                    } else {
                        strDecode2 = NPStringFog.decode("B5D6B6C3B5C5");
                    }
                    iM455 = C0001.m190(strDecode2);
            }
            return sArr2;
        }
    }
}
