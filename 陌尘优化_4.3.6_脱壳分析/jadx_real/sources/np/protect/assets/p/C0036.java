package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟ۡ۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0036 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f58short = {900, 901, 919, 713, 712, 730, 692, 734, 728, 729, 692, 715, 720, 728, 712, 682, 715, 762, 767, 767, 754, 757, 764, 1136, 1137, 1123, 1032, 1053};

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0027. Please report as an issue. */
    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    public static String m439(String str, String str2) throws InvalidKeyException {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5EFB6C7B5C0");
        int iM503 = C0052.m503(strDecode3);
        Cipher cipherM252 = null;
        byte[] bArrM247 = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = null;
        PrivateKey privateKey = null;
        byte[] bArrM571 = null;
        byte[] bArrM5712 = null;
        byte[] bArrM257 = null;
        int i5 = 0;
        int i6 = 0;
        int length = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D4B6C4B5FE");
            String strDecode5 = "ۣ۠۟";
            String strDecode6 = NPStringFog.decode("B5D2B6C6B5FE");
            switch (iM503) {
                case 56386:
                    bArrM5712 = C0053.m571(cipherM252, bArrM247, i5, i6);
                    if (C0043.f73 >= 0) {
                        iM503 = C0002.m230(NPStringFog.decode("B5D6B6FEB5FE"));
                    } else {
                        i = C0053.f92 + C0043.f73;
                        i2 = -1751517;
                        iM503 = i ^ i2;
                    }
                case 1746722:
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    if (C0052.f91 + (C0043.f73 ^ (-8145)) <= 0) {
                        iM503 = C0052.m503(strDecode6);
                    } else {
                        strDecode = NPStringFog.decode("B5D6B6C0B5C3");
                        iM503 = C0001.m190(strDecode);
                    }
                case 1746788:
                    i7 = ((length - 7) - i5) + 7;
                    if (i7 <= 0) {
                        iM503 = C0052.m503(strDecode5);
                    } else if ((C0001.f1 ^ (C0053.f92 ^ 5984)) >= 0) {
                        C0052.f91 = 30;
                        strDecode4 = NPStringFog.decode("B5D6B6C2B5C2");
                        iM503 = C0052.m503(strDecode4);
                    } else {
                        i3 = C0053.f92 * C0043.f73;
                        i4 = 1617484;
                        iM503 = i3 + i4;
                    }
                case 1746847:
                case 1755493:
                    strDecode5 = NPStringFog.decode("B5EFB6C3B5C6");
                    iM503 = C0052.m503(strDecode5);
                case 1746848:
                case 1752523:
                    if (C0002.m259() >= 0) {
                        strDecode = NPStringFog.decode("B5D8B6C4B5C9");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        i = C0001.f1 / C0043.f73;
                        i2 = -1751680;
                        iM503 = i ^ i2;
                    }
                case 1746906:
                    bArrM247 = C0002.m247(str);
                    pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(C0002.m247(str2));
                    if (C0002.f2 - (C0001.f1 / (-7998)) >= 0) {
                        C0053.m574();
                        strDecode2 = NPStringFog.decode("B5D8B6C5B5C2");
                        iM503 = C0053.m562(strDecode2);
                    } else {
                        i3 = C0052.f91 * C0053.f92;
                        i4 = 1615970;
                        iM503 = i3 + i4;
                    }
                case 1747652:
                    bArrM257 = C0002.m257(byteArrayOutputStream);
                    C0053.m566(byteArrayOutputStream);
                    if (C0052.f91 >= 0) {
                        C0001.f1 = 14;
                        strDecode2 = NPStringFog.decode("B5EFB6C5B5C4");
                        iM503 = C0053.m562(strDecode2);
                    } else {
                        iM503 = (C0002.f2 ^ C0052.f91) ^ 1754435;
                    }
                case 1747838:
                    PrivateKey privateKeyM184 = C0001.m184(C0052.m518(C0053.m577(m440(), 0, C0002.f2 ^ (-73), 982)), pKCS8EncodedKeySpec);
                    cipherM252 = C0002.m252(C0002.m242(m440(), 3, C0052.f91 ^ (-182), 667));
                    if (C0002.f2 * (C0002.f2 ^ 7966) <= 0) {
                        C0043.f73 = 32;
                        iM503 = C0053.m562(strDecode3);
                    } else {
                        iM503 = (C0043.f73 - C0001.f1) + 1750310;
                    }
                    privateKey = privateKeyM184;
                case 1749788:
                    C0053.m552(cipherM252, C0043.f73 ^ (-165), privateKey);
                    length = bArrM247.length;
                    i = C0001.f1 * C0052.f91;
                    i2 = -1722248;
                    iM503 = i ^ i2;
                case 1749818:
                    bArrM571 = C0053.m571(cipherM252, bArrM247, i5, i7);
                    if (C0002.f2 - (C0043.f73 | (-5885)) <= 0) {
                        C0001.f1 = 91;
                        strDecode = NPStringFog.decode("B5D8B6C5B5C0");
                    } else {
                        strDecode = NPStringFog.decode("B5D3B6C2B5C0");
                    }
                    iM503 = C0001.m190(strDecode);
                case 1749826:
                    strDecode5 = strDecode6;
                    iM503 = C0052.m503(strDecode5);
                case 1750657:
                    if (C0052.f91 - (C0052.f91 / (-1507)) >= 0) {
                        C0053.f92 = 87;
                        iM503 = C0002.m230(NPStringFog.decode("B5D2B6C6B5C6"));
                        bArrM5712 = bArrM571;
                    } else {
                        bArrM5712 = bArrM571;
                        iM503 = C0052.m503(strDecode4);
                    }
                case 1751560:
                    if (C0001.f1 <= 0) {
                        C0001.f1 = 85;
                        strDecode = NPStringFog.decode("B5D2B6C5");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        i3 = C0001.f1 ^ C0053.f92;
                        i4 = 1747439;
                        iM503 = i3 + i4;
                    }
                case 1751678:
                    C0053.m540(byteArrayOutputStream, bArrM5712, 0, bArrM5712.length);
                    i9 = i8 + (C0043.f73 ^ (-168));
                    strDecode4 = NPStringFog.decode("B5D6B6C2B5C2");
                    iM503 = C0052.m503(strDecode4);
                case 1753414:
                    i = C0052.f91 ^ C0043.f73;
                    i2 = 1746840;
                    iM503 = i ^ i2;
                case 1753415:
                    if ((C0043.f73 | (C0002.f2 / 2010)) >= 0) {
                        C0053.m574();
                        strDecode5 = NPStringFog.decode("B5D4B6C0B5C6");
                        iM503 = C0052.m503(strDecode5);
                    } else {
                        i = C0052.f91 | C0043.f73;
                        i2 = -1746811;
                        iM503 = i ^ i2;
                    }
                case 1753422:
                    i6 = C0053.f92 ^ (-942);
                    if (i7 > i6) {
                        strDecode = NPStringFog.decode("B5D2B6C5");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        strDecode5 = strDecode6;
                        iM503 = C0052.m503(strDecode5);
                    }
                case 1753479:
                    if (C0052.f91 - (C0053.f92 ^ 1225) <= 0) {
                        C0002.f2 = 36;
                        iM503 = C0043.m455(strDecode4);
                    } else {
                        iM503 = (C0001.f1 - C0043.f73) + 1753082;
                    }
                    i8 = 0;
                case 1753480:
                    iM503 = C0052.m503(strDecode5);
                case 1753542:
                    i5 = i9 * (C0053.f92 ^ (-942));
                    if (C0053.m574() >= 0) {
                        C0043.m456();
                        iM503 = C0043.m455(NPStringFog.decode("B5D6B6C4B5C2"));
                    } else {
                        strDecode2 = NPStringFog.decode("B5D8B6C5B5C2");
                        iM503 = C0053.m562(strDecode2);
                    }
                case 1753604:
                    if (C0053.f92 >= 0) {
                        C0052.f91 = 33;
                        i5 = 0;
                        strDecode = NPStringFog.decode("B5D6B6C0B5C3");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        iM503 = (C0002.f2 | C0043.f73) + 1746791;
                        i5 = 0;
                    }
                case 1754537:
                    break;
                case 1755495:
                    if (C0001.f1 % (C0002.f2 ^ 7879) <= 0) {
                        C0001.f1 = 61;
                        iM503 = C0053.m562("ۣ۠۟");
                        i8 = i9;
                    } else {
                        i8 = i9;
                        iM503 = C0002.m230(NPStringFog.decode("B5D6B6FEB5FE"));
                    }
            }
            return C0001.m209(new String(bArrM257), C0002.m242(m440(), 23, C0043.f73 ^ (-164), 1061));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x001d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0093 A[SYNTHETIC] */
    /* renamed from: ۤۤ۠ۢ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m440() {
        int i;
        int i2;
        String strDecode;
        int iM190 = C0001.m190(NPStringFog.decode("B5D7B6C2B5C6"));
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D7B6C2B5C2");
                String strDecode3 = NPStringFog.decode("B5D4B6C4");
                String strDecode4 = "ۧۥ۟";
                switch (iM190) {
                    case 56449:
                        if (C0052.f91 >= 0) {
                            C0002.f2 = 22;
                            iM190 = C0053.m562(strDecode2);
                        } else {
                            strDecode3 = NPStringFog.decode("B5D5B6C6B5C6");
                            iM190 = C0052.m503(strDecode3);
                        }
                    case 1749789:
                        sArr = f58short;
                        if ((C0053.f92 | (C0002.f2 + 4072)) >= 0) {
                            C0043.f73 = 64;
                            strDecode4 = NPStringFog.decode("B5D8B6C4B5C6");
                            iM190 = C0053.m562(strDecode4);
                        } else {
                            i = C0052.f91 | C0052.f91;
                            i2 = 1750015;
                            iM190 = i + i2;
                        }
                    case 1749853:
                        if (C0043.f73 - (C0052.f91 | (-4853)) >= 0) {
                            break;
                        }
                        sArr2 = sArr;
                        iM190 = C0053.m562(strDecode4);
                        break;
                    case 1751498:
                        if (C0052.f91 - (C0001.f1 * 4234) < 0) {
                            C0053.f92 = 76;
                            strDecode = NPStringFog.decode("B5EFB6FEB5C6");
                            iM190 = C0053.m562(strDecode);
                        } else {
                            i = C0001.f1 / C0053.f92;
                            i2 = 1755530;
                            iM190 = i + i2;
                        }
                    case 1752709:
                    case 1755372:
                        if (C0053.f92 / (C0043.f73 + 4468) != 0) {
                            strDecode2 = NPStringFog.decode("B5D0B6C5");
                            iM190 = C0043.m455(strDecode2);
                        } else {
                            strDecode3 = "ۧۥ۟";
                            iM190 = C0001.m190(strDecode3);
                        }
                    case 1754503:
                        sArr2 = null;
                        iM190 = C0001.m190(strDecode3);
                    case 1754507:
                        if (C0052.m520() > 0) {
                            if (C0052.f91 + (C0053.f92 - 9965) >= 0) {
                                C0001.m192();
                                iM190 = C0052.m503(strDecode3);
                            } else {
                                iM190 = (C0002.f2 | C0001.f1) ^ (-1749782);
                            }
                        } else if (C0052.f91 - (C0001.f1 * 4234) < 0) {
                        }
                        break;
                    case 1754561:
                        break;
                    case 1754631:
                        if (C0053.m574() >= 0) {
                            C0002.f2 = 97;
                            strDecode3 = NPStringFog.decode("B5D3B6C5B5C1");
                            iM190 = C0001.m190(strDecode3);
                        } else {
                            i = C0001.f1 % C0052.f91;
                            i2 = 1754476;
                            iM190 = i + i2;
                        }
                    case 1755530:
                        if (C0052.f91 % (C0001.f1 | 5345) >= 0) {
                            C0043.f73 = 15;
                            strDecode = NPStringFog.decode("B5D8B6C1B5C5");
                            iM190 = C0053.m562(strDecode);
                        } else {
                            iM190 = C0043.m455(strDecode2);
                        }
                }
                return sArr2;
            }
            C0052.f91 = 22;
            iM190 = C0002.m230("ۧۥ۟");
        }
    }
}
