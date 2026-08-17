package org.jetbrains.kotlin.konan.util;

import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/ArchiveExtractor;", "", "extract", "", "archive", "Ljava/io/File;", "targetDirectory", "archiveType", "Lorg/jetbrains/kotlin/konan/util/ArchiveType;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ArchiveExtractor {
    void extract(File archive, File targetDirectory, ArchiveType archiveType);
}
