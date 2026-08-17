package androidx.compose.foundation.lazy.grid;

import android.os.Trace;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollableAreaKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.grid.LazyGridKt;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Dp;
import com.intellij.util.io.IOUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0088\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0002\u0010\u001a\u001aq\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0003¢\u0006\u0002\u0010&\u001a*\u0010'\u001a\u00020\u0001*\u00020(2\u0006\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010.\u001a\u00020/H\u0002¨\u00060"}, d2 = {"LazyGrid", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "slots", "Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyGridMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyItemsScrollBehavior", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "keepAroundItems", "Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "visibleItemsList", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyGridKt {
    /* JADX WARN: Code duplicated, block: B:100:0x011a  */
    /* JADX WARN: Code duplicated, block: B:102:0x0120  */
    /* JADX WARN: Code duplicated, block: B:103:0x0123  */
    /* JADX WARN: Code duplicated, block: B:107:0x0139  */
    /* JADX WARN: Code duplicated, block: B:111:0x0141  */
    /* JADX WARN: Code duplicated, block: B:114:0x014a  */
    /* JADX WARN: Code duplicated, block: B:116:0x0154  */
    /* JADX WARN: Code duplicated, block: B:123:0x016c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:124:0x016e  */
    /* JADX WARN: Code duplicated, block: B:125:0x0171  */
    /* JADX WARN: Code duplicated, block: B:127:0x0174  */
    /* JADX WARN: Code duplicated, block: B:130:0x0180  */
    /* JADX WARN: Code duplicated, block: B:133:0x0186  */
    /* JADX WARN: Code duplicated, block: B:135:0x0196  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:141:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:144:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:145:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:148:0x022c  */
    /* JADX WARN: Code duplicated, block: B:150:0x0230  */
    /* JADX WARN: Code duplicated, block: B:152:0x0235  */
    /* JADX WARN: Code duplicated, block: B:154:0x024e  */
    /* JADX WARN: Code duplicated, block: B:157:0x02be  */
    /* JADX WARN: Code duplicated, block: B:159:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:162:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:164:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x007c  */
    /* JADX WARN: Code duplicated, block: B:45:0x0081  */
    /* JADX WARN: Code duplicated, block: B:47:0x0085  */
    /* JADX WARN: Code duplicated, block: B:49:0x008d  */
    /* JADX WARN: Code duplicated, block: B:50:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x009a  */
    /* JADX WARN: Code duplicated, block: B:56:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:78:0x00de  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:88:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:92:0x0106  */
    /* JADX WARN: Code duplicated, block: B:94:0x010c  */
    /* JADX WARN: Code duplicated, block: B:95:0x010f  */
    /* JADX WARN: Code duplicated, block: B:97:0x0114  */
    public static final void LazyGrid(Modifier modifier, final LazyGridState lazyGridState, final LazyGridSlotsProvider lazyGridSlotsProvider, PaddingValues paddingValues, boolean z, final boolean z2, FlingBehavior flingBehavior, final boolean z3, final OverscrollEffect overscrollEffect, final Arrangement.Vertical vertical, final Arrangement.Horizontal horizontal, final Function1<? super LazyGridScope, Unit> function1, Composer composer, final int i, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        int i5;
        boolean z4;
        int i6;
        FlingBehavior flingBehavior2;
        int i7;
        int i8;
        boolean z5;
        final PaddingValues paddingValues2;
        final boolean z6;
        final Modifier modifier3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i9;
        FlingBehavior flingBehavior4;
        boolean z7;
        int i10;
        Object objRememberedValue;
        StickyItemsPlacement stickToTopPlacement;
        boolean z8;
        Orientation orientation;
        Orientation orientation2;
        Modifier modifierLazyLayoutBeyondBoundsModifier;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        Composer composerStartRestartGroup = composer.startRestartGroup(708740370);
        int i18 = i3 & 1;
        if (i18 != 0) {
            i4 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i4 = i;
        }
        if ((i & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(lazyGridState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i4 |= (i & 512) == 0 ? composerStartRestartGroup.changed(lazyGridSlotsProvider) : composerStartRestartGroup.changedInstance(lazyGridSlotsProvider) ? 256 : 128;
        }
        int i19 = i3 & 8;
        if (i19 == 0) {
            if ((i & 3072) == 0) {
                paddingValuesM923PaddingValues0680j_4 = paddingValues;
                i4 |= composerStartRestartGroup.changed(paddingValuesM923PaddingValues0680j_4) ? 2048 : 1024;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z;
                    if (composerStartRestartGroup.changed(z4)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i4 |= i6;
                }
                if ((i & 196608) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i17 = 131072;
                    } else {
                        i17 = 65536;
                    }
                    i4 |= i17;
                }
                if ((i & 1572864) == 0) {
                    flingBehavior2 = flingBehavior;
                    if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(flingBehavior2)) {
                        i16 = 524288;
                    } else {
                        i16 = IOUtil.MiB;
                    }
                    i4 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i15 = 8388608;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(overscrollEffect)) {
                        i14 = 67108864;
                    } else {
                        i14 = 33554432;
                    }
                    i4 |= i14;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(vertical)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changed(horizontal)) {
                        i12 = 4;
                    } else {
                        i12 = 2;
                    }
                    i7 = i2 | i12;
                } else {
                    i7 = i2;
                }
                if ((i2 & 48) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 32;
                    } else {
                        i11 = 16;
                    }
                    i7 |= i11;
                }
                i8 = i7;
                char c = 18;
                if ((i4 & 306783379) == 306783378 || (i8 & 19) != 18) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i18 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i19 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        }
                        boolean z9 = i5 == 0 ? z4 : false;
                        if ((i3 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i9 = i4 & (-3670017);
                        } else {
                            i9 = i4;
                            flingBehavior4 = flingBehavior2;
                        }
                        z7 = z9;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                        }
                        boolean z10 = z4;
                        c = 18;
                        z7 = z10;
                        paddingValuesM923PaddingValues0680j_4 = paddingValuesM923PaddingValues0680j_4;
                        modifier4 = modifier2;
                        i9 = i4;
                        flingBehavior4 = flingBehavior2;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(708740370, i9, i8, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:83)");
                    }
                    i10 = (i9 >> 3) & 14;
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(lazyGridState, function1, composerStartRestartGroup, (i8 & 112) | i10);
                    int i20 = i9 >> 9;
                    LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyGridSemanticState = LazySemanticsKt.rememberLazyGridSemanticState(lazyGridState, z7, composerStartRestartGroup, (i20 & 112) | i10);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
                    GraphicsContext graphicsContext = (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext());
                    if (((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue()) {
                        stickToTopPlacement = null;
                    } else {
                        stickToTopPlacement = StickyItemsPlacement.INSTANCE.getStickToTopPlacement();
                    }
                    Modifier modifier5 = modifier4;
                    int i21 = i9;
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda, lazyGridState, lazyGridSlotsProvider, paddingValuesM923PaddingValues0680j_4, z7, z2, horizontal, vertical, coroutineScope, graphicsContext, stickToTopPlacement, composerStartRestartGroup, (i9 & 524272) | ((i8 << 18) & 3670016) | ((i9 >> 6) & 29360128), 0);
                    z8 = z7;
                    PaddingValues paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    if (z2) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    orientation2 = orientation;
                    if (z3) {
                        composerStartRestartGroup.startReplaceGroup(27281635);
                        modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(lazyGridState, composerStartRestartGroup, i10), lazyGridState.getBeyondBoundsInfo(), z8, orientation2);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(27577840);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
                    }
                    FlingBehavior flingBehavior5 = flingBehavior4;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier5.then(lazyGridState.getRemeasurementModifier()).then(lazyGridState.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda, lazyLayoutSemanticStateRememberLazyGridSemanticState, orientation2, z3, z8, composerStartRestartGroup, (i20 & 57344) | (458752 & (i21 << 3))).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyGridState.getItemAnimator$foundation().getModifier()), lazyGridState, orientation2, overscrollEffect, z3, z8, flingBehavior5, lazyGridState.getInternalInteractionSource(), null, 128, null), lazyGridState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy, composerStartRestartGroup, 0, 0);
                    composerStartRestartGroup = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flingBehavior3 = flingBehavior5;
                    paddingValues2 = paddingValues3;
                    modifier3 = modifier5;
                    z6 = z8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    paddingValues2 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z4;
                    modifier3 = modifier2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jr8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyGridKt.a(modifier3, lazyGridState, lazyGridSlotsProvider, paddingValues2, z6, z2, flingBehavior3, z3, overscrollEffect, vertical, horizontal, function1, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z;
            if ((i & 196608) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i17 = 131072;
                } else {
                    i17 = 65536;
                }
                i4 |= i17;
            }
            if ((i & 1572864) == 0) {
                flingBehavior2 = flingBehavior;
                if ((i3 & 64) == 0) {
                    i16 = 524288;
                } else {
                    i16 = 524288;
                }
                i4 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i15 = 8388608;
                } else {
                    i15 = 4194304;
                }
                i4 |= i15;
            }
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(overscrollEffect)) {
                    i14 = 67108864;
                } else {
                    i14 = 33554432;
                }
                i4 |= i14;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(vertical)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i4 |= i13;
            }
            if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changed(horizontal)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                i7 = i2 | i12;
            } else {
                i7 = i2;
            }
            if ((i2 & 48) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 32;
                } else {
                    i11 = 16;
                }
                i7 |= i11;
            }
            i8 = i7;
            char c2 = 18;
            if ((i4 & 306783379) == 306783378) {
                z5 = true;
            } else {
                z5 = true;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i18 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i19 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    }
                    if (i5 == 0) {
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i9 = i4 & (-3670017);
                    } else {
                        i9 = i4;
                        flingBehavior4 = flingBehavior2;
                    }
                    z7 = z9;
                } else {
                    if (i18 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i19 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    }
                    if (i5 == 0) {
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i9 = i4 & (-3670017);
                    } else {
                        i9 = i4;
                        flingBehavior4 = flingBehavior2;
                    }
                    z7 = z9;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(708740370, i9, i8, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:83)");
                }
                i10 = (i9 >> 3) & 14;
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda2 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(lazyGridState, function1, composerStartRestartGroup, (i8 & 112) | i10);
                int i22 = i9 >> 9;
                LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyGridSemanticState2 = LazySemanticsKt.rememberLazyGridSemanticState(lazyGridState, z7, composerStartRestartGroup, (i22 & 112) | i10);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CoroutineScope coroutineScope2 = (CoroutineScope) objRememberedValue;
                GraphicsContext graphicsContext2 = (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext());
                if (((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue()) {
                    stickToTopPlacement = StickyItemsPlacement.INSTANCE.getStickToTopPlacement();
                } else {
                    stickToTopPlacement = null;
                }
                Modifier modifier6 = modifier4;
                int i23 = i9;
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy2 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda2, lazyGridState, lazyGridSlotsProvider, paddingValuesM923PaddingValues0680j_4, z7, z2, horizontal, vertical, coroutineScope2, graphicsContext2, stickToTopPlacement, composerStartRestartGroup, (i9 & 524272) | ((i8 << 18) & 3670016) | ((i9 >> 6) & 29360128), 0);
                z8 = z7;
                PaddingValues paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                if (z2) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                orientation2 = orientation;
                if (z3) {
                    composerStartRestartGroup.startReplaceGroup(27281635);
                    modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(lazyGridState, composerStartRestartGroup, i10), lazyGridState.getBeyondBoundsInfo(), z8, orientation2);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(27577840);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
                }
                FlingBehavior flingBehavior6 = flingBehavior4;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda2, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier6.then(lazyGridState.getRemeasurementModifier()).then(lazyGridState.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda2, lazyLayoutSemanticStateRememberLazyGridSemanticState2, orientation2, z3, z8, composerStartRestartGroup, (i22 & 57344) | (458752 & (i23 << 3))).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyGridState.getItemAnimator$foundation().getModifier()), lazyGridState, orientation2, overscrollEffect, z3, z8, flingBehavior6, lazyGridState.getInternalInteractionSource(), null, 128, null), lazyGridState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy2, composerStartRestartGroup, 0, 0);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flingBehavior3 = flingBehavior6;
                paddingValues2 = paddingValues4;
                modifier3 = modifier6;
                z6 = z8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                paddingValues2 = paddingValuesM923PaddingValues0680j_4;
                z6 = z4;
                modifier3 = modifier2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jr8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyGridKt.a(modifier3, lazyGridState, lazyGridSlotsProvider, paddingValues2, z6, z2, flingBehavior3, z3, overscrollEffect, vertical, horizontal, function1, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        paddingValuesM923PaddingValues0680j_4 = paddingValues;
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i & 24576) == 0) {
                z4 = z;
                if (composerStartRestartGroup.changed(z4)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            if ((i & 196608) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i17 = 131072;
                } else {
                    i17 = 65536;
                }
                i4 |= i17;
            }
            if ((i & 1572864) == 0) {
                flingBehavior2 = flingBehavior;
                if ((i3 & 64) == 0) {
                    i16 = 524288;
                } else {
                    i16 = 524288;
                }
                i4 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i15 = 8388608;
                } else {
                    i15 = 4194304;
                }
                i4 |= i15;
            }
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(overscrollEffect)) {
                    i14 = 67108864;
                } else {
                    i14 = 33554432;
                }
                i4 |= i14;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(vertical)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i4 |= i13;
            }
            if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changed(horizontal)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                i7 = i2 | i12;
            } else {
                i7 = i2;
            }
            if ((i2 & 48) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 32;
                } else {
                    i11 = 16;
                }
                i7 |= i11;
            }
            i8 = i7;
            char c3 = 18;
            if ((i4 & 306783379) == 306783378) {
                z5 = true;
            } else {
                z5 = true;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i18 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i19 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    }
                    if (i5 == 0) {
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i9 = i4 & (-3670017);
                    } else {
                        i9 = i4;
                        flingBehavior4 = flingBehavior2;
                    }
                    z7 = z9;
                } else {
                    if (i18 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i19 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    }
                    if (i5 == 0) {
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i9 = i4 & (-3670017);
                    } else {
                        i9 = i4;
                        flingBehavior4 = flingBehavior2;
                    }
                    z7 = z9;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(708740370, i9, i8, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:83)");
                }
                i10 = (i9 >> 3) & 14;
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda3 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(lazyGridState, function1, composerStartRestartGroup, (i8 & 112) | i10);
                int i24 = i9 >> 9;
                LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyGridSemanticState3 = LazySemanticsKt.rememberLazyGridSemanticState(lazyGridState, z7, composerStartRestartGroup, (i24 & 112) | i10);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CoroutineScope coroutineScope3 = (CoroutineScope) objRememberedValue;
                GraphicsContext graphicsContext3 = (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext());
                if (((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue()) {
                    stickToTopPlacement = StickyItemsPlacement.INSTANCE.getStickToTopPlacement();
                } else {
                    stickToTopPlacement = null;
                }
                Modifier modifier7 = modifier4;
                int i25 = i9;
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy3 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda3, lazyGridState, lazyGridSlotsProvider, paddingValuesM923PaddingValues0680j_4, z7, z2, horizontal, vertical, coroutineScope3, graphicsContext3, stickToTopPlacement, composerStartRestartGroup, (i9 & 524272) | ((i8 << 18) & 3670016) | ((i9 >> 6) & 29360128), 0);
                z8 = z7;
                PaddingValues paddingValues5 = paddingValuesM923PaddingValues0680j_4;
                if (z2) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                orientation2 = orientation;
                if (z3) {
                    composerStartRestartGroup.startReplaceGroup(27281635);
                    modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(lazyGridState, composerStartRestartGroup, i10), lazyGridState.getBeyondBoundsInfo(), z8, orientation2);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(27577840);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
                }
                FlingBehavior flingBehavior7 = flingBehavior4;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda3, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier7.then(lazyGridState.getRemeasurementModifier()).then(lazyGridState.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda3, lazyLayoutSemanticStateRememberLazyGridSemanticState3, orientation2, z3, z8, composerStartRestartGroup, (i24 & 57344) | (458752 & (i25 << 3))).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyGridState.getItemAnimator$foundation().getModifier()), lazyGridState, orientation2, overscrollEffect, z3, z8, flingBehavior7, lazyGridState.getInternalInteractionSource(), null, 128, null), lazyGridState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy3, composerStartRestartGroup, 0, 0);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flingBehavior3 = flingBehavior7;
                paddingValues2 = paddingValues5;
                modifier3 = modifier7;
                z6 = z8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                paddingValues2 = paddingValuesM923PaddingValues0680j_4;
                z6 = z4;
                modifier3 = modifier2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jr8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyGridKt.a(modifier3, lazyGridState, lazyGridSlotsProvider, paddingValues2, z6, z2, flingBehavior3, z3, overscrollEffect, vertical, horizontal, function1, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        z4 = z;
        if ((i & 196608) == 0) {
            if (composerStartRestartGroup.changed(z2)) {
                i17 = 131072;
            } else {
                i17 = 65536;
            }
            i4 |= i17;
        }
        if ((i & 1572864) == 0) {
            flingBehavior2 = flingBehavior;
            if ((i3 & 64) == 0) {
                i16 = 524288;
            } else {
                i16 = 524288;
            }
            i4 |= i16;
        } else {
            flingBehavior2 = flingBehavior;
        }
        if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(z3)) {
                i15 = 8388608;
            } else {
                i15 = 4194304;
            }
            i4 |= i15;
        }
        if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changed(overscrollEffect)) {
                i14 = 67108864;
            } else {
                i14 = 33554432;
            }
            i4 |= i14;
        }
        if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changed(vertical)) {
                i13 = 536870912;
            } else {
                i13 = 268435456;
            }
            i4 |= i13;
        }
        if ((i2 & 6) == 0) {
            if (composerStartRestartGroup.changed(horizontal)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            i7 = i2 | i12;
        } else {
            i7 = i2;
        }
        if ((i2 & 48) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i11 = 32;
            } else {
                i11 = 16;
            }
            i7 |= i11;
        }
        i8 = i7;
        char c4 = 18;
        if ((i4 & 306783379) == 306783378) {
            z5 = true;
        } else {
            z5 = true;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i18 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i19 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                }
                if (i5 == 0) {
                }
                if ((i3 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i9 = i4 & (-3670017);
                } else {
                    i9 = i4;
                    flingBehavior4 = flingBehavior2;
                }
                z7 = z9;
            } else {
                if (i18 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i19 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                }
                if (i5 == 0) {
                }
                if ((i3 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i9 = i4 & (-3670017);
                } else {
                    i9 = i4;
                    flingBehavior4 = flingBehavior2;
                }
                z7 = z9;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(708740370, i9, i8, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:83)");
            }
            i10 = (i9 >> 3) & 14;
            Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda4 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(lazyGridState, function1, composerStartRestartGroup, (i8 & 112) | i10);
            int i26 = i9 >> 9;
            LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyGridSemanticState4 = LazySemanticsKt.rememberLazyGridSemanticState(lazyGridState, z7, composerStartRestartGroup, (i26 & 112) | i10);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CoroutineScope coroutineScope4 = (CoroutineScope) objRememberedValue;
            GraphicsContext graphicsContext4 = (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext());
            if (((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue()) {
                stickToTopPlacement = StickyItemsPlacement.INSTANCE.getStickToTopPlacement();
            } else {
                stickToTopPlacement = null;
            }
            Modifier modifier8 = modifier4;
            int i27 = i9;
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy4 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda4, lazyGridState, lazyGridSlotsProvider, paddingValuesM923PaddingValues0680j_4, z7, z2, horizontal, vertical, coroutineScope4, graphicsContext4, stickToTopPlacement, composerStartRestartGroup, (i9 & 524272) | ((i8 << 18) & 3670016) | ((i9 >> 6) & 29360128), 0);
            z8 = z7;
            PaddingValues paddingValues6 = paddingValuesM923PaddingValues0680j_4;
            if (z2) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            orientation2 = orientation;
            if (z3) {
                composerStartRestartGroup.startReplaceGroup(27281635);
                modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(lazyGridState, composerStartRestartGroup, i10), lazyGridState.getBeyondBoundsInfo(), z8, orientation2);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(27577840);
                composerStartRestartGroup.endReplaceGroup();
                modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
            }
            FlingBehavior flingBehavior8 = flingBehavior4;
            LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda4, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier8.then(lazyGridState.getRemeasurementModifier()).then(lazyGridState.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda4, lazyLayoutSemanticStateRememberLazyGridSemanticState4, orientation2, z3, z8, composerStartRestartGroup, (i26 & 57344) | (458752 & (i27 << 3))).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyGridState.getItemAnimator$foundation().getModifier()), lazyGridState, orientation2, overscrollEffect, z3, z8, flingBehavior8, lazyGridState.getInternalInteractionSource(), null, 128, null), lazyGridState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyGridMeasurePolicy4, composerStartRestartGroup, 0, 0);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            flingBehavior3 = flingBehavior8;
            paddingValues2 = paddingValues6;
            modifier3 = modifier8;
            z6 = z8;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            paddingValues2 = paddingValuesM923PaddingValues0680j_4;
            z6 = z4;
            modifier3 = modifier2;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jr8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyGridKt.a(modifier3, lazyGridState, lazyGridSlotsProvider, paddingValues2, z6, z2, flingBehavior3, z3, overscrollEffect, vertical, horizontal, function1, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Modifier modifier, LazyGridState lazyGridState, LazyGridSlotsProvider lazyGridSlotsProvider, PaddingValues paddingValues, boolean z, boolean z2, FlingBehavior flingBehavior, boolean z3, OverscrollEffect overscrollEffect, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Function1 function1, int i, int i2, int i3, Composer composer, int i4) {
        LazyGrid(modifier, lazyGridState, lazyGridSlotsProvider, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, vertical, horizontal, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void keepAroundItems(CacheWindowLogic cacheWindowLogic, Orientation orientation, List<LazyGridMeasuredItem> list, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider) {
        Trace.beginSection("compose:lazy:cache_window:keepAroundItems");
        try {
            if (cacheWindowLogic.hasValidBounds() && !list.isEmpty()) {
                int iLineIndex = LazyGridItemInfoKt.lineIndex((LazyGridItemInfo) CollectionsKt.first(list), orientation);
                int iLineIndex2 = LazyGridItemInfoKt.lineIndex((LazyGridItemInfo) CollectionsKt.last(list), orientation);
                for (int prefetchWindowStartLine$foundation = cacheWindowLogic.getPrefetchWindowStartLine(); prefetchWindowStartLine$foundation < iLineIndex; prefetchWindowStartLine$foundation++) {
                    lazyGridMeasuredLineProvider.keepAround(prefetchWindowStartLine$foundation);
                }
                int i = iLineIndex2 + 1;
                int prefetchWindowEndLine$foundation = cacheWindowLogic.getPrefetchWindowEndLine();
                if (i <= prefetchWindowEndLine$foundation) {
                    while (true) {
                        lazyGridMeasuredLineProvider.keepAround(i);
                        if (i == prefetchWindowEndLine$foundation) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x008f A[PHI: r3
      0x008f: PHI (r3v23 boolean) = (r3v21 boolean), (r3v24 boolean) binds: [B:44:0x008d, B:40:0x0087] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:66:0x00da  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f4  */
    private static final LazyLayoutMeasurePolicy rememberLazyGridMeasurePolicy(Function0<? extends LazyGridItemProvider> function0, LazyGridState lazyGridState, LazyGridSlotsProvider lazyGridSlotsProvider, PaddingValues paddingValues, boolean z, boolean z2, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Composer composer, int i, int i2) {
        boolean z3;
        boolean z4;
        boolean zChanged;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1030995717, i, i2, "androidx.compose.foundation.lazy.grid.rememberLazyGridMeasurePolicy (LazyGrid.kt:179)");
        }
        boolean z5 = ((((i & 112) ^ 48) > 32 && composer.changed(lazyGridState)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(lazyGridSlotsProvider)) || (i & 384) == 256) | ((((i & 7168) ^ 3072) > 2048 && composer.changed(paddingValues)) || (i & 3072) == 2048) | ((((57344 & i) ^ 24576) > 16384 && composer.changed(z)) || (i & 24576) == 16384);
        if (((458752 & i) ^ 196608) > 131072) {
            z3 = z2;
            if (composer.changed(z3)) {
                z4 = true;
            }
            zChanged = z5 | z4 | ((((3670016 & i) ^ 1572864) <= 1048576 && composer.changed(horizontal)) || (i & 1572864) == 1048576) | ((((29360128 & i) ^ 12582912) <= 8388608 && composer.changed(vertical)) || (i & 12582912) == 8388608) | composer.changed(graphicsContext);
            objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                LazyGridKt$rememberLazyGridMeasurePolicy$1$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1 = new LazyGridKt$rememberLazyGridMeasurePolicy$1$1(lazyGridState, z3, paddingValues, z, function0, lazyGridSlotsProvider, vertical, horizontal, coroutineScope, graphicsContext, stickyItemsPlacement);
                composer.updateRememberedValue(lazyGridKt$rememberLazyGridMeasurePolicy$1$1);
                objRememberedValue = lazyGridKt$rememberLazyGridMeasurePolicy$1$1;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy;
        }
        z3 = z2;
        if ((196608 & i) == 131072) {
            z4 = true;
        } else {
            z4 = false;
        }
        zChanged = z5 | z4 | ((((3670016 & i) ^ 1572864) <= 1048576 && composer.changed(horizontal)) || (i & 1572864) == 1048576) | ((((29360128 & i) ^ 12582912) <= 8388608 && composer.changed(vertical)) || (i & 12582912) == 8388608) | composer.changed(graphicsContext);
        objRememberedValue = composer.rememberedValue();
        if (zChanged) {
            LazyGridKt$rememberLazyGridMeasurePolicy$1$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$2 = new LazyGridKt$rememberLazyGridMeasurePolicy$1$1(lazyGridState, z3, paddingValues, z, function0, lazyGridSlotsProvider, vertical, horizontal, coroutineScope, graphicsContext, stickyItemsPlacement);
            composer.updateRememberedValue(lazyGridKt$rememberLazyGridMeasurePolicy$1$2);
            objRememberedValue = lazyGridKt$rememberLazyGridMeasurePolicy$1$2;
        } else {
            LazyGridKt$rememberLazyGridMeasurePolicy$1$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$3 = new LazyGridKt$rememberLazyGridMeasurePolicy$1$1(lazyGridState, z3, paddingValues, z, function0, lazyGridSlotsProvider, vertical, horizontal, coroutineScope, graphicsContext, stickyItemsPlacement);
            composer.updateRememberedValue(lazyGridKt$rememberLazyGridMeasurePolicy$1$3);
            objRememberedValue = lazyGridKt$rememberLazyGridMeasurePolicy$1$3;
        }
        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2 = (LazyLayoutMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lazyLayoutMeasurePolicy2;
    }
}
