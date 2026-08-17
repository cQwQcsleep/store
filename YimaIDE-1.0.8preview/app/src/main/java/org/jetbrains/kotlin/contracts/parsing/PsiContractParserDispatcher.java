package org.jetbrains.kotlin.contracts.parsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.BooleanExpression;
import org.jetbrains.kotlin.contracts.description.CallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.ContractDescription;
import org.jetbrains.kotlin.contracts.description.EffectDeclaration;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanVariableReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ContractDescriptionValue;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;
import org.jetbrains.kotlin.contracts.parsing.effects.PsiCallsEffectParser;
import org.jetbrains.kotlin.contracts.parsing.effects.PsiConditionalEffectParser;
import org.jetbrains.kotlin.contracts.parsing.effects.PsiReturnsEffectParser;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.mpp.ClassifierSymbolMarker;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtBlockExpression;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.resolve.ContractsDslNames;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J$\u0010\u001a\u001a\u00020\u001b2\u001a\u0010\u001c\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u00170\u001e0\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0012\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0012\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;", Argument.Delimiters.none, "collector", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "<init>", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;Lorg/jetbrains/kotlin/storage/StorageManager;)V", "conditionParser", "Lorg/jetbrains/kotlin/contracts/parsing/PsiConditionParser;", "constantParser", "Lorg/jetbrains/kotlin/contracts/parsing/PsiConstantParser;", "effectsParsers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/contracts/parsing/PsiEffectParser;", "parseContract", "Lorg/jetbrains/kotlin/contracts/description/ContractDescription;", "parseCondition", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "expression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "parseEffect", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "checkDuplicatedCallsEffectsAndReport", Argument.Delimiters.none, "effects", Argument.Delimiters.none, "Lkotlin/Pair;", "isValidEffectDeclaration", Argument.Delimiters.none, "parseConstant", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "parseVariable", "Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "parseValue", "Lorg/jetbrains/kotlin/contracts/description/expressions/ContractDescriptionValue;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiContractParserDispatcher {
    private final ContractCallContext callContext;
    private final ContractParsingDiagnosticsCollector collector;
    private final PsiConditionParser conditionParser;
    private final PsiConstantParser constantParser;
    private final Map<Name, PsiEffectParser> effectsParsers;
    private final StorageManager storageManager;

    public PsiContractParserDispatcher(ContractParsingDiagnosticsCollector contractParsingDiagnosticsCollector, ContractCallContext contractCallContext, StorageManager storageManager) {
        contractParsingDiagnosticsCollector.getClass();
        contractCallContext.getClass();
        storageManager.getClass();
        this.collector = contractParsingDiagnosticsCollector;
        this.callContext = contractCallContext;
        this.storageManager = storageManager;
        this.conditionParser = new PsiConditionParser(contractParsingDiagnosticsCollector, contractCallContext, this);
        this.constantParser = new PsiConstantParser(contractCallContext);
        ContractsDslNames contractsDslNames = ContractsDslNames.INSTANCE;
        this.effectsParsers = MapsKt.mapOf(new Pair[]{TuplesKt.to(contractsDslNames.getRETURNS_EFFECT().getCallableName(), new PsiReturnsEffectParser(contractParsingDiagnosticsCollector, contractCallContext, this)), TuplesKt.to(contractsDslNames.getRETURNS_NOT_NULL_EFFECT().getCallableName(), new PsiReturnsEffectParser(contractParsingDiagnosticsCollector, contractCallContext, this)), TuplesKt.to(contractsDslNames.getCALLS_IN_PLACE_EFFECT().getCallableName(), new PsiCallsEffectParser(contractParsingDiagnosticsCollector, contractCallContext, this)), TuplesKt.to(contractsDslNames.getCONDITIONAL_EFFECT().getCallableName(), new PsiConditionalEffectParser(contractParsingDiagnosticsCollector, contractCallContext, this))});
    }

    private final void checkDuplicatedCallsEffectsAndReport(List<? extends Pair<? extends EffectDeclaration, ? extends KtExpression>> effects) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Pair<? extends EffectDeclaration, ? extends KtExpression> pair : effects) {
            EffectDeclaration effectDeclaration = (EffectDeclaration) pair.component1();
            KtElement ktElement = (KtExpression) pair.component2();
            if (effectDeclaration instanceof CallsEffectDeclaration) {
                ParameterDescriptor descriptor = ((CallsEffectDeclaration) effectDeclaration).getVariableReference().getDescriptor();
                if (linkedHashSet.contains(descriptor)) {
                    this.collector.badDescription("Duplicated contract for " + descriptor.getName() + ". Only one `callsInPlace` contract per parameter is allowed.", ktElement);
                } else {
                    linkedHashSet.add(descriptor);
                }
            }
        }
    }

    private final boolean isValidEffectDeclaration(KtExpression expression) {
        CallableDescriptor resultingDescriptor;
        if (!(expression instanceof KtCallExpression) && !(expression instanceof KtBinaryExpression)) {
            this.collector.badDescription("unexpected construction in contract description", expression);
            return false;
        }
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(expression, this.callContext.getBindingContext());
        if (resolvedCall == null || (resultingDescriptor = resolvedCall.getResultingDescriptor()) == null) {
            return false;
        }
        if (PsiContractsUtilsKt.isFromContractDsl(resultingDescriptor)) {
            return true;
        }
        this.collector.badDescription("effects can be produced only by direct calls to ContractsDSL", expression);
        return false;
    }

    public final BooleanExpression parseCondition(KtExpression expression) {
        if (expression != null) {
            return (BooleanExpression) expression.accept(this.conditionParser, Unit.INSTANCE);
        }
        return null;
    }

    public final ConstantReference parseConstant(KtExpression expression) {
        if (expression == null) {
            return null;
        }
        return (ConstantReference) expression.accept(this.constantParser, Unit.INSTANCE);
    }

    public final ContractDescription parseContract() {
        List statements;
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(this.callContext.getContractCallExpression(), this.callContext.getBindingContext());
        if (resolvedCall == null) {
            return null;
        }
        KtLambdaExpression ktLambdaExpressionFirstArgumentAsExpressionOrNull = PsiContractsUtilsKt.firstArgumentAsExpressionOrNull(resolvedCall);
        if (!(ktLambdaExpressionFirstArgumentAsExpressionOrNull instanceof KtLambdaExpression)) {
            if (ktLambdaExpressionFirstArgumentAsExpressionOrNull == null) {
                ktLambdaExpressionFirstArgumentAsExpressionOrNull = this.callContext.getContractCallExpression();
            }
            this.collector.badDescription("first argument of 'contract'-call should be a lambda expression", ktLambdaExpressionFirstArgumentAsExpressionOrNull);
            return null;
        }
        KtBlockExpression bodyExpression = ktLambdaExpressionFirstArgumentAsExpressionOrNull.getBodyExpression();
        if (bodyExpression == null || (statements = bodyExpression.getStatements()) == null) {
            return null;
        }
        List<KtExpression> list = statements;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (KtExpression ktExpression : list) {
            arrayList.add(TuplesKt.to(parseEffect(ktExpression), ktExpression));
        }
        checkDuplicatedCallsEffectsAndReport(arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            EffectDeclaration effectDeclaration = (EffectDeclaration) ((Pair) it.next()).getFirst();
            if (effectDeclaration != null) {
                arrayList2.add(effectDeclaration);
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return new ContractDescription(arrayList2, this.callContext.getFunctionDescriptor(), this.storageManager);
    }

    public final EffectDeclaration parseEffect(KtExpression expression) {
        KotlinType type;
        if (expression == null || !isValidEffectDeclaration(expression) || (type = CallUtilKt.getType(expression, this.callContext.getBindingContext())) == null) {
            return null;
        }
        Map<Name, PsiEffectParser> map = this.effectsParsers;
        ClassifierSymbolMarker declarationDescriptor = type.getConstructor().getDeclarationDescriptor();
        PsiEffectParser psiEffectParser = map.get(declarationDescriptor != null ? declarationDescriptor.getName() : null);
        if (psiEffectParser != null) {
            return psiEffectParser.tryParseEffect(expression);
        }
        this.collector.badDescription("unrecognized effect", expression);
        return null;
    }

    public final ContractDescriptionValue parseValue(KtExpression expression) {
        VariableReference variable = parseVariable(expression);
        return variable != null ? variable : parseConstant(expression);
    }

    public final VariableReference parseVariable(KtExpression expression) {
        if (expression == null) {
            return null;
        }
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(expression, this.callContext.getBindingContext());
        ParameterDescriptor resultingDescriptor = resolvedCall != null ? resolvedCall.getResultingDescriptor() : null;
        if (!(resultingDescriptor instanceof ParameterDescriptor)) {
            if (!(expression instanceof KtConstantExpression)) {
                this.collector.badDescription("only references to parameters are allowed in contract description", expression);
            }
            return null;
        }
        if (resultingDescriptor instanceof ReceiverParameterDescriptor) {
            ClassifierDescriptor declarationDescriptor = ((ReceiverParameterDescriptor) resultingDescriptor).getType().getConstructor().getDeclarationDescriptor();
            if (declarationDescriptor != null && PsiContractsUtilsKt.isFromContractDsl((DeclarationDescriptor) declarationDescriptor)) {
                this.collector.badDescription("only references to parameters are allowed. Did you miss label on <this>?", expression);
                return null;
            }
            FunctionDescriptor functionDescriptor = this.callContext.getFunctionDescriptor();
            ReceiverParameterDescriptor extensionReceiverParameter = functionDescriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter == null) {
                extensionReceiverParameter = functionDescriptor.getDispatchReceiverParameter();
            }
            if (!Intrinsics.areEqual(resultingDescriptor, extensionReceiverParameter)) {
                this.collector.badDescription("only references to direct <this> are allowed", expression);
                return null;
            }
        }
        ParameterDescriptor parameterDescriptor = resultingDescriptor;
        return KotlinBuiltIns.isBoolean(parameterDescriptor.getType()) ? new BooleanVariableReference(parameterDescriptor) : new VariableReference(parameterDescriptor);
    }
}
