package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010*\n\u0000\bÆ\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0003H\u0096\u0082\u0004J\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fH\u0096\u0082\u0004J\u0018\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012H\u0096\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0007H\u0096\u0082\u0004J\u0010\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0096\u0080\u0004R\u0015\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirEmptyJavaAnnotationList;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "Lkotlin/collections/AbstractList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "()V", "size", Argument.Delimiters.none, "getSize", "()I", "isEmpty", Argument.Delimiters.none, "contains", "element", "iterator", Argument.Delimiters.none, "containsAll", "elements", Argument.Delimiters.none, "get", "index", "listIterator", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEmptyJavaAnnotationList extends AbstractList<FirAnnotation> implements FirJavaAnnotationList {
    public static final FirEmptyJavaAnnotationList INSTANCE = new FirEmptyJavaAnnotationList();

    private FirEmptyJavaAnnotationList() {
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
        return elements.isEmpty();
    }

    @Override // java.util.List
    public FirAnnotation get(int index) {
        throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
    }

    public int getSize() {
        return 0;
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
        return true;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<FirAnnotation> iterator() {
        Iterator<FirAnnotation> itEmptyIterator = Collections.emptyIterator();
        itEmptyIterator.getClass();
        return itEmptyIterator;
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
        ListIterator<FirAnnotation> listIteratorEmptyListIterator = Collections.emptyListIterator();
        listIteratorEmptyListIterator.getClass();
        return listIteratorEmptyListIterator;
    }

    public boolean contains(FirAnnotation element) {
        element.getClass();
        return false;
    }

    public /* bridge */ int indexOf(FirAnnotation firAnnotation) {
        return super.indexOf(firAnnotation);
    }

    public /* bridge */ int lastIndexOf(FirAnnotation firAnnotation) {
        return super.lastIndexOf(firAnnotation);
    }
}
