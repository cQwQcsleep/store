package com.intellij.util.io;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.CompressionUtil;
import com.intellij.util.io.DataOutputStream;
import com.intellij.util.io.PersistentMapWal;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003BG\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0014\u0010\u001a\u001a\u00020\u0018*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0012H\u0002J\f\u0010\u001d\u001a\u00020\u001b*\u00020\u001eH\u0002J\u001d\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00028\u00002\u0006\u0010\"\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00020 2\u0006\u0010!\u001a\u00028\u00002\u0006\u0010%\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010&J\u0015\u0010'\u001a\u00020 2\u0006\u0010!\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010(J\f\u0010)\u001a\u00020\u0018*\u00020 H\u0002J\u001b\u0010*\u001a\u00020\u00182\u0006\u0010!\u001a\u00028\u00002\u0006\u0010\"\u001a\u00020\u001e¢\u0006\u0002\u0010+J\u001b\u0010,\u001a\u00020\u00182\u0006\u0010!\u001a\u00028\u00002\u0006\u0010%\u001a\u00028\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020\u00182\u0006\u0010!\u001a\u00028\u0000¢\u0006\u0002\u0010/J\u0006\u00100\u001a\u00020\u0018J\b\u00101\u001a\u00020\u0018H\u0016J\u0006\u00102\u001a\u00020\u0018R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u00063"}, d2 = {"Lcom/intellij/util/io/PersistentMapWal;", "K", "V", "Ljava/io/Closeable;", "keyDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "valueExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "useCompression", "", "file", "Ljava/nio/file/Path;", "walIoExecutor", "Ljava/util/concurrent/ExecutorService;", "compact", "<init>", "(Lcom/intellij/util/io/KeyDescriptor;Lcom/intellij/util/io/DataExternalizer;ZLjava/nio/file/Path;Ljava/util/concurrent/ExecutorService;Z)V", "out", "Lcom/intellij/util/io/DataOutputStream;", "version", "", "getVersion", "()I", "ensureCompatible", "", "expectedVersion", "write", "", "outputStream", "writeToByteArray", "Lcom/intellij/util/io/AppendablePersistentMap$ValueDataAppender;", "appendRecord", "Lcom/intellij/util/io/WalRecord;", "key", "appender", "(Ljava/lang/Object;Lcom/intellij/util/io/AppendablePersistentMap$ValueDataAppender;)Lcom/intellij/util/io/WalRecord;", "putRecord", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/util/io/WalRecord;", "removeRecord", "(Ljava/lang/Object;)Lcom/intellij/util/io/WalRecord;", "submitWrite", "appendData", "(Ljava/lang/Object;Lcom/intellij/util/io/AppendablePersistentMap$ValueDataAppender;)V", "put", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)V", "flush", "close", "closeAndDelete", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PersistentMapWal<K, V> implements Closeable {
    private final Path file;
    private final KeyDescriptor<K> keyDescriptor;
    private final DataOutputStream out;
    private final boolean useCompression;
    private final DataExternalizer<V> valueExternalizer;
    private final int version;
    private final ExecutorService walIoExecutor;

    public PersistentMapWal(KeyDescriptor<K> keyDescriptor, DataExternalizer<V> dataExternalizer, boolean z, Path path, ExecutorService executorService, boolean z2) throws IOException {
        Path pathTryCompact;
        keyDescriptor.getClass();
        dataExternalizer.getClass();
        path.getClass();
        executorService.getClass();
        this.keyDescriptor = keyDescriptor;
        this.valueExternalizer = dataExternalizer;
        this.useCompression = z;
        this.file = path;
        this.walIoExecutor = executorService;
        if (z2 && (pathTryCompact = WriteAheadLogKt.tryCompact(path, keyDescriptor, dataExternalizer)) != null) {
            FileUtil.deleteWithRenaming(path);
            FileUtil.rename(pathTryCompact.toFile(), path.toFile());
        }
        ensureCompatible(this.version, z, path);
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        outputStreamNewOutputStream.getClass();
        this.out = new DataOutputStream(outputStreamNewOutputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStreamNewOutputStream : new BufferedOutputStream(outputStreamNewOutputStream, 8192));
    }

    public static void a(WalRecord walRecord, PersistentMapWal persistentMapWal) throws IOException {
        if (WriteAheadLogKt.getDebugWalRecords()) {
            System.out.println((Object) ("write: " + walRecord));
        }
        walRecord.write(persistentMapWal.out);
    }

    private final WalRecord appendRecord(final K key, final AppendablePersistentMap.ValueDataAppender appender) {
        return WalRecord.INSTANCE.writeRecord(WalOpCode.APPEND, new Function1() { // from class: g1b
            public final Object invoke(Object obj) {
                return PersistentMapWal.e(this.b, key, appender, (DataOutputStream) obj);
            }
        });
    }

    public static void c(PersistentMapWal persistentMapWal) throws IOException {
        persistentMapWal.out.close();
    }

    public static Unit d(PersistentMapWal persistentMapWal, Object obj, Object obj2, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.getClass();
        persistentMapWal.keyDescriptor.save(dataOutputStream, obj);
        persistentMapWal.write(WriteAheadLogKt.writeData(obj2, persistentMapWal.valueExternalizer), dataOutputStream);
        return Unit.INSTANCE;
    }

    public static Unit e(PersistentMapWal persistentMapWal, Object obj, AppendablePersistentMap.ValueDataAppender valueDataAppender, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.getClass();
        persistentMapWal.keyDescriptor.save(dataOutputStream, obj);
        persistentMapWal.write(persistentMapWal.writeToByteArray(valueDataAppender), dataOutputStream);
        return Unit.INSTANCE;
    }

    private final void ensureCompatible(int expectedVersion, boolean useCompression, Path file) throws IOException {
        if (!Files.exists(file, new LinkOption[0])) {
            Files.createDirectories(file.getParent(), new FileAttribute[0]);
            DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
            try {
                DataInputOutputUtil.writeINT(dataOutputStream, expectedVersion);
                dataOutputStream.writeBoolean(useCompression);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(dataOutputStream, (Throwable) null);
                return;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(dataOutputStream, th);
                    throw th2;
                }
            }
        }
        DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(file, StandardOpenOption.READ));
        try {
            Pair pair = TuplesKt.to(Integer.valueOf(DataInputOutputUtil.readINT(dataInputStream)), Boolean.valueOf(dataInputStream.readBoolean()));
            CloseableKt.closeFinally(dataInputStream, (Throwable) null);
            int iIntValue = ((Number) pair.component1()).intValue();
            Boolean bool = (Boolean) pair.component2();
            boolean zBooleanValue = bool.booleanValue();
            if (iIntValue != expectedVersion) {
                throw new VersionUpdatedException(file, Integer.valueOf(expectedVersion), Integer.valueOf(iIntValue));
            }
            if (zBooleanValue != useCompression) {
                throw new VersionUpdatedException(file, Boolean.valueOf(useCompression), bool);
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(dataInputStream, th3);
                throw th4;
            }
        }
    }

    public static Unit f(PersistentMapWal persistentMapWal, Object obj, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.getClass();
        persistentMapWal.keyDescriptor.save(dataOutputStream, obj);
        return Unit.INSTANCE;
    }

    public static void g(PersistentMapWal persistentMapWal) throws IOException {
        persistentMapWal.out.flush();
    }

    private final WalRecord putRecord(final K key, final V value) {
        return WalRecord.INSTANCE.writeRecord(WalOpCode.PUT, new Function1() { // from class: f1b
            public final Object invoke(Object obj) {
                return PersistentMapWal.d(this.b, key, value, (DataOutputStream) obj);
            }
        });
    }

    private final WalRecord removeRecord(final K key) {
        return WalRecord.INSTANCE.writeRecord(WalOpCode.REMOVE, new Function1() { // from class: j1b
            public final Object invoke(Object obj) {
                return PersistentMapWal.f(this.b, key, (DataOutputStream) obj);
            }
        });
    }

    private final void submitWrite(final WalRecord walRecord) {
        this.walIoExecutor.submit(new Runnable() { // from class: com.intellij.util.io.l
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                PersistentMapWal.a(walRecord, this);
            }
        });
    }

    private final void write(byte[] bArr, DataOutputStream dataOutputStream) throws IOException {
        if (this.useCompression) {
            CompressionUtil.writeCompressed(dataOutputStream, bArr, 0, bArr.length);
        } else {
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
    }

    private final byte[] writeToByteArray(AppendablePersistentMap.ValueDataAppender valueDataAppender) throws IOException {
        UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
        valueDataAppender.append(new DataOutputStream(unsyncByteArrayOutputStream));
        byte[] byteArray = unsyncByteArrayOutputStream.toByteArray();
        byteArray.getClass();
        return byteArray;
    }

    public final void appendData(K key, AppendablePersistentMap.ValueDataAppender appender) throws IOException {
        appender.getClass();
        submitWrite(appendRecord(key, appender));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws ExecutionException, InterruptedException, IOException {
        this.walIoExecutor.submit(new Runnable() { // from class: i1b
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                PersistentMapWal.c(this.b);
            }
        }).get();
    }

    public final void closeAndDelete() throws IOException {
        close();
        FileUtil.deleteWithRenaming(this.file);
    }

    public final void flush() throws IOException {
        this.walIoExecutor.submit(new Runnable() { // from class: h1b
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                PersistentMapWal.g(this.b);
            }
        }).get();
    }

    public final void put(K key, V value) throws IOException {
        submitWrite(putRecord(key, value));
    }

    public final void remove(K key) throws IOException {
        submitWrite(removeRecord(key));
    }

    public /* synthetic */ PersistentMapWal(KeyDescriptor keyDescriptor, DataExternalizer dataExternalizer, boolean z, Path path, ExecutorService executorService, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) throws IOException {
        this(keyDescriptor, dataExternalizer, z, path, executorService, (i & 32) != 0 ? false : z2);
    }
}
