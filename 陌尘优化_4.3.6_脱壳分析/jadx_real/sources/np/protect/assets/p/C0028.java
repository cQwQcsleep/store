package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.ۣ۟۟۟۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0028 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f52short = {3326, 3282, 3327, 3327, 3282, 3299, 3299, 3327, 3322, 3312, 3314, 3303, 3322, 3324, 3325, 3296};

    /* renamed from: ۟۟۟۠ۤ, reason: not valid java name and contains not printable characters */
    public static String m423() {
        return C0052.m507(m424(), 0, C0001.f1 ^ 371, 3219);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* renamed from: ۣۧ۠ۤ, reason: not valid java name and contains not printable characters */
    public static short[] m424() {
        int i;
        int i2;
        int iM455 = C0043.m455(NPStringFog.decode("B5D7B6C0"));
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D8B6C6");
            switch (iM455) {
                case 56416:
                    sArr2 = sArr;
                    iM455 = C0001.m190(strDecode);
                case 56538:
                    if (C0053.m574() >= 0) {
                        strDecode = NPStringFog.decode("B5EFB6FEB5C6");
                        iM455 = C0001.m190(strDecode);
                    } else if (C0053.f92 >= 0) {
                        iM455 = C0043.m455(strDecode);
                    } else {
                        i = C0001.f1 % C0053.f92;
                        i2 = 1755076;
                        iM455 = i + i2;
                    }
                case 56575:
                    break;
                case 1746695:
                    i = C0002.f2 + C0052.f91;
                    i2 = 1748977;
                    iM455 = i + i2;
                case 1746787:
                    if (C0053.m574() >= 0) {
                        C0001.m192();
                        iM455 = C0043.m455(NPStringFog.decode("B5D4B6FEB5C6"));
                    } else {
                        i = C0002.f2 % C0002.f2;
                        i2 = 1752672;
                        iM455 = i + i2;
                    }
                case 1748739:
                    int i3 = C0052.f91;
                    strDecode = NPStringFog.decode("B5EFB6C3B5C7");
                    if (i3 >= 0) {
                        C0052.m520();
                        iM455 = C0053.m562(strDecode);
                        sArr2 = null;
                    } else {
                        sArr2 = null;
                        iM455 = C0001.m190(strDecode);
                    }
                case 1751500:
                    i = C0053.f92 - C0043.f73;
                    i2 = 57185;
                    iM455 = i + i2;
                case 1752453:
                case 1752672:
                    if (C0001.f1 <= 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D4B6C9");
                    }
                    iM455 = C0001.m190(strDecode);
                case 1754438:
                    strDecode = NPStringFog.decode("B5EFB6FEB5C6");
                    iM455 = C0001.m190(strDecode);
                case 1755431:
                    sArr = f52short;
                    C0043.m456();
                    strDecode = NPStringFog.decode("B5D3B6C2");
                    iM455 = C0001.m190(strDecode);
            }
            return sArr2;
        }
    }
}
