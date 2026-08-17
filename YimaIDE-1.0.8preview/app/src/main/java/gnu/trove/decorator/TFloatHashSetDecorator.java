package gnu.trove.decorator;

import gnu.trove.TFloatHashSet;
import gnu.trove.TFloatIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatHashSetDecorator extends AbstractSet<Float> implements Set<Float> {
    protected final TFloatHashSet _set;

    public TFloatHashSetDecorator(TFloatHashSet tFloatHashSet) {
        this._set = tFloatHashSet;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Float f) {
        return this._set.add(unwrap(f));
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
            if (!(next instanceof Float)) {
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
    public Iterator<Float> iterator() {
        return new Iterator<Float>() { // from class: gnu.trove.decorator.TFloatHashSetDecorator.1

            /* JADX INFO: renamed from: it, reason: collision with root package name */
            private final TFloatIterator f22it;

            {
                this.f22it = TFloatHashSetDecorator.this._set.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f22it.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Float next() {
                return TFloatHashSetDecorator.this.wrap(this.f22it.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f22it.remove();
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

    public float unwrap(Object obj) {
        return ((Float) obj).floatValue();
    }

    public Float wrap(float f) {
        return new Float(f);
    }
}
