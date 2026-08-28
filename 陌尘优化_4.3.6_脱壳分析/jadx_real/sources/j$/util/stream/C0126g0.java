package j$.util.stream;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

/* renamed from: j$.util.stream.g0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0126g0 extends AbstractC0158m2 {
    public final /* synthetic */ int b;
    final /* synthetic */ AbstractC0100b c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0126g0(AbstractC0100b abstractC0100b, InterfaceC0182r2 interfaceC0182r2, int i) {
        super(interfaceC0182r2);
        this.b = i;
        this.c = abstractC0100b;
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
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

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        switch (this.b) {
            case 0:
                this.a.accept((InterfaceC0182r2) ((LongFunction) ((C0189t) this.c).n).apply(j));
                return;
            case 1:
                ((C0204w) this.c).getClass();
                LongUnaryOperator longUnaryOperator = null;
                longUnaryOperator.applyAsLong(j);
                throw null;
            case 2:
                ((C0199v) this.c).getClass();
                LongToIntFunction longToIntFunction = null;
                longToIntFunction.applyAsInt(j);
                throw null;
            case 3:
                ((C0194u) this.c).getClass();
                LongToDoubleFunction longToDoubleFunction = null;
                longToDoubleFunction.applyAsDouble(j);
                throw null;
            case 4:
                ((C0204w) this.c).getClass();
                LongPredicate longPredicate = null;
                longPredicate.test(j);
                throw null;
            default:
                ((LongConsumer) ((C0141j0) this.c).n).accept(j);
                this.a.accept(j);
                return;
        }
    }
}
