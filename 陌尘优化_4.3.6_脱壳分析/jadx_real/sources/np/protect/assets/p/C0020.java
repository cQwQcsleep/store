package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟ۦ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0020 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f44short = {2869, 2874, 2864, 2854, 2875, 2877, 2864, 2938, 2869, 2852, 2852, 2938, 2845, 2874, 2855, 2848, 2854, 2849, 2873, 2865, 2874, 2848, 2869, 2848, 2877, 2875, 2874};

    /* renamed from: ۟۟۟۟ۧ, reason: not valid java name and contains not printable characters */
    public static String m407() {
        return C0002.m242(m408(), 0, C0002.f2 ^ (-81), 2900);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0092 A[SYNTHETIC] */
    /* renamed from: ۣ۠ۧۨ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m408() {
        String strDecode;
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode2;
        int iM230 = C0002.m230(NPStringFog.decode("B5D2B6C4B5C3"));
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                switch (iM230) {
                    case 56356:
                        if (C0053.m574() >= 0) {
                            C0052.m520();
                            strDecode = NPStringFog.decode("B5EFB6C0B5FE");
                            iM230 = C0001.m190(strDecode);
                        } else {
                            i = C0002.f2 ^ C0001.f1;
                            i2 = 1751084;
                            iM230 = i + i2;
                        }
                    case 1746749:
                        if (C0053.f92 >= 0) {
                            C0043.m456();
                            strDecode2 = NPStringFog.decode("B5D0B6C1B5C1");
                            iM230 = C0043.m455(strDecode2);
                        } else {
                            i3 = C0053.f92 + C0002.f2;
                            i4 = -1749383;
                            iM230 = i3 ^ i4;
                        }
                    case 1746755:
                        if ((C0002.f2 | (C0002.f2 + 652)) < 0) {
                            C0001.m192();
                            strDecode = NPStringFog.decode("B5D5B6FEB5FE");
                            iM230 = C0001.m190(strDecode);
                        } else {
                            i3 = C0001.f1 ^ C0043.f73;
                            i4 = -1753986;
                            iM230 = i3 ^ i4;
                        }
                    case 1747806:
                        if (C0002.f2 + C0052.f91 + 6301 <= 0) {
                            break;
                        }
                        strDecode2 = NPStringFog.decode("B5D4B6C6B5C1");
                        sArr2 = sArr;
                        iM230 = C0043.m455(strDecode2);
                        break;
                    case 1747932:
                    case 1750787:
                        if (C0001.m192() >= 0) {
                            C0002.m259();
                            iM230 = C0053.m562(NPStringFog.decode("B5D0B6C5B5C6"));
                        } else {
                            i = C0043.f73 + C0052.f91;
                            i2 = 1752070;
                            iM230 = i + i2;
                        }
                    case 1749576:
                        sArr = f44short;
                        strDecode2 = NPStringFog.decode("B5D0B6C5B5C3");
                        iM230 = C0043.m455(strDecode2);
                    case 1749759:
                        if (C0052.m520() > 0) {
                            i = C0043.f73 * C0002.f2;
                            i2 = 1736884;
                            iM230 = i + i2;
                        } else if ((C0002.f2 | (C0002.f2 + 652)) < 0) {
                        }
                        break;
                    case 1751741:
                        break;
                    case 1753668:
                        i = C0052.f91 ^ C0053.f92;
                        i2 = 1753471;
                        iM230 = i + i2;
                    case 1754379:
                        if (C0001.m192() >= 0) {
                            iM230 = C0052.m503(NPStringFog.decode("B5D3B6C6B5C6"));
                            sArr2 = null;
                        } else {
                            strDecode = NPStringFog.decode("B5D1B6C4");
                            sArr2 = null;
                            iM230 = C0001.m190(strDecode);
                        }
                }
                return sArr2;
            }
            iM230 = C0001.m190(NPStringFog.decode("B5D6B6C6B5C4"));
        }
    }
}
