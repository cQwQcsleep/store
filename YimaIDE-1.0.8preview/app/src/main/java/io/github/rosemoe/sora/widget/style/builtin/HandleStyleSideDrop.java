package io.github.rosemoe.sora.widget.style.builtin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.TypedValue;
import io.github.rosemoe.sora.widget.style.SelectionHandleStyle;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class HandleStyleSideDrop extends HandleStyleDrop {
    private final Paint paint;
    private final int size;

    public HandleStyleSideDrop(Context context) {
        super(context);
        this.size = (int) TypedValue.applyDimension(1, 22.0f, context.getResources().getDisplayMetrics());
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
    }

    @Override // io.github.rosemoe.sora.widget.style.builtin.HandleStyleDrop, io.github.rosemoe.sora.widget.style.SelectionHandleStyle
    public void draw(Canvas canvas, int i, float f, float f2, int i2, int i3, SelectionHandleStyle.HandleDescriptor handleDescriptor) {
        float f3 = this.size / 2.0f;
        this.paint.setColor(i3);
        if (i == 0 || i == -1) {
            super.draw(canvas, i, f, f2, i2, i3, handleDescriptor);
            return;
        }
        boolean z = i == 1;
        float f4 = z ? f - f3 : f + f3;
        float f5 = f2 + f3;
        canvas.drawCircle(f4, f5, f3, this.paint);
        canvas.drawRect(z ? f4 : f4 - f3, f2, z ? f4 + f3 : f4, f5, this.paint);
        handleDescriptor.set(f4 - f3, f2, f4 + f3, f2 + (f3 * 2.0f), z ? 1 : 2);
    }
}
