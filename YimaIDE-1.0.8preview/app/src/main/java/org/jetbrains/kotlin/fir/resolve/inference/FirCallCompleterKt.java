package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.TypeArgumentMapping;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleterKt;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"isFunctionForExpectTypeFromCastFeature", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "unwrap", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallCompleterKt {
    public static boolean a(FirTypeParameterRef firTypeParameterRef, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrap = unwrap(coneKotlinType);
        ConeTypeParameterType coneTypeParameterType = coneSimpleKotlinTypeUnwrap instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneSimpleKotlinTypeUnwrap : null;
        return Intrinsics.areEqual(coneTypeParameterType != null ? coneTypeParameterType.getLookupTag() : null, firTypeParameterRef.getSymbol().getLookupTag());
    }

    public static final boolean isFunctionForExpectTypeFromCastFeature(FirFunction firFunction) {
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        firFunction.getClass();
        FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) CollectionsKt.singleOrNull(firFunction.getTypeParameters());
        if (firTypeParameterRef == null) {
            return false;
        }
        FirResolvedTypeRef returnTypeRef = firFunction.getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            coneType = null;
        }
        if (coneType == null) {
            return false;
        }
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrap = unwrap(coneType);
        ConeTypeParameterType coneTypeParameterType = coneSimpleKotlinTypeUnwrap instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneSimpleKotlinTypeUnwrap : null;
        if (!Intrinsics.areEqual(coneTypeParameterType != null ? coneTypeParameterType.getLookupTag() : null, firTypeParameterRef.getSymbol().getLookupTag())) {
            return false;
        }
        List<FirValueParameter> valueParameters = firFunction.getValueParameters();
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            receiverParameter = firFunction.getReceiverParameter();
            if (receiverParameter != null) {
            }
            return true;
        }
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameter) it.next()).getReturnTypeRef(), firTypeParameterRef)) {
            }
        }
        receiverParameter = firFunction.getReceiverParameter();
        if (receiverParameter != null || (typeRef = receiverParameter.getTypeRef()) == null || !isFunctionForExpectTypeFromCastFeature$isBadType(typeRef, firTypeParameterRef)) {
            return true;
        }
        return false;
    }

    private static final boolean isFunctionForExpectTypeFromCastFeature$isBadType(FirTypeRef firTypeRef, final FirTypeParameterRef firTypeParameterRef) {
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeKotlinType coneKotlinType = coneType != null ? coneType : null;
        boolean z = false;
        if (coneKotlinType != null && !ConeTypeUtilsKt.contains(coneKotlinType, new Function1() { // from class: uy4
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirCallCompleterKt.a(firTypeParameterRef, (ConeKotlinType) obj));
            }
        })) {
            z = true;
        }
        return !z;
    }

    private static final ConeSimpleKotlinType unwrap(ConeKotlinType coneKotlinType) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        if (coneRigidTypeLowerBoundIfFlexible instanceof ConeDefinitelyNotNullType) {
            return unwrap(((ConeDefinitelyNotNullType) coneRigidTypeLowerBoundIfFlexible).getOriginal());
        }
        if (coneRigidTypeLowerBoundIfFlexible instanceof ConeSimpleKotlinType) {
            return (ConeSimpleKotlinType) coneRigidTypeLowerBoundIfFlexible;
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isFunctionForExpectTypeFromCastFeature(Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (!Intrinsics.areEqual(candidate.getTypeArgumentMapping(), TypeArgumentMapping.NoExplicitArguments.INSTANCE)) {
            return false;
        }
        FirDeclaration fir = candidate.getSymbol().getFir();
        FirFunction firFunction = fir instanceof FirFunction ? (FirFunction) fir : null;
        if (firFunction == null) {
            return false;
        }
        return isFunctionForExpectTypeFromCastFeature(firFunction);
    }
}
