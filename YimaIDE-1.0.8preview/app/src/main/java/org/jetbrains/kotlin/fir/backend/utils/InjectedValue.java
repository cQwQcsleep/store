package org.jetbrains.kotlin.fir.backend.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/utils/InjectedValue;", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isMutated", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Z)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "()Z", "irParameterSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrValueParameterSymbol;", "getIrParameterSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrValueParameterSymbol;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InjectedValue {
    private final IrValueParameterSymbol irParameterSymbol;
    private final boolean isMutated;
    private final FirBasedSymbol<?> symbol;
    private final FirTypeRef typeRef;

    public InjectedValue(FirBasedSymbol<?> firBasedSymbol, FirTypeRef firTypeRef, boolean z) {
        firBasedSymbol.getClass();
        firTypeRef.getClass();
        this.symbol = firBasedSymbol;
        this.typeRef = firTypeRef;
        this.isMutated = z;
        this.irParameterSymbol = new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
    }

    public final IrValueParameterSymbol getIrParameterSymbol() {
        return this.irParameterSymbol;
    }

    public final FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }

    public final FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    /* JADX INFO: renamed from: isMutated, reason: from getter */
    public final boolean getIsMutated() {
        return this.isMutated;
    }
}
