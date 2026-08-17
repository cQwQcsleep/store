package defpackage;

import com.reandroid.dex.data.FieldDef;
import com.reandroid.dex.model.DexClass;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class ip3 implements Function {
    public final /* synthetic */ DexClass b;

    public /* synthetic */ ip3(DexClass dexClass) {
        this.b = dexClass;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.initializeField((FieldDef) obj);
    }
}
