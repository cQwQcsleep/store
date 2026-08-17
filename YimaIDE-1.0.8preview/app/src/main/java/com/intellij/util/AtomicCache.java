package com.intellij.util;

import com.intellij.util.AtomicCache;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/intellij/util/AtomicCache;", "Cache", "", "newInstance", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getNewInstance", "()Lkotlin/jvm/functions/Function0;", "_cache", "Ljava/util/concurrent/atomic/AtomicReference;", "cache", "getCache", "()Ljava/lang/Object;", "invalidate", "", "isInitialized", "", "()Z", "intellij.platform.core.impl"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class AtomicCache<Cache> {
    private final AtomicReference<Cache> _cache;
    private final Function0<Cache> newInstance;

    /* JADX WARN: Multi-variable type inference failed */
    public AtomicCache(Function0<? extends Cache> function0) {
        function0.getClass();
        this.newInstance = function0;
        this._cache = new AtomicReference<>(null);
    }

    public static Object a(AtomicCache atomicCache, Object obj) {
        return obj == null ? atomicCache.newInstance.invoke() : obj;
    }

    public final Cache getCache() {
        Cache cache = this._cache.get();
        if (cache != null) {
            return cache;
        }
        Cache cacheUpdateAndGet = this._cache.updateAndGet(new UnaryOperator() { // from class: jh0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AtomicCache.a(this.b, obj);
            }
        });
        cacheUpdateAndGet.getClass();
        return cacheUpdateAndGet;
    }

    public final void invalidate() {
        this._cache.set(null);
    }

    public final boolean isInitialized() {
        return this._cache.get() != null;
    }
}
