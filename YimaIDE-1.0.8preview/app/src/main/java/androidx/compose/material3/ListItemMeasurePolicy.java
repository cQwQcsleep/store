package androidx.compose.material3;

import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ(\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J(\u0010\u0013\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016J(\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J(\u0010\u0016\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016JV\u0010\u0017\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000f2,\u0010\u0018\u001a(\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001cH\u0002JV\u0010\u001d\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000f2,\u0010\u0018\u001a(\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001cH\u0002¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/ListItemMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "minIntrinsicHeight", "minIntrinsicWidth", "calculateIntrinsicWidth", "intrinsicMeasure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lkotlin/ExtensionFunctionType;", "calculateIntrinsicHeight", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class ListItemMeasurePolicy implements MultiContentMeasurePolicy {
    private final int calculateIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int iIntValue;
        int iIntValue2;
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        int iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(i, intrinsicMeasureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(ListItemKt.getListItemStartPadding() + ListItemKt.getListItemEndPadding())));
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list5);
        if (intrinsicMeasurable != null) {
            iIntValue = ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(iSubtractConstraintSafely))).intValue();
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue = 0;
        }
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list6);
        if (intrinsicMeasurable2 != null) {
            iIntValue2 = ((Number) function2.invoke(intrinsicMeasurable2, Integer.valueOf(iSubtractConstraintSafely))).intValue();
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable2.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue2 = 0;
        }
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3);
        int iIntValue3 = intrinsicMeasurable3 != null ? ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(iSubtractConstraintSafely))).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2);
        int iIntValue4 = intrinsicMeasurable4 != null ? ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(iSubtractConstraintSafely))).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list4);
        int iIntValue5 = intrinsicMeasurable5 != null ? ((Number) function2.invoke(intrinsicMeasurable5, Integer.valueOf(iSubtractConstraintSafely))).intValue() : 0;
        int iM612invokeZLSjz4$material3 = ListItemType.INSTANCE.m612invokeZLSjz4$material3(iIntValue3 > 0, iIntValue5 > 0, ListItemKt.isSupportingMultilineHeuristic(intrinsicMeasureScope, iIntValue5));
        return ListItemKt.m597calculateHeightN4Jib3Y(intrinsicMeasureScope, iIntValue, iIntValue2, iIntValue4, iIntValue3, iIntValue5, iM612invokeZLSjz4$material3, intrinsicMeasureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(ListItemKt.m599verticalPaddingyh95HIg(iM612invokeZLSjz4$material3) * 2.0f)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
    }

    private final int calculateIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list5);
        int iIntValue = intrinsicMeasurable != null ? ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(i))).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list6);
        int iIntValue2 = intrinsicMeasurable2 != null ? ((Number) function2.invoke(intrinsicMeasurable2, Integer.valueOf(i))).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2);
        int iIntValue3 = intrinsicMeasurable3 != null ? ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(i))).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3);
        int iIntValue4 = intrinsicMeasurable4 != null ? ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(i))).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(list4);
        return ListItemKt.m598calculateWidthyeHjK3Y(intrinsicMeasureScope, iIntValue, iIntValue2, iIntValue3, iIntValue4, intrinsicMeasurable5 != null ? ((Number) function2.invoke(intrinsicMeasurable5, Integer.valueOf(i))).intValue() : 0, intrinsicMeasureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(ListItemKt.getListItemStartPadding() + ListItemKt.getListItemEndPadding())), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicHeight(intrinsicMeasureScope, list, i, AnonymousClass1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicWidth(intrinsicMeasureScope, list, i, C00511.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo127measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        long j2;
        Placeable placeableMo4605measureBRTryo0;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        List<? extends Measurable> list6 = list.get(4);
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        float listItemStartPadding = ListItemKt.getListItemStartPadding();
        float listItemEndPadding = ListItemKt.getListItemEndPadding();
        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(listItemStartPadding + listItemEndPadding));
        Measurable measurable = (Measurable) CollectionsKt.firstOrNull(list5);
        int iMinIntrinsicWidth = measurable != null ? measurable.minIntrinsicWidth(Constraints.m5974getMaxHeightimpl(j)) : 0;
        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull(list6);
        int iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(Constraints.m5975getMaxWidthimpl(jM5965copyZbe2FdA$default), iMinIntrinsicWidth + (measurable2 != null ? measurable2.minIntrinsicWidth(Constraints.m5974getMaxHeightimpl(j)) : 0) + iMo4551roundToPx0680j_4);
        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull(list4);
        boolean zIsSupportingMultilineHeuristic = ListItemKt.isSupportingMultilineHeuristic(measureScope, measurable3 != null ? measurable3.minIntrinsicHeight(iSubtractConstraintSafely) : 0);
        ListItemType.Companion companion = ListItemType.INSTANCE;
        long jM5994offsetNN6EwU = ConstraintsKt.m5994offsetNN6EwU(jM5965copyZbe2FdA$default, -iMo4551roundToPx0680j_4, -measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(ListItemKt.m599verticalPaddingyh95HIg(companion.m612invokeZLSjz4$material3(CollectionsKt.firstOrNull(list3) != null, CollectionsKt.firstOrNull(list4) != null, zIsSupportingMultilineHeuristic)) * 2.0f)));
        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull(list5);
        Placeable placeableMo4605measureBRTryo1 = measurable4 != null ? measurable4.mo4605measureBRTryo0(jM5994offsetNN6EwU) : null;
        int widthOrZero = LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1);
        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull(list6);
        if (measurable5 != null) {
            j2 = jM5994offsetNN6EwU;
            placeableMo4605measureBRTryo0 = measurable5.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero, 0, 2, null));
        } else {
            j2 = jM5994offsetNN6EwU;
            placeableMo4605measureBRTryo0 = null;
        }
        int widthOrZero2 = widthOrZero + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0);
        Measurable measurable6 = (Measurable) CollectionsKt.firstOrNull(list2);
        Placeable placeableMo4605measureBRTryo2 = measurable6 != null ? measurable6.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero2, 0, 2, null)) : null;
        int heightOrZero = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo2);
        Measurable measurable7 = (Measurable) CollectionsKt.firstOrNull(list4);
        Placeable placeableMo4605measureBRTryo3 = measurable7 != null ? measurable7.mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(j2, -widthOrZero2, -heightOrZero)) : null;
        int heightOrZero2 = heightOrZero + LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo3);
        boolean z = (placeableMo4605measureBRTryo3 == null || placeableMo4605measureBRTryo3.get(AlignmentLineKt.getFirstBaseline()) == placeableMo4605measureBRTryo3.get(AlignmentLineKt.getLastBaseline())) ? false : true;
        Measurable measurable8 = (Measurable) CollectionsKt.firstOrNull(list3);
        Placeable placeableMo4605measureBRTryo4 = measurable8 != null ? measurable8.mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(j2, -widthOrZero2, -heightOrZero2)) : null;
        int iM612invokeZLSjz4$material3 = companion.m612invokeZLSjz4$material3(placeableMo4605measureBRTryo4 != null, placeableMo4605measureBRTryo3 != null, z);
        float fM599verticalPaddingyh95HIg = ListItemKt.m599verticalPaddingyh95HIg(iM612invokeZLSjz4$material3);
        Placeable placeable = placeableMo4605measureBRTryo3;
        return ListItemKt.place(measureScope, ListItemKt.m598calculateWidthyeHjK3Y(measureScope, LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo2), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo4), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo3), iMo4551roundToPx0680j_4, j), ListItemKt.m597calculateHeightN4Jib3Y(measureScope, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo1), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo0), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo2), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo4), LayoutUtilKt.getHeightOrZero(placeable), iM612invokeZLSjz4$material3, measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(fM599verticalPaddingyh95HIg * 2.0f)), j), placeableMo4605measureBRTryo1, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo2, placeableMo4605measureBRTryo4, placeable, ListItemType.m604equalsimpl0(iM612invokeZLSjz4$material3, companion.m610getThreeLineAlXitO8()), measureScope.mo4551roundToPx0680j_4(listItemStartPadding), measureScope.mo4551roundToPx0680j_4(listItemEndPadding), measureScope.mo4551roundToPx0680j_4(fM599verticalPaddingyh95HIg));
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicHeight(intrinsicMeasureScope, list, i, C00521.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicWidth(intrinsicMeasureScope, list, i, C00531.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicHeight$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, IntrinsicMeasurable.class, "maxIntrinsicHeight", "maxIntrinsicHeight(I)I", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i) {
            return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(i));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class C00511 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C00511 INSTANCE = new C00511();

        public C00511() {
            super(2, IntrinsicMeasurable.class, "maxIntrinsicWidth", "maxIntrinsicWidth(I)I", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i) {
            return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(i));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicHeight$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class C00521 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C00521 INSTANCE = new C00521();

        public C00521() {
            super(2, IntrinsicMeasurable.class, "minIntrinsicHeight", "minIntrinsicHeight(I)I", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i) {
            return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(i));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class C00531 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C00531 INSTANCE = new C00531();

        public C00531() {
            super(2, IntrinsicMeasurable.class, "minIntrinsicWidth", "minIntrinsicWidth(I)I", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i) {
            return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(i));
        }
    }
}
