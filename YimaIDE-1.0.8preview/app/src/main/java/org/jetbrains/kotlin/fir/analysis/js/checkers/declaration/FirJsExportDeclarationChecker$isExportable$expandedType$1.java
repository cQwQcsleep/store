package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirJsExportDeclarationChecker$isExportable$expandedType$1 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
    public static final FirJsExportDeclarationChecker$isExportable$expandedType$1 INSTANCE = new FirJsExportDeclarationChecker$isExportable$expandedType$1();

    public FirJsExportDeclarationChecker$isExportable$expandedType$1() {
        super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
    }

    public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
    }
}
