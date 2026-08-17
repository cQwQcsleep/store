package org.jetbrains.kotlin.ir.inline;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.PreSerializationLoweringContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$8 extends FunctionReferenceImpl implements Function1<PreSerializationLoweringContext, PreSerializationPrivateFunctionInlining> {
    public static final CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$8 INSTANCE = new CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$8();

    public CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$8() {
        super(1, PreSerializationPrivateFunctionInlining.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;)V", 0);
    }

    public final PreSerializationPrivateFunctionInlining invoke(PreSerializationLoweringContext preSerializationLoweringContext) {
        preSerializationLoweringContext.getClass();
        return new PreSerializationPrivateFunctionInlining(preSerializationLoweringContext);
    }
}
