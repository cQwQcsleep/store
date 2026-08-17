package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.Item;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeBuilder;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0014\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionParameter;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "parameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;)V", "getParameter", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "name", "", "getName", "()Ljava/lang/String;", "hashCode", "", "equals", "", "other", "", "schemeIsUpdatable", "getSchemeIsUpdatable", "()Z", "toDeclaredScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "defaultTarget", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "updateScheme", "", "scheme", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceFunctionParameter extends InferenceFunction {
    private final IrValueParameter parameter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceFunctionParameter(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrValueParameter irValueParameter) {
        super(composableTargetAnnotationsTransformer, null);
        composableTargetAnnotationsTransformer.getClass();
        irValueParameter.getClass();
        this.parameter = irValueParameter;
    }

    public boolean equals(Object other) {
        return (other instanceof InferenceFunctionParameter) && Intrinsics.areEqual(((InferenceFunctionParameter) other).parameter, this.parameter);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public String getName() {
        return "<parameter>";
    }

    public final IrValueParameter getParameter() {
        return this.parameter;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public boolean getSchemeIsUpdatable() {
        return false;
    }

    public int hashCode() {
        return this.parameter.hashCode() * 31;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public Scheme toDeclaredScheme(Item defaultTarget) {
        List listEmptyList;
        defaultTarget.getClass();
        ComposableTargetAnnotationsTransformer transformer = getTransformer();
        IrSimpleFunction irSimpleFunctionSamOwnerOrNull = ComposableTargetAnnotationsTransformerKt.samOwnerOrNull(this.parameter.getType());
        if (irSimpleFunctionSamOwnerOrNull == null || (listEmptyList = irSimpleFunctionSamOwnerOrNull.getAnnotations()) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        Item target = transformer.getTarget(CollectionsKt.plus(this.parameter.getType().getAnnotations(), listEmptyList));
        if (!target.getIsUnspecified()) {
            defaultTarget = target;
        }
        return transformer.toScheme(this.parameter.getType(), defaultTarget);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public void updateScheme(Scheme scheme) {
        scheme.getClass();
        IrSimpleType type = this.parameter.getType();
        if (type instanceof IrSimpleType) {
            IrSimpleTypeBuilder builder = IrSimpleTypeImplKt.toBuilder(type);
            getTransformer().addAnnotationToType(builder, scheme.getTarget());
            this.parameter.setType(IrSimpleTypeImplKt.buildSimpleType(builder));
        }
    }
}
