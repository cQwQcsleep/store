package androidx.window.embedding;

import android.content.res.Configuration;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B1\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0018\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "", "parentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "parentConfiguration", "Landroid/content/res/Configuration;", "parentWindowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "overlayTag", "", "defaultOverlayAttributes", "Landroidx/window/embedding/OverlayAttributes;", "<init>", "(Landroidx/window/layout/WindowMetrics;Landroid/content/res/Configuration;Landroidx/window/layout/WindowLayoutInfo;Ljava/lang/String;Landroidx/window/embedding/OverlayAttributes;)V", "getParentWindowMetrics", "()Landroidx/window/layout/WindowMetrics;", "getParentConfiguration", "()Landroid/content/res/Configuration;", "getParentWindowLayoutInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "getOverlayTag", "()Ljava/lang/String;", "getDefaultOverlayAttributes", "()Landroidx/window/embedding/OverlayAttributes;", "toString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OverlayAttributesCalculatorParams {
    private final OverlayAttributes defaultOverlayAttributes;
    private final String overlayTag;
    private final Configuration parentConfiguration;
    private final WindowLayoutInfo parentWindowLayoutInfo;
    private final WindowMetrics parentWindowMetrics;

    public OverlayAttributesCalculatorParams(WindowMetrics windowMetrics, Configuration configuration, WindowLayoutInfo windowLayoutInfo, String str, OverlayAttributes overlayAttributes) {
        windowMetrics.getClass();
        configuration.getClass();
        windowLayoutInfo.getClass();
        str.getClass();
        overlayAttributes.getClass();
        this.parentWindowMetrics = windowMetrics;
        this.parentConfiguration = configuration;
        this.parentWindowLayoutInfo = windowLayoutInfo;
        this.overlayTag = str;
        this.defaultOverlayAttributes = overlayAttributes;
    }

    public final OverlayAttributes getDefaultOverlayAttributes() {
        return this.defaultOverlayAttributes;
    }

    public final String getOverlayTag() {
        return this.overlayTag;
    }

    public final Configuration getParentConfiguration() {
        return this.parentConfiguration;
    }

    public final WindowLayoutInfo getParentWindowLayoutInfo() {
        return this.parentWindowLayoutInfo;
    }

    public final WindowMetrics getParentWindowMetrics() {
        return this.parentWindowMetrics;
    }

    public String toString() {
        return OverlayAttributesCalculatorParams.class + ":{parentWindowMetrics=" + this.parentWindowMetrics + "parentConfiguration=" + this.parentConfiguration + "parentWindowLayoutInfo=" + this.parentWindowLayoutInfo + "overlayTag=" + this.overlayTag + "defaultOverlayAttributes=" + this.defaultOverlayAttributes;
    }
}
