package org.jetbrains.kotlin.compilerRunner;

import java.io.File;
import java.util.Collection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/compilerRunner/OutputItemsCollector;", "", "add", "", "sourceFiles", "", "Ljava/io/File;", "outputFile", "addSourceReferencedByCompilerPlugin", "sourceFile", "addOutputFileGeneratedForPlugin", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface OutputItemsCollector {
    void add(Collection<? extends File> sourceFiles, File outputFile);

    void addOutputFileGeneratedForPlugin(File outputFile);

    void addSourceReferencedByCompilerPlugin(File sourceFile);
}
