package org.jetbrains.kotlin.codegen.signature;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.resolve.jvm.KotlinToJvmSignatureMapper;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0016J\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/codegen/signature/KotlinToJvmSignatureMapperImpl;", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinToJvmSignatureMapper;", "<init>", "()V", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper;", "mapToJvmMethodSignature", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinToJvmSignatureMapper$MethodSignature;", "function", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "erasedSignaturesEqualIgnoringReturnTypes", Argument.Delimiters.none, "subFunction", "superFunction", "parametersDescriptor", Argument.Delimiters.none, "MethodSignatureImpl", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinToJvmSignatureMapperImpl implements KotlinToJvmSignatureMapper {
    private final KotlinTypeMapper typeMapper = new KotlinTypeMapper("main", LanguageVersionSettingsImpl.DEFAULT, false, null, null, 24, null);

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/signature/KotlinToJvmSignatureMapperImpl$MethodSignatureImpl;", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinToJvmSignatureMapper$MethodSignature;", "method", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/commons/Method;)V", "getMethod", "()Lorg/jetbrains/org/objectweb/asm/commons/Method;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MethodSignatureImpl implements KotlinToJvmSignatureMapper.MethodSignature {
        private final Method method;

        public MethodSignatureImpl(Method method) {
            method.getClass();
            this.method = method;
        }

        public final Method getMethod() {
            return this.method;
        }
    }

    private final String parametersDescriptor(KotlinToJvmSignatureMapper.MethodSignature methodSignature) {
        methodSignature.getClass();
        Method method = ((MethodSignatureImpl) methodSignature).getMethod();
        String descriptor = method.getDescriptor();
        descriptor.getClass();
        String descriptor2 = method.getDescriptor();
        descriptor2.getClass();
        return descriptor.substring(1, StringsKt.lastIndexOf$default(descriptor2, ")", 0, false, 6, (Object) null));
    }

    public boolean erasedSignaturesEqualIgnoringReturnTypes(KotlinToJvmSignatureMapper.MethodSignature subFunction, KotlinToJvmSignatureMapper.MethodSignature superFunction) {
        subFunction.getClass();
        superFunction.getClass();
        return Intrinsics.areEqual(parametersDescriptor(subFunction), parametersDescriptor(superFunction));
    }

    public KotlinToJvmSignatureMapper.MethodSignature mapToJvmMethodSignature(FunctionDescriptor function) {
        function.getClass();
        return new MethodSignatureImpl(this.typeMapper.mapAsmMethod(function));
    }
}
