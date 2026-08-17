package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u001cB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u0013H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u0010*\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\u0010H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0018J\u001f\u0010\u0019\u001a\u00020\u0010*\u0004\u0018\u00010\u001aH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUnsupportedArrayLiteralChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCollectionLiteralChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;)V", "reportUnsupported", "forceError", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;Z)V", "containingCallKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUnsupportedArrayLiteralChecker$ContainingCallKind;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUnsupportedArrayLiteralChecker$ContainingCallKind;", "isInDefinitelyFailingPosition", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;)Z", "isInsideAnnotationConstructor", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "isAnnotationClass", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "ContainingCallKind", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnsupportedArrayLiteralChecker extends FirExpressionChecker<FirCollectionLiteral> {
    public static final FirUnsupportedArrayLiteralChecker INSTANCE = new FirUnsupportedArrayLiteralChecker();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUnsupportedArrayLiteralChecker$ContainingCallKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "NotFound", "FunctionReturningAnnotation", "Annotation", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ContainingCallKind {
        NotFound,
        FunctionReturningAnnotation,
        Annotation;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ContainingCallKind> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContainingCallKind.values().length];
            try {
                iArr[ContainingCallKind.Annotation.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ContainingCallKind.FunctionReturningAnnotation.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ContainingCallKind.NotFound.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirUnsupportedArrayLiteralChecker() {
        super(MppCheckerKind.Common);
    }

    private final ContainingCallKind containingCallKind(CheckerContext checkerContext) {
        ContainingCallKind containingCallKind = ContainingCallKind.NotFound;
        for (FirStatement firStatement : CollectionsKt.asReversed(checkerContext.getCallsOrAssignments())) {
            if (firStatement instanceof FirAnnotationCall) {
                return ContainingCallKind.Annotation;
            }
            if ((firStatement instanceof FirFunctionCall) && isAnnotationClass(checkerContext, FirTypeUtilsKt.getResolvedType((FirExpression) firStatement))) {
                containingCallKind = ContainingCallKind.FunctionReturningAnnotation;
            }
        }
        return containingCallKind;
    }

    private final boolean isAnnotationClass(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        FirRegularClassSymbol regularClassSymbol;
        return ((coneKotlinType == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinType)) == null) ? null : regularClassSymbol.getClassKind()) == ClassKind.ANNOTATION_CLASS;
    }

    private final boolean isInDefinitelyFailingPosition(CheckerContext checkerContext, FirCollectionLiteral firCollectionLiteral) {
        FirElement secondToLastContainer = FirHelpersKt.getSecondToLastContainer(checkerContext);
        FirBlock firBlock = secondToLastContainer instanceof FirBlock ? (FirBlock) secondToLastContainer : null;
        if (firBlock == null) {
            return false;
        }
        if (FirHelpersKt.nthLastContainer(checkerContext, 3) instanceof FirAnonymousFunction) {
            return firBlock.getIsUnitCoerced() || UtilsKt.getLastExpression(firBlock) != firCollectionLiteral;
        }
        return !(firBlock instanceof FirSingleExpressionBlock);
    }

    private final boolean isInsideAnnotationConstructor(CheckerContext checkerContext) {
        FirConstructorSymbol firConstructorSymbol;
        Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
        do {
            if (!it.hasNext()) {
                firConstructorSymbol = null;
                break;
            }
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) it.next();
            if (!(firBasedSymbol instanceof FirConstructorSymbol)) {
                firBasedSymbol = null;
            }
            firConstructorSymbol = (FirConstructorSymbol) firBasedSymbol;
        } while (firConstructorSymbol == null);
        return isAnnotationClass(checkerContext, firConstructorSymbol != null ? firConstructorSymbol.getResolvedReturnType() : null);
    }

    private final void reportUnsupported(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCollectionLiteral firCollectionLiteral, boolean z) {
        CheckerContext checkerContext2;
        if (z) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCollectionLiteral.getSource(), FirErrors.INSTANCE.getUNSUPPORTED_ARRAY_LITERAL_OUTSIDE_OF_ANNOTATION().getErrorFactory(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCollectionLiteral.getSource(), FirErrors.INSTANCE.getUNSUPPORTED_ARRAY_LITERAL_OUTSIDE_OF_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (z) {
            checkerContext2 = checkerContext;
        } else {
            checkerContext2 = checkerContext;
            if (!LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.ForbidArrayLiteralsInNonAnnotationContexts)) {
                return;
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firCollectionLiteral.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(LanguageFeature.CollectionLiterals, checkerContext2.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCollectionLiteral firCollectionLiteral) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCollectionLiteral.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.CollectionLiterals) || isInsideAnnotationConstructor(checkerContext)) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[containingCallKind(checkerContext).ordinal()];
        if (i != 1) {
            if (i == 2) {
                reportUnsupported(checkerContext, diagnosticReporter, firCollectionLiteral, isInDefinitelyFailingPosition(checkerContext, firCollectionLiteral));
            } else if (i == 3) {
                reportUnsupported(checkerContext, diagnosticReporter, firCollectionLiteral, true);
            } else {
                bu8.a();
            }
        }
    }
}
