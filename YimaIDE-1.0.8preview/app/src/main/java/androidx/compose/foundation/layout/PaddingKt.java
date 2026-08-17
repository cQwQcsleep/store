package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a;\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0015\u0010\b\u001a\u0019\u0010\u0016\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u0019\u0010\u001a\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a#\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a7\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b \u0010!¨\u0006\""}, d2 = {"padding", "Landroidx/compose/ui/Modifier;", "start", "Landroidx/compose/ui/unit/Dp;", "top", "end", "bottom", "padding-qDBjuR0", "(Landroidx/compose/ui/Modifier;FFFF)Landroidx/compose/ui/Modifier;", "horizontal", "vertical", "padding-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "all", "padding-3ABfNKs", "(Landroidx/compose/ui/Modifier;F)Landroidx/compose/ui/Modifier;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "absolutePadding", "left", "right", "absolutePadding-qDBjuR0", "calculateStartPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateEndPadding", "PaddingValues", "PaddingValues-0680j_4", "(F)Landroidx/compose/foundation/layout/PaddingValues;", "PaddingValues-YgX7TsA", "(FF)Landroidx/compose/foundation/layout/PaddingValues;", "PaddingValues-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PaddingKt {
    /* JADX INFO: renamed from: PaddingValues-0680j_4, reason: not valid java name */
    public static final PaddingValues m923PaddingValues0680j_4(float f) {
        return new PaddingValuesImpl(f, f, f, f, null);
    }

    /* JADX INFO: renamed from: PaddingValues-YgX7TsA, reason: not valid java name */
    public static final PaddingValues m924PaddingValuesYgX7TsA(float f, float f2) {
        return new PaddingValuesImpl(f, f2, f, f2, null);
    }

    /* JADX INFO: renamed from: PaddingValues-YgX7TsA$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m925PaddingValuesYgX7TsA$default(float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.constructor-impl(0.0f);
        }
        if ((i & 2) != 0) {
            f2 = Dp.constructor-impl(0.0f);
        }
        return m924PaddingValuesYgX7TsA(f, f2);
    }

    /* JADX INFO: renamed from: PaddingValues-a9UjIt4, reason: not valid java name */
    public static final PaddingValues m926PaddingValuesa9UjIt4(float f, float f2, float f3, float f4) {
        return new PaddingValuesImpl(f, f2, f3, f4, null);
    }

    /* JADX INFO: renamed from: PaddingValues-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m927PaddingValuesa9UjIt4$default(float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.constructor-impl(0.0f);
        }
        if ((i & 2) != 0) {
            f2 = Dp.constructor-impl(0.0f);
        }
        if ((i & 4) != 0) {
            f3 = Dp.constructor-impl(0.0f);
        }
        if ((i & 8) != 0) {
            f4 = Dp.constructor-impl(0.0f);
        }
        return m926PaddingValuesa9UjIt4(f, f2, f3, f4);
    }

    public static Unit a(float f, float f2, InspectorInfo inspectorInfo) {
        inspectorInfo.setName("padding");
        inspectorInfo.getProperties().set("horizontal", Dp.box-impl(f));
        inspectorInfo.getProperties().set("vertical", Dp.box-impl(f2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: absolutePadding-qDBjuR0, reason: not valid java name */
    public static final Modifier m928absolutePaddingqDBjuR0(Modifier modifier, final float f, final float f2, final float f3, final float f4) {
        return modifier.then(new PaddingElement(f, f2, f3, f4, false, new Function1() { // from class: nwa
            public final Object invoke(Object obj) {
                return PaddingKt.c(f, f2, f3, f4, (InspectorInfo) obj);
            }
        }, null));
    }

    /* JADX INFO: renamed from: absolutePadding-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m929absolutePaddingqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.constructor-impl(0.0f);
        }
        if ((i & 2) != 0) {
            f2 = Dp.constructor-impl(0.0f);
        }
        if ((i & 4) != 0) {
            f3 = Dp.constructor-impl(0.0f);
        }
        if ((i & 8) != 0) {
            f4 = Dp.constructor-impl(0.0f);
        }
        return m928absolutePaddingqDBjuR0(modifier, f, f2, f3, f4);
    }

    public static Unit b(float f, float f2, float f3, float f4, InspectorInfo inspectorInfo) {
        inspectorInfo.setName("padding");
        inspectorInfo.getProperties().set("start", Dp.box-impl(f));
        inspectorInfo.getProperties().set("top", Dp.box-impl(f2));
        inspectorInfo.getProperties().set("end", Dp.box-impl(f3));
        inspectorInfo.getProperties().set("bottom", Dp.box-impl(f4));
        return Unit.INSTANCE;
    }

    public static Unit c(float f, float f2, float f3, float f4, InspectorInfo inspectorInfo) {
        inspectorInfo.setName("absolutePadding");
        inspectorInfo.getProperties().set("left", Dp.box-impl(f));
        inspectorInfo.getProperties().set("top", Dp.box-impl(f2));
        inspectorInfo.getProperties().set("right", Dp.box-impl(f3));
        inspectorInfo.getProperties().set("bottom", Dp.box-impl(f4));
        return Unit.INSTANCE;
    }

    public static final float calculateEndPadding(PaddingValues paddingValues, LayoutDirection layoutDirection) {
        return layoutDirection == LayoutDirection.Ltr ? paddingValues.mo870calculateRightPaddingu2uoSUM(layoutDirection) : paddingValues.mo869calculateLeftPaddingu2uoSUM(layoutDirection);
    }

    public static final float calculateStartPadding(PaddingValues paddingValues, LayoutDirection layoutDirection) {
        return layoutDirection == LayoutDirection.Ltr ? paddingValues.mo869calculateLeftPaddingu2uoSUM(layoutDirection) : paddingValues.mo870calculateRightPaddingu2uoSUM(layoutDirection);
    }

    public static Unit d(float f, InspectorInfo inspectorInfo) {
        inspectorInfo.setName("padding");
        inspectorInfo.setValue(Dp.box-impl(f));
        return Unit.INSTANCE;
    }

    public static Unit e(PaddingValues paddingValues, InspectorInfo inspectorInfo) {
        inspectorInfo.setName("padding");
        inspectorInfo.getProperties().set("paddingValues", paddingValues);
        return Unit.INSTANCE;
    }

    public static final Modifier padding(Modifier modifier, final PaddingValues paddingValues) {
        return modifier.then(new PaddingValuesElement(paddingValues, new Function1() { // from class: mwa
            public final Object invoke(Object obj) {
                return PaddingKt.e(paddingValues, (InspectorInfo) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: padding-3ABfNKs, reason: not valid java name */
    public static final Modifier m930padding3ABfNKs(Modifier modifier, final float f) {
        return modifier.then(new PaddingElement(f, f, f, f, true, new Function1() { // from class: pwa
            public final Object invoke(Object obj) {
                return PaddingKt.d(f, (InspectorInfo) obj);
            }
        }, null));
    }

    /* JADX INFO: renamed from: padding-VpY3zN4, reason: not valid java name */
    public static final Modifier m931paddingVpY3zN4(Modifier modifier, final float f, final float f2) {
        return modifier.then(new PaddingElement(f, f2, f, f2, true, new Function1() { // from class: owa
            public final Object invoke(Object obj) {
                return PaddingKt.a(f, f2, (InspectorInfo) obj);
            }
        }, null));
    }

    /* JADX INFO: renamed from: padding-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m932paddingVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.constructor-impl(0.0f);
        }
        if ((i & 2) != 0) {
            f2 = Dp.constructor-impl(0.0f);
        }
        return m931paddingVpY3zN4(modifier, f, f2);
    }

    /* JADX INFO: renamed from: padding-qDBjuR0, reason: not valid java name */
    public static final Modifier m933paddingqDBjuR0(Modifier modifier, final float f, final float f2, final float f3, final float f4) {
        return modifier.then(new PaddingElement(f, f2, f3, f4, true, new Function1() { // from class: lwa
            public final Object invoke(Object obj) {
                return PaddingKt.b(f, f2, f3, f4, (InspectorInfo) obj);
            }
        }, null));
    }

    /* JADX INFO: renamed from: padding-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m934paddingqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.constructor-impl(0.0f);
        }
        if ((i & 2) != 0) {
            f2 = Dp.constructor-impl(0.0f);
        }
        if ((i & 4) != 0) {
            f3 = Dp.constructor-impl(0.0f);
        }
        if ((i & 8) != 0) {
            f4 = Dp.constructor-impl(0.0f);
        }
        return m933paddingqDBjuR0(modifier, f, f2, f3, f4);
    }
}
