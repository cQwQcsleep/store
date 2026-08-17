package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.backend.common.lower.SymbolWithIrBuilder;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\n\b\u0001\u0010\u0003 \u0001*\u00020\u00042\u00020\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00028\u0000H$¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0014J\r\u0010\f\u001a\u00028\u0001H$¢\u0006\u0002\u0010\rJ\u0006\u0010\u0017\u001a\u00020\u000bR\u001b\u0010\u000e\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000f\u0010\tR\u001b\u0010\u0012\u001a\u00028\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0013\u0010\rR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00028\u00018F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\r¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/SymbolWithIrBuilder;", "S", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "D", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "buildSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "doInitialize", "", "buildIr", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "symbol", "getSymbol", "symbol$delegate", "Lkotlin/Lazy;", "builtIr", "getBuiltIr", "builtIr$delegate", "initialized", "", "initialize", "ir", "getIr", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class SymbolWithIrBuilder<S extends IrSymbol, D extends IrDeclaration> {
    private boolean initialized;

    /* JADX INFO: renamed from: symbol$delegate, reason: from kotlin metadata */
    private final Lazy symbol = LazyKt.lazy(new Function0() { // from class: kyd
        public final Object invoke() {
            return SymbolWithIrBuilder.b(this.b);
        }
    });

    /* JADX INFO: renamed from: builtIr$delegate, reason: from kotlin metadata */
    private final Lazy builtIr = LazyKt.lazy(new Function0() { // from class: lyd
        public final Object invoke() {
            return SymbolWithIrBuilder.a(this.b);
        }
    });

    public static IrDeclaration a(SymbolWithIrBuilder symbolWithIrBuilder) {
        return symbolWithIrBuilder.buildIr();
    }

    public static IrSymbol b(SymbolWithIrBuilder symbolWithIrBuilder) {
        return symbolWithIrBuilder.buildSymbol();
    }

    private final D getBuiltIr() {
        return (D) this.builtIr.getValue();
    }

    public abstract D buildIr();

    public abstract S buildSymbol();

    public void doInitialize() {
    }

    public final D getIr() {
        if (this.initialized) {
            return (D) getBuiltIr();
        }
        throw new Error("Access to IR before initialization");
    }

    public final S getSymbol() {
        return (S) this.symbol.getValue();
    }

    public final void initialize() {
        doInitialize();
        this.initialized = true;
    }
}
