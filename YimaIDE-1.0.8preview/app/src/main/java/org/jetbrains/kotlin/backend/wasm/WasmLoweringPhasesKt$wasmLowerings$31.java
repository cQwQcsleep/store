package org.jetbrains.kotlin.backend.wasm;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.backend.js.JsCommonBackendContext;
import org.jetbrains.kotlin.ir.backend.js.lower.EnumEntryCreateGetInstancesFunsLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class WasmLoweringPhasesKt$wasmLowerings$31 extends FunctionReferenceImpl implements Function1<JsCommonBackendContext, EnumEntryCreateGetInstancesFunsLowering> {
    public static final WasmLoweringPhasesKt$wasmLowerings$31 INSTANCE = new WasmLoweringPhasesKt$wasmLowerings$31();

    public WasmLoweringPhasesKt$wasmLowerings$31() {
        super(1, EnumEntryCreateGetInstancesFunsLowering.class, CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "<init>(Lorg/jetbrains/kotlin/ir/backend/js/JsCommonBackendContext;)V", 0);
    }

    public final EnumEntryCreateGetInstancesFunsLowering invoke(JsCommonBackendContext jsCommonBackendContext) {
        jsCommonBackendContext.getClass();
        return new EnumEntryCreateGetInstancesFunsLowering(jsCommonBackendContext);
    }
}
