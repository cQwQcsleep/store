package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.contracts.builder.FirLazyContractDescriptionBuilderKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class PsiRawFirBuilder$Visitor$buildFirBody$contractDescription$2 extends FunctionReferenceImpl implements Function0<FirLazyContractDescription> {
    public static final PsiRawFirBuilder$Visitor$buildFirBody$contractDescription$2 INSTANCE = new PsiRawFirBuilder$Visitor$buildFirBody$contractDescription$2();

    public PsiRawFirBuilder$Visitor$buildFirBody$contractDescription$2() {
        super(0, FirLazyContractDescriptionBuilderKt.class, "buildLazyContractDescription", "buildLazyContractDescription()Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", 1);
    }

    public final FirLazyContractDescription invoke() {
        return FirLazyContractDescriptionBuilderKt.buildLazyContractDescription();
    }
}
