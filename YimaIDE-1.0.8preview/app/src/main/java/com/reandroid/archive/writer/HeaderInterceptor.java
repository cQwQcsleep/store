package com.reandroid.archive.writer;

import com.reandroid.archive.block.CentralEntryHeader;
import com.reandroid.archive.block.DataDescriptor;
import com.reandroid.archive.block.LocalFileHeader;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface HeaderInterceptor {
    void onWriteCeh(CentralEntryHeader centralEntryHeader);

    void onWriteDD(DataDescriptor dataDescriptor);

    void onWriteLfh(LocalFileHeader localFileHeader);
}
