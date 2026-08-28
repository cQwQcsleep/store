package com.shadow.kotlin.sequences;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.Lambda;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class TransformingSequence<T, R> implements kotlin.sequences.Sequence<R> {
    private final kotlin.sequences.Sequence<T> sequence;
    private final Lambda transformer;

    /* renamed from: com.shadow.kotlin.sequences.TransformingSequence$iterator$1, reason: invalid class name */
    public final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        private final Iterator<Object> iterator;
        final /* synthetic */ kotlin.sequences.TransformingSequence<Object, Object> this$0;

        public AnonymousClass1(kotlin.sequences.TransformingSequence<Object, Object> transformingSequence) {
            this.this$0 = transformingSequence;
            this.iterator = ((TransformingSequence) transformingSequence).sequence.iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public final Object next() {
            return ((TransformingSequence) this.this$0).transformer.invoke(this.iterator.next());
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TransformingSequence(kotlin.sequences.Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        CloseableKt.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.transformer = (Lambda) function1;
    }

    public final Iterator<R> iterator() {
        return new AnonymousClass1(this);
    }
}
