package androidx.window.layout.adapter.extensions;

import android.content.Context;
import android.graphics.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.Bounds;
import androidx.window.extensions.layout.DisplayFoldFeature;
import androidx.window.extensions.layout.SupportedWindowFeatures;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.SupportedPosture;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculatorCompat;
import androidx.window.layout.util.DensityCompatHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u001d\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\nJ\u001d\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\nJ\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\nJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionsWindowLayoutInfoAdapter;", "", "<init>", "()V", "translate", "Landroidx/window/layout/FoldingFeature;", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "oemFeature", "Landroidx/window/extensions/layout/FoldingFeature;", "translate$window_release", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "info", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "", "Landroidx/window/layout/SupportedPosture;", "features", "Landroidx/window/extensions/layout/SupportedWindowFeatures;", "validBounds", "", "bounds", "Landroidx/window/core/Bounds;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ExtensionsWindowLayoutInfoAdapter {
    public static final ExtensionsWindowLayoutInfoAdapter INSTANCE = new ExtensionsWindowLayoutInfoAdapter();

    private ExtensionsWindowLayoutInfoAdapter() {
    }

    private final boolean validBounds(WindowMetrics windowMetrics, Bounds bounds) {
        Rect bounds2 = windowMetrics.getBounds();
        if (bounds.isZero()) {
            return false;
        }
        if (bounds.getWidth() != bounds2.width() && bounds.getHeight() != bounds2.height()) {
            return false;
        }
        if (bounds.getWidth() >= bounds2.width() || bounds.getHeight() >= bounds2.height()) {
            return (bounds.getWidth() == bounds2.width() && bounds.getHeight() == bounds2.height()) ? false : true;
        }
        return false;
    }

    public final FoldingFeature translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.FoldingFeature oemFeature) {
        HardwareFoldingFeature.Type fold;
        FoldingFeature.State state;
        windowMetrics.getClass();
        oemFeature.getClass();
        int type = oemFeature.getType();
        if (type == 1) {
            fold = HardwareFoldingFeature.Type.INSTANCE.getFOLD();
        } else {
            if (type != 2) {
                return null;
            }
            fold = HardwareFoldingFeature.Type.INSTANCE.getHINGE();
        }
        int state2 = oemFeature.getState();
        if (state2 == 1) {
            state = FoldingFeature.State.FLAT;
        } else {
            if (state2 != 2) {
                return null;
            }
            state = FoldingFeature.State.HALF_OPENED;
        }
        Rect bounds = oemFeature.getBounds();
        bounds.getClass();
        if (!validBounds(windowMetrics, new Bounds(bounds))) {
            return null;
        }
        Rect bounds2 = oemFeature.getBounds();
        bounds2.getClass();
        return new HardwareFoldingFeature(new Bounds(bounds2), fold, state);
    }

    public final WindowLayoutInfo translate$window_release(Context context, androidx.window.extensions.layout.WindowLayoutInfo info) {
        context.getClass();
        info.getClass();
        DensityCompatHelper densityCompatHelper = null;
        return translate$window_release(new WindowMetricsCalculatorCompat(densityCompatHelper, 1, densityCompatHelper).computeCurrentWindowMetrics(context), info);
    }

    public final WindowLayoutInfo translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.WindowLayoutInfo info) {
        windowMetrics.getClass();
        info.getClass();
        List<androidx.window.extensions.layout.FoldingFeature> displayFeatures = info.getDisplayFeatures();
        displayFeatures.getClass();
        ArrayList arrayList = new ArrayList();
        for (androidx.window.extensions.layout.FoldingFeature foldingFeature : displayFeatures) {
            FoldingFeature foldingFeatureTranslate$window_release = foldingFeature instanceof androidx.window.extensions.layout.FoldingFeature ? INSTANCE.translate$window_release(windowMetrics, foldingFeature) : null;
            if (foldingFeatureTranslate$window_release != null) {
                arrayList.add(foldingFeatureTranslate$window_release);
            }
        }
        return new WindowLayoutInfo(arrayList);
    }

    public final List<SupportedPosture> translate$window_release(SupportedWindowFeatures features) {
        features.getClass();
        List displayFoldFeatures = features.getDisplayFoldFeatures();
        displayFoldFeatures.getClass();
        List list = displayFoldFeatures;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((DisplayFoldFeature) it.next()).hasProperties(new int[]{1})) {
                    return CollectionsKt.listOf(SupportedPosture.TABLETOP);
                }
            }
        }
        return CollectionsKt.emptyList();
    }
}
