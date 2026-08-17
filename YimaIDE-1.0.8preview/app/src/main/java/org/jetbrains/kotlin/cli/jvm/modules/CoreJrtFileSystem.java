package org.jetbrains.kotlin.cli.jvm.modules;

import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Function;
import com.intellij.util.containers.ConcurrentFactoryMap;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.modules.CoreJrtFileSystem;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u0006H\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0006\u0010\u0013\u001a\u00020\u000fR/\u0010\u0004\u001a#\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b0\u0005¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/modules/CoreJrtFileSystem;", "Lcom/intellij/openapi/vfs/DeprecatedVirtualFileSystem;", "<init>", "()V", "roots", "Ljava/util/concurrent/ConcurrentMap;", Argument.Delimiters.none, "kotlin.jvm.PlatformType", "Lorg/jetbrains/kotlin/cli/jvm/modules/CoreJrtVirtualFile;", "Lorg/jetbrains/annotations/NotNull;", "getProtocol", "findFileByPath", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.PATH, "refresh", Argument.Delimiters.none, "asynchronous", Argument.Delimiters.none, "refreshAndFindFileByPath", "clearRoots", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoreJrtFileSystem extends DeprecatedVirtualFileSystem {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ConcurrentMap<String, FileSystem> globalJrtFsCache;
    private final ConcurrentMap<String, CoreJrtVirtualFile> roots;

    static {
        final Function1 function1 = new Function1() { // from class: sy2
            public final Object invoke(Object obj) {
                return CoreJrtFileSystem.c((String) obj);
            }
        };
        ConcurrentMap<String, FileSystem> concurrentMapCreateMap = ConcurrentFactoryMap.createMap(new Function() { // from class: ty2
            public final Object fun(Object obj) {
                return CoreJrtFileSystem.e(function1, obj);
            }
        });
        concurrentMapCreateMap.getClass();
        globalJrtFsCache = concurrentMapCreateMap;
    }

    public CoreJrtFileSystem() {
        final Function1 function1 = new Function1() { // from class: uy2
            public final Object invoke(Object obj) {
                return CoreJrtFileSystem.f(this.b, (String) obj);
            }
        };
        ConcurrentMap<String, CoreJrtVirtualFile> concurrentMapCreateMap = ConcurrentFactoryMap.createMap(new Function() { // from class: vy2
            public final Object fun(Object obj) {
                return CoreJrtFileSystem.d(function1, obj);
            }
        });
        concurrentMapCreateMap.getClass();
        this.roots = concurrentMapCreateMap;
    }

    public static FileSystem c(String str) {
        File file = new File(str);
        File fileLoadJrtFsJar = INSTANCE.loadJrtFsJar(file);
        if (fileLoadJrtFsJar == null) {
            return null;
        }
        URI uriCreate = URI.create("jrt:/");
        if (JavaVersionUtilsKt.isAtLeastJava9()) {
            return FileSystems.newFileSystem(uriCreate, (Map<String, ?>) MapsKt.mapOf(TuplesKt.to("java.home", file.getAbsolutePath())));
        }
        return FileSystems.newFileSystem(uriCreate, MapsKt.emptyMap(), new URLClassLoader(new URL[]{fileLoadJrtFsJar.toURI().toURL()}, null));
    }

    public static CoreJrtVirtualFile d(Function1 function1, Object obj) {
        return (CoreJrtVirtualFile) function1.invoke(obj);
    }

    public static FileSystem e(Function1 function1, Object obj) {
        return (FileSystem) function1.invoke(obj);
    }

    public static CoreJrtVirtualFile f(CoreJrtFileSystem coreJrtFileSystem, String str) {
        FileSystem fileSystem = globalJrtFsCache.get(str);
        if (fileSystem == null) {
            return null;
        }
        str.getClass();
        Path path = fileSystem.getPath(Argument.Delimiters.none, new String[0]);
        path.getClass();
        return new CoreJrtVirtualFile(coreJrtFileSystem, str, path, (CoreJrtVirtualFile) null);
    }

    public final void clearRoots() {
        this.roots.clear();
    }

    public VirtualFile findFileByPath(String path) {
        path.getClass();
        Pair<String, String> pairSplitPath = INSTANCE.splitPath(path);
        String str = (String) pairSplitPath.component1();
        String str2 = (String) pairSplitPath.component2();
        CoreJrtVirtualFile coreJrtVirtualFile = this.roots.get(str);
        if (coreJrtVirtualFile == null) {
            return null;
        }
        return str2.length() == 0 ? coreJrtVirtualFile : coreJrtVirtualFile.findFileByRelativePath(str2);
    }

    public String getProtocol() {
        return "jrt";
    }

    public void refresh(boolean asynchronous) {
    }

    public VirtualFile refreshAndFindFileByPath(String path) {
        path.getClass();
        return findFileByPath(path);
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005J\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bR/\u0010\r\u001a#\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000b0\u000b\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00100\u00100\u000e¢\u0006\u0002\b\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/modules/CoreJrtFileSystem$Companion;", Argument.Delimiters.none, "<init>", "()V", "loadJrtFsJar", "Ljava/io/File;", "jdkHome", "isModularJdk", Argument.Delimiters.none, "splitPath", "Lkotlin/Pair;", Argument.Delimiters.none, ModuleXmlParser.PATH, "globalJrtFsCache", "Ljava/util/concurrent/ConcurrentMap;", "kotlin.jvm.PlatformType", "Ljava/nio/file/FileSystem;", "Lorg/jetbrains/annotations/NotNull;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final File loadJrtFsJar(File jdkHome) {
            File file = new File(jdkHome, "lib/jrt-fs.jar");
            if (file.exists()) {
                return file;
            }
            return null;
        }

        public final boolean isModularJdk(File jdkHome) {
            jdkHome.getClass();
            return loadJrtFsJar(jdkHome) != null;
        }

        public final Pair<String, String> splitPath(String path) {
            path.getClass();
            int iIndexOf$default = StringsKt.indexOf$default(path, "!/", 0, false, 6, (Object) null);
            if (iIndexOf$default >= 0) {
                return new Pair<>(path.substring(0, iIndexOf$default), path.substring(iIndexOf$default + 2));
            }
            aca.a("Path in CoreJrtFileSystem must contain a separator: ", path);
            return null;
        }

        private Companion() {
        }
    }
}
