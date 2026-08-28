package top.canyie.pine.entry;

import np.protect.assets.p.C0038;
import np.protect.assets.p.C0041;
import np.protect.assets.p.C0042;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;

/* loaded from: /workspace/unpacked/classes.dex */
public final class Arm32Entry {
    private static final int CR_SIZE = 3;
    private static final boolean DISALLOW_LONG_CROSS_CR_AND_STACK;
    private static final int FPR_SIZE = 16;
    private static final boolean USE_HARDFP;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final float[] EMPTY_FLOAT_ARRAY = new float[0];

    static class ParamTypesCache {
        int crLength;
        int fpLength;
        int stackLength;

        private ParamTypesCache() {
        }
    }

    static {
        USE_HARDFP = C0038.f66 >= 23;
        DISALLOW_LONG_CROSS_CR_AND_STACK = C0038.f66 >= 31;
    }

    private Arm32Entry() {
    }

    private static boolean booleanBridge(int i, int i2, int i3) {
        return ((Boolean) handleBridge(i, i2, i3)).booleanValue();
    }

    private static byte byteBridge(int i, int i2, int i3) {
        return ((Byte) handleBridge(i, i2, i3)).byteValue();
    }

    private static char charBridge(int i, int i2, int i3) {
        return ((Character) handleBridge(i, i2, i3)).charValue();
    }

    private static double doubleBridge(int i, int i2, int i3) {
        return ((Double) handleBridge(i, i2, i3)).doubleValue();
    }

    private static float floatBridge(int i, int i2, int i3) {
        return ((Float) handleBridge(i, i2, i3)).floatValue();
    }

    private static C0042<int[], int[], float[]> getArgs(Pine.C0050 c0050, int i, int i2) {
        int iMin;
        int i3;
        int i4;
        if (c0050.f89 == null) {
            iMin = !c0050.f86 ? 1 : 0;
            i3 = iMin;
            int i5 = 0;
            int i6 = 0;
            for (Class<?> cls : c0050.f88) {
                if (cls == Double.TYPE) {
                    i5++;
                    i3++;
                } else if (cls == Float.TYPE) {
                    i6++;
                } else {
                    if (cls == Long.TYPE) {
                        if (iMin == 0) {
                            iMin++;
                        }
                        if (iMin < 3) {
                            iMin++;
                        }
                        i3++;
                    }
                    if (iMin < 3) {
                        iMin++;
                    }
                }
                i3++;
            }
            i4 = (i5 * 2) + i6;
            ParamTypesCache paramTypesCache = new ParamTypesCache();
            paramTypesCache.crLength = iMin;
            paramTypesCache.stackLength = i3;
            paramTypesCache.fpLength = i4;
            c0050.f89 = paramTypesCache;
        } else {
            ParamTypesCache paramTypesCache2 = (ParamTypesCache) c0050.f89;
            iMin = paramTypesCache2.crLength;
            i3 = paramTypesCache2.stackLength;
            i4 = paramTypesCache2.fpLength;
        }
        float[] fArr = EMPTY_FLOAT_ARRAY;
        if (!USE_HARDFP) {
            iMin = Math.min(iMin + i4, 3);
        } else if (i4 != 0) {
            fArr = new float[Math.min(C0041.m451(i4), 16)];
        }
        int[] iArr = iMin != 0 ? new int[iMin] : EMPTY_INT_ARRAY;
        int[] iArr2 = i3 != 0 ? new int[i3] : EMPTY_INT_ARRAY;
        Pine.getArgsArm32(i, i2, iArr, iArr2, fArr);
        return new C0042<>(iArr, iArr2, fArr);
    }

