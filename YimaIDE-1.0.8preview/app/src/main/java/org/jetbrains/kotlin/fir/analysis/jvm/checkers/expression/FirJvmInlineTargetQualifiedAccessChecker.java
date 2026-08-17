package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.java.FirJvmTargetProvider;
import org.jetbrains.kotlin.fir.java.FirJvmTargetProviderKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.load.kotlin.FileBasedKotlinClass;
import org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinarySourceElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmInlineTargetQualifiedAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkInlineTargetVersion", "callableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/FirElement;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmInlineTargetQualifiedAccessChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirJvmInlineTargetQualifiedAccessChecker INSTANCE = new FirJvmInlineTargetQualifiedAccessChecker();

    private FirJvmInlineTargetQualifiedAccessChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkInlineTargetVersion(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirElement firElement) {
        JvmTarget jvmTarget;
        KotlinJvmBinaryClass knownJvmBinaryClass;
        int classVersion;
        int majorVersion;
        KtSourceElement source;
        FirJvmTargetProvider jvmTargetProvider = FirJvmTargetProviderKt.getJvmTargetProvider(checkerContext.getSession());
        if (jvmTargetProvider == null || (jvmTarget = jvmTargetProvider.getJvmTarget()) == null) {
            return;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol);
        if (coneClassLikeLookupTagContainingClassLookupTag != null) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag);
            if (regularClassSymbol == null) {
                return;
            }
            KotlinJvmBinarySourceElement sourceElement = DeclarationAttributesKt.getSourceElement((FirClassLikeDeclaration) regularClassSymbol.getFir());
            KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = sourceElement instanceof KotlinJvmBinarySourceElement ? sourceElement : null;
            if (kotlinJvmBinarySourceElement == null) {
                return;
            } else {
                knownJvmBinaryClass = kotlinJvmBinarySourceElement.getBinaryClass();
            }
        } else {
            JvmPackagePartSource containerSource = firCallableSymbol.getContainerSource();
            JvmPackagePartSource jvmPackagePartSource = containerSource instanceof JvmPackagePartSource ? containerSource : null;
            if (jvmPackagePartSource == null) {
                return;
            } else {
                knownJvmBinaryClass = jvmPackagePartSource.getKnownJvmBinaryClass();
            }
        }
        FileBasedKotlinClass fileBasedKotlinClass = knownJvmBinaryClass instanceof FileBasedKotlinClass ? (FileBasedKotlinClass) knownJvmBinaryClass : null;
        if (fileBasedKotlinClass == null || (majorVersion = jvmTarget.getMajorVersion()) >= (classVersion = fileBasedKotlinClass.getClassVersion())) {
            return;
        }
        FirReference reference = ReferenceUtilsKt.toReference(firElement, checkerContext.getSession());
        if (reference == null || (source = reference.getSource()) == null) {
            source = firElement.getSource();
        }
        KtSourceElement ktSourceElement = source;
        KtDiagnosticFactory2<String, String> inline_from_higher_platform = FirJvmErrors.INSTANCE.getINLINE_FROM_HIGHER_PLATFORM();
        JvmTarget.Companion companion = JvmTarget.INSTANCE;
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) inline_from_higher_platform, (Object) companion.getDescription(classVersion), (Object) companion.getDescription(majorVersion), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        boolean zIsInline = false;
        FirCallableSymbol<?> resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null || resolvedCallableSymbol$default.getOrigin().getFromSource()) {
            return;
        }
        if (resolvedCallableSymbol$default instanceof FirFunctionSymbol) {
            zIsInline = resolvedCallableSymbol$default.getRawStatus().isInline();
        } else if (resolvedCallableSymbol$default instanceof FirPropertySymbol) {
            FirPropertyAccessorSymbol setterSymbol = FirHelpersKt.isLhsOfAssignment(checkerContext, firQualifiedAccessExpression) ? ((FirPropertySymbol) resolvedCallableSymbol$default).getSetterSymbol() : ((FirPropertySymbol) resolvedCallableSymbol$default).getGetterSymbol();
            if (setterSymbol != null && setterSymbol.getRawStatus().isInline()) {
                zIsInline = true;
            }
        }
        if (zIsInline) {
            checkInlineTargetVersion(checkerContext, diagnosticReporter, resolvedCallableSymbol$default, firQualifiedAccessExpression);
        }
    }
}
