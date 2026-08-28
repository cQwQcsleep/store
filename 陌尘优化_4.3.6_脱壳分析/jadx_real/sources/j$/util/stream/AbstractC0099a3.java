package j$.util.stream;

import java.util.Arrays;
import java.util.Spliterator;

/* renamed from: j$.util.stream.a3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0099a3 extends AbstractC0110d implements Iterable {
    Object e;
    Object[] f;

    public abstract Object c(int i);

    protected abstract void s(Object obj, int i, int i2, Object obj2);

    public abstract j$.util.U spliterator();

    @Override // java.lang.Iterable
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.T.a(spliterator());
    }

    protected abstract int t(Object obj);

    protected abstract Object[] w();

    AbstractC0099a3(int i) {
        super(i);
        this.e = c(1 << this.a);
    }

    AbstractC0099a3() {
        this.e = c(16);
    }

    protected final void v(long j) {
        long jT;
        int i = this.c;
        if (i == 0) {
            jT = t(this.e);
        } else {
            jT = t(this.f[i]) + this.d[i];
        }
        if (j > jT) {
            if (this.f == null) {
                Object[] objArrW = w();
                this.f = objArrW;
                this.d = new long[8];
                objArrW[0] = this.e;
            }
            int i2 = this.c + 1;
            while (j > jT) {
                Object[] objArr = this.f;
                if (i2 >= objArr.length) {
                    int length = objArr.length * 2;
                    this.f = Arrays.copyOf(objArr, length);
                    this.d = Arrays.copyOf(this.d, length);
                }
                int iMin = this.a;
                if (i2 != 0 && i2 != 1) {
                    iMin = Math.min((iMin + i2) - 1, 30);
                }
                int i3 = 1 << iMin;
                this.f[i2] = c(i3);
                long[] jArr = this.d;
                jArr[i2] = jArr[i2 - 1] + t(this.f[r6]);
                jT += i3;
                i2++;
            }
        }
    }

    protected final int u(long j) {
        if (this.c == 0) {
            if (j < this.b) {
                return 0;
            }
            throw new IndexOutOfBoundsException(Long.toString(j));
        }
        if (j >= count()) {
            throw new IndexOutOfBoundsException(Long.toString(j));
        }
        for (int i = 0; i <= this.c; i++) {
            if (j < this.d[i] + t(this.f[i])) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException(Long.toString(j));
    }

    public void d(Object obj, int i) {
        long j = i;
        long jCount = count() + j;
        if (jCount > t(obj) || jCount < j) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.c == 0) {
            System.arraycopy(this.e, 0, obj, i, this.b);
            return;
        }
        for (int i2 = 0; i2 < this.c; i2++) {
            Object obj2 = this.f[i2];
            System.arraycopy(obj2, 0, obj, i, t(obj2));
            i += t(this.f[i2]);
        }
        int i3 = this.b;
        if (i3 > 0) {
            System.arraycopy(this.e, 0, obj, i, i3);
        }
    }

    public Object e() {
        long jCount = count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object objC = c((int) jCount);
        d(objC, 0);
        return objC;
    }

    protected final void x() {
        long jT;
        if (this.b == t(this.e)) {
            if (this.f == null) {
                Object[] objArrW = w();
                this.f = objArrW;
                this.d = new long[8];
                objArrW[0] = this.e;
            }
            int i = this.c;
            int i2 = i + 1;
            Object[] objArr = this.f;
            if (i2 >= objArr.length || objArr[i2] == null) {
                if (i == 0) {
                    jT = t(this.e);
                } else {
                    jT = t(objArr[i]) + this.d[i];
                }
                v(jT + 1);
            }
            this.b = 0;
            int i3 = this.c + 1;
            this.c = i3;
            this.e = this.f[i3];
        }
    }

    @Override // j$.util.stream.AbstractC0110d
    public final void clear() {
        Object[] objArr = this.f;
        if (objArr != null) {
            this.e = objArr[0];
            this.f = null;
            this.d = null;
        }
        this.b = 0;
        this.c = 0;
    }

    public void f(Object obj) {
        for (int i = 0; i < this.c; i++) {
            Object obj2 = this.f[i];
            s(obj2, 0, t(obj2), obj);
        }
        s(this.e, 0, this.b, obj);
    }
}
