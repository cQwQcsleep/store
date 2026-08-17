package androidx.core.view.insets;

import android.graphics.drawable.ColorDrawable;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ColorProtection extends Protection {
    private int mColor;
    private final ColorDrawable mDrawable;
    private boolean mHasColor;

    public ColorProtection(int i) {
        super(i);
        this.mDrawable = new ColorDrawable();
        this.mColor = 0;
    }

    private void setColorInner(int i) {
        if (this.mColor != i) {
            this.mColor = i;
            this.mDrawable.setColor(i);
            setDrawable(this.mDrawable);
        }
    }

    @Override // androidx.core.view.insets.Protection
    public void dispatchColorHint(int i) {
        if (this.mHasColor) {
            return;
        }
        setColorInner(i);
    }

    public int getColor() {
        return this.mColor;
    }

    @Override // androidx.core.view.insets.Protection
    public boolean occupiesCorners() {
        return true;
    }

    public void setColor(int i) {
        this.mHasColor = true;
        setColorInner(i);
    }

    public ColorProtection(int i, int i2) {
        this(i);
        setColor(i2);
    }
}
