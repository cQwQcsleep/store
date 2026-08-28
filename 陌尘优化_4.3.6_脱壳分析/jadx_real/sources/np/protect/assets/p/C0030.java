package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠ۥ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0030 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f54short = {1252, 1224, 1273, 1273, 1253, 1248, 1258, 1256, 1277, 1248, 1254, 1255};

    /* renamed from: ۟۟۟۠ۦ, reason: not valid java name and contains not printable characters */
    public static String m427() {
        return C0052.m507(m428(), 0, C0043.f73 ^ (-171), 1161);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00cf A[SYNTHETIC] */
    /* renamed from: ۤ۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m428() {
        String strDecode;
        String strDecode2;
        String strDecode3;
        int i;
        int i2;
        String strDecode4 = NPStringFog.decode("B5D0B6C7B5C7");
        int iM562 = C0053.m562(strDecode4);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            switch (iM562) {
                case 1747872:
                    if (C0002.m259() <= 0) {
                        if (C0002.m259() >= 0) {
                            C0053.f92 = 82;
                            strDecode = NPStringFog.decode("B5D1B6FEB5FE");
                            iM562 = C0043.m455(strDecode);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D5B6C9B5C6");
                            iM562 = C0053.m562(strDecode2);
                        }
                    } else if (C0053.f92 * (C0053.f92 % (-4891)) > 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D1B6C1B5FE");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        iM562 = (C0043.f73 % C0052.f91) + 1748614;
                    }
                case 1748609:
                    if ((C0053.f92 ^ (C0053.f92 | 3676)) <= 0) {
                        strDecode3 = NPStringFog.decode("B5D5B6C7B5C5");
                        iM562 = C0002.m230(strDecode3);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D2B6C2B5FE");
                        iM562 = C0053.m562(strDecode2);
                    }
                case 1748672:
                    break;
                case 1749579:
                    if (C0043.f73 / (C0043.f73 | 344) <= 0) {
                        C0043.f73 = 39;
                        strDecode2 = strDecode4;
                        sArr = sArr2;
                        iM562 = C0053.m562(strDecode2);
                    } else {
                        iM562 = C0043.f73 + C0052.f91 + 1749001;
                        sArr = sArr2;
                    }
                case 1749694:
                    int i3 = C0052.f91 % (C0043.f73 % (-5721));
                    String strDecode5 = NPStringFog.decode("B5D2B6C9B5C4");
                    if (i3 >= 0) {
                        iM562 = C0001.m190(strDecode5);
                        sArr = null;
                    } else {
                        strDecode3 = strDecode5;
                        sArr = null;
                        iM562 = C0002.m230(strDecode3);
                    }
                case 1749855:
                    i = C0001.f1 ^ C0052.f91;
                    i2 = -1754023;
                    iM562 = i ^ i2;
                case 1752675:
                    if (C0053.f92 * (C0053.f92 % (-4891)) > 0) {
                    }
                    break;
                case 1752740:
                    sArr2 = f54short;
                    i = C0002.f2 % C0002.f2;
                    i2 = 1749579;
                    iM562 = i ^ i2;
                case 1753700:
                case 1754597:
                    if (C0002.m259() >= 0) {
                        strDecode3 = NPStringFog.decode("B5EFB6C6B5C1");
                        iM562 = C0002.m230(strDecode3);
                    } else {
                        i = C0052.f91 / C0043.f73;
                        i2 = 1748672;
                        iM562 = i ^ i2;
                    }
                case 1754538:
                    iM562 = C0001.m190(C0043.f73 % (C0043.f73 ^ 5842) >= 0 ? NPStringFog.decode("B5D1B6FE") : strDecode4);
            }
            return sArr;
        }
    }
}
