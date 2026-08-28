package top.canyie.pine;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import np.protect.assets.p.AbstractC0039;
import np.protect.assets.p.C0038;
import obfuse.NPStringFog;
import top.canyie.pine.entry.Arm64MarshmallowEntry;

/* loaded from: /workspace/unpacked/classes.dex */
public final class Pine {
    static final boolean $assertionsDisabled = false;
    private static final int ARCH_ARM = 1;
    private static final int ARCH_ARM64 = 2;
    private static final int ARCH_X86 = 3;
    private static final String TAG = "Pine";
    private static int arch;
    public static long closeElf;
    public static long findElfSymbol;
    private static volatile int hookMode;
    private static volatile boolean initialized;
    public static long openElf;
    private static InterfaceC0049 sHookListener;
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final Map<String, Method> sBridgeMethods = new HashMap(8, 2.0f);
    private static final Map<Long, C0050> sHookRecords = new ConcurrentHashMap();
    private static final Object sHookLock = new Object();
    private static InterfaceC0048 sHookHandler = new InterfaceC0048() { // from class: top.canyie.pine.Pine.1
        @Override // top.canyie.pine.Pine.InterfaceC0048
        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        public AbstractC0039.C0040 mo485(C0050 c0050, AbstractC0039 abstractC0039, int i, boolean z, boolean z2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (z) {
                Pine.m480(c0050, i, z2);
            }
            if (abstractC0039 == null) {
                return null;
            }
            c0050.m495(abstractC0039);
            abstractC0039.getClass();
            return new AbstractC0039.C0040(abstractC0039, c0050);
        }
    };

    /* renamed from: top.canyie.pine.Pine$۟, reason: contains not printable characters */
    public static class C0047 {

        /* renamed from: ۟۟۟ۡ۟, reason: not valid java name and contains not printable characters */
        public Object[] f77;

        /* renamed from: ۣ۟۟۟ۡ, reason: not valid java name and contains not printable characters */
        private C0050 f78;

        /* renamed from: ۟۟۟ۡۤ, reason: not valid java name and contains not printable characters */
        public final Member f79;

        /* renamed from: ۟۟۟ۡۥ, reason: not valid java name and contains not printable characters */
        private Object f80;

        /* renamed from: ۟۟۟ۡۦ, reason: not valid java name and contains not printable characters */
        boolean f81;

        /* renamed from: ۟۟۟ۡۧ, reason: not valid java name and contains not printable characters */
        public Object f82;

        /* renamed from: ۟۟۟ۡۨ, reason: not valid java name and contains not printable characters */
        private Throwable f83;

        public C0047(C0050 c0050, Object obj, Object[] objArr) {
            this.f78 = c0050;
            this.f79 = c0050.f90;
            this.f82 = obj;
            this.f77 = objArr;
        }

        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        public void m486(Throwable th) {
            this.f83 = th;
            this.f80 = null;
            this.f81 = true;
        }

        /* renamed from: ۟۟۟۟۠, reason: not valid java name and contains not printable characters */
        public void m487(Object obj) {
            this.f80 = obj;
            this.f83 = null;
            this.f81 = true;
        }

        /* renamed from: ۟۟۟ۡۢ, reason: not valid java name and contains not printable characters */
        public Object m488() {
            return this.f80;
        }

        /* renamed from: ۣ۟۟۟ۡ, reason: not valid java name and contains not printable characters */
        public Throwable m489() {
            return this.f83;
        }

        /* renamed from: ۟۟۟ۡۤ, reason: not valid java name and contains not printable characters */
        public boolean m490() {
            return this.f83 != null;
        }

        /* renamed from: ۟۟۟ۡۥ, reason: not valid java name and contains not printable characters */
        public Object m491() {
            return Pine.m473(this.f78.f90, this.f78.backup, this.f82, this.f77);
        }

        /* renamed from: ۟۟۟ۡۦ, reason: not valid java name and contains not printable characters */
        public void m492() {
            this.f80 = null;
            this.f83 = null;
            this.f81 = false;
        }
    }

    /* renamed from: top.canyie.pine.Pine$۟۟, reason: contains not printable characters */
    public interface InterfaceC0048 {
        /* renamed from: ۟ */
        AbstractC0039.C0040 mo485(C0050 c0050, AbstractC0039 abstractC0039, int i, boolean z, boolean z2);
    }

    /* renamed from: top.canyie.pine.Pine$۟۟۟, reason: contains not printable characters */
    public interface InterfaceC0049 {
        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        void m493(Member member, AbstractC0039.C0040 c0040);

        /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
        void m494(Member member, AbstractC0039 abstractC0039);
    }

