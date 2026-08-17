package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.ir.backend.js.lower.inline.RemoveInlineDeclarationsWithReifiedTypeParametersLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsLoweringPhasesKt$jsLowerings$18 extends FunctionReferenceImpl implements Function1<LoweringContext, RemoveInlineDeclarationsWithReifiedTypeParametersLowering> {
    public static final JsLoweringPhasesKt$jsLowerings$18 INSTANCE = new JsLoweringPhasesKt$jsLowerings$18();

    public JsLoweringPhasesKt$jsLowerings$18() {
        super(1, RemoveInlineDeclarationsWithReifiedTypeParametersLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)V", 0);
    }

    public final RemoveInlineDeclarationsWithReifiedTypeParametersLowering invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return new RemoveInlineDeclarationsWithReifiedTypeParametersLowering(loweringContext);
    }
}
