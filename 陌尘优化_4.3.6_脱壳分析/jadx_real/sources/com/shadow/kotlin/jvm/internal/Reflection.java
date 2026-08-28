package com.shadow.kotlin.jvm.internal;

import com.shadow.kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class Reflection {
    private static final ReflectionFactory factory;

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        factory = reflectionFactory;
    }

    public static void function(FunctionReference functionReference) {
        factory.getClass();
    }

    public static ClassReference getOrCreateKotlinClass(Class cls) {
        factory.getClass();
        return new ClassReference(cls);
    }

    public static PackageReference getOrCreateKotlinPackage(Class cls) {
        factory.getClass();
        return new PackageReference(cls);
    }

    public static String renderLambdaToString(Lambda lambda) {
        factory.getClass();
        String string = lambda.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public static String renderLambdaToString(RestrictedSuspendLambda restrictedSuspendLambda) {
        factory.getClass();
        String string = restrictedSuspendLambda.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
