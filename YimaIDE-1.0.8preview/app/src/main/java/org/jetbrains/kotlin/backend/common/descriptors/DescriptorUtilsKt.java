package org.jetbrains.kotlin.backend.common.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u0015\u0010\b\u001a\u00020\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\b\u0010\u000b¨\u0006\f"}, d2 = {"synthesizedName", "Lorg/jetbrains/kotlin/name/Name;", "", "getSynthesizedName", "(Ljava/lang/String;)Lorg/jetbrains/kotlin/name/Name;", "synthesizedString", "getSynthesizedString", "(Ljava/lang/String;)Ljava/lang/String;", "isSuspend", "", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Z", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class DescriptorUtilsKt {
    public static final Name getSynthesizedName(String str) {
        str.getClass();
        Name nameIdentifier = Name.identifier(getSynthesizedString(str));
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    public static final String getSynthesizedString(String str) {
        str.getClass();
        return AsmUtil.CAPTURED_PREFIX + str;
    }

    public static final boolean isSuspend(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return (callableDescriptor instanceof FunctionDescriptor) && ((FunctionDescriptor) callableDescriptor).isSuspend();
    }
}
