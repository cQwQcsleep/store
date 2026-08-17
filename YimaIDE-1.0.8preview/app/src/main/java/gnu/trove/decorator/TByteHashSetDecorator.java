package gnu.trove.decorator;

import gnu.trove.TByteHashSet;
import gnu.trove.TByteIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteHashSetDecorator extends AbstractSet<Byte> implements Set<Byte> {
    protected final TByteHashSet _set;

    public TByteHashSetDecorator(TByteHashSet tByteHashSet) {
        this._set = tByteHashSet;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Byte b) {
        return this._set.add(unwrap(b));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this._set.clear();
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this._set.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() != this._set.size()) {
            return false;
        }
        Iterator it2 = set.iterator();
        int size = set.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return true;
            }
            Object next = it2.next();
            if (!(next instanceof Byte)) {
                break;
            }
            if (!this._set.contains(unwrap(next))) {
                break;
            }
            size = i;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<Byte> iterator() {
        return new Iterator<Byte>() { // from class: gnu.trove.decorator.TByteHashSetDecorator.1

            /* JADX INFO: renamed from: it, reason: collision with root package name */
            private final TByteIterator f8it;

            {
                this.f8it = TByteHashSetDecorator.this._set.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f8it.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Byte next() {
                return TByteHashSetDecorator.this.wrap(this.f8it.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f8it.remove();
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this._set.remove(unwrap(obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this._set.size();
    }

    public byte unwrap(Object obj) {
        return ((Byte) obj).byteValue();
    }

    public Byte wrap(byte b) {
        return new Byte(b);
    }
}
