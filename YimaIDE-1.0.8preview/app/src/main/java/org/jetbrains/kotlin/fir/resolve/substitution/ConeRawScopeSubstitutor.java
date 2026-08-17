package org.jetbrains.kotlin.fir.resolve.substitution;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0082\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeRawScopeSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "substituteType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeRawScopeSubstitutor extends AbstractConeSubstitutor {
    private final FirSession useSiteSession;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeRawScopeSubstitutor(FirSession firSession) {
        super(TypeComponentsKt.getTypeContext(firSession));
        firSession.getClass();
        this.useSiteSession = firSession;
    }

    public boolean equals(Object other) {
        return (other instanceof ConeRawScopeSubstitutor) && Intrinsics.areEqual(this.useSiteSession, ((ConeRawScopeSubstitutor) other).useSiteSession);
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        ConeTypeProjection typeProjection;
        type.getClass();
        if (type instanceof ConeTypeParameterType) {
            ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) type;
            return substituteOrSelf(TypeUtilsKt.getProjectionForRawType(coneTypeParameterType.getLookupTag().getSymbol(), this.useSiteSession, coneTypeParameterType.getIsMarkedNullable()));
        }
        if (type instanceof ConeClassLikeType) {
            if (!(type.getTypeArguments().length == 0)) {
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) type;
                if (Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getArray())) {
                    ConeTypeProjection coneTypeProjection = type.getTypeArguments()[0];
                    ConeKotlinType type2 = ConeTypeProjectionKt.getType(coneTypeProjection);
                    ConeKotlinType coneKotlinTypeSubstituteOrSelf = type2 != null ? substituteOrSelf(type2) : null;
                    if (coneKotlinTypeSubstituteOrSelf == null || (typeProjection = ConeTypeUtilsKt.toTypeProjection(coneKotlinTypeSubstituteOrSelf, coneTypeProjection.getKind())) == null) {
                        typeProjection = ConeStarProjection.INSTANCE;
                    }
                    return ConeTypeUtilsKt.withArguments(coneClassLikeType, new ConeTypeProjection[]{typeProjection});
                }
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, this.useSiteSession, (Function1) null, 2, (Object) null).getLookupTag(), this.useSiteSession);
                if (regularClassSymbol == null) {
                    return null;
                }
                int length = type.getTypeArguments().length;
                boolean[] zArr = new boolean[length];
                for (int i = 0; i < length; i++) {
                    ConeKotlinType type3 = ConeTypeProjectionKt.getType(type.getTypeArguments()[i]);
                    zArr[i] = type3 != null && ConeTypeUtilsKt.isMarkedNullable(type3);
                }
                return ConeRawType.INSTANCE.create(ConeTypeUtilsKt.withArguments(coneClassLikeType, TypeUtilsKt.getProjectionsForRawType(regularClassSymbol.getTypeParameterSymbols(), this.useSiteSession, zArr)), ConeTypeUtilsKt.replaceArgumentsWithStarProjections(coneClassLikeType));
            }
        }
        if (!(type instanceof ConeFlexibleType)) {
            return null;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) type;
        ConeKotlinType coneKotlinTypeSubstituteOrNull = substituteOrNull(coneFlexibleType.getLowerBound());
        ConeKotlinType coneKotlinTypeSubstituteOrNull2 = substituteOrNull(coneFlexibleType.getUpperBound());
        if (coneKotlinTypeSubstituteOrNull == null && coneKotlinTypeSubstituteOrNull2 == null) {
            return null;
        }
        if (coneKotlinTypeSubstituteOrNull == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeSubstituteOrNull)) == null) {
            lowerBound = coneFlexibleType.getLowerBound();
        }
        if (coneKotlinTypeSubstituteOrNull2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinTypeSubstituteOrNull2)) == null) {
            upperBound = coneFlexibleType.getUpperBound();
        }
        return ((coneKotlinTypeSubstituteOrNull instanceof ConeRawType) || (coneKotlinTypeSubstituteOrNull2 instanceof ConeRawType)) ? ConeRawType.INSTANCE.create(lowerBound, upperBound) : new ConeFlexibleType(lowerBound, upperBound, false);
    }
}
