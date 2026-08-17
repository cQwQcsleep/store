package org.jetbrains.kotlin.descriptors.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"referencedProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getReferencedProperty", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SyntheticFieldDescriptorKt {
    public static final PropertyDescriptor getReferencedProperty(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        if (declarationDescriptor instanceof SyntheticFieldDescriptor) {
            return ((SyntheticFieldDescriptor) declarationDescriptor).getPropertyDescriptor();
        }
        if (declarationDescriptor instanceof PropertyDescriptor) {
            return (PropertyDescriptor) declarationDescriptor;
        }
        return null;
    }
}
