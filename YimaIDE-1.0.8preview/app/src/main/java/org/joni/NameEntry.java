package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class NameEntry {
    static final int INIT_NAME_BACKREFS_ALLOC_NUM = 8;
    int backNum;
    int backRef1;
    int[] backRefs;
    public final byte[] name;
    public final int nameEnd;
    public final int nameP;

    public NameEntry(byte[] bArr, int i, int i2) {
        this.name = bArr;
        this.nameP = i;
        this.nameEnd = i2;
    }

    private void alloc() {
        this.backRefs = new int[8];
    }

    private void ensureSize() {
        int i = this.backNum;
        int[] iArr = this.backRefs;
        if (i > iArr.length) {
            int[] iArr2 = new int[iArr.length << 1];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.backRefs = iArr2;
        }
    }

    public void addBackref(int i) {
        int i2 = this.backNum + 1;
        this.backNum = i2;
        if (i2 == 1) {
            this.backRef1 = i;
            return;
        }
        if (i2 != 2) {
            ensureSize();
            this.backRefs[this.backNum - 1] = i;
        } else {
            alloc();
            int[] iArr = this.backRefs;
            iArr[0] = this.backRef1;
            iArr[1] = i;
        }
    }

    public int[] getBackRefs() {
        int i = this.backNum;
        if (i == 0) {
            return new int[0];
        }
        if (i == 1) {
            return new int[]{this.backRef1};
        }
        int[] iArr = new int[i];
        System.arraycopy(this.backRefs, 0, iArr, 0, i);
        return iArr;
    }

    public String toString() {
        byte[] bArr = this.name;
        int i = this.nameP;
        StringBuilder sb = new StringBuilder(new String(bArr, i, this.nameEnd - i).concat(" "));
        int i2 = this.backNum;
        if (i2 == 0) {
            sb.append("-");
        } else if (i2 == 1) {
            sb.append(this.backRef1);
        } else {
            for (int i3 = 0; i3 < this.backNum; i3++) {
                if (i3 > 0) {
                    sb.append(", ");
                }
                sb.append(this.backRefs[i3]);
            }
        }
        return sb.toString();
    }
}
