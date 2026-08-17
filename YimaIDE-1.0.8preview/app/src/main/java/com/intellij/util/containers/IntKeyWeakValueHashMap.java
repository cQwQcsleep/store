package com.intellij.util.containers;

import com.intellij.reference.SoftReference;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class IntKeyWeakValueHashMap<V> implements IntObjectMap<V>, ReferenceQueueable {
    private final Int2ObjectMap<MyReference<V>> myMap = new Int2ObjectOpenHashMap();
    private final ReferenceQueue<V> myQueue = new ReferenceQueue<>();

    public final class MyEntrySetView extends AbstractSet<IntObjectMap.Entry<V>> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/IntKeyWeakValueHashMap$MyEntrySetView", "iterator"));
        }

        private MyEntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<IntObjectMap.Entry<V>> iterator() {
            Iterator<IntObjectMap.Entry<V>> itEntriesIterator = IntKeyWeakValueHashMap.this.entriesIterator();
            if (itEntriesIterator == null) {
                $$$reportNull$$$0(0);
            }
            return itEntriesIterator;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntKeyWeakValueHashMap.this.size();
        }
    }

    public static final class MyReference<T> extends WeakReference<T> {
        private final int key;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "referent", "com/intellij/util/containers/IntKeyWeakValueHashMap$MyReference", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private MyReference(int i, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            if (t == null) {
                $$$reportNull$$$0(0);
            }
            this.key = i;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "value";
        } else {
            objArr[0] = "com/intellij/util/containers/IntKeyWeakValueHashMap";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/containers/IntKeyWeakValueHashMap";
        } else {
            objArr[1] = "values";
        }
        if (i != 1) {
            if (i != 2) {
                objArr[2] = "put";
            } else {
                objArr[2] = "containsValue";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterator<IntObjectMap.Entry<V>> entriesIterator() {
        final ObjectIterator it = this.myMap.int2ObjectEntrySet().iterator();
        return new Iterator<IntObjectMap.Entry<V>>() { // from class: com.intellij.util.containers.IntKeyWeakValueHashMap.1
            private int lastReturned;
            private IntObjectMap.Entry<V> nextVEntry;

            {
                nextAliveEntry();
            }

            private void nextAliveEntry() {
                while (it.hasNext()) {
                    Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry) it.next();
                    T t = ((MyReference) entry.getValue()).get();
                    if (t != 0) {
                        this.nextVEntry = new SimpleEntry(entry.getIntKey(), t);
                        return;
                    }
                }
                this.nextVEntry = null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.nextVEntry != null;
            }

            @Override // java.util.Iterator
            public IntObjectMap.Entry<V> next() {
                if (!hasNext()) {
                    z0e.a();
                    return null;
                }
                IntObjectMap.Entry<V> entry = this.nextVEntry;
                this.lastReturned = entry.getKey();
                nextAliveEntry();
                return entry;
            }

            @Override // java.util.Iterator
            public void remove() {
                IntKeyWeakValueHashMap.this.myMap.remove(this.lastReturned);
            }
        };
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public Set<IntObjectMap.Entry<V>> entrySet() {
        return new MyEntrySetView();
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public V get(int i) {
        return (V) SoftReference.dereference((Reference) this.myMap.get(i));
    }

    @Override // com.intellij.util.containers.ReferenceQueueable
    public boolean processQueue() {
        boolean zRemove = false;
        while (true) {
            MyReference myReference = (MyReference) this.myQueue.poll();
            if (myReference == null) {
                return zRemove;
            }
            zRemove |= this.myMap.remove(myReference.key, myReference);
        }
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public V put(int i, V v) {
        if (v == null) {
            $$$reportNull$$$0(0);
        }
        processQueue();
        return (V) SoftReference.dereference((MyReference) this.myMap.put(i, new MyReference(i, v, this.myQueue)));
    }

    public int size() {
        return this.myMap.size();
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public Collection<V> values() {
        ObjectCollection objectCollectionValues = this.myMap.values();
        ArrayList arrayList = new ArrayList(objectCollectionValues.size());
        Iterator it = objectCollectionValues.iterator();
        while (it.hasNext()) {
            T t = ((MyReference) it.next()).get();
            if (t != 0) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }
}
