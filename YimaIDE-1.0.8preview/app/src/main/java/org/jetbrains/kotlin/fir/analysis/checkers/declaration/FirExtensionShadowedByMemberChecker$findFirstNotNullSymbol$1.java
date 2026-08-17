package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExtensionShadowedByMemberChecker$findFirstNotNullSymbol$1 implements Function1 {
    final /* synthetic */ Ref.ObjectRef $found;
    final /* synthetic */ Function1 $transform;

    public FirExtensionShadowedByMemberChecker$findFirstNotNullSymbol$1(Ref.ObjectRef objectRef, Function1 function1) {
        this.$found = objectRef;
        this.$transform = function1;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m228invoke(Object obj) {
        Object objInvoke;
        if (this.$found.element != null || (objInvoke = this.$transform.invoke(obj)) == null) {
            return;
        }
        this.$found.element = objInvoke;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m228invoke(obj);
        return Unit.INSTANCE;
    }
}
