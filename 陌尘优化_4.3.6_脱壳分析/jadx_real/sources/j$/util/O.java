package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class O implements Q {
    public final /* synthetic */ Spliterator.OfPrimitive a;

    private /* synthetic */ O(Spliterator.OfPrimitive ofPrimitive) {
        this.a = ofPrimitive;
    }

    public static /* synthetic */ Q a(Spliterator.OfPrimitive ofPrimitive) {
        if (ofPrimitive == null) {
            return null;
        }
        return ofPrimitive instanceof P ? ((P) ofPrimitive).a : ofPrimitive instanceof Spliterator.OfDouble ? F.a((Spliterator.OfDouble) ofPrimitive) : ofPrimitive instanceof Spliterator.OfInt ? I.a((Spliterator.OfInt) ofPrimitive) : ofPrimitive instanceof Spliterator.OfLong ? L.a((Spliterator.OfLong) ofPrimitive) : new O(ofPrimitive);
    }

    @Override // j$.util.U
    public final /* synthetic */ int characteristics() {
        return this.a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Spliterator.OfPrimitive ofPrimitive = this.a;
        if (obj instanceof O) {
            obj = ((O) obj).a;
        }
        return ofPrimitive.equals(obj);
    }

    @Override // j$.util.U
    public final /* synthetic */ long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // j$.util.Q
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.a.forEachRemaining((Spliterator.OfPrimitive) obj);
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

    @Override // j$.util.Q
    public final /* synthetic */ boolean tryAdvance(Object obj) {
        return this.a.tryAdvance((Spliterator.OfPrimitive) obj);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.a.tryAdvance(consumer);
    }

    @Override // j$.util.Q, j$.util.U
    public final /* synthetic */ Q trySplit() {
        return a(this.a.trySplit());
    }

    @Override // j$.util.U
    public final /* synthetic */ U trySplit() {
        return S.a(this.a.trySplit());
    }
}
