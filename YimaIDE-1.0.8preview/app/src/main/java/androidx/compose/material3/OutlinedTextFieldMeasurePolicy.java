package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001BC\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\"\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010!\u001a\u00020\u001eH\u0016J\"\u0010\"\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010!\u001a\u00020\u001eH\u0016J\"\u0010#\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010$\u001a\u00020\u001eH\u0016J\"\u0010%\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010$\u001a\u00020\u001eH\u0016J<\u0010&\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010$\u001a\u00020\u001e2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0(H\u0002J<\u0010)\u001a\u00020\u001e*\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020 0\u00172\u0006\u0010!\u001a\u00020\u001e2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0(H\u0002J[\u0010*\u001a\u00020\u001e*\u00020+2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u00020\u001e2\u0006\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u000203H\u0002¢\u0006\u0004\b4\u00105Jk\u00106\u001a\u00020\u001e*\u00020+2\u0006\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u001e2\u0006\u0010:\u001a\u00020\u001e2\u0006\u0010;\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020\u001e2\u0006\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u00072\u0006\u0010\n\u001a\u000203H\u0002¢\u0006\u0004\b@\u0010AJ\u009a\u0001\u0010B\u001a\u00020\u0005*\u00020C2\u0006\u0010D\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010F2\b\u0010H\u001a\u0004\u0018\u00010F2\b\u0010I\u001a\u0004\u0018\u00010F2\u0006\u0010J\u001a\u00020F2\b\u0010K\u001a\u0004\u0018\u00010F2\b\u0010L\u001a\u0004\u0018\u00010F2\u0006\u0010M\u001a\u00020F2\b\u0010N\u001a\u0004\u0018\u00010F2\u0006\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020Q2\u0006\u0010?\u001a\u00020\u00072\u0006\u0010\n\u001a\u0002032\u0006\u0010R\u001a\u000203H\u0002R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006S"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "onLabelMeasured", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "", "singleLine", "", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "horizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "<init>", "(Lkotlin/jvm/functions/Function1;ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Landroidx/compose/foundation/layout/PaddingValues;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "height", "minIntrinsicWidth", "intrinsicWidth", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "calculateWidth", "Landroidx/compose/ui/unit/Density;", "leadingPlaceableWidth", "trailingPlaceableWidth", "prefixPlaceableWidth", "suffixPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "", "calculateWidth-IzADHW4", "(Landroidx/compose/ui/unit/Density;IIIIIIIJF)I", "calculateHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "textFieldHeight", "labelHeight", "placeholderHeight", "supportingHeight", "isLabelAbove", "calculateHeight-mKXJcVc", "(Landroidx/compose/ui/unit/Density;IIIIIIIIJZF)I", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "containerPlaceable", "supportingPlaceable", "density", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "iconPadding", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    private final float horizontalIconPadding;
    private final TextFieldLabelPosition labelPosition;
    private final FloatProducer labelProgress;
    private final Function1<Size, Unit> onLabelMeasured;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    /* JADX WARN: Multi-variable type inference failed */
    private OutlinedTextFieldMeasurePolicy(Function1<? super Size, Unit> function1, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, PaddingValues paddingValues, float f) {
        this.onLabelMeasured = function1;
        this.singleLine = z;
        this.labelPosition = textFieldLabelPosition;
        this.labelProgress = floatProducer;
        this.paddingValues = paddingValues;
        this.horizontalIconPadding = f;
    }

    public static int a(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    public static int b(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    public static Unit c(OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Ref.ObjectRef objectRef, Placeable placeable6, Placeable placeable7, Placeable placeable8, MeasureScope measureScope, boolean z, float f, Placeable.PlacementScope placementScope) {
        outlinedTextFieldMeasurePolicy.place(placementScope, i, i2, placeable, placeable2, placeable3, placeable4, placeable5, (Placeable) objectRef.element, placeable6, placeable7, placeable8, measureScope.getDensity(), measureScope.getLayoutDirection(), z, f, measureScope.mo4557toPx0680j_4(outlinedTextFieldMeasurePolicy.horizontalIconPadding));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    private final int m734calculateHeightmKXJcVc(Density density, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j, boolean z, float f) {
        int iMaxOf = ComparisonsKt.maxOf(i5, new int[]{i7, i3, i4, z ? 0 : MathHelpersKt.lerp(i6, 0, f)});
        float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(this.paddingValues.calculateTopPadding-D9Ej5fM());
        if (!z) {
            fMo4557toPx0680j_4 = MathHelpersKt.lerp(fMo4557toPx0680j_4, Math.max(fMo4557toPx0680j_4, i6 / 2.0f), f);
        }
        float fMo4557toPx0680j_5 = fMo4557toPx0680j_4 + iMaxOf + density.mo4557toPx0680j_4(this.paddingValues.calculateBottomPadding-D9Ej5fM());
        if (!z) {
            i6 = 0;
        }
        return ConstraintsKt.m5991constrainHeightK40F9xA(j, i6 + Math.max(i, Math.max(i2, MathKt.roundToInt(fMo4557toPx0680j_5))) + i8);
    }

    /* JADX INFO: renamed from: calculateWidth-IzADHW4, reason: not valid java name */
    private final int m735calculateWidthIzADHW4(Density density, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, float f) {
        int i8 = i3 + i4;
        int iMax = i + Math.max(i5 + i8, Math.max(i7 + i8, MathHelpersKt.lerp(i6, 0, f))) + i2;
        PaddingValues paddingValues = this.paddingValues;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        return ConstraintsKt.m5992constrainWidthK40F9xA(j, Math.max(iMax, MathKt.roundToInt((i6 + density.mo4557toPx0680j_4(Dp.m6022constructorimpl(paddingValues.calculateLeftPadding-u2uoSUM(layoutDirection) + this.paddingValues.calculateRightPadding-u2uoSUM(layoutDirection)))) * f)));
    }

    public static int d(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public static int e(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        int iSubtractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iIntValue2;
        int i2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int iIntValue3;
        IntrinsicMeasurable intrinsicMeasurable5;
        int iIntValue4;
        int i3;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = this;
        float fInvoke = outlinedTextFieldMeasurePolicy.labelProgress.invoke();
        List<? extends IntrinsicMeasurable> list2 = list;
        int size = list2.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i4);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i4++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(i, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = ((Number) function2.invoke(intrinsicMeasurable8, Integer.valueOf(i))).intValue();
        } else {
            iSubtractConstraintSafely = i;
            iIntValue = 0;
        }
        int size2 = list2.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i5);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i5++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = ((Number) function2.invoke(intrinsicMeasurable9, Integer.valueOf(i))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size3) {
                i2 = 0;
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i6);
            i2 = 0;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.LabelId)) {
                break;
            }
            i6++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int iIntValue5 = intrinsicMeasurable10 != null ? ((Number) function2.invoke(intrinsicMeasurable10, Integer.valueOf(MathHelpersKt.lerp(iSubtractConstraintSafely, i, fInvoke)))).intValue() : i2;
        int size4 = list2.size();
        int i7 = i2;
        while (true) {
            if (i7 >= size4) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i7);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i7++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            iIntValue3 = ((Number) function2.invoke(intrinsicMeasurable11, Integer.valueOf(iSubtractConstraintSafely))).intValue();
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = i2;
        }
        int size5 = list2.size();
        int i8 = i2;
        while (true) {
            if (i8 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i8);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i8++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            iIntValue4 = ((Number) function2.invoke(intrinsicMeasurable12, Integer.valueOf(iSubtractConstraintSafely))).intValue();
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue4 = i2;
        }
        int size6 = list2.size();
        int i9 = i2;
        while (i9 < size6) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i9);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.TextFieldId)) {
                int iIntValue6 = ((Number) function2.invoke(intrinsicMeasurable13, Integer.valueOf(iSubtractConstraintSafely))).intValue();
                List<? extends IntrinsicMeasurable> list3 = list;
                int size7 = list3.size();
                int i10 = i2;
                while (true) {
                    if (i10 >= size7) {
                        i3 = iIntValue6;
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i10);
                    i3 = iIntValue6;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i10++;
                    iIntValue6 = i3;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int iIntValue7 = intrinsicMeasurable14 != null ? ((Number) function2.invoke(intrinsicMeasurable14, Integer.valueOf(iSubtractConstraintSafely))).intValue() : i2;
                int size8 = list3.size();
                int i11 = i2;
                while (true) {
                    if (i11 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    intrinsicMeasurable7 = list.get(i11);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    i11++;
                }
                IntrinsicMeasurable intrinsicMeasurable15 = intrinsicMeasurable7;
                return outlinedTextFieldMeasurePolicy.m734calculateHeightmKXJcVc(intrinsicMeasureScope, iIntValue, iIntValue2, iIntValue3, iIntValue4, i3, iIntValue5, iIntValue7, intrinsicMeasurable15 != null ? ((Number) function2.invoke(intrinsicMeasurable15, Integer.valueOf(i))).intValue() : i2, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), outlinedTextFieldMeasurePolicy.labelPosition instanceof TextFieldLabelPosition.Above, fInvoke);
            }
            i9++;
            iIntValue5 = iIntValue5;
            outlinedTextFieldMeasurePolicy = this;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return i2;
    }

    private final int intrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable7 = list.get(i2);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.TextFieldId)) {
                int iIntValue = ((Number) function2.invoke(intrinsicMeasurable7, Integer.valueOf(i))).intValue();
                List<? extends IntrinsicMeasurable> list2 = list;
                int size2 = list2.size();
                int i3 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i3 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = list.get(i3);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.LabelId)) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable8 != null ? ((Number) function2.invoke(intrinsicMeasurable8, Integer.valueOf(i))).intValue() : 0;
                int size3 = list2.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = list.get(i4);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable9 != null ? ((Number) function2.invoke(intrinsicMeasurable9, Integer.valueOf(i))).intValue() : 0;
                int size4 = list2.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = list.get(i5);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) function2.invoke(intrinsicMeasurable10, Integer.valueOf(i))).intValue() : 0;
                int size5 = list2.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = list.get(i6);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int iIntValue5 = intrinsicMeasurable11 != null ? ((Number) function2.invoke(intrinsicMeasurable11, Integer.valueOf(i))).intValue() : 0;
                int size6 = list2.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i7);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int iIntValue6 = intrinsicMeasurable12 != null ? ((Number) function2.invoke(intrinsicMeasurable12, Integer.valueOf(i))).intValue() : 0;
                int size7 = list2.size();
                for (int i8 = 0; i8 < size7; i8++) {
                    IntrinsicMeasurable intrinsicMeasurable13 = list.get(i8);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.PlaceholderId)) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                return m735calculateWidthIzADHW4(intrinsicMeasureScope, iIntValue4, iIntValue3, iIntValue5, iIntValue6, iIntValue, iIntValue2, intrinsicMeasurable14 != null ? ((Number) function2.invoke(intrinsicMeasurable14, Integer.valueOf(i))).intValue() : 0, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), this.labelProgress.invoke());
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return 0;
    }

    private final void place(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, float f, LayoutDirection layoutDirection, boolean z, float f2, float f3) {
        int i3;
        int i4;
        int i5;
        int iAlign;
        int heightOrZero = z ? LayoutUtilKt.getHeightOrZero(placeable6) : 0;
        Placeable.PlacementScope.place$default(placementScope, placeable8, 0, heightOrZero, 0.0f, 4, null);
        int heightOrZero2 = (i - LayoutUtilKt.getHeightOrZero(placeable9)) - (z ? LayoutUtilKt.getHeightOrZero(placeable6) : 0);
        int iRoundToInt = MathKt.roundToInt(this.paddingValues.calculateTopPadding-D9Ej5fM() * f);
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, heightOrZero + Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), heightOrZero2), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            if (z) {
                iAlign = 0;
            } else {
                iAlign = this.singleLine ? Alignment.INSTANCE.getCenterVertically().align(placeable6.getHeight(), heightOrZero2) : iRoundToInt;
            }
            int iLerp = MathHelpersKt.lerp(iAlign, z ? 0 : -(placeable6.getHeight() / 2), f2);
            if (z) {
                Placeable.PlacementScope.place$default(placementScope, placeable6, TextFieldImplKt.getMinimizedAlignment(this.labelPosition).align(placeable6.getWidth(), i2, layoutDirection), iLerp, 0.0f, 4, null);
            } else {
                float fCalculateStartPadding = PaddingKt.calculateStartPadding(this.paddingValues, layoutDirection) * f;
                float fCalculateEndPadding = PaddingKt.calculateEndPadding(this.paddingValues, layoutDirection) * f;
                float width = placeable == null ? fCalculateStartPadding : placeable.getWidth() + RangesKt.coerceAtLeast(fCalculateStartPadding - f3, 0.0f);
                float width2 = placeable2 == null ? fCalculateEndPadding : placeable2.getWidth() + RangesKt.coerceAtLeast(fCalculateEndPadding - f3, 0.0f);
                LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
                Placeable.PlacementScope.place$default(placementScope, placeable6, MathKt.roundToInt(MathHelpersKt.lerp(TextFieldImplKt.getExpandedAlignment(this.labelPosition).align(placeable6.getWidth(), i2 - MathKt.roundToInt(width + width2), layoutDirection) + (layoutDirection == layoutDirection2 ? width : width2), TextFieldImplKt.getMinimizedAlignment(this.labelPosition).align(placeable6.getWidth(), i2 - MathKt.roundToInt(fCalculateStartPadding + fCalculateEndPadding), layoutDirection) + (layoutDirection == layoutDirection2 ? fCalculateStartPadding : fCalculateEndPadding), f2)), iLerp, 0.0f, 4, null);
            }
        }
        if (placeable3 != null) {
            i3 = heightOrZero2;
            i4 = iRoundToInt;
            i5 = heightOrZero;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, LayoutUtilKt.getWidthOrZero(placeable), place$calculateVerticalPosition(i5, this, i3, i4, placeable6, placeable3), 0.0f, 4, null);
        } else {
            i3 = heightOrZero2;
            i4 = iRoundToInt;
            i5 = heightOrZero;
        }
        int widthOrZero = LayoutUtilKt.getWidthOrZero(placeable) + LayoutUtilKt.getWidthOrZero(placeable3);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, widthOrZero, place$calculateVerticalPosition(i5, this, i3, i4, placeable6, placeable5), 0.0f, 4, null);
        if (placeable7 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, widthOrZero, place$calculateVerticalPosition(i5, this, i3, i4, placeable6, placeable7), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, (i2 - LayoutUtilKt.getWidthOrZero(placeable2)) - placeable4.getWidth(), place$calculateVerticalPosition(i5, this, i3, i4, placeable6, placeable4), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i2 - placeable2.getWidth(), i5 + Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i3), 0.0f, 4, null);
        }
        if (placeable9 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, 0, i5 + i3, 0.0f, 4, null);
        }
    }

    private static final int place$calculateVerticalPosition(int i, OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy, int i2, int i3, Placeable placeable, Placeable placeable2) {
        if (outlinedTextFieldMeasurePolicy.singleLine) {
            i3 = Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i2);
        }
        int i4 = i + i3;
        return outlinedTextFieldMeasurePolicy.labelPosition instanceof TextFieldLabelPosition.Above ? i4 : Math.max(i4, LayoutUtilKt.getHeightOrZero(placeable) / 2);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.l3
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(OutlinedTextFieldMeasurePolicy.b((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.j3
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(OutlinedTextFieldMeasurePolicy.a((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        long j2;
        Placeable placeableMo4605measureBRTryo0;
        Measurable measurable3;
        Measurable measurable4;
        Measurable measurable5;
        int iMinIntrinsicHeight;
        List<? extends Measurable> list2;
        Measurable measurable6;
        Measurable measurable7;
        Ref.ObjectRef objectRef;
        long j3;
        long jM2967getZeroNHjbRc;
        long jM2967getZeroNHjbRc2;
        List<? extends Measurable> list3 = list;
        final float fInvoke = this.labelProgress.invoke();
        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateBottomPadding-D9Ej5fM());
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        List<? extends Measurable> list4 = list3;
        int size = list4.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                measurable = null;
                break;
            }
            measurable = list3.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i++;
        }
        Measurable measurable8 = measurable;
        Placeable placeableMo4605measureBRTryo1 = measurable8 != null ? measurable8.mo4605measureBRTryo0(jM5965copyZbe2FdA$default) : null;
        int widthOrZero = LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1);
        int iMax = Math.max(0, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo1));
        int size2 = list4.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list3.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i2++;
        }
        Measurable measurable9 = measurable2;
        if (measurable9 != null) {
            j2 = jM5965copyZbe2FdA$default;
            placeableMo4605measureBRTryo0 = measurable9.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero, 0, 2, null));
        } else {
            j2 = jM5965copyZbe2FdA$default;
            placeableMo4605measureBRTryo0 = null;
        }
        int widthOrZero2 = widthOrZero + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0);
        int iMax2 = Math.max(iMax, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo0));
        int size3 = list4.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list3.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i3++;
        }
        Measurable measurable10 = measurable3;
        Placeable placeableMo4605measureBRTryo2 = measurable10 != null ? measurable10.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero2, 0, 2, null)) : null;
        int widthOrZero3 = widthOrZero2 + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo2);
        int iMax3 = Math.max(iMax2, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo2));
        int size4 = list4.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list3.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i4++;
        }
        Measurable measurable11 = measurable4;
        Placeable placeableMo4605measureBRTryo3 = measurable11 != null ? measurable11.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero3, 0, 2, null)) : null;
        int widthOrZero4 = widthOrZero3 + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo3);
        int iMax4 = Math.max(iMax3, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo3));
        boolean z = this.labelPosition instanceof TextFieldLabelPosition.Above;
        int size5 = list4.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size5) {
                measurable5 = null;
                break;
            }
            measurable5 = list3.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), TextFieldImplKt.LabelId)) {
                break;
            }
            i5++;
        }
        Measurable measurable12 = measurable5;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        if (z) {
            iMinIntrinsicHeight = measurable12 != null ? measurable12.minIntrinsicHeight(Constraints.m5977getMinWidthimpl(j)) : 0;
        } else {
            int iMo4551roundToPx0680j_5 = measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateLeftPadding-u2uoSUM(measureScope.getLayoutDirection())) + measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateRightPadding-u2uoSUM(measureScope.getLayoutDirection()));
            Placeable placeableMo4605measureBRTryo4 = measurable12 != null ? measurable12.mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(j2, -MathHelpersKt.lerp(widthOrZero4 + iMo4551roundToPx0680j_5, iMo4551roundToPx0680j_5, fInvoke), -iMo4551roundToPx0680j_4)) : null;
            objectRef2.element = placeableMo4605measureBRTryo4;
            if (placeableMo4605measureBRTryo4 != null) {
                jM2967getZeroNHjbRc2 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(placeableMo4605measureBRTryo4.getWidth())) << 32) | (((long) Float.floatToRawIntBits(placeableMo4605measureBRTryo4.getHeight())) & 4294967295L));
            } else {
                jM2967getZeroNHjbRc2 = Size.INSTANCE.m2967getZeroNHjbRc();
            }
            this.onLabelMeasured.invoke(Size.m2946boximpl(jM2967getZeroNHjbRc2));
            iMinIntrinsicHeight = 0;
        }
        int size6 = list4.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size6) {
                list2 = list4;
                measurable6 = null;
                break;
            }
            measurable6 = list3.get(i6);
            list2 = list4;
            int i7 = size6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i6++;
            size6 = i7;
            list4 = list2;
        }
        Measurable measurable13 = measurable6;
        int iMinIntrinsicHeight2 = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m5977getMinWidthimpl(j)) : 0;
        int iMo4551roundToPx0680j_6 = z ? measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateTopPadding-D9Ej5fM()) : Math.max(LayoutUtilKt.getHeightOrZero((Placeable) objectRef2.element) / 2, measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateTopPadding-D9Ej5fM()));
        long jM5965copyZbe2FdA$default2 = Constraints.m5965copyZbe2FdA$default(ConstraintsKt.m5994offsetNN6EwU(j, -widthOrZero4, (((-iMo4551roundToPx0680j_4) - iMo4551roundToPx0680j_6) - iMinIntrinsicHeight) - iMinIntrinsicHeight2), 0, 0, 0, 0, 11, null);
        int size7 = list2.size();
        int i8 = 0;
        while (i8 < size7) {
            Measurable measurable14 = list3.get(i8);
            int i9 = iMo4551roundToPx0680j_4;
            Measurable measurable15 = measurable13;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable14), TextFieldImplKt.TextFieldId)) {
                final Placeable placeableMo4605measureBRTryo5 = measurable14.mo4605measureBRTryo0(jM5965copyZbe2FdA$default2);
                long jM5965copyZbe2FdA$default3 = Constraints.m5965copyZbe2FdA$default(jM5965copyZbe2FdA$default2, 0, 0, 0, 0, 14, null);
                List<? extends Measurable> list5 = list3;
                int size8 = list5.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size8) {
                        measurable7 = null;
                        break;
                    }
                    measurable7 = list3.get(i10);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i10++;
                }
                Measurable measurable16 = measurable7;
                Placeable placeableMo4605measureBRTryo6 = measurable16 != null ? measurable16.mo4605measureBRTryo0(jM5965copyZbe2FdA$default3) : null;
                int iMax5 = Math.max(iMax4, Math.max(LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo5), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo6)) + iMo4551roundToPx0680j_6 + i9);
                boolean z2 = z;
                long j4 = j2;
                int iM735calculateWidthIzADHW4 = m735calculateWidthIzADHW4(measureScope, LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo2), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo3), placeableMo4605measureBRTryo5.getWidth(), LayoutUtilKt.getWidthOrZero((Placeable) objectRef2.element), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo6), j, fInvoke);
                if (z2) {
                    objectRef = objectRef2;
                    j3 = j4;
                    Placeable placeableMo4605measureBRTryo7 = measurable12 != null ? measurable12.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j3, 0, iM735calculateWidthIzADHW4, 0, iMinIntrinsicHeight, 5, null)) : null;
                    objectRef.element = placeableMo4605measureBRTryo7;
                    if (placeableMo4605measureBRTryo7 != null) {
                        jM2967getZeroNHjbRc = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(placeableMo4605measureBRTryo7.getWidth())) << 32) | (((long) Float.floatToRawIntBits(placeableMo4605measureBRTryo7.getHeight())) & 4294967295L));
                    } else {
                        jM2967getZeroNHjbRc = Size.INSTANCE.m2967getZeroNHjbRc();
                    }
                    this.onLabelMeasured.invoke(Size.m2946boximpl(jM2967getZeroNHjbRc));
                } else {
                    objectRef = objectRef2;
                    j3 = j4;
                }
                int i11 = iM735calculateWidthIzADHW4;
                Placeable placeableMo4605measureBRTryo8 = measurable15 != null ? measurable15.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(ConstraintsKt.m5995offsetNN6EwU$default(j3, 0, -iMax5, 1, null), 0, iM735calculateWidthIzADHW4, 0, 0, 9, null)) : null;
                int heightOrZero = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo8);
                int heightOrZero2 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo1);
                int heightOrZero3 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo0);
                int heightOrZero4 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo2);
                int heightOrZero5 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo3);
                int height = placeableMo4605measureBRTryo5.getHeight();
                int heightOrZero6 = LayoutUtilKt.getHeightOrZero((Placeable) objectRef.element);
                int heightOrZero7 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo6);
                int heightOrZero8 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo8);
                final Placeable placeable = placeableMo4605measureBRTryo8;
                boolean z3 = z2;
                final Ref.ObjectRef objectRef3 = objectRef;
                final int iM734calculateHeightmKXJcVc = m734calculateHeightmKXJcVc(measureScope, heightOrZero2, heightOrZero3, heightOrZero4, heightOrZero5, height, heightOrZero6, heightOrZero7, heightOrZero8, j, z3, fInvoke);
                int heightOrZero9 = (iM734calculateHeightmKXJcVc - heightOrZero) - (z3 ? LayoutUtilKt.getHeightOrZero((Placeable) objectRef3.element) : 0);
                int size9 = list5.size();
                int i12 = 0;
                while (i12 < size9) {
                    Measurable measurable17 = list.get(i12);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable17), TextFieldImplKt.ContainerId)) {
                        final Placeable placeableMo4605measureBRTryo9 = measurable17.mo4605measureBRTryo0(ConstraintsKt.Constraints(i11 != Integer.MAX_VALUE ? i11 : 0, i11, heightOrZero9 != Integer.MAX_VALUE ? heightOrZero9 : 0, heightOrZero9));
                        final int i13 = i11;
                        final Placeable placeable2 = placeableMo4605measureBRTryo0;
                        final Placeable placeable3 = placeableMo4605measureBRTryo2;
                        final Placeable placeable4 = placeableMo4605measureBRTryo3;
                        final Placeable placeable5 = placeableMo4605measureBRTryo6;
                        final boolean z4 = z3;
                        final Placeable placeable6 = placeableMo4605measureBRTryo1;
                        return MeasureScope.layout$default(measureScope, i13, iM734calculateHeightmKXJcVc, null, new Function1() { // from class: androidx.compose.material3.k3
                            public final Object invoke(Object obj) {
                                return OutlinedTextFieldMeasurePolicy.c(this.b, iM734calculateHeightmKXJcVc, i13, placeable6, placeable2, placeable3, placeable4, placeableMo4605measureBRTryo5, objectRef3, placeable5, placeableMo4605measureBRTryo9, placeable, measureScope, z4, fInvoke, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    i12++;
                    iM734calculateHeightmKXJcVc = iM734calculateHeightmKXJcVc;
                    z3 = z3;
                    i11 = i11;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
            i8++;
            measurable13 = measurable15;
            iMo4551roundToPx0680j_4 = i9;
            z = z;
            j2 = j2;
            list3 = list3;
            jM5965copyZbe2FdA$default2 = jM5965copyZbe2FdA$default2;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.i3
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(OutlinedTextFieldMeasurePolicy.d((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.m3
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(OutlinedTextFieldMeasurePolicy.e((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    public /* synthetic */ OutlinedTextFieldMeasurePolicy(Function1 function1, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, PaddingValues paddingValues, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, z, textFieldLabelPosition, floatProducer, paddingValues, f);
    }
}
