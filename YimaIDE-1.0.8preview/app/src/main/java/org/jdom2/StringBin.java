package org.jdom2;

import org.jdom2.internal.ArrayCopy;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
final class StringBin {
    private static final int DEFAULTCAP = 1023;
    private static final int GROW = 4;
    private static final int MAXBUCKET = 64;
    private String[][] buckets;
    private int[] lengths;
    private int mask;

    public StringBin(int i) {
        int i2 = 0;
        this.mask = 0;
        if (i < 0) {
            w01.a("Can not have a negative capacity");
            throw null;
        }
        int i3 = i - 1;
        int i4 = (i3 < DEFAULTCAP ? DEFAULTCAP : i3) / 3;
        while (i4 != 0) {
            i4 >>>= 1;
            i2++;
        }
        int i5 = 1 << i2;
        this.mask = i5 - 1;
        String[][] strArr = new String[i5][];
        this.buckets = strArr;
        this.lengths = new int[strArr.length];
    }

    private static final String compact(String str) {
        return new String(str.toCharArray());
    }

    private final int locate(int i, String str, String[] strArr, int i2) {
        int i3;
        int iCompareTo;
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            if (strArr[i6].hashCode() > i) {
                i4 = i6 - 1;
            } else {
                if (strArr[i6].hashCode() >= i) {
                    int iCompareTo2 = str.compareTo(strArr[i6]);
                    if (iCompareTo2 == 0) {
                        return i6;
                    }
                    if (iCompareTo2 < 0) {
                        while (true) {
                            int i7 = i6 - 1;
                            if (i7 < i5 || strArr[i7].hashCode() != i) {
                                break;
                            }
                            int iCompareTo3 = str.compareTo(strArr[i7]);
                            if (iCompareTo3 == 0) {
                                return i7;
                            }
                            if (iCompareTo3 > 0) {
                                break;
                            }
                            i6 = i7;
                        }
                    } else {
                        do {
                            i6++;
                            if (i6 > i4 || strArr[i6].hashCode() != i) {
                                break;
                            }
                            iCompareTo = str.compareTo(strArr[i6]);
                            if (iCompareTo == 0) {
                                return i6;
                            }
                        } while (iCompareTo >= 0);
                    }
                    i3 = -i6;
                    return i3 - 1;
                }
                i5 = i6 + 1;
            }
        }
        i3 = -i5;
        return i3 - 1;
    }

    private void rehash() {
        String[][] strArr = this.buckets;
        int i = (this.mask + 1) << 2;
        this.mask = i - 1;
        String[][] strArr2 = new String[i][];
        this.buckets = strArr2;
        this.lengths = new int[strArr2.length];
        for (String[] strArr3 : strArr) {
            if (strArr3 != null) {
                for (String str : strArr3) {
                    if (str == null) {
                        break;
                    }
                    int iHashCode = str.hashCode();
                    int i2 = (iHashCode ^ (iHashCode >>> 16)) & this.mask;
                    int i3 = this.lengths[i2];
                    String[][] strArr4 = this.buckets;
                    if (i3 == 0) {
                        String[] strArr5 = new String[(strArr3.length + 4) / 4];
                        strArr4[i2] = strArr5;
                        strArr5[0] = str;
                    } else {
                        String[] strArr6 = strArr4[i2];
                        if (strArr6.length == i3) {
                            strArr4[i2] = (String[]) ArrayCopy.copyOf(strArr6, i3 + 4);
                        }
                        this.buckets[i2][i3] = str;
                    }
                    int[] iArr = this.lengths;
                    iArr[i2] = iArr[i2] + 1;
                }
            }
        }
    }

    public String reuse(String str) {
        if (str == null) {
            return null;
        }
        int iHashCode = str.hashCode();
        int i = ((iHashCode >>> 16) ^ iHashCode) & this.mask;
        int i2 = this.lengths[i];
        if (i2 == 0) {
            String strCompact = compact(str);
            String[] strArr = new String[4];
            this.buckets[i] = strArr;
            strArr[0] = strCompact;
            this.lengths[i] = 1;
            return strCompact;
        }
        String[] strArr2 = this.buckets[i];
        int i3 = -locate(iHashCode, str, strArr2, i2);
        int i4 = i3 - 1;
        if (i4 < 0) {
            return strArr2[(-i4) - 1];
        }
        if (i2 >= 64) {
            rehash();
            return reuse(str);
        }
        if (i2 == strArr2.length) {
            strArr2 = (String[]) ArrayCopy.copyOf(strArr2, i2 + 4);
            this.buckets[i] = strArr2;
        }
        System.arraycopy(strArr2, i4, strArr2, i3, i2 - i4);
        String strCompact2 = compact(str);
        strArr2[i4] = strCompact2;
        int[] iArr = this.lengths;
        iArr[i] = iArr[i] + 1;
        return strCompact2;
    }

    public int size() {
        int i = 0;
        for (int i2 : this.lengths) {
            i += i2;
        }
        return i;
    }

    public StringBin() {
        this(DEFAULTCAP);
    }
}
