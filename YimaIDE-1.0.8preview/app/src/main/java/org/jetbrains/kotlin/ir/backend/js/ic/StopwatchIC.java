package org.jetbrains.kotlin.ir.backend.js.ic;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0007J\u0017\u0010\u0011\u001a\u00020\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0010\u001a\u00020\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0017H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/StopwatchIC;", "", "<init>", "()V", "lapStart", "", "lapDescription", "", "laps", "", "Lkotlin/Pair;", "getLaps", "()Ljava/util/List;", "clear", "", "startNext", "description", "stop", "stopTime", "(Ljava/lang/Long;)V", "measure", "T", "f", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class StopwatchIC {
    private String lapDescription;
    private long lapStart;
    private final List<Pair<String, Long>> laps = new ArrayList();

    public static /* synthetic */ void stop$default(StopwatchIC stopwatchIC, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        stopwatchIC.stop(l);
    }

    public final void clear() {
        this.lapStart = 0L;
        this.lapDescription = null;
        this.laps.clear();
    }

    public final List<Pair<String, Long>> getLaps() {
        return this.laps;
    }

    public final <T> T measure(String description, Function0<? extends T> f) {
        description.getClass();
        f.getClass();
        startNext(description);
        T t = (T) f.invoke();
        stop$default(this, null, 1, null);
        return t;
    }

    public final void startNext(String description) {
        description.getClass();
        long jNanoTime = System.nanoTime();
        stop(Long.valueOf(jNanoTime));
        this.lapDescription = description;
        this.lapStart = jNanoTime;
    }

    public final void stop(Long stopTime) {
        String str = this.lapDescription;
        if (str != null) {
            this.laps.add(TuplesKt.to(str, Long.valueOf((stopTime != null ? stopTime.longValue() : System.nanoTime()) - this.lapStart)));
        }
        this.lapDescription = null;
    }
}
