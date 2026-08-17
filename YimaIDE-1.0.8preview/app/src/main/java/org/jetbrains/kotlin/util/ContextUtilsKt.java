package org.jetbrains.kotlin.util;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0003\u001a\u0002H\u00022!\u0010\u0004\u001a\u001d\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\u0002H\u0080\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\b\u001ai\u0010\u0000\u001a\u0002H\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\"\u0004\b\u0002\u0010\t2\u0006\u0010\f\u001a\u0002H\n2\u0006\u0010\r\u001a\u0002H\u000b2'\u0010\u0004\u001a#\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\t0\u000e¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\u0004H\u0080\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0002\u0010\u000f\u001a}\u0010\u0000\u001a\u0002H\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\"\u0004\b\u0002\u0010\u0010\"\u0004\b\u0003\u0010\t2\u0006\u0010\f\u001a\u0002H\n2\u0006\u0010\r\u001a\u0002H\u000b2\u0006\u0010\u0011\u001a\u0002H\u00102-\u0010\u0004\u001a)\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\t0\u0012¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\u0006H\u0080\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0004 \u0001¢\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"context", "R", "T", "with", "block", "Lkotlin/Function1;", "Lkotlin/ContextFunctionTypeParams;", "count", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Result", "A", "B", "a", "b", "Lkotlin/Function2;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "C", "c", "Lkotlin/Function3;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 2, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ContextUtilsKt {
    public static final <T, R> R context(T t, Function1<? super T, ? extends R> function1) {
        function1.getClass();
        return function1.invoke(t);
    }

    public static final <A, B, Result> Result context(A a, B b, Function2<? super A, ? super B, ? extends Result> function2) {
        function2.getClass();
        return function2.invoke(a, b);
    }

    public static final <A, B, C, Result> Result context(A a, B b, C c, Function3<? super A, ? super B, ? super C, ? extends Result> function3) {
        function3.getClass();
        return (Result) function3.invoke(a, b, c);
    }
}
