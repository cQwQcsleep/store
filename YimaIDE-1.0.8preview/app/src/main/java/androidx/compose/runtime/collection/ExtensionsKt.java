package androidx.compose.runtime.collection;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a9\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u0005H\u0080\b\u001a3\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\b0\u0005H\u0080\b\u001a-\u0010\t\u001a\u00020\b\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\b0\u0005H\u0080\b\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00030\u000b\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0000\u001aD\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003\"\u000e\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u0005H\u0000\u001a>\u0010\u0010\u001a\u00020\b\"\u0004\b\u0000\u0010\u0003\"\u000e\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u0005H\u0000\u001a>\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0003\"\u000e\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u0005H\u0000\u001a\u001d\u0010\u0013\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u000bH\u0000¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"fastMap", "Landroidx/collection/ObjectList;", "R", "T", "transform", "Lkotlin/Function1;", "fastFilter", "predicate", "", "all", "toMutableObjectList", "Landroidx/collection/MutableObjectList;", "sortedBy", "K", "", "selector", "isSorted", "sortBy", "", "removeLast", "(Landroidx/collection/MutableObjectList;)Ljava/lang/Object;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ExtensionsKt {
    public static final <T> boolean all(ObjectList<T> objectList, Function1<? super T, Boolean> function1) {
        Object[] objArr = objectList.content;
        int i = objectList._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (!((Boolean) function1.invoke(objArr[i2])).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> ObjectList<T> fastFilter(ObjectList<T> objectList, Function1<? super T, Boolean> function1) {
        Object[] objArr = objectList.content;
        int i = objectList._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (!((Boolean) function1.invoke(objArr[i2])).booleanValue()) {
                MutableObjectList mutableObjectList = new MutableObjectList(0, 1, (DefaultConstructorMarker) null);
                Object[] objArr2 = objectList.content;
                int i3 = objectList._size;
                for (int i4 = 0; i4 < i3; i4++) {
                    Object obj = objArr2[i4];
                    if (((Boolean) function1.invoke(obj)).booleanValue()) {
                        mutableObjectList.add(obj);
                    }
                }
                return mutableObjectList;
            }
        }
        return objectList;
    }

    public static final <T, R> ObjectList<R> fastMap(ObjectList<T> objectList, Function1<? super T, ? extends R> function1) {
        MutableObjectList mutableObjectList = new MutableObjectList(objectList.getSize());
        Object[] objArr = objectList.content;
        int i = objectList._size;
        for (int i2 = 0; i2 < i; i2++) {
            mutableObjectList.add(function1.invoke(objArr[i2]));
        }
        return mutableObjectList;
    }

    public static final <T, K extends Comparable<? super K>> boolean isSorted(ObjectList<T> objectList, Function1<? super T, ? extends K> function1) {
        if (objectList.getSize() <= 1) {
            return true;
        }
        Comparable comparable = (Comparable) function1.invoke(objectList.get(0));
        if (comparable == null) {
            return false;
        }
        int size = objectList.getSize();
        int i = 1;
        while (i < size) {
            Comparable comparable2 = (Comparable) function1.invoke(objectList.get(i));
            if (comparable2 == null || comparable.compareTo(comparable2) > 0) {
                return false;
            }
            i++;
            comparable = comparable2;
        }
        return true;
    }

    public static final <T> T removeLast(MutableObjectList<T> mutableObjectList) {
        if (mutableObjectList.isEmpty()) {
            hb9.a("List is empty.");
            return null;
        }
        int size = mutableObjectList.getSize() - 1;
        T t = (T) mutableObjectList.get(size);
        mutableObjectList.removeAt(size);
        return t;
    }

    public static final <T, K extends Comparable<? super K>> void sortBy(MutableObjectList<T> mutableObjectList, final Function1<? super T, ? extends K> function1) {
        List listAsMutableList = mutableObjectList.asMutableList();
        if (listAsMutableList.size() > 1) {
            CollectionsKt.sortWith(listAsMutableList, new Comparator() { // from class: androidx.compose.runtime.collection.ExtensionsKt$sortBy$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Function1 function2 = function1;
                    return ComparisonsKt.compareValues((Comparable) function2.invoke(t), (Comparable) function2.invoke(t2));
                }
            });
        }
    }

    public static final <T, K extends Comparable<? super K>> ObjectList<T> sortedBy(ObjectList<T> objectList, Function1<? super T, ? extends K> function1) {
        if (isSorted(objectList, function1)) {
            return objectList;
        }
        MutableObjectList mutableObjectList = toMutableObjectList(objectList);
        sortBy(mutableObjectList, function1);
        return mutableObjectList;
    }

    public static final <T> MutableObjectList<T> toMutableObjectList(ObjectList<T> objectList) {
        MutableObjectList<T> mutableObjectList = new MutableObjectList<>(objectList.getSize());
        Object[] objArr = objectList.content;
        int i = objectList._size;
        for (int i2 = 0; i2 < i; i2++) {
            mutableObjectList.add(objArr[i2]);
        }
        return mutableObjectList;
    }
}
