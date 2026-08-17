package androidx.compose.material3.carousel;

import androidx.collection.FloatList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0001\u0018\u0000 -2\u00020\u0001:\u0001-BM\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rB1\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\u000eJ'\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\b\b\u0002\u0010'\u001a\u00020!H\u0000¢\u0006\u0002\b(J\u0013\u0010)\u001a\u00020!2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010+\u001a\u00020,H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b \u0010\"R\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Landroidx/compose/material3/carousel/Strategy;", "", "defaultKeylines", "Landroidx/compose/material3/carousel/KeylineList;", "startKeylineSteps", "", "endKeylineSteps", "availableSpace", "", "itemSpacing", "beforeContentPadding", "afterContentPadding", "<init>", "(Landroidx/compose/material3/carousel/KeylineList;Ljava/util/List;Ljava/util/List;FFFF)V", "(Landroidx/compose/material3/carousel/KeylineList;FFFF)V", "getDefaultKeylines", "()Landroidx/compose/material3/carousel/KeylineList;", "getStartKeylineSteps", "()Ljava/util/List;", "getEndKeylineSteps", "getAvailableSpace", "()F", "getItemSpacing", "getBeforeContentPadding", "getAfterContentPadding", "startShiftDistance", "endShiftDistance", "startShiftPoints", "Landroidx/collection/FloatList;", "endShiftPoints", "itemMainAxisSize", "getItemMainAxisSize", "isValid", "", "()Z", "lastStartAndEndKeylineListSteps", "getKeylineListForScrollOffset", "scrollOffset", "maxScrollOffset", "roundToNearestStep", "getKeylineListForScrollOffset$material3", "equals", "other", "hashCode", "", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Strategy {
    private final float afterContentPadding;
    private final float availableSpace;
    private final float beforeContentPadding;
    private final KeylineList defaultKeylines;
    private final List<KeylineList> endKeylineSteps;
    private final float endShiftDistance;
    private final FloatList endShiftPoints;
    private final boolean isValid;
    private final float itemSpacing;
    private List<KeylineList> lastStartAndEndKeylineListSteps;
    private final List<KeylineList> startKeylineSteps;
    private final float startShiftDistance;
    private final FloatList startShiftPoints;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Strategy Empty = new Strategy(KeylineListKt.emptyKeylineList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), 0.0f, 0.0f, 0.0f, 0.0f);

    private Strategy(KeylineList keylineList, List<KeylineList> list, List<KeylineList> list2, float f, float f2, float f3, float f4) {
        this.defaultKeylines = keylineList;
        this.startKeylineSteps = list;
        this.endKeylineSteps = list2;
        this.availableSpace = f;
        this.itemSpacing = f2;
        this.beforeContentPadding = f3;
        this.afterContentPadding = f4;
        float startShiftDistance = StrategyKt.getStartShiftDistance(list, f3);
        this.startShiftDistance = startShiftDistance;
        float endShiftDistance = StrategyKt.getEndShiftDistance(list2, f4);
        this.endShiftDistance = endShiftDistance;
        this.startShiftPoints = StrategyKt.getStepInterpolationPoints(startShiftDistance, list, true);
        this.endShiftPoints = StrategyKt.getStepInterpolationPoints(endShiftDistance, list2, false);
        this.isValid = (keylineList.isEmpty() || f == 0.0f || getItemMainAxisSize() == 0.0f) ? false : true;
    }

    public static /* synthetic */ KeylineList getKeylineListForScrollOffset$material3$default(Strategy strategy, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return strategy.getKeylineListForScrollOffset$material3(f, f2, z);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Strategy)) {
            return false;
        }
        boolean z = this.isValid;
        if (!z && !((Strategy) other).isValid) {
            return true;
        }
        Strategy strategy = (Strategy) other;
        return z == strategy.isValid && this.availableSpace == strategy.availableSpace && this.itemSpacing == strategy.itemSpacing && this.beforeContentPadding == strategy.beforeContentPadding && this.afterContentPadding == strategy.afterContentPadding && getItemMainAxisSize() == strategy.getItemMainAxisSize() && this.startShiftDistance == strategy.startShiftDistance && this.endShiftDistance == strategy.endShiftDistance && Intrinsics.areEqual(this.startShiftPoints, strategy.startShiftPoints) && Intrinsics.areEqual(this.endShiftPoints, strategy.endShiftPoints) && Intrinsics.areEqual(this.defaultKeylines, strategy.defaultKeylines);
    }

    public final float getAfterContentPadding() {
        return this.afterContentPadding;
    }

    public final float getAvailableSpace() {
        return this.availableSpace;
    }

    public final float getBeforeContentPadding() {
        return this.beforeContentPadding;
    }

    public final KeylineList getDefaultKeylines() {
        return this.defaultKeylines;
    }

    public final List<KeylineList> getEndKeylineSteps() {
        return this.endKeylineSteps;
    }

    public final float getItemMainAxisSize() {
        return this.defaultKeylines.getFirstFocal().getSize();
    }

    public final float getItemSpacing() {
        return this.itemSpacing;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final KeylineList getKeylineListForScrollOffset$material3(float scrollOffset, float maxScrollOffset, boolean roundToNearestStep) {
        float fMax = Math.max(0.0f, scrollOffset);
        float f = this.startShiftDistance;
        float fMax2 = Math.max(0.0f, maxScrollOffset - this.endShiftDistance);
        if (f <= fMax && fMax <= fMax2) {
            return this.defaultKeylines;
        }
        float fLerp = StrategyKt.lerp(1.0f, 0.0f, 0.0f, f, fMax);
        FloatList floatList = this.startShiftPoints;
        List<KeylineList> list = this.startKeylineSteps;
        if (fMax > fMax2) {
            fLerp = StrategyKt.lerp(0.0f, 1.0f, fMax2, maxScrollOffset, fMax);
            floatList = this.endShiftPoints;
            list = this.endKeylineSteps;
            if (fMax2 < 0.01f && this.startKeylineSteps.size() == 2 && this.endKeylineSteps.size() == 2) {
                if (this.lastStartAndEndKeylineListSteps == null) {
                    this.lastStartAndEndKeylineListSteps = CollectionsKt.listOf(new KeylineList[]{CollectionsKt.last(this.startKeylineSteps), CollectionsKt.last(this.endKeylineSteps)});
                }
                list = this.lastStartAndEndKeylineListSteps;
                list.getClass();
            }
        }
        ShiftPointRange shiftPointRange = StrategyKt.getShiftPointRange(list.size(), floatList, fLerp);
        if (roundToNearestStep) {
            return list.get(MathKt.roundToInt(shiftPointRange.getSteppedInterpolation()) == 0 ? shiftPointRange.getFromStepIndex() : shiftPointRange.getToStepIndex());
        }
        return KeylineListKt.lerp(list.get(shiftPointRange.getFromStepIndex()), list.get(shiftPointRange.getToStepIndex()), shiftPointRange.getSteppedInterpolation());
    }

    public final List<KeylineList> getStartKeylineSteps() {
        return this.startKeylineSteps;
    }

    public int hashCode() {
        boolean z = this.isValid;
        return !z ? Boolean.hashCode(z) : (((((((((((((((((((Boolean.hashCode(z) * 31) + Float.hashCode(this.availableSpace)) * 31) + Float.hashCode(this.itemSpacing)) * 31) + Float.hashCode(this.beforeContentPadding)) * 31) + Float.hashCode(this.afterContentPadding)) * 31) + Float.hashCode(getItemMainAxisSize())) * 31) + Float.hashCode(this.startShiftDistance)) * 31) + Float.hashCode(this.endShiftDistance)) * 31) + this.startShiftPoints.hashCode()) * 31) + this.endShiftPoints.hashCode()) * 31) + this.defaultKeylines.hashCode();
    }

    /* JADX INFO: renamed from: isValid, reason: from getter */
    public final boolean getIsValid() {
        return this.isValid;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/material3/carousel/Strategy$Companion;", "", "<init>", "()V", "Empty", "Landroidx/compose/material3/carousel/Strategy;", "getEmpty", "()Landroidx/compose/material3/carousel/Strategy;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Strategy getEmpty() {
            return Strategy.Empty;
        }

        private Companion() {
        }
    }

    public Strategy(KeylineList keylineList, float f, float f2, float f3, float f4) {
        this(keylineList, StrategyKt.getStartKeylineSteps(keylineList, f, f2, f3), StrategyKt.getEndKeylineSteps(keylineList, f, f2, f4), f, f2, f3, f4);
    }
}
