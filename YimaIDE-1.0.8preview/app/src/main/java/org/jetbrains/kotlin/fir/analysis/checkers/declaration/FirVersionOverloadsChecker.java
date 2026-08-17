package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitorVoid;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002\"#B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u0012H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0016J-\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0018J=\u0010\u0019\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b\u0012\u0004\u0012\u00020\u001c0\u001a2\u0006\u0010\u000e\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u0011*\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0082\u0004J\u0019\u0010 \u001a\u00020\u0011*\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0082\u0004J\f\u0010!\u001a\u00020\u0011*\u00020\u0002H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirVersionOverloadsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "versionArgument", "Lorg/jetbrains/kotlin/name/Name;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "hasIntroducedAtAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "getIntroducedAtAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "checkDeclarationOrContainingClass", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "computeAndCheckParameterVersions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/config/MavenComparableVersion;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Ljava/util/Map;", "lessThanOrEqual", "other", "greaterThan", "isCopyMethod", "DependencyChecker", "ComplexExpressionChecker", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirVersionOverloadsChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirVersionOverloadsChecker INSTANCE = new FirVersionOverloadsChecker();
    private static final Name versionArgument;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u00020\r*\u00020\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirVersionOverloadsChecker$ComplexExpressionChecker;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitorVoid;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "getReporter", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "tooComplex", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ComplexExpressionChecker extends FirDefaultVisitorVoid {
        private final CheckerContext context;
        private final DiagnosticReporter reporter;

        public ComplexExpressionChecker(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            this.context = checkerContext;
            this.reporter = diagnosticReporter;
        }

        public final CheckerContext getContext() {
            return this.context;
        }

        public final DiagnosticReporter getReporter() {
            return this.reporter;
        }

        public final void tooComplex(FirElement firElement) {
            firElement.getClass();
            KtDiagnosticReportHelpersKt.reportOn$default(this.reporter, (AbstractKtSourceElement) firElement.getSource(), FirErrors.INSTANCE.getVERSION_OVERLOADS_TOO_COMPLEX_EXPRESSION(), (DiagnosticContext) this.context, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }

        public void visitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression) {
            anonymousObjectExpression.getClass();
            tooComplex(anonymousObjectExpression);
        }

        public void visitElement(FirElement element) {
            element.getClass();
            element.acceptChildren(this);
        }
    }

    static {
        Name nameIdentifier = Name.identifier("version");
        nameIdentifier.getClass();
        versionArgument = nameIdentifier;
    }

    private FirVersionOverloadsChecker() {
        super(MppCheckerKind.Platform);
    }

    private final boolean checkDeclarationOrContainingClass(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firFunction);
        Modality modality = firFunction.getStatus().getModality();
        if (modality != null && modality != Modality.FINAL) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_NONFINAL_FUNCTION(), (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getDECLARATION_NAME());
            return true;
        }
        if (firFunction.getIsLocal()) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_LOCAL_FUNCTION(), (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getDECLARATION_NAME());
            return true;
        }
        if (!(firFunction instanceof FirConstructor) && containingClassSymbol != null && containingClassSymbol.getResolvedStatus().getModality() != Modality.FINAL) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_NONFINAL_CLASS(), (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getDECLARATION_NAME());
            return true;
        }
        if ((containingClassSymbol != null ? FirHelpersKt.getClassKind(containingClassSymbol) : null) != ClassKind.ANNOTATION_CLASS) {
            return false;
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_ANNOTATION_CLASS(), (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getDECLARATION_NAME());
        return true;
    }

    private final Map<FirCallableSymbol<?>, MavenComparableVersion> computeAndCheckParameterVersions(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        FirFunction firFunction2;
        FirCallableSymbol firCallableSymbol;
        String stringArgument;
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firFunction);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        MavenComparableVersion mavenComparableVersion = null;
        int i = 0;
        for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
            int i2 = i + 1;
            FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firValueParameter, StandardClassIds$Annotations.INSTANCE.getIntroducedAt(), checkerContext.getSession());
            MavenComparableVersion mavenComparableVersion2 = (annotationByClassId == null || (stringArgument = FirAnnotationUtilsKt.getStringArgument(annotationByClassId, versionArgument)) == null) ? null : new MavenComparableVersion(stringArgument);
            if (mavenComparableVersion2 == null) {
                boolean z = i == CollectionsKt.getLastIndex(firFunction.getValueParameters()) && FunctionalTypeUtilsKt.isSomeFunctionType(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), checkerContext.getSession());
                if (firValueParameter.getDefaultValue() == null && mavenComparableVersion != null && !z) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getINVALID_NON_OPTIONAL_PARAMETER_POSITION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            } else {
                linkedHashMap.put(firValueParameter.getSymbol(), mavenComparableVersion2);
                if (firValueParameter.getIsVararg()) {
                    KtSourceElement source = annotationByClassId.getSource();
                    if (source == null) {
                        source = firValueParameter.getSource();
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getINVALID_VERSIONING_ON_VARARG(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else {
                    if (firValueParameter.getIsVal()) {
                        firFunction2 = firFunction;
                        if ((firFunction2 instanceof FirConstructor) && containingClassSymbol != null && (containingClassSymbol.getRawStatus().isInline() || containingClassSymbol.getRawStatus().isValue())) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_VALUE_CLASS_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    } else {
                        firFunction2 = firFunction;
                    }
                    if (firValueParameter.getDefaultValue() == null && !firFunction2.getStatus().isActual()) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_NON_OPTIONAL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
                if (lessThanOrEqual(mavenComparableVersion, mavenComparableVersion2)) {
                    mavenComparableVersion = mavenComparableVersion2;
                } else {
                    KtSourceElement source2 = annotationByClassId.getSource();
                    KtDiagnosticFactory3<MavenComparableVersion, MavenComparableVersion, FirCallableSymbol<?>> non_ascending_version_annotation = FirErrors.INSTANCE.getNON_ASCENDING_VERSION_ANNOTATION();
                    Iterator it = linkedHashMap.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            firCallableSymbol = null;
                            break;
                        }
                        Map.Entry entry = (Map.Entry) it.next();
                        FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) entry.getKey();
                        if (!Intrinsics.areEqual((MavenComparableVersion) entry.getValue(), mavenComparableVersion)) {
                            firCallableSymbol2 = null;
                        }
                        if (firCallableSymbol2 != null) {
                            firCallableSymbol = firCallableSymbol2;
                            break;
                        }
                    }
                    if (firCallableSymbol == null) {
                        hb9.a("No element of the map was transformed to a non-null value.");
                        return null;
                    }
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, (KtDiagnosticFactory3<MavenComparableVersion, MavenComparableVersion, FirCallableSymbol>) ((KtDiagnosticFactory3<Object, Object, Object>) non_ascending_version_annotation), mavenComparableVersion2, mavenComparableVersion, firCallableSymbol, (64 & 64) != 0 ? null : null);
                }
            }
            i = i2;
        }
        return linkedHashMap;
    }

    private final FirAnnotation getIntroducedAtAnnotation(CheckerContext checkerContext, FirDeclaration firDeclaration) {
        return FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, StandardClassIds$Annotations.INSTANCE.getIntroducedAt(), checkerContext.getSession());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean greaterThan(MavenComparableVersion mavenComparableVersion, MavenComparableVersion mavenComparableVersion2) {
        return !lessThanOrEqual(mavenComparableVersion, mavenComparableVersion2);
    }

    private final boolean hasIntroducedAtAnnotation(CheckerContext checkerContext, FirDeclaration firDeclaration) {
        return FirAnnotationUtilsKt.hasAnnotation(firDeclaration, StandardClassIds$Annotations.INSTANCE.getIntroducedAt(), checkerContext.getSession());
    }

    private final boolean isCopyMethod(FirFunction firFunction) {
        FirClassLikeSymbol<?> containingClassSymbol;
        return Intrinsics.areEqual(firFunction.getOrigin(), FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE) && Intrinsics.areEqual(FirDeclarationUtilKt.getNameOrSpecialName(firFunction), StandardNames.DATA_CLASS_COPY) && (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firFunction)) != null && containingClassSymbol.getRawStatus().isData();
    }

    private final boolean lessThanOrEqual(MavenComparableVersion mavenComparableVersion, MavenComparableVersion mavenComparableVersion2) {
        if (mavenComparableVersion == null) {
            return true;
        }
        return mavenComparableVersion2 != null && mavenComparableVersion.compareTo(mavenComparableVersion2) <= 0;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        if (isCopyMethod(firFunction)) {
            return;
        }
        Iterator it = CollectionsKt.plus(firFunction.getContextParameters(), CollectionsKt.listOfNotNull(firFunction.getReceiverParameter())).iterator();
        boolean z = false;
        while (it.hasNext()) {
            FirAnnotation introducedAtAnnotation = getIntroducedAtAnnotation(checkerContext, (FirDeclaration) it.next());
            if (introducedAtAnnotation != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) introducedAtAnnotation.getSource(), FirErrors.INSTANCE.getINVALID_VERSIONING_ON_RECEIVER_OR_CONTEXT_PARAMETER_POSITION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                z = true;
            }
        }
        if (!z) {
            List<FirValueParameter> valueParameters = firFunction.getValueParameters();
            if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
                return;
            }
            Iterator<T> it2 = valueParameters.iterator();
            while (it2.hasNext()) {
                if (INSTANCE.hasIntroducedAtAnnotation(checkerContext, (FirValueParameter) it2.next())) {
                }
            }
            return;
        }
        checkDeclarationOrContainingClass(checkerContext, diagnosticReporter, firFunction);
        Map<FirCallableSymbol<?>, MavenComparableVersion> mapComputeAndCheckParameterVersions = computeAndCheckParameterVersions(checkerContext, diagnosticReporter, firFunction);
        DependencyChecker dependencyChecker = new DependencyChecker(checkerContext, diagnosticReporter, mapComputeAndCheckParameterVersions);
        FirVisitorVoid complexExpressionChecker = new ComplexExpressionChecker(checkerContext, diagnosticReporter);
        for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
            FirExpression defaultValue = firValueParameter.getDefaultValue();
            if (defaultValue != null) {
                defaultValue.accept(dependencyChecker, mapComputeAndCheckParameterVersions.get(firValueParameter.getSymbol()));
                defaultValue.accept(complexExpressionChecker);
            }
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0016\u0010\b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R!\u0010\b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirVersionOverloadsChecker$DependencyChecker;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/MavenComparableVersion;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "symbolVersions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/Map;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "getReporter", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "getSymbolVersions", "()Ljava/util/Map;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DependencyChecker extends FirDefaultVisitor<Unit, MavenComparableVersion> {
        private final CheckerContext context;
        private final DiagnosticReporter reporter;
        private final Map<FirCallableSymbol<?>, MavenComparableVersion> symbolVersions;

        /* JADX WARN: Multi-variable type inference failed */
        public DependencyChecker(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, Map<FirCallableSymbol<?>, ? extends MavenComparableVersion> map) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            map.getClass();
            this.context = checkerContext;
            this.reporter = diagnosticReporter;
            this.symbolVersions = map;
        }

        public final CheckerContext getContext() {
            return this.context;
        }

        public final DiagnosticReporter getReporter() {
            return this.reporter;
        }

        public final Map<FirCallableSymbol<?>, MavenComparableVersion> getSymbolVersions() {
            return this.symbolVersions;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
            visitElement(firElement, (MavenComparableVersion) obj);
            return Unit.INSTANCE;
        }

        public void visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, MavenComparableVersion data) {
            qualifiedAccessExpression.getClass();
            FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(qualifiedAccessExpression);
            if (resolvedCallableSymbol == null) {
                return;
            }
            MavenComparableVersion mavenComparableVersion = this.symbolVersions.get(resolvedCallableSymbol);
            if (FirVersionOverloadsChecker.INSTANCE.greaterThan(mavenComparableVersion, data)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) this.context, this.reporter, (AbstractKtSourceElement) qualifiedAccessExpression.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getINVALID_DEFAULT_VALUE_DEPENDENCY(), (Object) data, (Object) mavenComparableVersion, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }

        public void visitElement(FirElement element, MavenComparableVersion data) {
            element.getClass();
            element.acceptChildren(this, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitQualifiedAccessExpression(FirQualifiedAccessExpression firQualifiedAccessExpression, Object obj) {
            visitQualifiedAccessExpression(firQualifiedAccessExpression, (MavenComparableVersion) obj);
            return Unit.INSTANCE;
        }
    }
}
