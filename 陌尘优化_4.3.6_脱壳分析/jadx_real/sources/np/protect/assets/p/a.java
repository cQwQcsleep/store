package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* loaded from: /workspace/unpacked/classes.dex */
public class a {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f14short = {1090};
    public Method backup;
    private Member target;

    /* renamed from: ۟۟۟ۡۡ, reason: not valid java name and contains not printable characters */
    private Object f15;

    /* renamed from: ۟۟۟ۡۢ, reason: not valid java name and contains not printable characters */
    private Method f16;

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0053 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private a() throws NumberFormatException {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D0B6C6");
        int iM190 = C0001.m190(strDecode3);
        Long lDecode = null;
        while (true) {
            switch (iM190) {
                case 56327:
                    this.f15 = null;
                case 1748857:
                    lDecode = Long.decode(C0043.m453(NPStringFog.decode("37465A0D291733063E3823")));
                    iM190 = C0053.f92 / (C0053.f92 % 9473) <= 0 ? C0043.m455(NPStringFog.decode("B5D5B6FEB5C7")) : (C0001.f1 | C0001.f1) ^ 1754341;
                case 1749826:
                    break;
                case 1750751:
                    if (C0053.f92 + C0002.f2 + 9490 > 0) {
                        C0002.f2 = 94;
                        strDecode = NPStringFog.decode("B5D1B6C9B5C4");
                        iM190 = C0052.m503(strDecode);
                    } else {
                        i = C0052.f91 * C0053.f92;
                        i2 = 1617958;
                        iM190 = i + i2;
                    }
                case 1752460:
                    if (C0002.m259() >= 0) {
                        strDecode = NPStringFog.decode("B5D1B6C6B5FE");
                    } else if (C0053.f92 + C0002.f2 + 9490 > 0) {
                    }
                    iM190 = C0052.m503(strDecode);
                    break;
                case 1754444:
                    if (C0002.f2 * (C0001.f1 % (-1395)) >= 0) {
                        C0001.f1 = 75;
                        strDecode2 = NPStringFog.decode("B5D0B6C0B5C7");
                    } else {
                        strDecode2 = strDecode3;
                    }
                    iM190 = C0001.m190(strDecode2);
                case 1754502:
                    System.out.println(lDecode);
                    i = C0053.f92 - C0002.f2;
                    i2 = 1750564;
                    iM190 = i + i2;
            }
            return;
        }
    }

    private native Method doHook(Member member, Method method);

    private native boolean doUnhook(Member member);

