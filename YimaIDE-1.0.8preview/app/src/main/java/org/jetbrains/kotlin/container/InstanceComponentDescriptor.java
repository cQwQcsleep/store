package org.jetbrains.kotlin.container;

import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\u0003H\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u001a\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/container/InstanceComponentDescriptor;", "Lorg/jetbrains/kotlin/container/ComponentDescriptor;", "instance", Argument.Delimiters.none, "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getInstance", "()Ljava/lang/Object;", "getValue", "getRegistrations", Argument.Delimiters.none, "Ljava/lang/reflect/Type;", "getDependencies", Argument.Delimiters.none, "Ljava/lang/Class;", "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class InstanceComponentDescriptor implements ComponentDescriptor {
    private final Object instance;

    public InstanceComponentDescriptor(Object obj) {
        obj.getClass();
        this.instance = obj;
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public Collection<Class<?>> getDependencies(ValueResolveContext context) {
        context.getClass();
        return CollectionsKt.emptyList();
    }

    public final Object getInstance() {
        return this.instance;
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public Iterable<Type> getRegistrations() {
        return CacheKt.getInfo(this.instance.getClass()).getRegistrations();
    }

    @Override // org.jetbrains.kotlin.container.ValueDescriptor
    public Object getValue() {
        return this.instance;
    }

    public String toString() {
        return "Instance: ".concat(this.instance.getClass().getSimpleName());
    }
}
