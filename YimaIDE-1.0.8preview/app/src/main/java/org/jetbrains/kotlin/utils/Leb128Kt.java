package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.joni.constants.internal.OPCode;
import org.joni.constants.internal.StackType;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a#\u0010\u0007\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\t\u001a\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\u000e\u001a,\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\u000eø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a,\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\u000eø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0011\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"}, d2 = {"readSignedLeb128", "", "readNextByte", "Lkotlin/Function0;", "", "maxCount", "", "readUnsignedLeb128", "Lkotlin/UInt;", "(Lkotlin/jvm/functions/Function0;I)I", "writeSignedLeb128", "", "v", "writeNextByte", "Lkotlin/Function1;", "writeUnsignedLeb128", "writeUnsignedLeb128-qim9Vi0", "(ILkotlin/jvm/functions/Function1;)V", "writeUnsignedLeb128Fixed", "writeUnsignedLeb128Fixed-qim9Vi0", "kotlin-util-io"}, k = 2, mv = {1, 8, 0}, xi = OPCode.BACKREFN)
public final class Leb128Kt {
    public static final long readSignedLeb128(Function0<Byte> function0, int i) {
        byte bByteValue;
        int i2;
        int i3;
        function0.getClass();
        int i4 = 0;
        long j = -1;
        long j2 = 0;
        do {
            bByteValue = ((Number) function0.invoke()).byteValue();
            i2 = bByteValue & 255;
            j2 |= ((long) (bByteValue & 127)) << (i4 * 7);
            j <<= 7;
            i4++;
            i3 = bByteValue & 128;
            if (i3 != 128) {
                break;
            }
        } while (i4 <= i);
        if (i3 == 128) {
            k2d.a("InvalidLeb128Number");
            return 0L;
        }
        if (i4 > i && i == 9) {
            if ((bByteValue & 64) == 64) {
                if (((byte) (i2 | 128)) != -1) {
                    k2d.a("InvalidLeb128Number");
                    return 0L;
                }
            } else if (i2 != 0) {
                k2d.a("InvalidLeb128Number");
                return 0L;
            }
        }
        return ((j >> 1) & j2) != 0 ? j2 | j : j2;
    }

    public static /* synthetic */ long readSignedLeb128$default(Function0 function0, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 4;
        }
        return readSignedLeb128(function0, i);
    }

    public static final int readUnsignedLeb128(Function0<Byte> function0, int i) {
        int i2;
        function0.getClass();
        int i3 = 0;
        int i4 = 0;
        do {
            int i5 = UInt.constructor-impl(UInt.constructor-impl(((Number) function0.invoke()).byteValue()) & StackType.MASK_POP_USED);
            i3 = UInt.constructor-impl(i3 | UInt.constructor-impl(UInt.constructor-impl(i5 & 127) << (i4 * 7)));
            i4++;
            i2 = i5 & 128;
            if (UInt.constructor-impl(i2) != 128) {
                break;
            }
        } while (i4 <= i);
        if (UInt.constructor-impl(i2) != 128) {
            return i3;
        }
        k2d.a("InvalidLeb128Number");
        return 0;
    }

    public static /* synthetic */ int readUnsignedLeb128$default(Function0 function0, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 4;
        }
        return readUnsignedLeb128(function0, i);
    }

    public static final void writeSignedLeb128(long j, Function1<? super Byte, Unit> function1) {
        function1.getClass();
        long j2 = j >> 7;
        long j3 = (j & Long.MIN_VALUE) == 0 ? 0L : -1L;
        long j4 = j;
        boolean z = true;
        while (z) {
            z = (j2 == j3 && (j2 & 1) == (1 & (j4 >> 6))) ? false : true;
            function1.invoke(Byte.valueOf((byte) ((j4 & 127) | (z ? 128L : 0L))));
            j4 = j2;
            j2 >>= 7;
        }
    }

    /* JADX INFO: renamed from: writeUnsignedLeb128-qim9Vi0, reason: not valid java name */
    public static final void m1293writeUnsignedLeb128qim9Vi0(int i, Function1<? super Byte, Unit> function1) {
        function1.getClass();
        int i2 = UInt.constructor-impl(i >>> 7);
        while (true) {
            int i3 = i2;
            int i4 = i;
            i = i3;
            if (i == 0) {
                function1.invoke(Byte.valueOf((byte) UInt.constructor-impl(i4 & 127)));
                return;
            } else {
                function1.invoke(Byte.valueOf((byte) UInt.constructor-impl(UInt.constructor-impl(i4 & 127) | 128)));
                i2 = UInt.constructor-impl(i >>> 7);
            }
        }
    }

    /* JADX INFO: renamed from: writeUnsignedLeb128Fixed-qim9Vi0, reason: not valid java name */
    public static final void m1294writeUnsignedLeb128Fixedqim9Vi0(int i, Function1<? super Byte, Unit> function1) {
        function1.getClass();
        int i2 = UInt.constructor-impl(i >>> 7);
        int i3 = 0;
        while (i3 < 4) {
            function1.invoke(Byte.valueOf((byte) UInt.constructor-impl(UInt.constructor-impl(i & 127) | 128)));
            i3++;
            int i4 = i2;
            i2 = UInt.constructor-impl(i2 >>> 7);
            i = i4;
        }
        function1.invoke(Byte.valueOf((byte) UInt.constructor-impl(i & 127)));
    }
}
