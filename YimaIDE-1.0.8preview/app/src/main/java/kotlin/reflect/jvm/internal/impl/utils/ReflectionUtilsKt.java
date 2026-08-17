package kotlin.reflect.jvm.internal.impl.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class ReflectionUtilsKt {
    public static final Type[] getGenericParameterTypesWithEnclosingThis(Constructor<?> constructor) {
        constructor.getClass();
        Type outerClassForInnerClassConstructor = getOuterClassForInnerClassConstructor(constructor);
        if (outerClassForInnerClassConstructor == null) {
            return getGenericParameterTypesWithoutEnclosingThis(constructor);
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(outerClassForInnerClassConstructor);
        spreadBuilder.addSpread(getGenericParameterTypesWithoutEnclosingThis(constructor));
        return (Type[]) spreadBuilder.toArray(new Type[spreadBuilder.size()]);
    }

    private static final Type[] getGenericParameterTypesWithoutEnclosingThis(Constructor<?> constructor) {
        Type[] genericParameterTypes = constructor.getGenericParameterTypes();
        if (genericParameterTypes.length == constructor.getParameterTypes().length) {
            Class<?> declaringClass = constructor.getDeclaringClass();
            if (!Modifier.isStatic(declaringClass.getModifiers()) && declaringClass.getDeclaringClass() != null) {
                return (Type[]) ArraysKt.drop(genericParameterTypes, 1).toArray(new Type[0]);
            }
        }
        return genericParameterTypes;
    }

    private static final Type getOuterClassForInnerClassConstructor(Constructor<?> constructor) {
        Class<?> declaringClass = constructor.getDeclaringClass();
        Class<?> declaringClass2 = declaringClass.getDeclaringClass();
        if (declaringClass2 == null || Modifier.isStatic(declaringClass.getModifiers())) {
            return null;
        }
        return declaringClass2;
    }
}
