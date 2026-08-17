package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirConflictsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMemberPropertiesCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirDeclarationSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J5\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0013R\u0018\u0010\u000b\u001a\u00020\u0007*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0014\u001a\u00020\u0007*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/RedundantModalityModifierSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isMemberWithRealSource", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "checkPsiOrLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "declaresOpenModality", "getDeclaresOpenModality", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantModalityModifierSyntaxChecker extends FirDeclarationSyntaxChecker<FirDeclaration, KtDeclaration> {
    public static final RedundantModalityModifierSyntaxChecker INSTANCE = new RedundantModalityModifierSyntaxChecker();

    private RedundantModalityModifierSyntaxChecker() {
    }

    private final boolean getDeclaresOpenModality(FirDeclaration firDeclaration) {
        FirModifierList modifierList;
        List<FirModifier<?>> modifiers;
        KtSourceElement source = firDeclaration.getSource();
        if (source != null && (modifierList = FirKeywordUtilsKt.getModifierList(source)) != null && (modifiers = modifierList.getModifiers()) != null) {
            List<FirModifier<?>> list = modifiers;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((FirModifier) it.next()).getToken(), KtTokens.OPEN_KEYWORD)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final boolean isMemberWithRealSource(FirDeclaration firDeclaration) {
        if (!(firDeclaration instanceof FirMemberDeclaration)) {
            return false;
        }
        KtSourceElement source = ((FirMemberDeclaration) firDeclaration).getSource();
        return (source != null ? source.getKind() : null) instanceof KtRealSourceElementKind;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0063  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        boolean z;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        ktSourceElement.getClass();
        if (!(firDeclaration instanceof FirMemberDeclaration)) {
            w01.a("Failed requirement.");
            return;
        }
        FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
        Modality modality = firMemberDeclaration.getStatus().getModality();
        if (modality == null) {
            return;
        }
        FirResolvedDeclarationStatus resolvedStatus = FirConflictsHelpersKt.getResolvedStatus(firMemberDeclaration.getSymbol());
        resolvedStatus.getClass();
        Modality defaultModality = resolvedStatus.getDefaultModality();
        Object objLast = CollectionsKt.last(checkerContext.getContainingDeclarations());
        FirClassSymbol firClassSymbol = objLast instanceof FirClassSymbol ? (FirClassSymbol) objLast : null;
        if (getDeclaresOpenModality(firDeclaration)) {
            if ((firClassSymbol != null ? firClassSymbol.getClassKind() : null) == ClassKind.INTERFACE && (firDeclaration instanceof FirCallableDeclaration) && FirMemberPropertiesCheckerKt.shouldReportOpenInInterface(checkerContext, ((FirCallableDeclaration) firDeclaration).getSymbol(), firClassSymbol)) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (modality == defaultModality) {
            if ((firClassSymbol != null ? firClassSymbol.getClassKind() : null) == ClassKind.INTERFACE) {
                return;
            }
        }
        if (z || LightTreePositioningStrategiesKt.modalityModifier(ktSourceElement.getTreeStructure(), ktSourceElement.getLighterASTNode()) == null || !FirHelpersKt.redundantModalities(checkerContext, firMemberDeclaration, defaultModality).contains(modality)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getREDUNDANT_MODALITY_MODIFIER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirDeclaration element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        if (isMemberWithRealSource(element) && !(element instanceof FirValueParameter)) {
            return true;
        }
        KtSourceElement source2 = element.getSource();
        return Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE);
    }
}
