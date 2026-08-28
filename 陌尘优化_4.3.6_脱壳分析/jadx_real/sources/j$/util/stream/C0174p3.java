package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.p3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0174p3 implements j$.util.U, Consumer {
    private static final Object d = new Object();
    private final j$.util.U a;
    private final ConcurrentHashMap b;
    private Object c;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return AbstractC0078b.d(this);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    C0174p3(j$.util.U u) {
        this(u, new ConcurrentHashMap());
    }

    private C0174p3(j$.util.U u, ConcurrentHashMap concurrentHashMap) {
        this.a = u;
        this.b = concurrentHashMap;
    }

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final void q(Object obj) {
        this.c = obj;
    }

    final void b(Consumer consumer, Object obj) {
        if (this.b.putIfAbsent(obj != null ? obj : d, Boolean.TRUE) == null) {
            consumer.q(obj);
        }
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        while (this.a.tryAdvance(this)) {
            Object obj = this.c;
            if (obj == null) {
                obj = d;
            }
            if (this.b.putIfAbsent(obj, Boolean.TRUE) == null) {
                consumer.q(this.c);
                this.c = null;
                return true;
            }
        }
        return false;
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        this.a.forEachRemaining(new C0180r0(1, this, consumer));
    }

    @Override // j$.util.U
    public final j$.util.U trySplit() {
        j$.util.U uTrySplit = this.a.trySplit();
        if (uTrySplit != null) {
            return new C0174p3(uTrySplit, this.b);
        }
        return null;
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // j$.util.U
    public final int characteristics() {
        return (this.a.characteristics() & (-16469)) | 1;
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        return this.a.getComparator();
    }
}
