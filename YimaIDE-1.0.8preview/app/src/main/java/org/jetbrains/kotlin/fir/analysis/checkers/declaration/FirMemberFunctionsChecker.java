package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMemberFunctionsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "checkFunction", "containingDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "function", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMemberFunctionsChecker extends FirDeclarationChecker<FirNamedFunction> {
    public static final FirMemberFunctionsChecker INSTANCE = new FirMemberFunctionsChecker();

    private FirMemberFunctionsChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:25:0x0068  */
    /* JADX WARN: Multi-variable type inference failed */
    private final void checkFunction(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClassSymbol<?> firClassSymbol, FirNamedFunction firNamedFunction) throws KotlinIllegalArgumentExceptionWithAttachments {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirClassSymbol<?> firClassSymbol2;
        KtSourceElement source = firNamedFunction.getSource();
        if (source == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            return;
        }
        FirNamedFunctionSymbol symbol = firNamedFunction.getSymbol();
        FirModifierList modifierList = FirKeywordUtilsKt.getModifierList(source);
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ABSTRACT_KEYWORD;
        ktModifierKeywordToken.getClass();
        boolean zContains = FirKeywordUtilsKt.contains(modifierList, ktModifierKeywordToken);
        Modality modality = firNamedFunction.getStatus().getModality();
        Modality modality2 = Modality.ABSTRACT;
        if (modality == modality2 || zContains) {
            if (firClassSymbol instanceof FirRegularClassSymbol) {
                FirRegularClass firRegularClass = (FirRegularClass) ((FirRegularClassSymbol) firClassSymbol).getFir();
                if (firRegularClass.getClassKind() == ClassKind.INTERFACE || firRegularClass.getStatus().getModality() == modality2 || firRegularClass.getStatus().getModality() == Modality.SEALED || firRegularClass.getClassKind() == ClassKind.ENUM_CLASS) {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    firClassSymbol2 = firClassSymbol;
                } else {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) FirErrors.INSTANCE.getABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS(), (Object) symbol, (Object) firClassSymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    firClassSymbol2 = firClassSymbol;
                }
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                firClassSymbol2 = firClassSymbol;
            }
            if (firNamedFunction.getBody() != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getABSTRACT_FUNCTION_WITH_BODY(), (Object) symbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        } else {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            firClassSymbol2 = firClassSymbol;
        }
        boolean zIsInsideExpectClass = DeclarationUtilsKt.isInsideExpectClass(checkerContext2, firClassSymbol2);
        boolean zIsInsideExternalClass = DeclarationUtilsKt.isInsideExternalClass(checkerContext2, firClassSymbol2);
        KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.OPEN_KEYWORD;
        ktModifierKeywordToken2.getClass();
        boolean zContains2 = FirKeywordUtilsKt.contains(modifierList, ktModifierKeywordToken2);
        if (firNamedFunction.getBody() != null) {
            return;
        }
        if (firClassSymbol2.getClassKind() == ClassKind.INTERFACE) {
            if (Visibilities.INSTANCE.isPrivate(firNamedFunction.getStatus().getVisibility())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getPRIVATE_FUNCTION_WITH_NO_BODY(), (Object) symbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (zContains2 && FirMemberPropertiesCheckerKt.shouldReportOpenInInterface(checkerContext2, firNamedFunction.getSymbol(), firClassSymbol2)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getREDUNDANT_OPEN_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            return;
        }
        if (zIsInsideExpectClass || zContains || firNamedFunction.getStatus().isExternal() || zIsInsideExternalClass) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getNON_ABSTRACT_FUNCTION_WITH_NO_BODY(), (Object) symbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firNamedFunction.getClass();
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        Object obj = null;
        if (!(containingDeclarations instanceof List)) {
            for (Object obj2 : CollectionsKt.reversed(containingDeclarations)) {
                if (obj2 instanceof FirClassSymbol) {
                    obj = obj2;
                    break;
                }
            }
        } else {
            int size = containingDeclarations.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    obj2 = containingDeclarations.get(size);
                    if (obj2 instanceof FirClassSymbol) {
                        obj = obj2;
                        break;
                    } else if (i < 0) {
                        break;
                    } else {
                        size = i;
                    }
                }
            }
        }
        FirClassSymbol<?> firClassSymbol = (FirClassSymbol) obj;
        if (firClassSymbol == null) {
            return;
        }
        checkFunction(checkerContext, diagnosticReporter, firClassSymbol, firNamedFunction);
    }
}
