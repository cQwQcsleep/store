package defpackage;

import com.sun.tools.javac.code.Type;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class bqa implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((Type) obj).isNumeric();
    }
}
