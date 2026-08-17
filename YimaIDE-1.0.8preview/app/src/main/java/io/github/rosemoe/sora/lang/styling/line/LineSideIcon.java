package io.github.rosemoe.sora.lang.styling.line;

import android.graphics.drawable.Drawable;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/line/LineSideIcon;", "Lio/github/rosemoe/sora/lang/styling/line/LineAnchorStyle;", "line", "", "drawable", "Landroid/graphics/drawable/Drawable;", "<init>", "(ILandroid/graphics/drawable/Drawable;)V", "getLine", "()I", "setLine", "(I)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class LineSideIcon extends LineAnchorStyle {
    private final Drawable drawable;
    private int line;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineSideIcon(int i, Drawable drawable) {
        super(i);
        drawable.getClass();
        this.line = i;
        this.drawable = drawable;
    }

    public static /* synthetic */ LineSideIcon copy$default(LineSideIcon lineSideIcon, int i, Drawable drawable, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = lineSideIcon.line;
        }
        if ((i2 & 2) != 0) {
            drawable = lineSideIcon.drawable;
        }
        return lineSideIcon.copy(i, drawable);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLine() {
        return this.line;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final LineSideIcon copy(int line, Drawable drawable) {
        drawable.getClass();
        return new LineSideIcon(line, drawable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LineSideIcon)) {
            return false;
        }
        LineSideIcon lineSideIcon = (LineSideIcon) other;
        return this.line == lineSideIcon.line && Intrinsics.areEqual(this.drawable, lineSideIcon.drawable);
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public int getLine() {
        return this.line;
    }

    public int hashCode() {
        return (Integer.hashCode(this.line) * 31) + this.drawable.hashCode();
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public void setLine(int i) {
        this.line = i;
    }

    public String toString() {
        return "LineSideIcon(line=" + this.line + ", drawable=" + this.drawable + ")";
    }
}
