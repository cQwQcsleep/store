package org.jetbrains.kotlin.fir.resolve.transformers.contracts;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.KtBinaryLogicExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalReturnsDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtErroneousCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtErroneousConstantReference;
import org.jetbrains.kotlin.contracts.description.KtErroneousContractElement;
import org.jetbrains.kotlin.contracts.description.KtErroneousIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtErroneousValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtIsNullPredicate;
import org.jetbrains.kotlin.contracts.description.KtLogicalNot;
import org.jetbrains.kotlin.contracts.description.KtReturnsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtReturnsResultOfDeclaration;
import org.jetbrains.kotlin.contracts.description.KtValueParameterReference;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractConstantValues;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeContractDescriptionError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ContractsDslNames;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 O2 \u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00012\u00020\u0007:\u0001OB%\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0014*\u00020\u0015H\u0002J*\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010!\u001a\u00020\"2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010$\u001a\u00020%2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010'\u001a\u00020(2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010*\u001a\u00020+2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J0\u0010,\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040-j\u0002`.2\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0014\u00104\u001a\u000205*\u00020\u000b2\u0006\u00106\u001a\u000207H\u0002J*\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u00109\u001a\u00020:2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010;\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010<\u001a\u00020=2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J*\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010?\u001a\u00020@2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0016J\u000e\u0010A\u001a\u0004\u0018\u00010B*\u00020CH\u0002J\u001c\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00142\u0006\u0010E\u001a\u00020FH\u0002J\u001c\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00142\u0006\u0010E\u001a\u00020FH\u0002J\u001c\u0010H\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u0005*\u00020\u0018H\u0002J\u001c\u0010I\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040Jj\u0002`K*\u00020CH\u0002J&\u0010L\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040-j\u0002`.*\u0004\u0018\u00010C2\u0006\u0010M\u001a\u00020NH\u0002R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/ConeEffectExtractor;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractDescriptionElement;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "valueAndContextParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;Ljava/util/List;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "asElement", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousContractElement;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "visitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "toValueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeValueParameterReference;", ModuleXmlParser.TYPE, "index", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "isAccessorOf", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "parseInvocationKind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "noReceiver", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "noArgument", "asContractElement", "asContractBooleanExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeBooleanExpression;", "asContractValueExpression", "argumentName", "Lorg/jetbrains/kotlin/name/Name;", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeEffectExtractor extends FirDefaultVisitor implements SessionHolder {
    private static final CallableId BOOLEAN_AND;
    private static final CallableId BOOLEAN_NOT;
    private static final CallableId BOOLEAN_OR;
    private static final Name LAMBDA_ARGUMENT_NAME;
    private static final Name OTHER_ARGUMENT_NAME;
    private static final Name TARGET_ARGUMENT_NAME;
    private final FirContractDescriptionOwner owner;
    private final FirSession session;
    private final List<FirValueParameter> valueAndContextParameters;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EventOccurrencesRange.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.NOT_EQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.IDENTITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.NOT_IDENTITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr;
        }
    }

    static {
        ContractsDslNames contractsDslNames = ContractsDslNames.INSTANCE;
        BOOLEAN_AND = contractsDslNames.id("kotlin", "Boolean", "and");
        BOOLEAN_OR = contractsDslNames.id("kotlin", "Boolean", "or");
        BOOLEAN_NOT = contractsDslNames.id("kotlin", "Boolean", "not");
        Name nameIdentifier = Name.identifier("lambda");
        nameIdentifier.getClass();
        LAMBDA_ARGUMENT_NAME = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("other");
        nameIdentifier2.getClass();
        OTHER_ARGUMENT_NAME = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("target");
        nameIdentifier3.getClass();
        TARGET_ARGUMENT_NAME = nameIdentifier3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ConeEffectExtractor(FirSession firSession, FirContractDescriptionOwner firContractDescriptionOwner, List<? extends FirValueParameter> list) {
        firSession.getClass();
        firContractDescriptionOwner.getClass();
        list.getClass();
        this.session = firSession;
        this.owner = firContractDescriptionOwner;
        this.valueAndContextParameters = list;
    }

    private final KtBooleanExpression<ConeKotlinType, ConeDiagnostic> asContractBooleanExpression(FirExpression firExpression) {
        KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement = asContractElement(firExpression);
        return ktContractDescriptionElementAsContractElement instanceof KtBooleanExpression ? (KtBooleanExpression) ktContractDescriptionElementAsContractElement : asElement(new ConeContractDescriptionError.NotABooleanExpression(ktContractDescriptionElementAsContractElement));
    }

    private final KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> asContractElement(FirElement firElement) {
        return (KtContractDescriptionElement) firElement.accept(this, null);
    }

    private final KtValueParameterReference<ConeKotlinType, ConeDiagnostic> asContractValueExpression(FirExpression firExpression, Name name) {
        KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement = firExpression != null ? asContractElement(firExpression) : null;
        if (ktContractDescriptionElementAsContractElement instanceof KtValueParameterReference) {
            return (KtValueParameterReference) ktContractDescriptionElementAsContractElement;
        }
        return new KtErroneousValueParameterReference(ktContractDescriptionElementAsContractElement == null ? new ConeContractDescriptionError.NoArgument(name) : new ConeContractDescriptionError.NotAParameterReference(ktContractDescriptionElementAsContractElement));
    }

    private final KtErroneousContractElement<ConeKotlinType, ConeDiagnostic> asElement(ConeContractDescriptionError coneContractDescriptionError) {
        return new KtErroneousContractElement<>(coneContractDescriptionError);
    }

    private final boolean isAccessorOf(FirContractDescriptionOwner firContractDescriptionOwner, FirDeclaration firDeclaration) {
        if (!(firDeclaration instanceof FirProperty)) {
            return false;
        }
        FirProperty firProperty = (FirProperty) firDeclaration;
        return Intrinsics.areEqual(firProperty.getGetter(), firContractDescriptionOwner) || Intrinsics.areEqual(firProperty.getSetter(), firContractDescriptionOwner);
    }

    private final KtErroneousContractElement<ConeKotlinType, ConeDiagnostic> noArgument(CallableId callableId) {
        return asElement(new ConeContractDescriptionError.NoArgument(callableId.getCallableName()));
    }

    private final KtErroneousContractElement<ConeKotlinType, ConeDiagnostic> noReceiver(CallableId callableId) {
        return asElement(new ConeContractDescriptionError.NoReceiver(callableId.getCallableName()));
    }

    private final EventOccurrencesRange parseInvocationKind(FirExpression firExpression) {
        FirCallableSymbol<?> resolvedCallableSymbol;
        CallableId callableId;
        if ((firExpression instanceof FirQualifiedAccessExpression) && (resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firExpression, getSession())) != null && (callableId = resolvedCallableSymbol.getCallableId()) != null) {
            ContractsDslNames contractsDslNames = ContractsDslNames.INSTANCE;
            if (Intrinsics.areEqual(callableId, contractsDslNames.getEXACTLY_ONCE_KIND())) {
                return EventOccurrencesRange.EXACTLY_ONCE;
            }
            if (Intrinsics.areEqual(callableId, contractsDslNames.getAT_LEAST_ONCE_KIND())) {
                return EventOccurrencesRange.AT_LEAST_ONCE;
            }
            if (Intrinsics.areEqual(callableId, contractsDslNames.getAT_MOST_ONCE_KIND())) {
                return EventOccurrencesRange.AT_MOST_ONCE;
            }
            if (Intrinsics.areEqual(callableId, contractsDslNames.getUNKNOWN_KIND())) {
                return EventOccurrencesRange.UNKNOWN;
            }
        }
        return null;
    }

    private final KtValueParameterReference<ConeKotlinType, ConeDiagnostic> toValueParameterReference(ConeKotlinType type, int index, String name) {
        return Intrinsics.areEqual(type, getSession().getBuiltinTypes().getBooleanType().getConeType()) ? new KtBooleanValueParameterReference(index, name) : new KtValueParameterReference<>(index, name);
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, Void data) {
        booleanOperatorExpression.getClass();
        return new KtBinaryLogicExpression(asContractBooleanExpression(booleanOperatorExpression.getLeftOperand()), asContractBooleanExpression(booleanOperatorExpression.getRightOperand()), booleanOperatorExpression.getKind());
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitElement(FirElement element, Void data) {
        element.getClass();
        return asElement(new ConeContractDescriptionError.IllegalElement(element));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0028  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Void data) {
        boolean z;
        equalityOperatorCall.getClass();
        FirOperation operation = equalityOperatorCall.getOperation();
        int i = WhenMappings.$EnumSwitchMapping$1[operation.ordinal()];
        if (i == 1) {
            z = false;
        } else {
            if (i != 2) {
                if (i == 3) {
                    z = false;
                } else if (i != 4) {
                    return asElement(new ConeContractDescriptionError.IllegalEqualityOperator(operation));
                }
            }
            z = true;
        }
        FirExpression firExpression = (FirExpression) CollectionsKt.getOrNull(equalityOperatorCall.getArgumentList().getArguments(), 1);
        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
        if (firLiteralExpression == null) {
            return asElement(new ConeContractDescriptionError.NotAConstant(firExpression));
        }
        return !Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.Null.INSTANCE) ? asElement(new ConeContractDescriptionError.IllegalConst(firLiteralExpression, true)) : new KtIsNullPredicate(asContractValueExpression((FirExpression) CollectionsKt.getOrNull(equalityOperatorCall.getArgumentList().getArguments(), 0), OTHER_ARGUMENT_NAME), z);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitFunctionCall(FirFunctionCall functionCall, Void data) {
        CallableId callableId;
        KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpressionNoReceiver;
        KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpressionNoArgument;
        LogicOperationKind logicOperationKind;
        KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpressionNoReceiver2;
        KtConstantReference<ConeKotlinType, ConeDiagnostic> ktErroneousConstantReference;
        functionCall.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(functionCall);
        if (resolvedCallableSymbol == null || (callableId = resolvedCallableSymbol.getCallableId()) == null) {
            return asElement(new ConeContractDescriptionError.UnresolvedCall(functionCall.getCalleeReference().getName()));
        }
        ContractsDslNames contractsDslNames = ContractsDslNames.INSTANCE;
        if (Intrinsics.areEqual(callableId, contractsDslNames.getIMPLIES())) {
            FirExpression explicitReceiver = functionCall.getExplicitReceiver();
            KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement = explicitReceiver != null ? asContractElement(explicitReceiver) : null;
            KtEffectDeclaration ktEffectDeclarationNoReceiver = ktContractDescriptionElementAsContractElement instanceof KtEffectDeclaration ? (KtEffectDeclaration) ktContractDescriptionElementAsContractElement : null;
            if (ktEffectDeclarationNoReceiver == null) {
                ktEffectDeclarationNoReceiver = noReceiver(callableId);
            }
            KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement2 = asContractElement((FirExpression) CollectionsKt.first(functionCall.getArgumentList().getArguments()));
            KtBooleanExpression ktBooleanExpressionNoArgument2 = ktContractDescriptionElementAsContractElement2 instanceof KtBooleanExpression ? (KtBooleanExpression) ktContractDescriptionElementAsContractElement2 : null;
            if (ktBooleanExpressionNoArgument2 == null) {
                ktBooleanExpressionNoArgument2 = noArgument(callableId);
            }
            return new KtConditionalEffectDeclaration(ktEffectDeclarationNoReceiver, ktBooleanExpressionNoArgument2);
        }
        if (Intrinsics.areEqual(callableId, contractsDslNames.getRETURNS())) {
            FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(functionCall.getArgumentList().getArguments());
            if (firExpression == null) {
                ktErroneousConstantReference = ConeContractConstantValues.INSTANCE.getWILDCARD();
            } else {
                KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement3 = asContractElement(firExpression);
                ktErroneousConstantReference = ktContractDescriptionElementAsContractElement3 instanceof KtConstantReference ? (KtConstantReference) ktContractDescriptionElementAsContractElement3 : new KtErroneousConstantReference(new ConeContractDescriptionError.NotAConstant(ktContractDescriptionElementAsContractElement3));
            }
            ktErroneousConstantReference.getClass();
            return new KtReturnsEffectDeclaration(ktErroneousConstantReference);
        }
        if (Intrinsics.areEqual(callableId, contractsDslNames.getRETURNS_NOT_NULL())) {
            return new KtReturnsEffectDeclaration(ConeContractConstantValues.INSTANCE.getNOT_NULL());
        }
        if (Intrinsics.areEqual(callableId, contractsDslNames.getCALLS_IN_PLACE())) {
            KtValueParameterReference<ConeKotlinType, ConeDiagnostic> ktValueParameterReferenceAsContractValueExpression = asContractValueExpression((FirExpression) CollectionsKt.getOrNull(functionCall.getArgumentList().getArguments(), 0), LAMBDA_ARGUMENT_NAME);
            FirExpression firExpression2 = (FirExpression) CollectionsKt.getOrNull(functionCall.getArgumentList().getArguments(), 1);
            if (firExpression2 == null) {
                return new KtCallsEffectDeclaration(ktValueParameterReferenceAsContractValueExpression, EventOccurrencesRange.UNKNOWN);
            }
            EventOccurrencesRange invocationKind = parseInvocationKind(firExpression2);
            return (invocationKind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[invocationKind.ordinal()]) == -1 ? new KtErroneousCallsEffectDeclaration(ktValueParameterReferenceAsContractValueExpression, new ConeContractDescriptionError.UnresolvedInvocationKind(firExpression2)) : new KtCallsEffectDeclaration(ktValueParameterReferenceAsContractValueExpression, invocationKind);
        }
        if (Intrinsics.areEqual(callableId, contractsDslNames.getIMPLIES_BUILDER())) {
            if (!LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.ConditionImpliesReturnsContracts)) {
                return asElement(new ConeContractDescriptionError.NotContractDsl(callableId));
            }
            FirExpression explicitReceiver2 = functionCall.getExplicitReceiver();
            KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement4 = explicitReceiver2 != null ? asContractElement(explicitReceiver2) : null;
            KtBooleanExpression ktBooleanExpressionNoReceiver3 = ktContractDescriptionElementAsContractElement4 instanceof KtBooleanExpression ? (KtBooleanExpression) ktContractDescriptionElementAsContractElement4 : null;
            if (ktBooleanExpressionNoReceiver3 == null) {
                ktBooleanExpressionNoReceiver3 = noReceiver(callableId);
            }
            FirExpression firExpression3 = (FirExpression) CollectionsKt.getOrNull(functionCall.getArgumentList().getArguments(), 0);
            if (firExpression3 == null) {
                return noArgument(callableId);
            }
            KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement5 = asContractElement(firExpression3);
            KtEffectDeclaration ktEffectDeclaration = ktContractDescriptionElementAsContractElement5 instanceof KtEffectDeclaration ? (KtEffectDeclaration) ktContractDescriptionElementAsContractElement5 : null;
            return ktEffectDeclaration != null ? new KtConditionalReturnsDeclaration(ktBooleanExpressionNoReceiver3, ktEffectDeclaration) : asElement(new ConeContractDescriptionError.IllegalElement(firExpression3));
        }
        if (Intrinsics.areEqual(callableId, contractsDslNames.getHOLDS_IN())) {
            if (!LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.HoldsInContracts)) {
                return asElement(new ConeContractDescriptionError.NotContractDsl(callableId));
            }
            FirExpression explicitReceiver3 = functionCall.getExplicitReceiver();
            KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElementAsContractElement6 = explicitReceiver3 != null ? asContractElement(explicitReceiver3) : null;
            KtBooleanExpression ktBooleanExpressionNoReceiver4 = ktContractDescriptionElementAsContractElement6 instanceof KtBooleanExpression ? (KtBooleanExpression) ktContractDescriptionElementAsContractElement6 : null;
            if (ktBooleanExpressionNoReceiver4 == null) {
                ktBooleanExpressionNoReceiver4 = noReceiver(callableId);
            }
            return new KtHoldsInEffectDeclaration(ktBooleanExpressionNoReceiver4, asContractValueExpression((FirExpression) CollectionsKt.getOrNull(functionCall.getArgumentList().getArguments(), 0), LAMBDA_ARGUMENT_NAME));
        }
        if (Intrinsics.areEqual(callableId, contractsDslNames.getRETURNS_RESULT_OF())) {
            return (FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getReturnValueCheckerMode()) == ReturnValueCheckerMode.DISABLED || !LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.AllowReturnsResultOfContract)) ? asElement(new ConeContractDescriptionError.RequiresLanguageFeature("-Xallow-returns-result-of", "-Xreturn-value-checker")) : new KtReturnsResultOfDeclaration(asContractValueExpression((FirExpression) CollectionsKt.getOrNull(functionCall.getArgumentList().getArguments(), 0), LAMBDA_ARGUMENT_NAME));
        }
        CallableId callableId2 = BOOLEAN_AND;
        if (!Intrinsics.areEqual(callableId, callableId2) && !Intrinsics.areEqual(callableId, BOOLEAN_OR)) {
            if (!Intrinsics.areEqual(callableId, BOOLEAN_NOT)) {
                return asElement(new ConeContractDescriptionError.NotContractDsl(callableId));
            }
            FirExpression explicitReceiver4 = functionCall.getExplicitReceiver();
            if (explicitReceiver4 == null || (ktBooleanExpressionNoReceiver2 = asContractBooleanExpression(explicitReceiver4)) == null) {
                ktBooleanExpressionNoReceiver2 = noReceiver(callableId);
            }
            return new KtLogicalNot(ktBooleanExpressionNoReceiver2);
        }
        FirExpression explicitReceiver5 = functionCall.getExplicitReceiver();
        if (explicitReceiver5 == null || (ktBooleanExpressionNoReceiver = asContractBooleanExpression(explicitReceiver5)) == null) {
            ktBooleanExpressionNoReceiver = noReceiver(callableId);
        }
        FirExpression firExpression4 = (FirExpression) CollectionsKt.firstOrNull(functionCall.getArgumentList().getArguments());
        if (firExpression4 == null || (ktBooleanExpressionNoArgument = asContractBooleanExpression(firExpression4)) == null) {
            ktBooleanExpressionNoArgument = noArgument(callableId);
        }
        if (Intrinsics.areEqual(callableId, callableId2)) {
            logicOperationKind = LogicOperationKind.AND;
        } else {
            if (!Intrinsics.areEqual(callableId, BOOLEAN_OR)) {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
            logicOperationKind = LogicOperationKind.OR;
        }
        return new KtBinaryLogicExpression(ktBooleanExpressionNoReceiver, ktBooleanExpressionNoArgument, logicOperationKind);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitLiteralExpression(FirLiteralExpression literalExpression, Void data) {
        literalExpression.getClass();
        ConstantValueKind kind = literalExpression.getKind();
        if (Intrinsics.areEqual(kind, ConstantValueKind.Null.INSTANCE)) {
            return ConeContractConstantValues.INSTANCE.getNULL();
        }
        if (!Intrinsics.areEqual(kind, ConstantValueKind.Boolean.INSTANCE)) {
            return asElement(new ConeContractDescriptionError.IllegalConst(literalExpression, false));
        }
        Object value = literalExpression.getValue();
        value.getClass();
        boolean zBooleanValue = ((Boolean) value).booleanValue();
        if (zBooleanValue) {
            return ConeContractConstantValues.INSTANCE.getTRUE();
        }
        if (!zBooleanValue) {
            return ConeContractConstantValues.INSTANCE.getFALSE();
        }
        bu8.a();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, Void data) {
        propertyAccessExpression.getClass();
        return visitQualifiedAccessExpression((FirQualifiedAccessExpression) propertyAccessExpression, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, Void data) throws KotlinIllegalArgumentExceptionWithAttachments {
        Name nameSpecial;
        qualifiedAccessExpression.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(qualifiedAccessExpression);
        if (resolvedCallableSymbol == null) {
            FirReference calleeReference = qualifiedAccessExpression.getCalleeReference();
            FirNamedReference firNamedReference = calleeReference instanceof FirNamedReference ? (FirNamedReference) calleeReference : null;
            if (firNamedReference == null || (nameSpecial = firNamedReference.getName()) == null) {
                nameSpecial = Name.special("unresolved");
                nameSpecial.getClass();
            }
            return asElement(new ConeContractDescriptionError.UnresolvedCall(nameSpecial));
        }
        Object fir = resolvedCallableSymbol.getFir();
        FirValueParameter firValueParameter = fir instanceof FirValueParameter ? (FirValueParameter) fir : null;
        if (firValueParameter == null) {
            return new KtErroneousValueParameterReference(new ConeContractDescriptionError.IllegalParameter(resolvedCallableSymbol, "'" + resolvedCallableSymbol.getName() + "' is not a value parameter"));
        }
        Integer numValueOf = Integer.valueOf(this.valueAndContextParameters.indexOf(firValueParameter));
        Integer num = numValueOf.intValue() >= 0 ? numValueOf : null;
        if (num == null) {
            return new KtErroneousValueParameterReference(new ConeContractDescriptionError.IllegalParameter(resolvedCallableSymbol, "value parameter '" + resolvedCallableSymbol.getName() + "' is not found in parameters of outer function"));
        }
        int iIntValue = num.intValue();
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
        String strAsString = firValueParameter.getName().asString();
        strAsString.getClass();
        return toValueParameterReference(coneType, iIntValue, strAsString);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitSmartCastExpression(FirSmartCastExpression smartCastExpression, Void data) {
        smartCastExpression.getClass();
        return (KtContractDescriptionElement) smartCastExpression.getOriginalExpression().accept(this, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, Void data) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclaration fir;
        thisReceiverExpression.getClass();
        FirBasedSymbol<?> referencedMemberSymbol = ResolveUtilsKt.getReferencedMemberSymbol(thisReceiverExpression.getCalleeReference());
        if (referencedMemberSymbol == null || (fir = referencedMemberSymbol.getFir()) == null) {
            return asElement(new ConeContractDescriptionError.UnresolvedThis(thisReceiverExpression));
        }
        Object obj = this.owner;
        FirCallableDeclaration firCallableDeclaration = obj instanceof FirCallableDeclaration ? (FirCallableDeclaration) obj : null;
        return (Intrinsics.areEqual(fir, this.owner) || isAccessorOf(this.owner, fir) || (Intrinsics.areEqual(firCallableDeclaration != null ? ResolveUtilsKt.getContainingClass(firCallableDeclaration) : null, fir) && !((firCallableDeclaration != null ? firCallableDeclaration.getReceiverParameter() : null) != null))) ? toValueParameterReference(FirTypeUtilsKt.getResolvedType(thisReceiverExpression), -1, "this") : asElement(new ConeContractDescriptionError.IllegalThis(thisReceiverExpression));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, Void data) {
        typeOperatorCall.getClass();
        boolean z = false;
        KtValueParameterReference<ConeKotlinType, ConeDiagnostic> ktValueParameterReferenceAsContractValueExpression = asContractValueExpression((FirExpression) CollectionsKt.getOrNull(typeOperatorCall.getArgumentList().getArguments(), 0), TARGET_ARGUMENT_NAME);
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType(typeOperatorCall.getConversionTypeRef()));
        boolean z2 = typeOperatorCall.getOperation() == FirOperation.NOT_IS;
        FirTypeParameterSymbol typeParameterSymbol = ToSymbolUtilsKt.toTypeParameterSymbol(this, coneKotlinTypeFullyExpandedType);
        Object notReifiedTypeParameter = null;
        if (typeParameterSymbol != null) {
            FirContractDescriptionOwner firContractDescriptionOwner = this.owner;
            FirTypeParameterRefsOwner firTypeParameterRefsOwner = firContractDescriptionOwner instanceof FirTypeParameterRefsOwner ? (FirTypeParameterRefsOwner) firContractDescriptionOwner : null;
            List<FirTypeParameterRef> typeParameters = firTypeParameterRefsOwner != null ? firTypeParameterRefsOwner.getTypeParameters() : null;
            if (typeParameters == null) {
                typeParameters = CollectionsKt.emptyList();
            }
            List<FirTypeParameterRef> list = typeParameters;
            if ((list instanceof Collection) && list.isEmpty()) {
                notReifiedTypeParameter = new ConeContractDescriptionError.NotSelfTypeParameter(typeParameterSymbol);
            } else {
                for (FirTypeParameterRef firTypeParameterRef : list) {
                    if ((firTypeParameterRef instanceof FirTypeParameter) && Intrinsics.areEqual(((FirTypeParameter) firTypeParameterRef).getSymbol(), typeParameterSymbol)) {
                        if (!typeParameterSymbol.isReified() && !LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.AllowCheckForErasedTypesInContracts)) {
                            z = true;
                        }
                        if (z) {
                            notReifiedTypeParameter = new ConeContractDescriptionError.NotReifiedTypeParameter(typeParameterSymbol);
                        }
                    }
                }
                notReifiedTypeParameter = new ConeContractDescriptionError.NotSelfTypeParameter(typeParameterSymbol);
            }
        }
        return notReifiedTypeParameter == null ? new KtIsInstancePredicate(ktValueParameterReferenceAsContractValueExpression, coneKotlinTypeFullyExpandedType, z2) : new KtErroneousIsInstancePredicate(ktValueParameterReferenceAsContractValueExpression, coneKotlinTypeFullyExpandedType, z2, notReifiedTypeParameter);
    }
}
