package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOpenMemberChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOpenMemberChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "shouldReportOpenFromSource", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "getShouldReportOpenFromSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOpenMemberChecker extends FirDeclarationChecker<FirClass> {
    public static final FirOpenMemberChecker INSTANCE = new FirOpenMemberChecker();

    private FirOpenMemberChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0044  */
    /* JADX WARN: Code duplicated, block: B:22:0x004c  */
    /* JADX WARN: Code duplicated, block: B:23:0x005c  */
    public static Unit b(FirClass firClass, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol firCallableSymbol) {
        KtSourceElement source;
        firCallableSymbol.getClass();
        if (!(firCallableSymbol instanceof FirConstructorSymbol) && (source = firCallableSymbol.getSource()) != null) {
            if (firCallableSymbol.getResolvedStatus().getModality() != Modality.OPEN || firCallableSymbol.getResolvedStatus().isOverride() || firClass.getClassKind() != ClassKind.ANNOTATION_CLASS) {
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.OPEN_KEYWORD;
                ktModifierKeywordToken.getClass();
                if (FirKeywordUtilsKt.hasModifier(firCallableSymbol, ktModifierKeywordToken) && INSTANCE.getShouldReportOpenFromSource(source)) {
                    if (firClass.getClassKind() == ClassKind.OBJECT) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNON_FINAL_MEMBER_IN_OBJECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    } else {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNON_FINAL_MEMBER_IN_FINAL_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            } else if (firClass.getClassKind() == ClassKind.OBJECT) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNON_FINAL_MEMBER_IN_OBJECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNON_FINAL_MEMBER_IN_FINAL_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final boolean getShouldReportOpenFromSource(KtSourceElement ktSourceElement) {
        KtSourceElementKind kind = ktSourceElement.getKind();
        return Intrinsics.areEqual(kind, KtRealSourceElementKind.INSTANCE) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if (DeclarationUtilsKt.getCanHaveOpenMembers(firClass)) {
            return;
        }
        org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclaredCallables$default(firClass.getSymbol(), checkerContext.getSession(), null, new Function1() { // from class: lb5
            public final Object invoke(Object obj) {
                return FirOpenMemberChecker.b(firClass, checkerContext, diagnosticReporter, (FirCallableSymbol) obj);
            }
        }, 2, null);
    }
}
