package androidx.core.util;

import android.util.SizeF;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class SizeFCompat {
    private final float mHeight;
    private final float mWidth;

    public SizeFCompat(float f, float f2) {
        this.mWidth = Preconditions.checkArgumentFinite(f, "width");
        this.mHeight = Preconditions.checkArgumentFinite(f2, "height");
    }

    public static SizeFCompat toSizeFCompat(SizeF sizeF) {
        return new SizeFCompat(sizeF.getWidth(), sizeF.getHeight());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeFCompat)) {
            return false;
        }
        SizeFCompat sizeFCompat = (SizeFCompat) obj;
        return sizeFCompat.mWidth == this.mWidth && sizeFCompat.mHeight == this.mHeight;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.mHeight) ^ Float.floatToIntBits(this.mWidth);
    }

    public SizeF toSizeF() {
        return new SizeF(getWidth(), getHeight());
    }

    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }
}
