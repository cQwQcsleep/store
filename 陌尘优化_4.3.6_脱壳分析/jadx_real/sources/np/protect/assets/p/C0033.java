package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠ۨ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0033 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f57short = {846, 834, 840, 838, 866, 851, 851, 847, 842, 832, 834, 855, 842, 844, 845};

    /* renamed from: ۟۟۟ۡ, reason: not valid java name and contains not printable characters */
    public static String m433() {
        return C0053.m577(m434(), 0, C0002.f2 ^ (-69), 803);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* renamed from: ۟ۥۧۥۨ, reason: not valid java name and contains not printable characters */
    public static short[] m434() {
        int i;
        int i2;
        String strDecode;
        int i3;
        int i4;
        int iM230 = C0002.m230(NPStringFog.decode("B5D1B6FEB5FE"));
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D5B6C4");
            switch (iM230) {
                case 56480:
                    iM230 = C0043.f73 % (C0002.f2 ^ 1672) >= 0 ? C0043.m455("ۡ۟ۥ") : (C0002.f2 - C0043.f73) ^ 1755511;
                    sArr = null;
                case 1747716:
                    sArr2 = f57short;
                    if (C0001.m192() >= 0) {
                        iM230 = C0053.m562(strDecode2);
                    } else {
                        i = C0001.f1 - C0043.f73;
                        i2 = 1754104;
                        iM230 = i + i2;
                    }
                case 1747904:
                    if (C0052.f91 * (C0001.f1 / 8849) != 0) {
                        C0043.f73 = 35;
                        strDecode = NPStringFog.decode("B5D6B6FEB5C7");
                        iM230 = C0052.m503(strDecode);
                    } else {
                        i3 = C0052.f91 | C0052.f91;
                        i4 = -1748513;
                        iM230 = i3 ^ i4;
                    }
                case 1748609:
                    if (C0001.m192() <= 0) {
                        strDecode2 = NPStringFog.decode("B5D0B6C0B5C4");
                        iM230 = C0053.m562(strDecode2);
                    } else {
                        i3 = C0001.f1 ^ C0002.f2;
                        i4 = -1752272;
                        iM230 = i3 ^ i4;
                    }
                case 1748615:
                    break;
                case 1752551:
                    if (C0053.f92 % (C0053.f92 % 5666) != 0) {
                        C0002.m259();
                        strDecode = NPStringFog.decode("B5D8B6C6B5C4");
                        iM230 = C0052.m503(strDecode);
                    } else {
                        iM230 = C0002.m230(strDecode2);
                    }
                case 1752676:
                case 1753600:
                    i = C0001.f1 + C0002.f2;
                    i2 = 1748336;
                    iM230 = i + i2;
                case 1754626:
                    sArr = sArr2;
                    strDecode2 = "ۡ۟ۥ";
                    iM230 = C0002.m230(strDecode2);
                case 1755436:
                    if ((C0002.f2 ^ (C0053.f92 / 4239)) >= 0) {
                        C0002.f2 = 52;
                        strDecode2 = NPStringFog.decode("B5D0B6C6B5C6");
                        iM230 = C0002.m230(strDecode2);
                    } else {
                        iM230 = C0001.m190(NPStringFog.decode("B5D6B6C4B5FE"));
                    }
                case 1755590:
                    i3 = C0001.f1 ^ C0002.f2;
                    i4 = -1752272;
                    iM230 = i3 ^ i4;
            }
            return sArr;
        }
    }
}
