package org.jetbrains.kotlin.fir.analysis.checkers.type;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJG\u0010\u000e\u001a\u00020\u0007*\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u0010H\u0082\bR\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeArgumentsInPackagesTypeRefChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "forEachPackagePart", "block", "Lkotlin/Function2;", Argument.Delimiters.none, "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "isLastPackagePart", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Lkotlin/jvm/functions/Function2;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeArgumentsInPackagesTypeRefChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final TypeArgumentsInPackagesTypeRefChecker INSTANCE = new TypeArgumentsInPackagesTypeRefChecker();

    private TypeArgumentsInPackagesTypeRefChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x007d  */
    /* JADX WARN: Code duplicated, block: B:32:0x0085  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) {
        CheckerContext checkerContext2;
        boolean z;
        KtDiagnosticFactory1<String> type_arguments_not_allowed_warning;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        FirUserTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
        FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? delegatedTypeRef : null;
        if (firUserTypeRef == null) {
            return;
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(firResolvedTypeRef.getConeType()), checkerContext.getSession());
        FirClassLikeSymbol<FirClassLikeDeclaration> containingDeclaration = symbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) symbol : null;
        if (containingDeclaration == null) {
            return;
        }
        boolean z2 = false;
        for (FirQualifierPart firQualifierPart : CollectionsKt.asReversed(firUserTypeRef.getQualifier())) {
            if (containingDeclaration == null) {
                if (!firQualifierPart.getTypeArgumentList().getTypeArguments().isEmpty()) {
                    if (z2) {
                        checkerContext2 = checkerContext;
                        if (!LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.ForbidUselessTypeArgumentsIn25)) {
                            z = false;
                        }
                        KtSourceElement source = firQualifierPart.getTypeArgumentList().getSource();
                        if (z) {
                            type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED();
                        } else {
                            type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED_WARNING();
                        }
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) type_arguments_not_allowed_warning, (Object) "for packages", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else {
                        checkerContext2 = checkerContext;
                    }
                    z = true;
                    KtSourceElement source2 = firQualifierPart.getTypeArgumentList().getSource();
                    if (z) {
                        type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED();
                    } else {
                        type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED_WARNING();
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) source2, (KtDiagnosticFactory1) type_arguments_not_allowed_warning, (Object) "for packages", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                z2 = true;
            }
            containingDeclaration = containingDeclaration != null ? DeclarationUtilsKt.getContainingDeclaration(containingDeclaration, checkerContext.getSession()) : null;
        }
    }
}
