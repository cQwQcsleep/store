package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0007\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0096\u0083\u0004J\u0018\u0010\u000b\u001a\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0096\u0081\u0004J\u0012\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0096\u0083\u0004J\u0012\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0003H\u0096\u0081\u0004J\n\u0010\u0012\u001a\u00020\tH\u0096\u0081\u0004J\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0096\u0083\u0004J\u0012\u0010\u0015\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0003H\u0096\u0081\u0004J\u0010\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0017H\u0096\u0081\u0004J\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0096\u0081\u0004J \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0096\u0081\u0004R\u0013\u0010\u001b\u001a\u00020\u0010X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirDelegatedJavaAnnotationList;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "annotationsOwner", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;)V", "contains", Argument.Delimiters.none, "element", "containsAll", "elements", Argument.Delimiters.none, "get", "index", Argument.Delimiters.none, "indexOf", "isEmpty", "iterator", Argument.Delimiters.none, "lastIndexOf", "listIterator", Argument.Delimiters.none, "subList", "fromIndex", "toIndex", "size", "getSize", "()I", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatedJavaAnnotationList implements List<FirAnnotation>, KMappedMarker, FirJavaAnnotationList {
    private final /* synthetic */ List<FirAnnotation> $$delegate_0;

    public FirDelegatedJavaAnnotationList(FirAnnotationContainer firAnnotationContainer) {
        firAnnotationContainer.getClass();
        this.$$delegate_0 = firAnnotationContainer.getAnnotations();
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ void add(int i, FirAnnotation firAnnotation) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends FirAnnotation> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof FirAnnotation) {
            return contains((FirAnnotation) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        elements.getClass();
        return this.$$delegate_0.containsAll(elements);
    }

    @Override // java.util.List
    public FirAnnotation get(int index) {
        return this.$$delegate_0.get(index);
    }

    public int getSize() {
        return this.$$delegate_0.size();
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof FirAnnotation) {
            return indexOf((FirAnnotation) obj);
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.$$delegate_0.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<FirAnnotation> iterator() {
        return this.$$delegate_0.iterator();
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof FirAnnotation) {
            return lastIndexOf((FirAnnotation) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<FirAnnotation> listIterator() {
        return this.$$delegate_0.listIterator();
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ FirAnnotation remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<FirAnnotation> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ FirAnnotation set(int i, FirAnnotation firAnnotation) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.List
    public void sort(Comparator<? super FirAnnotation> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<FirAnnotation> subList(int fromIndex, int toIndex) {
        return this.$$delegate_0.subList(fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        tArr.getClass();
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @Override // java.util.List
    public ListIterator<FirAnnotation> listIterator(int index) {
        return this.$$delegate_0.listIterator(index);
    }

    /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
    public void add2(int i, FirAnnotation firAnnotation) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends FirAnnotation> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: remove, reason: avoid collision after fix types in other method */
    public FirAnnotation remove2(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: renamed from: set, reason: avoid collision after fix types in other method */
    public FirAnnotation set2(int i, FirAnnotation firAnnotation) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(FirAnnotation firAnnotation) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(FirAnnotation element) {
        element.getClass();
        return this.$$delegate_0.contains(element);
    }

    public int indexOf(FirAnnotation element) {
        element.getClass();
        return this.$$delegate_0.indexOf(element);
    }

    public int lastIndexOf(FirAnnotation element) {
        element.getClass();
        return this.$$delegate_0.lastIndexOf(element);
    }
}
