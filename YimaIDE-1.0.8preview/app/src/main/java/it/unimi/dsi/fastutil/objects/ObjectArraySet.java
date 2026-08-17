package it.unimi.dsi.fastutil.objects;

import defpackage.z0e;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ObjectArraySet<K> extends AbstractObjectSet<K> implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    protected transient Object[] a;
    protected int size;

    public ObjectArraySet(Object[] objArr) {
        this.a = objArr;
        this.size = objArr.length;
    }

    private int findKey(Object obj) {
        Object[] objArr = this.a;
        int i = this.size;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            if (Objects.equals(objArr[i2], obj)) {
                return i2;
            }
            i = i2;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Object[] objArr = new Object[this.size];
        this.a = objArr;
        for (int i = 0; i < this.size; i++) {
            objArr[i] = objectInputStream.readObject();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Object[] objArr = this.a;
        for (int i = 0; i < this.size; i++) {
            objectOutputStream.writeObject(objArr[i]);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(K k) {
        if (findKey(k) != -1) {
            return false;
        }
        int i = this.size;
        if (i == this.a.length) {
            Object[] objArr = new Object[i == 0 ? 2 : i * 2];
            while (true) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                objArr[i2] = this.a[i2];
                i = i2;
            }
            this.a = objArr;
        }
        Object[] objArr2 = this.a;
        int i3 = this.size;
        this.size = i3 + 1;
        objArr2[i3] = k;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        Arrays.fill(this.a, 0, this.size, (Object) null);
        this.size = 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ObjectArraySet<K> m22clone() {
        try {
            ObjectArraySet<K> objectArraySet = (ObjectArraySet) super.clone();
            objectArraySet.a = (Object[]) this.a.clone();
            return objectArraySet;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return findKey(obj) != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // it.unimi.dsi.fastutil.objects.AbstractObjectSet, it.unimi.dsi.fastutil.objects.AbstractObjectCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectCollection, it.unimi.dsi.fastutil.objects.ObjectIterable
    public ObjectIterator<K> iterator() {
        return new ObjectIterator<K>() { // from class: it.unimi.dsi.fastutil.objects.ObjectArraySet.1
            int curr = -1;
            int next = 0;

            @Override // java.util.Iterator
            public void forEachRemaining(Consumer<? super K> consumer) {
                Object[] objArr = ObjectArraySet.this.a;
                while (true) {
                    int i = this.next;
                    if (i >= ObjectArraySet.this.size) {
                        return;
                    }
                    this.next = i + 1;
                    consumer.accept(objArr[i]);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next < ObjectArraySet.this.size;
            }

            @Override // java.util.Iterator
            public K next() {
                if (!hasNext()) {
                    z0e.a();
                    return null;
                }
                Object[] objArr = ObjectArraySet.this.a;
                int i = this.next;
                this.next = i + 1;
                this.curr = i;
                return (K) objArr[i];
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.curr == -1) {
                    g33.a();
                    return;
                }
                this.curr = -1;
                ObjectArraySet objectArraySet = ObjectArraySet.this;
                int i = objectArraySet.size;
                objectArraySet.size = i - 1;
                int i2 = this.next;
                int i3 = i2 - 1;
                this.next = i3;
                Object[] objArr = objectArraySet.a;
                System.arraycopy(objArr, i2, objArr, i3, i - i2);
                ObjectArraySet objectArraySet2 = ObjectArraySet.this;
                objectArraySet2.a[objectArraySet2.size] = null;
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iFindKey = findKey(obj);
        if (iFindKey == -1) {
            return false;
        }
        int i = (this.size - iFindKey) - 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object[] objArr = this.a;
            int i3 = iFindKey + i2;
            objArr[i3] = objArr[i3 + 1];
        }
        int i4 = this.size - 1;
        this.size = i4;
        this.a[i4] = null;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectCollection, it.unimi.dsi.fastutil.objects.ObjectIterable
    /* JADX INFO: renamed from: spliterator */
    public ObjectSpliterator<K> mo3spliterator() {
        return new Spliterator(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        int i = this.size;
        if (tArr == null) {
            tArr = (T[]) new Object[i];
        } else if (tArr.length < i) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.size));
        }
        System.arraycopy(this.a, 0, tArr, 0, this.size);
        int length = tArr.length;
        int i2 = this.size;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    public ObjectArraySet() {
        this.a = ObjectArrays.EMPTY_ARRAY;
    }

    public final class Spliterator implements ObjectSpliterator<K> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean hasSplit;
        int max;
        int pos;

        private Spliterator(int i, int i2, boolean z) {
            this.pos = i;
            this.max = i2;
            this.hasSplit = z;
        }

        private int getWorkingMax() {
            return this.hasSplit ? this.max : ObjectArraySet.this.size;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16465;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return getWorkingMax() - this.pos;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            Object[] objArr = ObjectArraySet.this.a;
            int workingMax = getWorkingMax();
            while (true) {
                int i = this.pos;
                if (i >= workingMax) {
                    return;
                }
                consumer.accept(objArr[i]);
                this.pos++;
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> consumer) {
            if (this.pos >= getWorkingMax()) {
                return false;
            }
            Object[] objArr = ObjectArraySet.this.a;
            int i = this.pos;
            this.pos = i + 1;
            consumer.accept(objArr[i]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterator, java.util.Spliterator
        public ObjectSpliterator<K> trySplit() {
            int workingMax = getWorkingMax();
            int i = this.pos;
            int i2 = (workingMax - i) >> 1;
            if (i2 <= 1) {
                return null;
            }
            this.max = workingMax;
            int i3 = i2 + i;
            this.pos = i3;
            this.hasSplit = true;
            return new Spliterator(i, i3, true);
        }

        public Spliterator(ObjectArraySet objectArraySet) {
            this(0, objectArraySet.size, false);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        int size = size();
        if (size == 0) {
            return ObjectArrays.EMPTY_ARRAY;
        }
        return Arrays.copyOf(this.a, size, Object[].class);
    }
}
