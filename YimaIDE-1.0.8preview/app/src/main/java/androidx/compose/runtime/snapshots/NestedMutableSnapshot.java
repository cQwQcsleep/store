package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001BO\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\u0001¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0017\u001a\u00020\nH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\nH\u0002R\u0011\u0010\f\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/snapshots/NestedMutableSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "parent", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/snapshots/MutableSnapshot;)V", "getParent", "()Landroidx/compose/runtime/snapshots/MutableSnapshot;", "deactivated", "", "root", "Landroidx/compose/runtime/snapshots/Snapshot;", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "dispose", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "deactivate", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NestedMutableSnapshot extends MutableSnapshot {
    public static final int $stable = 8;
    private boolean deactivated;
    private final MutableSnapshot parent;

    public NestedMutableSnapshot(long j, SnapshotIdSet snapshotIdSet, Function1<Object, Unit> function1, Function1<Object, Unit> function2, MutableSnapshot mutableSnapshot) {
        super(j, snapshotIdSet, function1, function2);
        this.parent = mutableSnapshot;
        mutableSnapshot.mo2567nestedActivated$runtime(this);
    }

    private final void deactivate() {
        if (this.deactivated) {
            return;
        }
        this.deactivated = true;
        this.parent.mo2568nestedDeactivated$runtime(this);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        NestedMutableSnapshot nestedMutableSnapshot;
        if (this.parent.getApplied() || this.parent.getDisposed()) {
            return new SnapshotApplyResult.Failure(this);
        }
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        long snapshotId = getSnapshotId();
        Map<StateRecord, ? extends StateRecord> mapOptimisticMerges = modified$runtime != null ? SnapshotKt.optimisticMerges(this.parent.getSnapshotId(), this, this.parent.getInvalid()) : null;
        synchronized (SnapshotKt.getLock()) {
            try {
                SnapshotKt.validateOpen(this);
                if (modified$runtime == null || modified$runtime.getSize() == 0) {
                    nestedMutableSnapshot = this;
                    nestedMutableSnapshot.closeAndReleasePinning$runtime();
                    Unit unit = Unit.INSTANCE;
                } else {
                    nestedMutableSnapshot = this;
                    SnapshotApplyResult snapshotApplyResultInnerApplyLocked$runtime = nestedMutableSnapshot.innerApplyLocked$runtime(this.parent.getSnapshotId(), modified$runtime, mapOptimisticMerges, this.parent.getInvalid());
                    if (!Intrinsics.areEqual(snapshotApplyResultInnerApplyLocked$runtime, SnapshotApplyResult.Success.INSTANCE)) {
                        return snapshotApplyResultInnerApplyLocked$runtime;
                    }
                    MutableScatterSet<StateObject> modified$runtime2 = nestedMutableSnapshot.parent.getModified$runtime();
                    if (modified$runtime2 != null) {
                        modified$runtime2.addAll(modified$runtime);
                    } else {
                        nestedMutableSnapshot.parent.setModified$runtime(modified$runtime);
                        nestedMutableSnapshot.setModified$runtime(null);
                    }
                }
                if (Intrinsics.compare(nestedMutableSnapshot.parent.getSnapshotId(), snapshotId) < 0) {
                    nestedMutableSnapshot.parent.advance$runtime();
                }
                MutableSnapshot mutableSnapshot = nestedMutableSnapshot.parent;
                mutableSnapshot.setInvalid$runtime(mutableSnapshot.getInvalid().clear(snapshotId).andNot(nestedMutableSnapshot.getPreviousIds()));
                nestedMutableSnapshot.parent.recordPrevious$runtime(snapshotId);
                nestedMutableSnapshot.parent.recordPreviousPinnedSnapshot$runtime(nestedMutableSnapshot.takeoverPinnedSnapshot$runtime());
                nestedMutableSnapshot.parent.recordPreviousList$runtime(nestedMutableSnapshot.getPreviousIds());
                nestedMutableSnapshot.parent.recordPreviousPinnedSnapshots$runtime(nestedMutableSnapshot.getPreviousPinnedSnapshots());
                Unit unit2 = Unit.INSTANCE;
                nestedMutableSnapshot.setApplied$runtime(true);
                nestedMutableSnapshot.deactivate();
                SnapshotObserverKt.dispatchObserverOnApplied(nestedMutableSnapshot, modified$runtime);
                return SnapshotApplyResult.Success.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        deactivate();
    }

    public final MutableSnapshot getParent() {
        return this.parent;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this.parent.getRoot();
    }
}
