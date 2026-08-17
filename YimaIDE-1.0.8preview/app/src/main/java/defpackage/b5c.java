package defpackage;

import com.reandroid.dex.model.DexInstruction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class b5c implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((DexInstruction) obj).isNumber();
    }
}
