package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.util.SmartList;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import kotlin.jvm.PurelyImplements;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@PurelyImplements("kotlin.collections.MutableList")
public class SmartList<E> extends AbstractList<E> implements RandomAccess {
    private Object myElem;
    private int mySize;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8) ? 2 : 3];
        switch (i) {
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[0] = "com/intellij/util/SmartList";
                break;
            case 5:
                objArr[0] = "a";
                break;
            case 9:
            case 10:
                objArr[0] = "that";
                break;
            case 11:
                objArr[0] = "action";
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (i == 2) {
            objArr[1] = "outOfBoundsMessage";
        } else if (i == 3) {
            objArr[1] = "resizeIfNecessary";
        } else if (i == 4) {
            objArr[1] = "iterator";
        } else if (i == 6 || i == 7 || i == 8) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "com/intellij/util/SmartList";
        }
        switch (i) {
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                break;
            case 5:
                objArr[2] = "toArray";
                break;
            case 9:
                objArr[2] = "equalsWithSmartList";
                break;
            case 10:
                objArr[2] = "equalsWithArrayList";
                break;
            case 11:
                objArr[2] = "forEach";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 4 && i != 6 && i != 7 && i != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public SmartList(Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(0);
        }
        int size = collection.size();
        if (size == 1) {
            add(((collection instanceof List) && (collection instanceof RandomAccess)) ? (E) ((List) collection).get(0) : collection.iterator().next());
        } else if (size > 0) {
            this.mySize = size;
            this.myElem = collection.toArray(new Object[size]);
        }
    }

    private Object[] asArray() {
        return (Object[]) this.myElem;
    }

    private E asElement() {
        return (E) this.myElem;
    }

    private static void checkOutOfBounds(int i, int i2) {
        if (i < 0 || i >= i2) {
            jb9.a(outOfBoundsMessage(i, i2));
        }
    }

    private static void checkOutOfBoundsForAdd(int i, int i2) {
        if (i < 0 || i > i2) {
            jb9.a(outOfBoundsMessage(i, i2));
        }
    }

    private boolean equalsWithArrayList(ArrayList<?> arrayList) {
        if (arrayList == null) {
            $$$reportNull$$$0(10);
        }
        int i = this.mySize;
        if (i != arrayList.size()) {
            return false;
        }
        if (i != 0) {
            return i != 1 ? arrayList.equals(this) : Objects.equals(this.myElem, arrayList.get(0));
        }
        return true;
    }

    private boolean equalsWithSmartList(SmartList<?> smartList) {
        if (smartList == null) {
            $$$reportNull$$$0(9);
        }
        int i = this.mySize;
        if (i != smartList.mySize) {
            return false;
        }
        if (i != 0) {
            return i != 1 ? Arrays.equals(asArray(), smartList.asArray()) : Objects.equals(this.myElem, smartList.myElem);
        }
        return true;
    }

    private E getFromArray(Object[] objArr, int i) {
        return (E) objArr[i];
    }

    public static /* synthetic */ Object[] j(int i) {
        return new Object[i];
    }

    private static String outOfBoundsMessage(int i, int i2) {
        return "Index: " + i + ", Size: " + i2;
    }

    private Object[] resizeIfNecessary(int i) {
        Object[] objArrAsArray = asArray();
        int length = objArrAsArray.length;
        if (i >= length) {
            objArrAsArray = ArrayUtil.realloc(objArrAsArray, Math.max(((length * 3) / 2) + 1, i + 1), new ArrayFactory() { // from class: ahd
                @Override // com.intellij.util.ArrayFactory
                public final Object[] create(int i2) {
                    return SmartList.j(i2);
                }
            });
            this.myElem = objArrAsArray;
        }
        if (objArrAsArray == null) {
            $$$reportNull$$$0(3);
        }
        return objArrAsArray;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        int i2 = this.mySize;
        checkOutOfBoundsForAdd(i, i2);
        if (i2 == 0) {
            this.myElem = e;
        } else if (i2 != 1) {
            Object[] objArrResizeIfNecessary = resizeIfNecessary(i2);
            System.arraycopy(objArrResizeIfNecessary, i, objArrResizeIfNecessary, i + 1, i2 - i);
            objArrResizeIfNecessary[i] = e;
        } else {
            this.myElem = i == 0 ? new Object[]{e, this.myElem} : new Object[]{this.myElem, e};
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SmartList) {
            return equalsWithSmartList((SmartList) obj);
        }
        return obj instanceof ArrayList ? equalsWithArrayList((ArrayList) obj) : super.equals(obj);
    }

    @Override // java.lang.Iterable
    public void forEach(java.util.function.Consumer<? super E> consumer) {
        if (consumer == null) {
            $$$reportNull$$$0(11);
        }
        int i = this.mySize;
        if (i != 0) {
            if (i == 1) {
                consumer.accept(asElement());
                return;
            }
            Object[] objArrAsArray = asArray();
            for (int i2 = 0; i2 < i; i2++) {
                consumer.accept(getFromArray(objArrAsArray, i2));
            }
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int i2 = this.mySize;
        checkOutOfBounds(i, i2);
        return i2 == 1 ? asElement() : getFromArray(asArray(), i);
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i = this.mySize;
        if (i != 0) {
            if (i != 1) {
                return ArrayUtilRt.indexOf(asArray(), obj, 0, this.mySize);
            }
            if (Objects.equals(obj, this.myElem)) {
                return 0;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        Iterator<E> itEmptyIterator = this.mySize == 0 ? Collections.emptyIterator() : super.iterator();
        if (itEmptyIterator == null) {
            $$$reportNull$$$0(4);
        }
        return itEmptyIterator;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        E eAsElement;
        int i2 = this.mySize;
        checkOutOfBounds(i, i2);
        if (i2 == 0 || i2 == 1) {
            eAsElement = asElement();
            this.myElem = null;
        } else if (i2 != 2) {
            Object[] objArrAsArray = asArray();
            eAsElement = getFromArray(objArrAsArray, i);
            int i3 = (i2 - i) - 1;
            if (i3 > 0) {
                System.arraycopy(objArrAsArray, i + 1, objArrAsArray, i, i3);
            }
            objArrAsArray[i2 - 1] = null;
        } else {
            Object[] objArrAsArray2 = asArray();
            eAsElement = getFromArray(objArrAsArray2, i);
            this.myElem = objArrAsArray2[1 - i];
        }
        this.mySize--;
        ((AbstractList) this).modCount++;
        return eAsElement;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        int i3 = this.mySize;
        int i4 = i2 - i;
        if (i4 < 0) {
            jb9.a(outOfBoundsMessage(i, i2));
            return;
        }
        ((AbstractList) this).modCount++;
        if (i4 == 0) {
            return;
        }
        if (i4 == i3) {
            this.myElem = null;
            this.mySize = 0;
        } else if (i4 == i3 - 1) {
            this.myElem = asArray()[i4 * (1 - i)];
            this.mySize = 1;
        } else {
            Object[] objArrAsArray = asArray();
            System.arraycopy(objArrAsArray, i2, objArrAsArray, i, i3 - i2);
            Arrays.fill(objArrAsArray, i2, i3, (Object) null);
            this.mySize = i3 - i4;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        int i2 = this.mySize;
        checkOutOfBounds(i, i2);
        if (i2 == 1) {
            E eAsElement = asElement();
            this.myElem = e;
            return eAsElement;
        }
        Object[] objArrAsArray = asArray();
        E fromArray = getFromArray(objArrAsArray, i);
        objArrAsArray[i] = e;
        return fromArray;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }

    @Override // java.util.List
    public void sort(Comparator<? super E> comparator) {
        if (this.mySize >= 2) {
            Arrays.sort(asArray(), 0, this.mySize, comparator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        if (tArr == 0) {
            $$$reportNull$$$0(5);
        }
        int length = tArr.length;
        int i = this.mySize;
        if (i != 0) {
            if (i == 1) {
                E eAsElement = asElement();
                if (length == 0) {
                    T[] tArr2 = (T[]) ArrayUtil.newArray(ArrayUtil.getComponentType(tArr), 1);
                    tArr2[0] = eAsElement;
                    return tArr2;
                }
                tArr[0] = eAsElement;
            } else {
                if (length < i) {
                    T[] tArr3 = (T[]) Arrays.copyOf(asArray(), i, tArr.getClass());
                    if (tArr3 == null) {
                        $$$reportNull$$$0(7);
                    }
                    return tArr3;
                }
                System.arraycopy(asArray(), 0, tArr, 0, i);
            }
        }
        if (length > i) {
            tArr[i] = 0;
        }
        return tArr;
    }

    public SmartList(E e) {
        this.myElem = e;
        this.mySize = 1;
    }

    public SmartList() {
    }

    @SafeVarargs
    public SmartList(E... eArr) {
        if (eArr == null) {
            $$$reportNull$$$0(1);
        }
        int length = eArr.length;
        if (length != 0) {
            if (length != 1) {
                this.myElem = Arrays.copyOf(eArr, length);
                this.mySize = length;
            } else {
                this.myElem = eArr[0];
                this.mySize = 1;
            }
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        int i = this.mySize;
        if (i == 0) {
            this.myElem = e;
        } else if (i != 1) {
            resizeIfNecessary(i)[i] = e;
        } else {
            this.myElem = new Object[]{this.myElem, e};
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
        return true;
    }
}
