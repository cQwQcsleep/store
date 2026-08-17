package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.io.FileAccessorCache;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.FastJarHandler;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0017H\u0002J$\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0017H\u0002J\u0018\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001c*\u00020\u001aH\u0002J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001e\u001a\u00020\u0005J\u000e\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarHandler;", Argument.Delimiters.none, "fileSystem", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;", ModuleXmlParser.PATH, Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;Ljava/lang/String;)V", "getFileSystem", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;", "myRoot", "Lcom/intellij/openapi/vfs/VirtualFile;", "file", "Ljava/io/File;", "getFile$kotlin_compiler", "()Ljava/io/File;", "cachedManifest", Argument.Delimiters.none, "createFile", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarVirtualFile;", "entry", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription;", "directories", Argument.Delimiters.none, "getOrCreateDirectory", "entryName", Argument.Delimiters.none, "splitPath", "Lkotlin/Pair;", "findFileByPath", "pathInJar", "contentsToByteArray", "zipEntryDescription", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FastJarHandler {
    private final byte[] cachedManifest;
    private final File file;
    private final FastJarFileSystem fileSystem;
    private final VirtualFile myRoot;

    public FastJarHandler(FastJarFileSystem fastJarFileSystem, String str) {
        List<ZipEntryDescription> listEmptyList;
        Iterator<T> it;
        boolean z;
        Object obj;
        fastJarFileSystem.getClass();
        str.getClass();
        this.fileSystem = fastJarFileSystem;
        File file = new File(str);
        this.file = file;
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            long length = randomAccessFile.length();
            Function2 function2 = new Function2() { // from class: zn4
                public final Object invoke(Object obj2, Object obj3) {
                    return FastJarHandler.a(randomAccessFile, ((Long) obj2).longValue(), ((Long) obj3).longValue());
                }
            };
            Function1<MappedByteBuffer, Unit> unmapBuffer$kotlin_compiler = fastJarFileSystem.getUnmapBuffer$kotlin_compiler();
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            byteOrder.getClass();
            LargeDynamicMappedBuffer largeDynamicMappedBuffer = new LargeDynamicMappedBuffer(length, function2, unmapBuffer$kotlin_compiler, byteOrder);
            try {
                try {
                    listEmptyList = ZipImplementationKt.parseCentralDirectory(largeDynamicMappedBuffer);
                    while (true) {
                        if (!it.hasNext()) {
                            if (z) {
                                break;
                            }
                        } else {
                            Object next = it.next();
                            if (StringUtil.equals("META-INF/MANIFEST.MF", ((ZipEntryDescription) next).getRelativePath())) {
                                if (!z) {
                                    z = true;
                                    obj = next;
                                }
                            }
                        }
                        obj = null;
                        break;
                    }
                } catch (Exception e) {
                    Logger.getInstance(FastJarHandler.class).warn("Error while reading zip file: " + this.file.getPath() + ": " + e, e);
                    listEmptyList = CollectionsKt.emptyList();
                }
                it = listEmptyList.iterator();
                z = false;
                obj = null;
                ZipEntryDescription zipEntryDescription = (ZipEntryDescription) obj;
                this.cachedManifest = zipEntryDescription != null ? ZipImplementationKt.contentsToByteArray(largeDynamicMappedBuffer, zipEntryDescription) : null;
                largeDynamicMappedBuffer.unmap();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(randomAccessFile, (Throwable) null);
                FastJarVirtualFile fastJarVirtualFile = new FastJarVirtualFile(this, Argument.Delimiters.none, -1L, null, null);
                this.myRoot = fastJarVirtualFile;
                HashMap map = new HashMap(listEmptyList.size());
                map.put(Argument.Delimiters.none, fastJarVirtualFile);
                for (ZipEntryDescription zipEntryDescription2 : listEmptyList) {
                    if (zipEntryDescription2.isDirectory()) {
                        getOrCreateDirectory(zipEntryDescription2.getRelativePath(), map);
                    } else {
                        createFile(zipEntryDescription2, map);
                    }
                }
                for (Object obj2 : map.values()) {
                    obj2.getClass();
                    ((FastJarVirtualFile) obj2).initChildrenArrayFromList();
                }
            } catch (Throwable th) {
                largeDynamicMappedBuffer.unmap();
                throw th;
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(randomAccessFile, th2);
                throw th3;
            }
        }
    }

    public static MappedByteBuffer a(RandomAccessFile randomAccessFile, long j, long j2) throws IOException {
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j, j2);
        map.getClass();
        return map;
    }

    private final FastJarVirtualFile createFile(ZipEntryDescription entry, Map<String, FastJarVirtualFile> directories) {
        Pair<CharSequence, CharSequence> pairSplitPath = splitPath(entry.getRelativePath());
        CharSequence charSequence = (CharSequence) pairSplitPath.component1();
        CharSequence charSequence2 = (CharSequence) pairSplitPath.component2();
        FastJarVirtualFile orCreateDirectory = getOrCreateDirectory(charSequence, directories);
        if (Intrinsics.areEqual(".", charSequence2)) {
            return orCreateDirectory;
        }
        return new FastJarVirtualFile(this, charSequence2, entry.isDirectory() ? -1L : entry.getUncompressedSize(), orCreateDirectory, entry);
    }

    private final FastJarVirtualFile getOrCreateDirectory(CharSequence entryName, Map<String, FastJarVirtualFile> directories) {
        String string = entryName.toString();
        FastJarVirtualFile fastJarVirtualFile = directories.get(string);
        if (fastJarVirtualFile == null) {
            Pair<CharSequence, CharSequence> pairSplitPath = splitPath(entryName);
            FastJarVirtualFile fastJarVirtualFile2 = new FastJarVirtualFile(this, (CharSequence) pairSplitPath.component2(), -1L, getOrCreateDirectory((CharSequence) pairSplitPath.component1(), directories), null);
            directories.put(string, fastJarVirtualFile2);
            fastJarVirtualFile = fastJarVirtualFile2;
        }
        return fastJarVirtualFile;
    }

    private final Pair<CharSequence, CharSequence> splitPath(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        while (length >= 0 && charSequence.charAt(length) != '/') {
            length--;
        }
        return length == -1 ? new Pair<>(Argument.Delimiters.none, charSequence) : new Pair<>(charSequence.subSequence(0, length), charSequence.subSequence(length + 1, charSequence.length()));
    }

    public final byte[] contentsToByteArray(ZipEntryDescription zipEntryDescription) throws FileNotFoundException {
        byte[] bArrContentsToByteArray;
        zipEntryDescription.getClass();
        CharSequence relativePath = zipEntryDescription.getRelativePath();
        if (StringUtil.equals(relativePath, "META-INF/MANIFEST.MF")) {
            byte[] bArr = this.cachedManifest;
            if (bArr != null) {
                return bArr;
            }
            throw new FileNotFoundException(this.file + "!/" + ((Object) relativePath));
        }
        FileAccessorCache.Handle handle = this.fileSystem.getCachedOpenFileHandles$kotlin_compiler().get(this.file);
        try {
            handle.getClass();
            synchronized (handle) {
                bArrContentsToByteArray = ZipImplementationKt.contentsToByteArray((LargeDynamicMappedBuffer) ((Pair) handle.get()).getSecond(), zipEntryDescription);
            }
            CloseableKt.closeFinally(handle, (Throwable) null);
            return bArrContentsToByteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(handle, th);
                throw th2;
            }
        }
    }

    public final VirtualFile findFileByPath(String pathInJar) {
        pathInJar.getClass();
        VirtualFile virtualFile = this.myRoot;
        if (virtualFile != null) {
            return virtualFile.findFileByRelativePath(pathInJar);
        }
        return null;
    }

    /* JADX INFO: renamed from: getFile$kotlin_compiler, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    public final FastJarFileSystem getFileSystem() {
        return this.fileSystem;
    }
}
