package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DensityKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"rememberLazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "initialFirstVisibleItemIndex", "", "initialFirstVisibleItemScrollOffset", "(IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "prefetchStrategy", "Landroidx/compose/foundation/lazy/LazyListPrefetchStrategy;", "(IILandroidx/compose/foundation/lazy/LazyListPrefetchStrategy;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "cacheWindow", "Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "EmptyLazyListMeasureResult", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "NumberOfItemsToTeleport", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyListStateKt {
    private static final LazyListMeasureResult EmptyLazyListMeasureResult = new LazyListMeasureResult(null, 0, false, 0.0f, new MeasureResult() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$EmptyLazyListMeasureResult$1
        private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
        private final int height;
        private final int width;

        public static /* synthetic */ void getAlignmentLines$annotations() {
        }

        public Map<AlignmentLine, Integer> getAlignmentLines() {
            return this.alignmentLines;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        public void placeChildren() {
        }
    }, 0.0f, false, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), DensityKt.Density$default(1.0f, 0.0f, 2, (Object) null), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, (Object) null), CollectionsKt.emptyList(), 0, 0, 0, false, Orientation.Vertical, 0, 0, null);
    private static final int NumberOfItemsToTeleport = 100;

    public static final LazyListState rememberLazyListState(final int i, final int i2, final LazyListPrefetchStrategy lazyListPrefetchStrategy, Composer composer, int i3, int i4) {
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        boolean z = true;
        if ((i4 & 4) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = LazyListPrefetchStrategyKt.LazyListPrefetchStrategy$default(0, 1, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            lazyListPrefetchStrategy = (LazyListPrefetchStrategy) objRememberedValue;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1287535208, i3, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:102)");
        }
        Object[] objArr = {lazyListPrefetchStrategy};
        Saver<LazyListState, ?> saverSaver$foundation = LazyListState.Companion.saver$foundation(lazyListPrefetchStrategy);
        boolean z2 = ((((i3 & 14) ^ 6) > 4 && composer.changed(i)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(i2)) || (i3 & 48) == 32);
        if ((((i3 & 896) ^ 384) <= 256 || !composer.changedInstance(lazyListPrefetchStrategy)) && (i3 & 384) != 256) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: vv8
                public final Object invoke() {
                    return LazyListStateKt.rememberLazyListState$lambda$2$0(i, i2, lazyListPrefetchStrategy);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.rememberSaveable(objArr, saverSaver$foundation, (Function0) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lazyListState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListState rememberLazyListState$lambda$0$0(int i, int i2) {
        return new LazyListState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListState rememberLazyListState$lambda$2$0(int i, int i2, LazyListPrefetchStrategy lazyListPrefetchStrategy) {
        return new LazyListState(i, i2, lazyListPrefetchStrategy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListState rememberLazyListState$lambda$3$0(LazyLayoutCacheWindow lazyLayoutCacheWindow, int i, int i2) {
        return new LazyListState(lazyLayoutCacheWindow, i, i2);
    }

    public static final LazyListState rememberLazyListState(final LazyLayoutCacheWindow lazyLayoutCacheWindow, final int i, final int i2, Composer composer, int i3, int i4) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1588550476, i3, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:130)");
        }
        Object[] objArr = {lazyLayoutCacheWindow};
        Saver<LazyListState, ?> saverSaver$foundation = LazyListState.Companion.saver$foundation(lazyLayoutCacheWindow);
        boolean z = true;
        boolean z2 = ((((i3 & 14) ^ 6) > 4 && composer.changed(lazyLayoutCacheWindow)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(i)) || (i3 & 48) == 32);
        if ((((i3 & 896) ^ 384) <= 256 || !composer.changed(i2)) && (i3 & 384) != 256) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function0() { // from class: wv8
                public final Object invoke() {
                    return LazyListStateKt.rememberLazyListState$lambda$3$0(lazyLayoutCacheWindow, i, i2);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.rememberSaveable(objArr, saverSaver$foundation, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lazyListState;
    }

    public static final LazyListState rememberLazyListState(final int i, final int i2, Composer composer, int i3, int i4) {
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1470655220, i3, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:78)");
        }
        Object[] objArr = new Object[0];
        Saver<LazyListState, ?> saver = LazyListState.Companion.getSaver();
        boolean z = true;
        boolean z2 = (((i3 & 14) ^ 6) > 4 && composer.changed(i)) || (i3 & 6) == 4;
        if ((((i3 & 112) ^ 48) <= 32 || !composer.changed(i2)) && (i3 & 48) != 32) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function0() { // from class: uv8
                public final Object invoke() {
                    return LazyListStateKt.rememberLazyListState$lambda$0$0(i, i2);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.rememberSaveable(objArr, saver, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lazyListState;
    }
}
