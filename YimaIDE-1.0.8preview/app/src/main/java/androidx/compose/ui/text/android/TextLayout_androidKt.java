package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u001d\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0000¢\u0006\u0002\u0010\u000f\u001a\u0011\u0010\u0010\u001a\u00020\f*\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012\u001a\u0017\u0010\u0015\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002¢\u0006\u0002\u0010\u0018\u001a3\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\b2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010\u001f\u001a\u0019\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016*\u00020\u0011H\u0002¢\u0006\u0002\u0010!\u001a\u0014\u0010\"\u001a\u00020#*\u00020$2\u0006\u0010%\u001a\u00020\nH\u0000\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0010\u0010\u0013\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014¨\u0006&"}, d2 = {"SharedTextAndroidCanvas", "Ljava/lang/ThreadLocal;", "Landroidx/compose/ui/text/android/TextAndroidCanvas;", "getSharedTextAndroidCanvas$annotations", "()V", "getSharedTextAndroidCanvas", "()Ljava/lang/ThreadLocal;", "getTextDirectionHeuristic", "Landroid/text/TextDirectionHeuristic;", "textDirectionHeuristic", "", "VerticalPaddings", "Landroidx/compose/ui/text/android/VerticalPaddings;", "topPadding", "bottomPadding", "(II)J", "getVerticalPaddings", "Landroidx/compose/ui/text/android/TextLayout;", "(Landroidx/compose/ui/text/android/TextLayout;)J", "ZeroVerticalPadding", "J", "getLineHeightPaddings", "", "Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "([Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;)J", "getLastLineMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "textPaint", "Landroid/text/TextPaint;", "frameworkTextDir", "lineHeightSpans", "(Landroidx/compose/ui/text/android/TextLayout;Landroid/text/TextPaint;Landroid/text/TextDirectionHeuristic;[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;)Landroid/graphics/Paint$FontMetricsInt;", "getLineHeightSpans", "(Landroidx/compose/ui/text/android/TextLayout;)[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "isLineEllipsized", "", "Landroid/text/Layout;", "lineIndex", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextLayout_androidKt {
    private static final ThreadLocal<TextAndroidCanvas> SharedTextAndroidCanvas = new ThreadLocal<>();
    private static final long ZeroVerticalPadding = VerticalPaddings(0, 0);

    public static final long VerticalPaddings(int i, int i2) {
        return VerticalPaddings.m5527constructorimpl((((long) i2) & 4294967295L) | (((long) i) << 32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paint.FontMetricsInt getLastLineMetrics(TextLayout textLayout, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int lineCount = textLayout.getLineCount() - 1;
        if (textLayout.getLayout().getLineStart(lineCount) != textLayout.getLayout().getLineEnd(lineCount) || lineHeightStyleSpanArr == null || lineHeightStyleSpanArr.length == 0) {
            return null;
        }
        SpannableString spannableString = new SpannableString("\u200b");
        LineHeightStyleSpan lineHeightStyleSpan = (LineHeightStyleSpan) ArraysKt.first(lineHeightStyleSpanArr);
        spannableString.setSpan(lineHeightStyleSpan.copy$ui_text(0, spannableString.length(), (lineCount == 0 || !lineHeightStyleSpan.getTrimLastLineBottom()) ? lineHeightStyleSpan.getTrimLastLineBottom() : false), 0, spannableString.length(), 33);
        StaticLayout staticLayoutCreate = StaticLayoutFactory.INSTANCE.create(spannableString, textPaint, Integer.MAX_VALUE, (2072512 & 8) != 0 ? 0 : 0, (2072512 & 16) != 0 ? spannableString.length() : spannableString.length(), (2072512 & 32) != 0 ? LayoutCompat.INSTANCE.getDEFAULT_TEXT_DIRECTION_HEURISTIC$ui_text() : textDirectionHeuristic, (2072512 & 64) != 0 ? LayoutCompat.INSTANCE.getDEFAULT_LAYOUT_ALIGNMENT$ui_text() : null, (2072512 & 128) != 0 ? Integer.MAX_VALUE : 0, (2072512 & 256) != 0 ? null : null, (2072512 & 512) != 0 ? Integer.MAX_VALUE : 0, (2072512 & 1024) != 0 ? 1.0f : 0.0f, (2072512 & 2048) != 0 ? 0.0f : 0.0f, (2072512 & 4096) != 0 ? 0 : 0, (2072512 & 8192) != 0 ? false : textLayout.getIncludePadding(), (2072512 & 16384) != 0 ? true : textLayout.getFallbackLineSpacing(), (32768 & 2072512) != 0 ? 0 : 0, (65536 & 2072512) != 0 ? 0 : 0, (131072 & 2072512) != 0 ? 0 : 0, (262144 & 2072512) != 0 ? 0 : 0, (524288 & 2072512) != 0 ? null : null, (2072512 & 1048576) != 0 ? null : null);
        Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
        fontMetricsInt.ascent = staticLayoutCreate.getLineAscent(0);
        fontMetricsInt.descent = staticLayoutCreate.getLineDescent(0);
        fontMetricsInt.top = staticLayoutCreate.getLineTop(0);
        fontMetricsInt.bottom = staticLayoutCreate.getLineBottom(0);
        return fontMetricsInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getLineHeightPaddings(LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int iMax = 0;
        int iMax2 = 0;
        for (LineHeightStyleSpan lineHeightStyleSpan : lineHeightStyleSpanArr) {
            if (lineHeightStyleSpan.getFirstAscentDiff() < 0) {
                iMax = Math.max(iMax, Math.abs(lineHeightStyleSpan.getFirstAscentDiff()));
            }
            if (lineHeightStyleSpan.getLastDescentDiff() < 0) {
                iMax2 = Math.max(iMax, Math.abs(lineHeightStyleSpan.getLastDescentDiff()));
            }
        }
        return (iMax == 0 && iMax2 == 0) ? ZeroVerticalPadding : VerticalPaddings(iMax, iMax2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LineHeightStyleSpan[] getLineHeightSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = textLayout.getText();
        text.getClass();
        if (!SpannedExtensions_androidKt.hasSpan((Spanned) text, LineHeightStyleSpan.class) && textLayout.getText().length() > 0) {
            return null;
        }
        CharSequence text2 = textLayout.getText();
        text2.getClass();
        return (LineHeightStyleSpan[]) ((Spanned) text2).getSpans(0, textLayout.getText().length(), LineHeightStyleSpan.class);
    }

    public static final ThreadLocal<TextAndroidCanvas> getSharedTextAndroidCanvas() {
        return SharedTextAndroidCanvas;
    }

    public static /* synthetic */ void getSharedTextAndroidCanvas$annotations() {
    }

    public static final TextDirectionHeuristic getTextDirectionHeuristic(int i) {
        if (i == 0) {
            return TextDirectionHeuristics.LTR;
        }
        if (i == 1) {
            return TextDirectionHeuristics.RTL;
        }
        if (i == 2) {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
        if (i == 3) {
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        }
        if (i != 4) {
            return i != 5 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : TextDirectionHeuristics.LOCALE;
        }
        return TextDirectionHeuristics.ANYRTL_LTR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getVerticalPaddings(TextLayout textLayout) {
        if (textLayout.getIncludePadding() || textLayout.isFallbackLinespacingApplied$ui_text()) {
            return ZeroVerticalPadding;
        }
        TextPaint paint = textLayout.getLayout().getPaint();
        CharSequence text = textLayout.getLayout().getText();
        Rect charSequenceBounds = PaintExtensions_androidKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(0), textLayout.getLayout().getLineEnd(0));
        int lineAscent = textLayout.getLayout().getLineAscent(0);
        int i = charSequenceBounds.top;
        int topPadding = i < lineAscent ? lineAscent - i : textLayout.getLayout().getTopPadding();
        if (textLayout.getLineCount() != 1) {
            int lineCount = textLayout.getLineCount() - 1;
            charSequenceBounds = PaintExtensions_androidKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(lineCount), textLayout.getLayout().getLineEnd(lineCount));
        }
        int lineDescent = textLayout.getLayout().getLineDescent(textLayout.getLineCount() - 1);
        int i2 = charSequenceBounds.bottom;
        int bottomPadding = i2 > lineDescent ? i2 - lineDescent : textLayout.getLayout().getBottomPadding();
        return (topPadding == 0 && bottomPadding == 0) ? ZeroVerticalPadding : VerticalPaddings(topPadding, bottomPadding);
    }

    public static final boolean isLineEllipsized(Layout layout, int i) {
        return layout.getEllipsisCount(i) > 0;
    }
}
