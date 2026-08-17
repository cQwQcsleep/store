package io.github.rosemoe.sora.lang.completion;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0017R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/CircleDrawable;", "Landroid/graphics/drawable/Drawable;", "kind", "Lio/github/rosemoe/sora/lang/completion/CompletionItemKind;", "circle", "", "<init>", "(Lio/github/rosemoe/sora/lang/completion/CompletionItemKind;Z)V", "mPaint", "Landroid/graphics/Paint;", "mTextPaint", "mKind", "mCircle", "draw", "", "canvas", "Landroid/graphics/Canvas;", "setAlpha", "p1", "", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "getOpacity", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class CircleDrawable extends Drawable {
    private final boolean mCircle;
    private final CompletionItemKind mKind;
    private final Paint mPaint;
    private final Paint mTextPaint;

    public CircleDrawable(CompletionItemKind completionItemKind, boolean z) {
        completionItemKind.getClass();
        this.mKind = completionItemKind;
        this.mCircle = z;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor((int) completionItemKind.getDefaultDisplayBackgroundColor());
        this.mPaint = paint;
        Paint paint2 = new Paint();
        paint2.setColor(-1);
        paint2.setAntiAlias(true);
        paint2.setTextSize(Resources.getSystem().getDisplayMetrics().density * 14.0f);
        paint2.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint = paint2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Canvas canvas2;
        canvas.getClass();
        float f = getBounds().right;
        float f2 = getBounds().bottom;
        boolean z = this.mCircle;
        Paint paint = this.mPaint;
        if (z) {
            float f3 = f / 2.0f;
            canvas.drawCircle(f3, f2 / 2.0f, f3, paint);
            canvas2 = canvas;
        } else {
            canvas2 = canvas;
            canvas2.drawRect(0.0f, 0.0f, f, f2, paint);
        }
        canvas2.save();
        canvas2.translate(f / 2.0f, f2 / 2.0f);
        canvas2.drawText(this.mKind.getDisplayString(), 0.0f, (-(this.mTextPaint.descent() + this.mTextPaint.ascent())) / 2.0f, this.mTextPaint);
        canvas2.restore();
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated(message = "Deprecated in Java", replaceWith = @ReplaceWith(expression = "PixelFormat.OPAQUE", imports = {"android.graphics.PixelFormat"}))
    public int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int p1) {
        this.mPaint.setAlpha(p1);
        this.mTextPaint.setAlpha(p1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mTextPaint.setColorFilter(colorFilter);
    }
}
