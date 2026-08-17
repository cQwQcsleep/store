package androidx.compose.compiler.plugins.kotlin.k1;

import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0004H\u0016¨\u0006\n"}, d2 = {"androidx/compose/compiler/plugins/kotlin/k1/ComposableTargetChecker$infer$1", "Landroidx/compose/compiler/plugins/kotlin/inference/TypeAdapter;", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "declaredSchemaOf", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "type", "currentInferredSchemeOf", "updatedInferredScheme", "", "scheme", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetChecker$infer$1 implements TypeAdapter<InferenceNodeType> {
    final /* synthetic */ ComposableTargetChecker this$0;

    public ComposableTargetChecker$infer$1(ComposableTargetChecker composableTargetChecker) {
        this.this$0 = composableTargetChecker;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
    public Scheme declaredSchemaOf(InferenceNodeType type) {
        type.getClass();
        CallCheckerContext callCheckerContextAccess$getCallContext$p = ComposableTargetChecker.access$getCallContext$p(this.this$0);
        if (callCheckerContextAccess$getCallContext$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callContext");
            callCheckerContextAccess$getCallContext$p = null;
        }
        return type.toScheme(callCheckerContextAccess$getCallContext$p);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
    public void updatedInferredScheme(InferenceNodeType type, Scheme scheme) {
        type.getClass();
        scheme.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
    public Scheme currentInferredSchemeOf(InferenceNodeType type) {
        type.getClass();
        return null;
    }
}
