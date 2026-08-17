package org.jetbrains.kotlin.asJava.classes;

import com.intellij.openapi.util.Computable;
import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtClassOrObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 176)
public final class UltraLightUtilsKt$safeIsLocal$$inlined$runReadAction$1<T> implements Computable {
    final /* synthetic */ KtClassOrObject $this_safeIsLocal$inlined;

    public UltraLightUtilsKt$safeIsLocal$$inlined$runReadAction$1(KtClassOrObject ktClassOrObject) {
        this.$this_safeIsLocal$inlined = ktClassOrObject;
    }

    public final T compute() {
        return (T) Boolean.valueOf(this.$this_safeIsLocal$inlined.isLocal());
    }
}
