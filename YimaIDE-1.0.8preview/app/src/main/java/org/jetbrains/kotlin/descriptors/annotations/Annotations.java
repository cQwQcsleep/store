package org.jetbrains.kotlin.descriptors.annotations;

import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fJ\b\u0010\u0003\u001a\u00020\u0004H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0017¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "isEmpty", Argument.Delimiters.none, "findAnnotation", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasAnnotation", "getUseSiteTargetedAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationWithTarget;", "Companion", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface Annotations extends Iterable<AnnotationDescriptor>, KMappedMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/Annotations$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "getEMPTY", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Annotations EMPTY = new Annotations() { // from class: org.jetbrains.kotlin.descriptors.annotations.Annotations$Companion$EMPTY$1
            @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
            @Deprecated(message = "This method should only be used in frontend where we split annotations according to their use-site targets.")
            public /* bridge */ List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
                return Annotations.DefaultImpls.getUseSiteTargetedAnnotations(this);
            }

            @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
            public /* bridge */ boolean hasAnnotation(FqName fqName) {
                return Annotations.DefaultImpls.hasAnnotation(this, fqName);
            }

            @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
            public boolean isEmpty() {
                return true;
            }

            @Override // java.lang.Iterable
            public Iterator<AnnotationDescriptor> iterator() {
                return CollectionsKt.emptyList().iterator();
            }

            public String toString() {
                return "EMPTY";
            }

            @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
            /* JADX INFO: renamed from: findAnnotation, reason: merged with bridge method [inline-methods] */
            public Void mo108findAnnotation(FqName fqName) {
                fqName.getClass();
                return null;
            }
        };

        private Companion() {
        }

        public final Annotations create(List<? extends AnnotationDescriptor> annotations) {
            annotations.getClass();
            return annotations.isEmpty() ? EMPTY : new AnnotationsImpl(annotations);
        }

        public final Annotations getEMPTY() {
            return EMPTY;
        }
    }

    /* JADX INFO: renamed from: findAnnotation */
    AnnotationDescriptor mo108findAnnotation(FqName fqName);

    @Deprecated(message = "This method should only be used in frontend where we split annotations according to their use-site targets.")
    List<AnnotationWithTarget> getUseSiteTargetedAnnotations();

    boolean hasAnnotation(FqName fqName);

    boolean isEmpty();
}
