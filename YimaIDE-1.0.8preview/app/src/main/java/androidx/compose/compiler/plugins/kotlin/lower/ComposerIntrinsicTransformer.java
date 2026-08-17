package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.FileLoweringPass;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposerIntrinsicTransformer;", "Lorg/jetbrains/kotlin/ir/visitors/IrElementTransformerVoid;", "Lorg/jetbrains/kotlin/backend/common/FileLoweringPass;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;)V", "getContext", "()Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "currentComposerIntrinsic", "Lorg/jetbrains/kotlin/name/FqName;", "currentComposerFqName", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "visitCall", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposerIntrinsicTransformer extends IrElementTransformerVoid implements FileLoweringPass, ModuleLoweringPass {
    private final IrPluginContext context;
    private final FqName currentComposerIntrinsic;

    public ComposerIntrinsicTransformer(IrPluginContext irPluginContext) {
        irPluginContext.getClass();
        this.context = irPluginContext;
        this.currentComposerIntrinsic = currentComposerFqName();
    }

    private final FqName currentComposerFqName() {
        return ComposeFqNames.INSTANCE.getCurrentComposerIntrinsic();
    }

    public final IrPluginContext getContext() {
        return this.context;
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
    }

    public IrExpression visitCall(IrCall expression) {
        expression.getClass();
        if (!Intrinsics.areEqual(AdditionalIrUtilsKt.getKotlinFqName(expression.getSymbol().getOwner()), this.currentComposerIntrinsic)) {
            return super.visitCall(expression);
        }
        expression.getArguments().size();
        IrExpression irExpression = (IrExpression) expression.getArguments().get(0);
        if (irExpression != null) {
            return irExpression;
        }
        k2d.a("Expected non-null composer argument");
        return null;
    }

    public void lower(IrFile irFile) {
        irFile.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irFile, this);
    }
}
