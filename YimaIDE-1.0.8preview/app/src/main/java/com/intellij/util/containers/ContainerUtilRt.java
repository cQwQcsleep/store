package com.intellij.util.containers;

import com.intellij.util.ArrayUtilRt;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
public final class ContainerUtilRt {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 3) ? 2 : 3];
        if (i == 1 || i == 3) {
            objArr[0] = "com/intellij/util/containers/ContainerUtilRt";
        } else {
            objArr[0] = "elements";
        }
        if (i == 1) {
            objArr[1] = "newArrayList";
        } else if (i != 3) {
            objArr[1] = "com/intellij/util/containers/ContainerUtilRt";
        } else {
            objArr[1] = "emptyList";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "newLinkedHashSet";
            } else if (i != 3) {
                objArr[2] = "newArrayList";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Deprecated
    public static <T> List<T> emptyList() {
        EmptyList emptyList = EmptyList.INSTANCE;
        if (emptyList == null) {
            $$$reportNull$$$0(3);
        }
        return emptyList;
    }

    public static final class EmptyList<T> extends AbstractList<T> implements Serializable, RandomAccess {
        private static final EmptyList<?> INSTANCE = new EmptyList<>();
        private static final long serialVersionUID = 1;

        /* JADX WARN: Code duplicated, block: B:27:0x0042  */
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 1 || i == 5) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[(i == 1 || i == 5) ? 3 : 2];
            if (i == 1) {
                objArr[0] = "a";
            } else if (i != 5) {
                objArr[0] = "com/intellij/util/containers/ContainerUtilRt$EmptyList";
            } else {
                objArr[0] = "c";
            }
            if (i == 1) {
                objArr[1] = "com/intellij/util/containers/ContainerUtilRt$EmptyList";
            } else if (i == 3) {
                objArr[1] = "iterator";
            } else if (i == 4) {
                objArr[1] = "listIterator";
            } else if (i != 5) {
                objArr[1] = "toArray";
            } else {
                objArr[1] = "com/intellij/util/containers/ContainerUtilRt$EmptyList";
            }
            if (i == 1) {
                objArr[2] = "toArray";
            } else if (i == 5) {
                objArr[2] = "containsAll";
            }
            String str2 = String.format(str, objArr);
            if (i != 1 && i != 5) {
                throw new IllegalStateException(str2);
            }
            throw new IllegalArgumentException(str2);
        }

        private EmptyList() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean containsAll(Collection<?> collection) {
            if (collection == null) {
                $$$reportNull$$$0(5);
            }
            return collection.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            return (obj instanceof List) && ((List) obj).isEmpty();
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i) {
            throw new IndexOutOfBoundsException("Index: " + i);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            return 1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            EmptyIterator emptyIterator = EmptyIterator.getInstance();
            if (emptyIterator == null) {
                $$$reportNull$$$0(3);
            }
            return emptyIterator;
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator() {
            EmptyListIterator emptyListIterator = EmptyListIterator.getInstance();
            if (emptyListIterator == null) {
                $$$reportNull$$$0(4);
            }
            return emptyListIterator;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <E> E[] toArray(E[] eArr) {
            if (eArr == null) {
                $$$reportNull$$$0(1);
            }
            if (eArr.length != 0) {
                eArr[0] = null;
            }
            return eArr;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            Object[] objArr = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
            if (objArr == null) {
                $$$reportNull$$$0(0);
            }
            return objArr;
        }
    }
}
