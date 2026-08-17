package com.intellij.util.io;

import com.intellij.openapi.util.io.ByteArraySequence;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.openapi.util.text.StringUtil;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Lcom/intellij/util/io/WalRecord;", "", "opCode", "Lcom/intellij/util/io/WalOpCode;", "checksum", "", "payload", "Lcom/intellij/openapi/util/io/ByteArraySequence;", "<init>", "(Lcom/intellij/util/io/WalOpCode;JLcom/intellij/openapi/util/io/ByteArraySequence;)V", "getOpCode", "()Lcom/intellij/util/io/WalOpCode;", "getPayload", "()Lcom/intellij/openapi/util/io/ByteArraySequence;", "toString", "", "write", "", "target", "Lcom/intellij/util/io/DataOutputStream;", "Companion", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class WalRecord {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long checksum;
    private final WalOpCode opCode;
    private final ByteArraySequence payload;

    public WalRecord(WalOpCode walOpCode, long j, ByteArraySequence byteArraySequence) {
        walOpCode.getClass();
        byteArraySequence.getClass();
        this.opCode = walOpCode;
        this.checksum = j;
        this.payload = byteArraySequence;
    }

    public final WalOpCode getOpCode() {
        return this.opCode;
    }

    public final ByteArraySequence getPayload() {
        return this.payload;
    }

    public String toString() {
        return "record(" + this.opCode + ", checksum = " + this.checksum + ", len = " + this.payload.length() + ", payload = " + StringUtil.toHexString(this.payload.toBytes()) + ')';
    }

    public final void write(DataOutputStream target) throws IOException {
        target.getClass();
        target.writeByte(this.opCode.getCode());
        DataInputOutputUtil.writeLONG(target, this.checksum);
        DataInputOutputUtil.writeINT(target, this.payload.getLength());
        target.write(this.payload.getInternalBuffer(), this.payload.getOffset(), this.payload.getLength());
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tJ\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/intellij/util/io/WalRecord$Companion;", "", "<init>", "()V", "writeRecord", "Lcom/intellij/util/io/WalRecord;", "opCode", "Lcom/intellij/util/io/WalOpCode;", "writer", "Lkotlin/Function1;", "Lcom/intellij/util/io/DataOutputStream;", "", "read", "input", "Ljava/io/DataInputStream;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WalRecord read(DataInputStream input) throws IOException {
            input.getClass();
            try {
                byte b = input.readByte();
                if (b >= WalOpCode.INSTANCE.getSize()) {
                    throw new CorruptionException("no opcode present for code " + ((int) b));
                }
                long j = DataInputOutputUtil.readLONG(input);
                int i = DataInputOutputUtil.readINT(input);
                ChecksumInputStream checksumInputStream = new ChecksumInputStream(input, WriteAheadLogKt.checksumGen);
                byte[] bytes = StreamUtil.readBytes(checksumInputStream, i);
                bytes.getClass();
                long jChecksum = checksumInputStream.checksum();
                if (jChecksum == j) {
                    return new WalRecord(WalOpCode.values()[b], j, new ByteArraySequence(bytes));
                }
                throw new CorruptionException("checksum is wrong for log record: expected = " + j + " but actual = " + jChecksum);
            } catch (EOFException unused) {
                throw new EndOfLog();
            }
        }

        public final WalRecord writeRecord(WalOpCode opCode, Function1<? super DataOutputStream, Unit> writer) throws IOException {
            opCode.getClass();
            writer.getClass();
            UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
            ChecksumOutputStream checksumOutputStream = new ChecksumOutputStream(unsyncByteArrayOutputStream, WriteAheadLogKt.checksumGen);
            DataOutputStream dataOutputStream = new DataOutputStream(checksumOutputStream);
            try {
                writer.invoke(dataOutputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(dataOutputStream, (Throwable) null);
                long jChecksum = checksumOutputStream.checksum();
                ByteArraySequence byteArraySequence = unsyncByteArrayOutputStream.toByteArraySequence();
                byteArraySequence.getClass();
                return new WalRecord(opCode, jChecksum, byteArraySequence);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(dataOutputStream, th);
                    throw th2;
                }
            }
        }

        private Companion() {
        }
    }
}
