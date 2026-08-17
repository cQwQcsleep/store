package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import com.google.common.primitives.Longs;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001eBL\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ:\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u0002H\u00130\b¢\u0006\u0002\b\n¢\u0006\u0002\u0010\u0018J*\u0010\u0019\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u0002H\u00130\b¢\u0006\u0002\b\n¢\u0006\u0002\u0010\u001aJ2\u0010\u001b\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00020\u00032\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u0002H\u00130\b¢\u0006\u0002\b\n¢\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer;", Argument.Delimiters.none, "dataSize", Argument.Delimiters.none, "mapBuffer", "Lkotlin/Function2;", "Ljava/nio/MappedByteBuffer;", "unmapBuffer", "Lkotlin/Function1;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "defaultByteOrder", "Ljava/nio/ByteOrder;", "<init>", "(JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Ljava/nio/ByteOrder;)V", "currentMappedBuffer", "currentStart", "currentEnd", "withMappedRange", "R", "start", "end", "body", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer$Mapping;", "(JJLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withMappedTail", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withMappedRangeFrom", "(JLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "unmap", "Mapping", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LargeDynamicMappedBuffer {
    private long currentEnd;
    private MappedByteBuffer currentMappedBuffer;
    private long currentStart;
    private final long dataSize;
    private final ByteOrder defaultByteOrder;
    private final Function2<Long, Long, MappedByteBuffer> mapBuffer;
    private final Function1<MappedByteBuffer, Unit> unmapBuffer;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0005J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer$Mapping;", Argument.Delimiters.none, "buffer", "Ljava/nio/MappedByteBuffer;", "baseOffset", Argument.Delimiters.none, "<init>", "(Ljava/nio/MappedByteBuffer;I)V", "order", Argument.Delimiters.none, "bo", "Ljava/nio/ByteOrder;", "getInt", "offset", "getLong", Argument.Delimiters.none, "getShort", Argument.Delimiters.none, "getBytes", Argument.Delimiters.none, "length", "endOffset", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Mapping {
        private final int baseOffset;
        private final MappedByteBuffer buffer;

        public Mapping(MappedByteBuffer mappedByteBuffer, int i) {
            mappedByteBuffer.getClass();
            this.buffer = mappedByteBuffer;
            this.baseOffset = i;
        }

        public final int endOffset() {
            return this.buffer.capacity() - this.baseOffset;
        }

        public final byte[] getBytes(int offset, int length) {
            byte[] bArr = new byte[length];
            this.buffer.position(this.baseOffset + offset);
            try {
                this.buffer.get(bArr, 0, length);
                return bArr;
            } finally {
                this.buffer.position(this.baseOffset);
            }
        }

        public final int getInt(int offset) {
            return this.buffer.getInt(this.baseOffset + offset);
        }

        public final long getLong(int offset) {
            return this.buffer.getLong(this.baseOffset + offset);
        }

        public final short getShort(int offset) {
            return this.buffer.getShort(this.baseOffset + offset);
        }

        public final void order(ByteOrder bo) {
            bo.getClass();
            this.buffer.order(bo);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LargeDynamicMappedBuffer(long j, Function2<? super Long, ? super Long, ? extends MappedByteBuffer> function2, Function1<? super MappedByteBuffer, Unit> function1, ByteOrder byteOrder) {
        function2.getClass();
        function1.getClass();
        byteOrder.getClass();
        this.dataSize = j;
        this.mapBuffer = function2;
        this.unmapBuffer = function1;
        this.defaultByteOrder = byteOrder;
    }

    public final void unmap() {
        MappedByteBuffer mappedByteBuffer = this.currentMappedBuffer;
        if (mappedByteBuffer != null) {
            this.unmapBuffer.invoke(mappedByteBuffer);
        }
    }

    public final synchronized <R> R withMappedRange(long start, long end, Function1<? super Mapping, ? extends R> body) {
        MappedByteBuffer mappedByteBuffer;
        try {
            body.getClass();
            long j = 1 + start;
            long j2 = this.dataSize;
            if (end > j2 || j > end || end - start > 2147483647L) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (this.currentMappedBuffer == null || this.currentStart > start || this.currentEnd < end) {
                if (j2 <= 2147483647L) {
                    this.currentStart = 0L;
                    this.currentEnd = j2;
                } else {
                    long j3 = start + 2147483647L;
                    if (j3 > j2) {
                        this.currentStart = j2 - 2147483647L;
                        this.currentEnd = j2;
                    } else {
                        this.currentStart = start;
                        this.currentEnd = j3;
                    }
                    j2 = 2147483647L;
                }
                unmap();
                Object objInvoke = this.mapBuffer.invoke(Long.valueOf(this.currentStart), Long.valueOf(j2));
                ((MappedByteBuffer) objInvoke).order(this.defaultByteOrder);
                this.currentMappedBuffer = (MappedByteBuffer) objInvoke;
            }
            mappedByteBuffer = this.currentMappedBuffer;
            mappedByteBuffer.getClass();
            long j4 = this.currentStart;
            if (j4 > start || this.currentEnd < end || start - j4 >= 2147483647L) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            mappedByteBuffer.position((int) (start - j4));
        } catch (Throwable th) {
            throw th;
        }
        return (R) body.invoke(new Mapping(mappedByteBuffer, mappedByteBuffer.position()));
    }

    public final <R> R withMappedRangeFrom(long start, Function1<? super Mapping, ? extends R> body) {
        body.getClass();
        long j = this.dataSize;
        if (start < j) {
            return (R) withMappedRange(start, start + Longs.min(new long[]{j - start, 2147483647L}), body);
        }
        w01.a("Failed requirement.");
        return null;
    }

    public final <R> R withMappedTail(Function1<? super Mapping, ? extends R> body) {
        body.getClass();
        long jMin = Longs.min(new long[]{this.dataSize, 2147483647L});
        long j = this.dataSize;
        return (R) withMappedRange(j - jMin, j, body);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LargeDynamicMappedBuffer(long j, Function2 function2, Function1 function1, ByteOrder byteOrder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 8) != 0) {
            byteOrder = ByteOrder.nativeOrder();
            byteOrder.getClass();
        }
        this(j, function2, function1, byteOrder);
    }
}
