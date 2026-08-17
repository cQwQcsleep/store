package androidx.window.core;

import android.util.Log;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.extensions.WindowExtensionsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/window/core/ExtensionsUtil;", "", "<init>", "()V", "TAG", "", "safeVendorApiLevel", "", "getSafeVendorApiLevel", "()I", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ExtensionsUtil {
    public static final ExtensionsUtil INSTANCE = new ExtensionsUtil();
    private static final String TAG = Reflection.getOrCreateKotlinClass(ExtensionsUtil.class).getSimpleName();

    private ExtensionsUtil() {
    }

    public final int getSafeVendorApiLevel() {
        try {
            return WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
        } catch (NoClassDefFoundError unused) {
            if (BuildConfig.INSTANCE.getVerificationMode() != VerificationMode.LOG) {
                return 0;
            }
            Log.d(TAG, "Embedding extension version not found");
            return 0;
        } catch (NullPointerException unused2) {
            if (BuildConfig.INSTANCE.getVerificationMode() != VerificationMode.LOG) {
                return 0;
            }
            Log.d(TAG, "Error with Extension implementation");
            return 0;
        } catch (UnsupportedOperationException unused3) {
            if (BuildConfig.INSTANCE.getVerificationMode() != VerificationMode.LOG) {
                return 0;
            }
            Log.d(TAG, "Stub Extension");
            return 0;
        }
    }
}
