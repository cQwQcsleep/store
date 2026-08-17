package org.jetbrains.kotlin.fir.renderer;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.renderer.FirBodyRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cJ \u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!J\u0010\u0010#\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010%R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00060\u000fR\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirBodyRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "annotationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "getAnnotationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "render", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "renderBody", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "additionalStatements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "renderDelegatedConstructor", "delegatedConstructor", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBodyRenderer {
    public FirRendererComponents components;

    public static Unit a(List list, FirBlock firBlock, FirBodyRenderer firBodyRenderer) {
        Iterator it = CollectionsKt.plus(list, firBlock.getStatements()).iterator();
        while (it.hasNext()) {
            ((FirStatement) it.next()).accept(firBodyRenderer.getVisitor());
            firBodyRenderer.getPrinter().println(new Object[0]);
        }
        return Unit.INSTANCE;
    }

    public static Unit b(FirDelegatedConstructorCall firDelegatedConstructorCall, FirBodyRenderer firBodyRenderer) {
        firDelegatedConstructorCall.accept(firBodyRenderer.getVisitor());
        firBodyRenderer.getPrinter().println(new Object[0]);
        return Unit.INSTANCE;
    }

    private final FirAnnotationRenderer getAnnotationRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getAnnotationRenderer();
    }

    private final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    private final FirRenderer.Visitor getVisitor() {
        return getComponents$org_jetbrains_kotlin_tree().getVisitor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void renderBody$default(FirBodyRenderer firBodyRenderer, FirBlock firBlock, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        firBodyRenderer.renderBody(firBlock, list);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRendererComponents getComponents$org_jetbrains_kotlin_tree() throws UninitializedPropertyAccessException {
        FirRendererComponents firRendererComponents = this.components;
        if (firRendererComponents != null) {
            return firRendererComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    public final void render(FirVariable variable) {
        variable.getClass();
        FirExpression initializer = variable.getInitializer();
        if (initializer != null) {
            getPrinter().print(" = ");
            initializer.accept(getVisitor());
        }
        FirExpression delegate = variable.getDelegate();
        if (delegate != null) {
            getPrinter().print("by ");
            delegate.accept(getVisitor());
        }
    }

    public final void renderBody(final FirBlock block, final List<? extends FirStatement> additionalStatements) {
        additionalStatements.getClass();
        if (block == null) {
            return;
        }
        if (block instanceof FirLazyBlock) {
            getPrinter().println(" { LAZY_BLOCK }");
            return;
        }
        FirAnnotationRenderer annotationRenderer = getAnnotationRenderer();
        if (annotationRenderer != null) {
            FirAnnotationRenderer.render$default(annotationRenderer, block, null, 2, null);
        }
        FirPrinter.renderInBraces$default(getPrinter(), null, null, new Function0() { // from class: ny4
            public final Object invoke() {
                return FirBodyRenderer.a(additionalStatements, block, this);
            }
        }, 3, null);
    }

    public final void renderDelegatedConstructor(final FirDelegatedConstructorCall delegatedConstructor) {
        if (delegatedConstructor != null) {
            FirPrinter.renderInBraces$default(getPrinter(), null, null, new Function0() { // from class: my4
                public final Object invoke() {
                    return FirBodyRenderer.b(delegatedConstructor, this);
                }
            }, 3, null);
        } else {
            getPrinter().println(new Object[0]);
        }
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }

    public final void render(FirFunction function) {
        function.getClass();
        renderBody$default(this, function.getBody(), null, 2, null);
    }
}
