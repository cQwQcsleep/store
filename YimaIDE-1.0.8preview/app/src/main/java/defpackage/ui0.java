package defpackage;

import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class ui0 implements Consumer {
    public final /* synthetic */ Scope.WriteableScope b;

    public /* synthetic */ ui0(Scope.WriteableScope writeableScope) {
        this.b = writeableScope;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.enter((Symbol.BindingSymbol) obj);
    }
}
