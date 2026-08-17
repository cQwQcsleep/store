package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    public static class Api16Impl {
        private Api16Impl() {
        }

        public static void setDecorFitsSystemWindows(Window window, boolean z) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility & (-1793) : systemUiVisibility | 1792);
        }
    }

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static <T> T requireViewById(Window window, int i) {
            return (T) window.requireViewById(i);
        }
    }

    public static class Api30Impl {
        private Api30Impl() {
        }

        public static void setDecorFitsSystemWindows(Window window, boolean z) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility & (-257) : systemUiVisibility | 256);
            window.setDecorFitsSystemWindows(z);
        }
    }

    public static class Api35Impl {
        private Api35Impl() {
        }

        public static void setDecorFitsSystemWindows(Window window, boolean z) {
            window.setDecorFitsSystemWindows(z);
        }
    }

    private WindowCompat() {
    }

    public static void enableEdgeToEdge(Window window) {
        Objects.requireNonNull(window);
        window.getDecorView();
        setDecorFitsSystemWindows(window, false);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes.layoutInDisplayCutoutMode != 3) {
            attributes.layoutInDisplayCutoutMode = 3;
            window.setAttributes(attributes);
        }
        window.setStatusBarContrastEnforced(false);
        window.setNavigationBarContrastEnforced(false);
    }

    public static WindowInsetsControllerCompat getInsetsController(Window window, View view) {
        return new WindowInsetsControllerCompat(window, view);
    }

    public static <T extends View> T requireViewById(Window window, int i) {
        return (T) Api28Impl.requireViewById(window, i);
    }

    public static void setDecorFitsSystemWindows(Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setDecorFitsSystemWindows(window, z);
        } else {
            Api30Impl.setDecorFitsSystemWindows(window, z);
        }
    }
}
