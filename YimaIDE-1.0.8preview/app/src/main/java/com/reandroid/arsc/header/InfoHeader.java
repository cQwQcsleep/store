package com.reandroid.arsc.header;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.common.FileChannelInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class InfoHeader extends HeaderBlock {
    public static final int INFO_MIN_SIZE = 8;

    public InfoHeader() {
        this((short) 0);
    }

    public static InfoHeader read(BlockReader blockReader) throws IOException {
        InfoHeader infoHeader = new InfoHeader();
        if (blockReader.available() < infoHeader.getMinimumSize()) {
            return null;
        }
        int position = blockReader.getPosition();
        infoHeader.readBytes(blockReader);
        blockReader.seek(position);
        return infoHeader;
    }

    public static InfoHeader readHeaderBlock(byte[] bArr) throws IOException {
        BlockReader blockReader = new BlockReader(bArr);
        InfoHeader infoHeader = new InfoHeader();
        infoHeader.readBytes(blockReader);
        return infoHeader;
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public int countBytes() {
        return 8;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public int getMinimumSize() {
        return 8;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public void initExtraBytes(ByteArray byteArray, int i) {
    }

    public InfoHeader(short s) {
        super(s);
    }

    public static InfoHeader readHeaderBlock(InputStream inputStream) throws IOException {
        InfoHeader infoHeader = new InfoHeader();
        infoHeader.readBytes(inputStream);
        return infoHeader;
    }

    public static InfoHeader readHeaderBlock(BlockReader blockReader) throws IOException {
        InfoHeader infoHeader = new InfoHeader();
        infoHeader.readBytes(blockReader);
        return infoHeader;
    }

    public static InfoHeader readHeaderBlock(File file) throws IOException {
        return readHeaderBlock(FileChannelInputStream.read(file, 8));
    }
}
