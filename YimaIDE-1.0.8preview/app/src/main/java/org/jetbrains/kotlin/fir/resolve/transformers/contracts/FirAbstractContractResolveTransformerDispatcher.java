package org.jetbrains.kotlin.fir.resolve.transformers.contracts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.contracts.ContractUtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.builder.FirErrorContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.contracts.builder.FirLegacyRawContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.contracts.builder.FirResolvedContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousFunctionExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.contracts.FirAbstractContractResolveTransformerDispatcher;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirMemberTypeParameterScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ContractsDslNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001#B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00060\u0013R\u00020\u0000X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "expressionsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "getExpressionsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "declarationsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "getDeclarationsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "contractDeclarationsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher$FirDeclarationsContractResolveTransformer;", "getContractDeclarationsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher$FirDeclarationsContractResolveTransformer;", "regularDeclarationsTransformer", "contractMode", Argument.Delimiters.none, "insideContractDescription", "transformAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "FirDeclarationsContractResolveTransformer", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractContractResolveTransformerDispatcher extends FirAbstractBodyResolveTransformerDispatcher {
    private boolean contractMode;
    private final FirExpressionsResolveTransformer expressionsTransformer;
    private boolean insideContractDescription;
    private final FirDeclarationsResolveTransformer regularDeclarationsTransformer;

    @Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0094\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\rH\u0002J\u001f\u0010\u001a\u001a\u0002H\u001b\"\b\b\u0000\u0010\u001b*\u00020\u001c2\u0006\u0010\u0019\u001a\u0002H\u001bH\u0002¢\u0006\u0002\u0010\u001dJ/\u0010\u001e\u001a\u0002H\u001b\"\b\b\u0000\u0010\u001b*\u00020\u001c2\u0006\u0010\u0019\u001a\u0002H\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002¢\u0006\u0002\u0010#J\"\u0010$\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001b0&H\u0082\b¢\u0006\u0002\u0010'J\"\u0010(\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001b0&H\u0082\b¢\u0006\u0002\u0010'J'\u0010)\u001a\u0002H\u001b\"\b\b\u0000\u0010\u001b*\u00020\u001c2\u0006\u0010\u0019\u001a\u0002H\u001b2\u0006\u0010\u001f\u001a\u00020*H\u0002¢\u0006\u0002\u0010+J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u00100\u001a\u0002012\u0006\u00102\u001a\u0002012\f\u00103\u001a\b\u0012\u0004\u0012\u0002010&H\u0016J\u0018\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u00107\u001a\u0002052\u0006\u00106\u001a\u0002052\f\u00103\u001a\b\u0012\u0004\u0012\u0002050&H\u0016J\u001e\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002092\f\u00103\u001a\b\u0012\u0004\u0012\u0002090&H\u0016J\u0018\u0010;\u001a\u0002092\u0006\u0010:\u001a\u0002092\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020=2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020C2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020F2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020I2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020L2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020O2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020R2\u0006\u0010\u0007\u001a\u00020\bH\u0016J/\u0010T\u001a\u0002H\u001b\"\b\b\u0000\u0010\u001b*\u00020\u001c2\u0006\u0010\u0019\u001a\u0002H\u001b2\u0006\u0010U\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002¢\u0006\u0002\u0010#R\u0018\u0010V\u001a\u00020\"*\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010X¨\u0006Y"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher$FirDeclarationsContractResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher;)V", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformAnonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymousFunction", "transformProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "transformErrorProperty", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "transformField", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "field", "transformPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "owner", "transformContractDescriptionOwner", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "(Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;)Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "transformLegacyRawContractDescriptionOwner", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "hasBodyContract", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;Z)Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "withContractModeDisabled", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withEnteringContractDescription", "transformRawContractDescriptionOwner", "Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;", "(Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;)Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "transformDeclarationContent", Argument.Delimiters.none, "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "withFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "action", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "regularClass", "forRegularClassBody", "withScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "transformScript", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "transformAnonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "anonymousObject", "transformAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "anonymousInitializer", "transformConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "constructor", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "typeAlias", "transformDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "danglingModifierList", "transformErrorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "errorPrimaryConstructor", "transformEnumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "enumEntry", "transformOwnerOfErrorContract", "description", "hasContractToResolve", "getHasContractToResolve", "(Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;)Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public class FirDeclarationsContractResolveTransformer extends FirDeclarationsResolveTransformer {
        public FirDeclarationsContractResolveTransformer() {
            super(FirAbstractContractResolveTransformerDispatcher.this);
        }

        private final boolean getHasContractToResolve(FirContractDescriptionOwner firContractDescriptionOwner) {
            return (firContractDescriptionOwner.getContractDescription() instanceof FirLegacyRawContractDescription) || (firContractDescriptionOwner.getContractDescription() instanceof FirRawContractDescription);
        }

        public static FirScript p(Function0 function0) {
            return (FirScript) function0.invoke();
        }

        public static FirStatement q(FirLegacyRawContractDescription firLegacyRawContractDescription, FirContractCallBlock firContractCallBlock) {
            firContractCallBlock.getClass();
            return firLegacyRawContractDescription.getContractCall();
        }

        public static FirRegularClass r(FirDeclarationsContractResolveTransformer firDeclarationsContractResolveTransformer, FirRegularClass firRegularClass, ResolutionMode resolutionMode) {
            firDeclarationsContractResolveTransformer.transformDeclarationContent((FirClass) firRegularClass, resolutionMode);
            return firRegularClass;
        }

        public static FirAnonymousFunction s(FirDeclarationsContractResolveTransformer firDeclarationsContractResolveTransformer, FirAnonymousFunction firAnonymousFunction) {
            return (FirAnonymousFunction) firDeclarationsContractResolveTransformer.transformContractDescriptionOwner(firAnonymousFunction);
        }

        public static FirRegularClass t(Function0 function0) {
            return (FirRegularClass) function0.invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirConstructor transformConstructor$lambda$0$0(FirDeclarationsContractResolveTransformer firDeclarationsContractResolveTransformer, FirConstructor firConstructor) {
            return (FirConstructor) firDeclarationsContractResolveTransformer.transformContractDescriptionOwner(firConstructor);
        }

        private final <T extends FirContractDescriptionOwner> T transformContractDescriptionOwner(T owner) {
            T t;
            getComponents().getDataFlowAnalyzer().enterContractDescription();
            try {
                FirAbstractContractResolveTransformerDispatcher.this.insideContractDescription = true;
                FirContractDescription contractDescription = owner.getContractDescription();
                if (contractDescription instanceof FirLegacyRawContractDescription) {
                    t = (T) transformLegacyRawContractDescriptionOwner(owner, (FirLegacyRawContractDescription) contractDescription, true);
                } else {
                    if (!(contractDescription instanceof FirRawContractDescription)) {
                        throw new IllegalArgumentException(owner + " has a contract description of an unknown type");
                    }
                    t = (T) transformRawContractDescriptionOwner(owner, (FirRawContractDescription) contractDescription);
                }
                FirAbstractContractResolveTransformerDispatcher.this.insideContractDescription = false;
                return t;
            } catch (Throwable th) {
                FirAbstractContractResolveTransformerDispatcher.this.insideContractDescription = false;
                throw th;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final <T extends FirContractDescriptionOwner> T transformLegacyRawContractDescriptionOwner(T owner, final FirLegacyRawContractDescription contractDescription, boolean hasBodyContract) {
            List listPlus;
            FirBlock body;
            if (owner instanceof FirPropertyAccessor) {
                FirPropertyAccessor firPropertyAccessor = (FirPropertyAccessor) owner;
                listPlus = CollectionsKt.plus(firPropertyAccessor.getValueParameters(), ((FirProperty) firPropertyAccessor.getPropertySymbol().getFir()).getContextParameters());
            } else {
                if (!(owner instanceof FirFunction)) {
                    bu8.a();
                    return null;
                }
                FirFunction firFunction = (FirFunction) owner;
                listPlus = CollectionsKt.plus(firFunction.getValueParameters(), firFunction.getContextParameters());
            }
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                getTransformer().getContext().storeVariable((FirValueParameter) it.next(), getSession());
            }
            try {
                FirAbstractContractResolveTransformerDispatcher.this.contractMode = false;
                FirElement firElementTransformSingle = FirTransformerUtilKt.transformSingle(contractDescription.getContractCall(), getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                ((FirFunctionCall) firElementTransformSingle).replaceConeTypeOrNull(getSession().getBuiltinTypes().getUnitType().getConeType());
                FirFunctionCall firFunctionCall = (FirFunctionCall) firElementTransformSingle;
                FirAbstractContractResolveTransformerDispatcher.this.contractMode = true;
                FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
                if (!Intrinsics.areEqual(resolvedCallableSymbol != null ? resolvedCallableSymbol.getCallableId() : null, ContractsDslNames.INSTANCE.getCONTRACT())) {
                    if (hasBodyContract) {
                        FirBlock body2 = ((FirFunction) owner).getBody();
                        body2.getClass();
                        FirExpressionUtilKt.replaceFirstStatement(body2, new Function1() { // from class: rx4
                            public final Object invoke(Object obj) {
                                return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.x(contractDescription, (FirContractCallBlock) obj);
                            }
                        });
                    }
                    owner.replaceContractDescription(null);
                    getComponents().getDataFlowAnalyzer().exitContractDescription();
                    return owner;
                }
                Object objSingleOrNull = CollectionsKt.singleOrNull(firFunctionCall.getArgumentList().getArguments());
                FirAnonymousFunctionExpression firAnonymousFunctionExpression = objSingleOrNull instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) objSingleOrNull : null;
                if (firAnonymousFunctionExpression != null && firAnonymousFunctionExpression.getAnonymousFunction().getIsLambda() && (body = firAnonymousFunctionExpression.getAnonymousFunction().getBody()) != null) {
                    FirResolvedContractDescriptionBuilder firResolvedContractDescriptionBuilder = new FirResolvedContractDescriptionBuilder();
                    ConeEffectExtractor coneEffectExtractor = new ConeEffectExtractor(getSession(), owner, listPlus);
                    for (FirStatement firStatement : body.getStatements()) {
                        KtSourceElement source = firStatement.getSource();
                        if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitReturn)) {
                            KtContractDescriptionElement ktContractDescriptionElement = (KtContractDescriptionElement) firStatement.accept(coneEffectExtractor, null);
                            if (ktContractDescriptionElement instanceof KtEffectDeclaration) {
                                KtEffectDeclaration ktEffectDeclaration = (KtEffectDeclaration) ktContractDescriptionElement;
                                boolean erroneous = ktEffectDeclaration.getErroneous();
                                if (!erroneous) {
                                    firResolvedContractDescriptionBuilder.getEffects().add(ContractUtilsKt.toFirElement((KtEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktEffectDeclaration, firStatement.getSource()));
                                } else {
                                    if (!erroneous) {
                                        bu8.a();
                                        return null;
                                    }
                                    firResolvedContractDescriptionBuilder.getUnresolvedEffects().add(ContractUtilsKt.toFirElement((KtEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktEffectDeclaration, firStatement.getSource()));
                                }
                            } else {
                                firResolvedContractDescriptionBuilder.getUnresolvedEffects().add(ContractUtilsKt.toFirElement((KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic>) ktContractDescriptionElement, firStatement.getSource()));
                            }
                        }
                    }
                    firResolvedContractDescriptionBuilder.setSource(contractDescription.getSource());
                    firResolvedContractDescriptionBuilder.setDiagnostic(contractDescription.getDiagnostic());
                    owner.replaceContractDescription(firResolvedContractDescriptionBuilder.build());
                    getComponents().getDataFlowAnalyzer().exitContractDescription();
                    return owner;
                }
                return (T) transformOwnerOfErrorContract(owner, contractDescription, hasBodyContract);
            } catch (Throwable th) {
                FirAbstractContractResolveTransformerDispatcher.this.contractMode = true;
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirNamedFunction transformNamedFunction$lambda$0$0(FirDeclarationsContractResolveTransformer firDeclarationsContractResolveTransformer, FirNamedFunction firNamedFunction) {
            return (FirNamedFunction) firDeclarationsContractResolveTransformer.transformContractDescriptionOwner(firNamedFunction);
        }

        private final <T extends FirContractDescriptionOwner> T transformOwnerOfErrorContract(T owner, final FirLegacyRawContractDescription description, boolean hasBodyContract) {
            FirErrorContractDescriptionBuilder firErrorContractDescriptionBuilder = new FirErrorContractDescriptionBuilder();
            firErrorContractDescriptionBuilder.setSource(description.getSource());
            firErrorContractDescriptionBuilder.setDiagnostic(description.getDiagnostic());
            owner.replaceContractDescription(firErrorContractDescriptionBuilder.build());
            if (hasBodyContract) {
                FirBlock body = owner.getBody();
                body.getClass();
                FirExpressionUtilKt.replaceFirstStatement(body, new Function1() { // from class: ox4
                    public final Object invoke(Object obj) {
                        return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.q(description, (FirContractCallBlock) obj);
                    }
                });
            }
            getComponents().getDataFlowAnalyzer().exitContractDescription();
            return owner;
        }

        private final FirStatement transformPropertyAccessor(final FirPropertyAccessor propertyAccessor, FirProperty owner) {
            return !getHasContractToResolve(propertyAccessor) ? propertyAccessor : (FirStatement) getTransformer().getContext().withPropertyAccessor(owner, propertyAccessor, getTransformer().getComponents(), true, new Function0() { // from class: sx4
                public final Object invoke() {
                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.u(this.b, propertyAccessor);
                }
            });
        }

        private final <T extends FirContractDescriptionOwner> T transformRawContractDescriptionOwner(T owner, FirRawContractDescription contractDescription) {
            FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
            firAnonymousFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
            FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
            firAnonymousFunctionBuilder.setOrigin(source);
            KtSourceElement source2 = contractDescription.getSource();
            firAnonymousFunctionBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ContractBlock.INSTANCE, null, 2, null) : null);
            FirTypeRef firTypeRef = FirImplicitTypeRefImplWithoutSource.INSTANCE;
            firAnonymousFunctionBuilder.setReturnTypeRef(firTypeRef);
            firAnonymousFunctionBuilder.setSymbol(new FirAnonymousFunctionSymbol());
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setTypeRef(firTypeRef);
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
            firReceiverParameterBuilder.setOrigin(source);
            KtSourceElement source3 = contractDescription.getSource();
            firReceiverParameterBuilder.setSource(source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.ContractBlock.INSTANCE, null, 2, null) : null);
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firAnonymousFunctionBuilder.getSymbol());
            firAnonymousFunctionBuilder.setReceiverParameter(firReceiverParameterBuilder.mo288build());
            firAnonymousFunctionBuilder.setLambda(true);
            firAnonymousFunctionBuilder.setHasExplicitParameterList(true);
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            Iterator<T> it = contractDescription.getRawEffects().iterator();
            while (it.hasNext()) {
                firBlockBuilder.getStatements().add((FirExpression) it.next());
            }
            firAnonymousFunctionBuilder.setBody(firBlockBuilder.mo288build());
            FirAnonymousFunction firAnonymousFunctionMo288build = firAnonymousFunctionBuilder.mo288build();
            FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
            firAnonymousFunctionExpressionBuilder.setAnonymousFunction(firAnonymousFunctionMo288build);
            firAnonymousFunctionExpressionBuilder.setTrailingLambda(true);
            FirAnonymousFunctionExpression firAnonymousFunctionExpressionMo288build = firAnonymousFunctionExpressionBuilder.mo288build();
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            Name nameIdentifier = Name.identifier("contract");
            nameIdentifier.getClass();
            firSimpleNamedReferenceBuilder.setName(nameIdentifier);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            firArgumentListBuilder.getArguments().add(firAnonymousFunctionExpressionMo288build);
            firFunctionCallBuilder.setArgumentList(firArgumentListBuilder.build());
            FirFunctionCall firFunctionCallMo288build = firFunctionCallBuilder.mo288build();
            FirLegacyRawContractDescriptionBuilder firLegacyRawContractDescriptionBuilder = new FirLegacyRawContractDescriptionBuilder();
            firLegacyRawContractDescriptionBuilder.setContractCall(firFunctionCallMo288build);
            firLegacyRawContractDescriptionBuilder.setSource(contractDescription.getSource());
            FirLegacyRawContractDescription firLegacyRawContractDescriptionBuild = firLegacyRawContractDescriptionBuilder.build();
            owner.replaceContractDescription(firLegacyRawContractDescriptionBuild);
            return (T) transformLegacyRawContractDescriptionOwner(owner, firLegacyRawContractDescriptionBuild, false);
        }

        public static FirPropertyAccessor u(FirDeclarationsContractResolveTransformer firDeclarationsContractResolveTransformer, FirPropertyAccessor firPropertyAccessor) {
            return (FirPropertyAccessor) firDeclarationsContractResolveTransformer.transformContractDescriptionOwner(firPropertyAccessor);
        }

        public static FirScript w(FirDeclarationsContractResolveTransformer firDeclarationsContractResolveTransformer, FirScript firScript, ResolutionMode resolutionMode) {
            FirDeclaration firDeclarationTransformDeclarationContent = firDeclarationsContractResolveTransformer.transformDeclarationContent(firScript, resolutionMode);
            firDeclarationTransformDeclarationContent.getClass();
            return (FirScript) firDeclarationTransformDeclarationContent;
        }

        public static FirStatement x(FirLegacyRawContractDescription firLegacyRawContractDescription, FirContractCallBlock firContractCallBlock) {
            firContractCallBlock.getClass();
            return firLegacyRawContractDescription.getContractCall();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer
        public FirRegularClass forRegularClassBody(FirRegularClass regularClass, final Function0<? extends FirRegularClass> action) {
            regularClass.getClass();
            action.getClass();
            return (FirRegularClass) getTransformer().getContext().forRegularClassBody(regularClass, getTransformer().getComponents(), new Function0() { // from class: mx4
                public final Object invoke() {
                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.t(action);
                }
            });
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirAnonymousFunction transformAnonymousFunction(final FirAnonymousFunction anonymousFunction, ResolutionMode data) {
            anonymousFunction.getClass();
            data.getClass();
            return !getHasContractToResolve(anonymousFunction) ? anonymousFunction : (FirAnonymousFunction) getTransformer().getContext().forFunctionBody(anonymousFunction, getTransformer().getComponents(), new Function0() { // from class: nx4
                public final Object invoke() {
                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.s(this.b, anonymousFunction);
                }
            });
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirAnonymousInitializer transformAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, ResolutionMode data) {
            anonymousInitializer.getClass();
            data.getClass();
            return anonymousInitializer;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirAnonymousObject transformAnonymousObject(final FirAnonymousObject anonymousObject, final ResolutionMode data) {
            anonymousObject.getClass();
            data.getClass();
            final BodyResolveContext context = getTransformer().getContext();
            context.withScopesForClass(anonymousObject, getTransformer().getComponents(), new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.contracts.FirAbstractContractResolveTransformerDispatcher$FirDeclarationsContractResolveTransformer$transformAnonymousObject$$inlined$withAnonymousObject$1
                public final Unit invoke() {
                    BodyResolveContext bodyResolveContext = context;
                    bodyResolveContext.getContainers().add(anonymousObject);
                    try {
                        this.transformDeclarationContent((FirClass) anonymousObject, data);
                        return Unit.INSTANCE;
                    } finally {
                        bodyResolveContext.getContainers().removeLast();
                    }
                }
            });
            return anonymousObject;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirConstructor transformConstructor(final FirConstructor constructor, ResolutionMode data) {
            constructor.getClass();
            data.getClass();
            if (!getHasContractToResolve(constructor)) {
                return constructor;
            }
            BodyResolveContext context = getTransformer().getContext();
            FirTowerDataMode towerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
            FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
            if (towerDataMode == null) {
                try {
                    towerDataMode = context.getTowerDataMode();
                } catch (Throwable th) {
                    context.setTowerDataMode(towerDataMode2);
                    throw th;
                }
            }
            context.setTowerDataMode(towerDataMode);
            context.getContainers().add(constructor);
            try {
                FirConstructor firConstructor = (FirConstructor) getTransformer().getContext().forConstructorBody(constructor, getSession(), new Function0() { // from class: px4
                    public final Object invoke() {
                        return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.transformConstructor$lambda$0$0(this.b, constructor);
                    }
                });
                context.getContainers().removeLast();
                context.setTowerDataMode(towerDataMode2);
                return firConstructor;
            } catch (Throwable th2) {
                context.getContainers().removeLast();
                throw th2;
            }
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirDanglingModifierList transformDanglingModifierList(FirDanglingModifierList danglingModifierList, ResolutionMode data) {
            danglingModifierList.getClass();
            data.getClass();
            return danglingModifierList;
        }

        public void transformDeclarationContent(FirClass firClass, ResolutionMode data) {
            firClass.getClass();
            data.getClass();
            firClass.transformDeclarations(this, data);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirEnumEntry transformEnumEntry(FirEnumEntry enumEntry, ResolutionMode data) {
            enumEntry.getClass();
            data.getClass();
            return enumEntry;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirErrorPrimaryConstructor transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, ResolutionMode data) {
            errorPrimaryConstructor.getClass();
            data.getClass();
            FirConstructor firConstructorTransformConstructor = transformConstructor((FirConstructor) errorPrimaryConstructor, data);
            firConstructorTransformConstructor.getClass();
            return (FirErrorPrimaryConstructor) firConstructorTransformConstructor;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformErrorProperty(FirErrorProperty errorProperty, ResolutionMode data) {
            errorProperty.getClass();
            data.getClass();
            return transformProperty((FirProperty) errorProperty, data);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirField transformField(FirField field, ResolutionMode data) {
            field.getClass();
            data.getClass();
            return field;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirNamedFunction transformNamedFunction(final FirNamedFunction namedFunction, ResolutionMode data) {
            FirFunction publicApiInlineFunction;
            FirNamedFunction firNamedFunction;
            namedFunction.getClass();
            data.getClass();
            if (!getHasContractToResolve(namedFunction)) {
                return namedFunction;
            }
            BodyResolveContext context = getTransformer().getContext();
            FirSession session = getSession();
            if (!(context.getContainerIfAny() instanceof FirClass)) {
                context.storeFunction(namedFunction, session);
            }
            FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(namedFunction) ? FirTowerDataMode.COMPANION_BLOCK : null;
            FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
            if (towerDataMode == null) {
                try {
                    towerDataMode = context.getTowerDataMode();
                } catch (Throwable th) {
                    context.setTowerDataMode(towerDataMode2);
                    throw th;
                }
            }
            context.setTowerDataMode(towerDataMode);
            if (namedFunction.getTypeParameters().isEmpty()) {
                publicApiInlineFunction = context.isPublicInline(namedFunction) ? namedFunction : null;
                if (publicApiInlineFunction == null) {
                    publicApiInlineFunction = context.getPublicApiInlineFunction();
                }
                FirFunction publicApiInlineFunction2 = context.getPublicApiInlineFunction();
                try {
                    context.setPublicApiInlineFunction(publicApiInlineFunction);
                    context.getContainers().add(namedFunction);
                    try {
                        firNamedFunction = (FirNamedFunction) getTransformer().getContext().forFunctionBody(namedFunction, getTransformer().getComponents(), new Function0() { // from class: qx4
                            public final Object invoke() {
                                return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.transformNamedFunction$lambda$0$0(this.b, namedFunction);
                            }
                        });
                        context.getContainers().removeLast();
                        context.setPublicApiInlineFunction(publicApiInlineFunction2);
                    } catch (Throwable th2) {
                        context.getContainers().removeLast();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    context.setPublicApiInlineFunction(publicApiInlineFunction2);
                    throw th3;
                }
            } else {
                FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(namedFunction);
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                    publicApiInlineFunction = context.isPublicInline(namedFunction) ? namedFunction : null;
                    if (publicApiInlineFunction == null) {
                        publicApiInlineFunction = context.getPublicApiInlineFunction();
                    }
                    FirFunction publicApiInlineFunction3 = context.getPublicApiInlineFunction();
                    try {
                        context.setPublicApiInlineFunction(publicApiInlineFunction);
                        context.getContainers().add(namedFunction);
                        try {
                            firNamedFunction = (FirNamedFunction) getTransformer().getContext().forFunctionBody(namedFunction, getTransformer().getComponents(), new Function0() { // from class: qx4
                                public final Object invoke() {
                                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.transformNamedFunction$lambda$0$0(this.b, namedFunction);
                                }
                            });
                            context.getContainers().removeLast();
                            context.setPublicApiInlineFunction(publicApiInlineFunction3);
                            context.replaceTowerDataContext(towerDataContext);
                        } catch (Throwable th4) {
                            context.getContainers().removeLast();
                            throw th4;
                        }
                    } catch (Throwable th5) {
                        context.setPublicApiInlineFunction(publicApiInlineFunction3);
                        throw th5;
                    }
                } catch (Throwable th6) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th6;
                }
            }
            context.setTowerDataMode(towerDataMode2);
            return firNamedFunction;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirProperty transformProperty(FirProperty property, ResolutionMode data) {
            FirPropertyAccessor setter;
            property.getClass();
            data.getClass();
            FirPropertyAccessor getter = property.getGetter();
            if (((getter == null || !getHasContractToResolve(getter)) && ((setter = property.getSetter()) == null || !getHasContractToResolve(setter))) || (property.getSymbol() instanceof FirLocalPropertySymbol) || property.getDelegate() != null) {
                return property;
            }
            if (property instanceof FirSyntheticProperty) {
                transformNamedFunction(((FirSyntheticProperty) property).getGetter().getDelegate(), data);
                return property;
            }
            BodyResolveContext context = getTransformer().getContext();
            FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(property) ? FirTowerDataMode.COMPANION_BLOCK : null;
            FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
            if (towerDataMode == null) {
                try {
                    towerDataMode = context.getTowerDataMode();
                } catch (Throwable th) {
                    context.setTowerDataMode(towerDataMode2);
                    throw th;
                }
            }
            context.setTowerDataMode(towerDataMode);
            if (property.getTypeParameters().isEmpty()) {
                context.getContainers().add(property);
                try {
                    FirPropertyAccessor getter2 = property.getGetter();
                    if (getter2 != null) {
                        transformPropertyAccessor(getter2, property);
                    }
                    FirPropertyAccessor setter2 = property.getSetter();
                    if (setter2 != null) {
                        transformPropertyAccessor(setter2, property);
                    }
                    context.getContainers().removeLast();
                } catch (Throwable th2) {
                    context.getContainers().removeLast();
                    throw th2;
                }
            } else {
                FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(property);
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                    context.getContainers().add(property);
                    try {
                        FirPropertyAccessor getter3 = property.getGetter();
                        if (getter3 != null) {
                            transformPropertyAccessor(getter3, property);
                        }
                        FirPropertyAccessor setter3 = property.getSetter();
                        if (setter3 != null) {
                            transformPropertyAccessor(setter3, property);
                        }
                        context.getContainers().removeLast();
                        context.replaceTowerDataContext(towerDataContext);
                    } catch (Throwable th3) {
                        context.getContainers().removeLast();
                        throw th3;
                    }
                } catch (Throwable th4) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th4;
                }
            }
            context.setTowerDataMode(towerDataMode2);
            return property;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirRegularClass transformRegularClass(final FirRegularClass regularClass, final ResolutionMode data) {
            regularClass.getClass();
            data.getClass();
            return forRegularClassBody(regularClass, new Function0() { // from class: kx4
                public final Object invoke() {
                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.r(this.b, regularClass, data);
                }
            });
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, ResolutionMode data) {
            replSnippet.getClass();
            data.getClass();
            return replSnippet;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirScript transformScript(final FirScript script, final ResolutionMode data) {
            script.getClass();
            data.getClass();
            return withScript(script, new Function0() { // from class: lx4
                public final Object invoke() {
                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.w(this.b, script, data);
                }
            });
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirTypeAlias transformTypeAlias(FirTypeAlias typeAlias, ResolutionMode data) {
            typeAlias.getClass();
            data.getClass();
            return typeAlias;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer
        public FirFile withFile(FirFile file, Function0<? extends FirFile> action) {
            file.getClass();
            action.getClass();
            BodyResolveContext context = getTransformer().getContext();
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getTransformer().getComponents();
            context.clear();
            context.setFile(file);
            List<FirScope> fileImportsScope = context.getFileImportsScope();
            int size = fileImportsScope.size();
            int i = 0;
            try {
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    List listCreateImportingScopes$default = ImportingScopesKt.createImportingScopes$default(file, components.getSession(), components.getScopeSession(), false, 8, null);
                    CollectionsKt.addAll(context.getFileImportsScope(), listCreateImportingScopes$default);
                    List list = listCreateImportingScopes$default;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(ImplicitReceiverUtilsKt.asTowerDataElement((FirScope) it.next(), false));
                    }
                    context.addNonLocalTowerDataElements(arrayList);
                    context.getContainers().add(file);
                    try {
                        FirFile firFile = (FirFile) action.invoke();
                        context.getContainers().removeLast();
                        context.replaceTowerDataContext(towerDataContext);
                        int size2 = fileImportsScope.size() - size;
                        while (i < size2) {
                            fileImportsScope.remove(fileImportsScope.size() - 1);
                            i++;
                        }
                        return firFile;
                    } catch (Throwable th) {
                        context.getContainers().removeLast();
                        throw th;
                    }
                } catch (Throwable th2) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th2;
                }
            } catch (Throwable th3) {
                int size3 = fileImportsScope.size() - size;
                while (i < size3) {
                    fileImportsScope.remove(fileImportsScope.size() - 1);
                    i++;
                }
                throw th3;
            }
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer
        public FirScript withScript(FirScript script, final Function0<? extends FirScript> action) {
            script.getClass();
            action.getClass();
            return (FirScript) getTransformer().getContext().withScript(script, getTransformer().getComponents(), new Function0() { // from class: tx4
                public final Object invoke() {
                    return FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer.p(action);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractContractResolveTransformerDispatcher(FirSession firSession, ScopeSession scopeSession, BodyResolveContext bodyResolveContext) {
        super(firSession, FirResolvePhase.CONTRACTS, false, scopeSession, ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getContract(), bodyResolveContext, true);
        firSession.getClass();
        scopeSession.getClass();
        this.expressionsTransformer = new FirExpressionsResolveTransformer(this);
        this.regularDeclarationsTransformer = new FirDeclarationsResolveTransformer(this);
        this.contractMode = true;
    }

    public abstract FirDeclarationsContractResolveTransformer getContractDeclarationsTransformer();

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public final FirDeclarationsResolveTransformer getDeclarationsTransformer() {
        return this.contractMode ? getContractDeclarationsTransformer() : this.regularDeclarationsTransformer;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public final FirExpressionsResolveTransformer getExpressionsTransformer() {
        return this.expressionsTransformer;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotation(FirAnnotation annotation, ResolutionMode data) {
        annotation.getClass();
        data.getClass();
        return annotation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, ResolutionMode data) {
        annotationCall.getClass();
        data.getClass();
        return annotationCall;
    }

    public /* synthetic */ FirAbstractContractResolveTransformerDispatcher(FirSession firSession, ScopeSession scopeSession, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, (i & 4) != 0 ? null : bodyResolveContext);
    }
}
