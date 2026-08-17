package io.github.rosemoe.sora.widget.style.builtin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import io.github.rosemoe.sora.R;
import io.github.rosemoe.sora.widget.style.SelectionHandleStyle;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class HandleStyleDrop implements SelectionHandleStyle {
    private final Drawable drawable;
    private final int height;
    private final int width;
    private int lastColor = 0;
    private int alpha = 255;
    private float scaleFactor = 1.0f;

    public HandleStyleDrop(Context context) {
        this.drawable = context.getDrawable(R.drawable.ic_sora_handle_drop).mutate();
        this.width = (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
        this.height = (int) TypedValue.applyDimension(1, 30.0f, context.getResources().getDisplayMetrics());
    }

    @Override // io.github.rosemoe.sora.widget.style.SelectionHandleStyle
    public void draw(Canvas canvas, int i, float f, float f2, int i2, int i3, SelectionHandleStyle.HandleDescriptor handleDescriptor) {
        if (this.lastColor != i3) {
            this.lastColor = i3;
            this.drawable.setColorFilter(new PorterDuffColorFilter(i3, PorterDuff.Mode.SRC_ATOP));
        }
        int i4 = this.width;
        float f3 = this.scaleFactor;
        int i5 = (int) (f - ((i4 * f3) / 2.0f));
        int i6 = (int) f2;
        int i7 = (int) (f + ((i4 * f3) / 2.0f));
        int i8 = (int) (f2 + (this.height * f3));
        this.drawable.setBounds(i5, i6, i7, i8);
        this.drawable.setAlpha(this.alpha);
        this.drawable.draw(canvas);
        handleDescriptor.set(i5, i6, i7, i8, 0);
    }

    @Override // io.github.rosemoe.sora.widget.style.SelectionHandleStyle
    public void setAlpha(int i) {
        this.alpha = i;
    }

    @Override // io.github.rosemoe.sora.widget.style.SelectionHandleStyle
    public void setScale(float f) {
        this.scaleFactor = f;
    }
}
