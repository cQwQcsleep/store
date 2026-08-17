package com.reandroid.utils;

import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ObjectsStore {
    public static Object add(Object obj, Object obj2) {
        ObjectsList objectsList;
        Object obj3 = obj;
        obj3 = obj;
        if (obj2 != null && obj2 != obj) {
            if (obj == null) {
                return obj2;
            }
            if (obj.getClass() == ObjectsList.class) {
                objectsList = (ObjectsList) obj;
            } else {
                ObjectsList objectsList2 = new ObjectsList();
                objectsList2.add(obj);
                objectsList = objectsList2;
            }
            objectsList.add(obj2);
            obj3 = objectsList;
        }
        return obj3;
    }

    public static Object addAll(Object obj, Iterator<?> it) {
        ObjectsList objectsList;
        if (it == null || !it.hasNext()) {
            return obj;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return add(obj, next);
        }
        if (obj == null || obj.getClass() != ObjectsList.class) {
            ObjectsList objectsList2 = new ObjectsList();
            if (obj != null) {
                objectsList2.add(obj);
            }
            objectsList = objectsList2;
        } else {
            objectsList = (ObjectsList) obj;
        }
        objectsList.add(next);
        objectsList.add(it.next());
        objectsList.addAll((Iterator<? extends Object>) it);
        int size = objectsList.size();
        if (size == 0) {
            return null;
        }
        return size == 1 ? objectsList.get(0) : objectsList;
    }

    public static Object clear(Object obj) {
        if (obj == null || obj.getClass() != ObjectsList.class) {
            return null;
        }
        ((ObjectsList) obj).clear();
        return null;
    }

    public static <T> Iterator<T> clonedIterator(Object obj) throws ClassCastException {
        Object objClonedIterator;
        if (obj == null) {
            objClonedIterator = EmptyIterator.of();
        } else {
            objClonedIterator = obj.getClass() == ObjectsList.class ? ((ObjectsList) obj).clonedIterator() : SingleIterator.of(obj);
        }
        return (Iterator) ObjectsUtil.cast(objClonedIterator);
    }

    public static void collect(Object obj, Object[] objArr) {
        if (obj == null || objArr == null || objArr.length == 0) {
            return;
        }
        if (obj.getClass() == ObjectsList.class) {
            ((ObjectsList) obj).toArrayFill(objArr);
        } else {
            objArr[0] = obj;
        }
    }

    public static boolean contains(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        return obj.getClass() == ObjectsList.class ? ((ObjectsList) obj).contains(obj2) : obj.equals(obj2);
    }

    public static <T> boolean containsIf(Object obj, Predicate<T> predicate) {
        return iteratorIf(obj, predicate).hasNext();
    }

    public static Object create(Iterator<?> it) {
        if (it == null || !it.hasNext()) {
            return null;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        ObjectsList objectsList = new ObjectsList();
        objectsList.add(next);
        objectsList.addAll((Iterator<? extends Object>) it);
        return objectsList;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public static <T> T get(Object obj, int i) throws ClassCastException {
        if (obj == null) {
            obj = null;
        } else if (obj.getClass() == ObjectsList.class) {
            obj = ((ObjectsList) obj).get(i);
        } else if (i != 0) {
            obj = null;
        }
        return (T) ObjectsUtil.cast(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass() == ObjectsList.class) {
            return ((ObjectsList) obj).isEmpty();
        }
        return false;
    }

    public static <T> Iterator<T> iterator(Object obj, Class<T> cls) throws ClassCastException {
        Object objOf;
        if (obj == null) {
            objOf = EmptyIterator.of();
        } else if (obj.getClass() == ObjectsList.class) {
            objOf = ((ObjectsList) obj).iterator(cls);
        } else {
            objOf = cls.isInstance(obj) ? SingleIterator.of(obj) : EmptyIterator.of();
        }
        return (Iterator) ObjectsUtil.cast(objOf);
    }

    public static <T> Iterator<T> iteratorIf(Object obj, Predicate<T> predicate) throws ClassCastException {
        return FilterIterator.of(iterator(obj), predicate);
    }

    public static Object remove(Object obj, Object obj2) {
        if (obj2 == null || obj == null || obj2 == obj) {
            return null;
        }
        if (obj.getClass() != ObjectsList.class) {
            if (obj.equals(obj2)) {
                return null;
            }
            return obj;
        }
        ObjectsList objectsList = (ObjectsList) obj;
        objectsList.remove(obj2);
        int size = objectsList.size();
        if (size == 0) {
            return null;
        }
        return size == 1 ? objectsList.get(0) : objectsList;
    }

    public static int size(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj.getClass() == ObjectsList.class) {
            return ((ObjectsList) obj).size();
        }
        return 1;
    }

    public static void sort(Object obj, Comparator<?> comparator) throws ClassCastException {
        if (obj == null || obj.getClass() != ObjectsList.class) {
            return;
        }
        ((ObjectsList) obj).sort((Comparator) ObjectsUtil.cast(comparator));
    }

    public static final class ObjectsList extends ArrayCollection<Object> {
        private boolean sorted;

        public ObjectsList() {
            super(10);
        }

        @Override // com.reandroid.utils.collection.ArrayCollection, java.util.List, java.util.Collection, java.util.Set
        public boolean add(Object obj) {
            if (containsExact(obj) || obj == null) {
                return false;
            }
            this.sorted = false;
            return super.add(obj);
        }

        @Override // com.reandroid.utils.collection.ArrayCollection, java.util.List, java.util.Collection, java.util.Set
        public boolean addAll(Collection<?> collection) {
            int size = size();
            add(collection.iterator());
            return size != size();
        }

        @Override // com.reandroid.utils.collection.ArrayCollection, java.util.List, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean zRemove = super.remove(obj);
            if (zRemove) {
                this.sorted = false;
            }
            return zRemove;
        }

        @Override // com.reandroid.utils.collection.ArrayCollection, java.util.List
        public void sort(Comparator<? super Object> comparator) {
            boolean z = this.sorted;
            if (!z) {
                if (size() < 2) {
                    z = true;
                }
                this.sorted = true;
            }
            if (z) {
                return;
            }
            super.sort(comparator);
        }

        public ObjectsList(Object[] objArr) {
            super(objArr);
        }

        @Override // com.reandroid.utils.collection.ArrayCollection
        public void addAll(Iterator<? extends Object> it) {
            while (it.hasNext()) {
                add(it.next());
            }
        }
    }

    public static Object create(Object[] objArr) {
        int length;
        if (objArr == null || (length = objArr.length) == 0) {
            return null;
        }
        if (length == 1) {
            return objArr[0];
        }
        return new ObjectsList(objArr);
    }

    public static <T> Iterator<T> iterator(Object obj) throws ClassCastException {
        Object objOf;
        if (obj == null) {
            objOf = EmptyIterator.of();
        } else if (obj.getClass() == ObjectsList.class) {
            objOf = ((ObjectsList) obj).iterator();
        } else {
            objOf = SingleIterator.of(obj);
        }
        return (Iterator) ObjectsUtil.cast(objOf);
    }

    public static Object addAll(Object obj, Collection<?> collection) {
        ObjectsList objectsList;
        if (collection == null || collection.isEmpty()) {
            return obj;
        }
        if (obj != null && obj.getClass() == ObjectsList.class) {
            objectsList = (ObjectsList) obj;
            objectsList.addAll(collection);
        } else if (obj == null) {
            objectsList = new ObjectsList(collection.toArray());
        } else {
            ObjectsList objectsList2 = new ObjectsList();
            objectsList2.add(obj);
            objectsList2.addAll(collection);
            objectsList = objectsList2;
        }
        int size = objectsList.size();
        if (size == 0) {
            return null;
        }
        return size == 1 ? objectsList.get(0) : objectsList;
    }

    public static Object addAll(Object obj, Object[] objArr) {
        ObjectsList objectsList;
        if (objArr == null || objArr.length == 0) {
            return obj;
        }
        if (obj != null && obj.getClass() == ObjectsList.class) {
            objectsList = (ObjectsList) obj;
            objectsList.addAll(objArr);
        } else if (obj == null) {
            objectsList = new ObjectsList(objArr);
        } else {
            ObjectsList objectsList2 = new ObjectsList();
            objectsList2.add(obj);
            objectsList2.addAll(objArr);
            objectsList = objectsList2;
        }
        int size = objectsList.size();
        if (size == 0) {
            return null;
        }
        return size == 1 ? objectsList.get(0) : objectsList;
    }
}
