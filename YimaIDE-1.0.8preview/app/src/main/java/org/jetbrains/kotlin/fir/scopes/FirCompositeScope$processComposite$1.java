package org.jetbrains.kotlin.fir.scopes;

import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompositeScope$processComposite$1 implements Function1 {
    final /* synthetic */ Function1 $processor;
    final /* synthetic */ Set $unique;

    public FirCompositeScope$processComposite$1(Set set, Function1 function1) {
        this.$unique = set;
        this.$processor = function1;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m618invoke(Object obj) {
        if (this.$unique.add(obj)) {
            this.$processor.invoke(obj);
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m618invoke(obj);
        return Unit.INSTANCE;
    }
}
