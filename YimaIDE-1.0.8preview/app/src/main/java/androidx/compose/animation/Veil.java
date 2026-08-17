package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0014\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0016\u0010\fJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J>\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\""}, d2 = {"Landroidx/compose/animation/Veil;", "", "initialColor", "Landroidx/compose/ui/graphics/Color;", "targetColor", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "matchParentSize", "", "<init>", "(JJLandroidx/compose/animation/core/FiniteAnimationSpec;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getInitialColor-0d7_KjU", "()J", "J", "getTargetColor-0d7_KjU", "getAnimationSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "getMatchParentSize", "()Z", "component1", "component1-0d7_KjU", "component2", "component2-0d7_KjU", "component3", "component4", "copy", "copy-RFnl5yQ", "(JJLandroidx/compose/animation/core/FiniteAnimationSpec;Z)Landroidx/compose/animation/Veil;", "equals", "other", "hashCode", "", "toString", "", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class Veil {
    public static final int $stable = 0;
    private final FiniteAnimationSpec<Color> animationSpec;
    private final long initialColor;
    private final boolean matchParentSize;
    private final long targetColor;

    private Veil(long j, long j2, FiniteAnimationSpec<Color> finiteAnimationSpec, boolean z) {
        this.initialColor = j;
        this.targetColor = j2;
        this.animationSpec = finiteAnimationSpec;
        this.matchParentSize = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-RFnl5yQ$default, reason: not valid java name */
    public static /* synthetic */ Veil m192copyRFnl5yQ$default(Veil veil, long j, long j2, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = veil.initialColor;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = veil.targetColor;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            finiteAnimationSpec = veil.animationSpec;
        }
        FiniteAnimationSpec finiteAnimationSpec2 = finiteAnimationSpec;
        if ((i & 8) != 0) {
            z = veil.matchParentSize;
        }
        return veil.m195copyRFnl5yQ(j3, j4, finiteAnimationSpec2, z);
    }

    /* JADX INFO: renamed from: component1-0d7_KjU, reason: not valid java name and from getter */
    public final long getInitialColor() {
        return this.initialColor;
    }

    /* JADX INFO: renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
    public final long getTargetColor() {
        return this.targetColor;
    }

    public final FiniteAnimationSpec<Color> component3() {
        return this.animationSpec;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getMatchParentSize() {
        return this.matchParentSize;
    }

    /* JADX INFO: renamed from: copy-RFnl5yQ, reason: not valid java name */
    public final Veil m195copyRFnl5yQ(long initialColor, long targetColor, FiniteAnimationSpec<Color> animationSpec, boolean matchParentSize) {
        return new Veil(initialColor, targetColor, animationSpec, matchParentSize, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Veil)) {
            return false;
        }
        Veil veil = (Veil) other;
        return Color.equals-impl0(this.initialColor, veil.initialColor) && Color.equals-impl0(this.targetColor, veil.targetColor) && Intrinsics.areEqual(this.animationSpec, veil.animationSpec) && this.matchParentSize == veil.matchParentSize;
    }

    public final FiniteAnimationSpec<Color> getAnimationSpec() {
        return this.animationSpec;
    }

    /* JADX INFO: renamed from: getInitialColor-0d7_KjU, reason: not valid java name */
    public final long m196getInitialColor0d7_KjU() {
        return this.initialColor;
    }

    public final boolean getMatchParentSize() {
        return this.matchParentSize;
    }

    /* JADX INFO: renamed from: getTargetColor-0d7_KjU, reason: not valid java name */
    public final long m197getTargetColor0d7_KjU() {
        return this.targetColor;
    }

    public int hashCode() {
        return (((((Color.hashCode-impl(this.initialColor) * 31) + Color.hashCode-impl(this.targetColor)) * 31) + this.animationSpec.hashCode()) * 31) + Boolean.hashCode(this.matchParentSize);
    }

    public String toString() {
        return "Veil(initialColor=" + ((Object) Color.toString-impl(this.initialColor)) + ", targetColor=" + ((Object) Color.toString-impl(this.targetColor)) + ", animationSpec=" + this.animationSpec + ", matchParentSize=" + this.matchParentSize + ')';
    }

    public /* synthetic */ Veil(long j, long j2, FiniteAnimationSpec finiteAnimationSpec, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, finiteAnimationSpec, z);
    }
}
