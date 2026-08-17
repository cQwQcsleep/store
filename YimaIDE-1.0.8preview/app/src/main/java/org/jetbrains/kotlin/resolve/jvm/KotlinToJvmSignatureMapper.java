package org.jetbrains.kotlin.resolve.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/KotlinToJvmSignatureMapper;", "", "mapToJvmMethodSignature", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinToJvmSignatureMapper$MethodSignature;", "function", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "erasedSignaturesEqualIgnoringReturnTypes", "", "subFunction", "superFunction", "MethodSignature", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface KotlinToJvmSignatureMapper {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/KotlinToJvmSignatureMapper$MethodSignature;", "", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public interface MethodSignature {
    }

    boolean erasedSignaturesEqualIgnoringReturnTypes(MethodSignature subFunction, MethodSignature superFunction);

    MethodSignature mapToJvmMethodSignature(FunctionDescriptor function);
}
