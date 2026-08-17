package org.jetbrains.kotlin.ir.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/descriptors/IrPropertyDelegateDescriptor;", "Lorg/jetbrains/kotlin/ir/descriptors/IrDelegateDescriptor;", "correspondingProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getCorrespondingProperty", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "kPropertyType", "Lorg/jetbrains/kotlin/types/KotlinType;", "getKPropertyType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrPropertyDelegateDescriptor extends IrDelegateDescriptor {
    PropertyDescriptor getCorrespondingProperty();

    KotlinType getKPropertyType();
}
