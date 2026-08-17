package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.compilerRunner.OutputItemsCollector;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ICFileMappingTrackerImpl;", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "outputItemsCollector", "Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollector;", "<init>", "(Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollector;)V", "recordSourceFilesToOutputFileMapping", "", "sourceFiles", "", "Ljava/io/File;", "outputFile", "recordSourceReferencedByCompilerPlugin", "sourceFile", "recordOutputFileGeneratedForPlugin", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ICFileMappingTrackerImpl implements ICFileMappingTracker {
    private final OutputItemsCollector outputItemsCollector;

    public ICFileMappingTrackerImpl(OutputItemsCollector outputItemsCollector) {
        outputItemsCollector.getClass();
        this.outputItemsCollector = outputItemsCollector;
    }

    public void recordOutputFileGeneratedForPlugin(File outputFile) {
        outputFile.getClass();
        this.outputItemsCollector.addOutputFileGeneratedForPlugin(outputFile);
    }

    public void recordSourceFilesToOutputFileMapping(Collection<? extends File> sourceFiles, File outputFile) {
        sourceFiles.getClass();
        outputFile.getClass();
        this.outputItemsCollector.add(sourceFiles, outputFile);
    }

    public void recordSourceReferencedByCompilerPlugin(File sourceFile) {
        sourceFile.getClass();
        this.outputItemsCollector.addSourceReferencedByCompilerPlugin(sourceFile);
    }
}
