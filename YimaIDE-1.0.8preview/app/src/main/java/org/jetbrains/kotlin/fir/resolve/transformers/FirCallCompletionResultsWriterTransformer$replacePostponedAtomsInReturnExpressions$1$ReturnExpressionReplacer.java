package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FirAnonymousFunctionReturnExpressionInfo;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformer$replacePostponedAtomsInReturnExpressions$1$ReturnExpressionReplacer;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.TransformData;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J)\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0012"}, d2 = {"org/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$replacePostponedAtomsInReturnExpressions$1$ReturnExpressionReplacer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirAnonymousFunctionReturnExpressionInfo;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformReturnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "transformBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallCompletionResultsWriterTransformer$replacePostponedAtomsInReturnExpressions$1$ReturnExpressionReplacer extends FirTransformer {
    final /* synthetic */ FirExpression $replacement;
    final /* synthetic */ FirAnonymousFunctionReturnExpressionInfo $returnInfo;

    public FirCallCompletionResultsWriterTransformer$replacePostponedAtomsInReturnExpressions$1$ReturnExpressionReplacer(FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfo, FirExpression firExpression) {
        this.$returnInfo = firAnonymousFunctionReturnExpressionInfo;
        this.$replacement = firExpression;
    }

    public static TransformData b(FirBlock firBlock, int i) {
        return i == CollectionsKt.getLastIndex(firBlock.getStatements()) ? new TransformData.Data(null) : TransformData.Nothing.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBlock(final FirBlock block, Void data) {
        block.getClass();
        return FirExpressionUtilKt.transformStatementsIndexed(block, this, new Function1() { // from class: az4
            public final Object invoke(Object obj) {
                return FirCallCompletionResultsWriterTransformer$replacePostponedAtomsInReturnExpressions$1$ReturnExpressionReplacer.b(block, ((Integer) obj).intValue());
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Void data) {
        element.getClass();
        if (element != this.$returnInfo.getExpression()) {
            return element;
        }
        FirExpression firExpression = this.$replacement;
        firExpression.getClass();
        return firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReturnExpression(FirReturnExpression returnExpression, Void data) {
        returnExpression.getClass();
        return returnExpression.transformResult(this, data);
    }
}
