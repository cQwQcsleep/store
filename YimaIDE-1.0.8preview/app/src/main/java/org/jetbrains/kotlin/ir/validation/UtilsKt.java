package org.jetbrains.kotlin.ir.validation;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aE\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0014\b\u0004\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"temporarilyPushing", "R", "E", "", "element", "body", "Lkotlin/Function1;", "(Ljava/util/List;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:ir.validation"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UtilsKt {
    public static final <E, R> R temporarilyPushing(List<E> list, E e, Function1<? super E, ? extends R> function1) {
        list.getClass();
        function1.getClass();
        list.add(e);
        R r = (R) function1.invoke(e);
        list.remove(list.size() - 1);
        return r;
    }
}
