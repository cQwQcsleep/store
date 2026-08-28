package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۠ۦ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0031 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f55short = {757, 730, 759, 749, 758, 764, 729, 744, 744, 756, 753, 763, 761, 748, 753, 759, 758};

    /* renamed from: ۟۟۟۠ۧ, reason: not valid java name and contains not printable characters */
    public static String m429() {
        return C0002.m242(m430(), 0, C0001.f1 ^ 370, 664);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* renamed from: ۟ۥۣۡۧ, reason: not valid java name and contains not printable characters */
    public static short[] m430() {
        int i;
        int i2;
        String strDecode;
        int iM190 = C0001.m190(NPStringFog.decode("B5D5B6C2B5FE"));
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D0B6FEB5C9");
            switch (iM190) {
                case 56539:
                case 1752648:
                    if (C0043.f73 >= 0) {
                        C0001.f1 = 2;
                        iM190 = C0052.m503(NPStringFog.decode("B5D2B6FEB5C6"));
                    } else {
                        iM190 = C0002.m230("ۣ۠ۢ");
                    }
                case 1747657:
                    sArr2 = f55short;
                    iM190 = C0053.m562(NPStringFog.decode("B5D1B6FEB5C4"));
                case 1747745:
                    break;
                case 1748615:
                    if (C0043.f73 >= 0) {
                        sArr = sArr2;
                        strDecode2 = NPStringFog.decode("B5D8B6C0B5C3");
                        iM190 = C0052.m503(strDecode2);
                    } else {
                        iM190 = (C0053.f92 - C0052.f91) ^ (-1747371);
                        sArr = sArr2;
                    }
                case 1749725:
                    if (C0002.m259() >= 0) {
                        C0002.f2 = 30;
                        iM190 = C0052.m503(strDecode2);
                    } else {
                        i = C0001.f1 ^ C0001.f1;
                        i2 = 56539;
                        iM190 = i + i2;
                    }
                case 1752577:
                    if (C0002.m259() >= 0) {
                        strDecode = NPStringFog.decode("B5D7B6C3B5C5");
                        iM190 = C0001.m190(strDecode);
                    } else if (C0002.m259() >= 0) {
                        C0052.f91 = 42;
                        iM190 = C0052.m503("ۣ۠ۢ");
                    } else {
                        iM190 = C0052.m503(strDecode2);
                    }
                case 1754473:
                    if (C0002.m259() >= 0) {
                        C0002.f2 = 29;
                        iM190 = C0002.m230(NPStringFog.decode("B5D8B6C1B5FE"));
                    } else {
                        strDecode2 = NPStringFog.decode("B5D8B6C0B5C3");
                        iM190 = C0052.m503(strDecode2);
                    }
                case 1754658:
                    strDecode = NPStringFog.decode("B5D7B6C3B5C5");
                    iM190 = C0001.m190(strDecode);
                case 1755367:
                    if (C0001.f1 / (C0043.f73 | (-5372)) >= 0) {
                        strDecode = NPStringFog.decode("B5D5B6C5B5C5");
                        iM190 = C0001.m190(strDecode);
                    } else {
                        i = C0001.f1 * C0002.f2;
                        i2 = 1779557;
                        iM190 = i + i2;
                    }
                case 1755401:
                    if ((C0052.f91 | (C0053.f92 / 8640)) >= 0) {
                        C0002.m259();
                        sArr = null;
                        iM190 = C0053.m562(NPStringFog.decode("B5D1B6FEB5C4"));
                    } else {
                        iM190 = (C0001.f1 - C0002.f2) ^ 1749874;
                        sArr = null;
                    }
            }
            return sArr;
        }
    }
}
