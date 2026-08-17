package org.jetbrains.kotlin.contracts.parsing;

import com.intellij.psi.tree.IElementType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.BooleanExpression;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanVariableReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ContractDescriptionValue;
import org.jetbrains.kotlin.contracts.description.expressions.IsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.expressions.IsNullPredicate;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalAnd;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalNot;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalOr;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ValueDescriptor;
import org.jetbrains.kotlin.descriptors.impl.AbstractTypeParameterDescriptor;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtIsExpression;
import org.jetbrains.kotlin.psi.KtParenthesizedExpression;
import org.jetbrains.kotlin.psi.KtPsiUtil;
import org.jetbrains.kotlin.psi.KtUnaryExpression;
import org.jetbrains.kotlin.psi.KtVisitor;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver;
import org.jetbrains.kotlin.types.CastDiagnosticsUtil;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.UnwrappedType;
import org.jetbrains.kotlin.types.checker.KotlinTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0014J!\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0017J!\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u001aJ\u001f\u0010\u001b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u001cH\u0002J*\u0010\u001f\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0013H\u0002J\u001f\u0010&\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020'2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010(J\u001f\u0010)\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020*2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010+R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/PsiConditionParser;", "Lorg/jetbrains/kotlin/psi/KtVisitor;", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", Argument.Delimiters.none, "collector", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "dispatcher", "Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;)V", "visitIsExpression", "expression", "Lorg/jetbrains/kotlin/psi/KtIsExpression;", "data", "(Lorg/jetbrains/kotlin/psi/KtIsExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "visitKtElement", "element", "Lorg/jetbrains/kotlin/psi/KtElement;", "(Lorg/jetbrains/kotlin/psi/KtElement;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "visitConstantExpression", "Lorg/jetbrains/kotlin/psi/KtConstantExpression;", "(Lorg/jetbrains/kotlin/psi/KtConstantExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "visitCallExpression", "Lorg/jetbrains/kotlin/psi/KtCallExpression;", "(Lorg/jetbrains/kotlin/psi/KtCallExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "visitBinaryExpression", "Lorg/jetbrains/kotlin/psi/KtBinaryExpression;", "(Lorg/jetbrains/kotlin/psi/KtBinaryExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "parseIdentityEquals", "processEquals", "left", "Lorg/jetbrains/kotlin/contracts/description/expressions/ContractDescriptionValue;", "right", "isNegated", Argument.Delimiters.none, "reportOn", "visitUnaryExpression", "Lorg/jetbrains/kotlin/psi/KtUnaryExpression;", "(Lorg/jetbrains/kotlin/psi/KtUnaryExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "visitParenthesizedExpression", "Lorg/jetbrains/kotlin/psi/KtParenthesizedExpression;", "(Lorg/jetbrains/kotlin/psi/KtParenthesizedExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiConditionParser extends KtVisitor<BooleanExpression, Unit> {
    private final ContractCallContext callContext;
    private final ContractParsingDiagnosticsCollector collector;
    private final PsiContractParserDispatcher dispatcher;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.contracts.parsing.PsiConditionParser$visitBinaryExpression$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<BooleanExpression, BooleanExpression, LogicalAnd> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, LogicalAnd.class, "<init>", "<init>(Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;)V", 0);
        }

        public final LogicalAnd invoke(BooleanExpression booleanExpression, BooleanExpression booleanExpression2) {
            booleanExpression.getClass();
            booleanExpression2.getClass();
            return new LogicalAnd(booleanExpression, booleanExpression2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.contracts.parsing.PsiConditionParser$visitBinaryExpression$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<BooleanExpression, BooleanExpression, LogicalOr> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(2, LogicalOr.class, "<init>", "<init>(Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;)V", 0);
        }

        public final LogicalOr invoke(BooleanExpression booleanExpression, BooleanExpression booleanExpression2) {
            booleanExpression.getClass();
            booleanExpression2.getClass();
            return new LogicalOr(booleanExpression, booleanExpression2);
        }
    }

    public PsiConditionParser(ContractParsingDiagnosticsCollector contractParsingDiagnosticsCollector, ContractCallContext contractCallContext, PsiContractParserDispatcher psiContractParserDispatcher) {
        contractParsingDiagnosticsCollector.getClass();
        contractCallContext.getClass();
        psiContractParserDispatcher.getClass();
        this.collector = contractParsingDiagnosticsCollector;
        this.callContext = contractCallContext;
        this.dispatcher = psiContractParserDispatcher;
    }

    private final BooleanExpression parseIdentityEquals(KtBinaryExpression expression) {
        ContractDescriptionValue value;
        ContractDescriptionValue value2 = this.dispatcher.parseValue(expression.getLeft());
        if (value2 == null || (value = this.dispatcher.parseValue(expression.getRight())) == null) {
            return null;
        }
        return processEquals(value2, value, Intrinsics.areEqual(expression.getOperationToken(), KtTokens.EXCLEQEQEQ), expression);
    }

    private final BooleanExpression processEquals(ContractDescriptionValue left, ContractDescriptionValue right, boolean isNegated, KtElement reportOn) {
        if ((left instanceof ConstantReference) && Intrinsics.areEqual(left, ConstantReference.INSTANCE.getNULL()) && (right instanceof VariableReference)) {
            return new IsNullPredicate((VariableReference) right, isNegated);
        }
        if ((right instanceof ConstantReference) && Intrinsics.areEqual(right, ConstantReference.INSTANCE.getNULL()) && (left instanceof VariableReference)) {
            return new IsNullPredicate((VariableReference) left, isNegated);
        }
        this.collector.badDescription("only equality comparisons with 'null' allowed", reportOn);
        return null;
    }

    public BooleanExpression visitBinaryExpression(KtBinaryExpression expression, Unit data) {
        Function2 function2;
        BooleanExpression booleanExpression;
        KtExpression right;
        BooleanExpression booleanExpression2;
        expression.getClass();
        data.getClass();
        IElementType operationToken = expression.getOperationToken();
        if (Intrinsics.areEqual(operationToken, KtTokens.ANDAND)) {
            function2 = AnonymousClass1.INSTANCE;
        } else {
            if (!Intrinsics.areEqual(operationToken, KtTokens.OROR)) {
                return (Intrinsics.areEqual(operationToken, KtTokens.EXCLEQEQEQ) || Intrinsics.areEqual(operationToken, KtTokens.EQEQEQ)) ? parseIdentityEquals(expression) : (BooleanExpression) super.visitBinaryExpression(expression, data);
            }
            function2 = AnonymousClass2.INSTANCE;
        }
        KtExpression left = expression.getLeft();
        if (left == null || (booleanExpression = (BooleanExpression) left.accept(this, data)) == null || (right = expression.getRight()) == null || (booleanExpression2 = (BooleanExpression) right.accept(this, data)) == null) {
            return null;
        }
        return (BooleanExpression) function2.invoke(booleanExpression, booleanExpression2);
    }

    public BooleanExpression visitCallExpression(KtCallExpression expression, Unit data) {
        expression.getClass();
        this.collector.badDescription("call-expressions are not supported yet", expression);
        return null;
    }

    public BooleanExpression visitConstantExpression(KtConstantExpression expression, Unit data) {
        expression.getClass();
        ConstantReference constant = this.dispatcher.parseConstant(expression);
        if (constant instanceof BooleanConstantReference) {
            return (BooleanConstantReference) constant;
        }
        return null;
    }

    public BooleanExpression visitIsExpression(KtIsExpression expression, Unit data) {
        KtElement typeReference;
        KotlinType kotlinType;
        UnwrappedType unwrappedTypeUnwrap;
        boolean zSupportsFeature;
        expression.getClass();
        data.getClass();
        VariableReference variable = this.dispatcher.parseVariable(expression.getLeftHandSide());
        if (variable == null || (typeReference = expression.getTypeReference()) == null || (kotlinType = (KotlinType) this.callContext.getBindingContext().get(BindingContext.TYPE, typeReference)) == null || (unwrappedTypeUnwrap = kotlinType.unwrap()) == null) {
            return null;
        }
        AbstractTypeParameterDescriptor declarationDescriptor = unwrappedTypeUnwrap.getConstructor().getDeclarationDescriptor();
        if (unwrappedTypeUnwrap instanceof CapturedType) {
            this.collector.badDescription("references to captured types are forbidden in contracts", typeReference);
            return null;
        }
        if ((declarationDescriptor instanceof AbstractTypeParameterDescriptor) && (!(zSupportsFeature = this.callContext.getLanguageVersionSettings().supportsFeature(LanguageFeature.AllowReifiedGenericsInContracts)) || !declarationDescriptor.isReified())) {
            this.collector.badDescription(zSupportsFeature ? "references to not reified type parameters are forbidden in contracts" : "references to type parameters are forbidden in contracts", typeReference);
            return null;
        }
        KotlinType type = variable.getDescriptor().getType();
        type.getClass();
        KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
        kotlinTypeChecker.getClass();
        if (CastDiagnosticsUtil.isCastErased(type, unwrappedTypeUnwrap, kotlinTypeChecker)) {
            return null;
        }
        return new IsInstancePredicate(variable, unwrappedTypeUnwrap, expression.isNegated());
    }

    public BooleanExpression visitKtElement(KtElement element, Unit data) {
        CallableDescriptor resultingDescriptor;
        ContractDescriptionValue value;
        element.getClass();
        data.getClass();
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(element, this.callContext.getBindingContext());
        if (resolvedCall != null && (resultingDescriptor = resolvedCall.getResultingDescriptor()) != null) {
            if (resultingDescriptor instanceof ValueDescriptor) {
                VariableReference variable = this.dispatcher.parseVariable(element instanceof KtExpression ? (KtExpression) element : null);
                if (variable != null && (variable instanceof BooleanVariableReference)) {
                    return (BooleanVariableReference) variable;
                }
                return null;
            }
            if (PsiContractsUtilsKt.isEqualsDescriptor(resultingDescriptor)) {
                PsiContractParserDispatcher psiContractParserDispatcher = this.dispatcher;
                ExpressionReceiver dispatchReceiver = resolvedCall.getDispatchReceiver();
                ExpressionReceiver expressionReceiver = dispatchReceiver instanceof ExpressionReceiver ? dispatchReceiver : null;
                ContractDescriptionValue value2 = psiContractParserDispatcher.parseValue(expressionReceiver != null ? expressionReceiver.getExpression() : null);
                if (value2 == null || (value = this.dispatcher.parseValue(PsiContractsUtilsKt.firstArgumentAsExpressionOrNull(resolvedCall))) == null) {
                    return null;
                }
                KtBinaryExpression ktBinaryExpression = element instanceof KtBinaryExpression ? (KtBinaryExpression) element : null;
                IElementType operationToken = ktBinaryExpression != null ? ktBinaryExpression.getOperationToken() : null;
                Boolean bool = KtTokens.EXCLEQ;
                if (bool == null) {
                    bool = Boolean.FALSE;
                }
                return processEquals(value2, value, Intrinsics.areEqual(operationToken, bool), element);
            }
            this.collector.badDescription("unsupported construction", element);
        }
        return null;
    }

    public BooleanExpression visitParenthesizedExpression(KtParenthesizedExpression expression, Unit data) {
        expression.getClass();
        data.getClass();
        KtExpression ktExpressionDeparenthesize = KtPsiUtil.deparenthesize(expression);
        if (ktExpressionDeparenthesize != null) {
            return (BooleanExpression) ktExpressionDeparenthesize.accept(this, data);
        }
        return null;
    }

    public BooleanExpression visitUnaryExpression(KtUnaryExpression expression, Unit data) {
        BooleanExpression booleanExpression;
        expression.getClass();
        data.getClass();
        if (!Intrinsics.areEqual(expression.getOperationToken(), KtTokens.EXCL)) {
            return (BooleanExpression) super.visitUnaryExpression(expression, data);
        }
        KtExpression baseExpression = expression.getBaseExpression();
        if (baseExpression == null || (booleanExpression = (BooleanExpression) baseExpression.accept(this, data)) == null) {
            return null;
        }
        if (!(booleanExpression instanceof ContractDescriptionValue)) {
            ContractParsingDiagnosticsCollector contractParsingDiagnosticsCollector = this.collector;
            KtExpression baseExpression2 = expression.getBaseExpression();
            baseExpression2.getClass();
            contractParsingDiagnosticsCollector.badDescription("negations in contract description can be applied only to variables/values", baseExpression2);
        }
        return new LogicalNot(booleanExpression);
    }
}
