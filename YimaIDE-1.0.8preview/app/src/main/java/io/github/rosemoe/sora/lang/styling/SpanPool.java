package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.lang.styling.Span;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u0000 \u0017*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u0017BK\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\u0016R>\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/SpanPool;", "SpanT", "Lio/github/rosemoe/sora/lang/styling/Span;", "", "capacity", "", "factory", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "column", "", "style", "<init>", "(ILkotlin/jvm/functions/Function2;)V", "cacheQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "offer", "", "span", "(Lio/github/rosemoe/sora/lang/styling/Span;)Z", "obtain", "(IJ)Lio/github/rosemoe/sora/lang/styling/Span;", "Companion", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class SpanPool<SpanT extends Span> {
    public static final int CAPACITY_LARGE = 16384;
    public static final int CAPACITY_SMALL = 8192;
    public static final int DEFAULT_CAPACITY = 16384;
    private final ArrayBlockingQueue<SpanT> cacheQueue;
    private final Function2<Integer, Long, SpanT> factory;

    /* JADX WARN: Multi-variable type inference failed */
    public SpanPool(int i, Function2<? super Integer, ? super Long, ? extends SpanT> function2) {
        function2.getClass();
        this.factory = function2;
        this.cacheQueue = new ArrayBlockingQueue<>(i);
    }

    public SpanT obtain(int column, long style) {
        SpanT spantPoll = this.cacheQueue.poll();
        if (spantPoll == null) {
            return this.factory.invoke(Integer.valueOf(column), Long.valueOf(style));
        }
        spantPoll.setColumn(column);
        spantPoll.setStyle(style);
        return spantPoll;
    }

    public boolean offer(SpanT span) {
        span.getClass();
        return this.cacheQueue.offer(span);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SpanPool(Function2<? super Integer, ? super Long, ? extends SpanT> function2) {
        this(0, function2, 1, null);
        function2.getClass();
    }

    public /* synthetic */ SpanPool(int i, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16384 : i, function2);
    }
}
