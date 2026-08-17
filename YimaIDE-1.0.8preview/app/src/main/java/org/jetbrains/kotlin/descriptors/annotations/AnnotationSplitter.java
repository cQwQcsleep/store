package org.jetbrains.kotlin.descriptors.annotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationSplitter;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.AnnotationChecker;
import org.jetbrains.kotlin.resolve.lazy.LazyEntity;
import org.jetbrains.kotlin.storage.NotNullLazyValue;
import org.jetbrains.kotlin.storage.StorageKt;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000RH\u0010\u000b\u001a<\u00128\u00126\u0012,\u0012*\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000ej\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f`\u0011\u0012\u0004\u0012\u00020\u00050\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationSplitter;", Argument.Delimiters.none, "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "allAnnotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "applicableTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "<init>", "(Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;Ljava/util/Set;)V", "splitAnnotations", "Lorg/jetbrains/kotlin/storage/NotNullLazyValue;", "Lkotlin/Pair;", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "Lkotlin/collections/HashMap;", "getOtherAnnotations", "getAnnotationsForTarget", "target", "Companion", "LazySplitAnnotations", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationSplitter {
    private static final Set<AnnotationUseSiteTarget> TARGET_PRIORITIES = SetsKt.setOf(new AnnotationUseSiteTarget[]{AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER, AnnotationUseSiteTarget.PROPERTY, AnnotationUseSiteTarget.FIELD});
    private final NotNullLazyValue<Pair<HashMap<AnnotationUseSiteTarget, List<AnnotationDescriptor>>, Annotations>> splitAnnotations;
    private final StorageManager storageManager;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0096\u0082\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationSplitter$LazySplitAnnotations;", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "Lorg/jetbrains/kotlin/resolve/lazy/LazyEntity;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "target", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationSplitter;Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "getTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "annotations", "getAnnotations", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "annotations$delegate", "Lorg/jetbrains/kotlin/storage/NotNullLazyValue;", "forceResolveAllContents", Argument.Delimiters.none, "isEmpty", Argument.Delimiters.none, "hasAnnotation", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "findAnnotation", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "iterator", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class LazySplitAnnotations implements Annotations, LazyEntity {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(LazySplitAnnotations.class, "annotations", "getAnnotations()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", 0)};

        /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
        private final NotNullLazyValue annotations;
        private final AnnotationUseSiteTarget target;
        final /* synthetic */ AnnotationSplitter this$0;

        public LazySplitAnnotations(final AnnotationSplitter annotationSplitter, StorageManager storageManager, AnnotationUseSiteTarget annotationUseSiteTarget) {
            storageManager.getClass();
            this.this$0 = annotationSplitter;
            this.target = annotationUseSiteTarget;
            this.annotations = storageManager.createLazyValue(new Function0() { // from class: org.jetbrains.kotlin.descriptors.annotations.a
                public final Object invoke() {
                    return AnnotationSplitter.LazySplitAnnotations.a(annotationSplitter, this);
                }
            });
        }

        public static Annotations a(AnnotationSplitter annotationSplitter, LazySplitAnnotations lazySplitAnnotations) {
            Annotations annotationsCreate;
            Pair pair = (Pair) annotationSplitter.splitAnnotations.invoke();
            HashMap map = (HashMap) pair.component1();
            Annotations annotations = (Annotations) pair.component2();
            AnnotationUseSiteTarget annotationUseSiteTarget = lazySplitAnnotations.target;
            if (annotationUseSiteTarget == null) {
                return annotations;
            }
            List<? extends AnnotationDescriptor> list = (List) map.get(annotationUseSiteTarget);
            return (list == null || (annotationsCreate = Annotations.INSTANCE.create(list)) == null) ? Annotations.INSTANCE.getEMPTY() : annotationsCreate;
        }

        private final Annotations getAnnotations() {
            return (Annotations) StorageKt.getValue(this.annotations, this, $$delegatedProperties[0]);
        }

        @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
        /* JADX INFO: renamed from: findAnnotation */
        public AnnotationDescriptor mo108findAnnotation(FqName fqName) {
            fqName.getClass();
            return getAnnotations().mo108findAnnotation(fqName);
        }

        public void forceResolveAllContents() {
            for (AnnotationDescriptor annotationDescriptor : this) {
            }
        }

        public final AnnotationUseSiteTarget getTarget() {
            return this.target;
        }

        @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
        @Deprecated(message = "This method should only be used in frontend where we split annotations according to their use-site targets.")
        public /* bridge */ List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
            return Annotations.DefaultImpls.getUseSiteTargetedAnnotations(this);
        }

        @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
        public boolean hasAnnotation(FqName fqName) {
            fqName.getClass();
            return getAnnotations().hasAnnotation(fqName);
        }

        @Override // org.jetbrains.kotlin.descriptors.annotations.Annotations
        public boolean isEmpty() {
            return getAnnotations().isEmpty();
        }

        @Override // java.lang.Iterable
        public Iterator<AnnotationDescriptor> iterator() {
            return getAnnotations().iterator();
        }

        public String toString() {
            return getAnnotations().toString();
        }
    }

    public AnnotationSplitter(StorageManager storageManager, final Annotations annotations, final Set<? extends AnnotationUseSiteTarget> set) {
        storageManager.getClass();
        annotations.getClass();
        set.getClass();
        this.storageManager = storageManager;
        this.splitAnnotations = storageManager.createLazyValue(new Function0() { // from class: g90
            public final Object invoke() {
                return AnnotationSplitter.a(set, annotations);
            }
        });
    }

    public static Pair a(Set set, Annotations annotations) {
        KotlinTarget kotlinTarget;
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        Set setIntersect = CollectionsKt.intersect(set, TARGET_PRIORITIES);
        for (AnnotationDescriptor annotationDescriptor : annotations) {
            Iterator<AnnotationUseSiteTarget> it = TARGET_PRIORITIES.iterator();
            while (true) {
                if (!it.hasNext()) {
                    arrayList.add(annotationDescriptor);
                    break;
                }
                AnnotationUseSiteTarget next = it.next();
                if (setIntersect.contains(next) && (kotlinTarget = KotlinTarget.INSTANCE.getUSE_SITE_MAPPING().get(next)) != null && AnnotationChecker.Companion.applicableTargetSet(annotationDescriptor).contains(kotlinTarget)) {
                    Object arrayList2 = map.get(next);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        map.put(next, arrayList2);
                    }
                    ((List) arrayList2).add(annotationDescriptor);
                    break;
                }
            }
        }
        for (AnnotationWithTarget annotationWithTarget : annotations.getUseSiteTargetedAnnotations()) {
            AnnotationDescriptor annotationDescriptorComponent1 = annotationWithTarget.component1();
            AnnotationUseSiteTarget annotationUseSiteTargetComponent2 = annotationWithTarget.component2();
            if (set.contains(annotationUseSiteTargetComponent2)) {
                Object arrayList3 = map.get(annotationUseSiteTargetComponent2);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                    map.put(annotationUseSiteTargetComponent2, arrayList3);
                }
                ((List) arrayList3).add(annotationDescriptorComponent1);
            }
        }
        return TuplesKt.to(map, Annotations.INSTANCE.create(arrayList));
    }

    public final Annotations getAnnotationsForTarget(AnnotationUseSiteTarget target) {
        target.getClass();
        return new LazySplitAnnotations(this, this.storageManager, target);
    }

    public final Annotations getOtherAnnotations() {
        return new LazySplitAnnotations(this, this.storageManager, null);
    }
}
