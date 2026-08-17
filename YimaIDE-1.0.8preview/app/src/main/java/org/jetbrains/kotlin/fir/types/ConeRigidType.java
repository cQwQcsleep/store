package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "<init>", "()V", "Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeRigidType extends ConeKotlinType implements RigidTypeMarker {
    private ConeRigidType() {
        super((DefaultConstructorMarker) null);
    }

    public /* synthetic */ ConeRigidType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
