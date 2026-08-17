package androidx.window.core.layout;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\nJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0003J\u0016\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003J\u0013\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020#H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Landroidx/window/core/layout/WindowSizeClass;", "", "minWidthDp", "", "minHeightDp", "<init>", "(II)V", "widthDp", "", "heightDp", "(FF)V", "getMinWidthDp", "()I", "getMinHeightDp", "windowWidthSizeClass", "Landroidx/window/core/layout/WindowWidthSizeClass;", "getWindowWidthSizeClass$annotations", "()V", "getWindowWidthSizeClass", "()Landroidx/window/core/layout/WindowWidthSizeClass;", "windowHeightSizeClass", "Landroidx/window/core/layout/WindowHeightSizeClass;", "getWindowHeightSizeClass$annotations", "getWindowHeightSizeClass", "()Landroidx/window/core/layout/WindowHeightSizeClass;", "isWidthAtLeastBreakpoint", "", "widthDpBreakpoint", "isHeightAtLeastBreakpoint", "heightDpBreakpoint", "isAtLeastBreakpoint", "equals", "other", "hashCode", "toString", "", "Companion", "window-core"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowSizeClass {
    public static final Set<WindowSizeClass> BREAKPOINTS_V1;
    public static final Set<WindowSizeClass> BREAKPOINTS_V2;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final List<Integer> HEIGHT_DP_BREAKPOINTS_V1;
    private static final List<Integer> HEIGHT_DP_BREAKPOINTS_V2;
    public static final int HEIGHT_DP_EXPANDED_LOWER_BOUND = 900;
    public static final int HEIGHT_DP_MEDIUM_LOWER_BOUND = 480;
    private static final List<Integer> WIDTH_DP_BREAKPOINTS_V1;
    private static final List<Integer> WIDTH_DP_BREAKPOINTS_V2;
    public static final int WIDTH_DP_EXPANDED_LOWER_BOUND = 840;
    public static final int WIDTH_DP_EXTRA_LARGE_LOWER_BOUND = 1600;
    public static final int WIDTH_DP_LARGE_LOWER_BOUND = 1200;
    public static final int WIDTH_DP_MEDIUM_LOWER_BOUND = 600;
    private final int minHeightDp;
    private final int minWidthDp;

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        List<Integer> listListOf = CollectionsKt.listOf(new Integer[]{0, 600, Integer.valueOf(WIDTH_DP_EXPANDED_LOWER_BOUND)});
        WIDTH_DP_BREAKPOINTS_V1 = listListOf;
        List<Integer> listPlus = CollectionsKt.plus(listListOf, CollectionsKt.listOf(new Integer[]{Integer.valueOf(WIDTH_DP_LARGE_LOWER_BOUND), Integer.valueOf(WIDTH_DP_EXTRA_LARGE_LOWER_BOUND)}));
        WIDTH_DP_BREAKPOINTS_V2 = listPlus;
        List<Integer> listListOf2 = CollectionsKt.listOf(new Integer[]{0, Integer.valueOf(HEIGHT_DP_MEDIUM_LOWER_BOUND), 900});
        HEIGHT_DP_BREAKPOINTS_V1 = listListOf2;
        HEIGHT_DP_BREAKPOINTS_V2 = listListOf2;
        BREAKPOINTS_V1 = companion.createBreakpointSet(listListOf, listListOf2);
        BREAKPOINTS_V2 = companion.createBreakpointSet(listPlus, listListOf2);
    }

    public WindowSizeClass(int i, int i2) {
        this.minWidthDp = i;
        this.minHeightDp = i2;
        if (i < 0) {
            d41.a("Expected minWidthDp to be at least 0, minWidthDp: ", i, 46);
            throw null;
        }
        if (i2 >= 0) {
            return;
        }
        d41.a("Expected minHeightDp to be at least 0, minHeightDp: ", i2, 46);
        throw null;
    }

    @Deprecated(message = "Use computeWindowSizeClass instead.", replaceWith = @ReplaceWith(expression = "BREAKPOINTS_V1.computeWindowSizeClass(widthDp = dpWidth, heightDp = dpHeight)", imports = {"androidx.window.core.layout.computeWindowSizeClass"}))
    @JvmStatic
    public static final WindowSizeClass compute(float f, float f2) {
        return INSTANCE.compute(f, f2);
    }

    @Deprecated(message = "Use either isHeightAtLeastBreakpoint or isAtLeastBreakpoint to check matching bounds.")
    public static /* synthetic */ void getWindowHeightSizeClass$annotations() {
    }

    @Deprecated(message = "Use either isWidthAtLeastBreakpoint or isAtLeastBreakpoint to check matching bounds.")
    public static /* synthetic */ void getWindowWidthSizeClass$annotations() {
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || WindowSizeClass.class != other.getClass()) {
            return false;
        }
        WindowSizeClass windowSizeClass = (WindowSizeClass) other;
        return this.minWidthDp == windowSizeClass.minWidthDp && this.minHeightDp == windowSizeClass.minHeightDp;
    }

    public final int getMinHeightDp() {
        return this.minHeightDp;
    }

    public final int getMinWidthDp() {
        return this.minWidthDp;
    }

    public final WindowHeightSizeClass getWindowHeightSizeClass() {
        return WindowHeightSizeClass.INSTANCE.compute$window_core(this.minHeightDp);
    }

    public final WindowWidthSizeClass getWindowWidthSizeClass() {
        return WindowWidthSizeClass.INSTANCE.compute$window_core(this.minWidthDp);
    }

    public int hashCode() {
        return (this.minWidthDp * 31) + this.minHeightDp;
    }

    public final boolean isAtLeastBreakpoint(int widthDpBreakpoint, int heightDpBreakpoint) {
        return isWidthAtLeastBreakpoint(widthDpBreakpoint) && isHeightAtLeastBreakpoint(heightDpBreakpoint);
    }

    public final boolean isHeightAtLeastBreakpoint(int heightDpBreakpoint) {
        return this.minHeightDp >= heightDpBreakpoint;
    }

    public final boolean isWidthAtLeastBreakpoint(int widthDpBreakpoint) {
        return this.minWidthDp >= widthDpBreakpoint;
    }

    public String toString() {
        return "WindowSizeClass(minWidthDp=" + this.minWidthDp + ", minHeightDp=" + this.minHeightDp + ')';
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0002J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/window/core/layout/WindowSizeClass$Companion;", "", "<init>", "()V", "WIDTH_DP_MEDIUM_LOWER_BOUND", "", "WIDTH_DP_EXPANDED_LOWER_BOUND", "WIDTH_DP_LARGE_LOWER_BOUND", "WIDTH_DP_EXTRA_LARGE_LOWER_BOUND", "HEIGHT_DP_MEDIUM_LOWER_BOUND", "HEIGHT_DP_EXPANDED_LOWER_BOUND", "WIDTH_DP_BREAKPOINTS_V1", "", "WIDTH_DP_BREAKPOINTS_V2", "HEIGHT_DP_BREAKPOINTS_V1", "HEIGHT_DP_BREAKPOINTS_V2", "createBreakpointSet", "", "Landroidx/window/core/layout/WindowSizeClass;", "widthBreakpoints", "heightBreakpoints", "BREAKPOINTS_V1", "BREAKPOINTS_V2", "compute", "dpWidth", "", "dpHeight", "window-core"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<WindowSizeClass> createBreakpointSet(List<Integer> widthBreakpoints, List<Integer> heightBreakpoints) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = widthBreakpoints.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                List<Integer> list = heightBreakpoints;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new WindowSizeClass(iIntValue, ((Number) it2.next()).intValue()));
                }
                CollectionsKt.addAll(arrayList, arrayList2);
            }
            return CollectionsKt.toSet(arrayList);
        }

        @Deprecated(message = "Use computeWindowSizeClass instead.", replaceWith = @ReplaceWith(expression = "BREAKPOINTS_V1.computeWindowSizeClass(widthDp = dpWidth, heightDp = dpHeight)", imports = {"androidx.window.core.layout.computeWindowSizeClass"}))
        @JvmStatic
        public final WindowSizeClass compute(float dpWidth, float dpHeight) {
            int i;
            int i2 = 0;
            if (dpWidth >= 840.0f) {
                i = WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND;
            } else {
                i = dpWidth >= 600.0f ? 600 : 0;
            }
            if (dpHeight >= 900.0f) {
                i2 = 900;
            } else if (dpHeight >= 480.0f) {
                i2 = WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND;
            }
            return new WindowSizeClass(i, i2);
        }

        private Companion() {
        }
    }

    public WindowSizeClass(float f, float f2) {
        this((int) f, (int) f2);
    }
}
