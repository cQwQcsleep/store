package androidx.core.telephony;

import android.telephony.TelephonyManager;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class TelephonyManagerCompat {
    private static Method sGetSubIdMethod;

    public static class Api26Impl {
        private Api26Impl() {
        }

        public static String getImei(TelephonyManager telephonyManager) {
            return telephonyManager.getImei();
        }
    }

    public static class Api30Impl {
        private Api30Impl() {
        }

        public static int getSubscriptionId(TelephonyManager telephonyManager) {
            return telephonyManager.getSubscriptionId();
        }
    }

    private TelephonyManagerCompat() {
    }

    public static String getImei(TelephonyManager telephonyManager) {
        return Api26Impl.getImei(telephonyManager);
    }

    public static int getSubscriptionId(TelephonyManager telephonyManager) {
        return Api30Impl.getSubscriptionId(telephonyManager);
    }
}
