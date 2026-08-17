package org.jetbrains.kotlin.library.metadata.resolver.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.metadata.resolver.KotlinResolvedLibrary;
import org.jetbrains.kotlin.library.metadata.resolver.impl.KotlinResolvedLibraryImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u000fJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/resolver/impl/KotlinResolvedLibraryImpl;", "Lorg/jetbrains/kotlin/library/metadata/resolver/KotlinResolvedLibrary;", "library", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "<init>", "(Lorg/jetbrains/kotlin/library/KotlinLibrary;)V", "getLibrary", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "resolvedDependencies", "", "getResolvedDependencies", "()Ljava/util/List;", "addDependency", "", "resolvedLibrary", "addDependency$org_jetbrains_kotlin_kotlin_util_klib_metadata", "toString", "", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class KotlinResolvedLibraryImpl implements KotlinResolvedLibrary {
    private final KotlinLibrary library;
    private final List<KotlinResolvedLibrary> resolvedDependencies;

    public KotlinResolvedLibraryImpl(KotlinLibrary kotlinLibrary) {
        kotlinLibrary.getClass();
        this.library = kotlinLibrary;
        this.resolvedDependencies = new ArrayList();
    }

    public static CharSequence a(KotlinResolvedLibrary kotlinResolvedLibrary) {
        kotlinResolvedLibrary.getClass();
        return kotlinResolvedLibrary.getLibrary().toString();
    }

    public final boolean addDependency$org_jetbrains_kotlin_kotlin_util_klib_metadata(KotlinResolvedLibrary resolvedLibrary) {
        resolvedLibrary.getClass();
        return getResolvedDependencies().add(resolvedLibrary);
    }

    @Override // org.jetbrains.kotlin.library.metadata.resolver.KotlinResolvedLibrary
    public KotlinLibrary getLibrary() {
        return this.library;
    }

    @Override // org.jetbrains.kotlin.library.metadata.resolver.KotlinResolvedLibrary
    public List<KotlinResolvedLibrary> getResolvedDependencies() {
        return this.resolvedDependencies;
    }

    public String toString() {
        return "library=" + getLibrary() + ", dependsOn=" + CollectionsKt.joinToString$default(getResolvedDependencies(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: dd8
            public final Object invoke(Object obj) {
                return KotlinResolvedLibraryImpl.a((KotlinResolvedLibrary) obj);
            }
        }, 31, (Object) null);
    }
}
