package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟ۤ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0018 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f42short = {494, 481, 491, 509, 480, 486, 491, 417, 494, 511, 511, 417, 462, 492, 507, 486, 505, 486, 507, 502, 475, 487, 509, 490, 494, 491};

    /* renamed from: ۟۟۟۟ۥ, reason: not valid java name and contains not printable characters */
    public static String m403() {
        return C0002.m242(m404(), 0, C0052.f91 ^ (-188), 399);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d0  */
    /* renamed from: ۦ۟ۡ۟, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m404() {
        int i;
        int i2;
        int i3;
        int i4;
        int iM562 = C0053.m562(NPStringFog.decode("B5D2B6C2B5C1"));
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D6B6C4B5C1");
            switch (iM562) {
                case 56389:
                    sArr2 = f42short;
                    if (C0053.m574() >= 0) {
                        C0001.m192();
                        iM562 = C0002.m230(NPStringFog.decode("B5D5B6C4B5C7"));
                    } else {
                        i = C0002.f2 | C0002.f2;
                        i2 = -1755459;
                        iM562 = i ^ i2;
                    }
                case 1746788:
                    if (C0053.f92 % (C0001.f1 ^ 7277) >= 0) {
                        strDecode = NPStringFog.decode("B5D1B6C2B5C7");
                    }
                    iM562 = C0001.m190(strDecode);
                case 1746876:
                    break;
                case 1747805:
                    if (C0052.m520() <= 0) {
                        iM562 = C0001.m190(NPStringFog.decode("B5D0B6C5B5C0"));
                        sArr = null;
                    } else {
                        strDecode = NPStringFog.decode("B5D6B6FEB5C9");
                        sArr = null;
                        iM562 = C0043.m455(strDecode);
                    }
                case 1747936:
                case 1753538:
                    if (C0053.f92 % (C0002.f2 * (-6998)) >= 0) {
                        C0043.f73 = 24;
                        iM562 = C0052.m503(NPStringFog.decode("B5D2B6C9B5C1"));
                    } else {
                        iM562 = C0043.m455(NPStringFog.decode("B5EFB6C4B5C3"));
                    }
                case 1749695:
                    if (C0002.m259() <= 0) {
                        i = C0052.f91 | C0053.f92;
                        i2 = -56421;
                        iM562 = i ^ i2;
                    } else {
                        if (C0053.f92 % (C0001.f1 ^ 7277) >= 0) {
                        }
                        iM562 = C0001.m190(strDecode);
                    }
                    break;
                case 1752646:
                    if (C0053.f92 % (C0052.f91 * (-326)) >= 0) {
                        C0002.f2 = 2;
                        iM562 = C0001.m190(NPStringFog.decode("B5D2B6C9B5C7"));
                    } else {
                        i3 = C0052.f91 * C0052.f91;
                        i4 = 1723451;
                        iM562 = i3 + i4;
                    }
                case 1753423:
                    if (C0043.f73 >= 0) {
                        C0052.f91 = 71;
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i3 = C0052.f91 | C0001.f1;
                        i4 = 1748065;
                        iM562 = i3 + i4;
                    }
                case 1753601:
                    i = C0002.f2 + C0002.f2;
                    i2 = -1747915;
                    iM562 = i ^ i2;
                case 1755401:
                    if (C0001.f1 <= 0) {
                        C0001.m192();
                        iM562 = C0043.m455(NPStringFog.decode("B5D8B6C0B5C3"));
                    } else {
                        iM562 = (C0053.f92 + C0001.f1) ^ (-1746551);
                    }
                    sArr = sArr2;
            }
            return sArr;
        }
    }
}
