package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.model.DynamicTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u00012\u00020\u0002:\u0001\tB\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u0002\b\b¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeDynamicType;", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "Lorg/jetbrains/kotlin/types/model/DynamicTypeMarker;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;Lorg/jetbrains/kotlin/fir/types/ConeRigidType;)V", "Lorg/jetbrains/kotlin/fir/types/DynamicTypeConstructor;", "Companion", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ConeDynamicType extends ConeFlexibleType implements DynamicTypeMarker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @DynamicTypeConstructor
    public ConeDynamicType(ConeRigidType coneRigidType, ConeRigidType coneRigidType2) {
        super(coneRigidType, coneRigidType2, false);
        coneRigidType.getClass();
        coneRigidType2.getClass();
    }
}
