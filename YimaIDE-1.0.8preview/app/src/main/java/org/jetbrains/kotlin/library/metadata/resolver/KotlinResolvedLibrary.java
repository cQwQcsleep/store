package org.jetbrains.kotlin.library.metadata.resolver;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.KotlinLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/resolver/KotlinResolvedLibrary;", "", "library", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "getLibrary", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "resolvedDependencies", "", "getResolvedDependencies", "()Ljava/util/List;", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface KotlinResolvedLibrary {
    KotlinLibrary getLibrary();

    List<KotlinResolvedLibrary> getResolvedDependencies();
}
