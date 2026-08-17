package androidx.compose.compiler.plugins.kotlin.analysis;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@VisibleForTesting
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010Ê\u0001\u0002\b\u001d¨\u0006\u001c"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/SymbolForAnalysis;", "", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "typeParameters", "", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "analysisEntryFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "<init>", "(Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/ir/declarations/IrFile;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "getTypeParameters", "()Ljava/util/List;", "getAnalysisEntryFile", "()Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin", "Lcom/google/common/annotations/VisibleForTesting;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class SymbolForAnalysis {
    private final IrFile analysisEntryFile;
    private final IrClassifierSymbol symbol;
    private final List<IrTypeArgument> typeParameters;

    public SymbolForAnalysis(IrClassifierSymbol irClassifierSymbol, List<? extends IrTypeArgument> list, IrFile irFile) {
        irClassifierSymbol.getClass();
        list.getClass();
        this.symbol = irClassifierSymbol;
        this.typeParameters = list;
        this.analysisEntryFile = irFile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SymbolForAnalysis copy$default(SymbolForAnalysis symbolForAnalysis, IrClassifierSymbol irClassifierSymbol, List list, IrFile irFile, int i, Object obj) {
        if ((i & 1) != 0) {
            irClassifierSymbol = symbolForAnalysis.symbol;
        }
        if ((i & 2) != 0) {
            list = symbolForAnalysis.typeParameters;
        }
        if ((i & 4) != 0) {
            irFile = symbolForAnalysis.analysisEntryFile;
        }
        return symbolForAnalysis.copy(irClassifierSymbol, list, irFile);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrClassifierSymbol getSymbol() {
        return this.symbol;
    }

    public final List<IrTypeArgument> component2() {
        return this.typeParameters;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final IrFile getAnalysisEntryFile() {
        return this.analysisEntryFile;
    }

    public final SymbolForAnalysis copy(IrClassifierSymbol symbol, List<? extends IrTypeArgument> typeParameters, IrFile analysisEntryFile) {
        symbol.getClass();
        typeParameters.getClass();
        return new SymbolForAnalysis(symbol, typeParameters, analysisEntryFile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SymbolForAnalysis)) {
            return false;
        }
        SymbolForAnalysis symbolForAnalysis = (SymbolForAnalysis) other;
        return Intrinsics.areEqual(this.symbol, symbolForAnalysis.symbol) && Intrinsics.areEqual(this.typeParameters, symbolForAnalysis.typeParameters) && Intrinsics.areEqual(this.analysisEntryFile, symbolForAnalysis.analysisEntryFile);
    }

    public final IrFile getAnalysisEntryFile() {
        return this.analysisEntryFile;
    }

    public final IrClassifierSymbol getSymbol() {
        return this.symbol;
    }

    public final List<IrTypeArgument> getTypeParameters() {
        return this.typeParameters;
    }

    public int hashCode() {
        int iHashCode = ((this.symbol.hashCode() * 31) + this.typeParameters.hashCode()) * 31;
        IrFile irFile = this.analysisEntryFile;
        return iHashCode + (irFile == null ? 0 : irFile.hashCode());
    }

    public String toString() {
        return "SymbolForAnalysis(symbol=" + this.symbol + ", typeParameters=" + this.typeParameters + ", analysisEntryFile=" + this.analysisEntryFile + ')';
    }
}
