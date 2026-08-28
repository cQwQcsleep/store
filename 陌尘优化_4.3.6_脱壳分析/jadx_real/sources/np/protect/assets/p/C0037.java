package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟ۡۡ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0037 {
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m441(Class<?> cls, Object obj, String str) {
        String strDecode;
        int i;
        int i2;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D5B6C5B5FE");
        int iM562 = C0053.m562(strDecode3);
        Object objM572 = null;
        Field fieldM568 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D4B6C0B5C3");
            switch (iM562) {
                case 1748894:
                    break;
                case 1750782:
                    if (C0043.m456() <= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D5B6FEB5C4");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        i = C0001.f1 % C0001.f1;
                        i2 = 1748894;
                        iM562 = i + i2;
                    }
                case 1751555:
                    if ((C0001.f1 | C0002.f2 | (-270)) >= 0) {
                        C0053.m574();
                        strDecode4 = NPStringFog.decode("B5D0B6C4B5C3");
                    }
                    iM562 = C0052.m503(strDecode4);
                case 1751557:
                    fieldM568 = C0053.m568(cls, str);
                    if (C0001.f1 % (C0002.f2 * (-7001)) <= 0) {
                        C0052.f91 = 78;
                        iM562 = C0052.m503(strDecode3);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D6B6C2B5C0");
                        iM562 = C0001.m190(strDecode2);
                    }
                case 1752584:
                    if (C0053.f92 >= 0) {
                        strDecode = NPStringFog.decode("B5D2B6C0B5C1");
                        iM562 = C0052.m503(strDecode);
                    } else {
                        i = C0002.f2 | C0053.f92;
                        i2 = 1752618;
                        iM562 = i + i2;
                    }
                case 1752608:
                    if (C0043.f73 + C0002.f2 + 4556 <= 0) {
                        C0053.m574();
                        strDecode2 = NPStringFog.decode("B5D6B6C2B5C0");
                        iM562 = C0001.m190(strDecode2);
                    } else {
                        iM562 = C0053.m562(strDecode4);
                    }
                case 1753509:
                    objM572 = C0053.m572(fieldM568, obj);
                    strDecode2 = NPStringFog.decode("B5D1B6C9B5C4");
                    iM562 = C0001.m190(strDecode2);
                case 1753540:
                    try {
                        C0001.m199(fieldM568, true);
                        if (C0052.m520() <= 0) {
                            C0052.f91 = 16;
                            iM562 = C0043.m455(NPStringFog.decode("B5D5B6C2B5C7"));
                        } else {
                            iM562 = (C0043.f73 | C0001.f1) ^ (-1753378);
                        }
                    } catch (IllegalAccessException e) {
                        C0052.m517(e);
                        return null;
                    } catch (IllegalArgumentException e2) {
                        C0002.m238(e2);
                        return null;
                    } catch (NoSuchFieldException e3) {
                        C0052.m527(e3);
                        return null;
                    }
            }
            return null;
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m442(Class<?> cls, Object obj, String str, Object[] objArr, Class<?>... clsArr) {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5D2B6C1B5FE");
        int iM190 = C0001.m190(strDecode2);
        Object objM235 = null;
        Method methodM189 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D6B6FEB5C4");
            switch (iM190) {
                case 56444:
                    break;
                case 1746877:
                    C0053.m549(methodM189, true);
                    if (C0053.m574() >= 0) {
                        C0052.f91 = 72;
                        iM190 = C0002.m230(strDecode2);
                    } else {
                        iM190 = (C0002.f2 / C0053.f92) + 1751529;
                    }
                case 1749601:
                    iM190 = C0002.m230(strDecode3);
                case 1751529:
                    objM235 = C0002.m235(methodM189, obj, objArr);
                    strDecode = NPStringFog.decode("B5D4B6C1");
                    iM190 = C0001.m190(strDecode);
                case 1751593:
                    if (C0043.m456() <= 0) {
                        C0052.m520();
                        strDecode3 = NPStringFog.decode("B5D1B6C1B5C0");
                        iM190 = C0043.m455(strDecode3);
                    } else {
                        iM190 = C0002.m230(strDecode2);
                    }
                case 1753420:
                    try {
                        methodM189 = C0001.m189(cls, str, clsArr);
                        i = C0002.f2 % C0043.f73;
                        i2 = -1746935;
                        iM190 = i ^ i2;
                    } catch (IllegalAccessException e) {
                        C0052.m517(e);
                        return null;
                    } catch (IllegalArgumentException e2) {
                        C0002.m238(e2);
                        return null;
                    } catch (NoSuchMethodException e3) {
                        C0002.m227(e3);
                        return null;
                    } catch (Exception e4) {
                        C0052.m536(e4);
                        return null;
                    }
                case 1754662:
                    if (C0001.m192() >= 0) {
                        C0043.m456();
                        strDecode3 = NPStringFog.decode("B5D2B6C2B5C2");
                        iM190 = C0002.m230(strDecode3);
                    } else {
                        iM190 = C0043.m455(strDecode3);
                    }
                case 1755491:
                    if (C0053.f92 >= 0) {
                        C0052.f91 = 10;
                        strDecode = NPStringFog.decode("B5EFB6C6B5C2");
                        iM190 = C0001.m190(strDecode);
                    } else {
                        i = C0002.f2 ^ C0001.f1;
                        i2 = -56661;
                        iM190 = i ^ i2;
                    }
            }
            return null;
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m443(String str, Object obj, String str2) {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5EFB6C3B5C6");
        int iM230 = C0002.m230(strDecode2);
        Object objM183 = null;
        while (true) {
            String strDecode3 = NPStringFog.decode("B5D1B6C3B5C0");
            switch (iM230) {
                case 1746788:
                    int i3 = C0052.f91;
                    int i4 = C0001.f1;
                    iM230 = C0002.m230(strDecode3);
                case 1747746:
                    if (C0052.m520() <= 0) {
                        C0052.f91 = 78;
                        strDecode = NPStringFog.decode("B5D8B6C1B5C7");
                        iM230 = C0052.m503(strDecode);
                    } else {
                        iM230 = C0002.m230(strDecode3);
                    }
                case 1747809:
                    break;
                case 1747901:
                    if ((C0002.f2 ^ (C0002.f2 % (-149))) != 0) {
                        C0052.m520();
                        strDecode3 = NPStringFog.decode("B5D2B6C0");
                        iM230 = C0002.m230(strDecode3);
                    } else {
                        i = C0001.f1 ^ C0052.f91;
                        i2 = 1747239;
                        iM230 = i + i2;
                    }
                case 1748704:
                    try {
                        objM183 = C0001.m183(C0002.m231(str), obj, str2);
                        if (C0043.f73 >= 0) {
                            iM230 = C0043.m455(strDecode2);
                        } else {
                            i = C0053.f92 - C0053.f92;
                            i2 = 1747809;
                            iM230 = i + i2;
                        }
                    } catch (ClassNotFoundException e) {
                        C0001.m198(e);
                        return null;
                    } catch (IllegalArgumentException e2) {
                        C0002.m238(e2);
                        return null;
                    }
                case 1750695:
                    if (C0053.f92 >= 0) {
                        C0052.f91 = 33;
                        strDecode = NPStringFog.decode("B5D6B6C3");
                        iM230 = C0052.m503(strDecode);
                    } else {
                        iM230 = (C0043.f73 ^ C0053.f92) ^ 1747178;
                    }
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x015b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x014f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f3 A[SYNTHETIC] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object m444(String str, Object obj, String str2, Object[] objArr, Class<?>... clsArr) {
        int i;
        int i2;
        String strDecode;
        String strDecode2;
        int i3;
        int i4;
        String strDecode3;
        String strDecode4;
        int iM562 = C0053.m562(NPStringFog.decode("B5D4B6C7B5C3"));
        Object[] objArr2 = null;
        Class<?>[] clsArr2 = null;
        Object[] objArr3 = null;
        Class<?>[] clsArr3 = null;
        Object obj2 = null;
        Object[] objArr4 = null;
        Class<?>[] clsArr4 = null;
        while (true) {
            String strDecode5 = NPStringFog.decode("B5D2B6C9B5C9");
            switch (iM562) {
                case 1746689:
                    if (C0001.f1 + (C0052.f91 * (-1271)) <= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D5B6C4B5C4");
                        iM562 = C0002.m230(strDecode);
                    } else {
                        i = C0043.f73 % C0002.f2;
                        i2 = 1751727;
                        iM562 = i + i2;
                    }
                case 1746845:
                    if (objArr2 == null) {
                        strDecode2 = NPStringFog.decode("B5D2B6C2B5C0");
                        iM562 = C0052.m503(strDecode2);
                    } else if (C0052.f91 < 0) {
                        strDecode4 = NPStringFog.decode("B5D7B6C6B5FE");
                        iM562 = C0053.m562(strDecode4);
                    } else {
                        i = C0052.f91 / C0002.f2;
                        i2 = 1747897;
                        iM562 = i + i2;
                    }
                case 1747899:
                    try {
                        Object objM554 = C0053.m554(C0002.m231(str), obj, str2, objArr4, clsArr4);
                        if (C0053.f92 + C0002.f2 + 6234 <= 0) {
                            C0052.m520();
                            obj2 = objM554;
                            strDecode3 = NPStringFog.decode("B5D3B6C6B5C1");
                            iM562 = C0001.m190(strDecode3);
                        } else {
                            obj2 = objM554;
                            strDecode2 = NPStringFog.decode("B5D5B6C4B5C6");
                            iM562 = C0052.m503(strDecode2);
                        }
                    } catch (ClassNotFoundException unused) {
                        return null;
                    }
                case 1748673:
                    if (C0043.m456() <= 0) {
                        objArr2 = objArr;
                        strDecode4 = NPStringFog.decode("B5D0B6C6B5C3");
                        iM562 = C0053.m562(strDecode4);
                    } else {
                        iM562 = (C0001.f1 ^ C0053.f92) ^ (-1754058);
                        objArr2 = objArr;
                    }
                case 1749579:
                    if (C0002.m259() < 0) {
                        C0043.m456();
                        strDecode3 = NPStringFog.decode("B5D6B6C7B5C2");
                        iM562 = C0001.m190(strDecode3);
                    } else {
                        strDecode = strDecode5;
                        iM562 = C0002.m230(strDecode);
                    }
                case 1749696:
                    objArr3 = new Object[0];
                    if ((C0043.f73 ^ (C0043.f73 / (-4607))) >= 0) {
                        C0052.f91 = 64;
                        iM562 = C0053.m562(strDecode5);
                    } else {
                        i3 = C0053.f92 - C0043.f73;
                        i4 = 1751427;
                        iM562 = i3 + i4;
                    }
                case 1749858:
                    if (C0052.f91 >= 0) {
                        C0052.f91 = 89;
                        iM562 = C0002.m230(NPStringFog.decode("B5D8B6C7B5FE"));
                        objArr4 = objArr2;
                    } else {
                        strDecode3 = NPStringFog.decode("B5EFB6C5B5C3");
                        objArr4 = objArr2;
                        iM562 = C0001.m190(strDecode3);
                    }
                case 1750658:
                    iM562 = C0002.m230(strDecode5);
                    clsArr4 = clsArr2;
                case 1750780:
                    objArr4 = objArr3;
                    strDecode4 = NPStringFog.decode("B5D0B6C6B5C3");
                    iM562 = C0053.m562(strDecode4);
                case 1750813:
                    if (clsArr3 == null) {
                        int i5 = C0052.f91 / (C0001.f1 * (-7420));
                        strDecode5 = NPStringFog.decode("B5D4B6C1B5C7");
                        if (i5 != 0) {
                            C0002.f2 = 1;
                            iM562 = C0001.m190(strDecode5);
                        } else {
                            strDecode = strDecode5;
                            iM562 = C0002.m230(strDecode);
                        }
                    } else if (C0002.m259() < 0) {
                    }
                    break;
                case 1751530:
                    clsArr2 = new Class[0];
                    if (C0052.f91 >= 0) {
                        strDecode3 = NPStringFog.decode("B5D7B6C4B5C9");
                        iM562 = C0001.m190(strDecode3);
                    } else {
                        strDecode4 = NPStringFog.decode("B5D3B6C2B5C3");
                        iM562 = C0053.m562(strDecode4);
                    }
                case 1751712:
                    if (C0053.m574() >= 0) {
                        C0002.m259();
                        clsArr3 = clsArr;
                        strDecode2 = NPStringFog.decode("B5D5B6C4B5C6");
                        iM562 = C0052.m503(strDecode2);
                    } else {
                        iM562 = (C0053.f92 % C0053.f92) ^ 1748673;
                        clsArr3 = clsArr;
                    }
                case 1752647:
                    return obj2;
                case 1753479:
                    strDecode5 = NPStringFog.decode("B5D3B6C9B5C3");
                    clsArr4 = clsArr3;
                    strDecode = strDecode5;
                    iM562 = C0002.m230(strDecode);
                case 1754570:
                    if (C0052.f91 < 0) {
                    }
                    break;
                case 1755553:
                    if (C0002.f2 >= 0) {
                        C0052.f91 = 58;
                        strDecode3 = NPStringFog.decode("B5D4B6C9B5C7");
                        iM562 = C0001.m190(strDecode3);
                    } else {
                        i3 = C0052.f91 * C0002.f2;
                        i4 = 1740335;
                        iM562 = i3 + i4;
                    }
            }
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m445(String str, String str2, Class[] clsArr, Object[] objArr) {
        String strDecode;
        int i;
        int i2;
        int iM503 = C0052.m503(NPStringFog.decode("B5D6B6C6B5C1"));
        Object objM235 = null;
        while (true) {
            switch (iM503) {
                case 1747772:
                    if (C0052.f91 >= 0) {
                        strDecode = NPStringFog.decode("B5D0B6C9B5C7");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        i = C0053.f92 + C0043.f73;
                        i2 = 1752475;
                        iM503 = i + i2;
                    }
                case 1751494:
                    break;
                case 1753419:
                    try {
                        objM235 = C0002.m235(C0052.m530(C0002.m231(str), str2, clsArr), null, objArr);
                        strDecode = C0052.f91 >= 0 ? NPStringFog.decode("B5D6B6C0B5C4") : NPStringFog.decode("B5D4B6FEB5C0");
                        iM503 = C0001.m190(strDecode);
                    } catch (ClassNotFoundException e) {
                        C0001.m198(e);
                        return null;
                    } catch (IllegalAccessException e2) {
                        C0052.m517(e2);
                        return null;
                    } catch (IllegalArgumentException e3) {
                        C0002.m238(e3);
                        return null;
                    } catch (NoSuchMethodException e4) {
                        C0002.m227(e4);
                        return null;
                    } catch (SecurityException e5) {
                        C0002.m260(e5);
                        return null;
                    } catch (InvocationTargetException e6) {
                        C0001.m186(e6);
                        return null;
                    }
                case 1753482:
                    i = C0043.f73 / C0001.f1;
                    i2 = 1753663;
                    iM503 = i + i2;
                case 1753663:
                    i = C0043.f73 | C0002.f2;
                    i2 = 1753422;
                    iM503 = i + i2;
                case 1755367:
                    if (C0052.f91 + C0052.f91 + 1490 <= 0) {
                        C0002.m259();
                        iM503 = C0053.m562(NPStringFog.decode("B5D3B6C7B5FE"));
                    } else {
                        i = C0052.f91 + C0053.f92;
                        i2 = 1754395;
                        iM503 = i + i2;
                    }
            }
            return null;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0011. Please report as an issue. */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static void m446(String str, String str2, Object obj, Object obj2) {
        int i;
        int i2;
        String strDecode;
        int i3;
        int i4;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D2B6C6B5C7");
        int iM455 = C0043.m455(strDecode3);
        Field fieldM568 = null;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5D1B6C1B5FE");
            switch (iM455) {
                case 56416:
                    C0052.m532(fieldM568, obj, obj2);
                    if (C0053.f92 + (C0043.f73 - 8082) >= 0) {
                        iM455 = C0001.m190(strDecode4);
                    } else {
                        i = C0001.f1 - C0052.f91;
                        i2 = 1749240;
                        iM455 = i + i2;
                    }
                case 56474:
                    if (C0043.f73 / (C0052.f91 - 3113) != 0) {
                        C0053.m574();
                        strDecode4 = NPStringFog.decode("B5D0B6C0B5C7");
                        iM455 = C0002.m230(strDecode4);
                    } else {
                        i = C0001.f1 * C0001.f1;
                        i2 = 1623800;
                        iM455 = i + i2;
                    }
                case 1746694:
                case 1747687:
                case 1747838:
                case 1748709:
                case 1750625:
                case 1752708:
                    if (C0053.f92 >= 0) {
                        strDecode = NPStringFog.decode("B5D1B6C4B5C0");
                        iM455 = C0043.m455(strDecode);
                    } else {
                        i = C0052.f91 | C0052.f91;
                        i2 = 1751938;
                        iM455 = i + i2;
                    }
                case 1746975:
                    i = C0053.f92 / C0043.f73;
                    i2 = 1749753;
                    iM455 = i + i2;
                case 1748640:
                    fieldM568 = C0053.m568(C0002.m231(str), str2);
                    if (C0002.f2 >= 0) {
                        C0043.m456();
                        iM455 = C0052.m503(NPStringFog.decode("B5EFB6C9B5C9"));
                    } else {
                        i3 = C0053.f92 / C0002.f2;
                        i4 = 1748654;
                        iM455 = i3 ^ i4;
                    }
                case 1748644:
                    try {
                        C0001.m199(fieldM568, true);
                        i3 = C0043.f73 | C0002.f2;
                        i4 = -56419;
                    } catch (ClassNotFoundException e) {
                        C0001.m198(e);
                        if ((C0002.f2 | (C0043.f73 - 5345)) < 0) {
                            i = C0053.f92 % C0002.f2;
                            i2 = 1750679;
                            break;
                        } else {
                            C0002.m259();
                            strDecode = NPStringFog.decode("B5D0B6C1B5C6");
                            break;
                        }
                    } catch (IllegalAccessException e2) {
                        C0052.m517(e2);
                        i3 = C0002.f2 ^ C0001.f1;
                        i4 = -1748942;
                    } catch (IllegalArgumentException e3) {
                        C0002.m238(e3);
                        if (C0002.m259() < 0) {
                            i = C0043.f73 % C0052.f91;
                            i2 = 1746699;
                            break;
                        } else {
                            strDecode2 = NPStringFog.decode("B5D1B6C1B5C2");
                            iM455 = C0002.m230(strDecode2);
                        }
                    } catch (NoSuchFieldException e4) {
                        C0052.m527(e4);
                        if (C0053.m574() < 0) {
                            strDecode = NPStringFog.decode("B5D5B6C6B5C7");
                            break;
                        } else {
                            C0052.f91 = 4;
                            strDecode2 = NPStringFog.decode("B5D2B6C4B5C1");
                            iM455 = C0002.m230(strDecode2);
                        }
                    } catch (SecurityException e5) {
                        C0002.m260(e5);
                        strDecode2 = NPStringFog.decode("B5D0B6C4B5C2");
                        iM455 = C0002.m230(strDecode2);
                    }
                    iM455 = i3 ^ i4;
                case 1749757:
                    if (C0002.f2 % (C0043.f73 * 8407) >= 0) {
                        C0001.m192();
                        iM455 = C0052.m503(strDecode3);
                    } else {
                        i = C0052.f91 * C0043.f73;
                        i2 = 1720633;
                        iM455 = i + i2;
                    }
                case 1749825:
                case 1754626:
                    iM455 = C0002.m230(strDecode4);
                case 1751776:
                    break;
            }
            return;
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static boolean m447(Class<?> cls, Object obj, String str, Object obj2) {
        String strDecode;
        int i;
        int i2;
        int i3;
        int i4;
        String strDecode2;
        int iM503 = C0052.m503(NPStringFog.decode("B5D6B6C0B5C4"));
        Field fieldM568 = null;
        while (true) {
            switch (iM503) {
                case 56325:
                    return true;
                case 1747654:
                case 1750601:
                case 1755562:
                    if (C0053.m574() >= 0) {
                        C0053.m574();
                        strDecode = NPStringFog.decode("B5D8B6C6B5C5");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        i = C0002.f2 - C0001.f1;
                        i2 = -1747816;
                        iM503 = i ^ i2;
                    }
                case 1747657:
                    return false;
                case 1747867:
                    i3 = C0053.f92 / C0002.f2;
                    i4 = 56315;
                    iM503 = i3 + i4;
                case 1748795:
                    C0001.m199(fieldM568, true);
                    if (C0001.f1 <= 0) {
                        C0001.m192();
                        strDecode2 = NPStringFog.decode("B5D0B6C7B5C0");
                        iM503 = C0043.m455(strDecode2);
                    } else {
                        i3 = C0052.f91 / C0002.f2;
                        i4 = 1752491;
                        iM503 = i3 + i4;
                    }
                case 1749732:
                    fieldM568 = C0053.m568(cls, str);
                    if (C0052.f91 >= 0) {
                        strDecode = NPStringFog.decode("B5D0B6FEB5C9");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        i3 = C0053.f92 - C0043.f73;
                        i4 = 1749442;
                        iM503 = i3 + i4;
                    }
                case 1751591:
                    if (C0053.f92 >= 0) {
                        C0002.f2 = 70;
                        strDecode = NPStringFog.decode("B5D7B6C9B5C4");
                        iM503 = C0001.m190(strDecode);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D2B6C5B5C7");
                        iM503 = C0043.m455(strDecode2);
                    }
                case 1752493:
                    try {
                        C0052.m532(fieldM568, obj, obj2);
                        if (C0002.m259() >= 0) {
                            C0043.f73 = 5;
                        }
                        strDecode2 = NPStringFog.decode("B5D0B6C4");
                        iM503 = C0043.m455(strDecode2);
                    } catch (IllegalAccessException e) {
                        C0052.m517(e);
                        if (C0043.m456() > 0) {
                            strDecode = NPStringFog.decode("B5D0B6FEB5C4");
                            break;
                        } else {
                            C0053.f92 = 96;
                            strDecode = NPStringFog.decode("B5D5B6C1B5C9");
                            break;
                        }
                    } catch (IllegalArgumentException e2) {
                        C0002.m238(e2);
                        if (C0001.m192() < 0) {
                            i = C0002.f2 - C0052.f91;
                            i2 = 1755644;
                            break;
                        } else {
                            iM503 = C0002.m230(NPStringFog.decode("B5D1B6C4B5FE"));
                        }
                    } catch (NoSuchFieldException e3) {
                        C0052.m527(e3);
                        i = C0043.f73 % C0002.f2;
                        i2 = -1750600;
                        break;
                    }
                case 1752552:
                    if (C0002.f2 >= 0) {
                        strDecode2 = NPStringFog.decode("B5D2B6FEB5C7");
                        iM503 = C0043.m455(strDecode2);
                    } else {
                        i3 = C0043.f73 + C0002.f2;
                        i4 = 1753725;
                        iM503 = i3 + i4;
                    }
                case 1753482:
                    if (C0002.m259() >= 0) {
                        C0001.m192();
                        strDecode2 = NPStringFog.decode("B5D4B6C3B5C4");
                        iM503 = C0043.m455(strDecode2);
                    } else {
                        i3 = C0001.f1 / C0002.f2;
                        i4 = 1749736;
                        iM503 = i3 + i4;
                    }
            }
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static boolean m448(String str, Object obj, String str2, Object obj2) {
        String strDecode;
        int i;
        int i2;
        String strDecode2 = NPStringFog.decode("B5EFB6C6B5C2");
        int iM190 = C0001.m190(strDecode2);
        while (true) {
            switch (iM190) {
                case 56451:
                    if ((C0002.f2 | (C0002.f2 % (-6734))) >= 0) {
                        C0052.m520();
                        strDecode = NPStringFog.decode("B5D4B6C2B5C9");
                        iM190 = C0053.m562(strDecode);
                    } else {
                        iM190 = C0002.m230(strDecode2);
                    }
                case 1746939:
                    strDecode = NPStringFog.decode("B5D4B6C6B5C7");
                    iM190 = C0053.m562(strDecode);
                case 1749765:
                    i = C0043.f73 + C0052.f91;
                    i2 = 1752076;
                    iM190 = i + i2;
                case 1751747:
                    try {
                        C0053.m544(C0002.m231(str), obj, str2, obj2);
                        if (C0002.m259() >= 0) {
                            strDecode = NPStringFog.decode("B5D4B6C6B5C7");
                            iM190 = C0053.m562(strDecode);
                        } else {
                            i = C0052.f91 | C0052.f91;
                            i2 = 1755784;
                            iM190 = i + i2;
                        }
                    } catch (ClassNotFoundException e) {
                        C0001.m198(e);
                        return false;
                    } catch (IllegalArgumentException e2) {
                        C0002.m238(e2);
                        return false;
                    }
                case 1752648:
                    strDecode = C0002.f2 >= 0 ? NPStringFog.decode("B5D0B6C7B5C6") : NPStringFog.decode("B5D8B6C9B5C7");
                    iM190 = C0053.m562(strDecode);
                case 1755622:
                    break;
            }
            return false;
        }
    }
}
