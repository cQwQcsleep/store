package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b\u000fJ\u000e\u0010\u0010\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/SAMInfo;", "C", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", ModuleXmlParser.TYPE, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getSymbol$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "component1", "component1$org_jetbrains_kotlin_resolve", "component2", "copy", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/resolve/SAMInfo;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SAMInfo<C extends ConeKotlinType> {
    private final FirNamedFunctionSymbol symbol;
    private final C type;

    public SAMInfo(FirNamedFunctionSymbol firNamedFunctionSymbol, C c) {
        firNamedFunctionSymbol.getClass();
        c.getClass();
        this.symbol = firNamedFunctionSymbol;
        this.type = c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SAMInfo copy$default(SAMInfo sAMInfo, FirNamedFunctionSymbol firNamedFunctionSymbol, ConeKotlinType coneKotlinType, int i, Object obj) {
        if ((i & 1) != 0) {
            firNamedFunctionSymbol = sAMInfo.symbol;
        }
        if ((i & 2) != 0) {
            coneKotlinType = sAMInfo.type;
        }
        return sAMInfo.copy(firNamedFunctionSymbol, coneKotlinType);
    }

    /* JADX INFO: renamed from: component1$org_jetbrains_kotlin_resolve, reason: from getter */
    public final FirNamedFunctionSymbol getSymbol() {
        return this.symbol;
    }

    public final C component2() {
        return this.type;
    }

    public final SAMInfo<C> copy(FirNamedFunctionSymbol symbol, C type) {
        symbol.getClass();
        type.getClass();
        return new SAMInfo<>(symbol, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SAMInfo)) {
            return false;
        }
        SAMInfo sAMInfo = (SAMInfo) other;
        return Intrinsics.areEqual(this.symbol, sAMInfo.symbol) && Intrinsics.areEqual(this.type, sAMInfo.type);
    }

    public final FirNamedFunctionSymbol getSymbol$org_jetbrains_kotlin_resolve() {
        return this.symbol;
    }

    public final C getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.symbol.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "SAMInfo(symbol=" + this.symbol + ", type=" + this.type + ')';
    }
}
