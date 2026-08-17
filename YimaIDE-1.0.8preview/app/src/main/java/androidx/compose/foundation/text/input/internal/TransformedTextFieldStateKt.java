package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a^\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042<\u0010\u0005\u001a8\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00010\u0006H\u0080\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"getIndexTransformationType", "R", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "transformedQueryIndex", "", "onResult", "Lkotlin/Function3;", "Landroidx/compose/foundation/text/input/internal/IndexTransformationType;", "Landroidx/compose/ui/text/TextRange;", "Lkotlin/ParameterName;", "name", "untransformed", "retransformed", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;ILkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TransformedTextFieldStateKt {
    public static final <R> R getIndexTransformationType(TransformedTextFieldState transformedTextFieldState, int i, Function3<? super IndexTransformationType, ? super TextRange, ? super TextRange, ? extends R> function3) {
        IndexTransformationType indexTransformationType;
        long jM1611mapFromTransformedjx7JFs = transformedTextFieldState.m1611mapFromTransformedjx7JFs(i);
        long jM1614mapToTransformedGEjPoXI = transformedTextFieldState.m1614mapToTransformedGEjPoXI(jM1611mapFromTransformedjx7JFs);
        if (TextRange.getCollapsed-impl(jM1611mapFromTransformedjx7JFs) && TextRange.getCollapsed-impl(jM1614mapToTransformedGEjPoXI)) {
            indexTransformationType = IndexTransformationType.Untransformed;
        } else if (TextRange.getCollapsed-impl(jM1611mapFromTransformedjx7JFs) || TextRange.getCollapsed-impl(jM1614mapToTransformedGEjPoXI)) {
            indexTransformationType = (!TextRange.getCollapsed-impl(jM1611mapFromTransformedjx7JFs) || TextRange.getCollapsed-impl(jM1614mapToTransformedGEjPoXI)) ? IndexTransformationType.Deletion : IndexTransformationType.Insertion;
        } else {
            indexTransformationType = IndexTransformationType.Replacement;
        }
        return (R) function3.invoke(indexTransformationType, TextRange.box-impl(jM1611mapFromTransformedjx7JFs), TextRange.box-impl(jM1614mapToTransformedGEjPoXI));
    }
}
