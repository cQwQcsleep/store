package com.shadow.kotlin.sequences;

import com.shadow.kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import com.shadow.kotlin.jvm.functions.Function2;
import java.util.Iterator;
import kotlin.coroutines.Continuation;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 implements kotlin.sequences.Sequence<Object> {
    final /* synthetic */ RestrictedSuspendLambda $block$inlined;

    /* JADX WARN: Multi-variable type inference failed */
    public SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(Function2 function2) {
        this.$block$inlined = (RestrictedSuspendLambda) function2;
    }

    public final Iterator<Object> iterator() {
        RestrictedSuspendLambda restrictedSuspendLambda = this.$block$inlined;
        Continuation<?> sequenceBuilderIterator = new SequenceBuilderIterator<>();
        sequenceBuilderIterator.setNextStep(restrictedSuspendLambda.create(sequenceBuilderIterator, sequenceBuilderIterator));
        return sequenceBuilderIterator;
    }
}
