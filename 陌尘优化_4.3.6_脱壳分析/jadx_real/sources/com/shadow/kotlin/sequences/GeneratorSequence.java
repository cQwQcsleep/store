package com.shadow.kotlin.sequences;

import com.shadow.kotlin.io.CloseableKt;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
final class GeneratorSequence<T> implements kotlin.sequences.Sequence<T> {
    private final Function0<T> getInitialValue;
    private final Function1<T, T> getNextValue;

    /* renamed from: com.shadow.kotlin.sequences.GeneratorSequence$iterator$1, reason: invalid class name */
    public final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        private Object nextItem;
        private int nextState = -2;
        final /* synthetic */ kotlin.sequences.GeneratorSequence<Object> this$0;

        public AnonymousClass1(kotlin.sequences.GeneratorSequence<Object> generatorSequence) {
            this.this$0 = generatorSequence;
        }

        private final void calcNext() {
            Object objInvoke;
            int i = this.nextState;
            kotlin.sequences.GeneratorSequence<Object> generatorSequence = this.this$0;
            if (i == -2) {
                objInvoke = ((GeneratorSequence) generatorSequence).getInitialValue.invoke();
            } else {
                com.shadow.kotlin.jvm.functions.Function1 function1 = ((GeneratorSequence) generatorSequence).getNextValue;
                Object obj = this.nextItem;
                CloseableKt.checkNotNull(obj);
                objInvoke = function1.invoke(obj);
            }
            this.nextItem = objInvoke;
            this.nextState = objInvoke == null ? 0 : 1;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.nextState < 0) {
                calcNext();
            }
            return this.nextState == 1;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (this.nextState < 0) {
                calcNext();
            }
            if (this.nextState == 0) {
                throw new NoSuchElementException();
            }
            Object obj = this.nextItem;
            CloseableKt.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
            this.nextState = -1;
            return obj;
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GeneratorSequence(Function0<? extends T> function0, Function1<? super T, ? extends T> function1) {
        CloseableKt.checkNotNullParameter(function1, "getNextValue");
        this.getInitialValue = function0;
        this.getNextValue = function1;
    }

    public final Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
