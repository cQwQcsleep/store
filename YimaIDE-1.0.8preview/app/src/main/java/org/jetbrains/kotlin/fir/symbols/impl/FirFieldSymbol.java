package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;)V", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "hasInitializer", Argument.Delimiters.none, "getHasInitializer", "()Z", "hasConstantInitializer", "getHasConstantInitializer", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirFieldSymbol extends FirVariableSymbol<FirField> {
    private final CallableId callableId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirFieldSymbol(CallableId callableId) {
        super(null);
        callableId.getClass();
        this.callableId = callableId;
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public CallableId getCallableId() {
        return this.callableId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasConstantInitializer() {
        return ((FirField) getFir()).getHasConstantInitializer();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasInitializer() {
        return ((FirField) getFir()).getInitializer() != null;
    }
}
