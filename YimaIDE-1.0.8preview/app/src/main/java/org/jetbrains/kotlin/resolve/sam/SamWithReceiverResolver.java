package org.jetbrains.kotlin.resolve.sam;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/resolve/sam/SamWithReceiverResolver;", "", "shouldConvertFirstSamParameterToReceiver", "", "function", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface SamWithReceiverResolver {
    boolean shouldConvertFirstSamParameterToReceiver(FunctionDescriptor function);
}
