package defpackage;

import com.sun.tools.javac.jvm.PoolReader;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class tw1 implements IntFunction {
    public final /* synthetic */ PoolReader b;

    public /* synthetic */ tw1(PoolReader poolReader) {
        this.b = poolReader;
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        return this.b.getName(i);
    }
}
