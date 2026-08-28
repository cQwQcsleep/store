package top.canyie.pine.entry;

import np.protect.assets.p.C0041;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;

/* loaded from: /workspace/unpacked/classes.dex */
public final class X86Entry {
    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private X86Entry() {
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

    private static int[] getArgsAsInts(Pine.C0050 c0050, int i, int i2) {
        int i3 = !c0050.f86 ? 1 : 0;
        Class<?>[] clsArr = c0050.f88;
        int length = clsArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            Class<?> cls = clsArr[i4];
            i3 += (cls == Long.TYPE || cls == Double.TYPE) ? 2 : 1;
        }
        int[] iArr = i3 != 0 ? new int[i3] : EMPTY_INT_ARRAY;
        Pine.getArgsX86(i, iArr, i2);
        return iArr;
    }

    private static Object handleBridge(int i, int i2, int i3) {
        Object objM472;
        int i4;
        Object[] objArr;
        Object objM4722;
        int i5;
        Object objValueOf;
        Pine.m478(NPStringFog.decode("06110305020425171B0A17085B4E0015113F0B04050E0A5C42460A4E1515151C001458574D084D040C195A405116"), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        Pine.C0050 c0050M477 = Pine.m477(i);
        int[] argsAsInts = getArgsAsInts(c0050M477, i2, i3);
        long jCurrentArtThread0 = Pine.currentArtThread0();
        if (c0050M477.f86) {
            objM472 = null;
            i4 = 0;
        } else {
            objM472 = Pine.m472(jCurrentArtThread0, argsAsInts[0]);
            i4 = 1;
        }
        if (c0050M477.f87 > 0) {
            objArr = new Object[c0050M477.f87];
            for (int i6 = 0; i6 < c0050M477.f87; i6++) {
                Class<?> cls = c0050M477.f88[i6];
                if (!cls.isPrimitive()) {
                    objM4722 = Pine.m472(jCurrentArtThread0, argsAsInts[i4]);
                } else if (cls == Integer.TYPE) {
                    objM4722 = Integer.valueOf(argsAsInts[i4]);
                } else {
                    if (cls == Long.TYPE) {
                        i5 = i4 + 1;
                        objValueOf = Long.valueOf(C0041.m452(argsAsInts[i4], argsAsInts[i5]));
                    } else if (cls == Double.TYPE) {
                        i5 = i4 + 1;
                        objValueOf = Double.valueOf(C0041.m450(argsAsInts[i4], argsAsInts[i5]));
                    } else if (cls == Float.TYPE) {
                        objM4722 = Float.valueOf(Float.intBitsToFloat(argsAsInts[i4]));
                    } else if (cls == Boolean.TYPE) {
                        objM4722 = Boolean.valueOf(argsAsInts[i4] != 0);
                    } else if (cls == Short.TYPE) {
                        objM4722 = Short.valueOf((short) argsAsInts[i4]);
                    } else if (cls == Character.TYPE) {
                        objM4722 = Character.valueOf((char) argsAsInts[i4]);
                    } else {
                        if (cls != Byte.TYPE) {
                            throw new AssertionError(NPStringFog.decode("3B1E060F01160945021C1900081A081100521A091D045441") + cls);
                        }
                        objM4722 = Byte.valueOf((byte) argsAsInts[i4]);
                    }
                    int i7 = i5;
                    objM4722 = objValueOf;
                    i4 = i7;
                }
                objArr[i6] = objM4722;
                i4++;
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
