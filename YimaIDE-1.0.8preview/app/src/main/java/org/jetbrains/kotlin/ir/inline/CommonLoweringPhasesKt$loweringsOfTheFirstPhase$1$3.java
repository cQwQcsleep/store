package org.jetbrains.kotlin.ir.inline;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.PreSerializationLoweringContext;
import org.jetbrains.kotlin.backend.common.lower.inline.InlineCallCycleCheckerLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$3 extends FunctionReferenceImpl implements Function1<PreSerializationLoweringContext, InlineCallCycleCheckerLowering<PreSerializationLoweringContext>> {
    public static final CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$3 INSTANCE = new CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$3();

    public CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$3() {
        super(1, InlineCallCycleCheckerLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)V", 0);
    }

    public final InlineCallCycleCheckerLowering<PreSerializationLoweringContext> invoke(PreSerializationLoweringContext preSerializationLoweringContext) {
        preSerializationLoweringContext.getClass();
        return new InlineCallCycleCheckerLowering<>(preSerializationLoweringContext);
    }
}
