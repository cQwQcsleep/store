package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.ui.Alignment;
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
import androidx.compose.ui.unit.IntOffset;
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

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ)\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\"\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\"\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\"\u0010\u001f\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010 \u001a\u00020\u001aH\u0016J\"\u0010!\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010 \u001a\u00020\u001aH\u0016J8\u0010\"\u001a\u00020\u001a2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010 \u001a\u00020\u001a2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0$H\u0002J<\u0010%\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010\u001d\u001a\u00020\u001a2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0$H\u0002JO\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b.\u0010/Jk\u00100\u001a\u00020\u001a*\u0002012\u0006\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\u001a2\u0006\u00106\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\u001a2\u0006\u00109\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020;H\u0002¢\u0006\u0004\b<\u0010=J \u0001\u0010>\u001a\u00020?*\u00020@2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010A\u001a\u00020\u001a2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020C2\b\u0010E\u001a\u0004\u0018\u00010C2\b\u0010F\u001a\u0004\u0018\u00010C2\b\u0010G\u001a\u0004\u0018\u00010C2\b\u0010H\u001a\u0004\u0018\u00010C2\b\u0010I\u001a\u0004\u0018\u00010C2\u0006\u0010J\u001a\u00020C2\b\u0010K\u001a\u0004\u0018\u00010C2\u0006\u0010L\u001a\u00020\u001a2\u0006\u0010M\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020;2\u0006\u0010N\u001a\u00020\u001a2\u0006\u0010O\u001a\u00020PH\u0002Jp\u0010Q\u001a\u00020?*\u00020@2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010A\u001a\u00020\u001a2\u0006\u0010R\u001a\u00020C2\b\u0010E\u001a\u0004\u0018\u00010C2\b\u0010F\u001a\u0004\u0018\u00010C2\b\u0010G\u001a\u0004\u0018\u00010C2\b\u0010H\u001a\u0004\u0018\u00010C2\b\u0010I\u001a\u0004\u0018\u00010C2\u0006\u0010J\u001a\u00020C2\b\u0010K\u001a\u0004\u0018\u00010C2\u0006\u0010S\u001a\u00020;H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006T"}, d2 = {"Landroidx/compose/material3/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "minimizedLabelHalfHeight", "Landroidx/compose/ui/unit/Dp;", "<init>", "(ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Landroidx/compose/foundation/layout/PaddingValues;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "height", "minIntrinsicWidth", "intrinsicWidth", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "calculateWidth", "leadingWidth", "trailingWidth", "prefixWidth", "suffixWidth", "textFieldWidth", "labelWidth", "placeholderWidth", "calculateWidth-yeHjK3Y", "(IIIIIIIJ)I", "calculateHeight", "Landroidx/compose/ui/unit/Density;", "textFieldHeight", "labelHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "placeholderHeight", "supportingHeight", "isLabelAbove", "", "calculateHeight-mKXJcVc", "(Landroidx/compose/ui/unit/Density;IIIIIIIIJZF)I", "placeWithLabel", "", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "textfieldPlaceable", "Landroidx/compose/ui/layout/Placeable;", "labelPlaceable", "placeholderPlaceable", "leadingPlaceable", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "containerPlaceable", "supportingPlaceable", "labelStartY", "labelEndY", "textPosition", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "placeWithoutLabel", "textPlaceable", "density", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final TextFieldLabelPosition labelPosition;
    private final FloatProducer labelProgress;
    private final float minimizedLabelHalfHeight;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    private TextFieldMeasurePolicy(boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, PaddingValues paddingValues, float f) {
        this.singleLine = z;
        this.labelPosition = textFieldLabelPosition;
        this.labelProgress = floatProducer;
        this.paddingValues = paddingValues;
        this.minimizedLabelHalfHeight = f;
    }

    public static int a(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public static int b(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    public static int c(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    /* JADX INFO: renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    private final int m1092calculateHeightmKXJcVc(Density density, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j, boolean z, float f) {
        int iMo4551roundToPx0680j_4 = density.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(this.paddingValues.calculateTopPadding-D9Ej5fM() + this.paddingValues.calculateBottomPadding-D9Ej5fM())) + ((i2 <= 0 || z) ? 0 : Math.max(density.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(this.minimizedLabelHalfHeight * 2.0f)), MathHelpersKt.lerp(0, i2, MotionTokens.INSTANCE.getEasingEmphasizedAccelerateCubicBezier().transform(f)))) + ComparisonsKt.maxOf(i, new int[]{i7, i5, i6, z ? 0 : MathHelpersKt.lerp(i2, 0, f)});
        if (!z) {
            i2 = 0;
        }
        return ConstraintsKt.m5991constrainHeightK40F9xA(j, i2 + Math.max(i3, Math.max(i4, iMo4551roundToPx0680j_4)) + i8);
    }

    /* JADX INFO: renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    private final int m1093calculateWidthyeHjK3Y(int leadingWidth, int trailingWidth, int prefixWidth, int suffixWidth, int textFieldWidth, int labelWidth, int placeholderWidth, long constraints) {
        int i = prefixWidth + suffixWidth;
        return ConstraintsKt.m5992constrainWidthK40F9xA(constraints, leadingWidth + Math.max(textFieldWidth + i, Math.max(placeholderWidth + i, labelWidth)) + trailingWidth);
    }

    public static Unit d(Ref.ObjectRef objectRef, boolean z, TextFieldMeasurePolicy textFieldMeasurePolicy, int i, int i2, MeasureScope measureScope, int i3, int i4, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, float f, Placeable.PlacementScope placementScope) {
        MeasureScope measureScope2;
        int iMo4551roundToPx0680j_4;
        int i5;
        if (objectRef.element != null) {
            if (z) {
                measureScope2 = measureScope;
                i5 = 0;
            } else {
                if (textFieldMeasurePolicy.singleLine) {
                    iMo4551roundToPx0680j_4 = Alignment.INSTANCE.getCenterVertically().align(((Placeable) objectRef.element).getHeight(), i);
                    measureScope2 = measureScope;
                } else {
                    measureScope2 = measureScope;
                    iMo4551roundToPx0680j_4 = i2 + measureScope2.mo4551roundToPx0680j_4(textFieldMeasurePolicy.minimizedLabelHalfHeight);
                }
                i5 = iMo4551roundToPx0680j_4;
            }
            int i6 = z ? 0 : i2;
            Placeable placeable9 = (Placeable) objectRef.element;
            textFieldMeasurePolicy.placeWithLabel(placementScope, i3, i4, placeable, placeable9, placeable2, placeable3, placeable4, placeable5, placeable6, placeable7, placeable8, i5, i6, z, f, i2 + (z ? 0 : placeable9.getHeight()), measureScope2.getLayoutDirection());
        } else {
            textFieldMeasurePolicy.placeWithoutLabel(placementScope, i3, i4, placeable, placeable2, placeable3, placeable4, placeable5, placeable6, placeable7, placeable8, measureScope.getDensity());
        }
        return Unit.INSTANCE;
    }

    public static int e(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        int i2;
        int iSubtractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable3;
        int i3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int iSubtractConstraintSafely2;
        int i4;
        IntrinsicMeasurable intrinsicMeasurable5;
        int iIntValue3;
        int i5;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        List<? extends IntrinsicMeasurable> list2 = list;
        int size = list2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i6);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i6++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            i2 = i;
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(i2, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = ((Number) function2.invoke(intrinsicMeasurable8, Integer.valueOf(i2))).intValue();
        } else {
            i2 = i;
            iSubtractConstraintSafely = i2;
            iIntValue = 0;
        }
        int size2 = list2.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i7);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i7++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            iSubtractConstraintSafely = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = ((Number) function2.invoke(intrinsicMeasurable9, Integer.valueOf(i2))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list2.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size3) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i8);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.LabelId)) {
                break;
            }
            i8++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) function2.invoke(intrinsicMeasurable10, Integer.valueOf(iSubtractConstraintSafely))).intValue() : 0;
        int size4 = list2.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size4) {
                i3 = 0;
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i9);
            i3 = 0;
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i9++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            int iIntValue5 = ((Number) function2.invoke(intrinsicMeasurable11, Integer.valueOf(iSubtractConstraintSafely))).intValue();
            int iSubtractConstraintSafely3 = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
            i4 = iIntValue5;
            iSubtractConstraintSafely2 = iSubtractConstraintSafely3;
        } else {
            iSubtractConstraintSafely2 = iSubtractConstraintSafely;
            i4 = i3;
        }
        int size5 = list2.size();
        int i10 = i3;
        while (true) {
            if (i10 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i10);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i10++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            iIntValue3 = ((Number) function2.invoke(intrinsicMeasurable12, Integer.valueOf(iSubtractConstraintSafely2))).intValue();
            iSubtractConstraintSafely2 = LayoutUtilKt.subtractConstraintSafely(iSubtractConstraintSafely2, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = i3;
        }
        int size6 = list2.size();
        for (int i11 = i3; i11 < size6; i11++) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i11);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.TextFieldId)) {
                int iIntValue6 = ((Number) function2.invoke(intrinsicMeasurable13, Integer.valueOf(iSubtractConstraintSafely2))).intValue();
                List<? extends IntrinsicMeasurable> list3 = list;
                int size7 = list3.size();
                int i12 = i3;
                while (true) {
                    if (i12 >= size7) {
                        i5 = iIntValue6;
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i12);
                    i5 = iIntValue6;
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i12++;
                    iIntValue6 = i5;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int iIntValue7 = intrinsicMeasurable14 != null ? ((Number) function2.invoke(intrinsicMeasurable14, Integer.valueOf(iSubtractConstraintSafely2))).intValue() : i3;
                int size8 = list3.size();
                int i13 = i3;
                while (true) {
                    if (i13 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    intrinsicMeasurable7 = list.get(i13);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    i13++;
                }
                IntrinsicMeasurable intrinsicMeasurable15 = intrinsicMeasurable7;
                return m1092calculateHeightmKXJcVc(intrinsicMeasureScope, i5, iIntValue4, iIntValue, iIntValue2, i4, iIntValue3, iIntValue7, intrinsicMeasurable15 != null ? ((Number) function2.invoke(intrinsicMeasurable15, Integer.valueOf(i2))).intValue() : i3, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), this.labelPosition instanceof TextFieldLabelPosition.Above, this.labelProgress.invoke());
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return i3;
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int size = measurables.size();
        for (int i = 0; i < size; i++) {
            IntrinsicMeasurable intrinsicMeasurable7 = measurables.get(i);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.TextFieldId)) {
                int iIntValue = ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable7, Integer.valueOf(height))).intValue();
                List<? extends IntrinsicMeasurable> list = measurables;
                int size2 = list.size();
                int i2 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i2 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = measurables.get(i2);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.LabelId)) {
                        break;
                    }
                    i2++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable8 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable8, Integer.valueOf(height))).intValue() : 0;
                int size3 = list.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = measurables.get(i3);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable9 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable9, Integer.valueOf(height))).intValue() : 0;
                int size4 = list.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = measurables.get(i4);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable10, Integer.valueOf(height))).intValue() : 0;
                int size5 = list.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = measurables.get(i5);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int iIntValue5 = intrinsicMeasurable11 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable11, Integer.valueOf(height))).intValue() : 0;
                int size6 = list.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = measurables.get(i6);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int iIntValue6 = intrinsicMeasurable12 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable12, Integer.valueOf(height))).intValue() : 0;
                int size7 = list.size();
                for (int i7 = 0; i7 < size7; i7++) {
                    IntrinsicMeasurable intrinsicMeasurable13 = measurables.get(i7);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.PlaceholderId)) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                return m1093calculateWidthyeHjK3Y(iIntValue6, iIntValue3, iIntValue4, iIntValue5, iIntValue, iIntValue2, intrinsicMeasurable14 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable14, Integer.valueOf(height))).intValue() : 0, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return 0;
    }

    private final void placeWithLabel(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, int i3, int i4, boolean z, float f, int i5, LayoutDirection layoutDirection) {
        int height = z ? placeable2.getHeight() : 0;
        Placeable.PlacementScope.place$default(placementScope, placeable8, 0, height, 0.0f, 4, null);
        int heightOrZero = (i2 - LayoutUtilKt.getHeightOrZero(placeable9)) - (z ? placeable2.getHeight() : 0);
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, 0, height + Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), heightOrZero), 0.0f, 4, null);
        }
        int iLerp = MathHelpersKt.lerp(i3, i4, f);
        if (z) {
            Placeable.PlacementScope.place$default(placementScope, placeable2, TextFieldImplKt.getMinimizedAlignment(this.labelPosition).align(placeable2.getWidth(), i, layoutDirection), iLerp, 0.0f, 4, null);
        } else {
            int widthOrZero = layoutDirection == LayoutDirection.Ltr ? LayoutUtilKt.getWidthOrZero(placeable4) : LayoutUtilKt.getWidthOrZero(placeable5);
            Placeable.PlacementScope.place$default(placementScope, placeable2, MathHelpersKt.lerp(TextFieldImplKt.getExpandedAlignment(this.labelPosition).align(placeable2.getWidth(), (i - LayoutUtilKt.getWidthOrZero(placeable4)) - LayoutUtilKt.getWidthOrZero(placeable5), layoutDirection) + widthOrZero, TextFieldImplKt.getMinimizedAlignment(this.labelPosition).align(placeable2.getWidth(), (i - LayoutUtilKt.getWidthOrZero(placeable4)) - LayoutUtilKt.getWidthOrZero(placeable5), layoutDirection) + widthOrZero, f), iLerp, 0.0f, 4, null);
        }
        if (placeable6 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, LayoutUtilKt.getWidthOrZero(placeable4), height + i5, 0.0f, 4, null);
        }
        int widthOrZero2 = LayoutUtilKt.getWidthOrZero(placeable4) + LayoutUtilKt.getWidthOrZero(placeable6);
        int i6 = height + i5;
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, widthOrZero2, i6, 0.0f, 4, null);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, widthOrZero2, i6, 0.0f, 4, null);
        }
        if (placeable7 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, (i - LayoutUtilKt.getWidthOrZero(placeable5)) - placeable7.getWidth(), i6, 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, i - placeable5.getWidth(), height + Alignment.INSTANCE.getCenterVertically().align(placeable5.getHeight(), heightOrZero), 0.0f, 4, null);
        }
        if (placeable9 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, 0, height + heightOrZero, 0.0f, 4, null);
        }
    }

    private final void placeWithoutLabel(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, float f) {
        Placeable.PlacementScope.m4676place70tqf50$default(placementScope, placeable7, IntOffset.INSTANCE.m6161getZeronOccac(), 0.0f, 2, null);
        int heightOrZero = i2 - LayoutUtilKt.getHeightOrZero(placeable8);
        int iRoundToInt = MathKt.roundToInt(this.paddingValues.calculateTopPadding-D9Ej5fM() * f);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, 0, Alignment.INSTANCE.getCenterVertically().align(placeable3.getHeight(), heightOrZero), 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, LayoutUtilKt.getWidthOrZero(placeable3), placeWithoutLabel$calculateVerticalPosition(this, heightOrZero, iRoundToInt, placeable5), 0.0f, 4, null);
        }
        int widthOrZero = LayoutUtilKt.getWidthOrZero(placeable5) + LayoutUtilKt.getWidthOrZero(placeable3);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, widthOrZero, placeWithoutLabel$calculateVerticalPosition(this, heightOrZero, iRoundToInt, placeable), 0.0f, 4, null);
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, widthOrZero, placeWithoutLabel$calculateVerticalPosition(this, heightOrZero, iRoundToInt, placeable2), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, (i - LayoutUtilKt.getWidthOrZero(placeable4)) - placeable6.getWidth(), placeWithoutLabel$calculateVerticalPosition(this, heightOrZero, iRoundToInt, placeable6), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i - placeable4.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), heightOrZero), 0.0f, 4, null);
        }
        if (placeable8 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable8, 0, heightOrZero, 0.0f, 4, null);
        }
    }

    private static final int placeWithoutLabel$calculateVerticalPosition(TextFieldMeasurePolicy textFieldMeasurePolicy, int i, int i2, Placeable placeable) {
        return textFieldMeasurePolicy.singleLine ? Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), i) : i2;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.v4
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(TextFieldMeasurePolicy.e((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(list, i, new Function2() { // from class: androidx.compose.material3.s4
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(TextFieldMeasurePolicy.b((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
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
        Measurable measurable6;
        int i;
        Measurable measurable7;
        Measurable measurable8;
        long j3;
        final float fInvoke = this.labelProgress.invoke();
        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateTopPadding-D9Ej5fM());
        int iMo4551roundToPx0680j_5 = measureScope.mo4551roundToPx0680j_4(this.paddingValues.calculateBottomPadding-D9Ej5fM());
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                measurable = null;
                break;
            }
            measurable = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i2++;
        }
        Measurable measurable9 = measurable;
        Placeable placeableMo4605measureBRTryo1 = measurable9 != null ? measurable9.mo4605measureBRTryo0(jM5965copyZbe2FdA$default) : null;
        int widthOrZero = LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1);
        int iMax = Math.max(0, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo1));
        int size2 = list2.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i3++;
        }
        Measurable measurable10 = measurable2;
        if (measurable10 != null) {
            j2 = jM5965copyZbe2FdA$default;
            placeableMo4605measureBRTryo0 = measurable10.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero, 0, 2, null));
        } else {
            j2 = jM5965copyZbe2FdA$default;
            placeableMo4605measureBRTryo0 = null;
        }
        int widthOrZero2 = widthOrZero + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0);
        int iMax2 = Math.max(iMax, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo0));
        int size3 = list2.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i4++;
        }
        Measurable measurable11 = measurable3;
        Placeable placeableMo4605measureBRTryo2 = measurable11 != null ? measurable11.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero2, 0, 2, null)) : null;
        int widthOrZero3 = widthOrZero2 + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo2);
        int iMax3 = Math.max(iMax2, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo2));
        int size4 = list2.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i5++;
        }
        Measurable measurable12 = measurable4;
        Placeable placeableMo4605measureBRTryo3 = measurable12 != null ? measurable12.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j2, -widthOrZero3, 0, 2, null)) : null;
        int widthOrZero4 = widthOrZero3 + LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo3);
        int iMax4 = Math.max(iMax3, LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo3));
        boolean z = this.labelPosition instanceof TextFieldLabelPosition.Above;
        int size5 = list2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size5) {
                measurable5 = null;
                break;
            }
            measurable5 = list.get(i6);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), TextFieldImplKt.LabelId)) {
                break;
            }
            i6++;
        }
        Measurable measurable13 = measurable5;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (z) {
            iMinIntrinsicHeight = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m5977getMinWidthimpl(j)) : 0;
        } else {
            objectRef.element = measurable13 != null ? measurable13.mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(j2, -widthOrZero4, -iMo4551roundToPx0680j_5)) : null;
            iMinIntrinsicHeight = 0;
        }
        int size6 = list2.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size6) {
                measurable6 = measurable13;
                i = iMo4551roundToPx0680j_5;
                measurable7 = null;
                break;
            }
            measurable7 = list.get(i7);
            measurable6 = measurable13;
            i = iMo4551roundToPx0680j_5;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), TextFieldImplKt.SupportingId)) {
                break;
            }
            i7++;
            measurable13 = measurable6;
            iMo4551roundToPx0680j_5 = i;
        }
        Measurable measurable14 = measurable7;
        int iMinIntrinsicHeight2 = measurable14 != null ? measurable14.minIntrinsicHeight(Constraints.m5977getMinWidthimpl(j)) : 0;
        int heightOrZero = LayoutUtilKt.getHeightOrZero((Placeable) objectRef.element) + iMinIntrinsicHeight + iMo4551roundToPx0680j_4;
        long jM5994offsetNN6EwU = ConstraintsKt.m5994offsetNN6EwU(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), -widthOrZero4, ((-heightOrZero) - i) - iMinIntrinsicHeight2);
        int size7 = list2.size();
        int i8 = 0;
        while (i8 < size7) {
            Measurable measurable15 = list.get(i8);
            int i9 = size7;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable15), TextFieldImplKt.TextFieldId)) {
                final Placeable placeableMo4605measureBRTryo4 = measurable15.mo4605measureBRTryo0(jM5994offsetNN6EwU);
                long jM5965copyZbe2FdA$default2 = Constraints.m5965copyZbe2FdA$default(jM5994offsetNN6EwU, 0, 0, 0, 0, 14, null);
                List<? extends Measurable> list3 = list;
                int size8 = list3.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size8) {
                        measurable8 = null;
                        break;
                    }
                    measurable8 = list.get(i10);
                    int i11 = size8;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable8), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i10++;
                    size8 = i11;
                }
                Measurable measurable16 = measurable8;
                Placeable placeableMo4605measureBRTryo5 = measurable16 != null ? measurable16.mo4605measureBRTryo0(jM5965copyZbe2FdA$default2) : null;
                int iMax5 = Math.max(iMax4, Math.max(LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo4), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo5)) + heightOrZero + i);
                boolean z2 = z;
                long j4 = j2;
                final int i12 = iMo4551roundToPx0680j_4;
                Measurable measurable17 = measurable6;
                final int iM1093calculateWidthyeHjK3Y = m1093calculateWidthyeHjK3Y(LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo2), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo3), placeableMo4605measureBRTryo4.getWidth(), LayoutUtilKt.getWidthOrZero((Placeable) objectRef.element), LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo5), j);
                if (z2) {
                    j3 = j4;
                    objectRef.element = measurable17 != null ? measurable17.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j3, 0, iM1093calculateWidthyeHjK3Y, 0, iMinIntrinsicHeight, 5, null)) : null;
                } else {
                    j3 = j4;
                }
                final Placeable placeableMo4605measureBRTryo6 = measurable14 != null ? measurable14.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(ConstraintsKt.m5995offsetNN6EwU$default(j3, 0, -iMax5, 1, null), 0, iM1093calculateWidthyeHjK3Y, 0, 0, 9, null)) : null;
                int heightOrZero2 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo6);
                boolean z3 = z2;
                int iM1092calculateHeightmKXJcVc = m1092calculateHeightmKXJcVc(measureScope, placeableMo4605measureBRTryo4.getHeight(), LayoutUtilKt.getHeightOrZero((Placeable) objectRef.element), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo1), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo0), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo2), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo3), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo5), LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo6), j, z3, fInvoke);
                final int heightOrZero3 = (iM1092calculateHeightmKXJcVc - heightOrZero2) - (z3 ? LayoutUtilKt.getHeightOrZero((Placeable) objectRef.element) : 0);
                int size9 = list3.size();
                int i13 = 0;
                while (i13 < size9) {
                    Measurable measurable18 = list.get(i13);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable18), TextFieldImplKt.ContainerId)) {
                        final Placeable placeableMo4605measureBRTryo7 = measurable18.mo4605measureBRTryo0(ConstraintsKt.Constraints(iM1093calculateWidthyeHjK3Y != Integer.MAX_VALUE ? iM1093calculateWidthyeHjK3Y : 0, iM1093calculateWidthyeHjK3Y, heightOrZero3 != Integer.MAX_VALUE ? heightOrZero3 : 0, heightOrZero3));
                        final int i14 = iM1092calculateHeightmKXJcVc;
                        final boolean z4 = z3;
                        final Placeable placeable = placeableMo4605measureBRTryo1;
                        final Placeable placeable2 = placeableMo4605measureBRTryo0;
                        final Placeable placeable3 = placeableMo4605measureBRTryo2;
                        final Placeable placeable4 = placeableMo4605measureBRTryo3;
                        final Placeable placeable5 = placeableMo4605measureBRTryo5;
                        return MeasureScope.layout$default(measureScope, iM1093calculateWidthyeHjK3Y, i14, null, new Function1() { // from class: androidx.compose.material3.t4
                            public final Object invoke(Object obj) {
                                return TextFieldMeasurePolicy.d(objectRef, z4, this, heightOrZero3, i12, measureScope, iM1093calculateWidthyeHjK3Y, i14, placeableMo4605measureBRTryo4, placeable5, placeable, placeable2, placeable3, placeable4, placeableMo4605measureBRTryo7, placeableMo4605measureBRTryo6, fInvoke, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    i13++;
                    iM1092calculateHeightmKXJcVc = iM1092calculateHeightmKXJcVc;
                    z3 = z3;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
            i8++;
            size7 = i9;
            j2 = j2;
            z = z;
            jM5994offsetNN6EwU = jM5994offsetNN6EwU;
            iMo4551roundToPx0680j_4 = iMo4551roundToPx0680j_4;
            measurable6 = measurable6;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.u4
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(TextFieldMeasurePolicy.a((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(list, i, new Function2() { // from class: androidx.compose.material3.r4
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(TextFieldMeasurePolicy.c((IntrinsicMeasurable) obj, ((Integer) obj2).intValue()));
            }
        });
    }

    public /* synthetic */ TextFieldMeasurePolicy(boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, PaddingValues paddingValues, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, textFieldLabelPosition, floatProducer, paddingValues, f);
    }
}
