package androidx.compose.animation;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001:\u0001BB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010 \u001a\u00020!J\u000e\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020\u001fJ\u0006\u00102\u001a\u00020!J\u000e\u00103\u001a\u00020!2\u0006\u00104\u001a\u00020\tJ\u0006\u00105\u001a\u00020!J\r\u00106\u001a\u00020!H\u0000¢\u0006\u0002\b7J\b\u00108\u001a\u0004\u0018\u000109J%\u0010:\u001a\u00020!2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>¢\u0006\u0004\b@\u0010AR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0017\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00168B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020$0#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010&R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010+\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00168B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u001d\u001a\u0004\b,\u0010\u0019\"\u0004\b-\u0010\u001bR\u000e\u0010/\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Landroidx/compose/animation/SharedTransitionStateMachine;", "", "sharedElement", "Landroidx/compose/animation/SharedElement;", "<init>", "(Landroidx/compose/animation/SharedElement;)V", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "activeMatchDeferred", "", "getActiveMatchDeferred", "()Z", "<set-?>", "Landroidx/compose/animation/SharedTransitionStateMachine$State;", "state", "getState", "()Landroidx/compose/animation/SharedTransitionStateMachine$State;", "setState", "(Landroidx/compose/animation/SharedTransitionStateMachine$State;)V", "state$delegate", "Landroidx/compose/runtime/MutableState;", "lastHandledRequestId", "", "requestId", "getRequestId", "()I", "setRequestId", "(I)V", "requestId$delegate", "Landroidx/compose/runtime/MutableIntState;", "requestToBeHandled", "Landroidx/compose/animation/StateChangeRequest;", "resetState", "", "allEntries", "", "Landroidx/compose/animation/SharedElementEntry;", "getAllEntries", "()Ljava/util/List;", "enabledEntries", "getEnabledEntries", "targetBoundsProvider", "Landroidx/compose/animation/BoundsProvider;", "targetBoundsProviderUpdateRequestId", "getTargetBoundsProviderUpdateRequestId", "setTargetBoundsProviderUpdateRequestId", "targetBoundsProviderUpdateRequestId$delegate", "lastHandledTargetProviderUpdateRequestId", "deferRequest", "request", "processPendingRequest", "checkForAndDeferStateUpdates", "hasVisibleContent", "invalidateTargetBoundsProvider", "updateTargetBoundsProvider", "updateTargetBoundsProvider$animation", "tryInitializingCurrentBounds", "Landroidx/compose/ui/geometry/Rect;", "configureActiveMatch", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "structuralOffset", "configureActiveMatch-L7TYDSY", "(JJJ)V", "State", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SharedTransitionStateMachine {
    public static final int $stable = 8;
    private int lastHandledRequestId;
    private int lastHandledTargetProviderUpdateRequestId;
    private final SharedElement sharedElement;
    private BoundsProvider targetBoundsProvider;

    /* JADX INFO: renamed from: state$delegate, reason: from kotlin metadata */
    private final MutableState state = SnapshotStateKt.mutableStateOf$default(NoMatchFound.INSTANCE, (SnapshotMutationPolicy) null, 2, (Object) null);

    /* JADX INFO: renamed from: requestId$delegate, reason: from kotlin metadata */
    private final MutableIntState requestId = SnapshotIntStateKt.mutableIntStateOf(0);
    private StateChangeRequest requestToBeHandled = StateChangeRequest.NoRequest;

    /* JADX INFO: renamed from: targetBoundsProviderUpdateRequestId$delegate, reason: from kotlin metadata */
    private final MutableIntState targetBoundsProviderUpdateRequestId = SnapshotIntStateKt.mutableIntStateOf(0);

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StateChangeRequest.values().length];
            try {
                iArr[StateChangeRequest.NoMatchFound.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[StateChangeRequest.NoRequest.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[StateChangeRequest.MatchFound.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[StateChangeRequest.VisibleContentAbsentDuringTransition.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SharedTransitionStateMachine(SharedElement sharedElement) {
        this.sharedElement = sharedElement;
    }

    private final List<SharedElementEntry> getAllEntries() {
        return this.sharedElement.getAllEntries();
    }

    private final List<SharedElementEntry> getEnabledEntries() {
        return this.sharedElement.getEnabledEntries();
    }

    private final int getRequestId() {
        return this.requestId.getIntValue();
    }

    private final int getTargetBoundsProviderUpdateRequestId() {
        return this.targetBoundsProviderUpdateRequestId.getIntValue();
    }

    private final void setRequestId(int i) {
        this.requestId.setIntValue(i);
    }

    private final void setState(State state) {
        this.state.setValue(state);
    }

    private final void setTargetBoundsProviderUpdateRequestId(int i) {
        this.targetBoundsProviderUpdateRequestId.setIntValue(i);
    }

    public final void checkForAndDeferStateUpdates(boolean hasVisibleContent) {
        if (getEnabledEntries().size() > 1 && hasVisibleContent) {
            deferRequest(StateChangeRequest.MatchFound);
        } else if (!this.sharedElement.getScope().isTransitionActive()) {
            resetState();
        } else if (!hasVisibleContent) {
            deferRequest(StateChangeRequest.VisibleContentAbsentDuringTransition);
        }
        invalidateTargetBoundsProvider();
    }

    /* JADX INFO: renamed from: configureActiveMatch-L7TYDSY, reason: not valid java name */
    public final void m161configureActiveMatchL7TYDSY(long lookaheadSize, long topLeft, long structuralOffset) {
        State state = getState();
        SharedElement sharedElement = this.sharedElement;
        BoundsProvider boundsProvider = this.targetBoundsProvider;
        boundsProvider.getClass();
        setState(state.mo42configureActiveMatch38uP1EE(sharedElement, boundsProvider, lookaheadSize, topLeft, structuralOffset));
    }

    public final void deferRequest(StateChangeRequest request) {
        this.requestToBeHandled = request;
        setRequestId(this.lastHandledRequestId + 1);
    }

    public final boolean getActiveMatchDeferred() {
        return this.requestToBeHandled == StateChangeRequest.MatchFound;
    }

    public final SharedElement getSharedElement() {
        return this.sharedElement;
    }

    public final State getState() {
        return (State) this.state.getValue();
    }

    public final void invalidateTargetBoundsProvider() {
        SharedElementEntry sharedElementEntry;
        List<SharedElementEntry> enabledEntries = getEnabledEntries();
        int size = enabledEntries.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                sharedElementEntry = null;
                break;
            }
            sharedElementEntry = enabledEntries.get(i);
            if (sharedElementEntry.getTarget()) {
                break;
            } else {
                i++;
            }
        }
        SharedElementEntry sharedElementEntry2 = sharedElementEntry;
        if (sharedElementEntry2 == null && this.targetBoundsProvider == null) {
            return;
        }
        if (Intrinsics.areEqual(sharedElementEntry2 != null ? sharedElementEntry2.getBoundsProvider() : null, this.targetBoundsProvider)) {
            return;
        }
        setTargetBoundsProviderUpdateRequestId(this.lastHandledTargetProviderUpdateRequestId + 1);
    }

    public final void processPendingRequest() {
        State state;
        if (getRequestId() != this.lastHandledRequestId) {
            this.lastHandledRequestId = getRequestId();
            int i = WhenMappings.$EnumSwitchMapping$0[this.requestToBeHandled.ordinal()];
            if (i == 1) {
                state = NoMatchFound.INSTANCE;
            } else if (i == 2) {
                state = getState();
            } else if (i == 3) {
                state = getState().onMatchFound(this.targetBoundsProvider);
            } else {
                if (i != 4) {
                    bu8.a();
                    return;
                }
                List<SharedElementEntry> enabledEntries = getEnabledEntries();
                int size = enabledEntries.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        state = getState().onVisibleContentRemovedDuringTransition();
                        break;
                    } else {
                        if (Intrinsics.areEqual(enabledEntries.get(i2).getBoundsProvider(), this.targetBoundsProvider)) {
                            state = NoMatchFound.INSTANCE;
                            break;
                        }
                        i2++;
                    }
                }
            }
            setState(state);
            this.requestToBeHandled = StateChangeRequest.NoRequest;
        }
        updateTargetBoundsProvider$animation();
    }

    public final void resetState() {
        this.requestToBeHandled = StateChangeRequest.NoRequest;
        this.lastHandledRequestId = getRequestId();
        setState(NoMatchFound.INSTANCE);
    }

    public final Rect tryInitializingCurrentBounds() {
        processPendingRequest();
        return getState().initializeCurrentBounds(this.sharedElement);
    }

    public final void updateTargetBoundsProvider$animation() {
        SharedElementEntry sharedElementEntry;
        SharedElementEntry sharedElementEntry2;
        if (getTargetBoundsProviderUpdateRequestId() != this.lastHandledTargetProviderUpdateRequestId) {
            int i = 0;
            BoundsProvider boundsProvider = null;
            if (this.sharedElement.getScope().isTransitionActive()) {
                List<SharedElementEntry> enabledEntries = getEnabledEntries();
                int size = enabledEntries.size();
                while (true) {
                    if (i >= size) {
                        sharedElementEntry2 = null;
                        break;
                    }
                    sharedElementEntry2 = enabledEntries.get(i);
                    if (sharedElementEntry2.getTarget()) {
                        break;
                    } else {
                        i++;
                    }
                }
                SharedElementEntry sharedElementEntry3 = sharedElementEntry2;
                if (sharedElementEntry3 != null) {
                    boundsProvider = sharedElementEntry3.getBoundsProvider();
                }
            } else {
                List<SharedElementEntry> allEntries = getAllEntries();
                int size2 = allEntries.size();
                while (true) {
                    if (i >= size2) {
                        sharedElementEntry = null;
                        break;
                    }
                    sharedElementEntry = allEntries.get(i);
                    if (sharedElementEntry.getTarget()) {
                        break;
                    } else {
                        i++;
                    }
                }
                SharedElementEntry sharedElementEntry4 = sharedElementEntry;
                if (sharedElementEntry4 != null) {
                    boundsProvider = sharedElementEntry4.getBoundsProvider();
                }
            }
            if (!Intrinsics.areEqual(boundsProvider, this.targetBoundsProvider)) {
                this.targetBoundsProvider = boundsProvider;
            }
            this.lastHandledTargetProviderUpdateRequestId = getTargetBoundsProviderUpdateRequestId();
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b1\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0016J\u0012\u0010\u0015\u001a\u00020\u00002\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J\b\u0010\u0018\u001a\u00020\u0000H&J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J7\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b#\u0010$R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f\u0082\u0001\u0003%&'¨\u0006("}, d2 = {"Landroidx/compose/animation/SharedTransitionStateMachine$State;", "", "<init>", "()V", "targetData", "Landroidx/compose/animation/TargetData;", "getTargetData", "()Landroidx/compose/animation/TargetData;", "currentBounds", "Landroidx/compose/ui/geometry/Rect;", "getCurrentBounds", "()Landroidx/compose/ui/geometry/Rect;", "activeMatchFound", "", "getActiveMatchFound", "()Z", "matchIsOrHasBeenConfigured", "getMatchIsOrHasBeenConfigured", "updateBounds", "", "bounds", "onMatchFound", "previousTargetBoundsProvider", "Landroidx/compose/animation/BoundsProvider;", "onVisibleContentRemovedDuringTransition", "initializeCurrentBounds", "sharedElement", "Landroidx/compose/animation/SharedElement;", "configureActiveMatch", "targetBoundsProvider", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "structuralOffset", "configureActiveMatch-38uP1EE", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsProvider;JJJ)Landroidx/compose/animation/SharedTransitionStateMachine$State;", "Landroidx/compose/animation/ActiveMatchFoundConfigPending;", "Landroidx/compose/animation/MatchIsOrHasBeenConfigured;", "Landroidx/compose/animation/NoMatchFound;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static abstract class State {
        public static final int $stable = 0;

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: configureActiveMatch-38uP1EE */
        public State mo42configureActiveMatch38uP1EE(SharedElement sharedElement, BoundsProvider targetBoundsProvider, long lookaheadSize, long topLeft, long structuralOffset) {
            throw new IllegalStateException(("Active match can only be configured in ActiveMatchFoundConfigPending or ActiveMatchConfigured state. Current state: " + this).toString());
        }

        public boolean getActiveMatchFound() {
            return false;
        }

        public Rect getCurrentBounds() {
            return null;
        }

        public boolean getMatchIsOrHasBeenConfigured() {
            return false;
        }

        public TargetData getTargetData() {
            return null;
        }

        public Rect initializeCurrentBounds(SharedElement sharedElement) {
            return getCurrentBounds();
        }

        public abstract State onMatchFound(BoundsProvider previousTargetBoundsProvider);

        public abstract State onVisibleContentRemovedDuringTransition();

        public void updateBounds(Rect bounds) {
        }

        private State() {
        }
    }
}
