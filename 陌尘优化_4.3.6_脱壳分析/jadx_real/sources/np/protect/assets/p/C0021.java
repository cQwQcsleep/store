package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟ۧ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0021 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f45short = {412, 403, 409, 399, 402, 404, 409, 467, 412, 397, 397, 467, 433, 402, 412, 409, 408, 409, 444, 397, 406};

    /* renamed from: ۟۟۟۟ۨ, reason: not valid java name and contains not printable characters */
    public static String m409() {
        return C0053.m577(m410(), 0, C0002.f2 ^ (-95), 509);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00fc A[SYNTHETIC] */
    /* renamed from: ۣ۟ۧ۠ۥ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m410() {
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5EFB6C0");
        int iM562 = C0053.m562(strDecode3);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5EFB6C2B5C6");
            switch (iM562) {
                case 56290:
                    iM562 = C0053.m574() <= 0 ? C0002.f2 / (C0001.f1 + 1603) != 0 ? C0053.m562(NPStringFog.decode("B5D4B6C0B5C3")) : C0043.m455(NPStringFog.decode("B5D4B6C9B5C6")) : C0043.f73 < 0 ? C0001.m190(NPStringFog.decode("B5D5B6C3B5C0")) : C0002.m230(strDecode4);
                case 56481:
                    break;
                case 1746819:
                    if (C0002.f2 >= 0) {
                        C0043.f73 = 7;
                        strDecode = NPStringFog.decode("B5D8B6C5B5C5");
                    } else {
                        strDecode = NPStringFog.decode("B5D3B6C1B5FE");
                    }
                    iM562 = C0002.m230(strDecode);
                case 1750562:
                    if ((C0001.f1 ^ (C0052.f91 | (-3288))) >= 0) {
                        C0053.m574();
                        iM562 = C0001.m190(strDecode3);
                    } else {
                        iM562 = C0052.f91 + C0052.f91 + 1751881;
                    }
                    sArr = null;
                case 1750687:
                    if (C0043.f73 >= 0) {
                        C0002.f2 = 39;
                        strDecode4 = NPStringFog.decode("B5D7B6C2B5C0");
                    } else {
                        strDecode4 = strDecode3;
                    }
                case 1751493:
                    break;
                case 1751557:
                    if (C0001.f1 <= 0) {
                        C0002.f2 = 11;
                        iM562 = C0043.m455(strDecode4);
                    } else {
                        iM562 = (C0052.f91 * C0001.f1) + 1810963;
                    }
                case 1751655:
                    if (C0043.f73 * (C0002.f2 % (-2015)) <= 0) {
                        C0001.m192();
                        sArr = sArr2;
                        strDecode = NPStringFog.decode("B5D3B6C1B5FE");
                        iM562 = C0002.m230(strDecode);
                    } else {
                        iM562 = (C0053.f92 % C0043.f73) ^ (-56369);
                        sArr = sArr2;
                    }
                case 1751779:
                    sArr2 = f45short;
                    if ((C0053.f92 ^ (C0043.f73 | (-8821))) <= 0) {
                        C0043.m456();
                    } else {
                        strDecode2 = NPStringFog.decode("B5D4B6C5B5C6");
                        iM562 = C0052.m503(strDecode2);
                    }
                case 1753453:
                case 1755496:
                    if ((C0001.f1 | (C0001.f1 - 8365)) >= 0) {
                        strDecode2 = NPStringFog.decode("B5D7B6C4B5C4");
                        iM562 = C0052.m503(strDecode2);
                    } else {
                        strDecode4 = NPStringFog.decode("B5D5B6C7");
                    }
            }
            return sArr;
        }
    }
}
