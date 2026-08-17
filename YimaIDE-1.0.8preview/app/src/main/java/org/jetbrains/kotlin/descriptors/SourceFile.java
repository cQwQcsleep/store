package org.jetbrains.kotlin.descriptors;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface SourceFile {
    public static final SourceFile NO_SOURCE_FILE = new SourceFile() { // from class: org.jetbrains.kotlin.descriptors.SourceFile.1
        @Override // org.jetbrains.kotlin.descriptors.SourceFile
        public String getName() {
            return null;
        }
    };

    String getName();
}
