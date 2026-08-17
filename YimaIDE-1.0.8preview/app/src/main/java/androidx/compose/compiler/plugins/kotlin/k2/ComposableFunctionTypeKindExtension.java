package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.extensions.FirFunctionTypeKindExtension;
import org.jetbrains.kotlin.fir.extensions.FirFunctionTypeKindExtension$FunctionTypeKindRegistrar;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposableFunctionTypeKindExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionTypeKindExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "registerKinds", "", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionTypeKindExtension$FunctionTypeKindRegistrar;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableFunctionTypeKindExtension extends FirFunctionTypeKindExtension {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableFunctionTypeKindExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
    }

    public void registerKinds(FirFunctionTypeKindExtension$FunctionTypeKindRegistrar firFunctionTypeKindExtension$FunctionTypeKindRegistrar) {
        firFunctionTypeKindExtension$FunctionTypeKindRegistrar.getClass();
        firFunctionTypeKindExtension$FunctionTypeKindRegistrar.registerKind(ComposableFunction.INSTANCE, KComposableFunction.INSTANCE);
    }
}
