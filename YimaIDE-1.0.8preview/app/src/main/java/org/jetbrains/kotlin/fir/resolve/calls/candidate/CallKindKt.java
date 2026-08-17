package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"buildCallKindWithCustomResolutionSequence", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/ResolutionSequenceBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallKindKt {
    public static final CallKind buildCallKindWithCustomResolutionSequence(Function1<? super ResolutionSequenceBuilder, Unit> function1) {
        function1.getClass();
        ResolutionSequenceBuilder resolutionSequenceBuilder = new ResolutionSequenceBuilder(false, false, false, false, false, false, false, false, false, false, 1023, null);
        function1.invoke(resolutionSequenceBuilder);
        return resolutionSequenceBuilder.build();
    }
}
