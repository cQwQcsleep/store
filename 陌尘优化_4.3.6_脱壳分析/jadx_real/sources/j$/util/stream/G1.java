package j$.util.stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* loaded from: /workspace/unpacked/classes3.dex */
final class G1 extends A0 {
    public final /* synthetic */ int h;
    final /* synthetic */ Object i;
    final /* synthetic */ Object j;
    final /* synthetic */ Object k;

    public /* synthetic */ G1(EnumC0134h3 enumC0134h3, Object obj, Object obj2, Object obj3, int i) {
        this.h = i;
        this.j = obj;
        this.k = obj2;
        this.i = obj3;
    }

    @Override // j$.util.stream.A0
    public final W1 e0() {
        switch (this.h) {
            case 0:
                return new D1((Supplier) this.i, (ObjLongConsumer) this.k, (C0175q) this.j);
            case 1:
                return new J1((Supplier) this.i, (ObjDoubleConsumer) this.k, (C0175q) this.j);
            case 2:
                return new L1(this.i, (BiFunction) this.k, (BinaryOperator) this.j);
            case 3:
                return new P1((Supplier) this.i, (BiConsumer) this.k, (BiConsumer) this.j);
            default:
                return new T1((Supplier) this.i, (ObjIntConsumer) this.k, (C0175q) this.j);
        }
    }
}
