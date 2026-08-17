package com.reandroid.archive.io;

import com.reandroid.archive.ArchiveEntry;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveByteEntrySource extends ArchiveEntrySource<ZipByteInput> {
    public ArchiveByteEntrySource(ZipByteInput zipByteInput, ArchiveEntry archiveEntry) {
        super(zipByteInput, archiveEntry);
        setSort(archiveEntry.getIndex());
    }
}
