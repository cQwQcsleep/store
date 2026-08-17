package com.intellij.util;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000bB)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/intellij/util/TakeWhileInclusiveSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "NextState", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TakeWhileInclusiveSequence<T> implements Sequence<T> {
    private final Function1<T, Boolean> predicate;
    private final Sequence<T> sequence;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/intellij/util/TakeWhileInclusiveSequence$NextState;", "", "<init>", "(Ljava/lang/String;I)V", "UNKNOWN", "DONE", "CONTINUE", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum NextState {
        UNKNOWN,
        DONE,
        CONTINUE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    }

    /* JADX INFO: renamed from: com.intellij.util.TakeWhileInclusiveSequence$iterator$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010J\t\u0010\u0017\u001a\u00020\u0018H\u0096\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001e\u0010\u000e\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0019"}, d2 = {"com/intellij/util/TakeWhileInclusiveSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "currentState", "Lcom/intellij/util/TakeWhileInclusiveSequence$NextState;", "getCurrentState", "()Lcom/intellij/util/TakeWhileInclusiveSequence$NextState;", "setCurrentState", "(Lcom/intellij/util/TakeWhileInclusiveSequence$NextState;)V", "nextState", "getNextState", "setNextState", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "calcNext", "", "next", "hasNext", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        private NextState currentState;
        private final Iterator<T> iterator;
        private T nextItem;
        private NextState nextState;
        final /* synthetic */ TakeWhileInclusiveSequence<T> this$0;

        public AnonymousClass1(TakeWhileInclusiveSequence<T> takeWhileInclusiveSequence) {
            this.this$0 = takeWhileInclusiveSequence;
            this.iterator = ((TakeWhileInclusiveSequence) takeWhileInclusiveSequence).sequence.iterator();
            NextState nextState = NextState.UNKNOWN;
            this.currentState = nextState;
            this.nextState = nextState;
        }

        private final void calcNext() {
            if (!this.iterator.hasNext()) {
                NextState nextState = NextState.DONE;
                this.currentState = nextState;
                this.nextState = nextState;
            } else {
                T next = this.iterator.next();
                this.nextItem = next;
                this.currentState = NextState.CONTINUE;
                this.nextState = ((Boolean) ((TakeWhileInclusiveSequence) this.this$0).predicate.invoke(next)).booleanValue() ? NextState.UNKNOWN : NextState.DONE;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.currentState == NextState.UNKNOWN) {
                calcNext();
            }
            return this.currentState == NextState.CONTINUE;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                z0e.a();
                return null;
            }
            T t = this.nextItem;
            this.nextItem = null;
            this.currentState = this.nextState;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TakeWhileInclusiveSequence(Sequence<? extends T> sequence, Function1<? super T, Boolean> function1) {
        sequence.getClass();
        function1.getClass();
        this.sequence = sequence;
        this.predicate = function1;
    }

    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
