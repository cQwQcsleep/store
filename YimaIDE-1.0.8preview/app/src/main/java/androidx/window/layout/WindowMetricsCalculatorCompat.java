package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.WindowInsetsCompat;
import androidx.window.layout.util.DensityCompatHelper;
import androidx.window.layout.util.WindowMetricsCompatHelper;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculatorCompat;", "Landroidx/window/layout/WindowMetricsCalculator;", "densityCompatHelper", "Landroidx/window/layout/util/DensityCompatHelper;", "<init>", "(Landroidx/window/layout/util/DensityCompatHelper;)V", "computeCurrentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "computeMaximumWindowMetrics", "insetsTypeMasks", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getInsetsTypeMasks$window_release", "()Ljava/util/ArrayList;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    private final DensityCompatHelper densityCompatHelper;
    private final ArrayList<Integer> insetsTypeMasks;

    public WindowMetricsCalculatorCompat(DensityCompatHelper densityCompatHelper) {
        densityCompatHelper.getClass();
        this.densityCompatHelper = densityCompatHelper;
        this.insetsTypeMasks = CollectionsKt.arrayListOf(new Integer[]{Integer.valueOf(WindowInsetsCompat.Type.statusBars()), Integer.valueOf(WindowInsetsCompat.Type.navigationBars()), Integer.valueOf(WindowInsetsCompat.Type.captionBar()), Integer.valueOf(WindowInsetsCompat.Type.ime()), Integer.valueOf(WindowInsetsCompat.Type.systemGestures()), Integer.valueOf(WindowInsetsCompat.Type.mandatorySystemGestures()), Integer.valueOf(WindowInsetsCompat.Type.tappableElement()), Integer.valueOf(WindowInsetsCompat.Type.displayCutout())});
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeCurrentWindowMetrics(Context context) {
        context.getClass();
        return WindowMetricsCompatHelper.INSTANCE.getInstance().currentWindowMetrics(context, this.densityCompatHelper);
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeMaximumWindowMetrics(Activity activity) {
        activity.getClass();
        return WindowMetricsCompatHelper.INSTANCE.getInstance().maximumWindowMetrics(activity, this.densityCompatHelper);
    }

    public final ArrayList<Integer> getInsetsTypeMasks$window_release() {
        return this.insetsTypeMasks;
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeCurrentWindowMetrics(Activity activity) {
        activity.getClass();
        return WindowMetricsCompatHelper.INSTANCE.getInstance().currentWindowMetrics(activity, this.densityCompatHelper);
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeMaximumWindowMetrics(Context context) {
        context.getClass();
        return WindowMetricsCompatHelper.INSTANCE.getInstance().maximumWindowMetrics(context, this.densityCompatHelper);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public WindowMetricsCalculatorCompat() {
        DensityCompatHelper densityCompatHelper = null;
        this(densityCompatHelper, 1, densityCompatHelper);
    }

    public /* synthetic */ WindowMetricsCalculatorCompat(DensityCompatHelper densityCompatHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DensityCompatHelper.INSTANCE.getInstance() : densityCompatHelper);
    }
}
