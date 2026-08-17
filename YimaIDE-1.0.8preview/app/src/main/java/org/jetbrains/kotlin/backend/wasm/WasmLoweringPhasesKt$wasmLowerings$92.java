package org.jetbrains.kotlin.backend.wasm;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.backend.js.JsCommonBackendContext;
import org.jetbrains.kotlin.ir.backend.js.lower.InvokeStaticInitializersLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class WasmLoweringPhasesKt$wasmLowerings$92 extends FunctionReferenceImpl implements Function1<JsCommonBackendContext, InvokeStaticInitializersLowering> {
    public static final WasmLoweringPhasesKt$wasmLowerings$92 INSTANCE = new WasmLoweringPhasesKt$wasmLowerings$92();

    public WasmLoweringPhasesKt$wasmLowerings$92() {
        super(1, InvokeStaticInitializersLowering.class, CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "<init>(Lorg/jetbrains/kotlin/ir/backend/js/JsCommonBackendContext;)V", 0);
    }

    public final InvokeStaticInitializersLowering invoke(JsCommonBackendContext jsCommonBackendContext) {
        jsCommonBackendContext.getClass();
        return new InvokeStaticInitializersLowering(jsCommonBackendContext);
    }
}
