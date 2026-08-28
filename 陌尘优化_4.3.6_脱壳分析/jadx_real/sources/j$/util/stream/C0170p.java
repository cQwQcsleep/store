package j$.util.stream;

import com.shadow.okhttp3.internal.ws.WebSocketProtocol;
import j$.util.C0085i;
import j$.util.C0086j;
import j$.util.C0088l;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import rikka.shizuku.ShizukuApiConstants;

/* renamed from: j$.util.stream.p, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0170p implements BiConsumer, ObjDoubleConsumer, Supplier, LongFunction, BinaryOperator, IntFunction, DoubleBinaryOperator, DoubleFunction, ToDoubleFunction {
    public final /* synthetic */ int a;

    public /* synthetic */ C0170p(int i) {
        this.a = i;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.a) {
            case 0:
                break;
            case 2:
                break;
            case 20:
                break;
            case 21:
                break;
        }
        return j$.com.android.tools.r8.a.a(this, biConsumer);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        switch (this.a) {
        }
        return j$.com.android.tools.r8.a.b(this, function);
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d) {
        return Double.valueOf(d);
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        switch (this.a) {
            case 8:
                return A0.J(j);
            case 9:
            default:
                return A0.U(j);
            case 10:
                return A0.S(j);
        }
    }

    @Override // java.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d, double d2) {
        switch (this.a) {
            case 22:
                return Math.min(d, d2);
            default:
                return Math.max(d, d2);
        }
    }

    @Override // java.util.function.ToDoubleFunction
    public double applyAsDouble(Object obj) {
        return ((Double) obj).doubleValue();
    }

    @Override // java.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        switch (this.a) {
            case 1:
                double[] dArr = (double[]) obj;
                dArr[2] = dArr[2] + 1.0d;
                AbstractC0145k.a(dArr, d);
                dArr[3] = dArr[3] + d;
                break;
            case 2:
            default:
                ((C0085i) obj).accept(d);
                break;
            case 3:
                double[] dArr2 = (double[]) obj;
                AbstractC0145k.a(dArr2, d);
                dArr2[2] = dArr2[2] + d;
                break;
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.a) {
            case 4:
                return new G();
            case 5:
                return new H();
            case 6:
                return new I();
            case 7:
                return new J();
            case 16:
                return new C0085i();
            case 17:
                return new C0086j();
            case 18:
                return new C0088l();
            case 19:
                return new LinkedHashSet();
            default:
                return new double[4];
        }
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                AbstractC0145k.a(dArr, dArr2[0]);
                AbstractC0145k.a(dArr, dArr2[1]);
                dArr[2] = dArr[2] + dArr2[2];
                break;
            case 2:
                double[] dArr3 = (double[]) obj;
                double[] dArr4 = (double[]) obj2;
                AbstractC0145k.a(dArr3, dArr4[0]);
                AbstractC0145k.a(dArr3, dArr4[1]);
                dArr3[2] = dArr3[2] + dArr4[2];
                dArr3[3] = dArr3[3] + dArr4[3];
                break;
            case 20:
                ((LinkedHashSet) obj).add(obj2);
                break;
            case 21:
                ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
                break;
            default:
                ((C0085i) obj).b((C0085i) obj2);
                break;
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        switch (this.a) {
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                return new Object[i];
            default:
                return new Double[i];
        }
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        switch (this.a) {
            case 9:
                return new U0((G0) obj, (G0) obj2);
            case 10:
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
            default:
                return new Y0((M0) obj, (M0) obj2);
            case 11:
                return new V0((I0) obj, (I0) obj2);
            case 13:
                return new W0((K0) obj, (K0) obj2);
        }
    }
}
