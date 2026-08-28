package com.swift.sandhook.wrapper;

import android.text.TextUtils;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.MethodReflectParams;
import com.swift.sandhook.annotation.Param;
import com.swift.sandhook.annotation.SkipParamCheck;
import com.swift.sandhook.annotation.ThisObject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class HookWrapper {

    public static class HookEntity {
        public Method backup;
        public boolean backupIsStub;
        public Method hook;
        public boolean hookIsStub;
        public int hookMode;
        public boolean initClass;
        public Class[] pars;
        public boolean resolveDexCache;
        public Member target;

        public HookEntity(Member member) {
            this.hookIsStub = false;
            this.resolveDexCache = true;
            this.backupIsStub = true;
            this.initClass = true;
            this.target = member;
        }

        public HookEntity(Member member, Method method, Method method2) {
            this.hookIsStub = false;
            this.resolveDexCache = true;
            this.backupIsStub = true;
            this.initClass = true;
            this.target = member;
            this.hook = method;
            this.backup = method2;
        }

        public HookEntity(Member member, Method method, Method method2, boolean z) {
            this.hookIsStub = false;
            this.backupIsStub = true;
            this.initClass = true;
            this.target = member;
            this.hook = method;
            this.backup = method2;
            this.resolveDexCache = z;
        }

        public Object callOrigin(Object obj, Object... objArr) {
            return SandHook.callOriginMethod(this.backupIsStub, this.target, this.backup, obj, objArr);
        }

        public boolean isCtor() {
            return this.target instanceof Constructor;
        }
    }

    public static void addHookClass(ClassLoader classLoader, Class<?> cls) throws HookErrorException {
        Class targetHookClass = getTargetHookClass(classLoader, cls);
        if (targetHookClass == null) {
            throw new HookErrorException("error hook wrapper class :" + cls.getName());
        }
        Map<Member, HookEntity> hookMethods = getHookMethods(classLoader, targetHookClass, cls);
        try {
            fillBackupMethod(classLoader, cls, hookMethods);
            Iterator<HookEntity> it = hookMethods.values().iterator();
            while (it.hasNext()) {
                SandHook.hook(it.next());
            }
        } catch (Throwable th) {
            throw new HookErrorException("fillBackupMethod error!", th);
        }
    }

    public static void addHookClass(ClassLoader classLoader, Class<?>... clsArr) throws HookErrorException {
        for (Class<?> cls : clsArr) {
            addHookClass(classLoader, cls);
        }
    }

    public static void addHookClass(Class<?>... clsArr) throws HookErrorException {
        addHookClass((ClassLoader) null, clsArr);
    }

    public static void checkSignature(Member member, Method method, Class[] clsArr) throws HookErrorException {
        Class<?> returnType;
        int i;
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new HookErrorException("hook method must static! - " + method.getName());
        }
        boolean z = member instanceof Constructor;
        String strDecode = "error return type! - ";
        if (z) {
            if (!method.getReturnType().equals(Void.TYPE)) {
                throw new HookErrorException(strDecode + method.getName());
            }
        } else if ((member instanceof Method) && (returnType = ((Method) member).getReturnType()) != method.getReturnType() && !returnType.isAssignableFrom(returnType)) {
            throw new HookErrorException(strDecode + method.getName());
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null) {
            parameterTypes = new Class[0];
        }
        if (clsArr == null) {
            clsArr = new Class[0];
        }
        if (clsArr.length == 0 && parameterTypes.length == 0) {
            return;
        }
        boolean zIsStatic = Modifier.isStatic(member.getModifiers());
        String strDecode2 = "hook method pars must match the origin method! ";
        if (!zIsStatic) {
            int length = parameterTypes.length;
            String strDecode3 = "first par must be this! ";
            if (length == 0) {
                throw new HookErrorException(strDecode3 + method.getName());
            }
            if (parameterTypes[0] != member.getDeclaringClass() && !parameterTypes[0].isAssignableFrom(member.getDeclaringClass())) {
                throw new HookErrorException(strDecode3 + method.getName());
            }
            i = 1;
            if (parameterTypes.length != clsArr.length + 1) {
                throw new HookErrorException(strDecode2 + method.getName());
            }
        } else {
            if (parameterTypes.length != clsArr.length) {
                throw new HookErrorException(strDecode2 + method.getName());
            }
            i = 0;
        }
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            Class<?> cls = parameterTypes[i2 + i];
            Class<?> cls2 = clsArr[i2];
            if (cls != cls2 && !cls.isAssignableFrom(cls2)) {
                throw new HookErrorException(strDecode2 + method.getName());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Class classNameToClass(String str, ClassLoader classLoader) {
        char c;
        switch (str.hashCode()) {
            case -1325958191:
                if (!str.equals("double")) {
                    c = 65535;
                    break;
                } else {
                    c = 3;
                    break;
                }
            case 104431:
                if (str.equals("int")) {
                    c = 5;
                    break;
                }
                break;
            case 3039496:
                if (str.equals("byte")) {
                    c = 1;
                    break;
                }
                break;
            case 3052374:
                if (str.equals("char")) {
                    c = 2;
                    break;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    c = 6;
                    break;
                }
                break;
            case 64711720:
                if (str.equals("boolean")) {
                    c = 0;
                    break;
                }
                break;
            case 97526364:
                if (str.equals("float")) {
                    c = 4;
                    break;
                }
                break;
            case 109413500:
                if (str.equals("short")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Boolean.TYPE;
            case 1:
                return Byte.TYPE;
            case 2:
                return Character.TYPE;
            case 3:
                return Double.TYPE;
            case 4:
                return Float.TYPE;
            case 5:
                return Integer.TYPE;
            case 6:
                return Long.TYPE;
            case 7:
                return Short.TYPE;
            default:
                return classLoader == null ? Class.forName(str) : Class.forName(str, true, classLoader);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.swift.sandhook.wrapper.HookWrapper$HookEntity] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.reflect.Method] */
    private static void fillBackupMethod(ClassLoader classLoader, Class<?> cls, Map<Member, HookEntity> map) throws IllegalAccessException, IllegalArgumentException {
        Field[] declaredFields;
        HookMethodBackup hookMethodBackup;
        try {
            declaredFields = cls.getDeclaredFields();
        } catch (Throwable unused) {
            declaredFields = null;
        }
        if (declaredFields == null || declaredFields.length == 0 || map.isEmpty()) {
            return;
        }
        for (Field field : declaredFields) {
            if (Modifier.isStatic(field.getModifiers()) && (hookMethodBackup = (HookMethodBackup) field.getAnnotation(HookMethodBackup.class)) != null) {
                for (HookEntity hookEntity : map.values()) {
                    if (TextUtils.equals(hookEntity.isCtor() ? "<init>" : hookEntity.target.getName(), hookMethodBackup.value()) && samePars(classLoader, field, hookEntity.pars)) {
                        field.setAccessible(true);
                        if (hookEntity.backup == null) {
                            hookEntity.backup = StubMethodsFactory.getStubMethod();
                            hookEntity.hookIsStub = true;
                            hookEntity.resolveDexCache = false;
                        }
                        if (hookEntity.backup != null) {
                            try {
                                if (field.getType() == Method.class) {
                                    hookEntity = hookEntity.backup;
                                } else if (field.getType() == HookEntity.class) {
                                }
                                field.set(null, hookEntity);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    private static Map<Member, HookEntity> getHookMethods(ClassLoader classLoader, Class cls, Class<?> cls2) throws HookErrorException {
        Method[] declaredMethods;
        HashMap map = new HashMap();
        try {
            declaredMethods = cls2.getDeclaredMethods();
        } catch (Throwable unused) {
            declaredMethods = null;
        }
        if (declaredMethods == null || declaredMethods.length == 0) {
            throw new HookErrorException("error hook wrapper class :" + cls.getName());
        }
        for (Method method : declaredMethods) {
            HookMethod hookMethod = (HookMethod) method.getAnnotation(HookMethod.class);
            HookMethodBackup hookMethodBackup = (HookMethodBackup) method.getAnnotation(HookMethodBackup.class);
            String strDecode = "can not find target method: ";
            if (hookMethod != null) {
                String strValue = hookMethod.value();
                Class<?>[] methodPars = parseMethodPars(classLoader, method);
                try {
                    Member constructor = strValue.equals("<init>") ? cls.getConstructor(methodPars) : cls.getDeclaredMethod(strValue, methodPars);
                    if (!method.isAnnotationPresent(SkipParamCheck.class)) {
                        checkSignature(constructor, method, methodPars);
                    }
                    HookEntity hookEntity = (HookEntity) map.get(constructor);
                    if (hookEntity == null) {
                        hookEntity = new HookEntity(constructor);
                        map.put(constructor, hookEntity);
                    }
                    hookEntity.pars = methodPars;
                    hookEntity.hook = method;
                } catch (NoSuchMethodException e) {
                    throw new HookErrorException(strDecode + strValue, e);
                }
            } else if (hookMethodBackup != null) {
                String strValue2 = hookMethodBackup.value();
                Class<?>[] methodPars2 = parseMethodPars(classLoader, method);
                try {
                    Member constructor2 = strValue2.equals("<init>") ? cls.getConstructor(methodPars2) : cls.getDeclaredMethod(strValue2, methodPars2);
                    if (!method.isAnnotationPresent(SkipParamCheck.class)) {
                        checkSignature(constructor2, method, methodPars2);
                    }
                    HookEntity hookEntity2 = (HookEntity) map.get(constructor2);
                    if (hookEntity2 == null) {
                        hookEntity2 = new HookEntity(constructor2);
                        map.put(constructor2, hookEntity2);
                    }
                    hookEntity2.pars = methodPars2;
                    hookEntity2.backup = method;
                } catch (NoSuchMethodException e2) {
                    throw new HookErrorException(strDecode + strValue2, e2);
                }
            } else {
                continue;
            }
        }
        return map;
    }

    private static int getParsCount(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null) {
            return 0;
        }
        return parameterTypes.length;
    }

    private static Class getRealParType(ClassLoader classLoader, Class cls, Annotation[] annotationArr, boolean z) {
        if (annotationArr != null && annotationArr.length != 0) {
            for (Annotation annotation : annotationArr) {
                if (annotation instanceof Param) {
                    Param param = (Param) annotation;
                    if (TextUtils.isEmpty(param.value())) {
                        return cls;
                    }
                    Class<?> clsClassNameToClass = classNameToClass(param.value(), classLoader);
                    if (z || clsClassNameToClass.equals(cls) || cls.isAssignableFrom(clsClassNameToClass)) {
                        return clsClassNameToClass;
                    }
                    throw new ClassCastException("hook method par cast error!");
                }
            }
        }
        return cls;
    }

    private static Class getTargetHookClass(ClassLoader classLoader, Class<?> cls) {
        HookClass hookClass = (HookClass) cls.getAnnotation(HookClass.class);
        HookReflectClass hookReflectClass = (HookReflectClass) cls.getAnnotation(HookReflectClass.class);
        if (hookClass != null) {
            return hookClass.value();
        }
        if (hookReflectClass != null) {
            try {
                return classLoader == null ? Class.forName(hookReflectClass.value()) : Class.forName(hookReflectClass.value(), true, classLoader);
            } catch (ClassNotFoundException unused) {
            }
        }
        return null;
    }

    private static boolean hasThisObject(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return false;
        }
        return isThisObject(parameterAnnotations[0]);
    }

    private static boolean isThisObject(Annotation[] annotationArr) {
        if (annotationArr != null && annotationArr.length != 0) {
            for (Annotation annotation : annotationArr) {
                if (annotation instanceof ThisObject) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Class[] parseMethodPars(ClassLoader classLoader, Field field) throws HookErrorException {
        MethodParams methodParams = (MethodParams) field.getAnnotation(MethodParams.class);
        MethodReflectParams methodReflectParams = (MethodReflectParams) field.getAnnotation(MethodReflectParams.class);
        if (methodParams != null) {
            return methodParams.value();
        }
        Class[] clsArr = null;
        if (methodReflectParams != null) {
            if (methodReflectParams.value().length == 0) {
                return null;
            }
            clsArr = new Class[methodReflectParams.value().length];
            for (int i = 0; i < methodReflectParams.value().length; i++) {
                try {
                    clsArr[i] = classNameToClass(methodReflectParams.value()[i], classLoader);
                } catch (ClassNotFoundException e) {
                    throw new HookErrorException("hook method pars error: " + field.getName(), e);
                }
            }
        }
        return clsArr;
    }

    private static Class[] parseMethodPars(ClassLoader classLoader, Method method) throws HookErrorException {
        MethodParams methodParams = (MethodParams) method.getAnnotation(MethodParams.class);
        MethodReflectParams methodReflectParams = (MethodReflectParams) method.getAnnotation(MethodReflectParams.class);
        if (methodParams != null) {
            return methodParams.value();
        }
        if (methodReflectParams == null) {
            if (getParsCount(method) <= 0) {
                return null;
            }
            if (getParsCount(method) != 1 || hasThisObject(method)) {
                return parseMethodParsNew(classLoader, method);
            }
            return null;
        }
        if (methodReflectParams.value().length == 0) {
            return null;
        }
        Class[] clsArr = new Class[methodReflectParams.value().length];
        for (int i = 0; i < methodReflectParams.value().length; i++) {
            try {
                clsArr[i] = classNameToClass(methodReflectParams.value()[i], classLoader);
            } catch (ClassNotFoundException e) {
                throw new HookErrorException("hook method pars error: " + method.getName(), e);
            }
        }
        return clsArr;
    }

    private static Class[] parseMethodParsNew(ClassLoader classLoader, Method method) throws HookErrorException {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class[] clsArr = null;
        if (parameterTypes != null && parameterTypes.length != 0) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int i = 0;
            for (int i2 = 0; i2 < parameterAnnotations.length; i2++) {
                Class<?> cls = parameterTypes[i2];
                Annotation[] annotationArr = parameterAnnotations[i2];
                if (i2 != 0) {
                    try {
                        clsArr[i] = getRealParType(classLoader, cls, annotationArr, method.isAnnotationPresent(SkipParamCheck.class));
                        i++;
                    } catch (Exception e) {
                        throw new HookErrorException("hook method <" + method.getName() + "> parser pars error", e);
                    }
                } else if (isThisObject(annotationArr)) {
                    clsArr = new Class[parameterAnnotations.length - 1];
                } else {
                    clsArr = new Class[parameterAnnotations.length];
                    clsArr[i] = getRealParType(classLoader, cls, annotationArr, method.isAnnotationPresent(SkipParamCheck.class));
                    i++;
                }
            }
        }
        return clsArr;
    }

    private static boolean samePars(ClassLoader classLoader, Field field, Class[] clsArr) {
        try {
            Class[] methodPars = parseMethodPars(classLoader, field);
            if (methodPars == null && field.isAnnotationPresent(SkipParamCheck.class)) {
                return true;
            }
            if (clsArr == null) {
                clsArr = new Class[0];
            }
            if (methodPars == null) {
                methodPars = new Class[0];
            }
            if (clsArr.length != methodPars.length) {
                return false;
            }
            for (int i = 0; i < clsArr.length; i++) {
                if (clsArr[i] != methodPars[i]) {
                    return false;
                }
            }
            return true;
        } catch (HookErrorException unused) {
            return false;
        }
    }
}
