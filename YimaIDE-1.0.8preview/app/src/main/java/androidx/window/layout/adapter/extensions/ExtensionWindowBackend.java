package androidx.window.layout.adapter.extensions;

import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.SupportedPosture;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0097\u0001J\t\u0010\t\u001a\u00020\nH\u0097\u0001J'\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0096\u0001J\u0017\u0010\u0011\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138WX\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowBackend;", "Landroidx/window/layout/adapter/WindowBackend;", "backend", "<init>", "(Landroidx/window/layout/adapter/WindowBackend;)V", "getCurrentWindowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "hasRegisteredListeners", "", "registerLayoutChangeCallback", "", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "unregisterLayoutChangeCallback", "supportedPostures", "", "Landroidx/window/layout/SupportedPosture;", "getSupportedPostures", "()Ljava/util/List;", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ExtensionWindowBackend implements WindowBackend {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final WindowBackend backend;

    public ExtensionWindowBackend(WindowBackend windowBackend) {
        windowBackend.getClass();
        this.backend = windowBackend;
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public WindowLayoutInfo getCurrentWindowLayoutInfo(Context context) {
        context.getClass();
        return this.backend.getCurrentWindowLayoutInfo(context);
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public List<SupportedPosture> getSupportedPostures() {
        return this.backend.getSupportedPostures();
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public boolean hasRegisteredListeners() {
        return this.backend.hasRegisteredListeners();
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void registerLayoutChangeCallback(Context context, Executor executor, Consumer<WindowLayoutInfo> callback) {
        context.getClass();
        executor.getClass();
        callback.getClass();
        this.backend.registerLayoutChangeCallback(context, executor, callback);
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback) {
        callback.getClass();
        this.backend.unregisterLayoutChangeCallback(callback);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowBackend$Companion;", "", "<init>", "()V", "newInstance", "Landroidx/window/layout/adapter/WindowBackend;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "adapter", "Landroidx/window/core/ConsumerAdapter;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WindowBackend newInstance(WindowLayoutComponent component, ConsumerAdapter adapter) {
            component.getClass();
            adapter.getClass();
            int safeVendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();
            if (safeVendorApiLevel >= 9) {
                return new ExtensionWindowBackendApi9(component, adapter);
            }
            if (safeVendorApiLevel >= 6) {
                return new ExtensionWindowBackendApi6(component, adapter);
            }
            if (safeVendorApiLevel >= 2) {
                return new ExtensionWindowBackendApi2(component, adapter);
            }
            return safeVendorApiLevel == 1 ? new ExtensionWindowBackendApi1(component, adapter) : new ExtensionWindowBackendApi0();
        }

        private Companion() {
        }
    }
}
