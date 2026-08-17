package gnu.trove;

import defpackage.z0e;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class TIterator {
    protected int _expectedSize;
    protected final THash _hash;
    protected int _index;

    public TIterator(THash tHash) {
        this._hash = tHash;
        this._expectedSize = tHash.size();
        this._index = tHash.capacity();
    }

    public boolean hasNext() {
        return nextIndex() >= 0;
    }

    public final void moveToNextIndex() {
        int iNextIndex = nextIndex();
        this._index = iNextIndex;
        if (iNextIndex >= 0) {
            return;
        }
        z0e.a();
    }

    public abstract int nextIndex();

    public void remove() {
        if (this._expectedSize != this._hash.size()) {
            a1e.a();
            return;
        }
        this._hash.stopCompactingOnRemove();
        try {
            this._hash.removeAt(this._index);
            this._hash.startCompactingOnRemove(false);
            this._expectedSize--;
        } catch (Throwable th) {
            this._hash.startCompactingOnRemove(false);
            throw th;
        }
    }
}
