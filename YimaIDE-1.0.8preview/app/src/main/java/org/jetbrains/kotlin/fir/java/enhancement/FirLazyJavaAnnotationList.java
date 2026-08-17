package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CollectionToArray;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.java.FirJavaFacadeKt;
import org.jetbrains.kotlin.fir.java.JavaAnnotationsMappingKt;
import org.jetbrains.kotlin.fir.java.enhancement.FirLazyJavaAnnotationList;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationOwner;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0007\n\u0002\u0010*\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u0016\u001a\u00020\u0017H\u0096\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0010H\u0096\u0082\u0004J\u0010\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fH\u0096\u0082\u0004J\u0018\u0010 \u001a\u00020\u00172\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00100\tH\u0096\u0080\u0004J\u0012\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0019H\u0096\u0082\u0004J\u0012\u0010$\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0010H\u0096\u0080\u0004J\u0012\u0010%\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0010H\u0096\u0080\u0004J\u0010\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100'H\u0096\u0080\u0004J\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100'2\u0006\u0010#\u001a\u00020\u0019H\u0096\u0080\u0004J \u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u0019H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015*\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0018\u001a\u00020\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010+\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b+\u0010,¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirLazyJavaAnnotationList;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "annotationOwner", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationOwner;", "ownerModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "<init>", "(Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationOwner;Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "javaAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "getJavaAnnotations", "()Ljava/util/Collection;", "lazyFirAnnotations", "Lkotlin/Lazy;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "firAnnotations", "getFirAnnotations$delegate", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirLazyJavaAnnotationList;)Ljava/lang/Object;", "getFirAnnotations", "()Ljava/util/List;", "isEmpty", Argument.Delimiters.none, "size", Argument.Delimiters.none, "getSize", "()I", "contains", "element", "iterator", Argument.Delimiters.none, "containsAll", "elements", "get", "index", "indexOf", "lastIndexOf", "listIterator", Argument.Delimiters.none, "subList", "fromIndex", "toIndex", "isInitialized", "()Z", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyJavaAnnotationList implements FirJavaAnnotationList {
    private final JavaAnnotationOwner annotationOwner;
    private final Lazy<List<FirAnnotation>> lazyFirAnnotations;
    private final FirModuleData ownerModuleData;

    public FirLazyJavaAnnotationList(JavaAnnotationOwner javaAnnotationOwner, FirModuleData firModuleData) {
        javaAnnotationOwner.getClass();
        firModuleData.getClass();
        this.annotationOwner = javaAnnotationOwner;
        this.ownerModuleData = firModuleData;
        this.lazyFirAnnotations = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: la5
            public final Object invoke() {
                return FirLazyJavaAnnotationList.a(this.b);
            }
        });
    }

    public static List a(FirLazyJavaAnnotationList firLazyJavaAnnotationList) {
        return JavaAnnotationsMappingKt.convertAnnotationsToFir(firLazyJavaAnnotationList.getJavaAnnotations(), firLazyJavaAnnotationList.ownerModuleData.getSession(), FirJavaFacadeKt.toSourceElement(firLazyJavaAnnotationList.annotationOwner, KtFakeSourceElementKind.Enhancement.INSTANCE), firLazyJavaAnnotationList.annotationOwner.isDeprecatedInJavaDoc());
    }

    private final List<FirAnnotation> getFirAnnotations() {
        return (List) this.lazyFirAnnotations.getValue();
    }

    private final Collection<JavaAnnotation> getJavaAnnotations() {
        return this.annotationOwner.getAnnotations();
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
        return getFirAnnotations().containsAll(elements);
    }

    @Override // java.util.List
    public FirAnnotation get(int index) {
        return getFirAnnotations().get(index);
    }

    public int getSize() {
        return getFirAnnotations().size();
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
        return getJavaAnnotations().isEmpty() && !this.annotationOwner.isDeprecatedInJavaDoc();
    }

    public final boolean isInitialized() {
        return this.lazyFirAnnotations.isInitialized();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<FirAnnotation> iterator() {
        return getFirAnnotations().iterator();
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
        return getFirAnnotations().listIterator();
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
        return getFirAnnotations().subList(fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        tArr.getClass();
        return (T[]) CollectionToArray.toArray(this, tArr);
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

    @Override // java.util.List
    public ListIterator<FirAnnotation> listIterator(int index) {
        return getFirAnnotations().listIterator(index);
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
        return getFirAnnotations().contains(element);
    }

    public int indexOf(FirAnnotation element) {
        element.getClass();
        return getFirAnnotations().indexOf(element);
    }

    public int lastIndexOf(FirAnnotation element) {
        element.getClass();
        return getFirAnnotations().lastIndexOf(element);
    }
}
