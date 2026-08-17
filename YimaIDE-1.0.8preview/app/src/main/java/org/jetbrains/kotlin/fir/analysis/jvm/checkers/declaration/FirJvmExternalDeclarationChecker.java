package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011JA\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmExternalDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkInternal", "reportSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/descriptors/Modality;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmExternalDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJvmExternalDeclarationChecker INSTANCE = new FirJvmExternalDeclarationChecker();

    private FirJvmExternalDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkInternal(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement, Modality modality) {
        KtSourceElement source;
        String str;
        FirProperty firProperty;
        FirPropertyAccessor getter;
        if (firDeclaration instanceof FirMemberDeclaration) {
            if ((firDeclaration instanceof FirProperty) && (getter = (firProperty = (FirProperty) firDeclaration).getGetter()) != null) {
                INSTANCE.checkInternal(checkerContext, diagnosticReporter, getter, firProperty.getSource(), ((FirMemberDeclaration) firDeclaration).getStatus().getModality());
            }
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            if (!firMemberDeclaration.getStatus().isExternal() || (source = firMemberDeclaration.getSource()) == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
                return;
            }
            if (!(firDeclaration instanceof FirFunction)) {
                if (firMemberDeclaration instanceof FirProperty) {
                    str = "property";
                } else {
                    str = firMemberDeclaration instanceof FirRegularClass ? "class" : "non-function declaration";
                }
                String str2 = str;
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.EXTERNAL_KEYWORD;
                ktModifierKeywordToken.getClass();
                FirModifier<?> modifier = FirKeywordUtilsKt.getModifier(firDeclaration, ktModifierKeywordToken);
                if (modifier != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) modifier.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_MODIFIER_TARGET(), (Object) modifier.getToken(), (Object) str2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    return;
                }
                return;
            }
            FirFunction firFunction = (FirFunction) firDeclaration;
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firFunction.getSymbol());
            FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag) : null;
            if (regularClassSymbol != null) {
                if (regularClassSymbol.getClassKind() == ClassKind.INTERFACE) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirJvmErrors.INSTANCE.getEXTERNAL_DECLARATION_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else {
                    if ((modality == null ? firMemberDeclaration.getStatus().getModality() : modality) == Modality.ABSTRACT) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (ktSourceElement == null ? firFunction.getSource() : ktSourceElement), FirJvmErrors.INSTANCE.getEXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
            if (!(firDeclaration instanceof FirConstructor) && firFunction.getBody() != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirJvmErrors.INSTANCE.getEXTERNAL_DECLARATION_CANNOT_HAVE_BODY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firMemberDeclaration.getStatus().isInline()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirJvmErrors.INSTANCE.getEXTERNAL_DECLARATION_CANNOT_BE_INLINED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirPropertyAccessor) {
            return;
        }
        checkInternal(checkerContext, diagnosticReporter, firDeclaration, null, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
