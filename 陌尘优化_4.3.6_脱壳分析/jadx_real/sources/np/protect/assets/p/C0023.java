package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0023 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f47short = {2251, 2244, 2254, 2264, 2245, 2243, 2254, 2180, 2249, 2245, 2244, 2270, 2255, 2244, 2270, 2180, 2281, 2245, 2244, 2270, 2255, 2258, 2270, 2301, 2264, 2251, 2266, 2266, 2255, 2264};

    /* renamed from: ۟۟۟۠۟, reason: not valid java name and contains not printable characters */
    public static String m413() {
        return C0052.m507(m414(), 0, C0052.f91 ^ (-192), 2218);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0088 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x007d A[SYNTHETIC] */
    /* renamed from: ۟ۦۧ۠ۡ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m414() {
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D8B6C1B5C4");
        int iM190 = C0001.m190(strDecode3);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D5B6C3B5C9");
            switch (iM190) {
                case 56567:
                    sArr2 = sArr;
                    iM190 = C0043.m455(strDecode4);
                case 1746751:
                case 1749757:
                    if (C0053.f92 >= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D6B6C2B5C4");
                        iM190 = C0053.m562(strDecode);
                    } else {
                        iM190 = C0053.m562(strDecode4);
                    }
                case 1746783:
                    if (C0001.m192() >= 0) {
                        C0053.m574();
                        iM190 = C0043.m455(strDecode4);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D2B6C4B5C1");
                        iM190 = C0002.m230(strDecode2);
                    }
                case 1748648:
                    if ((C0043.f73 ^ (C0002.f2 - 3231)) <= 0) {
                        C0052.f91 = 18;
                        strDecode4 = NPStringFog.decode("B5D7B6C0B5C4");
                    } else {
                        strDecode4 = strDecode3;
                    }
                    iM190 = C0053.m562(strDecode4);
                case 1752457:
                    if (C0053.f92 + (C0052.f91 - 6240) < 0) {
                        C0053.f92 = 24;
                        strDecode2 = NPStringFog.decode("B5EFB6FEB5C9");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        iM190 = (C0001.f1 / C0001.f1) ^ 1755555;
                    }
                case 1752555:
                    break;
                case 1753420:
                    sArr = f47short;
                    if (C0002.f2 % (C0052.f91 * 5132) >= 0) {
                        C0002.f2 = 14;
                        strDecode2 = NPStringFog.decode("B5D1B6C1B5C6");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        strDecode = NPStringFog.decode("B5D8B6FE");
                        iM190 = C0053.m562(strDecode);
                    }
                case 1754661:
                    if (C0052.m520() <= 0) {
                        C0002.m259();
                        iM190 = C0002.m230(strDecode3);
                        sArr2 = null;
                    } else {
                        strDecode2 = NPStringFog.decode("B5EFB6C3B5C3");
                        sArr2 = null;
                        iM190 = C0002.m230(strDecode2);
                    }
                case 1755373:
                    if (C0052.m520() >= 0) {
                        if (C0052.f91 >= 0) {
                            C0002.f2 = 46;
                            strDecode2 = NPStringFog.decode("B5D5B6FEB5C2");
                            iM190 = C0002.m230(strDecode2);
                        } else {
                            iM190 = (C0002.f2 - C0043.f73) + 1753329;
                        }
                    } else if (C0053.f92 + (C0052.f91 - 6240) < 0) {
                    }
                    break;
                case 1755554:
                    strDecode = NPStringFog.decode("B5D7B6C9B5C7");
                    iM190 = C0053.m562(strDecode);
            }
            return sArr2;
        }
    }
}
