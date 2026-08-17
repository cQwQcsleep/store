package org.jetbrains.kotlin.fir.session.environment;

import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.java.FirJavaFacade;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J \u0010\u000e\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H&J \u0010\u0014\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H&J\u0016\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\u0016\u0010\u0018\u001a\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0010H&J\b\u0010\u001b\u001a\u00020\u0005H&J\b\u0010\u001c\u001a\u00020\u0005H&J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006!À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", Argument.Delimiters.none, "getKotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "fileSearchScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "getJavaModuleResolver", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver;", "getPackagePartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "registerAsJavaElementFinder", Argument.Delimiters.none, "firSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSearchScopeByIoFiles", "files", Argument.Delimiters.none, "Ljava/io/File;", "allowOutOfProjectRoots", Argument.Delimiters.none, "getSearchScopeBySourceFiles", "Lorg/jetbrains/kotlin/KtSourceFile;", "getSearchScopeByDirectories", "directories", "getSearchScopeByClassPath", "paths", "Ljava/nio/file/Path;", "getSearchScopeForProjectLibraries", "getSearchScopeForProjectJavaSources", "getFirJavaFacade", "Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;", "baseModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface AbstractProjectEnvironment {
    static /* synthetic */ AbstractProjectFileSearchScope getSearchScopeByIoFiles$default(AbstractProjectEnvironment abstractProjectEnvironment, Iterable iterable, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getSearchScopeByIoFiles");
            return null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return abstractProjectEnvironment.getSearchScopeByIoFiles(iterable, z);
    }

    static /* synthetic */ AbstractProjectFileSearchScope getSearchScopeBySourceFiles$default(AbstractProjectEnvironment abstractProjectEnvironment, Iterable iterable, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getSearchScopeBySourceFiles");
            return null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return abstractProjectEnvironment.getSearchScopeBySourceFiles(iterable, z);
    }

    FirJavaFacade getFirJavaFacade(FirSession firSession, FirModuleData baseModuleData, AbstractProjectFileSearchScope fileSearchScope);

    JavaModuleResolver getJavaModuleResolver();

    KotlinClassFinder getKotlinClassFinder(AbstractProjectFileSearchScope fileSearchScope);

    PackagePartProvider getPackagePartProvider(AbstractProjectFileSearchScope fileSearchScope);

    AbstractProjectFileSearchScope getSearchScopeByClassPath(Iterable<? extends Path> paths);

    AbstractProjectFileSearchScope getSearchScopeByDirectories(Iterable<? extends File> directories);

    AbstractProjectFileSearchScope getSearchScopeByIoFiles(Iterable<? extends File> files, boolean allowOutOfProjectRoots);

    AbstractProjectFileSearchScope getSearchScopeBySourceFiles(Iterable<? extends KtSourceFile> files, boolean allowOutOfProjectRoots);

    AbstractProjectFileSearchScope getSearchScopeForProjectJavaSources();

    AbstractProjectFileSearchScope getSearchScopeForProjectLibraries();

    void registerAsJavaElementFinder(FirSession firSession);
}
