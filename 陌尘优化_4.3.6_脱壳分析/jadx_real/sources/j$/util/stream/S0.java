package j$.util.stream;

import j$.util.C0083g;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.stream.IntStream;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class S0 implements LongFunction, IntFunction {
    public IntFunction a;

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        Object objApply = this.a.apply(i);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof InterfaceC0116e0) {
            return C0111d0.k((InterfaceC0116e0) objApply);
        }
        if (objApply instanceof IntStream) {
            return C0106c0.k((IntStream) objApply);
        }
        C0083g.a("java.util.stream.IntStream", objApply.getClass());
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        return A0.D(j, this.a);
    }
}
