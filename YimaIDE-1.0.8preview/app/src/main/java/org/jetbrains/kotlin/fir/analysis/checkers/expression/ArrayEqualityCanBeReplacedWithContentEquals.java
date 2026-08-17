package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ArrayEqualityCanBeReplacedWithContentEquals;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "ARRAY_CLASS_IDS", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArrayEqualityCanBeReplacedWithContentEquals extends FirExpressionChecker<FirStatement> {
    private static final List<ClassId> ARRAY_CLASS_IDS;
    public static final ArrayEqualityCanBeReplacedWithContentEquals INSTANCE = new ArrayEqualityCanBeReplacedWithContentEquals();

    static {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        listCreateListBuilder.add(standardClassIds.getArray());
        listCreateListBuilder.addAll(standardClassIds.getPrimitiveArrayTypeByElementType().values());
        listCreateListBuilder.addAll(standardClassIds.getUnsignedArrayTypeByElementType().values());
        ARRAY_CLASS_IDS = CollectionsKt.build(listCreateListBuilder);
    }

    private ArrayEqualityCanBeReplacedWithContentEquals() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirExpression firExpression;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (firStatement instanceof FirEqualityOperatorCall) {
            FirEqualityOperatorCall firEqualityOperatorCall = (FirEqualityOperatorCall) firStatement;
            FirOperation operation = firEqualityOperatorCall.getOperation();
            if (operation == FirOperation.EQ || operation == FirOperation.NOT_EQ) {
                List<FirExpression> arguments = ((FirCall) firStatement).getArgumentList().getArguments();
                FirExpression firExpression2 = (FirExpression) CollectionsKt.getOrNull(arguments, 0);
                if (firExpression2 == null || (firExpression = (FirExpression) CollectionsKt.getOrNull(arguments, 1)) == null) {
                    return;
                }
                List<ClassId> list = ARRAY_CLASS_IDS;
                Integer numValueOf = Integer.valueOf(CollectionsKt.indexOf(list, FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getResolvedType(firExpression2), checkerContext.getSession())));
                if (numValueOf.intValue() == -1) {
                    numValueOf = null;
                }
                int iIndexOf = CollectionsKt.indexOf(list, FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getResolvedType(firExpression), checkerContext.getSession()));
                if (numValueOf != null && numValueOf.intValue() == iIndexOf) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), FirErrors.INSTANCE.getARRAY_EQUALITY_OPERATOR_CAN_BE_REPLACED_WITH_CONTENT_EQUALS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }
}
