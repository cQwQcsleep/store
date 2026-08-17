package org.jetbrains.kotlin.library.abi.impl;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"org/jetbrains/kotlin/library/abi/impl/LibraryDeserializer$annotationInterner$1", "", "uniqueAnnotations", "Lit/unimi/dsi/fastutil/objects/ObjectOpenHashSet;", "Lorg/jetbrains/kotlin/library/abi/impl/AbiAnnotationImpl;", "intern", "annotation", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LibraryDeserializer$annotationInterner$1 {
    private final ObjectOpenHashSet<AbiAnnotationImpl> uniqueAnnotations = new ObjectOpenHashSet<>();

    public final AbiAnnotationImpl intern(AbiAnnotationImpl annotation) {
        annotation.getClass();
        Object objAddOrGet = this.uniqueAnnotations.addOrGet(annotation);
        objAddOrGet.getClass();
        return (AbiAnnotationImpl) objAddOrGet;
    }
}
