package defpackage;

import com.sun.tools.javac.code.Lint;
import java.util.EnumSet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class xa9 implements Consumer {
    public final /* synthetic */ EnumSet b;

    public /* synthetic */ xa9(EnumSet enumSet) {
        this.b = enumSet;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((Lint.LintCategory) obj);
    }
}
