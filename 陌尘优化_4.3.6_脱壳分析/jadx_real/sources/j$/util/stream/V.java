package j$.util.stream;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

/* loaded from: /workspace/unpacked/classes3.dex */
final class V extends AbstractC0153l2 {
    public final /* synthetic */ int b;
    final /* synthetic */ AbstractC0100b c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ V(AbstractC0100b abstractC0100b, InterfaceC0182r2 interfaceC0182r2, int i) {
        super(interfaceC0182r2);
        this.b = i;
        this.c = abstractC0100b;
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public void m(long j) {
        switch (this.b) {
            case 5:
                this.a.m(-1L);
                break;
            default:
                super.m(j);
                break;
        }
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        switch (this.b) {
            case 0:
                this.a.accept((InterfaceC0182r2) ((IntFunction) ((C0189t) this.c).n).apply(i));
                return;
            case 1:
                ((IntConsumer) ((W) this.c).n).accept(i);
                this.a.accept(i);
                return;
            case 2:
                ((C0199v) this.c).getClass();
                IntUnaryOperator intUnaryOperator = null;
                intUnaryOperator.applyAsInt(i);
                throw null;
            case 3:
                ((C0204w) this.c).getClass();
                IntToLongFunction intToLongFunction = null;
                intToLongFunction.applyAsLong(i);
                throw null;
            case 4:
                ((C0194u) this.c).getClass();
                IntToDoubleFunction intToDoubleFunction = null;
                intToDoubleFunction.applyAsDouble(i);
                throw null;
            default:
                ((C0199v) this.c).getClass();
                IntPredicate intPredicate = null;
                intPredicate.test(i);
                throw null;
        }
    }
}
