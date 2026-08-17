package defpackage;

import android.os.Build;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract /* synthetic */ class f11 {
    public static /* synthetic */ int a() {
        int i = Build.VERSION.SDK_INT;
        return i < 36 ? i * AndroidComposeViewAccessibilityDelegateCompat.ParcelSafeTextLength : Build.VERSION.SDK_INT_FULL;
    }
}
