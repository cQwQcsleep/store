package org.jetbrains.kotlin.incremental.classpathDiff;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FileWalkDirection;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequencesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JF\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000726\u0010\t\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u000e0\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryReader;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryOrJarReader;", "directory", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "getUnixStyleRelativePaths", "", "", "filter", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "unixStyleRelativePath", "", "isDirectory", "readBytes", "", "close", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class DirectoryReader implements DirectoryOrJarReader {
    private final File directory;

    public DirectoryReader(File file) {
        file.getClass();
        this.directory = file;
    }

    public static boolean a(Function2 function2, DirectoryReader directoryReader, File file) {
        file.getClass();
        return ((Boolean) function2.invoke(FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo(file, directoryReader.directory)), Boolean.valueOf(file.isDirectory()))).booleanValue();
    }

    public static String c(DirectoryReader directoryReader, File file) {
        file.getClass();
        return FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo(file, directoryReader.directory));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // org.jetbrains.kotlin.incremental.classpathDiff.DirectoryOrJarReader
    public List<String> getUnixStyleRelativePaths(final Function2<? super String, ? super Boolean, Boolean> filter) {
        filter.getClass();
        return SequencesKt.toList(SequencesKt.sorted(SequencesKt.map(SequencesKt.filter(FilesKt.walk$default(this.directory, (FileWalkDirection) null, 1, (Object) null), new Function1() { // from class: org.jetbrains.kotlin.incremental.classpathDiff.c
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DirectoryReader.a(filter, this, (File) obj));
            }
        }), new Function1() { // from class: org.jetbrains.kotlin.incremental.classpathDiff.d
            public final Object invoke(Object obj) {
                return DirectoryReader.c(this.b, (File) obj);
            }
        })));
    }

    @Override // org.jetbrains.kotlin.incremental.classpathDiff.DirectoryOrJarReader
    public byte[] readBytes(String unixStyleRelativePath) {
        unixStyleRelativePath.getClass();
        return FilesKt.readBytes(FilesKt.resolve(this.directory, unixStyleRelativePath));
    }
}
