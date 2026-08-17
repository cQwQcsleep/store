package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0000H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\n\u0010\u0018\u001a\u00020\u0014H\u0096\u0080\u0004J\u0014\u0010\u0019\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0001\b\u001b\u001c\u001d\u001e\u001f !\"¨\u0006#"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "", "<init>", "()V", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "getElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "function", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "getFunction", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "referenceContainer", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "parameterIndex", "", "node", "isOverlyWide", "", "hashCode", "equals", "other", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceCallExpression;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceCallTargetNode;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceElementExpression;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionDeclarationNode;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionExpressionNode;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceResolvedParameter;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceUnknownElement;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceVariable;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class InferenceNode {
    private final InferenceNode referenceContainer;

    public /* synthetic */ InferenceNode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public boolean equals(Object other) {
        return (other instanceof InferenceNode) && Intrinsics.areEqual(((InferenceNode) other).getElement(), getElement());
    }

    public abstract IrElement getElement();

    public abstract InferenceFunction getFunction();

    public abstract NodeKind getKind();

    public InferenceNode getReferenceContainer() {
        return this.referenceContainer;
    }

    public int hashCode() {
        return getElement().hashCode() * 31;
    }

    public boolean isOverlyWide() {
        InferenceFunction function = getFunction();
        return function != null && function.isOverlyWide();
    }

    public int parameterIndex(InferenceNode node) {
        node.getClass();
        return -1;
    }

    private InferenceNode() {
    }
}
