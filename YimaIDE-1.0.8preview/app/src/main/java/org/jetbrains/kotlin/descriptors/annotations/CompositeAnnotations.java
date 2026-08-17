package org.jetbrains.kotlin.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.annotations.CompositeAnnotations;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\"\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003H\u0016J\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012H\u0096\u0082\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/CompositeAnnotations;", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "delegates", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;)V", "isEmpty", Argument.Delimiters.none, "hasAnnotation", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "findAnnotation", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "getUseSiteTargetedAnnotations", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationWithTarget;", "iterator", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompositeAnnotations implements Annotations {
    private final List<Annotations> delegates;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CompositeAnnotations(Annotations... annotationsArr) {
        this((List<? extends Annotations>) ArraysKt.toList(annotationsArr));
        annotationsArr.getClass();
    }

    public static AnnotationDescriptor a(FqName fqName, Annotations annotations) {
        annotations.getClass();
        return annotations.mo108findAnnotation(fqName);
    }

    public static Sequence b(Annotations annotations) {
        annotations.getClass();
        return CollectionsKt.asSequence(annotations);
    }

    @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
    /* JADX INFO: renamed from: findAnnotation */
    public AnnotationDescriptor mo108findAnnotation(final FqName fqName) {
        fqName.getClass();
        return (AnnotationDescriptor) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.delegates), new Function1() { // from class: wn2
            public final Object invoke(Object obj) {
                return CompositeAnnotations.a(fqName, (Annotations) obj);
            }
        }));
    }

    @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        List<Annotations> list = this.delegates;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((Annotations) it.next()).getUseSiteTargetedAnnotations());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName) {
        fqName.getClass();
        Iterator it = CollectionsKt.asSequence(this.delegates).iterator();
        while (it.hasNext()) {
            if (((Annotations) it.next()).hasAnnotation(fqName)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
    public boolean isEmpty() {
        List<Annotations> list = this.delegates;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((Annotations) it.next()).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotationDescriptor> iterator() {
        return SequencesKt.flatMap(CollectionsKt.asSequence(this.delegates), new Function1() { // from class: vn2
            public final Object invoke(Object obj) {
                return CompositeAnnotations.b((Annotations) obj);
            }
        }).iterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeAnnotations(List<? extends Annotations> list) {
        list.getClass();
        this.delegates = list;
    }
}
