package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/container/ImplicitSingletonTypeComponentDescriptor;", "Lorg/jetbrains/kotlin/container/SingletonTypeComponentDescriptor;", "container", "Lorg/jetbrains/kotlin/container/ComponentContainer;", "klass", "Ljava/lang/Class;", "<init>", "(Lorg/jetbrains/kotlin/container/ComponentContainer;Ljava/lang/Class;)V", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitSingletonTypeComponentDescriptor extends SingletonTypeComponentDescriptor {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImplicitSingletonTypeComponentDescriptor(ComponentContainer componentContainer, Class<?> cls) {
        super(componentContainer, cls);
        componentContainer.getClass();
        cls.getClass();
    }

    @Override // org.jetbrains.kotlin.container.SingletonTypeComponentDescriptor
    public String toString() {
        return "Implicit: ".concat(getKlass().getSimpleName());
    }
}
