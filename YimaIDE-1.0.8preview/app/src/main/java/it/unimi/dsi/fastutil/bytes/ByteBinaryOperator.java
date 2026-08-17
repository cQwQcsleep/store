package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ByteBinaryOperator extends BinaryOperator<Byte>, IntBinaryOperator {
    byte apply(byte b, byte b2);

    @Override // java.util.function.BiFunction
    @Deprecated
    default Byte apply(Byte b, Byte b2) {
        return Byte.valueOf(apply(b.byteValue(), b2.byteValue()));
    }

    @Override // java.util.function.IntBinaryOperator
    @Deprecated
    default int applyAsInt(int i, int i2) {
        return apply(SafeMath.safeIntToByte(i), SafeMath.safeIntToByte(i2));
    }
}
