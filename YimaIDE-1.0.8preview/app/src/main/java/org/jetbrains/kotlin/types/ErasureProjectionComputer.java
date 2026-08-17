package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/types/ErasureProjectionComputer;", "", "<init>", "()V", "computeProjection", "Lorg/jetbrains/kotlin/types/TypeProjection;", "parameter", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "typeAttr", "Lorg/jetbrains/kotlin/types/ErasureTypeAttributes;", "typeParameterUpperBoundEraser", "Lorg/jetbrains/kotlin/types/TypeParameterUpperBoundEraser;", "erasedUpperBound", "Lorg/jetbrains/kotlin/types/KotlinType;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class ErasureProjectionComputer {
    public static /* synthetic */ TypeProjection computeProjection$default(ErasureProjectionComputer erasureProjectionComputer, TypeParameterDescriptor typeParameterDescriptor, ErasureTypeAttributes erasureTypeAttributes, TypeParameterUpperBoundEraser typeParameterUpperBoundEraser, KotlinType kotlinType, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: computeProjection");
            return null;
        }
        if ((i & 8) != 0) {
            kotlinType = typeParameterUpperBoundEraser.getErasedUpperBound(typeParameterDescriptor, erasureTypeAttributes);
        }
        return erasureProjectionComputer.computeProjection(typeParameterDescriptor, erasureTypeAttributes, typeParameterUpperBoundEraser, kotlinType);
    }

    public TypeProjection computeProjection(TypeParameterDescriptor parameter, ErasureTypeAttributes typeAttr, TypeParameterUpperBoundEraser typeParameterUpperBoundEraser, KotlinType erasedUpperBound) {
        parameter.getClass();
        typeAttr.getClass();
        typeParameterUpperBoundEraser.getClass();
        erasedUpperBound.getClass();
        return new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound);
    }
}
