package com.shadow.kotlin.sequences;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.functions.Function0;
import com.shadow.kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class SequencesKt extends SequencesKt__SequencesJVMKt {
    public static Sequence a(Function0 function0, Function1 function1) {
        CloseableKt.checkNotNullParameter(function1, "nextFunction");
        return new GeneratorSequence(function0, function1);
    }
}
