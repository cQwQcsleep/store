package np.protect.assets;

import Ark.VMProtect;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.iqoocg.nm.VfSEUCNxUWTV;
import com.shadow.okio.Utf8;
import com.swift.sandhook.C0002;
import com.swift.sandhook.SandHookConfig;
import com.swift.sandhook.lib.C0001;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import np.protect.assets.p.C0010;
import np.protect.assets.p.C0011;
import np.protect.assets.p.C0038;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* loaded from: /workspace/unpacked/classes.dex */
public class Shell2Application extends VfSEUCNxUWTV {
    private static int hookMode;

    /* renamed from: short, reason: not valid java name */
    private static final short[] f5short;

    /* renamed from: ۟۟۟۟ۡ, reason: not valid java name and contains not printable characters */
    public static String f6;

    /* renamed from: ۟۟۟۟ۢ, reason: not valid java name and contains not printable characters */
    private static String f7;

    /* renamed from: ۣ۟۟۟۟, reason: not valid java name and contains not printable characters */
    private static boolean f8;

    /* renamed from: ۟۟۟۟ۤ, reason: not valid java name and contains not printable characters */
    private static String f9;

    /* renamed from: ۟۟۟۟ۥ, reason: not valid java name and contains not printable characters */
    private static String f10;

    /* renamed from: ۟۟۟۟ۦ, reason: not valid java name and contains not printable characters */
    private static String f11;

    /* renamed from: ۟۟۟۟ۧ, reason: not valid java name and contains not printable characters */
    private static String f12;

