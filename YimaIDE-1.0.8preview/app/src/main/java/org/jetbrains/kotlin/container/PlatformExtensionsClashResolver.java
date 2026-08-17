package org.jetbrains.kotlin.container;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.PlatformSpecificExtension;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\b&\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0002\u000e\u000fB\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00028\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH&¢\u0006\u0002\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "E", "Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", Argument.Delimiters.none, "applicableTo", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getApplicableTo", "()Ljava/lang/Class;", "resolveExtensionsClash", "extensions", Argument.Delimiters.none, "(Ljava/util/List;)Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "FallbackToDefault", "FirstWins", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PlatformExtensionsClashResolver<E extends PlatformSpecificExtension<E>> {
    private final Class<E> applicableTo;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\n\u001a\u00028\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\fH\u0016¢\u0006\u0002\u0010\rR\u0010\u0010\u0004\u001a\u00028\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver$FallbackToDefault;", "E", "Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "defaultValue", "applicableTo", "Ljava/lang/Class;", "<init>", "(Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;Ljava/lang/Class;)V", "Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "resolveExtensionsClash", "extensions", Argument.Delimiters.none, "(Ljava/util/List;)Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FallbackToDefault<E extends PlatformSpecificExtension<E>> extends PlatformExtensionsClashResolver<E> {
        private final E defaultValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FallbackToDefault(E e, Class<E> cls) {
            super(cls);
            e.getClass();
            cls.getClass();
            this.defaultValue = e;
        }

        @Override // org.jetbrains.kotlin.container.PlatformExtensionsClashResolver
        public E resolveExtensionsClash(List<? extends E> extensions) {
            extensions.getClass();
            return this.defaultValue;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\b\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver$FirstWins;", "E", "Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "applicableTo", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "resolveExtensionsClash", "extensions", Argument.Delimiters.none, "(Ljava/util/List;)Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirstWins<E extends PlatformSpecificExtension<E>> extends PlatformExtensionsClashResolver<E> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirstWins(Class<E> cls) {
            super(cls);
            cls.getClass();
        }

        @Override // org.jetbrains.kotlin.container.PlatformExtensionsClashResolver
        public E resolveExtensionsClash(List<? extends E> extensions) {
            extensions.getClass();
            return (E) CollectionsKt.first(extensions);
        }
    }

    public PlatformExtensionsClashResolver(Class<E> cls) {
        cls.getClass();
        this.applicableTo = cls;
    }

    public final Class<E> getApplicableTo() {
        return this.applicableTo;
    }

    public abstract E resolveExtensionsClash(List<? extends E> extensions);
}
