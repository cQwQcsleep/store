package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.DslScopeViolation;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JQ\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0013J/\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0019J3\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001eJ+\u0010\u001f\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00180 2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010!J1\u0010\u001f\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00180 2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010%¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckDslScopeViolation;", Argument.Delimiters.none, "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "receiverValueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "boundSymbolOfReceiverToCheck", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "closerOrOnTheSameLevelImplicitValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/util/List;)V", "containsAnyOfGivenDslMarkers", Argument.Delimiters.none, "otherDslMarkers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;Ljava/util/Set;)Z", "getDslMarkersOfImplicitValue", "boundSymbol", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Ljava/util/Set;", "collectDslMarkerAnnotations", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Ljava/util/Set;Ljava/util/Collection;)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CheckDslScopeViolation {
    public static final CheckDslScopeViolation INSTANCE = new CheckDslScopeViolation();

    private CheckDslScopeViolation() {
    }

    private final void collectDslMarkerAnnotations(ResolutionContext resolutionContext, Set<ClassId> set, ConeKotlinType coneKotlinType) {
        ConeClassLikeType coneClassLikeType;
        FirClassLikeSymbol<?> symbol;
        List<ConeKotlinType> supertypes;
        ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneKotlinType);
        collectDslMarkerAnnotations(resolutionContext, set, CustomAnnotationTypeAttributeKt.getCustomAnnotations(abbreviatedTypeOrSelf));
        if (abbreviatedTypeOrSelf instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) abbreviatedTypeOrSelf;
            collectDslMarkerAnnotations(resolutionContext, set, coneFlexibleType.getLowerBound());
            collectDslMarkerAnnotations(resolutionContext, set, coneFlexibleType.getUpperBound());
            return;
        }
        if (abbreviatedTypeOrSelf instanceof ConeCapturedType) {
            ConeCapturedType coneCapturedType = (ConeCapturedType) abbreviatedTypeOrSelf;
            if (coneCapturedType.getConstructor().getProjection().getKind() != ProjectionKind.OUT || (supertypes = coneCapturedType.getConstructor().getSupertypes()) == null) {
                return;
            }
            Iterator<T> it = supertypes.iterator();
            while (it.hasNext()) {
                INSTANCE.collectDslMarkerAnnotations(resolutionContext, set, (ConeKotlinType) it.next());
            }
            return;
        }
        if (abbreviatedTypeOrSelf instanceof ConeDefinitelyNotNullType) {
            collectDslMarkerAnnotations(resolutionContext, set, ((ConeDefinitelyNotNullType) abbreviatedTypeOrSelf).getOriginal());
            return;
        }
        if (abbreviatedTypeOrSelf instanceof ConeIntersectionType) {
            Iterator<T> it2 = ((ConeIntersectionType) abbreviatedTypeOrSelf).getIntersectedTypes().iterator();
            while (it2.hasNext()) {
                INSTANCE.collectDslMarkerAnnotations(resolutionContext, set, (ConeKotlinType) it2.next());
            }
            return;
        }
        if (!(abbreviatedTypeOrSelf instanceof ConeClassLikeType) || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) resolutionContext, (coneClassLikeType = (ConeClassLikeType) abbreviatedTypeOrSelf))) == null) {
            return;
        }
        collectDslMarkerAnnotations(resolutionContext, set, symbol.getResolvedAnnotationsWithClassIds());
        if (symbol instanceof FirClassSymbol) {
            Iterator<ConeKotlinType> it3 = ((FirClassSymbol) symbol).getResolvedSuperTypes().iterator();
            while (it3.hasNext()) {
                collectDslMarkerAnnotations(resolutionContext, set, it3.next());
            }
        } else {
            if (!(symbol instanceof FirTypeAliasSymbol)) {
                bu8.a();
                return;
            }
            ConeClassLikeType coneClassLikeTypeDirectExpansionType$default = TypeExpansionUtilsKt.directExpansionType$default(coneClassLikeType, resolutionContext.getSession(), null, 2, null);
            if (coneClassLikeTypeDirectExpansionType$default != null) {
                INSTANCE.collectDslMarkerAnnotations(resolutionContext, set, coneClassLikeTypeDirectExpansionType$default);
            }
        }
    }

    private final boolean containsAnyOfGivenDslMarkers(ResolutionContext resolutionContext, ImplicitValue<?> implicitValue, Set<ClassId> set) {
        Set<ClassId> dslMarkersOfImplicitValue = getDslMarkersOfImplicitValue(resolutionContext, implicitValue.getBoundSymbol(), implicitValue.getType());
        if ((dslMarkersOfImplicitValue instanceof Collection) && dslMarkersOfImplicitValue.isEmpty()) {
            return false;
        }
        Iterator<T> it = dslMarkersOfImplicitValue.iterator();
        while (it.hasNext()) {
            if (set.contains((ClassId) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Set<ClassId> getDslMarkersOfImplicitValue(ResolutionContext resolutionContext, FirBasedSymbol<?> firBasedSymbol, ConeKotlinType coneKotlinType) {
        ConeKotlinType matchingParameterFunctionType;
        ConeKotlinType coneKotlinTypeReceiverType;
        Set<ClassId> setCreateSetBuilder = SetsKt.createSetBuilder();
        FirBasedSymbol firBasedSymbolContainingDeclarationIfParameter = ResolutionStagesKt.containingDeclarationIfParameter(firBasedSymbol);
        FirAnonymousFunctionSymbol firAnonymousFunctionSymbol = firBasedSymbolContainingDeclarationIfParameter instanceof FirAnonymousFunctionSymbol ? (FirAnonymousFunctionSymbol) firBasedSymbolContainingDeclarationIfParameter : null;
        if (firAnonymousFunctionSymbol != null && (matchingParameterFunctionType = ClassMembersKt.getMatchingParameterFunctionType((FirAnonymousFunction) firAnonymousFunctionSymbol.getFir())) != null) {
            CheckDslScopeViolation checkDslScopeViolation = INSTANCE;
            checkDslScopeViolation.collectDslMarkerAnnotations(resolutionContext, setCreateSetBuilder, CustomAnnotationTypeAttributeKt.getCustomAnnotations(matchingParameterFunctionType));
            if (firBasedSymbol instanceof FirValueParameterSymbol) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) CollectionsKt.getOrNull(FunctionalTypeUtilsKt.contextParameterTypes(matchingParameterFunctionType, resolutionContext.getSession()), firAnonymousFunctionSymbol.getContextParameterSymbols().indexOf(firBasedSymbol));
                if (coneKotlinType2 != null) {
                    checkDslScopeViolation.collectDslMarkerAnnotations(resolutionContext, setCreateSetBuilder, coneKotlinType2);
                }
            }
            if ((firBasedSymbol instanceof FirReceiverParameterSymbol) && (coneKotlinTypeReceiverType = FunctionalTypeUtilsKt.receiverType(matchingParameterFunctionType, resolutionContext.getSession())) != null) {
                checkDslScopeViolation.collectDslMarkerAnnotations(resolutionContext, setCreateSetBuilder, coneKotlinTypeReceiverType);
            }
        }
        INSTANCE.collectDslMarkerAnnotations(resolutionContext, setCreateSetBuilder, coneKotlinType);
        return SetsKt.build(setCreateSetBuilder);
    }

    public final void check(CheckerSink checkerSink, ResolutionContext resolutionContext, FirExpression firExpression, Candidate candidate, FirBasedSymbol<?> firBasedSymbol, List<? extends ImplicitValue<?>> list) {
        checkerSink.getClass();
        resolutionContext.getClass();
        firExpression.getClass();
        candidate.getClass();
        firBasedSymbol.getClass();
        list.getClass();
        Set<ClassId> dslMarkersOfImplicitValue = getDslMarkersOfImplicitValue(resolutionContext, firBasedSymbol, FirTypeUtilsKt.getResolvedType(firExpression));
        if (dslMarkersOfImplicitValue.isEmpty()) {
            return;
        }
        Set<ClassId> set = dslMarkersOfImplicitValue;
        List<? extends ImplicitValue<?>> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return;
        }
        for (ImplicitValue<?> implicitValue : list2) {
            if (!implicitValue.isSameImplicitReceiverInstance(firExpression) && INSTANCE.containsAnyOfGivenDslMarkers(resolutionContext, implicitValue, set)) {
                checkerSink.reportDiagnostic(new DslScopeViolation(candidate.getSymbol()));
                return;
            }
        }
    }

    private final void collectDslMarkerAnnotations(ResolutionContext resolutionContext, Set<ClassId> set, Collection<? extends FirAnnotation> collection) {
        Iterator<? extends FirAnnotation> it = collection.iterator();
        while (it.hasNext()) {
            FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(resolutionContext, TypeExpansionUtilsKt.fullyExpandedType(resolutionContext, FirTypeUtilsKt.getConeType(it.next().getAnnotationTypeRef())));
            if (classSymbol != null && FirAnnotationUtilsKt.hasAnnotation(classSymbol, StandardClassIds$Annotations.INSTANCE.getDslMarker(), resolutionContext.getSession())) {
                set.add(classSymbol.getClassId());
            }
        }
    }
}
