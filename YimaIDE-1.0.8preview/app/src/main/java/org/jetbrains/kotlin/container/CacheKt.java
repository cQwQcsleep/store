package org.jetbrains.kotlin.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.CacheKt;
import org.jetbrains.kotlin.utils.ReflectionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002\u001a\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002\u001a\u0016\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002\u001a\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002\u001a\u001a\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00022\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002\u001a\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002¨\u0006\u0013"}, d2 = {"getInfo", "Lorg/jetbrains/kotlin/container/ClassInfo;", "Ljava/lang/Class;", "traverseClass", "c", "getSetterInfos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/container/SetterInfo;", "getConstructorInfo", "Lorg/jetbrains/kotlin/container/ConstructorInfo;", "collectInterfacesRecursive", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Ljava/lang/reflect/Type;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "getDefaultImplementation", "klass", "getRegistrations", "org.jetbrains.kotlin:container"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CacheKt {
    public static Type a(Type type) {
        type.getClass();
        if (type instanceof Class) {
            return ((Class) type).getGenericSuperclass();
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Class cls = rawType instanceof Class ? (Class) rawType : null;
            if (cls != null) {
                return cls.getGenericSuperclass();
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0019  */
    private static final void collectInterfacesRecursive(Type type, Set<Type> set) {
        Class cls;
        Type[] genericInterfaces;
        if (type instanceof Class) {
            cls = (Class) type;
        } else if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                cls = (Class) rawType;
            } else {
                cls = null;
            }
        } else {
            cls = null;
        }
        if (cls == null || (genericInterfaces = cls.getGenericInterfaces()) == null) {
            return;
        }
        for (Type type2 : genericInterfaces) {
            type2.getClass();
            if (set.add(type2)) {
                collectInterfacesRecursive(type2, set);
            }
        }
    }

    private static final ConstructorInfo getConstructorInfo(Class<?> cls) {
        if (Modifier.isAbstract(cls.getModifiers()) || cls.isPrimitive()) {
            return null;
        }
        Constructor<?>[] constructors = cls.getConstructors();
        constructors.getClass();
        ArrayList arrayList = new ArrayList();
        for (Constructor<?> constructor : constructors) {
            if (Modifier.isPublic(constructor.getModifiers()) && !constructor.isSynthetic()) {
                arrayList.add(constructor);
            }
        }
        if (arrayList.size() != 1) {
            return null;
        }
        Constructor constructor2 = (Constructor) CollectionsKt.single(arrayList);
        constructor2.getClass();
        return new ConstructorInfo(constructor2, ArraysKt.toList(ReflectionUtilsKt.getGenericParameterTypesWithEnclosingThis(constructor2)));
    }

    private static final Class<?> getDefaultImplementation(Class<?> cls) {
        DefaultImplementation defaultImplementation = (DefaultImplementation) cls.getAnnotation(DefaultImplementation.class);
        if (defaultImplementation != null) {
            return defaultImplementation.impl();
        }
        return null;
    }

    public static final ClassInfo getInfo(Class<?> cls) {
        cls.getClass();
        return ClassTraversalCache.INSTANCE.getClassInfo(cls);
    }

    private static final List<Type> getRegistrations(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        Sequence sequenceGenerateSequence = SequencesKt.generateSequence(cls, new Function1() { // from class: g91
            public final Object invoke(Object obj) {
                return CacheKt.a((Type) obj);
            }
        });
        CollectionsKt.addAll(arrayList, sequenceGenerateSequence);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = sequenceGenerateSequence.iterator();
        while (it.hasNext()) {
            collectInterfacesRecursive((Type) it.next(), linkedHashSet);
        }
        arrayList.addAll(linkedHashSet);
        arrayList.remove(Object.class);
        return arrayList;
    }

    private static final List<SetterInfo> getSetterInfos(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        Method[] methods = cls.getMethods();
        methods.getClass();
        for (Method method : methods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            declaredAnnotations.getClass();
            for (Annotation annotation : declaredAnnotations) {
                if (StringsKt.endsWith$default(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation)).getName(), ".Inject", false, 2, (Object) null)) {
                    Type[] genericParameterTypes = method.getGenericParameterTypes();
                    genericParameterTypes.getClass();
                    arrayList.add(new SetterInfo(method, ArraysKt.toList(genericParameterTypes)));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassInfo traverseClass(Class<?> cls) {
        return new ClassInfo(getConstructorInfo(cls), getSetterInfos(cls), getRegistrations(cls), getDefaultImplementation(cls));
    }
}
