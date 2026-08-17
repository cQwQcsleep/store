package org.jetbrains.kotlin.fir;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010*\n\u0002\b\u0010\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0087@\u0018\u0000 ;*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001;B\u0017\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0013\b\u0012\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0005\u0010\bJ\u0019\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\nH\u0096\u0082\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0096\u0082\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0080\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a2\u0006\u0010\u000e\u001a\u00020\nH\u0096\u0080\u0004¢\u0006\u0004\b\u001b\u0010\u001dJ'\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0096\u0080\u0004¢\u0006\u0004\b!\u0010\"J\u0019\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0004\b%\u0010&J\u0019\u0010'\u001a\u00020\n2\u0006\u0010$\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0004\b(\u0010&J\u001f\u0010)\u001a\u00020\u00122\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000+H\u0096\u0080\u0004¢\u0006\u0004\b,\u0010-J\u0019\u0010.\u001a\u00020\u00122\u0006\u0010$\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0004\b/\u00100J\u0011\u00101\u001a\u000202H\u0096\u0080\u0004¢\u0006\u0004\b3\u00104J\u001b\u00105\u001a\u00020\u00122\b\u00106\u001a\u0004\u0018\u000107HÖ\u0083\u0004¢\u0006\u0004\b8\u00100J\u0011\u00109\u001a\u00020\nHÖ\u0081\u0004¢\u0006\u0004\b:\u0010\fR\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0080\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\t\u001a\u00020\n8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0088\u0001\u0003\u0092\u0001\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u0004Ê\u0001\u0002\b=¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "T", Argument.Delimiters.none, "list", Argument.Delimiters.none, "constructor-impl", "(Ljava/util/List;)Ljava/util/List;", Argument.Delimiters.none, "(Ljava/lang/Void;)Ljava/util/List;", "size", Argument.Delimiters.none, "getSize-impl", "(Ljava/util/List;)I", "get", "index", "get-impl", "(Ljava/util/List;I)Ljava/lang/Object;", "isEmpty", Argument.Delimiters.none, "isEmpty-impl", "(Ljava/util/List;)Z", "iterator", Argument.Delimiters.none, "iterator-impl", "(Ljava/util/List;)Ljava/util/Iterator;", "listIterator", Argument.Delimiters.none, "listIterator-impl", "(Ljava/util/List;)Ljava/util/ListIterator;", "(Ljava/util/List;I)Ljava/util/ListIterator;", "subList", "fromIndex", "toIndex", "subList-impl", "(Ljava/util/List;II)Ljava/util/List;", "lastIndexOf", "element", "lastIndexOf-impl", "(Ljava/util/List;Ljava/lang/Object;)I", "indexOf", "indexOf-impl", "containsAll", "elements", Argument.Delimiters.none, "containsAll-impl", "(Ljava/util/List;Ljava/util/Collection;)Z", "contains", "contains-impl", "(Ljava/util/List;Ljava/lang/Object;)Z", "toString", Argument.Delimiters.none, "toString-impl", "(Ljava/util/List;)Ljava/lang/String;", "equals", "other", Argument.Delimiters.none, "equals-impl", "hashCode", "hashCode-impl", "Companion", "org.jetbrains.kotlin:tree", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@JvmInline
public final class MutableOrEmptyList<T> implements List<T>, KMappedMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List EMPTY = m195constructorimpl((Void) null);
    private static final List EMPTY_LIST_STUB;
    private static final Iterator EMPTY_LIST_STUB_ITERATOR;
    private static final ListIterator EMPTY_LIST_STUB_LIST_ITERATOR;
    private final List<T> list;

    static {
        List listEmptyList = CollectionsKt.emptyList();
        EMPTY_LIST_STUB = listEmptyList;
        EMPTY_LIST_STUB_ITERATOR = listEmptyList.iterator();
        EMPTY_LIST_STUB_LIST_ITERATOR = listEmptyList.listIterator();
    }

    private /* synthetic */ MutableOrEmptyList(List list) {
        this.list = list;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ MutableOrEmptyList m194boximpl(List list) {
        return new MutableOrEmptyList(list);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static <T> List<T> m195constructorimpl(Void r0) {
        return m196constructorimpl(TypeIntrinsics.asMutableList(r0));
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static boolean m197containsimpl(List<T> list, T t) {
        if (list != null) {
            return list.contains(t);
        }
        return false;
    }

    /* JADX INFO: renamed from: containsAll-impl, reason: not valid java name */
    public static boolean m198containsAllimpl(List<T> list, Collection<? extends T> collection) {
        collection.getClass();
        return list != null ? list.containsAll(collection) : collection.isEmpty();
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m199equalsimpl(List<T> list, Object obj) {
        return (obj instanceof MutableOrEmptyList) && Intrinsics.areEqual(list, ((MutableOrEmptyList) obj).getList());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m200equalsimpl0(List<Object> list, List<Object> list2) {
        return Intrinsics.areEqual(list, list2);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static T m201getimpl(List<T> list, int i) {
        list.getClass();
        return list.get(i);
    }

    /* JADX INFO: renamed from: getSize-impl, reason: not valid java name */
    public static int m202getSizeimpl(List<T> list) {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m203hashCodeimpl(List<T> list) {
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    /* JADX INFO: renamed from: indexOf-impl, reason: not valid java name */
    public static int m204indexOfimpl(List<T> list, T t) {
        if (list != null) {
            return list.indexOf(t);
        }
        return -1;
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static boolean m205isEmptyimpl(List<T> list) {
        if (list != null) {
            return list.isEmpty();
        }
        return true;
    }

    /* JADX INFO: renamed from: iterator-impl, reason: not valid java name */
    public static Iterator<T> m206iteratorimpl(List<T> list) {
        Iterator<T> it;
        return (list == null || (it = list.iterator()) == null) ? EMPTY_LIST_STUB_ITERATOR : it;
    }

    /* JADX INFO: renamed from: lastIndexOf-impl, reason: not valid java name */
    public static int m207lastIndexOfimpl(List<T> list, T t) {
        if (list != null) {
            return list.lastIndexOf(t);
        }
        return -1;
    }

    /* JADX INFO: renamed from: listIterator-impl, reason: not valid java name */
    public static ListIterator<T> m208listIteratorimpl(List<T> list) {
        ListIterator<T> listIterator;
        return (list == null || (listIterator = list.listIterator()) == null) ? EMPTY_LIST_STUB_LIST_ITERATOR : listIterator;
    }

    /* JADX INFO: renamed from: subList-impl, reason: not valid java name */
    public static List<T> m210subListimpl(List<T> list, int i, int i2) {
        if (list == null && i == 0 && i2 == 0) {
            return m194boximpl(list);
        }
        list.getClass();
        return list.subList(i, i2);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m211toStringimpl(List<T> list) {
        String strJoinToString$default;
        return (list == null || (strJoinToString$default = CollectionsKt.joinToString$default(list, (CharSequence) null, "[", "]", 0, (CharSequence) null, (Function1) null, 57, (Object) null)) == null) ? "[]" : strJoinToString$default;
    }

    @Override // java.util.List
    public void add(int i, T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return m197containsimpl(this.list, obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        collection.getClass();
        return m198containsAllimpl(this.list, collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return m199equalsimpl(this.list, obj);
    }

    @Override // java.util.List
    public T get(int i) {
        return (T) m201getimpl(this.list, i);
    }

    @Override // java.util.List, java.util.Collection
    /* JADX INFO: renamed from: getSize, reason: merged with bridge method [inline-methods] */
    public int size() {
        return m202getSizeimpl(this.list);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return m203hashCodeimpl(this.list);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return m204indexOfimpl(this.list, obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return m205isEmptyimpl(this.list);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return m206iteratorimpl(this.list);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return m207lastIndexOfimpl(this.list, obj);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return m208listIteratorimpl(this.list);
    }

    @Override // java.util.List
    public T remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<T> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public T set(int i, T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void sort(Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<T> subList(int i, int i2) {
        return m210subListimpl(this.list, i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        tArr.getClass();
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public String toString() {
        return m211toStringimpl(this.list);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ List getList() {
        return this.list;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010*\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0005\"\u0004\b\u0001\u0010\u000f¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/MutableOrEmptyList$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", Argument.Delimiters.none, "Ljava/util/List;", "EMPTY_LIST_STUB", Argument.Delimiters.none, "EMPTY_LIST_STUB_ITERATOR", Argument.Delimiters.none, "EMPTY_LIST_STUB_LIST_ITERATOR", Argument.Delimiters.none, "empty", "T", "empty-5e3fPpI", "()Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: empty-5e3fPpI, reason: not valid java name */
        public final <T> List<T> m213empty5e3fPpI() {
            return MutableOrEmptyList.EMPTY;
        }

        private Companion() {
        }
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i) {
        return m209listIteratorimpl(this.list, i);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> List<T> m196constructorimpl(List<T> list) {
        return list;
    }

    /* JADX INFO: renamed from: listIterator-impl, reason: not valid java name */
    public static ListIterator<T> m209listIteratorimpl(List<T> list, int i) {
        ListIterator<T> listIterator;
        return (list == null || (listIterator = list.listIterator(i)) == null) ? EMPTY_LIST_STUB_LIST_ITERATOR : listIterator;
    }
}
