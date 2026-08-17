package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.compilerRunner.OutputItemsCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/incremental/TransactionOutputsRegistrar;", "Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollector;", "transaction", "Lorg/jetbrains/kotlin/incremental/CompilationTransaction;", "origin", "<init>", "(Lorg/jetbrains/kotlin/incremental/CompilationTransaction;Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollector;)V", "add", "", "sourceFiles", "", "Ljava/io/File;", "outputFile", "addSourceReferencedByCompilerPlugin", "sourceFile", "addOutputFileGeneratedForPlugin", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TransactionOutputsRegistrar implements OutputItemsCollector {
    private final OutputItemsCollector origin;
    private final CompilationTransaction transaction;

    public TransactionOutputsRegistrar(CompilationTransaction compilationTransaction, OutputItemsCollector outputItemsCollector) {
        compilationTransaction.getClass();
        outputItemsCollector.getClass();
        this.transaction = compilationTransaction;
        this.origin = outputItemsCollector;
    }

    @Override // org.jetbrains.kotlin.compilerRunner.OutputItemsCollector
    public void add(Collection<? extends File> sourceFiles, File outputFile) {
        sourceFiles.getClass();
        outputFile.getClass();
        CompilationTransaction compilationTransaction = this.transaction;
        Path path = outputFile.toPath();
        path.getClass();
        compilationTransaction.registerAddedOrChangedFile(path);
        this.origin.add(sourceFiles, outputFile);
    }

    @Override // org.jetbrains.kotlin.compilerRunner.OutputItemsCollector
    public void addOutputFileGeneratedForPlugin(File outputFile) {
        outputFile.getClass();
        CompilationTransaction compilationTransaction = this.transaction;
        Path path = outputFile.toPath();
        path.getClass();
        compilationTransaction.registerAddedOrChangedFile(path);
        this.origin.addOutputFileGeneratedForPlugin(outputFile);
    }

    @Override // org.jetbrains.kotlin.compilerRunner.OutputItemsCollector
    public void addSourceReferencedByCompilerPlugin(File sourceFile) {
        sourceFile.getClass();
        this.origin.addSourceReferencedByCompilerPlugin(sourceFile);
    }
}
