package androidx.compose.foundation.pager;

import android.os.Trace;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003H\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a\"\u0010\u001e\u001a\u00020\u001f*\u00020 2\u0006\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002\u001a\u0017\u0010'\u001a\u00020\u001f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0003H\u0082\b\"\u000e\u0010&\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"rememberPagerMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "pageCount", "rememberPagerMeasurePolicy-8u0NR3k", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "keepAroundItems", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "cacheWindowLogic", "Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "visiblePagesList", "", "Landroidx/compose/foundation/pager/PageInfo;", "DebugEnabled", "debugLog", "generateMsg", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PagerMeasurePolicyKt {
    private static final boolean DebugEnabled = false;

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void keepAroundItems(LazyLayoutMeasureScope lazyLayoutMeasureScope, CacheWindowLogic cacheWindowLogic, List<? extends PageInfo> list) {
        Trace.beginSection("compose:pager:cache_window:keepAroundItems");
        try {
            if (cacheWindowLogic.hasValidBounds() && !list.isEmpty()) {
                int index = ((PageInfo) CollectionsKt.first(list)).getIndex();
                int index2 = ((PageInfo) CollectionsKt.last(list)).getIndex();
                for (int prefetchWindowStartLine = cacheWindowLogic.getPrefetchWindowStartLine(); prefetchWindowStartLine < index; prefetchWindowStartLine++) {
                    lazyLayoutMeasureScope.compose(prefetchWindowStartLine);
                }
                int i = index2 + 1;
                int prefetchWindowEndLine = cacheWindowLogic.getPrefetchWindowEndLine();
                if (i <= prefetchWindowEndLine) {
                    while (true) {
                        lazyLayoutMeasureScope.compose(i);
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

    /* JADX WARN: Code duplicated, block: B:100:0x012b  */
    /* JADX WARN: Code duplicated, block: B:103:0x0130 A[PHI: r3
      0x0130: PHI (r3v20 int) = (r3v18 int), (r3v21 int) binds: [B:102:0x012e, B:98:0x0128] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:104:0x0132  */
    /* JADX WARN: Code duplicated, block: B:107:0x0142  */
    /* JADX WARN: Code duplicated, block: B:109:0x014a  */
    /* JADX WARN: Code duplicated, block: B:112:0x0169  */
    /* JADX WARN: Code duplicated, block: B:45:0x0090 A[PHI: r4
      0x0090: PHI (r4v23 androidx.compose.ui.Alignment$Horizontal) = (r4v21 androidx.compose.ui.Alignment$Horizontal), (r4v24 androidx.compose.ui.Alignment$Horizontal) binds: [B:44:0x008e, B:40:0x0088] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:49:0x009e  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:55:0x00ac A[PHI: r9
      0x00ac: PHI (r9v13 androidx.compose.ui.Alignment$Vertical) = (r9v10 androidx.compose.ui.Alignment$Vertical), (r9v14 androidx.compose.ui.Alignment$Vertical) binds: [B:54:0x00aa, B:50:0x00a4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:56:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:62:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00c8 A[PHI: r12
      0x00c8: PHI (r12v11 float) = (r12v9 float), (r12v12 float) binds: [B:64:0x00c6, B:60:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:69:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:72:0x00df  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e4 A[PHI: r13
      0x00e4: PHI (r13v11 androidx.compose.foundation.pager.PageSize) = (r13v9 androidx.compose.foundation.pager.PageSize), (r13v12 androidx.compose.foundation.pager.PageSize) binds: [B:74:0x00e2, B:70:0x00dc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:76:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:82:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:85:0x00fe A[PHI: r14
      0x00fe: PHI (r14v11 androidx.compose.foundation.gestures.snapping.SnapPosition) = 
      (r14v8 androidx.compose.foundation.gestures.snapping.SnapPosition)
      (r14v12 androidx.compose.foundation.gestures.snapping.SnapPosition)
     binds: [B:84:0x00fc, B:80:0x00f5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:86:0x0100  */
    /* JADX WARN: Code duplicated, block: B:89:0x010a  */
    /* JADX WARN: Code duplicated, block: B:91:0x0110  */
    /* JADX WARN: Code duplicated, block: B:97:0x0122  */
    /* JADX INFO: renamed from: rememberPagerMeasurePolicy-8u0NR3k, reason: not valid java name */
    public static final LazyLayoutMeasurePolicy m1233rememberPagerMeasurePolicy8u0NR3k(Function0<PagerLazyLayoutItemProvider> function0, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, int i, float f, PageSize pageSize, Alignment.Horizontal horizontal, Alignment.Vertical vertical, SnapPosition snapPosition, CoroutineScope coroutineScope, Function0<Integer> function1, Composer composer, int i2, int i3) {
        Alignment.Horizontal horizontal2;
        boolean z2;
        Alignment.Vertical vertical2;
        boolean z3;
        float f2;
        boolean z4;
        PageSize pageSize2;
        boolean z5;
        SnapPosition snapPosition2;
        boolean z6;
        int i4;
        boolean z7;
        boolean zChanged;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1294131537, i2, i3, "androidx.compose.foundation.pager.rememberPagerMeasurePolicy (PagerMeasurePolicy.kt:61)");
        }
        boolean z8 = ((((i2 & 112) ^ 48) > 32 && composer.changed(pagerState)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(paddingValues)) || (i2 & 384) == 256) | ((((i2 & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i2 & 3072) == 2048) | ((((57344 & i2) ^ 24576) > 16384 && composer.changed(orientation.ordinal())) || (i2 & 24576) == 16384);
        if (((234881024 & i2) ^ 100663296) > 67108864) {
            horizontal2 = horizontal;
            if (composer.changed(horizontal2)) {
                z2 = true;
            }
            boolean z9 = z8 | z2;
            if (((1879048192 & i2) ^ 805306368) > 536870912) {
                vertical2 = vertical;
                if (!composer.changed(vertical2)) {
                    z3 = true;
                }
                boolean z10 = z9 | z3;
                if (((3670016 & i2) ^ 1572864) > 1048576) {
                    f2 = f;
                    if (!composer.changed(f2)) {
                        z4 = true;
                    }
                    boolean z11 = z10 | z4;
                    if (((29360128 & i2) ^ 12582912) > 8388608) {
                        pageSize2 = pageSize;
                        if (!composer.changed(pageSize2)) {
                            z5 = true;
                        }
                        boolean z12 = z11 | z5;
                        if (((i3 & 14) ^ 6) > 4) {
                            snapPosition2 = snapPosition;
                            if (!composer.changed(snapPosition2)) {
                                z6 = true;
                            }
                            boolean z13 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z12 | z6;
                            if (((458752 & i2) ^ 196608) > 131072) {
                                i4 = i;
                                if (!composer.changed(i4)) {
                                    z7 = true;
                                }
                                zChanged = z13 | z7 | composer.changed(coroutineScope);
                                objRememberedValue = composer.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1);
                                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1;
                                }
                                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy = (LazyLayoutMeasurePolicy) objRememberedValue;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                return lazyLayoutMeasurePolicy;
                            }
                            i4 = i;
                            if ((i2 & 196608) == 131072) {
                                z7 = true;
                            } else {
                                z7 = false;
                            }
                            zChanged = z13 | z7 | composer.changed(coroutineScope);
                            objRememberedValue = composer.rememberedValue();
                            if (zChanged) {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$2 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$2);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$2;
                            } else {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$3 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$3);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$3;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy2;
                        }
                        snapPosition2 = snapPosition;
                        if ((i3 & 6) == 4) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        boolean z14 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z12 | z6;
                        if (((458752 & i2) ^ 196608) > 131072) {
                            i4 = i;
                            if (!composer.changed(i4)) {
                                z7 = true;
                            }
                            zChanged = z14 | z7 | composer.changed(coroutineScope);
                            objRememberedValue = composer.rememberedValue();
                            if (zChanged) {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$4 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$4);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$4;
                            } else {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$5 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$5);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$5;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy3 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy3;
                        }
                        i4 = i;
                        if ((i2 & 196608) == 131072) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        zChanged = z14 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$6 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$6);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$6;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$7 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$7);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$7;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy4 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy4;
                    }
                    pageSize2 = pageSize;
                    if ((12582912 & i2) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    boolean z15 = z11 | z5;
                    if (((i3 & 14) ^ 6) > 4) {
                        snapPosition2 = snapPosition;
                        if (!composer.changed(snapPosition2)) {
                            z6 = true;
                        }
                        boolean z16 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z15 | z6;
                        if (((458752 & i2) ^ 196608) > 131072) {
                            i4 = i;
                            if (!composer.changed(i4)) {
                                z7 = true;
                            }
                            zChanged = z16 | z7 | composer.changed(coroutineScope);
                            objRememberedValue = composer.rememberedValue();
                            if (zChanged) {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$8 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$8);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$8;
                            } else {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$9 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$9);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$9;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy5 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy5;
                        }
                        i4 = i;
                        if ((i2 & 196608) == 131072) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        zChanged = z16 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$10 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$10);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$10;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy6 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy6;
                    }
                    snapPosition2 = snapPosition;
                    if ((i3 & 6) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z17 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z15 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z17 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$12 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$12);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$12;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$13 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$13);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$13;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy7 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy7;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z17 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$14 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$14);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$14;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$15 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$15);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$15;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy8 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy8;
                }
                f2 = f;
                if ((1572864 & i2) == 1048576) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z18 = z10 | z4;
                if (((29360128 & i2) ^ 12582912) > 8388608) {
                    pageSize2 = pageSize;
                    if (!composer.changed(pageSize2)) {
                        z5 = true;
                    }
                    boolean z19 = z18 | z5;
                    if (((i3 & 14) ^ 6) > 4) {
                        snapPosition2 = snapPosition;
                        if (!composer.changed(snapPosition2)) {
                            z6 = true;
                        }
                        boolean z110 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z19 | z6;
                        if (((458752 & i2) ^ 196608) > 131072) {
                            i4 = i;
                            if (!composer.changed(i4)) {
                                z7 = true;
                            }
                            zChanged = z110 | z7 | composer.changed(coroutineScope);
                            objRememberedValue = composer.rememberedValue();
                            if (zChanged) {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$16 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$16);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$16;
                            } else {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$17 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$17);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$17;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy9 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy9;
                        }
                        i4 = i;
                        if ((i2 & 196608) == 131072) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        zChanged = z110 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$18 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$18);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$18;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$19 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$19);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$19;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy10 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy10;
                    }
                    snapPosition2 = snapPosition;
                    if ((i3 & 6) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z111 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z19 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z111 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$110);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$110;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy11;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z111 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$112);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$112;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$113);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy12 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy12;
                }
                pageSize2 = pageSize;
                if ((12582912 & i2) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z112 = z18 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                    snapPosition2 = snapPosition;
                    if (!composer.changed(snapPosition2)) {
                        z6 = true;
                    }
                    boolean z113 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z112 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z113 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$114);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$114;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$115);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$115;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy13 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy13;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z113 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$116);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$116;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$117);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$117;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy14 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy14;
                }
                snapPosition2 = snapPosition;
                if ((i3 & 6) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z114 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z112 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z114 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$118);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$118;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$119);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy15 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy15;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z114 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1110);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1110;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy16 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy16;
            }
            vertical2 = vertical;
            if ((805306368 & i2) == 536870912) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z115 = z9 | z3;
            if (((3670016 & i2) ^ 1572864) > 1048576) {
                f2 = f;
                if (!composer.changed(f2)) {
                    z4 = true;
                }
                boolean z116 = z115 | z4;
                if (((29360128 & i2) ^ 12582912) > 8388608) {
                    pageSize2 = pageSize;
                    if (!composer.changed(pageSize2)) {
                        z5 = true;
                    }
                    boolean z117 = z116 | z5;
                    if (((i3 & 14) ^ 6) > 4) {
                        snapPosition2 = snapPosition;
                        if (!composer.changed(snapPosition2)) {
                            z6 = true;
                        }
                        boolean z118 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z117 | z6;
                        if (((458752 & i2) ^ 196608) > 131072) {
                            i4 = i;
                            if (!composer.changed(i4)) {
                                z7 = true;
                            }
                            zChanged = z118 | z7 | composer.changed(coroutineScope);
                            objRememberedValue = composer.rememberedValue();
                            if (zChanged) {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1112);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1112;
                            } else {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1113);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1113;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy17 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy17;
                        }
                        i4 = i;
                        if ((i2 & 196608) == 131072) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        zChanged = z118 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1114);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1114;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1115);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1115;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy18 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy18;
                    }
                    snapPosition2 = snapPosition;
                    if ((i3 & 6) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z119 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z117 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z119 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1116);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1116;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1117);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1117;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy19 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy19;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z119 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1118);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1118;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1119);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy110;
                }
                pageSize2 = pageSize;
                if ((12582912 & i2) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z1110 = z116 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                    snapPosition2 = snapPosition;
                    if (!composer.changed(snapPosition2)) {
                        z6 = true;
                    }
                    boolean z1111 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1110 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z1111 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11110);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11110;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy111;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z1111 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11112);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11112;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11113);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy112 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy112;
                }
                snapPosition2 = snapPosition;
                if ((i3 & 6) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1112 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1110 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z1112 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11114);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11114;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11115);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy113;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z1112 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11116);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11116;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11117);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy114;
            }
            f2 = f;
            if ((1572864 & i2) == 1048576) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z1113 = z115 | z4;
            if (((29360128 & i2) ^ 12582912) > 8388608) {
                pageSize2 = pageSize;
                if (!composer.changed(pageSize2)) {
                    z5 = true;
                }
                boolean z1114 = z1113 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                    snapPosition2 = snapPosition;
                    if (!composer.changed(snapPosition2)) {
                        z6 = true;
                    }
                    boolean z1115 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1114 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z1115 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11118);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11118;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11119);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11119;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy115;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z1115 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111110);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111110;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy116 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy116;
                }
                snapPosition2 = snapPosition;
                if ((i3 & 6) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1116 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1114 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z1116 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111112);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111112;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111113);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy117;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z1116 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111114);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111114;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111115);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111115;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy118 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy118;
            }
            pageSize2 = pageSize;
            if ((12582912 & i2) == 8388608) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z1117 = z1113 | z5;
            if (((i3 & 14) ^ 6) > 4) {
                snapPosition2 = snapPosition;
                if (!composer.changed(snapPosition2)) {
                    z6 = true;
                }
                boolean z1118 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1117 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z1118 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111116);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111116;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111117);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111117;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy119;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z1118 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111118);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111118;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111119);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111119;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy1110;
            }
            snapPosition2 = snapPosition;
            if ((i3 & 6) == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z1119 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1117 | z6;
            if (((458752 & i2) ^ 196608) > 131072) {
                i4 = i;
                if (!composer.changed(i4)) {
                    z7 = true;
                }
                zChanged = z1119 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111110);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111110;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy1111;
            }
            i4 = i;
            if ((i2 & 196608) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChanged = z1119 | z7 | composer.changed(coroutineScope);
            objRememberedValue = composer.rememberedValue();
            if (zChanged) {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111112);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111112;
            } else {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111113);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111113;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1112 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1112;
        }
        horizontal2 = horizontal;
        if ((100663296 & i2) == 67108864) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z20 = z8 | z2;
        if (((1879048192 & i2) ^ 805306368) > 536870912) {
            vertical2 = vertical;
            if (!composer.changed(vertical2)) {
                z3 = true;
            }
            boolean z1120 = z20 | z3;
            if (((3670016 & i2) ^ 1572864) > 1048576) {
                f2 = f;
                if (!composer.changed(f2)) {
                    z4 = true;
                }
                boolean z11110 = z1120 | z4;
                if (((29360128 & i2) ^ 12582912) > 8388608) {
                    pageSize2 = pageSize;
                    if (!composer.changed(pageSize2)) {
                        z5 = true;
                    }
                    boolean z11111 = z11110 | z5;
                    if (((i3 & 14) ^ 6) > 4) {
                        snapPosition2 = snapPosition;
                        if (!composer.changed(snapPosition2)) {
                            z6 = true;
                        }
                        boolean z11112 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z11111 | z6;
                        if (((458752 & i2) ^ 196608) > 131072) {
                            i4 = i;
                            if (!composer.changed(i4)) {
                                z7 = true;
                            }
                            zChanged = z11112 | z7 | composer.changed(coroutineScope);
                            objRememberedValue = composer.rememberedValue();
                            if (zChanged) {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111114);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111114;
                            } else {
                                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111115);
                                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111115;
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            return lazyLayoutMeasurePolicy1113;
                        }
                        i4 = i;
                        if ((i2 & 196608) == 131072) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        zChanged = z11112 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111116);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111116;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111117);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111117;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy1114;
                    }
                    snapPosition2 = snapPosition;
                    if ((i3 & 6) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z11113 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z11111 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z11113 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111118);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111118;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111119);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111119;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy1115;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z11113 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111110);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111110;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1116 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy1116;
                }
                pageSize2 = pageSize;
                if ((12582912 & i2) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z11114 = z11110 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                    snapPosition2 = snapPosition;
                    if (!composer.changed(snapPosition2)) {
                        z6 = true;
                    }
                    boolean z11115 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z11114 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z11115 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111112);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111112;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111113);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111113;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy1117;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z11115 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111114);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111114;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111115);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1118 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy1118;
                }
                snapPosition2 = snapPosition;
                if ((i3 & 6) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z11116 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z11114 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z11116 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111116);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111116;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111117);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111117;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy1119;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z11116 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111118);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111118;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111119);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111119;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11110;
            }
            f2 = f;
            if ((1572864 & i2) == 1048576) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z11117 = z1120 | z4;
            if (((29360128 & i2) ^ 12582912) > 8388608) {
                pageSize2 = pageSize;
                if (!composer.changed(pageSize2)) {
                    z5 = true;
                }
                boolean z11118 = z11117 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                    snapPosition2 = snapPosition;
                    if (!composer.changed(snapPosition2)) {
                        z6 = true;
                    }
                    boolean z11119 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z11118 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z11119 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111110);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111110;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy11111;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z11119 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111112);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111112;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111113);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11112 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy11112;
                }
                snapPosition2 = snapPosition;
                if ((i3 & 6) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z111110 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z11118 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z111110 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111114);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111114;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111115);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy11113;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z111110 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111116);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111116;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111117);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11114;
            }
            pageSize2 = pageSize;
            if ((12582912 & i2) == 8388608) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z111111 = z11117 | z5;
            if (((i3 & 14) ^ 6) > 4) {
                snapPosition2 = snapPosition;
                if (!composer.changed(snapPosition2)) {
                    z6 = true;
                }
                boolean z111112 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z111111 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z111112 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111118);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111118;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111119);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy11115;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z111112 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111110);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111110;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11116 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11116;
            }
            snapPosition2 = snapPosition;
            if ((i3 & 6) == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z111113 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z111111 | z6;
            if (((458752 & i2) ^ 196608) > 131072) {
                i4 = i;
                if (!composer.changed(i4)) {
                    z7 = true;
                }
                zChanged = z111113 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111112);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111112;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111113);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111113;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy11117;
            }
            i4 = i;
            if ((i2 & 196608) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChanged = z111113 | z7 | composer.changed(coroutineScope);
            objRememberedValue = composer.rememberedValue();
            if (zChanged) {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111114);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111114;
            } else {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111115);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111115;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11118 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy11118;
        }
        vertical2 = vertical;
        if ((805306368 & i2) == 536870912) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z1121 = z20 | z3;
        if (((3670016 & i2) ^ 1572864) > 1048576) {
            f2 = f;
            if (!composer.changed(f2)) {
                z4 = true;
            }
            boolean z111114 = z1121 | z4;
            if (((29360128 & i2) ^ 12582912) > 8388608) {
                pageSize2 = pageSize;
                if (!composer.changed(pageSize2)) {
                    z5 = true;
                }
                boolean z111115 = z111114 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                    snapPosition2 = snapPosition;
                    if (!composer.changed(snapPosition2)) {
                        z6 = true;
                    }
                    boolean z111116 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z111115 | z6;
                    if (((458752 & i2) ^ 196608) > 131072) {
                        i4 = i;
                        if (!composer.changed(i4)) {
                            z7 = true;
                        }
                        zChanged = z111116 | z7 | composer.changed(coroutineScope);
                        objRememberedValue = composer.rememberedValue();
                        if (zChanged) {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111116);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111116;
                        } else {
                            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111117);
                            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111117;
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        return lazyLayoutMeasurePolicy11119;
                    }
                    i4 = i;
                    if ((i2 & 196608) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChanged = z111116 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111118);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111118;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111119);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111119;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111110 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111110;
                }
                snapPosition2 = snapPosition;
                if ((i3 & 6) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z111117 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z111115 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z111117 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111110);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111110;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111111);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111111;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111111;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z111117 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111112);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111112;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111113);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111113;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111112 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111112;
            }
            pageSize2 = pageSize;
            if ((12582912 & i2) == 8388608) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z111118 = z111114 | z5;
            if (((i3 & 14) ^ 6) > 4) {
                snapPosition2 = snapPosition;
                if (!composer.changed(snapPosition2)) {
                    z6 = true;
                }
                boolean z111119 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z111118 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z111119 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111114);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111114;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111115);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111115;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111113 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111113;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z111119 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111116);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111116;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111117);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111114 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111114;
            }
            snapPosition2 = snapPosition;
            if ((i3 & 6) == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z1111110 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z111118 | z6;
            if (((458752 & i2) ^ 196608) > 131072) {
                i4 = i;
                if (!composer.changed(i4)) {
                    z7 = true;
                }
                zChanged = z1111110 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111118);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111118;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111119);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$11111111119;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111115 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111115;
            }
            i4 = i;
            if ((i2 & 196608) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChanged = z1111110 | z7 | composer.changed(coroutineScope);
            objRememberedValue = composer.rememberedValue();
            if (zChanged) {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111110);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111110;
            } else {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111111);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111111;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111116 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy111116;
        }
        f2 = f;
        if ((1572864 & i2) == 1048576) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z1111111 = z1121 | z4;
        if (((29360128 & i2) ^ 12582912) > 8388608) {
            pageSize2 = pageSize;
            if (!composer.changed(pageSize2)) {
                z5 = true;
            }
            boolean z1111112 = z1111111 | z5;
            if (((i3 & 14) ^ 6) > 4) {
                snapPosition2 = snapPosition;
                if (!composer.changed(snapPosition2)) {
                    z6 = true;
                }
                boolean z1111113 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1111112 | z6;
                if (((458752 & i2) ^ 196608) > 131072) {
                    i4 = i;
                    if (!composer.changed(i4)) {
                        z7 = true;
                    }
                    zChanged = z1111113 | z7 | composer.changed(coroutineScope);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged) {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111112);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111112;
                    } else {
                        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111113);
                        objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111113;
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111117 = (LazyLayoutMeasurePolicy) objRememberedValue;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return lazyLayoutMeasurePolicy111117;
                }
                i4 = i;
                if ((i2 & 196608) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChanged = z1111113 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111114);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111114;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111115);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111115;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111118 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111118;
            }
            snapPosition2 = snapPosition;
            if ((i3 & 6) == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z1111114 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1111112 | z6;
            if (((458752 & i2) ^ 196608) > 131072) {
                i4 = i;
                if (!composer.changed(i4)) {
                    z7 = true;
                }
                zChanged = z1111114 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111116);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111116;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111117);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111117;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111119 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy111119;
            }
            i4 = i;
            if ((i2 & 196608) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChanged = z1111114 | z7 | composer.changed(coroutineScope);
            objRememberedValue = composer.rememberedValue();
            if (zChanged) {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111118 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111118);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111118;
            } else {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111119 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111119);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$111111111119;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111110 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1111110;
        }
        pageSize2 = pageSize;
        if ((12582912 & i2) == 8388608) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z1111115 = z1111111 | z5;
        if (((i3 & 14) ^ 6) > 4) {
            snapPosition2 = snapPosition;
            if (!composer.changed(snapPosition2)) {
                z6 = true;
            }
            boolean z1111116 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1111115 | z6;
            if (((458752 & i2) ^ 196608) > 131072) {
                i4 = i;
                if (!composer.changed(i4)) {
                    z7 = true;
                }
                zChanged = z1111116 | z7 | composer.changed(coroutineScope);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111110 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111110);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111110;
                } else {
                    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111111 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111111);
                    objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111111;
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111111 = (LazyLayoutMeasurePolicy) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return lazyLayoutMeasurePolicy1111111;
            }
            i4 = i;
            if ((i2 & 196608) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChanged = z1111116 | z7 | composer.changed(coroutineScope);
            objRememberedValue = composer.rememberedValue();
            if (zChanged) {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111112 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111112);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111112;
            } else {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111113 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111113);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111113;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111112 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1111112;
        }
        snapPosition2 = snapPosition;
        if ((i3 & 6) == 4) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z1111117 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function1)) || (i3 & 384) == 256) | z1111115 | z6;
        if (((458752 & i2) ^ 196608) > 131072) {
            i4 = i;
            if (!composer.changed(i4)) {
                z7 = true;
            }
            zChanged = z1111117 | z7 | composer.changed(coroutineScope);
            objRememberedValue = composer.rememberedValue();
            if (zChanged) {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111114 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111114);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111114;
            } else {
                PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111115 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111115);
                objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111115;
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111113 = (LazyLayoutMeasurePolicy) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return lazyLayoutMeasurePolicy1111113;
        }
        i4 = i;
        if ((i2 & 196608) == 131072) {
            z7 = true;
        } else {
            z7 = false;
        }
        zChanged = z1111117 | z7 | composer.changed(coroutineScope);
        objRememberedValue = composer.rememberedValue();
        if (zChanged) {
            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111116 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111116);
            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111116;
        } else {
            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111117 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function1, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111117);
            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1111111111117;
        }
        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111114 = (LazyLayoutMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lazyLayoutMeasurePolicy1111114;
    }
}
