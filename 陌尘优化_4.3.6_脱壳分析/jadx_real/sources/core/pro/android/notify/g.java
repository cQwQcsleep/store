package core.pro.android.notify;

import android.util.Log;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class g {
    public static final int[] a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    public static int b = 5;

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e0, code lost:
    
        if (r7 != 4) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] a(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        int i = (length * 3) / 4;
        byte[] bArr = new byte[i];
        int[] iArr = a;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < length) {
            if (i3 == 0) {
                while (true) {
                    int i6 = i2 + 4;
                    if (i6 > length || (i4 = (iArr[bytes[i2] & 255] << 18) | (iArr[bytes[i2 + 1] & 255] << 12) | (iArr[bytes[i2 + 2] & 255] << 6) | iArr[bytes[i2 + 3] & 255]) < 0) {
                        break;
                    }
                    bArr[i5 + 2] = (byte) i4;
                    bArr[i5 + 1] = (byte) (i4 >> 8);
                    bArr[i5] = (byte) (i4 >> 16);
                    i5 += 3;
                    i2 = i6;
                }
                if (i2 >= length) {
                    break;
                }
            }
            int i7 = i2 + 1;
            int i8 = iArr[bytes[i2] & 255];
            if (i3 == 0) {
                if (i8 < 0) {
                    if (i8 != -1) {
                        break;
                    }
                } else {
                    i3++;
                    i4 = i8;
                }
            } else {
                if (i3 == 1) {
                    if (i8 < 0) {
                        if (i8 != -1) {
                            break;
                        }
                    }
                    i8 |= i4 << 6;
                } else if (i3 == 2) {
                    if (i8 < 0) {
                        if (i8 != -2) {
                            if (i8 != -1) {
                                break;
                            }
                        } else {
                            bArr[i5] = (byte) (i4 >> 4);
                            i5++;
                            i3 = 4;
                        }
                    }
                    i8 |= i4 << 6;
                } else if (i3 == 3) {
                    if (i8 < 0) {
                        if (i8 != -2) {
                            if (i8 != -1) {
                                break;
                            }
                        } else {
                            bArr[i5 + 1] = (byte) (i4 >> 2);
                            bArr[i5] = (byte) (i4 >> 10);
                            i5 += 2;
                            i3 = 5;
                        }
                    } else {
                        int i9 = i8 | (i4 << 6);
                        bArr[i5 + 2] = (byte) i9;
                        bArr[i5 + 1] = (byte) (i9 >> 8);
                        bArr[i5] = (byte) (i9 >> 16);
                        i5 += 3;
                        i4 = i9;
                        i3 = 0;
                    }
                } else if (i3 == 4) {
                    if (i8 != -2) {
                        if (i8 != -1) {
                            break;
                        }
                    } else {
                        i3++;
                    }
                } else if (i3 == 5 && i8 != -1) {
                    break;
                }
                i3++;
                i4 = i8;
            }
            i2 = i7;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                bArr[i5] = (byte) (i4 >> 4);
                i5++;
            } else if (i3 == 3) {
                int i10 = i5 + 1;
                bArr[i5] = (byte) (i4 >> 10);
                i5 += 2;
                bArr[i10] = (byte) (i4 >> 2);
            }
            if (i5 == i) {
                return bArr;
            }
            byte[] bArr2 = new byte[i5];
            System.arraycopy(bArr, 0, bArr2, 0, i5);
            return bArr2;
        }
        throw new IllegalArgumentException("bad base-64");
    }

    public static void b(Serializable serializable) {
        if (b > 0) {
            Log.e("RootBeer", c() + String.valueOf(serializable));
            Log.e("QLog", c() + String.valueOf(serializable));
        }
    }

    public static String c() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String methodName = stackTrace[2].getMethodName();
        String className = stackTrace[2].getClassName();
        int lineNumber = stackTrace[2].getLineNumber();
        return className.substring(className.lastIndexOf(46) + 1) + ": " + methodName + "() [" + lineNumber + "] - ";
    }

    public static void d(String str) {
        if (b > 4) {
            Log.v("RootBeer", c() + String.valueOf(str));
        }
    }
}
