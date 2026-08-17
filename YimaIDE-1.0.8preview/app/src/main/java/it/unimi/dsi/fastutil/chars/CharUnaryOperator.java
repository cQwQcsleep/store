package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CharUnaryOperator extends IntUnaryOperator, UnaryOperator<Character> {
    char apply(char c);

    @Override // java.util.function.Function
    @Deprecated
    default Character apply(Character ch) {
        return Character.valueOf(apply(ch.charValue()));
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return apply(SafeMath.safeIntToChar(i));
    }
}
