package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007R\u0011\u0010\t\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionWithoutNameSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "<init>", "()V", "isGetter", Argument.Delimiters.none, "()Z", "isSetter", "isDefault", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getPropertySymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirPropertyAccessorSymbol extends FirFunctionWithoutNameSymbol<FirPropertyAccessor> {
    /* JADX WARN: Illegal instructions before constructor call */
    public FirPropertyAccessorSymbol() {
        Name nameIdentifier = Name.identifier("accessor");
        nameIdentifier.getClass();
        super(nameIdentifier, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirPropertySymbol getPropertySymbol() {
        return ((FirPropertyAccessor) getFir()).getPropertySymbol();
    }

    public final boolean isDefault() {
        return getFir() instanceof FirDefaultPropertyAccessor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isGetter() {
        return ((FirPropertyAccessor) getFir()).getIsGetter();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isSetter() {
        return ((FirPropertyAccessor) getFir()).isSetter();
    }
}
