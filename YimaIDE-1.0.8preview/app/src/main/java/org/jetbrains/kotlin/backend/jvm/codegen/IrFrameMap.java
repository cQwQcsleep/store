package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.konan.InteropFqNames;
import org.jetbrains.kotlin.codegen.FrameMapBase;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/IrFrameMap;", "Lorg/jetbrains/kotlin/codegen/FrameMapBase;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "typeMap", "", "Lorg/jetbrains/org/objectweb/asm/Type;", "enter", "", "key", "type", "leave", InteropFqNames.typeOfFunName, "symbol", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrFrameMap extends FrameMapBase<IrSymbol> {
    private final Map<IrSymbol, Type> typeMap = new LinkedHashMap();

    public int enter(IrSymbol key, Type type) {
        key.getClass();
        type.getClass();
        this.typeMap.put(key, type);
        return super.enter(key, type);
    }

    public int leave(IrSymbol key) {
        key.getClass();
        this.typeMap.remove(key);
        return super.leave(key);
    }

    public final Type typeOf(IrSymbol symbol) {
        symbol.getClass();
        Type type = this.typeMap.get(symbol);
        if (type != null) {
            return type;
        }
        f2f.a("No mapping for symbol: ", RenderIrElementKt.render$default(symbol.getOwner(), (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }
}
