package np.protect.assets.p;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import com.shadow.okio.Utf8;
import com.swift.sandhook.C0002;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.ThisObject;
import com.swift.sandhook.lib.C0001;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟۟, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0011 {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f25short = {2226, 2224, 2226, 2233, 2228, 2302, 2213, 2228, 2236, 2209, 2190, 2190, 2190, 2190, 2224, 1642, 1645, 1639, 1638, 1659, 1581, 1643, 1655, 1646, 1647, 1122, 1151, 1126, 1130, 1143, 1131, 1122, 1064, 1139, 1122, 1140, 1139, 1065, 1139, 1151, 1139, 2188, 2206, 2185, 2186, 2178, 2180, 2188, 2200, 2180, 2187, 2470, 2536, 2554, 2554, 2540, 2557, 2554, 2470, 1356, 1286, 1283, 1302, 508, 1440, 1442, 1440, 1451, 1446, 1516, 1463, 1446, 1454, 1459, 1436, 1436, 1436, 1436, 1442, 1516, 3112, 3127, 3106, 3113, 2959, 2429, 2402, 2423, 2428, 2388, 2422, 961, 2113, 2142, 2123, 2112, 1027, 1052, 1033, 1026, 1066, 1032};

    /* renamed from: ۟۟۟۠۠, reason: not valid java name and contains not printable characters */
    private static String f26;

    /* renamed from: ۟۟۟۠ۡ, reason: not valid java name and contains not printable characters */
    public static AssetManager f27;

    /* renamed from: ۟۟۟۠ۢ, reason: not valid java name and contains not printable characters */
    private static a f28;

    /* renamed from: ۣ۟۟۟۠, reason: not valid java name and contains not printable characters */
    private static a f29;

    /* renamed from: ۟۟۟۠ۤ, reason: not valid java name and contains not printable characters */
    private static byte[] f30;

    /* renamed from: ۟۟۟۠ۥ, reason: not valid java name and contains not printable characters */
    private static Set<String> f31;

    @HookClass(AssetManager.class)
    /* renamed from: np.protect.assets.p.۟۟۟۟۟$۟, reason: contains not printable characters */
    public class C0012 {

        /* renamed from: ۟۟۟۠ۦ, reason: not valid java name and contains not printable characters */
        @HookMethodBackup("open")
        @MethodParams({String.class, int.class})
        static Method f32;

        /* renamed from: ۟۟۟۠ۧ, reason: not valid java name and contains not printable characters */
        @HookMethodBackup("openFd")
        @MethodParams({String.class})
        static HookWrapper.HookEntity f33;

        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0014. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:46:0x008d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x009d A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public C0012() throws NumberFormatException {
            int i;
            int i2;
            String strDecode = NPStringFog.decode("B5EFB6C4B5C1");
            int iM562 = C0053.m562(strDecode);
            Double d = null;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D4B6C4B5C6");
                switch (iM562) {
                    case 1746844:
                        if (C0043.f73 < 0) {
                            C0043.f73 = 35;
                            iM562 = C0052.m503(NPStringFog.decode("B5D4B6C7B5C1"));
                        } else {
                            i = C0052.f91 % C0052.f91;
                            i2 = 1755368;
                            iM562 = i + i2;
                        }
                    case 1746874:
                        if (C0043.m456() <= 0) {
                            if (C0052.m520() <= 0) {
                                C0053.m574();
                                iM562 = C0052.m503(strDecode2);
                            } else {
                                iM562 = (C0002.f2 - C0043.f73) ^ 1752563;
                            }
                        } else if (C0043.f73 < 0) {
                        }
                        break;
                    case 1749635:
                        if (C0002.m259() >= 0) {
                            strDecode2 = NPStringFog.decode("B5D6B6FEB5C6");
                            iM562 = C0052.m503(strDecode2);
                        } else {
                            i = C0052.f91 / C0053.f92;
                            i2 = 1746874;
                            iM562 = i + i2;
                        }
                    case 1751686:
                        System.out.println(d);
                        if (C0052.f91 + (C0053.f92 | (-3147)) >= 0) {
                            C0043.m456();
                            iM562 = C0001.m190(strDecode);
                        } else {
                            iM562 = C0001.m190(NPStringFog.decode("B5D8B6C1B5C1"));
                        }
                    case 1752488:
                        Double dValueOf = Double.valueOf(C0052.m511(NPStringFog.decode("07263B08")));
                        if (C0043.m456() <= 0) {
                            C0002.m259();
                            d = dValueOf;
                            iM562 = C0001.m190(NPStringFog.decode("B5D8B6C1B5C1"));
                        } else {
                            d = dValueOf;
                            iM562 = C0052.m503(strDecode2);
                        }
                    case 1755368:
                        break;
                }
                return;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
        @MethodParams({String.class})
        @HookMethod("openFd")
        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static AssetFileDescriptor m381(@ThisObject AssetManager assetManager, String str) {
            String strDecode;
            String strDecode2 = NPStringFog.decode("B5D6B6C0");
            while (true) {
                int iM562 = C0053.m562(strDecode2);
                while (true) {
                    switch (iM562) {
                        case 56507:
                            if (C0052.m502(m386(), str)) {
                                if (C0052.m520() <= 0) {
                                    C0002.f2 = 2;
                                    iM562 = C0043.m455(NPStringFog.decode("B5D8B6C7B5FE"));
                                } else {
                                    iM562 = (C0043.f73 + C0001.f1) ^ 1754544;
                                }
                            } else {
                                if (C0001.f1 > 0) {
                                    break;
                                }
                                strDecode = NPStringFog.decode("B5D3B6C1B5C1");
                                iM562 = C0002.m230(strDecode);
                            }
                            break;
                        case 1749663:
                            if (C0001.f1 <= 0) {
                                strDecode = NPStringFog.decode("B5D2B6C0B5FE");
                                iM562 = C0002.m230(strDecode);
                            } else {
                                iM562 = (C0002.f2 ^ C0001.f1) + 56804;
                            }
                        case 1750563:
                            return (AssetFileDescriptor) C0053.m542(m384(), assetManager, new Object[]{str});
                        case 1754380:
                            return m385(C0053.m559(C0002.m234(), str), str);
                        case 1755553:
                            if (C0001.f1 > 0) {
                            }
                            break;
                    }
                }
                strDecode2 = NPStringFog.decode("B5EFB6C2B5C4");
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
        @MethodParams({String.class, int.class})
        @HookMethod("open")
        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        public static InputStream m382(@ThisObject AssetManager assetManager, String str, int i) {
            String strDecode;
            String strDecode2;
            String strDecode3 = NPStringFog.decode("B5D0B6FEB5FE");
            int iM503 = C0052.m503(strDecode3);
            InputStream inputStream = null;
            InputStream inputStreamM387 = null;
            while (true) {
                InputStream inputStream2 = inputStreamM387;
                while (true) {
                    String strDecode4 = NPStringFog.decode("B5EFB6C6B5C7");
                    switch (iM503) {
                        case 1746694:
                            if (C0052.m520() <= 0) {
                                C0002.m259();
                                strDecode = NPStringFog.decode("B5D4B6C9B5C5");
                            } else {
                                strDecode = strDecode3;
                            }
                            iM503 = C0052.m503(strDecode);
                        case 1746942:
                            if (!C0052.m502(m386(), str)) {
                                strDecode2 = NPStringFog.decode("B5D6B6C5B5C4");
                                iM503 = C0043.m455(strDecode2);
                            } else if ((C0043.f73 ^ (C0052.f91 * 6378)) <= 0) {
                                strDecode2 = strDecode3;
                                iM503 = C0043.m455(strDecode2);
                            } else {
                                iM503 = (C0002.f2 % C0052.f91) ^ (-1747824);
                            }
                        case 1747648:
                            inputStream = (InputStream) C0053.m541(m383(), assetManager, new Object[]{str, C0052.m510(i)});
                            if (C0001.m192() >= 0) {
                                iM503 = C0002.m230(strDecode4);
                            } else {
                                strDecode2 = NPStringFog.decode("B5D1B6C5B5C1");
                                iM503 = C0043.m455(strDecode2);
                            }
                        case 1747748:
                            inputStreamM387 = m387(inputStream);
                            if (C0043.m456() <= 0) {
                                C0002.m259();
                                strDecode = NPStringFog.decode("B5EFB6FEB5C7");
                            } else {
                                strDecode = NPStringFog.decode("B5D3B6C1B5C7");
                            }
                            iM503 = C0052.m503(strDecode);
                        case 1748765:
                            if (C0052.f91 - (C0052.f91 ^ 1351) <= 0) {
                                C0001.m192();
                                strDecode4 = NPStringFog.decode("B5D8B6C4B5C6");
                            }
                            iM503 = C0002.m230(strDecode4);
                            inputStream2 = inputStream;
                        case 1750569:
                            break;
                        case 1753575:
                            break;
                        case 1755530:
                            strDecode2 = NPStringFog.decode("B5D6B6C5B5C4");
                            iM503 = C0043.m455(strDecode2);
                    }
                    return inputStream2;
                }
                iM503 = (C0052.f91 ^ C0053.f92) + 1752667;
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x001b. Please report as an issue. */
        /* renamed from: ۣ۟ۥۢۨ, reason: not valid java name and contains not printable characters */
        public static Method m383() {
            String strDecode;
            int i;
            int i2;
            String strDecode2;
            String strDecode3 = NPStringFog.decode("B5D5B6C7B5FE");
            int iM190 = C0001.m190(strDecode3);
            Method method = null;
            while (true) {
                Method method2 = method;
                while (true) {
                    String strDecode4 = NPStringFog.decode("B5D5B6C0B5C2");
                    String strDecode5 = "۠ۦ۠";
                    strDecode = NPStringFog.decode("B5D4B6C0B5C6");
                    switch (iM190) {
                        case 56292:
                            if (C0053.f92 >= 0) {
                                iM190 = C0052.m503(NPStringFog.decode("B5D2B6C5B5C9"));
                                method2 = null;
                            } else {
                                method2 = null;
                                iM190 = C0052.m503(strDecode5);
                            }
                        case 1747808:
                            strDecode5 = strDecode;
                            iM190 = C0052.m503(strDecode5);
                        case 1747866:
                            if ((C0052.f91 ^ (C0001.f1 % 4662)) >= 0) {
                                C0002.f2 = 61;
                                iM190 = C0053.m562(strDecode4);
                            } else {
                                i = C0002.f2 | C0052.f91;
                                i2 = -1751556;
                                iM190 = i ^ i2;
                            }
                        case 1748611:
                            method = f32;
                            if (C0043.f73 >= 0) {
                                C0001.m192();
                                strDecode2 = NPStringFog.decode("B5EFB6C2");
                                iM190 = C0002.m230(strDecode2);
                            } else {
                                iM190 = C0001.m190(strDecode4);
                            }
                        case 1749734:
                        case 1751554:
                            if (C0052.f91 % (C0001.f1 ^ 736) >= 0) {
                                C0001.f1 = 55;
                                strDecode5 = NPStringFog.decode("B5D5B6FEB5C0");
                                iM190 = C0052.m503(strDecode5);
                            } else {
                                i = C0052.f91 + C0053.f92;
                                i2 = -1752456;
                                iM190 = i ^ i2;
                            }
                        case 1750564:
                            if (C0043.f73 * (C0053.f92 ^ (-6973)) >= 0) {
                                C0002.f2 = 14;
                                strDecode4 = NPStringFog.decode("B5D6B6C0B5C7");
                            } else {
                                strDecode4 = strDecode3;
                            }
                            iM190 = C0001.m190(strDecode4);
                        case 1751562:
                            if (C0002.f2 >= 0) {
                                C0052.m520();
                                strDecode2 = NPStringFog.decode("B5D0B6C5B5C5");
                                iM190 = C0002.m230(strDecode2);
                            } else {
                                iM190 = (C0043.f73 * C0053.f92) - 79646;
                            }
                        case 1752519:
                            if (C0002.f2 >= 0) {
                                break;
                            }
                            strDecode2 = NPStringFog.decode("B5D5B6C4B5C9");
                            method2 = method;
                            iM190 = C0002.m230(strDecode2);
                            break;
                        case 1752648:
                            break;
                        case 1752670:
                            if (C0053.m574() >= 0) {
                                strDecode5 = strDecode;
                                iM190 = C0052.m503(strDecode5);
                            } else if (C0052.f91 + (C0043.f73 / 6961) >= 0) {
                                C0052.m520();
                                iM190 = C0002.m230("۠ۦ۠");
                            } else {
                                iM190 = (C0052.f91 ^ C0053.f92) + 1747703;
                            }
                    }
                    return method2;
                }
                iM190 = C0001.m190(strDecode);
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:63:0x00f9 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x00e9 A[SYNTHETIC] */
        /* renamed from: ۟ۤۨۨۦ, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static HookWrapper.HookEntity m384() {
            String strDecode;
            int i;
            int i2;
            String strDecode2;
            int i3;
            int i4;
            int iM503 = C0052.m503(NPStringFog.decode("B5D0B6FE"));
            HookWrapper.HookEntity hookEntity = null;
            HookWrapper.HookEntity hookEntity2 = null;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D3B6C6B5C2");
                switch (iM503) {
                    case 56319:
                        if (C0001.m192() <= 0) {
                            strDecode2 = C0002.m259() >= 0 ? NPStringFog.decode("B5D7B6C2B5C6") : NPStringFog.decode("B5D6B6C3B5C9");
                            iM503 = C0001.m190(strDecode2);
                        } else if (C0053.m574() < 0) {
                            C0043.f73 = 14;
                            strDecode = NPStringFog.decode("B5D5B6C3B5C4");
                            iM503 = C0043.m455(strDecode);
                        } else {
                            i = C0043.f73 ^ C0002.f2;
                            i2 = 1746452;
                            iM503 = i + i2;
                        }
                    case 56350:
                        if (C0053.m574() < 0) {
                        }
                        break;
                    case 1746689:
                        if (C0001.m192() >= 0) {
                            C0001.m192();
                            strDecode2 = NPStringFog.decode("B5D2B6C6B5C2");
                            iM503 = C0001.m190(strDecode2);
                        } else {
                            i3 = C0043.f73 - C0043.f73;
                            i4 = 1752459;
                            iM503 = i3 ^ i4;
                        }
                    case 1749822:
                        if (C0053.m574() >= 0) {
                            iM503 = C0053.m562(NPStringFog.decode("B5D3B6C4B5C1"));
                        } else {
                            i3 = C0053.f92 ^ C0001.f1;
                            i4 = -55730;
                            iM503 = i3 ^ i4;
                        }
                    case 1750783:
                        break;
                    case 1751497:
                        if (C0052.f91 / (C0001.f1 ^ (-1718)) != 0) {
                            C0001.f1 = 6;
                            strDecode = NPStringFog.decode("B5D1B6FE");
                            iM503 = C0043.m455(strDecode);
                        } else {
                            i = C0053.f92 + C0001.f1;
                            i2 = 1753102;
                            iM503 = i + i2;
                        }
                    case 1751718:
                        if (C0002.f2 >= 0) {
                            C0001.m192();
                            iM503 = C0001.m190(NPStringFog.decode("B5EFB6FEB5C0"));
                        } else {
                            iM503 = C0043.m455(strDecode3);
                        }
                        hookEntity = hookEntity2;
                    case 1752459:
                        if ((C0052.f91 | (C0002.f2 ^ 7675)) >= 0) {
                            C0043.f73 = 56;
                        }
                        iM503 = C0053.m562(NPStringFog.decode("B5D4B6FEB5C5"));
                        hookEntity = null;
                    case 1752643:
                    case 1754507:
                        if (C0002.m259() >= 0) {
                            strDecode3 = NPStringFog.decode("B5D1B6C7B5C1");
                        }
                        iM503 = C0001.m190(strDecode3);
                    case 1753516:
                        hookEntity2 = f33;
                        if (C0002.f2 % (C0053.f92 % (-441)) >= 0) {
                            C0002.m259();
                            iM503 = C0001.m190(strDecode2);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D4B6C7B5C9");
                            iM503 = C0001.m190(strDecode2);
                        }
                }
                return hookEntity;
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:70:0x0079 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0084 A[SYNTHETIC] */
        /* renamed from: ۟ۥۣۧۢ, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static AssetFileDescriptor m385(Object obj, Object obj2) {
            int i;
            int i2;
            String strDecode;
            String strDecode2;
            int iM562 = C0053.m562(NPStringFog.decode("B5D3B6C4B5C7"));
            AssetFileDescriptor assetFileDescriptorM343 = null;
            while (true) {
                AssetFileDescriptor assetFileDescriptor = assetFileDescriptorM343;
                while (true) {
                    String strDecode3 = NPStringFog.decode("B5D6B6C3B5C1");
                    switch (iM562) {
                        case 56481:
                        case 1748742:
                            if (C0052.m520() <= 0) {
                                C0053.f92 = 30;
                                strDecode3 = NPStringFog.decode("B5D1B6C6B5C9");
                            }
                            iM562 = C0001.m190(strDecode3);
                        case 56567:
                            assetFileDescriptor = null;
                            iM562 = C0053.m562(NPStringFog.decode("B5D5B6FEB5C9"));
                        case 1747835:
                            if (C0053.m574() >= 0) {
                                strDecode = NPStringFog.decode("B5D2B6C4B5C5");
                                iM562 = C0002.m230(strDecode);
                            } else {
                                i = C0053.f92 | C0052.f91;
                                i2 = 1750758;
                                iM562 = i + i2;
                            }
                        case 1749601:
                            if ((C0001.f1 | (C0053.f92 + 3469)) <= 0) {
                                C0043.m456();
                                iM562 = C0043.m455("ۢۧ۟");
                            } else {
                                iM562 = (C0002.f2 | C0002.f2) + 1753584;
                            }
                        case 1749818:
                            if (C0002.f2 >= 0) {
                                C0001.f1 = 10;
                                strDecode2 = NPStringFog.decode("B5D0B6C4B5C1");
                                iM562 = C0001.m190(strDecode2);
                            } else {
                                i = C0052.f91 % C0001.f1;
                                i2 = 56729;
                                iM562 = i + i2;
                            }
                        case 1750724:
                            if (C0001.m192() < 0) {
                                if (C0052.f91 + (C0053.f92 % 2460) >= 0) {
                                    strDecode = NPStringFog.decode("B5D8B6FE");
                                    iM562 = C0002.m230(strDecode);
                                } else {
                                    i = C0043.f73 / C0002.f2;
                                    i2 = 1752672;
                                    iM562 = i + i2;
                                }
                            } else if ((C0043.f73 ^ (C0001.f1 % 8628)) < 0) {
                                iM562 = C0052.m503(NPStringFog.decode("B5D1B6C9B5C1"));
                            } else {
                                strDecode3 = "ۢۧ۟";
                                iM562 = C0001.m190(strDecode3);
                            }
                        case 1752462:
                            if (C0001.f1 <= 0) {
                                C0002.m259();
                                iM562 = C0001.m190(strDecode3);
                            } else {
                                strDecode2 = NPStringFog.decode("B5D5B6C7");
                                iM562 = C0001.m190(strDecode2);
                            }
                        case 1752674:
                            assetFileDescriptorM343 = C0011.m343((InputStream) obj, (String) obj2);
                            if (C0002.f2 * C0053.f92 * 7579 <= 0) {
                                C0043.f73 = 18;
                                iM562 = C0053.m562(NPStringFog.decode("B5D5B6FEB5C9"));
                            } else {
                                i = C0043.f73 % C0001.f1;
                                i2 = 1749768;
                                iM562 = i + i2;
                            }
                        case 1752739:
                            if ((C0043.f73 ^ (C0001.f1 % 8628)) < 0) {
                            }
                            break;
                        case 1753508:
                            break;
                    }
                    return assetFileDescriptor;
                }
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:56:0x00d1 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00c1 A[SYNTHETIC] */
        /* renamed from: ۧۨۢۢ, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static Set m386() {
            int i;
            int i2;
            String strDecode;
            String strDecode2 = NPStringFog.decode("B5D1B6FEB5C6");
            int iM562 = C0053.m562(strDecode2);
            Set set = null;
            Set setM359 = null;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D0B6FEB5C7");
                switch (iM562) {
                    case 56451:
                        if (C0002.f2 / (C0053.f92 | 2184) == 0) {
                            C0052.f91 = 18;
                            iM562 = C0001.m190(NPStringFog.decode("B5D6B6C9B5FE"));
                        } else {
                            iM562 = (C0002.f2 | C0001.f1) ^ (-1747914);
                        }
                    case 1746912:
                    case 1752550:
                        if (C0053.f92 % (C0043.f73 * 3681) >= 0) {
                            C0053.f92 = 74;
                            strDecode3 = NPStringFog.decode("B5D8B6C4B5C1");
                        }
                        iM562 = C0053.m562(strDecode3);
                    case 1747655:
                        break;
                    case 1747715:
                        if (C0052.m520() <= 0) {
                            C0043.m456();
                            strDecode = NPStringFog.decode("B5EFB6C0");
                            iM562 = C0053.m562(strDecode);
                        } else {
                            i = C0001.f1 % C0001.f1;
                            i2 = 1748617;
                            iM562 = i + i2;
                        }
                    case 1747905:
                        if ((C0002.f2 ^ (C0002.f2 - 91)) <= 0) {
                            C0052.m520();
                            iM562 = C0052.m503(strDecode2);
                        }
                    case 1748617:
                        if (C0053.m574() <= 0) {
                            iM562 = C0043.m455(NPStringFog.decode("B5D7B6C1B5C4"));
                        } else if (C0002.f2 / (C0053.f92 | 2184) == 0) {
                        }
                        break;
                    case 1748677:
                        if (C0002.m259() >= 0) {
                            C0002.m259();
                            strDecode3 = NPStringFog.decode("B5D4B6C6");
                            set = null;
                        } else {
                            strDecode3 = NPStringFog.decode("B5D2B6C6B5C0");
                            set = null;
                            iM562 = C0053.m562(strDecode3);
                        }
                    case 1749820:
                        strDecode = NPStringFog.decode("B5D5B6C3B5C2");
                        iM562 = C0053.m562(strDecode);
                    case 1753632:
                        set = setM359;
                        iM562 = C0053.f92 - (C0052.f91 % (-7731)) >= 0 ? C0052.m503(NPStringFog.decode("B5D1B6C0B5C4")) : C0002.m230(strDecode3);
                    case 1754412:
                        setM359 = C0011.m359();
                        i = C0043.f73 / C0052.f91;
                        i2 = 1753631;
                        iM562 = i + i2;
                }
                return set;
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:53:0x008e A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x007f A[SYNTHETIC] */
        /* renamed from: ۧۨۢۧ, reason: not valid java name and contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static InputStream m387(Object obj) {
            String strDecode;
            int i;
            int i2;
            int iM562 = C0053.m562(NPStringFog.decode("B5D1B6C6B5C4"));
            InputStream inputStream = null;
            InputStream inputStreamM366 = null;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D0B6C0B5C5");
                switch (iM562) {
                    case 56323:
                        iM562 = (C0053.f92 ^ (C0001.f1 + 7370)) >= 0 ? C0052.m503(NPStringFog.decode("B5D4B6FEB5C4")) : C0001.m190(NPStringFog.decode("B5D2B6C9B5C6"));
                    case 1747715:
                        break;
                    case 1748863:
                        if (C0002.m259() <= 0) {
                            strDecode2 = NPStringFog.decode("B5D8B6C1B5C5");
                            iM562 = C0053.m562(strDecode2);
                        } else if (C0001.f1 > 0) {
                            C0052.f91 = 23;
                            strDecode = NPStringFog.decode("B5D6B6C2B5C2");
                            iM562 = C0002.m230(strDecode);
                        } else {
                            iM562 = (C0052.f91 - C0001.f1) ^ (-1753967);
                        }
                    case 1749857:
                    case 1750757:
                        iM562 = C0053.m562(strDecode2);
                    case 1750754:
                        iM562 = C0002.f2 + C0002.f2 + 56475;
                        inputStream = null;
                    case 1751498:
                        if (C0001.f1 > 0) {
                        }
                        break;
                    case 1752459:
                        i = C0001.f1 + C0043.f73;
                        i2 = 1748675;
                        iM562 = i + i2;
                    case 1753450:
                        if (C0043.f73 >= 0) {
                            strDecode = NPStringFog.decode("B5D8B6C4B5C3");
                            iM562 = C0002.m230(strDecode);
                        } else {
                            i = C0002.f2 - C0052.f91;
                            i2 = 1750668;
                            iM562 = i + i2;
                        }
                    case 1755372:
                        inputStreamM366 = C0011.m366((InputStream) obj);
                        if (C0002.m259() >= 0) {
                            iM562 = C0053.m562(strDecode2);
                        } else {
                            i = C0001.f1 | C0052.f91;
                            i2 = 1755654;
                            iM562 = i + i2;
                        }
                    case 1755525:
                        if (C0002.f2 >= 0) {
                            iM562 = C0043.m455(NPStringFog.decode("B5D3B6C7B5C4"));
                            inputStream = inputStreamM366;
                        } else {
                            inputStream = inputStreamM366;
                            iM562 = C0053.m562(strDecode2);
                        }
                }
                return inputStream;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0049 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x003b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C0011() throws NumberFormatException {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D8B6C9B5C7");
        int iM455 = C0043.m455(strDecode3);
        Long lValueOf = null;
        while (true) {
            switch (iM455) {
                case 1746850:
                    lValueOf = Long.valueOf(C0002.m256(NPStringFog.decode("2B355A1000")));
                    if (C0001.m192() >= 0) {
                        C0053.f92 = 13;
                        strDecode = NPStringFog.decode("B5D2B6C5B5C6");
                        iM455 = C0053.m562(strDecode);
                    } else {
                        iM455 = (C0052.f91 * C0052.f91) ^ 1760359;
                    }
                case 1749733:
                    break;
                case 1751779:
                    System.out.println(lValueOf);
                    strDecode = NPStringFog.decode("B5D2B6C5B5C6");
                    iM455 = C0053.m562(strDecode);
                case 1754561:
                    i = C0043.f73 | C0053.f92;
                    i2 = 1755659;
                    iM455 = i + i2;
                case 1755375:
                    if (C0053.f92 + (C0002.f2 / 516) < 0) {
                        C0043.m456();
                        strDecode = NPStringFog.decode("B5EFB6C5B5C3");
                        iM455 = C0053.m562(strDecode);
                    } else {
                        i = C0043.f73 / C0001.f1;
                        i2 = 1749733;
                        iM455 = i + i2;
                    }
                case 1755622:
                    if (C0043.m456() <= 0) {
                        if (C0052.f91 * C0053.f92 * (-8850) >= 0) {
                            C0043.m456();
                            strDecode2 = strDecode3;
                        } else {
                            strDecode2 = NPStringFog.decode("B5EFB6C5B5C6");
                        }
                        iM455 = C0002.m230(strDecode2);
                    } else if (C0053.f92 + (C0002.f2 / 516) < 0) {
                    }
                    break;
            }
            return;
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    static /* synthetic */ AssetFileDescriptor m343(InputStream inputStream, String str) {
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D6B6C4B5C0");
        int iM230 = C0002.m230(strDecode2);
        while (iM230 != 1753602) {
            if (iM230 == 1755586) {
                if (C0052.m520() <= 0) {
                    C0053.f92 = 50;
                    strDecode = NPStringFog.decode("B5D5B6C3B5FE");
                } else {
                    strDecode = strDecode2;
                }
                iM230 = C0043.m455(strDecode);
            }
        }
        return m373(inputStream, str);
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private static InputStream m345(InputStream inputStream, byte[] bArr) throws Throwable {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3;
        String strDecode4;
        String strDecode5;
        String strDecode6 = NPStringFog.decode("B5D6B6C1B5C9");
        int iM503 = C0052.m503(strDecode6);
        ByteArrayInputStream byteArrayInputStream = null;
        Throwable th = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        while (true) {
            String strDecode7 = NPStringFog.decode("B5D3B6FEB5C1");
            switch (iM503) {
                case 56482:
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(C0002.m257(byteArrayOutputStream));
                    if (C0052.m520() <= 0) {
                        byteArrayInputStream = byteArrayInputStream2;
                        iM503 = C0052.m503(NPStringFog.decode("B5D4B6C2B5C0"));
                    } else {
                        strDecode = NPStringFog.decode("B5D4B6C6B5C6");
                        byteArrayInputStream = byteArrayInputStream2;
                        strDecode3 = strDecode;
                        iM503 = C0043.m455(strDecode3);
                    }
                case 1746724:
                    return byteArrayInputStream;
                case 1748642:
                    if ((C0001.f1 ^ (C0043.f73 / (-5651))) <= 0) {
                        strDecode7 = NPStringFog.decode("B5D4B6C0B5C9");
                        iM503 = C0002.m230(strDecode7);
                    } else {
                        i = C0001.f1 / C0043.f73;
                        i2 = 1755592;
                        iM503 = i + i2;
                    }
                case 1748892:
                    if (C0052.f91 >= 0) {
                        C0053.f92 = 83;
                        strDecode2 = NPStringFog.decode("B5D5B6C9B5C3");
                        iM503 = C0052.m503(strDecode2);
                    } else {
                        iM503 = C0002.m230(strDecode7);
                    }
                case 1749668:
                case 1753634:
                    strDecode3 = NPStringFog.decode("B5D4B6C7B5C1");
                    iM503 = C0043.m455(strDecode3);
                case 1750532:
                    i = C0002.f2 ^ C0052.f91;
                    i2 = 1753400;
                    iM503 = i + i2;
                case 1751618:
                    i = C0001.f1 / C0001.f1;
                    i2 = 1751747;
                    iM503 = i + i2;
                case 1751710:
                    throw th;
                case 1751748:
                    C0053.m566(byteArrayOutputStream);
                    i = C0002.f2 ^ C0002.f2;
                    i2 = 1746724;
                    iM503 = i + i2;
                case 1753454:
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    if (C0043.f73 >= 0) {
                        C0001.m192();
                        byteArrayOutputStream = byteArrayOutputStream2;
                        iM503 = C0052.m503(strDecode7);
                    } else {
                        strDecode = NPStringFog.decode("B5D8B6C9B5C3");
                        byteArrayOutputStream = byteArrayOutputStream2;
                        strDecode3 = strDecode;
                        iM503 = C0043.m455(strDecode3);
                    }
                case 1753480:
                    if (C0001.f1 <= 0) {
                        C0043.m456();
                        strDecode4 = NPStringFog.decode("B5EFB6C6B5C6");
                    } else {
                        strDecode4 = strDecode6;
                    }
                    iM503 = C0053.m562(strDecode4);
                case 1755466:
                    if (C0053.f92 / (C0001.f1 ^ (-3174)) != 0) {
                        strDecode3 = NPStringFog.decode("B5D0B6C3B5C3");
                        iM503 = C0043.m455(strDecode3);
                    } else {
                        iM503 = (C0001.f1 ^ C0043.f73) ^ (-1755176);
                    }
                case 1755590:
                    try {
                        C0053.m566(byteArrayOutputStream);
                        iM503 = C0052.m503(strDecode7);
                    } catch (Throwable th2) {
                        C0052.m525(th, th2);
                        if ((C0001.f1 | (C0001.f1 * (-6703))) < 0) {
                            strDecode4 = NPStringFog.decode("B5D2B6C3B5C5");
                            break;
                        } else {
                            strDecode5 = NPStringFog.decode("B5D8B6C6B5C4");
                            iM503 = C0001.m190(strDecode5);
                        }
                    }
                case 1755618:
                    try {
                        C0053.m556(inputStream, byteArrayOutputStream, bArr);
                        strDecode2 = NPStringFog.decode("B5D5B6C6");
                        iM503 = C0052.m503(strDecode2);
                    } catch (Throwable th3) {
                        th = th3;
                        strDecode5 = NPStringFog.decode("B5D1B6C1B5C0");
                        iM503 = C0001.m190(strDecode5);
                    }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0028. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01c5  */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m346(int i, String str, String str2, String str3, String str4) throws NumberFormatException {
        int i2;
        int i3;
        String strDecode;
        int i4;
        int i5;
        String strDecode2;
        int i6;
        int i7;
        String str5 = str;
        String strDecode3 = NPStringFog.decode("B5D2B6C5B5C4");
        int iM230 = C0002.m230(strDecode3);
        byte[] bArrM579 = null;
        byte[] bArrM5792 = null;
        String[] strArr = null;
        byte[] bArrM374 = null;
        String[] strArrM253 = null;
        HashSet hashSet = null;
        String[] strArr2 = null;
        String strM256 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D3B6C3B5C3");
            String strDecode5 = "ۣۨۨ";
            String strDecode6 = NPStringFog.decode("B5D5B6C0B5C2");
            String strDecode7 = "ۡۨۤ";
            switch (iM230) {
                case 56351:
                    if (C0043.m456() <= 0) {
                        C0053.f92 = 85;
                        strDecode2 = NPStringFog.decode("B5D7B6C4");
                        iM230 = C0053.m562(strDecode2);
                        str5 = str;
                    } else {
                        strDecode6 = NPStringFog.decode("B5D0B6C5B5C2");
                        iM230 = C0001.m190(strDecode6);
                        str5 = str;
                    }
                case 56385:
                    m372(bArrM579);
                    bArrM374 = m374(bArrM579, bArrM5792);
                    iM230 = C0043.m455(NPStringFog.decode("B5D2B6FEB5C1"));
                    str5 = str;
                case 56388:
                case 1747711:
                case 1747807:
                    if (C0043.m456() <= 0) {
                        strDecode7 = NPStringFog.decode("B5D3B6C0B5C5");
                        iM230 = C0053.m562(strDecode7);
                        str5 = str;
                    } else {
                        i2 = C0002.f2 % C0002.f2;
                        i3 = 1748893;
                        iM230 = i3 + i2;
                        str5 = str;
                    }
                case 56450:
                    m360();
                    iM230 = C0053.m562(strDecode7);
                    str5 = str;
                case 56542:
                    bArrM579 = C0053.m579(str2);
                    m367(bArrM579);
                    if (C0043.f73 - (C0052.f91 * (-9665)) >= 0) {
                        C0053.f92 = 64;
                        iM230 = C0001.m190(strDecode6);
                        str5 = str;
                    } else {
                        strDecode = NPStringFog.decode("B5D2B6C2");
                        iM230 = C0001.m190(strDecode);
                        str5 = str;
                    }
                case 1747648:
                    if (i != 2) {
                        m362();
                        i2 = C0002.f2 / C0001.f1;
                        i3 = 1751650;
                        iM230 = i3 + i2;
                        str5 = str;
                    }
                    i4 = C0052.f91 + C0001.f1;
                    i5 = 1747491;
                    iM230 = i5 ^ i4;
                    str5 = str;
                case 1747682:
                    m370();
                    if (C0002.f2 >= 0) {
                        iM230 = C0052.m503(NPStringFog.decode("B5D2B6C7"));
                        str5 = str;
                    } else {
                        i2 = C0052.f91 * C0002.f2;
                        i3 = 44039;
                        iM230 = i3 + i2;
                        str5 = str;
                    }
                case 1747773:
                    if (C0053.m550()) {
                        strArr = new String[3];
                        if (C0052.f91 / (C0001.f1 | 8767) != 0) {
                            C0001.f1 = 51;
                            strDecode4 = NPStringFog.decode("B5D0B6C9B5C2");
                        }
                        iM230 = C0002.m230(strDecode4);
                        str5 = str;
                    }
                    i2 = C0043.f73 | C0002.f2;
                    i3 = 1747934;
                    iM230 = i3 + i2;
                    str5 = str;
                case 1747774:
                    strArr[2] = C0043.m454(m371(), 41, C0052.f91 ^ (-172), 2285);
                    if (C0043.f73 >= 0) {
                        C0052.m520();
                        iM230 = C0043.m455(NPStringFog.decode("B5D0B6FEB5FE"));
                    } else {
                        iM230 = 1753644 + (C0053.f92 % C0001.f1);
                    }
                    str5 = str;
                    strArr2 = strArr;
                case 1747873:
                case 1749632:
                    i4 = C0053.f92 / C0002.f2;
                    i5 = 1750638;
                    iM230 = i5 ^ i4;
                    str5 = str;
                case 1747931:
                    strArrM253 = C0002.m253(m369(C0053.m573(C0011.class, C0002.m248(C0001.m211(C0001.m211(new StringBuilder(C0053.m577(m371(), 51, C0043.f73 ^ (-175), 2441)), str4), C0052.m507(m371(), 59, C0002.f2 ^ (-80), 1378)))), bArrM374), C0043.m454(m371(), 63, 1, 502));
                    iM230 = C0001.m190(strDecode6);
                    str5 = str;
                case 1748893:
                    if (C0043.m456() <= 0) {
                        strM256 = C0002.m256(NPStringFog.decode("0637070C5E15"));
                        if (C0053.m574() >= 0) {
                            C0002.f2 = 77;
                        } else {
                            strDecode5 = NPStringFog.decode("B5D8B6C1B5C3");
                            strDecode6 = strDecode5;
                            iM230 = C0001.m190(strDecode6);
                            str5 = str;
                        }
                    } else if ((C0002.f2 | (C0001.f1 ^ (-7599))) < 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D1B6C7B5C2");
                        iM230 = C0001.m190(strDecode);
                        str5 = str;
                    } else {
                        strDecode7 = "ۣۨۨ";
                        iM230 = C0053.m562(strDecode7);
                        str5 = str;
                    }
                case 1749571:
                    f30 = bArrM374;
                    f26 = str5;
                    i6 = C0001.f1 % C0052.f91;
                    i7 = 1747742;
                    iM230 = i6 + i7;
                case 1749731:
                    C0002.m218(new File(str5, C0053.m577(m371(), 0, C0052.f91 ^ (-175), 2257)));
                    bArrM5792 = C0053.m579(str3);
                    if ((C0001.f1 ^ (C0001.f1 * 987)) <= 0) {
                        C0053.f92 = 51;
                        iM230 = C0053.m562(strDecode3);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D7B6C4");
                        iM230 = C0053.m562(strDecode2);
                        str5 = str;
                    }
                case 1750627:
                    strArr[0] = C0052.m507(m371(), 15, C0001.f1 ^ 361, 1539);
                    strArr[1] = C0002.m242(m371(), 25, C0043.f73 ^ (-183), 1031);
                    if (C0001.f1 % (C0052.f91 ^ 6429) <= 0) {
                        C0001.m192();
                        iM230 = C0053.m562("ۡۨۤ");
                    } else {
                        strDecode2 = NPStringFog.decode("B5D0B6C2B5C0");
                        iM230 = C0053.m562(strDecode2);
                        str5 = str;
                    }
                case 1750628:
                    hashSet = new HashSet();
                    f31 = hashSet;
                    iM230 = C0053.f92 + (C0053.f92 % (-1058)) >= 0 ? C0053.m562(NPStringFog.decode("B5D6B6FEB5C9")) : C0001.m190(NPStringFog.decode("B5D7B6C6B5C7"));
                case 1750750:
                    i6 = C0053.f92 * C0053.f92;
                    i7 = 1087135;
                    iM230 = i6 + i7;
                case 1751650:
                    iM230 = C0001.f1 / (C0043.f73 + 6145) != 0 ? C0053.m562(strDecode4) : (C0002.f2 + C0053.f92) ^ (-57150);
                case 1752519:
                    if (C0001.m192() >= 0) {
                        strArr2 = strArrM253;
                        iM230 = C0043.m455(NPStringFog.decode("B5D2B6FEB5C1"));
                        str5 = str;
                    } else {
                        iM230 = (C0053.f92 % C0001.f1) ^ (-1750532);
                        strArr2 = strArrM253;
                    }
                case 1752670:
                    i2 = C0043.f73 | C0002.f2;
                    i3 = 1747934;
                    iM230 = i3 + i2;
                    str5 = str;
                case 1752710:
                    i4 = C0052.f91 + C0001.f1;
                    i5 = 1747491;
                    iM230 = i5 ^ i4;
                    str5 = str;
                case 1752733:
                    if ((C0002.f2 | (C0001.f1 ^ (-7599))) < 0) {
                    }
                    break;
                case 1753423:
                    if (C0053.f92 - (C0002.f2 | (-349)) < 0) {
                        C0002.f2 = 42;
                        iM230 = C0052.m503(NPStringFog.decode("B5D4B6C4B5C2"));
                    } else {
                        i6 = C0043.f73 + C0052.f91;
                        i7 = 56779;
                        iM230 = i6 + i7;
                    }
                case 1753540:
                    i6 = C0053.f92 | C0052.f91;
                    i7 = 1747907;
                    iM230 = i6 + i7;
                case 1754630:
                    C0002.m220(hashSet, C0052.m516(strArr2));
                    if (i != 1) {
                        if ((C0001.f1 ^ (C0052.f91 - 1729)) >= 0) {
                            C0053.f92 = 17;
                            strDecode5 = NPStringFog.decode("B5D8B6C1B5C3");
                            strDecode6 = strDecode5;
                            iM230 = C0001.m190(strDecode6);
                            str5 = str;
                        } else {
                            i6 = C0001.f1 + C0052.f91;
                            i7 = 1747455;
                            iM230 = i6 + i7;
                        }
                    } else if (C0053.f92 - (C0002.f2 | (-349)) < 0) {
                    }
                    break;
                case 1755370:
                    System.out.println(strM256);
                    if ((C0002.f2 ^ (C0002.f2 - 1457)) <= 0) {
                        C0043.m456();
                        iM230 = C0002.m230(NPStringFog.decode("B5D4B6C7"));
                    } else {
                        strDecode6 = strDecode5;
                        iM230 = C0001.m190(strDecode6);
                        str5 = str;
                    }
                case 1755469:
                    break;
                default:
                    str5 = str;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0017. Please report as an issue. */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private static byte[] m347(byte[] bArr, byte[] bArr2) {
        String strDecode;
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D7B6C6B5C3");
        int iM562 = C0053.m562(strDecode3);
        byte[] bArr3 = null;
        int i5 = 0;
        int i6 = 0;
        byte b = 0;
        byte b2 = 0;
        int length = 0;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D4B6C3B5C3");
            switch (iM562) {
                case 56389:
                    if (C0043.m456() <= 0) {
                        C0052.f91 = 69;
                        iM562 = C0002.m230(NPStringFog.decode("B5D6B6FEB5C0"));
                        i6 = i5;
                    } else {
                        strDecode4 = NPStringFog.decode("B5D0B6C7B5FE");
                        i6 = i5;
                        iM562 = C0001.m190(strDecode4);
                    }
                case 1746758:
                    i5 = i6 + (C0043.f73 ^ (-168));
                    if (C0002.m259() >= 0) {
                        strDecode = NPStringFog.decode("B5D7B6C0B5C9");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i = C0001.f1 + C0053.f92;
                        i2 = -56720;
                        iM562 = i ^ i2;
                    }
                case 1747865:
                    if (C0002.f2 - (C0002.f2 % (-6503)) != 0) {
                        C0002.f2 = 77;
                        iM562 = C0053.m562(strDecode3);
                    } else {
                        i3 = C0043.f73 | C0002.f2;
                        i4 = 1752611;
                        iM562 = i3 + i4;
                    }
                case 1750633:
                    i3 = C0043.f73 % C0043.f73;
                    i4 = 1754626;
                    iM562 = i3 + i4;
                case 1751558:
                    i3 = C0001.f1 ^ C0052.f91;
                    i4 = 1753867;
                    iM562 = i3 + i4;
                case 1751588:
                    i = C0001.f1 - C0002.f2;
                    i2 = 1751858;
                    iM562 = i ^ i2;
                case 1751656:
                    if (i6 >= bArr.length) {
                        i3 = C0001.f1 ^ C0052.f91;
                        i4 = 1753867;
                        iM562 = i3 + i4;
                    } else if ((C0001.f1 | (C0043.f73 * 9776)) >= 0) {
                        C0052.m520();
                    } else {
                        i3 = C0052.f91 ^ C0002.f2;
                        i4 = 1753336;
                        iM562 = i3 + i4;
                    }
                case 1751682:
                    bArr3[i6] = (byte) (((b ^ (-1)) & b2) | ((b2 ^ (-1)) & b));
                    iM562 = C0053.f92 * (C0043.f73 * 7348) <= 0 ? C0053.m562(NPStringFog.decode("B5D6B6C2B5C9")) : C0002.m230(NPStringFog.decode("B5EFB6C0B5C9"));
                case 1751709:
                    iM562 = C0052.f91 + C0053.f92 + 1752632;
                    i6 = 0;
                case 1752608:
                case 1753547:
                    if (C0001.f1 - (C0052.f91 * 3546) <= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D2B6C0B5C2");
                        iM562 = C0043.m455(strDecode);
                    } else {
                        i3 = C0001.f1 | C0053.f92;
                        i4 = 1752181;
                        iM562 = i3 + i4;
                    }
                case 1753416:
                    break;
                case 1753570:
                    b = bArr3[i6];
                    if (C0001.f1 <= 0) {
                        strDecode2 = NPStringFog.decode("B5D3B6C3B5C9");
                        iM562 = C0052.m503(strDecode2);
                    } else {
                        i3 = C0052.f91 ^ C0043.f73;
                        i4 = 1754439;
                        iM562 = i3 + i4;
                    }
                case 1754446:
                    b2 = bArr2[i6 % length];
                    strDecode2 = NPStringFog.decode("B5D4B6C4B5C2");
                    iM562 = C0052.m503(strDecode2);
                case 1754626:
                    length = bArr2.length;
                    if (C0001.f1 > 0) {
                        strDecode4 = NPStringFog.decode("B5D8B6C9B5FE");
                    }
                    iM562 = C0001.m190(strDecode4);
                case 1755615:
                    bArr3 = new byte[bArr.length];
                    if (C0043.m456() <= 0) {
                        C0001.f1 = 21;
                        strDecode4 = NPStringFog.decode("B5D8B6C9B5FE");
                    }
                    iM562 = C0001.m190(strDecode4);
            }
            return bArr3;
        }
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    private static AssetFileDescriptor m348(InputStream inputStream, String str) throws Throwable {
        int iM455 = C0043.m455(NPStringFog.decode("B5D0B6C3B5C3"));
        ParcelFileDescriptor parcelFileDescriptorM361 = null;
        while (true) {
            if (iM455 == 1747744) {
                parcelFileDescriptorM361 = m361(inputStream, str);
                if ((C0002.f2 | (C0043.f73 + 8983)) >= 0) {
                    C0053.m574();
                }
                iM455 = C0043.m455(NPStringFog.decode("B5D5B6C6B5C9"));
            } else if (iM455 != 1749852) {
                if (iM455 == 1752710) {
                    return new AssetFileDescriptor(parcelFileDescriptorM361, (-162) ^ C0052.f91, C0001.m208(parcelFileDescriptorM361));
                }
            } else if (C0001.f1 <= 0) {
                C0001.f1 = 86;
                iM455 = C0052.m503(NPStringFog.decode("B5D3B6C4B5C5"));
            } else {
                iM455 = (C0002.f2 * C0053.f92) ^ 1727112;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0064 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b4 A[SYNTHETIC] */
    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static InputStream m349(InputStream inputStream) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D1B6C2B5C2");
        int iM503 = C0052.m503(strDecode3);
        byte[] bArrM364 = null;
        while (true) {
            switch (iM503) {
                case 56478:
                    if (C0001.m192() < 0) {
                        C0002.m259();
                        iM503 = C0052.m503(NPStringFog.decode("B5D6B6C4B5C4"));
                    } else {
                        strDecode = NPStringFog.decode("B5D8B6C9B5C9");
                        iM503 = C0053.m562(strDecode);
                    }
                case 1747679:
                    i = C0053.f92 ^ C0052.f91;
                    i2 = 1748109;
                    iM503 = i ^ i2;
                case 1748671:
                    if (C0001.m205(inputStream) > 10485760) {
                        if (C0053.f92 - (C0043.f73 - 2284) <= 0) {
                            strDecode = NPStringFog.decode("B5D7B6C3B5C0");
                            iM503 = C0053.m562(strDecode);
                        } else {
                            iM503 = (C0043.f73 % C0001.f1) + 1751790;
                        }
                    } else if (C0001.m192() < 0) {
                    }
                    break;
                case 1748737:
                    if (C0053.m550()) {
                        int i3 = C0043.f73 ^ (C0001.f1 / (-4307));
                        strDecode2 = NPStringFog.decode("B5D8B6C6B5C3");
                        if (i3 >= 0) {
                            C0002.m259();
                            iM503 = C0052.m503(strDecode2);
                        } else {
                            iM503 = C0001.m190(strDecode2);
                        }
                    } else if (C0043.f73 < 0) {
                        strDecode2 = NPStringFog.decode("B5D1B6C9B5C0");
                        iM503 = C0001.m190(strDecode2);
                    } else {
                        i = C0052.f91 / C0002.f2;
                        i2 = 1754468;
                        iM503 = i ^ i2;
                    }
                case 1749827:
                    if (C0043.f73 < 0) {
                    }
                    break;
                case 1751623:
                    return new C0017(inputStream, bArrM364);
                case 1754470:
                    bArrM364 = m364();
                    if (C0001.f1 % (C0052.f91 * 8896) <= 0) {
                        C0043.m456();
                        iM503 = C0043.m455(strDecode3);
                    } else {
                        i = C0052.f91 ^ C0043.f73;
                        i2 = 1748664;
                        iM503 = i ^ i2;
                    }
                case 1755587:
                    return inputStream;
                case 1755624:
                    return m368(inputStream, bArrM364);
            }
        }
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    private static String m350(InputStream inputStream, byte[] bArr) throws Throwable {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        int iM230 = C0002.m230(NPStringFog.decode("B5D7B6C3B5C2"));
        ByteArrayOutputStream byteArrayOutputStream = null;
        String strM514 = null;
        Throwable th = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D8B6C3B5C0");
            String strDecode3 = "ۣۧۡ";
            String strDecode4 = NPStringFog.decode("B5EFB6FEB5C4");
            switch (iM230) {
                case 56294:
                    i = C0002.f2 ^ C0043.f73;
                    i2 = 1754235;
                    iM230 = i + i2;
                case 56506:
                    if (C0002.f2 * (C0001.f1 / (-9236)) != 0) {
                        C0002.m259();
                        strDecode3 = NPStringFog.decode("B5EFB6C0B5C4");
                        iM230 = C0002.m230(strDecode3);
                    } else {
                        i3 = C0001.f1 * C0052.f91;
                        i4 = -1713441;
                        iM230 = i3 ^ i4;
                    }
                case 1746693:
                    C0053.m556(inputStream, byteArrayOutputStream, bArr);
                    int i5 = C0043.f73 + (C0001.f1 - 2914);
                    strDecode2 = NPStringFog.decode("B5D2B6C5B5C6");
                    if (i5 >= 0) {
                        C0002.f2 = 0;
                        iM230 = C0001.m190(strDecode2);
                    }
                case 1746781:
                    return strM514;
                case 1748709:
                case 1751525:
                    i = C0053.f92 + C0052.f91;
                    i2 = 1749804;
                    iM230 = i + i2;
                case 1748797:
                    strDecode2 = C0002.f2 % (C0001.f1 | 9491) >= 0 ? NPStringFog.decode("B5D8B6C4B5FE") : strDecode4;
                case 1748828:
                    throw th;
                case 1749733:
                    try {
                        strM514 = C0052.m514(byteArrayOutputStream);
                        iM230 = C0002.m230(strDecode3);
                    } catch (Throwable th2) {
                        th = th2;
                        if (C0052.f91 + (C0002.f2 * (-7921)) > 0) {
                            i3 = C0002.f2 * C0053.f92;
                            i4 = 1723145;
                            break;
                        } else {
                            strDecode = NPStringFog.decode("B5D1B6C3B5C7");
                            break;
                        }
                    }
                case 1751713:
                    i = C0053.f92 / C0002.f2;
                    i2 = 1755425;
                    iM230 = i + i2;
                case 1754472:
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    i3 = C0043.f73 / C0043.f73;
                    i4 = 1746692;
                    iM230 = i3 ^ i4;
                case 1754501:
                    C0053.m566(byteArrayOutputStream);
                    if (C0043.m456() <= 0) {
                        C0043.f73 = 26;
                        strDecode = NPStringFog.decode("B5D1B6C4B5C0");
                        iM230 = C0053.m562(strDecode);
                    } else {
                        iM230 = C0002.m230(NPStringFog.decode("B5EFB6C3B5C1"));
                    }
                case 1755373:
                    iM230 = C0053.f92 * (C0002.f2 + 9151) >= 0 ? C0052.m503(NPStringFog.decode("B5D1B6C3B5C0")) : C0053.m562(strDecode2);
                case 1755431:
                    if (C0052.f91 / (C0001.f1 * (-5454)) != 0) {
                        iM230 = C0001.m190("ۣۧۡ");
                    } else {
                        i = C0043.f73 | C0053.f92;
                        i2 = 1748746;
                        iM230 = i + i2;
                    }
                case 1755435:
                    try {
                        C0053.m566(byteArrayOutputStream);
                        if (C0043.f73 >= 0) {
                            C0002.m259();
                            iM230 = C0002.m230(strDecode4);
                        } else {
                            iM230 = C0052.m503(strDecode2);
                        }
                    } catch (Throwable th3) {
                        C0052.m525(th, th3);
                        if (C0052.f91 < 0) {
                            i = C0043.f73 - C0043.f73;
                            i2 = 1751525;
                            break;
                        } else {
                            iM230 = C0001.m190(NPStringFog.decode("B5D1B6C7B5C0"));
                        }
                    }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01c8 A[SYNTHETIC] */
    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m351(byte[] bArr) throws NumberFormatException {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        String strDecode2;
        String strDecode3;
        String strDecode4;
        String strDecode5 = NPStringFog.decode("B5D1B6C3");
        int iM455 = C0043.m455(strDecode5);
        long j = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        byte b = 0;
        int i8 = 0;
        int length = 0;
        while (true) {
            String strDecode6 = NPStringFog.decode("B5D8B6C6");
            String strDecode7 = NPStringFog.decode("B5D1B6C9B5FE");
            switch (iM455) {
                case 56290:
                    if (i5 >= i6) {
                        i = C0043.f73 ^ C0001.f1;
                        i2 = 56873;
                        iM455 = i + i2;
                    } else if (C0002.m259() >= 0) {
                        strDecode = NPStringFog.decode("B5D1B6FEB5C2");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i3 = C0052.f91 | C0002.f2;
                        i4 = -1748774;
                        iM455 = i3 ^ i4;
                    }
                case 56353:
                    length = (C0043.f73 ^ 166) + bArr.length;
                    if (C0052.f91 >= 0) {
                        C0002.f2 = 23;
                        iM455 = C0002.m230(strDecode5);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D0B6C1B5C1");
                        iM455 = C0002.m230(strDecode2);
                    }
                case 56419:
                    if (C0001.m192() >= 0) {
                        if (C0053.f92 / (C0043.f73 | 4423) <= 0) {
                            C0001.f1 = 45;
                            iM455 = C0053.m562("ۨ۟۟");
                        } else {
                            iM455 = C0052.m503(strDecode6);
                        }
                    } else if ((C0052.f91 | (C0002.f2 / (-2384))) < 0) {
                        C0002.m259();
                        strDecode3 = NPStringFog.decode("B5D5B6C6B5C4");
                        iM455 = C0043.m455(strDecode3);
                    } else {
                        i = C0001.f1 * C0043.f73;
                        i2 = 1809816;
                        iM455 = i + i2;
                    }
                case 56575:
                    j = Long.parseLong(C0052.m511(NPStringFog.decode("5A1E5D0C06513215010202213820102609385F335D")));
                    if (C0052.f91 >= 0) {
                        C0001.f1 = 80;
                        iM455 = C0052.m503(NPStringFog.decode("B5EFB6C1B5C0"));
                    } else {
                        iM455 = C0053.m562("ۨ۟۟");
                    }
                case 1746720:
                case 1749579:
                    i = C0001.f1 / C0002.f2;
                    i2 = 56294;
                    iM455 = i + i2;
                case 1746813:
                    if (C0002.f2 / (C0052.f91 + 9994) != 0) {
                        C0002.f2 = 84;
                        strDecode = NPStringFog.decode("B5D2B6C2B5C0");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i3 = C0052.f91 * C0053.f92;
                        i4 = 1618263;
                        iM455 = i3 ^ i4;
                    }
                case 1746873:
                    if ((C0052.f91 | (C0002.f2 / (-2384))) < 0) {
                    }
                    break;
                case 1747680:
                    if (C0052.m520() <= 0) {
                        C0043.m456();
                        strDecode2 = NPStringFog.decode("B5D3B6C7");
                        iM455 = C0002.m230(strDecode2);
                    } else {
                        i3 = C0001.f1 ^ C0001.f1;
                        i4 = 1748613;
                        iM455 = i3 ^ i4;
                    }
                case 1747688:
                    i = C0002.f2 / C0043.f73;
                    i2 = 56353;
                    iM455 = i + i2;
                case 1747742:
                    if ((C0043.f73 ^ (C0053.f92 / (-3519))) >= 0) {
                        C0001.f1 = 74;
                        iM455 = C0002.m230(strDecode7);
                    } else {
                        iM455 = (C0053.f92 % C0052.f91) ^ (-1749732);
                    }
                    i6 = i7;
                case 1747776:
                    i = C0043.f73 ^ C0001.f1;
                    i2 = 56873;
                    iM455 = i + i2;
                case 1748613:
                    iM455 = (C0052.f91 * C0053.f92) + 1622512;
                    i5 = 0;
                case 1748766:
                    bArr[i6] = b;
                    strDecode6 = strDecode7;
                    iM455 = C0053.m562(strDecode6);
                case 1748772:
                    b = bArr[i5];
                    if (C0001.f1 / (C0053.f92 % (-6509)) != 0) {
                        C0001.m192();
                        iM455 = C0053.m562(strDecode6);
                    } else {
                        i = C0052.f91 | C0002.f2;
                        i2 = 1751713;
                        iM455 = i + i2;
                    }
                case 1748888:
                    i8 = i5 + (C0002.f2 ^ (-75));
                    if (C0052.f91 + (C0002.f2 - 1767) >= 0) {
                        C0053.m574();
                        strDecode4 = NPStringFog.decode("B5EFB6C4B5FE");
                        iM455 = C0053.m562(strDecode4);
                    } else {
                        i = C0002.f2 % C0002.f2;
                        i2 = 1749696;
                        iM455 = i + i2;
                    }
                case 1749696:
                    i7 = i6 + (C0052.f91 ^ 161);
                    if ((C0002.f2 ^ (C0001.f1 ^ 5243)) >= 0) {
                        C0053.m574();
                        strDecode6 = NPStringFog.decode("B5D2B6C5B5C3");
                        iM455 = C0052.m503(strDecode6);
                    } else {
                        i3 = C0053.f92 | C0053.f92;
                        i4 = -1746996;
                        iM455 = i3 ^ i4;
                    }
                case 1749728:
                    if (C0043.f73 + (C0001.f1 | 8129) <= 0) {
                        iM455 = C0052.m503(NPStringFog.decode("B5D0B6C1B5C9"));
                        i5 = i8;
                    } else {
                        strDecode6 = NPStringFog.decode("B5EFB6C2B5C0");
                        i5 = i8;
                        iM455 = C0052.m503(strDecode6);
                    }
                case 1750531:
                    break;
                case 1751711:
                    bArr[i5] = bArr[i6];
                    if (C0053.f92 % (C0052.f91 ^ (-4266)) >= 0) {
                        C0052.m520();
                        strDecode4 = NPStringFog.decode("B5D0B6C2B5C2");
                        iM455 = C0053.m562(strDecode4);
                    } else {
                        strDecode3 = NPStringFog.decode("B5D1B6C5B5C0");
                        iM455 = C0043.m455(strDecode3);
                    }
                case 1754380:
                    if ((C0043.f73 ^ (C0053.f92 / 1449)) >= 0) {
                        C0052.m520();
                        iM455 = C0043.m455(NPStringFog.decode("B5D7B6FEB5C5"));
                        i6 = length;
                    } else {
                        strDecode3 = NPStringFog.decode("B5EFB6C0");
                        i6 = length;
                        iM455 = C0043.m455(strDecode3);
                    }
                case 1755336:
                    System.out.println(j);
                    strDecode4 = NPStringFog.decode("B5D3B6FEB5FE");
                    iM455 = C0053.m562(strDecode4);
            }
            return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:139:0x0426, code lost:
    
        r0 = np.protect.assets.C0043.m455(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0433, code lost:
    
        r0 = r0 + r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0745  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x078b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0793  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x08b5  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0960  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x096e  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x099f  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x09ab  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0c2f  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x0c3a  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0c54  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0c62  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0cf8  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0d04  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x0d18 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:646:0x0d32 A[SYNTHETIC] */
    /* renamed from: ۟۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ParcelFileDescriptor m352(InputStream inputStream, String str) throws Throwable {
        ParcelFileDescriptor parcelFileDescriptor;
        String str2;
        StringBuilder sb;
        String str3;
        ParcelFileDescriptor parcelFileDescriptorM213;
        String strDecode;
        int i;
        int i2;
        StringBuilder sb2;
        String str4;
        String strDecode2;
        String strDecode3;
        String strDecode4;
        int i3;
        int i4;
        String strDecode5;
        int i5;
        int i6;
        String strDecode6;
        int i7;
        int i8;
        String strDecode7;
        String strDecode8;
        int i9;
        int i10;
        String strDecode9;
        String strDecode10;
        int i11;
        int i12;
        String strDecode11;
        int i13;
        int i14;
        int iM230;
        String strDecode12;
        String str5;
        ParcelFileDescriptor parcelFileDescriptor2;
        String str6;
        StringBuilder sb3;
        int iM562;
        String str7;
        int i15;
        int i16;
        String strDecode13;
        String strDecode14 = NPStringFog.decode("B5D2B6C9B5C7");
        int iM503 = C0052.m503(strDecode14);
        String strM454 = null;
        String str8 = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        FileOutputStream fileOutputStream2 = null;
        ParcelFileDescriptor parcelFileDescriptor3 = null;
        ParcelFileDescriptor parcelFileDescriptor4 = null;
        byte[] bArr = null;
        int iM504 = 0;
        FileOutputStream fileOutputStream3 = null;
        FileOutputStream fileOutputStream4 = null;
        boolean z = false;
        IOException iOException = null;
        ParcelFileDescriptor parcelFileDescriptor5 = null;
        ParcelFileDescriptor parcelFileDescriptor6 = null;
        IOException iOException2 = null;
        boolean z2 = false;
        Throwable th = null;
        ParcelFileDescriptor parcelFileDescriptor7 = null;
        StringBuilder sb4 = null;
        File file2 = null;
        ParcelFileDescriptor parcelFileDescriptor8 = null;
        FileOutputStream fileOutputStream5 = null;
        FileOutputStream fileOutputStream6 = null;
        while (true) {
            FileOutputStream fileOutputStream7 = null;
            while (true) {
                String strDecode15 = NPStringFog.decode("B5D4B6C6B5C1");
                String strDecode16 = "ۣۦۢ";
                String strDecode17 = NPStringFog.decode("B5D1B6C6B5C9");
                String str9 = "ۢۦ۠";
                String strDecode18 = NPStringFog.decode("B5D7B6C3B5C0");
                String strDecode19 = "۠۠";
                String strDecode20 = NPStringFog.decode("B5EFB6C5B5C0");
                String strDecode21 = "۟ۢۧ";
                String strDecode22 = NPStringFog.decode("B5D3B6C9B5C2");
                String strDecode23 = NPStringFog.decode("B5D0B6C3B5C6");
                String strDecode24 = NPStringFog.decode("B5D8B6C7");
                String strDecode25 = "ۡۥۢ";
                String strDecode26 = NPStringFog.decode("B5EFB6C9");
                String strDecode27 = NPStringFog.decode("B5D2B6C3B5FE");
                String strDecode28 = "ۢ۟۠";
                switch (iM503) {
                    case 56289:
                    case 1751740:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        iM503 = C0043.m455(strDecode27);
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56290:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        iM503 = C0053.f92 + C0043.f73 + 1747703;
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56292:
                        String str10 = str8;
                        ParcelFileDescriptor parcelFileDescriptor9 = parcelFileDescriptor3;
                        StringBuilder sb5 = sb4;
                        String str11 = strDecode14;
                        if (C0043.f73 >= 0) {
                            C0001.f1 = 53;
                            iM503 = C0043.m455(strDecode24);
                        } else {
                            iM503 = (C0052.f91 | C0052.f91) + 1748097;
                        }
                        strDecode14 = str11;
                        str8 = str10;
                        sb4 = sb5;
                        parcelFileDescriptor3 = parcelFileDescriptor9;
                        fileOutputStream3 = null;
                    case 56296:
                        str5 = str8;
                        parcelFileDescriptor2 = parcelFileDescriptor3;
                        str6 = strDecode14;
                        sb3 = new StringBuilder(strM454);
                        if (C0002.m259() >= 0) {
                            C0002.m259();
                            fileOutputStream7 = null;
                            iM562 = C0053.m562(strDecode25);
                            str8 = str5;
                            parcelFileDescriptor3 = parcelFileDescriptor2;
                            String str12 = str6;
                            sb4 = sb3;
                            iM503 = iM562;
                            strDecode14 = str12;
                        } else {
                            iM562 = (C0043.f73 / C0052.f91) ^ 56481;
                            str8 = str5;
                            parcelFileDescriptor3 = parcelFileDescriptor2;
                            fileOutputStream7 = null;
                            String str122 = str6;
                            sb4 = sb3;
                            iM503 = iM562;
                            strDecode14 = str122;
                        }
                    case 56297:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        if (fileOutputStream != null) {
                            if (C0053.f92 - (C0053.f92 - 7755) <= 0) {
                                C0052.f91 = 41;
                                iM503 = C0043.m455(strDecode23);
                                strDecode14 = str3;
                                str8 = str2;
                                sb4 = sb;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            }
                            strDecode8 = NPStringFog.decode("B5EFB6C7B5C4");
                            iM503 = C0052.m503(strDecode8);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        i = C0043.f73 ^ C0043.f73;
                        i2 = 1755431;
                        iM503 = i ^ i2;
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case Utf8.LOG_SURROGATE_HEADER /* 56320 */:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        try {
                            parcelFileDescriptorM213 = C0001.m213(file, C0053.f92 ^ (-268436270));
                        } catch (IOException e) {
                            iOException2 = e;
                            if ((C0001.f1 ^ (C0001.f1 / 9637)) > 0) {
                            }
                            strDecode14 = str3;
                            parcelFileDescriptor7 = parcelFileDescriptor8;
                            fileOutputStream = fileOutputStream5;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } catch (Throwable th2) {
                            th = th2;
                            if (C0001.f1 > 0) {
                            }
                            strDecode14 = str3;
                            fileOutputStream4 = fileOutputStream6;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        if (C0052.f91 >= 0) {
                            parcelFileDescriptor3 = parcelFileDescriptorM213;
                            iM503 = C0001.m190(NPStringFog.decode("B5EFB6C0"));
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                        } else {
                            strDecode26 = NPStringFog.decode("B5D8B6C0B5C2");
                            parcelFileDescriptor = parcelFileDescriptorM213;
                            iM503 = C0043.m455(strDecode26);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        break;
                    case 56322:
                    case 1746723:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        strDecode = NPStringFog.decode("B5D2B6C0B5C9");
                        iM503 = C0001.m190(strDecode);
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56324:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        if ((C0053.f92 ^ (C0052.f91 / 6416)) >= 0) {
                            C0052.f91 = 7;
                            iM503 = C0002.m230(NPStringFog.decode("B5EFB6C2"));
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            iM503 = C0053.m562("ۥۤ");
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 56326:
                    case 1746787:
                    case 1754623:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        if (C0043.f73 - (C0053.f92 + 5306) >= 0) {
                            strDecode = NPStringFog.decode("B5D6B6C9");
                            iM503 = C0001.m190(strDecode);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i = C0052.f91 ^ C0043.f73;
                            i2 = 1748737;
                            iM503 = i ^ i2;
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 56418:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        if (C0001.m192() >= 0) {
                            C0043.m456();
                            iM503 = C0002.m230("ۨۢۦ");
                            fileOutputStream5 = fileOutputStream2;
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            fileOutputStream5 = fileOutputStream2;
                            iM503 = C0043.m455(strDecode23);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 56450:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        if (C0053.f92 + (C0043.f73 % (-7834)) >= 0) {
                            C0052.m520();
                            iM503 = C0053.m562(NPStringFog.decode("B5D8B6C4B5C7"));
                        } else {
                            iM503 = C0043.m455("ۢ۟۠");
                        }
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56479:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        str9 = "ۣۡۨ";
                        fileOutputStream7 = null;
                        iM503 = C0001.m190(str9);
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56480:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        String str13 = strDecode14;
                        try {
                            sb2 = sb4;
                            str3 = str13;
                            try {
                                sb = sb2;
                            } catch (IOException e2) {
                                e = e2;
                                sb = sb2;
                            } catch (Throwable th3) {
                                th = th3;
                                sb = sb2;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            sb = sb4;
                            str3 = str13;
                        } catch (Throwable th4) {
                            th = th4;
                            sb = sb4;
                            str3 = str13;
                        }
                        try {
                            File file3 = new File(C0002.m248(C0001.m211(sb2, str)));
                            iM503 = C0002.f2 - (C0043.f73 + (-6084)) <= 0 ? C0053.m562(NPStringFog.decode("B5EFB6C1B5C2")) : (C0002.f2 * C0002.f2) + 1747644;
                            strDecode14 = str3;
                            str8 = str2;
                            file2 = file3;
                            sb4 = sb;
                            break;
                        } catch (IOException e4) {
                            e = e4;
                            iOException2 = e;
                            if (C0002.f2 < 0) {
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            th = th;
                            if (C0001.m192() < 0) {
                            }
                        }
                        break;
                    case 56482:
                    case 1749852:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        sb = sb4;
                        str3 = strDecode14;
                        i = C0043.f73 ^ C0043.f73;
                        i2 = 1755431;
                        iM503 = i ^ i2;
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56574:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        iM503 = C0053.m562(C0002.m259() >= 0 ? NPStringFog.decode("B5D6B6C7B5C4") : NPStringFog.decode("B5D8B6C4B5C3"));
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 56575:
                    case 1751555:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        strDecode2 = NPStringFog.decode("B5D2B6C6B5C2");
                        iM503 = C0043.m455(strDecode2);
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1746687:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        String str14 = strDecode14;
                        if ((C0043.f73 | (C0002.f2 / (-5576))) >= 0) {
                            C0053.f92 = 97;
                            strDecode26 = NPStringFog.decode("B5D6B6C6B5C3");
                            sb = sb4;
                            str3 = str14;
                        } else {
                            strDecode26 = str14;
                            sb = sb4;
                            str3 = strDecode26;
                        }
                        iM503 = C0043.m455(strDecode26);
                        strDecode14 = str3;
                        str8 = str2;
                        sb4 = sb;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1746691:
                    case 1752484:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        iM503 = (C0002.f2 * C0001.f1) + 1782536;
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1746695:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        if (C0052.m520() <= 0) {
                            C0002.m259();
                            strDecode3 = NPStringFog.decode("B5D7B6C9B5C7");
                            iM503 = C0002.m230(strDecode3);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            sb = sb4;
                            str3 = str4;
                            iM503 = C0002.m230(NPStringFog.decode("B5EFB6C2"));
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1746722:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        if (C0001.m192() >= 0) {
                            C0002.m259();
                            iM503 = C0001.m190("۟ۢۧ");
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            sb = sb4;
                            str3 = str4;
                            iM503 = C0053.m562(strDecode22);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1746752:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        if (C0043.f73 >= 0) {
                            C0001.m192();
                            strDecode4 = NPStringFog.decode("B5D8B6C3B5C0");
                            iM503 = C0052.m503(strDecode4);
                            strDecode14 = str4;
                            fileOutputStream5 = fileOutputStream2;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            fileOutputStream5 = fileOutputStream2;
                            strDecode12 = NPStringFog.decode("B5D5B6C0B5C7");
                            iM503 = C0001.m190(strDecode12);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1746755:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        if (C0052.f91 >= 0) {
                            C0052.m520();
                            iM503 = C0052.m503(strDecode20);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                            parcelFileDescriptor4 = parcelFileDescriptor3;
                        } else {
                            parcelFileDescriptor4 = parcelFileDescriptor;
                            strDecode2 = NPStringFog.decode("B5D5B6C4B5C5");
                            iM503 = C0043.m455(strDecode2);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1746758:
                    case 1747650:
                    case 1750753:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str4 = strDecode14;
                        if (C0053.f92 + (C0053.f92 % (-3727)) < 0) {
                            strDecode24 = NPStringFog.decode("B5D1B6C4B5C1");
                            iM503 = C0043.m455(strDecode24);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode3 = NPStringFog.decode("B5EFB6C2B5C6");
                            iM503 = C0002.m230(strDecode3);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1746788:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            str4 = strDecode14;
                        } catch (IOException e5) {
                            e = e5;
                            iOException2 = e;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0002.f2 < 0) {
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            th = th;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0001.m192() < 0) {
                            }
                        }
                        if (C0052.m531(file) == C0001.m205(inputStream)) {
                            i3 = C0043.f73 | C0052.f91;
                            i4 = -1752330;
                        } else if (C0001.f1 % (C0053.f92 * (-5611)) > 0) {
                            strDecode = NPStringFog.decode("B5D6B6C1");
                            sb = sb4;
                            str3 = str4;
                            iM503 = C0001.m190(strDecode);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i3 = C0001.f1 ^ C0001.f1;
                            i4 = 1746939;
                        }
                        iM503 = i3 ^ i4;
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                        break;
                    case 1746819:
                        return parcelFileDescriptor4;
                    case 1746844:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0002.m259() >= 0) {
                            C0001.m192();
                            parcelFileDescriptor8 = null;
                            strDecode5 = NPStringFog.decode("B5EFB6C1B5C5");
                            iM503 = C0001.m190(strDecode5);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode24 = NPStringFog.decode("B5D1B6FEB5C5");
                            str4 = strDecode14;
                            parcelFileDescriptor8 = null;
                            iM503 = C0043.m455(strDecode24);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1746910:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            C0002.m225(fileOutputStream);
                            i5 = C0043.f73 + C0043.f73;
                            i6 = 1749069;
                            iM503 = i5 + i6;
                        } catch (IOException e6) {
                            if (C0043.f73 * (C0052.f91 | 766) > 0) {
                                iOException = e6;
                                parcelFileDescriptor5 = parcelFileDescriptor7;
                                break;
                            } else {
                                C0053.m574();
                                iOException = e6;
                                iM503 = C0001.m190("ۣۡۨ");
                                parcelFileDescriptor5 = parcelFileDescriptor7;
                            }
                        }
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1746939:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        fileOutputStream7 = null;
                        strDecode7 = NPStringFog.decode("B5D1B6C9B5C1");
                        iM503 = C0052.m503(strDecode7);
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1746940:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0043.f73 * (C0053.f92 | (-8364)) > 0) {
                            C0043.m456();
                            strDecode6 = NPStringFog.decode("B5D2B6C1B5C1");
                            iM503 = C0053.m562(strDecode6);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i5 = C0053.f92 / C0001.f1;
                            i6 = 1747937;
                            iM503 = i5 + i6;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1747653:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        iM503 = C0052.m503(strDecode26);
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1747749:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            iM504 = C0052.m504(inputStream, bArr);
                            iM503 = (C0052.f91 % C0043.f73) ^ (-1750626);
                            str8 = str2;
                        } catch (IOException e7) {
                            e = e7;
                            iOException2 = e;
                            sb = sb4;
                            str3 = strDecode14;
                            if ((C0001.f1 ^ (C0001.f1 / 9637)) > 0) {
                                C0052.m520();
                                iM503 = C0052.m503(NPStringFog.decode("B5EFB6C0B5C3"));
                            } else {
                                iM503 = (C0002.f2 | C0052.f91) + 1752486;
                            }
                            strDecode14 = str3;
                            parcelFileDescriptor7 = parcelFileDescriptor8;
                            fileOutputStream = fileOutputStream5;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } catch (Throwable th7) {
                            th = th7;
                            th = th;
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0001.f1 > 0 ? C0001.m190(NPStringFog.decode("B5D0B6FEB5C5")) : C0052.f91 + C0002.f2 + 56564;
                            strDecode14 = str3;
                            fileOutputStream4 = fileOutputStream6;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1747773:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (iM504 != -1) {
                            if ((C0053.f92 ^ (C0043.f73 / (-7952))) >= 0) {
                                C0002.f2 = 82;
                                iM503 = C0001.m190(NPStringFog.decode("B5EFB6C0B5C9"));
                                fileOutputStream6 = fileOutputStream2;
                                str8 = str2;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            } else {
                                fileOutputStream6 = fileOutputStream2;
                                iM503 = C0053.m562(strDecode18);
                                str8 = str2;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            }
                        }
                        if (C0043.f73 * (C0053.f92 | (-8364)) > 0) {
                        }
                        break;
                    case 1747869:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        i7 = C0001.f1 - C0001.f1;
                        i8 = 1751555;
                        iM503 = i7 + i8;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1747935:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        fileOutputStream6 = fileOutputStream3;
                        strDecode19 = C0002.f2 * (C0002.f2 ^ 8747) <= 0 ? NPStringFog.decode("B5D7B6C7B5C7") : strDecode20;
                        iM503 = C0052.m503(strDecode19);
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1748614:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0001.f1 <= 0) {
                            C0053.f92 = 98;
                            iM503 = C0052.m503(strDecode18);
                            fileOutputStream5 = fileOutputStream3;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            fileOutputStream5 = fileOutputStream3;
                            iM503 = C0052.m503(strDecode19);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748641:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0052.f91 - (C0001.f1 % 5017) >= 0) {
                            C0043.f73 = 42;
                            strDecode21 = NPStringFog.decode("B5D4B6C1B5C1");
                            iM503 = C0002.m230(strDecode21);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode7 = NPStringFog.decode("B5D2B6C4B5C9");
                            iM503 = C0052.m503(strDecode7);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748642:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        C0001.m201(C0052.m524(file));
                        if ((C0001.f1 | (C0001.f1 - 1967)) >= 0) {
                            sb = sb4;
                            str3 = strDecode14;
                            strDecode8 = NPStringFog.decode("B5EFB6C7B5C4");
                            iM503 = C0052.m503(strDecode8);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i7 = C0001.f1 / C0052.f91;
                            i8 = 1751686;
                            iM503 = i7 + i8;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748677:
                    case 1752735:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0052.m520() > 0) {
                            strDecode6 = NPStringFog.decode("B5D3B6C2B5C7");
                            iM503 = C0053.m562(strDecode6);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i7 = C0052.f91 / C0052.f91;
                            i8 = 1747868;
                            iM503 = i7 + i8;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748735:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0001.f1 - (C0052.f91 + 643) >= 0) {
                            strDecode15 = "ۢ۟۠";
                            iM503 = C0002.m230(strDecode15);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i9 = C0043.f73 - C0053.f92;
                            i10 = 1749467;
                            iM503 = i9 ^ i10;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748742:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (fileOutputStream4 != null) {
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0002.m230(strDecode28);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        if (C0052.m520() > 0) {
                        }
                        break;
                    case 1748769:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0002.f2 % (C0002.f2 % (-3440)) == 0) {
                            C0001.f1 = 16;
                            strDecode3 = NPStringFog.decode("B5D4B6C1B5C0");
                            str4 = strDecode14;
                            iM503 = C0002.m230(strDecode3);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i9 = C0052.f91 / C0043.f73;
                            i10 = 1751684;
                            iM503 = i9 ^ i10;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748773:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.m574() >= 0) {
                            strDecode6 = NPStringFog.decode("B5D5B6C7B5C5");
                            iM503 = C0053.m562(strDecode6);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i7 = C0001.f1 / C0043.f73;
                            i8 = 1746757;
                            iM503 = i7 + i8;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748798:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (z) {
                            if (C0052.m520() <= 0) {
                                C0002.f2 = 57;
                                iM503 = C0043.m455(NPStringFog.decode("B5D4B6C9B5FE"));
                                str8 = str2;
                                break;
                            } else {
                                fileOutputStream7 = null;
                                iM503 = C0043.m455(NPStringFog.decode("B5D6B6C2B5C4"));
                                str8 = str2;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            }
                        }
                        if ((C0052.f91 | C0043.f73 | (-1402)) < 0) {
                            C0053.m574();
                            strDecode2 = NPStringFog.decode("B5D8B6C4B5C2");
                        } else {
                            strDecode2 = NPStringFog.decode("B5EFB6FEB5C6");
                        }
                        str4 = strDecode14;
                        iM503 = C0043.m455(strDecode2);
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1748827:
                        str5 = str8;
                        parcelFileDescriptor2 = parcelFileDescriptor3;
                        FileOutputStream fileOutputStream8 = new FileOutputStream(file);
                        strDecode25 = NPStringFog.decode("B5EFB6C1B5C2");
                        fileOutputStream2 = fileOutputStream8;
                        sb3 = sb4;
                        str6 = strDecode14;
                        iM562 = C0053.m562(strDecode25);
                        str8 = str5;
                        parcelFileDescriptor3 = parcelFileDescriptor2;
                        String str1222 = str6;
                        sb4 = sb3;
                        iM503 = iM562;
                        strDecode14 = str1222;
                    case 1748859:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        C0052.m521(fileOutputStream2, bArr, 0, iM504);
                        iM503 = C0001.m190("ۢۦ۠");
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1748864:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.m574() >= 0) {
                            C0053.m574();
                            strDecode9 = NPStringFog.decode("B5D6B6FEB5FE");
                            iM503 = C0002.m230(strDecode9);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        iM503 = C0052.m503(strDecode22);
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1748866:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if ((C0053.f92 | (C0043.f73 - 8162)) >= 0) {
                            C0043.m456();
                            strDecode25 = NPStringFog.decode("B5D5B6C4");
                            fileOutputStream5 = fileOutputStream2;
                            iM503 = C0002.m230(strDecode25);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode4 = NPStringFog.decode("B5D1B6C6B5C0");
                            str4 = strDecode14;
                            iM503 = C0052.m503(strDecode4);
                            strDecode14 = str4;
                            fileOutputStream5 = fileOutputStream2;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1748889:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            C0052.m508(file);
                            i5 = C0001.f1 | C0052.f91;
                            i6 = 1752681;
                            iM503 = i5 + i6;
                            str8 = str2;
                        } catch (IOException e8) {
                            e = e8;
                            iOException2 = e;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0002.f2 < 0) {
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            th = th;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0001.m192() < 0) {
                            }
                        }
                        parcelFileDescriptor3 = parcelFileDescriptor;
                        break;
                    case 1748896:
                        String str15 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if ((C0001.f1 ^ (C0002.f2 / 5216)) <= 0) {
                            C0053.m574();
                            iM503 = C0001.m190(NPStringFog.decode("B5EFB6FEB5FE"));
                        } else {
                            iM503 = C0043.f73 + C0052.f91 + 1747081;
                        }
                        str8 = str15;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                        parcelFileDescriptor8 = null;
                    case 1749571:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            C0002.m225(fileOutputStream4);
                            if (C0043.f73 >= 0) {
                                C0053.f92 = 40;
                                strDecode6 = NPStringFog.decode("B5D8B6C6");
                            } else {
                                strDecode6 = NPStringFog.decode("B5D5B6C7B5C2");
                            }
                            iM503 = C0053.m562(strDecode6);
                        } catch (IOException e9) {
                            C0001.m193(e9);
                            strDecode5 = NPStringFog.decode("B5D1B6C0B5C4");
                            break;
                        }
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1749641:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        C0001.m193(iOException);
                        if ((C0053.f92 | (C0052.f91 % (-4698))) >= 0) {
                            parcelFileDescriptor4 = parcelFileDescriptor5;
                            sb = sb4;
                            str3 = strDecode14;
                            strDecode28 = NPStringFog.decode("B5EFB6FEB5C2");
                            iM503 = C0002.m230(strDecode28);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            iM503 = C0001.m190("ۨۢۦ");
                            parcelFileDescriptor4 = parcelFileDescriptor5;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1749663:
                        str7 = str8;
                        iM503 = (C0002.f2 / C0053.f92) ^ 1753637;
                        fileOutputStream6 = fileOutputStream2;
                        str8 = str7;
                    case 1749765:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        iM503 = C0043.f73 >= 0 ? C0001.m190(strDecode14) : (C0001.f1 | C0052.f91) + 1754786;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                        parcelFileDescriptor4 = parcelFileDescriptor3;
                    case 1749788:
                        str7 = str8;
                        iM503 = C0002.f2 + C0002.f2 + 56441;
                        str8 = str7;
                    case 1749789:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            C0052.m539(file);
                        } catch (IOException e10) {
                            e = e10;
                            iOException2 = e;
                            sb = sb4;
                            str3 = strDecode14;
                            if ((C0001.f1 ^ (C0001.f1 / 9637)) > 0) {
                            }
                            strDecode14 = str3;
                            parcelFileDescriptor7 = parcelFileDescriptor8;
                            fileOutputStream = fileOutputStream5;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } catch (Throwable th9) {
                            th = th9;
                            th = th;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0001.f1 > 0) {
                            }
                            strDecode14 = str3;
                            fileOutputStream4 = fileOutputStream6;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        if (C0002.f2 >= 0) {
                            strDecode17 = "ۥۤ";
                            iM503 = C0043.m455(strDecode17);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode16 = NPStringFog.decode("B5EFB6C0B5C4");
                            iM503 = C0052.m503(strDecode16);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        break;
                    case 1749822:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0052.m520() <= 0) {
                            C0002.f2 = 50;
                            iM503 = C0053.m562(strDecode17);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode8 = NPStringFog.decode("B5D7B6C5B5C5");
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0052.m503(strDecode8);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1749856:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str2 = str8;
                        strM454 = C0043.m454(m371(), 64, C0001.f1 ^ 371, 1475);
                        strDecode2 = NPStringFog.decode("B5D0B6C5");
                        str4 = strDecode14;
                        iM503 = C0043.m455(strDecode2);
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1750594:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if ((C0052.f91 ^ (C0001.f1 ^ (-1182))) <= 0) {
                            strDecode10 = NPStringFog.decode("B5D6B6C2B5C0");
                            iM503 = C0043.m455(strDecode10);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode6 = NPStringFog.decode("B5D6B6C5B5C6");
                            str2 = str8;
                            iM503 = C0053.m562(strDecode6);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1750602:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        String strM363 = m363();
                        if (C0002.f2 >= 0) {
                            str2 = strM363;
                            str4 = strDecode14;
                            strDecode2 = NPStringFog.decode("B5D5B6C4B5C5");
                            iM503 = C0043.m455(strDecode2);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            str2 = strM363;
                            iM503 = C0052.m503(strDecode19);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1750689:
                    case 1754535:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        i11 = C0043.f73 + C0043.f73;
                        i12 = 1753071;
                        iM503 = i11 + i12;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1750720:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0002.f2 * C0052.f91 * (-8747) >= 0) {
                            C0001.m192();
                            str2 = str8;
                            fileOutputStream3 = fileOutputStream2;
                            iM503 = C0052.m503(strDecode16);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            iM503 = (C0052.f91 + C0052.f91) ^ (-1747583);
                            fileOutputStream3 = fileOutputStream2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1750722:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0002.f2 >= 0) {
                            C0002.f2 = 71;
                            str2 = str8;
                            parcelFileDescriptor4 = parcelFileDescriptor6;
                            iM503 = C0043.m455(NPStringFog.decode("B5D6B6C2B5C4"));
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            iM503 = C0052.f91 + C0052.f91 + 1747143;
                            parcelFileDescriptor4 = parcelFileDescriptor6;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1750751:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        C0001.m193(iOException2);
                        if (C0002.f2 - (C0043.f73 / 6254) >= 0) {
                            C0052.f91 = 90;
                            iM503 = C0052.m503("۠۠");
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0043.m455(strDecode26);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1750786:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str2 = str8;
                        str4 = strDecode14;
                        if (C0001.f1 % (C0053.f92 * (-5611)) > 0) {
                        }
                        break;
                    case 1750814:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0043.f73 >= 0) {
                            C0001.f1 = 27;
                            iM503 = C0052.m503(NPStringFog.decode("B5EFB6C6B5C2"));
                            fileOutputStream6 = fileOutputStream2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode11 = NPStringFog.decode("B5D1B6C9B5C6");
                            fileOutputStream6 = fileOutputStream2;
                            iM503 = C0001.m190(strDecode11);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1751684:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0043.f73 < 0) {
                            i15 = C0002.f2 ^ C0043.f73;
                            i16 = 1748590;
                            break;
                        } else {
                            C0053.f92 = 29;
                            strDecode13 = NPStringFog.decode("B5D5B6C2B5C7");
                            break;
                        }
                    case 1751741:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str2 = str8;
                        z = z2;
                        iM503 = C0002.m230(strDecode25);
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1751771:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0001.m200(file)) {
                            if ((C0053.f92 ^ (C0001.f1 / (-3451))) >= 0) {
                                C0053.m574();
                                strDecode18 = NPStringFog.decode("B5D7B6C6B5FE");
                                str2 = str8;
                                fileOutputStream7 = null;
                                iM503 = C0053.m562(strDecode18);
                                str8 = str2;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            } else {
                                str2 = str8;
                                fileOutputStream7 = null;
                                iM503 = C0002.m230(strDecode21);
                                str8 = str2;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            }
                        }
                        if ((C0001.f1 ^ (C0043.f73 / 6233)) > 0) {
                            strDecode22 = NPStringFog.decode("B5D2B6C4B5C6");
                            str2 = str8;
                            iM503 = C0052.m503(strDecode22);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0052.f91 / C0002.f2;
                            i12 = 1752550;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752455:
                    case 1755525:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.m574() >= 0) {
                            C0053.f92 = 26;
                            strDecode6 = NPStringFog.decode("B5D6B6C9B5C4");
                            str2 = str8;
                            iM503 = C0053.m562(strDecode6);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0002.f2 * C0002.f2;
                            i12 = 1743022;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752489:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.f92 >= 0) {
                            C0002.f2 = 72;
                            iM503 = C0001.m190(NPStringFog.decode("B5D1B6C5B5C5"));
                            parcelFileDescriptor3 = parcelFileDescriptor;
                            z = false;
                        } else {
                            str2 = str8;
                            z = false;
                            str4 = strDecode14;
                            iM503 = C0043.m455(strDecode24);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752522:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            bArr = new byte[1024000];
                        } catch (IOException e11) {
                            iOException2 = e11;
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            if ((C0001.f1 ^ (C0001.f1 / 9637)) > 0) {
                            }
                            strDecode14 = str3;
                            parcelFileDescriptor7 = parcelFileDescriptor8;
                            fileOutputStream = fileOutputStream5;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } catch (Throwable th10) {
                            th = th10;
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0001.f1 > 0) {
                            }
                            strDecode14 = str3;
                            fileOutputStream4 = fileOutputStream6;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        if ((C0043.f73 ^ (C0002.f2 / 319)) >= 0) {
                            C0052.m520();
                            iM503 = C0053.m562(NPStringFog.decode("B5D3B6C0B5C1"));
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0002.f2 % C0043.f73;
                            i12 = 1749739;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        break;
                    case 1752552:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        z2 = true;
                        if (C0053.m574() >= 0) {
                            C0052.f91 = 36;
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0001.m190(str9);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            str2 = str8;
                            iM503 = C0002.m230(strDecode15);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752555:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.f92 - (C0052.f91 - 4557) <= 0) {
                            C0002.f2 = 76;
                            strDecode11 = NPStringFog.decode("B5D8B6C1B5C5");
                            iM503 = C0001.m190(strDecode11);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0002.f2 ^ C0053.f92;
                            i12 = 1746040;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752584:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.m574() >= 0) {
                            strDecode23 = NPStringFog.decode("B5D8B6C9B5C0");
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0043.m455(strDecode23);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0053.f92 - C0001.f1;
                            i12 = 57648;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752644:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (fileOutputStream3 == null) {
                            str2 = str8;
                            str4 = strDecode14;
                            if (C0053.f92 + (C0053.f92 % (-3727)) < 0) {
                            }
                        } else if (C0002.f2 >= 0) {
                            C0052.m520();
                            strDecode22 = NPStringFog.decode("B5D2B6C7B5C0");
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0053.m562(strDecode22);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0043.f73 ^ C0053.f92;
                            i12 = 1752670;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        break;
                    case 1752674:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.f92 % (C0001.f1 * 9650) >= 0) {
                            C0043.f73 = 45;
                            str2 = str8;
                            strDecode7 = NPStringFog.decode("B5D1B6C9B5C1");
                            iM503 = C0052.m503(strDecode7);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i13 = C0002.f2 - C0052.f91;
                            i14 = 1752777;
                            iM503 = i13 ^ i14;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1752737:
                        throw th;
                    case 1753420:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        File file4 = new File(str8, C0002.m223(file2));
                        if (C0053.m574() >= 0) {
                            parcelFileDescriptor3 = parcelFileDescriptor;
                            fileOutputStream7 = null;
                            file = file4;
                            iM503 = C0002.m230(NPStringFog.decode("B5D8B6C7B5C4"));
                        } else {
                            fileOutputStream7 = null;
                            file = file4;
                            strDecode11 = NPStringFog.decode("B5D4B6C9B5FE");
                            iM503 = C0001.m190(strDecode11);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1753544:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            try {
                            } catch (IOException e12) {
                                iOException2 = e12;
                                str2 = str8;
                                sb = sb4;
                                str3 = strDecode14;
                                if (C0002.f2 < 0) {
                                    C0053.m574();
                                    iM503 = C0053.m562(NPStringFog.decode("B5D4B6C0B5C1"));
                                    strDecode14 = str3;
                                    str8 = str2;
                                    sb4 = sb;
                                    parcelFileDescriptor3 = parcelFileDescriptor;
                                    fileOutputStream = null;
                                    parcelFileDescriptor7 = null;
                                } else {
                                    fileOutputStream = null;
                                    parcelFileDescriptor7 = null;
                                    strDecode28 = NPStringFog.decode("B5EFB6FEB5C2");
                                    iM503 = C0002.m230(strDecode28);
                                    strDecode14 = str3;
                                    str8 = str2;
                                    sb4 = sb;
                                    parcelFileDescriptor3 = parcelFileDescriptor;
                                }
                            }
                        } catch (Throwable th11) {
                            th = th11;
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            if (C0001.m192() < 0) {
                                C0053.m574();
                                iM503 = C0053.m562(strDecode27);
                                strDecode14 = str3;
                                fileOutputStream4 = fileOutputStream7;
                                str8 = str2;
                                sb4 = sb;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            } else {
                                strDecode22 = NPStringFog.decode("B5EFB6C3B5C7");
                                fileOutputStream4 = fileOutputStream7;
                                iM503 = C0053.m562(strDecode22);
                                strDecode14 = str3;
                                str8 = str2;
                                sb4 = sb;
                                parcelFileDescriptor3 = parcelFileDescriptor;
                            }
                        }
                        if (C0001.m200(C0052.m524(file))) {
                            str2 = str8;
                            if (C0002.f2 % (C0002.f2 % (-3440)) == 0) {
                            }
                        } else if (C0002.f2 * (C0002.f2 / (-2957)) != 0) {
                            strDecode10 = NPStringFog.decode("B5D3B6C4");
                            fileOutputStream7 = null;
                            iM503 = C0043.m455(strDecode10);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode9 = NPStringFog.decode("B5D1B6C1B5C0");
                            str2 = str8;
                            fileOutputStream7 = null;
                            iM503 = C0002.m230(strDecode9);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                        break;
                    case 1753577:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        try {
                            C0002.m225(fileOutputStream3);
                        } catch (IOException e13) {
                            if (C0052.f91 - (C0001.f1 + 9002) >= 0) {
                                C0053.m574();
                                iM230 = C0002.m230(NPStringFog.decode("B5D5B6C3B5C9"));
                            } else {
                                iM230 = (C0001.f1 % C0001.f1) + 56322;
                            }
                            iOException = e13;
                            iM503 = iM230;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                            parcelFileDescriptor5 = parcelFileDescriptor3;
                        }
                        if (C0053.f92 * (C0002.f2 + 4161) >= 0) {
                            strDecode16 = NPStringFog.decode("B5D0B6C7B5C2");
                            iM503 = C0053.m562(strDecode16);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i11 = C0053.f92 % C0052.f91;
                            i12 = 1749769;
                            iM503 = i11 + i12;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1753633:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if ((C0001.f1 ^ (C0043.f73 / 6233)) > 0) {
                        }
                        break;
                    case 1753637:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        iM503 = (C0052.f91 - C0043.f73) ^ 56423;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                        parcelFileDescriptor8 = null;
                    case 1754413:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.f92 >= 0) {
                            iM503 = C0043.m455(strDecode15);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                            parcelFileDescriptor8 = parcelFileDescriptor3;
                        } else {
                            strDecode10 = NPStringFog.decode("B5D7B6C5B5C0");
                            parcelFileDescriptor8 = parcelFileDescriptor;
                            iM503 = C0043.m455(strDecode10);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1754470:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0043.f73 - (C0043.f73 + 9933) >= 0) {
                            C0001.m192();
                            iM503 = C0053.m562(NPStringFog.decode("B5D5B6C9B5C3"));
                            parcelFileDescriptor3 = parcelFileDescriptor;
                            parcelFileDescriptor8 = null;
                        } else {
                            str2 = str8;
                            parcelFileDescriptor8 = null;
                            iM503 = C0043.m455(strDecode17);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1754532:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0052.f91 * (C0001.f1 - 7763) <= 0) {
                            C0052.m520();
                            strDecode5 = NPStringFog.decode("B5D8B6C0B5C2");
                            str2 = str8;
                            fileOutputStream5 = fileOutputStream3;
                            iM503 = C0001.m190(strDecode5);
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            fileOutputStream5 = fileOutputStream3;
                            strDecode22 = NPStringFog.decode("B5D2B6C7B5C0");
                            str2 = str8;
                            sb = sb4;
                            str3 = strDecode14;
                            iM503 = C0053.m562(strDecode22);
                            strDecode14 = str3;
                            str8 = str2;
                            sb4 = sb;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1754599:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0001.m192() < 0) {
                            i15 = C0001.f1 % C0001.f1;
                            i16 = 56296;
                            break;
                        } else {
                            C0002.f2 = 76;
                            strDecode13 = NPStringFog.decode("B5D5B6FEB5C0");
                            break;
                        }
                    case 1754657:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.m574() >= 0) {
                            strDecode10 = NPStringFog.decode("B5D1B6C7B5C1");
                            iM503 = C0043.m455(strDecode10);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i13 = C0052.f91 ^ C0002.f2;
                            i14 = 1747496;
                            iM503 = i13 ^ i14;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1754661:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0001.f1 / (C0053.f92 | 9881) >= 0) {
                            iM503 = C0002.m230(NPStringFog.decode("B5D1B6FE"));
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i13 = C0043.f73 * C0053.f92;
                            i14 = 1621501;
                            iM503 = i13 ^ i14;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1755402:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0002.m259() >= 0) {
                            strDecode11 = NPStringFog.decode("B5D1B6C2B5C9");
                            fileOutputStream6 = fileOutputStream3;
                            iM503 = C0001.m190(strDecode11);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            iM503 = C0052.m503(NPStringFog.decode("B5D7B6C1B5C7"));
                            fileOutputStream6 = fileOutputStream3;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1755431:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        iM503 = (C0052.f91 ^ C0052.f91) ^ 1750722;
                        parcelFileDescriptor6 = parcelFileDescriptor7;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                    case 1755436:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if ((C0053.f92 ^ (C0052.f91 / 909)) >= 0) {
                            str2 = str8;
                            str4 = strDecode14;
                            strDecode12 = NPStringFog.decode("B5D5B6C0B5C7");
                            iM503 = C0001.m190(strDecode12);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            strDecode12 = NPStringFog.decode("B5D3B6C7B5C5");
                            str2 = str8;
                            str4 = strDecode14;
                            iM503 = C0001.m190(strDecode12);
                            strDecode14 = str4;
                            str8 = str2;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1755527:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str2 = str8;
                        if ((C0052.f91 | C0043.f73 | (-1402)) < 0) {
                        }
                        str4 = strDecode14;
                        iM503 = C0043.m455(strDecode2);
                        strDecode14 = str4;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                        break;
                    case 1755556:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0052.f91 + C0053.f92 + 8938 <= 0) {
                            C0052.m520();
                            iM503 = C0053.m562(NPStringFog.decode("B5D7B6C9B5C3"));
                            fileOutputStream7 = fileOutputStream;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            fileOutputStream7 = fileOutputStream;
                            iM503 = C0053.m562(strDecode16);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    case 1755559:
                        parcelFileDescriptor = parcelFileDescriptor3;
                        if (C0053.f92 + (C0001.f1 * 9253) <= 0) {
                            strDecode11 = NPStringFog.decode("B5D8B6C0B5FE");
                            iM503 = C0001.m190(strDecode11);
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        } else {
                            i13 = C0052.f91 / C0052.f91;
                            i14 = 1752675;
                            iM503 = i13 ^ i14;
                            parcelFileDescriptor3 = parcelFileDescriptor;
                        }
                    default:
                        str2 = str8;
                        parcelFileDescriptor = parcelFileDescriptor3;
                        str8 = str2;
                        parcelFileDescriptor3 = parcelFileDescriptor;
                }
            }
            parcelFileDescriptor3 = parcelFileDescriptor;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0104 A[SYNTHETIC] */
    /* renamed from: ۟۟۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m353(byte[] bArr) throws NumberFormatException {
        String strDecode;
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode2 = NPStringFog.decode("B5EFB6C4B5C9");
        int iM455 = C0043.m455(strDecode2);
        long j = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        byte b = 0;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D7B6C6B5C2");
            String strDecode4 = NPStringFog.decode("B5D7B6C9B5C5");
            switch (iM455) {
                case 56359:
                    j = Long.parseLong(C0052.m511(NPStringFog.decode("033E172C382B0F02142120353B2252253D46094218510507")));
                    if (C0053.m574() >= 0) {
                        C0043.f73 = 97;
                        iM455 = C0053.m562(NPStringFog.decode("B5D7B6C3B5C5"));
                    } else {
                        iM455 = C0052.m503("ۧۡۦ");
                    }
                case 1746882:
                    iM455 = C0001.m190(strDecode);
                case 1747745:
                    break;
                case 1747869:
                    iM455 = (C0053.f92 ^ C0001.f1) + 1751348;
                    i6 = i5;
                case 1748859:
                    strDecode3 = "ۣ۠ۢ";
                    iM455 = C0002.m230(strDecode3);
                case 1749601:
                    if (C0001.f1 > 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D8B6C5");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i3 = C0002.f2 - C0053.f92;
                        i4 = 1755117;
                        iM455 = i3 ^ i4;
                    }
                case 1749703:
                    bArr[i6] = bArr[i7];
                    if ((C0001.f1 | (C0001.f1 * (-2002))) >= 0) {
                        strDecode = NPStringFog.decode("B5D1B6C6B5C0");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i = C0053.f92 / C0001.f1;
                        i2 = 1749728;
                        iM455 = i + i2;
                    }
                case 1749726:
                    bArr[i7] = b;
                    if (C0001.f1 / (C0043.f73 + 8608) != 0) {
                        C0052.m520();
                        iM455 = C0043.m455(strDecode3);
                    } else {
                        strDecode = NPStringFog.decode("B5D3B6C6B5C1");
                        iM455 = C0001.m190(strDecode);
                    }
                case 1749818:
                    iM455 = C0053.m562(strDecode4);
                    i6 = 0;
                case 1749823:
                    i7 = i6 + (C0043.f73 ^ (-168));
                    strDecode = NPStringFog.decode("B5D2B6C2B5C9");
                    iM455 = C0001.m190(strDecode);
                case 1750757:
                    i = C0001.f1 / C0053.f92;
                    i2 = 1754473;
                    iM455 = i + i2;
                case 1750780:
                    i5 = i6 + (C0052.f91 ^ (-164));
                    if (C0053.f92 < 0) {
                        i3 = C0053.f92 / C0053.f92;
                        i4 = 1747868;
                        iM455 = i3 ^ i4;
                    }
                case 1752733:
                case 1754473:
                    iM455 = C0001.m190(strDecode4);
                case 1753700:
                    strDecode3 = C0001.f1 <= 0 ? NPStringFog.decode("B5D7B6C4B5C3") : strDecode2;
                    iM455 = C0002.m230(strDecode3);
                case 1754383:
                    if (C0053.m574() >= 0) {
                        strDecode = C0053.f92 * (C0053.f92 ^ 5538) <= 0 ? NPStringFog.decode("B5D2B6C6B5FE") : NPStringFog.decode("B5D1B6C9");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        strDecode3 = "ۣ۠ۢ";
                        iM455 = C0002.m230(strDecode3);
                    }
                case 1754444:
                    System.out.println(j);
                    iM455 = C0052.f91 % (C0043.f73 % (-383)) >= 0 ? C0002.m230("ۧۡۦ") : C0053.m562("ۣ۠ۢ");
                case 1754627:
                    b = bArr[i6];
                    i = C0052.f91 / C0043.f73;
                    i2 = 1749823;
                    iM455 = i + i2;
                case 1754659:
                    if (i6 < bArr.length + (C0052.f91 ^ 161)) {
                        if (C0043.f73 >= 0) {
                            C0002.m259();
                            iM455 = C0052.m503(NPStringFog.decode("B5D6B6C9B5C7"));
                        } else {
                            iM455 = C0002.m230(strDecode3);
                        }
                    } else if (C0001.f1 > 0) {
                    }
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0017. Please report as an issue. */
    /* renamed from: ۟۟۟۟ۢ, reason: not valid java name and contains not printable characters */
    private static void m355() {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5D8B6C1B5C2");
        int iM190 = C0001.m190(strDecode2);
        Object obj = null;
        C0011 c0011 = null;
        Class clsM528 = null;
        Method methodM189 = null;
        Method methodM1892 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D8B6C9B5C7");
            switch (iM190) {
                case 1746850:
                    f29 = C0052.m506(C0001.m189(AssetManager.class, C0043.m454(m371(), 85, C0053.f92 ^ (-812), 2322), new Class[]{String.class}), C0001.m189(obj, C0052.m507(m371(), 91, 1, 935), new Class[]{C0015.class}), c0011);
                    if (C0052.m520() <= 0) {
                        C0002.m259();
                        strDecode = NPStringFog.decode("B5D3B6FEB5C6");
                    } else {
                        strDecode = NPStringFog.decode("B5D2B6C2B5C2");
                    }
                    iM190 = C0001.m190(strDecode);
                case 1748866:
                    c0011 = new C0011();
                    i = C0002.f2 * C0052.f91;
                    i2 = 1743030;
                    iM190 = i + i2;
                case 1749698:
                    break;
                case 1749824:
                    methodM189 = C0001.m189(AssetManager.class, C0043.m454(m371(), 80, C0043.f73 ^ (-163), 3143), new Class[]{String.class, clsM528});
                    if (C0053.m574() >= 0) {
                        iM190 = C0043.m455(strDecode3);
                    } else {
                        i = C0052.f91 + C0002.f2;
                        i2 = 1751826;
                        iM190 = i + i2;
                    }
                case 1750539:
                    strDecode3 = C0052.m520() <= 0 ? NPStringFog.decode("B5D3B6C9B5FE") : strDecode2;
                    iM190 = C0002.m230(strDecode3);
                case 1751588:
                    obj = C0011.class;
                    iM190 = C0002.m230(strDecode3);
                case 1755342:
                    f28 = C0052.m506(methodM189, methodM1892, c0011);
                    if ((C0052.f91 | (C0052.f91 - 3744)) >= 0) {
                        C0053.f92 = 48;
                        strDecode = NPStringFog.decode("B5D2B6C2B5C2");
                        iM190 = C0001.m190(strDecode);
                    } else {
                        iM190 = C0052.m503(NPStringFog.decode("B5EFB6C5B5C6"));
                    }
                case 1755371:
                    clsM528 = C0052.m528();
                    if ((C0001.f1 ^ (C0053.f92 % 5317)) >= 0) {
                        C0001.f1 = 0;
                        iM190 = C0052.m503(NPStringFog.decode("B5EFB6C5B5C6"));
                    } else {
                        i = C0043.f73 * C0043.f73;
                        i2 = 1721935;
                        iM190 = i + i2;
                    }
                case 1755622:
                    methodM1892 = C0001.m189(obj, C0052.m507(m371(), 84, 1, 3040), new Class[]{C0015.class});
                    if (C0053.f92 * (C0052.f91 ^ 7220) <= 0) {
                        C0001.m192();
                        iM190 = C0053.m562(NPStringFog.decode("B5D4B6C3B5C3"));
                    } else {
                        iM190 = C0043.m455(NPStringFog.decode("B5D1B6C6B5C9"));
                    }
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* renamed from: ۣ۟۟۟۟, reason: not valid java name and contains not printable characters */
    private static void m356() {
        String strDecode;
        int i;
        int i2;
        int iM455 = C0043.m455(NPStringFog.decode("B5D6B6C5B5C9"));
        Class clsM528 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D1B6C3B5C5");
            switch (iM455) {
                case 56294:
                    C0002.m240(C0001.m189(AssetManager.class, C0053.m577(m371(), 92, C0053.f92 ^ (-810), 2094), new Class[]{String.class, clsM528}), new AbstractC0039() { // from class: np.protect.assets.p.۟۟۟۟۟.1
                        /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000e. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:42:0x00a9 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:43:0x0099 A[SYNTHETIC] */
                        {
                            String strDecode3;
                            String strDecode4 = NPStringFog.decode("B5EFB6C3");
                            int iM190 = C0001.m190(strDecode4);
                            Float fDecode = null;
                            while (true) {
                                switch (iM190) {
                                    case 56291:
                                        if (C0052.m520() <= 0) {
                                            String strDecode5 = C0002.f2 * (C0043.f73 ^ (-8105)) >= 0 ? NPStringFog.decode("B5D5B6C5B5C5") : NPStringFog.decode("B5D7B6C7B5C7");
                                            iM190 = C0053.m562(strDecode5);
                                        } else if ((C0052.f91 | C0002.f2 | (-5427)) < 0) {
                                            C0052.f91 = 17;
                                            iM190 = C0052.m503(NPStringFog.decode("B5D8B6C6"));
                                        } else {
                                            iM190 = C0001.m190(NPStringFog.decode("B5D5B6C0B5C9"));
                                        }
                                    case 1747932:
                                        if ((C0052.f91 | C0002.f2 | (-5427)) < 0) {
                                        }
                                        break;
                                    case 1751560:
                                        System.out.println(fDecode);
                                        if (C0052.f91 >= 0) {
                                            C0043.m456();
                                            iM190 = C0053.m562(strDecode5);
                                        } else {
                                            iM190 = (C0002.f2 + C0043.f73) ^ (-1752383);
                                        }
                                    case 1752524:
                                        break;
                                    case 1752613:
                                        if (C0053.f92 - (C0002.f2 + 2418) >= 0) {
                                            C0053.f92 = 37;
                                            strDecode3 = NPStringFog.decode("B5D1B6C7B5FE");
                                        } else {
                                            strDecode3 = strDecode4;
                                        }
                                        iM190 = C0043.m455(strDecode3);
                                    case 1754599:
                                        fDecode = Float.decode(C0053.m553(NPStringFog.decode("17141C2E3A3154211405")));
                                        if (C0002.f2 % (C0052.f91 - 3348) >= 0) {
                                            C0001.f1 = 16;
                                            iM190 = C0002.m230(strDecode4);
                                        } else {
                                            iM190 = (C0043.f73 | C0053.f92) + 1751597;
                                        }
                                }
                                return;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
                        /* renamed from: ۠ۡ, reason: not valid java name and contains not printable characters */
                        public static InputStream m375(Object obj) {
                            String strDecode3;
                            String strDecode4;
                            int i3;
                            int i4;
                            int iM190 = C0001.m190(NPStringFog.decode("B5D8B6C2B5FE"));
                            InputStream inputStream = null;
                            InputStream inputStreamM366 = null;
                            while (true) {
                                String strDecode5 = NPStringFog.decode("B5EFB6C0B5C3");
                                switch (iM190) {
                                    case 1746752:
                                        if (C0043.f73 - (C0052.f91 + 1945) >= 0) {
                                            strDecode3 = NPStringFog.decode("B5D7B6C9B5C4");
                                            iM190 = C0052.m503(strDecode3);
                                        } else {
                                            strDecode4 = NPStringFog.decode("B5D1B6C6B5C6");
                                            iM190 = C0053.m562(strDecode4);
                                        }
                                    case 1747686:
                                        break;
                                    case 1747807:
                                    case 1754660:
                                        if (C0053.m574() >= 0) {
                                            C0053.f92 = 60;
                                            strDecode5 = NPStringFog.decode("B5D1B6C6B5C0");
                                            iM190 = C0052.m503(strDecode5);
                                        } else {
                                            i3 = C0001.f1 ^ C0052.f91;
                                            i4 = -1747749;
                                            iM190 = i3 ^ i4;
                                        }
                                    case 1748616:
                                        iM190 = C0052.m503(strDecode5);
                                    case 1748707:
                                        iM190 = (C0052.f91 / C0001.f1) + 1747686;
                                        inputStream = inputStreamM366;
                                    case 1748865:
                                        if (C0053.f92 - (C0002.f2 | 7211) >= 0) {
                                            C0002.f2 = 46;
                                            iM190 = C0043.m455(strDecode5);
                                        } else {
                                            iM190 = (C0001.f1 / C0002.f2) ^ (-1754443);
                                        }
                                        inputStream = null;
                                    case 1750631:
                                        if (C0043.m456() <= 0) {
                                            strDecode4 = NPStringFog.decode("B5D1B6C3B5C6");
                                            iM190 = C0053.m562(strDecode4);
                                        } else {
                                            i3 = C0052.f91 % C0001.f1;
                                            i4 = -1755622;
                                            iM190 = i3 ^ i4;
                                        }
                                    case 1754441:
                                        if (C0043.f73 - (C0052.f91 | (-9760)) >= 0) {
                                            C0043.m456();
                                            iM190 = C0001.m190(NPStringFog.decode("B5D8B6C2B5C6"));
                                        } else {
                                            strDecode3 = NPStringFog.decode("B5D0B6C5B5C2");
                                            iM190 = C0052.m503(strDecode3);
                                        }
                                    case 1755460:
                                        if (C0001.m192() >= 0) {
                                            iM190 = C0052.m503(strDecode5);
                                        } else if (C0053.f92 % (C0002.f2 ^ 7676) >= 0) {
                                            C0043.m456();
                                            iM190 = C0043.m455(NPStringFog.decode("B5D3B6C3B5C7"));
                                        } else {
                                            iM190 = C0001.m190(NPStringFog.decode("B5D8B6C2B5C6"));
                                        }
                                    case 1755468:
                                        inputStreamM366 = C0011.m366((InputStream) obj);
                                        int i5 = C0001.f1 % (C0001.f1 - 422);
                                        strDecode5 = NPStringFog.decode("B5D1B6C3B5C5");
                                        if (i5 <= 0) {
                                            C0001.m192();
                                            iM190 = C0002.m230(strDecode5);
                                        } else {
                                            iM190 = C0052.m503(strDecode5);
                                        }
                                }
                                return inputStream;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:51:0x0076 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:53:0x006b A[SYNTHETIC] */
                        /* renamed from: ۢۨۢۦ, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static Set m376() {
                            int i3;
                            int i4;
                            String strDecode3;
                            String strDecode4 = NPStringFog.decode("B5D6B6C5B5C5");
                            int iM230 = C0002.m230(strDecode4);
                            Set setM359 = null;
                            Set set = null;
                            while (true) {
                                String strDecode5 = NPStringFog.decode("B5D3B6C4B5C4");
                                switch (iM230) {
                                    case 56536:
                                    case 1748734:
                                        if (C0001.f1 <= 0) {
                                            C0002.f2 = 24;
                                            strDecode5 = NPStringFog.decode("B5D1B6C3B5C1");
                                            iM230 = C0002.m230(strDecode5);
                                        } else {
                                            i3 = C0043.f73 * C0052.f91;
                                            i4 = 1721593;
                                            iM230 = i3 + i4;
                                        }
                                    case 56540:
                                        set = setM359;
                                        strDecode5 = "ۡ۠ۦ";
                                        iM230 = C0002.m230(strDecode5);
                                    case 1748647:
                                        break;
                                    case 1748770:
                                        if (C0053.f92 / (C0052.f91 % 6772) <= 0) {
                                            C0001.f1 = 45;
                                            iM230 = C0053.m562("ۡ۠ۦ");
                                            set = null;
                                        } else {
                                            set = null;
                                            iM230 = C0002.m230(strDecode5);
                                        }
                                    case 1749698:
                                        setM359 = C0011.m359();
                                        i3 = C0001.f1 * C0052.f91;
                                        i4 = 114050;
                                        iM230 = i3 + i4;
                                    case 1749725:
                                        if (C0052.f91 * (C0001.f1 | 3881) < 0) {
                                            strDecode3 = NPStringFog.decode("B5D7B6C0B5C2");
                                            iM230 = C0052.m503(strDecode3);
                                        } else {
                                            strDecode5 = NPStringFog.decode("B5D8B6C4B5C6");
                                            iM230 = C0002.m230(strDecode5);
                                        }
                                    case 1750723:
                                        if (C0001.f1 * (C0043.f73 / 9125) != 0) {
                                            iM230 = C0053.m562(NPStringFog.decode("B5D2B6C5B5FE"));
                                        } else {
                                            strDecode3 = NPStringFog.decode("B5D7B6FE");
                                            iM230 = C0052.m503(strDecode3);
                                        }
                                    case 1753574:
                                        if (C0002.m259() < 0) {
                                            if ((C0052.f91 ^ (C0043.f73 / 8098)) >= 0) {
                                                C0001.m192();
                                            } else {
                                                strDecode5 = NPStringFog.decode("B5D2B6C2B5C2");
                                            }
                                            iM230 = C0001.m190(strDecode5);
                                        } else if (C0052.f91 * (C0001.f1 | 3881) < 0) {
                                        }
                                        break;
                                    case 1754384:
                                        iM230 = C0053.m562(strDecode4);
                                    case 1755530:
                                        i3 = C0002.f2 / C0043.f73;
                                        i4 = 1748770;
                                        iM230 = i3 + i4;
                                }
                                return set;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:55:0x00d2 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:60:0x00e3 A[SYNTHETIC] */
                        @Override // np.protect.assets.p.AbstractC0039
                        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void mo377(Pine.C0047 c0047) {
                            String strDecode3;
                            int i3;
                            int i4;
                            String strDecode4 = NPStringFog.decode("B5D7B6C6B5C4");
                            int iM4552 = C0043.m455(strDecode4);
                            String str = null;
                            while (true) {
                                String strDecode5 = NPStringFog.decode("B5D8B6C3B5C1");
                                String strDecode6 = "ۤ۟ۢ";
                                switch (iM4552) {
                                    case 56324:
                                        strDecode3 = NPStringFog.decode("B5EFB6C2B5C3");
                                        iM4552 = C0053.m562(strDecode3);
                                    case 1746814:
                                        strDecode5 = "ۤ۟ۢ";
                                        iM4552 = C0002.m230(strDecode5);
                                    case 1746815:
                                    case 1751495:
                                        if (C0052.f91 / (C0002.f2 ^ 5233) == 0) {
                                            C0053.f92 = 47;
                                            iM4552 = C0043.m455(NPStringFog.decode("B5D1B6C7B5C3"));
                                        } else {
                                            iM4552 = C0002.m230(strDecode5);
                                        }
                                    case 1746942:
                                        if ((C0043.f73 | (C0053.f92 % (-4744))) >= 0) {
                                            C0001.m192();
                                            strDecode5 = NPStringFog.decode("B5EFB6C4B5C5");
                                            iM4552 = C0002.m230(strDecode5);
                                        } else {
                                            i3 = C0001.f1 - C0043.f73;
                                            i4 = 1747254;
                                            iM4552 = i3 + i4;
                                        }
                                    case 1747776:
                                        try {
                                            C0002.m243(c0047, m375((InputStream) C0053.m575(c0047)));
                                        } catch (IOException e) {
                                            C0052.m538(c0047, e);
                                            if (C0002.f2 % (C0001.f1 + 1609) >= 0) {
                                                C0052.f91 = 93;
                                                break;
                                            } else {
                                                strDecode6 = NPStringFog.decode("B5EFB6C2B5C2");
                                            }
                                        }
                                        if (C0002.m259() >= 0) {
                                            C0052.f91 = 92;
                                            iM4552 = C0053.m562(strDecode6);
                                        } else {
                                            i3 = C0002.f2 | C0043.f73;
                                            i4 = 1746817;
                                            iM4552 = i3 + i4;
                                        }
                                    case 1750726:
                                        strDecode3 = C0002.f2 >= 0 ? NPStringFog.decode("B5D8B6C1B5C0") : strDecode4;
                                        iM4552 = C0053.m562(strDecode3);
                                    case 1751530:
                                        if (C0052.m502(m376(), str)) {
                                            if (C0001.m192() >= 0) {
                                                C0002.f2 = 40;
                                                iM4552 = C0002.m230(NPStringFog.decode("B5D3B6C4B5C9"));
                                            } else {
                                                iM4552 = (C0002.f2 / C0052.f91) ^ 1747776;
                                            }
                                        } else if (C0052.f91 / (C0002.f2 ^ 5233) == 0) {
                                        }
                                        break;
                                    case 1754629:
                                        str = (String) C0002.m245(c0047)[0];
                                        int i5 = C0052.f91 / (C0052.f91 % (-4248));
                                        iM4552 = C0001.m190(NPStringFog.decode("B5D4B6C1B5C7"));
                                    case 1755430:
                                        break;
                                }
                                return;
                            }
                        }
                    });
                    if (C0053.f92 >= 0) {
                        C0043.m456();
                        strDecode = NPStringFog.decode("B5D4B6C4B5C7");
                    } else {
                        strDecode = NPStringFog.decode("B5D2B6C5B5C3");
                    }
                    iM455 = C0001.m190(strDecode);
                case 1748707:
                    break;
                case 1749728:
                    C0002.m240(C0001.m189(AssetManager.class, C0053.m577(m371(), 96, C0043.f73 ^ (-161), 1132), new Class[]{String.class}), new AbstractC0039() { // from class: np.protect.assets.p.۟۟۟۟۟.2
                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0016. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:41:0x0049 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:42:0x003c A[SYNTHETIC] */
                        {
                            int i3;
                            int i4;
                            int iM190 = C0001.m190(NPStringFog.decode("B5D5B6C4B5FE"));
                            int i5 = 0;
                            while (true) {
                                String strDecode3 = NPStringFog.decode("B5EFB6C2B5C3");
                                switch (iM190) {
                                    case 1746814:
                                        i5 = Integer.parseInt(C0043.m453(NPStringFog.decode("06201E3B5A3817330B380A")));
                                        iM190 = C0002.m259() >= 0 ? C0053.m562(strDecode3) : C0053.m562(NPStringFog.decode("B5D1B6C5B5C1"));
                                    case 1746969:
                                        break;
                                    case 1748765:
                                        System.out.println(i5);
                                        if ((C0001.f1 | (C0001.f1 ^ (-7342))) >= 0) {
                                            C0043.f73 = 85;
                                        } else {
                                            i3 = C0043.f73 | C0043.f73;
                                            i4 = 1747136;
                                            iM190 = i3 + i4;
                                        }
                                    case 1752551:
                                        if (C0043.f73 >= 0) {
                                            C0043.f73 = 74;
                                            strDecode3 = NPStringFog.decode("B5D0B6C3B5C6");
                                            iM190 = C0052.m503(strDecode3);
                                        } else {
                                            i3 = C0002.f2 - C0002.f2;
                                            i4 = 1752639;
                                            iM190 = i3 + i4;
                                        }
                                    case 1752639:
                                        if (C0052.m520() <= 0) {
                                            if (C0053.f92 - (C0052.f91 | (-3906)) >= 0) {
                                                C0043.f73 = 16;
                                                iM190 = C0043.m455(NPStringFog.decode("B5D5B6C3B5C5"));
                                            } else {
                                                iM190 = C0052.m503(strDecode3);
                                            }
                                        } else if (C0043.m456() > 0) {
                                            C0052.f91 = 16;
                                            iM190 = C0001.m190(NPStringFog.decode("B5D1B6C4B5C9"));
                                        } else {
                                            iM190 = (C0002.f2 | C0043.f73) ^ (-1746972);
                                        }
                                    case 1755430:
                                        if (C0043.m456() > 0) {
                                        }
                                        break;
                                }
                                return;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
                        /* JADX WARN: Removed duplicated region for block: B:46:0x00b8 A[SYNTHETIC] */
                        /* JADX WARN: Removed duplicated region for block: B:50:0x00a8 A[SYNTHETIC] */
                        /* renamed from: ۟ۢۦۦۥ, reason: not valid java name and contains not printable characters */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public static AssetFileDescriptor m378(Object obj, Object obj2) {
                            int i3;
                            int i4;
                            String strDecode3;
                            int iM230 = C0002.m230(NPStringFog.decode("B5D3B6C7B5C0"));
                            AssetFileDescriptor assetFileDescriptor = null;
                            AssetFileDescriptor assetFileDescriptorM343 = null;
                            while (true) {
                                String strDecode4 = NPStringFog.decode("B5D7B6C1B5C4");
                                switch (iM230) {
                                    case 56290:
                                        if (C0052.f91 < 0) {
                                            C0001.f1 = 27;
                                            strDecode4 = NPStringFog.decode("B5D0B6C6B5C1");
                                            iM230 = C0001.m190(strDecode4);
                                        } else {
                                            iM230 = C0002.m230(NPStringFog.decode("B5D7B6FEB5C7"));
                                        }
                                    case 56387:
                                        iM230 = C0043.f73 >= 0 ? C0053.m562(NPStringFog.decode("B5D2B6C1B5FE")) : (C0052.f91 | C0043.f73) + 1747072;
                                        assetFileDescriptor = null;
                                    case 1746911:
                                        iM230 = (C0002.f2 ^ C0043.f73) + 1755349;
                                    case 1747805:
                                        break;
                                    case 1749601:
                                        i3 = C0052.f91 - C0052.f91;
                                        i4 = 1750750;
                                        iM230 = i3 ^ i4;
                                    case 1749702:
                                    case 1755586:
                                        iM230 = C0052.m503(NPStringFog.decode("B5D0B6C5B5C0"));
                                    case 1750750:
                                        if (C0002.m259() <= 0) {
                                            int i5 = C0052.f91;
                                            strDecode3 = NPStringFog.decode("B5D8B6C7B5C6");
                                            iM230 = C0043.m455(strDecode3);
                                        } else if (C0052.f91 < 0) {
                                        }
                                        break;
                                    case 1754382:
                                        if (C0001.m192() >= 0) {
                                            C0052.f91 = 71;
                                            strDecode3 = NPStringFog.decode("B5EFB6C0");
                                            iM230 = C0043.m455(strDecode3);
                                        } else {
                                            i3 = C0002.f2 ^ C0001.f1;
                                            i4 = -56684;
                                            iM230 = i3 ^ i4;
                                        }
                                    case 1754412:
                                        iM230 = C0043.f73 + (C0043.f73 / (-9951)) >= 0 ? C0001.m190(strDecode4) : (C0053.f92 - C0043.f73) + 1748452;
                                        assetFileDescriptor = assetFileDescriptorM343;
                                    case 1755561:
                                        assetFileDescriptorM343 = C0011.m343((InputStream) obj, (String) obj2);
                                        iM230 = C0001.m190(strDecode4);
                                }
                                return assetFileDescriptor;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
                        /* renamed from: ۧۡۨ۟, reason: not valid java name and contains not printable characters */
                        public static Set m379() {
                            String strDecode3;
                            String strDecode4;
                            String strDecode5 = NPStringFog.decode("B5D4B6C4B5FE");
                            int iM503 = C0052.m503(strDecode5);
                            Set set = null;
                            Set setM359 = null;
                            while (true) {
                                String strDecode6 = NPStringFog.decode("B5D0B6C2B5C2");
                                switch (iM503) {
                                    case 56447:
                                    case 1748707:
                                        if (C0043.f73 % (C0043.f73 % 2955) != 0) {
                                            strDecode3 = NPStringFog.decode("B5D6B6C1B5C9");
                                            iM503 = C0043.m455(strDecode3);
                                        } else {
                                            iM503 = (C0002.f2 * C0043.f73) + 1735084;
                                        }
                                    case 1747776:
                                        break;
                                    case 1749731:
                                        strDecode4 = NPStringFog.decode("B5D6B6C0B5C7");
                                        iM503 = C0002.m230(strDecode4);
                                    case 1749788:
                                        if (C0002.m259() >= 0) {
                                            C0052.f91 = 26;
                                            iM503 = C0001.m190(NPStringFog.decode("B5D5B6C6B5C3"));
                                        } else {
                                            iM503 = (C0002.f2 + C0002.f2) ^ (-1751786);
                                        }
                                    case 1751678:
                                        if (C0001.m192() < 0) {
                                            strDecode3 = NPStringFog.decode("B5D7B6C3B5C5");
                                            iM503 = C0043.m455(strDecode3);
                                        } else {
                                            strDecode4 = NPStringFog.decode("B5D6B6C0B5C7");
                                            iM503 = C0002.m230(strDecode4);
                                        }
                                    case 1752646:
                                        iM503 = (C0002.f2 - C0043.f73) + 1755245;
                                        set = null;
                                    case 1753483:
                                        if (C0052.f91 / (C0002.f2 | 1531) <= 0) {
                                            iM503 = C0052.m503(strDecode6);
                                        } else {
                                            strDecode4 = NPStringFog.decode("B5D5B6C4B5C7");
                                            iM503 = C0002.m230(strDecode4);
                                        }
                                    case 1753663:
                                        if (C0052.m520() <= 0) {
                                            C0052.m520();
                                            iM503 = C0052.m503(strDecode5);
                                        } else {
                                            iM503 = C0043.m455(strDecode6);
                                        }
                                        set = setM359;
                                    case 1754473:
                                        setM359 = C0011.m359();
                                        if (C0043.f73 / (C0002.f2 ^ 8582) != 0) {
                                            C0052.f91 = 24;
                                            strDecode4 = NPStringFog.decode("B5D5B6C4B5C7");
                                            iM503 = C0002.m230(strDecode4);
                                        } else {
                                            strDecode4 = NPStringFog.decode("B5D6B6C6B5C1");
                                            iM503 = C0002.m230(strDecode4);
                                        }
                                    case 1755336:
                                        iM503 = C0053.m562(NPStringFog.decode("B5D1B6C3B5C5"));
                                }
                                return set;
                            }
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000b. Please report as an issue. */
                        @Override // np.protect.assets.p.AbstractC0039
                        /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
                        public void mo380(Pine.C0047 c0047) {
                            int i3;
                            int i4;
                            String strDecode3;
                            String strDecode4;
                            int i5;
                            int i6;
                            String strDecode5;
                            int iM4552 = C0043.m455(NPStringFog.decode("B5D4B6C3B5C6"));
                            String str = null;
                            while (true) {
                                switch (iM4552) {
                                    case 56483:
                                        break;
                                    case 1748609:
                                        i3 = C0043.f73 * C0053.f92;
                                        i4 = 1616262;
                                        iM4552 = i3 ^ i4;
                                    case 1748740:
                                        if (C0053.f92 >= 0) {
                                            strDecode3 = NPStringFog.decode("B5EFB6C1B5C0");
                                            iM4552 = C0052.m503(strDecode3);
                                        } else {
                                            i3 = C0052.f91 | C0053.f92;
                                            i4 = -1750685;
                                            iM4552 = i3 ^ i4;
                                        }
                                    case 1749579:
                                    case 1755584:
                                        strDecode3 = NPStringFog.decode("B5D5B6C9");
                                        iM4552 = C0052.m503(strDecode3);
                                    case 1750717:
                                        try {
                                            C0002.m243(c0047, m378(C0053.m559(C0002.m234(), str), str));
                                        } catch (Throwable th) {
                                            C0052.m538(c0047, th);
                                            if (C0002.f2 * C0001.f1 * (-1186) <= 0) {
                                                C0002.f2 = 83;
                                                strDecode5 = NPStringFog.decode("B5D1B6FEB5FE");
                                                break;
                                            } else {
                                                i5 = C0001.f1 | C0053.f92;
                                                i6 = 1756109;
                                            }
                                        }
                                        if (C0043.m456() <= 0) {
                                            strDecode4 = NPStringFog.decode("B5D3B6C9B5C7");
                                            iM4552 = C0053.m562(strDecode4);
                                        } else {
                                            i5 = C0002.f2 % C0052.f91;
                                            i6 = 1751760;
                                            iM4552 = i5 + i6;
                                        }
                                    case 1750817:
                                        if (!C0052.m502(m379(), str)) {
                                            strDecode3 = NPStringFog.decode("B5D5B6C9");
                                            iM4552 = C0052.m503(strDecode3);
                                        } else if (C0052.m520() <= 0) {
                                            C0043.f73 = 51;
                                            strDecode4 = NPStringFog.decode("B5D1B6C2B5C7");
                                            iM4552 = C0053.m562(strDecode4);
                                        } else {
                                            i5 = C0043.f73 | C0043.f73;
                                            i6 = 1750884;
                                            iM4552 = i5 + i6;
                                        }
                                    case 1751593:
                                        str = (String) C0002.m245(c0047)[0];
                                        if (C0052.m520() <= 0) {
                                            strDecode3 = NPStringFog.decode("B5D3B6C4B5FE");
                                            iM4552 = C0052.m503(strDecode3);
                                        } else {
                                            strDecode4 = NPStringFog.decode("B5D3B6C9B5C7");
                                            iM4552 = C0053.m562(strDecode4);
                                        }
                                    case 1751684:
                                        i5 = C0053.f92 ^ C0052.f91;
                                        i6 = 1748671;
                                        iM4552 = i5 + i6;
                                    case 1753420:
                                        if (C0001.f1 <= 0) {
                                            C0002.m259();
                                            strDecode5 = NPStringFog.decode("B5D7B6C5B5C0");
                                            iM4552 = C0001.m190(strDecode5);
                                        } else {
                                            i5 = C0052.f91 / C0001.f1;
                                            i6 = 1751593;
                                            iM4552 = i5 + i6;
                                        }
                                }
                                return;
                            }
                        }
                    });
                    iM455 = C0043.m455(strDecode2);
                case 1751685:
                    if (C0053.m574() >= 0) {
                        strDecode = NPStringFog.decode("B5EFB6C1B5C4");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i = C0053.f92 / C0052.f91;
                        i2 = 1753573;
                        iM455 = i + i2;
                    }
                case 1753578:
                    clsM528 = C0052.m528();
                    if (C0001.f1 / (C0052.f91 % (-3707)) >= 0) {
                        iM455 = C0052.m503(strDecode2);
                    } else {
                        i = C0043.f73 | C0053.f92;
                        i2 = 56331;
                        iM455 = i + i2;
                    }
            }
            return;
        }
    }

    /* renamed from: ۟۟۟۟ۤ, reason: not valid java name and contains not printable characters */
    private static void m357() {
        String strDecode;
        String strDecode2;
        String strDecode3;
        int i;
        int i2;
        String strDecode4 = NPStringFog.decode("B5D0B6C4B5C1");
        int iM503 = C0052.m503(strDecode4);
        while (true) {
            switch (iM503) {
                case 56543:
                    if (C0001.f1 <= 0) {
                        C0052.f91 = 30;
                        strDecode = NPStringFog.decode("B5D1B6C5B5C3");
                    } else {
                        strDecode = strDecode4;
                    }
                    iM503 = C0001.m190(strDecode);
                case 1746785:
                    if (C0001.m192() >= 0) {
                        C0002.f2 = 88;
                        strDecode2 = NPStringFog.decode("B5D2B6C6B5C9");
                    } else {
                        strDecode2 = NPStringFog.decode("B5D0B6C1B5C7");
                    }
                    iM503 = C0043.m455(strDecode2);
                case 1747686:
                    try {
                        C0001.m212(new Class[]{C0012.class});
                        if ((C0053.f92 | C0001.f1 | (-1005)) >= 0) {
                            C0043.m456();
                            strDecode3 = NPStringFog.decode("B5EFB6C3B5C5");
                            iM503 = C0052.m503(strDecode3);
                        } else {
                            i = C0002.f2 - C0053.f92;
                            i2 = 1749057;
                            iM503 = i + i2;
                        }
                    } catch (HookErrorException e) {
                        throw new RuntimeException(e);
                    }
                case 1747835:
                    i = C0052.f91 + C0053.f92;
                    i2 = 1748662;
                    iM503 = i + i2;
                case 1749795:
                    return;
                case 1755590:
                    if (C0053.f92 * (C0052.f91 % (-9083)) <= 0) {
                        C0052.m520();
                        strDecode3 = NPStringFog.decode("B5D7B6C6");
                        iM503 = C0052.m503(strDecode3);
                    } else {
                        iM503 = (C0052.f91 + C0001.f1) ^ 1749986;
                    }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* renamed from: ۟۠۠ۤ, reason: not valid java name and contains not printable characters */
    public static a m358() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D8B6C1B5C4");
        int iM562 = C0053.m562(strDecode3);
        a aVar = null;
        while (true) {
            a aVar2 = aVar;
            while (true) {
                switch (iM562) {
                    case 1746691:
                        if (C0052.m520() <= 0) {
                            C0043.m456();
                            strDecode = NPStringFog.decode("B5D3B6C0B5C9");
                            iM562 = C0043.m455(strDecode);
                        } else {
                            i = C0053.f92 ^ C0053.f92;
                            i2 = 1750568;
                            iM562 = i + i2;
                        }
                    case 1746786:
                        aVar = f28;
                        if ((C0043.f73 ^ (C0043.f73 / (-7193))) >= 0) {
                            C0002.f2 = 87;
                            iM562 = C0043.m455(strDecode3);
                        } else {
                            i = C0052.f91 | C0053.f92;
                            i2 = 1748772;
                            iM562 = i + i2;
                        }
                    case 1747648:
                        i = C0052.f91 - C0053.f92;
                        i2 = 1746039;
                        iM562 = i + i2;
                    case 1748649:
                        if (C0001.m192() >= 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D4B6C7B5FE");
                            iM562 = C0043.m455(strDecode);
                        } else {
                            iM562 = C0053.m562(strDecode3);
                        }
                    case 1748738:
                        iM562 = (C0053.f92 | (C0001.f1 | (-2640))) >= 0 ? C0052.m503(NPStringFog.decode("B5EFB6FEB5C2")) : (C0052.f91 ^ C0002.f2) ^ 1749666;
                    case 1749576:
                        break;
                    case 1750568:
                        iM562 = (C0002.f2 / C0002.f2) + 1754381;
                        aVar2 = null;
                    case 1750602:
                    case 1752672:
                        if (C0002.m259() >= 0) {
                            iM562 = C0052.m503(NPStringFog.decode("B5D2B6FEB5C7"));
                        } else {
                            strDecode2 = NPStringFog.decode("B5D2B6FEB5C4");
                            iM562 = C0053.m562(strDecode2);
                        }
                    case 1754382:
                        i = C0002.f2 + C0053.f92;
                        i2 = 1753562;
                        iM562 = i + i2;
                    case 1755373:
                        if (C0052.m520() <= 0) {
                            i = C0052.f91 - C0053.f92;
                            i2 = 1746039;
                            iM562 = i + i2;
                        } else if (C0052.m520() <= 0) {
                            strDecode2 = NPStringFog.decode("B5D5B6C7B5C0");
                            iM562 = C0053.m562(strDecode2);
                        } else {
                            i = C0043.f73 * C0053.f92;
                            i2 = 1610848;
                            iM562 = i + i2;
                        }
                }
                return aVar2;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0076 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0084 A[SYNTHETIC] */
    /* renamed from: ۟۠ۦۨ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Set m359() {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D2B6C3B5C1");
        int iM562 = C0053.m562(strDecode2);
        Set<String> set = null;
        while (true) {
            Set<String> set2 = set;
            while (true) {
                String strDecode3 = NPStringFog.decode("B5D2B6C1B5FE");
                switch (iM562) {
                    case 1746848:
                        if ((C0002.f2 | (C0002.f2 % 1716)) >= 0) {
                            C0043.f73 = 66;
                            iM562 = C0052.m503(NPStringFog.decode("B5D3B6C3B5C1"));
                        } else {
                            iM562 = (C0053.f92 + C0001.f1) ^ (-1747608);
                        }
                    case 1746937:
                    case 1746969:
                        i = C0053.f92 | C0052.f91;
                        i2 = 1747839;
                        iM562 = i + i2;
                    case 1747805:
                        break;
                    case 1748896:
                        iM562 = C0052.f91 % (C0002.f2 + (-2574)) >= 0 ? C0052.m503(strDecode3) : C0001.m190(NPStringFog.decode("B5EFB6C6B5C0"));
                    case 1749601:
                        i = C0002.f2 / C0053.f92;
                        i2 = 1750625;
                        iM562 = i + i2;
                    case 1749664:
                        if (C0052.m520() > 0) {
                            if (C0053.m574() >= 0) {
                                C0043.m456();
                                iM562 = C0053.m562(NPStringFog.decode("B5D1B6C9B5C6"));
                            } else {
                                strDecode = NPStringFog.decode("B5D7B6C4B5C1");
                                iM562 = C0043.m455(strDecode);
                            }
                        } else if (C0002.f2 % (C0053.f92 | 4428) < 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D5B6C4B5C4");
                            iM562 = C0043.m455(strDecode);
                        } else {
                            iM562 = C0043.m455(strDecode3);
                        }
                    case 1749790:
                        if (C0053.m574() >= 0) {
                            C0043.f73 = 24;
                            strDecode3 = NPStringFog.decode("B5D3B6C5B5C1");
                        } else {
                            strDecode3 = strDecode2;
                        }
                        iM562 = C0043.m455(strDecode3);
                    case 1750625:
                        if ((C0001.f1 | C0052.f91 | (-5555)) >= 0) {
                            C0001.f1 = 54;
                            iM562 = C0053.m562(strDecode2);
                            set2 = null;
                        } else {
                            set2 = null;
                            iM562 = C0053.m562(NPStringFog.decode("B5D1B6C9B5C6"));
                        }
                    case 1752550:
                        if (C0002.f2 % (C0053.f92 | 4428) < 0) {
                        }
                        break;
                    case 1754562:
                        set = f31;
                        iM562 = C0052.m503(NPStringFog.decode("B5EFB6C5B5C4"));
                }
                return set2;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x003b A[SYNTHETIC] */
    /* renamed from: ۣ۟ۡۡۥ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m360() {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D3B6C4B5C0");
        int iM190 = C0001.m190(strDecode2);
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D5B6C6B5C9");
            switch (iM190) {
                case 1746757:
                    if (C0053.f92 >= 0) {
                        strDecode3 = NPStringFog.decode("B5D5B6C7B5C1");
                        iM190 = C0002.m230(strDecode3);
                    } else {
                        i = C0002.f2 * C0002.f2;
                        i2 = 1744943;
                        iM190 = i + i2;
                    }
                case 1748803:
                    m356();
                    if (C0052.f91 * (C0053.f92 / 9306) != 0) {
                        iM190 = C0002.m230(strDecode3);
                    } else {
                        i3 = C0052.f91 / C0001.f1;
                        i4 = 1748892;
                        iM190 = i3 ^ i4;
                    }
                case 1748835:
                case 1753669:
                    if (C0001.f1 <= 0) {
                        C0052.f91 = 34;
                        strDecode = NPStringFog.decode("B5D6B6C6B5FE");
                        iM190 = C0043.m455(strDecode);
                    } else {
                        i3 = C0002.f2 ^ C0053.f92;
                        i4 = 1748218;
                        iM190 = i3 ^ i4;
                    }
                case 1748892:
                    break;
                case 1750719:
                    if (C0002.m259() <= 0) {
                        i = C0043.f73 - C0052.f91;
                        i2 = 1748808;
                        iM190 = i + i2;
                    } else if (C0053.m574() < 0) {
                        C0001.f1 = 71;
                        strDecode = NPStringFog.decode("B5D4B6C4B5C3");
                        iM190 = C0043.m455(strDecode);
                    } else {
                        iM190 = C0002.m230(strDecode3);
                    }
                case 1752710:
                    if (C0052.f91 >= 0) {
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        i3 = C0002.f2 % C0002.f2;
                        i4 = 1748835;
                        iM190 = i3 ^ i4;
                    }
                case 1754627:
                    if (C0053.m574() < 0) {
                    }
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* renamed from: ۟ۢۦۡۦ, reason: not valid java name and contains not printable characters */
    public static ParcelFileDescriptor m361(Object obj, Object obj2) throws Throwable {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5EFB6C7B5C5");
        int iM455 = C0043.m455(strDecode3);
        ParcelFileDescriptor parcelFileDescriptorM352 = null;
        while (true) {
            ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorM352;
            while (true) {
                String strDecode4 = NPStringFog.decode("B5D0B6FEB5C3");
                switch (iM455) {
                    case 56291:
                    case 1747901:
                        if ((C0043.f73 ^ (C0053.f92 - 3581)) <= 0) {
                            C0043.m456();
                            iM455 = C0053.m562(NPStringFog.decode("B5D3B6C6B5C7"));
                        } else {
                            i = C0002.f2 - C0053.f92;
                            i2 = 1750467;
                            iM455 = i ^ i2;
                        }
                    case 1746909:
                        if (C0002.m259() <= 0) {
                            strDecode = NPStringFog.decode("B5D5B6C0B5C3");
                            iM455 = C0052.m503(strDecode);
                        } else {
                            i = C0052.f91 - C0001.f1;
                            i2 = -1749063;
                            iM455 = i ^ i2;
                        }
                    case 1747651:
                        iM455 = C0043.f73 >= 0 ? C0043.m455(strDecode4) : (C0053.f92 | C0002.f2) ^ (-1750825);
                    case 1747741:
                        if (C0001.m192() >= 0) {
                            iM455 = C0053.m562(NPStringFog.decode("B5D3B6C9B5C7"));
                            parcelFileDescriptor = null;
                        } else {
                            strDecode2 = NPStringFog.decode("B5D4B6C4B5C7");
                            parcelFileDescriptor = null;
                            iM455 = C0002.m230(strDecode2);
                        }
                    case 1749570:
                        if (C0043.f73 * (C0001.f1 | 3614) >= 0) {
                            strDecode2 = NPStringFog.decode("B5EFB6C3");
                            iM455 = C0002.m230(strDecode2);
                        } else {
                            strDecode4 = NPStringFog.decode("B5D0B6C3B5FE");
                            iM455 = C0052.m503(strDecode4);
                        }
                    case 1750817:
                        break;
                    case 1751685:
                        if (C0053.f92 % (C0053.f92 - 999) >= 0) {
                            C0053.m574();
                            strDecode = NPStringFog.decode("B5D5B6C7B5C4");
                            iM455 = C0052.m503(strDecode);
                        } else {
                            iM455 = (C0043.f73 ^ C0002.f2) + 1747664;
                        }
                    case 1752518:
                        parcelFileDescriptorM352 = m352((InputStream) obj, (String) obj2);
                        if (C0002.m259() >= 0) {
                            strDecode = NPStringFog.decode("B5D7B6FEB5C3");
                            iM455 = C0052.m503(strDecode);
                        } else {
                            iM455 = C0052.m503(strDecode4);
                        }
                    case 1752676:
                        if (C0002.f2 >= 0) {
                            C0002.m259();
                            strDecode4 = NPStringFog.decode("B5D0B6C6B5C4");
                        } else {
                            strDecode4 = strDecode3;
                        }
                        iM455 = C0052.m503(strDecode4);
                    case 1754378:
                        i = C0052.f91 - C0001.f1;
                        i2 = -1749063;
                        iM455 = i ^ i2;
                }
                return parcelFileDescriptor;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x005a A[SYNTHETIC] */
    /* renamed from: ۟ۤ۟ۤ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m362() {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D7B6C5B5C9");
        int iM455 = C0043.m455(strDecode2);
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D7B6C7");
            switch (iM455) {
                case 56543:
                    m355();
                    int i3 = C0052.f91 ^ (C0053.f92 - 7338);
                    strDecode3 = NPStringFog.decode("B5D7B6C3B5C6");
                    if (i3 <= 0) {
                        C0053.m574();
                        iM455 = C0052.m503(strDecode3);
                    } else {
                        iM455 = C0053.m562(strDecode3);
                    }
                case 1747775:
                    if (C0001.m192() >= 0) {
                        C0002.m259();
                        iM455 = C0053.m562(strDecode3);
                    } else {
                        i = C0052.f91 % C0043.f73;
                        i2 = 1755622;
                        iM455 = i + i2;
                    }
                case 1748680:
                    if (C0052.f91 >= 0) {
                        C0053.f92 = 11;
                        strDecode3 = NPStringFog.decode("B5D6B6C4B5C2");
                    } else {
                        strDecode3 = strDecode2;
                    }
                case 1750594:
                    if (C0002.m259() < 0) {
                        iM455 = C0002.m230(NPStringFog.decode("B5D6B6C4B5C4"));
                    } else {
                        strDecode = NPStringFog.decode("B5D0B6C2B5C3");
                        iM455 = C0001.m190(strDecode);
                    }
                case 1754476:
                    break;
                case 1754539:
                    if (C0052.m520() >= 0) {
                        iM455 = C0001.f1 - (C0043.f73 + (-7609)) <= 0 ? C0052.m503(NPStringFog.decode("B5D8B6C2B5FE")) : C0002.m230(strDecode3);
                    } else if (C0002.m259() < 0) {
                    }
                    break;
                case 1754657:
                case 1755460:
                    if (C0001.m192() >= 0) {
                        C0053.f92 = 27;
                        strDecode = NPStringFog.decode("B5D4B6C9B5C1");
                        iM455 = C0001.m190(strDecode);
                    } else {
                        i = C0002.f2 % C0052.f91;
                        i2 = 1754552;
                        iM455 = i + i2;
                    }
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0079  */
    /* renamed from: ۟ۤۤ۠, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m363() {
        int i;
        int i2;
        int iM190 = C0001.m190(NPStringFog.decode("B5D6B6C7B5C3"));
        String str = null;
        String str2 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D6B6C2B5FE");
            String strDecode2 = "ۣ۠";
            switch (iM190) {
                case 56413:
                case 1751562:
                    i = C0043.f73 - C0002.f2;
                    i2 = 1747869;
                    iM190 = i + i2;
                case 1747778:
                    break;
                case 1750724:
                    i = C0053.f92 ^ C0043.f73;
                    i2 = 1752727;
                    iM190 = i + i2;
                case 1753538:
                    str2 = f26;
                    if (C0052.m520() <= 0) {
                        C0052.f91 = 10;
                    } else {
                        i = C0043.f73 | C0052.f91;
                        i2 = 1755780;
                        iM190 = i + i2;
                    }
                case 1753634:
                    if (C0002.m259() < 0) {
                        iM190 = C0053.m562(strDecode);
                    } else {
                        strDecode2 = (C0001.f1 | (C0053.f92 | 1116)) < 0 ? NPStringFog.decode("B5D2B6C6B5C4") : NPStringFog.decode("B5D8B6C2B5FE");
                        iM190 = C0053.m562(strDecode2);
                    }
                case 1754473:
                    if ((C0001.f1 | (C0053.f92 | 1116)) < 0) {
                    }
                    iM190 = C0053.m562(strDecode2);
                    break;
                case 1754597:
                    iM190 = C0053.m562(strDecode2);
                case 1755460:
                    iM190 = (C0001.f1 | (C0001.f1 % 2755)) <= 0 ? C0001.m190("ۣ۠") : C0002.m230(NPStringFog.decode("B5D8B6C5B5C1"));
                case 1755492:
                    iM190 = C0043.f73 * (C0002.f2 | 4337) <= 0 ? C0052.m503(strDecode) : (C0001.f1 % C0001.f1) ^ 1754597;
                    str = null;
                case 1755619:
                    if (C0002.f2 >= 0) {
                        C0001.f1 = 7;
                        iM190 = C0002.m230(NPStringFog.decode("B5D8B6C9B5C2"));
                    } else {
                        iM190 = (C0053.f92 + C0052.f91) ^ (-1747086);
                    }
                    str = str2;
            }
            return str;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /* renamed from: ۣ۟ۤۨۡ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] m364() {
        int i;
        int i2;
        int iM455 = C0043.m455(NPStringFog.decode("B5D0B6C9B5C2"));
        byte[] bArr = null;
        while (true) {
            byte[] bArr2 = bArr;
            while (true) {
                String strDecode = NPStringFog.decode("B5D8B6C3B5C3");
                String strDecode2 = "ۤۨۢ";
                switch (iM455) {
                    case 56324:
                        int i3 = C0002.f2;
                        iM455 = C0001.m190(NPStringFog.decode("B5D1B6C1B5C9"));
                        bArr2 = null;
                    case 1746849:
                        i = C0052.f91 | C0053.f92;
                        i2 = 1747965;
                        iM455 = i + i2;
                    case 1747931:
                        if (C0001.m192() <= 0) {
                            if ((C0002.f2 | (C0052.f91 ^ 9032)) >= 0) {
                                C0001.m192();
                            } else {
                                strDecode = NPStringFog.decode("B5D8B6C9B5C1");
                            }
                        } else if (C0053.m574() >= 0) {
                            strDecode = NPStringFog.decode("B5D7B6FEB5C2");
                        }
                        iM455 = C0002.m230(strDecode);
                    case 1748649:
                        if ((C0043.f73 ^ (C0052.f91 + 7788)) >= 0) {
                            C0002.f2 = 35;
                        } else {
                            strDecode2 = NPStringFog.decode("B5D7B6C9B5C6");
                        }
                        iM455 = C0052.m503(strDecode2);
                    case 1748706:
                        if (C0053.m574() >= 0) {
                        }
                        iM455 = C0002.m230(strDecode);
                        break;
                    case 1749795:
                    case 1754662:
                        iM455 = C0053.m562(NPStringFog.decode("B5D5B6C1B5C9"));
                    case 1751774:
                        break;
                    case 1752493:
                        break;
                    case 1755432:
                        i = C0053.f92 % C0043.f73;
                        i2 = 56470;
                        iM455 = i + i2;
                    case 1755616:
                        bArr = f30;
                        strDecode = "ۤۨۢ";
                        iM455 = C0002.m230(strDecode);
                }
                return bArr2;
            }
            iM455 = (C0001.f1 | C0053.f92) + 1753018;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* renamed from: ۟ۥۨۨۥ, reason: not valid java name and contains not printable characters */
    public static a m365() {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        int iM190 = C0001.m190(NPStringFog.decode("B5D3B6C4B5C2"));
        a aVar = null;
        a aVar2 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D6B6C3B5FE");
            switch (iM190) {
                case 1748796:
                    if (C0053.m574() >= 0) {
                        C0052.f91 = 36;
                        strDecode2 = NPStringFog.decode("B5D0B6C7B5C7");
                        iM190 = C0043.m455(strDecode2);
                    } else {
                        i = C0002.f2 ^ C0052.f91;
                        i2 = 1750571;
                        iM190 = i ^ i2;
                    }
                case 1748828:
                    i3 = C0002.f2 * C0053.f92;
                    i4 = 1688825;
                    iM190 = i3 + i4;
                case 1749759:
                case 1751563:
                    if (C0002.m259() >= 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D2B6C9B5C5");
                        iM190 = C0043.m455(strDecode);
                    } else {
                        i3 = C0043.f73 % C0002.f2;
                        i4 = 1751733;
                        iM190 = i3 + i4;
                    }
                case 1750689:
                    iM190 = C0043.m455(strDecode2);
                case 1750721:
                    if (C0053.m574() >= 0) {
                        i3 = C0002.f2 * C0053.f92;
                        i4 = 1688825;
                        iM190 = i3 + i4;
                    } else if ((C0052.f91 | (C0043.f73 % 8590)) >= 0) {
                        C0002.f2 = 13;
                        strDecode = NPStringFog.decode("B5D4B6C0B5C9");
                        iM190 = C0043.m455(strDecode);
                    } else {
                        i = C0001.f1 | C0052.f91;
                        i2 = -1753449;
                        iM190 = i ^ i2;
                    }
                case 1751621:
                    if (C0053.f92 >= 0) {
                        C0001.m192();
                        iM190 = C0052.m503(NPStringFog.decode("B5D4B6C7B5C9"));
                    } else {
                        strDecode = NPStringFog.decode("B5D2B6C4B5C3");
                        iM190 = C0043.m455(strDecode);
                    }
                case 1751718:
                    break;
                case 1753507:
                    if (C0052.f91 + (C0001.f1 | 6946) <= 0) {
                        C0053.m574();
                        iM190 = C0052.m503(NPStringFog.decode("B5D8B6C3B5C9"));
                    } else {
                        iM190 = (C0002.f2 | C0002.f2) ^ (-1751567);
                    }
                    aVar = null;
                case 1753576:
                    aVar2 = f29;
                    if (C0043.f73 % (C0002.f2 | (-8244)) >= 0) {
                        C0001.m192();
                        iM190 = C0053.m562(strDecode2);
                    } else {
                        i3 = C0002.f2 * C0002.f2;
                        i4 = 1749662;
                        iM190 = i3 + i4;
                    }
                case 1755438:
                    iM190 = (C0002.f2 * C0053.f92) + 1689854;
                    aVar = aVar2;
            }
            return aVar;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1 A[SYNTHETIC] */
    /* renamed from: ۟ۦۣۧ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static InputStream m366(Object obj) {
        String strDecode;
        int i;
        int i2;
        int iM230 = C0002.m230(NPStringFog.decode("B5D2B6FEB5C2"));
        InputStream inputStreamM349 = null;
        while (true) {
            InputStream inputStream = inputStreamM349;
            while (true) {
                String strDecode2 = NPStringFog.decode("B5D8B6C0B5C9");
                switch (iM230) {
                    case 1747679:
                    case 1754569:
                        if (C0001.f1 % (C0053.f92 + 7540) <= 0) {
                            C0043.m456();
                            strDecode = NPStringFog.decode("B5D6B6C3B5C6");
                            iM230 = C0043.m455(strDecode);
                        } else {
                            iM230 = C0053.m562(strDecode2);
                        }
                    case 1748858:
                        if (C0052.f91 >= 0) {
                            break;
                        }
                        inputStream = inputStreamM349;
                        iM230 = C0043.m455(strDecode2);
                        break;
                    case 1749574:
                        if (C0001.m192() < 0) {
                            if (C0001.f1 + (C0053.f92 - 864) >= 0) {
                                C0001.f1 = 29;
                                strDecode2 = NPStringFog.decode("B5D3B6C4B5C9");
                            } else {
                                strDecode2 = NPStringFog.decode("B5D6B6C9B5C4");
                            }
                            iM230 = C0043.m455(strDecode2);
                        } else if (C0052.f91 < 0) {
                            C0002.m259();
                            iM230 = C0002.m230(NPStringFog.decode("B5D6B6C9B5C7"));
                        } else {
                            i = C0053.f92 + C0002.f2;
                            i2 = 1756233;
                            iM230 = i + i2;
                        }
                    case 1750726:
                        i = C0043.f73 % C0001.f1;
                        i2 = 1749741;
                        iM230 = i + i2;
                    case 1752732:
                        if (C0052.f91 >= 0) {
                            C0052.m520();
                            iM230 = C0001.m190(NPStringFog.decode("B5D8B6FEB5C7"));
                        } else {
                            i = C0052.f91 / C0052.f91;
                            i2 = 1754568;
                            iM230 = i + i2;
                        }
                    case 1753422:
                        iM230 = (C0052.f91 % C0052.f91) + 1752732;
                        inputStream = null;
                    case 1753514:
                        if (C0052.f91 < 0) {
                        }
                        break;
                    case 1753699:
                        inputStreamM349 = m349((InputStream) obj);
                        strDecode = NPStringFog.decode("B5D1B6C6B5C1");
                        iM230 = C0043.m455(strDecode);
                    case 1755343:
                        if (C0001.m192() >= 0) {
                            C0043.m456();
                            iM230 = C0043.m455(strDecode2);
                        } else {
                            strDecode = NPStringFog.decode("B5D6B6FEB5C6");
                            iM230 = C0043.m455(strDecode);
                        }
                    case 1755407:
                        break;
                }
                return inputStream;
            }
            C0002.f2 = 50;
            iM230 = C0053.m562(NPStringFog.decode("B5D5B6C9B5FE"));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x005c A[SYNTHETIC] */
    /* renamed from: ۣ۠۠ۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m367(Object obj) throws NumberFormatException {
        String strDecode;
        int i;
        int i2;
        int iM190 = C0001.m190(NPStringFog.decode("B5D6B6C6B5C0"));
        while (true) {
            switch (iM190) {
                case 56452:
                case 1755624:
                    if (C0043.m456() <= 0) {
                        C0001.f1 = 16;
                        iM190 = C0052.m503(NPStringFog.decode("B5D5B6C7B5C5"));
                    } else {
                        iM190 = (C0052.f91 % C0043.f73) ^ (-1755595);
                    }
                case 1746848:
                    m351((byte[]) obj);
                    strDecode = NPStringFog.decode("B5D8B6C5B5C6");
                    iM190 = C0002.m230(strDecode);
                case 1747868:
                    if (C0043.m456() > 0) {
                        strDecode = NPStringFog.decode("B5D0B6C1B5FE");
                        iM190 = C0002.m230(strDecode);
                    } else {
                        iM190 = C0053.m562(NPStringFog.decode("B5D8B6C6B5C9"));
                    }
                case 1749732:
                    if (C0053.f92 + (C0043.f73 * (-2751)) <= 0) {
                        C0052.f91 = 89;
                        strDecode = NPStringFog.decode("B5D1B6C1B5C3");
                        iM190 = C0002.m230(strDecode);
                    } else {
                        i = C0043.f73 * C0001.f1;
                        i2 = 1812949;
                        iM190 = i + i2;
                    }
                case 1753664:
                    if (C0052.m520() > 0) {
                        i = C0002.f2 % C0053.f92;
                        i2 = 1746924;
                        iM190 = i + i2;
                    } else if (C0043.m456() > 0) {
                    }
                    break;
                case 1755499:
                    break;
                case 1755593:
                    if (C0001.m192() >= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5EFB6C5B5C4");
                        iM190 = C0002.m230(strDecode);
                    } else {
                        i = C0053.f92 | C0002.f2;
                        i2 = 1755634;
                        iM190 = i + i2;
                    }
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    /* renamed from: ۣ۠ۤ۟, reason: not valid java name and contains not printable characters */
    public static InputStream m368(Object obj, Object obj2) throws Throwable {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D5B6C4B5C6");
        int iM230 = C0002.m230(strDecode3);
        InputStream inputStreamM345 = null;
        while (true) {
            InputStream inputStream = inputStreamM345;
            while (true) {
                switch (iM230) {
                    case 1747715:
                        if (C0002.f2 >= 0) {
                            C0043.m456();
                            iM230 = C0002.m230(NPStringFog.decode("B5D7B6C3B5FE"));
                        } else {
                            iM230 = (C0043.f73 * C0002.f2) + 1742801;
                        }
                    case 1747930:
                    case 1754468:
                        if (C0053.f92 % (C0053.f92 | 7383) >= 0) {
                            C0043.f73 = 78;
                            strDecode = NPStringFog.decode("B5D6B6C2B5C5");
                            iM230 = C0002.m230(strDecode);
                        } else {
                            i = C0001.f1 * C0053.f92;
                            i2 = -2007469;
                            iM230 = i ^ i2;
                        }
                    case 1749575:
                        if (C0052.m520() <= 0) {
                            iM230 = C0043.m455(NPStringFog.decode("B5D2B6FEB5C5"));
                        } else {
                            strDecode = NPStringFog.decode("B5D7B6C7B5C0");
                            iM230 = C0002.m230(strDecode);
                        }
                    case 1750661:
                        if (C0053.m574() >= 0) {
                            strDecode2 = NPStringFog.decode("B5EFB6C0B5C7");
                            iM230 = C0052.m503(strDecode2);
                        } else {
                            iM230 = C0043.m455(strDecode3);
                        }
                    case 1752647:
                        if (C0053.m574() < 0) {
                            iM230 = C0001.f1 + C0043.f73 + 1753419;
                        } else {
                            i = C0053.f92 ^ C0053.f92;
                            i2 = 1749575;
                            iM230 = i ^ i2;
                        }
                    case 1753607:
                        inputStreamM345 = m345((InputStream) obj, (byte[]) obj2);
                        if ((C0052.f91 | (C0053.f92 % 6724)) >= 0) {
                            C0001.m192();
                            strDecode2 = NPStringFog.decode("B5D8B6C3B5C9");
                            iM230 = C0052.m503(strDecode2);
                        } else {
                            i = C0043.f73 | C0053.f92;
                            i2 = -1747752;
                            iM230 = i ^ i2;
                        }
                    case 1754594:
                        if (C0001.f1 - (C0053.f92 / 8973) <= 0) {
                            C0002.f2 = 6;
                            iM230 = C0053.m562(NPStringFog.decode("B5D8B6C5B5C0"));
                        } else {
                            iM230 = (C0043.f73 | C0043.f73) + 1755534;
                        }
                        inputStream = null;
                    case 1755367:
                        strDecode = NPStringFog.decode("B5D0B6C9B5C3");
                        iM230 = C0002.m230(strDecode);
                    case 1755438:
                        i = C0053.f92 ^ C0053.f92;
                        i2 = 1749575;
                        iM230 = i ^ i2;
                    case 1755493:
                        break;
                }
                return inputStream;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:52:0x006e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x007d A[SYNTHETIC] */
    /* renamed from: ۣۤۡۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m369(Object obj, Object obj2) throws Throwable {
        String strDecode;
        String strDecode2;
        String strDecode3;
        int i;
        int i2;
        int iM503 = C0052.m503(NPStringFog.decode("B5D5B6C2B5C3"));
        String strM350 = null;
        while (true) {
            String str = strM350;
            while (true) {
                strDecode = NPStringFog.decode("B5D0B6C3B5C2");
                String strDecode4 = "ۢۤ۟";
                switch (iM503) {
                    case 1747745:
                        if (C0002.f2 >= 0) {
                            break;
                        }
                        str = strM350;
                        iM503 = C0053.m562(strDecode4);
                        break;
                    case 1749725:
                        break;
                    case 1749764:
                    case 1752610:
                        if (C0002.f2 >= 0) {
                            strDecode4 = NPStringFog.decode("B5D1B6C1B5C2");
                        }
                        iM503 = C0053.m562(strDecode4);
                    case 1749790:
                        int i3 = C0002.f2;
                        int i4 = C0002.f2;
                        iM503 = C0052.m503(NPStringFog.decode("B5D6B6C6B5C1"));
                        str = null;
                    case 1752547:
                        strM350 = m350((InputStream) obj, (byte[]) obj2);
                        if (C0052.f91 + (C0001.f1 / (-2488)) >= 0) {
                            C0052.m520();
                            strDecode2 = NPStringFog.decode("B5D2B6C7B5C3");
                            iM503 = C0053.m562(strDecode2);
                        } else {
                            iM503 = C0043.m455(strDecode);
                        }
                    case 1752580:
                        if (C0001.m192() <= 0) {
                            i = C0053.f92 / C0053.f92;
                            i2 = 1752546;
                            iM503 = i + i2;
                        } else if (C0002.f2 % (C0053.f92 - 7960) < 0) {
                            C0043.f73 = 58;
                            strDecode2 = NPStringFog.decode("B5D3B6FEB5C2");
                            iM503 = C0053.m562(strDecode2);
                        } else {
                            strDecode3 = NPStringFog.decode("B5D6B6FEB5C0");
                            iM503 = C0002.m230(strDecode3);
                        }
                    case 1753416:
                        if (C0043.f73 / (C0001.f1 % 8975) != 0) {
                            C0052.m520();
                            strDecode = NPStringFog.decode("B5D6B6C9B5C1");
                            iM503 = C0043.m455(strDecode);
                        } else {
                            i = C0052.f91 % C0001.f1;
                            i2 = 1749952;
                            iM503 = i + i2;
                        }
                    case 1753571:
                        if (C0002.f2 % (C0053.f92 - 7960) < 0) {
                        }
                        break;
                    case 1753663:
                        strDecode3 = NPStringFog.decode("B5D2B6C4B5C6");
                        iM503 = C0002.m230(strDecode3);
                    case 1753694:
                        if (C0001.f1 + (C0043.f73 * (-9413)) <= 0) {
                            strDecode3 = NPStringFog.decode("B5D4B6C2B5C3");
                            iM503 = C0002.m230(strDecode3);
                        } else {
                            i = C0002.f2 | C0002.f2;
                            i2 = 1752656;
                            iM503 = i + i2;
                        }
                }
                return str;
            }
            C0002.m259();
            iM503 = C0052.m503(strDecode);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000a. Please report as an issue. */
    /* renamed from: ۥۢۢۧ, reason: contains not printable characters */
    public static void m370() {
        int i;
        int i2;
        String strDecode;
        int iM562 = C0053.m562(NPStringFog.decode("B5D1B6FEB5C3"));
        while (true) {
            switch (iM562) {
                case 1746974:
                    if ((C0001.f1 ^ (C0052.f91 / 964)) <= 0) {
                        C0043.m456();
                        strDecode = NPStringFog.decode("B5D5B6C9B5C7");
                        iM562 = C0002.m230(strDecode);
                    } else {
                        i = C0052.f91 + C0053.f92;
                        i2 = 1749588;
                        iM562 = i + i2;
                    }
                case 1748612:
                    if (C0002.m259() < 0) {
                        i = C0002.f2 ^ C0001.f1;
                        i2 = 1752999;
                    } else {
                        i = C0053.f92 - C0052.f91;
                        i2 = 1749296;
                    }
                    iM562 = i + i2;
                case 1748644:
                    if (C0053.m574() >= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5EFB6C9B5C6");
                        iM562 = C0002.m230(strDecode);
                    } else {
                        i = C0052.f91 ^ C0052.f91;
                        i2 = 1755560;
                        iM562 = i + i2;
                    }
                case 1748673:
                case 1755560:
                    if (C0001.f1 <= 0) {
                        C0001.f1 = 88;
                        iM562 = C0043.m455(NPStringFog.decode("B5D8B6C1B5C5"));
                    } else {
                        i = C0053.f92 / C0053.f92;
                        i2 = 1749755;
                        iM562 = i + i2;
                    }
                case 1749756:
                    break;
                case 1752702:
                    m357();
                    iM562 = (C0043.f73 + C0043.f73) ^ (-1749938);
                case 1754569:
                    i = C0053.f92 - C0052.f91;
                    i2 = 1749296;
                    iM562 = i + i2;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /* renamed from: ۦۢۢۤ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m371() {
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D2B6C7B5C4");
        int iM503 = C0052.m503(strDecode2);
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D2B6C6B5C0");
            switch (iM503) {
                case 56322:
                    if ((C0043.f73 | (C0002.f2 - 6108)) >= 0) {
                        C0053.m574();
                        strDecode3 = NPStringFog.decode("B5D6B6C7B5C2");
                    } else {
                        strDecode3 = strDecode2;
                    }
                    iM503 = C0043.m455(strDecode3);
                case 56537:
                case 1749731:
                    if ((C0043.f73 ^ (C0001.f1 - 7171)) <= 0) {
                        strDecode = NPStringFog.decode("B5D5B6C4B5C9");
                        iM503 = C0043.m455(strDecode);
                    } else {
                        iM503 = C0043.m455(strDecode3);
                    }
                case 1747897:
                    iM503 = C0052.f91 >= 0 ? C0043.m455(NPStringFog.decode("B5D0B6C3")) : (C0052.f91 ^ C0001.f1) + 1753188;
                    sArr2 = null;
                case 1748615:
                    sArr2 = sArr;
                    iM503 = C0043.m455(strDecode3);
                case 1748644:
                    sArr = f25short;
                    if (C0052.m520() <= 0) {
                        C0002.f2 = 51;
                        iM503 = C0001.m190(NPStringFog.decode("B5D7B6C1"));
                    } else {
                        iM503 = (C0043.f73 - C0001.f1) + 1749137;
                    }
                case 1748895:
                    strDecode = C0043.f73 + (C0052.f91 | (-8152)) >= 0 ? NPStringFog.decode("B5D4B6C9B5C3") : NPStringFog.decode("B5D0B6C6B5C1");
                    iM503 = C0043.m455(strDecode);
                case 1749793:
                    if (C0053.m574() > 0) {
                        strDecode3 = (C0052.f91 | (C0053.f92 * (-243))) < 0 ? NPStringFog.decode("B5D1B6C1B5C0") : NPStringFog.decode("B5D1B6C9B5C7");
                        iM503 = C0043.m455(strDecode3);
                    } else if ((C0052.f91 ^ (C0052.f91 / 1824)) >= 0) {
                        C0001.f1 = 81;
                        iM503 = C0001.m190(strDecode2);
                    } else {
                        strDecode3 = NPStringFog.decode("B5D1B6C1B5C2");
                        iM503 = C0043.m455(strDecode3);
                    }
                case 1749820:
                    break;
                case 1751774:
                    if ((C0052.f91 | (C0053.f92 * (-243))) < 0) {
                    }
                    iM503 = C0043.m455(strDecode3);
                    break;
                case 1752737:
                    iM503 = C0043.f73 + (C0001.f1 * 6705) <= 0 ? C0053.m562(strDecode3) : (C0001.f1 - C0002.f2) ^ 1749836;
            }
            return sArr2;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0010. Please report as an issue. */
    /* renamed from: ۦۤ۟ۧ, reason: contains not printable characters */
    public static void m372(Object obj) throws NumberFormatException {
        String strDecode = NPStringFog.decode("B5D1B6C0");
        int iM562 = C0053.m562(strDecode);
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D7B6C4B5FE");
            switch (iM562) {
                case 56352:
                    if (C0052.m520() <= 0) {
                        strDecode2 = NPStringFog.decode("B5D0B6C4B5C4");
                        iM562 = C0053.m562(strDecode2);
                    } else if (C0002.f2 / (C0002.f2 % (-3075)) <= 0) {
                        C0002.m259();
                        iM562 = C0052.m503(strDecode2);
                    } else {
                        iM562 = (C0052.f91 * C0043.f73) ^ 1759111;
                    }
                case 56450:
                case 1747745:
                    if (C0002.m259() >= 0) {
                        C0052.f91 = 62;
                        iM562 = C0052.m503(NPStringFog.decode("B5D4B6C3B5C5"));
                    } else {
                        iM562 = C0053.m562(strDecode2);
                    }
                case 1747840:
                    if (C0053.f92 * (C0001.f1 / (-1389)) != 0) {
                        C0052.f91 = 63;
                        iM562 = C0001.m190(NPStringFog.decode("B5D4B6C7"));
                    } else {
                        iM562 = (C0052.f91 * C0052.f91) + 1721501;
                    }
                case 1752617:
                    m353((byte[]) obj);
                    if (C0002.f2 >= 0) {
                        C0001.f1 = 75;
                        iM562 = C0043.m455(NPStringFog.decode("B5D5B6C5B5C9"));
                    } else {
                        iM562 = C0052.m503(strDecode2);
                    }
                case 1752705:
                    strDecode2 = C0053.m574() >= 0 ? NPStringFog.decode("B5D6B6C6B5FE") : strDecode;
                    iM562 = C0053.m562(strDecode2);
                case 1753574:
                    strDecode2 = NPStringFog.decode("B5D0B6C4B5C4");
                    iM562 = C0053.m562(strDecode2);
                case 1754561:
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0047 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0056 A[SYNTHETIC] */
    /* renamed from: ۧ۠ۦۤ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AssetFileDescriptor m373(Object obj, Object obj2) throws Throwable {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D3B6C7B5C6");
        int iM562 = C0053.m562(strDecode2);
        AssetFileDescriptor assetFileDescriptor = null;
        AssetFileDescriptor assetFileDescriptorM348 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5EFB6C7B5C4");
            switch (iM562) {
                case 56322:
                case 1754595:
                    if (C0001.f1 <= 0) {
                        strDecode = NPStringFog.decode("B5D6B6C2B5C0");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        i = C0001.f1 / C0052.f91;
                        i2 = 1746912;
                        iM562 = i + i2;
                    }
                case 56476:
                    iM562 = C0002.m230(strDecode2);
                case 1746910:
                    break;
                case 1747772:
                    assetFileDescriptorM348 = m348((InputStream) obj, (String) obj2);
                    if (C0053.m574() >= 0) {
                        C0053.f92 = 33;
                        strDecode = NPStringFog.decode("B5D5B6C0");
                    } else {
                        strDecode = NPStringFog.decode("B5D1B6C4B5C2");
                    }
                    iM562 = C0052.m503(strDecode);
                case 1747865:
                    if ((C0052.f91 | (C0052.f91 ^ 234)) >= 0) {
                        C0043.f73 = 9;
                    }
                    strDecode3 = NPStringFog.decode("B5D1B6C7B5C2");
                    assetFileDescriptor = null;
                    iM562 = C0053.m562(strDecode3);
                case 1748799:
                    if ((C0001.f1 | (C0052.f91 - 5284)) >= 0) {
                        iM562 = C0052.m503(NPStringFog.decode("B5D2B6C9B5C3"));
                        assetFileDescriptor = assetFileDescriptorM348;
                    } else {
                        assetFileDescriptor = assetFileDescriptorM348;
                        iM562 = C0053.m562(strDecode3);
                    }
                case 1748830:
                    iM562 = (C0001.f1 | C0052.f91) ^ (-56451);
                case 1749852:
                    if (C0052.m520() > 0) {
                        C0053.f92 = 53;
                        iM562 = C0002.m230(NPStringFog.decode("B5D1B6C6B5C7"));
                    } else {
                        i = C0002.f2 - C0001.f1;
                        i2 = 1755923;
                        iM562 = i + i2;
                    }
                case 1750756:
                    if (C0052.m520() > 0) {
                        if (C0001.m192() >= 0) {
                            C0043.f73 = 40;
                            iM562 = C0002.m230(strDecode3);
                        } else {
                            iM562 = C0043.m455(NPStringFog.decode("B5D0B6C2B5FE"));
                        }
                    } else if (C0052.m520() > 0) {
                    }
                    break;
                case 1755492:
                    i = C0002.f2 | C0052.f91;
                    i2 = 1747867;
                    iM562 = i + i2;
            }
            return assetFileDescriptor;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x006c A[SYNTHETIC] */
    /* renamed from: ۣۨۢ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] m374(Object obj, Object obj2) {
        String strDecode = NPStringFog.decode("B5D3B6C9B5C0");
        int iM190 = C0001.m190(strDecode);
        byte[] bArr = null;
        byte[] bArrM347 = null;
        while (true) {
            String strDecode2 = NPStringFog.decode("B5D0B6C1B5C2");
            String strDecode3 = "ۧۥۡ";
            String strDecode4 = NPStringFog.decode("B5EFB6C9B5C0");
            switch (iM190) {
                case Utf8.LOG_SURROGATE_HEADER /* 56320 */:
                    iM190 = C0001.m190(strDecode);
                case 1746968:
                    break;
                case 1747683:
                    bArrM347 = m347((byte[]) obj, (byte[]) obj2);
                    if (C0043.f73 - (C0043.f73 ^ 2575) <= 0) {
                        C0053.f92 = 61;
                        iM190 = C0002.m230(strDecode4);
                    } else {
                        iM190 = (C0001.f1 ^ C0052.f91) ^ (-1749729);
                    }
                case 1747930:
                case 1755374:
                    strDecode3 = C0001.f1 <= 0 ? NPStringFog.decode("B5D8B6C7B5FE") : strDecode4;
                    iM190 = C0043.m455(strDecode3);
                case 1749794:
                    if (C0002.m259() >= 0) {
                        iM190 = C0053.m562(strDecode);
                        bArr = bArrM347;
                    } else {
                        bArr = bArrM347;
                        strDecode2 = strDecode4;
                        iM190 = C0053.m562(strDecode2);
                    }
                case 1750812:
                    if (C0053.m574() > 0) {
                        if (C0001.m192() < 0) {
                            C0001.f1 = 5;
                            strDecode2 = NPStringFog.decode("B5D6B6C5B5C6");
                        } else {
                            iM190 = C0043.m455(strDecode3);
                        }
                    }
                    iM190 = C0053.m562(strDecode2);
                case 1751683:
                    if (C0052.f91 + (C0002.f2 | (-6082)) >= 0) {
                        C0043.f73 = 47;
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        iM190 = (C0001.f1 - C0043.f73) + 1753111;
                    }
                    bArr = null;
                case 1753633:
                    if (C0052.f91 - (C0002.f2 ^ 6872) <= 0) {
                        C0052.f91 = 20;
                        iM190 = C0002.m230("ۧۥۡ");
                    } else {
                        iM190 = (C0002.f2 - C0053.f92) + 1754636;
                    }
                case 1753635:
                    if (C0001.m192() < 0) {
                    }
                    break;
                case 1754563:
                    strDecode2 = NPStringFog.decode("B5D4B6C4B5C5");
                    iM190 = C0053.m562(strDecode2);
            }
            return bArr;
        }
    }

    public AssetFileDescriptor f(C0015 c0015) {
        int i;
        int i2;
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D3B6C6B5C7");
        int iM230 = C0002.m230(strDecode2);
        InputStream inputStreamM559 = null;
        InputStream inputStream = null;
        String str = null;
        Object[] objArr = null;
        Method methodM219 = null;
        AssetManager assetManagerM234 = null;
        AssetManager.AssetInputStream assetInputStream = null;
        int i3 = 0;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D0B6C4B5FE");
            String strDecode4 = NPStringFog.decode("B5D7B6FEB5C6");
            String strDecode5 = "ۣۤۥ";
            String strDecode6 = NPStringFog.decode("B5D5B6C1B5C7");
            switch (iM230) {
                case 56508:
                    if (C0053.f92 >= 0) {
                        C0001.m192();
                        iM230 = C0053.m562("ۣۤۥ");
                    } else {
                        iM230 = C0001.f1 + C0002.f2 + 1746602;
                    }
                    inputStream = inputStreamM559;
                case 1746881:
                    return m373(inputStream, str);
                case 1747773:
                    objArr[1] = C0052.m510(i3);
                    if (C0001.f1 <= 0) {
                        C0053.f92 = 33;
                        iM230 = C0052.m503(strDecode4);
                    } else {
                        iM230 = (C0052.f91 | C0043.f73) ^ (-1750758);
                    }
                case 1747834:
                    methodM219 = C0002.m219(m358());
                    if (C0052.f91 >= 0) {
                        C0043.m456();
                    }
                    iM230 = C0053.m562(NPStringFog.decode("B5D0B6C7B5C6"));
                case 1747873:
                    assetManagerM234 = C0002.m234();
                    i = C0002.f2 % C0053.f92;
                    i2 = 1751788;
                    iM230 = i + i2;
                case 1748827:
                    return (AssetFileDescriptor) C0001.m210(C0002.m219(m365()), C0002.m234(), new Object[]{str});
                case 1748891:
                    if (C0002.f2 * (C0001.f1 - 1018) <= 0) {
                        C0052.m520();
                        iM230 = C0002.m230("ۡۦ۠");
                    } else {
                        i = C0052.f91 * C0001.f1;
                        i2 = 1809968;
                        iM230 = i + i2;
                    }
                case 1750597:
                    assetInputStream = (AssetManager.AssetInputStream) C0001.m210(methodM219, assetManagerM234, objArr);
                    if (C0002.f2 * (C0002.f2 + 1033) >= 0) {
                        C0002.m259();
                    }
                    iM230 = C0043.m455(NPStringFog.decode("B5D8B6C4B5C2"));
                case 1750692:
                    inputStreamM559 = C0053.m559(C0002.m234(), str);
                    i = C0001.f1 * C0053.f92;
                    i2 = 345478;
                    iM230 = i + i2;
                case 1750755:
                    iM230 = C0043.m455(strDecode6);
                case 1750786:
                    str = (String) C0002.m244(c0015)[1];
                    if (C0001.m192() >= 0) {
                        C0002.m259();
                        iM230 = C0053.m562(strDecode3);
                    } else {
                        i = C0052.f91 / C0053.f92;
                        i2 = 1751678;
                        iM230 = i + i2;
                    }
                case 1751678:
                    if (C0053.m550()) {
                        if ((C0053.f92 ^ (C0043.f73 ^ (-5313))) >= 0) {
                            C0043.f73 = 87;
                            strDecode3 = NPStringFog.decode("B5D3B6C7B5C7");
                        }
                        iM230 = C0001.m190(strDecode3);
                    } else {
                        iM230 = C0043.m455(strDecode6);
                    }
                case 1751712:
                    i3 = C0043.f73 ^ (-165);
                    if (C0001.f1 - (C0043.f73 - 1725) <= 0) {
                        C0001.f1 = 48;
                        iM230 = C0001.m190(NPStringFog.decode("B5D4B6C4B5FE"));
                    } else {
                        strDecode4 = NPStringFog.decode("B5D8B6C1B5C9");
                        iM230 = C0053.m562(strDecode4);
                    }
                case 1752458:
                case 1754568:
                    if (C0002.f2 - (C0043.f73 - 3170) <= 0) {
                        C0052.f91 = 34;
                        iM230 = C0002.m230(NPStringFog.decode("B5D5B6C7B5C2"));
                    } else {
                        i = C0001.f1 / C0053.f92;
                        i2 = 1746881;
                        iM230 = i + i2;
                    }
                case 1752491:
                    if (C0052.m502(m359(), str)) {
                        iM230 = C0001.m190(strDecode5);
                    } else if ((C0002.f2 ^ (C0053.f92 / (-1437))) >= 0) {
                        C0052.f91 = 38;
                        iM230 = C0052.m503(strDecode6);
                    } else {
                        iM230 = C0053.m562("ۡۦ۠");
                    }
                case 1753446:
                    iM230 = C0001.m190(strDecode5);
                case 1754383:
                    objArr[0] = str;
                    strDecode3 = NPStringFog.decode("B5D0B6C2B5C1");
                    iM230 = C0001.m190(strDecode3);
                case 1754600:
                    if (C0001.f1 <= 0) {
                        C0052.m520();
                        strDecode5 = NPStringFog.decode("B5D0B6C5B5C6");
                    } else {
                        strDecode5 = strDecode2;
                    }
                    iM230 = C0001.m190(strDecode5);
                case 1755376:
                    objArr = new Object[i3];
                    if (C0001.f1 / (C0053.f92 - 6931) != 0) {
                        C0043.f73 = 51;
                        strDecode4 = NPStringFog.decode("B5D8B6C1B5C9");
                    }
                    iM230 = C0053.m562(strDecode4);
                case 1755526:
                    if (C0043.f73 - (C0043.f73 | (-7218)) >= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D6B6C3");
                    } else {
                        strDecode = NPStringFog.decode("B5D1B6C9B5C3");
                    }
                    iM230 = C0052.m503(strDecode);
                    inputStream = assetInputStream;
            }
        }
    }

    public InputStream o(C0015 c0015) {
        int i;
        int i2;
        int iM254;
        String strDecode;
        int iM190 = C0001.m190(NPStringFog.decode("B5D4B6C9B5C9"));
        String str = null;
        AssetManager.AssetInputStream assetInputStream = null;
        int i3 = 0;
        while (true) {
            switch (iM190) {
                case 56569:
                    if (C0052.m502(m359(), str)) {
                        i = C0053.f92 / C0002.f2;
                        i2 = 1747700;
                        iM190 = i + i2;
                    } else if (C0043.f73 >= 0) {
                        int i4 = i3;
                        strDecode = NPStringFog.decode("B5D8B6C0");
                        iM254 = i4;
                        int iM503 = C0052.m503(strDecode);
                        i3 = iM254;
                        iM190 = iM503;
                    } else {
                        i = C0052.f91 - C0053.f92;
                        i2 = 1748236;
                        iM190 = i + i2;
                    }
                case 1747710:
                    return m366(assetInputStream);
                case 1748674:
                    iM254 = C0002.m254((Integer) C0002.m244(c0015)[2]);
                    strDecode = NPStringFog.decode("B5D3B6FEB5C6");
                    int iM5032 = C0052.m503(strDecode);
                    i3 = iM254;
                    iM190 = iM5032;
                case 1748888:
                    return assetInputStream;
                case 1750539:
                    assetInputStream = (AssetManager.AssetInputStream) C0001.m210(C0002.m219(m358()), C0002.m234(), new Object[]{str, C0052.m510(i3)});
                    if ((C0043.f73 | (C0002.f2 ^ 4331)) >= 0) {
                        C0043.m456();
                        iM190 = C0001.m190(NPStringFog.decode("B5D1B6C9B5FE"));
                    } else {
                        int i42 = i3;
                        strDecode = NPStringFog.decode("B5D8B6C0");
                        iM254 = i42;
                        int iM50322 = C0052.m503(strDecode);
                        i3 = iM254;
                        iM190 = iM50322;
                    }
                case 1751589:
                    if (C0001.f1 <= 0) {
                        C0002.f2 = 42;
                        iM190 = C0052.m503(NPStringFog.decode("B5D7B6C1B5C4"));
                    } else {
                        i = C0053.f92 | C0001.f1;
                        i2 = 1752305;
                        iM190 = i + i2;
                    }
                case 1751780:
                    str = (String) C0002.m244(c0015)[1];
                    if (C0052.f91 + (C0052.f91 ^ 1025) >= 0) {
                        C0053.f92 = 89;
                        iM190 = C0053.m562(NPStringFog.decode("B5D0B6C0B5FE"));
                    } else {
                        i = C0043.f73 * C0052.f91;
                        i2 = 1721620;
                        iM190 = i + i2;
                    }
                case 1753697:
                    i = C0053.f92 / C0002.f2;
                    i2 = 1747700;
                    iM190 = i + i2;
            }
        }
    }
}
