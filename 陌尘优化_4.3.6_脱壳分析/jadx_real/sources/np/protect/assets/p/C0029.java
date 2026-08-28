package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠ۤ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0029 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f53short = {2408, 2372, 2421, 2421, 2409, 2412, 2406, 2404, 2417, 2412, 2410, 2411, 2380, 2411, 2403, 2410};

    /* renamed from: ۟۟۟۠ۥ, reason: not valid java name and contains not printable characters */
    public static String m425() {
        return C0002.m242(m426(), 0, C0052.f91 ^ (-178), 2309);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b3  */
    /* renamed from: ۣ۟ۡۤۥ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m426() {
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D0B6C9B5C1");
        int iM562 = C0053.m562(strDecode2);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D8B6C1B5C7");
            switch (iM562) {
                case 56446:
                    if (C0052.m520() <= 0) {
                        C0001.m192();
                        iM562 = C0043.m455(strDecode2);
                    } else {
                        iM562 = C0002.m230(NPStringFog.decode("B5EFB6FEB5C7"));
                    }
                case 1746693:
                    strDecode = C0043.f73 - (C0001.f1 + (-6664)) > 0 ? NPStringFog.decode("B5D7B6C1B5C3") : NPStringFog.decode("B5D4B6C3");
                    iM562 = C0053.m562(strDecode);
                case 1746694:
                    if (C0043.m456() <= 0) {
                        C0043.f73 = 89;
                        sArr = null;
                        strDecode3 = NPStringFog.decode("B5D8B6C5B5C2");
                        iM562 = C0001.m190(strDecode3);
                    } else {
                        strDecode = NPStringFog.decode("B5D1B6C3B5FE");
                        sArr = null;
                        iM562 = C0053.m562(strDecode);
                    }
                case 1747836:
                    sArr2 = f53short;
                    strDecode3 = NPStringFog.decode("B5D8B6C5B5C2");
                    iM562 = C0001.m190(strDecode3);
                case 1747928:
                    if (C0001.m192() >= 0) {
                        if (C0043.f73 - (C0001.f1 + (-6664)) > 0) {
                        }
                        iM562 = C0053.m562(strDecode);
                    } else if (C0053.m574() >= 0) {
                        C0002.f2 = 38;
                        iM562 = C0002.m230(NPStringFog.decode("B5EFB6FEB5C7"));
                    } else {
                        strDecode = NPStringFog.decode("B5D0B6C4B5C0");
                        iM562 = C0053.m562(strDecode);
                    }
                    break;
                case 1748642:
                case 1749602:
                    iM562 = (C0052.f91 / C0001.f1) + 1755374;
                case 1748702:
                    if (C0002.f2 / (C0002.f2 % 368) <= 0) {
                        C0053.f92 = 93;
                        iM562 = C0001.m190(strDecode3);
                    } else {
                        iM562 = (C0053.f92 + C0053.f92) ^ (-1750074);
                    }
                case 1752643:
                    if (C0052.f91 % (C0053.f92 * 1145) >= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5EFB6C2");
                    } else {
                        strDecode = strDecode2;
                    }
                    iM562 = C0053.m562(strDecode);
                case 1755374:
                    break;
                case 1755495:
                    if (C0052.f91 / (C0043.f73 + 4202) != 0) {
                        C0002.m259();
                        iM562 = C0043.m455(NPStringFog.decode("B5D2B6C1B5C1"));
                        sArr = sArr2;
                    } else {
                        sArr = sArr2;
                        iM562 = C0001.m190(strDecode3);
                    }
            }
            return sArr;
        }
    }
}
