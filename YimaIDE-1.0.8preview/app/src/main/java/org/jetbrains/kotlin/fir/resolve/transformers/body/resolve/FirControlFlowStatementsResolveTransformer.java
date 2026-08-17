package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirTargetElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionModeKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSyntheticCallGenerator;
import org.jetbrains.kotlin.fir.resolve.transformers.FirWhenExhaustivenessComputer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirControlFlowStatementsResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.RefinedTypeForDataFlowTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\f\u0010\u0016\u001a\u00020\u0017*\u00020\u0015H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J(\u0010$\u001a\u00020\u000b\"\b\b\u0000\u0010%*\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H%0(2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0014\u00102\u001a\u00020\u0017*\u0002032\u0006\u00104\u001a\u000205H\u0002J\u0010\u00106\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0014\u00107\u001a\u000205*\u0002052\u0006\u00108\u001a\u000209H\u0002R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirControlFlowStatementsResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirPartialBodyResolveTransformer;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;)V", "syntheticCallGenerator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", "getSyntheticCallGenerator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", "transformWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "transformWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "isOneBranch", Argument.Delimiters.none, "transformWhenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "whenBranch", "transformWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "transformTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "transformCatch", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "catch", "transformJump", "E", "Lorg/jetbrains/kotlin/fir/FirTargetElement;", "jump", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "transformReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "transformThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "transformElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "isFlexibleWithNotNullable", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "rhsResolvedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "computeResolutionModeForElvisLHS", "makeConeFlexibleTypeWithNotNullableLowerBound", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirControlFlowStatementsResolveTransformer extends FirPartialBodyResolveTransformer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirControlFlowStatementsResolveTransformer(FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher) {
        super(firAbstractBodyResolveTransformerDispatcher);
        firAbstractBodyResolveTransformerDispatcher.getClass();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x007c  */
    public static FirWhenExpression b(FirWhenExpression firWhenExpression, FirControlFlowStatementsResolveTransformer firControlFlowStatementsResolveTransformer, ResolutionMode resolutionMode) {
        ResolutionMode resolutionModeCopy$default;
        FirAbstractBodyResolveTransformerDispatcher transformer = firControlFlowStatementsResolveTransformer.getTransformer();
        ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
        FirWhenExpression firWhenExpressionTransformSubjectVariable = firWhenExpression.transformSubjectVariable(transformer, contextIndependent);
        boolean z = false;
        if (firWhenExpressionTransformSubjectVariable.getBranches().isEmpty()) {
            firWhenExpressionTransformSubjectVariable.replaceConeTypeOrNull(firControlFlowStatementsResolveTransformer.getSession().getBuiltinTypes().getUnitType().getConeType());
        } else if (firControlFlowStatementsResolveTransformer.isOneBranch(firWhenExpressionTransformSubjectVariable) && resolutionMode.getForceFullCompletion() && !(resolutionMode instanceof ResolutionMode.WithExpectedType)) {
            firWhenExpressionTransformSubjectVariable = firWhenExpressionTransformSubjectVariable.transformBranches(firControlFlowStatementsResolveTransformer.getTransformer(), contextIndependent);
            firWhenExpressionTransformSubjectVariable.replaceConeTypeOrNull(FirTypeUtilsKt.getResolvedType(((FirWhenBranch) CollectionsKt.first(firWhenExpressionTransformSubjectVariable.getBranches())).getResult()));
        } else {
            ResolutionMode.WithExpectedType withExpectedType = resolutionMode instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) resolutionMode : null;
            if (withExpectedType == null) {
                resolutionModeCopy$default = ResolutionMode.ContextDependent.INSTANCE;
            } else {
                ResolutionMode.WithExpectedType withExpectedType2 = !withExpectedType.getFromCast() ? withExpectedType : null;
                if (withExpectedType2 == null || (resolutionModeCopy$default = ResolutionMode.WithExpectedType.copy$default(withExpectedType2, null, false, false, 3, null)) == null) {
                    resolutionModeCopy$default = ResolutionMode.ContextDependent.INSTANCE;
                }
            }
            firWhenExpressionTransformSubjectVariable = firControlFlowStatementsResolveTransformer.getSyntheticCallGenerator().generateCalleeForWhenExpression(firWhenExpressionTransformSubjectVariable.transformBranches(firControlFlowStatementsResolveTransformer.getTransformer(), resolutionModeCopy$default), firControlFlowStatementsResolveTransformer.getTransformer().getResolutionContext(), resolutionMode);
            z = true;
        }
        FirWhenExpression firWhenExpression2 = firWhenExpressionTransformSubjectVariable;
        firWhenExpression2.replaceExhaustivenessStatus(FirWhenExhaustivenessComputer.INSTANCE.computeExhaustivenessStatus(firControlFlowStatementsResolveTransformer, firWhenExpression2, firControlFlowStatementsResolveTransformer.getTransformer().getContext().getFile()));
        if (z) {
            firWhenExpression2 = (FirWhenExpression) FirCallCompleter.completeCall$default(firControlFlowStatementsResolveTransformer.getComponents().getCallCompleter(), firWhenExpression2, ExhaustivenessStatusKt.isProperlyExhaustive(firWhenExpression2) ? resolutionMode : contextIndependent, false, 4, null);
        }
        firControlFlowStatementsResolveTransformer.getComponents().getDataFlowAnalyzer().exitWhenExpression(firWhenExpression2, resolutionMode.getForceFullCompletion());
        BodyResolveUtilsKt.replaceReturnTypeIfNotExhaustive(firWhenExpression2, firControlFlowStatementsResolveTransformer.getSession());
        return firWhenExpression2;
    }

    private final ResolutionMode computeResolutionModeForElvisLHS(ResolutionMode data) {
        ConeKotlinType expectedType = ResolutionModeKt.getExpectedType(data);
        ResolutionMode.WithExpectedType withExpectedType = data instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) data : null;
        boolean z = withExpectedType != null && withExpectedType.getLastStatementInBlock();
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.ElvisInferenceImprovementsIn21);
        if (z && expectedType != null && TypeUtilsKt.isUnitOrFlexibleUnit(expectedType)) {
            return !zIsEnabled ? ResolutionModeKt.withExpectedType(expectedType, true) : ResolutionMode.ContextDependent.INSTANCE;
        }
        return ResolutionModeKt.withExpectedTypeNullable$default(expectedType != null ? TypeUtilsKt.withNullability$default(expectedType, true, TypeComponentsKt.getTypeContext(getSession()), null, false, 12, null) : null, false, 2, null);
    }

    private final FirSyntheticCallGenerator getSyntheticCallGenerator() {
        return getTransformer().getComponents().getSyntheticCallGenerator();
    }

    private final boolean isFlexibleWithNotNullable(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType) {
        return (coneKotlinType instanceof ConeFlexibleType) && !coneInferenceContext.isNullableType(((ConeFlexibleType) coneKotlinType).getLowerBound());
    }

    private final boolean isOneBranch(FirWhenExpression firWhenExpression) {
        if (firWhenExpression.getBranches().size() == 1) {
            return true;
        }
        if (firWhenExpression.getBranches().size() > 2) {
            return false;
        }
        FirWhenBranch firWhenBranch = (FirWhenBranch) CollectionsKt.last(firWhenExpression.getBranches());
        return firWhenBranch.getSource() != null && (firWhenBranch.getCondition() instanceof FirElseIfTrueCondition) && (firWhenBranch.getResult() instanceof FirEmptyExpressionBlock);
    }

    private final ConeKotlinType makeConeFlexibleTypeWithNotNullableLowerBound(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext) {
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            k2d.a("It can't happen because of the previous `isNullableType` check");
            return null;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            if (!coneTypeContext.isNullableType(coneFlexibleType.getLowerBound())) {
                return coneFlexibleType;
            }
            ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull((ConeKotlinType) coneFlexibleType.getLowerBound(), coneTypeContext, false, false);
            coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull.getClass();
            return new ConeFlexibleType((ConeRigidType) coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull, coneFlexibleType.getUpperBound(), false);
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                arrayList.add(makeConeFlexibleTypeWithNotNullableLowerBound((ConeKotlinType) it.next(), coneTypeContext));
            }
            return new ConeIntersectionType(arrayList, null, 2, null);
        }
        if (!(coneKotlinType instanceof ConeRigidType)) {
            bu8.a();
            return null;
        }
        ConeRigidType coneRigidType = (ConeRigidType) coneKotlinType;
        ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull2 = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull((ConeKotlinType) coneRigidType, coneTypeContext, false, false);
        coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull2.getClass();
        return new ConeFlexibleType((ConeRigidType) coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull2, coneRigidType, false);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirCatch transformCatch(FirCatch firCatch, ResolutionMode data) {
        firCatch.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterCatchClause(firCatch);
        FirProperty parameter = firCatch.getParameter();
        FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
        ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
        parameter.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
        BodyResolveContext context = getTransformer().getContext();
        FirSession session = getSession();
        FirTowerDataContext towerDataContext = context.getTowerDataContext();
        try {
            context.addLocalScope(new FirLocalScope(session));
            firCatch.transformParameter(getTransformer(), contextIndependent);
            FirCatch firCatchTransformBlock = firCatch.transformBlock(getTransformer(), ResolutionMode.ContextDependent.INSTANCE);
            context.replaceTowerDataContext(towerDataContext);
            getComponents().getDataFlowAnalyzer().exitCatchClause(firCatchTransformBlock);
            return firCatchTransformBlock;
        } catch (Throwable th) {
            context.replaceTowerDataContext(towerDataContext);
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformDoWhileLoop(FirDoWhileLoop doWhileLoop, ResolutionMode data) {
        doWhileLoop.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirSession session = getSession();
        FirTowerDataContext towerDataContext = context.getTowerDataContext();
        try {
            context.addLocalScope(new FirLocalScope(session));
            getComponents().getDataFlowAnalyzer().enterDoWhileLoop(doWhileLoop);
            FirExpressionsResolveTransformer expressionsTransformer = getTransformer().getExpressionsTransformer();
            if (expressionsTransformer != null) {
                expressionsTransformer.transformBlockInCurrentScope$org_jetbrains_kotlin_resolve(doWhileLoop.getBlock(), ResolutionMode.ContextIndependent.INSTANCE);
            }
            getComponents().getDataFlowAnalyzer().enterDoWhileLoopCondition(doWhileLoop);
            FirDoWhileLoop firDoWhileLoopTransformCondition = doWhileLoop.transformCondition((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(getSession().getBuiltinTypes().getBooleanType(), null, null, 6, null));
            getComponents().getDataFlowAnalyzer().exitDoWhileLoop(firDoWhileLoopTransformCondition);
            FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
            ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
            return firDoWhileLoopTransformCondition.transformLabel((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent).transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent);
        } finally {
            context.replaceTowerDataContext(towerDataContext);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformElvisExpression(FirElvisExpression elvisExpression, ResolutionMode data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ResolutionMode resolutionMode = data;
        elvisExpression.getClass();
        resolutionMode.getClass();
        if (elvisExpression.getCalleeReference() instanceof FirResolvedNamedReference) {
            return elvisExpression;
        }
        elvisExpression.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), resolutionMode);
        if ((resolutionMode instanceof ResolutionMode.WithExpectedType) && !resolutionMode.getForceFullCompletion()) {
            resolutionMode = null;
        }
        if (resolutionMode == null) {
            resolutionMode = ResolutionMode.ContextDependent.INSTANCE;
        }
        ResolutionMode resolutionMode2 = resolutionMode;
        getComponents().getDataFlowAnalyzer().enterElvis(elvisExpression);
        elvisExpression.transformLhs(getTransformer(), computeResolutionModeForElvisLHS(resolutionMode2));
        getComponents().getDataFlowAnalyzer().exitElvisLhs(elvisExpression);
        FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
        ConeKotlinType expectedType = ResolutionModeKt.getExpectedType(resolutionMode2);
        ResolutionMode.WithExpectedType withExpectedType = resolutionMode2 instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) resolutionMode2 : null;
        boolean z = false;
        elvisExpression.transformRhs(transformer, ResolutionModeKt.withExpectedTypeNullable(expectedType, withExpectedType != null && withExpectedType.getLastStatementInBlock()));
        FirElvisExpression firElvisExpression = (FirElvisExpression) FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), getSyntheticCallGenerator().generateCalleeForElvisExpression(elvisExpression, getTransformer().getResolutionContext(), resolutionMode2), resolutionMode2, false, 4, null);
        ConeKotlinType coneTypeOrNull = firElvisExpression.getRhs().getConeTypeOrNull();
        if (coneTypeOrNull != null && ConeBuiltinTypeUtilsKt.isNothing(coneTypeOrNull)) {
            firElvisExpression.replaceConeTypeOrNull(TypeUtilsKt.convertToNonRawVersion(TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(FirTypeUtilsKt.getResolvedType(firElvisExpression.getLhs()), (ConeTypeContext) TypeComponentsKt.getTypeContext(getSession()), false, false, 6, (Object) null)));
            getTransformer().getContext().getInferenceSession().updateExpressionReturnTypeWithCurrentSubstitutorInPCLA(firElvisExpression, resolutionMode2);
            z = true;
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firElvisExpression);
        if (!(resolvedType instanceof ConeTypeVariableType) && typeContext.isNullableType(resolvedType)) {
            ConeKotlinType resolvedType2 = FirTypeUtilsKt.getResolvedType(firElvisExpression.getRhs());
            if (!typeContext.isNullableType(RefinedTypeForDataFlowTypeAttributeKt.getRefinedTypeForDataFlowOrSelf(resolvedType2))) {
                firElvisExpression.replaceConeTypeOrNull(TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(FirTypeUtilsKt.getResolvedType(firElvisExpression), (ConeTypeContext) TypeComponentsKt.getTypeContext(typeContext.getSession()), false, false, 6, (Object) null));
            } else if (isFlexibleWithNotNullable(typeContext, RefinedTypeForDataFlowTypeAttributeKt.getRefinedTypeForDataFlowOrSelf(resolvedType2))) {
                firElvisExpression.replaceConeTypeOrNull(makeConeFlexibleTypeWithNotNullableLowerBound(FirTypeUtilsKt.getResolvedType(firElvisExpression), TypeComponentsKt.getTypeContext(typeContext.getSession())));
            }
        }
        getComponents().getDataFlowAnalyzer().exitElvis(elvisExpression, z, resolutionMode2.getForceFullCompletion());
        return firElvisExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirTargetElement> FirStatement transformJump(FirJump<E> jump, ResolutionMode data) {
        jump.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterJump(jump);
        FirStatement firStatementTransformExpression = getTransformer().transformExpression((FirExpression) jump, data);
        getComponents().getDataFlowAnalyzer().exitJump(jump);
        return firStatementTransformExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReturnExpression(FirReturnExpression returnExpression, ResolutionMode data) {
        ResolutionMode withExpectedType;
        returnExpression.getClass();
        data.getClass();
        FirFunction firFunction = (FirFunction) returnExpression.getTarget().getLabeledElement();
        FirResolvedTypeRef returnTypeRef = firFunction.getReturnTypeRef();
        if (getTransformer().getContext().getAnonymousFunctionsAnalyzedInDependentContext().contains(firFunction.getSymbol())) {
            withExpectedType = ResolutionMode.ContextDependent.INSTANCE;
        } else {
            withExpectedType = returnTypeRef instanceof FirResolvedTypeRef ? new ResolutionMode.WithExpectedType(returnTypeRef, false, false, null, null, false, 62, null) : ResolutionMode.ContextIndependent.INSTANCE;
        }
        return transformJump((FirJump) returnExpression, withExpectedType);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformThrowExpression(FirThrowExpression throwExpression, ResolutionMode data) {
        throwExpression.getClass();
        data.getClass();
        throwExpression.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
        throwExpression.transformException(getTransformer(), ResolutionModeKt.withExpectedType$default(getSession().getBuiltinTypes().getThrowableType(), null, null, 6, null));
        getComponents().getDataFlowAnalyzer().exitThrowExceptionNode(throwExpression);
        return throwExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTryExpression(FirTryExpression tryExpression, ResolutionMode data) {
        tryExpression.getClass();
        data.getClass();
        if ((tryExpression.getCalleeReference() instanceof FirResolvedNamedReference) && FirTypeUtilsKt.getHasResolvedType(tryExpression)) {
            return tryExpression;
        }
        FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
        ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
        tryExpression.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
        getComponents().getDataFlowAnalyzer().enterTryExpression(tryExpression);
        FirAbstractBodyResolveTransformerDispatcher transformer2 = getTransformer();
        ResolutionMode.ContextDependent.Companion companion = ResolutionMode.ContextDependent.INSTANCE;
        tryExpression.transformTryBlock(transformer2, companion);
        getComponents().getDataFlowAnalyzer().exitTryMainBlock();
        tryExpression.transformCatches(this, companion);
        FirTryExpression firTryExpressionTransformFinallyBlock = (FirTryExpression) FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), getSyntheticCallGenerator().generateCalleeForTryExpression(tryExpression, getTransformer().getResolutionContext(), data), data, false, 4, null);
        if (firTryExpressionTransformFinallyBlock.getFinallyBlock() != null) {
            getComponents().getDataFlowAnalyzer().enterFinallyBlock();
            firTryExpressionTransformFinallyBlock = firTryExpressionTransformFinallyBlock.transformFinallyBlock(getTransformer(), contextIndependent);
            getComponents().getDataFlowAnalyzer().exitFinallyBlock();
        }
        getComponents().getDataFlowAnalyzer().exitTryExpression(data.getForceFullCompletion());
        return firTryExpressionTransformFinallyBlock;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirWhenBranch transformWhenBranch(FirWhenBranch whenBranch, ResolutionMode data) {
        whenBranch.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterWhenBranchCondition(whenBranch);
        FirWhenBranch firWhenBranchTransformCondition = whenBranch.transformCondition(getTransformer(), ResolutionModeKt.withExpectedType$default(getSession().getBuiltinTypes().getBooleanType(), null, null, 6, null));
        getComponents().getDataFlowAnalyzer().exitWhenBranchCondition(firWhenBranchTransformCondition);
        FirWhenBranch firWhenBranchTransformResult = firWhenBranchTransformCondition.transformResult(getTransformer(), data);
        getComponents().getDataFlowAnalyzer().exitWhenBranchResult(firWhenBranchTransformResult);
        return firWhenBranchTransformResult;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhenExpression(final FirWhenExpression whenExpression, final ResolutionMode data) {
        whenExpression.getClass();
        data.getClass();
        if ((whenExpression.getCalleeReference() instanceof FirResolvedNamedReference) && FirTypeUtilsKt.getHasResolvedType(whenExpression)) {
            return whenExpression;
        }
        Iterator<T> it = whenExpression.getAnnotations().iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(this, data);
        }
        getComponents().getDataFlowAnalyzer().enterWhenExpression(whenExpression);
        return (FirStatement) getTransformer().getContext().withWhenExpression(whenExpression, getSession(), new Function0() { // from class: f05
            public final Object invoke() {
                return FirControlFlowStatementsResolveTransformer.b(whenExpression, this, data);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression, ResolutionMode data) {
        whenSubjectExpression.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().exitWhenSubjectExpression(whenSubjectExpression);
        return ResolveUtilsKt.transformExpressionUsingSmartcastInfo(getTransformer().getComponents(), whenSubjectExpression);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhileLoop(FirWhileLoop whileLoop, ResolutionMode data) {
        whileLoop.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterWhileLoop(whileLoop);
        FirWhileLoop firWhileLoopTransformCondition = whileLoop.transformCondition((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(getSession().getBuiltinTypes().getBooleanType(), null, null, 6, null));
        getComponents().getDataFlowAnalyzer().exitWhileLoopCondition(firWhileLoopTransformCondition);
        FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
        ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
        FirWhileLoop firWhileLoopTransformBlock = firWhileLoopTransformCondition.transformBlock((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
        getComponents().getDataFlowAnalyzer().exitWhileLoop(firWhileLoopTransformBlock);
        return firWhileLoopTransformBlock.transformLabel((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent).transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent);
    }
}
