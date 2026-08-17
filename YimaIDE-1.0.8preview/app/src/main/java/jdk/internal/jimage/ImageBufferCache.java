package jdk.internal.jimage;

import defpackage.jb9;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class ImageBufferCache {
    private static final ThreadLocal<Map.Entry<WeakReference<ByteBuffer>, Integer>[]> CACHE = new ThreadLocal<Map.Entry<WeakReference<ByteBuffer>, Integer>[]>() { // from class: jdk.internal.jimage.ImageBufferCache.1
        @Override // java.lang.ThreadLocal
        public Map.Entry<WeakReference<ByteBuffer>, Integer>[] initialValue() {
            return new Map.Entry[4];
        }
    };
    private static Comparator<Map.Entry<WeakReference<ByteBuffer>, Integer>> DECREASING_CAPACITY_NULLS_LAST = new Comparator<Map.Entry<WeakReference<ByteBuffer>, Integer>>() { // from class: jdk.internal.jimage.ImageBufferCache.2
        @Override // java.util.Comparator
        public int compare(Map.Entry<WeakReference<ByteBuffer>, Integer> entry, Map.Entry<WeakReference<ByteBuffer>, Integer> entry2) {
            return Integer.compare(ImageBufferCache.getCapacity(entry), ImageBufferCache.getCapacity(entry2));
        }
    };
    private static final int LARGE_BUFFER = 65536;
    private static final int MAX_CACHED_BUFFERS = 3;

    private static ByteBuffer allocateBuffer(long j) {
        return ByteBuffer.allocateDirect((int) ((j + 4095) & (-4096)));
    }

    public static ByteBuffer getBuffer(long j) {
        ByteBuffer byteBufferAllocateBuffer;
        ByteBuffer byteBuffer;
        ByteBuffer byteBuffer2 = null;
        if (j < 0 || 2147483647L < j) {
            jb9.a("size");
            return null;
        }
        if (j > 65536) {
            byteBufferAllocateBuffer = allocateBuffer(j);
        } else {
            Map.Entry<WeakReference<ByteBuffer>, Integer>[] entryArr = CACHE.get();
            for (int i = 2; i >= 0; i--) {
                Map.Entry<WeakReference<ByteBuffer>, Integer> entry = entryArr[i];
                if (entry != null && (byteBuffer = getByteBuffer(entry)) != null && j <= byteBuffer.capacity()) {
                    entryArr[i] = null;
                    byteBuffer.rewind();
                    byteBuffer2 = byteBuffer;
                    break;
                }
            }
            byteBufferAllocateBuffer = byteBuffer2 == null ? allocateBuffer(j) : byteBuffer2;
        }
        byteBufferAllocateBuffer.limit((int) j);
        return byteBufferAllocateBuffer;
    }

    private static ByteBuffer getByteBuffer(Map.Entry<WeakReference<ByteBuffer>, Integer> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey().get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getCapacity(Map.Entry<WeakReference<ByteBuffer>, Integer> entry) {
        if (entry == null) {
            return 0;
        }
        return entry.getValue().intValue();
    }

    private static Map.Entry<WeakReference<ByteBuffer>, Integer> newCacheEntry(ByteBuffer byteBuffer) {
        return new AbstractMap.SimpleEntry(new WeakReference(byteBuffer), Integer.valueOf(byteBuffer.capacity()));
    }

    public static void releaseBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer.capacity() > LARGE_BUFFER) {
            return;
        }
        Map.Entry<WeakReference<ByteBuffer>, Integer>[] entryArr = CACHE.get();
        for (int i = 0; i < 3; i++) {
            Map.Entry<WeakReference<ByteBuffer>, Integer> entry = entryArr[i];
            if (entry != null && getByteBuffer(entry) == null) {
                entryArr[i] = null;
            }
        }
        entryArr[3] = newCacheEntry(byteBuffer);
        Arrays.sort(entryArr, DECREASING_CAPACITY_NULLS_LAST);
        entryArr[3] = null;
    }
}
