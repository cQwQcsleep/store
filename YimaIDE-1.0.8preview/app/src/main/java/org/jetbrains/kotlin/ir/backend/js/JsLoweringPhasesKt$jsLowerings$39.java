package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.common.lower.TailrecLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsLoweringPhasesKt$jsLowerings$39 extends FunctionReferenceImpl implements Function1<LoweringContext, TailrecLowering> {
    public static final JsLoweringPhasesKt$jsLowerings$39 INSTANCE = new JsLoweringPhasesKt$jsLowerings$39();

    public JsLoweringPhasesKt$jsLowerings$39() {
        super(1, TailrecLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)V", 0);
    }

    public final TailrecLowering invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return new TailrecLowering(loweringContext);
    }
}
