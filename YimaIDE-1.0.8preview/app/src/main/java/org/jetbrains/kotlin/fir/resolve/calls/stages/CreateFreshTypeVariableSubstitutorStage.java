package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.OnlyForDefaultLanguageFeatureDisabled;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.InferenceError;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeParameterBasedTypeVariable;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeDeclaredUpperBoundConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeExplicitTypeParameterConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemOperation;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.inference.model.SimpleConstraintSystemConstraintPosition;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ)\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012J%\u0010\u0013\u001a\u00020\u0014*\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0017J;\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010!JI\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u00190\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001bH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010&J_\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u00190(2\u0006\u0010#\u001a\u00020\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u0003R\u00020\bb\u000e\b+\u0012\n\b,\u0012\u0006\b\n0-8.j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010*J%\u0010/\u001a\u000200*\u0002002\u0006\u00101\u001a\u000202H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00103¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CreateFreshTypeVariableSubstitutorStage;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTypePreservingFlexibilityWrtTypeVariable", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "shouldExplicitArgumentBeFlexibleForGivenParameter", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;)Z", "mayExplicitArgumentBeFlexibleForGivenParameter", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;)Z", "createToFreshVariableSubstitutorAndAddInitialConstraints", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "csBuilder", "Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemOperation;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemOperation;)Lkotlin/Pair;", "addConstraintsProperly", "toFreshVariables", "freshTypeVariables", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeParameterBasedTypeVariable;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Ljava/util/List;)Ljava/util/List;", "addConstraintsTheOldWay", Argument.Delimiters.none, "typeParameters", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables", "getTypeParameterFromExpandedClass", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "index", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;I)Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CreateFreshTypeVariableSubstitutorStage extends ResolutionStage {
    public static final CreateFreshTypeVariableSubstitutorStage INSTANCE = new CreateFreshTypeVariableSubstitutorStage();

    private CreateFreshTypeVariableSubstitutorStage() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<Pair<ConeKotlinType, ConeKotlinType>> addConstraintsProperly(ResolutionContext resolutionContext, FirTypeParameterRefsOwner firTypeParameterRefsOwner, ConeSubstitutor coneSubstitutor, List<ConeTypeParameterBasedTypeVariable> list) {
        Pair pair;
        ConeKotlinType type;
        ConeKotlinType coneKotlinTypeSubstituteOrSelf;
        List<FirTypeParameterRef> listEmptyList;
        FirClassLikeDeclaration firClassLikeDeclaration;
        FirConstructor firConstructor = firTypeParameterRefsOwner instanceof FirConstructor ? (FirConstructor) firTypeParameterRefsOwner : null;
        if ((firConstructor != null ? TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructor) : null) != null) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firTypeParameterRefsOwner;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(resolutionContext, FirTypeUtilsKt.getConeType(((FirConstructor) firCallableDeclaration).getReturnTypeRef()));
            List list2 = ArraysKt.toList(coneSubstitutor.substituteOrSelf(coneKotlinTypeFullyExpandedType).getTypeArguments());
            FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(resolutionContext, coneKotlinTypeFullyExpandedType);
            if (classLikeSymbol == null || (firClassLikeDeclaration = (FirClassLikeDeclaration) classLikeSymbol.getFir()) == null || (listEmptyList = firClassLikeDeclaration.getTypeParameters()) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            pair = TuplesKt.to(list2, listEmptyList);
        } else {
            List<ConeTypeParameterBasedTypeVariable> list3 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator<T> it = list3.iterator();
            while (it.hasNext()) {
                arrayList.add(ConeTypeUtilsKt.toTypeProjection(((ConeTypeParameterBasedTypeVariable) it.next()).getDefaultType(), ProjectionKind.INVARIANT));
            }
            pair = TuplesKt.to(arrayList, firTypeParameterRefsOwner.getTypeParameters());
        }
        List list4 = (List) pair.component1();
        List<FirTypeParameterRef> list5 = (List) pair.component2();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        for (FirTypeParameterRef firTypeParameterRef : list5) {
            int i2 = i + 1;
            ConeTypeProjection coneTypeProjection = (ConeTypeProjection) CollectionsKt.getOrNull(list4, i);
            if (coneTypeProjection != null && (type = ConeTypeProjectionKt.getType(coneTypeProjection)) != null && (coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf(type)) != null) {
                Iterator<FirResolvedTypeRef> it2 = firTypeParameterRef.getSymbol().getResolvedBounds().iterator();
                while (it2.hasNext()) {
                    ConeKotlinType coneKotlinTypeSubstituteOrSelf2 = coneSubstitutor.substituteOrSelf(it2.next().getConeType());
                    ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeSubstituteOrSelf2));
                    if (!Intrinsics.areEqual(classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null, StandardClassIds.INSTANCE.getAny()) || !ConeTypeUtilsKt.isMarkedNullable(ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinTypeSubstituteOrSelf2))) {
                        arrayList2.add(TuplesKt.to(coneKotlinTypeSubstituteOrSelf, coneKotlinTypeSubstituteOrSelf2));
                    }
                }
            }
            i = i2;
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables)
    private final List<Pair<ConeKotlinType, ConeKotlinType>> addConstraintsTheOldWay(ResolutionContext resolutionContext, ConeSubstitutor coneSubstitutor, List<ConeTypeParameterBasedTypeVariable> list, List<? extends FirTypeParameterRef> list2) {
        ArrayList arrayList = new ArrayList();
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            FirTypeParameterRef firTypeParameterRef = list2.get(i);
            ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable = list.get(i);
            Iterator<FirResolvedTypeRef> it = getTypeParameterFromExpandedClass(resolutionContext, (FirTypeParameter) firTypeParameterRef.getSymbol().getFir(), i).getSymbol().getResolvedBounds().iterator();
            while (it.hasNext()) {
                addConstraintsTheOldWay$addSubtypeConstraint(coneTypeParameterBasedTypeVariable, arrayList, coneSubstitutor, it.next().getConeType());
            }
        }
        return arrayList;
    }

    private static final void addConstraintsTheOldWay$addSubtypeConstraint(ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable, List<Pair<ConeKotlinType, ConeKotlinType>> list, ConeSubstitutor coneSubstitutor, ConeKotlinType coneKotlinType) {
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType));
        if (Intrinsics.areEqual(classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null, StandardClassIds.INSTANCE.getAny()) && ConeTypeUtilsKt.isMarkedNullable(ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinType))) {
            return;
        }
        list.add(TuplesKt.to(coneTypeParameterBasedTypeVariable.getDefaultType(), coneSubstitutor.substituteOrSelf(coneKotlinType)));
    }

    private final Pair<ConeSubstitutor, List<ConeTypeVariable>> createToFreshVariableSubstitutorAndAddInitialConstraints(ResolutionContext resolutionContext, FirTypeParameterRefsOwner firTypeParameterRefsOwner, ConstraintSystemOperation constraintSystemOperation) {
        TypeAliasConstructorInfo typeAliasConstructorInfo;
        List<FirTypeParameterRef> typeParameters = firTypeParameterRefsOwner.getTypeParameters();
        List<FirTypeParameterRef> list = typeParameters;
        ArrayList<ConeTypeParameterBasedTypeVariable> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConeTypeParameterBasedTypeVariable(((FirTypeParameterRef) it.next()).getSymbol()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable : arrayList) {
            Pair pair = TuplesKt.to(coneTypeParameterBasedTypeVariable.getTypeParameterSymbol(), coneTypeParameterBasedTypeVariable.getDefaultType());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        ConeSubstitutor substitutor = null;
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, resolutionContext.getSession(), false, 4, null);
        FirConstructor firConstructor = firTypeParameterRefsOwner instanceof FirConstructor ? (FirConstructor) firTypeParameterRefsOwner : null;
        if (firConstructor != null && (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructor)) != null) {
            substitutor = typeAliasConstructorInfo.getSubstitutor();
        }
        if (substitutor != null) {
            coneSubstitutorSubstitutorByMap$default = ChainedSubstitutor.INSTANCE.invoke(substitutor, coneSubstitutorSubstitutorByMap$default);
        }
        Iterator<ConeTypeParameterBasedTypeVariable> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            constraintSystemOperation.registerVariable(it2.next());
        }
        for (Pair<ConeKotlinType, ConeKotlinType> pair2 : LanguageVersionUtilsKt.isEnabled(resolutionContext, LanguageFeature.ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables) ? addConstraintsProperly(resolutionContext, firTypeParameterRefsOwner, coneSubstitutorSubstitutorByMap$default, arrayList) : addConstraintsTheOldWay(resolutionContext, coneSubstitutorSubstitutorByMap$default, arrayList, typeParameters)) {
            constraintSystemOperation.addSubtypeConstraint((ConeKotlinType) pair2.component1(), (ConeKotlinType) pair2.component2(), new ConeDeclaredUpperBoundConstraintPosition());
        }
        return TuplesKt.to(coneSubstitutorSubstitutorByMap$default, arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirTypeParameter getTypeParameterFromExpandedClass(ResolutionContext resolutionContext, FirTypeParameter firTypeParameter, int i) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirTypeParameterRef firTypeParameterRef;
        FirTypeParameterSymbol symbol;
        FirTypeParameter firTypeParameter2;
        FirTypeParameterSymbol symbol2;
        FirTypeParameter firTypeParameter3;
        FirDeclaration fir = firTypeParameter.getContainingDeclarationSymbol().getFir();
        if (fir instanceof FirRegularClass) {
            FirTypeParameterRef firTypeParameterRef2 = (FirTypeParameterRef) CollectionsKt.getOrNull(((FirRegularClass) fir).getTypeParameters(), i);
            if (firTypeParameterRef2 != null && (symbol2 = firTypeParameterRef2.getSymbol()) != null && (firTypeParameter3 = (FirTypeParameter) symbol2.getFir()) != null) {
                return firTypeParameter3;
            }
        } else if (fir instanceof FirTypeAlias) {
            ConeTypeParameterType coneType = FirNestedClassifierScopeKt.toConeType(firTypeParameter);
            ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(((FirTypeAlias) fir).getExpandedTypeRef());
            ConeTypeProjection[] typeArguments = coneType2.getTypeArguments();
            int length = typeArguments.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                }
                if (Intrinsics.areEqual(ConeTypeProjectionKt.getType(typeArguments[i2]), coneType)) {
                    break;
                }
                i2++;
            }
            FirClassifierSymbol<?> symbol3 = ToSymbolUtilsKt.toSymbol(resolutionContext, coneType2);
            Object fir2 = symbol3 != null ? symbol3.getFir() : null;
            if ((fir2 instanceof FirTypeParameterRefsOwner) && (firTypeParameterRef = (FirTypeParameterRef) CollectionsKt.getOrNull(((FirTypeParameterRefsOwner) fir2).getTypeParameters(), i2)) != null && (symbol = firTypeParameterRef.getSymbol()) != null && (firTypeParameter2 = (FirTypeParameter) symbol.getFir()) != null) {
                return fir2 instanceof FirTypeAlias ? getTypeParameterFromExpandedClass(resolutionContext, firTypeParameter2, i2) : firTypeParameter2;
            }
        }
        return firTypeParameter;
    }

    private final ConeKotlinType getTypePreservingFlexibilityWrtTypeVariable(ResolutionContext resolutionContext, ConeKotlinType coneKotlinType, FirTypeParameterRef firTypeParameterRef) {
        ConeKotlinType coneKotlinType2;
        ConeFlexibleType coneFlexibleType;
        FirSession session = resolutionContext.getSession();
        if (!shouldExplicitArgumentBeFlexibleForGivenParameter(resolutionContext, coneKotlinType, firTypeParameterRef)) {
            return coneKotlinType;
        }
        if (coneKotlinType instanceof ConeRigidType) {
            coneKotlinType2 = coneKotlinType;
            coneFlexibleType = TypeUtilsKt.toTrivialFlexibleType((ConeRigidType) TypeUtilsKt.withNullability$default(coneKotlinType2, false, TypeComponentsKt.getTypeContext(session), null, false, 12, null), TypeComponentsKt.getTypeContext(session));
        } else {
            coneKotlinType2 = coneKotlinType;
            if (!(coneKotlinType2 instanceof ConeFlexibleType)) {
                bu8.a();
                return null;
            }
            ConeFlexibleType coneFlexibleType2 = (ConeFlexibleType) coneKotlinType2;
            coneFlexibleType = new ConeFlexibleType((ConeRigidType) TypeUtilsKt.withNullability$default(coneFlexibleType2.getLowerBound(), false, TypeComponentsKt.getTypeContext(session), null, false, 12, null), (ConeRigidType) TypeUtilsKt.withNullability$default(coneFlexibleType2.getUpperBound(), true, TypeComponentsKt.getTypeContext(session), null, false, 12, null), false);
        }
        LanguageFeature languageFeature = LanguageFeature.DontMakeExplicitNullableJavaTypeArgumentsFlexible;
        return (!LanguageVersionUtilsKt.isEnabled(resolutionContext, languageFeature) && ConeTypeUtilsKt.isMarkedNullable(coneKotlinType2)) ? (ConeFlexibleType) TypeUtilsKt.withAttributes(coneFlexibleType, coneFlexibleType.getAttributes().add(new ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute(coneKotlinType2, languageFeature))) : coneFlexibleType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean mayExplicitArgumentBeFlexibleForGivenParameter(ResolutionContext resolutionContext, FirTypeParameterRef firTypeParameterRef) {
        FirTypeParameterSymbol symbol;
        FirTypeParameter firTypeParameter;
        List<FirResolvedTypeRef> resolvedBounds = firTypeParameterRef.getSymbol().getResolvedBounds();
        if ((resolvedBounds instanceof Collection) && resolvedBounds.isEmpty()) {
            return false;
        }
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            ConeKotlinType coneType = ((FirResolvedTypeRef) it.next()).getConeType();
            if (coneType instanceof ConeFlexibleType) {
                return true;
            }
            TypeConstructorMarker typeConstructorMarkerTypeConstructor = resolutionContext.getTypeContext().typeConstructor(coneType);
            ConeTypeParameterLookupTag coneTypeParameterLookupTag = typeConstructorMarkerTypeConstructor instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) typeConstructorMarkerTypeConstructor : null;
            if (coneTypeParameterLookupTag != null && (symbol = coneTypeParameterLookupTag.getSymbol()) != null && (firTypeParameter = (FirTypeParameter) symbol.getFir()) != null && INSTANCE.mayExplicitArgumentBeFlexibleForGivenParameter(resolutionContext, firTypeParameter)) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldExplicitArgumentBeFlexibleForGivenParameter(ResolutionContext resolutionContext, ConeKotlinType coneKotlinType, FirTypeParameterRef firTypeParameterRef) {
        if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(resolutionContext.getSession()).supportsFeature(LanguageFeature.DontMakeExplicitNullableJavaTypeArgumentsFlexible) && resolutionContext.getTypeContext().isNullableType(coneKotlinType)) {
            return false;
        }
        return mayExplicitArgumentBeFlexibleForGivenParameter(resolutionContext, firTypeParameterRef);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) throws KotlinIllegalArgumentExceptionWithAttachments {
        KotlinTypeMarker coneType;
        FirAnnotationContainer fir = candidate.getSymbol().getFir();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(candidate.getSymbol(), FirResolvePhase.STATUS);
        if (fir instanceof FirTypeParameterRefsOwner) {
            FirTypeParameterRefsOwner firTypeParameterRefsOwner = (FirTypeParameterRefsOwner) fir;
            if (!firTypeParameterRefsOwner.getTypeParameters().isEmpty()) {
                NewConstraintSystemImpl builder = candidate.getSystem().getBuilder();
                Pair<ConeSubstitutor, List<ConeTypeVariable>> pairCreateToFreshVariableSubstitutorAndAddInitialConstraints = createToFreshVariableSubstitutorAndAddInitialConstraints(resolutionContext, firTypeParameterRefsOwner, builder);
                ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pairCreateToFreshVariableSubstitutorAndAddInitialConstraints.component1();
                List<? extends ConeTypeVariable> list = (List) pairCreateToFreshVariableSubstitutorAndAddInitialConstraints.component2();
                candidate.initializeSubstitutorAndVariables(coneSubstitutor, list);
                if (builder.getHasContradiction()) {
                    checkerSink.reportDiagnostic(InapplicableCandidate.INSTANCE);
                    if (checkerSink.getNeedYielding()) {
                        Object objYield = checkerSink.yield(continuation);
                        if (objYield == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return objYield;
                        }
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                if (Intrinsics.areEqual(candidate.getTypeArgumentMapping(), TypeArgumentMapping.NoExplicitArguments.INSTANCE)) {
                    return Unit.INSTANCE;
                }
                List<FirTypeParameterRef> typeParameters = firTypeParameterRefsOwner.getTypeParameters();
                int size = typeParameters.size();
                for (int i = 0; i < size; i++) {
                    FirTypeParameterRef firTypeParameterRef = typeParameters.get(i);
                    ConeTypeVariable coneTypeVariable = list.get(i);
                    FirTypeProjection firTypeProjection = candidate.getTypeArgumentMapping().get(i);
                    if (firTypeProjection instanceof FirTypeProjectionWithVariance) {
                        builder.addEqualityConstraint(coneTypeVariable.getDefaultType(), TypeExpansionUtilsKt.fullyExpandedType(resolutionContext, getTypePreservingFlexibilityWrtTypeVariable(resolutionContext, FirTypeUtilsKt.getConeType(((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef()), firTypeParameterRef)), new ConeExplicitTypeParameterConstraintPosition(firTypeProjection));
                    } else if (firTypeProjection instanceof FirStarProjection) {
                        ConeTypeVariableType defaultType = coneTypeVariable.getDefaultType();
                        FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) CollectionsKt.firstOrNull(firTypeParameterRef.getSymbol().getResolvedBounds());
                        if (firResolvedTypeRef == null || (coneType = firResolvedTypeRef.getConeType()) == null) {
                            coneType = resolutionContext.getSession().getBuiltinTypes().getNullableAnyType().getConeType();
                        }
                        builder.addEqualityConstraint(defaultType, coneType, SimpleConstraintSystemConstraintPosition.INSTANCE);
                    }
                }
                if (!builder.getHasContradiction()) {
                    return Unit.INSTANCE;
                }
                Iterator it = builder.getErrors().iterator();
                while (it.hasNext()) {
                    checkerSink.reportDiagnostic(new InferenceError((ConstraintSystemError) it.next()));
                }
                return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
            }
        }
        candidate.initializeSubstitutorAndVariables(ConeSubstitutor.Empty.INSTANCE, CollectionsKt.emptyList());
        return Unit.INSTANCE;
    }
}
