package com.intellij.util.io.pagecache.impl;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface PageContentLockingStrategy {
    public static final PageContentLockingStrategy LOCK_PER_PAGE = new PageContentLockingStrategy() { // from class: com.intellij.util.io.pagecache.impl.PageContentLockingStrategy.1
        public String toString() {
            return "LockPerPageLockingStrategy";
        }
    };

    public static final class SharedLockLockingStrategy implements PageContentLockingStrategy {
        private final ReentrantReadWriteLock sharedLock;

        public SharedLockLockingStrategy() {
            this(new ReentrantReadWriteLock());
        }

        public String toString() {
            return "SingleLockLockingStrategy[" + this.sharedLock + "]";
        }

        public SharedLockLockingStrategy(ReentrantReadWriteLock reentrantReadWriteLock) {
            this.sharedLock = reentrantReadWriteLock;
        }
    }
}
