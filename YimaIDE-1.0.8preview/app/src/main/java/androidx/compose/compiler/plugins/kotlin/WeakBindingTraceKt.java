package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001b\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"ComposeTemporaryGlobalBindingTrace", "Landroidx/compose/compiler/plugins/kotlin/WeakBindingTrace;", "irTrace", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "getIrTrace$annotations", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;)V", "getIrTrace", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;)Landroidx/compose/compiler/plugins/kotlin/WeakBindingTrace;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class WeakBindingTraceKt {
    private static final WeakBindingTrace ComposeTemporaryGlobalBindingTrace = new WeakBindingTrace();

    public static final WeakBindingTrace getIrTrace(IrPluginContext irPluginContext) {
        irPluginContext.getClass();
        return ComposeTemporaryGlobalBindingTrace;
    }

    public static /* synthetic */ void getIrTrace$annotations(IrPluginContext irPluginContext) {
    }
}
