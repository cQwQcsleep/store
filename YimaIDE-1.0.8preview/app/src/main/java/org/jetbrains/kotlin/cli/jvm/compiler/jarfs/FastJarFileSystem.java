package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import com.intellij.openapi.util.Couple;
import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Function;
import com.intellij.util.containers.ConcurrentFactoryMap;
import com.intellij.util.io.FileAccessorCache;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.FastJarFileSystem;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0001\"B\"\b\u0002\u0012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0018\u001a\u00020\rH\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\rH\u0016J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\rH\u0016J\u0006\u0010 \u001a\u00020\u0005J\u0006\u0010!\u001a\u00020\u0005R%\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u0002`\u00150\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;", "Lcom/intellij/openapi/vfs/DeprecatedVirtualFileSystem;", "unmapBuffer", "Lkotlin/Function1;", "Ljava/nio/MappedByteBuffer;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getUnmapBuffer$kotlin_compiler", "()Lkotlin/jvm/functions/Function1;", "myHandlers", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarHandler;", "cachedOpenFileHandles", "Lcom/intellij/util/io/FileAccessorCache;", "Ljava/io/File;", "Lkotlin/Pair;", "Ljava/io/RandomAccessFile;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/RandomAccessFileAndBuffer;", "getCachedOpenFileHandles$kotlin_compiler", "()Lcom/intellij/util/io/FileAccessorCache;", "getProtocol", "findFileByPath", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.PATH, "refresh", "asynchronous", Argument.Delimiters.none, "refreshAndFindFileByPath", "clearHandlersCache", "cleanOpenFilesCache", "Companion", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FastJarFileSystem extends DeprecatedVirtualFileSystem {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FileAccessorCache<File, Pair<RandomAccessFile, LargeDynamicMappedBuffer>> cachedOpenFileHandles;
    private final Map<String, FastJarHandler> myHandlers;
    private final Function1<MappedByteBuffer, Unit> unmapBuffer;

    /* JADX WARN: Multi-variable type inference failed */
    private FastJarFileSystem(Function1<? super MappedByteBuffer, Unit> function1) {
        this.unmapBuffer = function1;
        final Function1 function2 = new Function1() { // from class: vn4
            public final Object invoke(Object obj) {
                return FastJarFileSystem.c(this.b, (String) obj);
            }
        };
        ConcurrentMap concurrentMapCreateMap = ConcurrentFactoryMap.createMap(new Function() { // from class: wn4
            public final Object fun(Object obj) {
                return FastJarFileSystem.d(function2, obj);
            }
        });
        concurrentMapCreateMap.getClass();
        this.myHandlers = concurrentMapCreateMap;
        this.cachedOpenFileHandles = new FastJarFileSystem$cachedOpenFileHandles$1(this);
    }

    public static FastJarHandler c(FastJarFileSystem fastJarFileSystem, String str) {
        str.getClass();
        return new FastJarHandler(fastJarFileSystem, str);
    }

    public static FastJarHandler d(Function1 function1, Object obj) {
        return (FastJarHandler) function1.invoke(obj);
    }

    public final void cleanOpenFilesCache() {
        this.cachedOpenFileHandles.clear();
    }

    public final void clearHandlersCache() {
        this.myHandlers.clear();
        cleanOpenFilesCache();
    }

    public VirtualFile findFileByPath(String path) {
        path.getClass();
        Couple<String> coupleSplitPath = INSTANCE.splitPath(path);
        FastJarHandler fastJarHandler = this.myHandlers.get(((com.intellij.openapi.util.Pair) coupleSplitPath).first);
        fastJarHandler.getClass();
        Object obj = ((com.intellij.openapi.util.Pair) coupleSplitPath).second;
        obj.getClass();
        return fastJarHandler.findFileByPath((String) obj);
    }

    public final FileAccessorCache<File, Pair<RandomAccessFile, LargeDynamicMappedBuffer>> getCachedOpenFileHandles$kotlin_compiler() {
        return this.cachedOpenFileHandles;
    }

    public String getProtocol() {
        return "jar";
    }

    public final Function1<MappedByteBuffer, Unit> getUnmapBuffer$kotlin_compiler() {
        return this.unmapBuffer;
    }

    public void refresh(boolean asynchronous) {
    }

    public VirtualFile refreshAndFindFileByPath(String path) {
        path.getClass();
        return findFileByPath(path);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006J\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem$Companion;", Argument.Delimiters.none, "<init>", "()V", "splitPath", "Lcom/intellij/openapi/util/Couple;", Argument.Delimiters.none, ModuleXmlParser.PATH, "createIfUnmappingPossible", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FastJarFileSystem createIfUnmappingPossible() {
            Function1 function1PrepareCleanerCallback = FastJarFileSystemKt.prepareCleanerCallback();
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (function1PrepareCleanerCallback == null) {
                return null;
            }
            return new FastJarFileSystem(function1PrepareCleanerCallback, defaultConstructorMarker);
        }

        public final Couple<String> splitPath(String path) {
            path.getClass();
            int iIndexOf$default = StringsKt.indexOf$default(path, "!/", 0, false, 6, (Object) null);
            if (iIndexOf$default < 0) {
                dt1.a("Path in JarFileSystem must contain a separator: ", path);
                return null;
            }
            Couple<String> coupleOf = Couple.of(Paths.get(path.substring(0, iIndexOf$default), new String[0]).toAbsolutePath().normalize().toString(), path.substring(iIndexOf$default + 2));
            coupleOf.getClass();
            return coupleOf;
        }

        private Companion() {
        }
    }

    public /* synthetic */ FastJarFileSystem(Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1);
    }
}
