package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class T implements Spliterator {
    public final /* synthetic */ U a;

    private /* synthetic */ T(U u) {
        this.a = u;
    }

    public static /* synthetic */ Spliterator a(U u) {
        if (u == null) {
            return null;
        }
        return u instanceof S ? ((S) u).a : u instanceof Q ? P.a((Q) u) : new T(u);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ int characteristics() {
        return this.a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        U u = this.a;
        if (obj instanceof T) {
            obj = ((T) obj).a;
        }
        return u.equals(obj);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.a.forEachRemaining(consumer);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.a.getComparator();
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.a.getExactSizeIfKnown();
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return this.a.hasCharacteristics(i);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.a.tryAdvance(consumer);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ Spliterator trySplit() {
        return a(this.a.trySplit());
    }
}
