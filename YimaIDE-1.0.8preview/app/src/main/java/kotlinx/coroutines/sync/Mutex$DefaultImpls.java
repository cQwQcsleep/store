package kotlinx.coroutines.sync;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class Mutex$DefaultImpls {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Mutex.onLock deprecated without replacement. For additional details please refer to #2794")
    public static /* synthetic */ void getOnLock$annotations() {
    }

    public static /* synthetic */ Object lock$default(Mutex mutex, Object obj, Continuation continuation, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: lock");
            return null;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        return mutex.lock(obj, continuation);
    }

    public static /* synthetic */ boolean tryLock$default(Mutex mutex, Object obj, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: tryLock");
            return false;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        return mutex.tryLock(obj);
    }

    public static /* synthetic */ void unlock$default(Mutex mutex, Object obj, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: unlock");
            return;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        mutex.unlock(obj);
    }
}
