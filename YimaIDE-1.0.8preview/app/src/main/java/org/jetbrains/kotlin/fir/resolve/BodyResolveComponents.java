package org.jetbrains.kotlin.fir.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.resolve.calls.FirCallResolver;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSyntheticCallGenerator;
import org.jetbrains.kotlin.fir.resolve.transformers.IntegerLiteralAndOperatorApproximationTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\f\u0012\u0004\u0012\u00020\u001d0\u001cj\u0002`\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0012\u0010-\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0012\u00100\u001a\u000201X¦\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0012\u00104\u001a\u000205X¦\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u0012\u00108\u001a\u000209X¦\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0012\u0010<\u001a\u00020=X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0012\u0010@\u001a\u00020AX¦\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0012\u0010D\u001a\u00020EX¦\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0012\u0010H\u001a\u00020IX¦\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0012\u0010L\u001a\u00020MX¦\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0012\u0010P\u001a\u00020QX¦\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0014\u0010T\u001a\u0004\u0018\u00010UX¦\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0012\u0010X\u001a\u00020YX¦\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0012\u0010\\\u001a\u00020]X¦\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_¨\u0006`"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "<init>", "()V", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "getReturnTypeCalculator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "implicitValueStorage", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getImplicitValueStorage", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getContainingDeclarations", "()Ljava/util/List;", "fileImportsScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getFileImportsScope", "towerDataElements", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "getTowerDataElements", "towerDataContext", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "getTowerDataContext", "()Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "localScopes", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirLocalScopes;", "getLocalScopes", "()Lkotlinx/collections/immutable/PersistentList;", "noExpectedType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getNoExpectedType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "container", "getContainer", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "getResolutionStageRunner", "()Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "samResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "getSamResolver", "()Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "callResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "getCallResolver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "callCompleter", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "getCallCompleter", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "doubleColonExpressionResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirDoubleColonExpressionResolver;", "getDoubleColonExpressionResolver", "()Lorg/jetbrains/kotlin/fir/resolve/FirDoubleColonExpressionResolver;", "syntheticCallGenerator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", "getSyntheticCallGenerator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", "dataFlowAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "getDataFlowAnalyzer", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "outerClassManager", "Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;", "getOuterClassManager", "()Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;", "integerLiteralAndOperatorApproximationTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;", "getIntegerLiteralAndOperatorApproximationTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;", "inlineFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getInlineFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "getResolutionContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class BodyResolveComponents implements SessionAndScopeSessionHolder {
    public abstract FirCallCompleter getCallCompleter();

    public abstract FirCallResolver getCallResolver();

    public abstract FirDeclaration getContainer();

    public abstract List<FirDeclaration> getContainingDeclarations();

    public abstract BodyResolveContext getContext();

    public abstract FirDataFlowAnalyzer getDataFlowAnalyzer();

    public abstract FirDoubleColonExpressionResolver getDoubleColonExpressionResolver();

    public abstract FirFile getFile();

    public abstract List<FirScope> getFileImportsScope();

    public abstract ImplicitValueStorage getImplicitValueStorage();

    public abstract FirFunction getInlineFunction();

    public abstract IntegerLiteralAndOperatorApproximationTransformer getIntegerLiteralAndOperatorApproximationTransformer();

    public abstract PersistentList<FirLocalScope> getLocalScopes();

    public abstract FirTypeRef getNoExpectedType();

    public abstract FirOuterClassManager getOuterClassManager();

    public abstract ResolutionContext getResolutionContext();

    public abstract ResolutionStageRunner getResolutionStageRunner();

    public abstract ReturnTypeCalculator getReturnTypeCalculator();

    public abstract FirSamResolver getSamResolver();

    public abstract FirSymbolProvider getSymbolProvider();

    public abstract FirSyntheticCallGenerator getSyntheticCallGenerator();

    public abstract FirTowerDataContext getTowerDataContext();

    public abstract List<FirTowerDataElement> getTowerDataElements();
}
