package rikka.shizuku;

import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class SystemServiceHelper {
    private static final Map<String, IBinder> SYSTEM_SERVICE_CACHE = new HashMap();
    private static final Map<String, Integer> TRANSACT_CODE_CACHE = new HashMap();
    private static Method getService;

    static {
        try {
            getService = Class.forName(NPStringFog.decode("0F1E09130108034B1D1D5E3E041C170E061723110300090415")).getMethod(NPStringFog.decode("091519320B13110C110B"), String.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.w(NPStringFog.decode("3D091E150B0C34000018190E0426040B15171C"), Log.getStackTraceString(e));
        }
    }

    public static IBinder getSystemService(String str) {
        IBinder iBinder = SYSTEM_SERVICE_CACHE.get(str);
        if (iBinder == null) {
            try {
                iBinder = (IBinder) getService.invoke(null, str);
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.w(NPStringFog.decode("3D091E150B0C34000018190E0426040B15171C"), Log.getStackTraceString(e));
            }
            SYSTEM_SERVICE_CACHE.put(str, iBinder);
        }
        return iBinder;
    }

    @Deprecated
    public static Integer getTransactionCode(String str, String str2) throws NoSuchFieldException, ClassNotFoundException {
        Integer num;
        Class<?> cls;
        Field declaredField;
        String str3 = NPStringFog.decode("3A222C2F3D2024313B213E32") + str2;
        String str4 = str + NPStringFog.decode("40") + str3;
        Integer num2 = TRANSACT_CODE_CACHE.get(str4);
        if (num2 != null) {
            return num2;
        }
        try {
            cls = Class.forName(str);
            declaredField = null;
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e = e;
            num = null;
        }
        try {
            try {
                declaredField = cls.getDeclaredField(str3);
                num = null;
            } catch (NoSuchFieldException e2) {
                Field[] declaredFields = cls.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        num = null;
                        break;
                    }
                    Field field = declaredFields[i];
                    if (field.getType() == Integer.TYPE) {
                        String name = field.getName();
                        num = null;
                        if (name.startsWith(str3 + NPStringFog.decode("31")) && TextUtils.isDigitsOnly(name.substring(str3.length() + 1))) {
                            declaredField = field;
                            break;
                        }
                    }
                    i++;
                }
            }
            if (declaredField == null) {
                return num;
            }
            declaredField.setAccessible(true);
            Integer numValueOf = Integer.valueOf(declaredField.getInt(cls));
            TRANSACT_CODE_CACHE.put(str4, numValueOf);
            return numValueOf;
        } catch (ClassNotFoundException | IllegalAccessException e3) {
            e = e3;
            e.printStackTrace();
            return num;
        }
    }

    @Deprecated
    public static Parcel obtainParcel(String str, String str2, String str3) {
        return obtainParcel(str, str2, str2 + NPStringFog.decode("4A2319140C"), str3);
    }

    @Deprecated
    public static Parcel obtainParcel(String str, String str2, String str3, String str4) {
        throw new UnsupportedOperationException(NPStringFog.decode("2A191F040D154710010B5002074E320F0C081B1B18421A13060B010F1319330B0C0811174E191E41000E47091D001708134E12121502010219040A4D47151E0B111E044E141400523D18041B1B0A12271B0014081339130615020B02"));
    }
}
