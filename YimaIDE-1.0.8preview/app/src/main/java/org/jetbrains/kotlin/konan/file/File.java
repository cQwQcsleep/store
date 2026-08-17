package org.jetbrains.kotlin.konan.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;
import org.jetbrains.kotlin.util.UtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\t\b\u0086\b\u0018\u0000 q2\u00020\u0001:\u0001qB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0000¢\u0006\u0002\u0010\bB\u000f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bB\r\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\u000e\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020>J\u0014\u0010?\u001a\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00050BJ\u000e\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020\u0005J\u0006\u0010E\u001a\u00020FJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u0005J\u000e\u0010G\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\bHJ\u0013\u0010I\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u000e\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020\u0000J\u000e\u0010L\u001a\u00020@2\u0006\u0010M\u001a\u00020\u0005J\u0006\u0010N\u001a\u00020\u001bJ\u0006\u0010O\u001a\u00020\u001bJ\u0006\u0010P\u001a\u00020\u0000J\u0006\u0010Q\u001a\u00020@J\u0006\u0010R\u001a\u00020@J\u0013\u0010S\u001a\u00020\u001b2\b\u0010T\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u001a\u0010U\u001a\u00020@2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020@0WJ\b\u0010X\u001a\u00020YH\u0016J$\u0010Z\u001a\u00020[2\b\b\u0002\u0010\\\u001a\u00020]2\b\b\u0002\u0010^\u001a\u0002092\b\b\u0002\u00108\u001a\u000209J\u0006\u0010_\u001a\u00020\u0003J\u0006\u0010`\u001a\u00020aJ\u001a\u0010b\u001a\u00020@2\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020@0WJ\u001a\u0010d\u001a\u00020@2\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020@0WJ\u0006\u0010e\u001a\u00020fJ\u0006\u0010g\u001a\u00020>J\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00050iJ\u000e\u0010j\u001a\u00020\u001b2\u0006\u0010K\u001a\u00020\u0000J\u000e\u0010k\u001a\u00020\u001b2\u0006\u0010l\u001a\u00020\u0000J\b\u0010m\u001a\u00020\u0005H\u0016J\u000e\u0010n\u001a\u00020\u00032\u0006\u0010=\u001a\u00020>J\u0014\u0010o\u001a\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00050BJ\u000e\u0010p\u001a\u00020@2\u0006\u0010D\u001a\u00020\u0005R\u0011\u0010\u000e\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010 \u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b#\u0010\u001dR\u0011\u0010$\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b$\u0010\u001dR\u0011\u0010%\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b%\u0010\u001dR\u0014\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00000)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000)8F¢\u0006\u0006\u001a\u0004\b-\u0010+R\u0011\u0010.\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b/\u0010\u0013R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00050)8F¢\u0006\u0006\u001a\u0004\b1\u0010+R\u0011\u0010\u0002\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b2\u0010\u0013R\u0011\u00103\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b4\u0010\u0010R\u0013\u00105\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b6\u0010\u0013R\u0011\u0010\t\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b7\u0010\u0013R\u0011\u00108\u001a\u0002098F¢\u0006\u0006\u001a\u0004\b:\u0010;¨\u0006r"}, d2 = {"Lorg/jetbrains/kotlin/konan/file/File;", "", "parent", "Ljava/nio/file/Path;", "child", "", "(Ljava/nio/file/Path;Ljava/lang/String;)V", "(Lorg/jetbrains/kotlin/konan/file/File;Ljava/lang/String;)V", "(Lorg/jetbrains/kotlin/konan/file/File;Lorg/jetbrains/kotlin/konan/file/File;)V", "path", "(Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "javaPath", "(Ljava/nio/file/Path;)V", "absoluteFile", "getAbsoluteFile", "()Lorg/jetbrains/kotlin/konan/file/File;", "absolutePath", "getAbsolutePath", "()Ljava/lang/String;", "canonicalFile", "getCanonicalFile", "canonicalPath", "getCanonicalPath", "canonicalPath$delegate", "Lkotlin/Lazy;", "exists", "", "getExists", "()Z", "extension", "getExtension", "fileKey", "getFileKey", "()Ljava/lang/Object;", "isAbsolute", "isDirectory", "isFile", "getJavaPath$kotlin_util_io", "()Ljava/nio/file/Path;", "listFiles", "", "getListFiles", "()Ljava/util/List;", "listFilesOrEmpty", "getListFilesOrEmpty", "name", "getName", "nameSegments", "getNameSegments", "getParent", "parentFile", "getParentFile", "parentOrNull", "getParentOrNull", "getPath", "size", "", "getSize", "()J", "appendBytes", "bytes", "", "appendLines", "", "lines", "", "appendText", "text", "bufferedReader", "Ljava/io/BufferedReader;", "component1", "component1$kotlin_util_io", "copy", "copyTo", "destination", "createAsSymlink", "target", "createNew", "delete", "deleteOnExit", "deleteOnExitRecursively", "deleteRecursively", "equals", "other", "forEachLine", "action", "Lkotlin/Function1;", "hashCode", "", "map", "Ljava/nio/MappedByteBuffer;", "mode", "Ljava/nio/channels/FileChannel$MapMode;", "start", "mkdirs", "outputStream", "Ljava/io/OutputStream;", "postorder", "task", "preorder", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readStrings", "", "renameTo", "startsWith", "another", "toString", "writeBytes", "writeLines", "writeText", "Companion", "kotlin-util-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class File {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String pathSeparator;
    private static final String separator;
    private static final char separatorChar;

    /* JADX INFO: renamed from: canonicalPath$delegate, reason: from kotlin metadata */
    private final Lazy canonicalPath;
    private final Path javaPath;

    static {
        String str = java.io.File.pathSeparator;
        str.getClass();
        pathSeparator = str;
        String str2 = java.io.File.separator;
        str2.getClass();
        separator = str2;
        separatorChar = java.io.File.separatorChar;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public File(File file, File file2) {
        file.getClass();
        file2.getClass();
        Path pathResolve = file.javaPath.resolve(file2.javaPath);
        pathResolve.getClass();
        this(pathResolve);
    }

    public static /* synthetic */ File copy$default(File file, Path path, int i, Object obj) {
        if ((i & 1) != 0) {
            path = file.javaPath;
        }
        return file.copy(path);
    }

    public static /* synthetic */ MappedByteBuffer map$default(File file, FileChannel.MapMode mapMode, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            mapMode = FileChannel.MapMode.READ_ONLY;
            mapMode.getClass();
        }
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            j2 = -1;
        }
        return file.map(mapMode, j, j2);
    }

    public final Path appendBytes(byte[] bytes) throws IOException {
        bytes.getClass();
        Path pathWrite = Files.write(this.javaPath, bytes, StandardOpenOption.APPEND);
        pathWrite.getClass();
        return pathWrite;
    }

    public final void appendLines(Iterable<String> lines) throws IOException {
        lines.getClass();
        Files.write(this.javaPath, lines, StandardOpenOption.APPEND);
    }

    public final void appendText(String text) throws IOException {
        text.getClass();
        appendLines(CollectionsKt.listOf(text));
    }

    public final BufferedReader bufferedReader() throws IOException {
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader(this.javaPath);
        bufferedReaderNewBufferedReader.getClass();
        return bufferedReaderNewBufferedReader;
    }

    public final File child(String name) {
        name.getClass();
        return new File(this, name);
    }

    /* JADX INFO: renamed from: component1$kotlin_util_io, reason: from getter */
    public final Path getJavaPath() {
        return this.javaPath;
    }

    public final File copy(Path javaPath) {
        javaPath.getClass();
        return new File(javaPath);
    }

    public final void copyTo(File destination) throws IOException {
        destination.getClass();
        Files.copy(this.javaPath, destination.javaPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public final void createAsSymlink(String target) throws IOException {
        target.getClass();
        Path path = Paths.get(target, new String[0]);
        if (Files.isSymbolicLink(this.javaPath) && Intrinsics.areEqual(Files.readSymbolicLink(this.javaPath), path)) {
            return;
        }
        Files.createSymbolicLink(this.javaPath, path, new FileAttribute[0]);
    }

    public final boolean createNew() {
        return this.javaPath.toFile().createNewFile();
    }

    public final boolean delete() {
        return Files.deleteIfExists(this.javaPath);
    }

    public final File deleteOnExit() {
        this.javaPath.toFile().deleteOnExit();
        return this;
    }

    public final void deleteOnExitRecursively() throws IOException {
        preorder(new Function1<Path, Unit>() { // from class: org.jetbrains.kotlin.konan.file.File.deleteOnExitRecursively.1
            public final void invoke(Path path) {
                path.getClass();
                new File(path).deleteOnExit();
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Path) obj);
                return Unit.INSTANCE;
            }
        });
    }

    public final void deleteRecursively() throws IOException {
        postorder(new Function1<Path, Unit>() { // from class: org.jetbrains.kotlin.konan.file.File.deleteRecursively.1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
                invoke((Path) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Path path) throws IOException {
                path.getClass();
                Files.delete(path);
            }
        });
    }

    public boolean equals(Object other) {
        File file = other instanceof File ? (File) other : null;
        if (file == null) {
            return false;
        }
        return Intrinsics.areEqual(file.javaPath.toAbsolutePath(), this.javaPath.toAbsolutePath());
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x001a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void forEachLine(final Function1<? super String, Unit> action) throws IOException {
        action.getClass();
        Stream<String> streamLines = Files.lines(this.javaPath);
        boolean z = false;
        try {
            streamLines.forEach(new Consumer() { // from class: org.jetbrains.kotlin.konan.file.File$forEachLine$1$1
                @Override // java.util.function.Consumer
                public final void accept(String str) {
                    Function1<String, Unit> function1 = action;
                    str.getClass();
                    function1.invoke(str);
                }
            });
            Unit unit = Unit.INSTANCE;
            if (streamLines != null) {
                hv3.a(streamLines);
            }
        } catch (Exception e) {
            z = true;
            if (streamLines != null) {
                try {
                    hv3.a(streamLines);
                } catch (Exception unused) {
                }
            }
            throw e;
        }
    }

    public final File getAbsoluteFile() {
        return new File(getAbsolutePath());
    }

    public final String getAbsolutePath() {
        return this.javaPath.toAbsolutePath().toString();
    }

    public final File getCanonicalFile() {
        return new File(getCanonicalPath());
    }

    public final String getCanonicalPath() {
        Object value = this.canonicalPath.getValue();
        value.getClass();
        return (String) value;
    }

    public final boolean getExists() {
        return Files.exists(this.javaPath, new LinkOption[0]);
    }

    public final String getExtension() {
        return StringsKt.substringAfterLast(getName(), AbiCompoundName.SEPARATOR, "");
    }

    public final Object getFileKey() {
        Object objFileKey = Files.readAttributes(this.javaPath, BasicFileAttributes.class, new LinkOption[0]).fileKey();
        if (objFileKey == null) {
            objFileKey = getCanonicalPath();
        }
        objFileKey.getClass();
        return objFileKey;
    }

    public final Path getJavaPath$kotlin_util_io() {
        return this.javaPath;
    }

    public final List<File> getListFiles() throws Throwable {
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(this.javaPath);
        boolean z = false;
        try {
            directoryStreamNewDirectoryStream.getClass();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directoryStreamNewDirectoryStream, 10));
            Iterator<Path> it = directoryStreamNewDirectoryStream.iterator();
            while (it.hasNext()) {
                arrayList.add(new File(it.next()));
            }
            if (directoryStreamNewDirectoryStream != null) {
                hv3.a(directoryStreamNewDirectoryStream);
            }
            return arrayList;
        } catch (Exception e) {
            if (directoryStreamNewDirectoryStream != null) {
                try {
                    try {
                        hv3.a(directoryStreamNewDirectoryStream);
                    } catch (Exception unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    z = true;
                    if (!z && directoryStreamNewDirectoryStream != null) {
                        hv3.a(directoryStreamNewDirectoryStream);
                    }
                    throw th;
                }
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            if (!z) {
                hv3.a(directoryStreamNewDirectoryStream);
            }
            throw th;
        }
    }

    public final List<File> getListFilesOrEmpty() {
        return getExists() ? getListFiles() : CollectionsKt.emptyList();
    }

    public final String getName() {
        return UtilKt.removeSuffixIfPresent(this.javaPath.getFileName().toString(), separator);
    }

    public final List<String> getNameSegments() {
        Path path = this.javaPath;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(path, 10));
        Iterator it = path.iterator();
        while (it.hasNext()) {
            arrayList.add(((Path) it.next()).getFileName().toString());
        }
        return arrayList;
    }

    public final String getParent() {
        String parentOrNull = getParentOrNull();
        parentOrNull.getClass();
        return parentOrNull;
    }

    public final File getParentFile() {
        Path parent = this.javaPath.getParent();
        parent.getClass();
        return new File(parent);
    }

    public final String getParentOrNull() {
        Path parent = this.javaPath.getParent();
        if (parent != null) {
            return parent.toString();
        }
        return null;
    }

    public final String getPath() {
        return this.javaPath.toString();
    }

    public final long getSize() {
        return Files.size(this.javaPath);
    }

    public int hashCode() {
        return this.javaPath.toAbsolutePath().hashCode();
    }

    public final boolean isAbsolute() {
        return this.javaPath.isAbsolute();
    }

    public final boolean isDirectory() {
        return Files.isDirectory(this.javaPath, new LinkOption[0]);
    }

    public final boolean isFile() {
        return Files.isRegularFile(this.javaPath, new LinkOption[0]);
    }

    public final MappedByteBuffer map(FileChannel.MapMode mode, long start, long size) throws IOException {
        mode.getClass();
        String path = getPath();
        FileChannel.MapMode mapMode = FileChannel.MapMode.READ_ONLY;
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, Intrinsics.areEqual(mode, mapMode) ? "r" : "rw");
        if (Intrinsics.areEqual(mode, mapMode)) {
            size = randomAccessFile.length();
        }
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer map = channel.map(mode, start, size);
        channel.close();
        map.getClass();
        return map;
    }

    public final Path mkdirs() throws IOException {
        Path pathCreateDirectories = Files.createDirectories(this.javaPath, new FileAttribute[0]);
        pathCreateDirectories.getClass();
        return pathCreateDirectories;
    }

    public final OutputStream outputStream() throws IOException {
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(this.javaPath, new OpenOption[0]);
        outputStreamNewOutputStream.getClass();
        return outputStreamNewOutputStream;
    }

    public final void postorder(final Function1<? super Path, Unit> task) throws IOException {
        task.getClass();
        if (getExists()) {
            Files.walkFileTree(this.javaPath, new SimpleFileVisitor<Path>() { // from class: org.jetbrains.kotlin.konan.file.File.postorder.1
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    dir.getClass();
                    task.invoke(dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    file.getClass();
                    attrs.getClass();
                    task.invoke(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public final void preorder(final Function1<? super Path, Unit> task) throws IOException {
        task.getClass();
        if (getExists()) {
            Files.walkFileTree(this.javaPath, new SimpleFileVisitor<Path>() { // from class: org.jetbrains.kotlin.konan.file.File.preorder.1
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    dir.getClass();
                    attrs.getClass();
                    task.invoke(dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    file.getClass();
                    attrs.getClass();
                    task.invoke(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public final PrintWriter printWriter() {
        java.io.File file = this.javaPath.toFile();
        file.getClass();
        return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8), 8192));
    }

    public final byte[] readBytes() throws IOException {
        byte[] allBytes = Files.readAllBytes(this.javaPath);
        allBytes.getClass();
        return allBytes;
    }

    public final List<String> readStrings() throws IOException {
        final ArrayList arrayList = new ArrayList();
        forEachLine(new Function1<String, Unit>() { // from class: org.jetbrains.kotlin.konan.file.File$readStrings$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(String str) {
                str.getClass();
                arrayList.add(str);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((String) obj);
                return Unit.INSTANCE;
            }
        });
        return arrayList;
    }

    public final boolean renameTo(File destination) {
        destination.getClass();
        return this.javaPath.toFile().renameTo(destination.javaPath.toFile());
    }

    public final boolean startsWith(File another) {
        another.getClass();
        return this.javaPath.startsWith(another.javaPath);
    }

    public String toString() {
        return getPath();
    }

    public final Path writeBytes(byte[] bytes) throws IOException {
        bytes.getClass();
        Path pathWrite = Files.write(this.javaPath, bytes, new OpenOption[0]);
        pathWrite.getClass();
        return pathWrite;
    }

    public final void writeLines(Iterable<String> lines) throws IOException {
        lines.getClass();
        Files.write(this.javaPath, lines, new OpenOption[0]);
    }

    public final void writeText(String text) throws IOException {
        text.getClass();
        writeLines(CollectionsKt.listOf(text));
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/konan/file/File$Companion;", "", "()V", "javaHome", "Lorg/jetbrains/kotlin/konan/file/File;", "getJavaHome", "()Lorg/jetbrains/kotlin/konan/file/File;", "pathSeparator", "", "getPathSeparator", "()Ljava/lang/String;", "separator", "getSeparator", "separatorChar", "", "getSeparatorChar", "()C", "userDir", "getUserDir", "userHome", "getUserHome", "kotlin-util-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final File getJavaHome() {
            String property = System.getProperty("java.home");
            property.getClass();
            return new File(property);
        }

        public final String getPathSeparator() {
            return File.pathSeparator;
        }

        public final String getSeparator() {
            return File.separator;
        }

        public final char getSeparatorChar() {
            return File.separatorChar;
        }

        public final File getUserDir() {
            String property = System.getProperty("user.dir");
            property.getClass();
            return new File(property);
        }

        public final File getUserHome() {
            String property = System.getProperty("user.home");
            property.getClass();
            return new File(property);
        }

        private Companion() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public File(Path path, String str) {
        path.getClass();
        str.getClass();
        Path pathResolve = path.resolve(str);
        pathResolve.getClass();
        this(pathResolve);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public File(File file, String str) {
        file.getClass();
        str.getClass();
        Path pathResolve = file.javaPath.resolve(str);
        pathResolve.getClass();
        this(pathResolve);
    }

    public File(Path path) {
        path.getClass();
        this.javaPath = path;
        this.canonicalPath = LazyKt.lazy(new Function0<String>() { // from class: org.jetbrains.kotlin.konan.file.File$canonicalPath$2
            {
                super(0);
            }

            public final String invoke() {
                return this.this$0.getJavaPath$kotlin_util_io().toFile().getCanonicalPath();
            }
        });
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public File(String str) {
        str.getClass();
        Path path = Paths.get(str, new String[0]);
        path.getClass();
        this(path);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public File(String str, String str2) {
        str.getClass();
        str2.getClass();
        Path path = Paths.get(str, str2);
        path.getClass();
        this(path);
    }
}
