package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
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
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabRowKt$TabRowWithSubcomposeImpl$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function2<Composer, Integer, Unit> $divider;
    final /* synthetic */ Function3<List<TabPosition>, Composer, Integer, Unit> $indicator;
    final /* synthetic */ Function2<Composer, Integer, Unit> $tabs;

    /* JADX WARN: Multi-variable type inference failed */
    public TabRowKt$TabRowWithSubcomposeImpl$1(Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function4) {
        this.$tabs = function2;
        this.$divider = function3;
        this.$indicator = function4;
    }

    public static Unit a(List list, SubcomposeMeasureScope subcomposeMeasureScope, Function2 function2, Ref.IntRef intRef, Constraints constraints, int i, final Function3 function3, final List list2, int i2, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i3), i3 * intRef.element, 0, 0.0f, 4, null);
        }
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(TabSlots.Divider, function2);
        int size2 = listSubcompose.size();
        for (int i4 = 0; i4 < size2; i4++) {
            Placeable placeableMo4605measureBRTryo0 = listSubcompose.get(i4).mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(constraints.getValue(), 0, 0, 0, 0, 11, null));
            Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4605measureBRTryo0, 0, i - placeableMo4605measureBRTryo0.getHeight(), 0.0f, 4, null);
        }
        List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(1918742627, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$1$1$1$3
            public final void invoke(Composer composer, int i5) {
                if (!composer.shouldExecute((i5 & 3) != 2, i5 & 1)) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1918742627, i5, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:814)");
                }
                function3.invoke(list2, composer, 0);
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
        for (int i5 = 0; i5 < size3; i5++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, listSubcompose2.get(i5).mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(i2, i)), 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    public static MeasureResult b(Function2 function2, final Function2 function3, final Function3 function4, final SubcomposeMeasureScope subcomposeMeasureScope, final Constraints constraints) {
        final int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(constraints.getValue());
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function2);
        int size = listSubcompose.size();
        final Ref.IntRef intRef = new Ref.IntRef();
        if (size > 0) {
            intRef.element = iM5975getMaxWidthimpl / size;
        }
        Integer numValueOf = 0;
        List<Measurable> list = listSubcompose;
        int size2 = list.size();
        for (int i = 0; i < size2; i++) {
            numValueOf = Integer.valueOf(Math.max(listSubcompose.get(i).maxIntrinsicHeight(intRef.element), numValueOf.intValue()));
        }
        final int iIntValue = numValueOf.intValue();
        final ArrayList arrayList = new ArrayList(listSubcompose.size());
        int size3 = list.size();
        for (int i2 = 0; i2 < size3; i2++) {
            Measurable measurable = listSubcompose.get(i2);
            long value = constraints.getValue();
            int i3 = intRef.element;
            arrayList.add(measurable.mo4605measureBRTryo0(Constraints.m5964copyZbe2FdA(value, i3, i3, iIntValue, iIntValue)));
        }
        final ArrayList arrayList2 = new ArrayList(size);
        for (int i4 = 0; i4 < size; i4++) {
            arrayList2.add(new TabPosition(Dp.m6022constructorimpl(subcomposeMeasureScope.mo4554toDpu2uoSUM(intRef.element) * i4), subcomposeMeasureScope.mo4554toDpu2uoSUM(intRef.element), ((Dp) ComparisonsKt.maxOf(Dp.m6020boximpl(Dp.m6022constructorimpl(subcomposeMeasureScope.mo4554toDpu2uoSUM(Math.min(listSubcompose.get(i4).maxIntrinsicWidth(iIntValue), intRef.element)) - Dp.m6022constructorimpl(TabKt.getHorizontalTextPadding() * 2.0f))), Dp.m6020boximpl(Dp.m6022constructorimpl(24.0f)))).m6036unboximpl(), null));
        }
        return MeasureScope.layout$default(subcomposeMeasureScope, iM5975getMaxWidthimpl, iIntValue, null, new Function1() { // from class: androidx.compose.material3.o4
            public final Object invoke(Object obj) {
                return TabRowKt$TabRowWithSubcomposeImpl$1.a(arrayList, subcomposeMeasureScope, function3, intRef, constraints, iIntValue, function4, arrayList2, iM5975getMaxWidthimpl, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1815327065, i, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:768)");
        }
        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, (Object) null);
        boolean zChanged = composer.changed(this.$tabs) | composer.changed(this.$divider) | composer.changed(this.$indicator);
        final Function2<Composer, Integer, Unit> function2 = this.$tabs;
        final Function2<Composer, Integer, Unit> function3 = this.$divider;
        final Function3<List<TabPosition>, Composer, Integer, Unit> function4 = this.$indicator;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function2() { // from class: androidx.compose.material3.p4
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt$TabRowWithSubcomposeImpl$1.b(function2, function3, function4, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        SubcomposeLayoutKt.SubcomposeLayout(modifierFillMaxWidth$default, (Function2) objRememberedValue, composer, 6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
