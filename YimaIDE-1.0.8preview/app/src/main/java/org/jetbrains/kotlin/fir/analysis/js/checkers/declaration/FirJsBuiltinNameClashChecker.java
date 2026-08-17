package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsStableName;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0002H\u0016R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0017\u001a\u00020\f*\b\u0012\u0002\b\u0003\u0018\u00010\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsBuiltinNameClashChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "PROHIBITED_STATIC_NAMES_FOR_INTERFACES", Argument.Delimiters.none, Argument.Delimiters.none, "PROHIBITED_MEMBER_NAMES", "PROHIBITED_STATIC_NAMES", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "isInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Z", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsBuiltinNameClashChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsBuiltinNameClashChecker INSTANCE = new FirJsBuiltinNameClashChecker();
    private static final Set<String> PROHIBITED_MEMBER_NAMES;
    private static final Set<String> PROHIBITED_STATIC_NAMES;
    private static final Set<String> PROHIBITED_STATIC_NAMES_FOR_INTERFACES;

    static {
        String identifier = StandardNames.DEFAULT_IMPLS_CLASS_NAME.getIdentifier();
        identifier.getClass();
        PROHIBITED_STATIC_NAMES_FOR_INTERFACES = SetsKt.setOf(new String[]{"Symbol", identifier});
        PROHIBITED_MEMBER_NAMES = SetsKt.setOf("constructor");
        PROHIBITED_STATIC_NAMES = SetsKt.setOf(new String[]{"prototype", "length", "$metadata$"});
    }

    private FirJsBuiltinNameClashChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isInterface(FirClassLikeSymbol<?> firClassLikeSymbol) {
        return (firClassLikeSymbol != null ? FirHelpersKt.getClassKind(firClassLikeSymbol) : null) == ClassKind.INTERFACE;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirClassLikeSymbol<?> containingClassSymbol;
        FirJsStableName firJsStableNameCreateStableNameOrNull;
        String name;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (FirWebCommonHelpersKt.isNativeObject(firDeclaration.getSymbol(), checkerContext.getSession()) || (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration)) == null || (firJsStableNameCreateStableNameOrNull = FirJsStableName.INSTANCE.createStableNameOrNull(checkerContext, firDeclaration.getSymbol())) == null || (name = firJsStableNameCreateStableNameOrNull.getName()) == null) {
            return;
        }
        if (firDeclaration instanceof FirClassLikeDeclaration) {
            if (PROHIBITED_STATIC_NAMES.contains(name) || (isInterface(containingClassSymbol) && PROHIBITED_STATIC_NAMES_FOR_INTERFACES.contains(name))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirClassLikeDeclaration) firDeclaration).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getJS_BUILTIN_NAME_CLASH(), (Object) "Function.".concat(name), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
            return;
        }
        if (!FirAnnotationUtilsKt.hasAnnotation(firDeclaration, JsStandardClassIds.Annotations.JsStatic, checkerContext.getSession())) {
            if ((firDeclaration instanceof FirCallableDeclaration) && PROHIBITED_MEMBER_NAMES.contains(name)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirCallableDeclaration) firDeclaration).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getJS_BUILTIN_NAME_CLASH(), (Object) "Object.prototype.".concat(name), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
            return;
        }
        if (PROHIBITED_STATIC_NAMES.contains(name) || (containingClassSymbol.getRawStatus().isCompanion() && isInterface(ContainingClassUtilsKt.getContainingClassSymbol(containingClassSymbol)) && PROHIBITED_STATIC_NAMES_FOR_INTERFACES.contains(name))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getJS_BUILTIN_NAME_CLASH(), (Object) "Function.".concat(name), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
