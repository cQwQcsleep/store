package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086H¢\u0006\u0002\u0010\u0003\u001a\u001a\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086H¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"yieldIfNeed", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldDiagnostic", "diagnostic", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckerSinkKt {
    public static final Object yieldDiagnostic(CheckerSink checkerSink, ResolutionDiagnostic resolutionDiagnostic, Continuation<? super Unit> continuation) {
        checkerSink.reportDiagnostic(resolutionDiagnostic);
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }

    public static final Object yieldIfNeed(CheckerSink checkerSink, Continuation<? super Unit> continuation) {
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }
}