    /* renamed from: top.canyie.pine.Pine$۟۟۟۟, reason: contains not printable characters */
    public static final class C0050 {
        public Method backup;

        /* renamed from: ۟۟۟ۢ, reason: not valid java name and contains not printable characters */
        public final long f84;

        /* renamed from: ۟۟۟ۢ۟, reason: not valid java name and contains not printable characters */
        private Set<AbstractC0039> f85 = new HashSet();

        /* renamed from: ۟۟۟ۢ۠, reason: not valid java name and contains not printable characters */
        public boolean f86;

        /* renamed from: ۟۟۟ۢۡ, reason: not valid java name and contains not printable characters */
        public int f87;

        /* renamed from: ۟۟۟ۢۢ, reason: not valid java name and contains not printable characters */
        public Class<?>[] f88;

        /* renamed from: ۣ۟۟۟ۢ, reason: not valid java name and contains not printable characters */
        public volatile Object f89;

        /* renamed from: ۟۟۟ۢۤ, reason: not valid java name and contains not printable characters */
        public final Member f90;

        public C0050(Member member, long j) {
            this.f90 = member;
            this.f84 = j;
        }

        /* renamed from: ۟, reason: not valid java name and contains not printable characters */
        public void m495(AbstractC0039 abstractC0039) {
            synchronized (this) {
                this.f85.add(abstractC0039);
            }
        }

        /* renamed from: ۟۟۟ۡۧ, reason: not valid java name and contains not printable characters */
        public boolean m496() {
            boolean zIsEmpty;
            synchronized (this) {
                zIsEmpty = this.f85.isEmpty();
            }
            return zIsEmpty;
        }

        /* renamed from: ۟۟۟ۡۨ, reason: not valid java name and contains not printable characters */
        public AbstractC0039[] m497() {
            AbstractC0039[] abstractC0039Arr;
            synchronized (this) {
                Set<AbstractC0039> set = this.f85;
                abstractC0039Arr = (AbstractC0039[]) set.toArray(new AbstractC0039[set.size()]);
            }
            return abstractC0039Arr;
        }
    }

    /* renamed from: top.canyie.pine.Pine$۟۟۟۟۟, reason: contains not printable characters */
    public interface InterfaceC0051 {
        void loadLib();
    }

    private Pine() {
        throw new RuntimeException("Use static methods");
    }

    public static native long cloneExtras(long j);

    private static native boolean compile0(long j, Member member);

    public static native long currentArtThread0();

    private static native boolean decompile0(Member member, boolean z);

    private static native void disableHiddenApiPolicy0(boolean z, boolean z2);

    private static native boolean disableJitInline0();

    private static native boolean disableProfileSaver0();

    private static native void enableFastNative();

    private static native long getAddress0(long j, Object obj);

    public static native void getArgsArm32(int i, int i2, int[] iArr, int[] iArr2, float[] fArr);

    public static native void getArgsArm64(long j, long j2, boolean[] zArr, long[] jArr, long[] jArr2, double[] dArr);

    public static native void getArgsX86(int i, int[] iArr, int i2);

    public static native long getArtMethod(Member member);

    private static native Object getObject0(long j, long j2);

    private static native Method hook0(long j, Class<?> cls, Member member, Method method, boolean z, boolean z2, boolean z3);

    private static native void init0(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5);

    private static native void makeClassesVisiblyInitialized(long j);

    private static native void setDebuggable0(boolean z);

    private static native void setJitCompilationAllowed0(boolean z, boolean z2);

