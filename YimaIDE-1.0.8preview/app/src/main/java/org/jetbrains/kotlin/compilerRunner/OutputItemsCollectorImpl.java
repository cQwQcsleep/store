package org.jetbrains.kotlin.compilerRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollectorImpl;", "Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollector;", "<init>", "()V", "outputs", "", "Lorg/jetbrains/kotlin/compilerRunner/SimpleOutputItem;", "getOutputs", "()Ljava/util/List;", "sourcesReferencedByCompilerPlugin", "Ljava/io/File;", "getSourcesReferencedByCompilerPlugin", "outputsFileGeneratedForPlugin", "getOutputsFileGeneratedForPlugin", "add", "", "sourceFiles", "", "outputFile", "addSourceReferencedByCompilerPlugin", "sourceFile", "addOutputFileGeneratedForPlugin", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OutputItemsCollectorImpl implements OutputItemsCollector {
    private final List<SimpleOutputItem> outputs = new ArrayList();
    private final List<File> sourcesReferencedByCompilerPlugin = new ArrayList();
    private final List<File> outputsFileGeneratedForPlugin = new ArrayList();

    @Override // org.jetbrains.kotlin.compilerRunner.OutputItemsCollector
    public void add(Collection<? extends File> sourceFiles, File outputFile) {
        sourceFiles.getClass();
        outputFile.getClass();
        this.outputs.add(new SimpleOutputItem(sourceFiles, outputFile));
    }

    @Override // org.jetbrains.kotlin.compilerRunner.OutputItemsCollector
    public void addOutputFileGeneratedForPlugin(File outputFile) {
        outputFile.getClass();
        this.outputsFileGeneratedForPlugin.add(outputFile);
    }

    @Override // org.jetbrains.kotlin.compilerRunner.OutputItemsCollector
    public void addSourceReferencedByCompilerPlugin(File sourceFile) {
        sourceFile.getClass();
        this.sourcesReferencedByCompilerPlugin.add(sourceFile);
    }

    public final List<SimpleOutputItem> getOutputs() {
        return this.outputs;
    }

    public final List<File> getOutputsFileGeneratedForPlugin() {
        return this.outputsFileGeneratedForPlugin;
    }

    public final List<File> getSourcesReferencedByCompilerPlugin() {
        return this.sourcesReferencedByCompilerPlugin;
    }
}
