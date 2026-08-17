package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.FirDoubleColonExpressionResolver;
import org.jetbrains.kotlin.fir.resolve.FirOuterClassManager;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueStorage;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.calls.FirCallResolver;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver;
import org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponentsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSpecificTypeResolverTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSyntheticCallGenerator;
import org.jetbrains.kotlin.fir.resolve.transformers.IntegerLiteralAndOperatorApproximationTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001lB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010 2\u000e\b\u0004\u0010!\u001a\b\u0012\u0004\u0012\u0002H 0\"H\u0080\bø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0002H\u0016J\u0018\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020,2\u0006\u0010)\u001a\u00020\u0002H\u0016J\u0018\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u0010)\u001a\u00020\u0002H\u0016J\u0018\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R'\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@aX¦\u000e\u0082\u0001\u0002\b\u001a¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001b\u00107\u001a\b\u0012\u0004\u0012\u000209088Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0015\u0010<\u001a\u00020=8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0015\u0010@\u001a\u00020A8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0015\u0010D\u001a\u00020E8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0015\u0010H\u001a\u00020I8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0015\u0010L\u001a\u00020M8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0015\u0010P\u001a\u00020Q8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0015\u0010T\u001a\u00020U8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0015\u0010X\u001a\u00020Y8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0015\u0010\\\u001a\u00020]8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0012\u0010`\u001a\u00020a8Æ\u0002¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0015\u0010d\u001a\u00020e8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0015\u0010h\u001a\u00020i8Ä\u0002X\u0084\u0004¢\u0006\u0006\u001a\u0004\bj\u0010k\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006m"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractPhaseTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "getResolutionContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "value", Argument.Delimiters.none, "implicitTypeOnly", "getImplicitTypeOnly", "()Z", "setImplicitTypeOnly$org_jetbrains_kotlin_resolve", "(Z)V", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "withFullBodyResolve", "T", "l", "Lkotlin/Function0;", "withFullBodyResolve$org_jetbrains_kotlin_resolve", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "transformLazyExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "lazyExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;", "data", "transformLazyBlock", "lazyBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", "transformLazyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "lazyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", "suppressOrThrowError", Argument.Delimiters.none, "message", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "localScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "getLocalScopes", "()Ljava/util/List;", "noExpectedType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getNoExpectedType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "implicitValueStorage", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getImplicitValueStorage", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "inferenceComponents", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "getInferenceComponents", "()Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "getResolutionStageRunner", "()Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "samResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "getSamResolver", "()Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "typeResolverTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "getTypeResolverTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "callResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "getCallResolver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "callCompleter", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "getCallCompleter", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "dataFlowAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "getDataFlowAnalyzer", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "BodyResolveTransformerComponents", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractBodyResolveTransformer extends FirAbstractPhaseTransformer<ResolutionMode> {

    @Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001aR\u001e\u0010\u001e\u001a\f\u0012\u0004\u0012\u00020 0\u001fj\u0002`!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u001aR\u0014\u00103\u001a\u0002048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010:\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020?8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020CX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER#\u0010F\u001a\u0004\u0018\u00010G8VX\u0096\u0084\u0002¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bJ\u0010K*\u0004\bH\u0010IR\u001b\u0010N\u001a\u00020O8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bP\u0010QR\u001b\u0010T\u001a\u00020U8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bX\u0010S\u001a\u0004\bV\u0010WR\u001b\u0010Y\u001a\u00020Z8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b]\u0010S\u001a\u0004\b[\u0010\\R\u001b\u0010^\u001a\u00020_8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bb\u0010S\u001a\u0004\b`\u0010aR\u001b\u0010c\u001a\u00020d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bg\u0010S\u001a\u0004\be\u0010fR\u001b\u0010h\u001a\u00020i8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bl\u0010S\u001a\u0004\bj\u0010kR\u001b\u0010m\u001a\u00020n8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bq\u0010S\u001a\u0004\bo\u0010pR\u001b\u0010r\u001a\u00020s8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bv\u0010S\u001a\u0004\bt\u0010uR\u001b\u0010w\u001a\u00020x8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b{\u0010S\u001a\u0004\by\u0010zR\u0014\u0010|\u001a\u00020}8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b~\u0010\u007f¨\u0006\u0080\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "expandTypeAliases", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "fileImportsScope", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getFileImportsScope", "()Ljava/util/List;", "towerDataElements", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "getTowerDataElements", "localScopes", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirLocalScopes;", "getLocalScopes", "()Lkotlinx/collections/immutable/PersistentList;", "towerDataContext", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "getTowerDataContext", "()Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "implicitValueStorage", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getImplicitValueStorage", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "containingDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getContainingDeclarations", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "getReturnTypeCalculator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "container", "getContainer", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "noExpectedType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getNoExpectedType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "getResolutionStageRunner", "()Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "inlineFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getInlineFunction$delegate", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;)Ljava/lang/Object;", "getInlineFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "inlineFunction$receiver", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "callResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "getCallResolver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "callResolver$delegate", "Lkotlin/Lazy;", "typeResolverTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "getTypeResolverTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "typeResolverTransformer$delegate", "callCompleter", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "getCallCompleter", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "callCompleter$delegate", "dataFlowAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "getDataFlowAnalyzer", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "dataFlowAnalyzer$delegate", "syntheticCallGenerator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", "getSyntheticCallGenerator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", "syntheticCallGenerator$delegate", "doubleColonExpressionResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirDoubleColonExpressionResolver;", "getDoubleColonExpressionResolver", "()Lorg/jetbrains/kotlin/fir/resolve/FirDoubleColonExpressionResolver;", "doubleColonExpressionResolver$delegate", "outerClassManager", "Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;", "getOuterClassManager", "()Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;", "outerClassManager$delegate", "samResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "getSamResolver", "()Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "samResolver$delegate", "integerLiteralAndOperatorApproximationTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;", "getIntegerLiteralAndOperatorApproximationTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;", "integerLiteralAndOperatorApproximationTransformer$delegate", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "getResolutionContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static class BodyResolveTransformerComponents extends BodyResolveComponents {

        /* JADX INFO: renamed from: callCompleter$delegate, reason: from kotlin metadata */
        private final Lazy callCompleter;

        /* JADX INFO: renamed from: callResolver$delegate, reason: from kotlin metadata */
        private final Lazy callResolver;
        private final BodyResolveContext context;

        /* JADX INFO: renamed from: dataFlowAnalyzer$delegate, reason: from kotlin metadata */
        private final Lazy dataFlowAnalyzer;

        /* JADX INFO: renamed from: doubleColonExpressionResolver$delegate, reason: from kotlin metadata */
        private final Lazy doubleColonExpressionResolver;

        /* JADX INFO: renamed from: inlineFunction$receiver, reason: from kotlin metadata */
        private final BodyResolveContext inlineFunction;

        /* JADX INFO: renamed from: integerLiteralAndOperatorApproximationTransformer$delegate, reason: from kotlin metadata */
        private final Lazy integerLiteralAndOperatorApproximationTransformer;

        /* JADX INFO: renamed from: outerClassManager$delegate, reason: from kotlin metadata */
        private final Lazy outerClassManager;
        private final ResolutionStageRunner resolutionStageRunner;

        /* JADX INFO: renamed from: samResolver$delegate, reason: from kotlin metadata */
        private final Lazy samResolver;
        private final ScopeSession scopeSession;
        private final FirSession session;

        /* JADX INFO: renamed from: syntheticCallGenerator$delegate, reason: from kotlin metadata */
        private final Lazy syntheticCallGenerator;
        private final FirAbstractBodyResolveTransformerDispatcher transformer;

        /* JADX INFO: renamed from: typeResolverTransformer$delegate, reason: from kotlin metadata */
        private final Lazy typeResolverTransformer;

        public BodyResolveTransformerComponents(FirSession firSession, ScopeSession scopeSession, FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher, BodyResolveContext bodyResolveContext, final boolean z) {
            firSession.getClass();
            scopeSession.getClass();
            firAbstractBodyResolveTransformerDispatcher.getClass();
            bodyResolveContext.getClass();
            this.session = firSession;
            this.scopeSession = scopeSession;
            this.transformer = firAbstractBodyResolveTransformerDispatcher;
            this.context = bodyResolveContext;
            this.resolutionStageRunner = new ResolutionStageRunner();
            this.inlineFunction = getContext();
            LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
            this.callResolver = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: bx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.c(this.b);
                }
            });
            this.typeResolverTransformer = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: cx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.h(this.b, z);
                }
            });
            this.callCompleter = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: dx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.g(this.b);
                }
            });
            this.dataFlowAnalyzer = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ex4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.d(this.b);
                }
            });
            this.syntheticCallGenerator = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: fx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.f(this.b);
                }
            });
            this.doubleColonExpressionResolver = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: gx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.i(this.b);
                }
            });
            this.outerClassManager = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: hx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.e(this.b);
                }
            });
            this.samResolver = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ix4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.j(this.b);
                }
            });
            this.integerLiteralAndOperatorApproximationTransformer = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: jx4
                public final Object invoke() {
                    return FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents.b(this.b);
                }
            });
        }

        public static IntegerLiteralAndOperatorApproximationTransformer b(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return new IntegerLiteralAndOperatorApproximationTransformer(bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession());
        }

        public static FirCallResolver c(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            FirTowerResolver firTowerResolver = null;
            return new FirCallResolver(bodyResolveTransformerComponents, firTowerResolver, 2, firTowerResolver);
        }

        public static FirDataFlowAnalyzer d(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return FirDataFlowAnalyzer.INSTANCE.createFirDataFlowAnalyzer(bodyResolveTransformerComponents, bodyResolveTransformerComponents.getContext().getDataFlowAnalyzerContext());
        }

        public static FirOuterClassManager e(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return new FirOuterClassManager(bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getContext().getOuterLocalClassForNested());
        }

        public static FirSyntheticCallGenerator f(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return new FirSyntheticCallGenerator(bodyResolveTransformerComponents);
        }

        public static FirCallCompleter g(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return new FirCallCompleter(bodyResolveTransformerComponents.transformer, bodyResolveTransformerComponents);
        }

        public static FirSpecificTypeResolverTransformer h(BodyResolveTransformerComponents bodyResolveTransformerComponents, boolean z) {
            return new FirSpecificTypeResolverTransformer(bodyResolveTransformerComponents.getSession(), false, false, null, z, 14, null);
        }

        public static FirDoubleColonExpressionResolver i(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return new FirDoubleColonExpressionResolver(bodyResolveTransformerComponents, bodyResolveTransformerComponents.getContext());
        }

        public static FirSamResolver j(BodyResolveTransformerComponents bodyResolveTransformerComponents) {
            return new FirSamResolver(bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), bodyResolveTransformerComponents.getOuterClassManager());
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirCallCompleter getCallCompleter() {
            return (FirCallCompleter) this.callCompleter.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirCallResolver getCallResolver() {
            return (FirCallResolver) this.callResolver.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirDeclaration getContainer() {
            FirDeclaration containerIfAny = getContext().getContainerIfAny();
            containerIfAny.getClass();
            return containerIfAny;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public List<FirDeclaration> getContainingDeclarations() {
            return getContext().getContainers();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public BodyResolveContext getContext() {
            return this.context;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirDataFlowAnalyzer getDataFlowAnalyzer() {
            return (FirDataFlowAnalyzer) this.dataFlowAnalyzer.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirDoubleColonExpressionResolver getDoubleColonExpressionResolver() {
            return (FirDoubleColonExpressionResolver) this.doubleColonExpressionResolver.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirFile getFile() {
            return getContext().getFile();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public List<FirScope> getFileImportsScope() {
            return getContext().getFileImportsScope();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public ImplicitValueStorage getImplicitValueStorage() {
            return getContext().getImplicitValueStorage();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirFunction getInlineFunction() {
            return this.inlineFunction.getPublicApiInlineFunction();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public IntegerLiteralAndOperatorApproximationTransformer getIntegerLiteralAndOperatorApproximationTransformer() {
            return (IntegerLiteralAndOperatorApproximationTransformer) this.integerLiteralAndOperatorApproximationTransformer.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public PersistentList<FirLocalScope> getLocalScopes() {
            return getContext().getTowerDataContext().getLocalScopes();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirTypeRef getNoExpectedType() {
            return FirImplicitTypeRefImplWithoutSource.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirOuterClassManager getOuterClassManager() {
            return (FirOuterClassManager) this.outerClassManager.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public ResolutionContext getResolutionContext() {
            return this.transformer.getResolutionContext();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public ResolutionStageRunner getResolutionStageRunner() {
            return this.resolutionStageRunner;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public ReturnTypeCalculator getReturnTypeCalculator() {
            return getContext().getReturnTypeCalculator();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirSamResolver getSamResolver() {
            return (FirSamResolver) this.samResolver.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
        public ScopeSession getScopeSession() {
            return this.scopeSession;
        }

        @Override // org.jetbrains.kotlin.fir.SessionHolder
        public FirSession getSession() {
            return this.session;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirSymbolProvider getSymbolProvider() {
            return FirSymbolProviderKt.getSymbolProvider(getSession());
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirSyntheticCallGenerator getSyntheticCallGenerator() {
            return (FirSyntheticCallGenerator) this.syntheticCallGenerator.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public FirTowerDataContext getTowerDataContext() {
            return getContext().getTowerDataContext();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.BodyResolveComponents
        public List<FirTowerDataElement> getTowerDataElements() {
            return getContext().getTowerDataContext().getTowerDataElements();
        }

        public final FirAbstractBodyResolveTransformerDispatcher getTransformer() {
            return this.transformer;
        }

        public final FirSpecificTypeResolverTransformer getTypeResolverTransformer() {
            return (FirSpecificTypeResolverTransformer) this.typeResolverTransformer.getValue();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractBodyResolveTransformer(FirResolvePhase firResolvePhase) {
        super(firResolvePhase);
        firResolvePhase.getClass();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void suppressOrThrowError(String message, FirElement element) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (Boolean.parseBoolean(System.getProperty("kotlin.suppress.lazy.expression.access"))) {
            return;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(message, (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "firElement", element);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public final FirCallCompleter getCallCompleter() {
        return getComponents().getCallCompleter();
    }

    public final FirCallResolver getCallResolver() {
        return getComponents().getCallResolver();
    }

    public abstract BodyResolveTransformerComponents getComponents();

    public abstract BodyResolveContext getContext();

    public final FirDataFlowAnalyzer getDataFlowAnalyzer() {
        return getComponents().getDataFlowAnalyzer();
    }

    public final FirFile getFile() {
        return getComponents().getFile();
    }

    public abstract boolean getImplicitTypeOnly();

    public final ImplicitValueStorage getImplicitValueStorage() {
        return getComponents().getImplicitValueStorage();
    }

    public final InferenceComponents getInferenceComponents() {
        return InferenceComponentsKt.getInferenceComponents(getSession());
    }

    public final List<FirLocalScope> getLocalScopes() {
        return getComponents().getLocalScopes();
    }

    public final FirTypeRef getNoExpectedType() {
        return getComponents().getNoExpectedType();
    }

    public abstract ResolutionContext getResolutionContext();

    public final ResolutionStageRunner getResolutionStageRunner() {
        return getComponents().getResolutionStageRunner();
    }

    public final FirSamResolver getSamResolver() {
        return getComponents().getSamResolver();
    }

    public final ScopeSession getScopeSession() {
        return getComponents().getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public final FirSession getSession() {
        return getComponents().getSession();
    }

    public final FirSymbolProvider getSymbolProvider() {
        return getComponents().getSymbolProvider();
    }

    public final FirSpecificTypeResolverTransformer getTypeResolverTransformer() {
        return getComponents().getTypeResolverTransformer();
    }

    @PrivateForInline
    public abstract void setImplicitTypeOnly$org_jetbrains_kotlin_resolve(boolean z);

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformLazyBlock(FirLazyBlock lazyBlock, ResolutionMode data) throws KotlinIllegalArgumentExceptionWithAttachments {
        lazyBlock.getClass();
        data.getClass();
        suppressOrThrowError("FirLazyBlock should be calculated before accessing", lazyBlock);
        return lazyBlock;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirContractDescription transformLazyContractDescription(FirLazyContractDescription lazyContractDescription, ResolutionMode data) throws KotlinIllegalArgumentExceptionWithAttachments {
        lazyContractDescription.getClass();
        data.getClass();
        suppressOrThrowError("FirLazyContractDescription should be calculated before accessing", lazyContractDescription);
        return lazyContractDescription;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformLazyExpression(FirLazyExpression lazyExpression, ResolutionMode data) throws KotlinIllegalArgumentExceptionWithAttachments {
        lazyExpression.getClass();
        data.getClass();
        suppressOrThrowError("FirLazyExpression should be calculated before accessing", lazyExpression);
        return lazyExpression;
    }

    public final <T> T withFullBodyResolve$org_jetbrains_kotlin_resolve(Function0<? extends T> l) {
        l.getClass();
        boolean implicitTypeOnly = getImplicitTypeOnly();
        if (implicitTypeOnly) {
            setImplicitTypeOnly$org_jetbrains_kotlin_resolve(false);
        }
        try {
            return (T) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (implicitTypeOnly) {
                setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
            }
            InlineMarker.finallyEnd(1);
        }
    }
}
