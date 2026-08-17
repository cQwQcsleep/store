package com.sun.jna.platform.win32.COM.util;

import com.reandroid.arsc.value.CompoundEntry;
import com.sun.jna.platform.win32.COM.Dispatch;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinDef;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Date;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class Convert {
    public static void free(Variant.VARIANT variant, Class<?> cls) {
        if ((cls == null || !WTypes.BSTR.class.isAssignableFrom(cls)) && variant != null && variant.getVarType().intValue() == 8) {
            Object value = variant.getValue();
            if (value instanceof WTypes.BSTR) {
                OleAuto.INSTANCE.SysFreeString((WTypes.BSTR) value);
            }
        }
    }

    public static <T extends IComEnum> T toComEnum(Class<T> cls, Object obj) {
        try {
            for (IComEnum iComEnum : (IComEnum[]) cls.getMethod(CompoundEntry.NAME_values, null).invoke(null, null)) {
                T t = (T) iComEnum;
                if (obj.equals(Long.valueOf(t.getValue()))) {
                    return t;
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:53:0x00ad  */
    public static Object toJavaObject(Variant.VARIANT variant, Class<?> cls, ObjectFactory objectFactory, boolean z, boolean z2) {
        Variant.VARIANT variant2;
        Object objValueOf;
        Object objCast;
        Object value;
        Dispatch dispatch;
        Class<?> cls2 = cls;
        int iIntValue = variant != null ? variant.getVarType().intValue() : 1;
        if (iIntValue == 0 || iIntValue == 1) {
            return null;
        }
        if (cls2 != null && !cls2.isAssignableFrom(Object.class)) {
            if (cls2.isAssignableFrom(variant.getClass())) {
                return variant;
            }
            Object value2 = variant.getValue();
            if (value2 != null && cls2.isAssignableFrom(value2.getClass())) {
                return value2;
            }
        }
        if (iIntValue == 16396) {
            Variant.VARIANT variant3 = (Variant.VARIANT) variant.getValue();
            variant2 = variant3;
            iIntValue = variant3.getVarType().intValue();
        } else {
            variant2 = variant;
        }
        if (cls2 == null || cls2.isAssignableFrom(Object.class)) {
            if (iIntValue == 13) {
                cls2 = com.sun.jna.platform.win32.COM.IUnknown.class;
            } else if (iIntValue == 16384) {
                cls2 = WinDef.PVOID.class;
            } else if (iIntValue == 16396) {
                cls2 = Variant.class;
            } else if (iIntValue != 16398) {
                switch (iIntValue) {
                    case 2:
                        cls2 = Short.class;
                        break;
                    case 3:
                        cls2 = Integer.class;
                        break;
                    case 4:
                        cls2 = Float.class;
                        break;
                    case 5:
                        cls2 = Double.class;
                        break;
                    case 6:
                        cls2 = OaIdl.CURRENCY.class;
                        break;
                    case 7:
                        cls2 = Date.class;
                        break;
                    case 8:
                        cls2 = String.class;
                        break;
                    case 9:
                        cls2 = IDispatch.class;
                        break;
                    case 10:
                        cls2 = WinDef.SCODE.class;
                        break;
                    case 11:
                        cls2 = Boolean.class;
                        break;
                    default:
                        switch (iIntValue) {
                            case 16:
                            case 17:
                                cls2 = Byte.class;
                                break;
                            case 18:
                                cls2 = Character.class;
                                break;
                            case 19:
                            case 22:
                            case 23:
                                cls2 = Integer.class;
                                break;
                            case 20:
                            case 21:
                                cls2 = Long.class;
                                break;
                            default:
                                cls2 = (iIntValue & 8192) <= 0 ? null : OaIdl.SAFEARRAY.class;
                                break;
                        }
                        break;
                }
            } else {
                cls2 = OaIdl.DECIMAL.class;
            }
        }
        if (Byte.class.equals(cls2) || Byte.TYPE.equals(cls2)) {
            objValueOf = Byte.valueOf(variant2.byteValue());
        } else if (Short.class.equals(cls2) || Short.TYPE.equals(cls2)) {
            objValueOf = Short.valueOf(variant2.shortValue());
        } else if (Character.class.equals(cls2) || Character.TYPE.equals(cls2)) {
            objValueOf = Character.valueOf((char) variant2.intValue());
        } else if (Integer.class.equals(cls2) || Integer.TYPE.equals(cls2)) {
            objValueOf = Integer.valueOf(variant2.intValue());
        } else if (Long.class.equals(cls2) || Long.TYPE.equals(cls2) || IComEnum.class.isAssignableFrom(cls2)) {
            objValueOf = Long.valueOf(variant2.longValue());
        } else if (Float.class.equals(cls2) || Float.TYPE.equals(cls2)) {
            objValueOf = Float.valueOf(variant2.floatValue());
        } else if (Double.class.equals(cls2) || Double.TYPE.equals(cls2)) {
            objValueOf = Double.valueOf(variant2.doubleValue());
        } else if (Boolean.class.equals(cls2) || Boolean.TYPE.equals(cls2)) {
            objValueOf = Boolean.valueOf(variant2.booleanValue());
        } else if (Date.class.equals(cls2)) {
            objValueOf = variant2.dateValue();
        } else if (String.class.equals(cls2)) {
            objValueOf = variant2.stringValue();
        } else {
            value = variant2.getValue();
            if (value instanceof Dispatch) {
                dispatch = (Dispatch) value;
                if (cls2 != null && cls2.isInterface()) {
                    Object objCreateProxy = objectFactory.createProxy(cls2, dispatch);
                    if (!z) {
                        objValueOf = value;
                        objValueOf = dispatch;
                        objValueOf = dispatch;
                        dispatch.Release();
                    }
                    objValueOf = value;
                    objValueOf = dispatch;
                    objValueOf = dispatch;
                    objValueOf = objCreateProxy;
                }
            }
        }
        objValueOf = value;
        objValueOf = dispatch;
        objValueOf = dispatch;
        objValueOf = value;
        objValueOf = dispatch;
        objValueOf = value;
        Object obj = objValueOf;
        if (IComEnum.class.isAssignableFrom(cls2)) {
            objCast = cls2.cast(toComEnum(cls2, objValueOf));
        }
        if (z2) {
            obj = objCast;
            free(variant, obj);
        }
        obj = objCast;
        return obj;
    }

    public static Variant.VARIANT toVariant(Object obj) {
        Constructor<?> constructor;
        if (obj instanceof Variant.VARIANT) {
            return (Variant.VARIANT) obj;
        }
        if (obj instanceof Byte) {
            return new Variant.VARIANT(((Byte) obj).byteValue());
        }
        if (obj instanceof Character) {
            return new Variant.VARIANT(((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return new Variant.VARIANT(((Short) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new Variant.VARIANT(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return new Variant.VARIANT(((Long) obj).longValue());
        }
        if (obj instanceof Float) {
            return new Variant.VARIANT(((Float) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new Variant.VARIANT(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return new Variant.VARIANT((String) obj);
        }
        if (obj instanceof Boolean) {
            return new Variant.VARIANT(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Dispatch) {
            return new Variant.VARIANT((Dispatch) obj);
        }
        if (obj instanceof Date) {
            return new Variant.VARIANT((Date) obj);
        }
        if (obj instanceof Proxy) {
            return new Variant.VARIANT(((ProxyObject) Proxy.getInvocationHandler(obj)).getRawDispatch());
        }
        if (obj instanceof IComEnum) {
            return new Variant.VARIANT(new WinDef.LONG(((IComEnum) obj).getValue()));
        }
        if (obj != null) {
            constructor = null;
            for (Constructor<?> constructor2 : Variant.VARIANT.class.getConstructors()) {
                Class<?>[] parameterTypes = constructor2.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0].isAssignableFrom(obj.getClass())) {
                    constructor = constructor2;
                }
            }
        } else {
            constructor = null;
        }
        if (constructor != null) {
            try {
                return (Variant.VARIANT) constructor.newInstance(obj);
            } catch (Exception e) {
                rc6.a(e);
            }
        }
        return null;
    }

    public static void free(Variant.VARIANT variant, Object obj) {
        free(variant, obj == null ? null : obj.getClass());
    }
}
