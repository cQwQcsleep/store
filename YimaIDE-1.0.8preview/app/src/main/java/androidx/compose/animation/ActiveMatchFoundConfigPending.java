package androidx.compose.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J7\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b%\u0010&J\u0012\u0010'\u001a\u00020\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0007H\u0016J\b\u0010,\u001a\u00020\u0001H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R/\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u00078V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Landroidx/compose/animation/ActiveMatchFoundConfigPending;", "Landroidx/compose/animation/SharedTransitionStateMachine$State;", "targetBoundsProviderBeforeConfig", "Landroidx/compose/animation/BoundsProvider;", "targetData", "Landroidx/compose/animation/TargetData;", "currentBounds", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Landroidx/compose/animation/BoundsProvider;Landroidx/compose/animation/TargetData;Landroidx/compose/ui/geometry/Rect;)V", "getTargetBoundsProviderBeforeConfig", "()Landroidx/compose/animation/BoundsProvider;", "setTargetBoundsProviderBeforeConfig", "(Landroidx/compose/animation/BoundsProvider;)V", "getTargetData", "()Landroidx/compose/animation/TargetData;", "activeMatchFound", "", "getActiveMatchFound", "()Z", "<set-?>", "getCurrentBounds", "()Landroidx/compose/ui/geometry/Rect;", "setCurrentBounds", "(Landroidx/compose/ui/geometry/Rect;)V", "currentBounds$delegate", "Landroidx/compose/runtime/MutableState;", "initializeCurrentBounds", "sharedElement", "Landroidx/compose/animation/SharedElement;", "configureActiveMatch", "targetBoundsProvider", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "structuralOffset", "configureActiveMatch-38uP1EE", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsProvider;JJJ)Landroidx/compose/animation/SharedTransitionStateMachine$State;", "onMatchFound", "previousTargetBoundsProvider", "updateBounds", "", "bounds", "onVisibleContentRemovedDuringTransition", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ActiveMatchFoundConfigPending extends SharedTransitionStateMachine.State {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: currentBounds$delegate, reason: from kotlin metadata */
    private final MutableState currentBounds;
    private BoundsProvider targetBoundsProviderBeforeConfig;
    private final TargetData targetData;

    public ActiveMatchFoundConfigPending(BoundsProvider boundsProvider, TargetData targetData, Rect rect) {
        super(null);
        this.targetBoundsProviderBeforeConfig = boundsProvider;
        this.targetData = targetData;
        this.currentBounds = SnapshotStateKt.mutableStateOf$default(rect, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    private void setCurrentBounds(Rect rect) {
        this.currentBounds.setValue(rect);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0059 A[PHI: r1
      0x0059: PHI (r1v1 androidx.compose.ui.geometry.Rect) = (r1v0 androidx.compose.ui.geometry.Rect), (r1v3 androidx.compose.ui.geometry.Rect) binds: [B:6:0x0018, B:22:0x0051] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    /* JADX INFO: renamed from: configureActiveMatch-38uP1EE */
    public SharedTransitionStateMachine.State mo42configureActiveMatch38uP1EE(SharedElement sharedElement, BoundsProvider targetBoundsProvider, long lookaheadSize, long topLeft, long structuralOffset) {
        long j;
        SharedElementEntry sharedElementEntry;
        TargetData targetData = getTargetData();
        if (targetData == null) {
            targetData = new TargetData(lookaheadSize, Offset.minus-MK-Hz9U(topLeft, structuralOffset), structuralOffset, null);
        }
        Rect currentBounds = getCurrentBounds();
        if (currentBounds != null) {
            j = topLeft;
        } else {
            BoundsProvider boundsProvider = this.targetBoundsProviderBeforeConfig;
            if (boundsProvider == null) {
                List<SharedElementEntry> allEntries = sharedElement.getAllEntries();
                int size = allEntries.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        sharedElementEntry = null;
                        break;
                    }
                    sharedElementEntry = allEntries.get(i);
                    if (sharedElement.getEnabledEntries().contains(sharedElementEntry)) {
                        break;
                    }
                    i++;
                }
                SharedElementEntry sharedElementEntry2 = sharedElementEntry;
                boundsProvider = sharedElementEntry2 != null ? sharedElementEntry2.getBoundsProvider() : null;
            }
            currentBounds = SharedTransitionStateMachineKt.obtainBoundsFromLastTarget(sharedElement, boundsProvider);
            if (currentBounds == null) {
                j = topLeft;
                currentBounds = RectKt.Rect-tz77jQw(j, lookaheadSize);
            } else {
                j = topLeft;
            }
        }
        TargetData targetData2 = targetData;
        SharedTransitionStateMachineKt.m163updateTargetDataBGTQxF0(targetData2, lookaheadSize, j, structuralOffset, true);
        return new ActiveMatchConfigured(targetData2, targetBoundsProvider, currentBounds);
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public boolean getActiveMatchFound() {
        return true;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public Rect getCurrentBounds() {
        return (Rect) this.currentBounds.getValue();
    }

    public final BoundsProvider getTargetBoundsProviderBeforeConfig() {
        return this.targetBoundsProviderBeforeConfig;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public TargetData getTargetData() {
        return this.targetData;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public Rect initializeCurrentBounds(SharedElement sharedElement) {
        SharedElementEntry sharedElementEntry;
        Rect currentBounds = getCurrentBounds();
        if (currentBounds != null) {
            return currentBounds;
        }
        if (getCurrentBounds() == null) {
            BoundsProvider boundsProvider = this.targetBoundsProviderBeforeConfig;
            if (boundsProvider == null) {
                List<SharedElementEntry> allEntries = sharedElement.getAllEntries();
                int size = allEntries.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        sharedElementEntry = null;
                        break;
                    }
                    sharedElementEntry = allEntries.get(i);
                    if (sharedElement.getEnabledEntries().contains(sharedElementEntry)) {
                        break;
                    }
                    i++;
                }
                SharedElementEntry sharedElementEntry2 = sharedElementEntry;
                boundsProvider = sharedElementEntry2 != null ? sharedElementEntry2.getBoundsProvider() : null;
            }
            Rect rectObtainBoundsFromLastTarget = SharedTransitionStateMachineKt.obtainBoundsFromLastTarget(sharedElement, boundsProvider);
            if (rectObtainBoundsFromLastTarget != null) {
                setCurrentBounds(rectObtainBoundsFromLastTarget);
            }
        }
        return getCurrentBounds();
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onMatchFound(BoundsProvider previousTargetBoundsProvider) {
        if (this.targetBoundsProviderBeforeConfig == null) {
            this.targetBoundsProviderBeforeConfig = previousTargetBoundsProvider;
        }
        return this;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onVisibleContentRemovedDuringTransition() {
        return NoMatchFound.INSTANCE;
    }

    public final void setTargetBoundsProviderBeforeConfig(BoundsProvider boundsProvider) {
        this.targetBoundsProviderBeforeConfig = boundsProvider;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public void updateBounds(Rect bounds) {
        setCurrentBounds(bounds);
    }

    public /* synthetic */ ActiveMatchFoundConfigPending(BoundsProvider boundsProvider, TargetData targetData, Rect rect, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(boundsProvider, (i & 2) != 0 ? null : targetData, (i & 4) != 0 ? null : rect);
    }
}
