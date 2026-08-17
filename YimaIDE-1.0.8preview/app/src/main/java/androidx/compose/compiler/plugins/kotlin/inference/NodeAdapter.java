package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006J\u0017\u0010\u000f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/NodeAdapter;", "Type", "Node", "", "containerOf", "node", "(Ljava/lang/Object;)Ljava/lang/Object;", "kindOf", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "(Ljava/lang/Object;)Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "schemeParameterIndexOf", "", "container", "(Ljava/lang/Object;Ljava/lang/Object;)I", "typeOf", "referencedContainerOf", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface NodeAdapter<Type, Node> {
    Node containerOf(Node node);

    NodeKind kindOf(Node node);

    Node referencedContainerOf(Node node);

    int schemeParameterIndexOf(Node node, Node container);

    Type typeOf(Node node);
}
