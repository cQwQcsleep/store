package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0002\u0010\tJ-\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000f\u001a\u00020\u0007H&¢\u0006\u0002\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/ErrorReporter;", "Node", "", "reportCallError", "", "node", "expected", "", "received", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V", "reportParameterError", "index", "", "(Ljava/lang/Object;ILjava/lang/String;Ljava/lang/String;)V", "log", "message", "(Ljava/lang/Object;Ljava/lang/String;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ErrorReporter<Node> {
    void log(Node node, String message);

    void reportCallError(Node node, String expected, String received);

    void reportParameterError(Node node, int index, String expected, String received);
}