    /* renamed from: ۟۟۟۟ۨ, reason: not valid java name and contains not printable characters */
    private final Application f13;

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0011. Please report as an issue. */
    static {
        String strDecode;
        String strDecode2;
        VMProtect.ArkSafeVM(403242415);
        String strDecode3 = NPStringFog.decode("B5D0B6C0B5C5");
        int iM562 = C0053.m562(strDecode3);
        while (true) {
            switch (iM562) {
                case Utf8.LOG_SURROGATE_HEADER /* 56320 */:
                    m320();
                    if (C0002.m259() >= 0) {
                        C0052.f91 = 98;
                        strDecode = NPStringFog.decode("B5D0B6C1");
                    } else {
                        strDecode = NPStringFog.decode("B5D3B6C3B5FE");
                    }
                    iM562 = C0053.m562(strDecode);
                case 1746722:
                    if (C0001.m192() >= 0) {
                        strDecode2 = NPStringFog.decode("B5D3B6C6B5C5");
                        iM562 = C0001.m190(strDecode2);
                    } else {
                        iM562 = C0002.m230(strDecode3);
                    }
                case 1747715:
                    f5short = new short[]{757, 753, 753, 763, 732, 735, 753, 762, 761, 764, 761, 758, 762, 735, 723, 713, 720, 723, 721, 767, 641, 719, 648, 762, 761, 745, 765, 766, 761, 761, 747, 763, 761, 725, 761, 719, 735, 735, 754, 731, 761, 735, 765, 761, 761, 727, 767, 762, 761, 756, 720, 727, 716, 738, 719, 663, 752, 654, 733, 726, 751, 715, 721, 714, 705, 718, 727, 749, 766, 747, 652, 766, 755, 718, 737, 651, 747, 726, 757, 738, 735, 753, 731, 731, 721, 761, 761, 762, 641, 765, 650, 722, 715, 659, 720, 729, 767, 745, 746, 648, 744, 718, 759, 649, 757, 748, 719, 763, 765, 648, 713, 746, 724, 733, 766, 748, 653, 755, 748, 762, 763, 719, 754, 738, 746, 717, 767, 650, 655, 751, 765, 659, 747, 651, 651, 650, 718, 755, 763, 735, 714, 744, 748, 744, 752, 721, 730, 762, 762, 725, 758, 704, 655, 716, 704, 722, 655, 749, 746, 652, 737, 759, 736, 765, 746, 761, 745, 724, 663, 706, 712, 659, 748, 715, 724, 732, 720, 712, 744, 724, 762, 749, 721, 649, 651, 659, 648, 719, 720, 748, 762, 737, 726, 724, 758, 722, 762, 766, 738, 719, 764, 704, 649, 750, 753, 752, 756, 640, 715, 738, 750, 754, 759, 723, 712, 754, 765, 758, 746, 663, 761, 735, 757, 762, 761, 761, 765, 763, 735, 737, 762, 758, 764, 705, 746, 723, 654, 766, 744, 737, 761, 747, 648, 765, 723, 720, 749, 641, 714, 648, 713, 705, 731, 725, 754, 722, 720, 744, 758, 758, 729, 729, 721, 649, 706, 745, 704, 734, 704, 752, 766, 757, 747, 765, 652, 748, 725, 763, 756, 761, 753, 655, 716, 705, 712, 653, 755, 716, 751, 715, 721, 727, 655, 726, 766, 758, 759, 736, 716, 735, 762, 736, 757, 759, 746, 722, 746, 763, 733, 733, 720, 648, 756, 765, 651, 713, 651, 767, 715, 724, 716, 725, 655, 640, 764, 763, 663, 763, 706, 720, 733, 717, 735, 716, 752, 764, 744, 712, 641, 714, 705, 650, 767, 735, 717, 717, 737, 754, 712, 763, 761, 736, 754, 757, 649, 737, 767, 726, 659, 726, 756, 733, 729, 733, 730, 748, 754, 758, 650, 720, 754, 654, 712, 722, 756, 749, 736, 716, 718, 717, 663, 746, 736, 755, 705, 715, 720, 716, 727, 653, 704, 738, 737, 713, 720, 726, 714, 729, 722, 745, 723, 745, 754, 762, 761, 744, 746, 746, 654, 754, 640, 705, 733, 712, 730, 723, 733, 767, 724, 726, 704, 756, 730, 736, 758, 754, 727, 717, 763, 734, 651, 757, 659, 735, 651, 738, 745, 731, 653, 650, 648, 729, 754, 758, 654, 756, 715, 738, 765, 652, 641, 745, 729, 724, 748, 754, 720, 763, 655, 648, 723, 641, 659, 649, 750, 752, 651, 704, 714, 745, 724, 705, 641, 717, 654, 650, 748, 704, 749, 640, 652, 727, 750, 730, 734, 704, 663, 714, 714, 764, 749, 763, 745, 745, 764, 762, 759, 738, 736, 750, 734, 659, 726, 747, 729, 746, 763, 652, 738, 704, 752, 757, 748, 718, 653, 653, 640, 712, 748, 721, 758, 713, 764, 749, 650, 755, 641, 755, 752, 755, 756, 766, 757, 755, 705, 764, 755, 655, 717, 734, 733, 648, 720, 715, 745, 714, 640, 732, 729, 704, 726, 751, 649, 653, 751, 716, 714, 756, 718, 653, 722, 744, 648, 714, 715, 717, 767, 736, 766, 749, 650, 653, 738, 704, 748, 641, 746, 640, 704, 722, 761, 723, 765, 761, 721, 767, 722, 663, 766, 651, 717, 753, 712, 754, 706, 649, 733, 733, 704, 641, 765, 738, 749, 761, 735, 713, 764, 651, 653, 747, 759, 663, 655, 751, 767, 763, 640, 651, 755, 649, 719, 719, 734, 654, 706, 762, 744, 754, 756, 718, 735, 652, 766, 720, 748, 738, 736, 653, 727, 735, 719, 735, 759, 716, 754, 762, 762, 659, 721, 648, 715, 757, 761, 733, 736, 654, 755, 654, 754, 716, 650, 719, 750, 724, 654, 734, 650, 731, 722, 745, 754, 761, 729, 749, 736, 719, 720, 725, 759, 715, 720, 719, 764, 756, 754, 749, 705, 705, 733, 655, 734, 767, 767, 730, 729, 654, 717, 719, 705, 640, 748, 651, 714, 767, 641, 734, 663, 727, 762, 714, 716, 735, 753, 735, 640, 734, 712, 659, 754, 754, 757, 745, 755, 723, 723, 751, 721, 729, 713, 750, 706, 752, 712, 732, 726, 727, 721, 763, 718, 705, 733, 650, 653, 652, 748, 720, 754, 764, 747, 752, 753, 756, 765, 651, 717, 731, 716, 745, 754, 761, 766, 712, 731, 723, 729, 649, 706, 725, 716, 756, 736, 733, 723, 712, 663, 747, 763, 653, 738, 653, 715, 706, 730, 653, 714, 712, 722, 726, 712, 734, 735, 765, 715, 719, 753, 648, 715, 640, 723, 744, 730, 745, 652, 718, 766, 745, 738, 727, 765, 716, 763, 715, 649, 715, 650, 654, 641, 724, 761, 705, 653, 759, 719, 641, 723, 649, 641, 663, 715, 752, 659, 751, 750, 750, 731, 721, 720, 715, 731, 764, 735, 729, 747, 717, 765, 761, 645, 645, 738, 764, 723, 764, 766, 739, 760, 745, 751, 760, 723, 749, 2715, 2697, 2697, 2719, 2702, 2697, 2773, 3239, 3244, 3315, 3305, 3322, 3307, 3296, 3317, 3239, 3244, 3315, 3302, 3305, 3301, 3309, 3317, 3238, 3323, 3303, 2825, 2902, 2892, 2911, 2894, 2885, 2896, 2467, 2556, 2537, 2534, 2538, 2530, 2554, 2298, 2234, 2228, 2470, 2534, 2536, 2433, 2536, 2538, 1545, 1562, 1541, 1630, 1628, 1605, 1566, 1616, 1545, 606, 589, 594, 602, 606, 605, 598, 530, 585, 520, 606, 1545, 2381, 2430, 2427, 2416, 748, 605, 630, 615, 623, 626, 1990, 2046, 1969, 430, 485, 480, 501, 480, 430, 500, 498, 484, 499, 430, 2071, 2971, 3024, 3029, 3008, 3029, 2971, 3024, 3029, 3008, 3029, 2971, 1589, 1653, 1659, 1554, 1659, 1657, 1321, 1338, 1317, 1406, 1404, 1381, 1342, 1392, 1321, 1026, 1090, 1100, 2045, 2030, 2033, 2041, 2045, 2046, 2037, 1969, 2026, 1963, 2045, 2979, 2947, 1330, 1298, 2166, 
                    491, 427, 421, 460, 421, 423, 2826, 2890, 2884, 2016, 2035, 2028, 1975, 1973, 1964, 2039, 1977, 2016, 1160, 1179, 1156, 1164, 1160, 1163, 1152, 1220, 1183, 1246, 1160, 429, 2927, 2678, 2232, 2322, 1509, 1513, 1515, 1448, 1507, 1534, 1511, 1515, 1526, 1514, 1507, 1448, 1515, 1535, 1511, 1526, 1526, 1514, 1519, 1509, 1511, 1522, 1519, 1513, 1512, 1448, 1483, 1535, 1479, 1526, 1526, 3048, 3044, 3046, 2981, 3054, 3059, 3050, 3046, 3067, 3047, 3054, 2981, 3046, 3058, 3050, 3067, 3067, 3047, 3042, 3048, 3050, 3071, 3042, 3044, 3045, 2256, 2257, 530, 606, 530, 721, 2034, 2451, 2960};
                    strDecode2 = NPStringFog.decode("B5D6B6C7B5FE");
                    iM562 = C0001.m190(strDecode2);
                case 1750624:
                    f8 = false;
                    strDecode2 = NPStringFog.decode("B5D8B6C6B5C0");
                    iM562 = C0001.m190(strDecode2);
                case 1753631:
                    f6 = C0043.m454(m313(), 0, C0043.f73 ^ (-1015), 696);
                    if (C0043.f73 + (C0001.f1 / (-8186)) >= 0) {
                        C0053.f92 = 94;
                        strDecode2 = NPStringFog.decode("B5D6B6C7B5FE");
                        iM562 = C0001.m190(strDecode2);
                    } else {
                        iM562 = (C0001.f1 % C0001.f1) + Utf8.LOG_SURROGATE_HEADER;
                    }
                case 1755586:
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000e. Please report as an issue. */
    public Shell2Application() throws NumberFormatException {
        String strDecode;
        String strDecode2;
        int i;
        int i2;
        int iM503 = C0052.m503(NPStringFog.decode("B5EFB6FEB5C4"));
        Double dValueOf = null;
        while (true) {
            switch (iM503) {
                case 56481:
                    if (C0052.m520() <= 0) {
                        iM503 = C0002.m230(NPStringFog.decode("B5D6B6C2B5C4"));
                    } else {
                        strDecode = NPStringFog.decode("B5D5B6FEB5C4");
                        iM503 = C0053.m562(strDecode);
                    }
                case 1746693:
                    this.f13 = (Application) C0052.m499(C0002.m228(C0002.m231(C0053.m543()), new Class[0]), new Object[0]);
                    if (C0053.m574() >= 0) {
                        C0053.f92 = 15;
                        iM503 = C0052.m503(NPStringFog.decode("B5D2B6C3B5C7"));
                    } else {
                        strDecode2 = NPStringFog.decode("B5D5B6C7");
                        iM503 = C0043.m455(strDecode2);
                    }
                case 1749670:
                    System.out.println(dValueOf);
                    if (C0001.f1 + (C0053.f92 ^ 6436) >= 0) {
                        C0053.m574();
                        iM503 = C0002.m230(NPStringFog.decode("B5D6B6C2B5C4"));
                    } else {
                        i = C0052.f91 ^ C0053.f92;
                        i2 = 1752583;
                        iM503 = i ^ i2;
                    }
                case 1752459:
                    break;
                case 1753544:
                    dValueOf = Double.valueOf(C0053.m553(NPStringFog.decode("02285F2D27182029461E291C2F1932")));
                    if (C0002.f2 >= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D6B6C4B5C1");
                        iM503 = C0053.m562(strDecode);
                    } else {
                        iM503 = (C0053.f92 - C0053.f92) + 1749670;
                    }
                case 1753601:
                    strDecode = NPStringFog.decode("B5D5B6FEB5C4");
                    iM503 = C0053.m562(strDecode);
                case 1755624:
                    if (C0053.f92 >= 0) {
                        C0043.m456();
                        strDecode2 = NPStringFog.decode("B5D4B6C1B5C0");
                        iM503 = C0043.m455(strDecode2);
                    } else {
                        i = C0052.f91 - C0043.f73;
                        i2 = 1746688;
                        iM503 = i ^ i2;
                    }
            }
            return;
        }
    }

    private static native boolean i();

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01df A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x023c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0226 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0192  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void init() throws NumberFormatException {
        int i;
        int i2;
        int iM308;
        String strDecode;
        int i3;
        int i4;
        String strDecode2;
        String strDecode3;
        String strDecode4 = NPStringFog.decode("B5D4B6C7B5C3");
        int iM562 = C0053.m562(strDecode4);
        int i5 = 0;
        Double dValueOf = null;
        while (true) {
            String strDecode5 = NPStringFog.decode("B5D6B6C9B5C7");
            String strDecode6 = NPStringFog.decode("B5EFB6C1B5C5");
            String strDecode7 = "ۥۧۢ";
            switch (iM562) {
                case 1746689:
                    if (C0052.m520() > 0) {
                        C0001.f1 = 14;
                        int i6 = i5;
                        strDecode = NPStringFog.decode("B5D5B6FEB5C7");
                        iM308 = i6;
                        int iM190 = C0001.m190(strDecode);
                        i5 = iM308;
                        iM562 = iM190;
                    } else {
                        iM562 = C0043.m455("ۥۧۢ");
                    }
                case 1746692:
                    f7 = C0002.m223(m306(C0001.m191(C0001.m216(), m321())));
                    i = C0043.f73 + C0052.f91;
                    i2 = 1750120;
                    iM562 = i + i2;
                case 1746723:
                    iM308 = m308();
                    if (C0043.f73 >= 0) {
                        i5 = iM308;
                        iM562 = C0053.m562(strDecode7);
                    } else {
                        strDecode = NPStringFog.decode("B5D1B6C7B5C6");
                        int iM1902 = C0001.m190(strDecode);
                        i5 = iM308;
                        iM562 = iM1902;
                    }
                case 1747719:
                    if (C0001.m192() >= 0) {
                        if (C0043.f73 / (C0043.f73 + 502) != 0) {
                            iM562 = C0043.m455(strDecode5);
                        } else {
                            i3 = C0053.f92 % C0001.f1;
                            i4 = -1748744;
                            iM562 = i3 ^ i4;
                        }
                    } else if (C0001.m192() < 0) {
                        strDecode2 = NPStringFog.decode("B5D5B6C2B5C9");
                        iM562 = C0053.m562(strDecode2);
                    } else {
                        i3 = C0043.f73 - C0053.f92;
                        i4 = 1753315;
                        iM562 = i3 ^ i4;
                    }
                case 1747741:
                    strDecode3 = NPStringFog.decode("B5D1B6C6B5C6");
                    iM562 = C0043.m455(strDecode3);
                case 1748645:
                    if (m308() != 0) {
                        if (C0052.f91 / (C0053.f92 ^ (-1631)) != 0) {
                            C0001.f1 = 38;
                            strDecode6 = NPStringFog.decode("B5D5B6C2B5C5");
                        }
                    } else if (C0053.f92 >= 0) {
                        strDecode3 = NPStringFog.decode("B5D1B6C1B5C6");
                        iM562 = C0043.m455(strDecode3);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D3B6C3B5C4");
                        iM562 = C0053.m562(strDecode2);
                    }
                case 1748648:
                case 1751711:
                case 1752674:
                    if (C0053.f92 - (C0002.f2 ^ (-4904)) >= 0) {
                        C0001.f1 = 24;
                        strDecode2 = NPStringFog.decode("B5D5B6C9");
                        iM562 = C0053.m562(strDecode2);
                    } else {
                        i = C0001.f1 - C0043.f73;
                        i2 = 1748246;
                        iM562 = i + i2;
                    }
                case 1748740:
                    if (C0001.f1 <= 0) {
                        C0053.f92 = 39;
                        strDecode5 = NPStringFog.decode("B5EFB6C2B5C0");
                    } else {
                        i3 = C0002.f2 % C0053.f92;
                        i4 = -1751788;
                        iM562 = i3 ^ i4;
                    }
                case 1748768:
                    m317(m310());
                    if ((C0052.f91 | (C0043.f73 ^ (-8674))) >= 0) {
                        iM562 = C0052.m503(strDecode4);
                    } else {
                        i3 = C0002.f2 / C0043.f73;
                        i4 = 1747719;
                        iM562 = i3 ^ i4;
                    }
                case 1748832:
                    dValueOf = Double.valueOf(C0043.m453(NPStringFog.decode("1A033D51082B100101061124220C0537323416095A0A1605")));
                    i = C0043.f73 ^ C0052.f91;
                    i2 = 1755366;
                    iM562 = i + i2;
                case 1748834:
                    if (i5 != 1) {
                        if (C0052.m520() <= 0) {
                            strDecode2 = NPStringFog.decode("B5D3B6C3B5C4");
                            iM562 = C0053.m562(strDecode2);
                        } else {
                            iM562 = C0043.m455("ۧۢۢ");
                        }
                    } else if (C0052.m520() > 0) {
                    }
                    break;
                case 1748865:
                    SandHookConfig.libLoader = new SandHookConfig.LibLoader() { // from class: np.protect.assets.Shell2Application.2
                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0014. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
                        {
                            int i7;
                            int i8;
                            int iM1903 = C0001.m190(NPStringFog.decode("B5EFB6C9B5C9"));
                            int i9 = 0;
                            while (true) {
                                String strDecode8 = NPStringFog.decode("B5D3B6FEB5C7");
                                switch (iM1903) {
                                    case 56481:
                                        iM1903 = C0002.m230(C0002.f2 >= 0 ? NPStringFog.decode("B5D1B6C4B5C1") : strDecode8);
                                    case 1746975:
                                        if (C0053.m574() >= 0) {
                                            i7 = C0043.f73 - C0002.f2;
                                            i8 = 1753726;
                                            iM1903 = i7 + i8;
                                        } else {
                                            iM1903 = C0002.m230(C0002.f2 >= 0 ? NPStringFog.decode("B5D1B6C4B5C1") : strDecode8);
                                        }
                                        break;
                                    case 1747719:
                                        i7 = C0043.f73 ^ C0002.f2;
                                        i8 = 1746738;
                                        iM1903 = i7 + i8;
                                    case 1750538:
                                        break;
                                    case 1752488:
                                        System.out.println(i9);
                                        iM1903 = C0052.m503(strDecode8);
                                    case 1753635:
                                        i9 = Integer.parseInt(C0043.m453(NPStringFog.decode("06360252242B1504472A3B581521")));
                                        iM1903 = C0043.m455(NPStringFog.decode("B5D5B6C1B5C2"));
                                }
                                return;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
                        /* renamed from: ۟ۧ۠ۤ۠, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static void m331(Object obj) {
                            int i7;
                            int i8;
                            String strDecode8;
                            int iM455 = C0043.m455(NPStringFog.decode("B5D7B6C7B5FE"));
                            while (true) {
                                String strDecode9 = NPStringFog.decode("B5D0B6C7B5C0");
                                switch (iM455) {
                                    case 1746721:
                                        break;
                                    case 1746904:
                                    case 1750725:
                                        iM455 = (C0052.f91 - C0053.f92) + 1746069;
                                    case 1747867:
                                        if (C0052.f91 - (C0002.f2 * 8257) <= 0) {
                                            C0002.m259();
                                        } else {
                                            i7 = C0001.f1 | C0043.f73;
                                            i8 = -1746781;
                                            iM455 = i7 ^ i8;
                                        }
                                    case 1747929:
                                        if (C0043.f73 + (C0053.f92 | 9971) >= 0) {
                                            C0043.f73 = 31;
                                            strDecode8 = NPStringFog.decode("B5D7B6C9B5C7");
                                            iM455 = C0052.m503(strDecode8);
                                        } else {
                                            i7 = C0002.f2 / C0043.f73;
                                            i8 = 1754592;
                                            iM455 = i7 ^ i8;
                                        }
                                    case 1749857:
                                        Shell2Application.m287((String) obj);
                                        strDecode8 = NPStringFog.decode("B5EFB6C1B5C3");
                                        iM455 = C0052.m503(strDecode8);
                                    case 1754592:
                                        if (C0052.m520() >= 0) {
                                            iM455 = (C0053.f92 ^ (C0001.f1 + (-826))) <= 0 ? C0002.m230(strDecode9) : C0001.m190(NPStringFog.decode("B5D2B6C9B5C6"));
                                        } else {
                                            if (C0002.f2 - (C0053.f92 | (-4696)) <= 0) {
                                                C0002.m259();
                                                strDecode9 = NPStringFog.decode("B5D7B6C5B5C6");
                                            }
                                        }
                                    case 1755494:
                                        if (C0002.f2 - (C0053.f92 | (-4696)) <= 0) {
                                        }
                                        break;
                                }
                                return;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:53:0x00ce A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:59:0x00d9 A[SYNTHETIC] */
                        /* renamed from: ۥۡۢۡ, reason: contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static String m332() {
                            String strDecode8;
                            String strDecode9;
                            String strDecode10 = NPStringFog.decode("B5D0B6FEB5C2");
                            int iM503 = C0052.m503(strDecode10);
                            String strM310 = null;
                            while (true) {
                                String str = strM310;
                                while (true) {
                                    switch (iM503) {
                                        case 1747652:
                                            if (C0052.m520() > 0) {
                                                if (C0053.f92 % (C0001.f1 ^ (-6375)) >= 0) {
                                                    strDecode9 = strDecode10;
                                                    iM503 = C0001.m190(strDecode9);
                                                } else {
                                                    iM503 = (C0002.f2 % C0052.f91) ^ (-1749781);
                                                }
                                            } else if (C0043.f73 - (C0052.f91 % (-122)) < 0) {
                                                C0001.f1 = 44;
                                                strDecode9 = NPStringFog.decode("B5D5B6C7B5FE");
                                                iM503 = C0001.m190(strDecode9);
                                            } else {
                                                strDecode8 = NPStringFog.decode("B5D6B6C2B5C3");
                                                iM503 = C0053.m562(strDecode8);
                                            }
                                        case 1748648:
                                            if (C0001.f1 <= 0) {
                                                break;
                                            }
                                            strDecode9 = NPStringFog.decode("B5D1B6C9B5C9");
                                            str = strM310;
                                            iM503 = C0001.m190(strDecode9);
                                            break;
                                        case 1748707:
                                        case 1752518:
                                            if ((C0053.f92 | (C0052.f91 * 8062)) >= 0) {
                                                C0002.m259();
                                                iM503 = C0052.m503(NPStringFog.decode("B5EFB6C7B5C2"));
                                            } else {
                                                iM503 = (C0001.f1 * C0053.f92) + 2037867;
                                            }
                                        case 1748897:
                                            break;
                                        case 1749855:
                                            strM310 = Shell2Application.m310();
                                            strDecode9 = NPStringFog.decode("B5D1B6C1B5C6");
                                            iM503 = C0001.m190(strDecode9);
                                        case 1750629:
                                            if (C0053.f92 / (C0001.f1 + 3519) != 0) {
                                                strDecode8 = NPStringFog.decode("B5D5B6C2B5C1");
                                                iM503 = C0053.m562(strDecode8);
                                            } else {
                                                iM503 = C0053.m562(strDecode10);
                                            }
                                        case 1753541:
                                            if ((C0053.f92 ^ (C0002.f2 | 4694)) <= 0) {
                                                C0053.f92 = 81;
                                                strDecode9 = NPStringFog.decode("B5D1B6C1B5C6");
                                                iM503 = C0001.m190(strDecode9);
                                            } else {
                                                strDecode9 = NPStringFog.decode("B5D8B6C4B5C4");
                                                iM503 = C0001.m190(strDecode9);
                                            }
                                        case 1753600:
                                            if (C0043.m456() <= 0) {
                                                strDecode8 = NPStringFog.decode("B5D5B6C0B5C3");
                                                iM503 = C0053.m562(strDecode8);
                                            } else {
                                                strDecode9 = NPStringFog.decode("B5D1B6C3B5C5");
                                                iM503 = C0001.m190(strDecode9);
                                            }
                                        case 1755438:
                                            if (C0043.f73 - (C0052.f91 % (-122)) < 0) {
                                            }
                                            break;
                                        case 1755528:
                                            iM503 = (C0053.f92 - C0052.f91) ^ (-1753228);
                                            str = null;
                                    }
                                    return str;
                                }
                                iM503 = C0052.m503(NPStringFog.decode("B5D6B6C4B5FE"));
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000b. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:42:0x0056 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:44:0x0048 A[SYNTHETIC] */
                        @Override // com.swift.sandhook.SandHookConfig.LibLoader
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void loadLib() throws NumberFormatException {
                            String strDecode8;
                            String strDecode9;
                            int i7;
                            int i8;
                            int iM503 = C0052.m503(NPStringFog.decode("B5D5B6C2B5C1"));
                            Integer numValueOf = null;
                            while (true) {
                                switch (iM503) {
                                    case 56417:
                                        break;
                                    case 56419:
                                        System.out.println(numValueOf);
                                        if ((C0052.f91 ^ (C0043.f73 - 6348)) <= 0) {
                                            C0002.f2 = 76;
                                            strDecode8 = NPStringFog.decode("B5D5B6C4B5FE");
                                        } else {
                                            strDecode8 = NPStringFog.decode("B5D3B6C5");
                                        }
                                        iM503 = C0001.m190(strDecode8);
                                    case 1751709:
                                        numValueOf = Integer.valueOf(C0002.m256(NPStringFog.decode("24272F132F0F2522103C46")));
                                        iM503 = (C0053.f92 * C0002.f2) - 5445;
                                    case 1752578:
                                        m331(m332());
                                        iM503 = C0043.m455(NPStringFog.decode("B5D6B6C9B5C4"));
                                    case 1752639:
                                        if (C0052.m520() > 0) {
                                            C0053.m574();
                                            strDecode9 = NPStringFog.decode("B5D6B6C5B5C9");
                                            iM503 = C0053.m562(strDecode9);
                                        } else {
                                            i7 = C0043.f73 + C0043.f73;
                                            i8 = 56751;
                                            iM503 = i7 + i8;
                                        }
                                    case 1752707:
                                        if (C0052.f91 % (C0053.f92 - 7749) >= 0) {
                                            strDecode9 = NPStringFog.decode("B5D5B6C1B5C5");
                                            iM503 = C0053.m562(strDecode9);
                                        } else {
                                            i7 = C0002.f2 + C0002.f2;
                                            i8 = 1752730;
                                            iM503 = i7 + i8;
                                        }
                                    case 1753699:
                                        if (C0043.m456() <= 0) {
                                            if (C0002.m259() >= 0) {
                                                C0052.m520();
                                                iM503 = C0043.m455(NPStringFog.decode("B5D6B6C9B5C4"));
                                            } else {
                                                iM503 = (C0052.f91 | C0002.f2) ^ (-1751709);
                                            }
                                        } else if (C0052.m520() > 0) {
                                        }
                                        break;
                                }
                                return;
                            }
                        }
                    };
                    strDecode7 = NPStringFog.decode("B5D7B6C9B5C7");
                    iM562 = C0053.m562(strDecode7);
                case 1749727:
                    m303(m310());
                    strDecode3 = NPStringFog.decode("B5D6B6FEB5C2");
                    iM562 = C0043.m455(strDecode3);
                case 1749791:
                    if (!C0053.m550()) {
                        if (C0052.f91 / (C0053.f92 ^ (-1631)) != 0) {
                        }
                    } else if (C0001.f1 * (C0043.f73 + 3215) <= 0) {
                        strDecode3 = NPStringFog.decode("B5D1B6C5B5C2");
                        iM562 = C0043.m455(strDecode3);
                    }
                    break;
                case 1750630:
                    C0052.m522(C0053.m577(m313(), 848, C0001.f1 ^ 367, 652));
                    iM562 = C0002.m259() >= 0 ? C0052.m503("ۧۢۢ") : C0043.m455(strDecode6);
                case 1750788:
                    m304();
                    strDecode2 = NPStringFog.decode("B5EFB6FEB5C5");
                    iM562 = C0053.m562(strDecode2);
                case 1751712:
                    m315();
                    if (C0001.f1 * (C0043.f73 % 5183) >= 0) {
                        C0053.f92 = 76;
                        strDecode3 = NPStringFog.decode("B5D6B6FEB5C2");
                        iM562 = C0043.m455(strDecode3);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D3B6C6B5C9");
                        iM562 = C0053.m562(strDecode2);
                    }
                case 1752555:
                    if (C0001.m192() < 0) {
                    }
                    break;
                case 1752704:
                    C0038.f65 = new Pine.InterfaceC0051() { // from class: np.protect.assets.Shell2Application.1
                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0014. Please report as an issue. */
                        {
                            String strDecode8 = NPStringFog.decode("B5EFB6C5B5FE");
                            int iM455 = C0043.m455(strDecode8);
                            Double dDecode = null;
                            while (true) {
                                String strDecode9 = NPStringFog.decode("B5D6B6C0B5C7");
                                switch (iM455) {
                                    case 1746842:
                                        if (C0052.m520() > 0) {
                                            strDecode9 = NPStringFog.decode("B5D1B6C1B5C3");
                                            iM455 = C0043.m455(strDecode9);
                                        } else if (C0002.f2 >= 0) {
                                            C0053.f92 = 55;
                                            iM455 = C0053.m562(strDecode9);
                                        } else {
                                            strDecode9 = NPStringFog.decode("B5D5B6C0B5C3");
                                            iM455 = C0043.m455(strDecode9);
                                        }
                                    case 1748643:
                                        break;
                                    case 1749795:
                                        if (C0053.f92 - (C0052.f91 * (-542)) >= 0) {
                                            strDecode9 = NPStringFog.decode("B5D8B6C0B5C1");
                                            iM455 = C0043.m455(strDecode9);
                                        } else {
                                            iM455 = (C0043.f73 % C0053.f92) ^ (-1746749);
                                        }
                                    case 1752518:
                                        dDecode = Double.decode(C0043.m453(NPStringFog.decode("38261C251F0437")));
                                        if (C0001.m192() >= 0) {
                                            C0052.f91 = 0;
                                            iM455 = C0053.m562(strDecode8);
                                        } else {
                                            iM455 = C0043.m455(strDecode9);
                                        }
                                    case 1753483:
                                        System.out.println(dDecode);
                                        if (C0053.f92 * C0001.f1 * (-2880) <= 0) {
                                            C0053.f92 = 12;
                                            iM455 = C0001.m190(NPStringFog.decode("B5D7B6C3B5C3"));
                                        } else {
                                            iM455 = (C0052.f91 * C0002.f2) + 1736331;
                                        }
                                    case 1754471:
                                        strDecode9 = NPStringFog.decode("B5D1B6C1B5C3");
                                        iM455 = C0043.m455(strDecode9);
                                }
                                return;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000c. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:39:0x0060 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:42:0x006d A[SYNTHETIC] */
                        /* renamed from: ۣ۟۠۟ۢ, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static void m329(Object obj) {
                            int i7;
                            int i8;
                            String strDecode8;
                            String strDecode9 = NPStringFog.decode("B5D0B6C1B5C1");
                            while (true) {
                                int iM230 = C0002.m230(strDecode9);
                                while (true) {
                                    switch (iM230) {
                                        case 56323:
                                            if (C0043.f73 >= 0) {
                                                C0043.f73 = 45;
                                                iM230 = C0002.m230(NPStringFog.decode("B5D4B6C9B5C0"));
                                            } else {
                                                i7 = C0043.f73 % C0053.f92;
                                                i8 = -1753754;
                                                iM230 = i7 ^ i8;
                                            }
                                        case 1747680:
                                            if (C0053.m574() < 0) {
                                                if ((C0053.f92 ^ (C0052.f91 / 2448)) >= 0) {
                                                    break;
                                                }
                                                iM230 = C0002.m230(NPStringFog.decode("B5D4B6C9B5C0"));
                                            } else if (C0052.f91 * (C0002.f2 | (-248)) > 0) {
                                                C0053.f92 = 36;
                                                iM230 = C0001.m190(NPStringFog.decode("B5D5B6C5B5C5"));
                                            } else {
                                                strDecode8 = NPStringFog.decode("B5D0B6C2");
                                                iM230 = C0052.m503(strDecode8);
                                            }
                                            break;
                                        case 1749732:
                                        case 1753663:
                                            if (C0043.f73 + (C0001.f1 - 5194) >= 0) {
                                                C0043.f73 = 36;
                                                strDecode8 = NPStringFog.decode("B5D8B6C7");
                                                iM230 = C0052.m503(strDecode8);
                                            } else {
                                                i7 = C0053.f92 % C0053.f92;
                                                i8 = 1755500;
                                                iM230 = i7 ^ i8;
                                            }
                                        case 1751686:
                                            iM230 = C0052.m503(strDecode9);
                                        case 1751773:
                                            Shell2Application.m287((String) obj);
                                            iM230 = (C0043.f73 * C0053.f92) + 1619562;
                                        case 1752707:
                                            if (C0052.f91 * (C0002.f2 | (-248)) > 0) {
                                            }
                                            break;
                                        case 1755500:
                                            break;
                                    }
                                    return;
                                }
                                C0001.f1 = 75;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:52:0x0067 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:59:0x0058 A[SYNTHETIC] */
                        /* renamed from: ۟ۡۥۥۨ, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static String m330() {
                            int i7;
                            int i8;
                            String strDecode8;
                            String strDecode9;
                            int i9;
                            int i10;
                            String strDecode10;
                            int iM455 = C0043.m455(NPStringFog.decode("B5D5B6C3B5C1"));
                            String strM310 = null;
                            String str = null;
                            while (true) {
                                switch (iM455) {
                                    case 56567:
                                    case 1747714:
                                        if (C0052.f91 >= 0) {
                                            C0043.m456();
                                            strDecode8 = NPStringFog.decode("B5EFB6C6B5C2");
                                        } else {
                                            i7 = C0002.f2 | C0052.f91;
                                            i8 = 1755586;
                                            iM455 = i7 + i8;
                                        }
                                    case 1746876:
                                        if (C0043.f73 >= 0) {
                                            C0001.m192();
                                            str = null;
                                            strDecode10 = NPStringFog.decode("B5D8B6C6B5FE");
                                            iM455 = C0053.m562(strDecode10);
                                        } else {
                                            iM455 = (C0052.f91 - C0053.f92) + 1753790;
                                            str = null;
                                        }
                                    case 1747873:
                                        if ((C0053.f92 ^ (C0001.f1 | 4664)) >= 0) {
                                            strDecode9 = NPStringFog.decode("B5D5B6C5B5C5");
                                            iM455 = C0001.m190(strDecode9);
                                        } else {
                                            i9 = C0002.f2 - C0053.f92;
                                            i10 = 1752833;
                                            iM455 = i9 ^ i10;
                                        }
                                    case 1747927:
                                        str = strM310;
                                        strDecode10 = NPStringFog.decode("B5D8B6C6B5FE");
                                        iM455 = C0053.m562(strDecode10);
                                    case 1748703:
                                        strDecode10 = NPStringFog.decode("B5EFB6C4B5C3");
                                        iM455 = C0053.m562(strDecode10);
                                    case 1751620:
                                        if (C0001.f1 > 0) {
                                            C0053.f92 = 59;
                                            strDecode9 = NPStringFog.decode("B5D2B6C7B5C9");
                                            iM455 = C0001.m190(strDecode9);
                                        } else {
                                            i9 = C0053.f92 % C0043.f73;
                                            i10 = -1748559;
                                            iM455 = i9 ^ i10;
                                        }
                                    case 1752547:
                                        if (C0002.m259() < 0) {
                                            i9 = C0001.f1 * C0043.f73;
                                            i10 = -1726910;
                                        } else if (C0001.f1 > 0) {
                                        }
                                        iM455 = i9 ^ i10;
                                        break;
                                    case 1752617:
                                        strM310 = Shell2Application.m310();
                                        int iM520 = C0052.m520();
                                        strDecode8 = NPStringFog.decode("B5D0B6C9B5FE");
                                        iM455 = iM520 <= 0 ? C0043.m455(strDecode8) : C0002.m230(strDecode8);
                                    case 1754442:
                                        if (C0002.f2 % (C0002.f2 ^ (-2446)) >= 0) {
                                            C0053.m574();
                                            strDecode9 = NPStringFog.decode("B5D1B6C3B5C1");
                                            iM455 = C0001.m190(strDecode9);
                                        } else {
                                            i7 = C0052.f91 | C0053.f92;
                                            i8 = 56601;
                                            iM455 = i7 + i8;
                                        }
                                    case 1755584:
                                        break;
                                }
                                return str;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0012. Please report as an issue. */
                        @Override // top.canyie.pine.Pine.InterfaceC0051
                        public void loadLib() throws NumberFormatException {
                            int i7;
                            int i8;
                            String strDecode8 = NPStringFog.decode("B5D5B6C9");
                            int iM1903 = C0001.m190(strDecode8);
                            long j = 0;
                            while (true) {
                                String strDecode9 = NPStringFog.decode("B5D4B6C0B5C3");
                                switch (iM1903) {
                                    case 56483:
                                        m329(m330());
                                        i7 = C0001.f1 | C0001.f1;
                                        i8 = 1746581;
                                        iM1903 = i7 + i8;
                                    case 1746936:
                                        if (C0052.m520() > 0) {
                                            i7 = C0053.f92 | C0002.f2;
                                            i8 = 1751628;
                                            iM1903 = i7 + i8;
                                        } else if (C0053.m574() >= 0) {
                                            C0043.f73 = 53;
                                            iM1903 = C0053.m562(strDecode8);
                                        } else {
                                            iM1903 = C0052.m503(strDecode9);
                                        }
                                    case 1749732:
                                        System.out.println(j);
                                        if (C0002.f2 + (C0001.f1 * (-8533)) >= 0) {
                                            C0001.f1 = 26;
                                            iM1903 = C0053.m562(strDecode9);
                                        } else {
                                            iM1903 = C0053.m562(NPStringFog.decode("B5D4B6C2B5C0"));
                                        }
                                    case 1751557:
                                        long j2 = Long.parseLong(C0002.m256(NPStringFog.decode("39185E000824520D2A")));
                                        if (C0043.m456() <= 0) {
                                            C0052.m520();
                                        }
                                        strDecode9 = NPStringFog.decode("B5D2B6C5B5C7");
                                        j = j2;
                                        iM1903 = C0052.m503(strDecode9);
                                    case 1751618:
                                        break;
                                    case 1753420:
                                        iM1903 = (C0052.f91 * C0053.f92) - 75385;
                                    case 1754503:
                                        i7 = C0053.f92 | C0002.f2;
                                        i8 = 1751628;
                                        iM1903 = i7 + i8;
                                }
                                return;
                            }
                        }
                    };
                    i3 = C0052.f91 - C0002.f2;
                    i4 = -1748854;
                    iM562 = i3 ^ i4;
                case 1753417:
                    if (C0052.f91 / (C0053.f92 ^ (-1631)) != 0) {
                    }
                    break;
                case 1753418:
                    strDecode5 = NPStringFog.decode("B5D4B6C7B5C0");
                    iM562 = C0043.m455(strDecode5);
                case 1753700:
                    break;
                case 1754471:
                    if (i5 == 2) {
                        strDecode3 = NPStringFog.decode("B5D1B6C6B5C6");
                        iM562 = C0043.m455(strDecode3);
                    } else if (C0002.m259() < 0) {
                        strDecode2 = NPStringFog.decode("B5D2B6C5B5C0");
                        iM562 = C0053.m562(strDecode2);
                    }
                case 1754661:
                    i3 = C0002.f2 - C0001.f1;
                    i4 = -1753037;
                    iM562 = i3 ^ i4;
                case 1755373:
                    System.out.println(dValueOf);
                    iM562 = C0002.f2 + (C0052.f91 ^ 8794) >= 0 ? C0001.m190(NPStringFog.decode("B5D1B6C1B5C5")) : C0002.m230(strDecode5);
            }
            return;
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private static Application m281(Object obj, Object obj2) {
        String strDecode;
        int i;
        int i2;
        int i3;
        int i4;
        int iM190 = C0001.m190(NPStringFog.decode("B5EFB6FEB5C3"));
        Application application = null;
        String strM567 = null;
        String strM557 = null;
        Class clsM565 = null;
        Class clsM231 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D5B6C7B5C4");
            switch (iM190) {
                case 1746690:
                    clsM565 = C0053.m565();
                    if (C0053.m574() >= 0) {
                        iM190 = C0053.m562(NPStringFog.decode("B5D2B6C0B5C9"));
                    } else {
                        strDecode = NPStringFog.decode("B5D2B6C4B5FE");
                        iM190 = C0001.m190(strDecode);
                    }
                case 1749641:
                    strM557 = C0053.m557();
                    iM190 = C0002.m230(NPStringFog.decode("B5D5B6FEB5C0"));
                case 1749756:
                    strM567 = C0053.m567();
                    iM190 = C0053.m562(NPStringFog.decode("B5D2B6C0B5C9"));
                case 1750624:
                    i = C0002.f2 / C0043.f73;
                    i2 = 1749756;
                    iM190 = i + i2;
                case 1750816:
                    if (C0001.f1 + (C0043.f73 % (-531)) <= 0) {
                        strDecode2 = NPStringFog.decode("B5D3B6C0B5C0");
                        iM190 = C0001.m190(strDecode2);
                    } else {
                        i3 = C0002.f2 | C0001.f1;
                        i4 = -1746699;
                        iM190 = i3 ^ i4;
                    }
                case 1752455:
                    clsM231 = C0002.m231(C0052.m537());
                    if (C0052.f91 + (C0043.f73 ^ 5388) >= 0) {
                        C0001.m192();
                        iM190 = C0002.m230(NPStringFog.decode("B5D5B6FEB5C0"));
                    } else {
                        i3 = C0043.f73 % C0043.f73;
                        i4 = 1753423;
                        iM190 = i3 ^ i4;
                    }
                case 1752676:
                    return application;
                case 1753423:
                    application = (Application) C0002.m233(strM567, obj, strM557, new Object[]{C0002.m251(false), null}, new Class[]{clsM565, clsM231});
                    if (C0001.f1 - (C0002.f2 * (-3924)) >= 0) {
                        iM190 = C0052.m503(strDecode2);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D7B6C2B5C9");
                        iM190 = C0001.m190(strDecode2);
                    }
                case 1753476:
                    if (C0053.f92 >= 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D8B6C0B5C3");
                        iM190 = C0001.m190(strDecode);
                    } else {
                        iM190 = C0001.m190(strDecode2);
                    }
                case 1754508:
                    try {
                        C0002.m246(C0002.m241(), C0052.m501(), obj2, application);
                        i = C0052.f91 - C0052.f91;
                        i2 = 1752676;
                        iM190 = i + i2;
                    } catch (ClassNotFoundException e) {
                        throw new NoClassDefFoundError(C0053.m551(e));
                    }
            }
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private static ApplicationInfo m282(Object obj) {
        return (ApplicationInfo) C0002.m250(C0002.m248(C0001.m211(C0001.m211(new StringBuilder(), C0002.m241()), C0001.m188())), obj, C0001.m196());
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0039 A[SYNTHETIC] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m283(String str, String str2) {
        int i;
        int i2;
        int iM562 = C0053.m562(NPStringFog.decode("B5D5B6C4B5FE"));
        String strM224 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D6B6FEB5C7");
            switch (iM562) {
                case 56288:
                    return str2;
                case 1752548:
                    try {
                        strM224 = C0002.m224(str2, str);
                        iM562 = C0052.f91 >= 0 ? C0043.m455(NPStringFog.decode("B5D5B6C3B5C0")) : C0001.m190(strDecode);
                    } catch (Exception unused) {
                        return C0053.m555();
                    }
                case 1752639:
                    if (C0053.m550()) {
                        i = C0043.f73 / C0053.f92;
                        i2 = 56288;
                        iM562 = i + i2;
                    } else if (C0043.f73 / (C0001.f1 + 9861) == 0) {
                        strDecode = NPStringFog.decode("B5D8B6C4B5C2");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        iM562 = (C0052.f91 % C0001.f1) ^ (-1752390);
                    }
                case 1752642:
                    i = C0043.f73 * C0001.f1;
                    i2 = 1811924;
                    iM562 = i + i2;
                case 1753421:
                    return strM224;
                case 1753478:
                    if (C0043.f73 / (C0001.f1 + 9861) == 0) {
                    }
                    break;
                case 1755492:
                    iM562 = C0052.m503(strDecode);
            }
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static String m284(String str, String str2, String str3) {
        String strDecode = NPStringFog.decode("B5D3B6C2B5C4");
        int iM190 = C0001.m190(strDecode);
        while (iM190 != 1750661) {
            if (iM190 == 1751592) {
                if (C0043.f73 + (C0002.f2 * 7579) >= 0) {
                    C0052.m520();
                    iM190 = C0053.m562(NPStringFog.decode("B5D6B6C5B5C5"));
                } else {
                    iM190 = C0053.m562(strDecode);
                }
            }
        }
        return C0002.m248(C0001.m211(C0001.m211(new StringBuilder(C0043.m454(m313(), 860, C0002.f2 ^ (-77), 2810)), str3), C0053.m564(C0053.m564(C0043.m454(m313(), 867, C0043.f73 ^ (-182), 3208), C0002.m242(m313(), 886, C0052.f91 ^ (-167), 2861), str), C0043.m454(m313(), 893, C0053.f92 ^ (-811), 2439), str2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x047e A[PHI: r9 r14 r17 r22
      0x047e: PHI (r9v8 java.lang.String) = (r9v1 java.lang.String), (r9v7 java.lang.String), (r9v1 java.lang.String) binds: [B:73:0x01d7, B:174:0x0478, B:146:0x03b3] A[DONT_GENERATE, DONT_INLINE]
      0x047e: PHI (r14v19 int) = (r14v2 int), (r14v10 int), (r14v1 int) binds: [B:73:0x01d7, B:174:0x0478, B:146:0x03b3] A[DONT_GENERATE, DONT_INLINE]
      0x047e: PHI (r17v23 java.lang.String) = (r17v2 java.lang.String), (r17v8 java.lang.String), (r17v25 java.lang.String) binds: [B:73:0x01d7, B:174:0x0478, B:146:0x03b3] A[DONT_GENERATE, DONT_INLINE]
      0x047e: PHI (r22v9 java.lang.String) = (r22v4 java.lang.String), (r22v8 java.lang.String), (r22v1 java.lang.String) binds: [B:73:0x01d7, B:174:0x0478, B:146:0x03b3] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m285(String[] strArr) {
        int i;
        int i2;
        int i3;
        String str;
        int i4;
        int i5;
        String strDecode;
        String strDecode2;
        String strDecode3;
        int i6;
        int i7;
        int i8;
        int i9;
        String strDecode4 = NPStringFog.decode("B5D4B6C3B5C4");
        int iM503 = C0052.m503(strDecode4);
        String str2 = null;
        String strM507 = null;
        List listM516 = null;
        String str3 = null;
        String[] strArrM194 = null;
        String strM577 = null;
        String strM5072 = null;
        String str4 = null;
        String strM5073 = null;
        String str5 = null;
        int iM546 = 0;
        int i10 = 0;
        int length = 0;
        int i11 = 0;
        boolean zM182 = false;
        while (true) {
            String strDecode5 = NPStringFog.decode("B5D3B6C7B5C6");
            String strDecode6 = NPStringFog.decode("B5D3B6C9B5C1");
            String strDecode7 = NPStringFog.decode("B5D5B6C7B5C5");
            String str6 = str2;
            String strDecode8 = NPStringFog.decode("B5D4B6C3B5C7");
            switch (iM503) {
                case 56296:
                    str = strDecode4;
                    strDecode7 = NPStringFog.decode("B5D0B6C9B5C4");
                    iM503 = C0001.m190(strDecode7);
                    strDecode4 = str;
                    str2 = str6;
                case 56415:
                    str = strDecode4;
                    if (C0002.f2 >= 0) {
                        C0052.f91 = 15;
                        strDecode8 = NPStringFog.decode("B5D4B6C0B5C4");
                        iM503 = C0002.m230(strDecode8);
                        strDecode4 = str;
                        str2 = str6;
                    } else {
                        iM503 = C0002.m230("ۧۧۤ");
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 56513:
                case 1748896:
                    str = strDecode4;
                    if (C0001.m192() >= 0) {
                        C0001.m192();
                        iM503 = C0001.m190(NPStringFog.decode("B5D1B6C7B5C0"));
                        strDecode4 = str;
                        str2 = str6;
                    } else {
                        i6 = C0043.f73 + C0001.f1;
                        i7 = 1749557;
                        iM503 = i7 ^ i6;
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 56539:
                    str = strDecode4;
                    if ((C0052.f91 | (C0001.f1 ^ (-5086))) >= 0) {
                        C0052.m520();
                        iM503 = C0053.m562(NPStringFog.decode("B5D2B6C5B5C6"));
                        strDecode4 = str;
                        str2 = str6;
                    } else {
                        i6 = C0052.f91 / C0002.f2;
                        i7 = 1754440;
                        iM503 = i7 ^ i6;
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 1746846:
                    str = strDecode4;
                    zM182 = C0001.m182(str6, strM507);
                    strM5073 = C0052.m507(m313(), 909, C0043.f73 ^ (-176), 1640);
                    if (C0052.f91 + (C0043.f73 | (-2411)) >= 0) {
                        C0043.f73 = 76;
                        strDecode6 = "ۢۦ۟";
                        iM503 = C0002.m230(strDecode6);
                        strDecode4 = str;
                        str2 = str6;
                    } else {
                        iM503 = 1753537 + (C0002.f2 ^ C0053.f92);
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 1746851:
                    str = strDecode4;
                    if (C0043.f73 >= 0) {
                        C0043.f73 = 55;
                        iM503 = C0043.m455(NPStringFog.decode("B5D3B6C2B5C0"));
                        strDecode4 = str;
                        str2 = str6;
                    } else {
                        i6 = C0043.f73 % C0002.f2;
                        i7 = -1748911;
                        iM503 = i7 ^ i6;
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 1747870:
                    str = strDecode4;
                    if (C0001.m197(listM516, str3)) {
                        iM503 = C0002.m230(strDecode6);
                    } else if (C0002.m259() < 0) {
                        C0043.f73 = 81;
                        iM503 = C0052.m503(NPStringFog.decode("B5D5B6C0B5C3"));
                    } else {
                        iM503 = C0001.m190(strDecode7);
                    }
                    strDecode4 = str;
                    str2 = str6;
                case 1747933:
                    String str7 = strArrM194[0];
                    String str8 = strDecode4;
                    strM507 = C0052.m507(m313(), 903, C0043.f73 ^ (-161), 2526);
                    iM503 = C0001.f1 - (C0053.f92 * 7237) <= 0 ? C0053.m562(NPStringFog.decode("B5D1B6C6B5C4")) : 1746846 + (C0002.f2 - C0002.f2);
                    strDecode4 = str8;
                    str2 = str7;
                case 1748710:
                case 1753699:
                    if ((C0053.f92 ^ (C0053.f92 | (-7949))) <= 0) {
                        C0001.f1 = 74;
                        iM503 = C0052.m503(NPStringFog.decode("B5D0B6C6"));
                        str2 = str6;
                    } else {
                        i8 = C0043.f73 * C0053.f92;
                        i9 = 1613701;
                        iM503 = i8 + i9;
                        str2 = str6;
                    }
                case 1748863:
                    return strM577;
                case 1749603:
                    iM503 = C0002.m230("ۥۧۤ");
                    str2 = str6;
                case 1749607:
                    return strM507;
                case 1749639:
                    if (i10 < length) {
                        str3 = strArrM194[i10];
                        i8 = C0052.f91 / C0053.f92;
                        i9 = 1747870;
                        iM503 = i8 + i9;
                        str2 = str6;
                    }
                    iM503 = C0002.m230("ۥۧۤ");
                    str2 = str6;
                case 1749641:
                    i = length;
                    if (i11 < 4) {
                        str4 = new String[]{strM5073, strM577, strM507, strM5072}[i11];
                        if (C0002.f2 % (C0053.f92 | (-1598)) >= 0) {
                            C0043.f73 = 53;
                            iM503 = C0052.m503(strDecode4);
                            str2 = str6;
                            length = i;
                        } else {
                            i2 = C0052.f91 - C0002.f2;
                            i3 = 1750688;
                        }
                    } else if (C0043.f73 * C0043.f73 * (-6768) < 0) {
                        C0002.f2 = 91;
                        strDecode = NPStringFog.decode("B5D1B6C1");
                        iM503 = C0002.m230(strDecode);
                        str2 = str6;
                        length = i;
                    } else {
                        i2 = C0053.f92 - C0001.f1;
                        i3 = 1750032;
                    }
                    iM503 = i2 + i3;
                    str2 = str6;
                    length = i;
                case 1749787:
                    i = length;
                    if (!C0002.m255(strArrM194[0], strM5072)) {
                        str = strDecode4;
                        length = i;
                        if (C0002.f2 >= 0) {
                        }
                    } else if (C0053.f92 >= 0) {
                        iM503 = C0001.m190(NPStringFog.decode("B5D7B6C1B5C1"));
                        str2 = str6;
                        length = i;
                    } else {
                        i2 = C0053.f92 % C0002.f2;
                        i3 = 1750653;
                        iM503 = i2 + i3;
                        str2 = str6;
                        length = i;
                    }
                    break;
                case 1750599:
                    return strM5072;
                case 1750602:
                    i = length;
                    if (C0002.m222(C0053.m547(), str4)) {
                        if (C0052.f91 - (C0052.f91 - 6842) > 0) {
                            strDecode = NPStringFog.decode("B5D3B6C2B5C9");
                            iM503 = C0002.m230(strDecode);
                            str2 = str6;
                            length = i;
                        } else {
                            i4 = C0053.f92 * C0052.f91;
                            i5 = 1623802;
                            iM503 = i4 ^ i5;
                            str2 = str6;
                            length = i;
                        }
                    } else if (C0001.f1 / (C0001.f1 - 7883) != 0) {
                        iM503 = C0053.m562("ۥۧۤ");
                        str2 = str6;
                        length = i;
                    } else {
                        strDecode7 = NPStringFog.decode("B5D5B6C4B5C9");
                        str = strDecode4;
                        length = i;
                        iM503 = C0001.m190(strDecode7);
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 1750657:
                    i = length;
                    if ((C0052.f91 ^ (C0052.f91 % (-7345))) != 0) {
                        strDecode = NPStringFog.decode("B5D4B6FEB5FE");
                        i10 = 0;
                        iM503 = C0002.m230(strDecode);
                        str2 = str6;
                        length = i;
                    } else {
                        iM503 = (C0001.f1 | C0002.f2) + 1749648;
                        str2 = str6;
                        length = i;
                        i10 = 0;
                    }
                case 1750756:
                    i = length;
                    iM503 = (C0043.f73 ^ (C0001.f1 / (-9814))) >= 0 ? C0043.m455(strDecode6) : C0001.m190("ۡۢۧ");
                    str2 = str6;
                    length = i;
                case 1750811:
                    return str3;
                case 1751492:
                    i = length;
                    str = strDecode4;
                    length = i;
                    iM503 = C0002.m230(strDecode8);
                    strDecode4 = str;
                    str2 = str6;
                case 1751591:
                    i = length;
                    listM516 = C0052.m516(strArr);
                    strArrM194 = C0001.m194();
                    if ((C0052.f91 ^ (C0001.f1 ^ 4560)) >= 0) {
                        C0052.f91 = 23;
                        strDecode3 = NPStringFog.decode("B5D6B6C6");
                        iM503 = C0052.m503(strDecode3);
                        str2 = str6;
                        length = i;
                    } else {
                        i2 = C0053.f92 - C0002.f2;
                        i3 = 1754185;
                        iM503 = i2 + i3;
                        str2 = str6;
                        length = i;
                    }
                case 1751592:
                    i = length;
                    i11 += C0043.f73 ^ (-168);
                    if (C0002.f2 >= 0) {
                        C0053.f92 = 26;
                        iM503 = C0052.m503(strDecode8);
                        str2 = str6;
                        length = i;
                    } else {
                        i4 = C0002.f2 % C0043.f73;
                        i5 = -1746921;
                        iM503 = i4 ^ i5;
                        str2 = str6;
                        length = i;
                    }
                case 1751655:
                    i = length;
                    if (C0052.f91 + (C0001.f1 - 1773) >= 0) {
                        C0001.f1 = 29;
                        strDecode7 = NPStringFog.decode("B5D8B6C4B5C9");
                        str = strDecode4;
                        length = i;
                        iM503 = C0001.m190(strDecode7);
                        strDecode4 = str;
                        str2 = str6;
                    } else {
                        i4 = C0002.f2 - C0002.f2;
                        i5 = 1751591;
                        iM503 = i4 ^ i5;
                        str2 = str6;
                        length = i;
                    }
                case 1752648:
                    i = length;
                    if (C0002.m222(C0053.m578(), str4)) {
                        if (C0053.m574() >= 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D8B6C3B5C5");
                        } else {
                            strDecode = NPStringFog.decode("B5D7B6C7B5C4");
                        }
                        iM503 = C0002.m230(strDecode);
                        str2 = str6;
                        length = i;
                    } else {
                        str = strDecode4;
                        length = i;
                        iM503 = C0002.m230(strDecode8);
                        strDecode4 = str;
                        str2 = str6;
                    }
                case 1752675:
                    i = length;
                    i10 += C0002.f2 ^ (-75);
                    if ((C0052.f91 ^ (C0002.f2 / 5131)) >= 0) {
                        strDecode5 = strDecode7;
                    }
                    iM503 = C0001.m190(strDecode5);
                    str2 = str6;
                    length = i;
                case 1752706:
                    int i12 = length;
                    if (C0052.f91 - (C0043.f73 * (-4286)) >= 0) {
                        C0043.m456();
                        iM503 = C0052.m503(NPStringFog.decode("B5D7B6C7B5C5"));
                    } else {
                        iM503 = (C0053.f92 | C0053.f92) ^ (-1749413);
                    }
                    str2 = str6;
                    length = i12;
                    i11 = 0;
                case 1753447:
                    i = length;
                    str5 = strArrM194[0];
                    if (C0053.m574() >= 0) {
                        C0052.m520();
                        iM503 = C0053.m562("ۡۢۧ");
                        str2 = str6;
                        length = i;
                    } else {
                        i2 = C0002.f2 * C0052.f91;
                        i3 = 1742314;
                        iM503 = i2 + i3;
                        str2 = str6;
                        length = i;
                    }
                case 1753454:
                    i = length;
                    if (C0001.m197(listM516, strM5073)) {
                        i4 = C0053.f92 - C0002.f2;
                        i5 = -1749127;
                        iM503 = i4 ^ i5;
                        str2 = str6;
                        length = i;
                    } else {
                        str = strDecode4;
                        length = i;
                        if ((C0052.f91 | (C0001.f1 ^ (-5086))) >= 0) {
                        }
                    }
                    break;
                case 1753578:
                    return str4;
                case 1753696:
                    return strM5072;
                case 1754407:
                    i = length;
                    if (!zM182 || C0001.m197(listM516, strM5072)) {
                        str = strDecode4;
                        length = i;
                        if ((C0052.f91 | (C0001.f1 ^ (-5086))) >= 0) {
                        }
                    } else {
                        if (C0002.m259() >= 0) {
                            C0052.f91 = 80;
                            iM503 = C0001.m190("ۧۧۤ");
                        } else {
                            iM503 = C0053.m562(NPStringFog.decode("B5D6B6C1B5C9"));
                        }
                        str2 = str6;
                        length = i;
                    }
                    break;
                case 1754442:
                    i = length;
                    iM546 = C0053.m546(listM516);
                    strM577 = C0053.m577(m313(), 918, C0052.f91 ^ (-171), 575);
                    if (C0052.f91 / (C0002.f2 * 885) != 0) {
                        iM503 = C0053.m562(strDecode5);
                        str2 = str6;
                        length = i;
                    } else {
                        i2 = C0002.f2 + C0043.f73;
                        i3 = 1754840;
                        iM503 = i2 + i3;
                        str2 = str6;
                        length = i;
                    }
                case 1754597:
                    i = length;
                    if (iM546 != 1 || !C0001.m197(listM516, strM577)) {
                        str = strDecode4;
                        length = i;
                        if (C0002.f2 >= 0) {
                        }
                    } else if (C0052.f91 >= 0) {
                        strDecode2 = NPStringFog.decode("B5D4B6C5B5C6");
                        iM503 = C0043.m455(strDecode2);
                        str2 = str6;
                        length = i;
                    } else {
                        strDecode6 = "ۢۦ۟";
                        str2 = str6;
                        length = i;
                    }
                    break;
                case 1754598:
                    i = length;
                    if (!C0001.m197(listM516, str4)) {
                        str = strDecode4;
                        length = i;
                        iM503 = C0002.m230(strDecode8);
                        strDecode4 = str;
                        str2 = str6;
                    } else if (C0053.f92 >= 0) {
                        C0052.f91 = 74;
                        strDecode2 = NPStringFog.decode("B5D1B6C9B5C6");
                        iM503 = C0043.m455(strDecode2);
                        str2 = str6;
                        length = i;
                    } else {
                        i2 = C0001.f1 + C0052.f91;
                        i3 = 1753385;
                        iM503 = i2 + i3;
                        str2 = str6;
                        length = i;
                    }
                case 1754626:
                    i = length;
                    strM5072 = C0052.m507(m313(), 900, C0043.f73 ^ (-166), 2178);
                    if (!C0001.m182(str5, strM5072)) {
                        str = strDecode4;
                        length = i;
                        strDecode7 = NPStringFog.decode("B5D0B6C9B5C4");
                        iM503 = C0001.m190(strDecode7);
                        strDecode4 = str;
                        str2 = str6;
                    } else if (C0052.f91 >= 0) {
                        strDecode3 = NPStringFog.decode("B5D7B6C3");
                        iM503 = C0052.m503(strDecode3);
                        str2 = str6;
                        length = i;
                    } else {
                        i4 = C0043.f73 * C0001.f1;
                        i5 = -1713653;
                        iM503 = i4 ^ i5;
                        str2 = str6;
                        length = i;
                    }
                case 1754628:
                    length = strArrM194.length;
                    i8 = C0043.f73 ^ C0001.f1;
                    i9 = 1751111;
                    iM503 = i8 + i9;
                    str2 = str6;
                case 1754659:
                    str = strDecode4;
                    if (C0002.m259() < 0) {
                    }
                    strDecode4 = str;
                    str2 = str6;
                    break;
                case 1755372:
                    i = length;
                    if (C0052.f91 - (C0052.f91 - 6842) > 0) {
                    }
                    break;
                case 1755434:
                    i = length;
                    if (C0043.f73 * C0043.f73 * (-6768) < 0) {
                    }
                    break;
                default:
                    str2 = str6;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x007d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0088 A[SYNTHETIC] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected static void m286(Context context, Application application) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D4B6C0B5C5");
        int iM455 = C0043.m455(strDecode3);
        Class clsM231 = null;
        Method methodM189 = null;
        Class clsM2312 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D0B6C9B5C9");
            switch (iM455) {
                case 1746819:
                    clsM2312 = C0002.m231(C0053.m580());
                    if ((C0043.f73 ^ (C0052.f91 / 9098)) >= 0) {
                        C0043.f73 = 46;
                        strDecode = NPStringFog.decode("B5D6B6C0B5C6");
                        iM455 = C0043.m455(strDecode);
                    } else {
                        i = C0052.f91 | C0002.f2;
                        i2 = 1746822;
                        iM455 = i + i2;
                    }
                case 1746820:
                    methodM189 = C0001.m189(clsM231, C0053.m560(), new Class[]{clsM2312});
                    strDecode2 = C0052.m520() <= 0 ? NPStringFog.decode("B5EFB6C9B5C1") : NPStringFog.decode("B5D0B6C0B5C4");
                    iM455 = C0002.m230(strDecode2);
                case 1746967:
                    i = C0053.f92 % C0052.f91;
                    i2 = 1751563;
                    iM455 = i + i2;
                case 1747716:
                    C0053.m549(methodM189, true);
                    if (C0001.f1 + (C0002.f2 | 1443) > 0) {
                        strDecode4 = NPStringFog.decode("B5D8B6FEB5C0");
                    }
                case 1747936:
                    clsM231 = C0002.m231(C0002.m237());
                    i = C0001.f1 | C0002.f2;
                    i2 = 1746828;
                    iM455 = i + i2;
                case 1751559:
                    if (application != null) {
                        iM455 = C0002.f2 + (C0053.f92 / (-7513)) >= 0 ? C0002.m230(strDecode3) : C0053.m562(strDecode4);
                    } else if (C0001.f1 - (C0043.f73 - 8566) > 0) {
                        strDecode = NPStringFog.decode("B5D2B6C1");
                        iM455 = C0043.m455(strDecode);
                    } else {
                        i = C0052.f91 - C0001.f1;
                        i2 = 1756071;
                        iM455 = i + i2;
                    }
                case 1751562:
                    if (C0052.f91 - (C0002.f2 % 4555) >= 0) {
                        C0053.f92 = 44;
                        strDecode2 = NPStringFog.decode("B5D4B6C0B5C6");
                        iM455 = C0002.m230(strDecode2);
                    } else {
                        strDecode = NPStringFog.decode("B5D4B6C5B5C4");
                        iM455 = C0043.m455(strDecode);
                    }
                case 1751653:
                case 1755616:
                    if (C0001.f1 - (C0043.f73 - 8566) > 0) {
                    }
                    break;
                case 1753484:
                    if (C0002.f2 >= 0) {
                        C0002.m259();
                        strDecode = NPStringFog.decode("B5D2B6C5B5C1");
                        iM455 = C0043.m455(strDecode);
                    } else {
                        i = C0052.f91 ^ C0052.f91;
                        i2 = 1751562;
                        iM455 = i + i2;
                    }
                case 1754627:
                    iM455 = C0001.m190(strDecode4);
                case 1755338:
                    try {
                        C0002.m235(methodM189, application, new Object[]{context});
                        i = C0053.f92 / C0052.f91;
                        i2 = 1751557;
                        iM455 = i + i2;
                    } catch (ClassNotFoundException e) {
                        throw new NoClassDefFoundError(C0053.m551(e));
                    } catch (Throwable th) {
                        throw new IllegalStateException(th);
                    }
                case 1755554:
                    return;
            }
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    static /* synthetic */ void m287(String str) {
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D5B6C6B5C3");
        int iM190 = C0001.m190(strDecode2);
        while (iM190 != 1747653) {
            if (iM190 == 1752704) {
                m303(str);
                if (C0002.f2 >= 0) {
                    C0001.m192();
                    strDecode = NPStringFog.decode("B5D7B6C3B5C0");
                } else {
                    strDecode = NPStringFog.decode("B5D0B6FEB5C5");
                }
            } else if (iM190 == 1754470) {
                if (C0002.f2 % (C0052.f91 ^ (-1587)) >= 0) {
                    strDecode = NPStringFog.decode("B5D7B6C6B5C6");
                } else {
                    iM190 = C0043.m455(strDecode2);
                }
            }
            iM190 = C0053.m562(strDecode);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:165:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x066e  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x011a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0246 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0257 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x010e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x029e  */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m288(String str, String str2, String str3, String[] strArr) {
        File file;
        int i;
        int i2;
        File file2;
        String str4;
        String strDecode;
        String strDecode2;
        int i3;
        int i4;
        int i5;
        int i6;
        String str5;
        int i7;
        int i8;
        int i9;
        int i10;
        String strDecode3;
        int i11;
        String strDecode4;
        int i12;
        int i13;
        String strDecode5;
        String str6 = str3;
        int iM190 = C0001.m190(NPStringFog.decode("B5D7B6C9B5C1"));
        File file3 = null;
        String str7 = null;
        String strM513 = null;
        InputStream inputStreamM573 = null;
        FileOutputStream fileOutputStream = null;
        File file4 = null;
        File file5 = null;
        String[] strArr2 = null;
        int i14 = 0;
        int i15 = 0;
        int length = 0;
        boolean z = false;
        int i16 = 0;
        int i17 = 0;
        boolean z2 = false;
        while (true) {
            String strDecode6 = NPStringFog.decode("B5D0B6C6B5C9");
            String strDecode7 = "ۥۢۢ";
            String strDecode8 = NPStringFog.decode("B5D2B6C2B5C5");
            String strDecode9 = NPStringFog.decode("B5EFB6C2");
            boolean z3 = z;
            switch (iM190) {
                case 56292:
                    i5 = i14;
                    file2 = file4;
                    i6 = length;
                    str5 = str6;
                    if (i15 < i6) {
                        str7 = strArr[i15];
                        file5 = new File(file3, strArr2[i15]);
                        if (C0053.m574() >= 0) {
                            iM190 = C0053.m562(strDecode6);
                            str6 = str5;
                            length = i6;
                            i14 = i5;
                            z = z3;
                            file4 = file2;
                        } else {
                            i7 = C0043.f73 ^ C0002.f2;
                            i8 = 1747663;
                            iM190 = i7 + i8;
                            str6 = str5;
                            length = i6;
                            i14 = i5;
                            z = z3;
                            file4 = file2;
                        }
                    } else if (C0052.f91 * (C0052.f91 - 2623) > 0) {
                        C0002.m259();
                        iM190 = C0002.m230(NPStringFog.decode("B5EFB6C2B5C9"));
                        str6 = str5;
                        length = i6;
                        i14 = i5;
                        z = z3;
                        file4 = file2;
                    } else {
                        i7 = C0052.f91 % C0052.f91;
                        i8 = 1747780;
                        iM190 = i7 + i8;
                        str6 = str5;
                        length = i6;
                        i14 = i5;
                        z = z3;
                        file4 = file2;
                    }
                case 56477:
                case 56512:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    if (C0052.f91 < 0) {
                        C0001.m192();
                        iM190 = C0002.m230(NPStringFog.decode("B5D2B6C0B5C4"));
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        i9 = C0052.f91 * C0043.f73;
                        i10 = 1724662;
                        iM190 = i9 + i10;
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 56507:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    i9 = C0053.f92 % C0053.f92;
                    i10 = 1755461;
                    iM190 = i9 + i10;
                    str6 = str4;
                    i14 = i2;
                    z = z3;
                    length = i;
                    file4 = file2;
                case 56545:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    if (C0053.f92 % (C0001.f1 + 2894) >= 0) {
                        iM190 = C0001.m190(strDecode8);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        i9 = C0001.f1 ^ C0001.f1;
                        i10 = 1751591;
                        iM190 = i9 + i10;
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1746693:
                    iM190 = (C0043.f73 * C0052.f91) ^ 1761162;
                    str6 = str6;
                    i14 = i14;
                    z = z3;
                    length = length;
                    file4 = file4;
                    z2 = true;
                case 1746694:
                case 1750816:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    i9 = C0052.f91 ^ C0002.f2;
                    i10 = 1755258;
                    iM190 = i9 + i10;
                    str6 = str4;
                    i14 = i2;
                    z = z3;
                    length = i;
                    file4 = file2;
                case 1746789:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    i9 = C0052.f91 * C0001.f1;
                    i10 = 1812165;
                    iM190 = i9 + i10;
                    str6 = str4;
                    i14 = i2;
                    z = z3;
                    length = i;
                    file4 = file2;
                case 1746815:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    if (!z3) {
                        C0002.m218(file3);
                        i15 = 0;
                        iM190 = C0043.m455(NPStringFog.decode("B5D0B6C0B5C1"));
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                    i15 = 0;
                    if (C0002.f2 + (C0002.f2 | (-3295)) >= 0) {
                        C0001.m192();
                        strDecode7 = NPStringFog.decode("B5D0B6FEB5C9");
                        iM190 = C0002.m230(strDecode7);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                    iM190 = C0002.m230(strDecode9);
                    str6 = str4;
                    i14 = i2;
                    z = z3;
                    length = i;
                    file4 = file2;
                case 1746818:
                case 1747936:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    if (C0002.f2 + (C0001.f1 - 9631) >= 0) {
                        C0002.m259();
                        strDecode9 = NPStringFog.decode("B5D5B6C0B5C7");
                        iM190 = C0002.m230(strDecode9);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        iM190 = (C0052.f91 + C0043.f73) ^ (-1755331);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1747711:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    if (C0001.m200(file3)) {
                        i15 = 0;
                        if (C0002.f2 + (C0002.f2 | (-3295)) >= 0) {
                        }
                        iM190 = C0002.m230(strDecode9);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        C0001.m201(file3);
                        if (C0001.f1 <= 0) {
                            i15 = 0;
                            iM190 = C0053.m562(NPStringFog.decode("B5D3B6C0B5C0"));
                            str6 = str4;
                            i14 = i2;
                            z = z3;
                            length = i;
                            file4 = file2;
                        } else {
                            iM190 = (C0053.f92 | C0002.f2) ^ (-1749613);
                            str6 = str4;
                            i14 = i2;
                            z = z3;
                            length = i;
                            file4 = file2;
                            i15 = 0;
                        }
                    }
                    break;
                case 1747780:
                    return;
                case 1747867:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str6;
                    if (C0002.f2 >= 0) {
                        C0043.f73 = 31;
                        strDecode9 = NPStringFog.decode("B5D4B6C0B5C7");
                        iM190 = C0052.m503(strDecode9);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        i9 = C0052.f91 ^ C0001.f1;
                        i10 = 1747269;
                        iM190 = i9 + i10;
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1747900:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str3;
                    if (!z3) {
                        strM513 = C0052.m513(str2, str7, str4);
                        if (!C0001.m200(file5)) {
                            if ((C0043.f73 ^ (C0052.f91 + 6588)) >= 0) {
                                C0053.f92 = 81;
                                iM190 = C0002.m230(strDecode9);
                                str6 = str4;
                                i14 = i2;
                                z = z3;
                                length = i;
                                file4 = file2;
                            }
                            iM190 = C0043.m455(strDecode6);
                            str6 = str4;
                            i14 = i2;
                            z = z3;
                            length = i;
                            file4 = file2;
                        }
                    }
                    if (C0052.f91 < 0) {
                    }
                    break;
                case 1747902:
                    i11 = i14;
                    i = length;
                    file3 = file3;
                    file4 = new File(file3, C0002.m248(C0001.m211(C0001.m211(new StringBuilder(), str7), C0002.m242(m313(), 935, C0002.f2 ^ (-79), 514))));
                    if ((C0001.f1 | (C0002.f2 - 3160)) >= 0) {
                        C0001.f1 = 83;
                        iM190 = C0043.m455(NPStringFog.decode("B5D4B6C3B5C5"));
                    } else {
                        iM190 = C0001.m190(strDecode7);
                    }
                    str6 = str3;
                    i14 = i11;
                    z = z3;
                    length = i;
                case 1747905:
                    i2 = i14;
                    file = file3;
                    file2 = file4;
                    i = length;
                    inputStreamM573 = C0053.m573(Shell2Application.class, C0002.m248(C0001.m211(new StringBuilder(C0043.m454(m313(), 934, 1, 707)), strM513)));
                    if (C0002.f2 / (C0052.f91 + 9480) != 0) {
                        iM190 = C0052.m503(NPStringFog.decode("B5D0B6C9B5C9"));
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file3 = file;
                        file4 = file2;
                    } else {
                        strDecode2 = NPStringFog.decode("B5D0B6C6B5C4");
                        iM190 = C0053.m562(strDecode2);
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file3 = file;
                        file4 = file2;
                    }
                case 1748676:
                    i2 = i14;
                    file = file3;
                    file2 = file4;
                    i = length;
                    if (C0052.f91 >= 0) {
                        strDecode2 = NPStringFog.decode("B5D8B6C2B5C1");
                        iM190 = C0053.m562(strDecode2);
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file3 = file;
                        file4 = file2;
                    } else {
                        strDecode3 = NPStringFog.decode("B5D3B6C9B5C4");
                        file3 = file;
                        iM190 = C0043.m455(strDecode3);
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1749601:
                case 1755461:
                    i2 = i14;
                    file2 = file4;
                    i = length;
                    str4 = str3;
                    if (C0002.f2 + (C0002.f2 | (-3295)) >= 0) {
                    }
                    iM190 = C0002.m230(strDecode9);
                    str6 = str4;
                    i14 = i2;
                    z = z3;
                    length = i;
                    file4 = file2;
                    break;
                case 1749605:
                    i2 = i14;
                    File file6 = file3;
                    file2 = file4;
                    i = length;
                    if (C0043.f73 / (C0043.f73 + 2419) != 0) {
                        C0043.f73 = 61;
                        iM190 = C0002.m230(NPStringFog.decode("B5EFB6FEB5C7"));
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file3 = file6;
                        file4 = file2;
                        i15 = 0;
                    } else {
                        str4 = str3;
                        file3 = file6;
                        i15 = 0;
                        iM190 = C0052.m503(strDecode9);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1749699:
                    i2 = i14;
                    file = file3;
                    file2 = file4;
                    i = length;
                    C0001.m195(inputStreamM573, fileOutputStream);
                    iM190 = (C0002.f2 * C0052.f91) ^ 1739353;
                    str6 = str3;
                    i14 = i2;
                    z = z3;
                    length = i;
                    file3 = file;
                    file4 = file2;
                case 1749822:
                    i = length;
                    i2 = i14;
                    file2 = file4;
                    file3 = new File(C0002.m248(C0001.m211(C0001.m211(C0001.m211(new StringBuilder(), str), C0053.m576()), C0053.m577(m313(), 930, C0002.f2 ^ (-80), 2322))));
                    if (C0043.f73 >= 0) {
                        C0001.f1 = 0;
                        strDecode3 = NPStringFog.decode("B5D4B6C7B5C7");
                        iM190 = C0043.m455(strDecode3);
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        iM190 = (C0053.f92 + C0052.f91) ^ (-1753515);
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1750532:
                    File file7 = file3;
                    int i18 = length;
                    iM190 = C0053.f92 * (C0002.f2 + 3790) >= 0 ? C0001.m190(NPStringFog.decode("B5EFB6C2B5C7")) : (C0053.f92 * C0052.f91) - 75323;
                    str6 = str3;
                    length = i18;
                    file3 = file7;
                    z = false;
                case 1750564:
                    iM190 = 1746808 + (C0052.f91 ^ C0043.f73);
                    str6 = str3;
                    z = z2;
                case 1750595:
                    file = file3;
                    i = length;
                    C0002.m232(file4, file5);
                    if (C0043.f73 % (C0052.f91 | (-774)) >= 0) {
                        iM190 = C0002.m230("۟۟ۥ");
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        strDecode4 = NPStringFog.decode("B5D3B6C5B5C9");
                        iM190 = C0002.m230(strDecode4);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    }
                case 1750599:
                    file = file3;
                    i = length;
                    if (C0001.f1 / (C0043.f73 + 179) <= 0) {
                        strDecode = NPStringFog.decode("B5D4B6C7B5C1");
                        iM190 = C0043.m455(strDecode);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        i2 = i14;
                        file2 = file4;
                        file3 = file;
                        str4 = str3;
                        iM190 = C0002.m230(strDecode7);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1750695:
                    iM190 = 43785 + (C0002.f2 * C0043.f73);
                    str6 = str3;
                    z = z3;
                case 1751523:
                    file = file3;
                    i = length;
                    if (C0043.f73 + (C0052.f91 * 1414) < 0) {
                        strDecode = NPStringFog.decode("B5D8B6C2B5C9");
                        iM190 = C0043.m455(strDecode);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        i12 = C0001.f1 / C0052.f91;
                        i13 = -1749824;
                        iM190 = i13 ^ i12;
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    }
                case 1751561:
                    file = file3;
                    i = length;
                    if (C0001.f1 <= 0) {
                        C0052.f91 = 70;
                        strDecode2 = NPStringFog.decode("B5D5B6C5B5C7");
                        i2 = i14;
                        file2 = file4;
                        iM190 = C0053.m562(strDecode2);
                        str6 = str3;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file3 = file;
                        file4 = file2;
                    } else {
                        i3 = C0043.f73 % C0052.f91;
                        i4 = 1750700;
                        iM190 = i4 + i3;
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    }
                case 1751590:
                    file = file3;
                    i = length;
                    if (C0043.f73 % (C0001.f1 * 4850) < 0) {
                        C0052.m520();
                        strDecode7 = NPStringFog.decode("B5D5B6FEB5C7");
                        i11 = i14;
                        file3 = file;
                        iM190 = C0001.m190(strDecode7);
                        str6 = str3;
                        i14 = i11;
                        z = z3;
                        length = i;
                    } else {
                        i3 = C0043.f73 / C0053.f92;
                        i4 = 1753451;
                        iM190 = i4 + i3;
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    }
                case 1751591:
                case 1755463:
                    file = file3;
                    i = length;
                    if (C0043.f73 * (C0043.f73 | (-8552)) <= 0) {
                        C0043.m456();
                        strDecode4 = NPStringFog.decode("B5D3B6C2B5C3");
                        iM190 = C0002.m230(strDecode4);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        i3 = C0053.f92 + C0043.f73;
                        i4 = 1747796;
                        iM190 = i4 + i3;
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    }
                case 1751617:
                    file = file3;
                    i = length;
                    C0002.m225(fileOutputStream);
                    if (C0001.m192() >= 0) {
                        C0043.f73 = 66;
                        strDecode = NPStringFog.decode("B5D2B6C1B5FE");
                        iM190 = C0043.m455(strDecode);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        i2 = i14;
                        file2 = file4;
                        file3 = file;
                        str4 = str3;
                        iM190 = C0053.m562(NPStringFog.decode("B5D3B6C0B5C0"));
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    }
                case 1751716:
                    file = file3;
                    i = length;
                    C0052.m500(C0002.m223(file5));
                    i15 += C0043.f73 ^ (-168);
                    if (C0052.f91 >= 0) {
                        C0043.f73 = 54;
                        iM190 = C0052.m503(NPStringFog.decode("B5D4B6C2B5C1"));
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        i12 = C0043.f73 - C0001.f1;
                        i13 = -57011;
                        iM190 = i13 ^ i12;
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    }
                case 1751771:
                    i5 = i14;
                    file2 = file4;
                    i6 = length;
                    str5 = str3;
                    if (C0052.f91 * (C0052.f91 - 2623) > 0) {
                    }
                    break;
                case 1752515:
                    file = file3;
                    i = length;
                    if (C0002.f2 / (C0043.f73 ^ 1724) != 0) {
                        C0052.f91 = 26;
                        strDecode6 = NPStringFog.decode("B5D6B6C7");
                        i2 = i14;
                        file2 = file4;
                        file3 = file;
                        str4 = str3;
                        iM190 = C0043.m455(strDecode6);
                        str6 = str4;
                        i14 = i2;
                        z = z3;
                        length = i;
                        file4 = file2;
                    } else {
                        strDecode7 = NPStringFog.decode("B5D8B6C4B5C4");
                        i11 = i14;
                        file3 = file;
                        iM190 = C0001.m190(strDecode7);
                        str6 = str3;
                        i14 = i11;
                        z = z3;
                        length = i;
                    }
                case 1752549:
                    file = file3;
                    i = length;
                    try {
                        fileOutputStream = new FileOutputStream(file4);
                        if (C0052.m520() <= 0) {
                            strDecode8 = NPStringFog.decode("B5D0B6C7B5C0");
                        }
                        iM190 = C0001.m190(strDecode8);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                case 1753451:
                    file = file3;
                    i = length;
                    i14 += C0053.f92 ^ (-813);
                    strDecode8 = NPStringFog.decode("B5D0B6C7B5C0");
                    iM190 = C0001.m190(strDecode8);
                    str6 = str3;
                    z = z3;
                    length = i;
                    file3 = file;
                case 1753701:
                    iM190 = 1755606 + (C0002.f2 | C0002.f2);
                    str6 = str3;
                    z = z3;
                    i14 = 0;
                case 1754567:
                    file = file3;
                    i = length;
                    if (C0053.m574() >= 0) {
                        C0053.m574();
                        iM190 = C0053.m562(NPStringFog.decode("B5D4B6C3B5C4"));
                    } else {
                        iM190 = (-1748678) ^ (C0053.f92 / C0001.f1);
                    }
                    str6 = str3;
                    i16 = i17;
                    z = z3;
                    length = i;
                    file3 = file;
                case 1754596:
                    file = file3;
                    i = length;
                    if (C0053.f92 % (C0053.f92 - 3547) < 0) {
                        strDecode5 = NPStringFog.decode("B5D5B6C7B5C0");
                        iM190 = C0002.m230(strDecode5);
                        str6 = str3;
                        z = z3;
                        length = i;
                        file3 = file;
                    } else {
                        strDecode7 = "۟۟ۥ";
                        file3 = file;
                        i11 = i14;
                        iM190 = C0001.m190(strDecode7);
                        str6 = str3;
                        i14 = i11;
                        z = z3;
                        length = i;
                    }
                case 1754655:
                    file = file3;
                    length = strArr.length;
                    strArr2 = new String[length];
                    if (C0002.f2 * (C0043.f73 - 4131) <= 0) {
                        C0002.m259();
                        iM190 = C0052.m503(NPStringFog.decode("B5D3B6C0B5C4"));
                    } else {
                        iM190 = 1752515 ^ (C0001.f1 - C0001.f1);
                    }
                    str6 = str3;
                    z = z3;
                    file3 = file;
                case 1755492:
                    if (i16 < strArr.length) {
                        i = length;
                        file = file3;
                        strArr2[i16] = C0002.m248(C0001.m211(C0001.m211(C0001.m211(new StringBuilder(), str6), C0002.m242(m313(), 929, 1, 1622)), strArr[i16]));
                        i17 = i16 + (C0001.f1 ^ 354);
                        if (C0002.f2 * (C0002.f2 - 3629) <= 0) {
                            i2 = i14;
                            file2 = file4;
                            file3 = file;
                            str4 = str3;
                            iM190 = C0043.m455(NPStringFog.decode("B5D0B6C0B5C1"));
                            str6 = str4;
                            i14 = i2;
                            z = z3;
                            length = i;
                            file4 = file2;
                        } else {
                            strDecode = NPStringFog.decode("B5D7B6C4B5C4");
                            iM190 = C0043.m455(strDecode);
                            str6 = str3;
                            z = z3;
                            length = i;
                            file3 = file;
                        }
                    } else {
                        file = file3;
                        i = length;
                        if (C0043.f73 + (C0052.f91 * 1414) < 0) {
                        }
                    }
                    break;
                case 1755528:
                    if ((C0001.f1 ^ (C0052.f91 + 2088)) <= 0) {
                        C0053.f92 = 25;
                    }
                    iM190 = C0043.m455(NPStringFog.decode("B5D8B6C5B5C1"));
                    z = z3;
                    i16 = 0;
                case 1755530:
                    if (i14 < length) {
                        if (!C0001.m200(new File(file3, strArr2[i14]))) {
                            strDecode5 = NPStringFog.decode("B5D3B6FEB5C1");
                            file = file3;
                            i = length;
                        }
                        file = file3;
                        i = length;
                        if (C0043.f73 % (C0001.f1 * 4850) < 0) {
                        }
                    } else {
                        file = file3;
                        i = length;
                        if (C0053.f92 % (C0053.f92 - 3547) < 0) {
                        }
                    }
                    iM190 = C0002.m230(strDecode5);
                    str6 = str3;
                    z = z3;
                    length = i;
                    file3 = file;
                    break;
                default:
                    i5 = i14;
                    file2 = file4;
                    i6 = length;
                    str5 = str6;
                    str6 = str5;
                    length = i6;
                    i14 = i5;
                    z = z3;
                    file4 = file2;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0014. Please report as an issue. */
    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    private Application m289(String str) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int iM190 = C0001.m190(NPStringFog.decode("B5D3B6C7B5C5"));
        Object objM319 = null;
        Object objM323 = null;
        Object objM325 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D0B6FEB5C4");
            switch (iM190) {
                case 56480:
                    i = C0001.f1 / C0053.f92;
                    i2 = 1750753;
                    iM190 = i + i2;
                case 56572:
                    m302(objM319).className = str;
                    int i3 = C0053.f92;
                    String strDecode4 = NPStringFog.decode("B5D2B6C9B5C9");
                    if (i3 >= 0) {
                        C0053.m574();
                    }
                    strDecode = strDecode4;
                    iM190 = C0053.m562(strDecode);
                case 1747654:
                    C0053.m561((ArrayList) C0002.m250(C0002.m241(), objM323, C0052.m523()), C0002.m250(C0002.m241(), objM323, C0052.m501()));
                    iM190 = C0001.m190(NPStringFog.decode("B5D8B6C5"));
                case 1748678:
                    objM325 = m325(objM323);
                    strDecode = NPStringFog.decode("B5D7B6C3B5C2");
                    iM190 = C0053.m562(strDecode);
                case 1749858:
                    m322(objM325).className = str;
                    strDecode2 = NPStringFog.decode("B5D8B6C7B5C1");
                    iM190 = C0052.m503(strDecode2);
                case 1750753:
                    objM323 = m323();
                    if (C0052.f91 >= 0) {
                        iM190 = C0043.m455(strDecode3);
                    } else {
                        i = C0052.f91 * C0052.f91;
                        i2 = 1722434;
                        iM190 = i + i2;
                    }
                case 1751592:
                    C0052.m526(C0053.m567(), objM319, C0001.m204(), null);
                    if (C0053.f92 >= 0) {
                        strDecode2 = NPStringFog.decode("B5D5B6C4");
                        iM190 = C0052.m503(strDecode2);
                    } else {
                        iM190 = C0052.m503(strDecode3);
                    }
                case 1754472:
                    objM319 = m319(objM325);
                    iM190 = C0002.m230(NPStringFog.decode("B5D4B6C3B5C7"));
                case 1755554:
                    break;
            }
            return m316(objM319, objM323);
        }
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    private static ApplicationInfo m290(Object obj) {
        return (ApplicationInfo) C0002.m250(C0053.m567(), obj, C0002.m249());
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    public static String m291() {
        return C0001.m191(C0001.m216(), m311());
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00db A[SYNTHETIC] */
    /* renamed from: ۟۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static File m292(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int iM230 = C0002.m230(NPStringFog.decode("B5D3B6C9B5C1"));
        File file = null;
        String strM221 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D5B6C4B5C7");
            switch (iM230) {
                case 1746695:
                    return new File(C0002.m248(C0001.m211(new StringBuilder(C0052.m507(m313(), 955, C0001.f1 ^ 360, 2996)), str)));
                case 1747807:
                    if (C0043.f73 % (C0053.f92 | (-3613)) >= 0) {
                        iM230 = C0043.m455(NPStringFog.decode("B5D4B6FEB5C5"));
                    } else {
                        i = C0002.f2 | C0052.f91;
                        i2 = 1750813;
                        iM230 = i + i2;
                    }
                case 1748769:
                    if (C0001.m203(file)) {
                        if (C0043.f73 >= 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D0B6C5B5C2");
                            iM230 = C0052.m503(strDecode);
                        } else {
                            i3 = C0053.f92 | C0001.f1;
                            i4 = -1753924;
                            iM230 = i3 ^ i4;
                        }
                    } else if (C0001.f1 <= 0) {
                        C0053.f92 = 52;
                        iM230 = C0001.m190(NPStringFog.decode("B5D4B6FEB5C6"));
                    } else {
                        i = C0052.f91 % C0052.f91;
                        i2 = 1746695;
                        iM230 = i + i2;
                    }
                case 1750780:
                    if (C0001.f1 <= 0) {
                    }
                    break;
                case 1750811:
                    strM221 = C0002.m221(C0053.m563());
                    if (C0002.f2 % (C0053.f92 * (-7597)) >= 0) {
                        C0001.m192();
                        iM230 = C0002.m230(NPStringFog.decode("B5D6B6FEB5C9"));
                    } else {
                        iM230 = C0052.m503(strDecode);
                    }
                case 1752646:
                    if (C0052.m512(strM221, C0052.m507(m313(), 940, C0001.f1 ^ 352, 1946))) {
                        if (C0043.f73 - (C0052.f91 - 2362) <= 0) {
                            C0053.m574();
                            iM230 = C0043.m455(strDecode);
                        } else {
                            iM230 = C0052.m503(NPStringFog.decode("B5D5B6C7B5C3"));
                        }
                    } else if (C0001.f1 <= 0) {
                    }
                    break;
                case 1752673:
                    file = new File(C0002.m248(C0001.m211(C0001.m211(C0001.m211(new StringBuilder(C0053.m577(m313(), 943, C0043.f73 ^ (-174), 385)), strM221), C0053.m577(m313(), 954, 1, 2104)), str)));
                    if (C0052.m520() <= 0) {
                        C0001.m192();
                        iM230 = C0052.m503(NPStringFog.decode("B5D5B6C7B5C3"));
                    } else {
                        i3 = C0053.f92 + C0001.f1;
                        i4 = -1748716;
                        iM230 = i3 ^ i4;
                    }
                case 1753423:
                    return file;
            }
        }
    }

    /* renamed from: ۟۟۟, reason: not valid java name and contains not printable characters */
    private static Object m293(Object obj) {
        return C0002.m250(C0002.m248(C0001.m211(C0001.m211(new StringBuilder(), C0002.m241()), C0001.m188())), obj, C0052.m519());
    }

    /* renamed from: ۟۟۟۟, reason: not valid java name and contains not printable characters */
    private static Object m295() {
        return C0001.m207(C0002.m241(), C0052.m535(), new Class[0], new Object[0]);
    }

    /* renamed from: ۟۟۟۟, reason: not valid java name and contains not printable characters */
    private static Object m296(Object obj) {
        return C0002.m250(C0002.m241(), obj, C0001.m202());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0135 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0141 A[SYNTHETIC] */
    /* renamed from: ۟۟۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m297(String str) {
        int i;
        int i2;
        String strDecode;
        String strM577;
        String strDecode2;
        int iM562 = C0053.m562(NPStringFog.decode("B5D0B6C0B5C0"));
        String str2 = null;
        String str3 = null;
        String strM242 = null;
        while (true) {
            String str4 = strM242;
            while (true) {
                switch (iM562) {
                    case 56352:
                        if (C0001.m182(str2, C0043.m454(m313(), 981, C0002.f2 ^ (-73), 1146))) {
                            if (C0052.f91 * (C0043.f73 + 4330) >= 0) {
                                C0001.f1 = 30;
                                String str5 = str3;
                                strDecode = NPStringFog.decode("B5D8B6C5B5C6");
                                strM577 = str5;
                                int iM190 = C0001.m190(strDecode);
                                str3 = strM577;
                                iM562 = iM190;
                            } else {
                                i = C0001.f1 * C0052.f91;
                                i2 = 1812885;
                                iM562 = i + i2;
                            }
                        } else if (C0001.m192() < 0) {
                            iM562 = C0002.m230(NPStringFog.decode("B5D4B6C5B5C0"));
                        } else {
                            i = C0052.f91 + C0001.f1;
                            i2 = 1755306;
                            iM562 = i + i2;
                        }
                    case 56536:
                        strM242 = C0002.m242(m313(), 972, C0001.f1 ^ 362, 1352);
                        strDecode2 = NPStringFog.decode("B5D5B6C9B5C0");
                        iM562 = C0052.m503(strDecode2);
                    case 1746943:
                        i = C0001.f1 - C0001.f1;
                        i2 = 1750812;
                        iM562 = i + i2;
                    case 1747712:
                        if (C0052.f91 >= 0) {
                            C0001.m192();
                            str2 = str;
                            strDecode2 = NPStringFog.decode("B5D7B6FE");
                            iM562 = C0052.m503(strDecode2);
                        } else {
                            iM562 = C0052.m503(NPStringFog.decode("B5D1B6FEB5C7"));
                            str2 = str;
                        }
                    case 1748616:
                        if (C0001.m182(str2, C0043.m454(m313(), 966, C0053.f92 ^ (-812), 1613))) {
                            if (C0053.m574() >= 0) {
                                C0043.f73 = 75;
                                strDecode2 = NPStringFog.decode("B5D3B6C1B5C1");
                            } else {
                                strDecode2 = NPStringFog.decode("B5D7B6FE");
                            }
                        } else if (C0043.f73 - (C0053.f92 | 4512) > 0) {
                            C0043.m456();
                            strDecode2 = NPStringFog.decode("B5D0B6C7B5C0");
                        } else {
                            i = C0053.f92 ^ C0043.f73;
                            i2 = 1749656;
                            iM562 = i + i2;
                        }
                        iM562 = C0052.m503(strDecode2);
                    case 1750563:
                        strDecode2 = NPStringFog.decode("B5D1B6C0");
                        str4 = str2;
                        iM562 = C0052.m503(strDecode2);
                    case 1750658:
                        iM562 = (C0001.f1 * C0053.f92) + 2044469;
                        str4 = str3;
                    case 1750812:
                    case 1751559:
                        if (C0001.m192() < 0) {
                        }
                        break;
                    case 1751492:
                        if (C0001.f1 / (C0052.f91 * (-1391)) != 0) {
                            strDecode2 = NPStringFog.decode("B5D1B6C7B5C1");
                            iM562 = C0052.m503(strDecode2);
                        } else {
                            i = C0002.f2 / C0052.f91;
                            i2 = 1747712;
                            iM562 = i + i2;
                        }
                    case 1752734:
                        break;
                    case 1753607:
                        if (C0043.f73 - (C0053.f92 | 4512) > 0) {
                        }
                        break;
                    case 1755375:
                        strM577 = C0053.m577(m313(), 984, C0053.f92 ^ (-807), 1948);
                        strDecode = NPStringFog.decode("B5D3B6C2B5C3");
                        int iM1902 = C0001.m190(strDecode);
                        str3 = strM577;
                        iM562 = iM1902;
                    case 1755499:
                        break;
                }
                return str4;
            }
            iM562 = C0001.m190(NPStringFog.decode("B5EFB6C6B5C6"));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0058 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x012c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0138 A[SYNTHETIC] */
    /* renamed from: ۟۟۟۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m298() throws NumberFormatException {
        int i;
        int i2;
        String strDecode;
        int i3;
        int i4;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D7B6C4B5C2");
        int iM190 = C0001.m190(strDecode3);
        float f = 0.0f;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5EFB6C1B5C2");
            switch (iM190) {
                case 56294:
                    iM190 = C0053.m562(NPStringFog.decode("B5D2B6C9"));
                    i6 = i5;
                case 56390:
                    hookMode = i6;
                    if (C0043.f73 / (C0043.f73 + 4370) != 0) {
                        C0052.f91 = 80;
                        strDecode = NPStringFog.decode("B5D1B6C2B5FE");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0052.f91 ^ C0001.f1;
                        i2 = -1752967;
                        iM190 = i ^ i2;
                    }
                case 1746720:
                case 1749854:
                case 1753478:
                    i3 = C0002.f2 * C0043.f73;
                    i4 = 43698;
                    iM190 = i3 + i4;
                case 1746722:
                    if (C0043.m456() <= 0) {
                        C0053.m574();
                        iM190 = C0052.m503(NPStringFog.decode("B5D5B6C4B5C5"));
                    } else {
                        iM190 = (C0053.f92 % C0052.f91) + 1751778;
                    }
                    i6 = 0;
                case 1746814:
                    f = Float.parseFloat(C0052.m511(NPStringFog.decode("22343E310C28")));
                    i = C0052.f91 % C0043.f73;
                    i2 = -1755209;
                    iM190 = i ^ i2;
                case 1746817:
                    iM190 = C0002.f2 >= 0 ? C0002.m230(NPStringFog.decode("B5D8B6C1B5C0")) : (C0052.f91 + C0043.f73) ^ (-1752014);
                    i6 = i7;
                case 1748672:
                    if (C0001.m206() > 34) {
                        strDecode = NPStringFog.decode("B5D3B6C0B5C6");
                    } else if ((C0053.f92 ^ (C0053.f92 | 4550)) > 0) {
                        strDecode = NPStringFog.decode("B5D0B6C2B5C4");
                    } else {
                        i3 = C0002.f2 * C0053.f92;
                        i4 = 1690811;
                        iM190 = i3 + i4;
                    }
                    iM190 = C0052.m503(strDecode);
                case 1748733:
                    if ((C0053.f92 ^ (C0053.f92 | 4550)) > 0) {
                    }
                    break;
                case 1749636:
                    if (C0053.m574() >= 0) {
                        strDecode = NPStringFog.decode("B5EFB6C2B5C3");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0043.f73 % C0002.f2;
                        i2 = -1749740;
                        iM190 = i ^ i2;
                    }
                case 1749727:
                    if (C0053.f92 >= 0) {
                        strDecode4 = NPStringFog.decode("B5EFB6C9B5C5");
                    } else {
                        i3 = C0052.f91 ^ C0043.f73;
                        i4 = 1754558;
                        iM190 = i3 + i4;
                    }
                case 1749733:
                    break;
                case 1750601:
                    i7 = 1;
                    if (C0001.f1 <= 0) {
                        C0001.f1 = 18;
                        iM190 = C0052.m503(strDecode3);
                    } else {
                        i3 = C0052.f91 % C0002.f2;
                        i4 = 1746827;
                        iM190 = i3 + i4;
                    }
                case 1751685:
                    i = C0001.f1 % C0001.f1;
                    i2 = 1746720;
                    iM190 = i ^ i2;
                case 1751774:
                    if (C0001.m192() >= 0) {
                        iM190 = C0043.m455(strDecode4);
                    } else {
                        i3 = C0052.f91 * C0002.f2;
                        i4 = 1741166;
                        iM190 = i3 + i4;
                    }
                case 1752489:
                    i5 = 2;
                    strDecode2 = NPStringFog.decode("B5EFB6C4");
                    iM190 = C0001.m190(strDecode2);
                case 1752644:
                    if ((C0052.f91 | (C0053.f92 * (-3539))) >= 0) {
                        iM190 = C0002.m230(NPStringFog.decode("B5D5B6C7B5C5"));
                    } else {
                        i3 = C0001.f1 % C0002.f2;
                        i4 = 1753496;
                        iM190 = i3 + i4;
                    }
                case 1752675:
                    if (C0002.f2 - (C0002.f2 | (-7707)) >= 0) {
                        C0001.m192();
                        strDecode4 = NPStringFog.decode("B5D8B6FEB5C0");
                    }
                case 1753547:
                case 1754655:
                    strDecode2 = NPStringFog.decode("B5D2B6C0B5C2");
                    iM190 = C0001.m190(strDecode2);
                case 1754439:
                    i = C0043.f73 % C0002.f2;
                    i2 = -1749740;
                    iM190 = i ^ i2;
                case 1754565:
                    if (C0001.m206() < 24) {
                        if ((C0052.f91 | (C0053.f92 * (-7121))) >= 0) {
                            strDecode2 = NPStringFog.decode("B5EFB6C4");
                            iM190 = C0001.m190(strDecode2);
                        } else {
                            i = C0053.f92 ^ C0043.f73;
                            i2 = 1752610;
                            iM190 = i ^ i2;
                        }
                    } else if (C0053.m574() < 0) {
                        strDecode2 = NPStringFog.decode("B5D4B6C6B5C9");
                        iM190 = C0001.m190(strDecode2);
                    } else {
                        i3 = C0053.f92 + C0043.f73;
                        i4 = 1749653;
                        iM190 = i3 + i4;
                    }
                case 1755338:
                    if (C0053.m574() < 0) {
                    }
                    break;
                case 1755369:
                    System.out.println(f);
                    int iM259 = C0002.m259();
                    strDecode4 = NPStringFog.decode("B5D2B6C5B5C6");
                    iM190 = iM259 >= 0 ? C0053.m562(strDecode4) : C0001.m190(strDecode4);
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00ca A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00c2 A[SYNTHETIC] */
    /* renamed from: ۟۟۟۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m299(String str) throws NumberFormatException {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3;
        String strDecode4 = NPStringFog.decode("B5D8B6C0B5C5");
        int iM503 = C0052.m503(strDecode4);
        Integer numValueOf = null;
        Throwable th = null;
        while (true) {
            String strDecode5 = NPStringFog.decode("B5D2B6C9B5C2");
            String strDecode6 = "ۣۨۨ";
            String strDecode7 = NPStringFog.decode("B5EFB6C4B5C0");
            String strDecode8 = NPStringFog.decode("B5D6B6C7B5C5");
            switch (iM503) {
                case 1746725:
                case 1748709:
                    i = C0002.f2 - C0043.f73;
                    i2 = 1753611;
                    iM503 = i + i2;
                case 1746875:
                    System.out.println(numValueOf);
                    if (C0043.f73 / (C0001.f1 | 2970) != 0) {
                        strDecode = NPStringFog.decode("B5D4B6C3B5C6");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        iM503 = C0043.m455(strDecode8);
                    }
                case 1748796:
                    iM503 = C0043.m455(strDecode8);
                case 1749793:
                    if (C0053.f92 % (C0052.f91 ^ (-5880)) >= 0) {
                        C0043.f73 = 94;
                        strDecode2 = NPStringFog.decode("B5D5B6C5B5C5");
                    } else {
                        strDecode2 = strDecode4;
                    }
                    iM503 = C0053.m562(strDecode2);
                case 1749853:
                    f10 = null;
                    strDecode2 = NPStringFog.decode("B5D4B6C1B5C2");
                    iM503 = C0053.m562(strDecode2);
                case 1750563:
                    numValueOf = Integer.valueOf(C0043.m453(NPStringFog.decode("1E05050907112D37041A1A232F193B")));
                    if (C0001.f1 * (C0043.f73 / (-1522)) != 0) {
                        strDecode7 = NPStringFog.decode("B5D3B6C1B5C1");
                    }
                    iM503 = C0052.m503(strDecode7);
                case 1750750:
                    m314();
                    if (C0001.f1 * (C0053.f92 + 3327) <= 0) {
                        C0052.m520();
                        iM503 = C0053.m562("ۣۨۨ");
                    } else {
                        strDecode6 = "ۨ۟ۨ";
                        iM503 = C0002.m230(strDecode6);
                    }
                case 1750754:
                    f8 = true;
                    if (C0053.f92 >= 0) {
                        C0001.m192();
                        iM503 = C0043.m455(NPStringFog.decode("B5D6B6C9B5C9"));
                    } else {
                        i = C0001.f1 + C0001.f1;
                        i2 = 1751006;
                        iM503 = i + i2;
                    }
                case 1750819:
                    if (C0043.m456() > 0) {
                        iM503 = C0043.m455(strDecode8);
                    } else if (C0053.m574() >= 0) {
                        C0001.m192();
                        strDecode3 = NPStringFog.decode("B5D1B6C4B5C1");
                        iM503 = C0002.m230(strDecode3);
                    } else {
                        strDecode7 = NPStringFog.decode("B5D3B6C1B5C1");
                        iM503 = C0052.m503(strDecode7);
                    }
                case 1751523:
                    return;
                case 1751527:
                    C0052.m529(m308());
                    iM503 = C0053.m562("ۣۨۨ");
                case 1751593:
                    if (C0052.m520() <= 0) {
                        C0043.m456();
                        strDecode3 = NPStringFog.decode("B5D5B6C1B5C6");
                        iM503 = C0002.m230(strDecode3);
                    } else {
                        iM503 = C0002.m230(strDecode6);
                    }
                case 1751716:
                    if (m308() == 0) {
                        if (C0001.m192() >= 0) {
                            C0052.m520();
                        }
                        strDecode = NPStringFog.decode("B5D3B6C7B5C0");
                        iM503 = C0001.m190(strDecode);
                    } else if (C0043.f73 * (C0002.f2 / 6349) == 0) {
                        strDecode6 = NPStringFog.decode("B5D3B6C7B5C5");
                        iM503 = C0002.m230(strDecode6);
                    } else {
                        iM503 = C0002.m230("ۨ۟ۨ");
                    }
                case 1753636:
                    return;
                case 1753702:
                    throw new RuntimeException(th);
                case 1754414:
                    if (C0043.f73 * (C0002.f2 / 6349) == 0) {
                    }
                    break;
                case 1755345:
                    try {
                        C0052.m505(m308(), str, C0001.m191(C0001.m216(), m327()), C0001.m191(C0001.m216(), m321()), C0002.m253(C0001.m191(C0001.m216(), m305()), C0052.m507(m313(), 995, C0001.f1 ^ 353, 3071))[0]);
                        if (C0001.m192() >= 0) {
                            strDecode5 = strDecode7;
                        }
                    } catch (IOException e) {
                        th = e;
                        if (C0052.f91 < 0) {
                            strDecode6 = NPStringFog.decode("B5EFB6C1B5C7");
                            break;
                        } else {
                            C0052.m520();
                        }
                    } catch (NoSuchMethodException e2) {
                        th = e2;
                        strDecode3 = NPStringFog.decode("B5D1B6C3B5C7");
                        break;
                    }
                    iM503 = C0002.m230(strDecode5);
                case 1755403:
                    if (!m309()) {
                        strDecode2 = NPStringFog.decode("B5D3B6C7B5C4");
                        iM503 = C0053.m562(strDecode2);
                    } else if (C0043.f73 >= 0) {
                        C0001.f1 = 15;
                        strDecode3 = NPStringFog.decode("B5D7B6C1B5C6");
                        iM503 = C0002.m230(strDecode3);
                    } else {
                        i = C0053.f92 + C0052.f91;
                        i2 = 1752499;
                        iM503 = i + i2;
                    }
                case 1755585:
                    strDecode2 = NPStringFog.decode("B5D3B6C7B5C4");
                    iM503 = C0053.m562(strDecode2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x02a4, code lost:
    
        if (top.canyie.pine.entry.C0053.m550() != false) goto L106;
     */
    /* JADX WARN: Removed duplicated region for block: B:147:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x01ce A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0487 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x01e1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0477 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02f8  */
    /* renamed from: ۟۟۟۟۠, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m300(String str) {
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String[] strArr4;
        int i;
        int i2;
        String str2;
        String strDecode;
        int i3;
        int i4;
        String strDecode2;
        String strDecode3;
        String strDecode4;
        int i5;
        int i6;
        int i7;
        int i8;
        int iM562;
        String strDecode5 = NPStringFog.decode("B5D6B6C0B5C4");
        int iM230 = C0002.m230(strDecode5);
        String strM307 = null;
        String str3 = null;
        String[] strArr5 = null;
        String[] strArr6 = null;
        String strM312 = null;
        String[] strArrM253 = null;
        String[] strArr7 = null;
        String[] strArrM2532 = null;
        String strM513 = null;
        String[] strArr8 = null;
        String[] strArr9 = null;
        String[] strArr10 = null;
        int iM308 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            String strDecode6 = NPStringFog.decode("B5D2B6C1B5FE");
            String strDecode7 = "ۣۦۤ";
            String strDecode8 = NPStringFog.decode("B5D0B6C3B5C9");
            String strDecode9 = "ۡۦ۠";
            String strDecode10 = NPStringFog.decode("B5D6B6C5");
            String strDecode11 = NPStringFog.decode("B5D6B6C9B5C5");
            switch (iM230) {
                case 56289:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    m328(str, strM307, str3, strArr5);
                    if (C0053.f92 >= 0) {
                        strDecode7 = NPStringFog.decode("B5D5B6FEB5C7");
                        iM230 = C0043.m455(strDecode7);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                    iM230 = C0053.m562(strDecode10);
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                case 56447:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (C0002.f2 < 0) {
                        C0002.m259();
                        strDecode2 = NPStringFog.decode("B5D6B6C5B5C3");
                    } else {
                        strDecode2 = NPStringFog.decode("B5D0B6C3B5C6");
                    }
                    iM230 = C0002.m230(strDecode2);
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                case 56483:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    strArr6[3] = C0052.m507(m313(), 1018, C0053.f92 ^ (-807), 1257);
                    if (C0002.f2 >= 0) {
                        C0052.f91 = 0;
                        iM230 = C0053.m562("ۡۦ۠");
                    } else {
                        iM230 = (C0052.f91 + C0001.f1) ^ 1748485;
                    }
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                case 56510:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (C0043.m456() <= 0) {
                        C0043.m456();
                        iM230 = C0052.m503(NPStringFog.decode("B5D0B6C7B5C0"));
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    } else {
                        iM230 = 1752397 ^ (C0052.f91 + C0001.f1);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                case 56537:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    strArr5 = new String[]{C0053.m577(m313(), 1031, 1, 2583), C0053.m577(m313(), 1032, 1, 2266)};
                    if (C0053.m574() >= 0) {
                        C0002.f2 = 21;
                        strDecode = NPStringFog.decode("B5D6B6C1B5C5");
                        iM230 = C0002.m230(strDecode);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                    iM230 = C0052.m503(strDecode9);
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                case 56539:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (C0053.m574() >= 0) {
                        strDecode10 = NPStringFog.decode("B5D6B6FEB5C7");
                        iM230 = C0053.m562(strDecode10);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                    iM230 = C0043.m455("۟۠");
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                case 1746691:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (m308() == 0) {
                        if (C0053.f92 < 0) {
                            i3 = C0052.f91 ^ C0052.f91;
                            i4 = 1750687;
                            iM230 = i4 + i3;
                            i10 = i2;
                            strDecode5 = str2;
                            strArr8 = strArr3;
                            strArrM2532 = strArr2;
                            strArr10 = strArr4;
                            i9 = i;
                        }
                    } else if (C0053.m574() >= 0) {
                    }
                    iM230 = C0043.m455("۟۠");
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                    break;
                case 1746781:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (C0001.f1 * C0053.f92 * (-3993) > 0) {
                        C0001.f1 = 88;
                        strDecode = NPStringFog.decode("B5D7B6C3B5C1");
                        iM230 = C0002.m230(strDecode);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    } else {
                        i3 = C0053.f92 - C0002.f2;
                        i4 = 57275;
                        iM230 = i4 + i3;
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                case 1746819:
                    i = i9;
                    iM230 = 1750757 + (C0052.f91 / C0043.f73);
                    strDecode5 = strDecode5;
                    strArrM2532 = strArrM2532;
                    i9 = i;
                case 1746882:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (iM308 == 1) {
                        strArr8 = new String[1];
                        if (C0053.m574() >= 0) {
                            C0002.m259();
                            iM230 = C0053.m562(NPStringFog.decode("B5D4B6C2"));
                        } else {
                            iM230 = C0043.m455(NPStringFog.decode("B5D7B6C4B5C7"));
                        }
                        i10 = i2;
                        strDecode5 = str2;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                    if (C0002.f2 < 0) {
                    }
                    iM230 = C0002.m230(strDecode2);
                    i10 = i2;
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                    break;
                case 1747749:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    str2 = strDecode5;
                    if (iM308 == 2) {
                        strDecode9 = NPStringFog.decode("B5D6B6C4B5FE");
                        strArr9 = new String[1];
                        iM230 = C0052.m503(strDecode9);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                    if (C0001.f1 * C0053.f92 * (-3993) > 0) {
                    }
                    break;
                case 1747750:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    strArr6 = new String[4];
                    str2 = strDecode5;
                    strArr6[0] = C0002.m242(m313(), 1000, C0001.f1 ^ 357, 403);
                    iM230 = C0001.m190(NPStringFog.decode("B5D8B6C1B5C1"));
                    strDecode5 = str2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    i9 = i;
                case 1747778:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    i2 = i10;
                    if (i < i2) {
                        strM513 = C0052.m513(strM307, strArr5[i], str3);
                        iM230 = C0002.f2 * (C0052.f91 / 1598) != 0 ? C0053.m562(strDecode6) : C0052.m503(NPStringFog.decode("B5D4B6C3B5C4"));
                    } else if ((C0053.f92 | (C0043.f73 - 5292)) < 0) {
                        iM230 = C0043.m455(NPStringFog.decode("B5D6B6C9B5C4"));
                    } else {
                        strDecode10 = NPStringFog.decode("B5D7B6C2B5C6");
                        str2 = strDecode5;
                        iM230 = C0053.m562(strDecode10);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                    i9 = i;
                    i10 = i2;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1747867:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    strDecode3 = C0053.m550() ? NPStringFog.decode("B5EFB6FEB5C2") : NPStringFog.decode("B5D3B6C5B5C1");
                    iM230 = C0001.m190(strDecode3);
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1747934:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    if (C0052.f91 >= 0) {
                        iM230 = C0002.m230(strDecode5);
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    } else {
                        strDecode4 = NPStringFog.decode("B5D8B6FEB5C4");
                        iM230 = C0043.m455(strDecode4);
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    }
                case 1748676:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    strDecode8 = NPStringFog.decode("B5D2B6C3B5C0");
                    strArr7 = strArr6;
                    iM230 = C0002.m230(strDecode8);
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1748827:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    if ((C0052.f91 ^ (C0052.f91 * (-3462))) >= 0) {
                        C0002.m259();
                        iM230 = C0052.m503(strDecode8);
                        i9 = i;
                        strM307 = strM312;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    } else {
                        str2 = strDecode5;
                        strM307 = strM312;
                        i2 = i10;
                        iM230 = C0052.m503(NPStringFog.decode("B5D0B6C7B5C0"));
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                case 1748893:
                case 1755342:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    if (C0052.f91 * C0002.f2 * 5954 <= 0) {
                        C0001.f1 = 17;
                        strDecode3 = NPStringFog.decode("B5D1B6C3B5C0");
                        iM230 = C0001.m190(strDecode3);
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    } else {
                        i5 = C0043.f73 * C0002.f2;
                        i6 = 1737127;
                        iM230 = i5 + i6;
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    }
                case 1749601:
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    String str4 = strArrM253[0];
                    strArrM2532 = C0002.m253(strArrM253[1], C0053.m577(m313(), 999, 1, 2138));
                    if (C0052.f91 >= 0) {
                        iM230 = C0001.m190(strDecode10);
                        str3 = str4;
                        strArr8 = strArr3;
                        strArr10 = strArr4;
                        i9 = i;
                    } else {
                        strArr2 = strArrM2532;
                        str3 = str4;
                        i2 = i10;
                        str2 = strDecode5;
                        iM230 = C0043.m455(strDecode7);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                case 1749665:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    strM312 = m312(strArr7);
                    iM308 = m308();
                    int iM520 = C0052.m520();
                    strDecode9 = NPStringFog.decode("B5EFB6C4B5C9");
                    if (iM520 <= 0) {
                        C0052.f91 = 32;
                        iM230 = C0053.m562(strDecode9);
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    } else {
                        str2 = strDecode5;
                        i2 = i10;
                        iM230 = C0052.m503(strDecode9);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    }
                case 1749819:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    strM307 = m307(strM312);
                    iM230 = C0001.m190(strDecode11);
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1750598:
                case 1755461:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    if (C0043.f73 / (C0052.f91 * (-590)) != 0) {
                        C0001.m192();
                        iM230 = C0052.m503(NPStringFog.decode("B5D8B6C4B5C0"));
                    } else {
                        iM230 = (C0053.f92 - C0053.f92) ^ 1747867;
                    }
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1750687:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    if (C0001.m206() < 24) {
                        if (C0053.f92 >= 0) {
                            strDecode8 = NPStringFog.decode("B5D3B6C0B5C5");
                            iM230 = C0002.m230(strDecode8);
                            i9 = i;
                            strArr8 = strArr3;
                            strArrM2532 = strArr2;
                            strArr10 = strArr4;
                        } else {
                            i5 = C0052.f91 | C0001.f1;
                            i6 = 56418;
                        }
                    } else if (C0053.f92 < 0) {
                        C0001.m192();
                        strDecode3 = NPStringFog.decode("B5D8B6C5B5FE");
                        iM230 = C0001.m190(strDecode3);
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    } else {
                        i5 = C0043.f73 / C0053.f92;
                        i6 = 1753605;
                    }
                    iM230 = i5 + i6;
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1750753:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    strArr7 = strArr2;
                    break;
                case 1750757:
                case 1753451:
                    strArr = strArrM2532;
                    iM230 = C0002.f2 + C0043.f73 + 1748021;
                    strArrM2532 = strArr;
                case 1750786:
                    strArr2 = strArrM2532;
                    strArr3 = strArr8;
                    strArr4 = strArr10;
                    i = i9;
                    if (C0053.f92 >= 0) {
                        C0043.f73 = 71;
                        strDecode11 = NPStringFog.decode("B5D1B6C1B5C5");
                        iM230 = C0001.m190(strDecode11);
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    } else {
                        i5 = C0002.f2 - C0043.f73;
                        i6 = 1749574;
                        iM230 = i5 + i6;
                        i9 = i;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    }
                case 1751591:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArr3 = strArr8;
                    C0052.m500(C0053.m548(C0052.m533(C0001.m214(Shell2Application.class, C0002.m248(C0001.m211(new StringBuilder(C0052.m507(m313(), 1033, 1, 2365)), strM513)))), C0052.f91 ^ (-165)));
                    i11 = i9 + (C0053.f92 ^ (-813));
                    iM230 = (C0052.f91 / C0043.f73) + 1755376;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1752460:
                case 1755616:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArr3 = strArr8;
                    i = i9;
                    i2 = i10;
                    if ((C0053.f92 | (C0043.f73 - 5292)) < 0) {
                    }
                    break;
                case 1752586:
                    break;
                case 1752644:
                    strArr = strArrM2532;
                    i9 = 0;
                    iM230 = C0043.m455(NPStringFog.decode("B5D0B6C2B5C4"));
                    strArrM2532 = strArr;
                case 1753450:
                    strArr = strArrM2532;
                    if (C0002.f2 + (C0052.f91 ^ 4979) >= 0) {
                        C0043.m456();
                        iM230 = C0053.m562("ۣۦۤ");
                    } else {
                        iM230 = (C0053.f92 ^ C0053.f92) + 1749819;
                    }
                    strArr10 = strArr8;
                    strArrM2532 = strArr;
                case 1753482:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    if (C0053.m550() && m308() == 0) {
                        if (C0002.f2 >= 0) {
                            C0001.m192();
                            iM230 = C0053.m562(NPStringFog.decode("B5D8B6C1B5C9"));
                            strArrM2532 = strArr2;
                            strArr10 = strArr4;
                        } else {
                            i7 = C0043.f73 + C0053.f92;
                            i8 = -1752543;
                        }
                    } else if (C0052.f91 < 0) {
                        C0043.m456();
                        strDecode9 = NPStringFog.decode("B5D2B6C0B5C9");
                        strArr3 = strArr8;
                        i = i9;
                        i2 = i10;
                        str2 = strDecode5;
                        iM230 = C0052.m503(strDecode9);
                        i10 = i2;
                        strDecode5 = str2;
                        strArr8 = strArr3;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    } else {
                        i7 = C0002.f2 * C0043.f73;
                        i8 = 1766532;
                    }
                    iM230 = i7 ^ i8;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                    break;
                case 1753540:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    if (C0052.f91 < 0) {
                    }
                    break;
                case 1753541:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArr3 = strArr8;
                    i = i9;
                    iM230 = C0001.m190(strDecode3);
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1753571:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strDecode4 = C0043.f73 / (C0053.f92 | 5436) != 0 ? NPStringFog.decode("B5D0B6C4B5C6") : strDecode5;
                    strArr3 = strArr8;
                    i = i9;
                    iM230 = C0043.m455(strDecode4);
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1753574:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strDecode4 = NPStringFog.decode("B5D8B6C2B5C1");
                    strArr3 = strArr8;
                    i = i9;
                    iM230 = C0043.m455(strDecode4);
                    i9 = i;
                    strArr8 = strArr3;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1753600:
                    strArr = strArrM2532;
                    strArr9[0] = C0043.m454(m313(), 1030, 1, 2827);
                    if (C0053.m574() >= 0) {
                        C0053.f92 = 47;
                        strArr10 = strArr9;
                        iM230 = C0043.m455(NPStringFog.decode("B5D0B6C2B5C4"));
                        strArrM2532 = strArr;
                    } else {
                        iM230 = C0052.m503(NPStringFog.decode("B5D0B6C9B5C7"));
                        strArr10 = strArr9;
                        strArrM2532 = strArr;
                    }
                case 1753605:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    int length = strArr5.length;
                    if (C0052.m520() <= 0) {
                        C0001.f1 = 61;
                        iM562 = C0053.m562(strDecode11);
                    } else {
                        iM562 = (C0043.f73 * C0002.f2) ^ 1740752;
                    }
                    i10 = length;
                    iM230 = iM562;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1753698:
                    iM230 = (C0052.f91 | C0053.f92) + 1753608;
                    strArr5 = strArr10;
                    strArr10 = strArr5;
                case 1754384:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArrM253 = C0002.m253(C0001.m191(C0001.m216(), m305()), C0002.m242(m313(), 997, C0043.f73 ^ (-165), 1390));
                    if (C0043.f73 >= 0) {
                        C0001.m192();
                        i = i9;
                        i2 = i10;
                        str2 = strDecode5;
                        iM230 = C0043.m455(NPStringFog.decode("B5D7B6C4B5C7"));
                        i10 = i2;
                        strDecode5 = str2;
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                        i9 = i;
                    } else {
                        iM230 = C0052.m503(strDecode6);
                        strArrM2532 = strArr2;
                        strArr10 = strArr4;
                    }
                case 1754468:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArr3 = strArr8;
                    i = i9;
                    if (C0053.f92 < 0) {
                    }
                    break;
                case 1754507:
                    break;
                case 1754568:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArr8[0] = C0002.m242(m313(), 1029, 1, 462);
                    i7 = C0052.f91 % C0002.f2;
                    i8 = -1753444;
                    iM230 = i7 ^ i8;
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1755368:
                    strArr2 = strArrM2532;
                    strArr4 = strArr10;
                    strArr6[1] = C0002.m242(m313(), 1006, C0052.f91 ^ (-163), 2930);
                    strArr6[2] = C0043.m454(m313(), 1009, C0002.f2 ^ (-67), 1921);
                    if (C0053.m574() >= 0) {
                        C0053.m574();
                        iM230 = C0001.m190(NPStringFog.decode("B5D7B6C3B5FE"));
                    } else {
                        iM230 = (C0053.f92 - C0001.f1) + 57652;
                    }
                    strArrM2532 = strArr2;
                    strArr10 = strArr4;
                case 1755376:
                    iM230 = C0043.f73 + C0002.f2 + 1747062;
                    i9 = i11;
                default:
                    strArr = strArrM2532;
                    strArr8 = strArr8;
                    strArrM2532 = strArr;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:173:0x021b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x020c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x031d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0329 A[SYNTHETIC] */
    /* renamed from: ۟۟۟۟۠, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m301() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3;
        int i3;
        int i4;
        String strDecode4;
        String strDecode5;
        String strDecode6 = NPStringFog.decode("B5EFB6C2B5C7");
        int iM190 = C0001.m190(strDecode6);
        Properties properties = null;
        ByteArrayInputStream byteArrayInputStream = null;
        String strM191 = null;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            String strDecode7 = NPStringFog.decode("B5D5B6C4");
            String str2 = "ۨ۟ۦ";
            String strDecode8 = NPStringFog.decode("B5D0B6C3B5C2");
            switch (iM190) {
                case 56447:
                    if (C0002.m226(C0001.m215()) == 10) {
                        if ((C0001.f1 ^ (C0052.f91 / (-4412))) <= 0) {
                            C0002.m259();
                            iM190 = C0001.m190(strDecode8);
                            z2 = true;
                        } else {
                            z2 = true;
                            strDecode4 = NPStringFog.decode("B5D3B6C4B5FE");
                            iM190 = C0001.m190(strDecode4);
                        }
                    } else if (C0052.f91 / (C0002.f2 | (-6279)) > 0) {
                        strDecode = NPStringFog.decode("B5D2B6FEB5C1");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0002.f2 ^ C0053.f92;
                        i2 = 1751681;
                        iM190 = i + i2;
                    }
                case 56450:
                    C0002.m229(properties, byteArrayInputStream);
                    strDecode3 = NPStringFog.decode("B5D8B6C2B5C6");
                    iM190 = C0043.m455(strDecode3);
                case 56480:
                    f12 = C0052.m515(properties, C0043.m454(m313(), 1096, 1, 1936));
                    int iM192 = C0001.m192();
                    String strDecode9 = NPStringFog.decode("B5D2B6FEB5C7");
                    if (iM192 >= 0) {
                        C0052.f91 = 76;
                        iM190 = C0052.m503(strDecode9);
                    } else {
                        strDecode2 = strDecode9;
                        iM190 = C0053.m562(strDecode2);
                    }
                case 56539:
                case 1754661:
                    if ((C0002.f2 ^ (C0052.f91 | (-2810))) <= 0) {
                        strDecode2 = NPStringFog.decode("B5D0B6FEB5C5");
                        iM190 = C0053.m562(strDecode2);
                    } else {
                        i = C0002.f2 ^ C0053.f92;
                        i2 = 1746997;
                        iM190 = i + i2;
                    }
                case 56575:
                    f9 = C0043.m454(m313(), 1090, C0052.f91 ^ (-164), 2272);
                    if (C0043.f73 + (C0043.f73 - 5743) >= 0) {
                        C0002.m259();
                        iM190 = C0052.m503(str2);
                    } else {
                        strDecode = NPStringFog.decode("B5EFB6C6B5C5");
                        iM190 = C0052.m503(strDecode);
                    }
                case 1746818:
                    if (C0053.f92 / (C0053.f92 - 9671) != 0) {
                        C0052.f91 = 5;
                        strDecode = NPStringFog.decode("B5D6B6C0B5C7");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0043.f73 / C0052.f91;
                        i2 = 56446;
                        iM190 = i + i2;
                    }
                case 1746940:
                    f10 = C0053.m577(m313(), 1092, C0043.f73 ^ (-166), 546);
                    if ((C0002.f2 | (C0052.f91 % (-7901))) >= 0) {
                        C0001.m192();
                        iM190 = C0001.m190(strDecode7);
                    } else {
                        str2 = "ۡۤۨ";
                        iM190 = C0052.m503(str2);
                    }
                case 1747714:
                    if (C0053.f92 - (C0001.f1 * 4722) < 0) {
                        C0052.m520();
                        strDecode5 = NPStringFog.decode("B5EFB6C0B5C3");
                        iM190 = C0002.m230(strDecode5);
                    } else {
                        i = C0043.f73 ^ C0043.f73;
                        i2 = 1748798;
                        iM190 = i + i2;
                    }
                case 1747745:
                    f11 = C0052.m507(m313(), 1034, C0002.f2 ^ (-85), 1414);
                    if ((C0052.f91 | (C0052.f91 / 8501)) >= 0) {
                        strDecode3 = NPStringFog.decode("B5D8B6C2B5C6");
                        iM190 = C0043.m455(strDecode3);
                    } else {
                        iM190 = C0052.m503(str2);
                    }
                case 1747867:
                    C0010.f24 = z;
                    i3 = C0043.f73 + C0043.f73;
                    i4 = -1753897;
                    iM190 = i3 ^ i4;
                case 1747901:
                    byteArrayInputStream = new ByteArrayInputStream(C0053.m579(strM191));
                    if (C0053.m574() >= 0) {
                        C0043.f73 = 2;
                        strDecode4 = NPStringFog.decode("B5D5B6C3B5C5");
                        iM190 = C0001.m190(strDecode4);
                    } else {
                        i = C0043.f73 ^ C0043.f73;
                        i2 = 56450;
                        iM190 = i + i2;
                    }
                case 1748646:
                    i = C0043.f73 - C0043.f73;
                    i2 = 1749610;
                    iM190 = i + i2;
                case 1748773:
                    break;
                case 1748798:
                    String strM215 = C0001.m215();
                    if (C0001.m192() >= 0) {
                        C0002.m259();
                        strDecode7 = NPStringFog.decode("B5D0B6C7B5C0");
                        str = strM215;
                        iM190 = C0053.m562(strDecode7);
                    } else {
                        str = strM215;
                        strDecode = NPStringFog.decode("B5D6B6C0B5C7");
                        iM190 = C0052.m503(strDecode);
                    }
                case 1749577:
                    f9 = C0052.m515(properties, C0043.m454(m313(), 1097, 1, 2544));
                    if (C0001.f1 <= 0) {
                        strDecode2 = NPStringFog.decode("B5D3B6C7B5C0");
                        iM190 = C0053.m562(strDecode2);
                    } else {
                        i3 = C0053.f92 + C0043.f73;
                        i4 = -1751370;
                        iM190 = i3 ^ i4;
                    }
                case 1749610:
                    break;
                case 1750717:
                    if (C0002.m259() >= 0) {
                        C0043.m456();
                        z = z2;
                        strDecode4 = NPStringFog.decode("B5D3B6C4B5FE");
                        iM190 = C0001.m190(strDecode4);
                    } else {
                        strDecode4 = NPStringFog.decode("B5D7B6C7B5C1");
                        z = z2;
                        iM190 = C0001.m190(strDecode4);
                    }
                case 1750750:
                    if ((C0043.f73 ^ (C0053.f92 ^ (-6937))) >= 0) {
                        C0043.f73 = 7;
                        iM190 = C0052.m503(NPStringFog.decode("B5D0B6C0B5C2"));
                    } else {
                        iM190 = (C0001.f1 - C0043.f73) + 1747345;
                    }
                    z = false;
                case 1751496:
                    strDecode = (C0002.f2 ^ (C0043.f73 % 4880)) <= 0 ? NPStringFog.decode("B5D5B6C3B5C3") : strDecode6;
                    iM190 = C0052.m503(strDecode);
                case 1751647:
                    if (C0052.f91 / (C0002.f2 | (-6279)) > 0) {
                    }
                    break;
                case 1751709:
                    f10 = C0052.m515(properties, C0043.m454(m313(), 1098, 1, 3060));
                    if (C0053.m574() >= 0) {
                        strDecode4 = NPStringFog.decode("B5D7B6C9B5C7");
                        iM190 = C0001.m190(strDecode4);
                    } else {
                        i = C0001.f1 | C0002.f2;
                        i2 = 1749619;
                        iM190 = i + i2;
                    }
                case 1752551:
                    if (C0052.m520() <= 0) {
                        C0002.f2 = 45;
                        strDecode5 = NPStringFog.decode("B5D8B6C6");
                        iM190 = C0002.m230(strDecode5);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D3B6C7B5C0");
                        iM190 = C0053.m562(strDecode2);
                    }
                case 1752615:
                    if (C0001.m192() >= 0) {
                        strDecode4 = NPStringFog.decode("B5D3B6C6B5C3");
                        iM190 = C0001.m190(strDecode4);
                    } else {
                        i3 = C0001.f1 / C0043.f73;
                        i4 = -56447;
                        iM190 = i3 ^ i4;
                    }
                case 1753483:
                    strM191 = C0001.m191(C0001.m216(), str);
                    properties = new Properties();
                    if ((C0002.f2 | C0053.f92 | 8544) >= 0) {
                        C0052.f91 = 19;
                        strDecode = NPStringFog.decode("B5D1B6C1B5C4");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0043.f73 % C0052.f91;
                        i2 = 1747906;
                        iM190 = i + i2;
                    }
                case 1753701:
                    if (C0053.m550()) {
                        if (C0043.f73 >= 0) {
                            C0001.m192();
                            strDecode3 = NPStringFog.decode("B5D4B6FEB5C2");
                            iM190 = C0043.m455(strDecode3);
                        } else {
                            iM190 = C0043.m455(strDecode8);
                        }
                    } else if (C0053.f92 - (C0001.f1 * 4722) < 0) {
                    }
                    break;
                case 1754593:
                    if ((C0053.f92 | (C0001.f1 / (-7295))) >= 0) {
                        strDecode8 = "ۡۤۨ";
                        iM190 = C0043.m455(strDecode8);
                    } else {
                        i3 = C0001.f1 + C0002.f2;
                        i4 = 56780;
                        iM190 = i3 ^ i4;
                    }
                case 1755343:
                    f12 = C0002.m242(m313(), 1065, C0052.f91 ^ (-185), 2955);
                    if (C0052.m520() <= 0) {
                        strDecode4 = NPStringFog.decode("B5D7B6C3");
                        iM190 = C0001.m190(strDecode4);
                    } else {
                        strDecode5 = NPStringFog.decode("B5D8B6C6");
                        iM190 = C0002.m230(strDecode5);
                    }
                case 1755468:
                    try {
                        f11 = C0052.m515(properties, C0052.m507(m313(), 1095, 1, 688));
                        if (C0053.f92 >= 0) {
                            C0001.m192();
                            strDecode4 = NPStringFog.decode("B5D0B6C6B5C5");
                            iM190 = C0001.m190(strDecode4);
                        } else {
                            iM190 = C0053.m562(strDecode7);
                        }
                    } catch (Exception unused) {
                        C0002.m236(0);
                        return false;
                    }
            }
            return true;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00db A[SYNTHETIC] */
    /* renamed from: ۟۟ۤ۠ۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ApplicationInfo m302(Object obj) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int iM562 = C0053.m562(NPStringFog.decode("B5D7B6C7B5C2"));
        ApplicationInfo applicationInfoM290 = null;
        while (true) {
            ApplicationInfo applicationInfo = applicationInfoM290;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D8B6C7B5C2");
                switch (iM562) {
                    case 56325:
                        if (C0002.f2 - (C0002.f2 % 2621) == 0) {
                            strDecode2 = NPStringFog.decode("B5D3B6FEB5FE");
                            iM562 = C0002.m230(strDecode2);
                        } else {
                            i = C0052.f91 - C0052.f91;
                            i2 = 1751746;
                            iM562 = i + i2;
                        }
                    case 56414:
                        if (C0002.m259() >= 0) {
                            C0002.m259();
                            strDecode = NPStringFog.decode("B5D2B6C3B5C2");
                            iM562 = C0052.m503(strDecode);
                        } else {
                            i = C0043.f73 | C0052.f91;
                            i2 = 1754757;
                            iM562 = i + i2;
                        }
                    case 1747679:
                    case 1755438:
                        iM562 = (C0053.f92 ^ C0052.f91) ^ 1753550;
                    case 1750755:
                        break;
                    case 1751746:
                        if (C0053.f92 >= 0) {
                            strDecode3 = NPStringFog.decode("B5D3B6C0");
                        }
                        iM562 = C0002.m230(strDecode3);
                    case 1752736:
                        applicationInfoM290 = m290(obj);
                        if (C0001.f1 <= 0) {
                            C0052.f91 = 19;
                            strDecode = NPStringFog.decode("B5D0B6C4");
                            iM562 = C0052.m503(strDecode);
                        } else {
                            i = C0001.f1 - C0053.f92;
                            i2 = 1749586;
                            iM562 = i + i2;
                        }
                    case 1753543:
                        if (C0053.m574() >= 0) {
                            C0001.m192();
                            iM562 = C0001.m190(strDecode3);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D8B6C3B5C9");
                            iM562 = C0002.m230(strDecode2);
                        }
                    case 1753666:
                        break;
                    case 1754596:
                        if (C0052.m520() >= 0) {
                            if (C0053.f92 >= 0) {
                                iM562 = C0043.m455(NPStringFog.decode("B5D6B6C2B5C5"));
                            } else {
                                i = C0052.f91 ^ C0053.f92;
                                i2 = 1751828;
                                iM562 = i + i2;
                            }
                        } else if (C0002.f2 - (C0002.f2 % 2621) == 0) {
                        }
                        break;
                    case 1755557:
                        if (C0043.f73 - (C0053.f92 % 8856) <= 0) {
                            C0043.m456();
                            iM562 = C0002.m230(NPStringFog.decode("B5D0B6C1B5FE"));
                            applicationInfo = null;
                        } else {
                            applicationInfo = null;
                            iM562 = C0043.m455(NPStringFog.decode("B5D6B6C2B5C5"));
                        }
                }
                return applicationInfo;
            }
            iM562 = (C0043.f73 ^ C0043.f73) + 1753666;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0011. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d8  */
    /* renamed from: ۟۟ۨۢ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m303(Object obj) {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        int iM562 = C0053.m562(NPStringFog.decode("B5D5B6C4"));
        Float fDecode = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D8B6C5B5C5");
            switch (iM562) {
                case 56480:
                    if (C0052.m520() < 0) {
                        if (C0053.f92 < 0) {
                            C0052.f91 = 10;
                            strDecode = NPStringFog.decode("B5D5B6FEB5C7");
                        } else {
                            strDecode = NPStringFog.decode("B5D5B6C4B5C9");
                        }
                        iM562 = C0052.m503(strDecode);
                    } else if (C0052.f91 / (C0001.f1 * 2169) != 0) {
                        strDecode = NPStringFog.decode("B5D0B6C6B5FE");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        strDecode3 = NPStringFog.decode("B5D1B6C2B5FE");
                        iM562 = C0043.m455(strDecode3);
                    }
                case 1746940:
                    fDecode = Float.decode(C0002.m256(NPStringFog.decode("2536293203082C554214111D1920070428081D0A3753142F1426")));
                    i = C0043.f73 + C0001.f1;
                    i2 = 1753446;
                    iM562 = i + i2;
                case 1746975:
                    if (C0053.f92 < 0) {
                    }
                    iM562 = C0052.m503(strDecode);
                    break;
                case 1747714:
                    if (C0002.m259() >= 0) {
                        if ((C0043.f73 | (C0053.f92 - 2249)) >= 0) {
                            C0002.m259();
                        }
                        strDecode = NPStringFog.decode("B5EFB6C6B5C5");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        if (C0001.f1 + (C0043.f73 * (-6399)) <= 0) {
                            C0002.m259();
                            strDecode3 = NPStringFog.decode("B5EFB6C9B5C4");
                        }
                        iM562 = C0043.m455(strDecode3);
                    }
                case 1747896:
                    if (C0001.f1 + (C0043.f73 * (-6399)) <= 0) {
                    }
                    iM562 = C0043.m455(strDecode3);
                    break;
                case 1748672:
                    i = C0001.f1 ^ C0043.f73;
                    i2 = 56934;
                    iM562 = i + i2;
                case 1748733:
                    m300((String) obj);
                    i = C0043.f73 + C0052.f91;
                    i2 = 1748043;
                    iM562 = i + i2;
                case 1752648:
                    i = C0053.f92 ^ C0052.f91;
                    i2 = 1752538;
                    iM562 = i + i2;
                case 1753446:
                case 1754446:
                    if (C0002.f2 >= 0) {
                        C0001.f1 = 24;
                        strDecode2 = NPStringFog.decode("B5D1B6C0B5C9");
                    } else {
                        strDecode2 = NPStringFog.decode("B5D0B6C0B5C2");
                    }
                    iM562 = C0001.m190(strDecode2);
                case 1753634:
                    System.out.println(fDecode);
                    if (C0053.f92 >= 0) {
                        C0052.m520();
                    }
                    iM562 = C0001.m190(strDecode3);
                case 1755496:
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x003a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00cd A[SYNTHETIC] */
    /* renamed from: ۟۠۠ۥ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m304() throws NumberFormatException {
        int i;
        int i2;
        String strDecode;
        int i3;
        int i4;
        String strDecode2;
        int i5;
        String strDecode3;
        String strDecode4 = NPStringFog.decode("B5D2B6C1B5C3");
        int iM562 = C0053.m562(strDecode4);
        int i6 = 0;
        while (true) {
            switch (iM562) {
                case 1747927:
                    i = C0053.f92 ^ C0043.f73;
                    i2 = 1750836;
                    iM562 = i + i2;
                case 1749604:
                    if (C0053.m574() < 0) {
                        i3 = C0052.f91 - C0043.f73;
                        i4 = 1749670;
                        iM562 = i3 ^ i4;
                    } else if ((C0053.f92 ^ (C0043.f73 / 3260)) < 0) {
                        C0053.f92 = 44;
                        strDecode = NPStringFog.decode("B5D2B6C1B5C9");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i = C0053.f92 % C0001.f1;
                        i2 = 1748031;
                        iM562 = i + i2;
                    }
                case 1749667:
                    m298();
                    int i7 = i6;
                    strDecode2 = NPStringFog.decode("B5D6B6C6B5C2");
                    i5 = i7;
                    int iM455 = C0043.m455(strDecode2);
                    i6 = i5;
                    iM562 = iM455;
                case 1750532:
                    if ((C0053.f92 ^ (C0043.f73 / 3260)) < 0) {
                    }
                    break;
                case 1751531:
                    System.out.println(i6);
                    strDecode = NPStringFog.decode("B5D6B6C4B5C1");
                    iM562 = C0043.m455(strDecode);
                case 1751556:
                    i5 = Integer.parseInt(C0002.m256(NPStringFog.decode("3E1E240D3A350550213C395E301759513C34392459311E03311D373405")));
                    if (C0002.m259() >= 0) {
                        C0002.m259();
                        strDecode2 = NPStringFog.decode("B5D4B6C6B5C3");
                        int iM4552 = C0043.m455(strDecode2);
                        i6 = i5;
                        iM562 = iM4552;
                    } else {
                        i6 = i5;
                        strDecode3 = NPStringFog.decode("B5D4B6C1B5C6");
                        iM562 = C0052.m503(strDecode3);
                    }
                case 1751743:
                case 1754568:
                    if (C0001.f1 + (C0001.f1 / (-5616)) <= 0) {
                        C0053.m574();
                        strDecode3 = NPStringFog.decode("B5D2B6C4B5C6");
                        iM562 = C0052.m503(strDecode3);
                    } else {
                        i = C0002.f2 + C0052.f91;
                        i2 = 1753904;
                        iM562 = i + i2;
                    }
                case 1753543:
                    if (C0053.f92 < 0) {
                        strDecode = NPStringFog.decode("B5D6B6C9B5C4");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i3 = C0053.f92 * C0053.f92;
                        i4 = 1105477;
                        iM562 = i3 ^ i4;
                    }
                case 1753601:
                    break;
                case 1753666:
                    if (C0052.m520() <= 0) {
                        strDecode3 = NPStringFog.decode("B5D4B6C0B5C0");
                        iM562 = C0052.m503(strDecode3);
                    } else if (C0053.f92 < 0) {
                    }
                    break;
                case 1754600:
                    strDecode3 = (C0053.f92 | (C0043.f73 % 6887)) >= 0 ? NPStringFog.decode("B5D6B6FEB5C9") : strDecode4;
                    iM562 = C0052.m503(strDecode3);
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00be  */
    /* renamed from: ۟ۡ۠ۦۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m305() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int iM562 = C0053.m562(NPStringFog.decode("B5D8B6C2"));
        String str = null;
        while (true) {
            String str2 = str;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D8B6C6B5FE");
                switch (iM562) {
                    case 56321:
                        i = C0001.f1 / C0053.f92;
                        i2 = 56571;
                        iM562 = i ^ i2;
                    case 56571:
                        if (C0002.m259() >= 0) {
                            strDecode3 = C0053.f92 < 0 ? NPStringFog.decode("B5D4B6C7B5C1") : NPStringFog.decode("B5D8B6C2B5C4");
                            iM562 = C0001.m190(strDecode3);
                        } else if (C0053.f92 * (C0052.f91 - 7197) <= 0) {
                            iM562 = C0053.m562(strDecode3);
                        } else {
                            i = C0043.f73 | C0053.f92;
                            i2 = -1747772;
                            iM562 = i ^ i2;
                        }
                    case 1747652:
                        int i3 = C0053.f92;
                        String strDecode4 = NPStringFog.decode("B5D3B6C2B5FE");
                        if (i3 >= 0) {
                            C0053.m574();
                            iM562 = C0043.m455(strDecode4);
                            str2 = null;
                        } else {
                            strDecode = strDecode4;
                            str2 = null;
                            iM562 = C0043.m455(strDecode);
                        }
                    case 1747743:
                        str = f10;
                        strDecode2 = NPStringFog.decode("B5D3B6C4B5C5");
                        iM562 = C0052.m503(strDecode2);
                    case 1749632:
                        if (C0053.f92 < 0) {
                        }
                        iM562 = C0001.m190(strDecode3);
                        break;
                    case 1749787:
                    case 1754662:
                        if (C0043.m456() <= 0) {
                            C0001.m192();
                            strDecode = NPStringFog.decode("B5D0B6C7B5C9");
                            iM562 = C0043.m455(strDecode);
                        } else {
                            iM562 = (C0053.f92 ^ C0001.f1) + 1756175;
                        }
                    case 1750655:
                        strDecode2 = NPStringFog.decode("B5D7B6C9B5C6");
                        iM562 = C0052.m503(strDecode2);
                    case 1750722:
                        if (C0002.f2 * (C0002.f2 | (-6344)) <= 0) {
                            break;
                        }
                        str2 = str;
                        iM562 = C0001.m190(strDecode3);
                        break;
                    case 1755466:
                        if (C0043.f73 % (C0002.f2 * (-7060)) >= 0) {
                            C0043.f73 = 30;
                            strDecode2 = NPStringFog.decode("B5D3B6C4B5C5");
                            iM562 = C0052.m503(strDecode2);
                        } else {
                            i = C0052.f91 % C0053.f92;
                            i2 = -1747558;
                            iM562 = i ^ i2;
                        }
                    case 1755584:
                        break;
                }
                return str2;
            }
            C0052.f91 = 74;
            iM562 = C0053.m562(NPStringFog.decode("B5D0B6C0"));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* renamed from: ۟ۡۥۦۣ, reason: not valid java name and contains not printable characters */
    public static File m306(Object obj) {
        int i;
        int i2;
        int iM503 = C0052.m503(NPStringFog.decode("B5D6B6C7B5C3"));
        File file = null;
        File fileM292 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5EFB6C7B5C1");
            switch (iM503) {
                case 1746815:
                case 1749667:
                    iM503 = C0043.m455(strDecode);
                case 1746905:
                    break;
                case 1748767:
                    i = C0001.f1 | C0001.f1;
                    i2 = 1751386;
                    iM503 = i + i2;
                case 1749577:
                    if (C0002.f2 * (C0052.f91 - 9655) <= 0) {
                        C0052.m520();
                        iM503 = C0043.m455(NPStringFog.decode("B5D4B6C6B5C1"));
                    } else {
                        iM503 = C0052.m503(NPStringFog.decode("B5D2B6C3B5C2"));
                    }
                case 1751560:
                    if (C0001.f1 <= 0) {
                        C0052.f91 = 89;
                        strDecode = NPStringFog.decode("B5D0B6C2B5C1");
                        iM503 = C0043.m455(strDecode);
                    } else {
                        i = C0053.f92 / C0001.f1;
                        i2 = 1753636;
                        iM503 = i + i2;
                    }
                case 1751651:
                    if (C0043.f73 % (C0002.f2 + 4421) >= 0) {
                        C0052.m520();
                        iM503 = C0002.m230(NPStringFog.decode("B5D1B6C5B5C3"));
                        file = null;
                    } else {
                        strDecode = NPStringFog.decode("B5D2B6FEB5C7");
                        file = null;
                        iM503 = C0043.m455(strDecode);
                    }
                case 1751741:
                    i = C0002.f2 ^ C0052.f91;
                    i2 = 1751417;
                    iM503 = i + i2;
                case 1752640:
                    if (C0001.m192() >= 0) {
                        iM503 = C0043.m455(NPStringFog.decode("B5D4B6C0B5C4"));
                        file = fileM292;
                    } else {
                        file = fileM292;
                        iM503 = C0043.m455(strDecode);
                    }
                case 1753634:
                    if (C0053.m574() >= 0) {
                        i = C0001.f1 | C0001.f1;
                        i2 = 1751386;
                        iM503 = i + i2;
                    } else if (C0053.f92 % (C0002.f2 ^ (-9765)) >= 0) {
                        iM503 = C0053.m562(NPStringFog.decode("B5D5B6C4B5C1"));
                    } else {
                        i = C0001.f1 - C0002.f2;
                        i2 = 1754044;
                        iM503 = i + i2;
                    }
                case 1754475:
                    fileM292 = m292((String) obj);
                    i = C0052.f91 | C0002.f2;
                    i2 = 1752642;
                    iM503 = i + i2;
            }
            return file;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x007b A[SYNTHETIC] */
    /* renamed from: ۟ۢ۟ۡ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m307(Object obj) {
        int i;
        int i2;
        String strDecode;
        String str;
        String strDecode2;
        String strDecode3;
        String strDecode4 = NPStringFog.decode("B5D3B6C1B5C3");
        int iM190 = C0001.m190(strDecode4);
        String str2 = null;
        String strM297 = null;
        while (true) {
            switch (iM190) {
                case 56327:
                    i = C0053.f92 ^ C0053.f92;
                    i2 = 1750565;
                    iM190 = i + i2;
                case 56386:
                    if ((C0001.f1 | C0043.f73 | 1240) >= 0) {
                        strDecode = strDecode4;
                        str = null;
                        int iM503 = C0052.m503(strDecode);
                        str2 = str;
                        iM190 = iM503;
                    } else {
                        iM190 = (C0043.f73 | C0002.f2) + 1749736;
                        str2 = null;
                    }
                case 1748799:
                case 1750630:
                    i = C0002.f2 | C0053.f92;
                    i2 = 1751657;
                    iM190 = i + i2;
                case 1749733:
                    if (C0043.f73 >= 0) {
                        C0002.m259();
                        strDecode2 = NPStringFog.decode("B5D0B6C6");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        i = C0052.f91 + C0043.f73;
                        i2 = 1750959;
                        iM190 = i + i2;
                    }
                case 1750535:
                    if (C0002.m259() < 0) {
                        C0001.m192();
                        strDecode2 = NPStringFog.decode("B5D7B6C3B5C1");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        iM190 = (C0001.f1 ^ C0053.f92) ^ (-1753170);
                    }
                case 1750565:
                    if (C0001.m192() < 0) {
                        if (C0052.f91 >= 0) {
                            C0002.m259();
                            strDecode2 = NPStringFog.decode("B5D3B6FEB5C2");
                            iM190 = C0002.m230(strDecode2);
                        } else {
                            i = C0053.f92 - C0053.f92;
                            i2 = 1753694;
                            iM190 = i + i2;
                        }
                    } else if (C0002.m259() < 0) {
                    }
                    break;
                case 1751647:
                    break;
                case 1751650:
                    int i3 = C0002.f2 + (C0052.f91 / (-1401));
                    strDecode = NPStringFog.decode("B5D4B6C5B5FE");
                    if (i3 >= 0) {
                        iM190 = C0001.m190(strDecode);
                        str2 = strM297;
                    } else {
                        str = strM297;
                        int iM5032 = C0052.m503(strDecode);
                        str2 = str;
                        iM190 = iM5032;
                    }
                case 1753631:
                    iM190 = (C0053.f92 * C0002.f2) - 5478;
                case 1753694:
                    strM297 = m297((String) obj);
                    if ((C0001.f1 | (C0052.f91 + 6027)) <= 0) {
                        C0002.f2 = 50;
                        strDecode3 = NPStringFog.decode("B5D3B6C3B5C4");
                    } else {
                        strDecode3 = NPStringFog.decode("B5D4B6C5B5C3");
                    }
                    iM190 = C0001.m190(strDecode3);
            }
            return str2;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:54:0x005b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x004d A[SYNTHETIC] */
    /* renamed from: ۣ۟ۢۡۦ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int m308() {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D4B6C6B5C0");
        int iM230 = C0002.m230(strDecode2);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D7B6C9B5FE");
            switch (iM230) {
                case 56542:
                    break;
                case 1746881:
                    i4 = hookMode;
                    String strDecode4 = C0053.f92 % (C0002.f2 % (-7077)) >= 0 ? NPStringFog.decode("B5D7B6C4") : NPStringFog.decode("B5D6B6C1B5C3");
                    iM230 = C0002.m230(strDecode4);
                case 1748672:
                    if (C0052.f91 - (C0053.f92 % (-6710)) <= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5EFB6C4B5C6");
                        iM230 = C0001.m190(strDecode);
                    } else {
                        iM230 = C0043.m455(strDecode3);
                    }
                case 1748764:
                    i = C0001.f1 * C0002.f2;
                    i2 = -1748197;
                    iM230 = i ^ i2;
                case 1748827:
                    if (C0002.f2 / (C0053.f92 | (-9193)) != 0) {
                        strDecode3 = NPStringFog.decode("B5D6B6FEB5C3");
                        iM230 = C0043.m455(strDecode3);
                    } else {
                        i = C0043.f73 / C0052.f91;
                        i2 = 1751743;
                        iM230 = i ^ i2;
                    }
                case 1749728:
                case 1754654:
                    iM230 = (C0053.f92 | C0043.f73) + 56579;
                case 1751526:
                    if (C0043.f73 < 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D2B6C9B5C0");
                        iM230 = C0001.m190(strDecode);
                    } else {
                        i = C0043.f73 % C0053.f92;
                        i2 = -1748923;
                        iM230 = i ^ i2;
                    }
                case 1751742:
                    if (C0002.m259() <= 0) {
                        if (C0052.m520() <= 0) {
                            C0052.f91 = 75;
                            iM230 = C0043.m455(strDecode2);
                        } else {
                            strDecode = NPStringFog.decode("B5EFB6C4B5C6");
                            iM230 = C0001.m190(strDecode);
                        }
                    } else if (C0043.f73 < 0) {
                    }
                    break;
                case 1753448:
                    if (C0053.m574() >= 0) {
                        C0002.f2 = 2;
                        iM230 = C0002.m230(strDecode3);
                        i3 = i4;
                    } else {
                        i3 = i4;
                        iM230 = C0002.m230(strDecode4);
                    }
                case 1754503:
                    iM230 = (C0043.f73 ^ C0043.f73) + 1748672;
                    i3 = 0;
            }
            return i3;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00aa A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b9 A[SYNTHETIC] */
    /* renamed from: ۟ۢۤ۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m309() {
        String strDecode;
        int i;
        int i2;
        int iM190 = C0001.m190(NPStringFog.decode("B5D2B6C5B5C3"));
        boolean z = false;
        while (true) {
            boolean z2 = false;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D1B6C2B5C9");
                switch (iM190) {
                    case 1748615:
                        if (C0053.m574() >= 0) {
                            break;
                        }
                        z2 = false;
                        iM190 = C0002.m230(strDecode2);
                        break;
                    case 1748742:
                        strDecode2 = NPStringFog.decode("B5D6B6C9B5C3");
                        iM190 = C0002.m230(strDecode2);
                    case 1749728:
                        if (C0052.m520() >= 0) {
                            if ((C0001.f1 ^ (C0002.f2 * (-3324))) <= 0) {
                                C0052.f91 = 29;
                                iM190 = C0001.m190(NPStringFog.decode("B5D4B6C0B5C9"));
                            } else {
                                strDecode = NPStringFog.decode("B5D3B6C4B5C9");
                                iM190 = C0053.m562(strDecode);
                            }
                        } else if (C0001.f1 > 0) {
                            C0001.m192();
                            iM190 = C0052.m503(NPStringFog.decode("B5D3B6C3B5C9"));
                        } else {
                            strDecode2 = "ۤۧۦ";
                            iM190 = C0002.m230(strDecode2);
                        }
                    case 1749850:
                        if ((C0001.f1 ^ (C0001.f1 * 6491)) <= 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D4B6C2B5C5");
                            iM190 = C0053.m562(strDecode);
                        } else {
                            i = C0053.f92 % C0001.f1;
                            i2 = 1749832;
                            iM190 = i + i2;
                        }
                    case 1750726:
                        z = f8;
                        if (C0053.m574() >= 0) {
                            C0002.m259();
                            iM190 = C0002.m230(strDecode2);
                        } else {
                            i = C0001.f1 % C0002.f2;
                            i2 = 1752411;
                            iM190 = i + i2;
                        }
                    case 1751560:
                        if (C0001.f1 > 0) {
                        }
                        break;
                    case 1751563:
                    case 1753696:
                        if (C0001.m192() >= 0) {
                            strDecode2 = NPStringFog.decode("B5D6B6C5B5FE");
                            iM190 = C0002.m230(strDecode2);
                        } else {
                            i = C0043.f73 - C0001.f1;
                            i2 = 1754099;
                            iM190 = i + i2;
                        }
                    case 1751747:
                        iM190 = C0043.m455(NPStringFog.decode("B5D1B6FEB5C4"));
                    case 1752462:
                        iM190 = C0002.m230(NPStringFog.decode("B5D6B6C5B5C6"));
                        z2 = z;
                    case 1753577:
                        break;
                }
                return z2;
            }
            C0002.m259();
            iM190 = C0053.m562("ۤۧۦ");
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* renamed from: ۣ۟ۤۢ۠, reason: not valid java name and contains not printable characters */
    public static String m310() {
        int i;
        int i2;
        String strDecode;
        int iM190 = C0001.m190(NPStringFog.decode("B5EFB6C9B5C1"));
        String str = null;
        while (true) {
            String str2 = str;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D8B6C4B5FE");
                switch (iM190) {
                    case 1746967:
                        if (C0002.m259() > 0) {
                            i = C0002.f2 % C0001.f1;
                            i2 = 1747728;
                            iM190 = i + i2;
                        } else if (C0043.f73 - (C0053.f92 | 6114) >= 0) {
                            C0053.m574();
                            iM190 = C0052.m503(strDecode2);
                        } else {
                            i = C0053.f92 / C0052.f91;
                            i2 = 1747683;
                            iM190 = i + i2;
                        }
                    case 1747652:
                        if (C0001.f1 <= 0) {
                            iM190 = C0002.m230(NPStringFog.decode("B5D3B6C2B5C2"));
                        } else {
                            i = C0001.f1 / C0001.f1;
                            i2 = 1751678;
                            iM190 = i + i2;
                        }
                    case 1747688:
                        str = f7;
                        iM190 = (C0043.f73 % C0001.f1) ^ (-1752614);
                    case 1750659:
                    case 1752579:
                        if (C0053.f92 >= 0) {
                            strDecode = NPStringFog.decode("B5D8B6C5B5C7");
                            iM190 = C0053.m562(strDecode);
                        } else {
                            iM190 = C0001.m190(strDecode2);
                        }
                    case 1751585:
                        if (C0053.f92 - (C0002.f2 + 4969) >= 0) {
                            C0053.f92 = 54;
                            strDecode2 = NPStringFog.decode("B5D4B6C5B5C1");
                            iM190 = C0001.m190(strDecode2);
                        } else {
                            i = C0043.f73 * C0052.f91;
                            i2 = 1719913;
                            iM190 = i + i2;
                        }
                    case 1751679:
                        if (C0053.m574() >= 0) {
                            C0043.f73 = 32;
                            iM190 = C0043.m455(NPStringFog.decode("B5D5B6C1B5C5"));
                            str2 = null;
                        } else {
                            strDecode = NPStringFog.decode("B5D5B6C1B5C9");
                            str2 = null;
                            iM190 = C0053.m562(strDecode);
                        }
                    case 1752489:
                        i = C0002.f2 % C0001.f1;
                        i2 = 1747728;
                        iM190 = i + i2;
                    case 1752493:
                        i = C0043.f73 * C0052.f91;
                        i2 = 1725525;
                        iM190 = i + i2;
                    case 1752707:
                        iM190 = C0052.f91 >= 0 ? C0002.m230(NPStringFog.decode("B5D0B6FEB5C2")) : C0002.f2 + C0001.f1 + 1755243;
                    case 1755522:
                        break;
                }
                return str2;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0094 A[SYNTHETIC] */
    /* renamed from: ۣ۟ۤۦۣ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m311() {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        int i3;
        int i4;
        int iM562 = C0053.m562(NPStringFog.decode("B5D0B6C1B5C2"));
        String str = null;
        String str2 = null;
        while (true) {
            switch (iM562) {
                case 1746723:
                    str2 = f11;
                    if (C0043.m456() <= 0) {
                        C0043.m456();
                        strDecode = NPStringFog.decode("B5D1B6C1B5C1");
                        iM562 = C0002.m230(strDecode);
                    } else {
                        i = C0002.f2 | C0053.f92;
                        i2 = -1754406;
                        iM562 = i ^ i2;
                    }
                case 1746940:
                    break;
                case 1747683:
                    if (C0001.m192() < 0) {
                        i3 = C0043.f73 * C0052.f91;
                        i4 = 1719669;
                        iM562 = i3 + i4;
                    } else if (C0043.m456() > 0) {
                        C0043.m456();
                        strDecode2 = NPStringFog.decode("B5D6B6C2B5C1");
                        iM562 = C0043.m455(strDecode2);
                    } else {
                        strDecode = NPStringFog.decode("B5D1B6C0B5C4");
                        iM562 = C0002.m230(strDecode);
                    }
                case 1747866:
                    i3 = C0053.f92 ^ C0052.f91;
                    i4 = 1746775;
                    iM562 = i3 + i4;
                case 1748641:
                    if (C0043.m456() > 0) {
                    }
                    break;
                case 1748677:
                    i3 = C0053.f92 / C0043.f73;
                    i4 = 1751775;
                    iM562 = i3 + i4;
                case 1748830:
                    strDecode2 = NPStringFog.decode("B5D3B6C6B5C4");
                    iM562 = C0043.m455(strDecode2);
                case 1750785:
                case 1754530:
                    if (C0002.f2 / (C0052.f91 + 4832) != 0) {
                        strDecode = NPStringFog.decode("B5EFB6C6B5C3");
                        iM562 = C0002.m230(strDecode);
                    } else {
                        i = C0001.f1 - C0001.f1;
                        i2 = 1746940;
                        iM562 = i ^ i2;
                    }
                case 1751779:
                    if (C0053.m574() >= 0) {
                        C0001.m192();
                        iM562 = C0043.m455(NPStringFog.decode("B5D4B6C9B5C6"));
                    } else {
                        iM562 = (C0053.f92 * C0001.f1) + 2037800;
                    }
                    str = null;
                case 1754412:
                    iM562 = (C0001.f1 - C0043.f73) + 1746418;
                    str = str2;
            }
            return str;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0097 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0088 A[SYNTHETIC] */
    /* renamed from: ۟ۤۧ۟ۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m312(Object obj) {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5D6B6C3B5FE");
        int iM230 = C0002.m230(strDecode2);
        String strM285 = null;
        while (true) {
            String str = strM285;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D8B6C2B5C1");
                switch (iM230) {
                    case 56443:
                    case 1754662:
                        if (C0043.f73 - (C0052.f91 * (-2153)) >= 0) {
                            C0002.f2 = 79;
                            strDecode = NPStringFog.decode("B5D3B6C7");
                            iM230 = C0002.m230(strDecode);
                        } else {
                            i = C0002.f2 - C0001.f1;
                            i2 = 1750280;
                            iM230 = i + i2;
                        }
                    case 56570:
                        if (C0052.f91 - (C0001.f1 ^ 1796) >= 0) {
                            C0001.f1 = 4;
                            strDecode3 = strDecode2;
                        }
                        iM230 = C0002.m230(strDecode3);
                    case 1746820:
                        strM285 = m285((String[]) obj);
                        strDecode3 = "ۡ۟ۨ";
                        iM230 = C0001.m190(strDecode3);
                    case 1748618:
                        if ((C0052.f91 ^ (C0053.f92 / (-8479))) >= 0) {
                            C0053.f92 = 0;
                            str = strM285;
                            iM230 = C0001.m190(strDecode3);
                        }
                        break;
                    case 1749703:
                        if (C0052.f91 < 0) {
                            C0002.m259();
                            iM230 = C0052.m503(NPStringFog.decode("B5EFB6FEB5C0"));
                        } else {
                            iM230 = (C0052.f91 - C0001.f1) ^ (-57087);
                        }
                    case 1749849:
                        break;
                    case 1750656:
                        if ((C0052.f91 ^ (C0043.f73 - 7568)) <= 0) {
                            C0053.f92 = 92;
                            iM230 = C0053.m562("ۡ۟ۨ");
                        } else {
                            iM230 = C0053.m562(NPStringFog.decode("B5D7B6C9B5C6"));
                        }
                    case 1753507:
                        if (C0053.m574() < 0) {
                            if ((C0002.f2 ^ (C0001.f1 / (-6600))) >= 0) {
                                strDecode = NPStringFog.decode("B5D4B6FE");
                                iM230 = C0002.m230(strDecode);
                            } else {
                                i = C0043.f73 * C0002.f2;
                                i2 = 1734128;
                                iM230 = i + i2;
                            }
                        } else if (C0052.f91 < 0) {
                        }
                        break;
                    case 1753600:
                        if (C0052.f91 >= 0) {
                            C0002.m259();
                            strDecode = NPStringFog.decode("B5EFB6C4B5C5");
                        } else {
                            strDecode = strDecode2;
                        }
                        iM230 = C0002.m230(strDecode);
                    case 1755461:
                        if (C0053.f92 / (C0001.f1 - 7196) != 0) {
                            C0001.f1 = 66;
                        }
                        iM230 = C0001.m190(NPStringFog.decode("B5D3B6C2B5C1"));
                        str = null;
                }
                return str;
            }
            iM230 = C0043.f73 + C0053.f92 + 1750830;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0071 A[SYNTHETIC] */
    /* renamed from: ۟ۥۥۧۡ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m313() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int iM230 = C0002.m230(NPStringFog.decode("B5D1B6C1B5C6"));
        short[] sArr = null;
        while (true) {
            short[] sArr2 = sArr;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D1B6C6B5C3");
                switch (iM230) {
                    case 56449:
                        if (C0002.f2 / (C0001.f1 ^ (-9717)) != 0) {
                            C0043.m456();
                        }
                        iM230 = C0053.m562(NPStringFog.decode("B5D3B6C0B5C1"));
                    case 1746811:
                    case 1748798:
                        iM230 = (C0002.f2 / C0002.f2) ^ 1750595;
                    case 1746908:
                        if (C0002.f2 >= 0) {
                            C0053.m574();
                            strDecode3 = NPStringFog.decode("B5D0B6C4");
                            iM230 = C0001.m190(strDecode3);
                        } else {
                            i = C0001.f1 % C0002.f2;
                            i2 = 1748597;
                            iM230 = i + i2;
                        }
                    case 1747871:
                        if (C0043.f73 >= 0) {
                            C0043.m456();
                            iM230 = C0043.m455(strDecode3);
                            sArr2 = null;
                        } else {
                            sArr2 = null;
                            strDecode2 = NPStringFog.decode("B5D1B6C2B5FE");
                            iM230 = C0043.m455(strDecode2);
                        }
                    case 1748648:
                        if (C0001.m192() <= 0) {
                            if (C0002.f2 >= 0) {
                                C0052.f91 = 37;
                            }
                            strDecode = NPStringFog.decode("B5D5B6C2B5C1");
                        } else if (C0002.m259() < 0) {
                            strDecode = NPStringFog.decode("B5D7B6C3B5C2");
                        } else {
                            iM230 = C0001.m190(strDecode3);
                        }
                        iM230 = C0053.m562(strDecode);
                    case 1748733:
                        if ((C0053.f92 ^ (C0002.f2 / (-8746))) >= 0) {
                            strDecode2 = NPStringFog.decode("B5D4B6C0B5C4");
                            iM230 = C0043.m455(strDecode2);
                        } else {
                            i = C0043.f73 * C0053.f92;
                            i2 = 1610873;
                            iM230 = i + i2;
                        }
                    case 1748860:
                        i = C0053.f92 ^ C0053.f92;
                        i2 = 1747871;
                        iM230 = i + i2;
                    case 1750594:
                        break;
                    case 1751560:
                        if (C0002.m259() < 0) {
                        }
                        break;
                    case 1752578:
                        sArr = f5short;
                        if ((C0053.f92 ^ (C0053.f92 + 3677)) >= 0) {
                            C0001.m192();
                            strDecode2 = NPStringFog.decode("B5D1B6C2B5FE");
                            iM230 = C0043.m455(strDecode2);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D4B6C4");
                            iM230 = C0043.m455(strDecode2);
                        }
                }
                return sArr2;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f2 A[SYNTHETIC] */
    /* renamed from: ۟ۦۦۨۦ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m314() {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5EFB6C3B5C4");
        int iM190 = C0001.m190(strDecode2);
        boolean z = false;
        boolean zI = false;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D0B6C7B5C5");
            String strDecode4 = "ۣۤ۟";
            String strDecode5 = NPStringFog.decode("B5D5B6C2B5C2");
            switch (iM190) {
                case 1746719:
                    break;
                case 1746786:
                    if (C0002.m259() < 0) {
                        if (C0043.m456() <= 0) {
                            C0043.m456();
                            iM190 = C0001.m190(strDecode3);
                        } else {
                            i = C0053.f92 ^ C0053.f92;
                            i2 = 1746812;
                            iM190 = i + i2;
                        }
                    } else if (C0002.m259() < 0) {
                        C0001.m192();
                        strDecode3 = NPStringFog.decode("B5D3B6C9B5C9");
                        iM190 = C0043.m455(strDecode3);
                    } else {
                        iM190 = C0053.m562(strDecode4);
                    }
                case 1746812:
                    zI = i();
                    strDecode4 = strDecode5;
                    iM190 = C0002.m230(strDecode4);
                case 1747870:
                    if (C0052.f91 % (C0002.f2 | 4003) >= 0) {
                        C0053.m574();
                        iM190 = C0053.m562(NPStringFog.decode("B5D3B6C2B5C7"));
                    } else {
                        iM190 = (C0001.f1 % C0002.f2) ^ 1752575;
                    }
                    z = false;
                case 1749824:
                case 1755619:
                    if (C0002.m259() >= 0) {
                        C0052.f91 = 28;
                        strDecode = NPStringFog.decode("B5D1B6C7B5C2");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0001.f1 / C0002.f2;
                        i2 = 1746723;
                        iM190 = i + i2;
                    }
                case 1750662:
                    if (C0002.m259() < 0) {
                    }
                    break;
                case 1750686:
                    if (C0043.f73 >= 0) {
                        C0053.f92 = 56;
                        iM190 = C0002.m230(strDecode4);
                    } else {
                        iM190 = C0043.m455(strDecode3);
                    }
                case 1752524:
                    if (C0052.f91 - (C0052.f91 - 3191) <= 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D8B6C9B5C2");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        iM190 = C0002.m230(NPStringFog.decode("B5D2B6C6B5C4"));
                    }
                case 1752581:
                    if (C0052.f91 >= 0) {
                        C0052.f91 = 78;
                        iM190 = C0001.m190(strDecode5);
                    } else {
                        iM190 = (C0053.f92 * C0001.f1) ^ (-2019287);
                    }
                    z = zI;
                case 1753634:
                    if (C0002.f2 >= 0) {
                        C0053.m574();
                        strDecode4 = NPStringFog.decode("B5D5B6C3B5C2");
                    } else {
                        strDecode4 = strDecode2;
                    }
                    iM190 = C0053.m562(strDecode4);
            }
            return z;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00dd  */
    /* renamed from: ۣۣ۠ۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m315() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D0B6FEB5C6");
        int iM190 = C0001.m190(strDecode3);
        boolean z = false;
        boolean zM301 = false;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D1B6FEB5C2");
            switch (iM190) {
                case 1746912:
                    zM301 = m301();
                    if (C0053.m574() >= 0) {
                        iM190 = C0002.m230("ۢۢۤ");
                    } else {
                        i = C0043.f73 - C0001.f1;
                        i2 = 1753135;
                        iM190 = i + i2;
                    }
                case 1747656:
                    if (C0001.m192() < 0) {
                        if (C0043.f73 + (C0002.f2 ^ (-5496)) <= 0) {
                            C0053.m574();
                        }
                        iM190 = C0001.m190(NPStringFog.decode("B5EFB6C7B5C6"));
                    } else {
                        iM190 = C0053.m562((C0002.f2 ^ (C0053.f92 + (-1366))) <= 0 ? NPStringFog.decode("B5D0B6C4B5C6") : "ۢۢۤ");
                    }
                case 1747743:
                    break;
                case 1747872:
                case 1751618:
                    if (C0001.f1 <= 0) {
                        C0052.m520();
                        strDecode2 = NPStringFog.decode("B5D0B6C3");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        strDecode = NPStringFog.decode("B5D0B6C3B5C0");
                        iM190 = C0053.m562(strDecode);
                    }
                case 1748613:
                    if (C0001.f1 <= 0) {
                        C0002.f2 = 60;
                        strDecode2 = NPStringFog.decode("B5D7B6C2B5C7");
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        i = C0053.f92 + C0052.f91;
                        i2 = 1752594;
                        iM190 = i + i2;
                    }
                case 1749668:
                    if ((C0002.f2 ^ (C0053.f92 / 18)) <= 0) {
                        C0053.m574();
                        iM190 = C0043.m455(strDecode4);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D7B6C2B5C7");
                        iM190 = C0002.m230(strDecode2);
                    }
                case 1752613:
                    iM190 = C0053.f92 + (C0001.f1 + (-2976)) >= 0 ? C0053.m562(NPStringFog.decode("B5D5B6C5B5C5")) : (C0001.f1 / C0043.f73) ^ (-1747743);
                    z = zM301;
                case 1754506:
                    if (C0043.f73 * (C0053.f92 - 4990) <= 0) {
                        C0002.m259();
                        iM190 = C0002.m230(NPStringFog.decode("B5D8B6C4B5C0"));
                    } else {
                        iM190 = C0052.m503(strDecode4);
                    }
                    z = false;
                case 1755435:
                    iM190 = C0053.m562((C0002.f2 ^ (C0053.f92 + (-1366))) <= 0 ? NPStringFog.decode("B5D0B6C4B5C6") : "ۢۢۤ");
                    break;
                case 1755524:
                    if (C0053.f92 * C0052.f91 * (-7715) >= 0) {
                        strDecode = NPStringFog.decode("B5D5B6C4B5C6");
                        iM190 = C0053.m562(strDecode);
                    } else {
                        iM190 = C0053.m562(strDecode3);
                    }
            }
            return z;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0093 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a3 A[SYNTHETIC] */
    /* renamed from: ۠ۤ۟ۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Application m316(Object obj, Object obj2) {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D7B6C2B5C7");
        int iM562 = C0053.m562(strDecode2);
        Application application = null;
        Application applicationM281 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D0B6C4B5C4");
            switch (iM562) {
                case 56358:
                case 1746966:
                    iM562 = C0053.m574() >= 0 ? C0001.m190(NPStringFog.decode("B5D5B6C3B5C5")) : C0053.m562(strDecode3);
                case 1747743:
                    if (C0043.f73 - (C0002.f2 / 7998) >= 0) {
                        C0053.f92 = 19;
                        iM562 = C0052.m503(NPStringFog.decode("B5D4B6C0"));
                    } else {
                        i = C0001.f1 + C0053.f92;
                        i2 = 1754965;
                        iM562 = i + i2;
                    }
                case 1747840:
                    break;
                case 1748736:
                    if (C0052.f91 + (C0002.f2 / (-7917)) >= 0) {
                        C0053.m574();
                        iM562 = C0001.m190(strDecode2);
                    } else {
                        i = C0043.f73 / C0052.f91;
                        i2 = 1754569;
                        iM562 = i + i2;
                    }
                case 1749572:
                    iM562 = (C0001.f1 / C0002.f2) ^ (-56358);
                case 1749603:
                    if (C0001.f1 - (C0053.f92 % 6173) > 0) {
                        C0052.f91 = 24;
                        strDecode = NPStringFog.decode("B5D1B6C3B5C6");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i = C0001.f1 ^ C0001.f1;
                        i2 = 1748736;
                        iM562 = i + i2;
                    }
                case 1751647:
                    applicationM281 = m281(obj, obj2);
                    if ((C0053.f92 | (C0053.f92 / (-1363))) >= 0) {
                        C0043.m456();
                        strDecode = NPStringFog.decode("B5D1B6C2B5C3");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i = C0053.f92 % C0043.f73;
                        i2 = 1754592;
                        iM562 = i + i2;
                    }
                case 1754446:
                    if (C0052.f91 >= 0) {
                        iM562 = C0002.m230(NPStringFog.decode("B5D1B6C6"));
                        application = applicationM281;
                    } else {
                        application = applicationM281;
                        iM562 = C0052.m503(strDecode3);
                    }
                case 1754506:
                    if (C0002.m259() < 0) {
                        if (C0002.f2 * (C0053.f92 | 4466) <= 0) {
                            iM562 = C0053.m562(NPStringFog.decode("B5D2B6C1B5C0"));
                        } else {
                            i = C0053.f92 - C0052.f91;
                            i2 = 1752299;
                            iM562 = i + i2;
                        }
                    } else if (C0001.f1 - (C0053.f92 % 6173) > 0) {
                    }
                    break;
                case 1754570:
                    if (C0052.f91 - (C0043.f73 % (-3522)) <= 0) {
                        C0001.m192();
                        application = null;
                        iM562 = C0052.m503(strDecode3);
                    } else {
                        iM562 = (C0053.f92 ^ C0052.f91) + 1748664;
                        application = null;
                    }
            }
            return application;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0012. Please report as an issue. */
    /* renamed from: ۡۡۡۧ, reason: not valid java name and contains not printable characters */
    public static void m317(Object obj) throws NumberFormatException {
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D5B6FEB5C9");
        int iM562 = C0053.m562(strDecode3);
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D0B6C0B5FE");
            String strDecode5 = "ۥۨۡ";
            switch (iM562) {
                case 56416:
                case 1751558:
                    if ((C0001.f1 ^ (C0043.f73 + 7027)) <= 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D4B6C4B5FE");
                        iM562 = C0001.m190(strDecode);
                    } else {
                        iM562 = C0001.m190(strDecode4);
                    }
                case 56513:
                    if ((C0043.f73 | (C0002.f2 * 5462)) >= 0) {
                        C0002.f2 = 4;
                        strDecode2 = NPStringFog.decode("B5D6B6C9");
                    } else {
                        strDecode2 = strDecode3;
                    }
                    iM562 = C0053.m562(strDecode2);
                case 1747710:
                    break;
                case 1750656:
                    iM562 = C0002.m230("ۥۨۡ");
                case 1751749:
                    m299((String) obj);
                    if (C0052.f91 * (C0052.f91 % (-8015)) <= 0) {
                        C0002.m259();
                    }
                    iM562 = C0002.m230(strDecode4);
                case 1752462:
                    if (C0001.m192() < 0) {
                        if ((C0001.f1 ^ (C0001.f1 ^ (-2448))) >= 0) {
                            C0053.f92 = 99;
                        } else {
                            strDecode5 = NPStringFog.decode("B5D4B6C6B5C9");
                        }
                        iM562 = C0053.m562(strDecode5);
                    } else {
                        iM562 = C0002.m230("ۥۨۡ");
                    }
                case 1752734:
                    if (C0053.f92 >= 0) {
                        C0043.f73 = 49;
                        strDecode = NPStringFog.decode("B5D3B6C2B5C1");
                        iM562 = C0001.m190(strDecode);
                    } else {
                        iM562 = (C0002.f2 * C0053.f92) ^ 11720;
                    }
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c0  */
    /* renamed from: ۡۨۢۦ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Application m318(Object obj, Object obj2) {
        String strDecode;
        String strDecode2;
        int i;
        int i2;
        String strDecode3 = NPStringFog.decode("B5D1B6C5");
        int iM190 = C0001.m190(strDecode3);
        Application application = null;
        Application applicationM289 = null;
        while (true) {
            switch (iM190) {
                case 56355:
                    if (C0002.m259() >= 0) {
                        iM190 = C0053.m562(C0002.f2 + (C0002.f2 % 9201) < 0 ? NPStringFog.decode("B5D1B6C0B5C9") : NPStringFog.decode("B5D8B6C1B5C2"));
                    } else if ((C0001.f1 ^ (C0001.f1 % (-2644))) != 0) {
                        C0043.f73 = 41;
                        strDecode = NPStringFog.decode("B5D1B6C0B5C6");
                        iM190 = C0043.m455(strDecode);
                    } else {
                        iM190 = C0052.m503(NPStringFog.decode("B5D6B6C1B5C5"));
                    }
                case 1747808:
                    break;
                case 1748679:
                    if (C0002.f2 % (C0001.f1 % 4154) >= 0) {
                        C0002.f2 = 50;
                        strDecode2 = NPStringFog.decode("B5D4B6C3B5FE");
                    } else {
                        strDecode2 = strDecode3;
                    }
                    iM190 = C0001.m190(strDecode2);
                case 1749695:
                    iM190 = C0053.m562(C0002.f2 + (C0002.f2 % 9201) < 0 ? NPStringFog.decode("B5D1B6C0B5C9") : NPStringFog.decode("B5D8B6C1B5C2"));
                    break;
                case 1750787:
                    i = C0002.f2 + C0053.f92;
                    i2 = 1755391;
                    iM190 = i + i2;
                case 1751743:
                    if (C0052.f91 * (C0043.f73 + 1114) >= 0) {
                        C0002.f2 = 64;
                        application = null;
                        iM190 = C0052.m503(NPStringFog.decode("B5D6B6C1B5C5"));
                    } else {
                        iM190 = (C0052.f91 % C0043.f73) + 1750949;
                        application = null;
                    }
                case 1752610:
                    iM190 = (C0052.f91 * C0001.f1) + 1805318;
                    application = applicationM289;
                case 1753450:
                    applicationM289 = ((Shell2Application) obj).m289((String) obj2);
                    if (C0001.f1 <= 0) {
                        C0001.m192();
                    }
                    strDecode = NPStringFog.decode("B5D5B6C5B5C0");
                    iM190 = C0043.m455(strDecode);
                case 1753514:
                case 1754501:
                    strDecode2 = NPStringFog.decode("B5D0B6C5B5C5");
                    iM190 = C0001.m190(strDecode2);
                case 1755371:
                    i = C0001.f1 | C0002.f2;
                    i2 = 1751752;
                    iM190 = i + i2;
            }
            return application;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00bf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00af A[SYNTHETIC] */
    /* renamed from: ۣ۟ۥۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object m319(Object obj) {
        String strDecode;
        String strDecode2;
        int i;
        int i2;
        String strDecode3;
        int i3;
        int i4;
        String strDecode4 = NPStringFog.decode("B5EFB6C5B5C1");
        int iM503 = C0052.m503(strDecode4);
        Object objM293 = null;
        while (true) {
            Object obj2 = objM293;
            while (true) {
                switch (iM503) {
                    case 56351:
                        iM503 = (C0043.f73 - C0052.f91) + 1749580;
                        obj2 = null;
                    case 1746843:
                        if (C0052.m520() >= 0) {
                            strDecode3 = NPStringFog.decode("B5EFB6C6B5C3");
                            iM503 = C0002.m230(strDecode3);
                        } else if (C0053.f92 < 0) {
                            C0002.f2 = 22;
                            strDecode = NPStringFog.decode("B5D8B6C0B5C4");
                            iM503 = C0001.m190(strDecode);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D8B6FEB5C0");
                            iM503 = C0053.m562(strDecode2);
                        }
                    case 1746938:
                        objM293 = m293(obj);
                        if ((C0043.f73 | C0053.f92 | 8898) >= 0) {
                            C0053.f92 = 85;
                            iM503 = C0002.m230(strDecode4);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D5B6C0B5C5");
                            iM503 = C0053.m562(strDecode2);
                        }
                    case 1748612:
                    case 1755401:
                        if (C0001.f1 <= 0) {
                            strDecode2 = NPStringFog.decode("B5D2B6C0B5C3");
                            iM503 = C0053.m562(strDecode2);
                        } else {
                            i = C0053.f92 + C0043.f73;
                            i2 = 1756317;
                            iM503 = i + i2;
                        }
                    case 1749575:
                        if (C0052.f91 / (C0043.f73 % 4804) != 0) {
                            strDecode3 = NPStringFog.decode("B5D8B6FEB5FE");
                            iM503 = C0002.m230(strDecode3);
                        } else {
                            i = C0043.f73 + C0043.f73;
                            i2 = 1755735;
                            iM503 = i + i2;
                        }
                    case 1751650:
                        if (C0001.f1 / (C0043.f73 - 265) != 0) {
                            C0001.f1 = 57;
                            strDecode = NPStringFog.decode("B5D3B6C9B5C5");
                            iM503 = C0001.m190(strDecode);
                        } else {
                            i3 = C0002.f2 ^ C0002.f2;
                            i4 = 1746843;
                            iM503 = i3 ^ i4;
                        }
                    case 1752520:
                        if (C0052.f91 >= 0) {
                            C0053.f92 = 23;
                            obj2 = objM293;
                            strDecode3 = NPStringFog.decode("B5EFB6C6B5C3");
                            iM503 = C0002.m230(strDecode3);
                        }
                        break;
                    case 1754441:
                        if (C0053.f92 < 0) {
                        }
                        break;
                    case 1755336:
                        break;
                    case 1755338:
                        i3 = C0002.f2 | C0052.f91;
                        i4 = -56351;
                        iM503 = i3 ^ i4;
                }
                return obj2;
            }
            iM503 = (C0043.f73 | C0052.f91) ^ (-1755241);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0059 A[SYNTHETIC] */
    /* renamed from: ۣۢۤۦ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m320() throws NumberFormatException {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D3B6C4B5C4");
        int iM455 = C0043.m455(strDecode2);
        Long lValueOf = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5EFB6C7");
            switch (iM455) {
                case 56288:
                    iM455 = C0043.m455("ۣۡۧ");
                case 56295:
                    System.out.println(lValueOf);
                    iM455 = (C0002.f2 % C0052.f91) ^ (-1747787);
                case 1746754:
                case 1749764:
                    if ((C0002.f2 ^ (C0052.f91 * (-2278))) >= 0) {
                        C0043.f73 = 37;
                        strDecode3 = NPStringFog.decode("B5D2B6C5B5C3");
                        iM455 = C0052.m503(strDecode3);
                    } else {
                        i = C0002.f2 + C0002.f2;
                        i2 = 1751898;
                        iM455 = i + i2;
                    }
                case 1747713:
                    break;
                case 1748771:
                    lValueOf = Long.valueOf(C0043.m453(NPStringFog.decode("070839580A323E101D3B421E2D05581E2925020807500126500C1A2721")));
                    if (C0052.m520() <= 0) {
                        C0001.f1 = 23;
                        iM455 = C0043.m455(strDecode2);
                    } else {
                        iM455 = C0052.m503(strDecode3);
                    }
                case 1749761:
                    init();
                    if (C0001.f1 % (C0053.f92 + 5575) <= 0) {
                        C0053.f92 = 54;
                        iM455 = C0043.m455(NPStringFog.decode("B5D2B6C4B5C6"));
                    } else {
                        strDecode = NPStringFog.decode("B5D4B6C6B5C4");
                        iM455 = C0053.m562(strDecode);
                    }
                case 1750601:
                    if (C0052.f91 * (C0052.f91 ^ (-5428)) >= 0) {
                        C0053.m574();
                        iM455 = C0001.m190(strDecode3);
                    } else {
                        i = C0002.f2 % C0053.f92;
                        i2 = 1749840;
                        iM455 = i + i2;
                    }
                case 1750723:
                    if (C0053.m574() < 0) {
                        strDecode = NPStringFog.decode("B5D2B6C4B5C5");
                        iM455 = C0053.m562(strDecode);
                    } else {
                        iM455 = C0043.m455("ۣۡۧ");
                    }
                case 1751746:
                    if (C0001.m192() >= 0) {
                        if ((C0002.f2 | (C0052.f91 * 1361)) >= 0) {
                            C0052.m520();
                            iM455 = C0052.m503("ۣۡۧ");
                        } else {
                            i = C0043.f73 * C0052.f91;
                            i2 = 1721717;
                            iM455 = i + i2;
                        }
                    } else if ((C0052.f91 ^ (C0002.f2 ^ (-3988))) < 0) {
                        C0002.m259();
                        iM455 = C0001.m190(NPStringFog.decode("B5D1B6C5B5FE"));
                    } else {
                        strDecode3 = NPStringFog.decode("B5D0B6C0B5C3");
                        iM455 = C0052.m503(strDecode3);
                    }
                case 1753420:
                    if ((C0052.f91 ^ (C0002.f2 ^ (-3988))) < 0) {
                    }
                    break;
                case 1754537:
                    if ((C0043.f73 ^ (C0043.f73 / (-9271))) >= 0) {
                        C0002.m259();
                        strDecode = NPStringFog.decode("B5D5B6C3B5C3");
                    } else {
                        strDecode = strDecode2;
                    }
                    iM455 = C0053.m562(strDecode);
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0016. Please report as an issue. */
    /* renamed from: ۣۦۧۦ, reason: not valid java name and contains not printable characters */
    public static String m321() {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5D1B6C3B5C3");
        int iM562 = C0053.m562(strDecode2);
        String str = null;
        while (true) {
            String str2 = str;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5EFB6C4B5C4");
                switch (iM562) {
                    case 1746879:
                        if (C0052.m520() <= 0) {
                            C0053.m574();
                            str2 = str;
                        }
                        break;
                    case 1747934:
                        if (C0053.f92 >= 0) {
                            C0052.m520();
                            iM562 = C0043.m455(NPStringFog.decode("B5D3B6C2B5C1"));
                            str2 = null;
                        } else {
                            str2 = null;
                            strDecode = NPStringFog.decode("B5D8B6C1B5FE");
                            iM562 = C0001.m190(strDecode);
                        }
                    case 1748705:
                        iM562 = C0053.m574() <= 0 ? C0043.f73 + (C0001.f1 / 5123) >= 0 ? C0002.m230(NPStringFog.decode("B5D6B6C9B5C4")) : C0043.m455(NPStringFog.decode("B5D7B6C5B5C5")) : (C0053.f92 ^ C0043.f73) + 1752792;
                    case 1748859:
                        break;
                    case 1749696:
                    case 1749792:
                        if (C0053.m574() >= 0) {
                            C0053.m574();
                            strDecode3 = NPStringFog.decode("B5D6B6C4B5C3");
                        } else {
                            strDecode3 = strDecode2;
                        }
                        iM562 = C0052.m503(strDecode3);
                    case 1750656:
                    case 1751778:
                        iM562 = (C0001.f1 / C0001.f1) + 1748858;
                    case 1753699:
                        if ((C0001.f1 ^ (C0002.f2 + 4910)) <= 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D8B6C1B5FE");
                            iM562 = C0001.m190(strDecode);
                        } else {
                            i = C0052.f91 % C0002.f2;
                            i2 = -1747928;
                            iM562 = i ^ i2;
                        }
                    case 1754535:
                        str = f12;
                        if (C0001.f1 * (C0053.f92 + 3467) <= 0) {
                            C0001.m192();
                            strDecode = NPStringFog.decode("B5D4B6C9B5C7");
                            iM562 = C0001.m190(strDecode);
                        } else {
                            iM562 = C0052.m503(strDecode3);
                        }
                    case 1755367:
                        if (C0001.f1 <= 0) {
                            C0043.f73 = 7;
                            iM562 = C0001.m190(strDecode3);
                        } else {
                            i = C0053.f92 | C0043.f73;
                            i2 = -1750693;
                            iM562 = i ^ i2;
                        }
                }
                return str2;
            }
            iM562 = (C0002.f2 / C0002.f2) + 1748858;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c7 A[SYNTHETIC] */
    /* renamed from: ۣۨۨۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ApplicationInfo m322(Object obj) {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D0B6C9B5C0");
        int iM562 = C0053.m562(strDecode2);
        ApplicationInfo applicationInfoM282 = null;
        while (true) {
            ApplicationInfo applicationInfo = applicationInfoM282;
            while (true) {
                switch (iM562) {
                    case 1747744:
                        i = C0002.f2 | C0002.f2;
                        i2 = 1748005;
                        iM562 = i + i2;
                    case 1747929:
                        if (C0052.m520() >= 0) {
                            if (C0053.f92 + C0053.f92 + 3964 <= 0) {
                                iM562 = C0053.m562(strDecode2);
                            } else {
                                i = C0002.f2 - C0053.f92;
                                i2 = 1749799;
                                iM562 = i + i2;
                            }
                        } else if ((C0002.f2 | (C0001.f1 - 497)) < 0) {
                            C0052.f91 = 67;
                            iM562 = C0001.m190(NPStringFog.decode("B5D4B6C9B5C7"));
                        } else {
                            i3 = C0052.f91 + C0043.f73;
                            i4 = -1755374;
                            iM562 = i3 ^ i4;
                        }
                    case 1749697:
                        if ((C0043.f73 ^ (C0002.f2 / 1228)) >= 0) {
                            C0053.f92 = 34;
                            strDecode = NPStringFog.decode("B5D8B6C7B5C2");
                            applicationInfo = null;
                            iM562 = C0052.m503(strDecode);
                        } else {
                            applicationInfo = null;
                            strDecode = NPStringFog.decode("B5D5B6C4B5C5");
                            iM562 = C0052.m503(strDecode);
                        }
                    case 1750537:
                        applicationInfoM282 = m282(obj);
                        if ((C0001.f1 ^ (C0002.f2 - 6731)) >= 0) {
                            C0052.f91 = 13;
                            strDecode = NPStringFog.decode("B5D5B6C4B5C5");
                            iM562 = C0052.m503(strDecode);
                        } else {
                            i = C0052.f91 % C0053.f92;
                            i2 = 1751751;
                            iM562 = i + i2;
                        }
                    case 1750784:
                    case 1753571:
                        i3 = C0052.f91 | C0052.f91;
                        i4 = -1753578;
                        iM562 = i3 ^ i4;
                    case 1751589:
                        if (C0002.m259() >= 0) {
                            break;
                        }
                        applicationInfo = applicationInfoM282;
                        iM562 = C0002.m230(NPStringFog.decode("B5D6B6FEB5C0"));
                        break;
                    case 1751771:
                        if ((C0002.f2 | (C0001.f1 - 497)) < 0) {
                        }
                        break;
                    case 1752644:
                        i = C0002.f2 - C0052.f91;
                        i2 = 1753485;
                        iM562 = i + i2;
                    case 1753416:
                        break;
                    case 1755557:
                        if (C0002.f2 >= 0) {
                            C0001.f1 = 28;
                            iM562 = C0002.m230(NPStringFog.decode("B5D6B6FEB5C0"));
                        } else {
                            strDecode = NPStringFog.decode("B5D2B6C2B5C3");
                            iM562 = C0052.m503(strDecode);
                        }
                }
                return applicationInfo;
            }
            iM562 = C0052.m503(NPStringFog.decode("B5D3B6FEB5C4"));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* renamed from: ۦۤۤۨ, reason: contains not printable characters */
    public static Object m323() {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D5B6C4");
        int iM190 = C0001.m190(strDecode3);
        Object objM295 = null;
        while (true) {
            Object obj = objM295;
            while (true) {
                strDecode = NPStringFog.decode("B5D3B6C0B5C3");
                String strDecode4 = "ۣۨ۠";
                switch (iM190) {
                    case 56418:
                    case 1747873:
                        if (C0002.m259() >= 0) {
                            C0002.m259();
                            strDecode4 = NPStringFog.decode("B5D2B6C1B5C0");
                            iM190 = C0053.m562(strDecode4);
                        } else {
                            i = C0052.f91 + C0053.f92;
                            i2 = 1748750;
                            iM190 = i + i2;
                        }
                    case 56480:
                        if (C0052.m520() < 0) {
                            iM190 = C0053.m562(strDecode4);
                        } else if (C0052.m520() <= 0) {
                            strDecode = strDecode3;
                            iM190 = C0052.m503(strDecode);
                        } else {
                            iM190 = C0002.m230(strDecode);
                        }
                    case 1746724:
                        iM190 = C0053.m562(strDecode4);
                    case 1746820:
                        if (C0052.f91 >= 0) {
                            break;
                        }
                        strDecode = NPStringFog.decode("B5D0B6C2B5C0");
                        obj = objM295;
                        iM190 = C0052.m503(strDecode);
                        break;
                    case 1747774:
                        break;
                    case 1747932:
                        if (C0053.m574() >= 0) {
                            C0001.m192();
                            strDecode2 = NPStringFog.decode("B5D7B6C5B5C6");
                        } else {
                            strDecode2 = NPStringFog.decode("B5D2B6C9B5C9");
                        }
                        iM190 = C0052.m503(strDecode2);
                        obj = null;
                    case 1749858:
                        if (C0001.f1 + (C0043.f73 | (-1033)) <= 0) {
                            C0001.f1 = 77;
                            iM190 = C0052.m503("ۣۨ۠");
                        } else {
                            i = C0053.f92 ^ C0001.f1;
                            i2 = 57009;
                            iM190 = i + i2;
                        }
                    case 1750596:
                        objM295 = m295();
                        i = C0053.f92 ^ C0053.f92;
                        i2 = 1746820;
                        iM190 = i + i2;
                    case 1750811:
                        int i3 = C0043.f73;
                        strDecode = NPStringFog.decode("B5D0B6C9B5C5");
                        if (i3 >= 0) {
                            C0001.f1 = 45;
                            iM190 = C0002.m230(strDecode);
                        } else {
                            iM190 = C0052.m503(strDecode);
                        }
                    case 1754538:
                        if (C0002.f2 * (C0002.f2 | (-4900)) <= 0) {
                            C0002.m259();
                            strDecode = NPStringFog.decode("B5D3B6C1B5C5");
                        } else {
                            strDecode = strDecode3;
                        }
                        iM190 = C0002.m230(strDecode);
                }
                return obj;
            }
            C0002.m259();
            iM190 = C0002.m230(strDecode);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0032 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0027 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa A[SYNTHETIC] */
    /* renamed from: ۦۣۨۤ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m324(Object obj, Object obj2) {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        String strDecode2;
        String strDecode3;
        int iM230 = C0002.m230(NPStringFog.decode("B5D7B6C9B5C1"));
        Double dDecode = null;
        while (true) {
            switch (iM230) {
                case 56573:
                    i = C0002.f2 * C0043.f73;
                    i2 = 1767307;
                    iM230 = i ^ i2;
                case 1746725:
                case 1746815:
                    if (C0002.m259() >= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5EFB6C4B5C4");
                        iM230 = C0043.m455(strDecode);
                    } else {
                        i3 = C0043.f73 ^ C0001.f1;
                        i4 = 1753071;
                        iM230 = i3 + i4;
                    }
                case 1749603:
                    if (C0053.m574() >= 0) {
                        iM230 = C0043.m455(NPStringFog.decode("B5EFB6C2B5C2"));
                    } else {
                        i3 = C0043.f73 / C0043.f73;
                        i4 = 1746724;
                        iM230 = i3 + i4;
                    }
                case 1750594:
                    if ((C0043.f73 ^ (C0043.f73 + 1171)) < 0) {
                        strDecode3 = NPStringFog.decode("B5D5B6C5");
                        iM230 = C0002.m230(strDecode3);
                    } else {
                        i3 = C0053.f92 % C0053.f92;
                        i4 = 1754415;
                        iM230 = i3 + i4;
                    }
                case 1751654:
                    m286((Context) obj, (Application) obj2);
                    int iM192 = C0001.m192();
                    strDecode = NPStringFog.decode("B5D5B6C5B5C9");
                    if (iM192 >= 0) {
                        C0002.f2 = 57;
                    }
                    iM230 = C0043.m455(strDecode);
                case 1751776:
                    dDecode = Double.decode(C0043.m453(NPStringFog.decode("0B393E511E33133414281F1533160C023613592029123D")));
                    if (C0052.m520() <= 0) {
                        C0002.m259();
                        strDecode2 = NPStringFog.decode("B5D8B6C4");
                        iM230 = C0052.m503(strDecode2);
                    } else {
                        i = C0002.f2 % C0043.f73;
                        i2 = -1753583;
                        iM230 = i ^ i2;
                    }
                case 1752617:
                    if (C0043.m456() <= 0) {
                        i3 = C0002.f2 / C0043.f73;
                        i4 = 1751776;
                    } else if ((C0043.f73 ^ (C0043.f73 + 1171)) < 0) {
                    }
                    iM230 = i3 + i4;
                    break;
                case 1753509:
                    System.out.println(dDecode);
                    strDecode2 = NPStringFog.decode("B5D7B6C1B5C9");
                    iM230 = C0052.m503(strDecode2);
                case 1753510:
                    if (C0043.f73 % (C0002.f2 - 8569) < 0) {
                        iM230 = C0053.m562(NPStringFog.decode("B5D3B6C9B5C4"));
                    } else {
                        i3 = C0052.f91 - C0001.f1;
                        i4 = 1750120;
                        iM230 = i3 + i4;
                    }
                case 1754415:
                    break;
                case 1754655:
                    if (C0002.m259() < 0) {
                        strDecode3 = NPStringFog.decode("B5D4B6C5B5C7");
                        iM230 = C0002.m230(strDecode3);
                    } else if (C0043.f73 % (C0002.f2 - 8569) < 0) {
                    }
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0079 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0072 A[SYNTHETIC] */
    /* renamed from: ۧۡۥۣ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object m325(Object obj) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int iM455 = C0043.m455(NPStringFog.decode("B5D3B6C2B5FE"));
        Object objM296 = null;
        while (true) {
            Object obj2 = objM296;
            while (true) {
                switch (iM455) {
                    case 56353:
                    case 1755562:
                        if (C0002.m259() >= 0) {
                            C0001.m192();
                            strDecode = NPStringFog.decode("B5D7B6C5B5C2");
                            iM455 = C0043.m455(strDecode);
                        } else {
                            i = C0043.f73 / C0052.f91;
                            i2 = 1750783;
                            iM455 = i + i2;
                        }
                    case 56508:
                        strDecode = NPStringFog.decode("B5D8B6C7B5C9");
                        iM455 = C0043.m455(strDecode);
                    case 1746789:
                        objM296 = m296(obj);
                        if (C0002.f2 >= 0) {
                            C0052.f91 = 84;
                            strDecode = NPStringFog.decode("B5EFB6C9B5FE");
                        } else {
                            strDecode = NPStringFog.decode("B5D2B6C5B5C0");
                        }
                        iM455 = C0043.m455(strDecode);
                    case 1746966:
                        if ((C0053.f92 | (C0052.f91 + 5195)) < 0) {
                            strDecode = NPStringFog.decode("B5D6B6C4B5C9");
                            iM455 = C0043.m455(strDecode);
                        } else {
                            iM455 = (C0002.f2 + C0053.f92) ^ (-1754706);
                        }
                    case 1749727:
                        break;
                    case 1749733:
                        if (C0052.m520() <= 0) {
                            strDecode2 = NPStringFog.decode("B5D4B6C7B5C2");
                            obj2 = null;
                            iM455 = C0052.m503(strDecode2);
                        } else {
                            strDecode = NPStringFog.decode("B5D6B6C3");
                            obj2 = null;
                            iM455 = C0043.m455(strDecode);
                        }
                    case 1750655:
                        if (C0002.m259() <= 0) {
                            strDecode = NPStringFog.decode("B5EFB6C3B5C9");
                        } else if ((C0053.f92 | (C0052.f91 + 5195)) < 0) {
                        }
                        iM455 = C0043.m455(strDecode);
                        break;
                    case 1750784:
                        break;
                    case 1751713:
                        if (C0052.m520() <= 0) {
                            C0001.f1 = 17;
                            iM455 = C0001.m190(NPStringFog.decode("B5D4B6C7B5C6"));
                        } else {
                            i = C0001.f1 / C0053.f92;
                            i2 = 1750655;
                            iM455 = i + i2;
                        }
                    case 1754408:
                        strDecode2 = NPStringFog.decode("B5D2B6C5B5C6");
                        iM455 = C0052.m503(strDecode2);
                }
                return obj2;
            }
            iM455 = (C0052.f91 | C0052.f91) + 1750946;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0062 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x006d A[SYNTHETIC] */
    /* renamed from: ۣۣۧۥ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Application m326(Object obj) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3;
        String strDecode4 = NPStringFog.decode("B5D3B6C6B5C1");
        int iM562 = C0053.m562(strDecode4);
        Application application = null;
        while (true) {
            Application application2 = application;
            while (true) {
                switch (iM562) {
                    case 56327:
                        if (C0002.m259() >= 0) {
                            application2 = null;
                            strDecode2 = NPStringFog.decode("B5D7B6C4B5C0");
                            iM562 = C0052.m503(strDecode2);
                        } else {
                            iM562 = (C0043.f73 | C0053.f92) ^ (-1752612);
                            application2 = null;
                        }
                    case 1746687:
                    case 1748609:
                        if (C0052.f91 % (C0001.f1 - 9662) >= 0) {
                            strDecode = NPStringFog.decode("B5D1B6C1B5C0");
                            iM562 = C0001.m190(strDecode);
                        } else {
                            i = C0002.f2 - C0052.f91;
                            i2 = 1754477;
                            iM562 = i + i2;
                        }
                    case 1746811:
                        if (C0001.m192() >= 0) {
                            C0053.m574();
                            strDecode2 = NPStringFog.decode("B5D7B6C0B5C2");
                        } else {
                            strDecode2 = strDecode4;
                        }
                        iM562 = C0052.m503(strDecode2);
                    case 1747682:
                        if (C0053.f92 >= 0) {
                            C0002.m259();
                            strDecode3 = NPStringFog.decode("B5D0B6C1B5C3");
                            iM562 = C0053.m562(strDecode3);
                        } else {
                            iM562 = (C0053.f92 * C0053.f92) - 606269;
                        }
                    case 1747712:
                        if (C0043.m456() > 0) {
                            strDecode3 = NPStringFog.decode("B5D4B6C0B5C3");
                            iM562 = C0053.m562(strDecode3);
                        } else {
                            i = C0053.f92 / C0053.f92;
                            i2 = 1747681;
                            iM562 = i + i2;
                        }
                    case 1749764:
                        application = ((Shell2Application) obj).f13;
                        if (C0043.f73 + (C0052.f91 ^ (-6075)) <= 0) {
                            C0052.m520();
                            strDecode2 = NPStringFog.decode("B5D5B6C2B5C4");
                        } else {
                            strDecode2 = NPStringFog.decode("B5D6B6FEB5C3");
                        }
                        iM562 = C0052.m503(strDecode2);
                    case 1750780:
                        if (C0052.m520() >= 0) {
                            int i3 = C0052.f91 | C0043.f73 | (-422);
                            strDecode = NPStringFog.decode("B5D2B6C4B5C6");
                            iM562 = C0001.m190(strDecode);
                        } else if (C0043.m456() > 0) {
                        }
                        break;
                    case 1752583:
                        i = C0001.f1 * C0001.f1;
                        i2 = 1620662;
                        iM562 = i + i2;
                    case 1753417:
                        if (C0002.f2 >= 0) {
                            break;
                        }
                        application2 = application;
                        strDecode2 = NPStringFog.decode("B5D7B6C4B5C0");
                        iM562 = C0052.m503(strDecode2);
                        break;
                    case 1754563:
                        break;
                }
                return application2;
            }
            iM562 = C0002.m230(strDecode4);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00cc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c4 A[SYNTHETIC] */
    /* renamed from: ۧۥۣۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m327() {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5EFB6C5B5C6");
        int iM503 = C0052.m503(strDecode3);
        String str = null;
        while (true) {
            String str2 = str;
            while (true) {
                String strDecode4 = NPStringFog.decode("B5D0B6FEB5C5");
                switch (iM503) {
                    case 56443:
                        str = f9;
                        if (C0001.f1 <= 0) {
                            C0002.m259();
                            strDecode = NPStringFog.decode("B5D4B6C5B5C4");
                            iM503 = C0001.m190(strDecode);
                        } else {
                            iM503 = C0001.m190(strDecode4);
                        }
                    case 56475:
                    case 1746847:
                        i = C0001.f1 / C0052.f91;
                        i2 = 1751655;
                        iM503 = i + i2;
                    case 1746850:
                        if (C0052.m520() > 0) {
                            iM503 = C0053.m574() >= 0 ? C0052.m503(strDecode4) : C0043.m455(NPStringFog.decode("B5D4B6FE"));
                        } else if (C0002.f2 * C0002.f2 * 2267 > 0) {
                            strDecode2 = NPStringFog.decode("B5D4B6C2B5C9");
                            iM503 = C0053.m562(strDecode2);
                        } else {
                            i = C0053.f92 ^ C0052.f91;
                            i2 = 1753658;
                            iM503 = i + i2;
                        }
                    case 1746942:
                        if (C0053.f92 >= 0) {
                            C0001.m192();
                            strDecode2 = strDecode3;
                        } else {
                            strDecode2 = NPStringFog.decode("B5D5B6C1");
                        }
                        iM503 = C0053.m562(strDecode2);
                    case 1747653:
                        if (C0053.f92 >= 0) {
                            C0053.f92 = 27;
                            strDecode4 = NPStringFog.decode("B5D2B6C5B5C4");
                            str2 = str;
                            iM503 = C0001.m190(strDecode4);
                        }
                        break;
                    case 1749731:
                        if (C0002.f2 * C0002.f2 * 2267 > 0) {
                        }
                        break;
                    case 1751653:
                        break;
                    case 1751744:
                        if ((C0001.f1 ^ (C0043.f73 ^ (-4081))) <= 0) {
                            strDecode = NPStringFog.decode("B5D2B6C4B5C4");
                            iM503 = C0001.m190(strDecode);
                        } else {
                            iM503 = (C0053.f92 / C0043.f73) ^ 1746854;
                        }
                    case 1753670:
                        if (C0001.m192() >= 0) {
                            C0002.f2 = 81;
                            iM503 = C0043.m455(NPStringFog.decode("B5D4B6C6B5C2"));
                        } else {
                            iM503 = (C0052.f91 / C0043.f73) ^ 1746942;
                        }
                        str2 = null;
                    case 1754566:
                        if (C0052.f91 / (C0053.f92 + 1196) != 0) {
                            C0053.f92 = 10;
                            strDecode2 = NPStringFog.decode("B5EFB6C5B5C5");
                        } else {
                            strDecode2 = NPStringFog.decode("B5D6B6C6B5C6");
                        }
                        iM503 = C0053.m562(strDecode2);
                }
                return str2;
            }
            iM503 = (C0052.f91 ^ C0043.f73) + 1751646;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bc A[SYNTHETIC] */
    /* renamed from: ۨۢۨۨ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m328(Object obj, Object obj2, Object obj3, Object obj4) {
        int i;
        int i2;
        String strDecode;
        int iM455 = C0043.m455(NPStringFog.decode("B5EFB6C7B5FE"));
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D5B6C2B5C7");
            switch (iM455) {
                case 1746904:
                    if (C0053.m574() < 0) {
                        if (C0052.f91 * (C0001.f1 / 8178) != 0) {
                            C0052.f91 = 87;
                            strDecode = NPStringFog.decode("B5D2B6C1B5C4");
                            iM455 = C0052.m503(strDecode);
                        } else {
                            iM455 = (C0002.f2 * C0002.f2) ^ 1758413;
                        }
                    } else if ((C0053.f92 | (C0001.f1 ^ 3521)) < 0) {
                        strDecode = NPStringFog.decode("B5D5B6FEB5C0");
                        iM455 = C0052.m503(strDecode);
                    } else {
                        i = C0001.f1 * C0053.f92;
                        i2 = 2037827;
                        iM455 = i + i2;
                    }
                case 1746936:
                    if (C0001.f1 - (C0002.f2 | (-877)) <= 0) {
                        C0053.f92 = 56;
                        iM455 = C0001.m190(NPStringFog.decode("B5D6B6C6"));
                    } else {
                        i = C0052.f91 / C0001.f1;
                        i2 = 1746904;
                        iM455 = i + i2;
                    }
                case 1748765:
                case 1753414:
                    i = C0053.f92 * C0001.f1;
                    i2 = 2041554;
                    iM455 = i + i2;
                case 1748857:
                    if (C0001.f1 % (C0052.f91 ^ (-1418)) <= 0) {
                        C0002.f2 = 83;
                        iM455 = C0052.m503(strDecode2);
                    } else {
                        i = C0052.f91 - C0053.f92;
                        i2 = 1752762;
                        iM455 = i + i2;
                    }
                case 1749607:
                    if ((C0053.f92 | (C0001.f1 ^ 3521)) < 0) {
                    }
                    break;
                case 1752584:
                    break;
                case 1753693:
                    m288((String) obj, (String) obj2, (String) obj3, (String[]) obj4);
                    if (C0052.m520() <= 0) {
                        C0043.f73 = 26;
                        strDecode2 = NPStringFog.decode("B5D1B6C5B5C1");
                    }
                    iM455 = C0001.m190(strDecode2);
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c0 A[SYNTHETIC] */
    @Override // com.iqoocg.nm.VfSEUCNxUWTV, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void attachBaseContext(Context context) throws Exception {
        String strDecode = NPStringFog.decode("B5D8B6C9B5C2");
        int iM455 = C0043.m455(strDecode);
        Application applicationM326 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D4B6C7B5C5");
            String strDecode3 = "ۥ۟ۥ";
            switch (iM455) {
                case 56537:
                    if (C0001.m182(C0001.m191(C0001.m216(), m321()), C0053.m570(context))) {
                        if (C0002.m259() >= 0) {
                            C0052.f91 = 14;
                        } else {
                            strDecode3 = NPStringFog.decode("B5D2B6C5B5C9");
                        }
                        iM455 = C0043.m455(strDecode3);
                    } else if (C0002.m259() >= 0) {
                        C0043.f73 = 53;
                        iM455 = C0001.m190(NPStringFog.decode("B5D2B6FEB5C5"));
                    } else {
                        iM455 = C0002.m230(strDecode2);
                    }
                case 1748828:
                    applicationM326 = m326(this);
                    strDecode2 = "ۥ۟ۥ";
                    iM455 = C0002.m230(strDecode2);
                case 1749634:
                    if (C0002.m259() >= 0) {
                    }
                    break;
                case 1749734:
                    C0011.f27 = C0002.m258(this);
                    if (C0053.f92 >= 0) {
                        iM455 = C0043.m455(NPStringFog.decode("B5D8B6C5B5FE"));
                    } else {
                        strDecode2 = NPStringFog.decode("B5D1B6C7B5C0");
                        iM455 = C0053.m562(strDecode2);
                    }
                case 1750536:
                    m324(context, applicationM326);
                    iM455 = C0053.m562(strDecode2);
                case 1751714:
                    break;
                case 1752459:
                    if (applicationM326 != null) {
                        if (C0043.m456() <= 0) {
                            strDecode2 = NPStringFog.decode("B5D1B6C7B5C0");
                            iM455 = C0053.m562(strDecode2);
                        } else {
                            strDecode3 = NPStringFog.decode("B5D3B6FEB5C5");
                            iM455 = C0043.m455(strDecode3);
                        }
                    } else if (C0002.m259() >= 0) {
                    }
                    break;
                case 1755491:
                    if (C0001.f1 <= 0) {
                        C0043.f73 = 82;
                        strDecode2 = NPStringFog.decode("B5D7B6C9B5C2");
                    } else {
                        strDecode2 = strDecode;
                    }
                    iM455 = C0002.m230(strDecode2);
                case 1755619:
                    super.attachBaseContext(context);
                    if (C0002.m259() >= 0) {
                        C0053.m574();
                    }
                    iM455 = C0002.m230(NPStringFog.decode("B5D7B6C1"));
            }
            return;
        }
    }

    @Override // com.iqoocg.nm.VfSEUCNxUWTV, android.app.Application
    public native void onCreate();
}
