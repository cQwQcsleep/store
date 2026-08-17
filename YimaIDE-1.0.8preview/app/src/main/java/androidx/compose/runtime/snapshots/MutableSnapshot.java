package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.tooling.SnapshotInstanceObservers;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserver;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0014\b\u0017\u0018\u0000 l2\u00020\u0001:\u0001lBI\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J8\u0010\u0013\u001a\u00020\u00002\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\u001e\u0010\u001d\u001a\u00020\u00012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\u0015\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b J\u0015\u0010!\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\nH\u0010¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\nH\u0010¢\u0006\u0002\b&J\r\u0010'\u001a\u00020\nH\u0010¢\u0006\u0002\b(J\b\u0010)\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\nH\u0002JG\u0010,\u001a\u00020\u00152\n\u0010-\u001a\u00060\u0003j\u0002`\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0018\u0001022\u0006\u00104\u001a\u00020\u0006H\u0000¢\u0006\u0004\b5\u00106J$\u00107\u001a\u0002H8\"\u0004\b\u0000\u001082\f\u00109\u001a\b\u0012\u0004\u0012\u0002H80:H\u0080\b¢\u0006\u0004\b;\u0010<J\r\u00107\u001a\u00020\nH\u0000¢\u0006\u0002\b;J\u001b\u0010=\u001a\u00020\n2\n\u0010>\u001a\u00060\u0003j\u0002`\u0004H\u0000¢\u0006\u0004\b?\u0010@J\u0015\u0010A\u001a\u00020\n2\u0006\u0010>\u001a\u00020BH\u0000¢\u0006\u0002\bCJ\u0015\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020FH\u0000¢\u0006\u0002\bGJ\b\u0010H\u001a\u00020\nH\u0002J\u0015\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\u0006H\u0000¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u000200H\u0010¢\u0006\u0002\bNR\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010O\u001a\u00020BX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\"\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\"\u0010X\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010YX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010^\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020FX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u000e\u0010J\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010h\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u0018\"\u0004\bj\u0010k¨\u0006m"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getReadObserver$runtime", "()Lkotlin/jvm/functions/Function1;", "getWriteObserver$runtime", "hasPendingChanges", "", "takeNestedMutableSnapshot", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "readOnly", "getReadOnly", "()Z", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "dispose", "takeNestedSnapshot", "nestedActivated", "snapshot", "nestedActivated$runtime", "nestedDeactivated", "nestedDeactivated$runtime", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "closeLocked", "closeLocked$runtime", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime", "validateNotApplied", "validateNotAppliedOrPinned", "abandon", "innerApplyLocked", "nextId", "modified", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "invalidSnapshots", "innerApplyLocked$runtime", "(JLandroidx/collection/MutableScatterSet;Ljava/util/Map;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "advance", "T", "block", "Lkotlin/Function0;", "advance$runtime", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recordPrevious", "id", "recordPrevious$runtime", "(J)V", "recordPreviousPinnedSnapshot", "", "recordPreviousPinnedSnapshot$runtime", "recordPreviousPinnedSnapshots", "handles", "", "recordPreviousPinnedSnapshots$runtime", "releasePreviouslyPinnedSnapshotsLocked", "recordPreviousList", "snapshots", "recordPreviousList$runtime", "recordModified", "state", "recordModified$runtime", "writeCount", "getWriteCount$runtime", "()I", "setWriteCount$runtime", "(I)V", "getModified$runtime", "()Landroidx/collection/MutableScatterSet;", "setModified$runtime", "(Landroidx/collection/MutableScatterSet;)V", "merged", "", "getMerged$runtime", "()Ljava/util/List;", "setMerged$runtime", "(Ljava/util/List;)V", "previousIds", "getPreviousIds$runtime", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "previousPinnedSnapshots", "getPreviousPinnedSnapshots$runtime", "()[I", "setPreviousPinnedSnapshots$runtime", "([I)V", "applied", "getApplied$runtime", "setApplied$runtime", "(Z)V", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private List<? extends StateObject> merged;
    private MutableScatterSet<StateObject> modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1<Object, Unit> readObserver;
    private int snapshots;
    private int writeCount;
    private final Function1<Object, Unit> writeObserver;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] EmptyIntArray = new int[0];

    public MutableSnapshot(long j, SnapshotIdSet snapshotIdSet, Function1<Object, Unit> function1, Function1<Object, Unit> function2) {
        super(j, snapshotIdSet, (DefaultConstructorMarker) null);
        this.readObserver = function1;
        this.writeObserver = function2;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x007a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x007c A[LOOP:0: B:7:0x001e->B:24:0x007c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:28:0x007f A[EDGE_INSN: B:28:0x007f->B:25:0x007f BREAK  A[LOOP:0: B:7:0x001e->B:24:0x007c], SYNTHETIC] */
    private final void abandon() {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        if (modified$runtime != null) {
            validateNotApplied();
            setModified$runtime(null);
            long snapshotId = getSnapshotId();
            Object[] objArr = ((ScatterSet) modified$runtime).elements;
            long[] jArr = ((ScatterSet) modified$runtime).metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                for (StateRecord firstStateRecord = ((StateObject) objArr[(i << 3) + i3]).getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
                                    if (firstStateRecord.getSnapshotId() == snapshotId || CollectionsKt.contains(this.previousIds, Long.valueOf(firstStateRecord.getSnapshotId()))) {
                                        firstStateRecord.setSnapshotId$runtime(SnapshotKt.INVALID_SNAPSHOT);
                                    }
                                }
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
        closeAndReleasePinning$runtime();
    }

    private final void releasePreviouslyPinnedSnapshotsLocked() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, Function1 function1, Function1 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
            return null;
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(function1, function2);
    }

    private final void validateNotApplied() {
        if (this.applied) {
            PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
        }
    }

    private final void validateNotAppliedOrPinned() {
        if (!this.applied || ((Snapshot) this).pinningTrackingHandle >= 0) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
    }

    public final <T> T advance$runtime(Function0<? extends T> block) {
        recordPrevious$runtime(getSnapshotId());
        T t = (T) block.invoke();
        if (getApplied() || getDisposed()) {
            return t;
        }
        long snapshotId = getSnapshotId();
        synchronized (SnapshotKt.getLock()) {
            try {
                long j = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId++;
                setSnapshotId$runtime(j);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId + 1, getSnapshotId()));
        return t;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0180 A[EDGE_INSN: B:102:0x0180->B:77:0x0180 BREAK  A[LOOP:4: B:66:0x0151->B:76:0x017d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x013c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x013e A[Catch: all -> 0x0133, LOOP:2: B:48:0x010b->B:60:0x013e, LOOP_END, TryCatch #0 {all -> 0x0133, blocks: (B:43:0x00ef, B:45:0x00ff, B:48:0x010b, B:50:0x0117, B:52:0x0121, B:54:0x0127, B:57:0x0136, B:63:0x0147, B:66:0x0151, B:68:0x015b, B:70:0x0165, B:72:0x016b, B:73:0x0175, B:76:0x017d, B:77:0x0180, B:79:0x0184, B:81:0x018e, B:82:0x019a, B:60:0x013e), top: B:90:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0141  */
    /* JADX WARN: Code duplicated, block: B:75:0x017b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x017d A[Catch: all -> 0x0133, LOOP:4: B:66:0x0151->B:76:0x017d, LOOP_END, TryCatch #0 {all -> 0x0133, blocks: (B:43:0x00ef, B:45:0x00ff, B:48:0x010b, B:50:0x0117, B:52:0x0121, B:54:0x0127, B:57:0x0136, B:63:0x0147, B:66:0x0151, B:68:0x015b, B:70:0x0165, B:72:0x016b, B:73:0x0175, B:76:0x017d, B:77:0x0180, B:79:0x0184, B:81:0x018e, B:82:0x019a, B:60:0x013e), top: B:90:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:97:0x0145 A[EDGE_INSN: B:97:0x0145->B:62:0x0145 BREAK  A[LOOP:2: B:48:0x010b->B:60:0x013e], SYNTHETIC] */
    public SnapshotApplyResult apply() {
        Map<StateRecord, ? extends StateRecord> mapOptimisticMerges;
        List list;
        MutableScatterSet<StateObject> modified$runtime;
        long j;
        long j2;
        MutableScatterSet<StateObject> modified$runtime2 = getModified$runtime();
        if (modified$runtime2 != null) {
            GlobalSnapshot globalSnapshot = SnapshotKt.globalSnapshot;
            mapOptimisticMerges = SnapshotKt.optimisticMerges(globalSnapshot.getSnapshotId(), this, SnapshotKt.openSnapshots.clear(globalSnapshot.getSnapshotId()));
        } else {
            mapOptimisticMerges = null;
        }
        List listEmptyList = CollectionsKt.emptyList();
        synchronized (SnapshotKt.getLock()) {
            try {
                SnapshotKt.validateOpen(this);
                if (modified$runtime2 == null || modified$runtime2.getSize() == 0) {
                    closeLocked$runtime();
                    GlobalSnapshot globalSnapshot2 = SnapshotKt.globalSnapshot;
                    MutableScatterSet<StateObject> modified$runtime3 = globalSnapshot2.getModified$runtime();
                    SnapshotKt.resetGlobalSnapshotLocked(globalSnapshot2, SnapshotKt.emptyLambda);
                    if (modified$runtime3 == null || !modified$runtime3.isNotEmpty()) {
                        list = listEmptyList;
                        modified$runtime = null;
                    } else {
                        list = SnapshotKt.applyObservers;
                        modified$runtime = modified$runtime3;
                    }
                } else {
                    GlobalSnapshot globalSnapshot3 = SnapshotKt.globalSnapshot;
                    SnapshotApplyResult snapshotApplyResultInnerApplyLocked$runtime = innerApplyLocked$runtime(SnapshotKt.nextSnapshotId, modified$runtime2, mapOptimisticMerges, SnapshotKt.openSnapshots.clear(globalSnapshot3.getSnapshotId()));
                    if (!Intrinsics.areEqual(snapshotApplyResultInnerApplyLocked$runtime, SnapshotApplyResult.Success.INSTANCE)) {
                        return snapshotApplyResultInnerApplyLocked$runtime;
                    }
                    closeLocked$runtime();
                    modified$runtime = globalSnapshot3.getModified$runtime();
                    SnapshotKt.resetGlobalSnapshotLocked(globalSnapshot3, SnapshotKt.emptyLambda);
                    setModified$runtime(null);
                    globalSnapshot3.setModified$runtime(null);
                    list = SnapshotKt.applyObservers;
                }
                Unit unit = Unit.INSTANCE;
                this.applied = true;
                if (modified$runtime != null) {
                    Set setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(modified$runtime);
                    if (!setWrapIntoSet.isEmpty()) {
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            ((Function2) list.get(i)).invoke(setWrapIntoSet, this);
                        }
                    }
                }
                if (modified$runtime2 != null && modified$runtime2.isNotEmpty()) {
                    Set setWrapIntoSet2 = ScatterSetWrapperKt.wrapIntoSet(modified$runtime2);
                    int size2 = list.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((Function2) list.get(i2)).invoke(setWrapIntoSet2, this);
                    }
                }
                SnapshotObserverKt.dispatchObserverOnApplied(this, modified$runtime2);
                synchronized (SnapshotKt.getLock()) {
                    try {
                        releasePinnedSnapshotsForCloseLocked$runtime();
                        SnapshotKt.checkAndOverwriteUnusedRecordsLocked();
                        if (modified$runtime != null) {
                            Object[] objArr = ((ScatterSet) modified$runtime).elements;
                            long[] jArr = ((ScatterSet) modified$runtime).metadata;
                            int length = jArr.length - 2;
                            if (length >= 0) {
                                int i3 = 0;
                                j = 128;
                                while (true) {
                                    long j3 = jArr[i3];
                                    j2 = 255;
                                    if ((((~j3) << 7) & j3 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i3 != length) {
                                            break;
                                            break;
                                        }
                                        i3++;
                                    } else {
                                        int i4 = 8 - ((~(i3 - length)) >>> 31);
                                        for (int i5 = 0; i5 < i4; i5++) {
                                            if ((j3 & 255) < 128) {
                                                SnapshotKt.processForUnusedRecordsLocked((StateObject) objArr[(i3 << 3) + i5]);
                                            }
                                            j3 >>= 8;
                                        }
                                        if (i4 != 8) {
                                            break;
                                        }
                                        if (i3 != length) {
                                            break;
                                        }
                                        i3++;
                                    }
                                }
                            } else {
                                j = 128;
                                j2 = 255;
                            }
                        } else {
                            j = 128;
                            j2 = 255;
                        }
                        if (modified$runtime2 != null) {
                            Object[] objArr2 = ((ScatterSet) modified$runtime2).elements;
                            long[] jArr2 = ((ScatterSet) modified$runtime2).metadata;
                            int length2 = jArr2.length - 2;
                            if (length2 >= 0) {
                                int i6 = 0;
                                while (true) {
                                    long j4 = jArr2[i6];
                                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i6 != length2) {
                                            break;
                                            break;
                                        }
                                        i6++;
                                    } else {
                                        int i7 = 8 - ((~(i6 - length2)) >>> 31);
                                        for (int i8 = 0; i8 < i7; i8++) {
                                            if ((j4 & j2) < j) {
                                                SnapshotKt.processForUnusedRecordsLocked((StateObject) objArr2[(i6 << 3) + i8]);
                                            }
                                            j4 >>= 8;
                                        }
                                        if (i7 != 8) {
                                            break;
                                        }
                                        if (i6 != length2) {
                                            break;
                                        }
                                        i6++;
                                    }
                                }
                            }
                        }
                        List<? extends StateObject> list2 = this.merged;
                        if (list2 != null) {
                            int size3 = list2.size();
                            for (int i9 = 0; i9 < size3; i9++) {
                                SnapshotKt.processForUnusedRecordsLocked(list2.get(i9));
                            }
                        }
                        this.merged = null;
                        Unit unit2 = Unit.INSTANCE;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return SnapshotApplyResult.Success.INSTANCE;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getSnapshotId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        mo2568nestedDeactivated$runtime(this);
        SnapshotObserverKt.dispatchObserverOnPreDispose(this);
    }

    /* JADX INFO: renamed from: getApplied$runtime, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    public final List<StateObject> getMerged$runtime() {
        return this.merged;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public MutableScatterSet<StateObject> getModified$runtime() {
        return this.modified;
    }

    /* JADX INFO: renamed from: getPreviousIds$runtime, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    /* JADX INFO: renamed from: getPreviousPinnedSnapshots$runtime, reason: from getter */
    public final int[] getPreviousPinnedSnapshots() {
        return this.previousPinnedSnapshots;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime */
    public Function1<Object, Unit> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getWriteCount$runtime, reason: from getter */
    public int getWriteCount() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getWriteObserver$runtime() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        return modified$runtime != null && modified$runtime.isNotEmpty();
    }

    /* JADX WARN: Code duplicated, block: B:67:0x0176  */
    /* JADX WARN: Code duplicated, block: B:69:0x0180  */
    /* JADX WARN: Code duplicated, block: B:78:0x01af  */
    /* JADX WARN: Code duplicated, block: B:80:0x01b6 A[LOOP:3: B:79:0x01b4->B:80:0x01b6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:84:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:88:0x019a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final SnapshotApplyResult innerApplyLocked$runtime(long nextId, MutableScatterSet<StateObject> modified, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) {
        ArrayList arrayList;
        List<? extends StateObject> listPlus;
        ArrayList arrayList2;
        int size;
        int i;
        List<? extends StateObject> list;
        int size2;
        int i2;
        StateObject stateObject;
        StateRecord stateRecord;
        SnapshotIdSet snapshotIdSet;
        Object[] objArr;
        long[] jArr;
        SnapshotIdSet snapshotIdSet2;
        Object[] objArr2;
        long[] jArr2;
        int i3;
        long j;
        List<? extends StateObject> list2;
        StateRecord stateRecordMergeRecords;
        SnapshotIdSet snapshotIdSetOr = getInvalid().set(getSnapshotId()).or(this.previousIds);
        Object[] objArr3 = ((ScatterSet) modified).elements;
        long[] jArr3 = ((ScatterSet) modified).metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i4 = 0;
            arrayList2 = null;
            listPlus = null;
            while (true) {
                long j2 = jArr3[i4];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i4 - length)) >>> 31);
                    int i6 = 0;
                    while (i6 < i5) {
                        if ((j2 & 255) < 128) {
                            objArr2 = objArr3;
                            StateObject stateObject2 = (StateObject) objArr3[(i4 << 3) + i6];
                            jArr2 = jArr3;
                            StateRecord firstStateRecord = stateObject2.getFirstStateRecord();
                            i3 = i6;
                            ArrayList arrayList3 = arrayList2;
                            StateRecord stateRecord2 = SnapshotKt.readable(firstStateRecord, nextId, invalidSnapshots);
                            if (stateRecord2 == null) {
                                list2 = listPlus;
                                j = j2;
                            } else {
                                list2 = listPlus;
                                j = j2;
                                StateRecord stateRecord3 = SnapshotKt.readable(firstStateRecord, getSnapshotId(), snapshotIdSetOr);
                                if (stateRecord3 != null && stateRecord3.getSnapshotId() != SnapshotId_jvmKt.toSnapshotId(1) && !Intrinsics.areEqual(stateRecord2, stateRecord3)) {
                                    snapshotIdSet2 = snapshotIdSetOr;
                                    StateRecord stateRecord4 = SnapshotKt.readable(firstStateRecord, getSnapshotId(), getInvalid());
                                    if (stateRecord4 == null) {
                                        SnapshotKt.readError();
                                        wq6.a();
                                        return null;
                                    }
                                    if (optimisticMerges == null || (stateRecordMergeRecords = optimisticMerges.get(stateRecord2)) == null) {
                                        stateRecordMergeRecords = stateObject2.mergeRecords(stateRecord3, stateRecord2, stateRecord4);
                                    }
                                    if (stateRecordMergeRecords == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord4)) {
                                        if (Intrinsics.areEqual(stateRecordMergeRecords, stateRecord2)) {
                                            ArrayList arrayList4 = arrayList3 == null ? new ArrayList() : arrayList3;
                                            arrayList4.add(TuplesKt.to(stateObject2, stateRecord2.create(getSnapshotId())));
                                            listPlus = list2 == null ? new ArrayList() : list2;
                                            listPlus.add(stateObject2);
                                            arrayList2 = arrayList4;
                                        } else {
                                            arrayList2 = arrayList3 == null ? new ArrayList() : arrayList3;
                                            arrayList2.add(!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord3) ? TuplesKt.to(stateObject2, stateRecordMergeRecords) : TuplesKt.to(stateObject2, stateRecord3.create(getSnapshotId())));
                                        }
                                    }
                                    listPlus = list2;
                                }
                                arrayList2 = arrayList3;
                                listPlus = list2;
                            }
                            snapshotIdSet2 = snapshotIdSetOr;
                            arrayList2 = arrayList3;
                            listPlus = list2;
                        } else {
                            snapshotIdSet2 = snapshotIdSetOr;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            i3 = i6;
                            j = j2;
                        }
                        j2 = j >> 8;
                        i6 = i3 + 1;
                        jArr3 = jArr2;
                        objArr3 = objArr2;
                        snapshotIdSetOr = snapshotIdSet2;
                    }
                    snapshotIdSet = snapshotIdSetOr;
                    objArr = objArr3;
                    jArr = jArr3;
                    if (i5 != 8) {
                        break;
                    }
                } else {
                    snapshotIdSet = snapshotIdSetOr;
                    objArr = objArr3;
                    jArr = jArr3;
                }
                if (i4 != length) {
                    i4++;
                    jArr3 = jArr;
                    objArr3 = objArr;
                    snapshotIdSetOr = snapshotIdSet;
                } else {
                    arrayList = arrayList2;
                }
            }
            if (arrayList2 != null) {
                advance$runtime();
                size2 = arrayList2.size();
                for (i2 = 0; i2 < size2; i2++) {
                    Pair pair = (Pair) arrayList2.get(i2);
                    stateObject = (StateObject) pair.component1();
                    stateRecord = (StateRecord) pair.component2();
                    stateRecord.setSnapshotId$runtime(nextId);
                    synchronized (SnapshotKt.getLock()) {
                        stateRecord.setNext$runtime(stateObject.getFirstStateRecord());
                        stateObject.prependStateRecord(stateRecord);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            if (listPlus != null) {
                size = listPlus.size();
                for (i = 0; i < size; i++) {
                    modified.remove(listPlus.get(i));
                }
                list = this.merged;
                if (list != null) {
                    listPlus = CollectionsKt.plus(list, listPlus);
                }
                this.merged = listPlus;
            }
            return SnapshotApplyResult.Success.INSTANCE;
        }
        arrayList = null;
        listPlus = null;
        arrayList2 = arrayList;
        if (arrayList2 != null) {
            advance$runtime();
            size2 = arrayList2.size();
            while (i2 < size2) {
                Pair pair2 = (Pair) arrayList2.get(i2);
                stateObject = (StateObject) pair2.component1();
                stateRecord = (StateRecord) pair2.component2();
                stateRecord.setSnapshotId$runtime(nextId);
                synchronized (SnapshotKt.getLock()) {
                    stateRecord.setNext$runtime(stateObject.getFirstStateRecord());
                    stateObject.prependStateRecord(stateRecord);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        if (listPlus != null) {
            size = listPlus.size();
            while (i < size) {
                modified.remove(listPlus.get(i));
            }
            list = this.merged;
            if (list != null) {
                listPlus = CollectionsKt.plus(list, listPlus);
            }
            this.merged = listPlus;
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime */
    public void mo2567nestedActivated$runtime(Snapshot snapshot) {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime */
    public void mo2568nestedDeactivated$runtime(Snapshot snapshot) {
        if (!(this.snapshots > 0)) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        int i = this.snapshots - 1;
        this.snapshots = i;
        if (i != 0 || this.applied) {
            return;
        }
        abandon();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime */
    public void mo2569recordModified$runtime(StateObject state) {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        if (modified$runtime == null) {
            modified$runtime = ScatterSetKt.mutableScatterSetOf();
            setModified$runtime(modified$runtime);
        }
        modified$runtime.add(state);
    }

    public final void recordPrevious$runtime(long id) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.set(id);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousList$runtime(SnapshotIdSet snapshots) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.or(snapshots);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousPinnedSnapshot$runtime(int id) {
        if (id >= 0) {
            this.previousPinnedSnapshots = ArraysKt.plus(this.previousPinnedSnapshots, id);
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime(int[] handles) {
        if (handles.length == 0) {
            return;
        }
        int[] iArr = this.previousPinnedSnapshots;
        if (iArr.length != 0) {
            handles = ArraysKt.plus(iArr, handles);
        }
        this.previousPinnedSnapshots = handles;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime() {
        releasePreviouslyPinnedSnapshotsLocked();
        super.releasePinnedSnapshotsForCloseLocked$runtime();
    }

    public final void setApplied$runtime(boolean z) {
        this.applied = z;
    }

    public final void setMerged$runtime(List<? extends StateObject> list) {
        this.merged = list;
    }

    public void setModified$runtime(MutableScatterSet<StateObject> mutableScatterSet) {
        this.modified = mutableScatterSet;
    }

    public final void setPreviousIds$runtime(SnapshotIdSet snapshotIdSet) {
        this.previousIds = snapshotIdSet;
    }

    public final void setPreviousPinnedSnapshots$runtime(int[] iArr) {
        this.previousPinnedSnapshots = iArr;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime(int i) {
        this.writeCount = i;
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        Map map;
        NestedMutableSnapshot nestedMutableSnapshot;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        PersistentList persistentList = SnapshotObserverKt.observers;
        Function1<Object, Unit> function1 = readObserver;
        Function1<Object, Unit> writeObserver2 = writeObserver;
        if (persistentList != null) {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(persistentList, this, false, function1, writeObserver2);
            SnapshotInstanceObservers snapshotInstanceObservers = (SnapshotInstanceObservers) pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = snapshotInstanceObservers.getReadObserver();
            writeObserver2 = snapshotInstanceObservers.getWriteObserver();
            map = (Map) pairMergeObservers.getSecond();
            function1 = readObserver2;
        } else {
            map = null;
        }
        recordPrevious$runtime(getSnapshotId());
        synchronized (SnapshotKt.getLock()) {
            long j = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId++;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(j);
            SnapshotIdSet invalid = getInvalid();
            setInvalid$runtime(invalid.set(j));
            nestedMutableSnapshot = new NestedMutableSnapshot(j, SnapshotKt.addRange(invalid, getSnapshotId() + 1, j), SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null), SnapshotKt.mergedWriteObserver(writeObserver2, getWriteObserver$runtime()), this);
        }
        if (!getApplied() && !getDisposed()) {
            long snapshotId = getSnapshotId();
            synchronized (SnapshotKt.getLock()) {
                long j2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId++;
                setSnapshotId$runtime(j2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId + 1, getSnapshotId()));
        }
        if (persistentList != null) {
            SnapshotObserverKt.dispatchCreatedObservers(persistentList, this, nestedMutableSnapshot, map);
        }
        return nestedMutableSnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver) {
        Function1<Object, Unit> function1;
        Map map;
        NestedReadonlySnapshot nestedReadonlySnapshot;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        long snapshotId = getSnapshotId();
        MutableSnapshot mutableSnapshot = this instanceof GlobalSnapshot ? null : this;
        PersistentList persistentList = SnapshotObserverKt.observers;
        if (persistentList != null) {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(persistentList, mutableSnapshot, true, readObserver, null);
            SnapshotInstanceObservers snapshotInstanceObservers = (SnapshotInstanceObservers) pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = snapshotInstanceObservers.getReadObserver();
            snapshotInstanceObservers.getWriteObserver();
            function1 = readObserver2;
            map = (Map) pairMergeObservers.getSecond();
        } else {
            function1 = readObserver;
            map = null;
        }
        recordPrevious$runtime(getSnapshotId());
        synchronized (SnapshotKt.getLock()) {
            long j = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId++;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(j);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(j, SnapshotKt.addRange(getInvalid(), snapshotId + 1, j), SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null), this);
        }
        if (!getApplied() && !getDisposed()) {
            long snapshotId2 = getSnapshotId();
            synchronized (SnapshotKt.getLock()) {
                long j2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId++;
                setSnapshotId$runtime(j2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId2 + 1, getSnapshotId()));
        }
        if (persistentList != null) {
            SnapshotObserverKt.dispatchCreatedObservers(persistentList, mutableSnapshot, nestedReadonlySnapshot, map);
        }
        return nestedReadonlySnapshot;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot$Companion;", "", "<init>", "()V", "EmptyIntArray", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void advance$runtime() {
        recordPrevious$runtime(getSnapshotId());
        Unit unit = Unit.INSTANCE;
        if (getApplied() || getDisposed()) {
            return;
        }
        long snapshotId = getSnapshotId();
        synchronized (SnapshotKt.getLock()) {
            long j = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId++;
            setSnapshotId$runtime(j);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
        }
        setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId + 1, getSnapshotId()));
    }
}
