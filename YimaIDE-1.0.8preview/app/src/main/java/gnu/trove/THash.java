package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class THash implements Cloneable {
    protected static final int DEFAULT_INITIAL_CAPACITY = 4;
    protected static final float DEFAULT_LOAD_FACTOR = 0.8f;
    protected static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    protected static final int JUST_CREATED_CAPACITY = -1;
    protected transient int _deadkeys;
    protected transient int _free;
    protected final float _loadFactor;
    protected int _maxSize;
    protected transient int _size;

    public THash(int i, float f) {
        this._loadFactor = f;
        setUp(i != -1 ? ((int) (i / f)) + 1 : -1);
    }

    private void compactIfNecessary() {
        if (this._deadkeys <= this._size || capacity() <= 42) {
            return;
        }
        compact();
    }

    private void computeMaxSize(int i) {
        this._maxSize = Math.max(0, Math.min(i - 1, (int) (i * this._loadFactor)));
        this._free = i - this._size;
        this._deadkeys = 0;
    }

    public int calculateGrownCapacity() {
        return capacity() << 1;
    }

    public abstract int capacity();

    public void clear() {
        this._size = 0;
        this._free = capacity();
        this._deadkeys = 0;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void compact() {
        rehash(PrimeFinder.nextPrime(((int) (size() / this._loadFactor)) + 2));
        computeMaxSize(capacity());
    }

    public void ensureCapacity(int i) {
        if (i > this._maxSize - size()) {
            rehash(PrimeFinder.nextPrime(((int) ((i + size()) / this._loadFactor)) + 2));
            computeMaxSize(capacity());
        }
    }

    public boolean isEmpty() {
        return this._size == 0;
    }

    public final void postInsertHook(boolean z) {
        if (z) {
            this._free--;
        } else {
            this._deadkeys--;
        }
        int i = this._size + 1;
        this._size = i;
        if (i > this._maxSize || this._free == 0) {
            rehash(PrimeFinder.nextPrime(calculateGrownCapacity()));
            computeMaxSize(capacity());
        }
    }

    public abstract void rehash(int i);

    public void removeAt(int i) {
        this._size--;
        this._deadkeys++;
        compactIfNecessary();
    }

    public int setUp(int i) {
        int iNextPrime = i == -1 ? 0 : PrimeFinder.nextPrime(i);
        computeMaxSize(iNextPrime);
        return iNextPrime;
    }

    public int size() {
        return this._size;
    }

    public final void startCompactingOnRemove(boolean z) {
        int i = this._deadkeys;
        if (i > 0) {
            k2d.a("Unpaired stop/startCompactingOnRemove");
            return;
        }
        this._deadkeys = i + capacity();
        if (z) {
            compactIfNecessary();
        }
    }

    public final void stopCompactingOnRemove() {
        int i = this._deadkeys;
        if (i >= 0) {
            this._deadkeys = i - capacity();
        } else {
            k2d.a("Unpaired stop/startCompactingOnRemove");
        }
    }

    public final void trimToSize() {
        compact();
    }

    public THash(int i) {
        this(i, DEFAULT_LOAD_FACTOR);
    }

    public THash() {
        this(-1, DEFAULT_LOAD_FACTOR);
    }
}
