package com.reandroid.archive.writer;

import com.reandroid.archive.Archive;
import com.reandroid.archive.block.LocalFileHeader;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface DataDescriptorFactory {
    public static final DataDescriptorFactory NO_ACTION = new DataDescriptorFactory() { // from class: h83
        @Override // com.reandroid.archive.writer.DataDescriptorFactory
        public final void createDataDescriptor(LocalFileHeader localFileHeader) {
            localFileHeader.setHasDataDescriptor(localFileHeader.hasDataDescriptor());
        }
    };
    public static final DataDescriptorFactory NONE = new DataDescriptorFactory() { // from class: i83
        @Override // com.reandroid.archive.writer.DataDescriptorFactory
        public final void createDataDescriptor(LocalFileHeader localFileHeader) {
            localFileHeader.setHasDataDescriptor(false);
        }
    };
    public static final DataDescriptorFactory FOR_DEFLATED = new DataDescriptorFactory() { // from class: j83
        @Override // com.reandroid.archive.writer.DataDescriptorFactory
        public final void createDataDescriptor(LocalFileHeader localFileHeader) {
            localFileHeader.setHasDataDescriptor(localFileHeader.getMethod() == Archive.DEFLATED);
        }
    };
    public static final DataDescriptorFactory FOR_STORED = new DataDescriptorFactory() { // from class: k83
        @Override // com.reandroid.archive.writer.DataDescriptorFactory
        public final void createDataDescriptor(LocalFileHeader localFileHeader) {
            localFileHeader.setHasDataDescriptor(localFileHeader.getMethod() == Archive.STORED);
        }
    };
    public static final DataDescriptorFactory FOR_ALL = new DataDescriptorFactory() { // from class: l83
        @Override // com.reandroid.archive.writer.DataDescriptorFactory
        public final void createDataDescriptor(LocalFileHeader localFileHeader) {
            localFileHeader.setHasDataDescriptor(true);
        }
    };

    void createDataDescriptor(LocalFileHeader localFileHeader);
}
