package com.reandroid.arsc.chunk;

import com.reandroid.arsc.header.HeaderBlock;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.common.BytesOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnknownChunk extends Chunk<HeaderBlock> implements HeaderBlock.HeaderLoaded {
    private static final short INITIAL_CHUNK_TYPE = 0;
    private final ByteArray body;

    public UnknownChunk() {
        super(new HeaderBlock((short) 0), 1);
        ByteArray byteArray = new ByteArray();
        this.body = byteArray;
        addChild(byteArray);
        setHeaderLoaded(this);
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void checkInvalidChunk(HeaderBlock headerBlock) {
    }

    public ByteArray getBody() {
        return this.body;
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream(getHeaderBlock().getChunkSize());
        try {
            writeBytes((OutputStream) bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    @Override // com.reandroid.arsc.header.HeaderBlock.HeaderLoaded
    public void onChunkSizeLoaded(int i, int i2) {
        getBody().setSize(i2 - i);
    }

    @Override // com.reandroid.arsc.header.HeaderBlock.HeaderLoaded
    public void onChunkTypeLoaded(short s) {
    }

    @Override // com.reandroid.arsc.header.HeaderBlock.HeaderLoaded
    public void onHeaderSizeLoaded(int i) {
    }

    public int readBytes(InputStream inputStream) throws IOException {
        int bytes = getHeaderBlock().readBytes(inputStream) + getBody().readBytes(inputStream);
        super.notifyBlockLoad();
        return bytes;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return getHeaderBlock() + " {Body=" + getBody().size() + "}";
    }

    public final int writeBytes(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && parentFile.mkdirs()) {
            r8g.a("Can not create directory: ", parentFile);
            return 0;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int iWriteBytes = super.writeBytes(fileOutputStream);
        fileOutputStream.close();
        return iWriteBytes;
    }

    public int readBytes(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        int bytes = readBytes(fileInputStream);
        fileInputStream.close();
        return bytes;
    }
}
