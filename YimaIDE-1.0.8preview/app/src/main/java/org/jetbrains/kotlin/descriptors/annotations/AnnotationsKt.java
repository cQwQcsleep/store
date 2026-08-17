package org.jetbrains.kotlin.descriptors.annotations;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"composeAnnotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "first", "second", "org.jetbrains.kotlin:descriptors"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationsKt {
    public static final Annotations composeAnnotations(Annotations annotations, Annotations annotations2) {
        annotations.getClass();
        annotations2.getClass();
        if (annotations.isEmpty()) {
            return annotations2;
        }
        return annotations2.isEmpty() ? annotations : new CompositeAnnotations(annotations, annotations2);
    }
}
