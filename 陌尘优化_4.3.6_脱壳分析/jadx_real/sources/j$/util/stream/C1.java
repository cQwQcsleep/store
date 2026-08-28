package j$.util.stream;

import java.util.function.LongBinaryOperator;

/* loaded from: /workspace/unpacked/classes3.dex */
final class C1 extends A0 {
    final /* synthetic */ LongBinaryOperator h;
    final /* synthetic */ long i;

    @Override // j$.util.stream.A0
    public final W1 e0() {
        return new U1(this.i, this.h);
    }

    C1(EnumC0134h3 enumC0134h3, LongBinaryOperator longBinaryOperator, long j) {
        this.h = longBinaryOperator;
        this.i = j;
    }
}
