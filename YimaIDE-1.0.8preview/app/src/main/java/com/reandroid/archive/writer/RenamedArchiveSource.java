package com.reandroid.archive.writer;

import com.reandroid.archive.RenamedInputSource;
import com.reandroid.archive.io.ArchiveFileEntrySource;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class RenamedArchiveSource extends ArchiveOutputSource {
    public RenamedArchiveSource(RenamedInputSource<?> renamedInputSource) {
        super(renamedInputSource);
    }

    @Override // com.reandroid.archive.writer.ArchiveOutputSource
    public ArchiveFileEntrySource getArchiveSource() {
        return (ArchiveFileEntrySource) ((RenamedInputSource) getInputSource()).getParentInputSource(ArchiveFileEntrySource.class);
    }
}
