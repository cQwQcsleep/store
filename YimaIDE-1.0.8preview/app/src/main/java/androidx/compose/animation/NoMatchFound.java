package androidx.compose.animation;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0001H\u0016¨\u0006\b"}, d2 = {"Landroidx/compose/animation/NoMatchFound;", "Landroidx/compose/animation/SharedTransitionStateMachine$State;", "<init>", "()V", "onMatchFound", "previousTargetBoundsProvider", "Landroidx/compose/animation/BoundsProvider;", "onVisibleContentRemovedDuringTransition", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NoMatchFound extends SharedTransitionStateMachine.State {
    public static final int $stable = 0;
    public static final NoMatchFound INSTANCE = new NoMatchFound();

    private NoMatchFound() {
        super(null);
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onMatchFound(BoundsProvider previousTargetBoundsProvider) {
        return new ActiveMatchFoundConfigPending(previousTargetBoundsProvider, null, null, 6, null);
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onVisibleContentRemovedDuringTransition() {
        return this;
    }
}
