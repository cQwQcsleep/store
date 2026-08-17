package com.google.common.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class RemovalListeners {
    private RemovalListeners() {
    }

    public static <K, V> RemovalListener<K, V> asynchronous(final RemovalListener<K, V> removalListener, final Executor executor) {
        Preconditions.checkNotNull(removalListener);
        Preconditions.checkNotNull(executor);
        return new RemovalListener() { // from class: ncc
            @Override // com.google.common.cache.RemovalListener
            public final void onRemoval(RemovalNotification removalNotification) {
                executor.execute(new Runnable() { // from class: occ
                    @Override // java.lang.Runnable
                    public final void run() {
                        removalListener.onRemoval(removalNotification);
                    }
                });
            }
        };
    }
}
