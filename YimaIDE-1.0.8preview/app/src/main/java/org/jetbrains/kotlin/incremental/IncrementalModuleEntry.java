package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IncrementalModuleEntry;", "Ljava/io/Serializable;", "projectPath", "", "name", "buildDir", "Ljava/io/File;", "buildHistoryFile", "abiSnapshot", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Ljava/io/File;Ljava/io/File;)V", "getName", "()Ljava/lang/String;", "getBuildDir", "()Ljava/io/File;", "getBuildHistoryFile", "getAbiSnapshot", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class IncrementalModuleEntry implements Serializable {
    private static final long serialVersionUID = 0;
    private final File abiSnapshot;
    private final File buildDir;
    private final File buildHistoryFile;
    private final String name;
    private final String projectPath;

    public IncrementalModuleEntry(String str, String str2, File file, File file2, File file3) {
        str.getClass();
        str2.getClass();
        file.getClass();
        file2.getClass();
        file3.getClass();
        this.projectPath = str;
        this.name = str2;
        this.buildDir = file;
        this.buildHistoryFile = file2;
        this.abiSnapshot = file3;
    }

    public static /* synthetic */ IncrementalModuleEntry copy$default(IncrementalModuleEntry incrementalModuleEntry, String str, String str2, File file, File file2, File file3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = incrementalModuleEntry.projectPath;
        }
        if ((i & 2) != 0) {
            str2 = incrementalModuleEntry.name;
        }
        if ((i & 4) != 0) {
            file = incrementalModuleEntry.buildDir;
        }
        if ((i & 8) != 0) {
            file2 = incrementalModuleEntry.buildHistoryFile;
        }
        if ((i & 16) != 0) {
            file3 = incrementalModuleEntry.abiSnapshot;
        }
        File file4 = file3;
        File file5 = file;
        return incrementalModuleEntry.copy(str, str2, file5, file2, file4);
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final File getBuildDir() {
        return this.buildDir;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final File getBuildHistoryFile() {
        return this.buildHistoryFile;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final File getAbiSnapshot() {
        return this.abiSnapshot;
    }

    public final IncrementalModuleEntry copy(String projectPath, String name, File buildDir, File buildHistoryFile, File abiSnapshot) {
        projectPath.getClass();
        name.getClass();
        buildDir.getClass();
        buildHistoryFile.getClass();
        abiSnapshot.getClass();
        return new IncrementalModuleEntry(projectPath, name, buildDir, buildHistoryFile, abiSnapshot);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncrementalModuleEntry)) {
            return false;
        }
        IncrementalModuleEntry incrementalModuleEntry = (IncrementalModuleEntry) other;
        return Intrinsics.areEqual(this.projectPath, incrementalModuleEntry.projectPath) && Intrinsics.areEqual(this.name, incrementalModuleEntry.name) && Intrinsics.areEqual(this.buildDir, incrementalModuleEntry.buildDir) && Intrinsics.areEqual(this.buildHistoryFile, incrementalModuleEntry.buildHistoryFile) && Intrinsics.areEqual(this.abiSnapshot, incrementalModuleEntry.abiSnapshot);
    }

    public final File getAbiSnapshot() {
        return this.abiSnapshot;
    }

    public final File getBuildDir() {
        return this.buildDir;
    }

    public final File getBuildHistoryFile() {
        return this.buildHistoryFile;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (((((((this.projectPath.hashCode() * 31) + this.name.hashCode()) * 31) + this.buildDir.hashCode()) * 31) + this.buildHistoryFile.hashCode()) * 31) + this.abiSnapshot.hashCode();
    }

    public String toString() {
        return "IncrementalModuleEntry(projectPath=" + this.projectPath + ", name=" + this.name + ", buildDir=" + this.buildDir + ", buildHistoryFile=" + this.buildHistoryFile + ", abiSnapshot=" + this.abiSnapshot + ')';
    }
}
