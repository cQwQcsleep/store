package org.jetbrains.kotlin.fir.expressions;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0006¨\u0006\n"}, d2 = {"createConeSubstitutorFromTypeArguments", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "discardErrorTypes", Argument.Delimiters.none, "callableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "unwrapExplicitTypeArgumentForMadeFlexibleSynthetically", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SubstitutionUtilsKt {
    public static final ConeSubstitutor createConeSubstitutorFromTypeArguments(FirQualifiedAccessExpression firQualifiedAccessExpression, FirCallableSymbol<?> firCallableSymbol, FirSession firSession, boolean z, boolean z2) {
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute explicitTypeArgumentIfMadeFlexibleSynthetically;
        ConeKotlinType coneType2;
        firQualifiedAccessExpression.getClass();
        firCallableSymbol.getClass();
        firSession.getClass();
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        Iterator it = CollectionsKt.zip(firQualifiedAccessExpression.getTypeArguments(), firCallableSymbol.getTypeParameterSymbols()).iterator();
        while (true) {
            if (!it.hasNext()) {
                return ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.build(mapCreateMapBuilder), firSession, false, 4, null);
            }
            Pair pair = (Pair) it.next();
            FirTypeProjection firTypeProjection = (FirTypeProjection) pair.component1();
            FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) pair.component2();
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = firTypeProjection instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) firTypeProjection : null;
            if (firTypeProjectionWithVariance != null && (typeRef = firTypeProjectionWithVariance.getTypeRef()) != null && (coneType = FirTypeUtilsKt.getConeType(typeRef)) != null && (!(coneType instanceof ConeErrorType) || !z)) {
                if (z2 && (explicitTypeArgumentIfMadeFlexibleSynthetically = ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt.getExplicitTypeArgumentIfMadeFlexibleSynthetically(coneType.getAttributes())) != null && (coneType2 = explicitTypeArgumentIfMadeFlexibleSynthetically.getConeType()) != null) {
                    coneType = coneType2;
                }
                mapCreateMapBuilder.put(firTypeParameterSymbol, coneType);
            }
        }
    }

    public static /* synthetic */ ConeSubstitutor createConeSubstitutorFromTypeArguments$default(FirQualifiedAccessExpression firQualifiedAccessExpression, FirCallableSymbol firCallableSymbol, FirSession firSession, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return createConeSubstitutorFromTypeArguments(firQualifiedAccessExpression, firCallableSymbol, firSession, z, z2);
    }

    public static /* synthetic */ ConeSubstitutor createConeSubstitutorFromTypeArguments$default(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return createConeSubstitutorFromTypeArguments(firQualifiedAccessExpression, firSession, z);
    }

    public static final ConeSubstitutor createConeSubstitutorFromTypeArguments(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, boolean z) {
        firQualifiedAccessExpression.getClass();
        firSession.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null) {
            return null;
        }
        return createConeSubstitutorFromTypeArguments$default(firQualifiedAccessExpression, resolvedCallableSymbol$default, firSession, z, false, 8, null);
    }
}
