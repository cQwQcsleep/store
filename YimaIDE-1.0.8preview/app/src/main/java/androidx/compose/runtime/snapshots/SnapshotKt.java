package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.internal.AtomicInt;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.internal.WeakReference;
import androidx.compose.runtime.snapshots.SnapshotIdSet;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.apk.ApkUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0001\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u001a!\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\u0010\u0007\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0000\u001a\b\u0010\u000b\u001a\u00020\fH\u0000\u001a4\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0002\u001aL\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\b\b\u0002\u0010\u0019\u001a\u00020\u0016H\u0000\u001aB\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u000eH\u0000\u001a/\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0*H\u0081\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010+\u001a>\u0010>\u001a\u0002H(\"\u0004\b\u0000\u0010(2\u0006\u00108\u001a\u0002092!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u0002H(0\u000eH\u0002¢\u0006\u0002\u0010?\u001a6\u0010B\u001a\u0002H(\"\u0004\b\u0000\u0010(2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u0002H(0\u000eH\u0002¢\u0006\u0002\u0010C\u001a\b\u0010B\u001a\u00020\tH\u0002\u001a:\u0010D\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020\f2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u0002H(0\u000eH\u0002¢\u0006\u0002\u0010E\u001a\u0010\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\fH\u0002\u001a-\u0010H\u001a\u00020\u00162\n\u0010\u000b\u001a\u00060\u0003j\u0002`\u00042\n\u0010I\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010J\u001a)\u0010H\u001a\u00020\u00162\u0006\u0010K\u001a\u00020L2\n\u0010G\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010M\u001a5\u0010N\u001a\u0004\u0018\u0001H(\"\b\b\u0000\u0010(*\u00020L2\u0006\u0010O\u001a\u0002H(2\n\u0010P\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010Q\u001a!\u0010N\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u000202¢\u0006\u0002\u0010S\u001a)\u0010N\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010G\u001a\u00020\f¢\u0006\u0002\u0010T\u001a\b\u0010U\u001a\u00020VH\u0002\u001a\u0012\u0010W\u001a\u0004\u0018\u00010L2\u0006\u0010R\u001a\u000202H\u0002\u001a\u0010\u0010X\u001a\u00020\u00162\u0006\u0010R\u001a\u000202H\u0002\u001a!\u0010Y\u001a\u00020L*\u00020L2\u0012\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u00160\u000eH\u0082\b\u001a\b\u0010[\u001a\u00020\tH\u0002\u001a\u0010\u0010\\\u001a\u00020\t2\u0006\u0010R\u001a\u000202H\u0002\u001a+\u0010]\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010G\u001a\u00020\fH\u0001¢\u0006\u0002\u0010T\u001a3\u0010^\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010G\u001a\u00020\f2\u0006\u0010_\u001a\u0002H(H\u0000¢\u0006\u0002\u0010`\u001a+\u0010a\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010G\u001a\u00020\fH\u0000¢\u0006\u0002\u0010T\u001a+\u0010b\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010G\u001a\u00020\fH\u0002¢\u0006\u0002\u0010T\u001a#\u0010c\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L*\u0002H(2\u0006\u0010R\u001a\u000202H\u0000¢\u0006\u0002\u0010S\u001a\u0018\u0010d\u001a\u00020\t2\u0006\u0010G\u001a\u00020\f2\u0006\u0010R\u001a\u000202H\u0001\u001aK\u0010e\u001a\u0002Hf\"\b\b\u0000\u0010(*\u00020L\"\u0004\b\u0001\u0010f*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010G\u001a\u00020\f2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002Hf0\u000e¢\u0006\u0002\bgH\u0086\b¢\u0006\u0002\u0010h\u001aC\u0010e\u001a\u0002Hf\"\b\b\u0000\u0010(*\u00020L\"\u0004\b\u0001\u0010f*\u0002H(2\u0006\u0010R\u001a\u0002022\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002Hf0\u000e¢\u0006\u0002\bgH\u0086\b¢\u0006\u0002\u0010i\u001aK\u0010j\u001a\u0002Hf\"\b\b\u0000\u0010(*\u00020L\"\u0004\b\u0001\u0010f*\u0002H(2\u0006\u0010R\u001a\u0002022\u0006\u0010_\u001a\u0002H(2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002Hf0\u000e¢\u0006\u0002\bgH\u0080\b¢\u0006\u0002\u0010k\u001a7\u0010l\u001a\u0010\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020L\u0018\u00010m2\n\u0010n\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010r\u001a\b\u0010s\u001a\u00020VH\u0002\u001a'\u0010t\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L2\u0006\u0010O\u001a\u0002H(2\u0006\u0010G\u001a\u00020\fH\u0001¢\u0006\u0002\u0010u\u001a\u001f\u0010t\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020L2\u0006\u0010O\u001a\u0002H(H\u0001¢\u0006\u0002\u0010v\u001aE\u0010w\u001a\u0002Hf\"\b\b\u0000\u0010(*\u00020L\"\u0004\b\u0001\u0010f*\u0002H(2!\u0010)\u001a\u001d\u0012\u0013\u0012\u0011H(¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(O\u0012\u0004\u0012\u0002Hf0\u000eH\u0086\b¢\u0006\u0002\u0010x\u001a)\u0010y\u001a\u00020\u0006*\u00020\u00062\n\u0010z\u001a\u00060\u0003j\u0002`\u00042\n\u0010{\u001a\u00060\u0003j\u0002`\u0004H\u0000¢\u0006\u0002\u0010|\")\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\t0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u001c\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d\"\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000\"\"\u0010 \u001a\u00060\u0014j\u0002`!8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010&\u0012\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u000e\u0010,\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0014\u0010-\u001a\u00060\u0003j\u0002`\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001d\"\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u0004¢\u0006\u0002\n\u0000\",\u00103\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001406\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0504X\u0082\u000e¢\u0006\u0002\n\u0000\" \u00107\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u000e04X\u0082\u000e¢\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010:\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u0010#\u001a\u0004\b<\u0010=\"\u000e\u0010@\u001a\u00020AX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"trackPinning", "", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;)I", "releasePinningLocked", "", "handle", "currentSnapshot", "Landroidx/compose/runtime/snapshots/Snapshot;", "emptyLambda", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "createTransparentSnapshotWithNoParentReadObserver", "previousSnapshot", "readObserver", "", "ownsPreviousSnapshot", "", "mergedReadObserver", "parentObserver", "mergeReadObserver", "mergedWriteObserver", "writeObserver", "INVALID_SNAPSHOT", "J", "threadSnapshot", "Landroidx/compose/runtime/internal/SnapshotThreadLocal;", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "getLock$annotations", "()V", "getLock", "()Ljava/lang/Object;", "Ljava/lang/Object;", "sync", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "openSnapshots", "nextSnapshotId", "pinningTable", "Landroidx/compose/runtime/snapshots/SnapshotDoubleIndexHeap;", "extraStateObjects", "Landroidx/compose/runtime/snapshots/SnapshotWeakSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "applyObservers", "", "Lkotlin/Function2;", "", "globalWriteObservers", "globalSnapshot", "Landroidx/compose/runtime/snapshots/GlobalSnapshot;", "snapshotInitializer", "getSnapshotInitializer$annotations", "getSnapshotInitializer", "()Landroidx/compose/runtime/snapshots/Snapshot;", "resetGlobalSnapshotLocked", "(Landroidx/compose/runtime/snapshots/GlobalSnapshot;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "pendingApplyObserverCount", "Landroidx/compose/runtime/internal/AtomicInt;", "advanceGlobalSnapshot", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "takeNewSnapshot", "(Lkotlin/jvm/functions/Function1;)Landroidx/compose/runtime/snapshots/Snapshot;", "validateOpen", "snapshot", "valid", "candidateSnapshot", "(JJLandroidx/compose/runtime/snapshots/SnapshotIdSet;)Z", ApkUtil.NAME_data, "Landroidx/compose/runtime/snapshots/StateRecord;", "(Landroidx/compose/runtime/snapshots/StateRecord;JLandroidx/compose/runtime/snapshots/SnapshotIdSet;)Z", "readable", "r", "id", "(Landroidx/compose/runtime/snapshots/StateRecord;JLandroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/StateRecord;", "state", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;)Landroidx/compose/runtime/snapshots/StateRecord;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "readError", "", "usedLocked", "overwriteUnusedRecordsLocked", "findYoungestOr", "predicate", "checkAndOverwriteUnusedRecordsLocked", "processForUnusedRecordsLocked", "writableRecord", "overwritableRecord", "candidate", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", "newWritableRecord", "newWritableRecordLocked", "newOverwritableRecordLocked", "notifyWrite", "writable", "R", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "overwritable", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/StateRecord;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "optimisticMerges", "", "currentSnapshotId", "applyingSnapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "invalidSnapshots", "(JLandroidx/compose/runtime/snapshots/MutableSnapshot;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Ljava/util/Map;", "reportReadonlySnapshotWrite", "current", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "(Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", "withCurrent", "(Landroidx/compose/runtime/snapshots/StateRecord;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "addRange", "from", "until", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;JJ)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnapshotKt {
    private static final long INVALID_SNAPSHOT = 0;
    private static List<? extends Function2<? super Set<? extends Object>, ? super Snapshot, Unit>> applyObservers;
    private static final SnapshotWeakSet<StateObject> extraStateObjects;
    private static final GlobalSnapshot globalSnapshot;
    private static List<? extends Function1<Object, Unit>> globalWriteObservers;
    private static long nextSnapshotId;
    private static SnapshotIdSet openSnapshots;
    private static AtomicInt pendingApplyObserverCount;
    private static final SnapshotDoubleIndexHeap pinningTable;
    private static final Snapshot snapshotInitializer;
    private static final Function1<SnapshotIdSet, Unit> emptyLambda = new Function1() { // from class: aid
        public final Object invoke(Object obj) {
            return SnapshotKt.a((SnapshotIdSet) obj);
        }
    };
    private static final SnapshotThreadLocal<Snapshot> threadSnapshot = new SnapshotThreadLocal<>();
    private static final Object lock = new Object();

    static {
        SnapshotIdSet.Companion companion = SnapshotIdSet.INSTANCE;
        openSnapshots = companion.getEMPTY();
        nextSnapshotId = SnapshotId_jvmKt.toSnapshotId(1) + 1;
        pinningTable = new SnapshotDoubleIndexHeap();
        extraStateObjects = new SnapshotWeakSet<>();
        applyObservers = CollectionsKt.emptyList();
        globalWriteObservers = CollectionsKt.emptyList();
        long j = nextSnapshotId;
        nextSnapshotId = 1 + j;
        GlobalSnapshot globalSnapshot2 = new GlobalSnapshot(j, companion.getEMPTY());
        openSnapshots = openSnapshots.set(globalSnapshot2.getSnapshotId());
        globalSnapshot = globalSnapshot2;
        snapshotInitializer = globalSnapshot2;
        pendingApplyObserverCount = new AtomicInt(0);
    }

    public static Unit a(SnapshotIdSet snapshotIdSet) {
        return Unit.INSTANCE;
    }

    public static final SnapshotIdSet addRange(SnapshotIdSet snapshotIdSet, long j, long j2) {
        while (Intrinsics.compare(j, j2) < 0) {
            snapshotIdSet = snapshotIdSet.set(j);
            j++;
        }
        return snapshotIdSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:41:0x0096 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:42:0x0098 A[Catch: all -> 0x008e, LOOP:1: B:30:0x005e->B:42:0x0098, LOOP_END, TryCatch #2 {all -> 0x008e, blocks: (B:25:0x004f, B:27:0x0054, B:30:0x005e, B:32:0x006e, B:34:0x007a, B:36:0x0083, B:39:0x0090, B:42:0x0098, B:43:0x009b), top: B:54:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:58:0x009b A[EDGE_INSN: B:58:0x009b->B:43:0x009b BREAK  A[LOOP:1: B:30:0x005e->B:42:0x0098], SYNTHETIC] */
    public static final <T> T advanceGlobalSnapshot(Function1<? super SnapshotIdSet, ? extends T> function1) {
        MutableScatterSet<StateObject> modified$runtime;
        T t;
        GlobalSnapshot globalSnapshot2 = globalSnapshot;
        synchronized (getLock()) {
            try {
                modified$runtime = globalSnapshot2.getModified$runtime();
                if (modified$runtime != null) {
                    pendingApplyObserverCount.add(1);
                }
                t = (T) resetGlobalSnapshotLocked(globalSnapshot2, function1);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (modified$runtime != null) {
            try {
                List<? extends Function2<? super Set<? extends Object>, ? super Snapshot, Unit>> list = applyObservers;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).invoke(ScatterSetWrapperKt.wrapIntoSet(modified$runtime), globalSnapshot2);
                }
                pendingApplyObserverCount.add(-1);
            } catch (Throwable th2) {
                pendingApplyObserverCount.add(-1);
                throw th2;
            }
        }
        synchronized (getLock()) {
            try {
                checkAndOverwriteUnusedRecordsLocked();
                if (modified$runtime != null) {
                    Object[] objArr = ((ScatterSet) modified$runtime).elements;
                    long[] jArr = ((ScatterSet) modified$runtime).metadata;
                    int length = jArr.length - 2;
                    if (length >= 0) {
                        int i2 = 0;
                        while (true) {
                            long j = jArr[i2];
                            if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                                if (i2 != length) {
                                    break;
                                    break;
                                }
                                i2++;
                            } else {
                                int i3 = 8 - ((~(i2 - length)) >>> 31);
                                for (int i4 = 0; i4 < i3; i4++) {
                                    if ((255 & j) < 128) {
                                        processForUnusedRecordsLocked((StateObject) objArr[(i2 << 3) + i4]);
                                    }
                                    j >>= 8;
                                }
                                if (i3 != 8) {
                                    break;
                                }
                                if (i2 != length) {
                                    break;
                                }
                                i2++;
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        return t;
    }

    public static Snapshot b(Function1 function1, SnapshotIdSet snapshotIdSet) {
        Snapshot snapshot = (Snapshot) function1.invoke(snapshotIdSet);
        synchronized (getLock()) {
            openSnapshots = openSnapshots.set(snapshot.getSnapshotId());
            Unit unit = Unit.INSTANCE;
        }
        return snapshot;
    }

    public static Unit c(Function1 function1, Function1 function2, Object obj) {
        function1.invoke(obj);
        function2.invoke(obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndOverwriteUnusedRecordsLocked() {
        SnapshotWeakSet<StateObject> snapshotWeakSet = extraStateObjects;
        int size = snapshotWeakSet.getSize();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            WeakReference<StateObject> weakReference = snapshotWeakSet.getValues$runtime()[i];
            StateObject stateObject = weakReference != null ? weakReference.get() : null;
            if (stateObject != null && overwriteUnusedRecordsLocked(stateObject)) {
                if (i2 != i) {
                    snapshotWeakSet.getValues$runtime()[i2] = weakReference;
                    snapshotWeakSet.getHashes()[i2] = snapshotWeakSet.getHashes()[i];
                }
                i2++;
            }
            i++;
        }
        for (int i3 = i2; i3 < size; i3++) {
            snapshotWeakSet.getValues$runtime()[i3] = null;
            snapshotWeakSet.getHashes()[i3] = 0;
        }
        if (i2 != size) {
            snapshotWeakSet.setSize$runtime(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, Function1<Object, Unit> function1, boolean z) {
        boolean z2 = snapshot instanceof MutableSnapshot;
        if (z2 || snapshot == null) {
            return new TransparentObserverMutableSnapshot(z2 ? (MutableSnapshot) snapshot : null, function1, null, false, z);
        }
        return new TransparentObserverSnapshot(snapshot, function1, false, z);
    }

    public static /* synthetic */ Snapshot createTransparentSnapshotWithNoParentReadObserver$default(Snapshot snapshot, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return createTransparentSnapshotWithNoParentReadObserver(snapshot, function1, z);
    }

    public static final <T extends StateRecord> T current(T t) {
        T t2;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot current = companion.getCurrent();
        T t3 = (T) readable(t, current.getSnapshotId(), current.getInvalid());
        if (t3 != null) {
            return t3;
        }
        synchronized (getLock()) {
            Snapshot current2 = companion.getCurrent();
            t2 = (T) readable(t, current2.getSnapshotId(), current2.getInvalid());
        }
        if (t2 != null) {
            return t2;
        }
        readError();
        wq6.a();
        return null;
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = threadSnapshot.get();
        return snapshot == null ? globalSnapshot : snapshot;
    }

    public static Unit d(Function1 function1, Function1 function2, Object obj) {
        function1.invoke(obj);
        function2.invoke(obj);
        return Unit.INSTANCE;
    }

    private static final StateRecord findYoungestOr(StateRecord stateRecord, Function1<? super StateRecord, Boolean> function1) {
        StateRecord stateRecord2 = stateRecord;
        while (stateRecord != null) {
            if (((Boolean) function1.invoke(stateRecord)).booleanValue()) {
                return stateRecord;
            }
            if (Intrinsics.compare(stateRecord2.getSnapshotId(), stateRecord.getSnapshotId()) < 0) {
                stateRecord2 = stateRecord;
            }
            stateRecord = stateRecord.getNext();
        }
        return stateRecord2;
    }

    public static final Object getLock() {
        return lock;
    }

    public static /* synthetic */ void getLock$annotations() {
    }

    public static final Snapshot getSnapshotInitializer() {
        return snapshotInitializer;
    }

    public static /* synthetic */ void getSnapshotInitializer$annotations() {
    }

    public static final Function1<Object, Unit> mergedReadObserver(final Function1<Object, Unit> function1, final Function1<Object, Unit> function2, boolean z) {
        if (!z) {
            function2 = null;
        }
        if (function1 == null || function2 == null || function1 == function2) {
            return function1 == null ? function2 : function1;
        }
        return new Function1() { // from class: zhd
            public final Object invoke(Object obj) {
                return SnapshotKt.c(function1, function2, obj);
            }
        };
    }

    public static /* synthetic */ Function1 mergedReadObserver$default(Function1 function1, Function1 function2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return mergedReadObserver(function1, function2, z);
    }

    public static final Function1<Object, Unit> mergedWriteObserver(final Function1<Object, Unit> function1, final Function1<Object, Unit> function2) {
        if (function1 == null || function2 == null || function1 == function2) {
            return function1 == null ? function2 : function1;
        }
        return new Function1() { // from class: bid
            public final Object invoke(Object obj) {
                return SnapshotKt.d(function1, function2, obj);
            }
        };
    }

    public static final <T extends StateRecord> T newOverwritableRecordLocked(T t, StateObject stateObject) {
        T t2 = (T) usedLocked(stateObject);
        if (t2 != null) {
            t2.setSnapshotId$runtime(Long.MAX_VALUE);
            return t2;
        }
        T t3 = (T) t.create(Long.MAX_VALUE);
        t3.setNext$runtime(stateObject.getFirstStateRecord());
        stateObject.prependStateRecord(t3);
        return t3;
    }

    public static final <T extends StateRecord> T newWritableRecord(T t, StateObject stateObject, Snapshot snapshot) {
        T t2;
        synchronized (getLock()) {
            t2 = (T) newWritableRecordLocked(t, stateObject, snapshot);
        }
        return t2;
    }

    private static final <T extends StateRecord> T newWritableRecordLocked(T t, StateObject stateObject, Snapshot snapshot) {
        T t2 = (T) newOverwritableRecordLocked(t, stateObject);
        t2.assign(t);
        t2.setSnapshotId$runtime(snapshot.getSnapshotId());
        return t2;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject stateObject) {
        snapshot.setWriteCount$runtime(snapshot.getWriteCount$runtime() + 1);
        Function1<Object, Unit> writeObserver$runtime = snapshot.getWriteObserver$runtime();
        if (writeObserver$runtime != null) {
            writeObserver$runtime.invoke(stateObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<StateRecord, StateRecord> optimisticMerges(long j, MutableSnapshot mutableSnapshot, SnapshotIdSet snapshotIdSet) {
        long[] jArr;
        Map<StateRecord, StateRecord> map;
        SnapshotIdSet snapshotIdSet2;
        Object[] objArr;
        int i;
        long[] jArr2;
        Map<StateRecord, StateRecord> map2;
        Object[] objArr2;
        int i2;
        int i3;
        MutableScatterSet<StateObject> modified$runtime = mutableSnapshot.getModified$runtime();
        Map<StateRecord, StateRecord> map3 = null;
        if (modified$runtime == null) {
            return null;
        }
        long snapshotId = mutableSnapshot.getSnapshotId();
        SnapshotIdSet snapshotIdSetOr = mutableSnapshot.getInvalid().set(snapshotId).or(mutableSnapshot.getPreviousIds$runtime());
        Object[] objArr3 = ((ScatterSet) modified$runtime).elements;
        long[] jArr3 = ((ScatterSet) modified$runtime).metadata;
        int length = jArr3.length - 2;
        if (length < 0) {
            return null;
        }
        HashMap map4 = null;
        int i4 = 0;
        while (true) {
            long j2 = jArr3[i4];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i5 = 8;
                int i6 = 8 - ((~(i4 - length)) >>> 31);
                int i7 = 0;
                while (i7 < i6) {
                    if ((255 & j2) < 128) {
                        StateObject stateObject = (StateObject) objArr3[(i4 << 3) + i7];
                        map2 = map3;
                        StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                        jArr2 = jArr3;
                        i2 = i4;
                        i3 = i5;
                        StateRecord stateRecord = readable(firstStateRecord, j, snapshotIdSet);
                        if (stateRecord == null) {
                            objArr2 = objArr3;
                        } else {
                            objArr2 = objArr3;
                            StateRecord stateRecord2 = readable(firstStateRecord, snapshotId, snapshotIdSetOr);
                            if (stateRecord2 != null && !Intrinsics.areEqual(stateRecord, stateRecord2)) {
                                StateRecord stateRecord3 = readable(firstStateRecord, snapshotId, mutableSnapshot.getInvalid());
                                if (stateRecord3 == null) {
                                    readError();
                                    wq6.a();
                                    return map2;
                                }
                                StateRecord stateRecordMergeRecords = stateObject.mergeRecords(stateRecord2, stateRecord, stateRecord3);
                                if (stateRecordMergeRecords == null) {
                                    return map2;
                                }
                                if (map4 == null) {
                                    map4 = new HashMap();
                                }
                                map4.put(stateRecord, stateRecordMergeRecords);
                                map4 = map4;
                            }
                        }
                    } else {
                        jArr2 = jArr3;
                        map2 = map3;
                        objArr2 = objArr3;
                        i2 = i4;
                        i3 = i5;
                    }
                    j2 >>= i3;
                    i7++;
                    map3 = map2;
                    i4 = i2;
                    i5 = i3;
                    jArr3 = jArr2;
                    objArr3 = objArr2;
                    snapshotIdSetOr = snapshotIdSetOr;
                }
                jArr = jArr3;
                map = map3;
                snapshotIdSet2 = snapshotIdSetOr;
                objArr = objArr3;
                i = i4;
                if (i6 != i5) {
                    return map4;
                }
            } else {
                jArr = jArr3;
                map = map3;
                snapshotIdSet2 = snapshotIdSetOr;
                objArr = objArr3;
                i = i4;
            }
            int i8 = i;
            if (i8 == length) {
                return map4;
            }
            i4 = i8 + 1;
            map3 = map;
            jArr3 = jArr;
            objArr3 = objArr;
            snapshotIdSetOr = snapshotIdSet2;
        }
    }

    public static final <T extends StateRecord, R> R overwritable(T t, StateObject stateObject, T t2, Function1<? super T, ? extends R> function1) {
        Snapshot current;
        R r;
        synchronized (getLock()) {
            try {
                current = Snapshot.INSTANCE.getCurrent();
                r = (R) function1.invoke(overwritableRecord(t, stateObject, current, t2));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(current, stateObject);
        return r;
    }

    public static final <T extends StateRecord> T overwritableRecord(T t, StateObject stateObject, Snapshot snapshot, T t2) {
        T t3;
        if (snapshot.getReadOnly()) {
            snapshot.mo2569recordModified$runtime(stateObject);
        }
        long snapshotId = snapshot.getSnapshotId();
        if (t2.getSnapshotId() == snapshotId) {
            return t2;
        }
        synchronized (getLock()) {
            t3 = (T) newOverwritableRecordLocked(t, stateObject);
        }
        t3.setSnapshotId$runtime(snapshotId);
        if (t2.getSnapshotId() != SnapshotId_jvmKt.toSnapshotId(1)) {
            snapshot.mo2569recordModified$runtime(stateObject);
        }
        return t3;
    }

    private static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        long jLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId);
        StateRecord stateRecord2 = null;
        StateRecord firstStateRecord = null;
        int i = 0;
        for (StateRecord firstStateRecord2 = stateObject.getFirstStateRecord(); firstStateRecord2 != null; firstStateRecord2 = firstStateRecord2.getNext()) {
            long snapshotId = firstStateRecord2.getSnapshotId();
            if (snapshotId != INVALID_SNAPSHOT) {
                if (Intrinsics.compare(snapshotId, jLowestOrDefault) >= 0) {
                    i++;
                } else if (stateRecord2 == null) {
                    i++;
                    stateRecord2 = firstStateRecord2;
                } else {
                    if (Intrinsics.compare(firstStateRecord2.getSnapshotId(), stateRecord2.getSnapshotId()) < 0) {
                        stateRecord = stateRecord2;
                        stateRecord2 = firstStateRecord2;
                    } else {
                        stateRecord = firstStateRecord2;
                    }
                    if (firstStateRecord == null) {
                        firstStateRecord = stateObject.getFirstStateRecord();
                        StateRecord stateRecord3 = firstStateRecord;
                        while (true) {
                            if (firstStateRecord == null) {
                                firstStateRecord = stateRecord3;
                                break;
                            }
                            if (Intrinsics.compare(firstStateRecord.getSnapshotId(), jLowestOrDefault) >= 0) {
                                break;
                            }
                            if (Intrinsics.compare(stateRecord3.getSnapshotId(), firstStateRecord.getSnapshotId()) < 0) {
                                stateRecord3 = firstStateRecord;
                            }
                            firstStateRecord = firstStateRecord.getNext();
                        }
                    }
                    stateRecord2.setSnapshotId$runtime(INVALID_SNAPSHOT);
                    stateRecord2.assign(firstStateRecord);
                    stateRecord2 = stateRecord;
                }
            }
        }
        return i > 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processForUnusedRecordsLocked(StateObject stateObject) {
        if (overwriteUnusedRecordsLocked(stateObject)) {
            extraStateObjects.add(stateObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied");
    }

    public static final <T extends StateRecord> T readable(T t, StateObject stateObject) {
        T t2;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot current = companion.getCurrent();
        Function1<Object, Unit> readObserver = current.getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(stateObject);
        }
        T t3 = (T) readable(t, current.getSnapshotId(), current.getInvalid());
        if (t3 != null) {
            return t3;
        }
        synchronized (getLock()) {
            Snapshot current2 = companion.getCurrent();
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            firstStateRecord.getClass();
            t2 = (T) readable(firstStateRecord, current2.getSnapshotId(), current2.getInvalid());
            if (t2 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
        }
        return t2;
    }

    public static final void releasePinningLocked(int i) {
        pinningTable.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void reportReadonlySnapshotWrite() {
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T resetGlobalSnapshotLocked(GlobalSnapshot globalSnapshot2, Function1<? super SnapshotIdSet, ? extends T> function1) {
        long snapshotId = globalSnapshot2.getSnapshotId();
        T t = (T) function1.invoke(openSnapshots.clear(snapshotId));
        long j = nextSnapshotId;
        nextSnapshotId = 1 + j;
        openSnapshots = openSnapshots.clear(snapshotId);
        globalSnapshot2.setSnapshotId$runtime(j);
        globalSnapshot2.setInvalid$runtime(openSnapshots);
        globalSnapshot2.setWriteCount$runtime(0);
        globalSnapshot2.setModified$runtime(null);
        globalSnapshot2.releasePinnedSnapshotLocked$runtime();
        openSnapshots = openSnapshots.set(j);
        return t;
    }

    public static final <T> T sync(Function0<? extends T> function0) {
        T t;
        synchronized (getLock()) {
            try {
                t = (T) function0.invoke();
                InlineMarker.finallyStart(1);
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends Snapshot> T takeNewSnapshot(final Function1<? super SnapshotIdSet, ? extends T> function1) {
        return (T) advanceGlobalSnapshot(new Function1() { // from class: cid
            public final Object invoke(Object obj) {
                return SnapshotKt.b(function1, (SnapshotIdSet) obj);
            }
        });
    }

    public static final int trackPinning(long j, SnapshotIdSet snapshotIdSet) {
        int iAdd;
        long jLowest = snapshotIdSet.lowest(j);
        synchronized (getLock()) {
            iAdd = pinningTable.add(jLowest);
        }
        return iAdd;
    }

    private static final StateRecord usedLocked(StateObject stateObject) {
        long jLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId) - 1;
        SnapshotIdSet empty = SnapshotIdSet.INSTANCE.getEMPTY();
        StateRecord stateRecord = null;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
            if (firstStateRecord.getSnapshotId() != INVALID_SNAPSHOT) {
                if (valid(firstStateRecord, jLowestOrDefault, empty)) {
                    if (stateRecord == null) {
                        stateRecord = firstStateRecord;
                    } else if (Intrinsics.compare(firstStateRecord.getSnapshotId(), stateRecord.getSnapshotId()) >= 0) {
                        return stateRecord;
                    }
                }
            }
            return firstStateRecord;
        }
        return null;
    }

    private static final boolean valid(long j, long j2, SnapshotIdSet snapshotIdSet) {
        return (j2 == INVALID_SNAPSHOT || Intrinsics.compare(j2, j) > 0 || snapshotIdSet.get(j2)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateOpen(Snapshot snapshot) {
        long jLowestOrDefault;
        if (openSnapshots.get(snapshot.getSnapshotId())) {
            return;
        }
        StringBuilder sb = new StringBuilder("Snapshot is not open: snapshotId=");
        sb.append(snapshot.getSnapshotId());
        sb.append(", disposed=");
        sb.append(snapshot.getDisposed());
        sb.append(", applied=");
        MutableSnapshot mutableSnapshot = snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null;
        sb.append(mutableSnapshot != null ? Boolean.valueOf(mutableSnapshot.getApplied$runtime()) : "read-only");
        sb.append(", lowestPin=");
        synchronized (getLock()) {
            jLowestOrDefault = pinningTable.lowestOrDefault(-1L);
        }
        sb.append(jLowestOrDefault);
        throw new IllegalStateException(sb.toString().toString());
    }

    public static final <T extends StateRecord, R> R withCurrent(T t, Function1<? super T, ? extends R> function1) {
        return (R) function1.invoke(current(t));
    }

    public static final <T extends StateRecord, R> R writable(T t, StateObject stateObject, Function1<? super T, ? extends R> function1) {
        Snapshot current;
        R r;
        synchronized (getLock()) {
            try {
                current = Snapshot.INSTANCE.getCurrent();
                r = (R) function1.invoke(writableRecord(t, stateObject, current));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(current, stateObject);
        return r;
    }

    public static final <T extends StateRecord> T writableRecord(T t, StateObject stateObject, Snapshot snapshot) {
        T t2;
        if (snapshot.getReadOnly()) {
            snapshot.mo2569recordModified$runtime(stateObject);
        }
        long snapshotId = snapshot.getSnapshotId();
        T t3 = (T) readable(t, snapshotId, snapshot.getInvalid());
        if (t3 == null) {
            readError();
            wq6.a();
            return null;
        }
        if (t3.getSnapshotId() == snapshot.getSnapshotId()) {
            return t3;
        }
        synchronized (getLock()) {
            t2 = (T) readable(stateObject.getFirstStateRecord(), snapshotId, snapshot.getInvalid());
            if (t2 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
            if (t2.getSnapshotId() != snapshotId) {
                t2 = (T) newWritableRecordLocked(t2, stateObject, snapshot);
            }
        }
        t2.getClass();
        if (t3.getSnapshotId() != SnapshotId_jvmKt.toSnapshotId(1)) {
            snapshot.mo2569recordModified$runtime(stateObject);
        }
        return t2;
    }

    private static final boolean valid(StateRecord stateRecord, long j, SnapshotIdSet snapshotIdSet) {
        return valid(j, stateRecord.getSnapshotId(), snapshotIdSet);
    }

    public static final <T extends StateRecord, R> R writable(T t, StateObject stateObject, Snapshot snapshot, Function1<? super T, ? extends R> function1) {
        R r;
        synchronized (getLock()) {
            try {
                r = (R) function1.invoke(writableRecord(t, stateObject, snapshot));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(snapshot, stateObject);
        return r;
    }

    public static final <T extends StateRecord> T current(T t, Snapshot snapshot) {
        T t2;
        T t3 = (T) readable(t, snapshot.getSnapshotId(), snapshot.getInvalid());
        if (t3 != null) {
            return t3;
        }
        synchronized (getLock()) {
            t2 = (T) readable(t, snapshot.getSnapshotId(), snapshot.getInvalid());
        }
        if (t2 != null) {
            return t2;
        }
        readError();
        wq6.a();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends StateRecord> T readable(T t, long j, SnapshotIdSet snapshotIdSet) {
        T t2 = null;
        while (t != null) {
            if (valid(t, j, snapshotIdSet) && (t2 == null || Intrinsics.compare(t2.getSnapshotId(), t.getSnapshotId()) < 0)) {
                t2 = t;
            }
            t = (T) t.getNext();
        }
        if (t2 != null) {
            return t2;
        }
        return null;
    }

    public static final <T extends StateRecord> T readable(T t, StateObject stateObject, Snapshot snapshot) {
        T t2;
        Function1<Object, Unit> readObserver = snapshot.getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(stateObject);
        }
        T t3 = (T) readable(t, snapshot.getSnapshotId(), snapshot.getInvalid());
        if (t3 != null) {
            return t3;
        }
        synchronized (getLock()) {
            Snapshot current = Snapshot.INSTANCE.getCurrent();
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            firstStateRecord.getClass();
            t2 = (T) readable(firstStateRecord, current.getSnapshotId(), current.getInvalid());
            if (t2 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
        }
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void advanceGlobalSnapshot() {
        advanceGlobalSnapshot(emptyLambda);
    }
}
