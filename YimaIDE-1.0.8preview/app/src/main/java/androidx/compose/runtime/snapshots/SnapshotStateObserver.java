package androidx.compose.runtime.snapshots;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0001<B0\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0005H\u0002J\u0016\u0010\u0017\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0002J\u0010\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u001d\u0010#\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u0003H\u0082\bJ\u001d\u0010%\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00100\u0003H\u0082\bJ?\u0010,\u001a\u00020\u0005\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010.\u001a\u0002H-2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u00100J\u0016\u00101\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u000e\u00102\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0001J)\u00103\u001a\u00020\u00052!\u00104\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00100\u0003J\u0006\u00105\u001a\u00020\u0005J\u0006\u00106\u001a\u00020\u0005J\u001e\u00107\u001a\u00020\u00052\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u00109\u001a\u00020\u0014H\u0007J\u0006\u00102\u001a\u00020\u0005J&\u0010:\u001a\u00020\u001f\"\b\b\u0000\u0010-*\u00020\u00012\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u0003H\u0002R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00060\u0001j\u0002`!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", "name", "callback", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "pendingChanges", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "sendingNotifications", "", "applyObserver", "Lkotlin/Function2;", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "drainChanges", "sendNotifications", "addChanges", "set", "removeChanges", "report", "", "readObserver", "observedScopeMaps", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "observedScopeMapsLock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "forEachScopeMap", "block", "removeScopeMapIf", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "isPaused", "currentMap", "currentMapThreadId", "", "observeReads", "T", "scope", "onValueChangedForScope", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "withNoObservations", "clear", "clearIf", "predicate", "start", "stop", "notifyChanges", "changes", "snapshot", "ensureMap", "onChanged", "ObservedScopeMap", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1<Function0<Unit>, Unit> onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference<Object> pendingChanges = new AtomicReference<>(null);
    private final Function2<Set<? extends Object>, Snapshot, Unit> applyObserver = new Function2() { // from class: oid
        public final Object invoke(Object obj, Object obj2) {
            return SnapshotStateObserver.b(this.b, (Set) obj, (Snapshot) obj2);
        }
    };
    private final Function1<Object, Unit> readObserver = new Function1() { // from class: pid
        public final Object invoke(Object obj) {
            return SnapshotStateObserver.c(this.b, obj);
        }
    };
    private final MutableVector<ObservedScopeMap> observedScopeMaps = new MutableVector<>(new ObservedScopeMap[16], 0);
    private final Object observedScopeMapsLock = new Object();
    private long currentMapThreadId = -1;

    public SnapshotStateObserver(Function1<? super Function0<Unit>, Unit> function1) {
        this.onChangedExecutor = function1;
    }

    public static Unit a(SnapshotStateObserver snapshotStateObserver) {
        do {
            synchronized (snapshotStateObserver.observedScopeMapsLock) {
                try {
                    if (!snapshotStateObserver.sendingNotifications) {
                        snapshotStateObserver.sendingNotifications = true;
                        try {
                            MutableVector<ObservedScopeMap> mutableVector = snapshotStateObserver.observedScopeMaps;
                            ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                            int size = mutableVector.getSize();
                            for (int i = 0; i < size; i++) {
                                observedScopeMapArr[i].notifyInvalidatedScopes();
                            }
                            snapshotStateObserver.sendingNotifications = false;
                        } catch (Throwable th) {
                            snapshotStateObserver.sendingNotifications = false;
                            throw th;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        } while (snapshotStateObserver.drainChanges());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    /* JADX WARN: Multi-variable type inference failed */
    private final void addChanges(Set<? extends Object> set) throws KotlinNothingValueException {
        Object obj;
        List listPlus;
        do {
            obj = this.pendingChanges.get();
            if (obj == null) {
                listPlus = set;
            } else if (obj instanceof Set) {
                listPlus = CollectionsKt.listOf(new Set[]{obj, set});
            } else {
                if (!(obj instanceof List)) {
                    report();
                    wq6.a();
                    return;
                }
                listPlus = CollectionsKt.plus((Collection) obj, CollectionsKt.listOf(set));
            }
        } while (!this.pendingChanges.compareAndSet(obj, listPlus));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public static Unit b(SnapshotStateObserver snapshotStateObserver, Set set, Snapshot snapshot) throws KotlinNothingValueException {
        snapshotStateObserver.addChanges(set);
        if (snapshotStateObserver.drainChanges()) {
            snapshotStateObserver.sendNotifications();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(SnapshotStateObserver snapshotStateObserver, Object obj) {
        if (!snapshotStateObserver.isPaused) {
            synchronized (snapshotStateObserver.observedScopeMapsLock) {
                ObservedScopeMap observedScopeMap = snapshotStateObserver.currentMap;
                observedScopeMap.getClass();
                observedScopeMap.recordRead(obj);
                Unit unit = Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    private final boolean drainChanges() throws KotlinNothingValueException {
        boolean z;
        synchronized (this.observedScopeMapsLock) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            Set<? extends Object> setRemoveChanges = removeChanges();
            if (setRemoveChanges == null) {
                return z2;
            }
            synchronized (this.observedScopeMapsLock) {
                try {
                    MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                    ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                    int size = mutableVector.getSize();
                    for (int i = 0; i < size; i++) {
                        z2 = observedScopeMapArr[i].recordInvalidation(setRemoveChanges) || z2;
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private final <T> ObservedScopeMap ensureMap(Function1<? super T, Unit> onChanged) {
        ObservedScopeMap observedScopeMap;
        MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
        ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
        int size = mutableVector.getSize();
        int i = 0;
        while (true) {
            if (i >= size) {
                observedScopeMap = null;
                break;
            }
            observedScopeMap = observedScopeMapArr[i];
            if (observedScopeMap.getOnChanged() == onChanged) {
                break;
            }
            i++;
        }
        ObservedScopeMap observedScopeMap2 = observedScopeMap;
        if (observedScopeMap2 != null) {
            return observedScopeMap2;
        }
        onChanged.getClass();
        ObservedScopeMap observedScopeMap3 = new ObservedScopeMap((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(onChanged, 1));
        this.observedScopeMaps.add(observedScopeMap3);
        return observedScopeMap3;
    }

    private final void forEachScopeMap(Function1<? super ObservedScopeMap, Unit> block) {
        synchronized (this.observedScopeMapsLock) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                int size = mutableVector.getSize();
                for (int i = 0; i < size; i++) {
                    block.invoke(observedScopeMapArr[i]);
                }
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    private final Set<Object> removeChanges() throws KotlinNothingValueException {
        Object obj;
        Object objSubList;
        Set<Object> set;
        do {
            obj = this.pendingChanges.get();
            objSubList = null;
            if (obj == null) {
                return null;
            }
            if (obj instanceof Set) {
                set = (Set) obj;
            } else {
                if (!(obj instanceof List)) {
                    report();
                    wq6.a();
                    return null;
                }
                List list = (List) obj;
                Set<Object> set2 = (Set) list.get(0);
                if (list.size() == 2) {
                    objSubList = list.get(1);
                } else if (list.size() > 2) {
                    objSubList = list.subList(1, list.size());
                }
                set = set2;
            }
        } while (!this.pendingChanges.compareAndSet(obj, objSubList));
        return set;
    }

    private final void removeScopeMapIf(Function1<? super ObservedScopeMap, Boolean> block) {
        synchronized (this.observedScopeMapsLock) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i = 0;
                int i2 = 0;
                while (true) {
                    ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                    if (i < size) {
                        if (((Boolean) block.invoke(observedScopeMapArr[i])).booleanValue()) {
                            i2++;
                        } else if (i2 > 0) {
                            ObservedScopeMap[] observedScopeMapArr2 = mutableVector.content;
                            observedScopeMapArr2[i - i2] = observedScopeMapArr2[i];
                        }
                        i++;
                    } else {
                        int i3 = size - i2;
                        ArraysKt.fill(observedScopeMapArr, (Object) null, i3, size);
                        mutableVector.setSize(i3);
                        Unit unit = Unit.INSTANCE;
                        InlineMarker.finallyStart(1);
                    }
                }
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    private final Void report() throws KotlinNothingValueException {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    private final void sendNotifications() {
        this.onChangedExecutor.invoke(new Function0() { // from class: nid
            public final Object invoke() {
                return SnapshotStateObserver.a(this.b);
            }
        });
    }

    public final void clear(Object scope) {
        synchronized (this.observedScopeMapsLock) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i = 0;
                int i2 = 0;
                while (true) {
                    ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                    if (i < size) {
                        ObservedScopeMap observedScopeMap = observedScopeMapArr[i];
                        observedScopeMap.clearScopeObservations(scope);
                        if (!observedScopeMap.hasScopeObservations()) {
                            i2++;
                        } else if (i2 > 0) {
                            ObservedScopeMap[] observedScopeMapArr2 = mutableVector.content;
                            observedScopeMapArr2[i - i2] = observedScopeMapArr2[i];
                        }
                        i++;
                    } else {
                        int i3 = size - i2;
                        ArraysKt.fill(observedScopeMapArr, (Object) null, i3, size);
                        mutableVector.setSize(i3);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void clearIf(Function1<Object, Boolean> predicate) {
        synchronized (this.observedScopeMapsLock) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i = 0;
                int i2 = 0;
                while (true) {
                    ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                    if (i < size) {
                        ObservedScopeMap observedScopeMap = observedScopeMapArr[i];
                        observedScopeMap.removeScopeIf(predicate);
                        if (!observedScopeMap.hasScopeObservations()) {
                            i2++;
                        } else if (i2 > 0) {
                            ObservedScopeMap[] observedScopeMapArr2 = mutableVector.content;
                            observedScopeMapArr2[i - i2] = observedScopeMapArr2[i];
                        }
                        i++;
                    } else {
                        int i3 = size - i2;
                        ArraysKt.fill(observedScopeMapArr, (Object) null, i3, size);
                        mutableVector.setSize(i3);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        this.applyObserver.invoke(changes, snapshot);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0135  */
    /* JADX WARN: Code duplicated, block: B:53:0x0149 A[Catch: all -> 0x0115, TryCatch #8 {all -> 0x0115, blocks: (B:35:0x0107, B:43:0x0123, B:44:0x012e, B:49:0x013c, B:52:0x0141, B:53:0x0149, B:55:0x014f), top: B:102:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:55:0x014f A[Catch: all -> 0x0115, TRY_LEAVE, TryCatch #8 {all -> 0x0115, blocks: (B:35:0x0107, B:43:0x0123, B:44:0x012e, B:49:0x013c, B:52:0x0141, B:53:0x0149, B:55:0x014f), top: B:102:0x00ca }] */
    public final <T> void observeReads(T scope, Function1<? super T, Unit> onValueChangedForScope, Function0<Unit> block) {
        ObservedScopeMap observedScopeMapEnsureMap;
        long j;
        MutableVector<DerivedStateObserver> mutableVector;
        Snapshot transparentObserverMutableSnapshot;
        Snapshot snapshotMakeCurrent;
        synchronized (this.observedScopeMapsLock) {
            observedScopeMapEnsureMap = ensureMap(onValueChangedForScope);
        }
        boolean z = this.isPaused;
        ObservedScopeMap observedScopeMap = this.currentMap;
        long j2 = this.currentMapThreadId;
        if (j2 != -1) {
            if (!(j2 == Thread_jvmKt.currentThreadId())) {
                PreconditionsKt.throwIllegalArgumentException("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + j2 + "), currentThread={id=" + Thread_jvmKt.currentThreadId() + ", name=" + Thread_jvmKt.currentThreadName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
            }
        }
        try {
            this.isPaused = false;
            this.currentMap = observedScopeMapEnsureMap;
            this.currentMapThreadId = Thread_jvmKt.currentThreadId();
            Function1<Object, Unit> function1 = this.readObserver;
            Object obj = observedScopeMapEnsureMap.currentScope;
            MutableObjectIntMap mutableObjectIntMap = observedScopeMapEnsureMap.currentScopeReads;
            int i = observedScopeMapEnsureMap.currentToken;
            observedScopeMapEnsureMap.currentScope = scope;
            observedScopeMapEnsureMap.currentScopeReads = (MutableObjectIntMap) observedScopeMapEnsureMap.scopeToValues.get(scope);
            if (observedScopeMapEnsureMap.currentToken == -1) {
                observedScopeMapEnsureMap.currentToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            }
            DerivedStateObserver derivedStateObserver = observedScopeMapEnsureMap.getDerivedStateObserver();
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(derivedStateObserver);
                Snapshot.Companion companion = Snapshot.INSTANCE;
                if (function1 == null) {
                    block.invoke();
                    j = j2;
                    mutableVector = mutableVectorDerivedStateObservers;
                } else {
                    Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
                    try {
                        if (!(snapshot instanceof TransparentObserverMutableSnapshot)) {
                            j = j2;
                            if (snapshot != null) {
                                mutableVector = mutableVectorDerivedStateObservers;
                                transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function1, null, true, false);
                                snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                                block.invoke();
                                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                transparentObserverMutableSnapshot.dispose();
                            } else {
                                mutableVector = mutableVectorDerivedStateObservers;
                                transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function1, null, true, false);
                                snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                                block.invoke();
                                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                transparentObserverMutableSnapshot.dispose();
                            }
                            this.currentMap = observedScopeMap;
                            this.isPaused = z;
                            this.currentMapThreadId = j;
                            throw th;
                        }
                        try {
                            if (((TransparentObserverMutableSnapshot) snapshot).getThreadId() == Thread_jvmKt.currentThreadId()) {
                                Function1<Object, Unit> readObserver = ((TransparentObserverMutableSnapshot) snapshot).getReadObserver();
                                Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) snapshot).getWriteObserver$runtime();
                                try {
                                    j = j2;
                                    try {
                                        ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(function1, readObserver, false, 4, null));
                                        ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(null, writeObserver$runtime));
                                        block.invoke();
                                        ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(readObserver);
                                        ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(writeObserver$runtime);
                                        mutableVector = mutableVectorDerivedStateObservers;
                                    } catch (Throwable th) {
                                        th = th;
                                        ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(readObserver);
                                        ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(writeObserver$runtime);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } else {
                                j = j2;
                                if (snapshot != null || (snapshot instanceof MutableSnapshot)) {
                                    mutableVector = mutableVectorDerivedStateObservers;
                                    try {
                                        transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function1, null, true, false);
                                    } catch (Throwable th3) {
                                        th = th3;
                                        j = j;
                                        try {
                                            mutableVector.removeAt(mutableVector.getSize() - 1);
                                            throw th;
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                    }
                                } else {
                                    transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(function1);
                                    mutableVector = mutableVectorDerivedStateObservers;
                                }
                                try {
                                    snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                                    try {
                                        block.invoke();
                                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                        transparentObserverMutableSnapshot.dispose();
                                    } catch (Throwable th5) {
                                        try {
                                            transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                            throw th5;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            try {
                                                transparentObserverMutableSnapshot.dispose();
                                                throw th;
                                            } catch (Throwable th7) {
                                                th = th7;
                                                mutableVector.removeAt(mutableVector.getSize() - 1);
                                                throw th;
                                            }
                                        }
                                    }
                                } catch (Throwable th8) {
                                    th = th8;
                                }
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            j = j2;
                            mutableVector = mutableVectorDerivedStateObservers;
                            j = j;
                            mutableVector.removeAt(mutableVector.getSize() - 1);
                            throw th;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                    }
                }
                try {
                    mutableVector.removeAt(mutableVector.getSize() - 1);
                    Object obj2 = observedScopeMapEnsureMap.currentScope;
                    obj2.getClass();
                    observedScopeMapEnsureMap.clearObsoleteStateReads(obj2);
                    observedScopeMapEnsureMap.currentScope = obj;
                    observedScopeMapEnsureMap.currentScopeReads = mutableObjectIntMap;
                    observedScopeMapEnsureMap.currentToken = i;
                    this.currentMap = observedScopeMap;
                    this.isPaused = z;
                    this.currentMapThreadId = j;
                } catch (Throwable th11) {
                    th = th11;
                    j = j;
                }
            } catch (Throwable th12) {
                th = th12;
                j = j2;
                mutableVector = mutableVectorDerivedStateObservers;
            }
        } catch (Throwable th13) {
            th = th13;
            j = j2;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    @Deprecated(message = "Replace with Snapshot.withoutReadObservation()", replaceWith = @ReplaceWith(expression = "Snapshot.withoutReadObservation(block)", imports = {"androidx.compose.runtime.snapshots.Snapshot"}))
    public final void withNoObservations(Function0<Unit> block) {
        boolean z = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = z;
        }
    }

    public final void clear() {
        synchronized (this.observedScopeMapsLock) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                int size = mutableVector.getSize();
                for (int i = 0; i < size; i++) {
                    observedScopeMapArr[i].clear();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0001J.\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0002J7\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00012\u0014\b\b\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\b\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040.H\u0086\bJ\u0010\u0010/\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0001H\u0002J\u000e\u00100\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0001J)\u00101\u001a\u00020\u00042!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0\u0003J\u0006\u00105\u001a\u00020\u001dJ\u0018\u00106\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u0001H\u0002J\u0006\u00107\u001a\u00020\u0004J\u0014\u00108\u001a\u00020\u001d2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00010:J\u0012\u0010;\u001a\u00020\u00042\n\u0010<\u001a\u0006\u0012\u0002\b\u00030\u0017J\u0006\u0010=\u001a\u00020\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R6\u0010$\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010%j\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u0001`&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "onChanged", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "currentScope", "currentScopeReads", "Landroidx/collection/MutableObjectIntMap;", "currentToken", "", "valueToScopes", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/collection/MutableScatterMap;", "scopeToValues", "Landroidx/collection/MutableScatterMap;", "invalidated", "Landroidx/collection/MutableScatterSet;", "statesToReread", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/DerivedState;", "derivedStateObserver", "Landroidx/compose/runtime/DerivedStateObserver;", "getDerivedStateObserver", "()Landroidx/compose/runtime/DerivedStateObserver;", "readingDerivedStates", "", "getReadingDerivedStates", "()Z", "setReadingDerivedStates", "(Z)V", "deriveStateScopeCount", "dependencyToDerivedStates", "recordedDerivedStateValues", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recordRead", "value", "recordedValues", "observe", "scope", "readObserver", "block", "Lkotlin/Function0;", "clearObsoleteStateReads", "clearScopeObservations", "removeScopeIf", "predicate", "Lkotlin/ParameterName;", "name", "hasScopeObservations", "removeObservation", "clear", "recordInvalidation", "changes", "", "rereadDerivedState", "derivedState", "notifyInvalidatedScopes", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ObservedScopeMap {
        private Object currentScope;
        private MutableObjectIntMap<Object> currentScopeReads;
        private int deriveStateScopeCount;
        private final Function1<Object, Unit> onChanged;
        private boolean readingDerivedStates;
        private int currentToken = -1;
        private final MutableScatterMap<Object, Object> valueToScopes = ScopeMap.m2490constructorimpl$default(null, 1, null);
        private final MutableScatterMap<Object, MutableObjectIntMap<Object>> scopeToValues = new MutableScatterMap<>(0, 1, (DefaultConstructorMarker) null);
        private final MutableScatterSet<Object> invalidated = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
        private final MutableVector<DerivedState<?>> statesToReread = new MutableVector<>(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount--;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount++;
            }
        };
        private final MutableScatterMap<Object, Object> dependencyToDerivedStates = ScopeMap.m2490constructorimpl$default(null, 1, null);
        private final HashMap<DerivedState<?>, Object> recordedDerivedStateValues = new HashMap<>();

        public ObservedScopeMap(Function1<Object, Unit> function1) {
            this.onChanged = function1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearObsoleteStateReads(Object scope) {
            int i = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                return;
            }
            long[] jArr = ((ObjectIntMap) mutableObjectIntMap).metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i2 << 3) + i4;
                            Object obj = ((ObjectIntMap) mutableObjectIntMap).keys[i5];
                            boolean z = ((ObjectIntMap) mutableObjectIntMap).values[i5] != i;
                            if (z) {
                                removeObservation(scope, obj);
                            }
                            if (z) {
                                mutableObjectIntMap.removeValueAt(i5);
                            }
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        return;
                    }
                }
                if (i2 == length) {
                    return;
                } else {
                    i2++;
                }
            }
        }

        private final void recordRead(Object value, int currentToken, Object currentScope, MutableObjectIntMap<Object> recordedValues) {
            int i;
            int i2;
            int i3;
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            int iPut = recordedValues.put(value, currentToken, -1);
            int i4 = 2;
            if (!(value instanceof DerivedState) || iPut == currentToken) {
                i = 2;
                i2 = -1;
            } else {
                DerivedState.Record currentRecord = ((DerivedState) value).getCurrentRecord();
                this.recordedDerivedStateValues.put(value, currentRecord.getCurrentValue());
                ObjectIntMap<StateObject> dependencies = currentRecord.getDependencies();
                MutableScatterMap<Object, Object> mutableScatterMap = this.dependencyToDerivedStates;
                ScopeMap.m2499removeScopeimpl(mutableScatterMap, value);
                Object[] objArr = dependencies.keys;
                long[] jArr = dependencies.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i5 = 0;
                    while (true) {
                        long j = jArr[i5];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i6 = 8 - ((~(i5 - length)) >>> 31);
                            int i7 = 0;
                            while (i7 < i6) {
                                if ((j & 255) < 128) {
                                    i3 = i4;
                                    StateObject stateObject = (StateObject) objArr[(i5 << 3) + i7];
                                    if (stateObject instanceof StateObjectImpl) {
                                        ((StateObjectImpl) stateObject).m2585recordReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(i3));
                                    }
                                    ScopeMap.m2484addimpl(mutableScatterMap, stateObject, value);
                                } else {
                                    i3 = i4;
                                }
                                j >>= 8;
                                i7++;
                                i4 = i3;
                            }
                            i = i4;
                            if (i6 != 8) {
                                break;
                            }
                        } else {
                            i = i4;
                        }
                        if (i5 == length) {
                            break;
                        }
                        i5++;
                        i4 = i;
                    }
                } else {
                    i = 2;
                }
                i2 = -1;
            }
            if (iPut == i2) {
                if (value instanceof StateObjectImpl) {
                    ((StateObjectImpl) value).m2585recordReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(i));
                }
                ScopeMap.m2484addimpl(this.valueToScopes, value, currentScope);
            }
        }

        private final void removeObservation(Object scope, Object value) {
            ScopeMap.m2497removeimpl(this.valueToScopes, value, scope);
            if (!(value instanceof DerivedState) || ScopeMap.m2491containsimpl(this.valueToScopes, value)) {
                return;
            }
            ScopeMap.m2499removeScopeimpl(this.dependencyToDerivedStates, value);
            this.recordedDerivedStateValues.remove(value);
        }

        public final void clear() {
            ScopeMap.m2488clearimpl(this.valueToScopes);
            this.scopeToValues.clear();
            ScopeMap.m2488clearimpl(this.dependencyToDerivedStates);
            this.recordedDerivedStateValues.clear();
        }

        public final void clearScopeObservations(Object scope) {
            MutableObjectIntMap mutableObjectIntMap = (MutableObjectIntMap) this.scopeToValues.remove(scope);
            if (mutableObjectIntMap == null) {
                return;
            }
            Object[] objArr = ((ObjectIntMap) mutableObjectIntMap).keys;
            int[] iArr = ((ObjectIntMap) mutableObjectIntMap).values;
            long[] jArr = ((ObjectIntMap) mutableObjectIntMap).metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            int i5 = iArr[i4];
                            removeObservation(scope, obj);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        }

        public final DerivedStateObserver getDerivedStateObserver() {
            return this.derivedStateObserver;
        }

        public final Function1<Object, Unit> getOnChanged() {
            return this.onChanged;
        }

        public final boolean getReadingDerivedStates() {
            return this.readingDerivedStates;
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.isNotEmpty();
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0042 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:15:0x0044 A[LOOP:0: B:5:0x000f->B:15:0x0044, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:19:0x0047 A[EDGE_INSN: B:19:0x0047->B:16:0x0047 BREAK  A[LOOP:0: B:5:0x000f->B:15:0x0044], SYNTHETIC] */
        public final void notifyInvalidatedScopes() {
            MutableScatterSet<Object> mutableScatterSet = this.invalidated;
            Function1<Object, Unit> function1 = this.onChanged;
            Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
            long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
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
                                function1.invoke(objArr[(i << 3) + i3]);
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
            mutableScatterSet.clear();
        }

        public final void observe(Object scope, Function1<Object, Unit> readObserver, Function0<Unit> block) {
            Snapshot transparentObserverMutableSnapshot;
            Object obj = this.currentScope;
            MutableObjectIntMap mutableObjectIntMap = this.currentScopeReads;
            int i = this.currentToken;
            this.currentScope = scope;
            this.currentScopeReads = (MutableObjectIntMap) this.scopeToValues.get(scope);
            if (this.currentToken == -1) {
                this.currentToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            }
            DerivedStateObserver derivedStateObserver = getDerivedStateObserver();
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(derivedStateObserver);
                Snapshot.Companion companion = Snapshot.INSTANCE;
                if (readObserver == null) {
                    block.invoke();
                } else {
                    Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
                    if ((snapshot instanceof TransparentObserverMutableSnapshot) && ((TransparentObserverMutableSnapshot) snapshot).getThreadId() == Thread_jvmKt.currentThreadId()) {
                        Function1<Object, Unit> readObserver2 = ((TransparentObserverMutableSnapshot) snapshot).getReadObserver();
                        Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) snapshot).getWriteObserver$runtime();
                        try {
                            ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, readObserver2, false, 4, null));
                            ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(null, writeObserver$runtime));
                            block.invoke();
                            InlineMarker.finallyStart(1);
                            ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(readObserver2);
                            ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(writeObserver$runtime);
                            InlineMarker.finallyEnd(1);
                        } catch (Throwable th) {
                            InlineMarker.finallyStart(1);
                            ((TransparentObserverMutableSnapshot) snapshot).setReadObserver$runtime(readObserver2);
                            ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver$runtime(writeObserver$runtime);
                            InlineMarker.finallyEnd(1);
                            throw th;
                        }
                    } else {
                        if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
                            transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, readObserver, null, true, false);
                        } else {
                            transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(readObserver);
                        }
                        try {
                            Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                            try {
                                block.invoke();
                                InlineMarker.finallyStart(1);
                                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                InlineMarker.finallyEnd(1);
                                InlineMarker.finallyStart(1);
                                transparentObserverMutableSnapshot.dispose();
                                InlineMarker.finallyEnd(1);
                            } catch (Throwable th2) {
                                InlineMarker.finallyStart(1);
                                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                InlineMarker.finallyEnd(1);
                                throw th2;
                            }
                        } catch (Throwable th3) {
                            InlineMarker.finallyStart(1);
                            transparentObserverMutableSnapshot.dispose();
                            InlineMarker.finallyEnd(1);
                            throw th3;
                        }
                    }
                }
                InlineMarker.finallyStart(1);
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                InlineMarker.finallyEnd(1);
                Object obj2 = this.currentScope;
                obj2.getClass();
                clearObsoleteStateReads(obj2);
                this.currentScope = obj;
                this.currentScopeReads = mutableObjectIntMap;
                this.currentToken = i;
            } catch (Throwable th4) {
                InlineMarker.finallyStart(1);
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                InlineMarker.finallyEnd(1);
                throw th4;
            }
        }

        /* JADX WARN: Code duplicated, block: B:100:0x0232 A[DONT_INVERT, PHI: r20
          0x0232: PHI (r20v38 boolean) = (r20v37 boolean), (r20v39 boolean) binds: [B:91:0x020a, B:99:0x0230] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:101:0x0234 A[Catch: all -> 0x00dd, LOOP:8: B:90:0x0200->B:101:0x0234, LOOP_END, TryCatch #1 {all -> 0x00dd, blocks: (B:23:0x0081, B:25:0x0087, B:27:0x008b, B:30:0x009d, B:32:0x00ad, B:34:0x00b7, B:36:0x00bd, B:38:0x00d8, B:41:0x00e1, B:43:0x00f1, B:45:0x00f7, B:47:0x00fb, B:50:0x010b, B:52:0x011b, B:54:0x0125, B:56:0x012b, B:58:0x013b, B:66:0x0162, B:70:0x0182, B:62:0x0146, B:63:0x014f, B:67:0x0167, B:76:0x01a9, B:78:0x01be, B:80:0x01d8, B:81:0x01dc, B:83:0x01ea, B:85:0x01f0, B:87:0x01f4, B:90:0x0200, B:92:0x020c, B:94:0x0218, B:96:0x021e, B:97:0x0228, B:101:0x0234, B:102:0x0237, B:103:0x023c, B:104:0x0240), top: B:293:0x0081 }] */
        /* JADX WARN: Code duplicated, block: B:130:0x02b7 A[DONT_INVERT, PHI: r20
          0x02b7: PHI (r20v30 boolean) = (r20v29 boolean), (r20v31 boolean) binds: [B:121:0x028e, B:129:0x02b5] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:131:0x02b9 A[LOOP:6: B:120:0x0284->B:131:0x02b9, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:245:0x04fa A[Catch: all -> 0x03a3, LOOP:18: B:232:0x04c1->B:245:0x04fa, LOOP_END, TryCatch #0 {all -> 0x03a3, blocks: (B:158:0x0349, B:160:0x034f, B:162:0x0353, B:165:0x0360, B:167:0x036d, B:169:0x0379, B:171:0x037f, B:173:0x039a, B:177:0x03a7, B:179:0x03b7, B:181:0x03bd, B:183:0x03c1, B:186:0x03d0, B:188:0x03e0, B:190:0x03ed, B:192:0x03f3, B:195:0x0406, B:201:0x041a, B:207:0x0433, B:211:0x0451, B:203:0x0423, B:208:0x0438, B:218:0x0478, B:220:0x0488, B:222:0x0498, B:223:0x049c, B:225:0x04aa, B:227:0x04b0, B:229:0x04b4, B:232:0x04c1, B:234:0x04cd, B:236:0x04db, B:238:0x04e1, B:239:0x04ea, B:245:0x04fa, B:247:0x04ff, B:248:0x0503, B:249:0x0507), top: B:291:0x0349 }] */
        /* JADX WARN: Code duplicated, block: B:251:0x050e  */
        /* JADX WARN: Code duplicated, block: B:304:0x0160 A[EDGE_INSN: B:304:0x0160->B:65:0x0160 BREAK  A[LOOP:4: B:50:0x010b->B:62:0x0146], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:309:0x02c1 A[EDGE_INSN: B:309:0x02c1->B:133:0x02c1 BREAK  A[LOOP:6: B:120:0x0284->B:131:0x02b9], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:314:0x023c A[EDGE_INSN: B:314:0x023c->B:103:0x023c BREAK  A[LOOP:8: B:90:0x0200->B:101:0x0234], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:336:0x04fd A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:61:0x0144 A[DONT_INVERT, PHI: r20
          0x0144: PHI (r20v49 boolean) = (r20v48 boolean), (r20v50 boolean) binds: [B:51:0x0119, B:60:0x0142] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:62:0x0146 A[Catch: all -> 0x00dd, LOOP:4: B:50:0x010b->B:62:0x0146, LOOP_END, TryCatch #1 {all -> 0x00dd, blocks: (B:23:0x0081, B:25:0x0087, B:27:0x008b, B:30:0x009d, B:32:0x00ad, B:34:0x00b7, B:36:0x00bd, B:38:0x00d8, B:41:0x00e1, B:43:0x00f1, B:45:0x00f7, B:47:0x00fb, B:50:0x010b, B:52:0x011b, B:54:0x0125, B:56:0x012b, B:58:0x013b, B:66:0x0162, B:70:0x0182, B:62:0x0146, B:63:0x014f, B:67:0x0167, B:76:0x01a9, B:78:0x01be, B:80:0x01d8, B:81:0x01dc, B:83:0x01ea, B:85:0x01f0, B:87:0x01f4, B:90:0x0200, B:92:0x020c, B:94:0x0218, B:96:0x021e, B:97:0x0228, B:101:0x0234, B:102:0x0237, B:103:0x023c, B:104:0x0240), top: B:293:0x0081 }] */
        /* JADX WARN: Code duplicated, block: B:64:0x015a  */
        public final boolean recordInvalidation(Set<? extends Object> changes) {
            boolean z;
            Iterator it;
            MutableScatterMap<Object, Object> mutableScatterMap;
            int i;
            Object[] objArr;
            long[] jArr;
            Iterator it2;
            MutableScatterMap<Object, Object> mutableScatterMap2;
            int i2;
            Object[] objArr2;
            long j;
            long[] jArr2;
            boolean z2;
            long[] jArr3;
            long[] jArr4;
            Object[] objArr3;
            int i3;
            long[] jArr5;
            Object[] objArr4;
            int i4;
            int i5;
            long j2;
            int i6;
            int i7;
            Object obj;
            long[] jArr6;
            long[] jArr7;
            Object obj2;
            int i8;
            int i9;
            long j3;
            int i10;
            boolean z3;
            MutableScatterMap<Object, Object> mutableScatterMap3 = this.dependencyToDerivedStates;
            HashMap<DerivedState<?>, Object> map = this.recordedDerivedStateValues;
            MutableScatterMap<Object, Object> mutableScatterMap4 = this.valueToScopes;
            MutableScatterSet<Object> mutableScatterSet = this.invalidated;
            int i11 = 8;
            if (changes instanceof ScatterSetWrapper) {
                ScatterSet set$runtime = ((ScatterSetWrapper) changes).getSet$runtime();
                Object[] objArr5 = set$runtime.elements;
                long[] jArr8 = set$runtime.metadata;
                int length = jArr8.length - 2;
                if (length >= 0) {
                    int i12 = 0;
                    z = false;
                    while (true) {
                        long j4 = jArr8[i12];
                        if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i13 = 8 - ((~(i12 - length)) >>> 31);
                            int i14 = 0;
                            while (i14 < i13) {
                                if ((j4 & 255) < 128) {
                                    Object obj3 = objArr5[(i12 << 3) + i14];
                                    int i15 = i11;
                                    if (!(obj3 instanceof StateObjectImpl) || ((StateObjectImpl) obj3).m2584isReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(2))) {
                                        if (this.readingDerivedStates || !ScopeMap.m2491containsimpl(mutableScatterMap3, obj3)) {
                                            jArr5 = jArr8;
                                            objArr4 = objArr5;
                                            obj = obj3;
                                            i4 = length;
                                            i5 = i12;
                                            j2 = j4;
                                            i6 = i14;
                                        } else {
                                            this.readingDerivedStates = true;
                                            try {
                                                Object obj4 = mutableScatterMap3.get(obj3);
                                                if (obj4 != null) {
                                                    if (obj4 instanceof MutableScatterSet) {
                                                        MutableScatterSet mutableScatterSet2 = (MutableScatterSet) obj4;
                                                        Object[] objArr6 = ((ScatterSet) mutableScatterSet2).elements;
                                                        long[] jArr9 = ((ScatterSet) mutableScatterSet2).metadata;
                                                        jArr5 = jArr8;
                                                        int length2 = jArr9.length - 2;
                                                        objArr4 = objArr5;
                                                        if (length2 >= 0) {
                                                            j2 = j4;
                                                            int i16 = 0;
                                                            while (true) {
                                                                long j5 = jArr9[i16];
                                                                i6 = i14;
                                                                Object[] objArr7 = objArr6;
                                                                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                    int i17 = 8 - ((~(i16 - length2)) >>> 31);
                                                                    int i18 = 0;
                                                                    while (i18 < i17) {
                                                                        if ((j5 & 255) < 128) {
                                                                            jArr7 = jArr9;
                                                                            DerivedState<?> derivedState = (DerivedState) objArr7[(i16 << 3) + i18];
                                                                            derivedState.getClass();
                                                                            j3 = j5;
                                                                            Object obj5 = map.get(derivedState);
                                                                            SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
                                                                            if (policy == null) {
                                                                                policy = SnapshotStateKt.structuralEqualityPolicy();
                                                                            }
                                                                            i10 = i18;
                                                                            if (policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), obj5)) {
                                                                                obj2 = obj3;
                                                                                i8 = length;
                                                                                i9 = i12;
                                                                                this.statesToReread.add(derivedState);
                                                                            } else {
                                                                                Object obj6 = mutableScatterMap4.get(derivedState);
                                                                                if (obj6 == null) {
                                                                                    obj2 = obj3;
                                                                                    i8 = length;
                                                                                    i9 = i12;
                                                                                    z3 = z;
                                                                                } else if (obj6 instanceof MutableScatterSet) {
                                                                                    MutableScatterSet mutableScatterSet3 = (MutableScatterSet) obj6;
                                                                                    Object[] objArr8 = ((ScatterSet) mutableScatterSet3).elements;
                                                                                    long[] jArr10 = ((ScatterSet) mutableScatterSet3).metadata;
                                                                                    int length3 = jArr10.length - 2;
                                                                                    if (length3 >= 0) {
                                                                                        i8 = length;
                                                                                        i9 = i12;
                                                                                        int i19 = 0;
                                                                                        while (true) {
                                                                                            long j6 = jArr10[i19];
                                                                                            long[] jArr11 = jArr10;
                                                                                            obj2 = obj3;
                                                                                            if ((((~j6) << 7) & j6 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                                                                if (i19 != length3) {
                                                                                                    break;
                                                                                                    break;
                                                                                                }
                                                                                                i19++;
                                                                                                obj3 = obj2;
                                                                                                jArr10 = jArr11;
                                                                                                i15 = 8;
                                                                                            } else {
                                                                                                int i20 = 8 - ((~(i19 - length3)) >>> 31);
                                                                                                for (int i21 = 0; i21 < i20; i21++) {
                                                                                                    if ((j6 & 255) < 128) {
                                                                                                        mutableScatterSet.add(objArr8[(i19 << 3) + i21]);
                                                                                                        z = true;
                                                                                                    }
                                                                                                    j6 >>= i15;
                                                                                                }
                                                                                                if (i20 != i15) {
                                                                                                    break;
                                                                                                }
                                                                                                if (i19 != length3) {
                                                                                                    break;
                                                                                                }
                                                                                                i19++;
                                                                                                obj3 = obj2;
                                                                                                jArr10 = jArr11;
                                                                                                i15 = 8;
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        obj2 = obj3;
                                                                                        i8 = length;
                                                                                        i9 = i12;
                                                                                    }
                                                                                    z3 = z;
                                                                                } else {
                                                                                    obj2 = obj3;
                                                                                    i8 = length;
                                                                                    i9 = i12;
                                                                                    mutableScatterSet.add(obj6);
                                                                                    z3 = true;
                                                                                }
                                                                                Unit unit = Unit.INSTANCE;
                                                                                z = z3;
                                                                            }
                                                                        } else {
                                                                            jArr7 = jArr9;
                                                                            obj2 = obj3;
                                                                            i8 = length;
                                                                            i9 = i12;
                                                                            j3 = j5;
                                                                            i10 = i18;
                                                                        }
                                                                        j5 = j3 >> 8;
                                                                        i18 = i10 + 1;
                                                                        i15 = 8;
                                                                        length = i8;
                                                                        jArr9 = jArr7;
                                                                        i12 = i9;
                                                                        obj3 = obj2;
                                                                    }
                                                                    jArr6 = jArr9;
                                                                    obj = obj3;
                                                                    i4 = length;
                                                                    i5 = i12;
                                                                    if (i17 != i15) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    jArr6 = jArr9;
                                                                    obj = obj3;
                                                                    i4 = length;
                                                                    i5 = i12;
                                                                }
                                                                if (i16 == length2) {
                                                                    break;
                                                                }
                                                                i16++;
                                                                i14 = i6;
                                                                objArr6 = objArr7;
                                                                length = i4;
                                                                jArr9 = jArr6;
                                                                i12 = i5;
                                                                obj3 = obj;
                                                                i15 = 8;
                                                            }
                                                        }
                                                    } else {
                                                        jArr5 = jArr8;
                                                        objArr4 = objArr5;
                                                        obj = obj3;
                                                        i4 = length;
                                                        i5 = i12;
                                                        j2 = j4;
                                                        i6 = i14;
                                                        DerivedState<?> derivedState2 = (DerivedState) obj4;
                                                        Object obj7 = map.get(derivedState2);
                                                        SnapshotMutationPolicy<?> policy2 = derivedState2.getPolicy();
                                                        if (policy2 == null) {
                                                            policy2 = SnapshotStateKt.structuralEqualityPolicy();
                                                        }
                                                        if (policy2.equivalent(derivedState2.getCurrentRecord().getCurrentValue(), obj7)) {
                                                            this.statesToReread.add(derivedState2);
                                                        } else {
                                                            Object obj8 = mutableScatterMap4.get(derivedState2);
                                                            if (obj8 != null) {
                                                                if (obj8 instanceof MutableScatterSet) {
                                                                    MutableScatterSet mutableScatterSet4 = (MutableScatterSet) obj8;
                                                                    Object[] objArr9 = ((ScatterSet) mutableScatterSet4).elements;
                                                                    long[] jArr12 = ((ScatterSet) mutableScatterSet4).metadata;
                                                                    int length4 = jArr12.length - 2;
                                                                    if (length4 >= 0) {
                                                                        int i22 = 0;
                                                                        while (true) {
                                                                            long j7 = jArr12[i22];
                                                                            if ((((~j7) << 7) & j7 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                                                if (i22 != length4) {
                                                                                    break;
                                                                                    break;
                                                                                }
                                                                                i22++;
                                                                            } else {
                                                                                int i23 = 8 - ((~(i22 - length4)) >>> 31);
                                                                                for (int i24 = 0; i24 < i23; i24++) {
                                                                                    if ((j7 & 255) < 128) {
                                                                                        mutableScatterSet.add(objArr9[(i22 << 3) + i24]);
                                                                                        z = true;
                                                                                    }
                                                                                    j7 >>= 8;
                                                                                }
                                                                                if (i23 != 8) {
                                                                                    break;
                                                                                }
                                                                                if (i22 != length4) {
                                                                                    break;
                                                                                }
                                                                                i22++;
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    mutableScatterSet.add(obj8);
                                                                    z = true;
                                                                }
                                                            }
                                                            Unit unit2 = Unit.INSTANCE;
                                                        }
                                                    }
                                                    this.readingDerivedStates = false;
                                                } else {
                                                    jArr5 = jArr8;
                                                    objArr4 = objArr5;
                                                }
                                                obj = obj3;
                                                i4 = length;
                                                i5 = i12;
                                                j2 = j4;
                                                i6 = i14;
                                                this.readingDerivedStates = false;
                                            } catch (Throwable th) {
                                                this.readingDerivedStates = false;
                                                throw th;
                                            }
                                        }
                                        Object obj9 = mutableScatterMap4.get(obj);
                                        if (obj9 != null) {
                                            if (obj9 instanceof MutableScatterSet) {
                                                MutableScatterSet mutableScatterSet5 = (MutableScatterSet) obj9;
                                                Object[] objArr10 = ((ScatterSet) mutableScatterSet5).elements;
                                                long[] jArr13 = ((ScatterSet) mutableScatterSet5).metadata;
                                                int length5 = jArr13.length - 2;
                                                if (length5 >= 0) {
                                                    int i25 = 0;
                                                    while (true) {
                                                        long j8 = jArr13[i25];
                                                        if ((((~j8) << 7) & j8 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                            if (i25 != length5) {
                                                                break;
                                                                break;
                                                            }
                                                            i25++;
                                                        } else {
                                                            int i26 = 8 - ((~(i25 - length5)) >>> 31);
                                                            long j9 = j8;
                                                            for (int i27 = 0; i27 < i26; i27++) {
                                                                if ((j9 & 255) < 128) {
                                                                    mutableScatterSet.add(objArr10[(i25 << 3) + i27]);
                                                                    z = true;
                                                                }
                                                                j9 >>= 8;
                                                            }
                                                            if (i26 != 8) {
                                                                break;
                                                            }
                                                            if (i25 != length5) {
                                                                break;
                                                            }
                                                            i25++;
                                                        }
                                                    }
                                                }
                                            } else {
                                                mutableScatterSet.add(obj9);
                                                z = true;
                                            }
                                        }
                                    } else {
                                        jArr5 = jArr8;
                                        objArr4 = objArr5;
                                        i4 = length;
                                        i5 = i12;
                                        j2 = j4;
                                        i6 = i14;
                                    }
                                    i7 = 8;
                                } else {
                                    jArr5 = jArr8;
                                    objArr4 = objArr5;
                                    i4 = length;
                                    i5 = i12;
                                    j2 = j4;
                                    i6 = i14;
                                    i7 = i11;
                                }
                                j4 = j2 >> i7;
                                i14 = i6 + 1;
                                jArr8 = jArr5;
                                i11 = i7;
                                objArr5 = objArr4;
                                length = i4;
                                i12 = i5;
                            }
                            jArr4 = jArr8;
                            objArr3 = objArr5;
                            int i28 = length;
                            int i29 = i12;
                            if (i13 != i11) {
                                break;
                            }
                            length = i28;
                            i3 = i29;
                        } else {
                            jArr4 = jArr8;
                            objArr3 = objArr5;
                            i3 = i12;
                        }
                        if (i3 == length) {
                            break;
                        }
                        i12 = i3 + 1;
                        jArr8 = jArr4;
                        objArr5 = objArr3;
                        i11 = 8;
                    }
                } else {
                    z = false;
                }
            } else {
                Iterator it3 = changes.iterator();
                boolean z4 = false;
                while (it3.hasNext()) {
                    Object next = it3.next();
                    if (!(next instanceof StateObjectImpl) || ((StateObjectImpl) next).m2584isReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(2))) {
                        if (this.readingDerivedStates || !ScopeMap.m2491containsimpl(mutableScatterMap3, next)) {
                            it = it3;
                            mutableScatterMap = mutableScatterMap3;
                            i = 0;
                        } else {
                            this.readingDerivedStates = true;
                            try {
                                Object obj10 = mutableScatterMap3.get(next);
                                if (obj10 == null) {
                                    it = it3;
                                    mutableScatterMap = mutableScatterMap3;
                                } else if (obj10 instanceof MutableScatterSet) {
                                    MutableScatterSet mutableScatterSet6 = (MutableScatterSet) obj10;
                                    Object[] objArr11 = ((ScatterSet) mutableScatterSet6).elements;
                                    long[] jArr14 = ((ScatterSet) mutableScatterSet6).metadata;
                                    int length6 = jArr14.length - 2;
                                    if (length6 >= 0) {
                                        boolean z5 = z4;
                                        int i30 = 0;
                                        while (true) {
                                            long j10 = jArr14[i30];
                                            long[] jArr15 = jArr14;
                                            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                int i31 = 8 - ((~(i30 - length6)) >>> 31);
                                                int i32 = 0;
                                                while (i32 < i31) {
                                                    if ((j10 & 255) < 128) {
                                                        it2 = it3;
                                                        DerivedState<?> derivedState3 = (DerivedState) objArr11[(i30 << 3) + i32];
                                                        derivedState3.getClass();
                                                        mutableScatterMap2 = mutableScatterMap3;
                                                        Object obj11 = map.get(derivedState3);
                                                        SnapshotMutationPolicy<?> policy3 = derivedState3.getPolicy();
                                                        if (policy3 == null) {
                                                            policy3 = SnapshotStateKt.structuralEqualityPolicy();
                                                        }
                                                        i2 = i32;
                                                        objArr2 = objArr11;
                                                        if (policy3.equivalent(derivedState3.getCurrentRecord().getCurrentValue(), obj11)) {
                                                            j = j10;
                                                            jArr2 = jArr15;
                                                            this.statesToReread.add(derivedState3);
                                                        } else {
                                                            Object obj12 = mutableScatterMap4.get(derivedState3);
                                                            if (obj12 != null) {
                                                                if (obj12 instanceof MutableScatterSet) {
                                                                    MutableScatterSet mutableScatterSet7 = (MutableScatterSet) obj12;
                                                                    Object[] objArr12 = ((ScatterSet) mutableScatterSet7).elements;
                                                                    long[] jArr16 = ((ScatterSet) mutableScatterSet7).metadata;
                                                                    int length7 = jArr16.length - 2;
                                                                    j = j10;
                                                                    if (length7 >= 0) {
                                                                        int i33 = 0;
                                                                        boolean z6 = z5;
                                                                        while (true) {
                                                                            long j11 = jArr16[i33];
                                                                            z2 = z6;
                                                                            jArr2 = jArr15;
                                                                            if ((((~j11) << 7) & j11 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                                int i34 = 8 - ((~(i33 - length7)) >>> 31);
                                                                                long j12 = j11;
                                                                                int i35 = 0;
                                                                                while (i35 < i34) {
                                                                                    if ((j12 & 255) < 128) {
                                                                                        mutableScatterSet.add(objArr12[(i33 << 3) + i35]);
                                                                                        z2 = true;
                                                                                    }
                                                                                    j12 >>= 8;
                                                                                    i35++;
                                                                                    jArr16 = jArr16;
                                                                                }
                                                                                jArr3 = jArr16;
                                                                                if (i34 != 8) {
                                                                                    break;
                                                                                }
                                                                            } else {
                                                                                jArr3 = jArr16;
                                                                            }
                                                                            z6 = z2;
                                                                            if (i33 != length7) {
                                                                                i33++;
                                                                                jArr15 = jArr2;
                                                                                jArr16 = jArr3;
                                                                            } else {
                                                                                z5 = z6;
                                                                            }
                                                                        }
                                                                    }
                                                                    z2 = z5;
                                                                    break;
                                                                } else {
                                                                    j = j10;
                                                                    jArr2 = jArr15;
                                                                    mutableScatterSet.add(obj12);
                                                                    z2 = true;
                                                                }
                                                                Unit unit3 = Unit.INSTANCE;
                                                                z5 = z2;
                                                            } else {
                                                                j = j10;
                                                            }
                                                            jArr2 = jArr15;
                                                            z2 = z5;
                                                            Unit unit4 = Unit.INSTANCE;
                                                            z5 = z2;
                                                        }
                                                    } else {
                                                        it2 = it3;
                                                        mutableScatterMap2 = mutableScatterMap3;
                                                        i2 = i32;
                                                        objArr2 = objArr11;
                                                        j = j10;
                                                        jArr2 = jArr15;
                                                    }
                                                    j10 = j >> 8;
                                                    i32 = i2 + 1;
                                                    it3 = it2;
                                                    mutableScatterMap3 = mutableScatterMap2;
                                                    jArr15 = jArr2;
                                                    objArr11 = objArr2;
                                                }
                                                it = it3;
                                                mutableScatterMap = mutableScatterMap3;
                                                objArr = objArr11;
                                                jArr = jArr15;
                                                if (i31 != 8) {
                                                    break;
                                                }
                                            } else {
                                                it = it3;
                                                mutableScatterMap = mutableScatterMap3;
                                                objArr = objArr11;
                                                jArr = jArr15;
                                            }
                                            if (i30 == length6) {
                                                break;
                                            }
                                            i30++;
                                            it3 = it;
                                            mutableScatterMap3 = mutableScatterMap;
                                            jArr14 = jArr;
                                            objArr11 = objArr;
                                        }
                                        z4 = z5;
                                    } else {
                                        it = it3;
                                        mutableScatterMap = mutableScatterMap3;
                                    }
                                } else {
                                    it = it3;
                                    mutableScatterMap = mutableScatterMap3;
                                    DerivedState<?> derivedState4 = (DerivedState) obj10;
                                    Object obj13 = map.get(derivedState4);
                                    SnapshotMutationPolicy<?> policy4 = derivedState4.getPolicy();
                                    if (policy4 == null) {
                                        policy4 = SnapshotStateKt.structuralEqualityPolicy();
                                    }
                                    if (policy4.equivalent(derivedState4.getCurrentRecord().getCurrentValue(), obj13)) {
                                        this.statesToReread.add(derivedState4);
                                    } else {
                                        Object obj14 = mutableScatterMap4.get(derivedState4);
                                        if (obj14 != null) {
                                            if (obj14 instanceof MutableScatterSet) {
                                                MutableScatterSet mutableScatterSet8 = (MutableScatterSet) obj14;
                                                Object[] objArr13 = ((ScatterSet) mutableScatterSet8).elements;
                                                long[] jArr17 = ((ScatterSet) mutableScatterSet8).metadata;
                                                int length8 = jArr17.length - 2;
                                                if (length8 >= 0) {
                                                    boolean z7 = z4;
                                                    int i36 = 0;
                                                    while (true) {
                                                        long j13 = jArr17[i36];
                                                        if ((((~j13) << 7) & j13 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                            if (i36 != length8) {
                                                                z4 = z7;
                                                                break;
                                                            }
                                                            i36++;
                                                        } else {
                                                            int i37 = 8 - ((~(i36 - length8)) >>> 31);
                                                            long j14 = j13;
                                                            boolean z8 = z7;
                                                            for (int i38 = 0; i38 < i37; i38++) {
                                                                if ((j14 & 255) < 128) {
                                                                    mutableScatterSet.add(objArr13[(i36 << 3) + i38]);
                                                                    z8 = true;
                                                                }
                                                                j14 >>= 8;
                                                            }
                                                            if (i37 != 8) {
                                                                z4 = z8;
                                                                break;
                                                            }
                                                            z7 = z8;
                                                            if (i36 != length8) {
                                                                z4 = z7;
                                                                break;
                                                            }
                                                            i36++;
                                                        }
                                                    }
                                                }
                                            } else {
                                                mutableScatterSet.add(obj14);
                                                z4 = true;
                                            }
                                        }
                                        Unit unit5 = Unit.INSTANCE;
                                    }
                                }
                                i = 0;
                                this.readingDerivedStates = false;
                            } catch (Throwable th2) {
                                this.readingDerivedStates = false;
                                throw th2;
                            }
                        }
                        Object obj15 = mutableScatterMap4.get(next);
                        if (obj15 != null) {
                            if (obj15 instanceof MutableScatterSet) {
                                MutableScatterSet mutableScatterSet9 = (MutableScatterSet) obj15;
                                Object[] objArr14 = ((ScatterSet) mutableScatterSet9).elements;
                                long[] jArr18 = ((ScatterSet) mutableScatterSet9).metadata;
                                int length9 = jArr18.length - 2;
                                if (length9 >= 0) {
                                    boolean z9 = z4;
                                    int i39 = i;
                                    while (true) {
                                        long j15 = jArr18[i39];
                                        if ((((~j15) << 7) & j15 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i40 = 8 - ((~(i39 - length9)) >>> 31);
                                            long j16 = j15;
                                            boolean z10 = z9;
                                            for (int i41 = i; i41 < i40; i41++) {
                                                if ((j16 & 255) < 128) {
                                                    mutableScatterSet.add(objArr14[(i39 << 3) + i41]);
                                                    z10 = true;
                                                }
                                                j16 >>= 8;
                                            }
                                            if (i40 != 8) {
                                                z4 = z10;
                                                break;
                                            }
                                            z9 = z10;
                                        }
                                        if (i39 == length9) {
                                            z4 = z9;
                                            break;
                                        }
                                        i39++;
                                    }
                                }
                            } else {
                                mutableScatterSet.add(obj15);
                                z4 = true;
                            }
                        }
                        it3 = it;
                        mutableScatterMap3 = mutableScatterMap;
                    } else {
                        it = it3;
                        mutableScatterMap = mutableScatterMap3;
                    }
                    it3 = it;
                    mutableScatterMap3 = mutableScatterMap;
                }
                z = z4;
            }
            if (!this.readingDerivedStates && this.statesToReread.getSize() != 0) {
                MutableVector<DerivedState<?>> mutableVector = this.statesToReread;
                DerivedState<?>[] derivedStateArr = mutableVector.content;
                int size = mutableVector.getSize();
                for (int i42 = 0; i42 < size; i42++) {
                    rereadDerivedState(derivedStateArr[i42]);
                }
                this.statesToReread.clear();
            }
            return z;
        }

        /* JADX WARN: Code duplicated, block: B:27:0x009d A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:28:0x009f A[LOOP:2: B:16:0x0066->B:28:0x009f, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:29:0x00a8  */
        /* JADX WARN: Code duplicated, block: B:49:0x00ac A[EDGE_INSN: B:49:0x00ac->B:30:0x00ac BREAK  A[LOOP:2: B:16:0x0066->B:28:0x009f], SYNTHETIC] */
        public final void removeScopeIf(Function1<Object, Boolean> predicate) {
            long[] jArr;
            long[] jArr2;
            long j;
            char c;
            long j2;
            int i;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            long[] jArr3 = ((ScatterMap) mutableScatterMap).metadata;
            int length = jArr3.length - 2;
            if (length < 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j3 = jArr3[i2];
                char c2 = 7;
                long j4 = -9187201950435737472L;
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8;
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    int i5 = 0;
                    while (i5 < i4) {
                        if ((j3 & 255) < 128) {
                            int i6 = (i2 << 3) + i5;
                            c = c2;
                            Object obj = ((ScatterMap) mutableScatterMap).keys[i6];
                            j2 = j4;
                            MutableObjectIntMap mutableObjectIntMap = (MutableObjectIntMap) ((ScatterMap) mutableScatterMap).values[i6];
                            Boolean bool = (Boolean) predicate.invoke(obj);
                            if (bool.booleanValue()) {
                                Object[] objArr = ((ObjectIntMap) mutableObjectIntMap).keys;
                                int[] iArr = ((ObjectIntMap) mutableObjectIntMap).values;
                                long[] jArr4 = ((ObjectIntMap) mutableObjectIntMap).metadata;
                                int i7 = i3;
                                int length2 = jArr4.length - 2;
                                if (length2 >= 0) {
                                    jArr2 = jArr3;
                                    j = j3;
                                    int i8 = 0;
                                    while (true) {
                                        long j5 = jArr4[i8];
                                        long[] jArr5 = jArr4;
                                        if ((((~j5) << c) & j5 & j2) != j2) {
                                            int i9 = 8 - ((~(i8 - length2)) >>> 31);
                                            for (int i10 = 0; i10 < i9; i10++) {
                                                if ((j5 & 255) < 128) {
                                                    int i11 = (i8 << 3) + i10;
                                                    Object obj2 = objArr[i11];
                                                    int i12 = iArr[i11];
                                                    removeObservation(obj, obj2);
                                                }
                                                j5 >>= i7;
                                            }
                                            if (i9 != i7) {
                                                break;
                                            }
                                            if (i8 != length2) {
                                                break;
                                            }
                                            i8++;
                                            jArr4 = jArr5;
                                            i7 = 8;
                                        } else if (i8 != length2) {
                                            break;
                                            break;
                                        } else {
                                            i8++;
                                            jArr4 = jArr5;
                                            i7 = 8;
                                        }
                                    }
                                } else {
                                    jArr2 = jArr3;
                                    j = j3;
                                }
                            } else {
                                jArr2 = jArr3;
                                j = j3;
                            }
                            if (bool.booleanValue()) {
                                mutableScatterMap.removeValueAt(i6);
                            }
                            i = 8;
                        } else {
                            jArr2 = jArr3;
                            j = j3;
                            c = c2;
                            j2 = j4;
                            i = i3;
                        }
                        i5++;
                        i3 = i;
                        j3 = j >> i;
                        c2 = c;
                        j4 = j2;
                        jArr3 = jArr2;
                    }
                    jArr = jArr3;
                    if (i4 != i3) {
                        return;
                    }
                } else {
                    jArr = jArr3;
                }
                if (i2 == length) {
                    return;
                }
                i2++;
                jArr3 = jArr;
            }
        }

        public final void rereadDerivedState(DerivedState<?> derivedState) {
            long[] jArr;
            MutableObjectIntMap<Object> mutableObjectIntMap;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            int iHashCode = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            Object obj = this.valueToScopes.get(derivedState);
            if (obj == null) {
                return;
            }
            if (!(obj instanceof MutableScatterSet)) {
                MutableObjectIntMap<Object> mutableObjectIntMap2 = (MutableObjectIntMap) mutableScatterMap.get(obj);
                if (mutableObjectIntMap2 == null) {
                    mutableObjectIntMap2 = new MutableObjectIntMap<>(0, 1, (DefaultConstructorMarker) null);
                    mutableScatterMap.set(obj, mutableObjectIntMap2);
                    Unit unit = Unit.INSTANCE;
                }
                recordRead(derivedState, iHashCode, obj, mutableObjectIntMap2);
                return;
            }
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
            long[] jArr2 = ((ScatterSet) mutableScatterSet).metadata;
            int length = jArr2.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr2[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8;
                    int i3 = 8 - ((~(i - length)) >>> 31);
                    int i4 = 0;
                    while (i4 < i3) {
                        if ((j & 255) < 128) {
                            Object obj2 = objArr[(i << 3) + i4];
                            MutableObjectIntMap<Object> mutableObjectIntMap3 = (MutableObjectIntMap) mutableScatterMap.get(obj2);
                            if (mutableObjectIntMap3 == null) {
                                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, (DefaultConstructorMarker) null);
                                mutableScatterMap.set(obj2, mutableObjectIntMap);
                                Unit unit2 = Unit.INSTANCE;
                            } else {
                                mutableObjectIntMap = mutableObjectIntMap3;
                            }
                            recordRead(derivedState, iHashCode, obj2, mutableObjectIntMap);
                        }
                        j >>= i2;
                        i4++;
                        i2 = i2;
                        jArr2 = jArr2;
                    }
                    jArr = jArr2;
                    if (i3 != i2) {
                        return;
                    }
                } else {
                    jArr = jArr2;
                }
                if (i == length) {
                    return;
                }
                i++;
                jArr2 = jArr;
            }
        }

        public final void setReadingDerivedStates(boolean z) {
            this.readingDerivedStates = z;
        }

        public final void recordRead(Object value) {
            Object obj = this.currentScope;
            obj.getClass();
            int i = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, (DefaultConstructorMarker) null);
                this.currentScopeReads = mutableObjectIntMap;
                this.scopeToValues.set(obj, mutableObjectIntMap);
                Unit unit = Unit.INSTANCE;
            }
            recordRead(value, i, obj, mutableObjectIntMap);
        }
    }
}
