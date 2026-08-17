package defpackage;

import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.model.DexLayout;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class qq3 implements Function {
    public final /* synthetic */ DexLayout b;

    public /* synthetic */ qq3(DexLayout dexLayout) {
        this.b = dexLayout;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.create((ClassId) obj);
    }
}
