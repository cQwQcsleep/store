package androidx.compose.animation;

import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/MatchIsOrHasBeenConfigured;", "Landroidx/compose/animation/SharedTransitionStateMachine$State;", "<init>", "()V", "targetData", "Landroidx/compose/animation/TargetData;", "getTargetData", "()Landroidx/compose/animation/TargetData;", "currentBounds", "Landroidx/compose/ui/geometry/Rect;", "getCurrentBounds", "()Landroidx/compose/ui/geometry/Rect;", "matchIsOrHasBeenConfigured", "", "getMatchIsOrHasBeenConfigured", "()Z", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class MatchIsOrHasBeenConfigured extends SharedTransitionStateMachine.State {
    public static final int $stable = 0;

    public MatchIsOrHasBeenConfigured() {
        super(null);
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public abstract Rect getCurrentBounds();

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public boolean getMatchIsOrHasBeenConfigured() {
        return true;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public abstract TargetData getTargetData();
}
