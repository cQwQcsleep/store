package org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtReturnsResultOfDeclaration;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponentKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u0006\u0012\u0002\b\u00030\tH\u0000\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0000¨\u0006\r"}, d2 = {"isIgnorable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "indicesOfPropagatingFunctionalParameters", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isFunctionalTypeThatReturnsUnit", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class HelpersKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final List<Integer> indicesOfPropagatingFunctionalParameters(FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firCallableSymbol.getClass();
        Object fir = firCallableSymbol.getFir();
        FirContractDescriptionOwner firContractDescriptionOwner = fir instanceof FirContractDescriptionOwner ? (FirContractDescriptionOwner) fir : null;
        if (firContractDescriptionOwner == null) {
            return CollectionsKt.emptyList();
        }
        FirContractDescription contractDescription = firContractDescriptionOwner.getContractDescription();
        FirResolvedContractDescription firResolvedContractDescription = contractDescription instanceof FirResolvedContractDescription ? (FirResolvedContractDescription) contractDescription : null;
        if (firResolvedContractDescription == null) {
            return CollectionsKt.emptyList();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<FirEffectDeclaration> it = firResolvedContractDescription.getEffects().iterator();
        while (it.hasNext()) {
            KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = it.next().getEffect();
            if (effect instanceof KtReturnsResultOfDeclaration) {
                listCreateListBuilder.add(Integer.valueOf(((KtReturnsResultOfDeclaration) effect).getValueParameterReference().getParameterIndex()));
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static final boolean isFunctionalTypeThatReturnsUnit(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeKotlinType type;
        coneKotlinType.getClass();
        firSession.getClass();
        return (FunctionalTypeUtilsKt.functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null) == null || (type = ConeTypeProjectionKt.getType((ConeTypeProjection) ArraysKt.last(coneKotlinType.getTypeArguments()))) == null || !ConeBuiltinTypeUtilsKt.isUnit(type)) ? false : true;
    }

    public static final boolean isIgnorable(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        checkerContext.getClass();
        coneKotlinType.getClass();
        return FirMustUseReturnValueStatusComponentKt.getMustUseReturnValueStatusComponent(checkerContext.getSession()).isIgnorableType(coneKotlinType);
    }
}
