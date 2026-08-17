package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 ]2\u00020\u0001:\u0001]B\u001d\b\u0004\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0015\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\u000bJ\b\u0010%\u001a\u00020&H\u0016J \u0010'\u001a\u00020\u00002\u0016\b\u0002\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020&\u0018\u00010)H&J\b\u0010*\u001a\u00020\"H&J\"\u0010+\u001a\u0002H,\"\u0004\b\u0000\u0010,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.H\u0086\b¢\u0006\u0002\u0010/J\n\u00100\u001a\u0004\u0018\u00010\u0000H\u0011J\u0012\u00101\u001a\u00020&2\b\u00102\u001a\u0004\u0018\u00010\u0000H\u0011J\b\u00103\u001a\u0004\u0018\u00010\u0000J\u0010\u00104\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u00010\u0000J\u0015\u0010C\u001a\u00020&2\u0006\u00102\u001a\u00020\u0000H ¢\u0006\u0002\bDJ\u0015\u0010E\u001a\u00020&2\u0006\u00102\u001a\u00020\u0000H ¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020&2\u0006\u0010H\u001a\u00020IH ¢\u0006\u0002\bJJ\r\u0010O\u001a\u00020&H ¢\u0006\u0002\bPJ\r\u0010Q\u001a\u00020&H\u0000¢\u0006\u0002\bRJ\r\u0010S\u001a\u00020&H\u0010¢\u0006\u0002\bTJ\r\u0010U\u001a\u00020&H\u0010¢\u0006\u0002\bVJ\r\u0010W\u001a\u00020&H\u0000¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020&H\u0000¢\u0006\u0002\bZJ\r\u0010[\u001a\u00020\nH\u0000¢\u0006\u0002\b\\R\u001a\u0010\u0005\u001a\u00020\u0006X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\t\u001a\u00020\n8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R.\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\n\u0010\u0014\u001a\u00060\u0003j\u0002`\u0004@PX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u001a\u00106\u001a\u00020\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\nX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b;\u0010\u0011R\u0015\u0010<\u001a\u00020\"8À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b=\u0010$R(\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020&\u0018\u00010)8 X¡\u0004¢\u0006\f\u0012\u0004\b>\u0010\u0011\u001a\u0004\b?\u0010@R \u0010A\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020&\u0018\u00010)X \u0004¢\u0006\u0006\u001a\u0004\bB\u0010@R\u001a\u0010K\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010LX \u0004¢\u0006\u0006\u001a\u0004\bM\u0010N\u0082\u0001\u0004^_`a¨\u0006b"}, d2 = {"Landroidx/compose/runtime/snapshots/Snapshot;", "", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "id", "", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "getInvalid$runtime", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setInvalid$runtime", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "getId$annotations", "()V", "getId", "()I", "value", "getSnapshotId", "()J", "setSnapshotId$runtime", "(J)V", "J", "writeCount", "getWriteCount$runtime", "setWriteCount$runtime", "(I)V", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "readOnly", "", "getReadOnly", "()Z", "dispose", "", "takeNestedSnapshot", "readObserver", "Lkotlin/Function1;", "hasPendingChanges", "enter", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "makeCurrent", "restoreCurrent", "snapshot", "unsafeEnter", "unsafeLeave", "oldSnapshot", "disposed", "getDisposed$runtime", "setDisposed$runtime", "(Z)V", "pinningTrackingHandle", "getPinningTrackingHandle$annotations", "isPinned", "isPinned$runtime", "getReadObserver$annotations", "getReadObserver", "()Lkotlin/jvm/functions/Function1;", "writeObserver", "getWriteObserver$runtime", "nestedActivated", "nestedActivated$runtime", "nestedDeactivated", "nestedDeactivated$runtime", "recordModified", "state", "Landroidx/compose/runtime/snapshots/StateObject;", "recordModified$runtime", "modified", "Landroidx/collection/MutableScatterSet;", "getModified$runtime", "()Landroidx/collection/MutableScatterSet;", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "closeAndReleasePinning", "closeAndReleasePinning$runtime", "closeLocked", "closeLocked$runtime", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime", "validateNotDisposed", "validateNotDisposed$runtime", "releasePinnedSnapshotLocked", "releasePinnedSnapshotLocked$runtime", "takeoverPinnedSnapshot", "takeoverPinnedSnapshot$runtime", "Companion", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/NestedReadonlySnapshot;", "Landroidx/compose/runtime/snapshots/ReadonlySnapshot;", "Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class Snapshot {
    public static final int PreexistingSnapshotId = 1;
    private boolean disposed;
    private SnapshotIdSet invalid;
    private int pinningTrackingHandle;
    private long snapshotId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    private Snapshot(long j, SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
        this.snapshotId = j;
        this.pinningTrackingHandle = j != SnapshotKt.INVALID_SNAPSHOT ? SnapshotKt.trackPinning(j, getInvalid()) : -1;
    }

    @Deprecated(message = "Use snapshotId instead", replaceWith = @ReplaceWith(expression = "snapshotId", imports = {}))
    public static /* synthetic */ void getId$annotations() {
    }

    private static /* synthetic */ void getPinningTrackingHandle$annotations() {
    }

    public static /* synthetic */ void getReadObserver$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Snapshot takeNestedSnapshot$default(Snapshot snapshot, Function1 function1, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: takeNestedSnapshot");
            return null;
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        return snapshot.takeNestedSnapshot(function1);
    }

    public final void closeAndReleasePinning$runtime() {
        synchronized (SnapshotKt.getLock()) {
            closeLocked$runtime();
            releasePinnedSnapshotsForCloseLocked$runtime();
            Unit unit = Unit.INSTANCE;
        }
    }

    public void closeLocked$runtime() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getSnapshotId());
    }

    public void dispose() {
        this.disposed = true;
        synchronized (SnapshotKt.getLock()) {
            releasePinnedSnapshotLocked$runtime();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final <T> T enter(Function0<? extends T> block) {
        Snapshot snapshotMakeCurrent = makeCurrent();
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            restoreCurrent(snapshotMakeCurrent);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: renamed from: getDisposed$runtime, reason: from getter */
    public final boolean getDisposed() {
        return this.disposed;
    }

    public int getId() {
        return (int) getSnapshotId();
    }

    /* JADX INFO: renamed from: getInvalid$runtime, reason: from getter */
    public SnapshotIdSet getInvalid() {
        return this.invalid;
    }

    public abstract MutableScatterSet<StateObject> getModified$runtime();

    public abstract Function1<Object, Unit> getReadObserver();

    public abstract boolean getReadOnly();

    public abstract Snapshot getRoot();

    public long getSnapshotId() {
        return this.snapshotId;
    }

    public int getWriteCount$runtime() {
        return 0;
    }

    public abstract Function1<Object, Unit> getWriteObserver$runtime();

    public abstract boolean hasPendingChanges();

    public final boolean isPinned$runtime() {
        return this.pinningTrackingHandle >= 0;
    }

    public Snapshot makeCurrent() {
        Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
        SnapshotKt.threadSnapshot.set(this);
        return snapshot;
    }

    /* JADX INFO: renamed from: nestedActivated$runtime */
    public abstract void mo2567nestedActivated$runtime(Snapshot snapshot);

    /* JADX INFO: renamed from: nestedDeactivated$runtime */
    public abstract void mo2568nestedDeactivated$runtime(Snapshot snapshot);

    public abstract void notifyObjectsInitialized$runtime();

    /* JADX INFO: renamed from: recordModified$runtime */
    public abstract void mo2569recordModified$runtime(StateObject state);

    public final void releasePinnedSnapshotLocked$runtime() {
        int i = this.pinningTrackingHandle;
        if (i >= 0) {
            SnapshotKt.releasePinningLocked(i);
            this.pinningTrackingHandle = -1;
        }
    }

    public void releasePinnedSnapshotsForCloseLocked$runtime() {
        releasePinnedSnapshotLocked$runtime();
    }

    public void restoreCurrent(Snapshot snapshot) {
        SnapshotKt.threadSnapshot.set(snapshot);
    }

    public final void setDisposed$runtime(boolean z) {
        this.disposed = z;
    }

    public void setInvalid$runtime(SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
    }

    public void setSnapshotId$runtime(long j) {
        this.snapshotId = j;
    }

    public void setWriteCount$runtime(int i) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot");
    }

    public abstract Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver);

    public final int takeoverPinnedSnapshot$runtime() {
        int i = this.pinningTrackingHandle;
        this.pinningTrackingHandle = -1;
        return i;
    }

    public final Snapshot unsafeEnter() {
        return makeCurrent();
    }

    public final void unsafeLeave(Snapshot oldSnapshot) {
        if (!(SnapshotKt.threadSnapshot.get() == this)) {
            PreconditionsKt.throwIllegalStateException("Cannot leave snapshot; " + this + " is not the current snapshot");
        }
        restoreCurrent(oldSnapshot);
    }

    public final void validateNotDisposed$runtime() {
        if (this.disposed) {
            PreconditionsKt.throwIllegalArgumentException("Cannot use a disposed snapshot");
        }
    }

    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000f\u001a\u00020\u00052\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011J6\u0010\u0013\u001a\u00020\u00142\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011J\"\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0086\b¢\u0006\u0002\u0010\u001aJ\"\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u0019H\u0086\b¢\u0006\u0002\u0010\u001aJO\u0010\u001d\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019¢\u0006\u0002\u0010\u001eJV\u0010\u001f\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0016\b\n\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0016\b\n\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0080\b¢\u0006\u0004\b \u0010\u001eJ\b\u0010!\u001a\u00020\u0005H\u0001J\u0012\u0010+\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010\u0005H\u0001J0\u0010-\u001a\u00020\u00122\b\u0010,\u001a\u0004\u0018\u00010\u00052\u0006\u0010.\u001a\u00020\u00052\u0014\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0001J4\u00100\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u0002H\u00170\u0019¢\u0006\u0002\b1H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u001aJ&\u00102\u001a\u0002032\u001e\u0010/\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000105\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001204J\u001a\u00106\u001a\u0002032\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00120\u0011J\u0006\u00107\u001a\u00020\u0012J\u0006\u00108\u001a\u00020\u0012J\b\u00109\u001a\u00020\rH\u0007J\n\u0010:\u001a\u0004\u0018\u00010\u0005H\u0001J\u0012\u0010;\u001a\u00020\u00122\b\u0010,\u001a\u0004\u0018\u00010\u0005H\u0001R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0011\u0010\u000b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\rX\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u001c\u0010\"\u001a\u0004\u0018\u00010\u00058@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u0003\u001a\u0004\b$\u0010\u0007R\u0019\u0010%\u001a\u00020\t*\u00020&8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0019\u0010%\u001a\u00020\t*\u00020)8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010*¨\u0006<"}, d2 = {"Landroidx/compose/runtime/snapshots/Snapshot$Companion;", "", "<init>", "()V", "current", "Landroidx/compose/runtime/snapshots/Snapshot;", "getCurrent", "()Landroidx/compose/runtime/snapshots/Snapshot;", "isInSnapshot", "", "()Z", "isApplyObserverNotificationPending", "PreexistingSnapshotId", "", "getPreexistingSnapshotId$annotations", "takeSnapshot", "readObserver", "Lkotlin/Function1;", "", "takeMutableSnapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "writeObserver", "global", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withMutableSnapshot", "R", "observe", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "observeInternal", "observeInternal$runtime", "createNonObservableSnapshot", "currentThreadSnapshot", "getCurrentThreadSnapshot$annotations", "getCurrentThreadSnapshot", "canBeReused", "Landroidx/compose/runtime/snapshots/TransparentObserverMutableSnapshot;", "getCanBeReused", "(Landroidx/compose/runtime/snapshots/TransparentObserverMutableSnapshot;)Z", "Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;", "(Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;)Z", "makeCurrentNonObservable", "previous", "restoreNonObservable", "nonObservable", "observer", "withoutReadObservation", "Landroidx/compose/runtime/DisallowComposableCalls;", "registerApplyObserver", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "Lkotlin/Function2;", "", "registerGlobalWriteObserver", "notifyObjectsInitialized", "sendApplyNotifications", "openSnapshotCount", "removeCurrent", "restoreCurrent", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static void a(Function2 function2) {
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.applyObservers = CollectionsKt.minus(SnapshotKt.applyObservers, function2);
                Unit unit = Unit.INSTANCE;
            }
        }

        public static void b(Function1 function1) {
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.globalWriteObservers = CollectionsKt.minus(SnapshotKt.globalWriteObservers, function1);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.advanceGlobalSnapshot();
        }

        private final boolean getCanBeReused(TransparentObserverMutableSnapshot transparentObserverMutableSnapshot) {
            return transparentObserverMutableSnapshot.getThreadId() == Thread_jvmKt.currentThreadId();
        }

        public static /* synthetic */ void getCurrentThreadSnapshot$annotations() {
        }

        public static /* synthetic */ void getPreexistingSnapshotId$annotations() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object observe$default(Companion companion, Function1 function1, Function1 function2, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            if ((i & 2) != 0) {
                function2 = null;
            }
            return companion.observe(function1, function2, function0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object observeInternal$runtime$default(Companion companion, Function1 function1, Function1 function2, Function0 function0, int i, Object obj) {
            Snapshot transparentObserverMutableSnapshot;
            Function1 function3 = (i & 1) != 0 ? null : function1;
            Function1 function4 = (i & 2) != 0 ? null : function2;
            if (function3 == null && function4 == null) {
                return function0.invoke();
            }
            Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (snapshot instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot2 = (TransparentObserverMutableSnapshot) snapshot;
                if (transparentObserverMutableSnapshot2.getThreadId() == Thread_jvmKt.currentThreadId()) {
                    Function1<Object, Unit> readObserver$runtime = transparentObserverMutableSnapshot2.getReadObserver();
                    Function1<Object, Unit> writeObserver$runtime = transparentObserverMutableSnapshot2.getWriteObserver$runtime();
                    try {
                        ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(function3, readObserver$runtime, false, 4, null));
                        ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(function4, writeObserver$runtime));
                        return function0.invoke();
                    } finally {
                        InlineMarker.finallyStart(1);
                        transparentObserverMutableSnapshot2.setReadObserver$runtime(readObserver$runtime);
                        transparentObserverMutableSnapshot2.setWriteObserver$runtime(writeObserver$runtime);
                        InlineMarker.finallyEnd(1);
                    }
                }
            }
            if (snapshot == 0 || (snapshot instanceof MutableSnapshot)) {
                transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function3, function4, true, false);
            } else {
                if (function3 == null) {
                    return function0.invoke();
                }
                transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(function3);
            }
            try {
                Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                try {
                    Object objInvoke = function0.invoke();
                    InlineMarker.finallyStart(1);
                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    transparentObserverMutableSnapshot.dispose();
                    return objInvoke;
                } finally {
                    InlineMarker.finallyStart(1);
                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                transparentObserverMutableSnapshot.dispose();
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MutableSnapshot takeMutableSnapshot$default(Companion companion, Function1 function1, Function1 function2, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            if ((i & 2) != 0) {
                function2 = null;
            }
            return companion.takeMutableSnapshot(function1, function2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Snapshot takeSnapshot$default(Companion companion, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            return companion.takeSnapshot(function1);
        }

        public final Snapshot createNonObservableSnapshot() {
            return SnapshotKt.createTransparentSnapshotWithNoParentReadObserver$default((Snapshot) SnapshotKt.threadSnapshot.get(), null, false, 6, null);
        }

        public final Snapshot getCurrent() {
            return SnapshotKt.currentSnapshot();
        }

        public final Snapshot getCurrentThreadSnapshot() {
            return (Snapshot) SnapshotKt.threadSnapshot.get();
        }

        public final <T> T global(Function0<? extends T> block) {
            Snapshot snapshotRemoveCurrent = removeCurrent();
            try {
                return (T) block.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                restoreCurrent(snapshotRemoveCurrent);
                InlineMarker.finallyEnd(1);
            }
        }

        public final boolean isApplyObserverNotificationPending() {
            return SnapshotKt.pendingApplyObserverCount.get() > 0;
        }

        public final boolean isInSnapshot() {
            return SnapshotKt.threadSnapshot.get() != null;
        }

        public final Snapshot makeCurrentNonObservable(Snapshot previous) {
            if (previous instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot = (TransparentObserverMutableSnapshot) previous;
                if (transparentObserverMutableSnapshot.getThreadId() == Thread_jvmKt.currentThreadId()) {
                    transparentObserverMutableSnapshot.setReadObserver$runtime(null);
                    return previous;
                }
            }
            if (previous instanceof TransparentObserverSnapshot) {
                TransparentObserverSnapshot transparentObserverSnapshot = (TransparentObserverSnapshot) previous;
                if (transparentObserverSnapshot.getThreadId$runtime() == Thread_jvmKt.currentThreadId()) {
                    transparentObserverSnapshot.setReadObserver$runtime(null);
                    return previous;
                }
            }
            Snapshot snapshotCreateTransparentSnapshotWithNoParentReadObserver$default = SnapshotKt.createTransparentSnapshotWithNoParentReadObserver$default(previous, null, false, 6, null);
            snapshotCreateTransparentSnapshotWithNoParentReadObserver$default.makeCurrent();
            return snapshotCreateTransparentSnapshotWithNoParentReadObserver$default;
        }

        public final void notifyObjectsInitialized() {
            SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime();
        }

        public final <T> T observe(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver, Function0<? extends T> block) {
            Snapshot transparentObserverMutableSnapshot;
            if (readObserver == null && writeObserver == null) {
                return (T) block.invoke();
            }
            Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (snapshot instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot2 = (TransparentObserverMutableSnapshot) snapshot;
                if (transparentObserverMutableSnapshot2.getThreadId() == Thread_jvmKt.currentThreadId()) {
                    Function1<Object, Unit> readObserver$runtime = transparentObserverMutableSnapshot2.getReadObserver();
                    Function1<Object, Unit> writeObserver$runtime = transparentObserverMutableSnapshot2.getWriteObserver$runtime();
                    try {
                        ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, readObserver$runtime, false, 4, null));
                        ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(writeObserver, writeObserver$runtime));
                        return (T) block.invoke();
                    } finally {
                        transparentObserverMutableSnapshot2.setReadObserver$runtime(readObserver$runtime);
                        transparentObserverMutableSnapshot2.setWriteObserver$runtime(writeObserver$runtime);
                    }
                }
            }
            if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
                transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, readObserver, writeObserver, true, false);
            } else {
                if (readObserver == null) {
                    return (T) block.invoke();
                }
                transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(readObserver);
            }
            try {
                Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                try {
                    T t = (T) block.invoke();
                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    transparentObserverMutableSnapshot.dispose();
                    return t;
                } catch (Throwable th) {
                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            } catch (Throwable th2) {
                transparentObserverMutableSnapshot.dispose();
                throw th2;
            }
        }

        public final <T> T observeInternal$runtime(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver, Function0<? extends T> block) {
            Snapshot transparentObserverMutableSnapshot;
            if (readObserver == null && writeObserver == null) {
                return (T) block.invoke();
            }
            Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (snapshot instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot2 = (TransparentObserverMutableSnapshot) snapshot;
                if (transparentObserverMutableSnapshot2.getThreadId() == Thread_jvmKt.currentThreadId()) {
                    Function1<Object, Unit> readObserver$runtime = transparentObserverMutableSnapshot2.getReadObserver();
                    Function1<Object, Unit> writeObserver$runtime = transparentObserverMutableSnapshot2.getWriteObserver$runtime();
                    try {
                        ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, readObserver$runtime, false, 4, null));
                        ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(writeObserver, writeObserver$runtime));
                        return (T) block.invoke();
                    } finally {
                        InlineMarker.finallyStart(1);
                        transparentObserverMutableSnapshot2.setReadObserver$runtime(readObserver$runtime);
                        transparentObserverMutableSnapshot2.setWriteObserver$runtime(writeObserver$runtime);
                        InlineMarker.finallyEnd(1);
                    }
                }
            }
            if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
                transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, readObserver, writeObserver, true, false);
            } else {
                if (readObserver == null) {
                    return (T) block.invoke();
                }
                transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(readObserver);
            }
            try {
                Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                try {
                    T t = (T) block.invoke();
                    InlineMarker.finallyStart(1);
                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    transparentObserverMutableSnapshot.dispose();
                    return t;
                } finally {
                    InlineMarker.finallyStart(1);
                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                transparentObserverMutableSnapshot.dispose();
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }

        public final int openSnapshotCount() {
            return CollectionsKt.toList(SnapshotKt.openSnapshots).size();
        }

        public final ObserverHandle registerApplyObserver(final Function2<? super Set<? extends Object>, ? super Snapshot, Unit> observer) {
            SnapshotKt.advanceGlobalSnapshot(SnapshotKt.emptyLambda);
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.applyObservers = CollectionsKt.plus(SnapshotKt.applyObservers, observer);
                Unit unit = Unit.INSTANCE;
            }
            return new ObserverHandle() { // from class: xhd
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Snapshot.Companion.a(observer);
                }
            };
        }

        public final ObserverHandle registerGlobalWriteObserver(final Function1<Object, Unit> observer) {
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.globalWriteObservers = CollectionsKt.plus(SnapshotKt.globalWriteObservers, observer);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.advanceGlobalSnapshot();
            return new ObserverHandle() { // from class: yhd
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Snapshot.Companion.b(observer);
                }
            };
        }

        public final Snapshot removeCurrent() {
            Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (snapshot != null) {
                SnapshotKt.threadSnapshot.set(null);
            }
            return snapshot;
        }

        public final void restoreCurrent(Snapshot previous) {
            if (previous != null) {
                SnapshotKt.threadSnapshot.set(previous);
            }
        }

        public final void restoreNonObservable(Snapshot previous, Snapshot nonObservable, Function1<Object, Unit> observer) {
            if (previous != nonObservable) {
                nonObservable.restoreCurrent(previous);
                nonObservable.dispose();
            } else if (previous instanceof TransparentObserverMutableSnapshot) {
                ((TransparentObserverMutableSnapshot) previous).setReadObserver$runtime(observer);
            } else if (previous instanceof TransparentObserverSnapshot) {
                ((TransparentObserverSnapshot) previous).setReadObserver$runtime(observer);
            } else {
                w04.a("Non-transparent snapshot was reused: ", previous);
            }
        }

        public final void sendApplyNotifications() {
            boolean zHasPendingChanges;
            synchronized (SnapshotKt.getLock()) {
                zHasPendingChanges = SnapshotKt.globalSnapshot.hasPendingChanges();
            }
            if (zHasPendingChanges) {
                SnapshotKt.advanceGlobalSnapshot();
            }
        }

        public final MutableSnapshot takeMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
            MutableSnapshot mutableSnapshotTakeNestedMutableSnapshot;
            Snapshot snapshotCurrentSnapshot = SnapshotKt.currentSnapshot();
            MutableSnapshot mutableSnapshot = snapshotCurrentSnapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshotCurrentSnapshot : null;
            if (mutableSnapshot != null && (mutableSnapshotTakeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(readObserver, writeObserver)) != null) {
                return mutableSnapshotTakeNestedMutableSnapshot;
            }
            k2d.a("Cannot create a mutable snapshot of an read-only snapshot");
            return null;
        }

        public final Snapshot takeSnapshot(Function1<Object, Unit> readObserver) {
            return SnapshotKt.currentSnapshot().takeNestedSnapshot(readObserver);
        }

        public final <R> R withMutableSnapshot(Function0<? extends R> block) {
            MutableSnapshot mutableSnapshotTakeMutableSnapshot$default = takeMutableSnapshot$default(this, null, null, 3, null);
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot$default.makeCurrent();
                try {
                    R r = (R) block.invoke();
                    InlineMarker.finallyStart(1);
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    mutableSnapshotTakeMutableSnapshot$default.apply().check();
                    mutableSnapshotTakeMutableSnapshot$default.dispose();
                    return r;
                } finally {
                    InlineMarker.finallyStart(1);
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    InlineMarker.finallyStart(1);
                    mutableSnapshotTakeMutableSnapshot$default.dispose();
                    InlineMarker.finallyEnd(1);
                    throw th2;
                }
            }
        }

        public final <T> T withoutReadObservation(Function0<? extends T> block) {
            Snapshot currentThreadSnapshot = getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = makeCurrentNonObservable(currentThreadSnapshot);
            try {
                return (T) block.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                InlineMarker.finallyEnd(1);
            }
        }

        private Companion() {
        }

        private final boolean getCanBeReused(TransparentObserverSnapshot transparentObserverSnapshot) {
            return transparentObserverSnapshot.getThreadId$runtime() == Thread_jvmKt.currentThreadId();
        }
    }

    public /* synthetic */ Snapshot(long j, SnapshotIdSet snapshotIdSet, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, snapshotIdSet);
    }

    public /* synthetic */ Snapshot(int i, SnapshotIdSet snapshotIdSet, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, snapshotIdSet);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use id: Long constructor instead")
    private /* synthetic */ Snapshot(int i, SnapshotIdSet snapshotIdSet) {
        this(SnapshotId_jvmKt.toSnapshotId(i), snapshotIdSet, (DefaultConstructorMarker) null);
    }
}
