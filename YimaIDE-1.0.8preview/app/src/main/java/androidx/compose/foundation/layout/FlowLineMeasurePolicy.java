package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.FlowLineMeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0016J\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0013H\u0016J7\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJg\u0010\u001e\u001a\u00020\u001f2\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00122\b\u0010)\u001a\u0004\u0018\u00010&2\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010-J(\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00122\u0006\u00100\u001a\u0002012\u0006\u0010$\u001a\u00020\u0012H\u0016J(\u00102\u001a\u0002032\u0006\u0010'\u001a\u00020\u00122\u0006\u00104\u001a\u00020&2\u0006\u0010%\u001a\u00020&2\u0006\u0010\"\u001a\u00020#H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00065À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "isHorizontal", "", "()Z", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "mainAxisSize", "", "Landroidx/compose/ui/layout/Placeable;", "crossAxisSize", "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "mainAxisMin", "crossAxisMin", "mainAxisMax", "crossAxisMax", "isPrioritizing", "createConstraints-xF2OJ5Q", "(IIIIZ)J", "placeHelper", "Landroidx/compose/ui/layout/MeasureResult;", "placeables", "", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "beforeCrossAxisAlignmentLine", "mainAxisPositions", "", "mainAxisLayoutSize", "crossAxisLayoutSize", "crossAxisOffset", "currentLineIndex", "startIndex", "endIndex", "([Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/MeasureScope;I[III[IIII)Landroidx/compose/ui/layout/MeasureResult;", "getCrossAxisPosition", "placeable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "populateMainAxisPositions", "", "childrenMainAxisSize", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface FlowLineMeasurePolicy extends RowColumnMeasurePolicy {
    /* JADX INFO: Access modifiers changed from: private */
    static Unit placeHelper$lambda$0$0(int[] iArr, int i, int i2, int i3, Placeable[] placeableArr, FlowLineMeasurePolicy flowLineMeasurePolicy, int i4, LayoutDirection layoutDirection, int i5, int[] iArr2, Placeable.PlacementScope placementScope) {
        int i6 = iArr != null ? iArr[i] : 0;
        for (int i7 = i2; i7 < i3; i7++) {
            Placeable placeable = placeableArr[i7];
            placeable.getClass();
            int crossAxisPosition = flowLineMeasurePolicy.getCrossAxisPosition(placeable, i4, layoutDirection, i5) + i6;
            if (flowLineMeasurePolicy.isHorizontal()) {
                Placeable.PlacementScope.place$default(placementScope, placeable, iArr2[i7 - i2], crossAxisPosition, 0.0f, 4, (Object) null);
            } else {
                Placeable.PlacementScope.place$default(placementScope, placeable, crossAxisPosition, iArr2[i7 - i2], 0.0f, 4, (Object) null);
            }
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* JADX INFO: renamed from: createConstraints-xF2OJ5Q */
    default long mo819createConstraintsxF2OJ5Q(int mainAxisMin, int crossAxisMin, int mainAxisMax, int crossAxisMax, boolean isPrioritizing) {
        return isHorizontal() ? RowKt.createRowConstraints(isPrioritizing, mainAxisMin, crossAxisMin, mainAxisMax, crossAxisMax) : ColumnKt.createColumnConstraints(isPrioritizing, mainAxisMin, crossAxisMin, mainAxisMax, crossAxisMax);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default int crossAxisSize(Placeable placeable) {
        return isHorizontal() ? placeable.getMeasuredHeight() : placeable.getMeasuredWidth();
    }

    CrossAxisAlignment getCrossAxisAlignment();

    default int getCrossAxisPosition(Placeable placeable, int crossAxisLayoutSize, LayoutDirection layoutDirection, int beforeCrossAxisAlignmentLine) {
        CrossAxisAlignment crossAxisAlignment;
        RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(placeable);
        if (rowColumnParentData == null || (crossAxisAlignment = rowColumnParentData.getCrossAxisAlignment()) == null) {
            crossAxisAlignment = getCrossAxisAlignment();
        }
        return crossAxisAlignment.align$foundation_layout(crossAxisLayoutSize, crossAxisSize(placeable), layoutDirection, placeable, beforeCrossAxisAlignmentLine);
    }

    Arrangement.Horizontal getHorizontalArrangement();

    Arrangement.Vertical getVerticalArrangement();

    boolean isHorizontal();

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default int mainAxisSize(Placeable placeable) {
        return isHorizontal() ? placeable.getMeasuredWidth() : placeable.getMeasuredHeight();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default MeasureResult placeHelper(final Placeable[] placeables, MeasureScope measureScope, final int beforeCrossAxisAlignmentLine, final int[] mainAxisPositions, int mainAxisLayoutSize, final int crossAxisLayoutSize, final int[] crossAxisOffset, final int currentLineIndex, final int startIndex, final int endIndex) {
        int i;
        int i2;
        if (isHorizontal()) {
            i2 = mainAxisLayoutSize;
            i = crossAxisLayoutSize;
        } else {
            i = mainAxisLayoutSize;
            i2 = crossAxisLayoutSize;
        }
        final LayoutDirection layoutDirection = isHorizontal() ? LayoutDirection.Ltr : measureScope.getLayoutDirection();
        return MeasureScope.layout$default(measureScope, i2, i, (Map) null, new Function1() { // from class: lj5
            public final Object invoke(Object obj) {
                return FlowLineMeasurePolicy.placeHelper$lambda$0$0(crossAxisOffset, currentLineIndex, startIndex, endIndex, placeables, this, crossAxisLayoutSize, layoutDirection, beforeCrossAxisAlignmentLine, mainAxisPositions, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default void populateMainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope) {
        if (isHorizontal()) {
            getHorizontalArrangement().arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, measureScope.getLayoutDirection(), mainAxisPositions);
        } else {
            getVerticalArrangement().arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, mainAxisPositions);
        }
    }
}
