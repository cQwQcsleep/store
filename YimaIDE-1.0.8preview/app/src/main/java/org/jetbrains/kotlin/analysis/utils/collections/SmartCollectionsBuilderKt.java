package org.jetbrains.kotlin.analysis.utils.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"buildSmartList", "", "E", "build", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SmartCollectionsBuilderKt {
    public static final <E> List<E> buildSmartList(Function1<? super List<E>, Unit> function1) {
        function1.getClass();
        SmartList smartList = new SmartList();
        function1.invoke(smartList);
        return smartList;
    }
}
