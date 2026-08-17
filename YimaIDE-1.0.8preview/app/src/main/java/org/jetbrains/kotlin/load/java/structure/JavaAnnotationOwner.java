package org.jetbrains.kotlin.load.java.structure;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\fH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationOwner;", "Lorg/jetbrains/kotlin/load/java/structure/JavaElement;", "annotations", "", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "getAnnotations", "()Ljava/util/Collection;", "isDeprecatedInJavaDoc", "", "()Z", "findAnnotation", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface JavaAnnotationOwner extends JavaElement {
    /* JADX INFO: renamed from: findAnnotation */
    JavaAnnotation mo503findAnnotation(FqName fqName);

    /* JADX INFO: renamed from: getAnnotations */
    Collection<JavaAnnotation> mo504getAnnotations();

    /* JADX INFO: renamed from: isDeprecatedInJavaDoc */
    boolean mo508isDeprecatedInJavaDoc();
}
