package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CharBinaryOperator extends BinaryOperator<Character>, IntBinaryOperator {
    char apply(char c, char c2);

    @Override // java.util.function.BiFunction
    @Deprecated
    default Character apply(Character ch, Character ch2) {
        return Character.valueOf(apply(ch.charValue(), ch2.charValue()));
    }

    @Override // java.util.function.IntBinaryOperator
    @Deprecated
    default int applyAsInt(int i, int i2) {
        return apply(SafeMath.safeIntToChar(i), SafeMath.safeIntToChar(i2));
    }
}
