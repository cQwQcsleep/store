package io.github.rosemoe.sora.widget.style;

import android.graphics.Canvas;
import android.graphics.RectF;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface SelectionHandleStyle {
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int HANDLE_TYPE_INSERT = 0;
    public static final int HANDLE_TYPE_LEFT = 1;
    public static final int HANDLE_TYPE_RIGHT = 2;
    public static final int HANDLE_TYPE_UNDEFINED = -1;

    public static class HandleDescriptor {
        public final RectF position = new RectF();
        public int alignment = 0;

        public void set(float f, float f2, float f3, float f4, int i) {
            this.alignment = i;
            this.position.set(f, f2, f3, f4);
        }

        public void setEmpty() {
            this.position.setEmpty();
            this.alignment = 0;
        }
    }

    void draw(Canvas canvas, int i, float f, float f2, int i2, int i3, HandleDescriptor handleDescriptor);

    void setAlpha(int i);

    void setScale(float f);
}
