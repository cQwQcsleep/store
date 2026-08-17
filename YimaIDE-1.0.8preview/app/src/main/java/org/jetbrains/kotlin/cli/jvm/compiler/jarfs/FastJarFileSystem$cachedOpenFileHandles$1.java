package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import com.intellij.util.io.FileAccessorCache;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.FastJarFileSystem$cachedOpenFileHandles$1;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u00060\u0001J \u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0014J \u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006H\u0014J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¨\u0006\u0010"}, d2 = {"org/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem$cachedOpenFileHandles$1", "Lcom/intellij/util/io/FileAccessorCache;", "Ljava/io/File;", "Lkotlin/Pair;", "Ljava/io/RandomAccessFile;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/RandomAccessFileAndBuffer;", "createAccessor", "file", "disposeAccessor", Argument.Delimiters.none, "fileAccessor", "isEqual", Argument.Delimiters.none, "val1", "val2", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FastJarFileSystem$cachedOpenFileHandles$1 extends FileAccessorCache<File, Pair<? extends RandomAccessFile, ? extends LargeDynamicMappedBuffer>> {
    final /* synthetic */ FastJarFileSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FastJarFileSystem$cachedOpenFileHandles$1(FastJarFileSystem fastJarFileSystem) {
        super(20, 10);
        this.this$0 = fastJarFileSystem;
    }

    public static MappedByteBuffer c(RandomAccessFile randomAccessFile, long j, long j2) throws IOException {
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j, j2);
        map.getClass();
        return map;
    }

    public Pair<RandomAccessFile, LargeDynamicMappedBuffer> createAccessor(File file) throws IOException {
        file.getClass();
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        Function2 function2 = new Function2() { // from class: xn4
            public final Object invoke(Object obj, Object obj2) {
                return FastJarFileSystem$cachedOpenFileHandles$1.c(randomAccessFile, ((Long) obj).longValue(), ((Long) obj2).longValue());
            }
        };
        Function1<MappedByteBuffer, Unit> unmapBuffer$kotlin_compiler = this.this$0.getUnmapBuffer$kotlin_compiler();
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byteOrder.getClass();
        return new Pair<>(randomAccessFile, new LargeDynamicMappedBuffer(length, function2, unmapBuffer$kotlin_compiler, byteOrder));
    }

    public void disposeAccessor(Pair<? extends RandomAccessFile, LargeDynamicMappedBuffer> fileAccessor) throws IOException {
        fileAccessor.getClass();
        ((RandomAccessFile) fileAccessor.getFirst()).close();
        ((LargeDynamicMappedBuffer) fileAccessor.getSecond()).unmap();
    }

    public boolean isEqual(File val1, File val2) {
        val1.getClass();
        val2.getClass();
        return Intrinsics.areEqual(val1, val2);
    }
}
