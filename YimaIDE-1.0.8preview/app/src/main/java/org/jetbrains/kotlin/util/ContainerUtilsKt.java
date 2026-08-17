package org.jetbrains.kotlin.util;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\u001a6\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"bfs", "Lkotlin/sequences/Sequence;", "T", "", "getNeighbors", "Lkotlin/Function1;", "", "org.jetbrains.kotlin:compiler.common"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class ContainerUtilsKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: org.jetbrains.kotlin.util.ContainerUtilsKt$bfs$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class AnonymousClass1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<T, Iterator<T>> $getNeighbors;
        final /* synthetic */ Collection<T> $this_bfs;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Collection<? extends T> collection, Function1<? super T, ? extends Iterator<? extends T>> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_bfs = collection;
            this.$getNeighbors = function1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_bfs, this.$getNeighbors, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super T> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x003b  */
        /* JADX WARN: Code duplicated, block: B:15:0x0058 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:24:0x0045 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:26:? A[LOOP:1: B:9:0x0035->B:26:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0056 -> B:16:0x0059). Please report as a decompilation issue!!! */
        /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = r6.L$0
                kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r6.label
                r3 = 1
                if (r2 == 0) goto L24
                if (r2 != r3) goto L1d
                java.lang.Object r2 = r6.L$3
                java.lang.Object r4 = r6.L$2
                java.util.Set r4 = (java.util.Set) r4
                java.lang.Object r5 = r6.L$1
                kotlin.collections.ArrayDeque r5 = (kotlin.collections.ArrayDeque) r5
                kotlin.ResultKt.throwOnFailure(r7)
                goto L59
            L1d:
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r6)
                r6 = 0
                return r6
            L24:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlin.collections.ArrayDeque r7 = new kotlin.collections.ArrayDeque
                java.util.Collection<T> r2 = r6.$this_bfs
                r7.<init>(r2)
                java.util.LinkedHashSet r2 = new java.util.LinkedHashSet
                r2.<init>()
                r5 = r7
                r4 = r2
            L35:
                boolean r7 = r5.isEmpty()
                if (r7 != 0) goto L6f
                java.lang.Object r2 = r5.removeFirst()
                boolean r7 = r4.contains(r2)
                if (r7 != 0) goto L35
                r4.add(r2)
                r6.L$0 = r0
                r6.L$1 = r5
                r6.L$2 = r4
                r6.L$3 = r2
                r6.label = r3
                java.lang.Object r7 = r0.yield(r2, r6)
                if (r7 != r1) goto L59
                return r1
            L59:
                kotlin.jvm.functions.Function1<T, java.util.Iterator<T>> r7 = r6.$getNeighbors
                java.lang.Object r7 = r7.invoke(r2)
                java.util.Iterator r7 = (java.util.Iterator) r7
            L61:
                boolean r2 = r7.hasNext()
                if (r2 == 0) goto L35
                java.lang.Object r2 = r7.next()
                r5.add(r2)
                goto L61
            L6f:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.util.ContainerUtilsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Sequence<T> bfs(Collection<? extends T> collection, Function1<? super T, ? extends Iterator<? extends T>> function1) {
        collection.getClass();
        function1.getClass();
        return SequencesKt.sequence(new AnonymousClass1(collection, function1, null));
    }
}
