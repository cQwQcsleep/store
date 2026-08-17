package org.jetbrains.kotlin.fir.analysis.cfa.util;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.util.SetMultimap;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u001f\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0002R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PropertyDeclarationCollector;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "declaredVariablesInLoop", "Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/util/SetMultimap;)V", "getDeclaredVariablesInLoop", "()Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitRepeatable", "loop", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class PropertyDeclarationCollector extends FirVisitor<Unit, FirStatement> {
    private final SetMultimap<FirStatement, FirVariableSymbol<?>> declaredVariablesInLoop;

    public PropertyDeclarationCollector(SetMultimap<FirStatement, FirVariableSymbol<?>> setMultimap) {
        setMultimap.getClass();
        this.declaredVariablesInLoop = setMultimap;
    }

    private final void visitRepeatable(FirStatement loop, FirStatement data) {
        visitElement2((FirElement) loop, loop);
        if (data != null) {
            SetMultimap<FirStatement, FirVariableSymbol<?>> setMultimap = this.declaredVariablesInLoop;
            setMultimap.putAll(data, setMultimap.get(loop));
        }
    }

    public final SetMultimap<FirStatement, FirVariableSymbol<?>> getDeclaredVariablesInLoop() {
        return this.declaredVariablesInLoop;
    }

    /* JADX INFO: renamed from: visitAnonymousFunction, reason: avoid collision after fix types in other method */
    public void visitAnonymousFunction2(FirAnonymousFunction anonymousFunction, FirStatement data) {
        anonymousFunction.getClass();
        EventOccurrencesRange invocationKind = anonymousFunction.getInvocationKind();
        if (invocationKind == null || !EventOccurrencesRangeKt.canBeRevisited(invocationKind)) {
            visitElement2((FirElement) anonymousFunction, data);
        } else {
            visitRepeatable(anonymousFunction, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDoWhileLoop(FirDoWhileLoop firDoWhileLoop, FirStatement firStatement) {
        visitDoWhileLoop2(firDoWhileLoop, firStatement);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, FirStatement firStatement) {
        visitElement2(firElement, firStatement);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: visitProperty, reason: avoid collision after fix types in other method */
    public void visitProperty2(FirProperty property, FirStatement data) {
        property.getClass();
        if ((property.getSymbol() instanceof FirLocalPropertySymbol) && data != null) {
            this.declaredVariablesInLoop.put(data, property.getSymbol());
        }
        visitElement2((FirElement) property, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitWhileLoop(FirWhileLoop firWhileLoop, FirStatement firStatement) {
        visitWhileLoop2(firWhileLoop, firStatement);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: visitDoWhileLoop, reason: avoid collision after fix types in other method */
    public void visitDoWhileLoop2(FirDoWhileLoop doWhileLoop, FirStatement data) {
        doWhileLoop.getClass();
        visitRepeatable(doWhileLoop, data);
    }

    /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
    public void visitElement2(FirElement element, FirStatement data) {
        element.getClass();
        element.acceptChildren(this, data);
    }

    /* JADX INFO: renamed from: visitWhileLoop, reason: avoid collision after fix types in other method */
    public void visitWhileLoop2(FirWhileLoop whileLoop, FirStatement data) {
        whileLoop.getClass();
        visitRepeatable(whileLoop, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnonymousFunction(FirAnonymousFunction firAnonymousFunction, FirStatement firStatement) {
        visitAnonymousFunction2(firAnonymousFunction, firStatement);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitProperty(FirProperty firProperty, FirStatement firStatement) {
        visitProperty2(firProperty, firStatement);
        return Unit.INSTANCE;
    }
}
