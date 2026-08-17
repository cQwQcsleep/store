package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.Item;
import androidx.compose.compiler.plugins.kotlin.inference.Open;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0012\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0004\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;)V", "getTransformer", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "name", "", "getName", "()Ljava/lang/String;", "schemeIsUpdatable", "", "getSchemeIsUpdatable", "()Z", "recordScheme", "", "scheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "updateScheme", "toDeclaredScheme", "defaultTarget", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "isOverlyWide", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionCallType;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionDeclaration;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionParameter;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionType;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class InferenceFunction {
    private final ComposableTargetAnnotationsTransformer transformer;

    private InferenceFunction(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer) {
        this.transformer = composableTargetAnnotationsTransformer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Scheme toDeclaredScheme$default(InferenceFunction inferenceFunction, Item item, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toDeclaredScheme");
            return null;
        }
        if ((i & 1) != 0) {
            item = new Open(0, 0 == true ? 1 : 0, 2, null);
        }
        return inferenceFunction.toDeclaredScheme(item);
    }

    public abstract String getName();

    public abstract boolean getSchemeIsUpdatable();

    public final ComposableTargetAnnotationsTransformer getTransformer() {
        return this.transformer;
    }

    public boolean isOverlyWide() {
        return false;
    }

    public void recordScheme(Scheme scheme) {
        scheme.getClass();
    }

    public abstract Scheme toDeclaredScheme(Item defaultTarget);

    public abstract void updateScheme(Scheme scheme);

    public /* synthetic */ InferenceFunction(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, DefaultConstructorMarker defaultConstructorMarker) {
        this(composableTargetAnnotationsTransformer);
    }
}
