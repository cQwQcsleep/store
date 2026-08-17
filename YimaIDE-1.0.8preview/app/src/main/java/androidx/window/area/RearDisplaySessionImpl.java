package androidx.window.area;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.extensions.area.WindowAreaComponent;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/window/area/RearDisplaySessionImpl;", "Landroidx/window/area/WindowAreaSession;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/extensions/area/WindowAreaComponent;)V", "close", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RearDisplaySessionImpl implements WindowAreaSession {
    private final WindowAreaComponent windowAreaComponent;

    public RearDisplaySessionImpl(WindowAreaComponent windowAreaComponent) {
        windowAreaComponent.getClass();
        this.windowAreaComponent = windowAreaComponent;
    }

    @Override // androidx.window.area.WindowAreaSession
    public void close() {
        this.windowAreaComponent.endRearDisplaySession();
    }
}
