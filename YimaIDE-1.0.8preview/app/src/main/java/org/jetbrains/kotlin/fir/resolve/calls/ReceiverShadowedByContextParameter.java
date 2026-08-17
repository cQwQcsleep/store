package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverShadowedByContextParameter;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "calleeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isDispatchOfMemberExtension", Argument.Delimiters.none, "compatibleContextParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;ZLjava/util/List;)V", "getCalleeSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "()Z", "getCompatibleContextParameters", "()Ljava/util/List;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReceiverShadowedByContextParameter extends ResolutionDiagnostic {
    private final FirBasedSymbol<?> calleeSymbol;
    private final List<FirValueParameterSymbol> compatibleContextParameters;
    private final boolean isDispatchOfMemberExtension;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReceiverShadowedByContextParameter(FirBasedSymbol<?> firBasedSymbol, boolean z, List<FirValueParameterSymbol> list) {
        super(CandidateApplicability.RESOLVED_WITH_ERROR);
        firBasedSymbol.getClass();
        list.getClass();
        this.calleeSymbol = firBasedSymbol;
        this.isDispatchOfMemberExtension = z;
        this.compatibleContextParameters = list;
    }

    public final FirBasedSymbol<?> getCalleeSymbol() {
        return this.calleeSymbol;
    }

    public final List<FirValueParameterSymbol> getCompatibleContextParameters() {
        return this.compatibleContextParameters;
    }

    /* JADX INFO: renamed from: isDispatchOfMemberExtension, reason: from getter */
    public final boolean getIsDispatchOfMemberExtension() {
        return this.isDispatchOfMemberExtension;
    }
}
