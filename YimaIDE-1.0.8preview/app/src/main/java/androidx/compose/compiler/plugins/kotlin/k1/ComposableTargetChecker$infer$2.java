package androidx.compose.compiler.plugins.kotlin.k1;

import androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter;
import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"androidx/compose/compiler/plugins/kotlin/k1/ComposableTargetChecker$infer$2", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeAdapter;", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNode;", "containerOf", "node", "kindOf", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "schemeParameterIndexOf", "", "container", "typeOf", "referencedContainerOf", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetChecker$infer$2 implements NodeAdapter<InferenceNodeType, InferenceNode> {
    final /* synthetic */ ComposableTargetChecker this$0;

    public ComposableTargetChecker$infer$2(ComposableTargetChecker composableTargetChecker) {
        this.this$0 = composableTargetChecker;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
    public InferenceNode containerOf(InferenceNode node) {
        node.getClass();
        PsiElementNode psiElementNodeAccess$containerNodeOf = ComposableTargetChecker.access$containerNodeOf(this.this$0, node.getElement());
        return psiElementNodeAccess$containerNodeOf != null ? psiElementNodeAccess$containerNodeOf : node;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
    public NodeKind kindOf(InferenceNode node) {
        node.getClass();
        return node.getKind();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
    public int schemeParameterIndexOf(InferenceNode node, InferenceNode container) {
        node.getClass();
        container.getClass();
        ResolvedPsiParameterReference resolvedPsiParameterReference = node instanceof ResolvedPsiParameterReference ? (ResolvedPsiParameterReference) node : null;
        if (resolvedPsiParameterReference == null || !Intrinsics.areEqual(resolvedPsiParameterReference.getContainer(), container.getElement())) {
            return -1;
        }
        return resolvedPsiParameterReference.getIndex();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
    public InferenceNodeType typeOf(InferenceNode node) {
        node.getClass();
        return node.getType();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
    public InferenceNode referencedContainerOf(InferenceNode node) {
        node.getClass();
        return null;
    }
}
