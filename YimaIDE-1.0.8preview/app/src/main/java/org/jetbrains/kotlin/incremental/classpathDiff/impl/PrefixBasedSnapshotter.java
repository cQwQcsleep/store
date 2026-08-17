package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\f\u001a\u00020\rJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/PrefixBasedSnapshotter;", "", "classNameToClassFileMap", "", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFileWithContentsProvider;", "<init>", "(Ljava/util/Map;)V", "sortedInternalNames", "", "getSetOfClasses", "", "classPrefix", "", "addedClasses", "processedClasses", "getRangeByPredicate", "", "prefix", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class PrefixBasedSnapshotter {
    private final List<JvmClassName> sortedInternalNames;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.incremental.classpathDiff.impl.PrefixBasedSnapshotter$getRangeByPredicate$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003*\u0002\u0000\u0004\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0004H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"org/jetbrains/kotlin/incremental/classpathDiff/impl/PrefixBasedSnapshotter$getRangeByPredicate$1", "", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "iterator", "org/jetbrains/kotlin/incremental/classpathDiff/impl/PrefixBasedSnapshotter$getRangeByPredicate$1$iterator$1", "()Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/PrefixBasedSnapshotter$getRangeByPredicate$1$iterator$1;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterable<JvmClassName>, KMappedMarker {
        final /* synthetic */ String $prefix;

        public AnonymousClass1(String str) {
            this.$prefix = str;
        }

        @Override // java.lang.Iterable
        public Iterator<JvmClassName> iterator() {
            return new PrefixBasedSnapshotter$getRangeByPredicate$1$iterator$1(PrefixBasedSnapshotter.this, this.$prefix);
        }
    }

    public PrefixBasedSnapshotter(Map<JvmClassName, ClassFileWithContentsProvider> map) {
        map.getClass();
        this.sortedInternalNames = CollectionsKt.sortedWith(map.keySet(), new Comparator() { // from class: org.jetbrains.kotlin.incremental.classpathDiff.impl.PrefixBasedSnapshotter$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((JvmClassName) t).getInternalName(), ((JvmClassName) t2).getInternalName());
            }
        });
    }

    public static boolean a(Set set, JvmClassName jvmClassName) {
        jvmClassName.getClass();
        return !set.contains(jvmClassName);
    }

    private final Iterable<JvmClassName> getRangeByPredicate(String prefix) {
        return new AnonymousClass1(prefix);
    }

    public final Set<JvmClassName> getSetOfClasses(Set<? extends JvmClassName> addedClasses, final Set<? extends JvmClassName> processedClasses) {
        addedClasses.getClass();
        processedClasses.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = addedClasses.iterator();
        while (it.hasNext()) {
            String internalName = ((JvmClassName) it.next()).getInternalName();
            internalName.getClass();
            CollectionsKt.addAll(linkedHashSet, getRangeByPredicate(internalName));
        }
        CollectionsKt.retainAll(linkedHashSet, new Function1() { // from class: org.jetbrains.kotlin.incremental.classpathDiff.impl.a
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PrefixBasedSnapshotter.a(processedClasses, (JvmClassName) obj));
            }
        });
        return linkedHashSet;
    }

    public final Set<JvmClassName> getSetOfClasses(String classPrefix) {
        classPrefix.getClass();
        return CollectionsKt.toSet(getRangeByPredicate(classPrefix));
    }
}
