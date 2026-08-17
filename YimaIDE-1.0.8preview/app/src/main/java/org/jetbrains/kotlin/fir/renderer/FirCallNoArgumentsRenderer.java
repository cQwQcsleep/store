package org.jetbrains.kotlin.fir.renderer;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirCallNoArgumentsRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "<init>", "()V", "renderArgumentMapping", Argument.Delimiters.none, "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "renderArguments", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallNoArgumentsRenderer extends FirCallArgumentsRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.FirCallArgumentsRenderer
    public void renderArgumentMapping(FirAnnotationArgumentMapping argumentMapping) {
        argumentMapping.getClass();
        getPrinter().print("(");
        if (!argumentMapping.getMapping().isEmpty()) {
            getPrinter().print("...");
        }
        getPrinter().print(")");
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirCallArgumentsRenderer
    public void renderArguments(List<? extends FirExpression> arguments) {
        arguments.getClass();
        getPrinter().print("(");
        if (!arguments.isEmpty()) {
            getPrinter().print("...");
        }
        getPrinter().print(")");
    }
}
