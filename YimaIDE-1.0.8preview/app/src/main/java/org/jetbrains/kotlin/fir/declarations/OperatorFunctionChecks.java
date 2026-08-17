package org.jetbrains.kotlin.fir.declarations;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.OperatorFunctionChecks;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ?\u0010\u0014\u001a\u00020\u0015*\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0019\"\u00020\u0010H\u0002¢\u0006\u0002\u0010\u001aJE\u0010\u0014\u001a\u00020\u0015*\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c2\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0019\"\u00020\u0010H\u0002¢\u0006\u0002\u0010\u001dJE\u0010\u0014\u001a\u00020\u0015*\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00120\u001e2\u0006\u0010\u001f\u001a\u00020\u00132\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0019\"\u00020\u0010H\u0002¢\u0006\u0002\u0010 R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/OperatorFunctionChecks;", Argument.Delimiters.none, "<init>", "()V", "isOperator", "Lorg/jetbrains/kotlin/fir/declarations/CheckResult;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "checksByName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/Check;", "regexChecks", "Lkotlin/Pair;", "Lkotlin/text/Regex;", "checkFor", Argument.Delimiters.none, Argument.Delimiters.none, ModuleXmlParser.NAME, "checks", Argument.Delimiters.none, "(Ljava/util/Map;Lorg/jetbrains/kotlin/name/Name;[Lorg/jetbrains/kotlin/fir/declarations/Check;)V", "names", Argument.Delimiters.none, "(Ljava/util/Map;Ljava/util/Set;[Lorg/jetbrains/kotlin/fir/declarations/Check;)V", Argument.Delimiters.none, "regex", "(Ljava/util/List;Lkotlin/text/Regex;[Lorg/jetbrains/kotlin/fir/declarations/Check;)V", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OperatorFunctionChecks {
    public static final OperatorFunctionChecks INSTANCE;
    private static final Map<Name, List<Check>> checksByName;
    private static final List<Pair<Regex, List<Check>>> regexChecks;

    static {
        OperatorFunctionChecks operatorFunctionChecks = new OperatorFunctionChecks();
        INSTANCE = operatorFunctionChecks;
        Map<Name, List<Check>> mapCreateMapBuilder = MapsKt.createMapBuilder();
        Name name = OperatorNameConventions.GET;
        Checks checks = Checks.INSTANCE;
        Check memberOrExtension = checks.getMemberOrExtension();
        Checks.ValueParametersCount valueParametersCount = Checks.ValueParametersCount.INSTANCE;
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, name, memberOrExtension, valueParametersCount.atLeast(1));
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.SET, checks.getMemberOrExtension(), valueParametersCount.atLeast(2), Checks.simple$default(checks, "last parameter must not have a default value or be a vararg", null, new Function2() { // from class: tpa
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(OperatorFunctionChecks.checksByName$lambda$0$0((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null));
        Name name2 = OperatorNameConventions.GET_VALUE;
        Check memberOrExtension2 = checks.getMemberOrExtension();
        Check noDefaultAndVarargs = checks.getNoDefaultAndVarargs();
        Check checkAtLeast = valueParametersCount.atLeast(2);
        LanguageFeature languageFeature = LanguageFeature.ForbidGetSetValueWithTooManyParameters;
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, name2, memberOrExtension2, noDefaultAndVarargs, checkAtLeast, valueParametersCount.atMost(2, languageFeature), checks.isKProperty(), checks.getNonSuspend());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.SET_VALUE, checks.getMemberOrExtension(), checks.getNoDefaultAndVarargs(), valueParametersCount.atLeast(3), valueParametersCount.atMost(3, languageFeature), checks.isKProperty(), checks.getNonSuspend());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.PROVIDE_DELEGATE, checks.getMemberOrExtension(), checks.getNoDefaultAndVarargs(), valueParametersCount.exactly(2), checks.isKProperty(), checks.getNonSuspend());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.INVOKE, checks.getMemberOrExtensionOrCompanionBlockMember());
        Name name3 = OperatorNameConventions.CONTAINS;
        Check memberOrExtension3 = checks.getMemberOrExtension();
        Check single = valueParametersCount.getSingle();
        Check noDefaultAndVarargs2 = checks.getNoDefaultAndVarargs();
        Checks.Returns returns = Checks.Returns.INSTANCE;
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, name3, memberOrExtension3, single, noDefaultAndVarargs2, returns.getBoolean());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.ITERATOR, checks.getMemberOrExtension(), valueParametersCount.getNone());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.NEXT, checks.getMemberOrExtension(), valueParametersCount.getNone());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.HAS_NEXT, checks.getMemberOrExtension(), valueParametersCount.getNone(), returns.getBoolean());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.RANGE_TO, checks.getMemberOrExtension(), valueParametersCount.getSingle(), checks.getNoDefaultAndVarargs());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.RANGE_UNTIL, checks.getMemberOrExtension(), valueParametersCount.getSingle(), checks.getNoDefaultAndVarargs());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.EQUALS, checks.getMember(), Checks.EqualsOverridesEqualsOfAny.INSTANCE);
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.COMPARE_TO, checks.getMemberOrExtension(), returns.getInt(), valueParametersCount.getSingle(), checks.getNoDefaultAndVarargs());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.BINARY_OPERATION_NAMES, checks.getMemberOrExtension(), valueParametersCount.getSingle(), checks.getNoDefaultAndVarargs());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, checks.getMemberOrExtension(), valueParametersCount.getNone());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, SetsKt.setOf(new Name[]{OperatorNameConventions.INC, OperatorNameConventions.DEC}), checks.getMemberOrExtension(), Checks.simple$default(checks, "receiver must be a supertype of the return type", null, new Function2() { // from class: upa
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(OperatorFunctionChecks.checksByName$lambda$0$1((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null));
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.ASSIGNMENT_OPERATIONS, checks.getMemberOrExtension(), returns.getUnit(), valueParametersCount.getSingle(), checks.getNoDefaultAndVarargs());
        operatorFunctionChecks.checkFor(mapCreateMapBuilder, OperatorNameConventions.OF, new Checks.FeatureIsSupported(LanguageFeature.CollectionLiterals), checks.getNotExtension(), checks.getNoContextParameters(), checks.getNoDefaults(), checks.getOnlyLastVararg(), returns.getOuterOfCompanionWhereDefined());
        checksByName = MapsKt.build(mapCreateMapBuilder);
        List<Pair<Regex, List<Check>>> listCreateListBuilder = CollectionsKt.createListBuilder();
        operatorFunctionChecks.checkFor(listCreateListBuilder, OperatorNameConventions.COMPONENT_REGEX, checks.getMemberOrExtension(), valueParametersCount.getNone());
        regexChecks = CollectionsKt.build(listCreateListBuilder);
    }

    private OperatorFunctionChecks() {
    }

    private final void checkFor(Map<Name, List<Check>> map, Set<Name> set, Check... checkArr) {
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            map.put((Name) it.next(), ArraysKt.asList(checkArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checksByName$lambda$0$0(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.lastOrNull(firNamedFunction.getValueParameters());
        return (firValueParameter == null || firValueParameter.getDefaultValue() != null || firValueParameter.getIsVararg()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checksByName$lambda$0$1(FirNamedFunction firNamedFunction, FirSession firSession) {
        KotlinTypeMarker dispatchReceiverType;
        FirTypeRef typeRef;
        firNamedFunction.getClass();
        firSession.getClass();
        FirReceiverParameter receiverParameter = firNamedFunction.getReceiverParameter();
        if ((receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null || (dispatchReceiverType = FirTypeUtilsKt.getConeType(typeRef)) == null) && (dispatchReceiverType = firNamedFunction.getDispatchReceiverType()) == null) {
            return false;
        }
        return TypeUtilsKt.isSubtypeOf((KotlinTypeMarker) FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()), (TypeCheckerProviderContext) TypeComponentsKt.getTypeContext(firSession), dispatchReceiverType);
    }

    public final CheckResult isOperator(FirFunction function, FirSession session, ScopeSession scopeSession) {
        Object next;
        Regex regex;
        String strAsString;
        function.getClass();
        session.getClass();
        if (!(function instanceof FirNamedFunction)) {
            return CheckResult.AnonymousOperatorFunction.INSTANCE;
        }
        FirNamedFunction firNamedFunction = (FirNamedFunction) function;
        List<Check> list = checksByName.get(firNamedFunction.getName());
        if (list == null) {
            Iterator<T> it = regexChecks.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                regex = (Regex) ((Pair) next).getFirst();
                strAsString = firNamedFunction.getName().asString();
                strAsString.getClass();
            } while (!regex.matches(strAsString));
            Pair pair = (Pair) next;
            list = pair != null ? (List) pair.getSecond() : null;
        }
        List<Check> list2 = list;
        if (list2 == null) {
            return CheckResult.IllegalFunctionName.INSTANCE;
        }
        Iterator<Check> it2 = list2.iterator();
        while (it2.hasNext()) {
            OperatorDiagnostic operatorDiagnosticCheck = it2.next().check(firNamedFunction, session, scopeSession);
            if (operatorDiagnosticCheck != null) {
                return new CheckResult.IllegalSignature(operatorDiagnosticCheck);
            }
        }
        return CheckResult.SuccessCheck.INSTANCE;
    }

    private final void checkFor(Map<Name, List<Check>> map, Name name, Check... checkArr) {
        map.put(name, ArraysKt.asList(checkArr));
    }

    private final void checkFor(List<Pair<Regex, List<Check>>> list, Regex regex, Check... checkArr) {
        list.add(TuplesKt.to(regex, ArraysKt.asList(checkArr)));
    }
}
