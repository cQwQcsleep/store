package androidx.core.flagging;

import android.os.Build;
import android.os.flagging.AconfigPackage;
import android.os.flagging.AconfigStorageReadException;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import defpackage.ih5;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/core/flagging/Flags;", "", "<init>", "()V", "Companion", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Flags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<String, AconfigPackage> aconfigCache;
    private static final Set<String> missingPackageCache;

    static {
        int i = Build.VERSION.SDK_INT;
        aconfigCache = i >= 36 ? new ConcurrentHashMap() : null;
        missingPackageCache = i >= 36 ? new CopyOnWriteArraySet() : null;
    }

    @JvmStatic
    public static final boolean getBooleanFlagValue(String str, String str2) {
        return INSTANCE.getBooleanFlagValue(str, str2);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000bH\u0007R\u001e\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00058\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t8\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/core/flagging/Flags$Companion;", "", "<init>", "()V", "aconfigCache", "", "", "Landroid/os/flagging/AconfigPackage;", "missingPackageCache", "", "getBooleanFlagValue", "", "packageName", "flagName", "defaultValue", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ boolean getBooleanFlagValue$default(Companion companion, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.getBooleanFlagValue(str, str2, z);
        }

        @JvmStatic
        public final boolean getBooleanFlagValue(String packageName, String flagName, boolean defaultValue) {
            AconfigPackage aconfigPackageA;
            packageName.getClass();
            flagName.getClass();
            if (Build.VERSION.SDK_INT < 36) {
                return defaultValue;
            }
            Map map = Flags.aconfigCache;
            map.getClass();
            Set set = Flags.missingPackageCache;
            set.getClass();
            if (map.containsKey(packageName)) {
                aconfigPackageA = ih5.a(map.get(packageName));
            } else if (set.contains(packageName)) {
                aconfigPackageA = null;
            } else {
                try {
                    AconfigPackage aconfigPackageLoad = AconfigPackage.load(packageName);
                    map.put(packageName, aconfigPackageLoad);
                    Unit unit = Unit.INSTANCE;
                    aconfigPackageA = aconfigPackageLoad;
                } catch (AconfigStorageReadException unused) {
                    set.add(packageName);
                    aconfigPackageA = null;
                }
            }
            return aconfigPackageA != null ? aconfigPackageA.getBooleanFlagValue(flagName, defaultValue) : defaultValue;
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean getBooleanFlagValue(String str, String str2) {
            str.getClass();
            str2.getClass();
            return getBooleanFlagValue$default(this, str, str2, false, 4, null);
        }
    }

    @JvmStatic
    public static final boolean getBooleanFlagValue(String str, String str2, boolean z) {
        return INSTANCE.getBooleanFlagValue(str, str2, z);
    }
}
