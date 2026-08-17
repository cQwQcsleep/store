package org.jetbrains.kotlin.backend.common;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.backend.common.LegacyKlibDependencyUtilsKt;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.utils.SortUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0003¨\u0006\u0004"}, d2 = {"legacyKlibReverseTopoSort", "", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class LegacyKlibDependencyUtilsKt {
    public static Iterable a(LegacyKlibDependencies legacyKlibDependencies, KotlinLibrary kotlinLibrary) {
        kotlinLibrary.getClass();
        return legacyKlibDependencies.getDependenciesFor(kotlinLibrary);
    }

    public static final List<KotlinLibrary> legacyKlibReverseTopoSort(Collection<? extends KotlinLibrary> collection) {
        collection.getClass();
        if (collection.size() <= 1) {
            return CollectionsKt.toList(collection);
        }
        final LegacyKlibDependencies legacyKlibDependencies = new LegacyKlibDependencies(collection);
        return CollectionsKt.reversed(SortUtilsKt.topologicalSort(collection, new Function1() { // from class: org.jetbrains.kotlin.backend.common.LegacyKlibDependencyUtilsKt.legacyKlibReverseTopoSort.1
            public final Void invoke(KotlinLibrary kotlinLibrary) {
                kotlinLibrary.getClass();
                throw new IllegalStateException(("Cyclic dependency in library graph for: " + kotlinLibrary.getLocation()).toString());
            }
        }, new Function1() { // from class: iy8
            public final Object invoke(Object obj) {
                return LegacyKlibDependencyUtilsKt.a(legacyKlibDependencies, (KotlinLibrary) obj);
            }
        }));
    }
}
