package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUnificationKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"computeRepresentativeTypeForBareType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "originalType", "canBeUsedAsBareType", Argument.Delimiters.none, "firTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BareTypesKt {
    private static final boolean canBeUsedAsBareType(FirTypeAlias firTypeAlias) {
        ConeTypeParameterLookupTag lookupTag;
        FirTypeParameterSymbol typeParameterSymbol;
        FirTypeParameter firTypeParameter;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firTypeAlias, FirResolvePhase.TYPES);
        Set set = CollectionsKt.toSet(firTypeAlias.getTypeParameters());
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType(firTypeAlias);
        if (expandedConeType == null) {
            return false;
        }
        for (ConeTypeProjection coneTypeProjection : expandedConeType.getTypeArguments()) {
            if (coneTypeProjection.getKind() != ProjectionKind.STAR) {
                if (coneTypeProjection.getKind() != ProjectionKind.INVARIANT) {
                    return false;
                }
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                type.getClass();
                ConeTypeParameterType coneTypeParameterType = type instanceof ConeTypeParameterType ? (ConeTypeParameterType) type : null;
                if (coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null || (typeParameterSymbol = lookupTag.getTypeParameterSymbol()) == null || (firTypeParameter = (FirTypeParameter) typeParameterSymbol.getFir()) == null || !set.contains(firTypeParameter) || linkedHashSet.contains(firTypeParameter)) {
                    return false;
                }
                linkedHashSet.add(firTypeParameter);
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeKotlinType computeRepresentativeTypeForBareType(BodyResolveComponents bodyResolveComponents, ConeClassLikeType coneClassLikeType, ConeKotlinType coneKotlinType) {
        FirRegularClass firRegularClass;
        FirTypeAliasSymbol typeAliasSymbol;
        bodyResolveComponents.getClass();
        coneClassLikeType.getClass();
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) bodyResolveComponents, ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType));
        if (coneRigidTypeFullyExpandedType != coneKotlinType) {
            return computeRepresentativeTypeForBareType(bodyResolveComponents, coneClassLikeType, coneRigidTypeFullyExpandedType);
        }
        Object obj = null;
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                ConeKotlinType coneKotlinTypeComputeRepresentativeTypeForBareType = computeRepresentativeTypeForBareType(bodyResolveComponents, coneClassLikeType, (ConeKotlinType) it.next());
                if (coneKotlinTypeComputeRepresentativeTypeForBareType != null) {
                    arrayList.add(coneKotlinTypeComputeRepresentativeTypeForBareType);
                }
            }
            for (Object obj2 : arrayList) {
                if (!(((ConeKotlinType) obj2).getTypeArguments().length == 0)) {
                    obj = obj2;
                    break;
                }
            }
            ConeKotlinType coneKotlinType2 = (ConeKotlinType) obj;
            return coneKotlinType2 != null ? coneKotlinType2 : (ConeKotlinType) CollectionsKt.firstOrNull(arrayList);
        }
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(bodyResolveComponents.getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE);
        if (coneKotlinTypeApproximateToSuperType != null) {
            return computeRepresentativeTypeForBareType(bodyResolveComponents, coneClassLikeType, coneKotlinTypeApproximateToSuperType);
        }
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(TypeExpansionUtilsKt.fullyExpandedType(bodyResolveComponents, coneKotlinType));
        if (classLikeLookupTagIfAny == null) {
            return null;
        }
        ConeClassLikeLookupTag classLikeLookupTagIfAny2 = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneClassLikeType));
        FirTypeAlias firTypeAlias = (classLikeLookupTagIfAny2 == null || (typeAliasSymbol = ToSymbolUtilsKt.toTypeAliasSymbol(bodyResolveComponents, classLikeLookupTagIfAny2)) == null) ? null : (FirTypeAlias) typeAliasSymbol.getFir();
        if (firTypeAlias != null && !canBeUsedAsBareType(firTypeAlias)) {
            return null;
        }
        ConeClassLikeType coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) bodyResolveComponents, coneClassLikeType);
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) bodyResolveComponents, coneClassLikeTypeFullyExpandedType.getLookupTag());
        if (regularClassSymbol == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null) {
            return null;
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(bodyResolveComponents.getSession());
        Object objFirstOrNull = CollectionsKt.firstOrNull(AbstractTypeChecker.INSTANCE.findCorrespondingSupertypes(TypeCheckerProviderContext.newTypeCheckerState$default(typeContext, false, false, false, 4, (Object) null), ScopeUtilsKt.defaultType(firRegularClass), classLikeLookupTagIfAny));
        ConeClassLikeType coneClassLikeType2 = objFirstOrNull instanceof ConeClassLikeType ? (ConeClassLikeType) objFirstOrNull : null;
        if (coneClassLikeType2 == null) {
            return null;
        }
        if (ConeTypeUtilsKt.isMarkedNullable(coneKotlinType)) {
            RigidTypeMarker rigidTypeMarkerM695withNullability = typeContext.m695withNullability((RigidTypeMarker) coneClassLikeType2, true);
            rigidTypeMarkerM695withNullability.getClass();
            coneClassLikeType2 = (ConeClassLikeType) rigidTypeMarkerM695withNullability;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<FirTypeParameterRef> typeParameters = firRegularClass.getTypeParameters();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it2 = typeParameters.iterator();
        while (it2.hasNext()) {
            linkedHashSet.add(((FirTypeParameterRef) it2.next()).getSymbol());
        }
        if (!TypeUnificationKt.doUnify(bodyResolveComponents.getSession(), coneKotlinType, coneClassLikeType2, linkedHashSet, linkedHashMap)) {
            return null;
        }
        List<FirTypeParameterRef> typeParameters2 = firRegularClass.getTypeParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters2, 10));
        Iterator<T> it3 = typeParameters2.iterator();
        while (it3.hasNext()) {
            ConeTypeProjection coneTypeProjection = (ConeTypeProjection) linkedHashMap.get(((FirTypeParameterRef) it3.next()).getSymbol());
            if (coneTypeProjection == null) {
                return null;
            }
            arrayList2.add(coneTypeProjection);
        }
        return ConeTypeUtilsKt.withArguments(coneClassLikeTypeFullyExpandedType, (ConeTypeProjection[]) arrayList2.toArray(new ConeTypeProjection[0]));
    }
}
