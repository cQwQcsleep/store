package defpackage;

import java.util.function.Predicate;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final /* synthetic */ class yq4 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((FileSystem.Classpath) obj).hasModule();
    }
}
