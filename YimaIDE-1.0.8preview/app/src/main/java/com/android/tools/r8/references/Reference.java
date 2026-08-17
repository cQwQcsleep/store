package com.android.tools.r8.references;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0929Wj;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Reference {
    static final /* synthetic */ boolean a = true;
    public static PrimitiveReference BOOL = PrimitiveReference.a;
    public static PrimitiveReference BYTE = PrimitiveReference.b;
    public static PrimitiveReference CHAR = PrimitiveReference.c;
    public static PrimitiveReference SHORT = PrimitiveReference.d;
    public static PrimitiveReference INT = PrimitiveReference.e;
    public static PrimitiveReference FLOAT = PrimitiveReference.f;
    public static PrimitiveReference LONG = PrimitiveReference.g;
    public static PrimitiveReference DOUBLE = PrimitiveReference.h;

    public static ArrayReference array(TypeReference typeReference, int i) {
        return ArrayReference.a(typeReference, i);
    }

    public static ArrayReference arrayFromDescriptor(String str) {
        return ArrayReference.a(str);
    }

    public static MethodReference classConstructor(ClassReference classReference) {
        return method(classReference, "<clinit>", Collections.EMPTY_LIST, null);
    }

    public static ClassReference classFromBinaryName(String str) {
        return classFromDescriptor(C0929Wj.l(str));
    }

    public static ClassReference classFromClass(Class<?> cls) {
        return classFromTypeName(cls.getTypeName());
    }

    public static ClassReference classFromDescriptor(String str) {
        return ClassReference.a(str);
    }

    public static ClassReference classFromTypeName(String str) {
        return classFromDescriptor(C0929Wj.I(str));
    }

    public static FieldReference field(ClassReference classReference, String str, TypeReference typeReference) {
        return new FieldReference(classReference, str, typeReference);
    }

    public static FieldReference fieldFromField(Field field) {
        Class<?> declaringClass = field.getDeclaringClass();
        return field(classFromClass(declaringClass), field.getName(), typeFromDescriptor(C0929Wj.I(field.getType().getTypeName())));
    }

    public static MethodReference method(ClassReference classReference, String str, List<TypeReference> list, TypeReference typeReference) {
        return new MethodReference(classReference, str, AbstractC0551Hu.a(list), typeReference);
    }

    public static MethodReference methodFromDescriptor(String str, String str2, String str3) {
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (String str4 : C0929Wj.e(str3)) {
            c0473EuG.a(typeFromDescriptor(str4));
        }
        String strU = C0929Wj.u(str3);
        return method(classFromDescriptor(str), str2, c0473EuG.a(), strU.equals("V") ? null : typeFromDescriptor(strU));
    }

    public static MethodReference methodFromMethod(Method method) {
        String name = method.getName();
        Class<?> declaringClass = method.getDeclaringClass();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> returnType = method.getReturnType();
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (Class<?> cls : parameterTypes) {
            c0473EuG.a(typeFromDescriptor(C0929Wj.I(cls.getTypeName())));
        }
        return method(classFromClass(declaringClass), name, c0473EuG.a(), returnType == Void.TYPE ? null : typeFromDescriptor(C0929Wj.I(returnType.getTypeName())));
    }

    public static PackageReference packageFromPackage(Package r1) {
        return new PackageReference(r1.getName());
    }

    public static PackageReference packageFromString(String str) {
        return new PackageReference(str);
    }

    public static PrimitiveReference primitiveFromDescriptor(String str) {
        return PrimitiveReference.a(str);
    }

    public static TypeReference returnTypeFromDescriptor(String str) {
        if (str.equals("V")) {
            return null;
        }
        return typeFromDescriptor(str);
    }

    public static TypeReference returnTypeFromTypeName(String str) {
        if (str.equals("void")) {
            return null;
        }
        return typeFromTypeName(str);
    }

    public static TypeReference typeFromDescriptor(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt != 'L') {
            return cCharAt != '[' ? primitiveFromDescriptor(str) : arrayFromDescriptor(str);
        }
        return classFromDescriptor(str);
    }

    public static TypeReference typeFromTypeName(String str) {
        return typeFromDescriptor(C0929Wj.I(str));
    }

    public static MethodReference methodFromDescriptor(ClassReference classReference, String str, String str2) {
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (String str3 : C0929Wj.e(str2)) {
            c0473EuG.a(typeFromDescriptor(str3));
        }
        String strU = C0929Wj.u(str2);
        return method(classReference, str, c0473EuG.a(), strU.equals("V") ? null : typeFromDescriptor(strU));
    }

    public static MethodReference methodFromMethod(Executable executable) {
        if (executable instanceof Constructor) {
            return methodFromMethod((Constructor<?>) executable);
        }
        if (a || (executable instanceof Method)) {
            return methodFromMethod((Method) executable);
        }
        x1f.a();
        return null;
    }

    public static MethodReference methodFromMethod(Constructor<?> constructor) {
        Class<?> declaringClass = constructor.getDeclaringClass();
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (Class<?> cls : parameterTypes) {
            c0473EuG.a(typeFromDescriptor(C0929Wj.I(cls.getTypeName())));
        }
        return method(classFromClass(declaringClass), "<init>", c0473EuG.a(), null);
    }
}
