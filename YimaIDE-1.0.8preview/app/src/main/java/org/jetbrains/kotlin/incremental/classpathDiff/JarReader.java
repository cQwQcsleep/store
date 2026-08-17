package org.jetbrains.kotlin.incremental.classpathDiff;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JF\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00100\fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/JarReader;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryOrJarReader;", "jar", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "zipFile", "Ljava/util/zip/ZipFile;", "getUnixStyleRelativePaths", "", "", "filter", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "unixStyleRelativePath", "", "isDirectory", "readBytes", "", "close", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class JarReader implements DirectoryOrJarReader {
    private final ZipFile zipFile;

    public JarReader(File file) {
        file.getClass();
        this.zipFile = new ZipFile(file);
    }

    public static boolean a(Function2 function2, ZipEntry zipEntry) {
        String name = zipEntry.getName();
        name.getClass();
        return ((Boolean) function2.invoke(name, Boolean.valueOf(zipEntry.isDirectory()))).booleanValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zipFile.close();
    }

    @Override // org.jetbrains.kotlin.incremental.classpathDiff.DirectoryOrJarReader
    public List<String> getUnixStyleRelativePaths(final Function2<? super String, ? super Boolean, Boolean> filter) {
        filter.getClass();
        Enumeration<? extends ZipEntry> enumerationEntries = this.zipFile.entries();
        enumerationEntries.getClass();
        Sequence sequenceFilter = SequencesKt.filter(SequencesKt.asSequence(CollectionsKt.iterator(enumerationEntries)), new Function1() { // from class: org.jetbrains.kotlin.incremental.classpathDiff.f
            public final Object invoke(Object obj) {
                return Boolean.valueOf(JarReader.a(filter, (ZipEntry) obj));
            }
        });
        TreeSet treeSetSortedSetOf = SetsKt.sortedSetOf(new String[0]);
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            treeSetSortedSetOf.add(((ZipEntry) it.next()).getName());
        }
        return CollectionsKt.toList(treeSetSortedSetOf);
    }

    @Override // org.jetbrains.kotlin.incremental.classpathDiff.DirectoryOrJarReader
    public byte[] readBytes(String unixStyleRelativePath) throws Throwable {
        unixStyleRelativePath.getClass();
        ZipFile zipFile = this.zipFile;
        InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(unixStyleRelativePath));
        boolean z = false;
        try {
            inputStream.getClass();
            byte[] bytes = ByteStreamsKt.readBytes(inputStream);
            if (inputStream != null) {
                hv3.a(inputStream);
            }
            return bytes;
        } catch (Exception e) {
            if (inputStream != null) {
                try {
                    try {
                        hv3.a(inputStream);
                    } catch (Throwable th) {
                        th = th;
                        z = true;
                        if (!z && inputStream != null) {
                            hv3.a(inputStream);
                        }
                        throw th;
                    }
                } catch (Exception unused) {
                }
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            if (!z) {
                hv3.a(inputStream);
            }
            throw th;
        }
    }
}
