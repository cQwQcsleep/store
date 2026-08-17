package defpackage;

import com.intellij.openapi.application.Application;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final /* synthetic */ class zde implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Application) obj).isLockingProhibited();
    }
}
