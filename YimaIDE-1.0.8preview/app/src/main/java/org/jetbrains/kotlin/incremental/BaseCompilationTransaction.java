package org.jetbrains.kotlin.incremental;

import java.io.Closeable;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import org.jetbrains.kotlin.incremental.storage.InMemoryStorageInterface;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\u0010\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u0018H\u0004J\b\u0010\u001f\u001a\u00020\u000eH\u0004R.\u0010\u0004\u001a\"\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR(\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0018@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/incremental/BaseCompilationTransaction;", "Lorg/jetbrains/kotlin/incremental/CompilationTransaction;", "<init>", "()V", "inMemoryStorageWrappers", "Ljava/util/HashSet;", "Lorg/jetbrains/kotlin/incremental/storage/InMemoryStorageInterface;", "Lkotlin/collections/HashSet;", "isSuccessful", "", "()Z", "setSuccessful", "(Z)V", "markAsSuccessful", "", "registerInMemoryStorageWrapper", "inMemoryStorageWrapper", "value", "Ljava/io/Closeable;", "cachesManager", "getCachesManager", "()Ljava/io/Closeable;", "setCachesManager", "(Ljava/io/Closeable;)V", "", "executionThrowable", "getExecutionThrowable", "()Ljava/lang/Throwable;", "setExecutionThrowable", "(Ljava/lang/Throwable;)V", "closeCachesManager", "checkForExecutionException", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseCompilationTransaction implements CompilationTransaction {
    private Closeable cachesManager;
    private Throwable executionThrowable;
    private final HashSet<InMemoryStorageInterface<?, ?>> inMemoryStorageWrappers = new HashSet<>();
    private boolean isSuccessful;

    public final void checkForExecutionException() {
        if (getExecutionThrowable() != null) {
            this.isSuccessful = false;
        }
    }

    public final Throwable closeCachesManager() {
        Object obj;
        Unit unit;
        try {
            Result.Companion companion = Result.Companion;
            if (!this.isSuccessful) {
                Iterator<InMemoryStorageInterface<?, ?>> it = this.inMemoryStorageWrappers.iterator();
                it.getClass();
                while (it.hasNext()) {
                    InMemoryStorageInterface<?, ?> next = it.next();
                    next.getClass();
                    next.clearChanges();
                }
            }
            Closeable cachesManager = getCachesManager();
            if (cachesManager != null) {
                cachesManager.close();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            obj = Result.constructor-impl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return null;
        }
        this.isSuccessful = false;
        CachesManagerCloseException cachesManagerCloseException = new CachesManagerCloseException(th2);
        Throwable executionThrowable = getExecutionThrowable();
        if (executionThrowable != null) {
            ExceptionsKt.addSuppressed(executionThrowable, cachesManagerCloseException);
        }
        Throwable executionThrowable2 = getExecutionThrowable();
        return executionThrowable2 == null ? cachesManagerCloseException : executionThrowable2;
    }

    @Override // org.jetbrains.kotlin.incremental.CompilationTransaction
    public Closeable getCachesManager() {
        return this.cachesManager;
    }

    @Override // org.jetbrains.kotlin.incremental.CompilationTransaction
    public Throwable getExecutionThrowable() {
        return this.executionThrowable;
    }

    /* JADX INFO: renamed from: isSuccessful, reason: from getter */
    public final boolean getIsSuccessful() {
        return this.isSuccessful;
    }

    @Override // org.jetbrains.kotlin.incremental.CompilationTransaction
    public void markAsSuccessful() {
        this.isSuccessful = true;
    }

    @Override // org.jetbrains.kotlin.incremental.CompilationTransaction
    public void registerInMemoryStorageWrapper(InMemoryStorageInterface<?, ?> inMemoryStorageWrapper) {
        inMemoryStorageWrapper.getClass();
        this.inMemoryStorageWrappers.add(inMemoryStorageWrapper);
    }

    @Override // org.jetbrains.kotlin.incremental.CompilationTransaction
    public void setCachesManager(Closeable closeable) {
        if (this.cachesManager == null) {
            this.cachesManager = closeable;
        } else {
            k2d.a("cachesManager is already set");
        }
    }

    @Override // org.jetbrains.kotlin.incremental.CompilationTransaction
    public void setExecutionThrowable(Throwable th) {
        if (this.executionThrowable == null) {
            this.executionThrowable = th;
        } else {
            k2d.a("executionThrowable is already set");
        }
    }

    public final void setSuccessful(boolean z) {
        this.isSuccessful = z;
    }
}
