package org.jetbrains.kotlin.fir.analysis.checkers.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.CliFrontendDiagnostics;
import org.jetbrains.kotlin.fir.declarations.DeprecationsPerUseSite;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.ImportUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PackageResolutionResult;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ)\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirOptInLanguageVersionSettingsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirLanguageVersionSettingsChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "checkOptInMarkerArgument", "fqNameAsString", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInLanguageVersionSettingsChecker extends FirLanguageVersionSettingsChecker {
    public static final FirOptInLanguageVersionSettingsChecker INSTANCE = new FirOptInLanguageVersionSettingsChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeprecationLevelValue.values().length];
            try {
                iArr[DeprecationLevelValue.WARNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirOptInLanguageVersionSettingsChecker() {
    }

    private final void checkOptInMarkerArgument(CheckerContext checkerContext, String str, DiagnosticReporter diagnosticReporter) {
        FirDeprecationInfo all;
        PackageResolutionResult packageResolutionResultResolveToPackageOrClass = ImportUtilsKt.resolveToPackageOrClass(FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()), new FqName(str));
        String str2 = null;
        PackageResolutionResult.PackageOrClass packageOrClass = packageResolutionResultResolveToPackageOrClass instanceof PackageResolutionResult.PackageOrClass ? (PackageResolutionResult.PackageOrClass) packageResolutionResultResolveToPackageOrClass : null;
        FirClassLikeSymbol<?> classSymbol = packageOrClass != null ? packageOrClass.getClassSymbol() : null;
        if (classSymbol == null) {
            KtDiagnosticReportHelpersKt.report$default(checkerContext, diagnosticReporter, CliFrontendDiagnostics.INSTANCE.getOPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED(), "Opt-in requirement marker '" + str + "' is unresolved. Make sure it's present in the module dependencies.", null, 8, null);
            return;
        }
        if (!FirAnnotationUtilsKt.hasAnnotationWithClassId(classSymbol, OptInNames.INSTANCE.getREQUIRES_OPT_IN_CLASS_ID(), checkerContext.getSession())) {
            KtDiagnosticReportHelpersKt.report$default(checkerContext, diagnosticReporter, CliFrontendDiagnostics.INSTANCE.getNOT_AN_OPT_IN_REQUIREMENT_MARKER(), "Class '" + str + "' is not an opt-in requirement marker.", null, 8, null);
            return;
        }
        DeprecationsPerUseSite ownDeprecation = classSymbol.getOwnDeprecation(checkerContext.getLanguageVersionSettings());
        if (ownDeprecation == null || (all = ownDeprecation.getAll()) == null) {
            return;
        }
        KtSourcelessDiagnosticFactory opt_in_requirement_marker_is_deprecated = WhenMappings.$EnumSwitchMapping$0[all.getDeprecationLevel().ordinal()] == 1 ? CliFrontendDiagnostics.INSTANCE.getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED() : CliFrontendDiagnostics.INSTANCE.getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR();
        StringBuilder sb = new StringBuilder("Opt-in requirement marker '");
        sb.append(str);
        sb.append("' is deprecated");
        String message = all.getMessage(checkerContext.getSession());
        if (message != null) {
            str2 = " (" + message + ')';
        }
        if (str2 == null) {
            str2 = Argument.Delimiters.none;
        }
        sb.append(str2);
        sb.append('.');
        KtDiagnosticReportHelpersKt.report$default(checkerContext, diagnosticReporter, opt_in_requirement_marker_is_deprecated, sb.toString(), null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.config.FirLanguageVersionSettingsChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        for (String str : (Iterable) checkerContext.getLanguageVersionSettings().getFlag(AnalysisFlags.getOptIn())) {
            if (!Intrinsics.areEqual(str, OptInNames.INSTANCE.getREQUIRES_OPT_IN_FQ_NAME().asString())) {
                INSTANCE.checkOptInMarkerArgument(checkerContext, str, diagnosticReporter);
            }
        }
    }
}
