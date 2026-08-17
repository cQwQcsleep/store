package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DispatchReceiverMemberScopeTowerLevel$processCandidates$1 implements Function1 {
    final /* synthetic */ Function1 $candidateProcessor;
    final /* synthetic */ Ref.ObjectRef<ProcessResult> $result;
    final /* synthetic */ DispatchReceiverMemberScopeTowerLevel this$0;

    public DispatchReceiverMemberScopeTowerLevel$processCandidates$1(Ref.ObjectRef objectRef, DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel, Function1 function1) {
        this.$result = objectRef;
        this.this$0 = dispatchReceiverMemberScopeTowerLevel;
        this.$candidateProcessor = function1;
    }

    public final void invoke(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        this.$result.element = ProcessResult.FOUND;
        DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel = this.this$0;
        if (dispatchReceiverMemberScopeTowerLevel.hasConsistentExtensionReceiver(firCallableSymbol, dispatchReceiverMemberScopeTowerLevel.givenExtensionReceiver)) {
            this.$candidateProcessor.invoke(firCallableSymbol);
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirCallableSymbol) obj);
        return Unit.INSTANCE;
    }
}
