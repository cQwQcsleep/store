package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class S implements U {
    public final /* synthetic */ Spliterator a;

    private /* synthetic */ S(Spliterator spliterator) {
        this.a = spliterator;
    }

    public static /* synthetic */ U a(Spliterator spliterator) {
        if (spliterator == null) {
            return null;
        }
        return spliterator instanceof T ? ((T) spliterator).a : spliterator instanceof Spliterator.OfPrimitive ? O.a((Spliterator.OfPrimitive) spliterator) : new S(spliterator);
    }

    @Override // j$.util.U
    public final /* synthetic */ int characteristics() {
        return this.a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Spliterator spliterator = this.a;
        if (obj instanceof S) {
            obj = ((S) obj).a;
        }
        return spliterator.equals(obj);
    }

    @Override // j$.util.U
    public final /* synthetic */ long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.a.forEachRemaining(consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.a.getComparator();
    }

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.a.getExactSizeIfKnown();
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return this.a.hasCharacteristics(i);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.a.tryAdvance(consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ U trySplit() {
        return a(this.a.trySplit());
    }
}
