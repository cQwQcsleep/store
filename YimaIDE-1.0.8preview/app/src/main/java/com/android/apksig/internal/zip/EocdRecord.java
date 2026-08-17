package com.android.apksig.internal.zip;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class EocdRecord {
    private static final int CD_OFFSET_OFFSET = 16;
    private static final int CD_RECORD_COUNT_ON_DISK_OFFSET = 8;
    private static final int CD_RECORD_COUNT_TOTAL_OFFSET = 10;
    private static final int CD_SIZE_OFFSET = 12;

    public static ByteBuffer createWithModifiedCentralDirectoryInfo(ByteBuffer byteBuffer, int i, long j, long j2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(byteBuffer.slice());
        byteBufferAllocate.flip();
        ZipUtils.setUnsignedInt16(byteBufferAllocate, 8, i);
        ZipUtils.setUnsignedInt16(byteBufferAllocate, 10, i);
        ZipUtils.setUnsignedInt32(byteBufferAllocate, 12, j);
        ZipUtils.setUnsignedInt32(byteBufferAllocate, 16, j2);
        return byteBufferAllocate;
    }

    public static ByteBuffer createWithPaddedComment(ByteBuffer byteBuffer, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining() + i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(byteBuffer.slice());
        byteBufferAllocate.rewind();
        ZipUtils.updateZipEocdCommentLen(byteBufferAllocate);
        return byteBufferAllocate;
    }
}
