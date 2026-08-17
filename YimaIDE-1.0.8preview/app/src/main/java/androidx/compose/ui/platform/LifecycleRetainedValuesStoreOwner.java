package androidx.compose.ui.platform;

import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.CancellationHandle;
import androidx.compose.runtime.retain.RetainedValuesStore;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.lifecycle.ViewModel;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\fH\u0014R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "scopes", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry;", "getOrCreateRetainedValuesStoreEntry", "viewId", "", "onCleared", "", "RetainedValuesStoreEntry", "FrameEndScheduler", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LifecycleRetainedValuesStoreOwner extends ViewModel {
    public static final int $stable = 8;
    private final MutableIntObjectMap<MutableObjectList<RetainedValuesStoreEntry>> scopes = IntObjectMapKt.mutableIntObjectMapOf();

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "Lkotlin/Function0;", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface FrameEndScheduler {
        CancellationHandle scheduleFrameEndCallback(Function0<Unit> action);
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0015J\u0006\u0010\u001a\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry;", "", "<init>", "()V", "_retainedValuesStore", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStore;", "retainedValuesStore", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "getRetainedValuesStore", "()Landroidx/compose/runtime/retain/RetainedValuesStore;", "isInUse", "", "()Z", "setInUse", "(Z)V", "value", "Landroidx/compose/runtime/CancellationHandle;", "endRetainCancellationHandle", "setEndRetainCancellationHandle", "(Landroidx/compose/runtime/CancellationHandle;)V", "startRetainingExitedValues", "", "stopRetainingExitedValues", "frameEndScheduler", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "onCleared", "release", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class RetainedValuesStoreEntry {
        public static final int $stable = 8;
        private final LifecycleRetainedValuesStore _retainedValuesStore;
        private CancellationHandle endRetainCancellationHandle;
        private boolean isInUse;
        private final RetainedValuesStore retainedValuesStore;

        public RetainedValuesStoreEntry() {
            LifecycleRetainedValuesStore lifecycleRetainedValuesStore = new LifecycleRetainedValuesStore(null, 1, null);
            this._retainedValuesStore = lifecycleRetainedValuesStore;
            this.retainedValuesStore = lifecycleRetainedValuesStore;
        }

        private final void setEndRetainCancellationHandle(CancellationHandle cancellationHandle) {
            CancellationHandle cancellationHandle2 = this.endRetainCancellationHandle;
            if (cancellationHandle2 != null) {
                cancellationHandle2.cancel();
            }
            this.endRetainCancellationHandle = cancellationHandle;
        }

        public final RetainedValuesStore getRetainedValuesStore() {
            return this.retainedValuesStore;
        }

        /* JADX INFO: renamed from: isInUse, reason: from getter */
        public final boolean getIsInUse() {
            return this.isInUse;
        }

        public final void onCleared() {
            setEndRetainCancellationHandle(null);
            this._retainedValuesStore.dispose();
        }

        public final void release() {
            this.isInUse = false;
        }

        public final void setInUse(boolean z) {
            this.isInUse = z;
        }

        public final void startRetainingExitedValues() {
            if (this._retainedValuesStore.isRetainingExitedValues()) {
                setEndRetainCancellationHandle(null);
            } else {
                this._retainedValuesStore.startLifecycleTransition();
            }
        }

        public final void stopRetainingExitedValues(FrameEndScheduler frameEndScheduler) {
            CancellationHandle cancellationHandleScheduleFrameEndCallback;
            if (this._retainedValuesStore.isRetainingExitedValues()) {
                try {
                    cancellationHandleScheduleFrameEndCallback = frameEndScheduler.scheduleFrameEndCallback(new Function0<Unit>() { // from class: androidx.compose.ui.platform.LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry$stopRetainingExitedValues$1
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m5184invoke() {
                            this.this$0._retainedValuesStore.endLifecycleTransition();
                        }

                        public /* bridge */ /* synthetic */ Object invoke() {
                            m5184invoke();
                            return Unit.INSTANCE;
                        }
                    });
                } catch (CancellationException unused) {
                    this._retainedValuesStore.endLifecycleTransition();
                    cancellationHandleScheduleFrameEndCallback = null;
                }
                setEndRetainCancellationHandle(cancellationHandleScheduleFrameEndCallback);
            }
        }
    }

    public final RetainedValuesStoreEntry getOrCreateRetainedValuesStoreEntry(int viewId) {
        Object obj;
        MutableIntObjectMap<MutableObjectList<RetainedValuesStoreEntry>> mutableIntObjectMap = this.scopes;
        Object mutableObjectList = mutableIntObjectMap.get(viewId);
        if (mutableObjectList == null) {
            mutableObjectList = new MutableObjectList(1);
            mutableIntObjectMap.set(viewId, mutableObjectList);
        }
        MutableObjectList mutableObjectList2 = (MutableObjectList) mutableObjectList;
        Object[] objArr = ((ObjectList) mutableObjectList2).content;
        int i = ((ObjectList) mutableObjectList2)._size;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                obj = null;
                break;
            }
            obj = objArr[i2];
            if (!((RetainedValuesStoreEntry) obj).getIsInUse()) {
                break;
            }
            i2++;
        }
        RetainedValuesStoreEntry retainedValuesStoreEntry = (RetainedValuesStoreEntry) obj;
        if (retainedValuesStoreEntry == null) {
            retainedValuesStoreEntry = new RetainedValuesStoreEntry();
            mutableObjectList2.add(retainedValuesStoreEntry);
        }
        retainedValuesStoreEntry.setInUse(true);
        return retainedValuesStoreEntry;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        MutableIntObjectMap<MutableObjectList<RetainedValuesStoreEntry>> mutableIntObjectMap = this.scopes;
        int[] iArr = ((IntObjectMap) mutableIntObjectMap).keys;
        Object[] objArr = ((IntObjectMap) mutableIntObjectMap).values;
        long[] jArr = ((IntObjectMap) mutableIntObjectMap).metadata;
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
                        int i5 = iArr[i4];
                        MutableObjectList mutableObjectList = (MutableObjectList) objArr[i4];
                        Object[] objArr2 = ((ObjectList) mutableObjectList).content;
                        int i6 = ((ObjectList) mutableObjectList)._size;
                        for (int i7 = 0; i7 < i6; i7++) {
                            ((RetainedValuesStoreEntry) objArr2[i7]).onCleared();
                        }
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
}
