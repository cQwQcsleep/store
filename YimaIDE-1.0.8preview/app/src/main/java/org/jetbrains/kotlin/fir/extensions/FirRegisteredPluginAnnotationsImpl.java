package org.jetbrains.kotlin.fir.extensions;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u000e\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u000f2\n\u0010\u0010\u001a\u00060\bj\u0002`\tH\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u000fH\u0014J\"\u0010\u0013\u001a\u00020\u00122\n\u0010\u0010\u001a\u00060\bj\u0002`\t2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0016R\u001e\u0010\u0006\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\b\u0012\u00060\bj\u0002`\t\u0012\b\u0012\u00060\bj\u0002`\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirRegisteredPluginAnnotationsImpl;", "Lorg/jetbrains/kotlin/fir/extensions/AbstractFirRegisteredPluginAnnotations;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "userDefinedAnnotations", "Lcom/google/common/collect/Multimap;", "getAnnotationsWithMetaAnnotation", Argument.Delimiters.none, "metaAnnotation", "saveAnnotationsFromPlugin", Argument.Delimiters.none, "registerUserDefinedAnnotation", "annotationClasses", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRegisteredPluginAnnotationsImpl extends AbstractFirRegisteredPluginAnnotations {
    private final Set<FqName> annotations;
    private final Multimap<FqName, FqName> userDefinedAnnotations;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirRegisteredPluginAnnotationsImpl(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.annotations = new LinkedHashSet();
        LinkedHashMultimap linkedHashMultimapCreate = LinkedHashMultimap.create();
        linkedHashMultimapCreate.getClass();
        this.userDefinedAnnotations = linkedHashMultimapCreate;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
    public Set<FqName> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
    public Collection<FqName> getAnnotationsWithMetaAnnotation(FqName metaAnnotation) {
        metaAnnotation.getClass();
        Collection<FqName> collection = this.userDefinedAnnotations.get(metaAnnotation);
        collection.getClass();
        return collection;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
    public void registerUserDefinedAnnotation(FqName metaAnnotation, Collection<? extends FirRegularClass> annotationClasses) {
        metaAnnotation.getClass();
        annotationClasses.getClass();
        Collection<? extends FirRegularClass> collection = annotationClasses;
        if (!collection.isEmpty()) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (((FirRegularClass) it.next()).getClassKind() != ClassKind.ANNOTATION_CLASS) {
                    w01.a("Failed requirement.");
                    return;
                }
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it2 = collection.iterator();
        while (it2.hasNext()) {
            arrayList.add(((FirRegularClass) it2.next()).getSymbol().getClassId().asSingleFqName());
        }
        CollectionsKt.addAll(getAnnotations(), arrayList);
        this.userDefinedAnnotations.putAll(metaAnnotation, arrayList);
    }

    @Override // org.jetbrains.kotlin.fir.extensions.AbstractFirRegisteredPluginAnnotations
    public void saveAnnotationsFromPlugin(Collection<FqName> annotations) {
        annotations.getClass();
        CollectionsKt.addAll(getAnnotations(), annotations);
    }
}
