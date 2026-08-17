package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.EnumValueArgumentInfo;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.UseSiteTargetsList;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\u001a\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0016\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\f\u001a\u0004\u0018\u00010\u0005*\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0018\u0010\f\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f*\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007\u001a&\u0010\u0011\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00120\u000f*\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\u0014\u001a\u0004\u0018\u00010\t*\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007\u001aO\u0010\u0015\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001e2\u0006\u0010\u001f\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0013R\u00020\u0017R\u00020\u0019j\u0006\u0010\u0018\u001a\u00020\u0017j\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0002\u0010!\u001a\u0012\u0010\"\u001a\u00020#*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a%\u0010$\u001a\u0004\u0018\u00010\u001c*\u00020%2\u0006\u0010\u001f\u001a\u00020\u0005R\u00020\u0017j\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0002\u0010&\u001a!\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000f*\u00020%R\u00020\u0017j\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0002\u0010(\u001a[\u0010\u0015\u001a\u00020\u00162\b\u0010)\u001a\u0004\u0018\u00010%2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130,2\b\u0010-\u001a\u0004\u0018\u00010\u0013R\u00020\u0017R\u00020\u0019j\u0006\u0010\u0018\u001a\u00020\u0017j\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0002\u0010.\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"defaultAnnotationTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "defaultAnnotationTargetsWithExpression", "getAllowedAnnotationTargets", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getAnnotationClassForOptInMarker", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getTargetAnnotation", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "extractClassesFromArgument", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "extractClassesAndSourcesFromArgument", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/KtSourceElement;", "extractClassFromArgument", "checkRepeatedAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "existingTargetsForAnnotation", Argument.Delimiters.none, "annotation", "annotationSource", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/KtSourceElement;)V", "isRepeatable", Argument.Delimiters.none, "getDefaultUseSiteTarget", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getImplicitUseSiteTargetList", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;)Ljava/util/List;", "annotationContainer", "annotations", "annotationSources", Argument.Delimiters.none, "defaultSource", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Ljava/util/List;Ljava/util/Map;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationHelpersKt {
    private static final Set<KotlinTarget> defaultAnnotationTargets;
    private static final Set<KotlinTarget> defaultAnnotationTargetsWithExpression;

    static {
        KotlinTarget.Companion companion = KotlinTarget.INSTANCE;
        defaultAnnotationTargets = companion.getDEFAULT_TARGET_SET();
        defaultAnnotationTargetsWithExpression = SetsKt.plus(companion.getDEFAULT_TARGET_SET(), KotlinTarget.EXPRESSION);
    }

    public static final void checkRepeatedAnnotation(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer, List<? extends FirAnnotation> list, Map<FirAnnotation, ? extends KtSourceElement> map, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        list.getClass();
        map.getClass();
        if (list.size() <= 1) {
            return;
        }
        HashMap map2 = new HashMap();
        for (FirAnnotation firAnnotation : list) {
            AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
            if (useSiteTarget == AnnotationUseSiteTarget.ALL) {
                useSiteTarget = null;
            }
            AnnotationUseSiteTarget defaultUseSiteTarget = useSiteTarget == null ? firAnnotationContainer != null ? getDefaultUseSiteTarget(checkerContext, firAnnotationContainer, firAnnotation) : null : useSiteTarget;
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef()));
            Object arrayList = map2.get(coneKotlinTypeFullyExpandedType);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map2.put(coneKotlinTypeFullyExpandedType, arrayList);
            }
            List list2 = (List) arrayList;
            KtSourceElement ktSourceElement2 = map.get(firAnnotation);
            CheckerContext checkerContext2 = checkerContext;
            DiagnosticReporter diagnosticReporter2 = diagnosticReporter;
            checkRepeatedAnnotation(checkerContext2, diagnosticReporter2, defaultUseSiteTarget, (List<AnnotationUseSiteTarget>) list2, firAnnotation, ktSourceElement2 == null ? ktSourceElement : ktSourceElement2);
            list2.add(defaultUseSiteTarget);
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }

    public static final FirRegularClassSymbol extractClassFromArgument(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        if (!(firExpression instanceof FirGetClassCall)) {
            return null;
        }
        FirExpression argument = ((FirGetClassCall) firExpression).getArgument();
        if (!(argument instanceof FirResolvedQualifier)) {
            if (argument instanceof FirClassReferenceExpression) {
                return ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(FirTypeUtilsKt.getConeType(((FirClassReferenceExpression) argument).getClassTypeRef())), firSession, (Function1) null, 2, (Object) null), firSession);
            }
            return null;
        }
        FirClassLikeSymbol<?> symbol = ((FirResolvedQualifier) argument).getSymbol();
        if (symbol != null) {
            return DeclarationUtilsKt.fullyExpandedClass(symbol, firSession);
        }
        return null;
    }

    public static final List<Pair<FirRegularClassSymbol, KtSourceElement>> extractClassesAndSourcesFromArgument(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        List<FirExpression> listUnwrapAndFlattenArgument = FirExpressionUtilKt.unwrapAndFlattenArgument(firExpression, true);
        ArrayList arrayList = new ArrayList();
        for (FirExpression firExpression2 : listUnwrapAndFlattenArgument) {
            FirRegularClassSymbol firRegularClassSymbolExtractClassFromArgument = extractClassFromArgument(firExpression2, firSession);
            Pair pair = firRegularClassSymbolExtractClassFromArgument != null ? TuplesKt.to(firRegularClassSymbolExtractClassFromArgument, firExpression2.getSource()) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return arrayList;
    }

    public static final List<FirRegularClassSymbol> extractClassesFromArgument(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        List<FirExpression> listUnwrapAndFlattenArgument = FirExpressionUtilKt.unwrapAndFlattenArgument(firExpression, true);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listUnwrapAndFlattenArgument.iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol firRegularClassSymbolExtractClassFromArgument = extractClassFromArgument((FirExpression) it.next(), firSession);
            if (firRegularClassSymbolExtractClassFromArgument != null) {
                arrayList.add(firRegularClassSymbolExtractClassFromArgument);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Set<KotlinTarget> getAllowedAnnotationTargets(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableDeclaration firCallableDeclaration;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        KotlinTarget kotlinTarget;
        Name enumEntryName;
        String strAsString;
        Object next;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData2;
        FirCallableDeclaration original2;
        firClassLikeSymbol.getClass();
        firSession.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firClassLikeSymbol, FirResolvePhase.ANNOTATION_ARGUMENTS);
        FirAnnotation targetAnnotation = getTargetAnnotation(firClassLikeSymbol, firSession);
        if (targetAnnotation == null) {
            FirDeclarationOrigin origin = firClassLikeSymbol.getOrigin();
            if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                E fir = firClassLikeSymbol.getFir();
                firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                if (firCallableDeclaration == null || (importedFromObjectOrStaticData2 = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original2 = importedFromObjectOrStaticData2.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original2)) {
                    return defaultAnnotationTargets;
                }
            }
            return defaultAnnotationTargetsWithExpression;
        }
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(targetAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getTargetAllowedTargets(), false, 2, null);
        List<FirExpression> listUnwrapAndFlattenArgument = firExpressionFindArgumentByName$default != null ? FirExpressionUtilKt.unwrapAndFlattenArgument(firExpressionFindArgumentByName$default, true) : null;
        if (listUnwrapAndFlattenArgument == null) {
            listUnwrapAndFlattenArgument = CollectionsKt.emptyList();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = listUnwrapAndFlattenArgument.iterator();
        while (it.hasNext()) {
            EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo = FirAnnotationUtilsKt.extractEnumValueArgumentInfo((FirExpression) it.next());
            if (enumValueArgumentInfoExtractEnumValueArgumentInfo == null || (enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName()) == null || (strAsString = enumEntryName.asString()) == null) {
                kotlinTarget = null;
            } else {
                Iterator it2 = KotlinTarget.getEntries().iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (!Intrinsics.areEqual(((KotlinTarget) next).name(), strAsString));
                kotlinTarget = (KotlinTarget) next;
            }
            if (kotlinTarget != null) {
                linkedHashSet.add(kotlinTarget);
            }
        }
        FirDeclarationOrigin origin2 = firClassLikeSymbol.getOrigin();
        if (!(origin2 instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin2, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            E fir2 = firClassLikeSymbol.getFir();
            firCallableDeclaration = fir2 instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir2 : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return linkedHashSet;
            }
        }
        return SetsKt.plus(linkedHashSet, KotlinTarget.EXPRESSION);
    }

    public static final FirRegularClassSymbol getAnnotationClassForOptInMarker(FirAnnotation firAnnotation, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol;
        firAnnotation.getClass();
        firSession.getClass();
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef()));
        if (classLikeLookupTagIfAny == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(classLikeLookupTagIfAny, firSession)) == null || !FirAnnotationUtilsKt.hasAnnotationWithClassId(regularClassSymbol, OptInNames.INSTANCE.getREQUIRES_OPT_IN_CLASS_ID(), firSession)) {
            return null;
        }
        return regularClassSymbol;
    }

    public static final AnnotationUseSiteTarget getDefaultUseSiteTarget(CheckerContext checkerContext, FirAnnotationContainer firAnnotationContainer, FirAnnotation firAnnotation) {
        Object next;
        checkerContext.getClass();
        firAnnotationContainer.getClass();
        firAnnotation.getClass();
        Iterator<T> it = getImplicitUseSiteTargetList(checkerContext, firAnnotationContainer).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (CollectionsKt.contains(getAllowedAnnotationTargets(firAnnotation, checkerContext.getSession()), KotlinTarget.INSTANCE.getUSE_SITE_MAPPING().get((AnnotationUseSiteTarget) next))) {
                return (AnnotationUseSiteTarget) next;
            }
        }
        next = null;
        return (AnnotationUseSiteTarget) next;
    }

    public static final List<AnnotationUseSiteTarget> getImplicitUseSiteTargetList(CheckerContext checkerContext, FirAnnotationContainer firAnnotationContainer) {
        FirBasedSymbol firBasedSymbol;
        checkerContext.getClass();
        firAnnotationContainer.getClass();
        if (!(firAnnotationContainer instanceof FirValueParameter)) {
            if (firAnnotationContainer instanceof FirProperty) {
                return ((FirProperty) firAnnotationContainer).getSymbol() instanceof FirLocalPropertySymbol ? CollectionsKt.emptyList() : UseSiteTargetsList.INSTANCE.getT_PROPERTY();
            }
            if (firAnnotationContainer instanceof FirPropertyAccessor) {
                return ((FirPropertyAccessor) firAnnotationContainer).getIsGetter() ? CollectionsKt.listOf(AnnotationUseSiteTarget.PROPERTY_GETTER) : CollectionsKt.listOf(AnnotationUseSiteTarget.PROPERTY_SETTER);
            }
            return CollectionsKt.emptyList();
        }
        Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
        do {
            firBasedSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) it.next();
            if (firBasedSymbol2 != null) {
                firBasedSymbol = firBasedSymbol2;
            }
        } while (firBasedSymbol == null);
        return ((firBasedSymbol instanceof FirConstructorSymbol) && ((FirConstructorSymbol) firBasedSymbol).isPrimary()) ? UseSiteTargetsList.INSTANCE.getT_CONSTRUCTOR_PARAMETER() : CollectionsKt.emptyList();
    }

    public static final FirAnnotation getTargetAnnotation(FirDeclaration firDeclaration, FirSession firSession) {
        firDeclaration.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, StandardClassIds$Annotations.INSTANCE.getTarget(), firSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isRepeatable(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> annotationClassLikeSymbol = FirAnnotationUtilsKt.toAnnotationClassLikeSymbol(firAnnotation, firSession);
        if (annotationClassLikeSymbol == null || ((FirClassLikeDeclaration) annotationClassLikeSymbol.getFir()).getIsLocal()) {
            return false;
        }
        return FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(firSession).symbolContainsRepeatableAnnotation(annotationClassLikeSymbol, firSession);
    }

    public static final FirAnnotation getTargetAnnotation(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firClassLikeSymbol, StandardClassIds$Annotations.INSTANCE.getTarget(), firSession);
    }

    public static final void checkRepeatedAnnotation(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, AnnotationUseSiteTarget annotationUseSiteTarget, List<AnnotationUseSiteTarget> list, FirAnnotation firAnnotation, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        list.getClass();
        firAnnotation.getClass();
        if (!list.contains(annotationUseSiteTarget)) {
            List<AnnotationUseSiteTarget> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                return;
            }
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                boolean z = false;
                boolean z2 = ((AnnotationUseSiteTarget) it.next()) == null;
                if (annotationUseSiteTarget == null) {
                    z = true;
                }
                if (z2 != z) {
                }
            }
            return;
        }
        if (isRepeatable(firAnnotation, checkerContext.getSession())) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getREPEATED_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    public static final Set<KotlinTarget> getAllowedAnnotationTargets(FirRegularClass firRegularClass, FirSession firSession) {
        firRegularClass.getClass();
        firSession.getClass();
        return getAllowedAnnotationTargets(firRegularClass.getSymbol(), firSession);
    }

    public static final Set<KotlinTarget> getAllowedAnnotationTargets(FirAnnotation firAnnotation, FirSession firSession) {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default;
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        firAnnotation.getClass();
        firSession.getClass();
        if (firAnnotation.getAnnotationTypeRef() instanceof FirErrorTypeRef) {
            return KotlinTarget.INSTANCE.getALL_TARGET_SET();
        }
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType != null && (coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null)) != null && (lookupTag = coneClassLikeTypeFullyExpandedType$default.getLookupTag()) != null && (symbol = ToSymbolUtilsKt.toSymbol(lookupTag, firSession)) != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, FirResolvePhase.BODY_RESOLVE);
            return getAllowedAnnotationTargets(symbol, firSession);
        }
        return defaultAnnotationTargets;
    }
}
