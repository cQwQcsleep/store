package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.impl.FirSimpleNamedReference;
import org.jetbrains.kotlin.fir.resolve.QualifiedNameResolutionKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDeprecated;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNestedClassAccessedViaInstanceReference;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirTypeCandidateCollector;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a4\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b\u001a:\u0010\f\u001a\u0004\u0018\u00010\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002\u001a*\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00022\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002\u001a@\u0010\u0018\u001a\u0004\u0018\u00010\u0001*\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0002\u001a8\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u001d\u001a\u00020\u0012H\u0002\u001a\u0080\u0001\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00192\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00192\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002\u001a*\u0010%\u001a\u0004\u0018\u00010\t2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0000\u001aF\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¨\u0006,"}, d2 = {"resolveRootPartOfQualifier", "Lorg/jetbrains/kotlin/fir/resolve/QualifierResolutionResult;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "namedReference", "Lorg/jetbrains/kotlin/fir/references/impl/FirSimpleNamedReference;", "qualifiedAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "nonFatalDiagnosticsFromExpression", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "isUsedAsReceiver", Argument.Delimiters.none, "continueQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "components", "getUnambiguousCandidate", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "continueQualifierInPackage", "Lorg/jetbrains/kotlin/name/FqName;", "buildResolvedQualifierResultForTopLevelClass", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "candidate", "buildResolvedQualifierResult", "packageFqName", "relativeClassFqName", "nonFatalDiagnostics", "extraTypeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "explicitParent", "extractNestedClassAccessDiagnostic", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "extractNonFatalDiagnostics", "extraNotFatalDiagnostics", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class QualifiedNameResolutionKt {
    public static Unit a(FirTypeCandidateCollector firTypeCandidateCollector, FirResolvedSymbolOrigin firResolvedSymbolOrigin, FirScope firScope, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if (firResolvedSymbolOrigin == null) {
            firResolvedSymbolOrigin = ResolveUtilsKt.toResolvedSymbolOrigin(firScope);
        }
        firTypeCandidateCollector.processCandidate(firClassifierSymbol, null, firResolvedSymbolOrigin);
        return Unit.INSTANCE;
    }

    public static Iterable b(FirScope firScope) {
        firScope.getClass();
        return firScope.getScopeOwnerLookupNames();
    }

    private static final QualifierResolutionResult buildResolvedQualifierResult(BodyResolveComponents bodyResolveComponents, FirQualifiedAccessExpression firQualifiedAccessExpression, FqName fqName, FqName fqName2, FirClassLikeSymbol<?> firClassLikeSymbol, List<? extends ConeDiagnostic> list, List<? extends FirTypeProjection> list2, FirTypeCandidateCollector.TypeCandidate typeCandidate, FirResolvedQualifier firResolvedQualifier, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        CandidateApplicability applicability;
        FirResolvedSymbolOrigin resolvedSymbolOrigin;
        KtSourceElement source = firQualifiedAccessExpression.getSource();
        List<FirTypeProjection> typeArguments = firQualifiedAccessExpression.getTypeArguments();
        List<? extends FirTypeProjection> list3 = list2;
        if (!(list3 == null || list3.isEmpty())) {
            typeArguments = CollectionsKt.plus(typeArguments, list2 == null ? CollectionsKt.emptyList() : list2);
        }
        FirResolvedQualifier firResolvedQualifierBuildResolvedQualifierForClass = ResolveUtilsKt.buildResolvedQualifierForClass(bodyResolveComponents, firClassLikeSymbol, source, fqName, fqName2, typeArguments, typeCandidate != null ? typeCandidate.getDiagnostic() : null, list == null ? CollectionsKt.emptyList() : list, firQualifiedAccessExpression.getAnnotations(), firResolvedQualifier, (typeCandidate == null || (resolvedSymbolOrigin = typeCandidate.getResolvedSymbolOrigin()) == null) ? firResolvedSymbolOrigin : resolvedSymbolOrigin);
        if (typeCandidate == null || (applicability = typeCandidate.getApplicability()) == null) {
            applicability = CandidateApplicability.RESOLVED;
        }
        return new QualifierResolutionResult(firResolvedQualifierBuildResolvedQualifierForClass, applicability);
    }

    public static /* synthetic */ QualifierResolutionResult buildResolvedQualifierResult$default(BodyResolveComponents bodyResolveComponents, FirQualifiedAccessExpression firQualifiedAccessExpression, FqName fqName, FqName fqName2, FirClassLikeSymbol firClassLikeSymbol, List list, List list2, FirTypeCandidateCollector.TypeCandidate typeCandidate, FirResolvedQualifier firResolvedQualifier, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            fqName2 = null;
        }
        if ((i & 8) != 0) {
            firClassLikeSymbol = null;
        }
        if ((i & 16) != 0) {
            list = null;
        }
        if ((i & 32) != 0) {
            list2 = null;
        }
        if ((i & 64) != 0) {
            typeCandidate = null;
        }
        if ((i & 128) != 0) {
            firResolvedQualifier = null;
        }
        if ((i & 256) != 0) {
            firResolvedSymbolOrigin = null;
        }
        return buildResolvedQualifierResult(bodyResolveComponents, firQualifiedAccessExpression, fqName, fqName2, firClassLikeSymbol, list, list2, typeCandidate, firResolvedQualifier, firResolvedSymbolOrigin);
    }

    private static final QualifierResolutionResult buildResolvedQualifierResultForTopLevelClass(BodyResolveComponents bodyResolveComponents, FirClassLikeSymbol<?> firClassLikeSymbol, FirQualifiedAccessExpression firQualifiedAccessExpression, List<? extends ConeDiagnostic> list, FirTypeCandidateCollector.TypeCandidate typeCandidate) {
        ClassId classId = firClassLikeSymbol.getClassId();
        return buildResolvedQualifierResult$default(bodyResolveComponents, firQualifiedAccessExpression, classId.getPackageFqName(), classId.getRelativeClassName(), firClassLikeSymbol, extractNonFatalDiagnostics(firQualifiedAccessExpression.getSource(), null, firClassLikeSymbol, list, bodyResolveComponents.getSession()), null, typeCandidate, null, null, 416, null);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final QualifierResolutionResult continueQualifier(FirResolvedQualifier firResolvedQualifier, FirSimpleNamedReference firSimpleNamedReference, FirQualifiedAccessExpression firQualifiedAccessExpression, List<? extends ConeDiagnostic> list, FirSession firSession, BodyResolveComponents bodyResolveComponents) {
        FirResolvedSymbolOrigin resolvedSymbolOrigin;
        firResolvedQualifier.getClass();
        firSimpleNamedReference.getClass();
        firQualifiedAccessExpression.getClass();
        list.getClass();
        firSession.getClass();
        bodyResolveComponents.getClass();
        Name name = firSimpleNamedReference.getName();
        FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
        FirResolvedQualifier firResolvedQualifier2 = explicitReceiver instanceof FirResolvedQualifier ? (FirResolvedQualifier) explicitReceiver : null;
        if (firResolvedQualifier2 == null || (resolvedSymbolOrigin = firResolvedQualifier2.getResolvedSymbolOrigin()) == null) {
            resolvedSymbolOrigin = FirResolvedSymbolOrigin.Qualified;
        } else {
            if (resolvedSymbolOrigin != FirResolvedSymbolOrigin.QualifiedWithDeprecatedRootIdePackage) {
                resolvedSymbolOrigin = null;
            }
            if (resolvedSymbolOrigin == null) {
                resolvedSymbolOrigin = FirResolvedSymbolOrigin.Qualified;
            }
        }
        FirResolvedSymbolOrigin firResolvedSymbolOrigin = resolvedSymbolOrigin;
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol == null) {
            return continueQualifierInPackage(firResolvedQualifier.getPackageFqName(), name, firQualifiedAccessExpression, list, bodyResolveComponents, firResolvedSymbolOrigin);
        }
        FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir();
        if (!(firClassLikeDeclaration instanceof FirClass)) {
            return null;
        }
        FirClass firClass = (FirClass) firClassLikeDeclaration;
        FirContainingNamesAwareScope nestedClassifierScope = firClass.getScopeProvider().getNestedClassifierScope(firClass, bodyResolveComponents.getSession(), bodyResolveComponents.getScopeSession());
        if (nestedClassifierScope == null) {
            return null;
        }
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(firSession);
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordNameLookup(lookupTracker, name, nestedClassifierScope.getScopeOwnerLookupNames(), firQualifiedAccessExpression.getSource(), bodyResolveComponents.getFile().getSource());
        }
        FirTypeCandidateCollector.TypeCandidate unambiguousCandidate = getUnambiguousCandidate(nestedClassifierScope, name, bodyResolveComponents, firResolvedSymbolOrigin);
        if (unambiguousCandidate == null) {
            return null;
        }
        FirBasedSymbol<?> symbol2 = unambiguousCandidate.getSymbol();
        FirClassLikeSymbol firClassLikeSymbol = symbol2 instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) symbol2 : null;
        if (firClassLikeSymbol == null) {
            return null;
        }
        List<ConeDiagnostic> listExtractNonFatalDiagnostics = extractNonFatalDiagnostics(firQualifiedAccessExpression.getSource(), null, firClassLikeSymbol, list, firSession);
        FqName fqNameChild = null;
        FqName packageFqName = firResolvedQualifier.getPackageFqName();
        FqName relativeClassFqName = firResolvedQualifier.getRelativeClassFqName();
        if (relativeClassFqName != null) {
            fqNameChild = relativeClassFqName.child(name);
        }
        return buildResolvedQualifierResult$default(bodyResolveComponents, firQualifiedAccessExpression, packageFqName, fqNameChild, firClassLikeSymbol, listExtractNonFatalDiagnostics, firResolvedQualifier.getTypeArguments(), unambiguousCandidate, firResolvedQualifier, null, 256, null);
    }

    private static final QualifierResolutionResult continueQualifierInPackage(FqName fqName, Name name, FirQualifiedAccessExpression firQualifiedAccessExpression, List<? extends ConeDiagnostic> list, BodyResolveComponents bodyResolveComponents, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        FqName fqNameChild = fqName.child(name);
        if (bodyResolveComponents.getSymbolProvider().hasPackage(fqNameChild)) {
            return buildResolvedQualifierResult$default(bodyResolveComponents, firQualifiedAccessExpression, fqNameChild, null, null, list, null, null, null, firResolvedSymbolOrigin, 236, null);
        }
        ClassId classId = ClassId.Companion.topLevel(fqNameChild);
        FirClassLikeSymbol<?> classLikeSymbolByClassId = bodyResolveComponents.getSymbolProvider().getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId == null) {
            return null;
        }
        FirTypeCandidateCollector firTypeCandidateCollector = new FirTypeCandidateCollector(bodyResolveComponents.getSession(), bodyResolveComponents.getFile(), bodyResolveComponents.getContainingDeclarations(), null, false, 24, null);
        FirTypeCandidateCollector.processCandidate$default(firTypeCandidateCollector, classLikeSymbolByClassId, null, firResolvedSymbolOrigin, 2, null);
        FirTypeCandidateCollector.TypeCandidate typeCandidateResolvedCandidateOrNull = firTypeCandidateCollector.getResult().resolvedCandidateOrNull();
        return buildResolvedQualifierResult$default(bodyResolveComponents, firQualifiedAccessExpression, fqName, classId.getRelativeClassName(), classLikeSymbolByClassId, extractNonFatalDiagnostics(firQualifiedAccessExpression.getSource(), null, classLikeSymbolByClassId, list, bodyResolveComponents.getSession()), null, typeCandidateResolvedCandidateOrNull, null, null, 416, null);
    }

    public static /* synthetic */ QualifierResolutionResult continueQualifierInPackage$default(FqName fqName, Name name, FirQualifiedAccessExpression firQualifiedAccessExpression, List list, BodyResolveComponents bodyResolveComponents, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, Object obj) {
        if ((i & 16) != 0) {
            firResolvedSymbolOrigin = FirResolvedSymbolOrigin.Qualified;
        }
        return continueQualifierInPackage(fqName, name, firQualifiedAccessExpression, list, bodyResolveComponents, firResolvedSymbolOrigin);
    }

    public static final ConeDiagnostic extractNestedClassAccessDiagnostic(KtSourceElement ktSourceElement, FirExpression firExpression, FirClassLikeSymbol<?> firClassLikeSymbol) {
        List<FirTypeProjection> typeArguments;
        firClassLikeSymbol.getClass();
        FirExpression firExpressionUnwrapSmartcastExpression = firExpression != null ? FirExpressionUtilKt.unwrapSmartcastExpression(firExpression) : null;
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        if (firResolvedQualifier == null || (typeArguments = firResolvedQualifier.getTypeArguments()) == null || !(!typeArguments.isEmpty())) {
            return null;
        }
        ktSourceElement.getClass();
        return new ConeNestedClassAccessedViaInstanceReference(ktSourceElement, firClassLikeSymbol);
    }

    public static final List<ConeDiagnostic> extractNonFatalDiagnostics(KtSourceElement ktSourceElement, FirExpression firExpression, FirClassLikeSymbol<?> firClassLikeSymbol, List<? extends ConeDiagnostic> list, FirSession firSession) {
        List<ConeDiagnostic> listEmptyList;
        List<ConeDiagnostic> list2;
        firClassLikeSymbol.getClass();
        firSession.getClass();
        ArrayList arrayList = null;
        FirExpression firExpressionUnwrapSmartcastExpression = firExpression != null ? FirExpressionUtilKt.unwrapSmartcastExpression(firExpression) : null;
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        if (firResolvedQualifier == null || (listEmptyList = firResolvedQualifier.getNonFatalDiagnostics()) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        FirDeprecationInfo deprecationForCallSite = DeprecationUtilsKt.getDeprecationForCallSite(firClassLikeSymbol, firSession, new AnnotationUseSiteTarget[0]);
        if (deprecationForCallSite != null) {
            arrayList = new ArrayList();
            arrayList.addAll(listEmptyList);
            arrayList.add(new ConeDeprecated(ktSourceElement, firClassLikeSymbol, deprecationForCallSite));
        }
        if (list != null) {
            List<? extends ConeDiagnostic> list3 = list;
            if (!list3.isEmpty()) {
                if (arrayList == null) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.addAll(listEmptyList);
                    arrayList = arrayList2;
                }
                arrayList.addAll(list3);
            }
        }
        return (arrayList == null || (list2 = CollectionsKt.toList(arrayList)) == null) ? listEmptyList : list2;
    }

    private static final FirTypeCandidateCollector.TypeCandidate getUnambiguousCandidate(final FirScope firScope, Name name, BodyResolveComponents bodyResolveComponents, final FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        final FirTypeCandidateCollector firTypeCandidateCollector = new FirTypeCandidateCollector(bodyResolveComponents.getSession(), bodyResolveComponents.getFile(), bodyResolveComponents.getContainingDeclarations(), null, false, 24, null);
        final Function1 function1 = new Function1() { // from class: g1c
            public final Object invoke(Object obj) {
                return QualifiedNameResolutionKt.a(firTypeCandidateCollector, firResolvedSymbolOrigin, firScope, (FirClassifierSymbol) obj);
            }
        };
        firScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.QualifiedNameResolutionKt$getUnambiguousCandidate$$inlined$processClassifiersByName$1
            public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                firClassifierSymbol.getClass();
                coneSubstitutor.getClass();
                function1.invoke(firClassifierSymbol);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                return Unit.INSTANCE;
            }
        });
        return firTypeCandidateCollector.getResult().resolvedCandidateOrNull();
    }

    public static /* synthetic */ FirTypeCandidateCollector.TypeCandidate getUnambiguousCandidate$default(FirScope firScope, Name name, BodyResolveComponents bodyResolveComponents, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            firResolvedSymbolOrigin = null;
        }
        return getUnambiguousCandidate(firScope, name, bodyResolveComponents, firResolvedSymbolOrigin);
    }

    public static final QualifierResolutionResult resolveRootPartOfQualifier(BodyResolveComponents bodyResolveComponents, FirSimpleNamedReference firSimpleNamedReference, FirQualifiedAccessExpression firQualifiedAccessExpression, List<? extends ConeDiagnostic> list, boolean z) {
        FirResolvedQualifier qualifier;
        bodyResolveComponents.getClass();
        firSimpleNamedReference.getClass();
        firQualifiedAccessExpression.getClass();
        Name name = firSimpleNamedReference.getName();
        FirQualifierResolver.Companion companion = FirQualifierResolver.INSTANCE;
        if (companion.isRootIdePackageAllowed(bodyResolveComponents) && Intrinsics.areEqual(name.asString(), "_root_ide_package_")) {
            return buildResolvedQualifierResult$default(bodyResolveComponents, firQualifiedAccessExpression, FqName.ROOT, null, null, list, null, null, null, companion.isRootIdePackageDeprecated(bodyResolveComponents) ? FirResolvedSymbolOrigin.QualifiedWithDeprecatedRootIdePackage : FirResolvedSymbolOrigin.Qualified, 236, null);
        }
        List<FirScope> listCreateCurrentScopeList = BodyResolveComponentsKt.createCurrentScopeList(bodyResolveComponents);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(bodyResolveComponents.getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordNameLookup(lookupTracker, name, (Iterable<String>) SequencesKt.asIterable(SequencesKt.flatMapIterable(CollectionsKt.asSequence(listCreateCurrentScopeList), new Function1() { // from class: h1c
                public final Object invoke(Object obj) {
                    return QualifiedNameResolutionKt.b((FirScope) obj);
                }
            })), firQualifiedAccessExpression.getSource(), bodyResolveComponents.getFile().getSource());
        }
        Iterator<FirScope> it = listCreateCurrentScopeList.iterator();
        FirTypeCandidateCollector.TypeCandidate typeCandidate = null;
        while (it.hasNext()) {
            FirTypeCandidateCollector.TypeCandidate unambiguousCandidate$default = getUnambiguousCandidate$default(it.next(), name, bodyResolveComponents, null, 4, null);
            if (unambiguousCandidate$default != null) {
                FirBasedSymbol<?> symbol = unambiguousCandidate$default.getSymbol();
                FirClassLikeSymbol firClassLikeSymbol = symbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) symbol : null;
                if (firClassLikeSymbol == null) {
                    continue;
                } else {
                    if (unambiguousCandidate$default.getApplicability() == CandidateApplicability.RESOLVED) {
                        return buildResolvedQualifierResultForTopLevelClass(bodyResolveComponents, firClassLikeSymbol, firQualifiedAccessExpression, list, unambiguousCandidate$default);
                    }
                    if (typeCandidate == null) {
                        typeCandidate = unambiguousCandidate$default;
                    }
                }
            }
        }
        if (typeCandidate != null) {
            FirBasedSymbol<?> symbol2 = typeCandidate.getSymbol();
            symbol2.getClass();
            return buildResolvedQualifierResultForTopLevelClass(bodyResolveComponents, (FirClassLikeSymbol) symbol2, firQualifiedAccessExpression, list, typeCandidate);
        }
        QualifierResolutionResult qualifierResolutionResultContinueQualifierInPackage$default = continueQualifierInPackage$default(FqName.ROOT, name, firQualifiedAccessExpression, list, bodyResolveComponents, null, 16, null);
        if (!z) {
            if (((qualifierResolutionResultContinueQualifierInPackage$default == null || (qualifier = qualifierResolutionResultContinueQualifierInPackage$default.getQualifier()) == null) ? null : qualifier.getSymbol()) != null) {
                return null;
            }
        }
        return qualifierResolutionResultContinueQualifierInPackage$default;
    }
}
