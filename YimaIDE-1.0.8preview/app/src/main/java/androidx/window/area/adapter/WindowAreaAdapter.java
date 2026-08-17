package androidx.window.area.adapter;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import androidx.window.area.WindowAreaCapability;
import androidx.window.core.ExtensionsUtil;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Landroidx/window/area/adapter/WindowAreaAdapter;", "", "<init>", "()V", "translate", "Landroidx/window/area/WindowAreaCapability$Status;", NotificationCompat.CATEGORY_STATUS, "", "sessionActive", "", "vendorApiLevel", "translate$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowAreaAdapter {
    public static final WindowAreaAdapter INSTANCE = new WindowAreaAdapter();

    private WindowAreaAdapter() {
    }

    public static /* synthetic */ WindowAreaCapability.Status translate$window_release$default(WindowAreaAdapter windowAreaAdapter, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();
        }
        return windowAreaAdapter.translate$window_release(i, z, i2);
    }

    public final WindowAreaCapability.Status translate$window_release(int status, boolean sessionActive, int vendorApiLevel) {
        return vendorApiLevel <= 3 ? WindowAreaAdapterApi3.INSTANCE.translate(status, sessionActive) : WindowAreaAdapterApi4.INSTANCE.translate(status);
    }
}
