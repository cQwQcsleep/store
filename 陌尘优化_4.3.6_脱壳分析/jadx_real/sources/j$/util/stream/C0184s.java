package j$.util.stream;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

/* renamed from: j$.util.stream.s, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0184s extends AbstractC0148k2 {
    public final /* synthetic */ int b;
    final /* synthetic */ AbstractC0100b c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0184s(AbstractC0100b abstractC0100b, InterfaceC0182r2 interfaceC0182r2, int i) {
        super(interfaceC0182r2);
        this.b = i;
        this.c = abstractC0100b;
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public void m(long j) {
        switch (this.b) {
            case 4:
                this.a.m(-1L);
                break;
            default:
                super.m(j);
                break;
        }
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        switch (this.b) {
            case 0:
                this.a.accept((InterfaceC0182r2) ((DoubleFunction) ((C0189t) this.c).n).apply(d));
                return;
            case 1:
                ((C0194u) this.c).getClass();
                DoubleUnaryOperator doubleUnaryOperator = null;
                doubleUnaryOperator.applyAsDouble(d);
                throw null;
            case 2:
                ((C0199v) this.c).getClass();
                DoubleToIntFunction doubleToIntFunction = null;
                doubleToIntFunction.applyAsInt(d);
                throw null;
            case 3:
                ((C0204w) this.c).getClass();
                DoubleToLongFunction doubleToLongFunction = null;
                doubleToLongFunction.applyAsLong(d);
                throw null;
            case 4:
                ((C0194u) this.c).getClass();
                DoublePredicate doublePredicate = null;
                doublePredicate.test(d);
                throw null;
            default:
                ((DoubleConsumer) ((C0214y) this.c).n).accept(d);
                this.a.accept(d);
                return;
        }
    }
}
