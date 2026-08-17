package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import defpackage.ena;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class OaIdlUtil {
    private static void toPrimitiveArray(Object obj, Object obj2, int[] iArr, int[] iArr2, int i, int[] iArr3) {
        Object obj3;
        int[] iArr4;
        int[] iArr5;
        int i2;
        int length = iArr3.length;
        int[] iArr6 = new int[iArr3.length + 1];
        System.arraycopy(iArr3, 0, iArr6, 0, length);
        int i3 = 0;
        while (i3 < iArr[length]) {
            iArr6[length] = i3;
            if (length == iArr.length - 1) {
                int i4 = 0;
                for (int i5 = 0; i5 < length; i5++) {
                    i4 += iArr2[i5] * iArr3[i5];
                }
                int i6 = iArr6[length];
                int i7 = i4 + i6;
                switch (i) {
                    case 2:
                    case 18:
                        Array.set(obj2, i6, Short.valueOf(Array.getShort(obj, i7)));
                        break;
                    case 3:
                    case 19:
                    case 22:
                    case 23:
                        Array.set(obj2, i6, Integer.valueOf(Array.getInt(obj, i7)));
                        break;
                    case 4:
                        Array.set(obj2, i6, Float.valueOf(Array.getFloat(obj, i7)));
                        break;
                    case 5:
                        Array.set(obj2, i6, Double.valueOf(Array.getDouble(obj, i7)));
                        break;
                    case 6:
                    case 9:
                    case 13:
                    case 14:
                    case 15:
                    case 20:
                    case 21:
                    default:
                        pu7.a("Type not supported: ", i);
                        return;
                    case 7:
                        Array.set(obj2, i6, new OaIdl.DATE(Array.getDouble(obj, i7)).getAsJavaDate());
                        break;
                    case 8:
                        Array.set(obj2, i6, new WTypes.BSTR((Pointer) Array.get(obj, i7)).getValue());
                        break;
                    case 10:
                        Array.set(obj2, i6, new WinDef.SCODE(Array.getInt(obj, i7)));
                        break;
                    case 11:
                        Array.set(obj2, i6, Boolean.valueOf(Array.getShort(obj, i7) != 0));
                        break;
                    case 12:
                        Variant.VARIANT variant = (Variant.VARIANT) Array.get(obj, i7);
                        switch (variant.getVarType().intValue()) {
                            case 0:
                            case 1:
                                Array.set(obj2, i6, null);
                                break;
                            case 2:
                            case 18:
                                Array.set(obj2, i6, Short.valueOf(variant.shortValue()));
                                break;
                            case 3:
                            case 19:
                            case 22:
                            case 23:
                                Array.set(obj2, i6, Integer.valueOf(variant.intValue()));
                                break;
                            case 4:
                                Array.set(obj2, i6, Float.valueOf(variant.floatValue()));
                                break;
                            case 5:
                                Array.set(obj2, i6, Double.valueOf(variant.doubleValue()));
                                break;
                            case 6:
                            case 9:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 20:
                            case 21:
                            default:
                                ena.a("Type not supported: ", variant.getVarType().intValue());
                                return;
                            case 7:
                                Array.set(obj2, i6, variant.dateValue());
                                break;
                            case 8:
                                Array.set(obj2, i6, variant.stringValue());
                                break;
                            case 10:
                                Array.set(obj2, i6, new WinDef.SCODE(variant.intValue()));
                                break;
                            case 11:
                                Array.set(obj2, i6, Boolean.valueOf(variant.booleanValue()));
                                break;
                            case 16:
                            case 17:
                                Array.set(obj2, i6, Byte.valueOf(variant.byteValue()));
                                break;
                        }
                        break;
                    case 16:
                    case 17:
                        Array.set(obj2, i6, Byte.valueOf(Array.getByte(obj, i7)));
                        break;
                }
                obj3 = obj;
                iArr4 = iArr;
                iArr5 = iArr2;
                i2 = i;
            } else {
                obj3 = obj;
                iArr4 = iArr;
                iArr5 = iArr2;
                i2 = i;
                toPrimitiveArray(obj3, Array.get(obj2, i3), iArr4, iArr5, i2, iArr6);
            }
            i3++;
            obj = obj3;
            iArr = iArr4;
            iArr2 = iArr5;
            i = i2;
        }
    }

    public static Object toPrimitiveArray(OaIdl.SAFEARRAY safearray, boolean z) {
        Object shortArray;
        Pointer pointerAccessData = safearray.accessData();
        try {
            int dimensionCount = safearray.getDimensionCount();
            int[] iArr = new int[dimensionCount];
            int[] iArr2 = new int[dimensionCount];
            int iIntValue = safearray.getVarType().intValue();
            for (int i = 0; i < dimensionCount; i++) {
                iArr[i] = (safearray.getUBound(i) - safearray.getLBound(i)) + 1;
            }
            int i2 = dimensionCount - 1;
            for (int i3 = i2; i3 >= 0; i3--) {
                if (i3 == i2) {
                    iArr2[i3] = 1;
                } else {
                    int i4 = i3 + 1;
                    iArr2[i3] = iArr2[i4] * iArr[i4];
                }
            }
            if (dimensionCount != 0) {
                int i5 = iArr2[0] * iArr[0];
                switch (iIntValue) {
                    case 2:
                    case 11:
                    case 18:
                        shortArray = pointerAccessData.getShortArray(0L, i5);
                        break;
                    case 3:
                    case 10:
                    case 19:
                    case 22:
                    case 23:
                        shortArray = pointerAccessData.getIntArray(0L, i5);
                        break;
                    case 4:
                        shortArray = pointerAccessData.getFloatArray(0L, i5);
                        break;
                    case 5:
                    case 7:
                        shortArray = pointerAccessData.getDoubleArray(0L, i5);
                        break;
                    case 6:
                    case 9:
                    case 13:
                    case 14:
                    case 15:
                    case 20:
                    case 21:
                    default:
                        throw new IllegalStateException("Type not supported: " + iIntValue);
                    case 8:
                        shortArray = pointerAccessData.getPointerArray(0L, i5);
                        break;
                    case 12:
                        shortArray = new Variant.VARIANT(pointerAccessData).toArray(i5);
                        break;
                    case 16:
                    case 17:
                        shortArray = pointerAccessData.getByteArray(0L, i5);
                        break;
                }
                Object objNewInstance = Array.newInstance((Class<?>) Object.class, iArr);
                toPrimitiveArray(shortArray, objNewInstance, iArr, iArr2, iIntValue, new int[0]);
                safearray.unaccessData();
                if (z) {
                    safearray.destroy();
                }
                return objNewInstance;
            }
            throw new IllegalArgumentException("Supplied Array has no dimensions.");
        } catch (Throwable th) {
            safearray.unaccessData();
            if (z) {
                safearray.destroy();
            }
            throw th;
        }
    }
}
