package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ArrayUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SmartHashSet<T> extends HashSet<T> {
    private T theElement;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5 || i == 6 || i == 8 || i == 9) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 5 || i == 6 || i == 8 || i == 9) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "obj";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 9:
                objArr[0] = "com/intellij/util/containers/SmartHashSet";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "a";
                break;
            default:
                objArr[0] = "collection";
                break;
        }
        if (i == 4) {
            objArr[1] = "iterator";
        } else if (i == 5 || i == 6 || i == 8 || i == 9) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "com/intellij/util/containers/SmartHashSet";
        }
        switch (i) {
            case 1:
                objArr[2] = "contains";
                break;
            case 2:
                objArr[2] = "add";
                break;
            case 3:
                objArr[2] = "remove";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 9:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5 && i != 6 && i != 8 && i != 9) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        if (t == null) {
            $$$reportNull$$$0(2);
        }
        T t2 = this.theElement;
        if (t2 == null) {
            if (!super.isEmpty()) {
                return super.add(t);
            }
            this.theElement = t;
            return true;
        }
        if (Objects.equals(t, t2)) {
            return false;
        }
        super.add(this.theElement);
        this.theElement = null;
        return super.add(t);
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.theElement = null;
        super.clear();
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(1);
        }
        T t = this.theElement;
        if (t != null) {
            return Objects.equals(obj, t);
        }
        return !super.isEmpty() && super.contains(obj);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        T t = this.theElement;
        if (t == null) {
            return super.equals(obj);
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        return set.size() == 1 && Objects.equals(set.iterator().next(), t);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super T> consumer) {
        T t = this.theElement;
        if (t == null) {
            super.forEach(consumer);
        } else {
            consumer.accept(t);
        }
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public int hashCode() {
        T t = this.theElement;
        return t == null ? super.hashCode() : t.hashCode();
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.theElement == null && super.isEmpty();
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<T> iterator() {
        if (this.theElement != null) {
            return new SingletonIteratorBase<T>() { // from class: com.intellij.util.containers.SmartHashSet.1
                @Override // com.intellij.util.containers.SingletonIteratorBase
                public void checkCoModification() {
                    if (SmartHashSet.this.theElement != null) {
                        return;
                    }
                    a1e.a();
                }

                @Override // com.intellij.util.containers.SingletonIteratorBase
                public T getElement() {
                    return (T) SmartHashSet.this.theElement;
                }

                @Override // java.util.Iterator
                public void remove() {
                    checkCoModification();
                    SmartHashSet.this.clear();
                }
            };
        }
        Iterator<T> it = super.iterator();
        if (it == null) {
            $$$reportNull$$$0(4);
        }
        return it;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        T t = this.theElement;
        if (t == null) {
            return super.remove(obj);
        }
        if (!Objects.equals(obj, t)) {
            return false;
        }
        this.theElement = null;
        return true;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        if (this.theElement == null) {
            return super.size();
        }
        return 1;
    }

    @Override // java.util.HashSet, java.util.Collection, java.lang.Iterable, java.util.Set
    public Spliterator<T> spliterator() {
        T t = this.theElement;
        return t == null ? super.spliterator() : Stream.of(t).spliterator();
    }

    @Override // java.util.Collection
    public Stream<T> stream() {
        T t = this.theElement;
        return t == null ? super.stream() : Stream.of(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [O[]] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <O> O[] toArray(O[] oArr) {
        ?? r3;
        if (oArr == 0) {
            $$$reportNull$$$0(7);
        }
        T t = this.theElement;
        if (t == null) {
            O[] oArr2 = (O[]) super.toArray(oArr);
            if (oArr2 == null) {
                $$$reportNull$$$0(8);
            }
            return oArr2;
        }
        if (oArr.length == 0) {
            r3 = oArr;
            r3 = (O[]) ArrayUtil.newArray(ArrayUtil.getComponentType(oArr), 1);
        }
        r3 = oArr;
        ((??[OBJECT, ARRAY][]) r3)[0] = t;
        if (((??[]) r3).length > 1) {
            ((??[OBJECT, ARRAY][]) r3)[1] = null;
        }
        return (O[]) r3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        T t = this.theElement;
        if (t == null) {
            Object[] array = super.toArray();
            if (array == null) {
                $$$reportNull$$$0(5);
            }
            return array;
        }
        return new Object[]{t};
    }
}
