package androidx.compose.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00058V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001f"}, d2 = {"Landroidx/compose/animation/ActiveMatchRemovedDuringTransition;", "Landroidx/compose/animation/MatchIsOrHasBeenConfigured;", "targetData", "Landroidx/compose/animation/TargetData;", "currentBounds", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Landroidx/compose/animation/TargetData;Landroidx/compose/ui/geometry/Rect;)V", "getTargetData", "()Landroidx/compose/animation/TargetData;", "<set-?>", "getCurrentBounds", "()Landroidx/compose/ui/geometry/Rect;", "setCurrentBounds", "(Landroidx/compose/ui/geometry/Rect;)V", "currentBounds$delegate", "Landroidx/compose/runtime/MutableState;", "alternativeTargetConfigured", "", "getAlternativeTargetConfigured", "()Z", "setAlternativeTargetConfigured", "(Z)V", "onMatchFound", "Landroidx/compose/animation/SharedTransitionStateMachine$State;", "previousTargetBoundsProvider", "Landroidx/compose/animation/BoundsProvider;", "updateBounds", "", "bounds", "onVisibleContentRemovedDuringTransition", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ActiveMatchRemovedDuringTransition extends MatchIsOrHasBeenConfigured {
    public static final int $stable = 8;
    private boolean alternativeTargetConfigured;

    /* JADX INFO: renamed from: currentBounds$delegate, reason: from kotlin metadata */
    private final MutableState currentBounds;
    private final TargetData targetData;

    public ActiveMatchRemovedDuringTransition(TargetData targetData, Rect rect) {
        this.targetData = targetData;
        this.currentBounds = SnapshotStateKt.mutableStateOf$default(rect, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    private void setCurrentBounds(Rect rect) {
        this.currentBounds.setValue(rect);
    }

    public final boolean getAlternativeTargetConfigured() {
        return this.alternativeTargetConfigured;
    }

    @Override // androidx.compose.animation.MatchIsOrHasBeenConfigured, androidx.compose.animation.SharedTransitionStateMachine.State
    public Rect getCurrentBounds() {
        return (Rect) this.currentBounds.getValue();
    }

    @Override // androidx.compose.animation.MatchIsOrHasBeenConfigured, androidx.compose.animation.SharedTransitionStateMachine.State
    public TargetData getTargetData() {
        return this.targetData;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onMatchFound(BoundsProvider previousTargetBoundsProvider) {
        return new ActiveMatchFoundConfigPending(previousTargetBoundsProvider, getTargetData(), getCurrentBounds());
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onVisibleContentRemovedDuringTransition() {
        return this;
    }

    public final void setAlternativeTargetConfigured(boolean z) {
        this.alternativeTargetConfigured = z;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public void updateBounds(Rect bounds) {
        setCurrentBounds(bounds);
    }
}
