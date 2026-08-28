package np.protect.assets.p;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟۟ۢ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0016 {
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m401(Method method, Object obj, Object... objArr) throws Throwable {
        int i;
        int i2;
        int iM230 = C0002.m230(NPStringFog.decode("B5D3B6C4B5C6"));
        Object objM235 = null;
        while (true) {
            String strDecode = NPStringFog.decode("B5D4B6C3B5C9");
            switch (iM230) {
                case 1749639:
                    if (C0001.m192() >= 0) {
                        C0001.m192();
                        strDecode = NPStringFog.decode("B5D1B6C4B5C7");
                    }
                    iM230 = C0053.m562(strDecode);
                case 1750725:
                    i = C0001.f1 + C0002.f2;
                    i2 = 1752397;
                    iM230 = i + i2;
                case 1751594:
                    return objM235;
                case 1752676:
                    try {
                        objM235 = C0002.m235(method, obj, objArr);
                        if (C0002.m259() >= 0) {
                            C0043.f73 = 59;
                            strDecode = NPStringFog.decode("B5D2B6C0B5C7");
                        }
                        iM230 = C0053.m562(strDecode);
                    } catch (InvocationTargetException e) {
                        throw ((Throwable) C0002.m239(C0053.m569(e)));
                    }
                case 1755403:
                    if (C0001.f1 * C0001.f1 * (-9742) >= 0) {
                        C0043.m456();
                        iM230 = C0002.m230(NPStringFog.decode("B5D5B6C2B5FE"));
                    } else {
                        iM230 = (C0001.f1 - C0043.f73) ^ 1752174;
                    }
                case 1755530:
                    i = C0052.f91 ^ C0001.f1;
                    i2 = 1751176;
                    iM230 = i + i2;
            }
        }
    }

    /* renamed from: ۟۟۟۟۟, reason: not valid java name and contains not printable characters */
    public static <T> T m402(T t) {
        String strDecode;
        String strDecode2 = NPStringFog.decode("B5D3B6FEB5C2");
        int iM562 = C0053.m562(strDecode2);
        while (iM562 != 56480) {
            if (iM562 == 1750535) {
                C0052.m509(t);
                strDecode = NPStringFog.decode("B5D5B6C4");
            } else if (iM562 == 1754631) {
                if (C0043.f73 + (C0001.f1 ^ 1520) <= 0) {
                    C0053.f92 = 23;
                    strDecode = NPStringFog.decode("B5D1B6C1B5C9");
                } else {
                    strDecode = strDecode2;
                }
            }
            iM562 = C0043.m455(strDecode);
        }
        return t;
    }
}
