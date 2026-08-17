package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/types/FlexibleTypeBoundsChecker;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "areTypesMayBeLowerAndUpperBoundsOfSameFlexibleTypeByMutability", "", "a", "Lorg/jetbrains/kotlin/types/KotlinType;", "b", "getBaseBoundFqNameByMutability", "Lorg/jetbrains/kotlin/name/FqName;", "type", "fqName", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FlexibleTypeBoundsChecker {
    public static final FlexibleTypeBoundsChecker INSTANCE = new FlexibleTypeBoundsChecker();

    private FlexibleTypeBoundsChecker() {
    }

    public final boolean areTypesMayBeLowerAndUpperBoundsOfSameFlexibleTypeByMutability(KotlinType a, KotlinType b) {
        FqName fqNameSafe;
        a.getClass();
        b.getClass();
        DeclarationDescriptor declarationDescriptor = a.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null || (fqNameSafe = DescriptorUtilsKt.getFqNameSafe(declarationDescriptor)) == null) {
            return false;
        }
        CommonFlexibleTypeBoundsChecker commonFlexibleTypeBoundsChecker = CommonFlexibleTypeBoundsChecker.INSTANCE;
        FqName fqName = commonFlexibleTypeBoundsChecker.getBaseTypesToMutableEquivalent().get(fqNameSafe);
        if (fqName == null && (fqName = commonFlexibleTypeBoundsChecker.getMutableToBaseMap().get(fqNameSafe)) == null) {
            return false;
        }
        DeclarationDescriptor declarationDescriptor2 = b.getConstructor().getDeclarationDescriptor();
        return Intrinsics.areEqual(fqName, declarationDescriptor2 != null ? DescriptorUtilsKt.getFqNameSafe(declarationDescriptor2) : null);
    }

    public final FqName getBaseBoundFqNameByMutability(KotlinType type) {
        FqName fqNameSafe;
        type.getClass();
        DeclarationDescriptor declarationDescriptor = type.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null || (fqNameSafe = DescriptorUtilsKt.getFqNameSafe(declarationDescriptor)) == null) {
            return null;
        }
        return getBaseBoundFqNameByMutability(fqNameSafe);
    }

    public final FqName getBaseBoundFqNameByMutability(FqName fqName) {
        fqName.getClass();
        return CommonFlexibleTypeBoundsChecker.INSTANCE.getBaseBoundFqNameByMutability(fqName);
    }
}
