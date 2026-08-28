package top.canyie.pine.entry;

import np.protect.assets.p.C0042;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;

/* loaded from: /workspace/unpacked/classes.dex */
public final class Arm64Entry {
    private static final long BYTE_BITS = 255;
    private static final int CR_SIZE = 7;
    private static final int FPR_SIZE = 8;
    private static final long INT_BITS = 4294967295L;
    private static final long SHORT_BITS = 65535;
    private static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

    static class ParamTypesCache {
        int crLength;
        int fprLength;
        int stackLength;
        boolean[] typeWides;

        private ParamTypesCache() {
        }
    }

    private Arm64Entry() {
    }

    static boolean booleanBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Boolean) handleBridge(j, j2, j3, j4, j5, j6, j7)).booleanValue();
    }

    static byte byteBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Byte) handleBridge(j, j2, j3, j4, j5, j6, j7)).byteValue();
    }

    static char charBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Character) handleBridge(j, j2, j3, j4, j5, j6, j7)).charValue();
    }

    static double doubleBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Double) handleBridge(j, j2, j3, j4, j5, j6, j7)).doubleValue();
    }

    static float floatBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Float) handleBridge(j, j2, j3, j4, j5, j6, j7)).floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static C0042<long[], long[], double[]> getArgs(Pine.C0050 c0050, long j, long j2, long j3, long j4, long j5, long j6) {
        int i;
        int i2;
        int i3;
        boolean[] zArr;
        boolean z;
        boolean z2;
        if (c0050.f89 == null) {
            int i4 = c0050.f87;
            if (c0050.f86) {
                i = 0;
                i2 = 0;
            } else {
                i4++;
                i = 1;
                i2 = 1;
            }
            if (i4 != 0) {
                zArr = new boolean[i4];
                if (!c0050.f86) {
                    zArr[0] = false;
                }
                i3 = 0;
                for (int i5 = 0; i5 < c0050.f87; i5++) {
                    Class<?> cls = c0050.f88[i5];
                    if (cls == Double.TYPE) {
                        z = true;
                    } else {
                        if (cls == Float.TYPE) {
                            z = true;
                        } else if (cls == Long.TYPE) {
                            z = false;
                        } else {
                            z = false;
                        }
                        z2 = false;
                        if (z) {
                            if (i < 7) {
                                i++;
                            }
                        } else if (i3 < 8) {
                            i3++;
                        }
                        i2 += z2 ? 8 : 4;
                        if (c0050.f86) {
                            zArr[i5 + 1] = z2;
                        } else {
                            zArr[i5] = z2;
                        }
                    }
                    z2 = true;
                    if (z) {
                    }
                    i2 += z2 ? 8 : 4;
                    if (c0050.f86) {
                    }
                }
            } else {
                zArr = EMPTY_BOOLEAN_ARRAY;
                i3 = 0;
            }
            ParamTypesCache paramTypesCache = new ParamTypesCache();
            paramTypesCache.crLength = i;
            paramTypesCache.stackLength = i2;
            paramTypesCache.fprLength = i3;
            paramTypesCache.typeWides = (boolean[]) zArr.clone();
            c0050.f89 = paramTypesCache;
        } else {
            ParamTypesCache paramTypesCache2 = (ParamTypesCache) c0050.f89;
            i = paramTypesCache2.crLength;
            i2 = paramTypesCache2.stackLength;
            i3 = paramTypesCache2.fprLength;
            zArr = (boolean[]) paramTypesCache2.typeWides.clone();
        }
        boolean[] zArr2 = zArr;
        int i6 = j2 != 0 ? i2 : 0;
        long[] jArr = i != 0 ? new long[i] : EMPTY_LONG_ARRAY;
        long[] jArr2 = i6 != 0 ? new long[i6] : EMPTY_LONG_ARRAY;
        double[] dArr = i3 != 0 ? new double[i3] : EMPTY_DOUBLE_ARRAY;
        Pine.getArgsArm64(j, j2, zArr2, jArr, jArr2, dArr);
        if (i >= 4) {
            jArr[3] = j3;
            if (i != 4) {
                jArr[4] = j4;
                if (i != 5) {
                    jArr[5] = j5;
                    if (i != 6) {
                        jArr[6] = j6;
                    }
                }
            }
        }
        return new C0042<>(jArr, jArr2, dArr);
    }

    private static Object handleBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        Object objM472;
        int i;
        int i2;
        Object[] objArr;
        long j8;
        Object objM4722;
        long jDoubleToLongBits;
        int i3;
        Object objValueOf;
        long jCloneExtras = Pine.cloneExtras(j2);
        Pine.m478(NPStringFog.decode("06110305020425171B0A17085B4E0015113F0B04050E0A5C42460A4E1F1F08090809200A1A020C125344441D520B0819130F125A405116501E115344441D"), Long.valueOf(j), Long.valueOf(j2), Long.valueOf(jCloneExtras), Long.valueOf(j3));
        Pine.C0050 c0050M477 = Pine.m477(j);
        C0042<long[], long[], double[]> args = getArgs(c0050M477, jCloneExtras, j3, j4, j5, j6, j7);
        long[] jArr = args.f70;
        long[] jArr2 = args.f71;
        double[] dArr = args.f72;
        long jCurrentArtThread0 = Pine.currentArtThread0();
        if (c0050M477.f86) {
            objM472 = null;
            i = 0;
            i2 = 0;
        } else {
            objM472 = Pine.m472(jCurrentArtThread0, jArr[0]);
            i = 1;
            i2 = 1;
        }
        if (c0050M477.f87 > 0) {
            objArr = new Object[c0050M477.f87];
            int i4 = 0;
            int i5 = 0;
            while (i4 < c0050M477.f87) {
                Class<?> cls = c0050M477.f88[i4];
                if (cls == Double.TYPE) {
                    if (i5 < dArr.length) {
                        i3 = i5 + 1;
                        objValueOf = Double.valueOf(dArr[i5]);
                        objArr[i4] = objValueOf;
                        i++;
                        i4++;
                        i5 = i3;
                    } else {
                        objM4722 = Double.valueOf(Double.longBitsToDouble(jArr2[i]));
                    }
                } else if (cls == Float.TYPE) {
                    if (i5 < dArr.length) {
                        jDoubleToLongBits = Double.doubleToLongBits(dArr[i5]);
                        i5++;
                    } else {
                        jDoubleToLongBits = jArr2[i];
                    }
                    objM4722 = Float.valueOf(Float.intBitsToFloat((int) (jDoubleToLongBits & INT_BITS)));
                } else {
                    if (i2 < jArr.length) {
                        j8 = jArr[i2];
                        i2++;
                    } else {
                        j8 = jArr2[i];
                    }
                    if (!cls.isPrimitive()) {
                        objM4722 = Pine.m472(jCurrentArtThread0, j8 & INT_BITS);
                    } else if (cls == Integer.TYPE) {
                        objM4722 = Integer.valueOf((int) (j8 & INT_BITS));
                    } else if (cls == Long.TYPE) {
                        objM4722 = Long.valueOf(j8);
                    } else if (cls == Boolean.TYPE) {
                        objM4722 = Boolean.valueOf(j8 != 0);
                    } else if (cls == Short.TYPE) {
                        objM4722 = Short.valueOf((short) (j8 & 65535));
                    } else if (cls == Character.TYPE) {
                        objM4722 = Character.valueOf((char) (j8 & 65535));
                    } else {
                        if (cls != Byte.TYPE) {
                            throw new AssertionError(NPStringFog.decode("3B1E060F01160945021C1900081A081100521A091D045441") + cls);
                        }
                        objM4722 = Byte.valueOf((byte) (j8 & BYTE_BITS));
                    }
                }
                Object obj = objM4722;
                i3 = i5;
                objValueOf = obj;
                objArr[i4] = objValueOf;
                i++;
                i4++;
                i5 = i3;
            }
        } else {
            objArr = Pine.EMPTY_OBJECT_ARRAY;
        }
        return Pine.m474(c0050M477, objM472, objArr);
    }

    static int intBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Integer) handleBridge(j, j2, j3, j4, j5, j6, j7)).intValue();
    }

    static long longBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Long) handleBridge(j, j2, j3, j4, j5, j6, j7)).longValue();
    }

    static Object objectBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return handleBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static short shortBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return ((Short) handleBridge(j, j2, j3, j4, j5, j6, j7)).shortValue();
    }

    static void voidBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        handleBridge(j, j2, j3, j4, j5, j6, j7);
    }
}
