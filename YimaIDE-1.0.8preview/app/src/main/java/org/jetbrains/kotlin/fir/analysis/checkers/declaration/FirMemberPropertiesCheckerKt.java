package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aG\u0010\u0000\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\r\u001a/\u0010\u000e\u001a\u00020\u000b2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"checkProperty", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "containingDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "isDefinitelyAssigned", Argument.Delimiters.none, "reachable", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;ZZ)V", "shouldReportOpenInInterface", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMemberPropertiesCheckerKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkProperty(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirPropertySymbol firPropertySymbol, boolean z, boolean z2) throws KotlinIllegalArgumentExceptionWithAttachments {
        KtSourceElement source;
        FirPropertySymbol firPropertySymbol2;
        KtSourceElement source2;
        KtSourceElement source3;
        KtSourceElement source4;
        FirPropertyAccessorSymbol getterSymbol;
        KtSourceElement source5;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firPropertySymbol.getClass();
        if ((firPropertySymbol instanceof FirErrorPropertySymbol) || (source = firPropertySymbol.getSource()) == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            return;
        }
        FirModifierList modifierList = FirKeywordUtilsKt.getModifierList(firPropertySymbol.getSource());
        FirTopLevelPropertiesCheckerKt.checkPropertyInitializer(diagnosticReporter, checkerContext, firClass, firPropertySymbol, modifierList, z, z2);
        if (firClass != null) {
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ABSTRACT_KEYWORD;
            ktModifierKeywordToken.getClass();
            boolean zContains = FirKeywordUtilsKt.contains(modifierList, ktModifierKeywordToken);
            Modality modality = firPropertySymbol.getResolvedStatus().getModality();
            Modality modality2 = Modality.ABSTRACT;
            boolean z3 = modality == modality2 || zContains;
            ClassKind classKind = firClass.getClassKind();
            ClassKind classKind2 = ClassKind.INTERFACE;
            if (classKind == classKind2 && Visibilities.INSTANCE.isPrivate(firPropertySymbol.getResolvedStatus().getVisibility()) && !z3 && (((getterSymbol = firPropertySymbol.getGetterSymbol()) == null || getterSymbol.isDefault()) && (source5 = firPropertySymbol.getSource()) != null)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source5, FirErrors.INSTANCE.getPRIVATE_PROPERTY_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (z3) {
                if (firClass instanceof FirRegularClass) {
                    FirRegularClass firRegularClass = (FirRegularClass) firClass;
                    if (firRegularClass.getClassKind() != classKind2 && firRegularClass.getStatus().getModality() != modality2 && firRegularClass.getStatus().getModality() != Modality.SEALED && firRegularClass.getClassKind() != ClassKind.ENUM_CLASS && (source4 = firPropertySymbol.getSource()) != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source4, (KtDiagnosticFactory2) FirErrors.INSTANCE.getABSTRACT_PROPERTY_IN_NON_ABSTRACT_CLASS(), (Object) firPropertySymbol, (Object) firRegularClass.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        return;
                    }
                }
                firPropertySymbol2 = firPropertySymbol;
                KtSourceElement initializerSource = firPropertySymbol2.getInitializerSource();
                if (initializerSource != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializerSource, FirErrors.INSTANCE.getABSTRACT_PROPERTY_WITH_INITIALIZER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                FirExpression delegate = firPropertySymbol2.getDelegate();
                if (delegate != null && (source3 = delegate.getSource()) != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source3, FirErrors.INSTANCE.getABSTRACT_DELEGATED_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            } else {
                firPropertySymbol2 = firPropertySymbol;
            }
            KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.OPEN_KEYWORD;
            ktModifierKeywordToken2.getClass();
            if (FirKeywordUtilsKt.contains(modifierList, ktModifierKeywordToken2) && firClass.getClassKind() == classKind2 && shouldReportOpenInInterface(checkerContext, firPropertySymbol2, firClass.getSymbol()) && (source2 = firPropertySymbol2.getSource()) != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, FirErrors.INSTANCE.getREDUNDANT_OPEN_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean shouldReportOpenInInterface(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, FirClassSymbol<?> firClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        firCallableSymbol.getClass();
        firClassSymbol.getClass();
        FirModifierList modifierList = FirKeywordUtilsKt.getModifierList(firCallableSymbol.getSource());
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ABSTRACT_KEYWORD;
        ktModifierKeywordToken.getClass();
        return (FirKeywordUtilsKt.contains(modifierList, ktModifierKeywordToken) || firCallableSymbol.getResolvedStatus().getModality() != Modality.ABSTRACT || DeclarationUtilsKt.isInsideExpectClass(checkerContext, firClassSymbol)) ? false : true;
    }
}
