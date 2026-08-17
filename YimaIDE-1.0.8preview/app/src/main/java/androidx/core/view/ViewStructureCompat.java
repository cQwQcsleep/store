package androidx.core.view;

import android.view.ViewStructure;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ViewStructureCompat {
    private final ViewStructure mWrappedObj;

    private ViewStructureCompat(ViewStructure viewStructure) {
        this.mWrappedObj = viewStructure;
    }

    public static ViewStructureCompat toViewStructureCompat(ViewStructure viewStructure) {
        return new ViewStructureCompat(viewStructure);
    }

    public void setClassName(String str) {
        this.mWrappedObj.setClassName(str);
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mWrappedObj.setContentDescription(charSequence);
    }

    public void setDimens(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mWrappedObj.setDimens(i, i2, i3, i4, i5, i6);
    }

    public void setText(CharSequence charSequence) {
        this.mWrappedObj.setText(charSequence);
    }

    public ViewStructure toViewStructure() {
        return this.mWrappedObj;
    }
}
