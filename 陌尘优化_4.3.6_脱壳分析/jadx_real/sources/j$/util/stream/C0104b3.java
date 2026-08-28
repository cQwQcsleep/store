package j$.util.stream;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.b3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
class C0104b3 extends AbstractC0110d implements Consumer, Iterable {
    protected Object[] e = new Object[1 << 4];
    protected Object[][] f;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.T.a(spliterator());
    }

    C0104b3() {
    }

    protected final void s(long j) {
        long length;
        int i = this.c;
        if (i == 0) {
            length = this.e.length;
        } else {
            length = this.d[i] + this.f[i].length;
        }
        if (j > length) {
            if (this.f == null) {
                Object[][] objArr = new Object[8][];
                this.f = objArr;
                this.d = new long[8];
                objArr[0] = this.e;
            }
            int i2 = i + 1;
            while (j > length) {
                Object[][] objArr2 = this.f;
                if (i2 >= objArr2.length) {
                    int length2 = objArr2.length * 2;
                    this.f = (Object[][]) Arrays.copyOf(objArr2, length2);
                    this.d = Arrays.copyOf(this.d, length2);
                }
                int iMin = this.a;
                if (i2 != 0 && i2 != 1) {
                    iMin = Math.min((iMin + i2) - 1, 30);
                }
                int i3 = 1 << iMin;
                this.f[i2] = new Object[i3];
                long[] jArr = this.d;
                jArr[i2] = jArr[i2 - 1] + r5[r7].length;
                length += i3;
                i2++;
            }
        }
    }

    @Override // j$.util.stream.AbstractC0110d
    public final void clear() {
        Object[][] objArr = this.f;
        if (objArr != null) {
            this.e = objArr[0];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.e;
                if (i >= objArr2.length) {
                    break;
                }
                objArr2[i] = null;
                i++;
            }
            this.f = null;
            this.d = null;
        } else {
            for (int i2 = 0; i2 < this.b; i2++) {
                this.e[i2] = null;
            }
        }
        this.b = 0;
        this.c = 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return j$.util.i0.i(spliterator());
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer consumer) {
        for (int i = 0; i < this.c; i++) {
            for (Object obj : this.f[i]) {
                consumer.accept(obj);
            }
        }
        for (int i2 = 0; i2 < this.b; i2++) {
            consumer.accept(this.e[i2]);
        }
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        long length;
        int i = this.b;
        Object[] objArr = this.e;
        if (i == objArr.length) {
            if (this.f == null) {
                Object[][] objArr2 = new Object[8][];
                this.f = objArr2;
                this.d = new long[8];
                objArr2[0] = objArr;
            }
            int i2 = this.c;
            int i3 = i2 + 1;
            Object[][] objArr3 = this.f;
            if (i3 >= objArr3.length || objArr3[i3] == null) {
                if (i2 == 0) {
                    length = objArr.length;
                } else {
                    length = objArr3[i2].length + this.d[i2];
                }
                s(length + 1);
            }
            this.b = 0;
            int i4 = this.c + 1;
            this.c = i4;
            this.e = this.f[i4];
        }
        Object[] objArr4 = this.e;
        int i5 = this.b;
        this.b = i5 + 1;
        objArr4[i5] = obj;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        Objects.requireNonNull(arrayList);
        forEach(new C0095a(arrayList, 10));
        return "SpinedBuffer:" + arrayList.toString();
    }

    @Override // java.lang.Iterable
    public j$.util.U spliterator() {
        return new S2(this, 0, this.c, 0, this.b);
    }
}
