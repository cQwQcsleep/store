package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.resolve.Compatibility;
import org.jetbrains.kotlin.resolve.ModifierCheckerHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aY\u0010\u0000\u001a\u00020\u00012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00072\u0010\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\r\u001aG\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0010\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\nH\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0011\u001a+\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013R\u00020\u0004R\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"checkCompatibilityType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "firstModifier", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;", "secondModifier", "reportedNodes", Argument.Delimiters.none, "owner", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/FirElement;)V", "checkModifiersCompatibility", "modifierList", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;Ljava/util/Set;)V", "typeArgument", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;)V", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModifiersCompatibilityUtilsKt {
    public static final void checkCompatibilityType(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirModifier<?> firModifier, FirModifier<?> firModifier2, Set<FirModifier<?>> set, FirElement firElement) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        firModifier.getClass();
        firModifier2.getClass();
        set.getClass();
        KtModifierKeywordToken token = firModifier.getToken();
        KtModifierKeywordToken token2 = firModifier2.getToken();
        Compatibility compatibility = ModifierCheckerHelpersKt.compatibility(token, token2);
        switch (WhenMappings.$EnumSwitchMapping$0[compatibility.ordinal()]) {
            case 1:
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                if (set.add(firModifier2)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getREPEATED_MODIFIER(), (Object) token2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                break;
            case 3:
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier2.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getREDUNDANT_MODIFIER(), (Object) token2, (Object) token, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                break;
            case 4:
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getREDUNDANT_MODIFIER(), (Object) token, (Object) token2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                break;
            case 5:
                KtSourceElement source = firModifier.getSource();
                FirErrors firErrors = FirErrors.INSTANCE;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) firErrors.getDEPRECATED_MODIFIER_PAIR(), (Object) token, (Object) token2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier2.getSource(), (KtDiagnosticFactory2) firErrors.getDEPRECATED_MODIFIER_PAIR(), (Object) token2, (Object) token, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                break;
            case 6:
            case 7:
                if (compatibility != Compatibility.COMPATIBLE_FOR_CLASSES_ONLY || !(firElement instanceof FirClass)) {
                    if (set.add(firModifier)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getINCOMPATIBLE_MODIFIERS(), (Object) token, (Object) token2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        token = token;
                        token2 = token2;
                    }
                    if (set.add(firModifier2)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier2.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getINCOMPATIBLE_MODIFIERS(), (Object) token2, (Object) token, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    }
                }
                break;
            default:
                bu8.a();
                break;
        }
    }

    private static final void checkModifiersCompatibility(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirElement firElement, FirModifierList firModifierList, Set<FirModifier<?>> set) {
        List<FirModifier<?>> modifiers = firModifierList.getModifiers();
        Iterator<T> it = modifiers.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            FirModifier firModifier = (FirModifier) it.next();
            for (int i3 = 0; i3 < i; i3++) {
                checkCompatibilityType(diagnosticReporter, checkerContext, modifiers.get(i3), firModifier, set, firElement);
            }
            i = i2;
        }
    }

    public static final void checkModifiersCompatibility(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeProjection firTypeProjection) {
        FirModifierList modifierList;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeProjection.getClass();
        KtSourceElement source = firTypeProjection.getSource();
        if (source != null) {
            if (!(source.getKind() instanceof KtRealSourceElementKind)) {
                source = null;
            }
            if (source == null || (modifierList = FirKeywordUtilsKt.getModifierList(source)) == null) {
                return;
            }
            checkModifiersCompatibility(diagnosticReporter, checkerContext, firTypeProjection, modifierList, new HashSet());
        }
    }
}
