package com.reandroid.apk;

import com.reandroid.archive.BlockInputSource;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.utils.Crc32OutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SplitJsonTableInputSource extends BlockInputSource<TableBlock> {
    private TableBlock mCache;
    private final File resourcesDirectory;

    public SplitJsonTableInputSource(File file) {
        super(TableBlock.FILE_NAME, (Block) null);
        this.resourcesDirectory = file;
    }

    @Override // com.reandroid.archive.BlockInputSource
    public TableBlock getBlock() {
        try {
            return getTableBlock();
        } catch (IOException e) {
            rc6.a(e);
            return null;
        }
    }

    @Override // com.reandroid.archive.BlockInputSource, com.reandroid.archive.InputSource
    public long getCrc() throws IOException {
        Crc32OutputStream crc32OutputStream = new Crc32OutputStream();
        write(crc32OutputStream);
        return crc32OutputStream.getValue();
    }

    @Override // com.reandroid.archive.BlockInputSource, com.reandroid.archive.InputSource
    public long getLength() throws IOException {
        return getTableBlock().countBytes();
    }

    public TableBlock getTableBlock() throws IOException {
        TableBlock tableBlock = this.mCache;
        if (tableBlock != null) {
            return tableBlock;
        }
        TableBlock tableBlockScanDirectory = new TableBlockJsonBuilder().scanDirectory(this.resourcesDirectory);
        this.mCache = tableBlockScanDirectory;
        return tableBlockScanDirectory;
    }

    @Override // com.reandroid.archive.ByteInputSource, com.reandroid.archive.InputSource
    public InputStream openStream() throws IOException {
        return new ByteArrayInputStream(getTableBlock().getBytes());
    }

    @Override // com.reandroid.archive.BlockInputSource, com.reandroid.archive.ByteInputSource, com.reandroid.archive.InputSource
    public long write(OutputStream outputStream) throws IOException {
        return getTableBlock().writeBytes(outputStream);
    }
}
