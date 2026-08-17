package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.SubstitutionUtilitiesKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB-\b\u0016\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0014\u0010\u0011\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FE10LikeConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "substitution", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/fir/FirSession;)V", "typeParameters", Argument.Delimiters.none, "typeArguments", "(Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;)V", "substituteType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "withProjection", "projection", "substituteArgument", "index", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FE10LikeConeSubstitutor extends AbstractConeSubstitutor {
    private final Map<FirTypeParameterSymbol, ConeTypeProjection> substitution;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FE10LikeConeSubstitutor(List<FirTypeParameterSymbol> list, List<? extends ConeTypeProjection> list2, FirSession firSession) {
        this(MapsKt.toMap(CollectionsKt.zip(list, list2)), firSession);
        list.getClass();
        list2.getClass();
        firSession.getClass();
    }

    private final ConeKotlinType withProjection(ConeKotlinType coneKotlinType, ConeTypeProjection coneTypeProjection) {
        return coneTypeProjection.getKind() == ProjectionKind.INVARIANT ? coneKotlinType : TypeUtilsKt.withAttributes(coneKotlinType, ConeAttributes.INSTANCE.create(CollectionsKt.listOf(new OriginalProjectionTypeAttribute(coneTypeProjection))));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor, org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
    public ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index) {
        ConeTypeProjection data;
        projection.getClass();
        ConeTypeProjection coneTypeProjectionSubstituteArgument = super.substituteArgument(projection, index);
        if (coneTypeProjectionSubstituteArgument == null || ConeTypeProjectionKt.isStarProjection(coneTypeProjectionSubstituteArgument)) {
            return null;
        }
        ConeTypeProjection type = ConeTypeProjectionKt.getType(coneTypeProjectionSubstituteArgument);
        type.getClass();
        OriginalProjectionTypeAttribute originalProjection = FE10LikeConeSubstitutorKt.getOriginalProjection(type.getAttributes());
        if (originalProjection == null || (data = originalProjection.getData()) == null) {
            data = type;
        }
        ProjectionKind kind = data.getKind();
        if (kind == ProjectionKind.STAR) {
            return ConeStarProjection.INSTANCE;
        }
        ProjectionKind projectionKind = ProjectionKind.INVARIANT;
        if (kind == projectionKind || kind == projection.getKind()) {
            return coneTypeProjectionSubstituteArgument;
        }
        return projection.getKind() == projectionKind ? SubstitutionUtilitiesKt.wrapProjection(data, type) : ConeStarProjection.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        ConeTypeProjection coneTypeProjection;
        type.getClass();
        if (!(type instanceof ConeTypeParameterType) || (coneTypeProjection = this.substitution.get(((ConeTypeParameterType) type).getLookupTag().getSymbol())) == null) {
            return null;
        }
        if (ConeTypeProjectionKt.isStarProjection(coneTypeProjection)) {
            return withProjection(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getAny(), null, true, null, 5, null), coneTypeProjection);
        }
        ConeKotlinType type2 = ConeTypeProjectionKt.getType(coneTypeProjection);
        type2.getClass();
        return withProjection(LookupTagUtilsKt.withCombinedAttributesFrom(updateNullabilityIfNeeded(type2, type), type), coneTypeProjection);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FE10LikeConeSubstitutor(Map<FirTypeParameterSymbol, ? extends ConeTypeProjection> map, FirSession firSession) {
        super(TypeComponentsKt.getTypeContext(firSession));
        map.getClass();
        firSession.getClass();
        this.substitution = map;
    }
}
