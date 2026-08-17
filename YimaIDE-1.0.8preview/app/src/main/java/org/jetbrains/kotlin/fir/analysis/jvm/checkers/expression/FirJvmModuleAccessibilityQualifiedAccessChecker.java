package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtVirtualFileSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.java.VirtualFileBasedSourceElement;
import org.jetbrains.kotlin.fir.modules.FirJavaModuleResolverProviderKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinarySourceElement;
import org.jetbrains.kotlin.load.kotlin.VirtualFileKotlinClass;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ;\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0013\u0010\u0014J?\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0012H\u0000R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmModuleAccessibilityQualifiedAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkClassAccess", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "checkClassAccess$org_jetbrains_kotlin_checkers_jvm", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/FirElement;)V", "checkPackageAccess", "fileFromPackage", "Lcom/intellij/openapi/vfs/VirtualFile;", "referencedPackageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "checkPackageAccess$org_jetbrains_kotlin_checkers_jvm", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lcom/intellij/openapi/vfs/VirtualFile;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/FirElement;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmModuleAccessibilityQualifiedAccessChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirJvmModuleAccessibilityQualifiedAccessChecker INSTANCE = new FirJvmModuleAccessibilityQualifiedAccessChecker();

    private FirJvmModuleAccessibilityQualifiedAccessChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        VirtualFile file;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null || resolvedCallableSymbol$default.getOrigin().getFromSource()) {
            return;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) resolvedCallableSymbol$default);
        if (coneClassLikeLookupTagContainingClassLookupTag != null) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag);
            if (regularClassSymbol == null) {
                return;
            }
            checkClassAccess$org_jetbrains_kotlin_checkers_jvm(checkerContext, diagnosticReporter, regularClassSymbol, firQualifiedAccessExpression);
            return;
        }
        JvmPackagePartSource containerSource = resolvedCallableSymbol$default.getContainerSource();
        JvmPackagePartSource jvmPackagePartSource = containerSource instanceof JvmPackagePartSource ? containerSource : null;
        if (jvmPackagePartSource == null) {
            return;
        }
        KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource.getKnownJvmBinaryClass();
        VirtualFileKotlinClass virtualFileKotlinClass = knownJvmBinaryClass instanceof VirtualFileKotlinClass ? (VirtualFileKotlinClass) knownJvmBinaryClass : null;
        if (virtualFileKotlinClass == null || (file = virtualFileKotlinClass.getFile()) == null) {
            return;
        }
        FqName packageFqName = jvmPackagePartSource.getClassName().getPackageFqName();
        packageFqName.getClass();
        checkPackageAccess$org_jetbrains_kotlin_checkers_jvm(checkerContext, diagnosticReporter, file, packageFqName, firQualifiedAccessExpression);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void checkClassAccess$org_jetbrains_kotlin_checkers_jvm(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClassSymbol<?> firClassSymbol, FirElement firElement) {
        VirtualFile file;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClassSymbol.getClass();
        firElement.getClass();
        if (firClassSymbol.getOrigin().getFromSource()) {
            return;
        }
        KotlinJvmBinarySourceElement sourceElement = DeclarationAttributesKt.getSourceElement((FirClassLikeDeclaration) firClassSymbol.getFir());
        if (sourceElement instanceof VirtualFileBasedSourceElement) {
            file = ((VirtualFileBasedSourceElement) sourceElement).getVirtualFile();
        } else {
            if (!(sourceElement instanceof KotlinJvmBinarySourceElement)) {
                return;
            }
            VirtualFileKotlinClass binaryClass = sourceElement.getBinaryClass();
            VirtualFileKotlinClass virtualFileKotlinClass = binaryClass instanceof VirtualFileKotlinClass ? binaryClass : null;
            if (virtualFileKotlinClass == null || (file = virtualFileKotlinClass.getFile()) == null) {
                return;
            }
        }
        checkPackageAccess$org_jetbrains_kotlin_checkers_jvm(checkerContext, diagnosticReporter, file, UtilsKt.packageFqName(firClassSymbol), firElement);
    }

    public final void checkPackageAccess$org_jetbrains_kotlin_checkers_jvm(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, VirtualFile virtualFile, FqName fqName, FirElement firElement) {
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        virtualFile.getClass();
        fqName.getClass();
        firElement.getClass();
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        KtSourceFile sourceFile = containingFileSymbol != null ? containingFileSymbol.getSourceFile() : null;
        KtVirtualFileSourceFile ktVirtualFileSourceFile = sourceFile instanceof KtVirtualFileSourceFile ? (KtVirtualFileSourceFile) sourceFile : null;
        JavaModuleResolver.AccessError.ModuleDoesNotExportPackage moduleDoesNotExportPackageCheckAccessibility = FirJavaModuleResolverProviderKt.getJavaModuleResolverProvider(checkerContext.getSession()).getJavaModuleResolver().checkAccessibility(ktVirtualFileSourceFile != null ? ktVirtualFileSourceFile.getVirtualFile() : null, virtualFile, fqName);
        if (moduleDoesNotExportPackageCheckAccessibility == null) {
            return;
        }
        FirReference reference = ReferenceUtilsKt.toReference(firElement, checkerContext.getSession());
        if (reference == null || (source = reference.getSource()) == null) {
            source = firElement.getSource();
        }
        KtSourceElement ktSourceElement = source;
        if (moduleDoesNotExportPackageCheckAccessibility instanceof JavaModuleResolver.AccessError.ModuleDoesNotExportPackage) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getJAVA_MODULE_DOES_NOT_EXPORT_PACKAGE(), (Object) moduleDoesNotExportPackageCheckAccessibility.getDependencyModuleName(), (Object) fqName.asString(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        if (moduleDoesNotExportPackageCheckAccessibility instanceof JavaModuleResolver.AccessError.ModuleDoesNotReadModule) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getJAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE(), (Object) ((JavaModuleResolver.AccessError.ModuleDoesNotReadModule) moduleDoesNotExportPackageCheckAccessibility).getDependencyModuleName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else if (Intrinsics.areEqual(moduleDoesNotExportPackageCheckAccessibility, JavaModuleResolver.AccessError.ModuleDoesNotReadUnnamedModule.INSTANCE)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getJAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            bu8.a();
        }
    }
}
