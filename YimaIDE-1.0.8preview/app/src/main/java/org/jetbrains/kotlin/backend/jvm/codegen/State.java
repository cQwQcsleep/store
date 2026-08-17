package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.org.objectweb.asm.TypePath;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0013\u001a\u00020\u0011J\u0014\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR-\u0010\n\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f`\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/State;", "T", "", "path", "", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;)V", "getPath", "()Ljava/util/List;", "results", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypePathInfo;", "Lkotlin/collections/ArrayList;", "getResults", "()Ljava/util/ArrayList;", "addStep", "", "step", "removeStep", "rememberAnnotations", "annotations", "", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class State<T> {
    private final List<String> path;
    private final ArrayList<TypePathInfo<T>> results;

    public State(List<String> list) {
        list.getClass();
        this.path = list;
        this.results = new ArrayList<>();
    }

    public final void addStep(String step) {
        step.getClass();
        this.path.add(step);
    }

    public final List<String> getPath() {
        return this.path;
    }

    public final ArrayList<TypePathInfo<T>> getResults() {
        return this.results;
    }

    public final void rememberAnnotations(List<? extends T> annotations) {
        annotations.getClass();
        this.results.add(new TypePathInfo<>(TypePath.fromString(CollectionsKt.joinToString$default(this.path, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)), annotations));
    }

    public final void removeStep() {
        List<String> list = this.path;
        list.remove(CollectionsKt.getLastIndex(list));
    }
}
