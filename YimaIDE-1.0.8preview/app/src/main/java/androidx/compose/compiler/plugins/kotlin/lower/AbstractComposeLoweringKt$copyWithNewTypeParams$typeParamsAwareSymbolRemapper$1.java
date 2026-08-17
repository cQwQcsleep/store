package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper;
import org.jetbrains.kotlin.ir.util.DescriptorsRemapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"androidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1", "Lorg/jetbrains/kotlin/ir/util/DeepCopySymbolRemapper;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 176)
public final class AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1 extends DeepCopySymbolRemapper {
    public AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1(IrFunction irFunction, IrFunction irFunction2) {
        super((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        for (Pair pair : CollectionsKt.zip(irFunction.getTypeParameters(), irFunction2.getTypeParameters())) {
            getTypeParameters().put(((IrTypeParameter) pair.component1()).getSymbol(), ((IrTypeParameter) pair.component2()).getSymbol());
        }
    }
}
