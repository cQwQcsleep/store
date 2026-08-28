package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠۟, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0024 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f48short = {1561, 1544, 1544, 1585, 1558, 1566, 1559};

    /* renamed from: ۟۟۟۠۠, reason: not valid java name and contains not printable characters */
    public static String m415() {
        return C0053.m577(m416(), 0, C0043.f73 ^ (-162), 1656);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* renamed from: ۟ۥۣۢ۠, reason: not valid java name and contains not printable characters */
    public static short[] m416() {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D0B6C9B5C3");
        int iM230 = C0002.m230(strDecode2);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            switch (iM230) {
                case 56574:
                case 1749729:
                    i = C0052.f91 * C0053.f92;
                    i2 = 1615100;
                    iM230 = i + i2;
                case 1746968:
                    break;
                case 1747930:
                    if (C0001.m192() <= 0) {
                        i = C0053.f92 / C0002.f2;
                        i2 = 1748819;
                    } else {
                        i = C0002.f2 % C0053.f92;
                        i2 = 1753491;
                    }
                    iM230 = i + i2;
                case 1748829:
                    sArr2 = f48short;
                    if (C0052.f91 + (C0002.f2 | 422) >= 0) {
                        C0002.f2 = 31;
                        iM230 = C0043.m455(strDecode2);
                    } else {
                        i3 = C0052.f91 + C0043.f73;
                        i4 = -1750785;
                        iM230 = i3 ^ i4;
                    }
                case 1750600:
                    sArr = sArr2;
                    strDecode = NPStringFog.decode("B5EFB6C9B5C0");
                    iM230 = C0043.m455(strDecode);
                case 1752707:
                    i = C0002.f2 % C0053.f92;
                    i2 = 1753491;
                    iM230 = i + i2;
                case 1753415:
                    if (C0052.f91 >= 0) {
                        strDecode = NPStringFog.decode("B5EFB6C9B5C0");
                        iM230 = C0043.m455(strDecode);
                    } else {
                        i = C0002.f2 % C0043.f73;
                        i2 = 1753560;
                        iM230 = i + i2;
                    }
                case 1753446:
                    if (C0043.f73 / (C0043.f73 + 3204) != 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D6B6C1B5C1");
                        iM230 = C0043.m455(strDecode);
                    } else {
                        i = C0052.f91 / C0043.f73;
                        i2 = 56574;
                        iM230 = i + i2;
                    }
                case 1753484:
                    if (C0001.m192() >= 0) {
                        C0043.f73 = 11;
                        iM230 = C0053.m562(NPStringFog.decode("B5D6B6C0B5C6"));
                        sArr = null;
                    } else {
                        sArr = null;
                        strDecode = NPStringFog.decode("B5D6B6C1B5C1");
                        iM230 = C0043.m455(strDecode);
                    }
                case 1754441:
                    i3 = C0052.f91 ^ C0052.f91;
                    i4 = 1747930;
                    iM230 = i3 ^ i4;
            }
            return sArr;
        }
    }
}
