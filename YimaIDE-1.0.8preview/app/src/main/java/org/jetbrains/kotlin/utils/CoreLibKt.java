package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\u0018\u0001H\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"sure", "T", "", "message", "Lkotlin/Function0;", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class CoreLibKt {
    public static final <T> T sure(T t, Function0<String> function0) {
        function0.getClass();
        if (t != null) {
            return t;
        }
        x01.a(function0.invoke());
        return null;
    }
}
