package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class SupertypeComputationSession$expandTypealiasInPlace$expanded$1 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
    public SupertypeComputationSession$expandTypealiasInPlace$expanded$1(Object obj) {
        super(1, obj, SupertypeComputationSession.class, "getResolvedExpandedType", "getResolvedExpandedType(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 0);
    }

    public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        return ((SupertypeComputationSession) ((CallableReference) this).receiver).getResolvedExpandedType(firTypeAlias);
    }
}
