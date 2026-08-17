package org.jetbrains.kotlin.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SpreadBuilder;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0005¨\u0006\u000b"}, d2 = {"genericParameterTypesWithoutEnclosingThis", "", "Ljava/lang/reflect/Type;", "Ljava/lang/reflect/Constructor;", "getGenericParameterTypesWithoutEnclosingThis", "(Ljava/lang/reflect/Constructor;)[Ljava/lang/reflect/Type;", "outerClassForInnerClassConstructor", "getOuterClassForInnerClassConstructor", "(Ljava/lang/reflect/Constructor;)Ljava/lang/reflect/Type;", "genericParameterTypesWithEnclosingThis", "getGenericParameterTypesWithEnclosingThis", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
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
