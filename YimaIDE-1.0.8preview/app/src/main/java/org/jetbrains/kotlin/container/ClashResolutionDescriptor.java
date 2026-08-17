package org.jetbrains.kotlin.container;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.PlatformSpecificExtension;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\b\u0000\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/container/ClashResolutionDescriptor;", "E", "Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "Lorg/jetbrains/kotlin/container/SingletonDescriptor;", "container", "Lorg/jetbrains/kotlin/container/ComponentContainer;", "resolver", "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "clashedComponents", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/container/ComponentDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/container/ComponentContainer;Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;Ljava/util/List;)V", "createInstance", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "getRegistrations", Argument.Delimiters.none, "Ljava/lang/reflect/Type;", "getDependencies", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClashResolutionDescriptor<E extends PlatformSpecificExtension<E>> extends SingletonDescriptor {
    private final List<ComponentDescriptor> clashedComponents;
    private final PlatformExtensionsClashResolver<E> resolver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ClashResolutionDescriptor(ComponentContainer componentContainer, PlatformExtensionsClashResolver<E> platformExtensionsClashResolver, List<? extends ComponentDescriptor> list) {
        super(componentContainer);
        componentContainer.getClass();
        platformExtensionsClashResolver.getClass();
        list.getClass();
        this.resolver = platformExtensionsClashResolver;
        this.clashedComponents = list;
    }

    @Override // org.jetbrains.kotlin.container.SingletonDescriptor
    public Object createInstance(ValueResolveContext context) {
        context.getClass();
        setState(ComponentState.Initializing);
        List<? extends E> listComputeArguments = ResolveKt.computeArguments(this.clashedComponents);
        listComputeArguments.getClass();
        PlatformSpecificExtension platformSpecificExtensionResolveExtensionsClash = this.resolver.resolveExtensionsClash(listComputeArguments);
        setState(ComponentState.Initialized);
        return platformSpecificExtensionResolveExtensionsClash;
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public Collection<Type> getDependencies(ValueResolveContext context) {
        context.getClass();
        throw new IllegalStateException("Shouldn't be called");
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public Iterable<Type> getRegistrations() {
        throw new IllegalStateException("Shouldn't be called");
    }
}
