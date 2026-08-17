package io.github.rosemoe.sora.graphics;

import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003Jm\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006/"}, d2 = {"Lio/github/rosemoe/sora/graphics/InlayHintRenderParams;", "", "tabWidth", "", "textMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "textTop", "textBottom", "textHeight", "textBaseline", "rowTop", "rowBottom", "rowHeight", "roundTextBackgroundFactor", "", "<init>", "(ILandroid/graphics/Paint$FontMetricsInt;IIIIIIIF)V", "getTabWidth", "()I", "getTextMetrics", "()Landroid/graphics/Paint$FontMetricsInt;", "getTextTop", "getTextBottom", "getTextHeight", "getTextBaseline", "getRowTop", "getRowBottom", "getRowHeight", "getRoundTextBackgroundFactor", "()F", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class InlayHintRenderParams {
    private final float roundTextBackgroundFactor;
    private final int rowBottom;
    private final int rowHeight;
    private final int rowTop;
    private final int tabWidth;
    private final int textBaseline;
    private final int textBottom;
    private final int textHeight;
    private final android.graphics.Paint.FontMetricsInt textMetrics;
    private final int textTop;

    public InlayHintRenderParams(int i, android.graphics.Paint.FontMetricsInt fontMetricsInt, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f) {
        fontMetricsInt.getClass();
        this.tabWidth = i;
        this.textMetrics = fontMetricsInt;
        this.textTop = i2;
        this.textBottom = i3;
        this.textHeight = i4;
        this.textBaseline = i5;
        this.rowTop = i6;
        this.rowBottom = i7;
        this.rowHeight = i8;
        this.roundTextBackgroundFactor = f;
    }

    public static /* synthetic */ InlayHintRenderParams copy$default(InlayHintRenderParams inlayHintRenderParams, int i, android.graphics.Paint.FontMetricsInt fontMetricsInt, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i = inlayHintRenderParams.tabWidth;
        }
        if ((i9 & 2) != 0) {
            fontMetricsInt = inlayHintRenderParams.textMetrics;
        }
        if ((i9 & 4) != 0) {
            i2 = inlayHintRenderParams.textTop;
        }
        if ((i9 & 8) != 0) {
            i3 = inlayHintRenderParams.textBottom;
        }
        if ((i9 & 16) != 0) {
            i4 = inlayHintRenderParams.textHeight;
        }
        if ((i9 & 32) != 0) {
            i5 = inlayHintRenderParams.textBaseline;
        }
        if ((i9 & 64) != 0) {
            i6 = inlayHintRenderParams.rowTop;
        }
        if ((i9 & CodeEditor.FLAG_DRAW_SOFT_WRAP) != 0) {
            i7 = inlayHintRenderParams.rowBottom;
        }
        if ((i9 & 256) != 0) {
            i8 = inlayHintRenderParams.rowHeight;
        }
        if ((i9 & 512) != 0) {
            f = inlayHintRenderParams.roundTextBackgroundFactor;
        }
        int i10 = i8;
        float f2 = f;
        int i11 = i6;
        int i12 = i7;
        int i13 = i4;
        int i14 = i5;
        return inlayHintRenderParams.copy(i, fontMetricsInt, i2, i3, i13, i14, i11, i12, i10, f2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getTabWidth() {
        return this.tabWidth;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getRoundTextBackgroundFactor() {
        return this.roundTextBackgroundFactor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final android.graphics.Paint.FontMetricsInt getTextMetrics() {
        return this.textMetrics;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTextTop() {
        return this.textTop;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTextBottom() {
        return this.textBottom;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getTextHeight() {
        return this.textHeight;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getTextBaseline() {
        return this.textBaseline;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getRowTop() {
        return this.rowTop;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getRowBottom() {
        return this.rowBottom;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getRowHeight() {
        return this.rowHeight;
    }

    public final InlayHintRenderParams copy(int tabWidth, android.graphics.Paint.FontMetricsInt textMetrics, int textTop, int textBottom, int textHeight, int textBaseline, int rowTop, int rowBottom, int rowHeight, float roundTextBackgroundFactor) {
        textMetrics.getClass();
        return new InlayHintRenderParams(tabWidth, textMetrics, textTop, textBottom, textHeight, textBaseline, rowTop, rowBottom, rowHeight, roundTextBackgroundFactor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InlayHintRenderParams)) {
            return false;
        }
        InlayHintRenderParams inlayHintRenderParams = (InlayHintRenderParams) other;
        return this.tabWidth == inlayHintRenderParams.tabWidth && Intrinsics.areEqual(this.textMetrics, inlayHintRenderParams.textMetrics) && this.textTop == inlayHintRenderParams.textTop && this.textBottom == inlayHintRenderParams.textBottom && this.textHeight == inlayHintRenderParams.textHeight && this.textBaseline == inlayHintRenderParams.textBaseline && this.rowTop == inlayHintRenderParams.rowTop && this.rowBottom == inlayHintRenderParams.rowBottom && this.rowHeight == inlayHintRenderParams.rowHeight && Float.compare(this.roundTextBackgroundFactor, inlayHintRenderParams.roundTextBackgroundFactor) == 0;
    }

    public final float getRoundTextBackgroundFactor() {
        return this.roundTextBackgroundFactor;
    }

    public final int getRowBottom() {
        return this.rowBottom;
    }

    public final int getRowHeight() {
        return this.rowHeight;
    }

    public final int getRowTop() {
        return this.rowTop;
    }

    public final int getTabWidth() {
        return this.tabWidth;
    }

    public final int getTextBaseline() {
        return this.textBaseline;
    }

    public final int getTextBottom() {
        return this.textBottom;
    }

    public final int getTextHeight() {
        return this.textHeight;
    }

    public final android.graphics.Paint.FontMetricsInt getTextMetrics() {
        return this.textMetrics;
    }

    public final int getTextTop() {
        return this.textTop;
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.tabWidth) * 31) + this.textMetrics.hashCode()) * 31) + Integer.hashCode(this.textTop)) * 31) + Integer.hashCode(this.textBottom)) * 31) + Integer.hashCode(this.textHeight)) * 31) + Integer.hashCode(this.textBaseline)) * 31) + Integer.hashCode(this.rowTop)) * 31) + Integer.hashCode(this.rowBottom)) * 31) + Integer.hashCode(this.rowHeight)) * 31) + Float.hashCode(this.roundTextBackgroundFactor);
    }

    public String toString() {
        return "InlayHintRenderParams(tabWidth=" + this.tabWidth + ", textMetrics=" + this.textMetrics + ", textTop=" + this.textTop + ", textBottom=" + this.textBottom + ", textHeight=" + this.textHeight + ", textBaseline=" + this.textBaseline + ", rowTop=" + this.rowTop + ", rowBottom=" + this.rowBottom + ", rowHeight=" + this.rowHeight + ", roundTextBackgroundFactor=" + this.roundTextBackgroundFactor + ")";
    }
}
