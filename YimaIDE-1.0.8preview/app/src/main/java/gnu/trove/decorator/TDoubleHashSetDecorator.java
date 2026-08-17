package gnu.trove.decorator;

import gnu.trove.TDoubleHashSet;
import gnu.trove.TDoubleIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleHashSetDecorator extends AbstractSet<Double> implements Set<Double> {
    protected final TDoubleHashSet _set;

    public TDoubleHashSetDecorator(TDoubleHashSet tDoubleHashSet) {
        this._set = tDoubleHashSet;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Double d) {
        return this._set.add(unwrap(d));
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
            if (!(next instanceof Double)) {
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
    public Iterator<Double> iterator() {
        return new Iterator<Double>() { // from class: gnu.trove.decorator.TDoubleHashSetDecorator.1

            /* JADX INFO: renamed from: it, reason: collision with root package name */
            private final TDoubleIterator f15it;

            {
                this.f15it = TDoubleHashSetDecorator.this._set.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f15it.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Double next() {
                return TDoubleHashSetDecorator.this.wrap(this.f15it.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f15it.remove();
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

    public double unwrap(Object obj) {
        return ((Double) obj).doubleValue();
    }

    public Double wrap(double d) {
        return new Double(d);
    }
}
