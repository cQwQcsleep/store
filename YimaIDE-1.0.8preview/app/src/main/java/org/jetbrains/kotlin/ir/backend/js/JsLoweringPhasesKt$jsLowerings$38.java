package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.CommonBackendContext;
import org.jetbrains.kotlin.ir.backend.js.lower.JsSingleAbstractMethodLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsLoweringPhasesKt$jsLowerings$38 extends FunctionReferenceImpl implements Function1<CommonBackendContext, JsSingleAbstractMethodLowering> {
    public static final JsLoweringPhasesKt$jsLowerings$38 INSTANCE = new JsLoweringPhasesKt$jsLowerings$38();

    public JsLoweringPhasesKt$jsLowerings$38() {
        super(1, JsSingleAbstractMethodLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/CommonBackendContext;)V", 0);
    }

    public final JsSingleAbstractMethodLowering invoke(CommonBackendContext commonBackendContext) {
        commonBackendContext.getClass();
        return new JsSingleAbstractMethodLowering(commonBackendContext);
    }
}