    /* JADX WARN: Removed duplicated region for block: B:90:0x00a0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0094 A[SYNTHETIC] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static a m333(Member member, Method method, Object obj) {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3;
        int i3;
        int i4;
        String strDecode4 = NPStringFog.decode("B5D4B6C7B5C2");
        int iM562 = C0053.m562(strDecode4);
        a aVar = null;
        Method methodM335 = null;
        while (true) {
            switch (iM562) {
                case 1747873:
                    return null;
                case 1747932:
                    return aVar;
                case 1749698:
                    methodM335 = m335(aVar, member, C0001.m189(a.class, C0052.m507(m334(), 0, 1, 669), new Class[]{Object[].class}));
                    if (C0052.f91 >= 0) {
                        C0053.m574();
                        strDecode3 = NPStringFog.decode("B5D2B6C5B5C7");
                        iM562 = C0043.m455(strDecode3);
                    } else {
                        strDecode = NPStringFog.decode("B5D8B6C3B5C5");
                        iM562 = C0052.m503(strDecode);
                    }
                case 1749732:
                    aVar.target = member;
                    i = C0052.f91 / C0053.f92;
                    i2 = 1754411;
                    iM562 = i + i2;
                case 1750565:
                    if (C0002.m259() >= 0) {
                        C0002.m259();
                        strDecode2 = NPStringFog.decode("B5D2B6C4B5C9");
                    } else {
                        strDecode2 = NPStringFog.decode("B5D2B6C2B5C2");
                    }
                    iM562 = C0053.m562(strDecode2);
                case 1751593:
                    aVar.f15 = obj;
                    strDecode = NPStringFog.decode("B5D0B6C9B5C5");
                    iM562 = C0052.m503(strDecode);
                case 1751713:
                    aVar = new a();
                    if (C0052.f91 % (C0053.f92 * (-7280)) >= 0) {
                        strDecode = strDecode4;
                        iM562 = C0052.m503(strDecode);
                    } else {
                        i = C0001.f1 / C0052.f91;
                        i2 = 1749700;
                        iM562 = i + i2;
                    }
                case 1754383:
                    if (C0053.f92 % (C0043.f73 + 5081) < 0) {
                        strDecode2 = NPStringFog.decode("B5EFB6C0B5FE");
                        iM562 = C0053.m562(strDecode2);
                    } else {
                        i = C0043.f73 / C0052.f91;
                        i2 = 1754414;
                        iM562 = i + i2;
                    }
                case 1754411:
                    aVar.f16 = method;
                    if (C0001.f1 <= 0) {
                        strDecode2 = NPStringFog.decode("B5D7B6C2B5C2");
                        iM562 = C0053.m562(strDecode2);
                    } else {
                        strDecode3 = NPStringFog.decode("B5D4B6C3B5C6");
                        iM562 = C0043.m455(strDecode3);
                    }
                case 1754415:
                    try {
                        aVar.backup = methodM335;
                    } catch (NoSuchMethodException unused) {
                        if (C0043.f73 < 0) {
                            i3 = C0002.f2 * C0052.f91;
                            i4 = 1767871;
                            break;
                        } else {
                            C0053.f92 = 69;
                            iM562 = C0002.m230(NPStringFog.decode("B5D8B6C5B5C0"));
                        }
                    }
                    if (C0002.f2 >= 0) {
                        C0043.f73 = 18;
                        iM562 = C0001.m190(NPStringFog.decode("B5D3B6C1B5C3"));
                    } else {
                        strDecode3 = NPStringFog.decode("B5D2B6C5B5C7");
                        iM562 = C0043.m455(strDecode3);
                    }
                case 1754503:
                    if (C0002.f2 >= 0) {
                        strDecode = NPStringFog.decode("B5D3B6FEB5C6");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        i = C0002.f2 / C0043.f73;
                        i2 = 1751713;
                        iM562 = i + i2;
                    }
                case 1755434:
                    if (methodM335 == null) {
                        if (C0053.f92 % (C0043.f73 - 2642) >= 0) {
                            C0001.f1 = 45;
                            strDecode3 = NPStringFog.decode("B5D7B6C1B5C9");
                            iM562 = C0043.m455(strDecode3);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D0B6C7B5C6");
                            iM562 = C0053.m562(strDecode2);
                        }
                    } else if (C0053.f92 % (C0043.f73 + 5081) < 0) {
                    }
                    break;
                case 1755493:
                case 1755559:
                    if (C0002.f2 >= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D4B6C2B5FE");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        i3 = C0052.f91 % C0002.f2;
                        i4 = -1747926;
                        iM562 = i3 ^ i4;
                    }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00fa A[SYNTHETIC] */
    /* renamed from: ۣۣ۟ۢۧ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short[] m334() {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        String strDecode3;
        int iM503 = C0052.m503(NPStringFog.decode("B5D2B6C4B5C3"));
        short[] sArr = null;
        short[] sArr2 = null;
        while (true) {
            switch (iM503) {
                case 1746726:
                    iM503 = (C0052.f91 ^ C0001.f1) + 1755821;
                    sArr = null;
                case 1749759:
                    if (C0002.m259() < 0) {
                        if (C0053.f92 + (C0053.f92 / 8985) >= 0) {
                            C0043.f73 = 12;
                            strDecode = NPStringFog.decode("B5D5B6C4B5C3");
                            iM503 = C0002.m230(strDecode);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D7B6C0B5C0");
                            iM503 = C0052.m503(strDecode2);
                        }
                    } else if (C0052.f91 * (C0001.f1 + 1573) < 0) {
                        iM503 = C0053.m562(NPStringFog.decode("B5D0B6C3B5C4"));
                    } else {
                        i = C0053.f92 % C0043.f73;
                        i2 = 1754591;
                        iM503 = i + i2;
                    }
                case 1749850:
                    break;
                case 1751524:
                    if ((C0043.f73 ^ (C0002.f2 ^ 8940)) <= 0) {
                        strDecode2 = NPStringFog.decode("B5D7B6C0B5C6");
                        sArr = sArr2;
                        iM503 = C0052.m503(strDecode2);
                    } else {
                        strDecode3 = NPStringFog.decode("B5D2B6C9B5C1");
                        sArr = sArr2;
                        iM503 = C0043.m455(strDecode3);
                    }
                case 1752642:
                    if (C0053.f92 / (C0043.f73 % 8651) <= 0) {
                        strDecode = NPStringFog.decode("B5D4B6C9B5C5");
                        iM503 = C0002.m230(strDecode);
                    } else {
                        i = C0043.f73 | C0001.f1;
                        i2 = 1749892;
                        iM503 = i + i2;
                    }
                case 1753573:
                    if (C0052.f91 * (C0001.f1 + 1573) < 0) {
                    }
                    break;
                case 1754412:
                case 1754600:
                    if (C0001.m192() >= 0) {
                        C0043.f73 = 82;
                        strDecode3 = NPStringFog.decode("B5D4B6C0B5C4");
                        iM503 = C0043.m455(strDecode3);
                    } else {
                        iM503 = (C0043.f73 ^ C0053.f92) ^ 1749201;
                    }
                case 1754439:
                    sArr2 = f14short;
                    if ((C0043.f73 | C0043.f73 | (-4192)) >= 0) {
                        strDecode = NPStringFog.decode("B5D8B6C1B5C3");
                        iM503 = C0002.m230(strDecode);
                    } else {
                        i = C0052.f91 * C0001.f1;
                        i2 = 1809034;
                        iM503 = i + i2;
                    }
                case 1754445:
                    if ((C0052.f91 ^ (C0002.f2 * 4967)) <= 0) {
                        C0002.f2 = 49;
                        strDecode3 = NPStringFog.decode("B5D6B6C5B5C2");
                        iM503 = C0043.m455(strDecode3);
                    } else {
                        i = C0052.f91 * C0052.f91;
                        i2 = 1720482;
                        iM503 = i + i2;
                    }
                case 1755370:
                    if ((C0052.f91 ^ (C0052.f91 + 8741)) >= 0) {
                        C0002.m259();
                        strDecode3 = NPStringFog.decode("B5EFB6C1B5C6");
                    } else {
                        strDecode3 = NPStringFog.decode("B5D7B6C7B5C6");
                    }
                    iM503 = C0043.m455(strDecode3);
            }
            return sArr;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    /* renamed from: ۣ۟ۢۧۥ, reason: not valid java name and contains not printable characters */
    public static Method m335(Object obj, Object obj2, Object obj3) {
        int i;
        int i2;
        int iM455 = C0043.m455(NPStringFog.decode("B5D6B6C3B5C7"));
        Method method = null;
        Method methodDoHook = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D8B6C9");
            switch (iM455) {
                case 56509:
                    if (C0053.f92 - (C0043.f73 % (-8590)) >= 0) {
                        C0001.f1 = 80;
                        method = null;
                        iM455 = C0052.m503(NPStringFog.decode("B5D8B6C3B5C5"));
                    } else {
                        iM455 = C0001.f1 + C0001.f1 + 1747040;
                        method = null;
                    }
                case 56576:
                    iM455 = C0002.f2 + C0053.f92 + 57399;
                case 1747750:
                    strDecode = NPStringFog.decode("B5D1B6C0B5C1");
                    iM455 = C0052.m503(strDecode);
                case 1748672:
                case 1750815:
                    i = C0053.f92 * C0002.f2;
                    i2 = 1723361;
                    iM455 = i ^ i2;
                case 1749694:
                    if (C0043.m456() <= 0) {
                        strDecode = NPStringFog.decode("B5D5B6FEB5FE");
                        iM455 = C0052.m503(strDecode);
                    } else {
                        i = C0053.f92 | C0001.f1;
                        i2 = -1754023;
                        iM455 = i ^ i2;
                    }
                case 1750535:
                    iM455 = C0052.m503(strDecode);
                case 1751625:
                    break;
                case 1753514:
                    if (C0001.m192() <= 0) {
                        i = C0052.f91 * C0053.f92;
                        i2 = 1623358;
                        iM455 = i ^ i2;
                    } else {
                        iM455 = C0052.m503(strDecode);
                    }
                case 1754658:
                    methodDoHook = ((a) obj).doHook((Member) obj2, (Method) obj3);
                    iM455 = C0052.m503(NPStringFog.decode("B5D8B6C3B5C5"));
                case 1755434:
                    if (C0001.m192() >= 0) {
                        C0002.f2 = 85;
                    } else {
                        strDecode = NPStringFog.decode("B5D4B6C2B5C9");
                    }
                    iM455 = C0043.m455(strDecode);
                    method = methodDoHook;
            }
            return method;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    /* renamed from: ۟ۥۥۥۤ, reason: not valid java name and contains not printable characters */
    public static Object m336(Object obj) {
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode;
        int iM230 = C0002.m230(NPStringFog.decode("B5D6B6C6"));
        Object obj2 = null;
        Object obj3 = null;
        while (true) {
            switch (iM230) {
                case 56421:
                    if (C0053.m574() >= 0) {
                        C0043.f73 = 8;
                        strDecode = NPStringFog.decode("B5D8B6C0B5C3");
                        iM230 = C0001.m190(strDecode);
                    } else {
                        i = C0052.f91 % C0001.f1;
                        i2 = -1750567;
                        iM230 = i ^ i2;
                    }
                case 56513:
                    if (C0002.m259() >= 0) {
                        iM230 = C0043.m455(NPStringFog.decode("B5D3B6C9"));
                    } else if (C0052.f91 >= 0) {
                        C0001.m192();
                        iM230 = C0052.m503(NPStringFog.decode("B5D3B6C2B5C6"));
                    } else {
                        i3 = C0043.f73 * C0052.f91;
                        i4 = 1724633;
                        iM230 = i3 + i4;
                    }
                case 56542:
                    break;
                case 1746696:
                case 1752484:
                    i3 = C0052.f91 | C0001.f1;
                    i4 = 56671;
                    iM230 = i3 + i4;
                case 1750663:
                    if ((C0002.f2 | (C0001.f1 - 9383)) >= 0) {
                        C0002.m259();
                        obj2 = null;
                        strDecode = NPStringFog.decode("B5D7B6C4B5C4");
                        iM230 = C0001.m190(strDecode);
                    } else {
                        obj2 = null;
                        strDecode = NPStringFog.decode("B5D8B6C0B5C3");
                        iM230 = C0001.m190(strDecode);
                    }
                case 1751687:
                    obj3 = ((a) obj).f15;
                    strDecode = NPStringFog.decode("B5D7B6C4B5C4");
                    iM230 = C0001.m190(strDecode);
                case 1753631:
                    i = C0001.f1 - C0002.f2;
                    i2 = 56686;
                    iM230 = i ^ i2;
                case 1754383:
                    iM230 = C0043.m455(NPStringFog.decode("B5D3B6C9"));
                case 1754567:
                    if ((C0052.f91 | C0043.f73 | 5707) >= 0) {
                        C0002.f2 = 33;
                        iM230 = C0052.m503(NPStringFog.decode("B5D5B6C1B5FE"));
                        obj2 = obj3;
                    } else {
                        strDecode = NPStringFog.decode("B5D7B6C4");
                        obj2 = obj3;
                        iM230 = C0001.m190(strDecode);
                    }
                case 1755401:
                    if (C0052.f91 % (C0052.f91 % 7955) != 0) {
                        iM230 = C0053.m562(NPStringFog.decode("B5D7B6FEB5C6"));
                    } else {
                        i3 = C0001.f1 % C0001.f1;
                        i4 = 1752484;
                        iM230 = i3 + i4;
                    }
            }
            return obj2;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x001b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:63:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0063 A[SYNTHETIC] */
    /* renamed from: ۣ۠ۢۢ, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Method m337(Object obj) {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D8B6C5B5C0");
        int iM562 = C0053.m562(strDecode3);
        Method method = null;
        while (true) {
            Method method2 = method;
            while (true) {
                String strDecode4 = NPStringFog.decode("B5D8B6FEB5C3");
                strDecode = NPStringFog.decode("B5D0B6C5B5FE");
                switch (iM562) {
                    case 1746939:
                        if (C0043.f73 + (C0052.f91 % 2893) >= 0) {
                            C0002.f2 = 83;
                            iM562 = C0001.m190(strDecode3);
                        } else {
                            iM562 = C0052.m503("ۡ۟ۥ");
                        }
                        method2 = null;
                    case 1747803:
                        if (C0052.f91 % (C0052.f91 - 4887) >= 0) {
                            iM562 = C0053.m562(NPStringFog.decode("B5D3B6FEB5C9"));
                        } else {
                            i = C0043.f73 / C0001.f1;
                            i2 = 1746939;
                            iM562 = i + i2;
                        }
                    case 1748615:
                        if (C0001.f1 + (C0001.f1 ^ 1630) <= 0) {
                            C0001.m192();
                            iM562 = C0001.m190("ۡ۟ۥ");
                        } else {
                            i = C0002.f2 ^ C0053.f92;
                            i2 = 1747838;
                            iM562 = i + i2;
                        }
                    case 1748708:
                    case 1749634:
                        if (C0052.f91 + C0053.f92 + 6064 <= 0) {
                            strDecode4 = NPStringFog.decode("B5D8B6FEB5FE");
                        }
                        iM562 = C0052.m503(strDecode4);
                    case 1749732:
                        if (C0053.f92 >= 0) {
                            C0001.f1 = 30;
                            strDecode = NPStringFog.decode("B5D7B6C3B5FE");
                            iM562 = C0052.m503(strDecode);
                        } else {
                            i = C0052.f91 / C0052.f91;
                            i2 = 1755492;
                            iM562 = i + i2;
                        }
                    case 1750540:
                        if (C0043.f73 < 0) {
                            strDecode2 = NPStringFog.decode("B5D5B6C0B5C0");
                            iM562 = C0001.m190(strDecode2);
                        } else {
                            iM562 = C0052.m503(strDecode);
                        }
                    case 1752616:
                        if (C0002.f2 * (C0002.f2 - 978) <= 0) {
                            break;
                        }
                        method2 = method;
                        iM562 = C0001.m190(strDecode4);
                        break;
                    case 1754438:
                        method = ((a) obj).f16;
                        if (C0001.m192() >= 0) {
                            C0002.f2 = 22;
                            iM562 = C0001.m190(strDecode4);
                        } else {
                            strDecode2 = NPStringFog.decode("B5D5B6C5B5C6");
                            iM562 = C0001.m190(strDecode2);
                        }
                    case 1755339:
                        break;
                    case 1755493:
                        if (C0001.m192() <= 0) {
                            i = C0002.f2 | C0043.f73;
                            i2 = 1754441;
                            iM562 = i + i2;
                        } else if (C0043.f73 < 0) {
                        }
                        break;
                }
                return method2;
            }
            C0052.m520();
            iM562 = C0001.m190(strDecode);
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public Object m338(Object[] objArr) {
        int iM562 = C0053.m562(NPStringFog.decode("B5D2B6C9B5C6"));
        C0015 c0015 = null;
        while (true) {
            if (iM562 == 1746881) {
                iM562 = C0001.f1 <= 0 ? C0052.m503(NPStringFog.decode("B5D6B6C2B5C5")) : (C0043.f73 ^ C0001.f1) ^ (-1749669);
            } else if (iM562 == 1749857) {
                c0015 = new C0015(C0002.m219(this), objArr);
                iM562 = (C0053.f92 - C0053.f92) + 1755523;
            } else if (iM562 == 1755523) {
                return C0001.m210(m337(this), m336(this), new Object[]{c0015});
            }
        }
    }
}
