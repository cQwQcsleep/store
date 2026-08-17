package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/container/UnresolvedServiceException;", "Ljava/lang/IllegalArgumentException;", "Lkotlin/IllegalArgumentException;", "container", "Lorg/jetbrains/kotlin/container/ComponentProvider;", "request", "Ljava/lang/Class;", "<init>", "(Lorg/jetbrains/kotlin/container/ComponentProvider;Ljava/lang/Class;)V", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnresolvedServiceException extends IllegalArgumentException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnresolvedServiceException(ComponentProvider componentProvider, Class<?> cls) {
        super("Unresolved service: " + cls + " in " + componentProvider);
        componentProvider.getClass();
        cls.getClass();
    }
}
