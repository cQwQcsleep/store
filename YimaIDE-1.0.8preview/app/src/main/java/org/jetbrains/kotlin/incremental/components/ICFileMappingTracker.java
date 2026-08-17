package org.jetbrains.kotlin.incremental.components;

import java.io.File;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0001\u000bJ\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", Argument.Delimiters.none, "recordSourceFilesToOutputFileMapping", Argument.Delimiters.none, "sourceFiles", Argument.Delimiters.none, "Ljava/io/File;", "outputFile", "recordSourceReferencedByCompilerPlugin", "sourceFile", "recordOutputFileGeneratedForPlugin", "DoNothing", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ICFileMappingTracker {

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker$DoNothing;", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "<init>", "()V", "recordSourceFilesToOutputFileMapping", Argument.Delimiters.none, "sourceFiles", Argument.Delimiters.none, "Ljava/io/File;", "outputFile", "recordSourceReferencedByCompilerPlugin", "sourceFile", "recordOutputFileGeneratedForPlugin", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DoNothing implements ICFileMappingTracker {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
        }

        @Override // org.jetbrains.kotlin.incremental.components.ICFileMappingTracker
        public void recordOutputFileGeneratedForPlugin(File outputFile) {
            outputFile.getClass();
        }

        @Override // org.jetbrains.kotlin.incremental.components.ICFileMappingTracker
        public void recordSourceFilesToOutputFileMapping(Collection<? extends File> sourceFiles, File outputFile) {
            sourceFiles.getClass();
            outputFile.getClass();
        }

        @Override // org.jetbrains.kotlin.incremental.components.ICFileMappingTracker
        public void recordSourceReferencedByCompilerPlugin(File sourceFile) {
            sourceFile.getClass();
        }
    }

    void recordOutputFileGeneratedForPlugin(File outputFile);

    void recordSourceFilesToOutputFileMapping(Collection<? extends File> sourceFiles, File outputFile);

    void recordSourceReferencedByCompilerPlugin(File sourceFile);
}
