package org.jetbrains.kotlin.container;

import defpackage.bhc;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\u001a\u001e\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u001a\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a0\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0013"}, d2 = {"computeArguments", Argument.Delimiters.none, Argument.Delimiters.none, "argumentDescriptors", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "bindToConstructor", "Lorg/jetbrains/kotlin/container/ConstructorBinding;", "Ljava/lang/Class;", "containerId", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "bindToMethod", "Lorg/jetbrains/kotlin/container/MethodBinding;", "Ljava/lang/reflect/Method;", "bindArguments", "Ljava/lang/reflect/Member;", "parameters", "Ljava/lang/reflect/Type;", "org.jetbrains.kotlin:container"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolveKt {
    private static final List<ValueDescriptor> bindArguments(Member member, String str, List<? extends Type> list, ValueResolveContext valueResolveContext) throws UnresolvedDependenciesException {
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = null;
        for (Type type : list) {
            ValueDescriptor valueDescriptorResolve = valueResolveContext.resolve(type);
            if (valueDescriptorResolve == null) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(type);
            } else {
                arrayList.add(valueDescriptorResolve);
            }
        }
        if (arrayList2 == null) {
            return arrayList;
        }
        throw new UnresolvedDependenciesException(str + ": Dependencies for `" + member + "` cannot be satisfied:\n  " + arrayList2);
    }

    public static final ConstructorBinding bindToConstructor(Class<?> cls, String str, ValueResolveContext valueResolveContext) {
        cls.getClass();
        str.getClass();
        valueResolveContext.getClass();
        ConstructorInfo constructorInfo = CacheKt.getInfo(cls).getConstructorInfo();
        if (constructorInfo != null) {
            Constructor<?> constructor = constructorInfo.getConstructor();
            return new ConstructorBinding(constructor, bindArguments(constructor, str, constructorInfo.getParameters(), valueResolveContext));
        }
        StringBuilder sb = new StringBuilder("No constructor for ");
        sb.append(cls);
        bhc.a(sb, ": ", CacheKt.getInfo(cls), " in ", str);
        return null;
    }

    public static final MethodBinding bindToMethod(Method method, String str, ValueResolveContext valueResolveContext) {
        method.getClass();
        str.getClass();
        valueResolveContext.getClass();
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        genericParameterTypes.getClass();
        return new MethodBinding(method, bindArguments(method, str, ArraysKt.toList(genericParameterTypes), valueResolveContext));
    }

    public static final List<Object> computeArguments(List<? extends ValueDescriptor> list) {
        list.getClass();
        List<? extends ValueDescriptor> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((ValueDescriptor) it.next()).getValue());
        }
        return arrayList;
    }
}
