package org.jetbrains.kotlin.codegen.inline;

import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006J\"\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00002\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00100\u000fJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "usedTypeParameters", "", "", "wereUsedReifiedParameters", "", "addUsedReifiedParameter", "", "name", "propagateChildUsagesWithinContext", "child", "reifiedTypeParameterNamesInContext", "Lkotlin/Function0;", "", "mergeAll", "other", "org.jetbrains.kotlin:backend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ReifiedTypeParametersUsages {
    private final Set<String> usedTypeParameters = new HashSet();

    public final void addUsedReifiedParameter(String name) {
        name.getClass();
        this.usedTypeParameters.add(name);
    }

    public final void mergeAll(ReifiedTypeParametersUsages other) {
        other.getClass();
        if (other.wereUsedReifiedParameters()) {
            this.usedTypeParameters.addAll(other.usedTypeParameters);
        }
    }

    public final void propagateChildUsagesWithinContext(ReifiedTypeParametersUsages child, Function0<? extends Set<String>> reifiedTypeParameterNamesInContext) {
        child.getClass();
        reifiedTypeParameterNamesInContext.getClass();
        if (child.wereUsedReifiedParameters()) {
            this.usedTypeParameters.addAll(SetsKt.minus(child.usedTypeParameters, (Iterable) reifiedTypeParameterNamesInContext.invoke()));
        }
    }

    public final boolean wereUsedReifiedParameters() {
        return !this.usedTypeParameters.isEmpty();
    }
}
