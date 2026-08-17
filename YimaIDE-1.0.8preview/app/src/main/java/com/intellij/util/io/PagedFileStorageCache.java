package com.intellij.util.io;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class PagedFileStorageCache {
    private volatile CachedBuffer myLastBuffer;
    private volatile CachedBuffer myLastBuffer2;
    private volatile CachedBuffer myLastBuffer3;

    public static final class CachedBuffer {
        private final long myLastPage;
        private final DirectBufferWrapper myWrapper;

        private CachedBuffer(DirectBufferWrapper directBufferWrapper, long j) {
            this.myWrapper = directBufferWrapper;
            this.myLastPage = j;
        }
    }

    private static DirectBufferWrapper fromCache(CachedBuffer cachedBuffer, long j) {
        if (cachedBuffer == null || cachedBuffer.myWrapper.isReleased() || cachedBuffer.myLastPage != j) {
            return null;
        }
        return cachedBuffer.myWrapper;
    }

    public void clear() {
        this.myLastBuffer = null;
        this.myLastBuffer2 = null;
        this.myLastBuffer3 = null;
    }

    public DirectBufferWrapper getPageFromCache(long j) {
        DirectBufferWrapper directBufferWrapperFromCache = fromCache(this.myLastBuffer, j);
        if (directBufferWrapperFromCache != null) {
            return directBufferWrapperFromCache;
        }
        DirectBufferWrapper directBufferWrapperFromCache2 = fromCache(this.myLastBuffer2, j);
        return directBufferWrapperFromCache2 != null ? directBufferWrapperFromCache2 : fromCache(this.myLastBuffer3, j);
    }

    public void updateCache(long j, DirectBufferWrapper directBufferWrapper) {
        if (this.myLastBuffer != null && this.myLastBuffer.myLastPage != j) {
            this.myLastBuffer3 = this.myLastBuffer2;
            this.myLastBuffer2 = this.myLastBuffer;
        }
        this.myLastBuffer = new CachedBuffer(directBufferWrapper, j);
    }
}
