package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.Comparator;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.i3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0139i3 implements j$.util.U {
    final boolean a;
    final AbstractC0100b b;
    private Supplier c;
    j$.util.U d;
    InterfaceC0182r2 e;
    BooleanSupplier f;
    long g;
    AbstractC0110d h;
    boolean i;

    abstract void d();

    abstract AbstractC0139i3 e(j$.util.U u);

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    AbstractC0139i3(AbstractC0100b abstractC0100b, Supplier supplier, boolean z) {
        this.b = abstractC0100b;
        this.c = supplier;
        this.d = null;
        this.a = z;
    }

    AbstractC0139i3(AbstractC0100b abstractC0100b, j$.util.U u, boolean z) {
        this.b = abstractC0100b;
        this.c = null;
        this.d = u;
        this.a = z;
    }

    final void c() {
        if (this.d == null) {
            this.d = (j$.util.U) this.c.get();
            this.c = null;
        }
    }

    final boolean a() {
        AbstractC0110d abstractC0110d = this.h;
        if (abstractC0110d == null) {
            if (this.i) {
                return false;
            }
            c();
            d();
            this.g = 0L;
            this.e.m(this.d.getExactSizeIfKnown());
            return b();
        }
        long j = this.g + 1;
        this.g = j;
        boolean z = j < abstractC0110d.count();
        if (z) {
            return z;
        }
        this.g = 0L;
        this.h.clear();
        return b();
    }

    @Override // j$.util.U
    public j$.util.U trySplit() {
        if (!this.a || this.h != null || this.i) {
            return null;
        }
        c();
        j$.util.U uTrySplit = this.d.trySplit();
        if (uTrySplit == null) {
            return null;
        }
        return e(uTrySplit);
    }

    private boolean b() {
        while (this.h.count() == 0) {
            if (this.e.o() || !this.f.getAsBoolean()) {
                if (this.i) {
                    return false;
                }
                this.e.l();
                this.i = true;
            }
        }
        return true;
    }

    @Override // j$.util.U
    public final long estimateSize() {
        c();
        return this.d.estimateSize();
    }

    @Override // j$.util.U
    public final long getExactSizeIfKnown() {
        c();
        if (EnumC0129g3.SIZED.n(this.b.K())) {
            return this.d.getExactSizeIfKnown();
        }
        return -1L;
    }

    @Override // j$.util.U
    public final int characteristics() {
        c();
        int iW = EnumC0129g3.w(this.b.K()) & EnumC0129g3.f;
        return (iW & 64) != 0 ? (iW & (-16449)) | (this.d.characteristics() & 16448) : iW;
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        if (AbstractC0078b.e(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }

    public final String toString() {
        return String.format("%s[%s]", getClass().getName(), this.d);
    }
}
