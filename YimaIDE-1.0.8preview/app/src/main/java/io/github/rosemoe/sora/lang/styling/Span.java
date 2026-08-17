package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.lang.styling.color.ConstColor;
import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.lang.styling.span.SpanExt;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Span {
    static Span obtain(int i, long j) {
        return SpanFactory.obtain(i, j);
    }

    static void recycleAll(Collection<Span> collection) {
        SpanFactory.recycleAll(collection);
    }

    Span copy();

    default int getBackgroundColorId() {
        return TextStyle.getBackgroundColorId(getStyle());
    }

    int getColumn();

    Object getExtra();

    default int getForegroundColorId() {
        return TextStyle.getForegroundColorId(getStyle());
    }

    <T> T getSpanExt(int i);

    long getStyle();

    default long getStyleBits() {
        return TextStyle.getStyleBits(getStyle());
    }

    ResolvableColor getUnderlineColor();

    boolean hasSpanExt(int i);

    boolean recycle();

    void removeAllSpanExt();

    void reset();

    void setColumn(int i);

    void setExtra(Object obj);

    void setSpanExt(int i, SpanExt spanExt);

    void setStyle(long j);

    default void setUnderlineColor(int i) {
        if (i == 0) {
            setUnderlineColor((ResolvableColor) null);
        } else {
            setUnderlineColor(new ConstColor(i));
        }
    }

    void setUnderlineColor(ResolvableColor resolvableColor);

    default void shiftColumnBy(int i) {
        setColumn(getColumn() + i);
    }
}
