package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;

/* renamed from: np.protect.assets.p.۟۟۟۠ۧ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0032 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f56short = {1581, 1545, 1582, 1577, 1588, 1577, 1569, 1580, 1537, 1584, 1584, 1580, 1577, 1571, 1569, 1588, 1577, 1583, 1582};

    /* renamed from: ۟۟۟۠ۨ, reason: not valid java name and contains not printable characters */
    public static String m431() {
        return C0002.m242(m432(), 0, C0001.f1 ^ 368, 1600);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:54:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0087 A[SYNTHETIC] */
    /* renamed from: ۣۧۢۢ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m432() {
        String strDecode;
        int i;
        int i2;
        int iM503 = C0052.m503(NPStringFog.decode("B5D0B6FEB5C2"));
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D3B6C2B5C4");
                switch (iM503) {
                    case 56418:
                        strDecode = NPStringFog.decode("B5D2B6C1B5C9");
                        iM503 = C0002.m230(strDecode);
                    case 56573:
                        if (C0002.m259() >= 0) {
                            C0001.m192();
                            strDecode = NPStringFog.decode("B5D2B6C0B5C5");
                            iM503 = C0002.m230(strDecode);
                        } else {
                            i = C0052.f91 - C0052.f91;
                            i2 = 1747652;
                            iM503 = i ^ i2;
                        }
                    case 1747652:
                        if (C0001.m192() > 0) {
                            if (C0001.m192() < 0) {
                                strDecode2 = NPStringFog.decode("B5D1B6C9B5C7");
                            } else {
                                iM503 = (C0002.f2 * C0001.f1) + 1777703;
                            }
                        }
                        iM503 = C0002.m230(strDecode2);
                    case 1749610:
                    case 1752580:
                        if (C0002.m259() >= 0) {
                            iM503 = C0001.m190(NPStringFog.decode("B5D1B6C3B5C5"));
                        } else {
                            i = C0001.f1 + C0002.f2;
                            i2 = 1752287;
                            iM503 = i ^ i2;
                        }
                    case 1749633:
                        break;
                    case 1750661:
                        sArr = f56short;
                        strDecode = NPStringFog.decode("B5D2B6C0B5C1");
                        iM503 = C0002.m230(strDecode);
                    case 1750723:
                        if (C0052.m520() <= 0) {
                            iM503 = C0001.m190(strDecode2);
                        } else {
                            i = C0052.f91 | C0052.f91;
                            i2 = -1755491;
                            iM503 = i ^ i2;
                        }
                    case 1752520:
                        break;
                    case 1752610:
                        if (C0001.m192() < 0) {
                        }
                        break;
                    case 1755587:
                        if (C0052.f91 >= 0) {
                            C0052.m520();
                        }
                        iM503 = C0002.m230(NPStringFog.decode("B5D3B6C4"));
                        sArr2 = null;
                }
                return sArr2;
            }
            int i3 = C0001.f1;
            iM503 = C0001.m190(NPStringFog.decode("B5D5B6C0B5C5"));
        }
    }
}
