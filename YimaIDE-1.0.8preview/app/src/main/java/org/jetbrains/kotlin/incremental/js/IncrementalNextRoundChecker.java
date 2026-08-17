package org.jetbrains.kotlin.incremental.js;

import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/js/IncrementalNextRoundChecker;", "", "checkProtoChanges", "", "sourceFile", "Ljava/io/File;", "packagePartMetadata", "", "shouldGoToNextRound", "", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface IncrementalNextRoundChecker {
    void checkProtoChanges(File sourceFile, byte[] packagePartMetadata);

    boolean shouldGoToNextRound();
}
