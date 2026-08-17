package defpackage;

import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final /* synthetic */ class k72 implements Predicate {
    public final /* synthetic */ PathMatcher b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.matches((Path) obj);
    }
}
