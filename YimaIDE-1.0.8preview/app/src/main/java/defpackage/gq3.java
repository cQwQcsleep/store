package defpackage;

import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.model.DexSource;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class gq3 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (DexFile) ((DexSource) obj).get();
    }
}
