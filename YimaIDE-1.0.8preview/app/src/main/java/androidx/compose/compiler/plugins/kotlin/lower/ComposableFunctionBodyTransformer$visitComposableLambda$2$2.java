package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class ComposableFunctionBodyTransformer$visitComposableLambda$2$2 extends FunctionReferenceImpl implements Function3<Boolean, IrExpression, ComposableFunctionBodyTransformer.CallArgumentMeta, IrExpression> {
    public ComposableFunctionBodyTransformer$visitComposableLambda$2$2(Object obj) {
        super(3, obj, ComposableFunctionBodyTransformer.class, "irIntrinsicChanged", "irIntrinsicChanged(ZLorg/jetbrains/kotlin/ir/expressions/IrExpression;Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(((Boolean) obj).booleanValue(), (IrExpression) obj2, (ComposableFunctionBodyTransformer.CallArgumentMeta) obj3);
    }

    public final IrExpression invoke(boolean z, IrExpression irExpression, ComposableFunctionBodyTransformer.CallArgumentMeta callArgumentMeta) {
        irExpression.getClass();
        callArgumentMeta.getClass();
        return ((ComposableFunctionBodyTransformer) ((CallableReference) this).receiver).irIntrinsicChanged(z, irExpression, callArgumentMeta);
    }
}
