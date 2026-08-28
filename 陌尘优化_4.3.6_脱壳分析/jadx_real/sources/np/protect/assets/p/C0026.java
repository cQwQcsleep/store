package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠ۡ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0026 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f50short = {1987, 2005, 2002, 2002, 1989, 1998, 2004, 2017, 1987, 2004, 1993, 2006, 1993, 2004, 2009, 2036, 1992, 2002, 1989, 1985, 1988};

    /* renamed from: ۟۟۟۠ۢ, reason: not valid java name and contains not printable characters */
    public static String m419() {
        return C0053.m577(m420(), 0, C0052.f91 ^ (-181), 1952);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* renamed from: ۥۣۡۨ, reason: contains not printable characters */
    public static short[] m420() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int i3;
        int i4;
        int iM503 = C0052.m503(NPStringFog.decode("B5D1B6C5B5C9"));
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                switch (iM503) {
                    case 1748645:
                    case 1751712:
                        if (C0052.f91 / (C0043.f73 + 8629) != 0) {
                            C0052.m520();
                            strDecode = NPStringFog.decode("B5D0B6C4B5C7");
                            iM503 = C0053.m562(strDecode);
                        } else {
                            i = C0043.f73 % C0052.f91;
                            i2 = 1755437;
                            iM503 = i + i2;
                        }
                    case 1748704:
                        int iM520 = C0052.m520();
                        String strDecode3 = NPStringFog.decode("B5D2B6C5B5C2");
                        if (iM520 <= 0) {
                            C0001.f1 = 68;
                            iM503 = C0002.m230(strDecode3);
                        } else {
                            strDecode2 = strDecode3;
                            iM503 = C0002.m230(strDecode2);
                        }
                    case 1748773:
                        if (C0052.m520() <= 0) {
                            i = C0002.f2 - C0001.f1;
                            i2 = 1749135;
                            iM503 = i + i2;
                        } else if (C0052.f91 % (C0002.f2 % 9550) >= 0) {
                            C0043.m456();
                            strDecode = NPStringFog.decode("B5D1B6C3B5C0");
                            iM503 = C0053.m562(strDecode);
                        } else {
                            i3 = C0002.f2 * C0002.f2;
                            i4 = 1758415;
                            iM503 = i3 ^ i4;
                        }
                    case 1749729:
                        int i5 = C0053.f92 / (C0002.f2 % (-5516));
                        strDecode2 = NPStringFog.decode("B5D5B6C3B5C6");
                        sArr2 = null;
                        iM503 = C0002.m230(strDecode2);
                    case 1750569:
                        i = C0002.f2 - C0001.f1;
                        i2 = 1749135;
                        iM503 = i + i2;
                    case 1751528:
                        if (C0002.f2 / (C0002.f2 - 1187) != 0) {
                            C0001.m192();
                            iM503 = C0052.m503(NPStringFog.decode("B5D1B6C1B5C9"));
                        } else {
                            i = C0043.f73 | C0002.f2;
                            i2 = 1748776;
                            iM503 = i + i2;
                        }
                    case 1752554:
                        i3 = C0002.f2 % C0043.f73;
                        i4 = -1751788;
                        iM503 = i3 ^ i4;
                    case 1753608:
                        if (C0001.m192() >= 0) {
                            sArr2 = sArr;
                            iM503 = C0001.m190(NPStringFog.decode("B5D6B6C4B5C6"));
                        }
                        break;
                    case 1753695:
                        sArr = f50short;
                        iM503 = C0001.m190(NPStringFog.decode("B5D6B6C4B5C6"));
                    case 1755432:
                        break;
                }
                return sArr2;
            }
            iM503 = (C0052.f91 / C0002.f2) + 1755430;
        }
    }
}
