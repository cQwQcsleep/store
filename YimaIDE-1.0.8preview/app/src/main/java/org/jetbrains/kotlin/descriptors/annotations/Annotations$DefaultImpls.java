package org.jetbrains.kotlin.descriptors.annotations;

import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class Annotations$DefaultImpls {
    public static AnnotationDescriptor findAnnotation(Annotations annotations, FqName fqName) {
        Object next;
        fqName.getClass();
        Iterator it = annotations.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((AnnotationDescriptor) next).getFqName(), fqName)) {
                return (AnnotationDescriptor) next;
            }
        }
        next = null;
        return (AnnotationDescriptor) next;
    }

    @Deprecated(message = "This method should only be used in frontend where we split annotations according to their use-site targets.")
    public static List<AnnotationWithTarget> getUseSiteTargetedAnnotations(Annotations annotations) {
        return CollectionsKt.emptyList();
    }

    public static boolean hasAnnotation(Annotations annotations, FqName fqName) {
        fqName.getClass();
        return annotations.findAnnotation(fqName) != null;
    }
}
