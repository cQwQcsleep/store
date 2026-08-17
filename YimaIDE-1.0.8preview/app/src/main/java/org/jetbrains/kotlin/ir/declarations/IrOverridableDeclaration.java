package org.jetbrains.kotlin.ir.declarations;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u00020\bX¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u0082\u0001\u0002\u0012\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "S", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableMember;", "symbol", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "isFakeOverride", "", "()Z", "setFakeOverride", "(Z)V", "overriddenSymbols", "", "getOverriddenSymbols", "()Ljava/util/List;", "setOverriddenSymbols", "(Ljava/util/List;)V", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrOverridableDeclaration<S extends IrSymbol> extends IrOverridableMember {
    List<S> getOverriddenSymbols();

    S getSymbol();

    boolean isFakeOverride();

    void setFakeOverride(boolean z);

    void setOverriddenSymbols(List<? extends S> list);
}
