package com.intellij.util.io;

import com.intellij.util.CompressionUtil;
import com.intellij.util.io.PersistentMapWalPlayer;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001b0\u001aJ\b\u0010\u001c\u001a\u00020\u0017H\u0016J\u0016\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001bH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\rH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/intellij/util/io/PersistentMapWalPlayer;", "K", "V", "Ljava/io/Closeable;", "keyDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "valueExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "file", "Ljava/nio/file/Path;", "<init>", "(Lcom/intellij/util/io/KeyDescriptor;Lcom/intellij/util/io/DataExternalizer;Ljava/nio/file/Path;)V", "input", "Ljava/io/DataInputStream;", "useCompression", "", "getUseCompression$intellij_platform_util", "()Z", "version", "", "getVersion", "()I", "ensureCompatible", "", "expectedVersion", "readWal", "Lkotlin/sequences/Sequence;", "Lcom/intellij/util/io/WalEvent;", "close", "readNextEvent", "readByteArray", "", "inputStream", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PersistentMapWalPlayer<K, V> implements Closeable {
    private final DataInputStream input;
    private final KeyDescriptor<K> keyDescriptor;
    private final boolean useCompression;
    private final DataExternalizer<V> valueExternalizer;
    private final int version;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WalOpCode.values().length];
            try {
                iArr[WalOpCode.PUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WalOpCode.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WalOpCode.APPEND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PersistentMapWalPlayer(KeyDescriptor<K> keyDescriptor, DataExternalizer<V> dataExternalizer, Path path) throws IOException {
        keyDescriptor.getClass();
        dataExternalizer.getClass();
        path.getClass();
        this.keyDescriptor = keyDescriptor;
        this.valueExternalizer = dataExternalizer;
        if (!Files.exists(path, new LinkOption[0])) {
            throw new FileNotFoundException(path.toString());
        }
        InputStream inputStreamNewInputStream = Files.newInputStream(path, new OpenOption[0]);
        inputStreamNewInputStream.getClass();
        DataInputStream dataInputStream = new DataInputStream(inputStreamNewInputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStreamNewInputStream : new BufferedInputStream(inputStreamNewInputStream, 8192));
        this.input = dataInputStream;
        ensureCompatible(this.version, dataInputStream, path);
        this.useCompression = dataInputStream.readBoolean();
    }

    public static WalEvent a(PersistentMapWalPlayer persistentMapWalPlayer) {
        return persistentMapWalPlayer.readNextEvent();
    }

    private final void ensureCompatible(int expectedVersion, DataInputStream input, Path file) throws IOException {
        int i = DataInputOutputUtil.readINT(input);
        if (i != expectedVersion) {
            throw new VersionUpdatedException(file, Integer.valueOf(expectedVersion), Integer.valueOf(i));
        }
    }

    private final byte[] readByteArray(DataInputStream inputStream) throws IOException {
        if (this.useCompression) {
            byte[] compressed = CompressionUtil.readCompressed(inputStream);
            compressed.getClass();
            return compressed;
        }
        byte[] bArr = new byte[inputStream.readInt()];
        inputStream.readFully(bArr);
        return bArr;
    }

    private final WalEvent<K, V> readNextEvent() throws IOException {
        try {
            WalRecord walRecord = WalRecord.INSTANCE.read(this.input);
            if (WriteAheadLogKt.getDebugWalRecords()) {
                System.out.println((Object) ("read: " + walRecord));
            }
            DataInputStream inputStream = walRecord.getPayload().toInputStream();
            inputStream.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[walRecord.getOpCode().ordinal()];
            if (i == 1) {
                return new WalEvent.PutEvent(this.keyDescriptor.read(inputStream), WriteAheadLogKt.readData(readByteArray(inputStream), this.valueExternalizer));
            }
            if (i == 2) {
                return new WalEvent.RemoveEvent(this.keyDescriptor.read(inputStream));
            }
            if (i == 3) {
                return new WalEvent.AppendEvent(this.keyDescriptor.read(inputStream), readByteArray(inputStream));
            }
            bu8.a();
            return null;
        } catch (EndOfLog unused) {
            return null;
        } catch (IOException unused2) {
            WalEvent.CorruptionEvent corruptionEvent = WalEvent.CorruptionEvent.INSTANCE;
            corruptionEvent.getClass();
            return corruptionEvent;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.input.close();
    }

    /* JADX INFO: renamed from: getUseCompression$intellij_platform_util, reason: from getter */
    public final boolean getUseCompression() {
        return this.useCompression;
    }

    public final Sequence<WalEvent<K, V>> readWal() {
        return SequencesKt.generateSequence(new Function0() { // from class: k1b
            public final Object invoke() {
                return PersistentMapWalPlayer.a(this.b);
            }
        });
    }
}
