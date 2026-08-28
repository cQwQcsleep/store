package j$.util.function;

import j$.util.Objects;
import java.util.function.Predicate;

/* renamed from: j$.util.function.Predicate$-CC, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class Predicate$CC {
    public static Predicate $default$and(Predicate predicate, Predicate predicate2) {
        Objects.requireNonNull(predicate2);
        return new g(predicate, predicate2, 0);
    }

    public static Predicate $default$negate(Predicate predicate) {
        return new b(predicate, 2);
    }

    public static Predicate $default$or(Predicate predicate, Predicate predicate2) {
        Objects.requireNonNull(predicate2);
        return new g(predicate, predicate2, 1);
    }
}
