package org.jetbrains.kotlin.fir.analysis.js.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsDefinedExternallyCallChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsDynamicCallChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsExternalArgumentCallChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsModuleGetClassCallChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsModuleQualifiedAccessChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsNativeRttiChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsNoRuntimeClassReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsNoRuntimeTypeOperatorChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsReifiedExternalChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsReifiedJsNoRuntimeChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirJsCodeConstantArgumentChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirJsQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirWebReflectionAPICallChecker;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\n¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/JsExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "<init>", "()V", "annotationCallCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "getAnnotationCallCheckers", "()Ljava/util/Set;", "basicExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "getBasicExpressionCheckers", "functionCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "callCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallChecker;", "getCallCheckers", "getClassCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGetClassCallChecker;", "getGetClassCallCheckers", "qualifiedAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "getQualifiedAccessExpressionCheckers", "typeOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "getTypeOperatorCallCheckers", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsExpressionCheckers extends ExpressionCheckers {
    public static final JsExpressionCheckers INSTANCE = new JsExpressionCheckers();

    private JsExpressionCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotationCall>> getAnnotationCallCheckers() {
        return SetsKt.setOf(FirJsQualifierChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStatement>> getBasicExpressionCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJsDefinedExternallyCallChecker.INSTANCE, FirJsNativeRttiChecker.INSTANCE, new FirWebReflectionAPICallChecker(false)});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCall>> getCallCheckers() {
        return SetsKt.setOf(FirJsExternalArgumentCallChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJsCodeConstantArgumentChecker.INSTANCE, FirJsReifiedExternalChecker.INSTANCE, FirJsReifiedJsNoRuntimeChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirGetClassCall>> getGetClassCallCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJsModuleGetClassCallChecker.INSTANCE, FirJsNoRuntimeClassReferenceChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirQualifiedAccessExpression>> getQualifiedAccessExpressionCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJsModuleQualifiedAccessChecker.INSTANCE, FirJsDynamicCallChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTypeOperatorCall>> getTypeOperatorCallCheckers() {
        return SetsKt.setOf(FirJsNoRuntimeTypeOperatorChecker.INSTANCE);
    }
}
