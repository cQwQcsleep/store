package org.eclipse.jdt.internal.compiler.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashSetOfInt implements Cloneable {
    public int elementSize;
    public int[] set;
    int threshold;

    public HashSetOfInt(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.75f);
        this.set = new int[i == i2 ? i2 + 1 : i2];
    }

    private void rehash() {
        HashSetOfInt hashSetOfInt = new HashSetOfInt(this.elementSize * 2);
        int length = this.set.length;
        while (true) {
            length--;
            if (length < 0) {
                this.set = hashSetOfInt.set;
                this.threshold = hashSetOfInt.threshold;
                return;
            } else {
                int i = this.set[length];
                if (i != 0) {
                    hashSetOfInt.add(i);
                }
            }
        }
    }

    public int add(int i) {
        int length = this.set.length;
        int i2 = i % length;
        while (true) {
            int[] iArr = this.set;
            int i3 = iArr[i2];
            if (i3 == 0) {
                iArr[i2] = i;
                int i4 = this.elementSize + 1;
                this.elementSize = i4;
                if (i4 > this.threshold) {
                    rehash();
                }
                return i;
            }
            if (i3 == i) {
                iArr[i2] = i;
                return i;
            }
            i2++;
            if (i2 == length) {
                i2 = 0;
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        HashSetOfInt hashSetOfInt = (HashSetOfInt) super.clone();
        hashSetOfInt.elementSize = this.elementSize;
        hashSetOfInt.threshold = this.threshold;
        int length = this.set.length;
        int[] iArr = new int[length];
        hashSetOfInt.set = iArr;
        System.arraycopy(this.set, 0, iArr, 0, length);
        return hashSetOfInt;
    }

    public boolean contains(int i) {
        int length = this.set.length;
        int i2 = i % length;
        while (true) {
            int i3 = this.set[i2];
            if (i3 == 0) {
                return false;
            }
            if (i3 == i) {
                return true;
            }
            i2++;
            if (i2 == length) {
                i2 = 0;
            }
        }
    }

    public int remove(int i) {
        int length = this.set.length;
        int i2 = i % length;
        while (true) {
            int[] iArr = this.set;
            int i3 = iArr[i2];
            if (i3 == 0) {
                return 0;
            }
            if (i3 == i) {
                this.elementSize--;
                iArr[i2] = 0;
                rehash();
                return i3;
            }
            i2++;
            if (i2 == length) {
                i2 = 0;
            }
        }
    }

    public int size() {
        return this.elementSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.set.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.set[i];
            if (i2 != 0) {
                sb.append(i2);
                if (i != length - 1) {
                    sb.append('\n');
                }
            }
        }
        return sb.toString();
    }

    public HashSetOfInt() {
        this(13);
    }
}
