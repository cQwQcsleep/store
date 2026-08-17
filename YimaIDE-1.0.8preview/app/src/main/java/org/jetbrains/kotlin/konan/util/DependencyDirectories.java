package org.jetbrains.kotlin.konan.util;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0010\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DependencyDirectories;", "", "()V", "CACHE_FOLDER_NAME", "", "DEPENDENCIES_FOLDER_NAME", "defaultDependenciesRoot", "Ljava/io/File;", "getDefaultDependenciesRoot$annotations", "getDefaultDependenciesRoot", "()Ljava/io/File;", "localKonanDir", "getLocalKonanDir$annotations", "getLocalKonanDir", "getDependenciesRoot", "konanDataDir", "getDependencyCacheDir", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DependencyDirectories {
    public static final DependencyDirectories INSTANCE = new DependencyDirectories();

    private DependencyDirectories() {
    }

    public static final File getDefaultDependenciesRoot() {
        return getDependenciesRoot$default(INSTANCE, null, 1, null);
    }

    @JvmStatic
    public static /* synthetic */ void getDefaultDependenciesRoot$annotations() {
    }

    public static /* synthetic */ File getDependenciesRoot$default(DependencyDirectories dependencyDirectories, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return dependencyDirectories.getDependenciesRoot(str);
    }

    public static /* synthetic */ File getDependencyCacheDir$default(DependencyDirectories dependencyDirectories, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return dependencyDirectories.getDependencyCacheDir(str);
    }

    @JvmStatic
    public static /* synthetic */ void getLocalKonanDir$annotations() {
    }

    public static /* synthetic */ File getLocalKonanDir$default(DependencyDirectories dependencyDirectories, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return dependencyDirectories.getLocalKonanDir(str);
    }

    public final File getDependenciesRoot(String konanDataDir) {
        return FilesKt.resolve(getLocalKonanDir(konanDataDir), "dependencies");
    }

    public final File getDependencyCacheDir(String konanDataDir) {
        return FilesKt.resolve(getLocalKonanDir(konanDataDir), "cache");
    }

    public final File getLocalKonanDir(String konanDataDir) {
        if (konanDataDir == null) {
            konanDataDir = System.getenv("KONAN_DATA_DIR");
        }
        if (konanDataDir == null) {
            konanDataDir = System.getProperty("user.home") + File.separator + ".konan";
        }
        return new File(konanDataDir);
    }

    public static final File getLocalKonanDir() {
        return getLocalKonanDir$default(INSTANCE, null, 1, null);
    }
}
