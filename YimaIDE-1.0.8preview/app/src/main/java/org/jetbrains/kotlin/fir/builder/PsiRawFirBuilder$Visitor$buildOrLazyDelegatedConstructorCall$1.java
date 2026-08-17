package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.builder.FirLazyDelegatedConstructorCallBuilder;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitSuperReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1 implements Function0<FirDelegatedConstructorCall> {
    final /* synthetic */ FirTypeRef $constructedTypeRef;
    final /* synthetic */ boolean $isThis;

    public PsiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1(boolean z, FirTypeRef firTypeRef) {
        this.$isThis = z;
        this.$constructedTypeRef = firTypeRef;
    }

    public final FirDelegatedConstructorCall invoke() {
        FirReference firReferenceBuild;
        boolean z = this.$isThis;
        FirTypeRef firTypeRef = this.$constructedTypeRef;
        FirLazyDelegatedConstructorCallBuilder firLazyDelegatedConstructorCallBuilder = new FirLazyDelegatedConstructorCallBuilder();
        firLazyDelegatedConstructorCallBuilder.setThis(z);
        firLazyDelegatedConstructorCallBuilder.setConstructedTypeRef(firTypeRef);
        if (z) {
            FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
            firExplicitThisReferenceBuilder.setSource(null);
            firReferenceBuild = firExplicitThisReferenceBuilder.build();
        } else {
            FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
            firExplicitSuperReferenceBuilder.setSource(null);
            firExplicitSuperReferenceBuilder.setSuperTypeRef(firTypeRef);
            firReferenceBuild = firExplicitSuperReferenceBuilder.build();
        }
        firLazyDelegatedConstructorCallBuilder.setCalleeReference(firReferenceBuild);
        return firLazyDelegatedConstructorCallBuilder.mo289build();
    }
}
