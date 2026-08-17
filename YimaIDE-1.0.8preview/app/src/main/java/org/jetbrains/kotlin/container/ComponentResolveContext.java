package org.jetbrains.kotlin.container;

import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/container/ComponentResolveContext;", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "container", "Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "requestingDescriptor", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "parentContext", "<init>", "(Lorg/jetbrains/kotlin/container/StorageComponentContainer;Lorg/jetbrains/kotlin/container/ValueDescriptor;Lorg/jetbrains/kotlin/container/ValueResolveContext;)V", "getContainer", "()Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "getRequestingDescriptor", "()Lorg/jetbrains/kotlin/container/ValueDescriptor;", "getParentContext", "()Lorg/jetbrains/kotlin/container/ValueResolveContext;", "resolve", "registration", "Ljava/lang/reflect/Type;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComponentResolveContext implements ValueResolveContext {
    private final StorageComponentContainer container;
    private final ValueResolveContext parentContext;
    private final ValueDescriptor requestingDescriptor;

    public ComponentResolveContext(StorageComponentContainer storageComponentContainer, ValueDescriptor valueDescriptor, ValueResolveContext valueResolveContext) {
        storageComponentContainer.getClass();
        valueDescriptor.getClass();
        this.container = storageComponentContainer;
        this.requestingDescriptor = valueDescriptor;
        this.parentContext = valueResolveContext;
    }

    public final StorageComponentContainer getContainer() {
        return this.container;
    }

    public final ValueResolveContext getParentContext() {
        return this.parentContext;
    }

    public final ValueDescriptor getRequestingDescriptor() {
        return this.requestingDescriptor;
    }

    @Override // org.jetbrains.kotlin.container.ValueResolveContext
    public ValueDescriptor resolve(Type registration) {
        registration.getClass();
        ValueDescriptor valueDescriptorResolve = this.container.resolve(registration, this);
        if (valueDescriptorResolve != null) {
            return valueDescriptorResolve;
        }
        ValueResolveContext valueResolveContext = this.parentContext;
        if (valueResolveContext != null) {
            return valueResolveContext.resolve(registration);
        }
        return null;
    }

    public String toString() {
        return "for " + this.requestingDescriptor + " in " + this.container;
    }

    public /* synthetic */ ComponentResolveContext(StorageComponentContainer storageComponentContainer, ValueDescriptor valueDescriptor, ValueResolveContext valueResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageComponentContainer, valueDescriptor, (i & 4) != 0 ? null : valueResolveContext);
    }
}
