package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.ArraysKt;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirRetentionAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0015H\u0002J5\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001aJ5\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0019H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001aJ?\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\"J?\u0010#\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010$J?\u0010%\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010$J?\u0010&\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010$R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirRepeatableAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "REPEATABLE_ANNOTATION_CONTAINER_NAME", "Lorg/jetbrains/kotlin/name/Name;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "resolveContainerAnnotation", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "checkJavaRepeatableAnnotationDeclaration", "javaRepeatable", "annotationClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "checkKotlinRepeatableAnnotationDeclaration", "kotlinRepeatable", "checkRepeatableAnnotationContainer", "containerClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "annotationSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkContainerParameters", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkContainerRetention", "checkContainerTarget", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRepeatableAnnotationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirRepeatableAnnotationChecker INSTANCE = new FirRepeatableAnnotationChecker();
    private static final Name REPEATABLE_ANNOTATION_CONTAINER_NAME;

    static {
        Name nameIdentifier = Name.identifier("Container");
        nameIdentifier.getClass();
        REPEATABLE_ANNOTATION_CONTAINER_NAME = nameIdentifier;
    }

    private FirRepeatableAnnotationChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkContainerParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClassSymbol firRegularClassSymbol, FirRegularClass firRegularClass, KtSourceElement ktSourceElement) {
        Object obj;
        Object next;
        FirResolvedTypeRef resolvedReturnTypeRef;
        ConeKotlinType coneType;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(firRegularClassSymbol, checkerContext.getSession());
        if (firConstructorSymbolPrimaryConstructorIfAny == null) {
            return;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols();
        Name value = StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue();
        List<FirValueParameterSymbol> list = valueParameterSymbols;
        Iterator<T> it = list.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((FirValueParameterSymbol) next).getName(), value));
        FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) next;
        ConeKotlinType coneKotlinTypeFullyExpandedType = (firValueParameterSymbol == null || (resolvedReturnTypeRef = firValueParameterSymbol.getResolvedReturnTypeRef()) == null || (coneType = resolvedReturnTypeRef.getConeType()) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneType);
        if (coneKotlinTypeFullyExpandedType == null || !ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinTypeFullyExpandedType) || !Intrinsics.areEqual(ConeTypeProjectionKt.getType((ConeTypeProjection) ArraysKt.single(coneKotlinTypeFullyExpandedType.getTypeArguments())), ScopeUtilsKt.defaultType(firRegularClass))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getREPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR(), (Object) firRegularClassSymbol.getClassId(), (Object) FirDeclarationUtilKt.getClassId(firRegularClass), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        for (Object obj2 : list) {
            FirValueParameterSymbol firValueParameterSymbol2 = (FirValueParameterSymbol) obj2;
            if (!Intrinsics.areEqual(firValueParameterSymbol2.getName(), value) && !firValueParameterSymbol2.getHasDefaultValue()) {
                obj = obj2;
                break;
            }
        }
        FirValueParameterSymbol firValueParameterSymbol3 = (FirValueParameterSymbol) obj;
        if (firValueParameterSymbol3 != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getREPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR(), (Object) firRegularClassSymbol.getClassId(), (Object) firValueParameterSymbol3.getName(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final void checkContainerRetention(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClassSymbol firRegularClassSymbol, FirRegularClass firRegularClass, KtSourceElement ktSourceElement) {
        AnnotationRetention annotationRetention = FirRetentionAnnotationHelpersKt.getAnnotationRetention(firRegularClass.getSymbol(), checkerContext.getSession());
        AnnotationRetention annotationRetention2 = FirRetentionAnnotationHelpersKt.getAnnotationRetention(firRegularClassSymbol, checkerContext.getSession());
        if (annotationRetention2.compareTo(annotationRetention) < 0) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory4<ClassId, String, ClassId, String>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirJvmErrors.INSTANCE.getREPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR()), firRegularClassSymbol.getClassId(), annotationRetention2.name(), FirDeclarationUtilKt.getClassId(firRegularClass), annotationRetention.name(), (128 & 128) != 0 ? null : null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003e  */
    private final void checkContainerTarget(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClassSymbol firRegularClassSymbol, FirRegularClass firRegularClass, KtSourceElement ktSourceElement) {
        Set<KotlinTarget> allowedAnnotationTargets = FirAnnotationHelpersKt.getAllowedAnnotationTargets(firRegularClass, checkerContext.getSession());
        for (KotlinTarget kotlinTarget : FirAnnotationHelpersKt.getAllowedAnnotationTargets(firRegularClassSymbol, checkerContext.getSession())) {
            boolean zContains = true;
            if (!allowedAnnotationTargets.contains(kotlinTarget)) {
                if (kotlinTarget == KotlinTarget.ANNOTATION_CLASS) {
                    if (!allowedAnnotationTargets.contains(KotlinTarget.CLASS) && !allowedAnnotationTargets.contains(KotlinTarget.TYPE)) {
                        zContains = false;
                    }
                } else if (kotlinTarget == KotlinTarget.CLASS) {
                    zContains = allowedAnnotationTargets.contains(KotlinTarget.TYPE);
                } else if (kotlinTarget == KotlinTarget.TYPE_PARAMETER) {
                    zContains = allowedAnnotationTargets.contains(KotlinTarget.TYPE);
                } else {
                    zContains = false;
                }
            }
            if (!zContains) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getREPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR(), (Object) firRegularClassSymbol.getClassId(), (Object) FirDeclarationUtilKt.getClassId(firRegularClass), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
        }
    }

    private final void checkJavaRepeatableAnnotationDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotation firAnnotation, FirRegularClass firRegularClass) {
        ClassId classIdResolveContainerAnnotation = resolveContainerAnnotation(firAnnotation);
        if (classIdResolveContainerAnnotation == null) {
            return;
        }
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getClassLikeSymbolByClassId(classIdResolveContainerAnnotation);
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
        if (firRegularClassSymbol == null) {
            return;
        }
        checkRepeatableAnnotationContainer(checkerContext, diagnosticReporter, firRegularClass, firRegularClassSymbol, firAnnotation.getSource());
    }

    private final void checkKotlinRepeatableAnnotationDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotation firAnnotation, FirRegularClass firRegularClass) {
        if (FirScopeKt.getSingleClassifier(FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClass), REPEATABLE_ANNOTATION_CONTAINER_NAME) != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getREPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkRepeatableAnnotationContainer(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass, FirRegularClassSymbol firRegularClassSymbol, KtSourceElement ktSourceElement) {
        checkContainerParameters(checkerContext, diagnosticReporter, firRegularClassSymbol, firRegularClass, ktSourceElement);
        checkContainerRetention(checkerContext, diagnosticReporter, firRegularClassSymbol, firRegularClass, ktSourceElement);
        checkContainerTarget(checkerContext, diagnosticReporter, firRegularClassSymbol, firRegularClass, ktSourceElement);
    }

    private final ClassId resolveContainerAnnotation(FirAnnotation firAnnotation) {
        FirExpression argument;
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue(), false, 2, null);
        if (firExpressionFindArgumentByName$default == null) {
            return null;
        }
        FirGetClassCall firGetClassCall = firExpressionFindArgumentByName$default instanceof FirGetClassCall ? (FirGetClassCall) firExpressionFindArgumentByName$default : null;
        if (firGetClassCall != null && (argument = firGetClassCall.getArgument()) != null) {
            if (argument instanceof FirResolvedQualifier) {
                return ((FirResolvedQualifier) argument).getClassId();
            }
            if (argument instanceof FirClassReferenceExpression) {
                ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(FirTypeUtilsKt.getConeType(((FirClassReferenceExpression) argument).getClassTypeRef()));
                ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
                if (coneClassLikeType == null) {
                    return null;
                }
                return coneClassLikeType.getLookupTag().getClassId();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirClassLikeSymbol<?> annotationClassLikeSymbol;
        ClassId classIdResolveContainerAnnotation;
        HashMap map;
        List list;
        ConeClassLikeLookupTag lookupTag;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        List<FirAnnotation> annotations = firDeclaration.getAnnotations();
        if (annotations.isEmpty()) {
            return;
        }
        HashMap map2 = new HashMap();
        FirSession session = checkerContext.getSession();
        for (FirAnnotation firAnnotation : annotations) {
            FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
            ClassId classId = null;
            FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            if (coneClassLikeType != null && (lookupTag = coneClassLikeType.getLookupTag()) != null) {
                classId = lookupTag.getClassId();
            }
            if (classId != null && (annotationClassLikeSymbol = FirAnnotationUtilsKt.toAnnotationClassLikeSymbol(firAnnotation, session)) != null && !((FirClassLikeDeclaration) annotationClassLikeSymbol.getFir()).getIsLocal()) {
                AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
                ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef()));
                Object arrayList = map2.get(coneKotlinTypeFullyExpandedType);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map2.put(coneKotlinTypeFullyExpandedType, arrayList);
                }
                List list2 = (List) arrayList;
                if (!list2.contains(useSiteTarget)) {
                    List list3 = list2;
                    if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                        Iterator it = list3.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                boolean z = false;
                                boolean z2 = ((AnnotationUseSiteTarget) it.next()) == null;
                                if (useSiteTarget == null) {
                                    z = true;
                                }
                                if (z2 != z) {
                                    if (FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(session).symbolContainsRepeatableAnnotation(annotationClassLikeSymbol, session) && FirRetentionAnnotationHelpersKt.getAnnotationRetention(annotationClassLikeSymbol, session) != AnnotationRetention.SOURCE && (classIdResolveContainerAnnotation = resolveContainerAnnotation(annotationClassLikeSymbol, session)) != null) {
                                        List<FirAnnotation> list4 = annotations;
                                        if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                                            Iterator<T> it2 = list4.iterator();
                                            while (true) {
                                                if (it2.hasNext()) {
                                                    if (Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassId((FirAnnotation) it2.next(), session), classIdResolveContainerAnnotation)) {
                                                        map = map2;
                                                        list = list2;
                                                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getREPEATED_ANNOTATION_WITH_CONTAINER(), (Object) classId, (Object) classIdResolveContainerAnnotation, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                                                        break;
                                                    }
                                                    list2 = list2;
                                                    map2 = map2;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    map = map2;
                    list = list2;
                    break;
                }
                if (FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(session).symbolContainsRepeatableAnnotation(annotationClassLikeSymbol, session)) {
                    map = map2;
                    list = list2;
                    break;
                } else {
                    map = map2;
                    list = list2;
                    break;
                }
                list.add(useSiteTarget);
                map2 = map;
            }
        }
        if (firDeclaration instanceof FirRegularClass) {
            FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(annotations, JvmStandardClassIds.Annotations.Java.INSTANCE.getRepeatable(), session);
            if (annotationByClassId != null) {
                checkJavaRepeatableAnnotationDeclaration(checkerContext, diagnosticReporter, annotationByClassId, (FirRegularClass) firDeclaration);
                return;
            }
            FirAnnotation annotationByClassId2 = FirAnnotationUtilsKt.getAnnotationByClassId(annotations, StandardClassIds$Annotations.INSTANCE.getRepeatable(), session);
            if (annotationByClassId2 != null) {
                checkKotlinRepeatableAnnotationDeclaration(checkerContext, diagnosticReporter, annotationByClassId2, (FirRegularClass) firDeclaration);
            }
        }
    }

    private final ClassId resolveContainerAnnotation(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) {
        FirAnnotation annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firClassLikeSymbol, StandardClassIds$Annotations.INSTANCE.getRepeatable(), firSession);
        if (annotationWithResolvedArgumentsByClassId == null && (annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firClassLikeSymbol, JvmStandardClassIds.Annotations.Java.INSTANCE.getRepeatable(), firSession)) == null) {
            return null;
        }
        return resolveContainerAnnotation(annotationWithResolvedArgumentsByClassId);
    }
}
