package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirProviderImplKt$sam$java_util_function_BiFunction$0 implements BiFunction {
    private final /* synthetic */ Function2 function;

    public FirProviderImplKt$sam$java_util_function_BiFunction$0(Function2 function2) {
        function2.getClass();
        this.function = function2;
    }

    @Override // java.util.function.BiFunction
    public final /* synthetic */ Object apply(Object obj, Object obj2) {
        return this.function.invoke(obj, obj2);
    }
}
