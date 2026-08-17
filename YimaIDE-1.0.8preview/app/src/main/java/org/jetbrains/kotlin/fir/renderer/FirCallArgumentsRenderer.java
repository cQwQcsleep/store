package org.jetbrains.kotlin.fir.renderer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u0018\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u001d\u001a\u00020\u0014*\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001eH\u0002J,\u0010!\u001a\u00020\u0014*\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\"0\u001e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001a0\u001eH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00060\u000bR\u00020\f8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "renderArgumentMapping", Argument.Delimiters.none, "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "renderArguments", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "renderArgumentsWithEvaluated", "Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentList;", "renderSeparated", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/FirElement;", "renderSeparatedWithEvaluatedValue", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "evaluated", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirCallArgumentsRenderer {
    public FirRendererComponents components;

    private final void renderSeparated(Map<Name, ? extends FirElement> map) {
        Iterator<T> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            Map.Entry entry = (Map.Entry) it.next();
            Name name = (Name) entry.getKey();
            FirElement firElement = (FirElement) entry.getValue();
            if (i > 0) {
                getPrinter().print(", ");
            }
            getPrinter().print(name + " = ");
            firElement.accept(getVisitor());
            i = i2;
        }
    }

    private final void renderSeparatedWithEvaluatedValue(Map<FirExpression, ? extends FirValueParameter> map, Map<Name, ? extends FirExpression> map2) {
        Iterator<T> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            Map.Entry entry = (Map.Entry) it.next();
            FirExpression firExpression = (FirExpression) entry.getKey();
            Name name = ((FirValueParameter) entry.getValue()).getName();
            if (i > 0) {
                getPrinter().print(", ");
            }
            getPrinter().print(name + " = ");
            firExpression.accept(getVisitor());
            if (map2.containsKey(name)) {
                getPrinter().print(" [evaluated = ");
                FirExpression firExpression2 = map2.get(name);
                if (firExpression2 != null) {
                    firExpression2.accept(getVisitor());
                }
                getPrinter().print("]");
            }
            i = i2;
        }
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

    public final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    public final FirRenderer.Visitor getVisitor() {
        return getComponents$org_jetbrains_kotlin_tree().getVisitor();
    }

    public void renderArgumentMapping(FirAnnotationArgumentMapping argumentMapping) {
        argumentMapping.getClass();
        getPrinter().print("(");
        renderSeparated(argumentMapping.getMapping());
        getPrinter().print(")");
    }

    public void renderArguments(List<? extends FirExpression> arguments) {
        arguments.getClass();
        getPrinter().print("(");
        getPrinter().renderSeparated$org_jetbrains_kotlin_tree(arguments, getVisitor());
        getPrinter().print(")");
    }

    public void renderArgumentsWithEvaluated(FirResolvedArgumentList arguments, FirAnnotationArgumentMapping argumentMapping) {
        arguments.getClass();
        argumentMapping.getClass();
        getPrinter().print("(");
        renderSeparatedWithEvaluatedValue(arguments.getMapping(), argumentMapping.getMapping());
        getPrinter().print(")");
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }
}
