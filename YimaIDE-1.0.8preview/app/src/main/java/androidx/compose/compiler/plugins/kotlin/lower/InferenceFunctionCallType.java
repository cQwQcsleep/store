package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.Item;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionCallType;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;Lorg/jetbrains/kotlin/ir/expressions/IrCall;)V", "name", "", "getName", "()Ljava/lang/String;", "schemeIsUpdatable", "", "getSchemeIsUpdatable", "()Z", "toDeclaredScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "defaultTarget", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "isOverlyWide", "updateScheme", "", "scheme", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceFunctionCallType extends InferenceFunction {
    private final IrCall call;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceFunctionCallType(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrCall irCall) {
        super(composableTargetAnnotationsTransformer, null);
        composableTargetAnnotationsTransformer.getClass();
        irCall.getClass();
        this.call = irCall;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public String getName() {
        return "Call(" + this.call.getSymbol().getOwner().getName() + ')';
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public boolean getSchemeIsUpdatable() {
        return false;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public boolean isOverlyWide() {
        return ComposableTargetAnnotationsTransformerKt.hasOverlyWideParameters(this.call.getSymbol().getOwner());
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public Scheme toDeclaredScheme(Item defaultTarget) {
        defaultTarget.getClass();
        ComposableTargetAnnotationsTransformer transformer = getTransformer();
        Item target = transformer.getTarget(this.call.getSymbol().getOwner().getAnnotations());
        Item item = target.getIsUnspecified() ? defaultTarget : target;
        List listFilterNotNull = CollectionsKt.filterNotNull(ComposableTargetAnnotationsTransformerKt.getTargetArguments(this.call));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listFilterNotNull) {
            if (transformer.isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(((IrExpression) obj).getType())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(transformer.toScheme(((IrExpression) it.next()).getType(), defaultTarget));
        }
        return new Scheme(item, CollectionsKt.toMutableList(arrayList2), transformer.isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(this.call.getType()) ? transformer.toScheme(this.call.getType(), defaultTarget) : null, false, 8, null);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public void updateScheme(Scheme scheme) {
        scheme.getClass();
    }
}
