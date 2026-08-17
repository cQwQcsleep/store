package androidx.compose.foundation.pager;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollableAreaKt;
import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.foundation.pager.LazyLayoutPagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aå\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182#\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001a2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$21\u0010%\u001a-\u0012\u0004\u0012\u00020'\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0002\b)¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b+\u0010,\u001a\u0081\u0001\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u0010\u0004\u001a\u00020\u000521\u0010%\u001a-\u0012\u0004\u0012\u00020'\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0002\b)¢\u0006\u0002\b*2#\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0003¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u00063"}, d2 = {"Pager", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Pager-eLwUrMk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "rememberPagerItemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pageCount", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "dragDirectionDetector", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutPagerKt {
    /* JADX WARN: Code duplicated, block: B:100:0x0135  */
    /* JADX WARN: Code duplicated, block: B:102:0x013a  */
    /* JADX WARN: Code duplicated, block: B:105:0x0140  */
    /* JADX WARN: Code duplicated, block: B:107:0x0148  */
    /* JADX WARN: Code duplicated, block: B:109:0x014d  */
    /* JADX WARN: Code duplicated, block: B:112:0x0153  */
    /* JADX WARN: Code duplicated, block: B:114:0x015b  */
    /* JADX WARN: Code duplicated, block: B:116:0x0160  */
    /* JADX WARN: Code duplicated, block: B:119:0x0168  */
    /* JADX WARN: Code duplicated, block: B:121:0x016e  */
    /* JADX WARN: Code duplicated, block: B:125:0x0178  */
    /* JADX WARN: Code duplicated, block: B:127:0x017e  */
    /* JADX WARN: Code duplicated, block: B:135:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:138:0x01ab A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:139:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:140:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:142:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:143:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:146:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:148:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:149:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:151:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:154:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:155:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:160:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:163:0x0236  */
    /* JADX WARN: Code duplicated, block: B:166:0x0243  */
    /* JADX WARN: Code duplicated, block: B:167:0x0246  */
    /* JADX WARN: Code duplicated, block: B:172:0x0254  */
    /* JADX WARN: Code duplicated, block: B:175:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:176:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:179:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:180:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:183:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:184:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:191:0x02de  */
    /* JADX WARN: Code duplicated, block: B:194:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:195:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:200:0x0310  */
    /* JADX WARN: Code duplicated, block: B:203:0x031e  */
    /* JADX WARN: Code duplicated, block: B:204:0x033b  */
    /* JADX WARN: Code duplicated, block: B:207:0x037b  */
    /* JADX WARN: Code duplicated, block: B:208:0x037e  */
    /* JADX WARN: Code duplicated, block: B:211:0x03bc  */
    /* JADX WARN: Code duplicated, block: B:213:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:216:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:218:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:78:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:79:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:83:0x0108  */
    /* JADX WARN: Code duplicated, block: B:85:0x010e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0111  */
    /* JADX WARN: Code duplicated, block: B:88:0x0116  */
    /* JADX WARN: Code duplicated, block: B:91:0x011c  */
    /* JADX WARN: Code duplicated, block: B:93:0x0122  */
    /* JADX WARN: Code duplicated, block: B:94:0x0125  */
    /* JADX WARN: Code duplicated, block: B:98:0x012d  */
    /* JADX WARN: Instruction removed from duplicated block: B:151:0x01d3, please report this as an issue */
    /* JADX INFO: renamed from: Pager-eLwUrMk, reason: not valid java name */
    public static final void m1222PagereLwUrMk(final Modifier modifier, final PagerState pagerState, final PaddingValues paddingValues, final boolean z, final Orientation orientation, final TargetedFlingBehavior targetedFlingBehavior, final boolean z2, final OverscrollEffect overscrollEffect, int i, float f, final PageSize pageSize, NestedScrollConnection nestedScrollConnection, final Function1<? super Integer, ? extends Object> function1, final Alignment.Horizontal horizontal, final Alignment.Vertical vertical, final SnapPosition snapPosition, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        int i6;
        float f2;
        int i7;
        int i8;
        int i9;
        boolean z3;
        final NestedScrollConnection nestedScrollConnection2;
        Composer composer2;
        final int i10;
        final float f3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i11;
        float f4;
        boolean z4;
        int i12;
        boolean z5;
        Object objRememberedValue;
        int i13;
        int i14;
        int i15;
        Object objRememberedValue2;
        Composer.Companion companion;
        boolean z6;
        Object objRememberedValue3;
        Orientation orientation2;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        Object objRememberedValue4;
        BringIntoViewSpec bringIntoViewSpec;
        boolean z11;
        boolean zChanged;
        Object objRememberedValue5;
        Modifier modifierLazyLayoutBeyondBoundsModifier;
        boolean z12;
        int i16;
        int i17;
        final PagerState pagerState2 = pagerState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-572816025);
        if ((i2 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        if ((i2 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(pagerState2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i5 |= composerStartRestartGroup.changed(paddingValues) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changed(orientation.ordinal()) ? 16384 : 8192;
        }
        if ((i2 & 196608) == 0) {
            i5 |= composerStartRestartGroup.changed(targetedFlingBehavior) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i5 |= composerStartRestartGroup.changed(z2) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i5 |= composerStartRestartGroup.changed(overscrollEffect) ? 8388608 : 4194304;
        }
        int i18 = i4 & 256;
        if (i18 == 0) {
            if ((i2 & 100663296) == 0) {
                i5 |= composerStartRestartGroup.changed(i) ? 67108864 : 33554432;
            }
            i6 = i4 & 512;
            if (i6 != 0) {
                i5 |= 805306368;
                f2 = f;
            } else {
                f2 = f;
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i7 = 536870912;
                    } else {
                        i7 = 268435456;
                    }
                    i5 |= i7;
                }
            }
            if ((i3 & 6) == 0) {
                if (composerStartRestartGroup.changed(pageSize)) {
                    i17 = 4;
                } else {
                    i17 = 2;
                }
                i8 = i3 | i17;
            } else {
                i8 = i3;
            }
            if ((i3 & 48) == 0) {
                if (composerStartRestartGroup.changedInstance(nestedScrollConnection)) {
                    i16 = 32;
                } else {
                    i16 = 16;
                }
                i8 |= i16;
            }
            if ((i3 & 384) != 0) {
                i8 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
            }
            if ((i3 & 3072) != 0) {
                i8 |= composerStartRestartGroup.changed(horizontal) ? 2048 : 1024;
            }
            if ((i3 & 24576) != 0) {
                i8 |= composerStartRestartGroup.changed(vertical) ? 16384 : 8192;
            }
            if ((i3 & 196608) == 0) {
                i8 |= composerStartRestartGroup.changed(snapPosition) ? 131072 : 65536;
            }
            if ((i3 & 1572864) == 0) {
                i8 |= composerStartRestartGroup.changedInstance(function4) ? 1048576 : 524288;
            }
            i9 = i8;
            if ((i5 & 306783379) == 306783378 || (599187 & i9) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i5 & 1)) {
                if (i18 != 0) {
                    i11 = 0;
                } else {
                    i11 = i;
                }
                if (i6 != 0) {
                    f4 = Dp.constructor-impl(0.0f);
                } else {
                    f4 = f2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-572816025, i5, i9, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:102)");
                }
                if (i11 >= 0) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (!z4) {
                    InlineClassHelperKt.throwIllegalArgumentException("beyondViewportPageCount should be greater than or equal to 0, you selected " + i11);
                }
                i12 = i5 & 112;
                if (i12 == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5 || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: nu8
                        public final Object invoke() {
                            return Integer.valueOf(pagerState2.getPageCount());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                int i19 = i5 >> 3;
                i13 = i19 & 14;
                int i20 = i9 >> 15;
                i14 = i5;
                i15 = i11;
                Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda = rememberPagerItemProviderLambda(pagerState, function4, function1, (Function0) objRememberedValue, composerStartRestartGroup, i13 | (i20 & 112) | (i9 & 896));
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
                if (i12 == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z6 || objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: ou8
                        public final Object invoke() {
                            return Integer.valueOf(pagerState.getPageCount());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                int i21 = i14 >> 9;
                int i22 = (i14 & 65520) | (i21 & 458752) | (i21 & 3670016) | ((i9 << 21) & 29360128);
                int i23 = i9 << 15;
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyM1233rememberPagerMeasurePolicy8u0NR3k = PagerMeasurePolicyKt.m1233rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda, pagerState, paddingValues, z, orientation, i15, f4, pageSize, horizontal, vertical, snapPosition, coroutineScope, (Function0) objRememberedValue3, composerStartRestartGroup, i22 | (i23 & 234881024) | (i23 & 1879048192), i20 & 14);
                float f5 = f4;
                orientation2 = Orientation.Vertical;
                if (orientation == orientation2) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                LazyLayoutSemanticState lazyLayoutSemanticStateRememberPagerSemanticState = PagerSemanticsKt.rememberPagerSemanticState(pagerState, z7, composerStartRestartGroup, i13);
                if (i12 == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                if ((i14 & 458752) == 131072) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z9 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z10 || objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new PagerWrapperFlingBehavior(targetedFlingBehavior, pagerState);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                PagerWrapperFlingBehavior pagerWrapperFlingBehavior = (PagerWrapperFlingBehavior) objRememberedValue4;
                bringIntoViewSpec = (BringIntoViewSpec) composerStartRestartGroup.consume(BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec());
                if (i12 == 32) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                zChanged = z11 | composerStartRestartGroup.changed(bringIntoViewSpec);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = new PagerBringIntoViewSpec(pagerState, bringIntoViewSpec);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                PagerBringIntoViewSpec pagerBringIntoViewSpec = (PagerBringIntoViewSpec) objRememberedValue5;
                if (z2) {
                    composerStartRestartGroup.startReplaceGroup(-853822717);
                    modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(pagerState, i15, composerStartRestartGroup, i13 | ((i14 >> 21) & 112)), pagerState.getBeyondBoundsInfo(), z, orientation);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-853392933);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
                }
                Modifier modifierLazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(pagerState.getRemeasurementModifier()).then(pagerState.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda, lazyLayoutSemanticStateRememberPagerSemanticState, orientation, z2, z, composerStartRestartGroup, (i19 & 7168) | ((i14 >> 6) & 57344) | ((i14 << 6) & 458752));
                if (orientation == orientation2) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                Modifier modifierScrollableArea = ScrollableAreaKt.scrollableArea(PagerKt.pagerSemantics(modifierLazyLayoutSemantics, pagerState, z12, coroutineScope, z2).then(modifierLazyLayoutBeyondBoundsModifier), pagerState, orientation, overscrollEffect, z2, z, pagerWrapperFlingBehavior, pagerState.getInternalInteractionSource(), pagerBringIntoViewSpec);
                pagerState2 = pagerState;
                nestedScrollConnection2 = nestedScrollConnection;
                LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(modifierScrollableArea, pagerState2), nestedScrollConnection2, (NestedScrollDispatcher) null, 2, (Object) null), pagerState2.getPrefetchState(), lazyLayoutMeasurePolicyM1233rememberPagerMeasurePolicy8u0NR3k, composerStartRestartGroup, 0, 0);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f3 = f5;
                i10 = i15;
            } else {
                nestedScrollConnection2 = nestedScrollConnection;
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                i10 = i;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pu8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyLayoutPagerKt.d(modifier, pagerState2, paddingValues, z, orientation, targetedFlingBehavior, z2, overscrollEffect, i10, f3, pageSize, nestedScrollConnection2, function1, horizontal, vertical, snapPosition, function4, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 100663296;
        i6 = i4 & 512;
        if (i6 != 0) {
            i5 |= 805306368;
            f2 = f;
        } else {
            f2 = f;
            if ((i2 & 805306368) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i7 = 536870912;
                } else {
                    i7 = 268435456;
                }
                i5 |= i7;
            }
        }
        if ((i3 & 6) == 0) {
            if (composerStartRestartGroup.changed(pageSize)) {
                i17 = 4;
            } else {
                i17 = 2;
            }
            i8 = i3 | i17;
        } else {
            i8 = i3;
        }
        if ((i3 & 48) == 0) {
            if (composerStartRestartGroup.changedInstance(nestedScrollConnection)) {
                i16 = 32;
            } else {
                i16 = 16;
            }
            i8 |= i16;
        }
        if ((i3 & 384) != 0) {
            i8 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i3 & 3072) != 0) {
            i8 |= composerStartRestartGroup.changed(horizontal) ? 2048 : 1024;
        }
        if ((i3 & 24576) != 0) {
            i8 |= composerStartRestartGroup.changed(vertical) ? 16384 : 8192;
        }
        if ((i3 & 196608) == 0) {
            i8 |= composerStartRestartGroup.changed(snapPosition) ? 131072 : 65536;
        }
        if ((i3 & 1572864) == 0) {
            i8 |= composerStartRestartGroup.changedInstance(function4) ? 1048576 : 524288;
        }
        i9 = i8;
        if ((i5 & 306783379) == 306783378) {
            z3 = true;
        } else {
            z3 = true;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i5 & 1)) {
            if (i18 != 0) {
                i11 = 0;
            } else {
                i11 = i;
            }
            if (i6 != 0) {
                f4 = Dp.constructor-impl(0.0f);
            } else {
                f4 = f2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-572816025, i5, i9, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:102)");
            }
            if (i11 >= 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (!z4) {
                InlineClassHelperKt.throwIllegalArgumentException("beyondViewportPageCount should be greater than or equal to 0, you selected " + i11);
            }
            i12 = i5 & 112;
            if (i12 == 32) {
                z5 = true;
            } else {
                z5 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue = new Function0() { // from class: nu8
                    public final Object invoke() {
                        return Integer.valueOf(pagerState2.getPageCount());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function0() { // from class: nu8
                    public final Object invoke() {
                        return Integer.valueOf(pagerState2.getPageCount());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            int i110 = i5 >> 3;
            i13 = i110 & 14;
            int i24 = i9 >> 15;
            i14 = i5;
            i15 = i11;
            Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda2 = rememberPagerItemProviderLambda(pagerState, function4, function1, (Function0) objRememberedValue, composerStartRestartGroup, i13 | (i24 & 112) | (i9 & 896));
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) objRememberedValue2;
            if (i12 == 32) {
                z6 = true;
            } else {
                z6 = false;
            }
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                objRememberedValue3 = new Function0() { // from class: ou8
                    public final Object invoke() {
                        return Integer.valueOf(pagerState.getPageCount());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function0() { // from class: ou8
                    public final Object invoke() {
                        return Integer.valueOf(pagerState.getPageCount());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            int i25 = i14 >> 9;
            int i26 = (i14 & 65520) | (i25 & 458752) | (i25 & 3670016) | ((i9 << 21) & 29360128);
            int i27 = i9 << 15;
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicyM1233rememberPagerMeasurePolicy8u0NR3k2 = PagerMeasurePolicyKt.m1233rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda2, pagerState, paddingValues, z, orientation, i15, f4, pageSize, horizontal, vertical, snapPosition, coroutineScope2, (Function0) objRememberedValue3, composerStartRestartGroup, i26 | (i27 & 234881024) | (i27 & 1879048192), i24 & 14);
            float f6 = f4;
            orientation2 = Orientation.Vertical;
            if (orientation == orientation2) {
                z7 = true;
            } else {
                z7 = false;
            }
            LazyLayoutSemanticState lazyLayoutSemanticStateRememberPagerSemanticState2 = PagerSemanticsKt.rememberPagerSemanticState(pagerState, z7, composerStartRestartGroup, i13);
            if (i12 == 32) {
                z8 = true;
            } else {
                z8 = false;
            }
            if ((i14 & 458752) == 131072) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = z9 | z8;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z10) {
                objRememberedValue4 = new PagerWrapperFlingBehavior(targetedFlingBehavior, pagerState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = new PagerWrapperFlingBehavior(targetedFlingBehavior, pagerState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            PagerWrapperFlingBehavior pagerWrapperFlingBehavior2 = (PagerWrapperFlingBehavior) objRememberedValue4;
            bringIntoViewSpec = (BringIntoViewSpec) composerStartRestartGroup.consume(BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec());
            if (i12 == 32) {
                z11 = true;
            } else {
                z11 = false;
            }
            zChanged = z11 | composerStartRestartGroup.changed(bringIntoViewSpec);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue5 = new PagerBringIntoViewSpec(pagerState, bringIntoViewSpec);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = new PagerBringIntoViewSpec(pagerState, bringIntoViewSpec);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            PagerBringIntoViewSpec pagerBringIntoViewSpec2 = (PagerBringIntoViewSpec) objRememberedValue5;
            if (z2) {
                composerStartRestartGroup.startReplaceGroup(-853822717);
                modifierLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.Companion, PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(pagerState, i15, composerStartRestartGroup, i13 | ((i14 >> 21) & 112)), pagerState.getBeyondBoundsInfo(), z, orientation);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-853392933);
                composerStartRestartGroup.endReplaceGroup();
                modifierLazyLayoutBeyondBoundsModifier = Modifier.Companion;
            }
            Modifier modifierLazyLayoutSemantics2 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(pagerState.getRemeasurementModifier()).then(pagerState.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda2, lazyLayoutSemanticStateRememberPagerSemanticState2, orientation, z2, z, composerStartRestartGroup, (i110 & 7168) | ((i14 >> 6) & 57344) | ((i14 << 6) & 458752));
            if (orientation == orientation2) {
                z12 = true;
            } else {
                z12 = false;
            }
            Modifier modifierScrollableArea2 = ScrollableAreaKt.scrollableArea(PagerKt.pagerSemantics(modifierLazyLayoutSemantics2, pagerState, z12, coroutineScope2, z2).then(modifierLazyLayoutBeyondBoundsModifier), pagerState, orientation, overscrollEffect, z2, z, pagerWrapperFlingBehavior2, pagerState.getInternalInteractionSource(), pagerBringIntoViewSpec2);
            pagerState2 = pagerState;
            nestedScrollConnection2 = nestedScrollConnection;
            LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda2, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(modifierScrollableArea2, pagerState2), nestedScrollConnection2, (NestedScrollDispatcher) null, 2, (Object) null), pagerState2.getPrefetchState(), lazyLayoutMeasurePolicyM1233rememberPagerMeasurePolicy8u0NR3k2, composerStartRestartGroup, 0, 0);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f3 = f6;
            i10 = i15;
        } else {
            nestedScrollConnection2 = nestedScrollConnection;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            i10 = i;
            f3 = f2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pu8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutPagerKt.d(modifier, pagerState2, paddingValues, z, orientation, targetedFlingBehavior, z2, overscrollEffect, i10, f3, pageSize, nestedScrollConnection2, function1, horizontal, vertical, snapPosition, function4, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit d(Modifier modifier, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, TargetedFlingBehavior targetedFlingBehavior, boolean z2, OverscrollEffect overscrollEffect, int i, float f, PageSize pageSize, NestedScrollConnection nestedScrollConnection, Function1 function1, Alignment.Horizontal horizontal, Alignment.Vertical vertical, SnapPosition snapPosition, Function4 function4, int i2, int i3, int i4, Composer composer, int i5) {
        m1222PagereLwUrMk(modifier, pagerState, paddingValues, z, orientation, targetedFlingBehavior, z2, overscrollEffect, i, f, pageSize, nestedScrollConnection, function1, horizontal, vertical, snapPosition, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    private static final Modifier dragDirectionDetector(Modifier modifier, final PagerState pagerState) {
        return modifier.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.Companion, pagerState, new PointerInputEventHandler() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt.dragDirectionDetector.1

            /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1", f = "LazyLayoutPager.kt", i = {}, l = {285}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PagerState $state;
                final /* synthetic */ PointerInputScope $this_pointerInput;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1", f = "LazyLayoutPager.kt", i = {0, 1, 1, 1}, l = {287, 291}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "downEvent", "upEventOrCancellation"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
                public static final class C00251 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ PagerState $state;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00251(PagerState pagerState, Continuation<? super C00251> continuation) {
                        super(2, continuation);
                        this.$state = pagerState;
                    }

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00251 c00251 = new C00251(this.$state, continuation);
                        c00251.L$0 = obj;
                        return c00251;
                    }

                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:21:0x0078  */
                    /* JADX WARN: Code duplicated, block: B:24:0x0087 A[LOOP:0: B:20:0x0076->B:24:0x0087, LOOP_END] */
                    /* JADX WARN: Code duplicated, block: B:28:0x008a A[SYNTHETIC] */
                    /* JADX WARN: Code duplicated, block: B:29:0x0084 A[SYNTHETIC] */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0064 -> B:19:0x0068). Please report as a decompilation issue!!! */
                    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                        */
                    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                        /*
                            r11 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r11.label
                            r2 = 0
                            r3 = 2
                            r4 = 0
                            r5 = 1
                            if (r1 == 0) goto L2e
                            if (r1 == r5) goto L26
                            if (r1 != r3) goto L20
                            java.lang.Object r1 = r11.L$2
                            androidx.compose.ui.input.pointer.PointerInputChange r1 = (androidx.compose.ui.input.pointer.PointerInputChange) r1
                            java.lang.Object r2 = r11.L$1
                            androidx.compose.ui.input.pointer.PointerInputChange r2 = (androidx.compose.ui.input.pointer.PointerInputChange) r2
                            java.lang.Object r5 = r11.L$0
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                            kotlin.ResultKt.throwOnFailure(r12)
                            goto L68
                        L20:
                            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                            k2d.a(r11)
                            return r2
                        L26:
                            java.lang.Object r1 = r11.L$0
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                            kotlin.ResultKt.throwOnFailure(r12)
                            goto L43
                        L2e:
                            kotlin.ResultKt.throwOnFailure(r12)
                            java.lang.Object r12 = r11.L$0
                            r1 = r12
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                            androidx.compose.ui.input.pointer.PointerEventPass r12 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                            r11.L$0 = r1
                            r11.label = r5
                            java.lang.Object r12 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(r1, r4, r12, r11)
                            if (r12 != r0) goto L43
                            goto L63
                        L43:
                            androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
                            androidx.compose.foundation.pager.PagerState r5 = r11.$state
                            androidx.compose.ui.geometry.Offset$Companion r6 = androidx.compose.ui.geometry.Offset.Companion
                            long r6 = r6.getZero-F1C5BW0()
                            r5.m1240setUpDownDifferencek4lQ0M$foundation(r6)
                            r5 = r1
                        L51:
                            if (r2 != 0) goto L98
                            androidx.compose.ui.input.pointer.PointerEventPass r1 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                            r11.L$0 = r5
                            r11.L$1 = r12
                            r11.L$2 = r2
                            r11.label = r3
                            java.lang.Object r1 = r5.awaitPointerEvent(r1, r11)
                            if (r1 != r0) goto L64
                        L63:
                            return r0
                        L64:
                            r10 = r2
                            r2 = r12
                            r12 = r1
                            r1 = r10
                        L68:
                            androidx.compose.ui.input.pointer.PointerEvent r12 = (androidx.compose.ui.input.pointer.PointerEvent) r12
                            java.util.List r6 = r12.getChanges()
                            r7 = r6
                            java.util.Collection r7 = (java.util.Collection) r7
                            int r7 = r7.size()
                            r8 = r4
                        L76:
                            if (r8 >= r7) goto L8a
                            java.lang.Object r9 = r6.get(r8)
                            androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
                            boolean r9 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r9)
                            if (r9 != 0) goto L87
                            r12 = r2
                            r2 = r1
                            goto L51
                        L87:
                            int r8 = r8 + 1
                            goto L76
                        L8a:
                            java.util.List r12 = r12.getChanges()
                            java.lang.Object r12 = r12.get(r4)
                            androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
                            r10 = r2
                            r2 = r12
                            r12 = r10
                            goto L51
                        L98:
                            androidx.compose.foundation.pager.PagerState r11 = r11.$state
                            long r0 = r2.getPosition-F1C5BW0()
                            long r2 = r12.getPosition-F1C5BW0()
                            long r0 = androidx.compose.ui.geometry.Offset.minus-MK-Hz9U(r0, r2)
                            r11.m1240setUpDownDifferencek4lQ0M$foundation(r0)
                            kotlin.Unit r11 = kotlin.Unit.INSTANCE
                            return r11
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.AnonymousClass1.C00241.C00251.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00241(PointerInputScope pointerInputScope, PagerState pagerState, Continuation<? super C00241> continuation) {
                    super(2, continuation);
                    this.$this_pointerInput = pointerInputScope;
                    this.$state = pagerState;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00241(this.$this_pointerInput, this.$state, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PointerInputScope pointerInputScope = this.$this_pointerInput;
                        C00251 c00251 = new C00251(this.$state, null);
                        this.label = 1;
                        if (ForEachGestureKt.awaitEachGesture(pointerInputScope, c00251, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00241(pointerInputScope, pagerState, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        }));
    }

    private static final Function0<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(final PagerState pagerState, Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Function1<? super Integer, ? extends Object> function1, final Function0<Integer> function0, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1052364153, i, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:257)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function4, composer, (i >> 3) & 14);
        final State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 6) & 14);
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer.changed(pagerState)) || (i & 6) == 4) | composer.changed(stateRememberUpdatedState) | composer.changed(stateRememberUpdatedState2) | ((((i & 7168) ^ 3072) > 2048 && composer.changed(function0)) || (i & 3072) == 2048);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            final State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0() { // from class: qu8
                public final Object invoke() {
                    return LazyLayoutPagerKt.rememberPagerItemProviderLambda$lambda$0$0(stateRememberUpdatedState, stateRememberUpdatedState2, function0);
                }
            });
            final State stateDerivedStateOf2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0() { // from class: ru8
                public final Object invoke() {
                    return LazyLayoutPagerKt.rememberPagerItemProviderLambda$lambda$0$1(stateDerivedStateOf, pagerState);
                }
            });
            objRememberedValue = new PropertyReference0Impl(stateDerivedStateOf2) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                public Object get() {
                    return ((State) ((CallableReference) this).receiver).getValue();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        KProperty0 kProperty0 = (KProperty0) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return kProperty0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PagerLayoutIntervalContent rememberPagerItemProviderLambda$lambda$0$0(State state, State state2, Function0 function0) {
        return new PagerLayoutIntervalContent((Function4) state.getValue(), (Function1) state2.getValue(), ((Number) function0.invoke()).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PagerLazyLayoutItemProvider rememberPagerItemProviderLambda$lambda$0$1(State state, PagerState pagerState) {
        PagerLayoutIntervalContent pagerLayoutIntervalContent = (PagerLayoutIntervalContent) state.getValue();
        return new PagerLazyLayoutItemProvider(pagerState, pagerLayoutIntervalContent, new NearestRangeKeyIndexMap(pagerState.getNearestRange$foundation(), pagerLayoutIntervalContent));
    }
}
