package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class ObjectPool<T> {
    private final Object[] pool;

    public ObjectPool(int i) {
        this.pool = new Object[i];
    }

    public abstract T allocateNew();

    public T obtain() {
        T t;
        synchronized (this) {
            try {
                int length = this.pool.length - 1;
                while (true) {
                    t = null;
                    if (length < 0) {
                        break;
                    }
                    Object[] objArr = this.pool;
                    Object obj = objArr[length];
                    if (obj != null) {
                        objArr[length] = null;
                        t = (T) obj;
                        break;
                    }
                    length--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t == null ? allocateNew() : t;
    }

    public void onRecycleObject(T t) {
    }

    public void recycle(T t) {
        if (t == null) {
            return;
        }
        onRecycleObject(t);
        synchronized (this) {
            int i = 0;
            while (true) {
                try {
                    Object[] objArr = this.pool;
                    if (i >= objArr.length) {
                        break;
                    }
                    if (objArr[i] == null) {
                        objArr[i] = t;
                        break;
                    }
                    i++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public ObjectPool() {
        this(16);
    }
}
