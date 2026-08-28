package j$.util.stream;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/* loaded from: /workspace/unpacked/classes3.dex */
final class E1 extends A0 {
    public final /* synthetic */ int h;
    final /* synthetic */ Object i;

    public /* synthetic */ E1(EnumC0134h3 enumC0134h3, Object obj, int i) {
        this.h = i;
        this.i = obj;
    }

    @Override // j$.util.stream.A0
    public final W1 e0() {
        switch (this.h) {
            case 0:
                return new V1((LongBinaryOperator) this.i);
            case 1:
                return new H1((DoubleBinaryOperator) this.i);
            case 2:
                return new M1((BinaryOperator) this.i);
            default:
                return new S1((IntBinaryOperator) this.i);
        }
    }
}
