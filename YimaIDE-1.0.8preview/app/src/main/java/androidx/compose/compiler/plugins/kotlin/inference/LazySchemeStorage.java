package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\u0004H&¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/LazySchemeStorage;", "Node", "", "getLazyScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "node", "(Ljava/lang/Object;)Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "storeLazyScheme", "", "value", "(Ljava/lang/Object;Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface LazySchemeStorage<Node> {
    LazyScheme getLazyScheme(Node node);

    void storeLazyScheme(Node node, LazyScheme value);
}
