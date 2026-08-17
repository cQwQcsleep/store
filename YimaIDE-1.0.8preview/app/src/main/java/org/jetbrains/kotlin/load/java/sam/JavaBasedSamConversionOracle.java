package org.jetbrains.kotlin.load.java.sam;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.JavaClassConstructorDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor;
import org.jetbrains.kotlin.resolve.sam.SamConversionOracle;
import org.jetbrains.kotlin.synthetic.SyntheticExtensionsUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/load/java/sam/JavaBasedSamConversionOracle;", "Lorg/jetbrains/kotlin/resolve/sam/SamConversionOracle;", "<init>", "()V", "shouldRunSamConversionForFunction", "", "candidate", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "isPossibleSamType", "samType", "Lorg/jetbrains/kotlin/types/KotlinType;", "isJavaApplicableCandidate", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaBasedSamConversionOracle implements SamConversionOracle {
    public static final JavaBasedSamConversionOracle INSTANCE = new JavaBasedSamConversionOracle();

    private JavaBasedSamConversionOracle() {
    }

    public boolean isJavaApplicableCandidate(CallableDescriptor candidate) {
        candidate.getClass();
        return shouldRunSamConversionForFunction(candidate);
    }

    public boolean isPossibleSamType(KotlinType samType) {
        samType.getClass();
        ClassDescriptor declarationDescriptor = samType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor instanceof ClassDescriptor) {
            return declarationDescriptor.isFun() || (declarationDescriptor instanceof JavaClassDescriptor);
        }
        return false;
    }

    public boolean shouldRunSamConversionForFunction(CallableDescriptor candidate) {
        candidate.getClass();
        FunctionDescriptor original = candidate.getOriginal();
        FunctionDescriptor functionDescriptor = original instanceof FunctionDescriptor ? original : null;
        if (functionDescriptor == null) {
            return false;
        }
        if ((functionDescriptor instanceof TypeAliasConstructorDescriptor) && (((TypeAliasConstructorDescriptor) functionDescriptor).getUnderlyingConstructorDescriptor() instanceof JavaClassConstructorDescriptor)) {
            return true;
        }
        return SyntheticExtensionsUtilsKt.hasJavaOriginInHierarchy(functionDescriptor);
    }
}
