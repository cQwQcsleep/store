package com.swift.sandhook;

import com.swift.sandhook.annotation.HookMode;
import com.swift.sandhook.blacklist.HookBlackList;
import com.swift.sandhook.utils.ClassStatusUtils;
import com.swift.sandhook.utils.FileUtils;
import com.swift.sandhook.utils.ReflectionUtils;
import com.swift.sandhook.utils.Unsafe;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class SandHook {
    public static Class artMethodClass;
    private static HookModeCallBack hookModeCallBack;
    private static HookResultCallBack hookResultCallBack;
    public static Field nativePeerField;
    public static int testAccessFlag;
    public static Object testOffsetArtMethod1;
    public static Object testOffsetArtMethod2;
    public static Method testOffsetMethod1;
    public static Method testOffsetMethod2;
    static Map<Member, HookWrapper.HookEntity> globalHookEntityMap = new ConcurrentHashMap();
    static Map<Method, HookWrapper.HookEntity> globalBackupMap = new ConcurrentHashMap();

    @FunctionalInterface
    public interface HookModeCallBack {
        int hookMode(Member member);
    }

    @FunctionalInterface
    public interface HookResultCallBack {
        void hookResult(boolean z, HookWrapper.HookEntity hookEntity);
    }

    static {
        SandHookConfig.libLoader.loadLib();
        init();
    }

    public static void addHookClass(ClassLoader classLoader, Class... clsArr) throws HookErrorException {
        HookWrapper.addHookClass(classLoader, (Class<?>[]) clsArr);
    }

    public static void addHookClass(Class... clsArr) throws HookErrorException {
        HookWrapper.addHookClass(clsArr);
    }

    public static final Object callOriginByBackup(Method method, Object obj, Object... objArr) {
        HookWrapper.HookEntity hookEntity = globalBackupMap.get(method);
        if (hookEntity == null) {
            return null;
        }
        return callOriginMethod(hookEntity.backupIsStub, hookEntity.target, method, obj, objArr);
    }

    public static final Object callOriginMethod(Member member, Object obj, Object... objArr) {
        HookWrapper.HookEntity hookEntity = globalHookEntityMap.get(member);
        if (hookEntity == null || hookEntity.backup == null) {
            return null;
        }
        return callOriginMethod(hookEntity.backupIsStub, member, hookEntity.backup, obj, objArr);
    }

    public static final Object callOriginMethod(Member member, Method method, Object obj, Object[] objArr) {
        return callOriginMethod(true, member, method, obj, objArr);
    }

    public static final Object callOriginMethod(boolean z, Member member, Method method, Object obj, Object[] objArr) throws Throwable {
        if (!z && SandHookConfig.SDK_INT >= 24) {
            member.getDeclaringClass();
            ensureDeclareClass(member, method);
        }
        if (Modifier.isStatic(member.getModifiers())) {
            try {
                return method.invoke(null, objArr);
            } catch (InvocationTargetException e) {
                if (e.getCause() != null) {
                    throw e.getCause();
                }
                throw e;
            }
        }
        try {
            return method.invoke(obj, objArr);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() != null) {
                throw e2.getCause();
            }
            throw e2;
        }
    }

    public static native boolean canGetObject();

    public static boolean canGetObjectAddress() {
        return Unsafe.support();
    }

    public static native boolean compileMethod(Member member);

    public static native boolean deCompileMethod(Member member, boolean z);

    public static native boolean disableDex2oatInline(boolean z);

    public static native boolean disableVMInline();

    public static final void ensureBackupMethod(Method method) {
        HookWrapper.HookEntity hookEntity;
        if (SandHookConfig.SDK_INT >= 24 && (hookEntity = globalBackupMap.get(method)) != null) {
            ensureDeclareClass(hookEntity.target, method);
        }
    }

    public static native void ensureDeclareClass(Member member, Method method);

    public static native void ensureMethodCached(Method method, Method method2);

    private static Object[] getFakeArgs(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
            return new Object[]{new Object()};
        }
        return null;
    }

    public static Field getField(Class cls, String str) {
        while (cls != null && cls != Object.class) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException(str);
    }

    public static Object getObject(long j) {
        if (j == 0) {
            return null;
        }
        return getObjectNative(getThreadId(), j);
    }

    public static long getObjectAddress(Object obj) {
        return Unsafe.getObjectAddress(obj);
    }

    public static native Object getObjectNative(long j, long j2);

    public static long getThreadId() {
        Field field = nativePeerField;
        if (field == null) {
            return 0L;
        }
        try {
            return field.getType() == Integer.TYPE ? nativePeerField.getInt(Thread.currentThread()) : nativePeerField.getLong(Thread.currentThread());
        } catch (IllegalAccessException unused) {
            return 0L;
        }
    }

    public static boolean hasJavaArtMethod() {
        if (SandHookConfig.SDK_INT >= 26) {
            return false;
        }
        if (artMethodClass != null) {
            return true;
        }
        try {
            ClassLoader classLoader = SandHookConfig.initClassLoader;
            String strDecode = NPStringFog.decode("04111B00400D060B1540020807020404115C2F02192C0B150F0A16");
            artMethodClass = classLoader == null ? Class.forName(strDecode) : Class.forName(strDecode, true, SandHookConfig.initClassLoader);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void hook(HookWrapper.HookEntity hookEntity) {
        String strDecode = NPStringFog.decode("061F020A4E0C02111A01144D5D");
        String strDecode2 = NPStringFog.decode("0315190901054759");
        synchronized (SandHook.class) {
            if (hookEntity == null) {
                throw new HookErrorException(NPStringFog.decode("0005010D4E09080A194E15031507151E"));
            }
            Member member = hookEntity.target;
            Method method = hookEntity.hook;
            Method method2 = hookEntity.backup;
            if (member == null || method == null) {
                throw new HookErrorException(NPStringFog.decode("0005010D4E080915071A"));
            }
            if (globalHookEntityMap.containsKey(hookEntity.target)) {
                throw new HookErrorException("method <" + hookEntity.target.toString() + NPStringFog.decode("505005001D410500170050050E010A020153"));
            }
            if (HookBlackList.canNotHook(member)) {
                throw new HookErrorException(strDecode2 + hookEntity.target.toString() + NPStringFog.decode("50500E000041090A064E18020E054D4707170D1118120B41080352071E4D030200040E1E07031940"));
            }
            if (SandHookConfig.delayHook && PendingHookHandler.canWork() && ClassStatusUtils.isStaticAndNoInited(hookEntity.target)) {
                PendingHookHandler.addPendingHook(hookEntity);
                return;
            }
            if (hookEntity.initClass) {
                resolveStaticMethod(member);
            }
            resolveStaticMethod(method2);
            if (method2 != null && hookEntity.resolveDexCache) {
                SandHookMethodResolver.resolveMethod(method, method2);
            }
            if (member instanceof Method) {
                ((Method) member).setAccessible(true);
            }
            HookModeCallBack hookModeCallBack2 = hookModeCallBack;
            int iHookMode = hookModeCallBack2 != null ? hookModeCallBack2.hookMode(member) : 0;
            globalHookEntityMap.put(hookEntity.target, hookEntity);
            if (iHookMode == 0) {
                HookMode hookMode = (HookMode) method.getAnnotation(HookMode.class);
                iHookMode = hookMode == null ? 0 : hookMode.value();
            }
            int iHookMethod = hookMethod(member, method, method2, iHookMode);
            if (iHookMethod > 0 && method2 != null) {
                method2.setAccessible(true);
            }
            hookEntity.hookMode = iHookMethod;
            HookResultCallBack hookResultCallBack2 = hookResultCallBack;
            if (hookResultCallBack2 != null) {
                hookResultCallBack2.hookResult(iHookMethod > 0, hookEntity);
            }
            if (iHookMethod < 0) {
                globalHookEntityMap.remove(hookEntity.target);
                throw new HookErrorException(strDecode + hookEntity.target.toString() + NPStringFog.decode("505008131C0E15451B005003001A08110053"));
            }
            if (hookEntity.backup != null) {
                globalBackupMap.put(hookEntity.backup, hookEntity);
            }
            HookLog.d("method <" + hookEntity.target.toString() + NPStringFog.decode("5050050E010A4759") + (iHookMethod == 1 ? NPStringFog.decode("071E01080004") : NPStringFog.decode("1C151D0D0F020208170004")) + NPStringFog.decode("50501E140D020216014F"));
        }
    }

    private static native int hookMethod(Member member, Method method, Method method2, int i);

    private static boolean init() {
        initTestOffset();
        initThreadPeer();
        SandHookMethodResolver.init();
        return initNative(SandHookConfig.SDK_INT, SandHookConfig.DEBUG);
    }

    public static native boolean initForPendingHook();

    private static native boolean initNative(int i, boolean z);

    private static void initTestAccessFlag() {
        Integer num;
        boolean zHasJavaArtMethod = hasJavaArtMethod();
        String strDecode = NPStringFog.decode("0F130E041D122109130903");
        try {
            if (zHasJavaArtMethod) {
                loadArtMethod();
                num = (Integer) getField(artMethodClass, strDecode).get(testOffsetArtMethod1);
            } else {
                num = (Integer) getField(Method.class, strDecode).get(testOffsetMethod1);
            }
            testAccessFlag = num.intValue();
        } catch (Exception unused) {
        }
    }

    private static void initTestOffset() {
        ArtMethodSizeTest.method1();
        ArtMethodSizeTest.method2();
        try {
            testOffsetMethod1 = ArtMethodSizeTest.class.getDeclaredMethod(NPStringFog.decode("03151909010556"), new Class[0]);
            testOffsetMethod2 = ArtMethodSizeTest.class.getDeclaredMethod(NPStringFog.decode("03151909010555"), new Class[0]);
            initTestAccessFlag();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(NPStringFog.decode("3D110305260E080E52071E04154E0415171D1C"), e);
        }
    }

    private static void initThreadPeer() {
        try {
            nativePeerField = getField(Thread.class, NPStringFog.decode("0011190818043700171C"));
        } catch (NoSuchFieldException unused) {
        }
    }

    public static native boolean is64Bit();

    private static void loadArtMethod() {
        try {
            Field field = getField(Method.class, NPStringFog.decode("0F02192C0B150F0A16"));
            testOffsetArtMethod1 = field.get(testOffsetMethod1);
            testOffsetArtMethod2 = field.get(testOffsetMethod2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean passApiCheck() {
        return ReflectionUtils.passApiCheck();
    }

    public static boolean resolveStaticMethod(Member member) {
        if (member == null) {
            return true;
        }
        try {
            if ((member instanceof Method) && Modifier.isStatic(member.getModifiers())) {
                ((Method) member).setAccessible(true);
                ((Method) member).invoke(new Object(), getFakeArgs((Method) member));
            }
        } catch (ExceptionInInitializerError unused) {
            return false;
        } catch (Throwable unused2) {
        }
        return true;
    }

    public static native void setHookMode(int i);

    public static void setHookModeCallBack(HookModeCallBack hookModeCallBack2) {
        hookModeCallBack = hookModeCallBack2;
    }

    public static void setHookResultCallBack(HookResultCallBack hookResultCallBack2) {
        hookResultCallBack = hookResultCallBack2;
    }

    public static native void setInlineSafeCheck(boolean z);

    public static native boolean setNativeEntry(Member member, Member member2, long j);

    public static native void skipAllSafeCheck(boolean z);

    public static boolean tryDisableProfile(String str) {
        String strDecode = NPStringFog.decode("41140C150F4E0A0C010D5F1D1301070E09171D5F0E141C4E");
        if (SandHookConfig.SDK_INT < 24) {
            return false;
        }
        try {
            File file = new File(strDecode + SandHookConfig.curUser + NPStringFog.decode("41") + str + NPStringFog.decode("41001F080300151C5C1E020207"));
            if (!file.getParentFile().exists()) {
                return false;
            }
            try {
                file.delete();
                file.createNewFile();
            } catch (Throwable unused) {
            }
            FileUtils.chmod(file.getAbsolutePath(), FileUtils.FileMode.MODE_IRUSR);
            return true;
        } catch (Throwable unused2) {
            return false;
        }
    }
}
