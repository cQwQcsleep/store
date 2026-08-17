package org.jetbrains.kotlin.context;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.storage.ExceptionTracker;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/context/SimpleGlobalContext;", "Lorg/jetbrains/kotlin/context/GlobalContext;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "exceptionTracker", "Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "<init>", "(Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/storage/ExceptionTracker;)V", "getStorageManager", "()Lorg/jetbrains/kotlin/storage/StorageManager;", "getExceptionTracker", "()Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class SimpleGlobalContext implements GlobalContext {
    private final ExceptionTracker exceptionTracker;
    private final StorageManager storageManager;

    public SimpleGlobalContext(StorageManager storageManager, ExceptionTracker exceptionTracker) {
        storageManager.getClass();
        exceptionTracker.getClass();
        this.storageManager = storageManager;
        this.exceptionTracker = exceptionTracker;
    }

    @Override // org.jetbrains.kotlin.context.GlobalContext
    public ExceptionTracker getExceptionTracker() {
        return this.exceptionTracker;
    }

    @Override // org.jetbrains.kotlin.context.GlobalContext
    public StorageManager getStorageManager() {
        return this.storageManager;
    }
}
