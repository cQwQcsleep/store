package j$.util.stream;

import java.util.function.DoubleBinaryOperator;

/* loaded from: /workspace/unpacked/classes3.dex */
final class K1 extends A0 {
    final /* synthetic */ DoubleBinaryOperator h;
    final /* synthetic */ double i;

    @Override // j$.util.stream.A0
    public final W1 e0() {
        return new F1(this.i, this.h);
    }

    K1(EnumC0134h3 enumC0134h3, DoubleBinaryOperator doubleBinaryOperator, double d) {
        this.h = doubleBinaryOperator;
        this.i = d;
    }
}
