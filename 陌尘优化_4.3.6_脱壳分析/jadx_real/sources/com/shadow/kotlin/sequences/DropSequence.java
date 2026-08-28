package com.shadow.kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class DropSequence<T> implements kotlin.sequences.Sequence<T> {
    private final int count;
    private final kotlin.sequences.Sequence<T> sequence;

    /* renamed from: com.shadow.kotlin.sequences.DropSequence$iterator$1, reason: invalid class name */
    public final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        private final Iterator<Object> iterator;
        private int left;

        public AnonymousClass1(kotlin.sequences.DropSequence<Object> dropSequence) {
            this.iterator = ((DropSequence) dropSequence).sequence.iterator();
            this.left = ((DropSequence) dropSequence).count;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            Iterator<Object> it;
            while (true) {
                int i = this.left;
                it = this.iterator;
                if (i <= 0 || !it.hasNext()) {
                    break;
                }
                it.next();
                this.left--;
            }
            return it.hasNext();
        }

        @Override // java.util.Iterator
        public final Object next() {
            Iterator<Object> it;
            while (true) {
                int i = this.left;
                it = this.iterator;
                if (i <= 0 || !it.hasNext()) {
                    break;
                }
                it.next();
                this.left--;
            }
            return it.next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DropSequence(kotlin.sequences.Sequence<? extends T> sequence, int i) {
        this.sequence = sequence;
        this.count = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + '.').toString());
    }

    public final DropSequence drop(int i) {
        int i2 = this.count + i;
        return i2 < 0 ? new DropSequence(this, i) : new DropSequence(this.sequence, i2);
    }

    public final Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
