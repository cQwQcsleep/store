package defpackage;

import java.util.function.Predicate;
import org.eclipse.jdt.internal.compiler.parser.Scanner;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final /* synthetic */ class fvc implements Predicate {
    public final /* synthetic */ Scanner b;

    public /* synthetic */ fvc(Scanner scanner) {
        this.b = scanner;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.mayBeAtASealedRestricedIdentifier(((Integer) obj).intValue());
    }
}
