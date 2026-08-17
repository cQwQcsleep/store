package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/DfaType;", Argument.Delimiters.none, "<init>", "()V", "Cone", "Symbol", "BooleanLiteral", "Lorg/jetbrains/kotlin/fir/DfaType$BooleanLiteral;", "Lorg/jetbrains/kotlin/fir/DfaType$Cone;", "Lorg/jetbrains/kotlin/fir/DfaType$Symbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DfaType {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/DfaType$BooleanLiteral;", "Lorg/jetbrains/kotlin/fir/DfaType;", "value", Argument.Delimiters.none, "<init>", "(Z)V", "getValue", "()Z", "toString", Argument.Delimiters.none, "component1", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class BooleanLiteral extends DfaType {
        private final boolean value;

        public BooleanLiteral(boolean z) {
            super(null);
            this.value = z;
        }

        public static /* synthetic */ BooleanLiteral copy$default(BooleanLiteral booleanLiteral, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = booleanLiteral.value;
            }
            return booleanLiteral.copy(z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getValue() {
            return this.value;
        }

        public final BooleanLiteral copy(boolean value) {
            return new BooleanLiteral(value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BooleanLiteral) && this.value == ((BooleanLiteral) other).value;
        }

        public final boolean getValue() {
            return this.value;
        }

        public int hashCode() {
            return Boolean.hashCode(this.value);
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/DfaType$Cone;", "Lorg/jetbrains/kotlin/fir/DfaType;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toString", Argument.Delimiters.none, "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Cone extends DfaType {
        private final ConeKotlinType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Cone(ConeKotlinType coneKotlinType) {
            super(null);
            coneKotlinType.getClass();
            this.type = coneKotlinType;
        }

        public static /* synthetic */ Cone copy$default(Cone cone, ConeKotlinType coneKotlinType, int i, Object obj) {
            if ((i & 1) != 0) {
                coneKotlinType = cone.type;
            }
            return cone.copy(coneKotlinType);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ConeKotlinType getType() {
            return this.type;
        }

        public final Cone copy(ConeKotlinType type) {
            type.getClass();
            return new Cone(type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Cone) && Intrinsics.areEqual(this.type, ((Cone) other).type);
        }

        public final ConeKotlinType getType() {
            return this.type;
        }

        public int hashCode() {
            return this.type.hashCode();
        }

        public String toString() {
            return String.valueOf(this.type);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\r\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0017\u0010\u000b\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/DfaType$Symbol;", "Lorg/jetbrains/kotlin/fir/DfaType;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "toString", Argument.Delimiters.none, "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Symbol extends DfaType {
        private final FirBasedSymbol<?> symbol;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Symbol(FirBasedSymbol<?> firBasedSymbol) {
            super(null);
            firBasedSymbol.getClass();
            this.symbol = firBasedSymbol;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Symbol copy$default(Symbol symbol, FirBasedSymbol firBasedSymbol, int i, Object obj) {
            if ((i & 1) != 0) {
                firBasedSymbol = symbol.symbol;
            }
            return symbol.copy(firBasedSymbol);
        }

        public final FirBasedSymbol<?> component1() {
            return this.symbol;
        }

        public final Symbol copy(FirBasedSymbol<?> symbol) {
            symbol.getClass();
            return new Symbol(symbol);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Symbol) && Intrinsics.areEqual(this.symbol, ((Symbol) other).symbol);
        }

        public final FirBasedSymbol<?> getSymbol() {
            return this.symbol;
        }

        public int hashCode() {
            return this.symbol.hashCode();
        }

        public String toString() {
            return String.valueOf(this.symbol);
        }
    }

    public /* synthetic */ DfaType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private DfaType() {
    }
}
