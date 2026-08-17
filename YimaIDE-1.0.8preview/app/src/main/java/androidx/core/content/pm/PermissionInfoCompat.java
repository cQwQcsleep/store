package androidx.core.content.pm;

import android.content.pm.PermissionInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class PermissionInfoCompat {

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static int getProtection(PermissionInfo permissionInfo) {
            return permissionInfo.getProtection();
        }

        public static int getProtectionFlags(PermissionInfo permissionInfo) {
            return permissionInfo.getProtectionFlags();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Protection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProtectionFlags {
    }

    private PermissionInfoCompat() {
    }

    public static int getProtection(PermissionInfo permissionInfo) {
        return Api28Impl.getProtection(permissionInfo);
    }

    public static int getProtectionFlags(PermissionInfo permissionInfo) {
        return Api28Impl.getProtectionFlags(permissionInfo);
    }
}
