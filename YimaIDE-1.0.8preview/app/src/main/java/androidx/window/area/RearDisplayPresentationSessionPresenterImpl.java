package androidx.window.area;

import android.content.Context;
import android.view.View;
import android.view.Window;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.area.utils.PresentationWindowCompatUtils;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import androidx.window.extensions.area.WindowAreaComponent;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Landroidx/window/area/RearDisplayPresentationSessionPresenterImpl;", "Landroidx/window/area/WindowAreaSessionPresenter;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "presentation", "Landroidx/window/extensions/area/ExtensionWindowAreaPresentation;", "vendorApiLevel", "", "<init>", "(Landroidx/window/extensions/area/WindowAreaComponent;Landroidx/window/extensions/area/ExtensionWindowAreaPresentation;I)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "window", "Landroid/view/Window;", "getWindow", "()Landroid/view/Window;", "setContentView", "", "view", "Landroid/view/View;", "close", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RearDisplayPresentationSessionPresenterImpl implements WindowAreaSessionPresenter {
    private final Context context;
    private final ExtensionWindowAreaPresentation presentation;
    private final Window window;
    private final WindowAreaComponent windowAreaComponent;

    public RearDisplayPresentationSessionPresenterImpl(WindowAreaComponent windowAreaComponent, ExtensionWindowAreaPresentation extensionWindowAreaPresentation, int i) {
        windowAreaComponent.getClass();
        extensionWindowAreaPresentation.getClass();
        this.windowAreaComponent = windowAreaComponent;
        this.presentation = extensionWindowAreaPresentation;
        Context presentationContext = extensionWindowAreaPresentation.getPresentationContext();
        presentationContext.getClass();
        this.context = presentationContext;
        this.window = i >= 4 ? extensionWindowAreaPresentation.getWindow() : PresentationWindowCompatUtils.INSTANCE.getWindowBeforeVendorApiLevel4(extensionWindowAreaPresentation);
    }

    @Override // androidx.window.area.WindowAreaSession
    public void close() {
        this.windowAreaComponent.endRearDisplayPresentationSession();
    }

    @Override // androidx.window.area.WindowAreaSessionPresenter
    public Context getContext() {
        return this.context;
    }

    @Override // androidx.window.area.WindowAreaSessionPresenter
    public Window getWindow() {
        return this.window;
    }

    @Override // androidx.window.area.WindowAreaSessionPresenter
    public void setContentView(View view) {
        view.getClass();
        this.presentation.setPresentationView(view);
    }
}