    private static native void syncMethodInfo(Member member, Method method);

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m472(long j, long j2) {
        if (j2 == 0) {
            return null;
        }
        return getObject0(j, j2);
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    static Object m473(Member member, Method method, Object obj, Object[] objArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> declaringClass = member.getDeclaringClass();
        syncMethodInfo(member, method);
        Object objInvoke = method.invoke(obj, objArr);
        declaringClass.getClass();
        return objInvoke;
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static Object m474(C0050 c0050, Object obj, Object[] objArr) throws Throwable {
        String strDecode = "Unexpected exception occurred when calling ";
        boolean z = C0038.f60;
        String strDecode2 = "Pine";
        if (z) {
            Log.d(strDecode2, "handleCall for method " + c0050.f90);
        }
        if (C0038.f64 || c0050.m496()) {
            try {
                return m473(c0050.f90, c0050.backup, obj, objArr);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }
        C0047 c0047 = new C0047(c0050, obj, objArr);
        AbstractC0039[] abstractC0039ArrM497 = c0050.m497();
        int i = 0;
        while (true) {
            AbstractC0039 abstractC0039 = abstractC0039ArrM497[i];
            try {
                abstractC0039.mo380(c0047);
            } catch (Throwable th) {
                Log.e(strDecode2, strDecode + abstractC0039.getClass().getName() + ".beforeCall()", th);
                c0047.m492();
            }
            if (c0047.f81) {
                i++;
                break;
            }
            i++;
            if (i >= abstractC0039ArrM497.length) {
                break;
            }
        }
        if (!c0047.f81) {
            try {
                c0047.m487(c0047.m491());
            } catch (InvocationTargetException e2) {
                c0047.m486(e2.getTargetException());
            }
        }
        int i2 = i - 1;
        do {
            AbstractC0039 abstractC00392 = abstractC0039ArrM497[i2];
            Object objM488 = c0047.m488();
            Throwable thM489 = c0047.m489();
            try {
                abstractC00392.mo377(c0047);
            } catch (Throwable th2) {
                Log.e(strDecode2, strDecode + abstractC00392.getClass().getName() + ".afterCall()", th2);
                if (thM489 == null) {
                    c0047.m487(objM488);
                } else {
                    c0047.m486(thM489);
                }
            }
            i2--;
        } while (i2 >= 0);
        if (c0047.m490()) {
            throw c0047.m489();
        }
        return c0047.m488();
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static AbstractC0039.C0040 m475(Member member, AbstractC0039 abstractC0039) {
        return m476(member, abstractC0039, true);
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static AbstractC0039.C0040 m476(Member member, AbstractC0039 abstractC0039, boolean z) {
        C0050 c0050;
        boolean z2;
        if (C0038.f60) {
            Log.d("Pine", "Hooking method " + member + " with callback " + abstractC0039);
        }
        if (member == null) {
            throw new NullPointerException("method == null");
        }
        if (abstractC0039 == null) {
            throw new NullPointerException("callback == null");
        }
        int modifiers = member.getModifiers();
        if (member instanceof Method) {
            if (Modifier.isAbstract(modifiers)) {
                throw new IllegalArgumentException("Cannot hook abstract methods: " + member);
            }
            ((Method) member).setAccessible(true);
        } else {
            if (!(member instanceof Constructor)) {
                throw new IllegalArgumentException("Only methods and constructors can be hooked: " + member);
            }
            if (Modifier.isStatic(modifiers)) {
                throw new IllegalArgumentException("Cannot hook class initializer: " + member);
            }
            ((Constructor) member).setAccessible(true);
        }
        m482();
        InterfaceC0049 interfaceC0049 = sHookListener;
        if (interfaceC0049 != null) {
            interfaceC0049.m494(member, abstractC0039);
        }
        long artMethod = getArtMethod(member);
        synchronized (sHookLock) {
            Map<Long, C0050> map = sHookRecords;
            C0050 c00502 = map.get(Long.valueOf(artMethod));
            if (c00502 == null) {
                C0050 c00503 = new C0050(member, artMethod);
                map.put(Long.valueOf(artMethod), c00503);
                c0050 = c00503;
                z2 = true;
            } else {
                c0050 = c00502;
                z2 = false;
            }
        }
        AbstractC0039.C0040 c0040Mo485 = sHookHandler.mo485(c0050, abstractC0039, modifiers, z2, z);
        if (interfaceC0049 != null) {
            interfaceC0049.m493(member, c0040Mo485);
        }
        return c0040Mo485;
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static C0050 m477(long j) {
        C0050 c0050 = sHookRecords.get(Long.valueOf(j));
        if (c0050 != null) {
            return c0050;
        }
        throw new AssertionError("No HookRecord found for ArtMethod pointer 0x" + Long.toHexString(j));
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static void m478(String str, Object... objArr) {
        if (C0038.f60) {
            Log.i("Pine", String.format(str, objArr));
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private static void m479(Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            method.invoke(null, method.getParameterTypes().length > 0 ? null : new Object[1]);
            throw new RuntimeException("No IllegalArgumentException thrown when resolve static method.");
        } catch (IllegalArgumentException unused) {
        } catch (Exception e) {
            throw new RuntimeException("Unknown exception thrown when resolve static method.", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void m480(C0050 c0050, int i, boolean z) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        boolean z2;
        String strDecode;
        Member member = c0050.f90;
        int i2 = hookMode;
        boolean z3 = false;
        boolean z4 = i2 == 1 || i2 == 3;
        long jCurrentArtThread0 = currentArtThread0();
        boolean zIsStatic = Modifier.isStatic(i);
        c0050.f86 = zIsStatic;
        if (zIsStatic && z) {
            m479((Method) member);
            if (C0038.f66 >= 29) {
                makeClassesVisiblyInitialized(jCurrentArtThread0);
            }
        }
        Class<?> declaringClass = member.getDeclaringClass();
        boolean zIsNative = Modifier.isNative(i);
        boolean zIsProxyClass = Proxy.isProxyClass(declaringClass);
        if (!z4) {
            z2 = z4;
        } else if (zIsNative || zIsProxyClass) {
            z2 = false;
        } else if (i2 == 1) {
            if (compile0(jCurrentArtThread0, member)) {
                z3 = z4;
            } else {
                Log.w("Pine", "Cannot compile the target method, force replacement mode.");
            }
            z2 = z3;
        }
        if (member instanceof Method) {
            Method method = (Method) member;
            c0050.f88 = method.getParameterTypes();
            Class<?> returnType = method.getReturnType();
            strDecode = returnType.isPrimitive() ? returnType.getName() + "Bridge" : "objectBridge";
        } else {
            c0050.f88 = ((Constructor) member).getParameterTypes();
            strDecode = "voidBridge";
        }
        c0050.f87 = c0050.f88.length;
        Method bridge = (C0038.f66 == 23 && arch == 2) ? Arm64MarshmallowEntry.getBridge(strDecode, c0050.f87) : sBridgeMethods.get(strDecode);
        if (bridge == null) {
            throw new AssertionError("Cannot find bridge method for " + member);
        }
        Method methodHook0 = hook0(jCurrentArtThread0, declaringClass, member, bridge, z2, zIsNative, zIsProxyClass);
        if (methodHook0 == null) {
            throw new RuntimeException("Failed to hook method " + member);
        }
        methodHook0.setAccessible(true);
        c0050.backup = methodHook0;
    }

    /* renamed from: ۟۟۟۟ۢ, reason: not valid java name and contains not printable characters */
    private static boolean m481(String str) {
        String upperCase = Build.VERSION.CODENAME.toUpperCase(Locale.ROOT);
        return !"REL".equals(upperCase) && upperCase.compareTo(str.toUpperCase(Locale.ROOT)) >= 0;
    }

    /* renamed from: ۟۟۟ۡ۟, reason: not valid java name and contains not printable characters */
    public static void m482() {
        if (initialized) {
            return;
        }
        synchronized (Pine.class) {
            if (initialized) {
                return;
            }
            m484();
            initialized = true;
        }
    }

    /* renamed from: ۟۟۟ۡ۠, reason: not valid java name and contains not printable characters */
    private static void m483() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        Class<?>[] clsArr;
        String strDecode;
        String strDecode2 = "Unexpected arch ";
        try {
            int i = arch;
            if (i == 2) {
                clsArr = new Class[]{Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE};
                strDecode = "top.canyie.pine.entry.Arm64Entry";
            } else if (i == 1) {
                clsArr = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE};
                strDecode = "top.canyie.pine.entry.Arm32Entry";
            } else {
                if (i != 3) {
                    throw new RuntimeException(strDecode2 + arch);
                }
                clsArr = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE};
                strDecode = "top.canyie.pine.entry.X86Entry";
            }
            Class<?> cls = Class.forName(strDecode, true, Pine.class.getClassLoader());
            String[] strArr = new String[10];
            strArr[0] = "voidBridge";
            strArr[1] = "intBridge";
            strArr[2] = "longBridge";
            strArr[3] = "doubleBridge";
            strArr[4] = "floatBridge";
            strArr[5] = "booleanBridge";
            strArr[6] = "byteBridge";
            strArr[7] = "charBridge";
            strArr[8] = "shortBridge";
            strArr[9] = "objectBridge";
            for (int i2 = 0; i2 < 10; i2++) {
                String str = strArr[i2];
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                declaredMethod.setAccessible(true);
                sBridgeMethods.put(str, declaredMethod);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to init bridge methods", e);
        }
    }

    /* renamed from: ۟۟۟ۡۡ, reason: not valid java name and contains not printable characters */
    private static void m484() {
        int i = C0038.f66;
        if (i < 19) {
            throw new RuntimeException("Unsupported android sdk level " + i);
        }
        if (i == 33 && m481("UpsideDownCake")) {
            i = 34;
        }
        String property = System.getProperty("java.vm.version");
        if (property == null || !property.startsWith("2")) {
            throw new RuntimeException("Only supports ART runtime");
        }
        hookMode = i < 26 ? 3 : 2;
        try {
            InterfaceC0051 interfaceC0051 = C0038.f65;
            if (interfaceC0051 != null) {
                interfaceC0051.loadLib();
            }
            init0(i, C0038.f60, C0038.f61, C0038.f59, C0038.f62, C0038.f63);
            m483();
            if (!C0038.f67 || i < 21) {
                return;
            }
            enableFastNative();
        } catch (Exception e) {
            throw new RuntimeException("Pine init error", e);
        }
    }
}
