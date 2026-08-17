package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/TypeMismatchDueToTypeProjectionsData;", Argument.Delimiters.none, "expectedType", "Lorg/jetbrains/kotlin/types/KotlinType;", "expressionType", "receiverType", "callableDescriptor", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)V", "getExpectedType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "getExpressionType", "getReceiverType", "getCallableDescriptor", "()Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeMismatchDueToTypeProjectionsData {
    private final CallableDescriptor callableDescriptor;
    private final KotlinType expectedType;
    private final KotlinType expressionType;
    private final KotlinType receiverType;

    public TypeMismatchDueToTypeProjectionsData(KotlinType kotlinType, KotlinType kotlinType2, KotlinType kotlinType3, CallableDescriptor callableDescriptor) {
        kotlinType.getClass();
        kotlinType2.getClass();
        kotlinType3.getClass();
        callableDescriptor.getClass();
        this.expectedType = kotlinType;
        this.expressionType = kotlinType2;
        this.receiverType = kotlinType3;
        this.callableDescriptor = callableDescriptor;
    }

    public final CallableDescriptor getCallableDescriptor() {
        return this.callableDescriptor;
    }

    public final KotlinType getExpectedType() {
        return this.expectedType;
    }

    public final KotlinType getExpressionType() {
        return this.expressionType;
    }

    public final KotlinType getReceiverType() {
        return this.receiverType;
    }
}
