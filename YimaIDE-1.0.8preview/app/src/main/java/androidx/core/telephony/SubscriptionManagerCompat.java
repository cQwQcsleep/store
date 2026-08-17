package androidx.core.telephony;

import android.telephony.SubscriptionManager;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SubscriptionManagerCompat {
    private static Method sGetSlotIndexMethod;

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static int getSlotIndex(int i) {
            return SubscriptionManager.getSlotIndex(i);
        }
    }

    private SubscriptionManagerCompat() {
    }

    public static int getSlotIndex(int i) {
        if (i == -1) {
            return -1;
        }
        return Api29Impl.getSlotIndex(i);
    }
}
