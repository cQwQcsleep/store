package j$.util;

import j$.util.Comparator;
import j$.util.stream.A0;
import j$.util.stream.Stream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.Predicate;

/* renamed from: j$.util.b, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract /* synthetic */ class AbstractC0078b {
    public static /* synthetic */ java.util.Comparator s(java.util.Comparator comparator, java.util.Comparator comparator2) {
        return comparator instanceof Comparator ? ((Comparator) comparator).thenComparing(comparator2) : Comparator.CC.$default$thenComparing(comparator, comparator2);
    }

    public static Optional o(C0090n c0090n) {
        if (c0090n == null) {
            return null;
        }
        if (c0090n.c()) {
            return Optional.of(c0090n.b());
        }
        return Optional.empty();
    }

    public static C0090n k(Optional optional) {
        if (optional == null) {
            return null;
        }
        if (optional.isPresent()) {
            return C0090n.d(optional.get());
        }
        return C0090n.a();
    }

    public static OptionalDouble p(C0091o c0091o) {
        if (c0091o == null) {
            return null;
        }
        if (c0091o.c()) {
            return OptionalDouble.of(c0091o.b());
        }
        return OptionalDouble.empty();
    }

    public static C0091o l(OptionalDouble optionalDouble) {
        if (optionalDouble == null) {
            return null;
        }
        if (optionalDouble.isPresent()) {
            return C0091o.d(optionalDouble.getAsDouble());
        }
        return C0091o.a();
    }

    public static OptionalLong r(C0093q c0093q) {
        if (c0093q == null) {
            return null;
        }
        if (c0093q.c()) {
            return OptionalLong.of(c0093q.b());
        }
        return OptionalLong.empty();
    }

    public static C0093q n(OptionalLong optionalLong) {
        if (optionalLong == null) {
            return null;
        }
        if (optionalLong.isPresent()) {
            return C0093q.d(optionalLong.getAsLong());
        }
        return C0093q.a();
    }

    public static OptionalInt q(C0092p c0092p) {
        if (c0092p == null) {
            return null;
        }
        if (c0092p.c()) {
            return OptionalInt.of(c0092p.b());
        }
        return OptionalInt.empty();
    }

    public static C0092p m(OptionalInt optionalInt) {
        if (optionalInt == null) {
            return null;
        }
        if (optionalInt.isPresent()) {
            return C0092p.d(optionalInt.getAsInt());
        }
        return C0092p.a();
    }

    public static long d(U u) {
        if ((u.characteristics() & 64) == 0) {
            return -1L;
        }
        return u.estimateSize();
    }

    public static boolean e(U u, int i) {
        return (u.characteristics() & i) == i;
    }

    public static boolean f(Collection collection, Predicate predicate) {
        if (AbstractC0084h.a.isInstance(collection)) {
            return AbstractC0084h.a(collection, predicate);
        }
        Objects.requireNonNull(predicate);
        Iterator it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static boolean i(K k, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            return k.tryAdvance((IntConsumer) consumer);
        }
        if (k0.a) {
            k0.a(k.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return k.tryAdvance((IntConsumer) new C0226v(consumer));
    }

    public static void b(K k, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            k.forEachRemaining((IntConsumer) consumer);
        } else {
            if (k0.a) {
                k0.a(k.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            k.forEachRemaining((IntConsumer) new C0226v(consumer));
        }
    }

    public static boolean j(N n, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            return n.tryAdvance((LongConsumer) consumer);
        }
        if (k0.a) {
            k0.a(n.getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return n.tryAdvance((LongConsumer) new C0230z(consumer));
    }

    public static Stream g(Collection collection) {
        return A0.f0(Collection$EL.b(collection), false);
    }

    public static void c(N n, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            n.forEachRemaining((LongConsumer) consumer);
        } else {
            if (k0.a) {
                k0.a(n.getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            n.forEachRemaining((LongConsumer) new C0230z(consumer));
        }
    }

    public static boolean h(H h, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            return h.tryAdvance((DoubleConsumer) consumer);
        }
        if (k0.a) {
            k0.a(h.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return h.tryAdvance((DoubleConsumer) new r(consumer));
    }

    public static void a(H h, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            h.forEachRemaining((DoubleConsumer) consumer);
        } else {
            if (k0.a) {
                k0.a(h.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            h.forEachRemaining((DoubleConsumer) new r(consumer));
        }
    }

    public U trySplit() {
        return null;
    }

    public boolean tryAdvance(Object obj) {
        Objects.requireNonNull(obj);
        return false;
    }

    public void forEachRemaining(Object obj) {
        Objects.requireNonNull(obj);
    }

    public long estimateSize() {
        return 0L;
    }

    public int characteristics() {
        return 16448;
    }
}
