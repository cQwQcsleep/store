package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnnotationClassDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirRetentionAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirConstChecksKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ1\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J-\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001d¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnnotationClassDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "checkAnnotationClassMember", "member", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "isAllowedClassKind", Argument.Delimiters.none, "cone", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isAllowedArray", ModuleXmlParser.TYPE, "checkCyclesInParameters", "annotation", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "CycleChecker", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationClassDeclarationChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirAnnotationClassDeclarationChecker INSTANCE = new FirAnnotationClassDeclarationChecker();

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnnotationClassDeclarationChecker$CycleChecker;", Argument.Delimiters.none, "targetAnnotation", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getTargetAnnotation", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "visitedAnnotations", Argument.Delimiters.none, "annotationsWithCycle", "annotationHasCycle", Argument.Delimiters.none, "annotation", "parameterHasCycle", "ownedAnnotation", "parameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "typeHasCycle", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CycleChecker {
        private final Set<FirRegularClassSymbol> annotationsWithCycle;
        private final FirSession session;
        private final FirRegularClassSymbol targetAnnotation;
        private final Set<FirRegularClassSymbol> visitedAnnotations;

        public CycleChecker(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
            firRegularClassSymbol.getClass();
            firSession.getClass();
            this.targetAnnotation = firRegularClassSymbol;
            this.session = firSession;
            this.visitedAnnotations = SetsKt.mutableSetOf(new FirRegularClassSymbol[]{firRegularClassSymbol});
            this.annotationsWithCycle = SetsKt.mutableSetOf(new FirRegularClassSymbol[]{firRegularClassSymbol});
        }

        public final boolean annotationHasCycle(FirRegularClassSymbol annotation) {
            annotation.getClass();
            FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(annotation, this.session);
            if (firConstructorSymbolPrimaryConstructorIfAny == null) {
                return false;
            }
            Iterator<FirValueParameterSymbol> it = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols().iterator();
            while (it.hasNext()) {
                if (parameterHasCycle(annotation, it.next())) {
                    return true;
                }
            }
            return false;
        }

        public final FirSession getSession() {
            return this.session;
        }

        public final FirRegularClassSymbol getTargetAnnotation() {
            return this.targetAnnotation;
        }

        public final boolean parameterHasCycle(FirRegularClassSymbol ownedAnnotation, FirValueParameterSymbol parameter) {
            ownedAnnotation.getClass();
            parameter.getClass();
            ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(parameter.getResolvedReturnTypeRef().getConeType(), this.session, (Function1) null, 2, (Object) null);
            if (parameter.isVararg() || ConeBuiltinTypeUtilsKt.isNonPrimitiveArray(coneKotlinTypeFullyExpandedType$default)) {
                return false;
            }
            if (coneKotlinTypeFullyExpandedType$default.getTypeArguments().length == 0) {
                return typeHasCycle(ownedAnnotation, coneKotlinTypeFullyExpandedType$default);
            }
            if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinTypeFullyExpandedType$default), StandardClassIds.INSTANCE.getKClass())) {
                return false;
            }
            for (ConeTypeProjection coneTypeProjection : coneKotlinTypeFullyExpandedType$default.getTypeArguments()) {
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                if (type != null && typeHasCycle(ownedAnnotation, type)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        public final boolean typeHasCycle(FirRegularClassSymbol ownedAnnotation, ConeKotlinType type) throws KotlinIllegalArgumentExceptionWithAttachments {
            ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
            FirCallableDeclaration original;
            ownedAnnotation.getClass();
            type.getClass();
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(type, this.session, (Function1) null, 2, (Object) null), this.session);
            if (regularClassSymbol != null) {
                if (regularClassSymbol.getClassKind() != ClassKind.ANNOTATION_CLASS) {
                    regularClassSymbol = null;
                }
                if (regularClassSymbol != null) {
                    if (!this.visitedAnnotations.add(regularClassSymbol)) {
                        boolean zContains = this.annotationsWithCycle.contains(regularClassSymbol);
                        if (zContains) {
                            this.annotationsWithCycle.add(ownedAnnotation);
                        }
                        return zContains;
                    }
                    if (Intrinsics.areEqual(regularClassSymbol, this.targetAnnotation)) {
                        this.annotationsWithCycle.add(ownedAnnotation);
                        return true;
                    }
                    FirDeclarationOrigin origin = regularClassSymbol.getOrigin();
                    if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                        E fir = regularClassSymbol.getFir();
                        FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                        if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                            return annotationHasCycle(regularClassSymbol);
                        }
                    }
                }
            }
            return false;
        }
    }

    private FirAnnotationClassDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    public static Unit b(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        INSTANCE.checkAnnotationClassMember(checkerContext, diagnosticReporter, firBasedSymbol);
        return Unit.INSTANCE;
    }

    private final void checkAnnotationClassMember(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firBasedSymbol;
            if (firConstructorSymbol.isPrimary()) {
                for (FirValueParameterSymbol firValueParameterSymbol : firConstructorSymbol.getValueParameterSymbols()) {
                    KtSourceElement source = firValueParameterSymbol.getSource();
                    if (source != null) {
                        if (!LightTreePositioningStrategiesKt.hasValOrVar(source)) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getMISSING_VAL_ON_ANNOTATION_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        } else if (LightTreePositioningStrategiesKt.hasVar(source)) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getVAR_ANNOTATION_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                        if (firValueParameterSymbol.getHasDefaultValue() && !FirConstChecksKt.canBeEvaluatedAtCompileTime(firValueParameterSymbol.getResolvedDefaultValue(), checkerContext.getSession(), true, true)) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getDefaultValueSource(), FirErrors.INSTANCE.getANNOTATION_PARAMETER_DEFAULT_VALUE_MUST_BE_CONSTANT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                        FirResolvedTypeRef resolvedReturnTypeRef = firValueParameterSymbol.getResolvedReturnTypeRef();
                        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, resolvedReturnTypeRef.getConeType());
                        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinTypeFullyExpandedType);
                        if (!(coneKotlinTypeFullyExpandedType instanceof ConeErrorType)) {
                            if (ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType)) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef.getSource(), FirErrors.INSTANCE.getNULLABLE_TYPE_OF_ANNOTATION_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            } else if (!ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneKotlinTypeFullyExpandedType) && !ConeBuiltinTypeUtilsKt.isUnsignedTypeOrNullableUnsignedType(coneKotlinTypeFullyExpandedType)) {
                                StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
                                if (!Intrinsics.areEqual(classId, standardClassIds.getKClass()) && !Intrinsics.areEqual(classId, standardClassIds.getString()) && !CollectionsKt.contains(standardClassIds.getPrimitiveArrayTypeByElementType().values(), classId) && !CollectionsKt.contains(standardClassIds.getUnsignedArrayTypeByElementType().values(), classId)) {
                                    if (Intrinsics.areEqual(classId, standardClassIds.getArray())) {
                                        if (!isAllowedArray(coneKotlinTypeFullyExpandedType, checkerContext.getSession())) {
                                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef.getSource(), FirErrors.INSTANCE.getINVALID_TYPE_OF_ANNOTATION_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                        } else if (!firValueParameterSymbol.isVararg()) {
                                            ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.firstOrNull(coneKotlinTypeFullyExpandedType.getTypeArguments());
                                            if ((coneTypeProjection != null ? ConeTypeProjectionKt.getVariance(coneTypeProjection) : null) != Variance.INVARIANT) {
                                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef.getSource(), FirErrors.INSTANCE.getPROJECTION_IN_TYPE_OF_ANNOTATION_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                            }
                                        }
                                    } else if (!isAllowedClassKind(coneKotlinTypeFullyExpandedType, checkerContext.getSession())) {
                                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef.getSource(), FirErrors.INSTANCE.getINVALID_TYPE_OF_ANNOTATION_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                    }
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        if (firBasedSymbol instanceof FirRegularClassSymbol) {
            return;
        }
        if (firBasedSymbol instanceof FirPropertySymbol) {
            KtSourceElement source2 = ((FirPropertySymbol) firBasedSymbol).getSource();
            if (Intrinsics.areEqual(source2 != null ? source2.getElementType() : null, KtNodeTypes.VALUE_PARAMETER)) {
                return;
            }
        }
        if ((firBasedSymbol instanceof FirNamedFunctionSymbol) && ((FirNamedFunctionSymbol) firBasedSymbol).isSynthetic()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBasedSymbol.getSource(), FirErrors.INSTANCE.getANNOTATION_CLASS_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final void checkCyclesInParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClassSymbol firRegularClassSymbol) {
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firRegularClassSymbol, checkerContext.getSession());
        if (firConstructorSymbolPrimaryConstructorIfAny == null) {
            return;
        }
        CycleChecker cycleChecker = new CycleChecker(firRegularClassSymbol, checkerContext.getSession());
        for (FirValueParameterSymbol firValueParameterSymbol : firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols()) {
            if (cycleChecker.parameterHasCycle(firRegularClassSymbol, firValueParameterSymbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), FirErrors.INSTANCE.getCYCLE_IN_ANNOTATION_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final boolean isAllowedArray(ConeKotlinType type, FirSession session) {
        ConeKotlinType type2;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default;
        ConeKotlinTypeProjection[] typeArguments = type.getTypeArguments();
        if (typeArguments.length != 1) {
            return false;
        }
        ConeKotlinTypeProjection coneKotlinTypeProjection = typeArguments[0];
        ConeKotlinTypeProjection coneKotlinTypeProjection2 = coneKotlinTypeProjection instanceof ConeKotlinTypeProjection ? coneKotlinTypeProjection : null;
        if (coneKotlinTypeProjection2 == null || (type2 = coneKotlinTypeProjection2.getType()) == null || (coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(type2, session, (Function1) null, 2, (Object) null)) == null || ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType$default)) {
            return false;
        }
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinTypeFullyExpandedType$default);
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        return Intrinsics.areEqual(classId, standardClassIds.getKClass()) || Intrinsics.areEqual(classId, standardClassIds.getString()) || isAllowedClassKind((ConeLookupTagBasedType) coneKotlinTypeFullyExpandedType$default, session);
    }

    private final boolean isAllowedClassKind(ConeKotlinType cone, FirSession session) {
        ClassKind classKind;
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(cone, session);
        if (regularClassSymbol == null || (classKind = regularClassSymbol.getClassKind()) == null) {
            return false;
        }
        return classKind == ClassKind.ANNOTATION_CLASS || classKind == ClassKind.ENUM_CLASS;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        FirElement targetAnnotation;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getClassKind() != ClassKind.ANNOTATION_CLASS) {
            return;
        }
        if (firRegularClass.getIsLocal()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getLOCAL_ANNOTATION_CLASS_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (firRegularClass.getSuperTypeRefs().size() != 1) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getSUPERTYPES_FOR_ANNOTATION_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclarations$default(firRegularClass, checkerContext.getSession(), (FirResolvePhase) null, new Function1() { // from class: hy4
            public final Object invoke(Object obj) {
                return FirAnnotationClassDeclarationChecker.b(checkerContext, diagnosticReporter, (FirBasedSymbol) obj);
            }
        }, 2, (Object) null);
        FirSession session = checkerContext.getSession();
        if (FirRetentionAnnotationHelpersKt.getRetention(firRegularClass, session) != AnnotationRetention.SOURCE && FirAnnotationHelpersKt.getAllowedAnnotationTargets(firRegularClass, session).contains(KotlinTarget.EXPRESSION)) {
            FirAnnotation retentionAnnotation = FirRetentionAnnotationHelpersKt.getRetentionAnnotation(firRegularClass, session);
            if (retentionAnnotation != null) {
                targetAnnotation = retentionAnnotation;
            } else {
                targetAnnotation = FirAnnotationHelpersKt.getTargetAnnotation(firRegularClass, session);
                if (targetAnnotation == null) {
                    targetAnnotation = firRegularClass;
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) targetAnnotation.getSource(), FirErrors.INSTANCE.getRESTRICTED_RETENTION_FOR_EXPRESSION_ANNOTATION_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        checkCyclesInParameters(checkerContext, diagnosticReporter, firRegularClass.getSymbol());
    }
}
