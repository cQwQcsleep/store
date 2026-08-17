package org.jetbrains.kotlin.context;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.storage.ExceptionTracker;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/context/GlobalContextImpl;", "Lorg/jetbrains/kotlin/context/SimpleGlobalContext;", "storageManager", "Lorg/jetbrains/kotlin/storage/LockBasedStorageManager;", "exceptionTracker", "Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "<init>", "(Lorg/jetbrains/kotlin/storage/LockBasedStorageManager;Lorg/jetbrains/kotlin/storage/ExceptionTracker;)V", "getStorageManager", "()Lorg/jetbrains/kotlin/storage/LockBasedStorageManager;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class GlobalContextImpl extends SimpleGlobalContext {
    private final LockBasedStorageManager storageManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlobalContextImpl(LockBasedStorageManager lockBasedStorageManager, ExceptionTracker exceptionTracker) {
        super(lockBasedStorageManager, exceptionTracker);
        lockBasedStorageManager.getClass();
        exceptionTracker.getClass();
        LockBasedStorageManager storageManager = super.getStorageManager();
        storageManager.getClass();
        this.storageManager = storageManager;
    }

    @Override // org.jetbrains.kotlin.context.SimpleGlobalContext, org.jetbrains.kotlin.context.GlobalContext
    public LockBasedStorageManager getStorageManager() {
        return this.storageManager;
    }
}
