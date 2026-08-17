package org.jetbrains.kotlin.load.java.lazy.types;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.ErasureProjectionComputer;
import org.jetbrains.kotlin.types.ErasureTypeAttributes;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeParameterUpperBoundEraser;
import org.jetbrains.kotlin.types.TypeProjection;
import org.jetbrains.kotlin.types.TypeProjectionImpl;
import org.jetbrains.kotlin.types.TypeUtils;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/load/java/lazy/types/RawProjectionComputer;", "Lorg/jetbrains/kotlin/types/ErasureProjectionComputer;", "<init>", "()V", "computeProjection", "Lorg/jetbrains/kotlin/types/TypeProjection;", "parameter", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "typeAttr", "Lorg/jetbrains/kotlin/types/ErasureTypeAttributes;", "typeParameterUpperBoundEraser", "Lorg/jetbrains/kotlin/types/TypeParameterUpperBoundEraser;", "erasedUpperBound", "Lorg/jetbrains/kotlin/types/KotlinType;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class RawProjectionComputer extends ErasureProjectionComputer {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JavaTypeFlexibility.values().length];
            try {
                iArr[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.jetbrains.kotlin.types.ErasureProjectionComputer
    public TypeProjection computeProjection(TypeParameterDescriptor parameter, ErasureTypeAttributes typeAttr, TypeParameterUpperBoundEraser typeParameterUpperBoundEraser, KotlinType erasedUpperBound) {
        TypeProjectionImpl typeProjectionImpl;
        parameter.getClass();
        typeAttr.getClass();
        typeParameterUpperBoundEraser.getClass();
        erasedUpperBound.getClass();
        if (!(typeAttr instanceof JavaTypeAttributes)) {
            return super.computeProjection(parameter, typeAttr, typeParameterUpperBoundEraser, erasedUpperBound);
        }
        JavaTypeAttributes javaTypeAttributesWithFlexibility = (JavaTypeAttributes) typeAttr;
        if (!javaTypeAttributesWithFlexibility.isRaw()) {
            javaTypeAttributesWithFlexibility = javaTypeAttributesWithFlexibility.withFlexibility(JavaTypeFlexibility.INFLEXIBLE);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[javaTypeAttributesWithFlexibility.getFlexibility().ordinal()];
        if (i == 1) {
            return new TypeProjectionImpl(Variance.INVARIANT, erasedUpperBound);
        }
        if (i != 2 && i != 3) {
            bu8.a();
            return null;
        }
        if (parameter.getVariance().getAllowsOutPosition()) {
            List parameters = erasedUpperBound.getConstructor().getParameters();
            parameters.getClass();
            typeProjectionImpl = !parameters.isEmpty() ? new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound) : TypeUtils.makeStarProjection(parameter, javaTypeAttributesWithFlexibility);
        } else {
            typeProjectionImpl = new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns((DeclarationDescriptor) parameter).getNothingType());
        }
        typeProjectionImpl.getClass();
        return typeProjectionImpl;
    }
}
