package io.github.rosemoe.sora.lang.styling.span.internal;

import io.github.rosemoe.sora.lang.styling.Span;
import io.github.rosemoe.sora.lang.styling.SpanPool;
import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.lang.styling.span.SpanExt;
import io.github.rosemoe.sora.lang.styling.span.internal.NoExtSpanImpl;
import java.util.Objects;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NoExtSpanImpl implements Span {
    private static final SpanPool<NoExtSpanImpl> pool = new SpanPool<>(new Function2() { // from class: gia
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return new NoExtSpanImpl(((Integer) obj).intValue(), ((Long) obj2).longValue());
        }
    });
    private int column;
    private Object extra;
    private long style;

    public NoExtSpanImpl(int i, long j) {
        this.column = i;
        this.style = j;
    }

    public static NoExtSpanImpl obtain(int i, long j) {
        return (NoExtSpanImpl) pool.obtain(i, j);
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public Span copy() {
        return new NoExtSpanImpl(this.column, this.style);
    }

    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            NoExtSpanImpl noExtSpanImpl = (NoExtSpanImpl) obj;
            if (this.column == noExtSpanImpl.column && this.style == noExtSpanImpl.style && Objects.equals(this.extra, noExtSpanImpl.extra)) {
                return true;
            }
        }
        return false;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public int getColumn() {
        return this.column;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public Object getExtra() {
        return this.extra;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public <T> T getSpanExt(int i) {
        return null;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public long getStyle() {
        return this.style;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public ResolvableColor getUnderlineColor() {
        return null;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public boolean hasSpanExt(int i) {
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.column), Long.valueOf(this.style), this.extra);
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public boolean recycle() {
        reset();
        return pool.offer(this);
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void removeAllSpanExt() {
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void reset() {
        setColumn(0);
        setStyle(0L);
        this.extra = null;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void setColumn(int i) {
        this.column = i;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void setExtra(Object obj) {
        this.extra = obj;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void setSpanExt(int i, SpanExt spanExt) {
        throw new UnsupportedOperationException();
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void setStyle(long j) {
        this.style = j;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Span
    public void setUnderlineColor(ResolvableColor resolvableColor) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "NoExtSpanImpl{column=" + this.column + ", style=" + this.style + ", extra=" + this.extra + '}';
    }

    public NoExtSpanImpl() {
    }
}
