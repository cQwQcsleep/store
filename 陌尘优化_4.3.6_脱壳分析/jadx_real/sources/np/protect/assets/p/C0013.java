package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.ThisObject;
import com.swift.sandhook.lib.C0001;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.io.IOException;
import java.io.InputStream;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0013 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f34short = {444, 446, 431, 393, 446, 424, 436, 430, 425, 440, 446, 410, 424, 392, 431, 425, 446, 442, 438, 2861, 492, 494, 511, 473, 494, 504, 484, 510, 505, 488, 494, 458, 504, 472, 511, 505, 494, 490, 486, 1613, 1631, 1631, 1609, 1624, 1631, 1539};

    /* renamed from: ۟۟۟۠ۨ, reason: not valid java name and contains not printable characters */
    private static a f35;

    @HookClass(ClassLoader.class)
    /* renamed from: np.protect.assets.p.۟۟۟۟۠$۟, reason: contains not printable characters */
    public class C0014 {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f37short = {1699, 1713, 1713, 1703, 1718, 1713, 1773};

        /* renamed from: ۟۟۟ۡ, reason: not valid java name and contains not printable characters */
        @HookMethodBackup("getResourceAsStream")
        @MethodParams({String.class})
        static HookWrapper.HookEntity f38;

        /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000e. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public C0014() throws NumberFormatException {
            String strDecode;
            int i;
            int i2;
            int iM562 = C0053.m562(NPStringFog.decode("B5D5B6C1B5C0"));
            float f = 0.0f;
            while (true) {
                switch (iM562) {
                    case 1751556:
                        if ((C0002.f2 ^ (C0001.f1 / (-8508))) < 0) {
                            C0043.f73 = 91;
                            strDecode = NPStringFog.decode("B5D5B6C4B5C6");
                        } else {
                            strDecode = NPStringFog.decode("B5D8B6C9B5C5");
                        }
                        iM562 = C0052.m503(strDecode);
                    case 1752486:
                        if (C0002.m259() >= 0) {
                            strDecode = NPStringFog.decode("B5D8B6C7B5C6");
                        } else if ((C0002.f2 ^ (C0001.f1 / (-8508))) < 0) {
                        }
                        iM562 = C0052.m503(strDecode);
                        break;
                    case 1752488:
                        System.out.println(f);
                        if ((C0001.f1 ^ (C0043.f73 / 3355)) <= 0) {
                            C0001.m192();
                            strDecode = NPStringFog.decode("B5D8B6C7B5C6");
                            iM562 = C0052.m503(strDecode);
                        } else {
                            i = C0053.f92 / C0002.f2;
                            i2 = 1755610;
                            iM562 = i + i2;
                        }
                    case 1752741:
                        iM562 = C0052.m520() <= 0 ? C0053.m562(NPStringFog.decode("B5EFB6C5B5FE")) : (C0001.f1 | C0053.f92) ^ (-1753003);
                    case 1755561:
                        f = Float.parseFloat(C0043.m453(NPStringFog.decode("5C1B35181D2F540E4A3B1E55310F3126")));
                        i = C0052.f91 % C0001.f1;
                        i2 = 1752650;
                        iM562 = i + i2;
                    case 1755620:
                        break;
                }
                return;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0061 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0056 A[SYNTHETIC] */
        @MethodParams({String.class})
        @HookMethod("getResourceAsStream")
        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static InputStream m398(@ThisObject ClassLoader classLoader, String str) {
            String strDecode;
            String strDecode2 = NPStringFog.decode("B5EFB6C5B5C4");
            int iM230 = C0002.m230(strDecode2);
            while (true) {
                switch (iM230) {
                    case 56513:
                        if (C0001.m192() >= 0) {
                            C0052.f91 = 47;
                            strDecode = NPStringFog.decode("B5D2B6C5");
                        } else {
                            strDecode = strDecode2;
                        }
                        iM230 = C0043.m455(strDecode);
                    case 1746844:
                        return (InputStream) C0053.m542(m400(), classLoader, new Object[]{str});
                    case 1746848:
                        if (C0002.m255(str, C0053.m577(m399(), 0, C0043.f73 ^ (-162), 1730))) {
                            if (C0053.f92 * (C0052.f91 - 9660) <= 0) {
                                C0002.f2 = 98;
                            }
                            iM230 = C0001.m190(NPStringFog.decode("B5D0B6C7B5FE"));
                        } else if (C0043.f73 + C0053.f92 + 5335 > 0) {
                            strDecode = NPStringFog.decode("B5D0B6C0B5C2");
                            iM230 = C0043.m455(strDecode);
                        } else {
                            iM230 = (C0043.f73 / C0043.f73) ^ 1746845;
                        }
                    case 1747865:
                        return C0053.m559(C0002.m234(), C0053.m548(str, C0043.f73 ^ (-162)));
                    case 1751501:
                        if (C0043.f73 + C0053.f92 + 5335 > 0) {
                        }
                        break;
                }
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
        /* renamed from: ۟ۦۣ۠۠, reason: not valid java name and contains not printable characters */
        public static short[] m399() {
            String strDecode;
            int i;
            int i2;
            int i3;
            int i4;
            String strDecode2 = NPStringFog.decode("B5EFB6FEB5C7");
            int iM455 = C0043.m455(strDecode2);
            short[] sArr = null;
            short[] sArr2 = null;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D3B6C2B5C2");
                switch (iM455) {
                    case 56506:
                        if (C0043.f73 >= 0) {
                            strDecode = NPStringFog.decode("B5D2B6C2B5C6");
                            iM455 = C0043.m455(strDecode);
                        } else {
                            strDecode3 = NPStringFog.decode("B5D8B6C2B5C5");
                            iM455 = C0052.m503(strDecode3);
                        }
                    case 1746694:
                        if (C0001.m192() < 0) {
                            if (C0052.f91 - (C0053.f92 % 8809) <= 0) {
                                C0053.m574();
                                strDecode3 = NPStringFog.decode("B5D1B6C5B5C7");
                            }
                            iM455 = C0052.m503(strDecode3);
                        } else {
                            i = C0002.f2 / C0053.f92;
                            i2 = 1748771;
                            iM455 = i + i2;
                        }
                    case 1746814:
                    case 1755465:
                        i = C0053.f92 * C0002.f2;
                        i2 = 1685880;
                        iM455 = i + i2;
                    case 1747744:
                        break;
                    case 1748771:
                        if (C0043.m456() <= 0) {
                            C0001.m192();
                            iM455 = C0052.m503(strDecode2);
                        } else {
                            strDecode = NPStringFog.decode("B5D6B6FEB5C6");
                            iM455 = C0043.m455(strDecode);
                        }
                    case 1749702:
                        if (C0053.m574() >= 0) {
                            C0052.m520();
                            sArr = sArr2;
                            iM455 = C0052.m503(NPStringFog.decode("B5D6B6C1"));
                        } else {
                            iM455 = (C0001.f1 / C0053.f92) ^ 1747744;
                            sArr = sArr2;
                        }
                    case 1750659:
                        sArr2 = f37short;
                        if (C0052.f91 >= 0) {
                            iM455 = C0052.m503(strDecode3);
                        } else {
                            i3 = C0043.f73 % C0001.f1;
                            i4 = -1749601;
                            iM455 = i3 ^ i4;
                        }
                    case 1751587:
                        i3 = C0001.f1 * C0043.f73;
                        i4 = -1720467;
                        iM455 = i3 ^ i4;
                    case 1751715:
                        i = C0002.f2 / C0053.f92;
                        i2 = 1748771;
                        iM455 = i + i2;
                    case 1753422:
                        sArr = null;
                        iM455 = C0052.m503(NPStringFog.decode("B5D6B6C1"));
                }
                return sArr;
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0100 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x00f4 A[SYNTHETIC] */
        /* renamed from: ۣۤۧۡ, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static HookWrapper.HookEntity m400() {
            String strDecode;
            String strDecode2;
            int i;
            int i2;
            int i3;
            int i4;
            String strDecode3;
            String strDecode4;
            int iM562 = C0053.m562(NPStringFog.decode("B5D4B6C1"));
            HookWrapper.HookEntity hookEntity = null;
            while (true) {
                HookWrapper.HookEntity hookEntity2 = hookEntity;
                while (true) {
                    switch (iM562) {
                        case 56444:
                            if (C0053.m574() <= 0) {
                                if (C0001.f1 - (C0001.f1 | (-7952)) <= 0) {
                                    C0043.f73 = 84;
                                    strDecode = NPStringFog.decode("B5D0B6C3B5C1");
                                    iM562 = C0001.m190(strDecode);
                                } else {
                                    i = C0053.f92 ^ C0053.f92;
                                    i2 = 1751497;
                                    iM562 = i + i2;
                                }
                            } else if (C0053.f92 * (C0043.f73 - 6599) > 0) {
                                strDecode = NPStringFog.decode("B5D5B6C9B5C3");
                                iM562 = C0001.m190(strDecode);
                            } else {
                                strDecode2 = NPStringFog.decode("B5D8B6C5B5C4");
                                iM562 = C0053.m562(strDecode2);
                            }
                        case 1747742:
                            if (C0053.f92 * (C0043.f73 - 6599) > 0) {
                            }
                            break;
                        case 1747836:
                            strDecode = NPStringFog.decode("B5D5B6C9B5C6");
                            hookEntity2 = null;
                            iM562 = C0001.m190(strDecode);
                        case 1749694:
                        case 1755340:
                            if (C0043.f73 - (C0043.f73 ^ 3655) <= 0) {
                                C0043.m456();
                                strDecode2 = NPStringFog.decode("B5D8B6C3B5C6");
                                iM562 = C0053.m562(strDecode2);
                            } else {
                                i3 = C0053.f92 * C0053.f92;
                                i4 = 1090247;
                                iM562 = i3 ^ i4;
                            }
                        case 1749787:
                            if (C0052.f91 % (C0002.f2 % (-9684)) >= 0) {
                                C0001.f1 = 97;
                                strDecode = NPStringFog.decode("B5D1B6C9B5C4");
                                iM562 = C0001.m190(strDecode);
                            } else {
                                iM562 = (C0053.f92 * C0052.f91) - 75424;
                            }
                        case 1749856:
                            if ((C0052.f91 | (C0043.f73 * 2256)) >= 0) {
                                C0001.m192();
                                hookEntity2 = hookEntity;
                                strDecode3 = NPStringFog.decode("B5D2B6C9B5C7");
                                iM562 = C0043.m455(strDecode3);
                            }
                            break;
                        case 1751497:
                            hookEntity = f38;
                            if (C0043.f73 >= 0) {
                                C0001.m192();
                                strDecode4 = NPStringFog.decode("B5D0B6C4B5C0");
                                iM562 = C0002.m230(strDecode4);
                            } else {
                                strDecode3 = NPStringFog.decode("B5D2B6C9B5C7");
                                iM562 = C0043.m455(strDecode3);
                            }
                        case 1752707:
                            break;
                        case 1752740:
                            if ((C0052.f91 ^ (C0052.f91 % (-1007))) != 0) {
                                strDecode3 = NPStringFog.decode("B5D5B6C6B5C4");
                                iM562 = C0043.m455(strDecode3);
                            } else {
                                i = C0001.f1 / C0001.f1;
                                i2 = 1749693;
                                iM562 = i + i2;
                            }
                        case 1755497:
                            if (C0043.f73 - (C0001.f1 ^ (-8665)) <= 0) {
                                C0053.m574();
                                strDecode4 = NPStringFog.decode("B5D8B6FEB5C2");
                                iM562 = C0002.m230(strDecode4);
                            } else {
                                i3 = C0002.f2 ^ C0053.f92;
                                i4 = 1746970;
                                iM562 = i3 ^ i4;
                            }
                    }
                    return hookEntity2;
                }
                iM562 = (C0053.f92 % C0002.f2) + 1752761;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C0013() throws NumberFormatException {
        String strDecode;
        int iM230 = C0002.m230(NPStringFog.decode("B5D2B6C6B5C0"));
        long j = 0;
        while (true) {
            switch (iM230) {
                case 1746693:
                    System.out.println(j);
                    iM230 = (C0001.f1 / C0052.f91) ^ (-1751497);
                case 1749633:
                    j = Long.parseLong(C0002.m256(NPStringFog.decode("19002F03203325552238373E035D14221D")));
                    int i = C0053.f92;
                    String strDecode2 = NPStringFog.decode("B5EFB6FEB5C4");
                    if (i >= 0) {
                        iM230 = C0001.m190(strDecode2);
                    } else {
                        strDecode = strDecode2;
                        iM230 = C0001.m190(strDecode);
                    }
                case 1749820:
                    if (C0043.m456() <= 0) {
                        if (C0001.m192() >= 0) {
                            C0053.m574();
                        }
                        strDecode = NPStringFog.decode("B5D2B6C0B5C1");
                    } else if (C0053.f92 < 0) {
                        C0002.m259();
                        strDecode = NPStringFog.decode("B5D2B6C3B5C9");
                    } else {
                        iM230 = C0002.m230(NPStringFog.decode("B5D4B6FEB5C5"));
                    }
                    iM230 = C0001.m190(strDecode);
                case 1751497:
                    break;
                case 1753508:
                    if (C0053.f92 < 0) {
                    }
                    break;
                case 1755495:
                    if (C0043.f73 >= 0) {
                        C0053.f92 = 57;
                        iM230 = C0053.m562(NPStringFog.decode("B5D8B6C3B5FE"));
                    } else {
                        iM230 = (C0053.f92 - C0052.f91) + 1750472;
                    }
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0076 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x006f A[SYNTHETIC] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m388(int i) throws NumberFormatException {
        int i2;
        int i3;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D2B6C7B5C3");
        int iM503 = C0052.m503(strDecode3);
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D3B6C4");
            switch (iM503) {
                case 56418:
                case 1749606:
                case 1752457:
                    i2 = C0001.f1 - C0001.f1;
                    i3 = 1750723;
                    iM503 = i2 + i3;
                case 56477:
                    i2 = C0001.f1 % C0001.f1;
                    i3 = 1755339;
                    iM503 = i2 + i3;
                case 1746970:
                    i2 = C0001.f1 - C0043.f73;
                    i3 = 1751935;
                    iM503 = i2 + i3;
                case 1749790:
                    if (i == 1) {
                        i2 = C0001.f1 % C0001.f1;
                        i3 = 1755339;
                        iM503 = i2 + i3;
                    } else if ((C0043.f73 ^ (C0052.f91 | 4662)) <= 0) {
                        C0043.m456();
                        iM503 = C0043.m455(NPStringFog.decode("B5D4B6C6B5C3"));
                    } else {
                        strDecode4 = NPStringFog.decode("B5D8B6C5B5C9");
                        iM503 = C0043.m455(strDecode4);
                    }
                case 1750723:
                    break;
                case 1751743:
                    m396();
                    if (C0053.f92 - (C0043.f73 * 6540) <= 0) {
                        C0052.f91 = 6;
                        strDecode = NPStringFog.decode("B5D5B6FEB5C2");
                        iM503 = C0002.m230(strDecode);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D8B6C6B5C3");
                        iM503 = C0001.m190(strDecode2);
                    }
                case 1752493:
                    m395();
                    if (C0052.f91 * (C0043.f73 - 7367) <= 0) {
                        C0001.f1 = 87;
                        iM503 = C0053.m562(strDecode4);
                    } else {
                        i2 = C0043.f73 - C0052.f91;
                        i3 = 1746975;
                        iM503 = i2 + i3;
                    }
                case 1754533:
                    if ((C0053.f92 | C0002.f2 | (-3160)) < 0) {
                        strDecode4 = NPStringFog.decode("B5D8B6C0B5C7");
                        iM503 = C0043.m455(strDecode4);
                    } else {
                        i2 = C0052.f91 | C0043.f73;
                        i3 = 1751904;
                        iM503 = i2 + i3;
                    }
                case 1755339:
                    m392();
                    if (C0002.m259() >= 0) {
                        iM503 = C0052.m503(NPStringFog.decode("B5D5B6C3"));
                    } else {
                        i2 = C0053.f92 % C0052.f91;
                        i3 = 1750727;
                        iM503 = i2 + i3;
                    }
                case 1755375:
                    if (C0002.f2 - (C0002.f2 / 9615) >= 0) {
                        C0001.m192();
                        strDecode2 = NPStringFog.decode("B5D2B6C5");
                    } else {
                        strDecode2 = strDecode3;
                    }
                    iM503 = C0001.m190(strDecode2);
                case 1755500:
                    if (i != 2) {
                        i2 = C0002.f2 + C0001.f1;
                        i3 = 1752214;
                    } else if ((C0053.f92 | C0002.f2 | (-3160)) < 0) {
                    }
                    iM503 = i2 + i3;
                    break;
                case 1755587:
                    if (C0001.m192() >= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D7B6C5B5C3");
                        iM503 = C0002.m230(strDecode);
                    } else {
                        iM503 = C0043.m455(strDecode4);
                    }
            }
            return;
        }
    }

    /* renamed from: ۟۟۟۟ۢ, reason: not valid java name and contains not printable characters */
    private static void m389() {
        f35 = C0052.m506(C0001.m189(ClassLoader.class, C0053.m577(m394(), 0, C0002.f2 ^ (-89), 475), new Class[]{String.class}), C0001.m189(C0013.class, C0053.m577(m394(), 19, 1, 2911), new Class[]{C0015.class}), new C0013());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0011. Please report as an issue. */
    /* renamed from: ۣ۟۟۟۟, reason: not valid java name and contains not printable characters */
    private static void m390() {
        int i;
        int i2;
        String strDecode = NPStringFog.decode("B5D8B6C4B5C3");
        int iM230 = C0002.m230(strDecode);
        String strM256 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5EFB6C0B5C9");
            switch (iM230) {
                case 1746758:
                    strM256 = C0002.m256(NPStringFog.decode("04062459"));
                    if (C0053.m574() >= 0) {
                        C0052.f91 = 88;
                        iM230 = C0001.m190(NPStringFog.decode("B5EFB6C6B5C5"));
                    } else {
                        i = C0001.f1 * C0052.f91;
                        i2 = -1714501;
                        iM230 = i ^ i2;
                    }
                case 1746940:
                    break;
                case 1747834:
                    if (C0053.m574() >= 0) {
                        if ((C0001.f1 ^ (C0053.f92 / 5581)) <= 0) {
                            strDecode2 = strDecode;
                        }
                        iM230 = C0053.m562(strDecode2);
                    } else {
                        i = C0002.f2 / C0001.f1;
                        i2 = 1746940;
                        iM230 = i ^ i2;
                    }
                case 1748827:
                    i = C0052.f91 * C0052.f91;
                    i2 = 1748737;
                    iM230 = i ^ i2;
                case 1749601:
                    i = C0002.f2 / C0001.f1;
                    i2 = 1746940;
                    iM230 = i ^ i2;
                case 1755525:
                    C0002.m240(C0001.m189(ClassLoader.class, C0043.m454(m394(), 20, C0002.f2 ^ (-89), 395), new Class[]{String.class}), new AbstractC0039() { // from class: np.protect.assets.p.۟۟۟۟۠.1

                        /* renamed from: short, reason: not valid java name */
                        private static final short[] f36short = {412, 398, 398, 408, 393, 398, 466};

                        /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000e. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
                        /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
                        {
                            String strDecode3;
                            int iM190 = C0001.m190(NPStringFog.decode("B5D7B6C9B5FE"));
                            Double dDecode = null;
                            while (true) {
                                switch (iM190) {
                                    case 56351:
                                        System.out.println(dDecode);
                                        if (C0043.m456() <= 0) {
                                            C0053.m574();
                                            strDecode3 = NPStringFog.decode("B5D7B6C1B5C5");
                                            iM190 = C0001.m190(strDecode3);
                                        } else {
                                            iM190 = (C0043.f73 - C0052.f91) ^ (-1747836);
                                        }
                                    case 1747839:
                                        break;
                                    case 1749639:
                                        dDecode = Double.decode(C0053.m553(NPStringFog.decode("211B3F230B1B35173136432A505B000E0D13")));
                                        iM190 = C0052.m503(NPStringFog.decode("B5D1B6C1"));
                                    case 1752740:
                                        iM190 = (C0043.f73 - C0053.f92) + 1754007;
                                    case 1754411:
                                        strDecode3 = C0052.f91 * (C0002.f2 + (-488)) > 0 ? NPStringFog.decode("B5D3B6C5B5FE") : NPStringFog.decode("B5D0B6C4B5C5");
                                        iM190 = C0001.m190(strDecode3);
                                    case 1754654:
                                        if (C0043.m456() <= 0) {
                                            if (C0043.f73 % (C0001.f1 % (-5117)) >= 0) {
                                                C0053.m574();
                                            }
                                            iM190 = C0043.m455(NPStringFog.decode("B5D2B6C0B5C7"));
                                        } else {
                                            if (C0052.f91 * (C0002.f2 + (-488)) > 0) {
                                            }
                                            iM190 = C0001.m190(strDecode3);
                                        }
                                        break;
                                }
                                return;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:60:0x009c A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:65:0x008d A[SYNTHETIC] */
                        /* renamed from: ۢۡ۟۟, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static short[] m397() {
                            String strDecode3;
                            int i3;
                            int i4;
                            String strDecode4 = NPStringFog.decode("B5D1B6C1B5C2");
                            int iM503 = C0052.m503(strDecode4);
                            short[] sArr = null;
                            while (true) {
                                short[] sArr2 = sArr;
                                while (true) {
                                    String strDecode5 = NPStringFog.decode("B5D3B6C9B5C7");
                                    switch (iM503) {
                                        case 56328:
                                        case 1753479:
                                            if (C0053.m574() >= 0) {
                                                C0001.m192();
                                                strDecode3 = NPStringFog.decode("B5D8B6C1B5C1");
                                                iM503 = C0001.m190(strDecode3);
                                            } else {
                                                iM503 = C0043.m455(strDecode5);
                                            }
                                        case 1746687:
                                            if ((C0053.f92 ^ (C0053.f92 / (-753))) >= 0) {
                                                C0043.f73 = 99;
                                                iM503 = C0053.m562(NPStringFog.decode("B5D3B6C2B5C6"));
                                            } else {
                                                iM503 = (C0043.f73 - C0053.f92) + 1753821;
                                            }
                                            sArr2 = null;
                                        case 1747650:
                                            if (C0002.f2 - (C0043.f73 * (-7823)) >= 0) {
                                                break;
                                            }
                                            sArr2 = sArr;
                                            break;
                                        case 1748644:
                                            if (C0052.m520() >= 0) {
                                                iM503 = C0043.f73 % (C0002.f2 + 1048) >= 0 ? C0053.m562(strDecode5) : (C0001.f1 ^ C0052.f91) + 1755850;
                                            } else if (C0002.m259() < 0) {
                                                C0002.m259();
                                                strDecode3 = NPStringFog.decode("B5D4B6C0B5C4");
                                                iM503 = C0001.m190(strDecode3);
                                            } else {
                                                iM503 = C0052.m503(NPStringFog.decode("B5D2B6C4B5C5"));
                                            }
                                        case 1749761:
                                            if (C0002.f2 >= 0) {
                                                C0043.m456();
                                                iM503 = C0001.m190(strDecode4);
                                            } else {
                                                i3 = C0002.f2 | C0001.f1;
                                                i4 = -1746680;
                                                iM503 = i3 ^ i4;
                                            }
                                        case 1750663:
                                            if (C0002.m259() < 0) {
                                            }
                                            break;
                                        case 1750817:
                                            break;
                                        case 1752648:
                                            if (C0002.m259() >= 0) {
                                                C0001.m192();
                                                strDecode5 = NPStringFog.decode("B5D6B6C7B5C1");
                                            } else {
                                                strDecode5 = strDecode4;
                                            }
                                            iM503 = C0043.m455(strDecode5);
                                        case 1754468:
                                            i3 = C0001.f1 * C0001.f1;
                                            i4 = 1781198;
                                            iM503 = i3 ^ i4;
                                        case 1755399:
                                            sArr = f36short;
                                            if (C0052.f91 - (C0002.f2 + 9527) >= 0) {
                                                C0001.f1 = 86;
                                                strDecode3 = NPStringFog.decode("B5D8B6C0B5C1");
                                                iM503 = C0001.m190(strDecode3);
                                            } else {
                                                iM503 = C0053.m562(NPStringFog.decode("B5D0B6FEB5C0"));
                                            }
                                    }
                                    return sArr2;
                                }
                                iM503 = C0001.m190(NPStringFog.decode("B5D6B6C0B5C3"));
                            }
                        }

                        /* JADX WARN: Removed duplicated region for block: B:87:0x00cd A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:90:0x00dc A[SYNTHETIC] */
                        @Override // np.protect.assets.p.AbstractC0039
                        /* renamed from: ۟۟ */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void mo380(Pine.C0047 c0047) {
                            int i3;
                            int i4;
                            String strDecode3;
                            int i5;
                            int i6;
                            String strDecode4;
                            String strDecode5 = NPStringFog.decode("B5D8B6C9B5C9");
                            int iM503 = C0052.m503(strDecode5);
                            String str = null;
                            while (true) {
                                String strDecode6 = NPStringFog.decode("B5D6B6C4B5C0");
                                switch (iM503) {
                                    case 56475:
                                        if ((C0001.f1 ^ (C0053.f92 + 9359)) <= 0) {
                                            C0052.m520();
                                            strDecode6 = NPStringFog.decode("B5D6B6C3B5C0");
                                            iM503 = C0052.m503(strDecode6);
                                        } else {
                                            i3 = C0053.f92 | C0053.f92;
                                            i4 = -1746162;
                                            iM503 = i3 ^ i4;
                                        }
                                    case 56567:
                                    case 1753514:
                                    case 1755431:
                                        if (C0043.f73 * (C0043.f73 / (-9785)) != 0) {
                                            C0043.m456();
                                            strDecode3 = NPStringFog.decode("B5D2B6C3B5C7");
                                            iM503 = C0052.m503(strDecode3);
                                        } else {
                                            i5 = C0043.f73 / C0043.f73;
                                            i6 = 1755619;
                                            iM503 = i5 + i6;
                                        }
                                    case 1746908:
                                        try {
                                            C0002.m243(c0047, C0053.m559(C0002.m234(), C0053.m548(str, C0002.f2 ^ (-77))));
                                        } catch (IOException e) {
                                            C0052.m538(c0047, e);
                                            strDecode4 = NPStringFog.decode("B5D8B6C3B5C0");
                                            break;
                                        }
                                        if (C0043.f73 + (C0043.f73 % (-8201)) >= 0) {
                                            C0043.f73 = 1;
                                            iM503 = C0002.m230(NPStringFog.decode("B5D3B6C4B5C9"));
                                        } else {
                                            iM503 = C0002.m230(strDecode6);
                                        }
                                    case 1750726:
                                        if (C0052.m520() > 0) {
                                            C0043.m456();
                                            iM503 = C0043.m455(NPStringFog.decode("B5D4B6C5B5C5"));
                                        } else {
                                            i5 = C0002.f2 ^ C0052.f91;
                                            i6 = 1755202;
                                            iM503 = i5 + i6;
                                        }
                                    case 1752640:
                                        if (C0002.f2 + C0043.f73 + 7126 <= 0) {
                                            C0001.f1 = 56;
                                            strDecode6 = NPStringFog.decode("B5D8B6C3B5C3");
                                        } else {
                                            strDecode6 = strDecode5;
                                        }
                                        iM503 = C0002.m230(strDecode6);
                                    case 1753602:
                                        if (C0053.f92 >= 0) {
                                            C0052.m520();
                                            iM503 = C0001.m190(NPStringFog.decode("B5D6B6C3B5C7"));
                                        } else {
                                            i3 = C0043.f73 * C0043.f73;
                                            i4 = 45062;
                                            iM503 = i3 ^ i4;
                                        }
                                    case 1754531:
                                        if (C0002.m255(str, C0053.m577(m397(), 0, C0043.f73 ^ (-162), 509))) {
                                            if (C0001.m192() >= 0) {
                                                C0043.f73 = 68;
                                                strDecode3 = NPStringFog.decode("B5D8B6FE");
                                                iM503 = C0052.m503(strDecode3);
                                            } else {
                                                i5 = C0002.f2 ^ C0052.f91;
                                                i6 = 1746674;
                                                iM503 = i5 + i6;
                                            }
                                        } else if (C0052.m520() > 0) {
                                        }
                                        break;
                                    case 1755338:
                                        if (C0002.f2 % (C0002.f2 % 1509) != 0) {
                                            strDecode4 = NPStringFog.decode("B5D8B6C9B5C4");
                                            iM503 = C0043.m455(strDecode4);
                                        } else {
                                            iM503 = C0052.m503(strDecode6);
                                        }
                                    case 1755436:
                                        try {
                                            super.mo380(c0047);
                                            if (C0002.m259() >= 0) {
                                                iM503 = C0001.m190(strDecode6);
                                            } else {
                                                strDecode3 = NPStringFog.decode("B5D8B6C9B5C5");
                                                iM503 = C0052.m503(strDecode3);
                                            }
                                        } catch (Throwable th) {
                                            throw new RuntimeException(th);
                                        }
                                    case 1755620:
                                        return;
                                    case 1755624:
                                        String str2 = (String) C0002.m245(c0047)[0];
                                        int iM520 = C0052.m520();
                                        strDecode4 = NPStringFog.decode("B5D7B6C5B5C1");
                                        if (iM520 <= 0) {
                                            C0043.m456();
                                            str = str2;
                                            iM503 = C0052.m503(strDecode4);
                                        } else {
                                            str = str2;
                                            iM503 = C0043.m455(strDecode4);
                                        }
                                }
                            }
                        }
                    });
                    if (C0043.f73 * (C0002.f2 % (-5494)) <= 0) {
                        C0043.m456();
                        iM230 = C0052.m503(strDecode2);
                    } else {
                        i = C0053.f92 ^ C0052.f91;
                        i2 = 1747190;
                        iM230 = i ^ i2;
                    }
                case 1755617:
                    System.out.println(strM256);
                    iM230 = C0001.m190(NPStringFog.decode("B5EFB6C6B5C5"));
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
    /* renamed from: ۟۟۟۟ۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m391() throws NumberFormatException {
        int i;
        int i2;
        String strDecode;
        int i3;
        int i4;
        int iM455 = C0043.m455(NPStringFog.decode("B5D3B6C2B5C9"));
        Float fValueOf = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D7B6C5B5C5");
            switch (iM455) {
                case 56323:
                    try {
                        C0001.m212(new Class[]{C0014.class});
                        iM455 = C0002.m230(NPStringFog.decode("B5D3B6C5B5C3"));
                    } catch (HookErrorException e) {
                        throw new RuntimeException(e);
                    }
                case 1747750:
                    System.out.println(fValueOf);
                    if (C0002.m259() >= 0) {
                        C0052.f91 = 29;
                        iM455 = C0043.m455(NPStringFog.decode("B5D5B6C7B5C7"));
                    } else {
                        iM455 = C0043.m455(strDecode2);
                    }
                case 1747900:
                    fValueOf = Float.valueOf(C0053.m553(NPStringFog.decode("1C045E560D2E26071D180A5B27222E1D53")));
                    if (C0043.m456() <= 0) {
                        strDecode2 = NPStringFog.decode("B5D2B6C6B5FE");
                        iM455 = C0043.m455(strDecode2);
                    } else {
                        i = C0043.f73 / C0043.f73;
                        i2 = 1747749;
                        iM455 = i + i2;
                    }
                case 1749818:
                    if ((C0053.f92 ^ (C0053.f92 * 2322)) <= 0) {
                        strDecode = NPStringFog.decode("B5D2B6C9B5C9");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i = C0001.f1 * C0001.f1;
                        i2 = 1624664;
                        iM455 = i + i2;
                    }
                case 1750628:
                    i = C0002.f2 + C0052.f91;
                    i2 = 56561;
                    iM455 = i + i2;
                case 1750664:
                    if (C0053.f92 / (C0001.f1 ^ 1493) != 0) {
                        C0002.f2 = 82;
                        iM455 = C0002.m230(NPStringFog.decode("B5D3B6C5B5C3"));
                    } else {
                        i3 = C0052.f91 % C0002.f2;
                        i4 = -56331;
                        iM455 = i3 ^ i4;
                    }
                case 1750689:
                    if (C0001.m192() < 0) {
                        if (C0052.f91 >= 0) {
                            C0001.f1 = 94;
                            strDecode2 = NPStringFog.decode("B5D5B6C4B5C9");
                        }
                        iM455 = C0053.m562(strDecode2);
                    } else if (C0043.m456() <= 0) {
                        C0001.f1 = 7;
                        strDecode = NPStringFog.decode("B5D5B6C7B5C0");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i3 = C0043.f73 * C0053.f92;
                        i4 = 1620158;
                        iM455 = i3 ^ i4;
                    }
                case 1752672:
                    if (C0052.f91 >= 0) {
                    }
                    iM455 = C0053.m562(strDecode2);
                    break;
                case 1752677:
                    if (C0001.m192() >= 0) {
                        iM455 = C0053.m562(NPStringFog.decode("B5D7B6C3B5C4"));
                    } else {
                        i3 = C0002.f2 % C0043.f73;
                        i4 = -1750724;
                        iM455 = i3 ^ i4;
                    }
                case 1754535:
                    return;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0019. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /* renamed from: ۟۠ۢۤۡ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m392() {
        int i;
        int i2;
        String strDecode = NPStringFog.decode("B5D8B6C1B5C5");
        int iM455 = C0043.m455(strDecode);
        Double dDecode = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D2B6C5B5C4");
            String strDecode3 = NPStringFog.decode("B5D3B6FEB5C2");
            switch (iM455) {
                case 56508:
                    m390();
                    iM455 = C0002.m230("ۥۧۥ");
                case 1746881:
                    System.out.println(dDecode);
                    if (C0052.f91 + (C0043.f73 / 6753) >= 0) {
                        C0053.m574();
                        iM455 = C0043.m455(strDecode);
                    } else {
                        iM455 = C0001.m190(strDecode3);
                    }
                case 1747656:
                    dDecode = Double.decode(C0002.m256(NPStringFog.decode("56421D25220F0C512336133F2E590E361F1B0F030532")));
                    if (C0002.f2 % (C0043.f73 % 7017) >= 0) {
                        C0052.f91 = 75;
                        iM455 = C0053.m562("ۥۧۥ");
                    } else {
                        i = C0053.f92 % C0043.f73;
                        i2 = 1747027;
                        iM455 = i + i2;
                    }
                case 1747810:
                    if (C0002.f2 - (C0043.f73 ^ (-3659)) >= 0) {
                        C0002.f2 = 25;
                        strDecode2 = NPStringFog.decode("B5D7B6C9B5C2");
                    }
                    iM455 = C0002.m230(strDecode2);
                case 1749671:
                case 1751771:
                    if (C0002.f2 >= 0) {
                        strDecode3 = NPStringFog.decode("B5D2B6C6B5C4");
                        iM455 = C0001.m190(strDecode3);
                    } else {
                        i = C0052.f91 - C0001.f1;
                        i2 = 1753224;
                        iM455 = i + i2;
                    }
                case 1749731:
                    strDecode2 = NPStringFog.decode("B5D4B6C9B5FE");
                    iM455 = C0002.m230(strDecode2);
                case 1750535:
                    break;
                case 1752707:
                    if (C0043.m456() > 0) {
                        iM455 = C0001.m190(strDecode3);
                    } else if (C0043.f73 >= 0) {
                        C0001.f1 = 12;
                        iM455 = C0002.m230(strDecode2);
                    } else {
                        i = C0053.f92 ^ C0043.f73;
                        i2 = 1746749;
                        iM455 = i + i2;
                    }
                case 1753511:
                    iM455 = C0001.m190(strDecode);
                case 1755372:
                    if (C0002.m259() >= 0) {
                        if (C0002.f2 - (C0043.f73 ^ (-3659)) >= 0) {
                        }
                        iM455 = C0002.m230(strDecode2);
                    } else if ((C0002.f2 | (C0002.f2 / (-2825))) >= 0) {
                        C0002.m259();
                        iM455 = C0043.m455(NPStringFog.decode("B5D6B6C3B5C2"));
                    } else {
                        iM455 = C0052.m503(NPStringFog.decode("B5D6B6C3"));
                    }
                    break;
                case 1755407:
                    iM455 = C0001.m190(strDecode3);
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d4 A[SYNTHETIC] */
    /* renamed from: ۟ۥ۟ۦ۠, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static a m393() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D5B6C0");
        int iM190 = C0001.m190(strDecode3);
        a aVar = null;
        a aVar2 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D1B6C1B5FE");
            switch (iM190) {
                case 56476:
                    if (C0052.m520() > 0) {
                        iM190 = (C0002.f2 | (C0052.f91 + (-8206))) >= 0 ? C0043.m455(NPStringFog.decode("B5D4B6C0B5C1")) : (C0002.f2 ^ C0053.f92) ^ 1749095;
                    } else if (C0002.m259() < 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D6B6C5B5FE");
                        iM190 = C0053.m562(strDecode);
                    } else {
                        i = C0053.f92 * C0053.f92;
                        i2 = 1085336;
                        iM190 = i + i2;
                    }
                case 1747868:
                    aVar2 = aVar;
                    iM190 = C0053.m562(strDecode4);
                case 1747932:
                    if (C0002.f2 >= 0) {
                        strDecode4 = NPStringFog.decode("B5D6B6C0B5C1");
                        iM190 = C0053.m562(strDecode4);
                    } else {
                        i = C0043.f73 / C0002.f2;
                        i2 = 1750810;
                        iM190 = i + i2;
                    }
                case 1748640:
                    break;
                case 1749579:
                case 1751555:
                    if (C0002.f2 - (C0043.f73 / (-1108)) >= 0) {
                        C0001.f1 = 52;
                        iM190 = C0052.m503(NPStringFog.decode("B5D6B6C6B5FE"));
                    } else {
                        iM190 = C0053.m562(strDecode4);
                    }
                case 1749761:
                    aVar = f35;
                    if (C0052.m520() <= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D0B6C9B5C5");
                        iM190 = C0053.m562(strDecode);
                    } else {
                        i = C0053.f92 | C0001.f1;
                        i2 = 1748393;
                        iM190 = i + i2;
                    }
                case 1750812:
                    iM190 = (C0052.f91 - C0002.f2) + 1754556;
                    aVar2 = null;
                case 1753477:
                    if (C0002.m259() < 0) {
                    }
                    break;
                case 1754470:
                    if (C0002.m259() >= 0) {
                        strDecode2 = NPStringFog.decode("B5D7B6C3B5C0");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        i = C0001.f1 % C0043.f73;
                        i2 = 1751534;
                        iM190 = i + i2;
                    }
                case 1755399:
                    strDecode2 = C0052.f91 >= 0 ? NPStringFog.decode("B5D1B6FEB5C7") : strDecode3;
                    iM190 = C0002.m230(strDecode2);
            }
            return aVar2;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* renamed from: ۣۥۤۢ, reason: not valid java name and contains not printable characters */
    public static short[] m394() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3;
        String strDecode4 = NPStringFog.decode("B5D4B6C6");
        int iM230 = C0002.m230(strDecode4);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            switch (iM230) {
                case 56451:
                    if (C0052.m520() >= 0) {
                        i = C0053.f92 / C0002.f2;
                        i2 = 1752474;
                        iM230 = i + i2;
                    } else {
                        iM230 = (C0053.f92 + C0001.f1) ^ (-1748682);
                    }
                case 1746850:
                case 1747930:
                    if (C0043.f73 >= 0) {
                        strDecode = NPStringFog.decode("B5D0B6C9B5C2");
                        iM230 = C0002.m230(strDecode);
                    } else {
                        i = C0001.f1 / C0053.f92;
                        i2 = 1747929;
                        iM230 = i + i2;
                    }
                case 1746941:
                    if ((C0053.f92 | (C0052.f91 % (-2108))) >= 0) {
                        C0002.f2 = 70;
                        strDecode3 = NPStringFog.decode("B5D0B6C9B5C3");
                        iM230 = C0001.m190(strDecode3);
                    } else {
                        strDecode2 = NPStringFog.decode("B5EFB6C5B5C6");
                        iM230 = C0053.m562(strDecode2);
                    }
                case 1747929:
                    break;
                case 1748739:
                    if ((C0001.f1 ^ (C0053.f92 % 2702)) >= 0) {
                        C0043.f73 = 68;
                        strDecode2 = NPStringFog.decode("B5D0B6C9B5C0");
                        iM230 = C0053.m562(strDecode2);
                    } else {
                        strDecode3 = NPStringFog.decode("B5D3B6C7B5C0");
                        iM230 = C0001.m190(strDecode3);
                    }
                case 1750750:
                    if (C0002.f2 / (C0053.f92 ^ (-2233)) != 0) {
                        C0002.m259();
                        iM230 = C0053.m562(NPStringFog.decode("B5D1B6C2B5C4"));
                    } else {
                        iM230 = (C0002.f2 | C0043.f73) + 1746944;
                    }
                    sArr = null;
                case 1752484:
                    sArr2 = f34short;
                    if (C0052.m520() <= 0) {
                        C0043.m456();
                        strDecode = NPStringFog.decode("B5D8B6C6B5FE");
                    } else {
                        strDecode = NPStringFog.decode("B5D6B6C1B5C2");
                    }
                    iM230 = C0002.m230(strDecode);
                case 1753449:
                    iM230 = C0052.f91 * (C0043.f73 + (-8999)) <= 0 ? C0002.m230(strDecode4) : (C0043.f73 | C0002.f2) ^ (-1747932);
                    sArr = sArr2;
                case 1754601:
                    iM230 = (C0053.f92 + C0001.f1) ^ (-1748682);
                case 1755584:
                    i = C0001.f1 / C0052.f91;
                    i2 = 56453;
                    iM230 = i + i2;
            }
            return sArr;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x007b A[SYNTHETIC] */
    /* renamed from: ۧۡۤۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m395() throws NumberFormatException {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        int iM455 = C0043.m455(NPStringFog.decode("B5D1B6C4B5C2"));
        long j = 0;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D4B6C5B5C0");
            String strDecode3 = "ۣ۠";
            String strDecode4 = NPStringFog.decode("B5D7B6C9B5C1");
            switch (iM455) {
                case 56413:
                    if (C0043.m456() <= 0) {
                        C0002.m259();
                        iM455 = C0001.m190(strDecode4);
                    } else {
                        i = C0002.f2 ^ C0043.f73;
                        i2 = 56456;
                        iM455 = i ^ i2;
                    }
                case 56415:
                    m389();
                    strDecode2 = NPStringFog.decode("B5D2B6C9B5C4");
                case 56421:
                case 1748611:
                    if (C0053.f92 >= 0) {
                        C0052.m520();
                        strDecode3 = NPStringFog.decode("B5D5B6C0");
                        iM455 = C0053.m562(strDecode3);
                    } else {
                        i3 = C0052.f91 / C0053.f92;
                        i4 = 1749855;
                        iM455 = i3 + i4;
                    }
                case 1747873:
                    if (C0001.f1 / (C0001.f1 % (-9100)) > 0) {
                        C0052.f91 = 78;
                        strDecode = NPStringFog.decode("B5D4B6C1B5C1");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        iM455 = C0053.m562(strDecode3);
                    }
                case 1748799:
                    if (C0001.m192() < 0) {
                        if (C0052.m520() <= 0) {
                            C0043.f73 = 58;
                        } else {
                            i3 = C0002.f2 / C0053.f92;
                            i4 = 56415;
                            iM455 = i3 + i4;
                        }
                    } else if (C0001.f1 / (C0001.f1 % (-9100)) > 0) {
                    }
                    break;
                case 1749855:
                    if (C0052.m520() <= 0) {
                        strDecode = C0001.f1 - (C0052.f91 | (-7100)) <= 0 ? NPStringFog.decode("B5D5B6C7B5C0") : NPStringFog.decode("B5D6B6C2B5C5");
                    } else if (C0002.f2 < 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D1B6C9B5C7");
                    } else {
                        i3 = C0001.f1 / C0043.f73;
                        i4 = 1754657;
                        iM455 = i3 + i4;
                    }
                    iM455 = C0001.m190(strDecode);
                case 1751649:
                    System.out.println(j);
                    strDecode3 = strDecode4;
                    iM455 = C0053.m562(strDecode3);
                case 1752672:
                    if (C0043.f73 >= 0) {
                        C0002.f2 = 14;
                        iM455 = C0043.m455(NPStringFog.decode("B5D7B6C0B5C3"));
                    } else {
                        i = C0053.f92 - C0002.f2;
                        i2 = -1748447;
                        iM455 = i ^ i2;
                    }
                case 1753512:
                    if (C0002.f2 < 0) {
                    }
                    break;
                case 1753543:
                    j = Long.parseLong(C0002.m256(NPStringFog.decode("580A2350023110032602122C162900")));
                    iM455 = C0052.f91 >= 0 ? C0001.m190("ۣ۠") : C0043.m455(strDecode2);
                case 1754655:
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0060 A[SYNTHETIC] */
    /* renamed from: ۨۤۥ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m396() throws NumberFormatException {
        String strDecode;
        int i;
        int i2;
        int iM230 = C0002.m230(NPStringFog.decode("B5D3B6C7B5C4"));
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D8B6C7B5C4");
            switch (iM230) {
                case 1746727:
                case 1753512:
                    if ((C0043.f73 ^ (C0002.f2 | (-12))) <= 0) {
                        C0002.m259();
                        strDecode2 = NPStringFog.decode("B5D8B6FEB5C0");
                    }
                    iM230 = C0002.m230(strDecode2);
                case 1746781:
                    m391();
                    if (C0002.f2 >= 0) {
                        strDecode = NPStringFog.decode("B5D6B6C5B5C4");
                        iM230 = C0001.m190(strDecode);
                    } else {
                        iM230 = C0002.m230(strDecode2);
                    }
                case 1747712:
                    if ((C0052.f91 ^ (C0053.f92 | 8812)) > 0) {
                        C0053.f92 = 23;
                        iM230 = C0053.m562(NPStringFog.decode("B5D6B6C9B5C7"));
                    } else {
                        i = C0001.f1 % C0053.f92;
                        i2 = 1753222;
                        iM230 = i + i2;
                    }
                case 1750754:
                    if (C0002.m259() < 0) {
                        iM230 = (C0053.f92 % C0053.f92) ^ 1746781;
                    } else if ((C0052.f91 ^ (C0053.f92 | 8812)) > 0) {
                    }
                    break;
                case 1753575:
                    i = C0052.f91 % C0052.f91;
                    i2 = 1750754;
                    iM230 = i + i2;
                case 1753577:
                    if (C0002.f2 >= 0) {
                        C0001.f1 = 65;
                        strDecode = NPStringFog.decode("B5EFB6C3B5C1");
                        iM230 = C0001.m190(strDecode);
                    } else {
                        i = C0052.f91 * C0052.f91;
                        i2 = 1727268;
                        iM230 = i + i2;
                    }
                case 1755559:
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0057 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0049 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public InputStream r(C0015 c0015) {
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D1B6C0B5C7");
        int iM562 = C0053.m562(strDecode2);
        String str = null;
        while (true) {
            switch (iM562) {
                case 56413:
                    return (InputStream) C0001.m210(C0002.m219(m393()), C0052.m534(C0052.m509(this)), new Object[]{str});
                case 56474:
                    strDecode = C0053.f92 % (C0002.f2 % 7306) >= 0 ? NPStringFog.decode("B5EFB6C9B5FE") : strDecode2;
                    iM562 = C0053.m562(strDecode);
                case 1748678:
                    str = (String) C0002.m244(c0015)[1];
                    if (C0002.f2 - (C0052.f91 | (-5805)) <= 0) {
                        C0053.m574();
                        iM562 = C0001.m190(NPStringFog.decode("B5D8B6C9B5C6"));
                    } else {
                        iM562 = (C0002.f2 | C0001.f1) ^ (-1755627);
                    }
                case 1748741:
                    return C0053.m559(C0002.m234(), C0053.m548(str, C0043.f73 ^ (-162)));
                case 1755618:
                    if (C0002.m255(str, C0053.m577(m394(), 39, C0053.f92 ^ (-811), 1580))) {
                        if (C0002.f2 >= 0) {
                            C0002.f2 = 38;
                            iM562 = C0043.m455(NPStringFog.decode("B5D5B6FE"));
                        } else {
                            strDecode = NPStringFog.decode("B5D1B6C2B5C6");
                            iM562 = C0053.m562(strDecode);
                        }
                    } else if (C0001.f1 + (C0053.f92 ^ 394) < 0) {
                        C0053.m574();
                        iM562 = C0002.m230(NPStringFog.decode("B5EFB6FEB5C7"));
                    } else {
                        iM562 = C0052.m503(NPStringFog.decode("B5D3B6C1"));
                    }
                case 1755623:
                    if (C0001.f1 + (C0053.f92 ^ 394) < 0) {
                    }
                    break;
            }
        }
    }
}
