package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForType$1 extends FunctionReferenceImpl implements Function1<FirCallableReferenceAccess, Boolean> {
    public FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForType$1(Object obj) {
        super(1, obj, FirDoubleColonExpressionResolver.class, "shouldTryResolveLHSAsType", "shouldTryResolveLHSAsType(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;)Z", 0);
    }

    public final Boolean invoke(FirCallableReferenceAccess firCallableReferenceAccess) {
        firCallableReferenceAccess.getClass();
        return Boolean.valueOf(((FirDoubleColonExpressionResolver) ((CallableReference) this).receiver).shouldTryResolveLHSAsType(firCallableReferenceAccess));
    }
}
