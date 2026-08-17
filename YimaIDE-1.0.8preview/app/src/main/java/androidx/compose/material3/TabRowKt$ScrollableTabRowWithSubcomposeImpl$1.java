package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabRowKt$ScrollableTabRowWithSubcomposeImpl$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function2<Composer, Integer, Unit> $divider;
    final /* synthetic */ float $edgePadding;
    final /* synthetic */ Function3<List<TabPosition>, Composer, Integer, Unit> $indicator;
    final /* synthetic */ ScrollState $scrollState;
    final /* synthetic */ int $selectedTabIndex;
    final /* synthetic */ Function2<Composer, Integer, Unit> $tabs;

    /* JADX WARN: Multi-variable type inference failed */
    public TabRowKt$ScrollableTabRowWithSubcomposeImpl$1(ScrollState scrollState, float f, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function4, int i) {
        this.$scrollState = scrollState;
        this.$edgePadding = f;
        this.$tabs = function2;
        this.$divider = function3;
        this.$indicator = function4;
        this.$selectedTabIndex = i;
    }

    public static Unit a(int i, List list, SubcomposeMeasureScope subcomposeMeasureScope, Function2 function2, ScrollableTabData scrollableTabData, int i2, List list2, Constraints constraints, int i3, int i4, final Function3 function3, Placeable.PlacementScope placementScope) {
        final ArrayList arrayList = new ArrayList();
        int size = list.size();
        int width = i;
        for (int i5 = 0; i5 < size; i5++) {
            Placeable placeable = (Placeable) list.get(i5);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, width, 0, 0.0f, 4, null);
            arrayList.add(new TabPosition(subcomposeMeasureScope.mo4554toDpu2uoSUM(width), subcomposeMeasureScope.mo4554toDpu2uoSUM(placeable.getWidth()), ((Dp) list2.get(i5)).m6036unboximpl(), null));
            width += placeable.getWidth();
        }
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(TabSlots.Divider, function2);
        int size2 = listSubcompose.size();
        for (int i6 = 0; i6 < size2; i6++) {
            Placeable placeableMo4605measureBRTryo0 = listSubcompose.get(i6).mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(constraints.getValue(), i3, i3, 0, 0, 8, null));
            Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4605measureBRTryo0, 0, i4 - placeableMo4605measureBRTryo0.getHeight(), 0.0f, 4, null);
        }
        List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(2125766411, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1$2$3
            public final void invoke(Composer composer, int i7) {
                if (!composer.shouldExecute((i7 & 3) != 2, i7 & 1)) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2125766411, i7, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:920)");
                }
                function3.invoke(arrayList, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }
        }));
        int size3 = listSubcompose2.size();
        for (int i7 = 0; i7 < size3; i7++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, listSubcompose2.get(i7).mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(i3, i4)), 0, 0, 0.0f, 4, null);
        }
        scrollableTabData.onLaidOut(subcomposeMeasureScope, i, arrayList, i2);
        return Unit.INSTANCE;
    }

    public static MeasureResult b(float f, Function2 function2, final Function2 function3, final ScrollableTabData scrollableTabData, final int i, final Function3 function4, final SubcomposeMeasureScope subcomposeMeasureScope, final Constraints constraints) {
        int iMo4551roundToPx0680j_4 = subcomposeMeasureScope.mo4551roundToPx0680j_4(TabRowDefaults.INSTANCE.m998getScrollableTabRowMinTabWidthD9Ej5fM());
        final int iMo4551roundToPx0680j_5 = subcomposeMeasureScope.mo4551roundToPx0680j_4(f);
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function2);
        Integer numValueOf = 0;
        List<Measurable> list = listSubcompose;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), listSubcompose.get(i2).maxIntrinsicHeight(Integer.MAX_VALUE)));
        }
        final int iIntValue = numValueOf.intValue();
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(constraints.getValue(), iMo4551roundToPx0680j_4, 0, iIntValue, iIntValue, 2, null);
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Measurable measurable = listSubcompose.get(i3);
            Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
            float fM6022constructorimpl = Dp.m6022constructorimpl(subcomposeMeasureScope.mo4554toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(placeableMo4605measureBRTryo0.getHeight()), placeableMo4605measureBRTryo0.getWidth())) - Dp.m6022constructorimpl(TabKt.getHorizontalTextPadding() * 2.0f));
            arrayList.add(placeableMo4605measureBRTryo0);
            arrayList2.add(Dp.m6020boximpl(fM6022constructorimpl));
        }
        Integer numValueOf2 = Integer.valueOf(iMo4551roundToPx0680j_5 * 2);
        int size3 = arrayList.size();
        for (int i4 = 0; i4 < size3; i4++) {
            numValueOf2 = Integer.valueOf(numValueOf2.intValue() + ((Placeable) arrayList.get(i4)).getWidth());
        }
        final int iIntValue2 = numValueOf2.intValue();
        return MeasureScope.layout$default(subcomposeMeasureScope, iIntValue2, iIntValue, null, new Function1() { // from class: androidx.compose.material3.l4
            public final Object invoke(Object obj) {
                return TabRowKt$ScrollableTabRowWithSubcomposeImpl$1.a(iMo4551roundToPx0680j_5, arrayList, subcomposeMeasureScope, function3, scrollableTabData, i, arrayList2, constraints, iIntValue2, iIntValue, function4, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2077251399, i, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:836)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer);
            composer.updateRememberedValue(objRememberedValue);
        }
        CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
        FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composer, 6);
        boolean zChanged = composer.changed(this.$scrollState) | composer.changed(coroutineScope);
        ScrollState scrollState = this.$scrollState;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new ScrollableTabData(scrollState, coroutineScope, finiteAnimationSpecValue);
            composer.updateRememberedValue(objRememberedValue2);
        }
        final ScrollableTabData scrollableTabData = (ScrollableTabData) objRememberedValue2;
        Modifier modifierClipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, (Object) null), Alignment.INSTANCE.getCenterStart(), false, 2, (Object) null), this.$scrollState, false, (FlingBehavior) null, false, 14, (Object) null)));
        boolean zChanged2 = composer.changed(this.$edgePadding) | composer.changed(this.$tabs) | composer.changed(this.$divider) | composer.changed(this.$indicator) | composer.changedInstance(scrollableTabData) | composer.changed(this.$selectedTabIndex);
        final float f = this.$edgePadding;
        final Function2<Composer, Integer, Unit> function2 = this.$tabs;
        final Function2<Composer, Integer, Unit> function3 = this.$divider;
        final int i2 = this.$selectedTabIndex;
        final Function3<List<TabPosition>, Composer, Integer, Unit> function4 = this.$indicator;
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
            Object obj = new Function2() { // from class: androidx.compose.material3.m4
                public final Object invoke(Object obj2, Object obj3) {
                    return TabRowKt$ScrollableTabRowWithSubcomposeImpl$1.b(f, function2, function3, scrollableTabData, i2, function4, (SubcomposeMeasureScope) obj2, (Constraints) obj3);
                }
            };
            composer.updateRememberedValue(obj);
            objRememberedValue3 = obj;
        }
        SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, (Function2) objRememberedValue3, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
