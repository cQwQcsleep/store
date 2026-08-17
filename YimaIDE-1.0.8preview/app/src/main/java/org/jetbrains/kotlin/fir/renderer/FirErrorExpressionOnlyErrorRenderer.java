package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionOnlyErrorRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionRenderer;", "<init>", "()V", "renderErrorExpression", Argument.Delimiters.none, "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorExpressionOnlyErrorRenderer extends FirErrorExpressionRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.FirErrorExpressionRenderer
    public void renderErrorExpression(FirErrorExpression errorExpression) {
        errorExpression.getClass();
        renderDiagnostic(errorExpression.getDiagnostic());
        FirExpression expression = errorExpression.getExpression();
        if (expression != null) {
            KtSourceElement source = errorExpression.getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ErrorExpressionForTopLevelLambda.INSTANCE)) {
                expression.accept(getComponents$org_jetbrains_kotlin_tree().getVisitor());
            }
        }
    }
}
