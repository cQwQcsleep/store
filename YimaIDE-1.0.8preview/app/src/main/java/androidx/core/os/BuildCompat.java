package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import defpackage.f11;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0007J\b\u0010\t\u001a\u00020\u0005H\u0007J\b\u0010\n\u001a\u00020\u0005H\u0007J\b\u0010\u000b\u001a\u00020\u0005H\u0007J\b\u0010\f\u001a\u00020\u0005H\u0007J\b\u0010\r\u001a\u00020\u0005H\u0007J\b\u0010\u000e\u001a\u00020\u0005H\u0007J\b\u0010\u000f\u001a\u00020\u0005H\u0007J\b\u0010\u0010\u001a\u00020\u0005H\u0007J\b\u0010\u0011\u001a\u00020\u0005H\u0007J\b\u0010\u0012\u001a\u00020\u0005H\u0007J\b\u0010\u0013\u001a\u00020\u0005H\u0007J\b\u0010\u0014\u001a\u00020\u0005H\u0007J\b\u0010\u0015\u001a\u00020\u0005H\u0007J\b\u0010\u0016\u001a\u00020\u0005H\u0007R\u0010\u0010\u0017\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/core/os/BuildCompat;", "", "<init>", "()V", "isAtLeastPreReleaseCodename", "", "codename", "", "buildCodename", "isAtLeastN", "isAtLeastNMR1", "isAtLeastO", "isAtLeastOMR1", "isAtLeastP", "isAtLeastQ", "isAtLeastR", "isAtLeastS", "isAtLeastSv2", "isAtLeastT", "isAtLeastU", "isAtLeastV", "isAtLeastB", "isAtLeastB_1", "R_EXTENSION_INT", "", "S_EXTENSION_INT", "T_EXTENSION_INT", "AD_SERVICES_EXTENSION_INT", "PrereleaseSdkCheck", "Api30Impl", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BuildCompat {
    public static final int AD_SERVICES_EXTENSION_INT;
    public static final BuildCompat INSTANCE = new BuildCompat();
    public static final int R_EXTENSION_INT;
    public static final int S_EXTENSION_INT;
    public static final int T_EXTENSION_INT;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Landroidx/core/os/BuildCompat$Api30Impl;", "", "<init>", "()V", "getExtensionVersion", "", "extension", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final int getExtensionVersion(int extension) {
            return SdkExtensions.getExtensionVersion(extension);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/core/os/BuildCompat$PrereleaseSdkCheck;", "", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @Retention(RetentionPolicy.CLASS)
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    public @interface PrereleaseSdkCheck {
    }

    static {
        Api30Impl api30Impl = Api30Impl.INSTANCE;
        R_EXTENSION_INT = api30Impl.getExtensionVersion(30);
        S_EXTENSION_INT = api30Impl.getExtensionVersion(31);
        T_EXTENSION_INT = api30Impl.getExtensionVersion(33);
        AD_SERVICES_EXTENSION_INT = api30Impl.getExtensionVersion(1000000);
    }

    private BuildCompat() {
    }

    @Deprecated(message = "Android Baklava is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 36`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 36", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastB() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 36) {
            return true;
        }
        if (i < 35) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        str.getClass();
        return isAtLeastPreReleaseCodename("Baklava", str);
    }

    @Deprecated(message = "Android Baklava minor release 1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT_FULL >= Build.VERSION_CODES_FULL.BAKLAVA_1`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT_FULL >= android.os.Build.VERSION_CODES_FULL.BAKLAVA_1", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastB_1() {
        return Build.VERSION.SDK_INT >= 36 && f11.a() >= 3600001;
    }

    @Deprecated(message = "Android N is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 24`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 24", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastN() {
        return true;
    }

    @Deprecated(message = "Android N MR1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 25`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 25", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastNMR1() {
        return true;
    }

    @Deprecated(message = "Android O is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead use `Build.VERSION.SDK_INT >= 26`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 26", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastO() {
        return true;
    }

    @Deprecated(message = "Android O MR1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 27`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 27", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastOMR1() {
        return true;
    }

    @Deprecated(message = "Android P is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 28`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 28", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastP() {
        return true;
    }

    @JvmStatic
    public static final boolean isAtLeastPreReleaseCodename(String codename, String buildCodename) {
        codename.getClass();
        buildCodename.getClass();
        if (Intrinsics.areEqual("REL", buildCodename)) {
            return false;
        }
        Integer numIsAtLeastPreReleaseCodename$codenameToInt = isAtLeastPreReleaseCodename$codenameToInt(buildCodename);
        Integer numIsAtLeastPreReleaseCodename$codenameToInt2 = isAtLeastPreReleaseCodename$codenameToInt(codename);
        if (numIsAtLeastPreReleaseCodename$codenameToInt != null && numIsAtLeastPreReleaseCodename$codenameToInt2 != null) {
            return numIsAtLeastPreReleaseCodename$codenameToInt.intValue() >= numIsAtLeastPreReleaseCodename$codenameToInt2.intValue();
        }
        if (numIsAtLeastPreReleaseCodename$codenameToInt != null || numIsAtLeastPreReleaseCodename$codenameToInt2 != null) {
            return numIsAtLeastPreReleaseCodename$codenameToInt != null;
        }
        Locale locale = Locale.ROOT;
        String upperCase = buildCodename.toUpperCase(locale);
        upperCase.getClass();
        String upperCase2 = codename.toUpperCase(locale);
        upperCase2.getClass();
        return upperCase.compareTo(upperCase2) >= 0;
    }

    private static final Integer isAtLeastPreReleaseCodename$codenameToInt(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        upperCase.getClass();
        return Intrinsics.areEqual(upperCase, "BAKLAVA") ? 0 : null;
    }

    @Deprecated(message = "Android Q is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 29`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 29", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastQ() {
        return true;
    }

    @Deprecated(message = "Android R is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 30`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 30", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastR() {
        return true;
    }

    @Deprecated(message = "Android S is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 31`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 31", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastS() {
        return true;
    }

    @Deprecated(message = "Android Sv2 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 32`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 32", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastSv2() {
        return true;
    }

    @Deprecated(message = "Android Tiramisu is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 33`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 33", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastT() {
        return true;
    }

    @Deprecated(message = "Android UpsideDownCase is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 34`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 34", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastU() {
        if (Build.VERSION.SDK_INT >= 34) {
            return true;
        }
        String str = Build.VERSION.CODENAME;
        str.getClass();
        return isAtLeastPreReleaseCodename("UpsideDownCake", str);
    }

    @Deprecated(message = "Android VanillaIceCream is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 35`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 35", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastV() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 35) {
            return true;
        }
        if (i < 34) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        str.getClass();
        return isAtLeastPreReleaseCodename("VanillaIceCream", str);
    }
}
