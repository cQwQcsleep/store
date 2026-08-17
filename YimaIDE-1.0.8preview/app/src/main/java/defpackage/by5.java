package defpackage;

import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.jvm.PoolWriter;
import java.util.function.ToIntBiFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class by5 implements ToIntBiFunction {
    @Override // java.util.function.ToIntBiFunction
    public final int applyAsInt(Object obj, Object obj2) {
        return ((PoolWriter) obj).putClass((Type) obj2);
    }
}
