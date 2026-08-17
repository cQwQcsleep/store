package org.jetbrains.kotlin.compilerRunner;

import java.io.File;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J#\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/compilerRunner/SimpleOutputItem;", "", "sourceFiles", "", "Ljava/io/File;", "outputFile", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Collection;Ljava/io/File;)V", "getSourceFiles", "()Ljava/util/Collection;", "getOutputFile", "()Ljava/io/File;", "toString", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SimpleOutputItem {
    private final File outputFile;
    private final Collection<File> sourceFiles;

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleOutputItem(Collection<? extends File> collection, File file) {
        collection.getClass();
        file.getClass();
        this.sourceFiles = collection;
        this.outputFile = file;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimpleOutputItem copy$default(SimpleOutputItem simpleOutputItem, Collection collection, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            collection = simpleOutputItem.sourceFiles;
        }
        if ((i & 2) != 0) {
            file = simpleOutputItem.outputFile;
        }
        return simpleOutputItem.copy(collection, file);
    }

    public final Collection<File> component1() {
        return this.sourceFiles;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final File getOutputFile() {
        return this.outputFile;
    }

    public final SimpleOutputItem copy(Collection<? extends File> sourceFiles, File outputFile) {
        sourceFiles.getClass();
        outputFile.getClass();
        return new SimpleOutputItem(sourceFiles, outputFile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimpleOutputItem)) {
            return false;
        }
        SimpleOutputItem simpleOutputItem = (SimpleOutputItem) other;
        return Intrinsics.areEqual(this.sourceFiles, simpleOutputItem.sourceFiles) && Intrinsics.areEqual(this.outputFile, simpleOutputItem.outputFile);
    }

    public final File getOutputFile() {
        return this.outputFile;
    }

    public final Collection<File> getSourceFiles() {
        return this.sourceFiles;
    }

    public int hashCode() {
        return (this.sourceFiles.hashCode() * 31) + this.outputFile.hashCode();
    }

    public String toString() {
        return this.sourceFiles + "->" + this.outputFile;
    }
}
