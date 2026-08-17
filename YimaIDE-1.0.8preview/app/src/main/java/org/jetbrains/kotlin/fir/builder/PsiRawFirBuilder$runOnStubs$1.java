package org.jetbrains.kotlin.fir.builder;

import com.intellij.openapi.util.ThrowableComputable;
import java.lang.Throwable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiRawFirBuilder$runOnStubs$1<T, E extends Throwable> implements ThrowableComputable {
    final /* synthetic */ Function0<T> $body;

    /* JADX WARN: Multi-variable type inference failed */
    public PsiRawFirBuilder$runOnStubs$1(Function0<? extends T> function0) {
        this.$body = function0;
    }

    public final T compute() {
        return (T) this.$body.invoke();
    }
}
