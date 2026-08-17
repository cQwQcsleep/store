package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsSizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\"+\u0010\u0004\u001a\u001f\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000\"+\u0010\u000b\u001a\u001f\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000\"%\u0010\r\u001a\u0019\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000\"%\u0010\u0010\u001a\u0019\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"windowInsetsStartWidth", "Landroidx/compose/ui/Modifier;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "startCalc", "Lkotlin/Function3;", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/Density;", "", "Lkotlin/ExtensionFunctionType;", "windowInsetsEndWidth", "endCalc", "windowInsetsTopHeight", "topCalc", "Lkotlin/Function2;", "windowInsetsBottomHeight", "bottomCalc", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WindowInsetsSizeKt {
    private static final Function3<WindowInsets, LayoutDirection, Density, Integer> startCalc = new Function3() { // from class: hof
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return Integer.valueOf(WindowInsetsSizeKt.d((WindowInsets) obj, (LayoutDirection) obj2, (Density) obj3));
        }
    };
    private static final Function3<WindowInsets, LayoutDirection, Density, Integer> endCalc = new Function3() { // from class: iof
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return Integer.valueOf(WindowInsetsSizeKt.a((WindowInsets) obj, (LayoutDirection) obj2, (Density) obj3));
        }
    };
    private static final Function2<WindowInsets, Density, Integer> topCalc = new Function2() { // from class: jof
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(WindowInsetsSizeKt.c((WindowInsets) obj, (Density) obj2));
        }
    };
    private static final Function2<WindowInsets, Density, Integer> bottomCalc = new Function2() { // from class: kof
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(WindowInsetsSizeKt.b((WindowInsets) obj, (Density) obj2));
        }
    };

    public static int a(WindowInsets windowInsets, LayoutDirection layoutDirection, Density density) {
        return layoutDirection == LayoutDirection.Rtl ? windowInsets.getLeft(density, layoutDirection) : windowInsets.getRight(density, layoutDirection);
    }

    public static int b(WindowInsets windowInsets, Density density) {
        return windowInsets.getBottom(density);
    }

    public static int c(WindowInsets windowInsets, Density density) {
        return windowInsets.getTop(density);
    }

    public static int d(WindowInsets windowInsets, LayoutDirection layoutDirection, Density density) {
        return layoutDirection == LayoutDirection.Ltr ? windowInsets.getLeft(density, layoutDirection) : windowInsets.getRight(density, layoutDirection);
    }

    public static final Modifier windowInsetsBottomHeight(Modifier modifier, final WindowInsets windowInsets) {
        return modifier.then(new DerivedHeightModifierElement(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsBottomHeight$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("insetsBottomHeight");
                inspectorInfo.getProperties().set("insets", windowInsets);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), bottomCalc));
    }

    public static final Modifier windowInsetsEndWidth(Modifier modifier, final WindowInsets windowInsets) {
        return modifier.then(new DerivedWidthModifierElement(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsEndWidth$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("insetsEndWidth");
                inspectorInfo.getProperties().set("insets", windowInsets);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), endCalc));
    }

    public static final Modifier windowInsetsStartWidth(Modifier modifier, final WindowInsets windowInsets) {
        return modifier.then(new DerivedWidthModifierElement(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsStartWidth$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("insetsStartWidth");
                inspectorInfo.getProperties().set("insets", windowInsets);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), startCalc));
    }

    public static final Modifier windowInsetsTopHeight(Modifier modifier, final WindowInsets windowInsets) {
        return modifier.then(new DerivedHeightModifierElement(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsTopHeight$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("insetsTopHeight");
                inspectorInfo.getProperties().set("insets", windowInsets);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), topCalc));
    }
}
