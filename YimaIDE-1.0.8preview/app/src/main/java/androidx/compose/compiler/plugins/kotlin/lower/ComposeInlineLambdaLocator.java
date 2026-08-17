package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposeInlineLambdaLocator;", "", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;)V", "inlineLambdaToParameter", "", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "inlineFunctionExpressions", "", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "isInlineLambda", "", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "isCrossinlineLambda", "isInlineFunctionExpression", "expression", "preservesComposableScope", "scan", "", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeInlineLambdaLocator {
    private final IrPluginContext context;
    private final Set<IrExpression> inlineFunctionExpressions;
    private final Map<IrFunctionSymbol, IrValueParameter> inlineLambdaToParameter;

    public ComposeInlineLambdaLocator(IrPluginContext irPluginContext) {
        irPluginContext.getClass();
        this.context = irPluginContext;
        this.inlineLambdaToParameter = new LinkedHashMap();
        this.inlineFunctionExpressions = new LinkedHashSet();
    }

    public final boolean isCrossinlineLambda(IrFunction irFunction) {
        irFunction.getClass();
        IrValueParameter irValueParameter = this.inlineLambdaToParameter.get(irFunction.getSymbol());
        return irValueParameter != null && irValueParameter.isCrossinline();
    }

    public final boolean isInlineFunctionExpression(IrExpression expression) {
        expression.getClass();
        return this.inlineFunctionExpressions.contains(expression);
    }

    public final boolean isInlineLambda(IrFunction irFunction) {
        irFunction.getClass();
        return this.inlineLambdaToParameter.keySet().contains(irFunction.getSymbol());
    }

    public final boolean preservesComposableScope(IrFunction irFunction) {
        irFunction.getClass();
        IrValueParameter irValueParameter = this.inlineLambdaToParameter.get(irFunction.getSymbol());
        return (irValueParameter == null || irValueParameter.isCrossinline() || IrUtilsKt.hasAnnotation(irValueParameter.getType(), ComposeFqNames.INSTANCE.getDisallowComposableCalls())) ? false : true;
    }

    public final void scan(IrElement element) {
        element.getClass();
        IrVisitorsKt.acceptVoid(element, new IrVisitorVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposeInlineLambdaLocator.scan.1
            public void visitElement(IrElement element2) {
                element2.getClass();
                IrVisitorsKt.acceptChildrenVoid(element2, this);
            }

            public void visitFunctionAccess(IrFunctionAccessExpression expression) {
                IrExpression irExpression;
                expression.getClass();
                IrVisitorsKt.acceptChildrenVoid(expression, this);
                IrFunction owner = expression.getSymbol().getOwner();
                if (IrInlineReferenceLocatorKt.access$isInlineFunctionCall(owner, ComposeInlineLambdaLocator.this.context)) {
                    for (IrValueParameter irValueParameter : owner.getParameters()) {
                        if (IrInlineReferenceLocatorKt.access$isInlinedFunction(irValueParameter) && (irExpression = (IrExpression) expression.getArguments().get(irValueParameter.getIndexInParameters())) != null) {
                            ComposeInlineLambdaLocator.this.inlineFunctionExpressions.add(irExpression);
                            IrFunctionSymbol irFunctionSymbolUnwrapLambda = IrInlineReferenceLocatorKt.unwrapLambda(irExpression);
                            if (irFunctionSymbolUnwrapLambda != null) {
                                ComposeInlineLambdaLocator.this.inlineLambdaToParameter.put(irFunctionSymbolUnwrapLambda, irValueParameter);
                            }
                        }
                    }
                }
            }

            public void visitValueParameter(IrValueParameter declaration) {
                IrExpressionBody defaultValue;
                IrExpression expression;
                IrFunctionSymbol irFunctionSymbolUnwrapLambda;
                declaration.getClass();
                IrVisitorsKt.acceptChildrenVoid(declaration, this);
                IrFunction parent = declaration.getParent();
                IrFunction irFunction = parent instanceof IrFunction ? parent : null;
                if (irFunction == null || !IrInlineReferenceLocatorKt.access$isInlineFunctionCall(irFunction, ComposeInlineLambdaLocator.this.context) || !IrInlineReferenceLocatorKt.access$isInlinedFunction(declaration) || (defaultValue = declaration.getDefaultValue()) == null || (expression = defaultValue.getExpression()) == null || (irFunctionSymbolUnwrapLambda = IrInlineReferenceLocatorKt.unwrapLambda(expression)) == null) {
                    return;
                }
                ComposeInlineLambdaLocator.this.inlineLambdaToParameter.put(irFunctionSymbolUnwrapLambda, declaration);
            }
        });
    }
}
