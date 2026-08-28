package j$.util.stream;

import java.util.function.IntBinaryOperator;

/* loaded from: /workspace/unpacked/classes3.dex */
final class R1 extends A0 {
    final /* synthetic */ IntBinaryOperator h;
    final /* synthetic */ int i;

    @Override // j$.util.stream.A0
    public final W1 e0() {
        return new Q1(this.i, this.h);
    }

    R1(EnumC0134h3 enumC0134h3, IntBinaryOperator intBinaryOperator, int i) {
        this.h = intBinaryOperator;
        this.i = i;
    }
}
