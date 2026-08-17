package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.Annotated;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/FieldDescriptor;", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotated;", "correspondingProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getCorrespondingProperty", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FieldDescriptor extends Annotated {
    PropertyDescriptor getCorrespondingProperty();
}
