package androidx.window.layout.adapter.extensions;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.SupportedWindowFeatures;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.SupportedPosture;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi6;", "Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi2;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "adapter", "Landroidx/window/core/ConsumerAdapter;", "<init>", "(Landroidx/window/extensions/layout/WindowLayoutComponent;Landroidx/window/core/ConsumerAdapter;)V", "supportedPostures", "", "Landroidx/window/layout/SupportedPosture;", "getSupportedPostures", "()Ljava/util/List;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class ExtensionWindowBackendApi6 extends ExtensionWindowBackendApi2 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtensionWindowBackendApi6(WindowLayoutComponent windowLayoutComponent, ConsumerAdapter consumerAdapter) {
        super(windowLayoutComponent, consumerAdapter);
        windowLayoutComponent.getClass();
        consumerAdapter.getClass();
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0, androidx.window.layout.adapter.WindowBackend
    public List<SupportedPosture> getSupportedPostures() {
        ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter = ExtensionsWindowLayoutInfoAdapter.INSTANCE;
        SupportedWindowFeatures supportedWindowFeatures = getComponent().getSupportedWindowFeatures();
        supportedWindowFeatures.getClass();
        return extensionsWindowLayoutInfoAdapter.translate$window_release(supportedWindowFeatures);
    }
}
