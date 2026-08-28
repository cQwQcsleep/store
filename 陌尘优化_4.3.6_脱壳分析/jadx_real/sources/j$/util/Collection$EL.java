package j$.util;

import j$.util.stream.Stream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* renamed from: j$.util.Collection$-EL, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class Collection$EL {
    public static /* synthetic */ boolean removeIf(Collection collection, Predicate predicate) {
        return collection instanceof InterfaceC0079c ? ((InterfaceC0079c) collection).removeIf(predicate) : AbstractC0078b.f(collection, predicate);
    }

    public static /* synthetic */ Stream stream(Collection collection) {
        return collection instanceof InterfaceC0079c ? ((InterfaceC0079c) collection).stream() : AbstractC0078b.g(collection);
    }

    public static U b(Collection collection) {
        if (collection instanceof InterfaceC0079c) {
            return ((InterfaceC0079c) collection).spliterator();
        }
        if (collection instanceof LinkedHashSet) {
            return new g0((Collection) Objects.requireNonNull((LinkedHashSet) collection), 17);
        }
        if (collection instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) collection;
            return new E(sortedSet, sortedSet);
        }
        if (collection instanceof Set) {
            return new g0((Collection) Objects.requireNonNull((Set) collection), 1);
        }
        if (!(collection instanceof List)) {
            return new g0((Collection) Objects.requireNonNull(collection), 0);
        }
        List list = (List) collection;
        return list instanceof RandomAccess ? new C0077a(list) : new g0((Collection) Objects.requireNonNull(list), 16);
    }

    public static void a(Collection collection, Consumer consumer) {
        if (collection instanceof InterfaceC0079c) {
            ((InterfaceC0079c) collection).forEach(consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }
}
