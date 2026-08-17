package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.mpp.ValueParameterSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0013\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0014\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0015\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/mpp/ValueParameterSymbolMarker;", "<init>", "()V", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "hasDefaultValue", Argument.Delimiters.none, "getHasDefaultValue", "()Z", "defaultValueSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "getDefaultValueSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "isCrossinline", "isNoinline", "isVararg", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirValueParameterSymbol extends FirVariableSymbol<FirValueParameter> implements ValueParameterSymbolMarker {
    public FirValueParameterSymbol() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public CallableId getCallableId() {
        return new CallableId(getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return ((FirValueParameter) getFir()).getContainingDeclarationSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final KtSourceElement getDefaultValueSource() {
        FirExpression defaultValue = ((FirValueParameter) getFir()).getDefaultValue();
        if (defaultValue != null) {
            return defaultValue.getSource();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasDefaultValue() {
        return ((FirValueParameter) getFir()).getDefaultValue() != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isCrossinline() {
        return ((FirValueParameter) getFir()).isCrossinline();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isNoinline() {
        return ((FirValueParameter) getFir()).isNoinline();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isVararg() {
        return ((FirValueParameter) getFir()).isVararg();
    }
}
