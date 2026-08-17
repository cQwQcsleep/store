package org.jetbrains.kotlin.contracts;

import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.AbstractContractProvider;
import org.jetbrains.kotlin.contracts.description.ContractDescription;
import org.jetbrains.kotlin.contracts.description.ContractProviderKey;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESTypeSubstitution;
import org.jetbrains.kotlin.contracts.model.Functor;
import org.jetbrains.kotlin.contracts.model.functors.AndFunctor;
import org.jetbrains.kotlin.contracts.model.functors.EqualsFunctor;
import org.jetbrains.kotlin.contracts.model.functors.IsFunctor;
import org.jetbrains.kotlin.contracts.model.functors.NotFunctor;
import org.jetbrains.kotlin.contracts.model.functors.OrFunctor;
import org.jetbrains.kotlin.contracts.model.structure.CallComputation;
import org.jetbrains.kotlin.contracts.model.structure.ESBooleanType;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.ESType;
import org.jetbrains.kotlin.contracts.model.structure.TypesKt;
import org.jetbrains.kotlin.contracts.model.structure.UNKNOWN_COMPUTATION;
import org.jetbrains.kotlin.contracts.model.visitors.Reducer;
import org.jetbrains.kotlin.contracts.parsing.PsiContractsUtilsKt;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueDescriptor;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtIsExpression;
import org.jetbrains.kotlin.psi.KtLambdaArgument;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.psi.KtParenthesizedExpression;
import org.jetbrains.kotlin.psi.KtPsiUtil;
import org.jetbrains.kotlin.psi.KtSafeQualifiedExpression;
import org.jetbrains.kotlin.psi.KtUnaryExpression;
import org.jetbrains.kotlin.psi.KtValueArgument;
import org.jetbrains.kotlin.psi.KtVisitor;
import org.jetbrains.kotlin.psi.ValueArgument;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.calls.inference.components.EmptySubstitutor;
import org.jetbrains.kotlin.resolve.calls.inference.components.NewTypeSubstitutorByConstructorMap;
import org.jetbrains.kotlin.resolve.calls.model.DefaultValueArgument;
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument;
import org.jetbrains.kotlin.resolve.calls.model.VarargValueArgument;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.resolve.constants.CompileTimeConstant;
import org.jetbrains.kotlin.resolve.constants.UnsignedErrorValueTypeConstant;
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver;
import org.jetbrains.kotlin.resolve.scopes.receivers.ExtensionReceiver;
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.util.slicedMap.WritableSlice;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016J\u001d\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010&J\u001f\u0010'\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020(2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020+2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020.2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010/J\f\u00100\u001a\u00020\u0002*\u000201H\u0002J\f\u00102\u001a\u000203*\u000204H\u0002J\u000e\u00102\u001a\u0004\u0018\u000103*\u000205H\u0002J\u000e\u00106\u001a\u0004\u0018\u000107*\u000208H\u0002J\u0010\u00109\u001a\u00020:*\u0006\u0012\u0002\b\u00030;H\u0002J\u0018\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010=*\u0006\u0012\u0002\b\u00030;H\u0002J\u0010\u0010>\u001a\u00020?*\u0006\u0012\u0002\b\u00030;H\u0002J\u000e\u00100\u001a\u0004\u0018\u00010\u0002*\u00020@H\u0002J\u000e\u00100\u001a\u0004\u0018\u00010\u0002*\u00020AH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lorg/jetbrains/kotlin/contracts/EffectsExtractingVisitor;", "Lorg/jetbrains/kotlin/psi/KtVisitor;", "Lorg/jetbrains/kotlin/contracts/model/Computation;", Argument.Delimiters.none, "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "dataFlowValueFactory", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValueFactory;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lorg/jetbrains/kotlin/resolve/BindingTrace;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValueFactory;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "reducer", "Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "extractOrGetCached", "element", "Lorg/jetbrains/kotlin/psi/KtElement;", "visitKtElement", "data", "(Lorg/jetbrains/kotlin/psi/KtElement;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitLambdaExpression", "expression", "Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "(Lorg/jetbrains/kotlin/psi/KtLambdaExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitParenthesizedExpression", "Lorg/jetbrains/kotlin/psi/KtParenthesizedExpression;", "(Lorg/jetbrains/kotlin/psi/KtParenthesizedExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitConstantExpression", "Lorg/jetbrains/kotlin/psi/KtConstantExpression;", "(Lorg/jetbrains/kotlin/psi/KtConstantExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitIsExpression", "Lorg/jetbrains/kotlin/psi/KtIsExpression;", "(Lorg/jetbrains/kotlin/psi/KtIsExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitSafeQualifiedExpression", "Lorg/jetbrains/kotlin/psi/KtSafeQualifiedExpression;", "(Lorg/jetbrains/kotlin/psi/KtSafeQualifiedExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitBinaryExpression", "Lorg/jetbrains/kotlin/psi/KtBinaryExpression;", "(Lorg/jetbrains/kotlin/psi/KtBinaryExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "visitUnaryExpression", "Lorg/jetbrains/kotlin/psi/KtUnaryExpression;", "(Lorg/jetbrains/kotlin/psi/KtUnaryExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/Computation;", "toComputation", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValue;", "createDataFlowValue", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValue;", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ExtensionReceiver;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "getFunctor", "Lorg/jetbrains/kotlin/contracts/model/Functor;", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "isCallWithUnsupportedReceiver", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;", "getCallArgumentsAsComputations", Argument.Delimiters.none, "getTypeSubstitution", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedValueArgument;", "Lorg/jetbrains/kotlin/psi/ValueArgument;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EffectsExtractingVisitor extends KtVisitor<Computation, Unit> {
    private final DataFlowValueFactory dataFlowValueFactory;
    private final LanguageVersionSettings languageVersionSettings;
    private final ModuleDescriptor moduleDescriptor;
    private final Reducer reducer;
    private final BindingTrace trace;

    public EffectsExtractingVisitor(BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor, DataFlowValueFactory dataFlowValueFactory, LanguageVersionSettings languageVersionSettings) {
        bindingTrace.getClass();
        moduleDescriptor.getClass();
        dataFlowValueFactory.getClass();
        languageVersionSettings.getClass();
        this.trace = bindingTrace;
        this.moduleDescriptor = moduleDescriptor;
        this.dataFlowValueFactory = dataFlowValueFactory;
        this.languageVersionSettings = languageVersionSettings;
        this.reducer = new Reducer(getBuiltIns());
    }

    private final DataFlowValue createDataFlowValue(KtExpression ktExpression) {
        DataFlowValueFactory dataFlowValueFactory = this.dataFlowValueFactory;
        KotlinType type = this.trace.getType(ktExpression);
        if (type == null) {
            return null;
        }
        BindingContext bindingContext = this.trace.getBindingContext();
        bindingContext.getClass();
        return dataFlowValueFactory.createDataFlowValue(ktExpression, type, bindingContext, this.moduleDescriptor);
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.moduleDescriptor.getBuiltIns();
    }

    private final List<Computation> getCallArgumentsAsComputations(ResolvedCall<?> resolvedCall) {
        ArrayList arrayList = new ArrayList();
        ReceiverValue extensionReceiver = resolvedCall.getExtensionReceiver();
        CollectionsKt.addIfNotNull(arrayList, extensionReceiver != null ? toComputation(extensionReceiver) : null);
        ReceiverValue dispatchReceiver = resolvedCall.getDispatchReceiver();
        CollectionsKt.addIfNotNull(arrayList, dispatchReceiver != null ? toComputation(dispatchReceiver) : null);
        List<ResolvedValueArgument> valueArgumentsByIndex = resolvedCall.getValueArgumentsByIndex();
        if (valueArgumentsByIndex == null) {
            return null;
        }
        for (ResolvedValueArgument resolvedValueArgument : valueArgumentsByIndex) {
            resolvedValueArgument.getClass();
            Computation computation = toComputation(resolvedValueArgument);
            if (computation == null) {
                return null;
            }
            arrayList.add(computation);
        }
        return arrayList;
    }

    private final Functor getFunctor(FunctionDescriptor functionDescriptor) {
        ContractDescription contractDescription;
        AbstractContractProvider abstractContractProvider = (AbstractContractProvider) functionDescriptor.getUserData(ContractProviderKey.INSTANCE);
        if (abstractContractProvider == null || (contractDescription = abstractContractProvider.getContractDescription()) == null) {
            return null;
        }
        return contractDescription.getFunctor(this.moduleDescriptor);
    }

    private final ESTypeSubstitution getTypeSubstitution(ResolvedCall<?> resolvedCall) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Map typeArguments = resolvedCall.getTypeArguments();
        typeArguments.getClass();
        for (Map.Entry entry : typeArguments.entrySet()) {
            linkedHashMap.put(((TypeParameterDescriptor) entry.getKey()).getTypeConstructor(), ((KotlinType) entry.getValue()).unwrap());
        }
        return new ESTypeSubstitution(!linkedHashMap.isEmpty() ? new NewTypeSubstitutorByConstructorMap(linkedHashMap) : EmptySubstitutor.INSTANCE, getBuiltIns());
    }

    private final boolean isCallWithUnsupportedReceiver(ResolvedCall<?> resolvedCall) {
        ResolvedCall resolvedCall2;
        KtExpression expression;
        KtExpression expression2;
        ExpressionReceiver extensionReceiver = resolvedCall.getExtensionReceiver();
        ResolvedCall resolvedCall3 = null;
        ExpressionReceiver expressionReceiver = extensionReceiver instanceof ExpressionReceiver ? extensionReceiver : null;
        if (expressionReceiver == null || (expression2 = expressionReceiver.getExpression()) == null) {
            resolvedCall2 = null;
        } else {
            BindingContext bindingContext = this.trace.getBindingContext();
            bindingContext.getClass();
            resolvedCall2 = CallUtilKt.getResolvedCall(expression2, bindingContext);
        }
        if (Intrinsics.areEqual(resolvedCall2, resolvedCall)) {
            return true;
        }
        ExpressionReceiver dispatchReceiver = resolvedCall.getDispatchReceiver();
        ExpressionReceiver expressionReceiver2 = dispatchReceiver instanceof ExpressionReceiver ? dispatchReceiver : null;
        if (expressionReceiver2 != null && (expression = expressionReceiver2.getExpression()) != null) {
            BindingContext bindingContext2 = this.trace.getBindingContext();
            bindingContext2.getClass();
            resolvedCall3 = CallUtilKt.getResolvedCall(expression, bindingContext2);
        }
        return Intrinsics.areEqual(resolvedCall3, resolvedCall) || resolvedCall.getExplicitReceiverKind() == ExplicitReceiverKind.BOTH_RECEIVERS;
    }

    private final Computation toComputation(ValueArgument valueArgument) {
        KtLambdaExpression argumentExpression;
        if (valueArgument instanceof KtLambdaArgument) {
            KtLambdaExpression lambdaExpression = ((KtLambdaArgument) valueArgument).getLambdaExpression();
            if (lambdaExpression != null) {
                return new ESLambda(lambdaExpression);
            }
            return null;
        }
        if (!(valueArgument instanceof KtValueArgument) || (argumentExpression = ((KtValueArgument) valueArgument).getArgumentExpression()) == null) {
            return null;
        }
        return argumentExpression instanceof KtLambdaExpression ? new ESLambda(argumentExpression) : extractOrGetCached(argumentExpression);
    }

    private static final boolean visitSafeQualifiedExpression$containsReturnsNull(ESEffect eSEffect) {
        if ((eSEffect instanceof ESReturns) && Intrinsics.areEqual(((ESReturns) eSEffect).getValue(), ESConstants.INSTANCE.getNullValue())) {
            return true;
        }
        return (eSEffect instanceof ConditionalEffect) && visitSafeQualifiedExpression$containsReturnsNull(((ConditionalEffect) eSEffect).getSimpleEffect());
    }

    public final Computation extractOrGetCached(KtElement element) {
        element.getClass();
        BindingTrace bindingTrace = this.trace;
        WritableSlice writableSlice = BindingContext.EXPRESSION_EFFECTS;
        Computation computation = (Computation) bindingTrace.get(writableSlice, element);
        if (computation != null) {
            return computation;
        }
        Computation computation2 = (Computation) element.accept(this, Unit.INSTANCE);
        this.trace.record(writableSlice, element, computation2);
        return computation2;
    }

    public Computation visitBinaryExpression(KtBinaryExpression expression, Unit data) {
        expression.getClass();
        data.getClass();
        KtExpression left = expression.getLeft();
        if (left == null) {
            return UNKNOWN_COMPUTATION.INSTANCE;
        }
        Computation computationExtractOrGetCached = extractOrGetCached(left);
        KtExpression right = expression.getRight();
        if (right == null) {
            return UNKNOWN_COMPUTATION.INSTANCE;
        }
        List<? extends Computation> listListOf = kotlin.collections.CollectionsKt.listOf(new Computation[]{computationExtractOrGetCached, extractOrGetCached(right)});
        IElementType operationToken = expression.getOperationToken();
        if (Intrinsics.areEqual(operationToken, KtTokens.EXCLEQ)) {
            return new CallComputation(ESBooleanType.INSTANCE, new EqualsFunctor(true).invokeWithArguments(listListOf, ESTypeSubstitution.INSTANCE.empty(getBuiltIns()), this.reducer));
        }
        if (Intrinsics.areEqual(operationToken, KtTokens.EQEQ)) {
            return new CallComputation(ESBooleanType.INSTANCE, new EqualsFunctor(false).invokeWithArguments(listListOf, ESTypeSubstitution.INSTANCE.empty(getBuiltIns()), this.reducer));
        }
        if (Intrinsics.areEqual(operationToken, KtTokens.ANDAND)) {
            return new CallComputation(ESBooleanType.INSTANCE, new AndFunctor().invokeWithArguments(listListOf, ESTypeSubstitution.INSTANCE.empty(getBuiltIns()), this.reducer));
        }
        return Intrinsics.areEqual(operationToken, KtTokens.OROR) ? new CallComputation(ESBooleanType.INSTANCE, new OrFunctor().invokeWithArguments(listListOf, ESTypeSubstitution.INSTANCE.empty(getBuiltIns()), this.reducer)) : UNKNOWN_COMPUTATION.INSTANCE;
    }

    public Computation visitConstantExpression(KtConstantExpression expression, Unit data) {
        CompileTimeConstant compileTimeConstant;
        expression.getClass();
        data.getClass();
        BindingContext bindingContext = this.trace.getBindingContext();
        bindingContext.getClass();
        KotlinType type = bindingContext.getType(expression);
        if (type != null && (compileTimeConstant = (CompileTimeConstant) bindingContext.get(BindingContext.COMPILE_TIME_VALUE, expression)) != null) {
            if (compileTimeConstant.isError() || (compileTimeConstant instanceof UnsignedErrorValueTypeConstant)) {
                return UNKNOWN_COMPUTATION.INSTANCE;
            }
            Object value = compileTimeConstant.getValue(type);
            if (value instanceof Boolean) {
                return ESConstants.INSTANCE.booleanValue(((Boolean) value).booleanValue());
            }
            return value == null ? ESConstants.INSTANCE.getNullValue() : UNKNOWN_COMPUTATION.INSTANCE;
        }
        return UNKNOWN_COMPUTATION.INSTANCE;
    }

    public Computation visitIsExpression(KtIsExpression expression, Unit data) {
        ESType eSType;
        expression.getClass();
        data.getClass();
        KotlinType kotlinType = (KotlinType) this.trace.get(BindingContext.TYPE, expression.getTypeReference());
        if (kotlinType == null || (eSType = TypesKt.toESType(kotlinType)) == null) {
            return UNKNOWN_COMPUTATION.INSTANCE;
        }
        KtExpression leftHandSide = expression.getLeftHandSide();
        leftHandSide.getClass();
        return new CallComputation(ESBooleanType.INSTANCE, new IsFunctor(eSType, expression.isNegated()).invokeWithArguments(kotlin.collections.CollectionsKt.listOf(extractOrGetCached(leftHandSide)), ESTypeSubstitution.INSTANCE.empty(getBuiltIns()), this.reducer));
    }

    public Computation visitKtElement(KtElement element, Unit data) {
        List<Computation> callArgumentsAsComputations;
        List<ESEffect> listEmptyList;
        element.getClass();
        data.getClass();
        BindingContext bindingContext = this.trace.getBindingContext();
        bindingContext.getClass();
        ResolvedCall<?> resolvedCall = CallUtilKt.getResolvedCall(element, bindingContext);
        if (resolvedCall != null && !isCallWithUnsupportedReceiver(resolvedCall) && (callArgumentsAsComputations = getCallArgumentsAsComputations(resolvedCall)) != null) {
            ESTypeSubstitution typeSubstitution = getTypeSubstitution(resolvedCall);
            CallableDescriptor resultingDescriptor = resolvedCall.getResultingDescriptor();
            resultingDescriptor.getClass();
            if (PsiContractsUtilsKt.isEqualsDescriptor(resultingDescriptor)) {
                return new CallComputation(ESBooleanType.INSTANCE, new EqualsFunctor(false).invokeWithArguments(callArgumentsAsComputations, typeSubstitution, this.reducer));
            }
            if (resultingDescriptor instanceof ValueDescriptor) {
                ValueDescriptor valueDescriptor = (ValueDescriptor) resultingDescriptor;
                DataFlowValue dataFlowValueCreateDataFlowValue = createDataFlowValue((KtExpression) element);
                return dataFlowValueCreateDataFlowValue == null ? UNKNOWN_COMPUTATION.INSTANCE : new ESVariableWithDataFlowValue(valueDescriptor, dataFlowValueCreateDataFlowValue);
            }
            if (!(resultingDescriptor instanceof FunctionDescriptor)) {
                return UNKNOWN_COMPUTATION.INSTANCE;
            }
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) resultingDescriptor;
            KotlinType returnType = functionDescriptor.getReturnType();
            ESType eSType = returnType != null ? TypesKt.toESType(returnType) : null;
            Functor functor = getFunctor(functionDescriptor);
            if (functor == null || (listEmptyList = functor.invokeWithArguments(callArgumentsAsComputations, typeSubstitution, this.reducer)) == null) {
                listEmptyList = kotlin.collections.CollectionsKt.emptyList();
            }
            return new CallComputation(eSType, listEmptyList);
        }
        return UNKNOWN_COMPUTATION.INSTANCE;
    }

    public Computation visitParenthesizedExpression(KtParenthesizedExpression expression, Unit data) {
        Computation computation;
        expression.getClass();
        data.getClass();
        KtExpression ktExpressionDeparenthesize = KtPsiUtil.deparenthesize(expression);
        return (ktExpressionDeparenthesize == null || (computation = (Computation) ktExpressionDeparenthesize.accept(this, data)) == null) ? UNKNOWN_COMPUTATION.INSTANCE : computation;
    }

    public Computation visitSafeQualifiedExpression(KtSafeQualifiedExpression expression, Unit data) {
        expression.getClass();
        Computation computation = (Computation) super.visitSafeQualifiedExpression(expression, data);
        if (computation == UNKNOWN_COMPUTATION.INSTANCE) {
            return computation;
        }
        List<ESEffect> effects = computation.getEffects();
        ArrayList arrayList = new ArrayList();
        for (Object obj : effects) {
            if (!visitSafeQualifiedExpression$containsReturnsNull((ESEffect) obj)) {
                arrayList.add(obj);
            }
        }
        return new CallComputation(computation.getType(), arrayList);
    }

    public Computation visitUnaryExpression(KtUnaryExpression expression, Unit data) {
        expression.getClass();
        data.getClass();
        KtExpression baseExpression = expression.getBaseExpression();
        if (baseExpression == null) {
            return UNKNOWN_COMPUTATION.INSTANCE;
        }
        return Intrinsics.areEqual(expression.getOperationToken(), KtTokens.EXCL) ? new CallComputation(ESBooleanType.INSTANCE, new NotFunctor().invokeWithArguments(extractOrGetCached(baseExpression))) : UNKNOWN_COMPUTATION.INSTANCE;
    }

    public Computation visitLambdaExpression(KtLambdaExpression expression, Unit data) {
        expression.getClass();
        return UNKNOWN_COMPUTATION.INSTANCE;
    }

    private final DataFlowValue createDataFlowValue(ExtensionReceiver extensionReceiver) {
        DataFlowValueFactory dataFlowValueFactory = this.dataFlowValueFactory;
        BindingContext bindingContext = this.trace.getBindingContext();
        bindingContext.getClass();
        CallableDescriptor declarationDescriptor = extensionReceiver.getDeclarationDescriptor();
        declarationDescriptor.getClass();
        return dataFlowValueFactory.createDataFlowValue(extensionReceiver, bindingContext, declarationDescriptor);
    }

    private final Computation toComputation(ResolvedValueArgument resolvedValueArgument) {
        if (resolvedValueArgument instanceof DefaultValueArgument) {
            return UNKNOWN_COMPUTATION.INSTANCE;
        }
        if (resolvedValueArgument instanceof VarargValueArgument) {
            return null;
        }
        if (!(resolvedValueArgument instanceof ExpressionValueArgument)) {
            qu7.a("Unexpected ResolvedValueArgument ", resolvedValueArgument);
            return null;
        }
        ValueArgument valueArgument = ((ExpressionValueArgument) resolvedValueArgument).getValueArgument();
        if (valueArgument != null) {
            return toComputation(valueArgument);
        }
        return null;
    }

    private final Computation toComputation(ReceiverValue receiverValue) {
        if (receiverValue instanceof ExpressionReceiver) {
            return extractOrGetCached(((ExpressionReceiver) receiverValue).getExpression());
        }
        if (receiverValue instanceof ExtensionReceiver) {
            if (this.languageVersionSettings.supportsFeature(LanguageFeature.ContractsOnCallsWithImplicitReceiver)) {
                return new ESReceiverWithDataFlowValue(receiverValue, createDataFlowValue((ExtensionReceiver) receiverValue));
            }
            return UNKNOWN_COMPUTATION.INSTANCE;
        }
        return UNKNOWN_COMPUTATION.INSTANCE;
    }
}
