package com.swift.sandhook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class SandHookMethodResolver {
    public static Field artMethodField;
    public static boolean canResolvedInJava;
    public static Field dexCacheField;
    public static int dexMethodIndex;
    public static Field dexMethodIndexField;
    public static long entryPointFromCompiledCode;
    public static long entryPointFromInterpreter;
    public static Field fieldEntryPointFromCompiledCode;
    public static Field fieldEntryPointFromInterpreter;
    public static boolean isArtMethod;
    public static long resolvedMethodsAddress;
    public static Field resolvedMethodsField;
    public static Object testArtMethod;
    public static Method testMethod;

    private static void checkSupport() {
        try {
            Field field = SandHook.getField(Method.class, NPStringFog.decode("0F02192C0B150F0A16"));
            artMethodField = field;
            testArtMethod = field.get(testMethod);
            if (SandHook.hasJavaArtMethod() && testArtMethod.getClass() == SandHook.artMethodClass) {
                checkSupportForArtMethod();
                isArtMethod = true;
            } else if (testArtMethod instanceof Long) {
                checkSupportForArtMethodId();
                isArtMethod = false;
            } else {
                canResolvedInJava = false;
            }
        } catch (Exception unused) {
        }
    }

    private static void checkSupportForArtMethod() throws IllegalAccessException, IllegalArgumentException {
        long j;
        try {
            dexMethodIndexField = SandHook.getField(SandHook.artMethodClass, NPStringFog.decode("0A15152C0B150F0A16271E090416"));
        } catch (NoSuchFieldException unused) {
            dexMethodIndexField = SandHook.getField(SandHook.artMethodClass, NPStringFog.decode("03151909010523000A271E090416"));
        }
        Field field = SandHook.getField(Class.class, NPStringFog.decode("0A1515220F020F00"));
        dexCacheField = field;
        Object obj = field.get(testMethod.getDeclaringClass());
        Field field2 = SandHook.getField(obj.getClass(), NPStringFog.decode("1C151E0E021702013F0B04050E0A12"));
        resolvedMethodsField = field2;
        if (field2.get(obj) instanceof Object[]) {
            canResolvedInJava = true;
        }
        try {
            try {
                dexMethodIndex = ((Integer) dexMethodIndexField.get(testArtMethod)).intValue();
            } catch (Throwable unused2) {
            }
            try {
                fieldEntryPointFromCompiledCode = SandHook.getField(SandHook.artMethodClass, NPStringFog.decode("0B1E19131731080C1C1A361F0E0330120C110533020C1E080B00162D1F0904"));
            } catch (Throwable unused3) {
                fieldEntryPointFromCompiledCode = SandHook.getField(SandHook.artMethodClass, NPStringFog.decode("0B1E19131731080C1C1A361F0E0322080802071C08052D0E0300"));
            }
            if (fieldEntryPointFromCompiledCode.getType() == Integer.TYPE) {
                entryPointFromCompiledCode = fieldEntryPointFromCompiledCode.getInt(testArtMethod);
            } else if (fieldEntryPointFromCompiledCode.getType() == Long.TYPE) {
                entryPointFromCompiledCode = fieldEntryPointFromCompiledCode.getLong(testArtMethod);
            }
            Field field3 = SandHook.getField(SandHook.artMethodClass, NPStringFog.decode("0B1E19131731080C1C1A361F0E03280911171C001F041A0415"));
            fieldEntryPointFromInterpreter = field3;
            if (field3.getType() == Integer.TYPE) {
                j = fieldEntryPointFromInterpreter.getInt(testArtMethod);
            } else if (fieldEntryPointFromCompiledCode.getType() != Long.TYPE) {
                return;
            } else {
                j = fieldEntryPointFromInterpreter.getLong(testArtMethod);
            }
            entryPointFromInterpreter = j;
        } catch (Throwable unused4) {
        }
    }

    private static void checkSupportForArtMethodId() throws IllegalAccessException, IllegalArgumentException {
        Field field = SandHook.getField(Method.class, NPStringFog.decode("0A15152C0B150F0A16271E090416"));
        dexMethodIndexField = field;
        dexMethodIndex = ((Integer) field.get(testMethod)).intValue();
        Field field2 = SandHook.getField(Class.class, NPStringFog.decode("0A1515220F020F00"));
        dexCacheField = field2;
        Object obj = field2.get(testMethod.getDeclaringClass());
        Field field3 = SandHook.getField(obj.getClass(), NPStringFog.decode("1C151E0E021702013F0B04050E0A12"));
        resolvedMethodsField = field3;
        Object obj2 = field3.get(obj);
        if (obj2 instanceof Long) {
            canResolvedInJava = false;
            resolvedMethodsAddress = ((Long) obj2).longValue();
        } else if ((obj2 instanceof long[]) || (obj2 instanceof int[])) {
            canResolvedInJava = true;
        }
    }

    public static void init() {
        testMethod = SandHook.testOffsetMethod1;
        checkSupport();
    }

    private static void resolveInJava(Method method, Method method2) throws IllegalAccessException, IllegalArgumentException {
        Object obj = dexCacheField.get(method.getDeclaringClass());
        if (isArtMethod) {
            Object obj2 = artMethodField.get(method2);
            ((Object[]) resolvedMethodsField.get(obj))[((Integer) dexMethodIndexField.get(obj2)).intValue()] = obj2;
            return;
        }
        int iIntValue = ((Integer) dexMethodIndexField.get(method2)).intValue();
        Object obj3 = resolvedMethodsField.get(obj);
        if (obj3 instanceof long[]) {
            ((long[]) obj3)[iIntValue] = ((Long) artMethodField.get(method2)).longValue();
        } else {
            if (!(obj3 instanceof int[])) {
                throw new UnsupportedOperationException(NPStringFog.decode("1B1E4D121B11170A001A"));
            }
            ((int[]) obj3)[iIntValue] = Long.valueOf(((Long) artMethodField.get(method2)).longValue()).intValue();
        }
    }

    private static void resolveInNative(Method method, Method method2) {
        SandHook.ensureMethodCached(method, method2);
    }

    public static void resolveMethod(Method method, Method method2) {
        if (canResolvedInJava && artMethodField != null) {
            try {
                resolveInJava(method, method2);
                return;
            } catch (Exception unused) {
            }
        }
        resolveInNative(method, method2);
    }
}
