package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ByteUnaryOperator extends IntUnaryOperator, UnaryOperator<Byte> {
    byte apply(byte b);

    @Override // java.util.function.Function
    @Deprecated
    default Byte apply(Byte b) {
        return Byte.valueOf(apply(b.byteValue()));
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return apply(SafeMath.safeIntToByte(i));
    }
}
