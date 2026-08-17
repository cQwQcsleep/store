package androidx.compose.animation;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\"\u0010\u0018\u001a\u00020\u0019*\u00020\u001a2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\"\u0010\u001d\u001a\u00020\u0019*\u00020\u001a2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\"\u0010\u001f\u001a\u00020\u0019*\u00020\u001a2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\"\u0010 \u001a\u00020\u0019*\u00020\u001a2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001e\u001a\u00020\u0019H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Landroidx/compose/animation/AnimatedEnterExitMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "scope", "Landroidx/compose/animation/AnimatedVisibilityScopeImpl;", "<init>", "(Landroidx/compose/animation/AnimatedVisibilityScopeImpl;)V", "getScope", "()Landroidx/compose/animation/AnimatedVisibilityScopeImpl;", "hasLookaheadOccurred", "", "getHasLookaheadOccurred", "()Z", "setHasLookaheadOccurred", "(Z)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class AnimatedEnterExitMeasurePolicy implements MeasurePolicy {
    private boolean hasLookaheadOccurred;
    private final AnimatedVisibilityScopeImpl scope;

    public AnimatedEnterExitMeasurePolicy(AnimatedVisibilityScopeImpl animatedVisibilityScopeImpl) {
        this.scope = animatedVisibilityScopeImpl;
    }

    public final boolean getHasLookaheadOccurred() {
        return this.hasLookaheadOccurred;
    }

    public final AnimatedVisibilityScopeImpl getScope() {
        return this.scope;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        if (list.isEmpty()) {
            return 0;
        }
        int iMaxIntrinsicHeight = list.get(0).maxIntrinsicHeight(i);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int iMaxIntrinsicHeight2 = list.get(i2).maxIntrinsicHeight(i);
                if (iMaxIntrinsicHeight2 > iMaxIntrinsicHeight) {
                    iMaxIntrinsicHeight = iMaxIntrinsicHeight2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return iMaxIntrinsicHeight;
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        if (list.isEmpty()) {
            return 0;
        }
        int iMaxIntrinsicWidth = list.get(0).maxIntrinsicWidth(i);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int iMaxIntrinsicWidth2 = list.get(i2).maxIntrinsicWidth(i);
                if (iMaxIntrinsicWidth2 > iMaxIntrinsicWidth) {
                    iMaxIntrinsicWidth = iMaxIntrinsicWidth2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return iMaxIntrinsicWidth;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m79measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        int iMax = 0;
        int iMax2 = 0;
        for (int i = 0; i < size; i++) {
            Placeable placeable = list.get(i).measure-BRTryo0(j);
            iMax = Math.max(iMax, placeable.getWidth());
            iMax2 = Math.max(iMax2, placeable.getHeight());
            arrayList.add(placeable);
        }
        if (measureScope.isLookingAhead()) {
            this.hasLookaheadOccurred = true;
            this.scope.getTargetSize$animation().setValue(IntSize.box-impl(IntSize.constructor-impl((((long) iMax2) & 4294967295L) | (((long) iMax) << 32))));
        } else if (!this.hasLookaheadOccurred) {
            this.scope.getTargetSize$animation().setValue(IntSize.box-impl(IntSize.constructor-impl((((long) iMax2) & 4294967295L) | (((long) iMax) << 32))));
        }
        return MeasureScope.layout$default(measureScope, iMax, iMax2, (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedEnterExitMeasurePolicy$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                List<Placeable> list2 = arrayList;
                int size2 = list2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    Placeable.PlacementScope.place$default(placementScope, list2.get(i2), 0, 0, 0.0f, 4, (Object) null);
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        if (list.isEmpty()) {
            return 0;
        }
        int iMinIntrinsicHeight = list.get(0).minIntrinsicHeight(i);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int iMinIntrinsicHeight2 = list.get(i2).minIntrinsicHeight(i);
                if (iMinIntrinsicHeight2 > iMinIntrinsicHeight) {
                    iMinIntrinsicHeight = iMinIntrinsicHeight2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return iMinIntrinsicHeight;
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        if (list.isEmpty()) {
            return 0;
        }
        int iMinIntrinsicWidth = list.get(0).minIntrinsicWidth(i);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int iMinIntrinsicWidth2 = list.get(i2).minIntrinsicWidth(i);
                if (iMinIntrinsicWidth2 > iMinIntrinsicWidth) {
                    iMinIntrinsicWidth = iMinIntrinsicWidth2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return iMinIntrinsicWidth;
    }

    public final void setHasLookaheadOccurred(boolean z) {
        this.hasLookaheadOccurred = z;
    }
}
