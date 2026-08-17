package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\f\u001a\u00020\u0005H§@b\u0002\b\u000e¢\u0006\u0002\u0010\rR\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", Argument.Delimiters.none, "<init>", "()V", "reportDiagnostic", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "needYielding", Argument.Delimiters.none, "getNeedYielding", "()Z", "yield", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CheckerSink {
    public abstract boolean getNeedYielding();

    public abstract void reportDiagnostic(ResolutionDiagnostic diagnostic);

    @PrivateForInline
    public abstract Object yield(Continuation<? super Unit> continuation);
}
