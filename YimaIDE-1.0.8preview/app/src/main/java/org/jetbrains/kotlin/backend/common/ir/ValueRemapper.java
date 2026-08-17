package org.jetbrains.kotlin.backend.common.ir;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0014R\"\u0010\u0002\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/ValueRemapper;", "Lorg/jetbrains/kotlin/backend/common/ir/AbstractValueRemapper;", "map", "", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Map;)V", "getMap", "()Ljava/util/Map;", "remapValue", "oldValue", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class ValueRemapper extends AbstractValueRemapper {
    private final Map<? extends IrValueSymbol, IrValueSymbol> map;

    public ValueRemapper(Map<? extends IrValueSymbol, ? extends IrValueSymbol> map) {
        map.getClass();
        this.map = map;
    }

    public Map<? extends IrValueSymbol, IrValueSymbol> getMap() {
        return this.map;
    }

    @Override // org.jetbrains.kotlin.backend.common.ir.AbstractValueRemapper
    public IrValueSymbol remapValue(IrValueSymbol oldValue) {
        oldValue.getClass();
        return getMap().get(oldValue);
    }
}
