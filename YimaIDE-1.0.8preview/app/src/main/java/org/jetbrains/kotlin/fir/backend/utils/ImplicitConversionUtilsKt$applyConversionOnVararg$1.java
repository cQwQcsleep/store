package org.jetbrains.kotlin.fir.backend.utils;

import defpackage.f2f;
import java.util.Map;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitConversionUtilsKt$applyConversionOnVararg$1<T> implements UnaryOperator {
    final /* synthetic */ Map<IrVarargElement, FirExpression> $argumentMapping;
    final /* synthetic */ Function2<IrExpression, FirExpression, IrExpression> $conversion;

    /* JADX WARN: Multi-variable type inference failed */
    public ImplicitConversionUtilsKt$applyConversionOnVararg$1(Map<IrVarargElement, ? extends FirExpression> map, Function2<? super IrExpression, ? super FirExpression, ? extends IrExpression> function2) {
        this.$argumentMapping = map;
        this.$conversion = function2;
    }

    @Override // java.util.function.Function
    public final IrVarargElement apply(IrVarargElement irVarargElement) {
        irVarargElement.getClass();
        if (!(irVarargElement instanceof IrExpression)) {
            return irVarargElement;
        }
        FirExpression firExpression = this.$argumentMapping.get(irVarargElement);
        if (firExpression != null) {
            return (IrVarargElement) this.$conversion.invoke(irVarargElement, firExpression);
        }
        f2f.a("Can't find the original FirExpression for ", RenderIrElementKt.render$default(irVarargElement, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }
}
