package org.jetbrains.kotlin.incremental.multiproject;

import java.io.File;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.util.Either;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\b\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/multiproject/ModulesApiHistory;", "", "historyFilesForChangedFiles", "Lorg/jetbrains/kotlin/incremental/util/Either;", "", "Ljava/io/File;", "changedFiles", "abiSnapshot", "jar", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ModulesApiHistory {
    Either<Set<File>> abiSnapshot(File jar);

    Either<Set<File>> historyFilesForChangedFiles(Set<? extends File> changedFiles);
}
