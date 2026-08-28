package com.swift.sandhook.utils;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class ReflectionUtils {
    static Method addWhiteListMethod;
    public static Method forNameMethod;
    public static Method getMethodMethod;
    static Object vmRuntime;
    static Class vmRuntimeClass;

    static {
        try {
            getMethodMethod = Class.class.getDeclaredMethod(NPStringFog.decode("091519250B020B04000B1420041A090801"), String.class, Class[].class);
            Method declaredMethod = Class.class.getDeclaredMethod(NPStringFog.decode("081F1F2F0F0C02"), String.class);
            forNameMethod = declaredMethod;
            Class cls = (Class) declaredMethod.invoke(null, NPStringFog.decode("0A110117070A49160B1D04080C40372A37070004040C0B"));
            vmRuntimeClass = cls;
            addWhiteListMethod = (Method) getMethodMethod.invoke(cls, NPStringFog.decode("1D151929070503001C2F00042416040A1506071F0312"), new Class[]{String[].class});
            vmRuntime = ((Method) getMethodMethod.invoke(vmRuntimeClass, NPStringFog.decode("091519331B0F130C1F0B"), null)).invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e(NPStringFog.decode("3C150B0D0B02130C1D002519080212"), "error get methods", e);
        }
    }

    public static void addReflectionWhiteList(String... strArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        addWhiteListMethod.invoke(vmRuntime, strArr);
    }

    public static boolean passApiCheck() {
        try {
            addReflectionWhiteList(NPStringFog.decode("221103051C0E0E015D"), NPStringFog.decode("2213020C41000901000119094E"), NPStringFog.decode("221A0C170F4E0B041C095F"), NPStringFog.decode("22140C0D18080C4A0117031904034E"), NPStringFog.decode("221C04030D0E15005D071F42"));
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
