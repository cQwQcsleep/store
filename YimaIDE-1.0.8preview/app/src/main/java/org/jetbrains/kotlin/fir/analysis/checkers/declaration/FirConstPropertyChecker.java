package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.ConstantArgumentKind;
import org.jetbrains.kotlin.fir.expressions.FirConstChecksKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstPropertyChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstPropertyChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirConstPropertyChecker INSTANCE = new FirConstPropertyChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConstantArgumentKind.values().length];
            try {
                iArr[ConstantArgumentKind.VALID_CONST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConstantArgumentKind.RESOLUTION_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConstantArgumentKind.NOT_CONST_VAL_IN_CONST_EXPRESSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirConstPropertyChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirProperty firProperty2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        if (firProperty.getStatus().isConst()) {
            if (firProperty.getIsVar()) {
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.CONST_KEYWORD;
                ktModifierKeywordToken.getClass();
                firProperty2 = firProperty;
                FirModifier<?> modifier = FirKeywordUtilsKt.getModifier(firProperty2, ktModifierKeywordToken);
                if (modifier != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) modifier.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_MODIFIER_TARGET(), (Object) modifier.getToken(), (Object) "vars", (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
            } else {
                firProperty2 = firProperty;
            }
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirRegularClassSymbol firRegularClassSymbol = objLastOrNull instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) objLastOrNull : null;
            if ((firRegularClassSymbol != null ? firRegularClassSymbol.getClassKind() : null) != ClassKind.OBJECT && checkerContext.getContainingDeclarations().size() > 1 && !FirStatusUtilsKt.isCompanionBlockMember(firProperty2)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty2.getSource(), FirErrors.INSTANCE.getCONST_VAL_NOT_TOP_LEVEL_OR_OBJECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            FirPropertyAccessor getter = firProperty2.getGetter();
            KtSourceElement source = getter != null ? getter.getSource() : null;
            if (source != null && !(source.getKind() instanceof KtFakeSourceElementKind)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getCONST_VAL_WITH_GETTER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            if (firProperty2.getDelegate() != null) {
                FirExpression delegate = firProperty2.getDelegate();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (delegate != null ? delegate.getSource() : null), FirErrors.INSTANCE.getCONST_VAL_WITH_DELEGATE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            FirExpression initializer = firProperty2.getInitializer();
            if (initializer == null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty2.getSource(), DeclarationAttributesKt.getHasExplicitBackingField(firProperty2) ? FirErrors.INSTANCE.getCONST_VAL_WITH_EBF() : FirErrors.INSTANCE.getCONST_VAL_WITHOUT_INITIALIZER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firProperty2.getReturnTypeRef()));
            if (!(coneKotlinTypeFullyExpandedType instanceof ConeErrorType) && !FirConstChecksKt.canBeUsedForConstVal(coneKotlinTypeFullyExpandedType)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPE_CANT_BE_USED_FOR_CONST_VAL(), (Object) FirTypeUtilsKt.getConeType(firProperty2.getReturnTypeRef()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
            if (DeclarationAttributesKt.getEvaluatedInitializer(firProperty2) instanceof FirEvaluatorResult.DivisionByZero) {
                KtSourceElement source2 = initializer.getSource();
                FirErrors firErrors = FirErrors.INSTANCE;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, firErrors.getDIVISION_BY_ZERO(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializer.getSource(), firErrors.getCONST_VAL_WITH_NON_CONST_INITIALIZER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[FirConstChecksKt.computeConstantExpressionKind(initializer, checkerContext.getSession(), true).ordinal()];
            if (i == 1 || i == 2) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializer.getSource(), i != 3 ? FirErrors.INSTANCE.getCONST_VAL_WITH_NON_CONST_INITIALIZER() : FirErrors.INSTANCE.getNON_CONST_VAL_USED_IN_CONSTANT_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
