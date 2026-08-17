package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.CommonBackendContext;
import org.jetbrains.kotlin.backend.common.lower.coroutines.AddContinuationToNonLocalSuspendFunctionsLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsLoweringPhasesKt$jsLowerings$71 extends FunctionReferenceImpl implements Function1<CommonBackendContext, AddContinuationToNonLocalSuspendFunctionsLowering> {
    public static final JsLoweringPhasesKt$jsLowerings$71 INSTANCE = new JsLoweringPhasesKt$jsLowerings$71();

    public JsLoweringPhasesKt$jsLowerings$71() {
        super(1, AddContinuationToNonLocalSuspendFunctionsLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/CommonBackendContext;)V", 0);
    }

    public final AddContinuationToNonLocalSuspendFunctionsLowering invoke(CommonBackendContext commonBackendContext) {
        commonBackendContext.getClass();
        return new AddContinuationToNonLocalSuspendFunctionsLowering(commonBackendContext);
    }
}
