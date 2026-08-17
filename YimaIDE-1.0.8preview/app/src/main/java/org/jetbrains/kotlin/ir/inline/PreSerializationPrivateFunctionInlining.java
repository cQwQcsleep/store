package org.jetbrains.kotlin.ir.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.PreSerializationLoweringContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/ir/inline/PreSerializationPrivateFunctionInlining;", "Lorg/jetbrains/kotlin/ir/inline/FunctionInlining;", "context", "Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;)V", "org.jetbrains.kotlin:ir.inline"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PreSerializationPrivateFunctionInlining extends FunctionInlining {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreSerializationPrivateFunctionInlining(PreSerializationLoweringContext preSerializationLoweringContext) {
        super(preSerializationLoweringContext, new PreSerializationPrivateInlineFunctionResolver(preSerializationLoweringContext));
        preSerializationLoweringContext.getClass();
    }
}
