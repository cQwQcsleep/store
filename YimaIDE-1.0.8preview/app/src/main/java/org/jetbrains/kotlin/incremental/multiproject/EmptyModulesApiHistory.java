package org.jetbrains.kotlin.incremental.multiproject;

import java.io.File;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.util.Either;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/multiproject/EmptyModulesApiHistory;", "Lorg/jetbrains/kotlin/incremental/multiproject/ModulesApiHistory;", "<init>", "()V", "historyFilesForChangedFiles", "Lorg/jetbrains/kotlin/incremental/util/Either;", "", "Ljava/io/File;", "changedFiles", "abiSnapshot", "jar", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class EmptyModulesApiHistory implements ModulesApiHistory {
    public static final EmptyModulesApiHistory INSTANCE = new EmptyModulesApiHistory();

    private EmptyModulesApiHistory() {
    }

    @Override // org.jetbrains.kotlin.incremental.multiproject.ModulesApiHistory
    public Either<Set<File>> abiSnapshot(File jar) {
        jar.getClass();
        return new Either.Error("Not supported");
    }

    @Override // org.jetbrains.kotlin.incremental.multiproject.ModulesApiHistory
    public Either<Set<File>> historyFilesForChangedFiles(Set<? extends File> changedFiles) {
        changedFiles.getClass();
        return new Either.Error("Multi-module IC is not configured");
    }
}
