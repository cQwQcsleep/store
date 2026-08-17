package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNamesKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\f\u0010\u0011\u001a\u00020\u0007*\u00020\u0012H\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableLambdaAnnotator;", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitorVoid;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;)V", "visitElement", "", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "visitFunctionExpression", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionExpression;", "visitFunctionReference", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionReference;", "composableSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "mark", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableLambdaAnnotator extends IrVisitorVoid {
    private final IrClassSymbol composableSymbol;

    public ComposableLambdaAnnotator(IrPluginContext irPluginContext) {
        irPluginContext.getClass();
        IrClassSymbol irClassSymbolFindClass = irPluginContext.finderForBuiltins().findClass(ComposeClassIds.INSTANCE.getComposable());
        irClassSymbolFindClass.getClass();
        this.composableSymbol = irClassSymbolFindClass;
    }

    private final void mark(IrFunction irFunction) {
        if (ComposeFqNamesKt.hasComposableAnnotation(irFunction)) {
            return;
        }
        irFunction.setAnnotations(CollectionsKt.plus(irFunction.getAnnotations(), BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, IrUtilsKt.getDefaultType(this.composableSymbol.getOwner()), (IrConstructorSymbol) SequencesKt.single(IrUtilsKt.getConstructors(this.composableSymbol)), (IrStatementOrigin) null, 4, (Object) null)));
    }

    public void visitElement(IrElement element) {
        element.getClass();
        IrVisitorsKt.acceptChildrenVoid(element, this);
    }

    public void visitFunctionExpression(IrFunctionExpression expression) {
        expression.getClass();
        if (AbstractComposeLoweringKt.isSyntheticComposableFunction(expression.getType())) {
            mark(expression.getFunction());
        }
        super.visitFunctionExpression(expression);
    }

    public void visitFunctionReference(IrFunctionReference expression) {
        expression.getClass();
        if (AbstractComposeLoweringKt.isSyntheticComposableFunction(expression.getType()) && Intrinsics.areEqual(expression.getSymbol().getOwner().getVisibility(), DescriptorVisibilities.LOCAL)) {
            mark(expression.getSymbol().getOwner());
        }
        super.visitFunctionReference(expression);
    }
}
