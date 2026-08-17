package defpackage;

import com.android.tools.r8.references.TypeReference;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class ykg implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((TypeReference) obj).getTypeName();
    }
}
