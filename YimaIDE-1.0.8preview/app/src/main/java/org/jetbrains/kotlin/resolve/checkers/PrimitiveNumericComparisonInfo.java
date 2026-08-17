package org.jetbrains.kotlin.resolve.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/PrimitiveNumericComparisonInfo;", "", "comparisonType", "Lorg/jetbrains/kotlin/types/KotlinType;", "leftPrimitiveType", "rightPrimitiveType", "leftType", "rightType", "<init>", "(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)V", "getComparisonType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "getLeftPrimitiveType", "getRightPrimitiveType", "getLeftType", "getRightType", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PrimitiveNumericComparisonInfo {
    private final KotlinType comparisonType;
    private final KotlinType leftPrimitiveType;
    private final KotlinType leftType;
    private final KotlinType rightPrimitiveType;
    private final KotlinType rightType;

    public PrimitiveNumericComparisonInfo(KotlinType kotlinType, KotlinType kotlinType2, KotlinType kotlinType3, KotlinType kotlinType4, KotlinType kotlinType5) {
        kotlinType.getClass();
        kotlinType2.getClass();
        kotlinType3.getClass();
        kotlinType4.getClass();
        kotlinType5.getClass();
        this.comparisonType = kotlinType;
        this.leftPrimitiveType = kotlinType2;
        this.rightPrimitiveType = kotlinType3;
        this.leftType = kotlinType4;
        this.rightType = kotlinType5;
    }

    public final KotlinType getComparisonType() {
        return this.comparisonType;
    }

    public final KotlinType getLeftPrimitiveType() {
        return this.leftPrimitiveType;
    }

    public final KotlinType getLeftType() {
        return this.leftType;
    }

    public final KotlinType getRightPrimitiveType() {
        return this.rightPrimitiveType;
    }

    public final KotlinType getRightType() {
        return this.rightType;
    }
}
