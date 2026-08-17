package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\"\n\u0002\b\u000f\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u0005\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IncrementalModuleInfo;", "Ljava/io/Serializable;", "rootProjectBuildDir", "Ljava/io/File;", "dirToModule", "", "Lorg/jetbrains/kotlin/incremental/IncrementalModuleEntry;", "nameToModules", "", "", "jarToClassListFile", "jarToModule", "jarToAbiSnapshot", "<init>", "(Ljava/io/File;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getRootProjectBuildDir", "()Ljava/io/File;", "getDirToModule", "()Ljava/util/Map;", "getNameToModules", "getJarToClassListFile", "getJarToModule", "getJarToAbiSnapshot", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IncrementalModuleInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private final Map<File, IncrementalModuleEntry> dirToModule;
    private final Map<File, File> jarToAbiSnapshot;
    private final Map<File, File> jarToClassListFile;
    private final Map<File, IncrementalModuleEntry> jarToModule;
    private final Map<String, Set<IncrementalModuleEntry>> nameToModules;
    private final File rootProjectBuildDir;

    /* JADX WARN: Multi-variable type inference failed */
    public IncrementalModuleInfo(File file, Map<File, IncrementalModuleEntry> map, Map<String, ? extends Set<IncrementalModuleEntry>> map2, Map<File, ? extends File> map3, Map<File, IncrementalModuleEntry> map4, Map<File, ? extends File> map5) {
        file.getClass();
        map.getClass();
        map2.getClass();
        map3.getClass();
        map4.getClass();
        map5.getClass();
        this.rootProjectBuildDir = file;
        this.dirToModule = map;
        this.nameToModules = map2;
        this.jarToClassListFile = map3;
        this.jarToModule = map4;
        this.jarToAbiSnapshot = map5;
    }

    public final Map<File, IncrementalModuleEntry> getDirToModule() {
        return this.dirToModule;
    }

    public final Map<File, File> getJarToAbiSnapshot() {
        return this.jarToAbiSnapshot;
    }

    public final Map<File, File> getJarToClassListFile() {
        return this.jarToClassListFile;
    }

    public final Map<File, IncrementalModuleEntry> getJarToModule() {
        return this.jarToModule;
    }

    public final Map<String, Set<IncrementalModuleEntry>> getNameToModules() {
        return this.nameToModules;
    }

    public final File getRootProjectBuildDir() {
        return this.rootProjectBuildDir;
    }
}
