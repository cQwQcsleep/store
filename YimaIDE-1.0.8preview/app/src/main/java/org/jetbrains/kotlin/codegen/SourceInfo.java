package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/codegen/SourceInfo;", Argument.Delimiters.none, "sourceFileName", Argument.Delimiters.none, "pathOrCleanFQN", "linesInFile", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "getSourceFileName", "()Ljava/lang/String;", "getPathOrCleanFQN", "getLinesInFile", "()I", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SourceInfo {
    private final int linesInFile;
    private final String pathOrCleanFQN;
    private final String sourceFileName;

    public SourceInfo(String str, String str2, int i) {
        str2.getClass();
        this.sourceFileName = str;
        this.pathOrCleanFQN = str2;
        this.linesInFile = i;
    }

    public static /* synthetic */ SourceInfo copy$default(SourceInfo sourceInfo, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sourceInfo.sourceFileName;
        }
        if ((i2 & 2) != 0) {
            str2 = sourceInfo.pathOrCleanFQN;
        }
        if ((i2 & 4) != 0) {
            i = sourceInfo.linesInFile;
        }
        return sourceInfo.copy(str, str2, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSourceFileName() {
        return this.sourceFileName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPathOrCleanFQN() {
        return this.pathOrCleanFQN;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getLinesInFile() {
        return this.linesInFile;
    }

    public final SourceInfo copy(String sourceFileName, String pathOrCleanFQN, int linesInFile) {
        pathOrCleanFQN.getClass();
        return new SourceInfo(sourceFileName, pathOrCleanFQN, linesInFile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceInfo)) {
            return false;
        }
        SourceInfo sourceInfo = (SourceInfo) other;
        return Intrinsics.areEqual(this.sourceFileName, sourceInfo.sourceFileName) && Intrinsics.areEqual(this.pathOrCleanFQN, sourceInfo.pathOrCleanFQN) && this.linesInFile == sourceInfo.linesInFile;
    }

    public final int getLinesInFile() {
        return this.linesInFile;
    }

    public final String getPathOrCleanFQN() {
        return this.pathOrCleanFQN;
    }

    public final String getSourceFileName() {
        return this.sourceFileName;
    }

    public int hashCode() {
        String str = this.sourceFileName;
        return ((((str == null ? 0 : str.hashCode()) * 31) + this.pathOrCleanFQN.hashCode()) * 31) + Integer.hashCode(this.linesInFile);
    }

    public String toString() {
        return "SourceInfo(sourceFileName=" + this.sourceFileName + ", pathOrCleanFQN=" + this.pathOrCleanFQN + ", linesInFile=" + this.linesInFile + ')';
    }
}
