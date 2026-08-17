package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.CommonBackendContext;
import org.jetbrains.kotlin.backend.common.lower.EnumWhenLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsLoweringPhasesKt$jsLowerings$58 extends FunctionReferenceImpl implements Function1<CommonBackendContext, EnumWhenLowering> {
    public static final JsLoweringPhasesKt$jsLowerings$58 INSTANCE = new JsLoweringPhasesKt$jsLowerings$58();

    public JsLoweringPhasesKt$jsLowerings$58() {
        super(1, EnumWhenLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/CommonBackendContext;)V", 0);
    }

    public final EnumWhenLowering invoke(CommonBackendContext commonBackendContext) {
        commonBackendContext.getClass();
        return new EnumWhenLowering(commonBackendContext);
    }
}
