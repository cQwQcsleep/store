package kotlin.concurrent;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a@\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a@\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a \u0010\u000e\u001a\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0081\u0080\u0004b\u0002\b\u0013\u001aT\u0010\u000e\u001a\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001aR\u0010\u000e\u001a\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001aT\u0010\u0016\u001a\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001aR\u0010\u0016\u001a\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u001a,\u0010\u0017\u001a\u00020\u00012\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004b\u0002\b\tø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"schedule", "Ljava/util/TimerTask;", "Ljava/util/Timer;", "delay", "", "action", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/internal/InlineOnly;", "time", "Ljava/util/Date;", "period", "scheduleAtFixedRate", "timer", "name", "", "daemon", "", "Lkotlin/PublishedApi;", "initialDelay", "startAt", "fixedRateTimer", "timerTask", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class TimersKt {

    /* JADX INFO: renamed from: kotlin.concurrent.TimersKt$timerTask$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = 176)
    public static final class AnonymousClass1 extends TimerTask {
        final /* synthetic */ Function1<TimerTask, Unit> $action;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super TimerTask, Unit> function1) {
            this.$action = function1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.$action.invoke(this);
        }
    }

    private static final Timer fixedRateTimer(String str, boolean z, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        date.getClass();
        function1.getClass();
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new AnonymousClass1(function1), date, j);
        return timer;
    }

    public static /* synthetic */ Timer fixedRateTimer$default(String str, boolean z, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        function1.getClass();
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new AnonymousClass1(function1), j, j2);
        return timer;
    }

    private static final TimerTask schedule(Timer timer, Date date, Function1<? super TimerTask, Unit> function1) {
        timer.getClass();
        date.getClass();
        function1.getClass();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(function1);
        timer.schedule(anonymousClass1, date);
        return anonymousClass1;
    }

    private static final TimerTask scheduleAtFixedRate(Timer timer, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        timer.getClass();
        function1.getClass();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(function1);
        timer.scheduleAtFixedRate(anonymousClass1, j, j2);
        return anonymousClass1;
    }

    private static final Timer timer(String str, boolean z, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        date.getClass();
        function1.getClass();
        Timer timer = timer(str, z);
        timer.schedule(new AnonymousClass1(function1), date, j);
        return timer;
    }

    public static /* synthetic */ Timer timer$default(String str, boolean z, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        function1.getClass();
        Timer timer = timer(str, z);
        timer.schedule(new AnonymousClass1(function1), j, j2);
        return timer;
    }

    private static final TimerTask timerTask(Function1<? super TimerTask, Unit> function1) {
        function1.getClass();
        return new AnonymousClass1(function1);
    }

    private static final TimerTask schedule(Timer timer, long j, Function1<? super TimerTask, Unit> function1) {
        timer.getClass();
        function1.getClass();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(function1);
        timer.schedule(anonymousClass1, j);
        return anonymousClass1;
    }

    private static final TimerTask scheduleAtFixedRate(Timer timer, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        timer.getClass();
        date.getClass();
        function1.getClass();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(function1);
        timer.scheduleAtFixedRate(anonymousClass1, date, j);
        return anonymousClass1;
    }

    private static final Timer fixedRateTimer(String str, boolean z, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        function1.getClass();
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new AnonymousClass1(function1), j, j2);
        return timer;
    }

    private static final Timer timer(String str, boolean z, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        function1.getClass();
        Timer timer = timer(str, z);
        timer.schedule(new AnonymousClass1(function1), j, j2);
        return timer;
    }

    private static final TimerTask schedule(Timer timer, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        timer.getClass();
        function1.getClass();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(function1);
        timer.schedule(anonymousClass1, j, j2);
        return anonymousClass1;
    }

    public static final Timer timer(String str, boolean z) {
        return str == null ? new Timer(z) : new Timer(str, z);
    }

    private static final TimerTask schedule(Timer timer, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        timer.getClass();
        date.getClass();
        function1.getClass();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(function1);
        timer.schedule(anonymousClass1, date, j);
        return anonymousClass1;
    }

    public static /* synthetic */ Timer fixedRateTimer$default(String str, boolean z, Date date, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        date.getClass();
        function1.getClass();
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new AnonymousClass1(function1), date, j);
        return timer;
    }

    public static /* synthetic */ Timer timer$default(String str, boolean z, Date date, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        date.getClass();
        function1.getClass();
        Timer timer = timer(str, z);
        timer.schedule(new AnonymousClass1(function1), date, j);
        return timer;
    }
}
