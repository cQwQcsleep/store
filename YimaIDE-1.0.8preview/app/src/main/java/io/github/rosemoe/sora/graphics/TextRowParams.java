package io.github.rosemoe.sora.graphics;

import io.github.rosemoe.sora.graphics.inlayHint.InlayHintRendererProvider;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010.\u001a\u00020/J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u000eHÆ\u0003J\t\u0010:\u001a\u00020\u0010HÆ\u0003J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\t\u0010<\u001a\u00020\u0014HÆ\u0003J\t\u0010=\u001a\u00020\u0014HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\u009f\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0005HÆ\u0001J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020\u0003HÖ\u0001J\t\u0010D\u001a\u00020EHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0015\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b,\u0010+R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001c¨\u0006F"}, d2 = {"Lio/github/rosemoe/sora/graphics/TextRowParams;", "", "tabWidth", "", "textMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "textTop", "textBottom", "textHeight", "textBaseline", "rowTop", "rowBottom", "rowHeight", "roundTextBackgroundFactor", "", "inlayHintRendererProvider", "Lio/github/rosemoe/sora/graphics/inlayHint/InlayHintRendererProvider;", "colorScheme", "Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;", "miscPaint", "Landroid/graphics/Paint;", "graphPaint", "graphMetrics", "<init>", "(ILandroid/graphics/Paint$FontMetricsInt;IIIIIIIFLio/github/rosemoe/sora/graphics/inlayHint/InlayHintRendererProvider;Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;Landroid/graphics/Paint;Landroid/graphics/Paint;Landroid/graphics/Paint$FontMetricsInt;)V", "getTabWidth", "()I", "getTextMetrics", "()Landroid/graphics/Paint$FontMetricsInt;", "getTextTop", "getTextBottom", "getTextHeight", "getTextBaseline", "getRowTop", "getRowBottom", "getRowHeight", "getRoundTextBackgroundFactor", "()F", "getInlayHintRendererProvider", "()Lio/github/rosemoe/sora/graphics/inlayHint/InlayHintRendererProvider;", "getColorScheme", "()Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;", "getMiscPaint", "()Landroid/graphics/Paint;", "getGraphPaint", "getGraphMetrics", "toInlayHintRenderParams", "Lio/github/rosemoe/sora/graphics/InlayHintRenderParams;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", "toString", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class TextRowParams {
    private final EditorColorScheme colorScheme;
    private final android.graphics.Paint.FontMetricsInt graphMetrics;
    private final android.graphics.Paint graphPaint;
    private final InlayHintRendererProvider inlayHintRendererProvider;
    private final android.graphics.Paint miscPaint;
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

    public TextRowParams(int i, android.graphics.Paint.FontMetricsInt fontMetricsInt, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f, InlayHintRendererProvider inlayHintRendererProvider, EditorColorScheme editorColorScheme, android.graphics.Paint paint, android.graphics.Paint paint2, android.graphics.Paint.FontMetricsInt fontMetricsInt2) {
        fontMetricsInt.getClass();
        inlayHintRendererProvider.getClass();
        editorColorScheme.getClass();
        paint.getClass();
        paint2.getClass();
        fontMetricsInt2.getClass();
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
        this.inlayHintRendererProvider = inlayHintRendererProvider;
        this.colorScheme = editorColorScheme;
        this.miscPaint = paint;
        this.graphPaint = paint2;
        this.graphMetrics = fontMetricsInt2;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getTabWidth() {
        return this.tabWidth;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getRoundTextBackgroundFactor() {
        return this.roundTextBackgroundFactor;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final InlayHintRendererProvider getInlayHintRendererProvider() {
        return this.inlayHintRendererProvider;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final EditorColorScheme getColorScheme() {
        return this.colorScheme;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final android.graphics.Paint getMiscPaint() {
        return this.miscPaint;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final android.graphics.Paint getGraphPaint() {
        return this.graphPaint;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final android.graphics.Paint.FontMetricsInt getGraphMetrics() {
        return this.graphMetrics;
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

    public final TextRowParams copy(int tabWidth, android.graphics.Paint.FontMetricsInt textMetrics, int textTop, int textBottom, int textHeight, int textBaseline, int rowTop, int rowBottom, int rowHeight, float roundTextBackgroundFactor, InlayHintRendererProvider inlayHintRendererProvider, EditorColorScheme colorScheme, android.graphics.Paint miscPaint, android.graphics.Paint graphPaint, android.graphics.Paint.FontMetricsInt graphMetrics) {
        textMetrics.getClass();
        inlayHintRendererProvider.getClass();
        colorScheme.getClass();
        miscPaint.getClass();
        graphPaint.getClass();
        graphMetrics.getClass();
        return new TextRowParams(tabWidth, textMetrics, textTop, textBottom, textHeight, textBaseline, rowTop, rowBottom, rowHeight, roundTextBackgroundFactor, inlayHintRendererProvider, colorScheme, miscPaint, graphPaint, graphMetrics);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextRowParams)) {
            return false;
        }
        TextRowParams textRowParams = (TextRowParams) other;
        return this.tabWidth == textRowParams.tabWidth && Intrinsics.areEqual(this.textMetrics, textRowParams.textMetrics) && this.textTop == textRowParams.textTop && this.textBottom == textRowParams.textBottom && this.textHeight == textRowParams.textHeight && this.textBaseline == textRowParams.textBaseline && this.rowTop == textRowParams.rowTop && this.rowBottom == textRowParams.rowBottom && this.rowHeight == textRowParams.rowHeight && Float.compare(this.roundTextBackgroundFactor, textRowParams.roundTextBackgroundFactor) == 0 && Intrinsics.areEqual(this.inlayHintRendererProvider, textRowParams.inlayHintRendererProvider) && Intrinsics.areEqual(this.colorScheme, textRowParams.colorScheme) && Intrinsics.areEqual(this.miscPaint, textRowParams.miscPaint) && Intrinsics.areEqual(this.graphPaint, textRowParams.graphPaint) && Intrinsics.areEqual(this.graphMetrics, textRowParams.graphMetrics);
    }

    public final EditorColorScheme getColorScheme() {
        return this.colorScheme;
    }

    public final android.graphics.Paint.FontMetricsInt getGraphMetrics() {
        return this.graphMetrics;
    }

    public final android.graphics.Paint getGraphPaint() {
        return this.graphPaint;
    }

    public final InlayHintRendererProvider getInlayHintRendererProvider() {
        return this.inlayHintRendererProvider;
    }

    public final android.graphics.Paint getMiscPaint() {
        return this.miscPaint;
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
        return (((((((((((((((((((((((((((Integer.hashCode(this.tabWidth) * 31) + this.textMetrics.hashCode()) * 31) + Integer.hashCode(this.textTop)) * 31) + Integer.hashCode(this.textBottom)) * 31) + Integer.hashCode(this.textHeight)) * 31) + Integer.hashCode(this.textBaseline)) * 31) + Integer.hashCode(this.rowTop)) * 31) + Integer.hashCode(this.rowBottom)) * 31) + Integer.hashCode(this.rowHeight)) * 31) + Float.hashCode(this.roundTextBackgroundFactor)) * 31) + this.inlayHintRendererProvider.hashCode()) * 31) + this.colorScheme.hashCode()) * 31) + this.miscPaint.hashCode()) * 31) + this.graphPaint.hashCode()) * 31) + this.graphMetrics.hashCode();
    }

    public final InlayHintRenderParams toInlayHintRenderParams() {
        return new InlayHintRenderParams(this.tabWidth, this.textMetrics, this.textTop, this.textBottom, this.textHeight, this.textBaseline, this.rowTop, this.rowBottom, this.rowHeight, this.roundTextBackgroundFactor);
    }

    public String toString() {
        return "TextRowParams(tabWidth=" + this.tabWidth + ", textMetrics=" + this.textMetrics + ", textTop=" + this.textTop + ", textBottom=" + this.textBottom + ", textHeight=" + this.textHeight + ", textBaseline=" + this.textBaseline + ", rowTop=" + this.rowTop + ", rowBottom=" + this.rowBottom + ", rowHeight=" + this.rowHeight + ", roundTextBackgroundFactor=" + this.roundTextBackgroundFactor + ", inlayHintRendererProvider=" + this.inlayHintRendererProvider + ", colorScheme=" + this.colorScheme + ", miscPaint=" + this.miscPaint + ", graphPaint=" + this.graphPaint + ", graphMetrics=" + this.graphMetrics + ")";
    }
}