    private static Object handleBridge(int i, int i2, int i3) {
        Object objM472;
        int i4;
        int i5;
        Object[] objArr;
        int i6;
        int i7;
        Object objM4722;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int iCloneExtras = (int) Pine.cloneExtras(i2);
        int i16 = 1;
        Pine.m478("handleBridge: artMethod=%#x originExtras=%#x extras=%#x sp=%#x", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(iCloneExtras), Integer.valueOf(i3));
        Pine.C0050 c0050M477 = Pine.m477(i);
        C0042<int[], int[], float[]> args = getArgs(c0050M477, iCloneExtras, i3);
        int[] iArr = args.f70;
        int[] iArr2 = args.f71;
        float[] fArr = args.f72;
        long jCurrentArtThread0 = Pine.currentArtThread0();
        if (c0050M477.f86) {
            objM472 = null;
            i4 = 0;
            i5 = 0;
        } else {
            objM472 = Pine.m472(jCurrentArtThread0, iArr[0]);
            i4 = 1;
            i5 = 1;
        }
        if (c0050M477.f87 > 0) {
            objArr = new Object[c0050M477.f87];
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            while (i18 < c0050M477.f87) {
                Class<?> cls = c0050M477.f88[i18];
                if (cls == Double.TYPE) {
                    int iMax = Math.max(i19, C0041.m451(i17));
                    if (iMax < fArr.length) {
                        int i20 = iMax + 1;
                        objM4722 = Double.valueOf(C0041.m449(fArr[iMax], fArr[i20]));
                        i5++;
                        i19 = i20 + i16;
                    } else {
                        if (i4 >= iArr.length || USE_HARDFP) {
                            i13 = iArr2[i5];
                        } else {
                            i13 = iArr[i4];
                            i4++;
                        }
                        i5++;
                        if (i4 >= iArr.length || USE_HARDFP) {
                            i14 = i4;
                            i15 = iArr2[i5];
                        } else {
                            i14 = i4 + 1;
                            i15 = iArr[i4];
                        }
                        Double dValueOf = Double.valueOf(C0041.m450(i13, i15));
                        i4 = i14;
                        i19 = iMax;
                        objM4722 = dValueOf;
                    }
                    i6 = i17;
                } else if (cls == Float.TYPE) {
                    int i21 = i17;
                    int iMax2 = i21 % 2 == 0 ? Math.max(i19, i21) : i21;
                    if (iMax2 < fArr.length) {
                        i6 = iMax2 + 1;
                        objM4722 = Float.valueOf(fArr[iMax2]);
                    } else {
                        if (i4 >= iArr.length || USE_HARDFP) {
                            int i22 = i4;
                            i11 = iArr2[i5];
                            i12 = i22;
                        } else {
                            i12 = i4 + 1;
                            i11 = iArr[i4];
                        }
                        int i23 = i12;
                        i6 = iMax2;
                        objM4722 = Float.valueOf(Float.intBitsToFloat(i11));
                        i4 = i23;
                    }
                } else {
                    i6 = i17;
                    if (cls != Long.TYPE) {
                        if (i4 < iArr.length) {
                            i7 = iArr[i4];
                            i4++;
                        } else {
                            i7 = iArr2[i5];
                        }
                        if (!cls.isPrimitive()) {
                            objM4722 = Pine.m472(jCurrentArtThread0, i7);
                        } else if (cls == Integer.TYPE) {
                            objM4722 = Integer.valueOf(i7);
                        } else if (cls == Boolean.TYPE) {
                            objM4722 = Boolean.valueOf(i7 != 0);
                        } else if (cls == Short.TYPE) {
                            objM4722 = Short.valueOf((short) i7);
                        } else if (cls == Character.TYPE) {
                            objM4722 = Character.valueOf((char) i7);
                        } else {
                            if (cls != Byte.TYPE) {
                                throw new AssertionError("Unknown primitive type: " + cls);
                            }
                            objM4722 = Byte.valueOf((byte) i7);
                        }
                    } else if (i4 == 0 && c0050M477.f86 && USE_HARDFP) {
                        objArr[i18] = Long.valueOf(C0041.m452(iArr[i16], iArr[2]));
                        i5 += 2;
                        i4 = 3;
                        i18++;
                        i17 = i6;
                    } else {
                        if (i4 == 2 && DISALLOW_LONG_CROSS_CR_AND_STACK) {
                            i4 = 3;
                        }
                        if (i4 < iArr.length) {
                            i8 = iArr[i4];
                            i4++;
                        } else {
                            i8 = iArr2[i5];
                        }
                        i5++;
                        if (i4 < iArr.length) {
                            i10 = i4 + 1;
                            i9 = iArr[i4];
                        } else {
                            int i24 = i4;
                            i9 = iArr2[i5];
                            i10 = i24;
                        }
                        objM4722 = Long.valueOf(C0041.m452(i8, i9));
                        i4 = i10;
                    }
                }
                objArr[i18] = objM4722;
                i16 = 1;
                i5++;
                i18++;
                i17 = i6;
            }
        } else {
            objArr = Pine.EMPTY_OBJECT_ARRAY;
        }
        return Pine.m474(c0050M477, objM472, objArr);
    }

    private static int intBridge(int i, int i2, int i3) {
        return ((Integer) handleBridge(i, i2, i3)).intValue();
    }

    private static long longBridge(int i, int i2, int i3) {
        return ((Long) handleBridge(i, i2, i3)).longValue();
    }

    private static Object objectBridge(int i, int i2, int i3) {
        return handleBridge(i, i2, i3);
    }

    private static short shortBridge(int i, int i2, int i3) {
        return ((Short) handleBridge(i, i2, i3)).shortValue();
    }

    private static void voidBridge(int i, int i2, int i3) {
        handleBridge(i, i2, i3);
    }
}
