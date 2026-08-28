package j$.util.stream;

import j$.util.C0083g;
import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* renamed from: j$.util.stream.a, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0095a implements Supplier, Consumer, BooleanSupplier, DoubleFunction, Function, LongFunction {
    public final /* synthetic */ int a;
    public Object b;

    public /* synthetic */ C0095a(int i) {
        this.a = i;
    }

    public /* synthetic */ C0095a(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        switch (this.a) {
            case 1:
                ((InterfaceC0182r2) this.b).accept((InterfaceC0182r2) obj);
                break;
            default:
                ((ArrayList) this.b).add(obj);
                break;
        }
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.a) {
            case 0:
                return ((AbstractC0100b) this.b).M();
            default:
                return (j$.util.U) this.b;
        }
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        Object objApply = ((Function) this.b).apply(obj);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof Stream) {
            return C0114d3.k((Stream) objApply);
        }
        if (objApply instanceof java.util.stream.Stream) {
            return C0109c3.k((java.util.stream.Stream) objApply);
        }
        if (objApply instanceof InterfaceC0116e0) {
            return C0111d0.k((InterfaceC0116e0) objApply);
        }
        if (objApply instanceof IntStream) {
            return C0106c0.k((IntStream) objApply);
        }
        if (objApply instanceof E) {
            return D.k((E) objApply);
        }
        if (objApply instanceof DoubleStream) {
            return C.k((DoubleStream) objApply);
        }
        if (objApply instanceof InterfaceC0171p0) {
            return C0166o0.k((InterfaceC0171p0) objApply);
        }
        if (objApply instanceof LongStream) {
            return C0161n0.k((LongStream) objApply);
        }
        C0083g.a("java.util.stream.*Stream", objApply.getClass());
        throw null;
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d) {
        Object objApply = ((DoubleFunction) this.b).apply(d);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof E) {
            return D.k((E) objApply);
        }
        if (objApply instanceof DoubleStream) {
            return C.k((DoubleStream) objApply);
        }
        C0083g.a("java.util.stream.DoubleStream", objApply.getClass());
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        Object objApply = ((LongFunction) this.b).apply(j);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof InterfaceC0171p0) {
            return C0166o0.k((InterfaceC0171p0) objApply);
        }
        if (objApply instanceof LongStream) {
            return C0161n0.k((LongStream) objApply);
        }
        C0083g.a("java.util.stream.LongStream", objApply.getClass());
        throw null;
    }

    @Override // java.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        switch (this.a) {
            case 2:
                C0183r3 c0183r3 = (C0183r3) this.b;
                return c0183r3.d.tryAdvance(c0183r3.e);
            case 3:
                C0193t3 c0193t3 = (C0193t3) this.b;
                return c0193t3.d.tryAdvance(c0193t3.e);
            case 4:
                C0203v3 c0203v3 = (C0203v3) this.b;
                return c0203v3.d.tryAdvance(c0203v3.e);
            default:
                K3 k3 = (K3) this.b;
                return k3.d.tryAdvance(k3.e);
        }
    }
}
