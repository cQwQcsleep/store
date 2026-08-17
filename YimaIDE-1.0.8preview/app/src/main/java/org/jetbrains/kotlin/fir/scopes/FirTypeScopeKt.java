package org.jetbrains.kotlin.fir.scopes;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u001a,\u0010\t\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\bH\u0007b\u0002\b\r\u001ax\u0010\u000e\u001a\u00020\u000f\"\u000e\b\u0000\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0011*\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u00102/\u0010\u0013\u001a+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\b\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u00062\u0014\b\b\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\bH\u0087\bb\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a,\u0010\u000e\u001a\u00020\u000f*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\bH\u0007b\u0002\b\r\u001aF\u0010\u0016\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\u00190\u00182\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\bH\u0003b\u0002\b\r\u001a,\u0010\u001a\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\bH\u0007b\u0002\b\r\u001aH\u0010\u001d\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u001a\b\u0002\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001c0\u00190\u00182\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\bH\u0003b\u0002\b\r\u001a2\u0010\t\u001a\u00020\u001e*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\bH\u0007b\u0002\b\r\u001a2\u0010\u001a\u001a\u00020\u001e*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\bH\u0007b\u0002\b\r\u001a\u0096\u0001\u0010 \u001a\u00020\u0005\"\f\b\u0000\u0010\u0010*\u0006\u0012\u0002\b\u00030\u0011*\u00020\u00032\u0006\u0010!\u001a\u0002H\u00102\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u000425\u0010\"\u001a1\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u00062\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00100\u00190\u0018H\u0003b\u0002\b\r¢\u0006\u0002\u0010#\u001a\u0080\u0001\u0010$\u001a\u00020\u0005\"\f\b\u0000\u0010\u0010*\u0006\u0012\u0002\b\u00030\u0011*\u00020\u00032\u0006\u0010!\u001a\u0002H\u00102\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\b2?\u0010\"\u001a;\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0002j\b\u0012\u0004\u0012\u0002H\u0010`%¢\u0006\u0002\b\u0006H\u0007b\u0002\b\r¢\u0006\u0002\u0010&\u001a\u0090\u0001\u0010 \u001a\u00020\u0005\"\f\b\u0000\u0010\u0010*\u0006\u0012\u0002\b\u00030\u0011*\u00020\u00032\u0006\u0010!\u001a\u0002H\u00102\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\b25\u0010\"\u001a1\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u00062\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00100\u00190\u0018H\u0003b\u0002\b\r¢\u0006\u0002\u0010'\u001a2\u0010(\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\bH\u0087\bb\u0002\b\rø\u0001\u0000\u001a2\u0010)\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\bH\u0087\bb\u0002\b\rø\u0001\u0000\u001a2\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u00110\u001f*\u00020\u00032\n\u0010,\u001a\u0006\u0012\u0002\b\u00030\u00112\b\b\u0002\u0010-\u001a\u00020\u000fH\u0007b\u0002\b\r\u001a&\u0010.\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110/0\u001f*\u00020\u00032\n\u0010,\u001a\u0006\u0012\u0002\b\u00030\u0011\u001a,\u00100\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110/0\u001f*\u00020\u00032\n\u0010,\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0007b\u0002\b\r\u001a$\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0/0\u001f*\u00020\u00032\u0006\u00102\u001a\u00020\u000bH\u0007b\u0002\b\r\u001a$\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0/0\u001f*\u00020\u00032\u0006\u00104\u001a\u00020\u001cH\u0007b\u0002\b\r\u001a(\u00105\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f*\u00020\u00032\u0006\u00102\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000fH\u0007b\u0002\b\r\u001a(\u00106\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001f*\u00020\u00032\u0006\u00104\u001a\u00020\u001c2\b\b\u0002\u0010-\u001a\u00020\u000fH\u0007b\u0002\b\r\u001a \u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u001f*\u00020\u00032\n\u00108\u001a\u0006\u0012\u0002\b\u00030\u0011\u001a\u0018\u00107\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f*\u00020\u00032\u0006\u00108\u001a\u00020\u000b\u001a\u0018\u00107\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001f*\u00020\u00032\u0006\u00108\u001a\u00020\u001c\u001a8\u00109\u001a\u00020\u001e\"\u000e\b\u0000\u0010\u0001\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0011*\b\u0012\u0004\u0012\u0002H\u00010\u00182\u0006\u0010\u0012\u001a\u0002H\u00012\u0006\u0010-\u001a\u00020\u000fH\u0082\b¢\u0006\u0002\u0010:*n\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\"1\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u000621\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006*b\u0010\u0007\u001a\u0004\b\u0000\u0010\u0001\"+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00050\b\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u00062+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00050\b\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"}, d2 = {"ProcessOverriddenWithBaseScope", "D", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "Lkotlin/ExtensionFunctionType;", "ProcessAllOverridden", "Lkotlin/Function1;", "processOverriddenFunctions", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processor", "Lorg/jetbrains/kotlin/fir/scopes/ScopeFunctionRequiresPrewarm;", "anyOverriddenOf", Argument.Delimiters.none, "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "symbol", "processOverridden", "predicate", "(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)Z", "processOverriddenFunctionsWithVisited", "visited", Argument.Delimiters.none, "Lkotlin/Pair;", "processOverriddenProperties", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processOverriddenPropertiesWithVisited", Argument.Delimiters.none, Argument.Delimiters.none, "doProcessAllOverriddenCallables", "callableSymbol", "processDirectOverriddenCallablesWithBaseScope", "(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Ljava/util/Set;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "processAllOverriddenCallables", "Lorg/jetbrains/kotlin/fir/scopes/ProcessOverriddenWithBaseScope;", "(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Ljava/util/Set;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "processDirectlyOverriddenFunctions", "processDirectlyOverriddenProperties", "getDirectOverriddenMembers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "member", "unwrapIntersectionAndSubstitutionOverride", "getDirectOverriddenMembersWithBaseScopeSafe", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "getDirectOverriddenMembersWithBaseScope", "getDirectOverriddenFunctionsWithBaseScope", "function", "getDirectOverriddenPropertiesWithBaseScope", "property", "getDirectOverriddenFunctions", "getDirectOverriddenProperties", "getDirectOverriddenSafe", "memberSymbol", "addOverridden", "(Ljava/util/Set;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Z)V", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeScopeKt {

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$processOverriddenFunctions$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00571 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00571 INSTANCE = new C00571();

        public C00571() {
            super(3, FirTypeScope.class, "processDirectOverriddenFunctionsWithBaseScope", "processDirectOverriddenFunctionsWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firNamedFunctionSymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, function2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$processOverriddenFunctionsWithVisited$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00581 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00581 INSTANCE = new C00581();

        public C00581() {
            super(3, FirTypeScope.class, "processDirectOverriddenFunctionsWithBaseScope", "processDirectOverriddenFunctionsWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firNamedFunctionSymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, function2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$processOverriddenProperties$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00591 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00591 INSTANCE = new C00591();

        public C00591() {
            super(3, FirTypeScope.class, "processDirectOverriddenPropertiesWithBaseScope", "processDirectOverriddenPropertiesWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firPropertySymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, function2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$processOverriddenPropertiesWithVisited$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00601 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00601 INSTANCE = new C00601();

        public C00601() {
            super(3, FirTypeScope.class, "processDirectOverriddenPropertiesWithBaseScope", "processDirectOverriddenPropertiesWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firPropertySymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, function2);
        }
    }

    public static ProcessorAction a(Function2 function2, Function3 function3, Set set, FirCallableSymbol firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        return ((ProcessorAction) function2.invoke(firCallableSymbol, firTypeScope)).not() ? ProcessorAction.STOP : doProcessAllOverriddenCallables(firTypeScope, firCallableSymbol, (Function2<? super FirCallableSymbol, ? super FirTypeScope, ? extends ProcessorAction>) function2, (Function3<? super FirTypeScope, ? super FirCallableSymbol, ? super Function2<? super FirCallableSymbol, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction>) function3, (Set<Pair<FirTypeScope, FirCallableSymbol>>) set);
    }

    @ScopeFunctionRequiresPrewarm
    public static final /* synthetic */ <S extends FirCallableSymbol<?>> boolean anyOverriddenOf(FirTypeScope firTypeScope, S s, Function3<? super FirTypeScope, ? super S, ? super Function1<? super S, ? extends ProcessorAction>, ? extends ProcessorAction> function3, final Function1<? super S, Boolean> function1) {
        firTypeScope.getClass();
        s.getClass();
        function3.getClass();
        function1.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Intrinsics.needClassReification();
        function3.invoke(firTypeScope, s, new Function1<S, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt.anyOverriddenOf.1
            /* JADX WARN: Incorrect types in method signature: (TS;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction; */
            public final ProcessorAction invoke(FirCallableSymbol firCallableSymbol) {
                firCallableSymbol.getClass();
                if (!((Boolean) function1.invoke(firCallableSymbol)).booleanValue()) {
                    return ProcessorAction.NEXT;
                }
                booleanRef.element = true;
                return ProcessorAction.STOP;
            }
        });
        return booleanRef.element;
    }

    public static ProcessorAction b(Set set, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        firNamedFunctionSymbol.getClass();
        firTypeScope.getClass();
        set.add(new MemberWithBaseScope(firNamedFunctionSymbol, firTypeScope));
        return ProcessorAction.NEXT;
    }

    public static Unit c(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static Unit d(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    @ScopeFunctionRequiresPrewarm
    private static final <S extends FirCallableSymbol<?>> ProcessorAction doProcessAllOverriddenCallables(FirTypeScope firTypeScope, S s, final Function2<? super S, ? super FirTypeScope, ? extends ProcessorAction> function2, final Function3<? super FirTypeScope, ? super S, ? super Function2<? super S, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> function3, final Set<Pair<FirTypeScope, S>> set) {
        return !set.add(TuplesKt.to(firTypeScope, s)) ? ProcessorAction.NONE : (ProcessorAction) function3.invoke(firTypeScope, s, new Function2() { // from class: ag5
            public final Object invoke(Object obj, Object obj2) {
                return FirTypeScopeKt.a(function2, function3, set, (FirCallableSymbol) obj, (FirTypeScope) obj2);
            }
        });
    }

    public static Unit e(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static Unit f(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static ProcessorAction g(Set set, FirPropertySymbol firPropertySymbol, FirTypeScope firTypeScope) {
        firPropertySymbol.getClass();
        firTypeScope.getClass();
        set.add(new MemberWithBaseScope(firPropertySymbol, firTypeScope));
        return ProcessorAction.NEXT;
    }

    @ScopeFunctionRequiresPrewarm
    public static final List<FirNamedFunctionSymbol> getDirectOverriddenFunctions(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, final boolean z) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, new Function2<FirNamedFunctionSymbol, FirTypeScope, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$getDirectOverriddenFunctions$$inlined$processDirectlyOverriddenFunctions$1
            /* JADX WARN: Multi-variable type inference failed */
            public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2, FirTypeScope firTypeScope2) {
                firNamedFunctionSymbol2.getClass();
                firTypeScope2.getClass();
                Set set = linkedHashSet;
                if (!z) {
                    set.add(firNamedFunctionSymbol2);
                } else if (firNamedFunctionSymbol2 instanceof FirIntersectionCallableSymbol) {
                    Collection<FirCallableSymbol<?>> intersections = ((FirIntersectionCallableSymbol) firNamedFunctionSymbol2).getIntersections();
                    intersections.getClass();
                    set.addAll(intersections);
                } else {
                    FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol2.getFir();
                    FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    Object obj = (FirNamedFunctionSymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
                    Object obj2 = firNamedFunctionSymbol2;
                    if (obj != null) {
                        obj2 = obj;
                    }
                    set.add(obj2);
                }
                return ProcessorAction.NEXT;
            }
        });
        linkedHashSet.remove(firNamedFunctionSymbol);
        return CollectionsKt.toList(linkedHashSet);
    }

    public static /* synthetic */ List getDirectOverriddenFunctions$default(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return getDirectOverriddenFunctions(firTypeScope, firNamedFunctionSymbol, z);
    }

    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirNamedFunctionSymbol>> getDirectOverriddenFunctionsWithBaseScope(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, new Function2() { // from class: zf5
            public final Object invoke(Object obj, Object obj2) {
                return FirTypeScopeKt.b(linkedHashSet, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
            }
        });
        return CollectionsKt.toList(linkedHashSet);
    }

    @ScopeFunctionRequiresPrewarm
    public static final List<FirCallableSymbol<FirCallableDeclaration>> getDirectOverriddenMembers(FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol, boolean z) {
        firTypeScope.getClass();
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            return getDirectOverriddenFunctions(firTypeScope, (FirNamedFunctionSymbol) firCallableSymbol, z);
        }
        return firCallableSymbol instanceof FirPropertySymbol ? getDirectOverriddenProperties(firTypeScope, (FirPropertySymbol) firCallableSymbol, z) : CollectionsKt.emptyList();
    }

    public static /* synthetic */ List getDirectOverriddenMembers$default(FirTypeScope firTypeScope, FirCallableSymbol firCallableSymbol, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return getDirectOverriddenMembers(firTypeScope, firCallableSymbol, z);
    }

    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirCallableSymbol<?>>> getDirectOverriddenMembersWithBaseScope(FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol) {
        firTypeScope.getClass();
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            return getDirectOverriddenFunctionsWithBaseScope(firTypeScope, (FirNamedFunctionSymbol) firCallableSymbol);
        }
        return firCallableSymbol instanceof FirPropertySymbol ? getDirectOverriddenPropertiesWithBaseScope(firTypeScope, (FirPropertySymbol) firCallableSymbol) : CollectionsKt.emptyList();
    }

    public static final List<MemberWithBaseScope<FirCallableSymbol<?>>> getDirectOverriddenMembersWithBaseScopeSafe(FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol) {
        firTypeScope.getClass();
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            firTypeScope.processFunctionsByName(((FirFunctionSymbol) firCallableSymbol).getName(), new Function1() { // from class: bg5
                public final Object invoke(Object obj) {
                    return FirTypeScopeKt.c((FirNamedFunctionSymbol) obj);
                }
            });
        } else if (firCallableSymbol instanceof FirPropertySymbol) {
            firTypeScope.processPropertiesByName(((FirPropertySymbol) firCallableSymbol).getName(), new Function1() { // from class: cg5
                public final Object invoke(Object obj) {
                    return FirTypeScopeKt.d((FirVariableSymbol) obj);
                }
            });
        }
        return getDirectOverriddenMembersWithBaseScope(firTypeScope, firCallableSymbol);
    }

    @ScopeFunctionRequiresPrewarm
    public static final List<FirPropertySymbol> getDirectOverriddenProperties(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, final boolean z) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, new Function2<FirPropertySymbol, FirTypeScope, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$getDirectOverriddenProperties$$inlined$processDirectlyOverriddenProperties$1
            /* JADX WARN: Multi-variable type inference failed */
            public final ProcessorAction invoke(FirPropertySymbol firPropertySymbol2, FirTypeScope firTypeScope2) {
                firPropertySymbol2.getClass();
                firTypeScope2.getClass();
                Set set = linkedHashSet;
                if (!z) {
                    set.add(firPropertySymbol2);
                } else if (firPropertySymbol2 instanceof FirIntersectionCallableSymbol) {
                    Collection<FirCallableSymbol<?>> intersections = ((FirIntersectionCallableSymbol) firPropertySymbol2).getIntersections();
                    intersections.getClass();
                    set.addAll(intersections);
                } else {
                    FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firPropertySymbol2.getFir();
                    FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    Object obj = (FirPropertySymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
                    Object obj2 = firPropertySymbol2;
                    if (obj != null) {
                        obj2 = obj;
                    }
                    set.add(obj2);
                }
                return ProcessorAction.NEXT;
            }
        });
        linkedHashSet.remove(firPropertySymbol);
        return CollectionsKt.toList(linkedHashSet);
    }

    public static /* synthetic */ List getDirectOverriddenProperties$default(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return getDirectOverriddenProperties(firTypeScope, firPropertySymbol, z);
    }

    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirPropertySymbol>> getDirectOverriddenPropertiesWithBaseScope(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, new Function2() { // from class: yf5
            public final Object invoke(Object obj, Object obj2) {
                return FirTypeScopeKt.g(linkedHashSet, (FirPropertySymbol) obj, (FirTypeScope) obj2);
            }
        });
        return CollectionsKt.toList(linkedHashSet);
    }

    public static final List<FirCallableSymbol<?>> getDirectOverriddenSafe(FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol) {
        firTypeScope.getClass();
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            return getDirectOverriddenSafe(firTypeScope, (FirNamedFunctionSymbol) firCallableSymbol);
        }
        if (firCallableSymbol instanceof FirPropertySymbol) {
            return getDirectOverriddenSafe(firTypeScope, (FirPropertySymbol) firCallableSymbol);
        }
        aca.a("unexpected member kind ", firCallableSymbol);
        return null;
    }

    public static ProcessorAction h(Function1 function1, FirCallableSymbol firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        return (ProcessorAction) function1.invoke(firCallableSymbol);
    }

    @ScopeFunctionRequiresPrewarm
    public static final <S extends FirCallableSymbol<?>> ProcessorAction processAllOverriddenCallables(FirTypeScope firTypeScope, S s, Function1<? super S, ? extends ProcessorAction> function1, Function3<? super FirTypeScope, ? super S, ? super Function2<? super S, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> function3) {
        firTypeScope.getClass();
        s.getClass();
        function1.getClass();
        function3.getClass();
        return doProcessAllOverriddenCallables(firTypeScope, s, function1, function3, new LinkedHashSet());
    }

    @ScopeFunctionRequiresPrewarm
    public static final ProcessorAction processDirectlyOverriddenFunctions(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, final Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, new Function2<FirNamedFunctionSymbol, FirTypeScope, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt.processDirectlyOverriddenFunctions.1
            public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2, FirTypeScope firTypeScope2) {
                firNamedFunctionSymbol2.getClass();
                firTypeScope2.getClass();
                return (ProcessorAction) function1.invoke(firNamedFunctionSymbol2);
            }
        });
    }

    @ScopeFunctionRequiresPrewarm
    public static final ProcessorAction processDirectlyOverriddenProperties(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, final Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        function1.getClass();
        return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, new Function2<FirPropertySymbol, FirTypeScope, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt.processDirectlyOverriddenProperties.1
            public final ProcessorAction invoke(FirPropertySymbol firPropertySymbol2, FirTypeScope firTypeScope2) {
                firPropertySymbol2.getClass();
                firTypeScope2.getClass();
                return (ProcessorAction) function1.invoke(firPropertySymbol2);
            }
        });
    }

    @ScopeFunctionRequiresPrewarm
    public static final void processOverriddenFunctions(List<? extends FirTypeScope> list, FirNamedFunctionSymbol firNamedFunctionSymbol, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        list.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends FirTypeScope> it = list.iterator();
        while (it.hasNext() && !processOverriddenFunctionsWithVisited(it.next(), firNamedFunctionSymbol, linkedHashSet, function1).not()) {
        }
    }

    @ScopeFunctionRequiresPrewarm
    private static final ProcessorAction processOverriddenFunctionsWithVisited(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Set<Pair<FirTypeScope, FirNamedFunctionSymbol>> set, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        return doProcessAllOverriddenCallables(firTypeScope, firNamedFunctionSymbol, function1, C00581.INSTANCE, set);
    }

    @ScopeFunctionRequiresPrewarm
    public static final void processOverriddenProperties(List<? extends FirTypeScope> list, FirPropertySymbol firPropertySymbol, Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        list.getClass();
        firPropertySymbol.getClass();
        function1.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends FirTypeScope> it = list.iterator();
        while (it.hasNext() && !processOverriddenPropertiesWithVisited(it.next(), firPropertySymbol, linkedHashSet, function1).not()) {
        }
    }

    @ScopeFunctionRequiresPrewarm
    private static final ProcessorAction processOverriddenPropertiesWithVisited(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Set<Pair<FirTypeScope, FirPropertySymbol>> set, Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        return doProcessAllOverriddenCallables(firTypeScope, firPropertySymbol, function1, C00601.INSTANCE, set);
    }

    @ScopeFunctionRequiresPrewarm
    private static final <S extends FirCallableSymbol<?>> ProcessorAction doProcessAllOverriddenCallables(FirTypeScope firTypeScope, S s, final Function1<? super S, ? extends ProcessorAction> function1, Function3<? super FirTypeScope, ? super S, ? super Function2<? super S, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> function3, Set<Pair<FirTypeScope, S>> set) {
        return doProcessAllOverriddenCallables(firTypeScope, s, new Function2() { // from class: xf5
            public final Object invoke(Object obj, Object obj2) {
                return FirTypeScopeKt.h(function1, (FirCallableSymbol) obj, (FirTypeScope) obj2);
            }
        }, function3, set);
    }

    @ScopeFunctionRequiresPrewarm
    public static final boolean anyOverriddenOf(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, final Function1<? super FirNamedFunctionSymbol, Boolean> function1) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        processOverriddenFunctions(firTypeScope, firNamedFunctionSymbol, new Function1<FirNamedFunctionSymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt$anyOverriddenOf$$inlined$anyOverriddenOf$1
            public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                firNamedFunctionSymbol2.getClass();
                if (!((Boolean) function1.invoke(firNamedFunctionSymbol2)).booleanValue()) {
                    return ProcessorAction.NEXT;
                }
                booleanRef.element = true;
                return ProcessorAction.STOP;
            }
        });
        return booleanRef.element;
    }

    public static final List<FirNamedFunctionSymbol> getDirectOverriddenSafe(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        firTypeScope.processFunctionsByName(firNamedFunctionSymbol.getName(), new Function1() { // from class: dg5
            public final Object invoke(Object obj) {
                return FirTypeScopeKt.f((FirNamedFunctionSymbol) obj);
            }
        });
        return getDirectOverriddenFunctions$default(firTypeScope, firNamedFunctionSymbol, false, 2, null);
    }

    public static final List<FirPropertySymbol> getDirectOverriddenSafe(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        firTypeScope.processPropertiesByName(firPropertySymbol.getName(), new Function1() { // from class: eg5
            public final Object invoke(Object obj) {
                return FirTypeScopeKt.e((FirVariableSymbol) obj);
            }
        });
        return getDirectOverriddenProperties$default(firTypeScope, firPropertySymbol, false, 2, null);
    }

    @ScopeFunctionRequiresPrewarm
    public static final ProcessorAction processOverriddenFunctions(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        return doProcessAllOverriddenCallables(firTypeScope, firNamedFunctionSymbol, function1, C00571.INSTANCE, new LinkedHashSet());
    }

    @ScopeFunctionRequiresPrewarm
    public static final ProcessorAction processOverriddenProperties(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        function1.getClass();
        return doProcessAllOverriddenCallables(firTypeScope, firPropertySymbol, function1, C00591.INSTANCE, new LinkedHashSet());
    }
}
