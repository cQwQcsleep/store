package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.common.lower.ExpectDeclarationsRemoveLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsLoweringPhasesKt$jsLowerings$32 extends FunctionReferenceImpl implements Function1<LoweringContext, ExpectDeclarationsRemoveLowering> {
    public static final JsLoweringPhasesKt$jsLowerings$32 INSTANCE = new JsLoweringPhasesKt$jsLowerings$32();

    public JsLoweringPhasesKt$jsLowerings$32() {
        super(1, ExpectDeclarationsRemoveLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)V", 0);
    }

    public final ExpectDeclarationsRemoveLowering invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return new ExpectDeclarationsRemoveLowering(loweringContext);
    }
}
