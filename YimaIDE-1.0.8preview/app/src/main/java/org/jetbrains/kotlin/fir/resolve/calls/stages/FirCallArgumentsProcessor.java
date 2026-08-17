package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.FirDefaultParametersResolverKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentPassedTwice;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithSingleChild;
import org.jetbrains.kotlin.fir.resolve.calls.ManyLambdaExpressionArguments;
import org.jetbrains.kotlin.fir.resolve.calls.MixingNamedAndPositionArguments;
import org.jetbrains.kotlin.fir.resolve.calls.NameForAmbiguousParameter;
import org.jetbrains.kotlin.fir.resolve.calls.NameNotFound;
import org.jetbrains.kotlin.fir.resolve.calls.NamedArgumentNotAllowed;
import org.jetbrains.kotlin.fir.resolve.calls.NoValueForParameter;
import org.jetbrains.kotlin.fir.resolve.calls.NonVarargSpread;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.resolve.calls.TooManyArguments;
import org.jetbrains.kotlin.fir.resolve.calls.VarargArgumentOutsideParentheses;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ForbiddenNamedArgumentsTarget;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001SB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0014\u00100\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001503J\u0018\u00104\u001a\u0002012\u0006\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u000bH\u0002J\u0018\u00107\u001a\u0002012\u0006\u00108\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u000bH\u0002J\u0018\u00109\u001a\u0002012\u0006\u00105\u001a\u00020:2\u0006\u00108\u001a\u00020;H\u0002J\u000e\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020\u0015J\u0014\u0010>\u001a\u0002012\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001503J\u0006\u0010@\u001a\u000201J\b\u0010A\u001a\u000201H\u0002J\u0010\u0010B\u001a\u0002012\u0006\u00108\u001a\u00020\u0015H\u0002J\u0012\u0010C\u001a\u0004\u0018\u00010\u00192\u0006\u0010D\u001a\u00020\u0018H\u0002J\u0012\u0010E\u001a\u0004\u0018\u00010\u00192\u0006\u00108\u001a\u00020;H\u0002J\u0010\u0010F\u001a\u0002012\u0006\u0010G\u001a\u00020 H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR.\u0010!\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u00142\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R9\u0010$\u001a*\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150&0%j\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150&`'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001d\u0010*\u001a\u0004\u0018\u00010+8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b,\u0010-R\u0018\u0010H\u001a\u00020\u000b*\u00020I8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010JR\u001a\u0010K\u001a\b\u0012\u0004\u0012\u00020\u0019038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010#R\u0014\u0010M\u001a\b\u0012\u0004\u0012\u00020\u001903X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010N\u001a\b\u0012\u0004\u0012\u00020O03*\u0006\u0012\u0002\b\u00030P8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010R¨\u0006T"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/FirCallArgumentsProcessor;", Argument.Delimiters.none, "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "bodyResolveComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "originScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "isIndexedSetOperator", Argument.Delimiters.none, "lookInContextParameters", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/scopes/FirScope;ZZ)V", "state", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/FirCallArgumentsProcessor$State;", "currentPositionedParameterIndex", Argument.Delimiters.none, "varargArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "nameToParameter", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "namedDynamicArgumentsNamesImpl", Argument.Delimiters.none, "namedDynamicArgumentsNames", "getNamedDynamicArgumentsNames", "()Ljava/util/Set;", "value", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "diagnostics", "getDiagnostics", "()Ljava/util/List;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "Lkotlin/collections/LinkedHashMap;", "getResult", "()Ljava/util/LinkedHashMap;", "forbiddenNamedArgumentsTarget", "Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;", "getForbiddenNamedArgumentsTarget", "()Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;", "forbiddenNamedArgumentsTarget$delegate", "Lkotlin/Lazy;", "processNonLambdaArguments", Argument.Delimiters.none, "arguments", Argument.Delimiters.none, "processNonLambdaArgument", "atom", "isLastArgument", "processPositionArgument", "argument", "processNamedArgument", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithSingleChild;", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "processExternalArgument", "externalArgument", "processExcessLambdaArguments", "excessLambdaArguments", "processDefaultsAndRunChecks", "completeVarargPositionArguments", "addVarargArgument", "getParameterByName", ModuleXmlParser.NAME, "findParameterByName", "addDiagnostic", "diagnostic", "isSpread", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "valueParameters", "getValueParameters", "valueAndContextParametersIfRequired", "valueAndContextParameterSymbolsIfRequired", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "getValueAndContextParameterSymbolsIfRequired", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Ljava/util/List;", "State", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirCallArgumentsProcessor {
    private final BodyResolveComponents bodyResolveComponents;
    private int currentPositionedParameterIndex;
    private List<ResolutionDiagnostic> diagnostics;

    /* JADX INFO: renamed from: forbiddenNamedArgumentsTarget$delegate, reason: from kotlin metadata */
    private final Lazy forbiddenNamedArgumentsTarget;
    private final FirFunction function;
    private final boolean isIndexedSetOperator;
    private final boolean lookInContextParameters;
    private Map<Name, ? extends FirValueParameter> nameToParameter;
    private Set<Name> namedDynamicArgumentsNamesImpl;
    private final FirScope originScope;
    private final LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> result;
    private State state;
    private final FirSession useSiteSession;
    private final List<FirValueParameter> valueAndContextParametersIfRequired;
    private List<ConeResolutionAtom> varargArguments;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/FirCallArgumentsProcessor$State;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "POSITION_ARGUMENTS", "VARARG_POSITION", "NAMED_ONLY_ARGUMENTS", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum State {
        POSITION_ARGUMENTS,
        VARARG_POSITION,
        NAMED_ONLY_ARGUMENTS;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    public FirCallArgumentsProcessor(FirSession firSession, FirFunction firFunction, BodyResolveComponents bodyResolveComponents, FirScope firScope, boolean z, boolean z2) {
        firSession.getClass();
        firFunction.getClass();
        bodyResolveComponents.getClass();
        this.useSiteSession = firSession;
        this.function = firFunction;
        this.bodyResolveComponents = bodyResolveComponents;
        this.originScope = firScope;
        this.isIndexedSetOperator = z;
        this.lookInContextParameters = z2;
        this.state = State.POSITION_ARGUMENTS;
        this.result = new LinkedHashMap<>(firFunction.getValueParameters().size());
        this.forbiddenNamedArgumentsTarget = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.calls.stages.c
            public final Object invoke() {
                return FirCallArgumentsProcessor.c(this.b);
            }
        });
        List<FirValueParameter> valueParameters = getValueParameters();
        this.valueAndContextParametersIfRequired = z2 ? CollectionsKt.plus(valueParameters, firFunction.getContextParameters()) : valueParameters;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction a(FirCallArgumentsProcessor firCallArgumentsProcessor, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, FirNamedArgumentExpression firNamedArgumentExpression, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        FirValueParameterSymbol firValueParameterSymbol;
        firNamedFunctionSymbol.getClass();
        if (ResolveUtilsKt.areNamedArgumentsForbiddenIgnoringOverridden((FirFunction) firNamedFunctionSymbol.getFir())) {
            return ProcessorAction.NEXT;
        }
        List<FirValueParameterSymbol> valueAndContextParameterSymbolsIfRequired = firCallArgumentsProcessor.getValueAndContextParameterSymbolsIfRequired(firNamedFunctionSymbol);
        if (intRef.element != -1) {
            return findParameterByName$findAndReportValueParameterWithDifferentName(valueAndContextParameterSymbolsIfRequired, intRef, firNamedArgumentExpression, firCallArgumentsProcessor);
        }
        Iterator<FirValueParameterSymbol> it = valueAndContextParameterSymbolsIfRequired.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            FirValueParameterSymbol next = it.next();
            if (!next.getName().isSpecial() && Intrinsics.areEqual(next.getName(), firNamedArgumentExpression.getName())) {
                break;
            }
            i++;
        }
        intRef.element = i;
        if (i == -1) {
            objectRef2.element = valueAndContextParameterSymbolsIfRequired;
            return ProcessorAction.NEXT;
        }
        objectRef.element = firCallArgumentsProcessor.valueAndContextParametersIfRequired.get(i);
        List list = (List) objectRef2.element;
        if (((list == null || (firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.getOrNull(list, intRef.element)) == null) ? null : (FirValueParameter) firValueParameterSymbol.getFir()) == null) {
            return ProcessorAction.NEXT;
        }
        firCallArgumentsProcessor.addDiagnostic(new NameForAmbiguousParameter(firNamedArgumentExpression));
        return ProcessorAction.STOP;
    }

    private final void addDiagnostic(ResolutionDiagnostic diagnostic) {
        if (this.diagnostics == null) {
            this.diagnostics = new ArrayList();
        }
        List<ResolutionDiagnostic> list = this.diagnostics;
        list.getClass();
        list.add(diagnostic);
    }

    private final void addVarargArgument(ConeResolutionAtom argument) {
        if (this.varargArguments == null) {
            this.varargArguments = new ArrayList();
        }
        List<ConeResolutionAtom> list = this.varargArguments;
        list.getClass();
        list.add(argument);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction b(FirCallArgumentsProcessor firCallArgumentsProcessor, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (ResolveUtilsKt.areNamedArgumentsForbiddenIgnoringOverridden((FirFunction) firNamedFunctionSymbol.getFir())) {
            return ProcessorAction.NEXT;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (FirValueParameter firValueParameter : firCallArgumentsProcessor.valueAndContextParametersIfRequired) {
            int i2 = i + 1;
            Name name = firCallArgumentsProcessor.getValueAndContextParameterSymbolsIfRequired(firNamedFunctionSymbol).get(i).getName();
            if (!name.isSpecial()) {
                linkedHashMap.put(name, firValueParameter);
            }
            i = i2;
        }
        firCallArgumentsProcessor.nameToParameter = linkedHashMap;
        return ProcessorAction.STOP;
    }

    public static ForbiddenNamedArgumentsTarget c(FirCallArgumentsProcessor firCallArgumentsProcessor) {
        FirFunction firFunction = firCallArgumentsProcessor.function;
        FirScope firScope = firCallArgumentsProcessor.originScope;
        return ResolveUtilsKt.forbiddenNamedArgumentsTargetOrNull(firFunction, firScope instanceof FirTypeScope ? (FirTypeScope) firScope : null);
    }

    private final void completeVarargPositionArguments() {
        State state = State.POSITION_ARGUMENTS;
        FirValueParameter firValueParameter = getValueParameters().get(this.currentPositionedParameterIndex);
        LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> linkedHashMap = this.result;
        List<ConeResolutionAtom> list = this.varargArguments;
        list.getClass();
        linkedHashMap.put(firValueParameter, new ResolvedCallArgument.VarargArgument(list));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction e(FirCallArgumentsProcessor firCallArgumentsProcessor, Ref.IntRef intRef, FirNamedArgumentExpression firNamedArgumentExpression, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return ResolveUtilsKt.areNamedArgumentsForbiddenIgnoringOverridden((FirFunction) firNamedFunctionSymbol.getFir()) ? ProcessorAction.NEXT : findParameterByName$findAndReportValueParameterWithDifferentName(firCallArgumentsProcessor.getValueAndContextParameterSymbolsIfRequired(firNamedFunctionSymbol), intRef, firNamedArgumentExpression, firCallArgumentsProcessor);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0040  */
    private final FirValueParameter findParameterByName(final FirNamedArgumentExpression argument) {
        FirTypeScope firTypeScope;
        FirNamedFunctionSymbol firNamedFunctionSymbol;
        final FirNamedArgumentExpression firNamedArgumentExpression;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = getParameterByName(argument.getName());
        FirFunctionSymbol<FirFunction> symbol = this.function.getSymbol();
        if (symbol instanceof FirNamedFunctionSymbol) {
            firNamedFunctionSymbol = (FirNamedFunctionSymbol) symbol;
            firTypeScope = null;
        } else {
            firTypeScope = null;
            firNamedFunctionSymbol = null;
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        if (objectRef.element == null) {
            if (firNamedFunctionSymbol == null || !(ClassMembersKt.isSubstitutionOrIntersectionOverride(this.function) || DeclarationUtilsKt.isJavaOrEnhancement(this.function))) {
                firNamedArgumentExpression = argument;
            } else {
                final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                FirScope firScope = this.originScope;
                if (firScope instanceof FirTypeScope) {
                    firTypeScope = (FirTypeScope) firScope;
                }
                FirTypeScope firTypeScope2 = firTypeScope;
                if (firTypeScope2 != null) {
                    firNamedArgumentExpression = argument;
                    FirTypeScopeKt.processOverriddenFunctions(firTypeScope2, firNamedFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.calls.stages.a
                        public final Object invoke(Object obj) {
                            return FirCallArgumentsProcessor.a(this.b, intRef, objectRef, objectRef2, firNamedArgumentExpression, (FirNamedFunctionSymbol) obj);
                        }
                    });
                } else {
                    firNamedArgumentExpression = argument;
                }
            }
            if (objectRef.element == null) {
                addDiagnostic(new NameNotFound(firNamedArgumentExpression, this.function));
            }
        } else if (firNamedFunctionSymbol != null && (ClassMembersKt.isSubstitutionOrIntersectionOverride(this.function) || DeclarationUtilsKt.isJavaOrEnhancement(this.function))) {
            Map<Name, ? extends FirValueParameter> map = this.nameToParameter;
            map.getClass();
            Iterator<T> it = map.entrySet().iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                Object next = it.next();
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual(((Map.Entry) next).getKey(), argument.getName())) {
                    break;
                }
                i++;
            }
            intRef.element = i;
            if (i != -1) {
                FirScope firScope2 = this.originScope;
                FirTypeScope firTypeScope3 = firScope2 instanceof FirTypeScope ? (FirTypeScope) firScope2 : firTypeScope;
                if (firTypeScope3 != null) {
                    FirTypeScopeKt.processOverriddenFunctions(firTypeScope3, firNamedFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.calls.stages.b
                        public final Object invoke(Object obj) {
                            return FirCallArgumentsProcessor.e(this.b, intRef, argument, (FirNamedFunctionSymbol) obj);
                        }
                    });
                }
            }
        }
        return (FirValueParameter) objectRef.element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ProcessorAction findParameterByName$findAndReportValueParameterWithDifferentName(List<FirValueParameterSymbol> list, Ref.IntRef intRef, FirNamedArgumentExpression firNamedArgumentExpression, FirCallArgumentsProcessor firCallArgumentsProcessor) {
        FirValueParameter firValueParameter;
        FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.getOrNull(list, intRef.element);
        Name name = (firValueParameterSymbol == null || (firValueParameter = (FirValueParameter) firValueParameterSymbol.getFir()) == null) ? null : firValueParameter.getName();
        if (name == null || name.isSpecial() || Intrinsics.areEqual(name, firNamedArgumentExpression.getName())) {
            return ProcessorAction.NEXT;
        }
        firCallArgumentsProcessor.addDiagnostic(new NameForAmbiguousParameter(firNamedArgumentExpression));
        return ProcessorAction.STOP;
    }

    private final Set<Name> getNamedDynamicArgumentsNames() {
        Set<Name> set = this.namedDynamicArgumentsNamesImpl;
        if (set != null) {
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.namedDynamicArgumentsNamesImpl = linkedHashSet;
        return linkedHashSet;
    }

    private final FirValueParameter getParameterByName(Name name) {
        Name name2;
        if (this.nameToParameter == null) {
            if (ResolveUtilsKt.areNamedArgumentsForbiddenIgnoringOverridden(this.function) && getForbiddenNamedArgumentsTarget() == null) {
                FirFunctionSymbol<FirFunction> symbol = this.function.getSymbol();
                FirNamedFunctionSymbol firNamedFunctionSymbol = symbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) symbol : null;
                if (firNamedFunctionSymbol != null) {
                    FirScope firScope = this.originScope;
                    FirTypeScope firTypeScope = firScope instanceof FirTypeScope ? (FirTypeScope) firScope : null;
                    if (firTypeScope != null) {
                        FirTypeScopeKt.processOverriddenFunctions(firTypeScope, firNamedFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.calls.stages.d
                            public final Object invoke(Object obj) {
                                return FirCallArgumentsProcessor.b(this.b, (FirNamedFunctionSymbol) obj);
                            }
                        });
                    }
                }
                if (this.nameToParameter == null) {
                    this.nameToParameter = MapsKt.emptyMap();
                }
            } else {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (FirValueParameter firValueParameter : this.valueAndContextParametersIfRequired) {
                    ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(firValueParameter.getReturnTypeRef());
                    if (coneTypeOrNull == null || (name2 = FunctionalTypeUtilsKt.valueParameterName(coneTypeOrNull, this.useSiteSession)) == null) {
                        name2 = firValueParameter.getName();
                    }
                    if (!name2.isSpecial()) {
                        linkedHashMap.put(name2, firValueParameter);
                    }
                }
                this.nameToParameter = linkedHashMap;
            }
        }
        Map<Name, ? extends FirValueParameter> map = this.nameToParameter;
        map.getClass();
        return map.get(name);
    }

    private final List<FirValueParameterSymbol> getValueAndContextParameterSymbolsIfRequired(FirFunctionSymbol<?> firFunctionSymbol) {
        return this.lookInContextParameters ? CollectionsKt.plus(firFunctionSymbol.getValueParameterSymbols(), firFunctionSymbol.getContextParameterSymbols()) : firFunctionSymbol.getValueParameterSymbols();
    }

    private final List<FirValueParameter> getValueParameters() {
        return this.function.getValueParameters();
    }

    private final boolean isSpread(FirExpression firExpression) {
        if (firExpression instanceof FirWrappedArgumentExpression) {
            return ((FirWrappedArgumentExpression) firExpression).getIsSpread();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processDefaultsAndRunChecks$lambda$0$0(ResolvedCallArgument resolvedCallArgument, ExceptionAttachmentBuilder exceptionAttachmentBuilder) {
        exceptionAttachmentBuilder.getClass();
        Iterator it = resolvedCallArgument.getArguments().iterator();
        int i = 0;
        while (it.hasNext()) {
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "argument" + i, ((ConeResolutionAtom) it.next()).getExpression());
            i++;
        }
        return Unit.INSTANCE;
    }

    private final void processNamedArgument(ConeResolutionAtomWithSingleChild atom, FirNamedArgumentExpression argument) {
        ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTarget = getForbiddenNamedArgumentsTarget();
        if (forbiddenNamedArgumentsTarget != null) {
            addDiagnostic(new NamedArgumentNotAllowed(atom.getExpression(), this.function, forbiddenNamedArgumentsTarget));
        }
        State state = this.state;
        State state2 = State.NAMED_ONLY_ARGUMENTS;
        boolean z = state != state2;
        this.state = state2;
        FirValueParameter firValueParameterFindParameterByName = findParameterByName(argument);
        if (firValueParameterFindParameterByName == null) {
            return;
        }
        if (this.result.get(firValueParameterFindParameterByName) != null) {
            addDiagnostic(new ArgumentPassedTwice(argument));
            return;
        }
        this.result.put(firValueParameterFindParameterByName, new ResolvedCallArgument.SimpleArgument(atom));
        if (z && Intrinsics.areEqual(CollectionsKt.getOrNull(getValueParameters(), this.currentPositionedParameterIndex), firValueParameterFindParameterByName)) {
            this.state = State.POSITION_ARGUMENTS;
            this.currentPositionedParameterIndex++;
        }
    }

    private final void processNonLambdaArgument(ConeResolutionAtom atom, boolean isLastArgument) {
        FirExpression expression = atom.getExpression();
        if (!(expression instanceof FirNamedArgumentExpression)) {
            if (this.state == State.VARARG_POSITION && this.isIndexedSetOperator && isLastArgument) {
                completeVarargPositionArguments();
            }
            processPositionArgument(atom, isLastArgument);
            return;
        }
        if (!(atom instanceof ConeResolutionAtomWithSingleChild)) {
            w01.a("Failed requirement.");
            return;
        }
        ConeResolutionAtomWithSingleChild coneResolutionAtomWithSingleChild = (ConeResolutionAtomWithSingleChild) atom;
        if (coneResolutionAtomWithSingleChild.getSubAtom() == null) {
            w01.a("SubAtom of named argument is null");
            return;
        }
        if (!Intrinsics.areEqual(this.function.getOrigin(), FirDeclarationOrigin.DynamicScope.INSTANCE)) {
            if (this.state == State.VARARG_POSITION) {
                completeVarargPositionArguments();
            }
            processNamedArgument(coneResolutionAtomWithSingleChild, (FirNamedArgumentExpression) expression);
        } else {
            processPositionArgument(coneResolutionAtomWithSingleChild.getSubAtom(), isLastArgument);
            FirNamedArgumentExpression firNamedArgumentExpression = (FirNamedArgumentExpression) expression;
            if (getNamedDynamicArgumentsNames().add(firNamedArgumentExpression.getName())) {
                return;
            }
            addDiagnostic(new ArgumentPassedTwice(firNamedArgumentExpression));
        }
    }

    private final void processPositionArgument(ConeResolutionAtom argument, boolean isLastArgument) {
        int lastIndex;
        if (this.state == State.NAMED_ONLY_ARGUMENTS) {
            addDiagnostic(new MixingNamedAndPositionArguments(argument.getExpression()));
            return;
        }
        if (this.isIndexedSetOperator) {
            lastIndex = CollectionsKt.getLastIndex(getValueParameters());
            if (!isLastArgument) {
                int i = this.currentPositionedParameterIndex;
                lastIndex = i >= lastIndex ? -1 : i;
            }
        } else {
            lastIndex = this.currentPositionedParameterIndex;
        }
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.getOrNull(getValueParameters(), lastIndex);
        if (firValueParameter == null) {
            addDiagnostic(new TooManyArguments(argument.getExpression(), this.function));
            return;
        }
        if (firValueParameter.getIsVararg()) {
            addVarargArgument(argument);
            this.state = State.VARARG_POSITION;
        } else {
            this.currentPositionedParameterIndex++;
            this.result.put(firValueParameter, new ResolvedCallArgument.SimpleArgument(argument));
            this.state = State.POSITION_ARGUMENTS;
        }
    }

    public final List<ResolutionDiagnostic> getDiagnostics() {
        return this.diagnostics;
    }

    public final ForbiddenNamedArgumentsTarget getForbiddenNamedArgumentsTarget() {
        return (ForbiddenNamedArgumentsTarget) this.forbiddenNamedArgumentsTarget.getValue();
    }

    public final LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> getResult() {
        return this.result;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void processDefaultsAndRunChecks() throws KotlinIllegalArgumentExceptionWithAttachments {
        for (Map.Entry<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> entry : this.result.entrySet()) {
            FirValueParameter key = entry.getKey();
            final ResolvedCallArgument<ConeResolutionAtom> value = entry.getValue();
            if (!key.getIsVararg()) {
                if (!(value instanceof ResolvedCallArgument.SimpleArgument)) {
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Incorrect resolved argument for parameter " + key.getClass() + ": " + value.getClass(), (Throwable) null);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "parameter", key);
                    exceptionAttachmentBuilder.withEntryGroup("arguments", new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.calls.stages.e
                        public final Object invoke(Object obj) {
                            return FirCallArgumentsProcessor.processDefaultsAndRunChecks$lambda$0$0(value, (ExceptionAttachmentBuilder) obj);
                        }
                    });
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
                ResolvedCallArgument.SimpleArgument simpleArgument = (ResolvedCallArgument.SimpleArgument) value;
                if (isSpread(((ConeResolutionAtom) simpleArgument.getCallArgument()).getExpression())) {
                    addDiagnostic(new NonVarargSpread(((ConeResolutionAtom) simpleArgument.getCallArgument()).getExpression()));
                }
            }
        }
        Iterator<T> it = getValueParameters().iterator();
        int i = 0;
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return;
            }
            i = i2 + 1;
            FirValueParameter firValueParameter = (FirValueParameter) it.next();
            if (!this.result.containsKey(firValueParameter)) {
                if (FirDefaultParametersResolverKt.getDefaultParameterResolver(this.bodyResolveComponents.getSession()).declaresDefaultValue(this.useSiteSession, this.bodyResolveComponents.getScopeSession(), this.function, this.originScope, i2)) {
                    this.result.put(firValueParameter, ResolvedCallArgument.DefaultArgument.INSTANCE);
                } else if (firValueParameter.getIsVararg()) {
                    this.result.put(firValueParameter, new ResolvedCallArgument.VarargArgument(CollectionsKt.emptyList()));
                } else {
                    addDiagnostic(new NoValueForParameter(firValueParameter, this.function));
                }
            }
        }
    }

    public final void processExcessLambdaArguments(List<? extends ConeResolutionAtom> excessLambdaArguments) {
        excessLambdaArguments.getClass();
        Iterator<T> it = excessLambdaArguments.iterator();
        while (it.hasNext()) {
            addDiagnostic(new ManyLambdaExpressionArguments(((ConeResolutionAtom) it.next()).getExpression()));
        }
    }

    public final void processExternalArgument(ConeResolutionAtom externalArgument) {
        externalArgument.getClass();
        FirExpression expression = externalArgument.getExpression();
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.lastOrNull(getValueParameters());
        FirFunction firFunction = this.function;
        if (firValueParameter == null) {
            addDiagnostic(new TooManyArguments(expression, firFunction));
            return;
        }
        if (Intrinsics.areEqual(firFunction.getOrigin(), FirDeclarationOrigin.DynamicScope.INSTANCE)) {
            ResolvedCallArgument<ConeResolutionAtom> resolvedCallArgument = this.result.get(firValueParameter);
            LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> linkedHashMap = this.result;
            if (resolvedCallArgument == null) {
                linkedHashMap.put(firValueParameter, new ResolvedCallArgument.SimpleArgument(externalArgument));
                return;
            } else {
                linkedHashMap.put(firValueParameter, new ResolvedCallArgument.VarargArgument(CollectionsKt.plus(resolvedCallArgument.getArguments(), externalArgument)));
                return;
            }
        }
        if (firValueParameter.getIsVararg()) {
            addDiagnostic(new VarargArgumentOutsideParentheses(expression, firValueParameter));
        } else if (this.result.get(firValueParameter) != null) {
            addDiagnostic(new TooManyArguments(expression, this.function));
        } else {
            this.result.put(firValueParameter, new ResolvedCallArgument.SimpleArgument(externalArgument));
        }
    }

    public final void processNonLambdaArguments(List<? extends ConeResolutionAtom> arguments) {
        arguments.getClass();
        Iterator<T> it = arguments.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            processNonLambdaArgument((ConeResolutionAtom) it.next(), i == CollectionsKt.getLastIndex(arguments));
            i = i2;
        }
        if (this.state == State.VARARG_POSITION) {
            completeVarargPositionArguments();
        }
    }
}
