package org.jetbrains.kotlin.container;

import com.intellij.util.containers.MultiMap;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0000J \u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000bj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/container/ComponentRegistry;", Argument.Delimiters.none, "<init>", "()V", "buildRegistrationMap", "Lcom/intellij/util/containers/MultiMap;", "Ljava/lang/reflect/Type;", "Lorg/jetbrains/kotlin/container/ComponentDescriptor;", "descriptors", Argument.Delimiters.none, "registrationMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "addAll", Argument.Delimiters.none, "tryGetEntry", "request", "other", "resolveClashesIfAny", "container", "Lorg/jetbrains/kotlin/container/ComponentContainer;", "clashResolvers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComponentRegistry {
    private final HashMap<Type, Object> registrationMap = new HashMap<>();

    public final void addAll(Collection<? extends ComponentDescriptor> descriptors) {
        descriptors.getClass();
        for (Map.Entry entry : buildRegistrationMap(descriptors).entrySet()) {
            Object obj = this.registrationMap.get(entry.getKey());
            if (obj != null || ((Collection) entry.getValue()).size() > 1) {
                ArrayList arrayList = new ArrayList();
                if (obj instanceof Collection) {
                    arrayList.addAll((Collection) obj);
                } else if (obj != null) {
                    arrayList.add((ComponentDescriptor) obj);
                }
                Object value = entry.getValue();
                value.getClass();
                arrayList.addAll((Collection) value);
                HashMap<Type, Object> map = this.registrationMap;
                Object key = entry.getKey();
                ComponentDescriptor componentDescriptor = (ComponentDescriptor) CollectionsKt.singleOrNull(arrayList);
                Object obj2 = arrayList;
                if (componentDescriptor != null) {
                    obj2 = componentDescriptor;
                }
                map.put((Type) key, obj2);
            } else {
                HashMap<Type, Object> map2 = this.registrationMap;
                Object key2 = entry.getKey();
                Object value2 = entry.getValue();
                value2.getClass();
                map2.put((Type) key2, CollectionsKt.single((Iterable) value2));
            }
        }
    }

    public final MultiMap<Type, ComponentDescriptor> buildRegistrationMap(Collection<? extends ComponentDescriptor> descriptors) {
        descriptors.getClass();
        MultiMap<Type, ComponentDescriptor> multiMap = new MultiMap<>();
        for (ComponentDescriptor componentDescriptor : descriptors) {
            Iterator<Type> it = componentDescriptor.getRegistrations().iterator();
            while (it.hasNext()) {
                multiMap.putValue(it.next(), componentDescriptor);
            }
        }
        return multiMap;
    }

    public final void resolveClashesIfAny(ComponentContainer container, List<? extends PlatformExtensionsClashResolver<?>> clashResolvers) {
        container.getClass();
        clashResolvers.getClass();
        for (PlatformExtensionsClashResolver<?> platformExtensionsClashResolver : clashResolvers) {
            Object obj = this.registrationMap.get(platformExtensionsClashResolver.getApplicableTo());
            Collection collection = obj instanceof Collection ? (Collection) obj : null;
            if (collection != null && collection.size() > 1) {
                this.registrationMap.put(platformExtensionsClashResolver.getApplicableTo(), new ClashResolutionDescriptor(container, platformExtensionsClashResolver, CollectionsKt.toList(collection)));
            }
        }
    }

    public final Collection<ComponentDescriptor> tryGetEntry(Type request) {
        request.getClass();
        Object obj = this.registrationMap.get(request);
        if (obj instanceof Collection) {
            return (Collection) obj;
        }
        return obj == null ? CollectionsKt.emptyList() : CollectionsKt.listOf((ComponentDescriptor) obj);
    }

    public final void addAll(ComponentRegistry other) {
        other.getClass();
        if (this.registrationMap.isEmpty()) {
            this.registrationMap.putAll(other.registrationMap);
        } else {
            k2d.a("Can only copy entries from another component registry into an empty component registry");
        }
    }
}
