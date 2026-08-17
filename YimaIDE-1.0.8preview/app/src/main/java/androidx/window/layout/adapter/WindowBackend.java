package androidx.window.layout.adapter;

import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.layout.SupportedPosture;
import androidx.window.layout.WindowLayoutInfo;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\b\u0010\f\u001a\u00020\rH\u0017J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H'R \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8gX§\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Landroidx/window/layout/adapter/WindowBackend;", "", "registerLayoutChangeCallback", "", "context", "Landroid/content/Context;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "unregisterLayoutChangeCallback", "hasRegisteredListeners", "", "supportedPostures", "", "Landroidx/window/layout/SupportedPosture;", "getSupportedPostures$annotations", "()V", "getSupportedPostures", "()Ljava/util/List;", "getCurrentWindowLayoutInfo", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface WindowBackend {
    static /* synthetic */ void getSupportedPostures$annotations() {
    }

    WindowLayoutInfo getCurrentWindowLayoutInfo(Context context);

    List<SupportedPosture> getSupportedPostures();

    default boolean hasRegisteredListeners() {
        return false;
    }

    void registerLayoutChangeCallback(Context context, Executor executor, Consumer<WindowLayoutInfo> callback);

    void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback);
}
