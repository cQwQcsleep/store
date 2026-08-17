package androidx.compose.foundation.lazy;

import android.os.Trace;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollableAreaKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyListKt;
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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.platform.CompositionLocalsKt;
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
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a¢\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b¢\u0006\u0002\b\u001dH\u0001¢\u0006\u0002\u0010\u001e\u001a\u0085\u0001\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0003¢\u0006\u0002\u0010*\u001a\"\u0010+\u001a\u00020\u0001*\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u000201H\u0002¨\u00062"}, d2 = {"LazyList", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "beyondBoundsItemCount", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;ILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyListMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyItemsPlacement", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "keepAroundItems", "Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "visibleItemsList", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyListKt {
    /* JADX WARN: Code duplicated, block: B:117:0x0162  */
    /* JADX WARN: Code duplicated, block: B:119:0x0168  */
    /* JADX WARN: Code duplicated, block: B:127:0x0184  */
    /* JADX WARN: Code duplicated, block: B:130:0x018d  */
    /* JADX WARN: Code duplicated, block: B:139:0x01b0 A[PHI: r4 r7 r8 r9 r11
      0x01b0: PHI (r4v19 int) = (r4v11 int), (r4v22 int) binds: [B:154:0x01d8, B:138:0x01a8] A[DONT_GENERATE, DONT_INLINE]
      0x01b0: PHI (r7v11 int) = (r7v5 int), (r7v12 int) binds: [B:154:0x01d8, B:138:0x01a8] A[DONT_GENERATE, DONT_INLINE]
      0x01b0: PHI (r8v7 androidx.compose.ui.Alignment$Horizontal) = (r8v2 androidx.compose.ui.Alignment$Horizontal), (r8v8 androidx.compose.ui.Alignment$Horizontal) binds: [B:154:0x01d8, B:138:0x01a8] A[DONT_GENERATE, DONT_INLINE]
      0x01b0: PHI (r9v9 androidx.compose.ui.Alignment$Vertical) = (r9v4 androidx.compose.ui.Alignment$Vertical), (r9v10 androidx.compose.ui.Alignment$Vertical) binds: [B:154:0x01d8, B:138:0x01a8] A[DONT_GENERATE, DONT_INLINE]
      0x01b0: PHI (r11v12 androidx.compose.foundation.layout.Arrangement$Vertical) = 
      (r11v7 androidx.compose.foundation.layout.Arrangement$Vertical)
      (r11v13 androidx.compose.foundation.layout.Arrangement$Vertical)
     binds: [B:154:0x01d8, B:138:0x01a8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:140:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:142:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:143:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:145:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:146:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:148:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:149:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:151:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:152:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:155:0x01da  */
    /* JADX WARN: Code duplicated, block: B:158:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:161:0x0213  */
    /* JADX WARN: Code duplicated, block: B:164:0x023a  */
    /* JADX WARN: Code duplicated, block: B:167:0x0283  */
    /* JADX WARN: Code duplicated, block: B:169:0x0287  */
    /* JADX WARN: Code duplicated, block: B:171:0x028c  */
    /* JADX WARN: Code duplicated, block: B:173:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:176:0x031b  */
    /* JADX WARN: Code duplicated, block: B:178:0x0328  */
    /* JADX WARN: Code duplicated, block: B:181:0x033a  */
    /* JADX WARN: Code duplicated, block: B:183:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:130:0x018d, please report this as an issue */
    public static final void LazyList(final Modifier modifier, final LazyListState lazyListState, final PaddingValues paddingValues, final boolean z, final boolean z2, final FlingBehavior flingBehavior, final boolean z3, final OverscrollEffect overscrollEffect, int i, Alignment.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, Arrangement.Horizontal horizontal2, final Function1<? super LazyListScope, Unit> function1, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        PaddingValues paddingValues2;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z4;
        final Alignment.Horizontal horizontal3;
        final Arrangement.Vertical vertical3;
        final Arrangement.Horizontal horizontal4;
        final int i10;
        final Alignment.Vertical vertical4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int iDefaultLazyListBeyondBoundsItemCount;
        Alignment.Horizontal horizontal5;
        Arrangement.Vertical vertical5;
        Alignment.Vertical vertical6;
        Alignment.Horizontal horizontal6;
        Arrangement.Vertical vertical7;
        Alignment.Vertical vertical8;
        int i11;
        int i12;
        Arrangement.Horizontal horizontal7;
        int i13;
        Object objRememberedValue;
        int i14;
        int i15;
        Orientation orientation;
        Orientation orientation2;
        Modifier modifierLazyLayoutBeyondBoundsModifier;
        Composer composerStartRestartGroup = composer.startRestartGroup(924924659);
        if ((i2 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        if ((i2 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(lazyListState) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            paddingValues2 = paddingValues;
            i5 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
        } else {
            paddingValues2 = paddingValues;
        }
        if ((i2 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changed(z2) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i5 |= composerStartRestartGroup.changed(flingBehavior) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i5 |= composerStartRestartGroup.changed(z3) ? IOUtil.MiB : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i5 |= composerStartRestartGroup.changed(overscrollEffect) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            if ((i4 & 256) == 0) {
                i6 = i;
                int i16 = composerStartRestartGroup.changed(i6) ? 67108864 : 33554432;
                i5 |= i16;
            } else {
                i6 = i;
            }
            i5 |= i16;
        } else {
            i6 = i;
        }
        int i17 = i4 & 512;
        if (i17 != 0) {
            i5 |= 805306368;
        } else if ((i2 & 805306368) == 0) {
            i5 |= composerStartRestartGroup.changed(horizontal) ? 536870912 : 268435456;
        }
        int i18 = i4 & 1024;
        if (i18 != 0) {
            i7 = i3 | 6;
        } else if ((i3 & 6) == 0) {
            i7 = i3 | (composerStartRestartGroup.changed(vertical) ? 4 : 2);
        } else {
            i7 = i3;
        }
        int i19 = i4 & 2048;
        if (i19 != 0) {
            i7 |= 48;
        } else if ((i3 & 48) == 0) {
            i7 |= composerStartRestartGroup.changed(vertical2) ? 32 : 16;
        }
        int i20 = i7;
        int i21 = i4 & 4096;
        if (i21 == 0) {
            i8 = i20;
            if ((i3 & 384) == 0) {
                i8 |= composerStartRestartGroup.changed(horizontal2) ? 256 : 128;
            }
            if ((i3 & 3072) == 0) {
                i8 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
            }
            i9 = i8;
            if ((i5 & 306783379) == 306783378 || (i9 & 1171) != 1170) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i5 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if ((i4 & 256) != 0) {
                        iDefaultLazyListBeyondBoundsItemCount = LazyList_androidKt.defaultLazyListBeyondBoundsItemCount(composerStartRestartGroup, 0);
                        i5 &= -234881025;
                    } else {
                        iDefaultLazyListBeyondBoundsItemCount = i6;
                    }
                    if (i17 != 0) {
                        horizontal5 = null;
                    } else {
                        horizontal5 = horizontal;
                    }
                    if (i18 != 0) {
                        vertical5 = null;
                    } else {
                        vertical5 = vertical;
                    }
                    if (i19 != 0) {
                        vertical6 = null;
                    } else {
                        vertical6 = vertical2;
                    }
                    horizontal6 = horizontal5;
                    vertical7 = vertical5;
                    vertical8 = vertical6;
                    i11 = iDefaultLazyListBeyondBoundsItemCount;
                    i12 = i5;
                    if (i21 != 0) {
                        horizontal7 = null;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(924924659, i12, i9, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:85)");
                    }
                    i13 = (i12 >> 3) & 14;
                    Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda = LazyListItemProviderKt.rememberLazyListItemProviderLambda(lazyListState, function1, composerStartRestartGroup, i13 | ((i9 >> 6) & 112));
                    int i22 = i12 >> 9;
                    LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyListSemanticState = LazyListSemanticsKt.rememberLazyListSemanticState(lazyListState, z2, composerStartRestartGroup, i13 | (i22 & 112));
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    int i23 = (65520 & i12) | (i22 & 458752) | (i22 & 3670016);
                    int i24 = i9 << 18;
                    int i25 = i23 | (i24 & 29360128) | (i24 & 234881024) | ((i9 << 27) & 1879048192);
                    i14 = i12;
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyListMeasurePolicy = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda, lazyListState, paddingValues2, z, z2, i11, horizontal6, vertical8, horizontal7, vertical7, (CoroutineScope) objRememberedValue, (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext()), ((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue() ? null : StickyItemsPlacement.INSTANCE.getStickToTopPlacement(), composerStartRestartGroup, i25, 0);
                    i15 = i11;
                    Alignment.Horizontal horizontal8 = horizontal6;
                    Alignment.Vertical vertical9 = vertical8;
                    Arrangement.Horizontal horizontal9 = horizontal7;
                    Arrangement.Vertical vertical10 = vertical7;
                    if (z2) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    orientation2 = orientation;
                    if (z3) {
                        composerStartRestartGroup.startReplaceGroup(-2077147368);
                        modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyListBeyondBoundsModifierKt.rememberLazyListBeyondBoundsState(lazyListState, i15, composerStartRestartGroup, i13 | ((i14 >> 21) & 112)), lazyListState.getBeyondBoundsInfo(), z, orientation2);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2076718545);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
                    }
                    LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(lazyListState.getRemeasurementModifier()).then(lazyListState.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda, lazyLayoutSemanticStateRememberLazyListSemanticState, orientation2, z3, z, composerStartRestartGroup, ((i14 >> 6) & 57344) | ((i14 << 6) & 458752)).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyListState.getItemAnimator$foundation().getModifier()), lazyListState, orientation2, overscrollEffect, z3, z, flingBehavior, lazyListState.getInternalInteractionSource(), null, 128, null), lazyListState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyListMeasurePolicy, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    i10 = i15;
                    horizontal3 = horizontal8;
                    vertical4 = vertical9;
                    horizontal4 = horizontal9;
                    vertical3 = vertical10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i4 & 256) != 0) {
                        i5 &= -234881025;
                    }
                    horizontal6 = horizontal;
                    vertical7 = vertical;
                    vertical8 = vertical2;
                    i12 = i5;
                    i11 = i6;
                }
                horizontal7 = horizontal2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(924924659, i12, i9, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:85)");
                }
                i13 = (i12 >> 3) & 14;
                Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda2 = LazyListItemProviderKt.rememberLazyListItemProviderLambda(lazyListState, function1, composerStartRestartGroup, i13 | ((i9 >> 6) & 112));
                int i26 = i12 >> 9;
                LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyListSemanticState2 = LazyListSemanticsKt.rememberLazyListSemanticState(lazyListState, z2, composerStartRestartGroup, i13 | (i26 & 112));
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                int i27 = (65520 & i12) | (i26 & 458752) | (i26 & 3670016);
                int i28 = i9 << 18;
                int i29 = i27 | (i28 & 29360128) | (i28 & 234881024) | ((i9 << 27) & 1879048192);
                i14 = i12;
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyListMeasurePolicy2 = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda2, lazyListState, paddingValues2, z, z2, i11, horizontal6, vertical8, horizontal7, vertical7, (CoroutineScope) objRememberedValue, (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext()), ((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue() ? null : StickyItemsPlacement.INSTANCE.getStickToTopPlacement(), composerStartRestartGroup, i29, 0);
                i15 = i11;
                Alignment.Horizontal horizontal10 = horizontal6;
                Alignment.Vertical vertical11 = vertical8;
                Arrangement.Horizontal horizontal11 = horizontal7;
                Arrangement.Vertical vertical12 = vertical7;
                if (z2) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                orientation2 = orientation;
                if (z3) {
                    composerStartRestartGroup.startReplaceGroup(-2077147368);
                    modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyListBeyondBoundsModifierKt.rememberLazyListBeyondBoundsState(lazyListState, i15, composerStartRestartGroup, i13 | ((i14 >> 21) & 112)), lazyListState.getBeyondBoundsInfo(), z, orientation2);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-2076718545);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
                }
                LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda2, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(lazyListState.getRemeasurementModifier()).then(lazyListState.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda2, lazyLayoutSemanticStateRememberLazyListSemanticState2, orientation2, z3, z, composerStartRestartGroup, ((i14 >> 6) & 57344) | ((i14 << 6) & 458752)).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyListState.getItemAnimator$foundation().getModifier()), lazyListState, orientation2, overscrollEffect, z3, z, flingBehavior, lazyListState.getInternalInteractionSource(), null, 128, null), lazyListState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyListMeasurePolicy2, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i10 = i15;
                horizontal3 = horizontal10;
                vertical4 = vertical11;
                horizontal4 = horizontal11;
                vertical3 = vertical12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                horizontal3 = horizontal;
                vertical3 = vertical;
                horizontal4 = horizontal2;
                i10 = i6;
                vertical4 = vertical2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dv8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyListKt.a(modifier, lazyListState, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, i10, horizontal3, vertical3, vertical4, horizontal4, function1, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i8 = i20 | 384;
        if ((i3 & 3072) == 0) {
            i8 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        i9 = i8;
        if ((i5 & 306783379) == 306783378) {
            z4 = true;
        } else {
            z4 = true;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i5 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if ((i4 & 256) != 0) {
                    iDefaultLazyListBeyondBoundsItemCount = LazyList_androidKt.defaultLazyListBeyondBoundsItemCount(composerStartRestartGroup, 0);
                    i5 &= -234881025;
                } else {
                    iDefaultLazyListBeyondBoundsItemCount = i6;
                }
                if (i17 != 0) {
                    horizontal5 = null;
                } else {
                    horizontal5 = horizontal;
                }
                if (i18 != 0) {
                    vertical5 = null;
                } else {
                    vertical5 = vertical;
                }
                if (i19 != 0) {
                    vertical6 = null;
                } else {
                    vertical6 = vertical2;
                }
                horizontal6 = horizontal5;
                vertical7 = vertical5;
                vertical8 = vertical6;
                i11 = iDefaultLazyListBeyondBoundsItemCount;
                i12 = i5;
                if (i21 != 0) {
                    horizontal7 = null;
                } else {
                    horizontal7 = horizontal2;
                }
            } else {
                if ((i4 & 256) != 0) {
                    iDefaultLazyListBeyondBoundsItemCount = LazyList_androidKt.defaultLazyListBeyondBoundsItemCount(composerStartRestartGroup, 0);
                    i5 &= -234881025;
                } else {
                    iDefaultLazyListBeyondBoundsItemCount = i6;
                }
                if (i17 != 0) {
                    horizontal5 = null;
                } else {
                    horizontal5 = horizontal;
                }
                if (i18 != 0) {
                    vertical5 = null;
                } else {
                    vertical5 = vertical;
                }
                if (i19 != 0) {
                    vertical6 = null;
                } else {
                    vertical6 = vertical2;
                }
                horizontal6 = horizontal5;
                vertical7 = vertical5;
                vertical8 = vertical6;
                i11 = iDefaultLazyListBeyondBoundsItemCount;
                i12 = i5;
                if (i21 != 0) {
                    horizontal7 = null;
                } else {
                    horizontal7 = horizontal2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(924924659, i12, i9, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:85)");
            }
            i13 = (i12 >> 3) & 14;
            Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda3 = LazyListItemProviderKt.rememberLazyListItemProviderLambda(lazyListState, function1, composerStartRestartGroup, i13 | ((i9 >> 6) & 112));
            int i210 = i12 >> 9;
            LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyListSemanticState3 = LazyListSemanticsKt.rememberLazyListSemanticState(lazyListState, z2, composerStartRestartGroup, i13 | (i210 & 112));
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            int i211 = (65520 & i12) | (i210 & 458752) | (i210 & 3670016);
            int i212 = i9 << 18;
            int i213 = i211 | (i212 & 29360128) | (i212 & 234881024) | ((i9 << 27) & 1879048192);
            i14 = i12;
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyRememberLazyListMeasurePolicy3 = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda3, lazyListState, paddingValues2, z, z2, i11, horizontal6, vertical8, horizontal7, vertical7, (CoroutineScope) objRememberedValue, (GraphicsContext) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalGraphicsContext()), ((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue() ? null : StickyItemsPlacement.INSTANCE.getStickToTopPlacement(), composerStartRestartGroup, i213, 0);
            i15 = i11;
            Alignment.Horizontal horizontal12 = horizontal6;
            Alignment.Vertical vertical13 = vertical8;
            Arrangement.Horizontal horizontal13 = horizontal7;
            Arrangement.Vertical vertical14 = vertical7;
            if (z2) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            orientation2 = orientation;
            if (z3) {
                composerStartRestartGroup.startReplaceGroup(-2077147368);
                modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, LazyListBeyondBoundsModifierKt.rememberLazyListBeyondBoundsState(lazyListState, i15, composerStartRestartGroup, i13 | ((i14 >> 21) & 112)), lazyListState.getBeyondBoundsInfo(), z, orientation2);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-2076718545);
                composerStartRestartGroup.endReplaceGroup();
                modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
            }
            LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda3, ScrollableAreaKt.scrollableArea$default(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(lazyListState.getRemeasurementModifier()).then(lazyListState.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda3, lazyLayoutSemanticStateRememberLazyListSemanticState3, orientation2, z3, z, composerStartRestartGroup, ((i14 >> 6) & 57344) | ((i14 << 6) & 458752)).then(modifierLazyLayoutBeyondBoundsModifier).then(lazyListState.getItemAnimator$foundation().getModifier()), lazyListState, orientation2, overscrollEffect, z3, z, flingBehavior, lazyListState.getInternalInteractionSource(), null, 128, null), lazyListState.getPrefetchState(), lazyLayoutMeasurePolicyRememberLazyListMeasurePolicy3, composerStartRestartGroup, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i10 = i15;
            horizontal3 = horizontal12;
            vertical4 = vertical13;
            horizontal4 = horizontal13;
            vertical3 = vertical14;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            horizontal3 = horizontal;
            vertical3 = vertical;
            horizontal4 = horizontal2;
            i10 = i6;
            vertical4 = vertical2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dv8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyListKt.a(modifier, lazyListState, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, i10, horizontal3, vertical3, vertical4, horizontal4, function1, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, boolean z2, FlingBehavior flingBehavior, boolean z3, OverscrollEffect overscrollEffect, int i, Alignment.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, Arrangement.Horizontal horizontal2, Function1 function1, int i2, int i3, int i4, Composer composer, int i5) {
        LazyList(modifier, lazyListState, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, i, horizontal, vertical, vertical2, horizontal2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void keepAroundItems(CacheWindowLogic cacheWindowLogic, List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider) {
        Trace.beginSection("compose:lazy:cache_window:keepAroundItems");
        try {
            if (cacheWindowLogic.hasValidBounds() && !list.isEmpty()) {
                int index = ((LazyListMeasuredItem) CollectionsKt.first(list)).getIndex();
                int index2 = ((LazyListMeasuredItem) CollectionsKt.last(list)).getIndex();
                for (int prefetchWindowStartLine = cacheWindowLogic.getPrefetchWindowStartLine(); prefetchWindowStartLine < index; prefetchWindowStartLine++) {
                    lazyListMeasuredItemProvider.keepAround(prefetchWindowStartLine);
                }
                int i = index2 + 1;
                int prefetchWindowEndLine = cacheWindowLogic.getPrefetchWindowEndLine();
                if (i <= prefetchWindowEndLine) {
                    while (true) {
                        lazyListMeasuredItemProvider.keepAround(i);
                        if (i == prefetchWindowEndLine) {
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

    /* JADX WARN: Code duplicated, block: B:101:0x0133  */
    /* JADX WARN: Code duplicated, block: B:104:0x0153  */
    /* JADX WARN: Code duplicated, block: B:37:0x0074 A[PHI: r4
      0x0074: PHI (r4v17 boolean) = (r4v15 boolean), (r4v18 boolean) binds: [B:36:0x0072, B:32:0x006b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:49:0x009c  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:55:0x00aa A[PHI: r9
      0x00aa: PHI (r9v17 androidx.compose.ui.Alignment$Horizontal) = (r9v14 androidx.compose.ui.Alignment$Horizontal), (r9v18 androidx.compose.ui.Alignment$Horizontal) binds: [B:54:0x00a8, B:50:0x00a2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:56:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:59:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:62:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:65:0x00c6 A[PHI: r12
      0x00c6: PHI (r12v13 androidx.compose.ui.Alignment$Vertical) = (r12v10 androidx.compose.ui.Alignment$Vertical), (r12v14 androidx.compose.ui.Alignment$Vertical) binds: [B:64:0x00c4, B:60:0x00be] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:66:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:69:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e2 A[PHI: r13
      0x00e2: PHI (r13v13 androidx.compose.foundation.layout.Arrangement$Horizontal) = 
      (r13v10 androidx.compose.foundation.layout.Arrangement$Horizontal)
      (r13v14 androidx.compose.foundation.layout.Arrangement$Horizontal)
     binds: [B:74:0x00e0, B:70:0x00da] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:76:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:79:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:82:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:85:0x00fe A[PHI: r5
      0x00fe: PHI (r5v9 androidx.compose.foundation.layout.Arrangement$Vertical) = 
      (r5v7 androidx.compose.foundation.layout.Arrangement$Vertical)
      (r5v10 androidx.compose.foundation.layout.Arrangement$Vertical)
     binds: [B:84:0x00fc, B:80:0x00f6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:86:0x0100  */
    /* JADX WARN: Code duplicated, block: B:89:0x010f  */
    /* JADX WARN: Code duplicated, block: B:92:0x0118  */
    /* JADX WARN: Code duplicated, block: B:95:0x011e A[PHI: r6
      0x011e: PHI (r6v7 androidx.compose.foundation.lazy.layout.StickyItemsPlacement) = 
      (r6v5 androidx.compose.foundation.lazy.layout.StickyItemsPlacement)
      (r6v8 androidx.compose.foundation.lazy.layout.StickyItemsPlacement)
     binds: [B:94:0x011c, B:90:0x0115] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:96:0x0121  */
    /* JADX WARN: Code duplicated, block: B:99:0x012b  */
    private static final LazyLayoutMeasurePolicy rememberLazyListMeasurePolicy(Function0<? extends LazyListItemProvider> function0, LazyListState lazyListState, PaddingValues paddingValues, boolean z, boolean z2, int i, Alignment.Horizontal horizontal, Alignment.Vertical vertical, Arrangement.Horizontal horizontal2, Arrangement.Vertical vertical2, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Composer composer, int i2, int i3) {
        boolean z3;
        boolean z4;
        Alignment.Horizontal horizontal3;
        boolean z5;
        Alignment.Vertical vertical3;
        boolean z6;
        Arrangement.Horizontal horizontal4;
        boolean z7;
        Arrangement.Vertical vertical4;
        boolean z8;
        StickyItemsPlacement stickyItemsPlacement2;
        boolean z9;
        boolean z10;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(406165748, i2, i3, "androidx.compose.foundation.lazy.rememberLazyListMeasurePolicy (LazyList.kt:187)");
        }
        boolean z11 = ((((i2 & 112) ^ 48) > 32 && composer.changed(lazyListState)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(paddingValues)) || (i2 & 384) == 256) | ((((i2 & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i2 & 3072) == 2048);
        if (((57344 & i2) ^ 24576) > 16384) {
            z3 = z2;
            if (composer.changed(z3)) {
                z4 = true;
            }
            boolean z12 = z11 | z4 | ((((458752 & i2) ^ 196608) <= 131072 && composer.changed(i)) || (i2 & 196608) == 131072);
            if (((3670016 & i2) ^ 1572864) > 1048576) {
                horizontal3 = horizontal;
                if (!composer.changed(horizontal3)) {
                    z5 = true;
                }
                boolean z13 = z12 | z5;
                if (((29360128 & i2) ^ 12582912) > 8388608) {
                    vertical3 = vertical;
                    if (!composer.changed(vertical3)) {
                        z6 = true;
                    }
                    boolean z14 = z13 | z6;
                    if (((234881024 & i2) ^ 100663296) > 67108864) {
                        horizontal4 = horizontal2;
                        if (!composer.changed(horizontal4)) {
                            z7 = true;
                        }
                        boolean z15 = z14 | z7;
                        if (((1879048192 & i2) ^ 805306368) > 536870912) {
                            vertical4 = vertical2;
                            if (!composer.changed(vertical4)) {
                                z8 = true;
                            }
                            boolean zChanged = z8 | z15 | composer.changed(graphicsContext);
                            if (((i3 & 896) ^ 384) > 256) {
                                stickyItemsPlacement2 = stickyItemsPlacement;
                                if (!composer.changed(stickyItemsPlacement2)) {
                                    z9 = true;
                                }
                                z10 = zChanged | z9;
                                objRememberedValue = composer.rememberedValue();
                                if (z10 || objRememberedValue == Composer.Companion.getEmpty()) {
                                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1);
                                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1;
                                }
                                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy = (LazyLayoutMeasurePolicy) objRememberedValue;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                return lazyLayoutMeasurePolicy;
                            }
                            stickyItemsPlacement2 = stickyItemsPlacement;
                            if ((i3 & 384) == 256) {
                                z9 = true;
                            } else {
                                z9 = false;
                            }
                            z10 = zChanged | z9;
                            objRememberedValue = composer.rememberedValue();
                            if (z10) {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$2 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$2);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$2;
                            } else {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$3 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$3);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$3;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy2;
                        }
                        vertical4 = vertical2;
                        if ((i2 & 805306368) == 536870912) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        boolean zChanged2 = z8 | z15 | composer.changed(graphicsContext);
                        if (((i3 & 896) ^ 384) > 256) {
                            stickyItemsPlacement2 = stickyItemsPlacement;
                            if (!composer.changed(stickyItemsPlacement2)) {
                                z9 = true;
                            }
                            z10 = zChanged2 | z9;
                            objRememberedValue = composer.rememberedValue();
                            if (z10) {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$4 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$4);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$4;
                            } else {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$5 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$5);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$5;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy3 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy3;
                        }
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if ((i3 & 384) == 256) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        z10 = zChanged2 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$6 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$6);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$6;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$7 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$7);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$7;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy4 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy4;
                    }
                    horizontal4 = horizontal2;
                    if ((100663296 & i2) == 67108864) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z16 = z14 | z7;
                    if (((1879048192 & i2) ^ 805306368) > 536870912) {
                        vertical4 = vertical2;
                        if (!composer.changed(vertical4)) {
                            z8 = true;
                        }
                        boolean zChanged3 = z8 | z16 | composer.changed(graphicsContext);
                        if (((i3 & 896) ^ 384) > 256) {
                            stickyItemsPlacement2 = stickyItemsPlacement;
                            if (!composer.changed(stickyItemsPlacement2)) {
                                z9 = true;
                            }
                            z10 = zChanged3 | z9;
                            objRememberedValue = composer.rememberedValue();
                            if (z10) {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$8 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$8);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$8;
                            } else {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$9 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$9);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$9;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy5 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy5;
                        }
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if ((i3 & 384) == 256) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        z10 = zChanged3 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$10 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$10);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$10;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy6 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy6;
                    }
                    vertical4 = vertical2;
                    if ((i2 & 805306368) == 536870912) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean zChanged4 = z8 | z16 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged4 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$12 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$12);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$12;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$13 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$13);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$13;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy7 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy7;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged4 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$14 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$14);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$14;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$15 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$15);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$15;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy8 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy8;
                }
                vertical3 = vertical;
                if ((12582912 & i2) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z17 = z13 | z6;
                if (((234881024 & i2) ^ 100663296) > 67108864) {
                    horizontal4 = horizontal2;
                    if (!composer.changed(horizontal4)) {
                        z7 = true;
                    }
                    boolean z18 = z17 | z7;
                    if (((1879048192 & i2) ^ 805306368) > 536870912) {
                        vertical4 = vertical2;
                        if (!composer.changed(vertical4)) {
                            z8 = true;
                        }
                        boolean zChanged5 = z8 | z18 | composer.changed(graphicsContext);
                        if (((i3 & 896) ^ 384) > 256) {
                            stickyItemsPlacement2 = stickyItemsPlacement;
                            if (!composer.changed(stickyItemsPlacement2)) {
                                z9 = true;
                            }
                            z10 = zChanged5 | z9;
                            objRememberedValue = composer.rememberedValue();
                            if (z10) {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$16 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$16);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$16;
                            } else {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$17 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$17);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$17;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy9 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy9;
                        }
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if ((i3 & 384) == 256) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        z10 = zChanged5 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$18 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$18);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$18;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$19 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$19);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$19;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy10 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy10;
                    }
                    vertical4 = vertical2;
                    if ((i2 & 805306368) == 536870912) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean zChanged6 = z8 | z18 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged6 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$110);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$110;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy11;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged6 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$112);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$112;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$113);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy12 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy12;
                }
                horizontal4 = horizontal2;
                if ((100663296 & i2) == 67108864) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z19 = z17 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    vertical4 = vertical2;
                    if (!composer.changed(vertical4)) {
                        z8 = true;
                    }
                    boolean zChanged7 = z8 | z19 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged7 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$114);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$114;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$115);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$115;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy13 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy13;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged7 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$116);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$116;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$117);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$117;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy14 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy14;
                }
                vertical4 = vertical2;
                if ((i2 & 805306368) == 536870912) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zChanged8 = z8 | z19 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged8 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$118);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$118;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$119);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy15 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy15;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged8 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1110);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1110;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy16 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy16;
            }
            horizontal3 = horizontal;
            if ((1572864 & i2) == 1048576) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z110 = z12 | z5;
            if (((29360128 & i2) ^ 12582912) > 8388608) {
                vertical3 = vertical;
                if (!composer.changed(vertical3)) {
                    z6 = true;
                }
                boolean z111 = z110 | z6;
                if (((234881024 & i2) ^ 100663296) > 67108864) {
                    horizontal4 = horizontal2;
                    if (!composer.changed(horizontal4)) {
                        z7 = true;
                    }
                    boolean z112 = z111 | z7;
                    if (((1879048192 & i2) ^ 805306368) > 536870912) {
                        vertical4 = vertical2;
                        if (!composer.changed(vertical4)) {
                            z8 = true;
                        }
                        boolean zChanged9 = z8 | z112 | composer.changed(graphicsContext);
                        if (((i3 & 896) ^ 384) > 256) {
                            stickyItemsPlacement2 = stickyItemsPlacement;
                            if (!composer.changed(stickyItemsPlacement2)) {
                                z9 = true;
                            }
                            z10 = zChanged9 | z9;
                            objRememberedValue = composer.rememberedValue();
                            if (z10) {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1112);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1112;
                            } else {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1113);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1113;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy17 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy17;
                        }
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if ((i3 & 384) == 256) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        z10 = zChanged9 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1114);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1114;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1115);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1115;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy18 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy18;
                    }
                    vertical4 = vertical2;
                    if ((i2 & 805306368) == 536870912) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean zChanged10 = z8 | z112 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged10 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1116);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1116;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1117);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1117;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy19 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy19;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged10 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1118);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1118;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1119);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy110;
                }
                horizontal4 = horizontal2;
                if ((100663296 & i2) == 67108864) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z113 = z111 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    vertical4 = vertical2;
                    if (!composer.changed(vertical4)) {
                        z8 = true;
                    }
                    boolean zChanged11 = z8 | z113 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged11 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11110);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11110;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy111;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged11 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11112);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11112;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11113);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy112 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy112;
                }
                vertical4 = vertical2;
                if ((i2 & 805306368) == 536870912) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zChanged12 = z8 | z113 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged12 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11114);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11114;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11115);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy113;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged12 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11116);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11116;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11117);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy114;
            }
            vertical3 = vertical;
            if ((12582912 & i2) == 8388608) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z114 = z110 | z6;
            if (((234881024 & i2) ^ 100663296) > 67108864) {
                horizontal4 = horizontal2;
                if (!composer.changed(horizontal4)) {
                    z7 = true;
                }
                boolean z115 = z114 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    vertical4 = vertical2;
                    if (!composer.changed(vertical4)) {
                        z8 = true;
                    }
                    boolean zChanged13 = z8 | z115 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged13 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11118);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11118;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11119);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11119;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy115;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged13 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111110);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111110;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy116 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy116;
                }
                vertical4 = vertical2;
                if ((i2 & 805306368) == 536870912) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zChanged14 = z8 | z115 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged14 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111112);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111112;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111113);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy117;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged14 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111114);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111114;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111115);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111115;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy118 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy118;
            }
            horizontal4 = horizontal2;
            if ((100663296 & i2) == 67108864) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z116 = z114 | z7;
            if (((1879048192 & i2) ^ 805306368) > 536870912) {
                vertical4 = vertical2;
                if (!composer.changed(vertical4)) {
                    z8 = true;
                }
                boolean zChanged15 = z8 | z116 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged15 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111116);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111116;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111117);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111117;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy119;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged15 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111118);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111118;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111119);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111119;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy1110;
            }
            vertical4 = vertical2;
            if ((i2 & 805306368) == 536870912) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean zChanged16 = z8 | z116 | composer.changed(graphicsContext);
            if (((i3 & 896) ^ 384) > 256) {
                stickyItemsPlacement2 = stickyItemsPlacement;
                if (!composer.changed(stickyItemsPlacement2)) {
                    z9 = true;
                }
                z10 = zChanged16 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111110);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111110;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy1111;
            }
            stickyItemsPlacement2 = stickyItemsPlacement;
            if ((i3 & 384) == 256) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = zChanged16 | z9;
            objRememberedValue = composer.rememberedValue();
            if (z10) {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111112);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111112;
            } else {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111113);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111113;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1112 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1112;
        }
        z3 = z2;
        if ((i2 & 24576) == 16384) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z117 = z11 | z4 | ((((458752 & i2) ^ 196608) <= 131072 && composer.changed(i)) || (i2 & 196608) == 131072);
        if (((3670016 & i2) ^ 1572864) > 1048576) {
            horizontal3 = horizontal;
            if (!composer.changed(horizontal3)) {
                z5 = true;
            }
            boolean z118 = z117 | z5;
            if (((29360128 & i2) ^ 12582912) > 8388608) {
                vertical3 = vertical;
                if (!composer.changed(vertical3)) {
                    z6 = true;
                }
                boolean z119 = z118 | z6;
                if (((234881024 & i2) ^ 100663296) > 67108864) {
                    horizontal4 = horizontal2;
                    if (!composer.changed(horizontal4)) {
                        z7 = true;
                    }
                    boolean z1110 = z119 | z7;
                    if (((1879048192 & i2) ^ 805306368) > 536870912) {
                        vertical4 = vertical2;
                        if (!composer.changed(vertical4)) {
                            z8 = true;
                        }
                        boolean zChanged17 = z8 | z1110 | composer.changed(graphicsContext);
                        if (((i3 & 896) ^ 384) > 256) {
                            stickyItemsPlacement2 = stickyItemsPlacement;
                            if (!composer.changed(stickyItemsPlacement2)) {
                                z9 = true;
                            }
                            z10 = zChanged17 | z9;
                            objRememberedValue = composer.rememberedValue();
                            if (z10) {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111114);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111114;
                            } else {
                                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111115);
                                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111115;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy1113;
                        }
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if ((i3 & 384) == 256) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        z10 = zChanged17 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111116);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111116;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111117);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111117;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy1114;
                    }
                    vertical4 = vertical2;
                    if ((i2 & 805306368) == 536870912) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean zChanged18 = z8 | z1110 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged18 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111118);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111118;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111119);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111119;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy1115;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged18 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111110);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111110;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1116 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy1116;
                }
                horizontal4 = horizontal2;
                if ((100663296 & i2) == 67108864) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z1111 = z119 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    vertical4 = vertical2;
                    if (!composer.changed(vertical4)) {
                        z8 = true;
                    }
                    boolean zChanged19 = z8 | z1111 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged19 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111112);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111112;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111113);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111113;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy1117;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged19 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111114);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111114;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111115);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1118 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy1118;
                }
                vertical4 = vertical2;
                if ((i2 & 805306368) == 536870912) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zChanged110 = z8 | z1111 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged110 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111116);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111116;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111117);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111117;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy1119;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged110 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111118);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111118;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111119);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111119;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11110;
            }
            vertical3 = vertical;
            if ((12582912 & i2) == 8388608) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z1112 = z118 | z6;
            if (((234881024 & i2) ^ 100663296) > 67108864) {
                horizontal4 = horizontal2;
                if (!composer.changed(horizontal4)) {
                    z7 = true;
                }
                boolean z1113 = z1112 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    vertical4 = vertical2;
                    if (!composer.changed(vertical4)) {
                        z8 = true;
                    }
                    boolean zChanged111 = z8 | z1113 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged111 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111110);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111110;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy11111;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged111 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111112);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111112;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111113);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11112 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy11112;
                }
                vertical4 = vertical2;
                if ((i2 & 805306368) == 536870912) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zChanged112 = z8 | z1113 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged112 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111114);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111114;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111115);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy11113;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged112 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111116);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111116;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111117);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11114;
            }
            horizontal4 = horizontal2;
            if ((100663296 & i2) == 67108864) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z1114 = z1112 | z7;
            if (((1879048192 & i2) ^ 805306368) > 536870912) {
                vertical4 = vertical2;
                if (!composer.changed(vertical4)) {
                    z8 = true;
                }
                boolean zChanged113 = z8 | z1114 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged113 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111118);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111118;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111119);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy11115;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged113 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111110);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111110;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11116 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11116;
            }
            vertical4 = vertical2;
            if ((i2 & 805306368) == 536870912) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean zChanged114 = z8 | z1114 | composer.changed(graphicsContext);
            if (((i3 & 896) ^ 384) > 256) {
                stickyItemsPlacement2 = stickyItemsPlacement;
                if (!composer.changed(stickyItemsPlacement2)) {
                    z9 = true;
                }
                z10 = zChanged114 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111112);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111112;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111113);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111113;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11117;
            }
            stickyItemsPlacement2 = stickyItemsPlacement;
            if ((i3 & 384) == 256) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = zChanged114 | z9;
            objRememberedValue = composer.rememberedValue();
            if (z10) {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111114);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111114;
            } else {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111115);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111115;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11118 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy11118;
        }
        horizontal3 = horizontal;
        if ((1572864 & i2) == 1048576) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z1115 = z117 | z5;
        if (((29360128 & i2) ^ 12582912) > 8388608) {
            vertical3 = vertical;
            if (!composer.changed(vertical3)) {
                z6 = true;
            }
            boolean z1116 = z1115 | z6;
            if (((234881024 & i2) ^ 100663296) > 67108864) {
                horizontal4 = horizontal2;
                if (!composer.changed(horizontal4)) {
                    z7 = true;
                }
                boolean z1117 = z1116 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    vertical4 = vertical2;
                    if (!composer.changed(vertical4)) {
                        z8 = true;
                    }
                    boolean zChanged115 = z8 | z1117 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) > 256) {
                        stickyItemsPlacement2 = stickyItemsPlacement;
                        if (!composer.changed(stickyItemsPlacement2)) {
                            z9 = true;
                        }
                        z10 = zChanged115 | z9;
                        objRememberedValue = composer.rememberedValue();
                        if (z10) {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111116);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111116;
                        } else {
                            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111117);
                            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111117;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy11119;
                    }
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if ((i3 & 384) == 256) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = zChanged115 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111118);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111118;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111119);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111110;
                }
                vertical4 = vertical2;
                if ((i2 & 805306368) == 536870912) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean zChanged116 = z8 | z1117 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged116 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111110);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111110;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111111);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111111;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111111;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged116 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111112);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111112;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111113);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111113;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111112 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111112;
            }
            horizontal4 = horizontal2;
            if ((100663296 & i2) == 67108864) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z1118 = z1116 | z7;
            if (((1879048192 & i2) ^ 805306368) > 536870912) {
                vertical4 = vertical2;
                if (!composer.changed(vertical4)) {
                    z8 = true;
                }
                boolean zChanged117 = z8 | z1118 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged117 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111114);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111114;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111115);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111113;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged117 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111116);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111116;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111117);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111114;
            }
            vertical4 = vertical2;
            if ((i2 & 805306368) == 536870912) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean zChanged118 = z8 | z1118 | composer.changed(graphicsContext);
            if (((i3 & 896) ^ 384) > 256) {
                stickyItemsPlacement2 = stickyItemsPlacement;
                if (!composer.changed(stickyItemsPlacement2)) {
                    z9 = true;
                }
                z10 = zChanged118 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111118);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111118;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$11111111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$11111111119);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$11111111119;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111115;
            }
            stickyItemsPlacement2 = stickyItemsPlacement;
            if ((i3 & 384) == 256) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = zChanged118 | z9;
            objRememberedValue = composer.rememberedValue();
            if (z10) {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111110);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111110;
            } else {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111111);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111111;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111116 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy111116;
        }
        vertical3 = vertical;
        if ((12582912 & i2) == 8388608) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z1119 = z1115 | z6;
        if (((234881024 & i2) ^ 100663296) > 67108864) {
            horizontal4 = horizontal2;
            if (!composer.changed(horizontal4)) {
                z7 = true;
            }
            boolean z11110 = z1119 | z7;
            if (((1879048192 & i2) ^ 805306368) > 536870912) {
                vertical4 = vertical2;
                if (!composer.changed(vertical4)) {
                    z8 = true;
                }
                boolean zChanged119 = z8 | z11110 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) > 256) {
                    stickyItemsPlacement2 = stickyItemsPlacement;
                    if (!composer.changed(stickyItemsPlacement2)) {
                        z9 = true;
                    }
                    z10 = zChanged119 | z9;
                    objRememberedValue = composer.rememberedValue();
                    if (z10) {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111112);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111112;
                    } else {
                        LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111113);
                        objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111117;
                }
                stickyItemsPlacement2 = stickyItemsPlacement;
                if ((i3 & 384) == 256) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = zChanged119 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111114);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111114;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111115);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111115;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111118 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111118;
            }
            vertical4 = vertical2;
            if ((i2 & 805306368) == 536870912) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean zChanged1110 = z8 | z11110 | composer.changed(graphicsContext);
            if (((i3 & 896) ^ 384) > 256) {
                stickyItemsPlacement2 = stickyItemsPlacement;
                if (!composer.changed(stickyItemsPlacement2)) {
                    z9 = true;
                }
                z10 = zChanged1110 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111116);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111116;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111117);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111119;
            }
            stickyItemsPlacement2 = stickyItemsPlacement;
            if ((i3 & 384) == 256) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = zChanged1110 | z9;
            objRememberedValue = composer.rememberedValue();
            if (z10) {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111118 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111118);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111118;
            } else {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$111111111119 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$111111111119);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$111111111119;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111110 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1111110;
        }
        horizontal4 = horizontal2;
        if ((100663296 & i2) == 67108864) {
            z7 = true;
        } else {
            z7 = false;
        }
        boolean z11111 = z1119 | z7;
        if (((1879048192 & i2) ^ 805306368) > 536870912) {
            vertical4 = vertical2;
            if (!composer.changed(vertical4)) {
                z8 = true;
            }
            boolean zChanged1111 = z8 | z11111 | composer.changed(graphicsContext);
            if (((i3 & 896) ^ 384) > 256) {
                stickyItemsPlacement2 = stickyItemsPlacement;
                if (!composer.changed(stickyItemsPlacement2)) {
                    z9 = true;
                }
                z10 = zChanged1111 | z9;
                objRememberedValue = composer.rememberedValue();
                if (z10) {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111110 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111110);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111110;
                } else {
                    LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111111 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111111);
                    objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy1111111;
            }
            stickyItemsPlacement2 = stickyItemsPlacement;
            if ((i3 & 384) == 256) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = zChanged1111 | z9;
            objRememberedValue = composer.rememberedValue();
            if (z10) {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111112 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111112);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111112;
            } else {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111113 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111113);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111113;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111112 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1111112;
        }
        vertical4 = vertical2;
        if ((i2 & 805306368) == 536870912) {
            z8 = true;
        } else {
            z8 = false;
        }
        boolean zChanged1112 = z8 | z11111 | composer.changed(graphicsContext);
        if (((i3 & 896) ^ 384) > 256) {
            stickyItemsPlacement2 = stickyItemsPlacement;
            if (!composer.changed(stickyItemsPlacement2)) {
                z9 = true;
            }
            z10 = zChanged1112 | z9;
            objRememberedValue = composer.rememberedValue();
            if (z10) {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111114 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111114);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111114;
            } else {
                LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111115 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111115);
                objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111115;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111113 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1111113;
        }
        stickyItemsPlacement2 = stickyItemsPlacement;
        if ((i3 & 384) == 256) {
            z9 = true;
        } else {
            z9 = false;
        }
        z10 = zChanged1112 | z9;
        objRememberedValue = composer.rememberedValue();
        if (z10) {
            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111116 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111116);
            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111116;
        } else {
            LazyListKt$rememberLazyListMeasurePolicy$1$1 lazyListKt$rememberLazyListMeasurePolicy$1$1111111111117 = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
            composer.updateRememberedValue(lazyListKt$rememberLazyListMeasurePolicy$1$1111111111117);
            objRememberedValue = lazyListKt$rememberLazyListMeasurePolicy$1$1111111111117;
        }
        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111114 = (LazyLayoutMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lazyLayoutMeasurePolicy1111114;
    }
}
