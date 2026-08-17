package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "mainAxisMin", "", "crossAxisMin", "mainAxisMax", "crossAxisMax", "arrangementSpacingInt", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "startIndex", "endIndex", "crossAxisOffset", "", "currentLineIndex", "(Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;IIIIILandroidx/compose/ui/layout/MeasureScope;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;II[II)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RowColumnMeasurePolicyKt {
    /* JADX WARN: Code duplicated, block: B:78:0x0194  */
    /* JADX WARN: Code duplicated, block: B:79:0x019c  */
    /* JADX WARN: Code duplicated, block: B:81:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:82:0x01a7  */
    public static final MeasureResult measure(RowColumnMeasurePolicy rowColumnMeasurePolicy, int i, int i2, int i3, int i4, int i5, MeasureScope measureScope, List<? extends Measurable> list, Placeable[] placeableArr, int i6, int i7, int[] iArr, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        FlowLayoutData flowLayoutData;
        int iIntValue;
        int iIntValue2;
        int i13;
        int i14;
        FlowLayoutData flowLayoutData2;
        RowColumnMeasurePolicy rowColumnMeasurePolicy2;
        int i15;
        long j = i5;
        int i16 = i7 - i6;
        int[] iArr2 = new int[i16];
        int i17 = i6;
        float f = 0.0f;
        int i18 = 0;
        int i19 = 0;
        boolean z = false;
        int i20 = 0;
        int i21 = 0;
        while (true) {
            i9 = Integer.MAX_VALUE;
            boolean z2 = true;
            if (i17 >= i7) {
                break;
            }
            Measurable measurable = list.get(i17);
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData((IntrinsicMeasurable) measurable);
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            if (!z && !RowColumnImplKt.isRelative(rowColumnParentData)) {
                z2 = false;
            }
            if (weight > 0.0f) {
                f += weight;
                i20++;
                iArr2 = iArr2;
                i17 = i17;
                j = j;
            } else {
                Integer numValueOf = (i4 == Integer.MAX_VALUE || rowColumnParentData == null || (flowLayoutData2 = rowColumnParentData.getFlowLayoutData()) == null) ? null : Integer.valueOf(Math.round(flowLayoutData2.getFillCrossAxisFraction() * i4));
                int i22 = i3 - i21;
                Placeable placeable = placeableArr[i17];
                if (placeable == null) {
                    i15 = i19;
                    rowColumnMeasurePolicy2 = rowColumnMeasurePolicy;
                    placeable = measurable.measure-BRTryo0(RowColumnMeasurePolicy.m961createConstraintsxF2OJ5Q$default(rowColumnMeasurePolicy2, 0, numValueOf != null ? numValueOf.intValue() : 0, i3 != Integer.MAX_VALUE ? i22 < 0 ? 0 : i22 : Integer.MAX_VALUE, numValueOf != null ? numValueOf.intValue() : i4, false, 16, null));
                } else {
                    rowColumnMeasurePolicy2 = rowColumnMeasurePolicy;
                    i15 = i19;
                }
                int iMainAxisSize = rowColumnMeasurePolicy2.mainAxisSize(placeable);
                int iCrossAxisSize = rowColumnMeasurePolicy2.crossAxisSize(placeable);
                iArr2[i17 - i6] = iMainAxisSize;
                int i23 = i22 - iMainAxisSize;
                if (i23 < 0) {
                    i23 = 0;
                }
                int iMin = Math.min(i5, i23);
                i21 += iMainAxisSize + iMin;
                int iMax = Math.max(i15, iCrossAxisSize);
                placeableArr[i17] = placeable;
                i18 = iMin;
                i19 = iMax;
            }
            i17++;
            z = z2;
            iArr2 = iArr2;
            j = j;
        }
        int[] iArr3 = iArr2;
        long j2 = j;
        RowColumnMeasurePolicy rowColumnMeasurePolicy3 = rowColumnMeasurePolicy;
        int i24 = i19;
        if (i20 == 0) {
            i21 -= i18;
            i11 = 0;
            i10 = 0;
        } else {
            long j3 = j2 * ((long) (i20 - 1));
            long jRound = ((long) ((i3 != Integer.MAX_VALUE ? i3 : i) - i21)) - j3;
            if (jRound < 0) {
                jRound = 0;
            }
            float f2 = jRound / f;
            for (int i25 = i6; i25 < i7; i25++) {
                jRound -= (long) Math.round(RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData((IntrinsicMeasurable) list.get(i25))) * f2);
            }
            int i26 = i6;
            int i27 = 0;
            while (i26 < i7) {
                if (placeableArr[i26] == null) {
                    Measurable measurable2 = list.get(i26);
                    RowColumnParentData rowColumnParentData2 = RowColumnImplKt.getRowColumnParentData((IntrinsicMeasurable) measurable2);
                    float weight2 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    Integer numValueOf2 = (i4 == i9 || rowColumnParentData2 == null || (flowLayoutData = rowColumnParentData2.getFlowLayoutData()) == null) ? null : Integer.valueOf(Math.round(flowLayoutData.getFillCrossAxisFraction() * i4));
                    if (!(weight2 > 0.0f)) {
                        InlineClassHelperKt.throwIllegalStateException("All weights <= 0 should have placeables");
                    }
                    int sign = MathKt.getSign(jRound);
                    long j4 = jRound - ((long) sign);
                    int iMax2 = Math.max(0, Math.round(weight2 * f2) + sign);
                    if (RowColumnImplKt.getFill(rowColumnParentData2)) {
                        i12 = Integer.MAX_VALUE;
                        int i28 = iMax2 != Integer.MAX_VALUE ? iMax2 : 0;
                        if (numValueOf2 != null) {
                            iIntValue = numValueOf2.intValue();
                        } else {
                            iIntValue = 0;
                        }
                        if (numValueOf2 != null) {
                            iIntValue2 = numValueOf2.intValue();
                        } else {
                            iIntValue2 = i4;
                        }
                        rowColumnMeasurePolicy3 = rowColumnMeasurePolicy;
                        Placeable placeable2 = measurable2.measure-BRTryo0(rowColumnMeasurePolicy3.mo819createConstraintsxF2OJ5Q(i28, iIntValue, iMax2, iIntValue2, true));
                        int iMainAxisSize2 = rowColumnMeasurePolicy3.mainAxisSize(placeable2);
                        int iCrossAxisSize2 = rowColumnMeasurePolicy3.crossAxisSize(placeable2);
                        iArr3[i26 - i6] = iMainAxisSize2;
                        i27 += iMainAxisSize2;
                        int iMax3 = Math.max(i24, iCrossAxisSize2);
                        placeableArr[i26] = placeable2;
                        i24 = iMax3;
                        jRound = j4;
                    } else {
                        i12 = Integer.MAX_VALUE;
                    }
                    if (numValueOf2 != null) {
                        iIntValue = numValueOf2.intValue();
                    } else {
                        iIntValue = 0;
                    }
                    if (numValueOf2 != null) {
                        iIntValue2 = numValueOf2.intValue();
                    } else {
                        iIntValue2 = i4;
                    }
                    rowColumnMeasurePolicy3 = rowColumnMeasurePolicy;
                    Placeable placeable3 = measurable2.measure-BRTryo0(rowColumnMeasurePolicy3.mo819createConstraintsxF2OJ5Q(i28, iIntValue, iMax2, iIntValue2, true));
                    int iMainAxisSize3 = rowColumnMeasurePolicy3.mainAxisSize(placeable3);
                    int iCrossAxisSize3 = rowColumnMeasurePolicy3.crossAxisSize(placeable3);
                    iArr3[i26 - i6] = iMainAxisSize3;
                    i27 += iMainAxisSize3;
                    int iMax4 = Math.max(i24, iCrossAxisSize3);
                    placeableArr[i26] = placeable3;
                    i24 = iMax4;
                    jRound = j4;
                } else {
                    i12 = i9;
                }
                i26++;
                i9 = i12;
            }
            i10 = 0;
            i11 = (int) (((long) i27) + j3);
            int i29 = i3 - i21;
            if (i11 < 0) {
                i11 = 0;
            }
            if (i11 > i29) {
                i11 = i29;
            }
        }
        int i30 = i24;
        if (z) {
            int iMax5 = i10;
            int iMax6 = iMax5;
            for (int i31 = i6; i31 < i7; i31++) {
                Placeable placeable4 = placeableArr[i31];
                placeable4.getClass();
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(RowColumnImplKt.getRowColumnParentData(placeable4));
                Integer numCalculateAlignmentLinePosition$foundation_layout = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout(placeable4) : null;
                if (numCalculateAlignmentLinePosition$foundation_layout != null) {
                    int iIntValue3 = numCalculateAlignmentLinePosition$foundation_layout.intValue();
                    int iCrossAxisSize4 = rowColumnMeasurePolicy3.crossAxisSize(placeable4);
                    iMax5 = Math.max(iMax5, iIntValue3 != Integer.MIN_VALUE ? numCalculateAlignmentLinePosition$foundation_layout.intValue() : i10);
                    if (iIntValue3 == Integer.MIN_VALUE) {
                        iIntValue3 = iCrossAxisSize4;
                    }
                    iMax6 = Math.max(iMax6, iCrossAxisSize4 - iIntValue3);
                }
            }
            int i32 = iMax6;
            i14 = iMax5;
            i13 = i32;
        } else {
            i13 = i10;
            i14 = i13;
        }
        int i33 = i21 + i11;
        if (i33 < 0) {
            i33 = i10;
        }
        int iMax7 = Math.max(i33, i);
        int iMax8 = Math.max(i30, Math.max(i2, i13 + i14));
        int[] iArr4 = new int[i16];
        rowColumnMeasurePolicy3.populateMainAxisPositions(iMax7, iArr3, iArr4, measureScope);
        return rowColumnMeasurePolicy3.placeHelper(placeableArr, measureScope, i14, iArr4, iMax7, iMax8, iArr, i8, i6, i7);
    }
}
