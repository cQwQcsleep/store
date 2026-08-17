package org.jetbrains.kotlin.load.java.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/java/descriptors/PossiblyExternalAnnotationDescriptor;", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "isIdeExternalAnnotation", "", "()Z", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface PossiblyExternalAnnotationDescriptor extends AnnotationDescriptor {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final class DefaultImpls {
        public static FqName getFqName(PossiblyExternalAnnotationDescriptor possiblyExternalAnnotationDescriptor) {
            return AnnotationDescriptor.DefaultImpls.getFqName(possiblyExternalAnnotationDescriptor);
        }
    }

    boolean isIdeExternalAnnotation();
}
