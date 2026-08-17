package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.ScopeFunctionRequiresPrewarm;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u0005\u001a8\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\b0\f\u001a&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n\u001a&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n\u001a8\u0010\u0012\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0004\u0012\u00020\b0\f\u001a4\u0010\u0014\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012\u0004\u0012\u00020\b0\f\u001a8\u0010\u0014\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012\u0004\u0012\u00020\b0\f\u001a\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0001*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u0005\u001a#\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0001*\u00020\u0019R\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010\u001d\u001a#\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0001*\u00020\u001eR\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010\u001f\u001a\u001b\u0010 \u001a\u0004\u0018\u00010\u001e*\u0006\u0012\u0002\b\u00030!2\u0006\u0010\"\u001a\u00020\u0005H\u0086\u0010\u001a!\u0010 \u001a\u0004\u0018\u00010\u001e*\u0006\u0012\u0002\b\u00030!R\u00020\u001bj\u0006\u0010#\u001a\u00020\u001b¢\u0006\u0002\u0010$\u001a\u0016\u0010%\u001a\u00020&*\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010'\u001a\u00020&*\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010(\u001a\u0004\u0018\u00010\u001e*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010)\u001a\u00020&*\u00020\u001e\u001a\u0014\u00105\u001a\u00020&*\u0002062\u0006\u00107\u001a\u000208H\u0002\u001a\u0012\u00109\u001a\u00020&*\u0002062\u0006\u00107\u001a\u000208\u001a\u0012\u0010:\u001a\u00020&*\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010;\u001a\u00020&*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<H\u0007b\u0002\b=\u001a+\u0010>\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u0001*\u00020AH\u0007R\u00020?b\u0002\b=j\u0006\u0010@\u001a\u00020?¢\u0006\u0002\u0010B\u001a$\u0010>\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u0001*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<H\u0007b\u0002\b=\u001a0\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<0\u0001*\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<0DH\u0007b\u0002\b=\u001a\u001f\u0010E\u001a\u00020F*\u0006\u0012\u0002\b\u00030\rR\u00020?j\u0006\u0010@\u001a\u00020?¢\u0006\u0002\u0010G\u001a*\u0010H\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<0\u0001*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<H\u0007b\u0002\b=\u001a*\u0010I\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<0\u0001*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<H\u0007b\u0002\b=\u001a0\u0010J\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<0\u0001*\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0<0DH\u0007b\u0002\b=\u001a\u0012\u0010K\u001a\u00020&*\u00020L2\u0006\u0010\u0004\u001a\u00020\u0005\"#\u0010*\u001a\u00020&*\u00020+8Æ\u0002X\u0087\u0004r\u0002\b/¢\u0006\f\u0012\u0004\b,\u0010-\u001a\u0004\b*\u0010.\"\u001b\u0010*\u001a\u00020&*\u0002008F¢\u0006\f\u0012\u0004\b,\u00101\u001a\u0004\b*\u00102\" \u0010*\u001a\u00020&*\u0006\u0012\u0002\b\u00030\u00158Æ\u0002¢\u0006\f\u0012\u0004\b,\u00103\u001a\u0004\b*\u00104¨\u0006M"}, d2 = {"constructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "processAllDeclaredCallables", Argument.Delimiters.none, "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "declaredProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "declaredFunctions", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processAllClassifiers", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "processAllDeclarations", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "primaryConstructorIfAny", "collectEnumEntries", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "getComplementarySymbols", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "holder", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;)Ljava/util/List;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)Ljava/util/List;", "fullyExpandedClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "useSiteSession", "sessionHolder", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "isAnnotationConstructor", Argument.Delimiters.none, "isPrimaryConstructorOfInlineOrValueClass", "getConstructedClass", "isInlineOrValueClass", "isJavaOrEnhancement", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "isJavaOrEnhancement$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)Z", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "containsDefaultValue", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "index", Argument.Delimiters.none, "itOrExpectHasDefaultParameterValue", "isEquals", "isTrivialIntersection", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "Lorg/jetbrains/kotlin/fir/scopes/ScopeFunctionRequiresPrewarm;", "getNonSubsumedOverriddenSymbols", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirIntersectionCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirIntersectionCallableSymbol;)Ljava/util/List;", "getNonSubsumedNonPhantomOverriddenSymbols", Argument.Delimiters.none, "dispatchReceiverScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "flattenIntersectionsRecursively", "flattenPhantomIntersectionsRecursively", "nonSubsumed", "isInlinable", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeclarationUtilsKt {
    public static Unit a(List list, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            list.add(firCallableSymbol);
        }
        return Unit.INSTANCE;
    }

    public static Unit b(List list, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        list.add(firConstructorSymbol);
        return Unit.INSTANCE;
    }

    public static Unit c(List list, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        list.add(firConstructorSymbol);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<FirEnumEntrySymbol> collectEnumEntries(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        List<FirEnumEntry> listCollectEnumEntries = collectEnumEntries((FirClass) firClassSymbol.getFir(), firSession);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listCollectEnumEntries, 10));
        Iterator<T> it = listCollectEnumEntries.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirEnumEntry) it.next()).getSymbol());
        }
        return arrayList;
    }

    public static final List<FirConstructorSymbol> constructors(FirClass firClass, FirSession firSession) {
        firClass.getClass();
        firSession.getClass();
        final ArrayList arrayList = new ArrayList();
        FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClass, (FirResolvePhase) null).processDeclaredConstructors(new Function1() { // from class: je3
            public final Object invoke(Object obj) {
                return DeclarationUtilsKt.c(arrayList, (FirConstructorSymbol) obj);
            }
        });
        return arrayList;
    }

    private static final boolean containsDefaultValue(FirFunction firFunction, int i) {
        return firFunction.getValueParameters().get(i).getDefaultValue() != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction d(FirCallableSymbol firCallableSymbol, Set set, FirCallableSymbol firCallableSymbol2) {
        firCallableSymbol2.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol2.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return null;
        }
        if (!Intrinsics.areEqual(symbol, firCallableSymbol)) {
            set.add(symbol);
        }
        return ProcessorAction.NEXT;
    }

    public static final List<FirNamedFunctionSymbol> declaredFunctions(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase) {
        firClassSymbol.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        final ArrayList arrayList = new ArrayList();
        processAllDeclaredCallables(firClassSymbol, firSession, firResolvePhase, new Function1() { // from class: oe3
            public final Object invoke(Object obj) {
                return DeclarationUtilsKt.a(arrayList, (FirCallableSymbol) obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ List declaredFunctions$default(FirClassSymbol firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.STATUS;
        }
        return declaredFunctions(firClassSymbol, firSession, firResolvePhase);
    }

    public static final List<FirPropertySymbol> declaredProperties(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase) {
        firClassSymbol.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        final ArrayList arrayList = new ArrayList();
        processAllDeclaredCallables(firClassSymbol, firSession, firResolvePhase, new Function1() { // from class: me3
            public final Object invoke(Object obj) {
                return DeclarationUtilsKt.f(arrayList, (FirCallableSymbol) obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ List declaredProperties$default(FirClassSymbol firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.STATUS;
        }
        return declaredProperties(firClassSymbol, firSession, firResolvePhase);
    }

    public static final FirTypeScope dispatchReceiverScope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirCallableSymbol<?> firCallableSymbol) {
        sessionAndScopeSessionHolder.getClass();
        firCallableSymbol.getClass();
        ConeSimpleKotlinType dispatchReceiverType = firCallableSymbol.getDispatchReceiverType();
        if (dispatchReceiverType != null) {
            FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(sessionAndScopeSessionHolder, dispatchReceiverType, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
            return firTypeScopeScope == null ? FirTypeScope.Empty.INSTANCE : firTypeScopeScope;
        }
        w01.a("Required value was null.");
        return null;
    }

    public static Unit e(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirEnumEntrySymbol) {
            list.add(((FirEnumEntrySymbol) firVariableSymbol).getFir());
        }
        return Unit.INSTANCE;
    }

    public static Unit f(List list, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirPropertySymbol) {
            list.add(firCallableSymbol);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirCallableSymbol<?>>> flattenIntersectionsRecursively(MemberWithBaseScope<? extends FirCallableSymbol<?>> memberWithBaseScope) {
        memberWithBaseScope.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) memberWithBaseScope.getMember().getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return null;
        }
        if (!Intrinsics.areEqual(symbol.getOrigin(), FirDeclarationOrigin.IntersectionOverride.INSTANCE)) {
            return CollectionsKt.listOf(memberWithBaseScope);
        }
        List<MemberWithBaseScope<FirCallableSymbol<?>>> directOverriddenMembersWithBaseScope = FirTypeScopeKt.getDirectOverriddenMembersWithBaseScope(memberWithBaseScope.getBaseScope(), memberWithBaseScope.getMember());
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = directOverriddenMembersWithBaseScope.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, flattenIntersectionsRecursively((MemberWithBaseScope) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirCallableSymbol<?>>> flattenPhantomIntersectionsRecursively(MemberWithBaseScope<? extends FirCallableSymbol<?>> memberWithBaseScope) {
        memberWithBaseScope.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) memberWithBaseScope.getMember().getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        DeclarationSymbolMarker symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return null;
        }
        if (!(symbol instanceof FirIntersectionCallableSymbol) || ((FirIntersectionCallableSymbol) symbol).getContainsMultipleNonSubsumed()) {
            return CollectionsKt.listOf(memberWithBaseScope);
        }
        List<MemberWithBaseScope<FirCallableSymbol<?>>> directOverriddenMembersWithBaseScope = FirTypeScopeKt.getDirectOverriddenMembersWithBaseScope(memberWithBaseScope.getBaseScope(), memberWithBaseScope.getMember());
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = directOverriddenMembersWithBaseScope.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, flattenPhantomIntersectionsRecursively((MemberWithBaseScope) it.next()));
        }
        return arrayList;
    }

    public static final FirRegularClassSymbol fullyExpandedClass(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        while (!(firClassLikeSymbol instanceof FirRegularClassSymbol)) {
            if (firClassLikeSymbol instanceof FirAnonymousObjectSymbol) {
                return null;
            }
            if (!(firClassLikeSymbol instanceof FirTypeAliasSymbol)) {
                bu8.a();
                return null;
            }
            FirResolvedTypeRef resolvedExpandedTypeRef = ((FirTypeAliasSymbol) firClassLikeSymbol).getResolvedExpandedTypeRef();
            if (resolvedExpandedTypeRef == null) {
                resolvedExpandedTypeRef = null;
            }
            ConeKotlinType coneType = resolvedExpandedTypeRef != null ? resolvedExpandedTypeRef.getConeType() : null;
            if (!(coneType instanceof ConeClassLikeType)) {
                coneType = null;
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
            if (coneClassLikeType == null || (firClassLikeSymbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType, firSession)) == null) {
                return null;
            }
        }
        return (FirRegularClassSymbol) firClassLikeSymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<FirRegularClassSymbol> getComplementarySymbols(SessionHolder sessionHolder, FirRegularClassSymbol firRegularClassSymbol) {
        Collection collectionEmptyList;
        sessionHolder.getClass();
        firRegularClassSymbol.getClass();
        List superTypes$default = SupertypeUtilsKt.getSuperTypes$default(firRegularClassSymbol, sessionHolder.getSession(), false, false, false, null, 30, null);
        LinkedHashSet<FirRegularClassSymbol> linkedHashSet = new LinkedHashSet();
        Iterator it = superTypes$default.iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, (ConeClassLikeType) it.next());
            if (regularClassSymbol != null) {
                linkedHashSet.add(regularClassSymbol);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (FirRegularClassSymbol firRegularClassSymbol2 : linkedHashSet) {
            if (firRegularClassSymbol2.getResolvedStatus().getModality() == Modality.SEALED) {
                List<ClassId> sealedClassInheritors = SealedClassInheritorsKt.getSealedClassInheritors((FirRegularClass) firRegularClassSymbol2.getFir(), sessionHolder.getSession());
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it2 = sealedClassInheritors.iterator();
                while (it2.hasNext()) {
                    FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(sessionHolder, (ClassId) it2.next());
                    FirRegularClassSymbol firRegularClassSymbol3 = symbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) symbol : null;
                    if (firRegularClassSymbol3 != null) {
                        arrayList2.add(firRegularClassSymbol3);
                    }
                }
                collectionEmptyList = new ArrayList();
                for (Object obj : arrayList2) {
                    FirRegularClassSymbol firRegularClassSymbol4 = (FirRegularClassSymbol) obj;
                    if (!Intrinsics.areEqual(firRegularClassSymbol4, firRegularClassSymbol) && !linkedHashSet.contains(firRegularClassSymbol4)) {
                        collectionEmptyList.add(obj);
                    }
                }
            } else {
                collectionEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, collectionEmptyList);
        }
        return arrayList;
    }

    public static final FirRegularClassSymbol getConstructedClass(FirConstructorSymbol firConstructorSymbol, FirSession firSession) {
        firConstructorSymbol.getClass();
        firSession.getClass();
        return ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(firConstructorSymbol.getResolvedReturnTypeRef().getConeType(), firSession, (Function1) null, 2, (Object) null), firSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirCallableSymbol<?>>> getNonSubsumedNonPhantomOverriddenSymbols(Collection<? extends MemberWithBaseScope<? extends FirCallableSymbol<?>>> collection) {
        collection.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, flattenPhantomIntersectionsRecursively((MemberWithBaseScope) it.next()));
        }
        List<MemberWithBaseScope<FirCallableSymbol<?>>> listNonSubsumed = nonSubsumed(arrayList);
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listNonSubsumed) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((MemberWithBaseScope) obj).getMember().getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return null;
            }
            if (hashSet.add(symbol)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final List<FirCallableSymbol<?>> getNonSubsumedOverriddenSymbols(MemberWithBaseScope<? extends FirCallableSymbol<?>> memberWithBaseScope) {
        memberWithBaseScope.getClass();
        List<MemberWithBaseScope<FirCallableSymbol<?>>> listNonSubsumed = nonSubsumed(flattenIntersectionsRecursively(memberWithBaseScope));
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listNonSubsumed) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((MemberWithBaseScope) obj).getMember().getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return null;
            }
            if (hashSet.add(symbol)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((MemberWithBaseScope) it.next()).getMember());
        }
        return arrayList2;
    }

    public static final boolean isAnnotationConstructor(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        if (!(firBasedSymbol instanceof FirConstructorSymbol)) {
            return false;
        }
        FirRegularClassSymbol constructedClass = getConstructedClass((FirConstructorSymbol) firBasedSymbol, firSession);
        return (constructedClass != null ? constructedClass.getClassKind() : null) == ClassKind.ANNOTATION_CLASS;
    }

    public static final boolean isEquals(FirNamedFunctionSymbol firNamedFunctionSymbol, FirSession firSession) {
        firNamedFunctionSymbol.getClass();
        firSession.getClass();
        if (Intrinsics.areEqual(firNamedFunctionSymbol.getName(), OperatorNameConventions.EQUALS) && firNamedFunctionSymbol.getValueParameterSymbols().size() == 1 && firNamedFunctionSymbol.getContextParameterSymbols().isEmpty() && firNamedFunctionSymbol.getReceiverParameterSymbol() == null) {
            return ConeBuiltinTypeUtilsKt.isNullableAny(TypeExpansionUtilsKt.fullyExpandedType$default(((FirValueParameterSymbol) CollectionsKt.first(firNamedFunctionSymbol.getValueParameterSymbols())).getResolvedReturnTypeRef().getConeType(), firSession, (Function1) null, 2, (Object) null));
        }
        return false;
    }

    public static final boolean isInlinable(FirValueParameter firValueParameter, FirSession firSession) {
        FunctionTypeKind functionTypeKindFunctionTypeKind$default;
        firValueParameter.getClass();
        firSession.getClass();
        if (firValueParameter.getIsNoinline()) {
            return false;
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), firSession, (Function1) null, 2, (Object) null);
        return (ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType$default) || (functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default(coneKotlinTypeFullyExpandedType$default, firSession, false, 2, (Object) null)) == null || !functionTypeKindFunctionTypeKind$default.isInlineable()) ? false : true;
    }

    public static final boolean isInlineOrValueClass(FirRegularClassSymbol firRegularClassSymbol) {
        firRegularClassSymbol.getClass();
        if (firRegularClassSymbol.getClassKind() != ClassKind.CLASS) {
            return false;
        }
        return firRegularClassSymbol.getRawStatus().isInline() || firRegularClassSymbol.getRawStatus().isValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isJavaOrEnhancement(FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        firBasedSymbol.getClass();
        FirDeclarationOrigin origin = firBasedSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            FirDeclaration fir = firBasedSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !isJavaOrEnhancement(original)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ void isJavaOrEnhancement$annotations(FirDeclaration firDeclaration) {
    }

    @PrivateForInline
    public static /* synthetic */ void isJavaOrEnhancement$annotations(FirDeclarationOrigin firDeclarationOrigin) {
    }

    public static final boolean isPrimaryConstructorOfInlineOrValueClass(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirConstructorSymbol firConstructorSymbol;
        FirRegularClassSymbol constructedClass;
        firBasedSymbol.getClass();
        firSession.getClass();
        return (firBasedSymbol instanceof FirConstructorSymbol) && (constructedClass = getConstructedClass((firConstructorSymbol = (FirConstructorSymbol) firBasedSymbol), firSession)) != null && isInlineOrValueClass(constructedClass) && firConstructorSymbol.isPrimary();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final boolean isTrivialIntersection(MemberWithBaseScope<? extends FirCallableSymbol<?>> memberWithBaseScope) {
        memberWithBaseScope.getClass();
        List<MemberWithBaseScope<FirCallableSymbol<?>>> listNonSubsumed = nonSubsumed(FirTypeScopeKt.getDirectOverriddenMembersWithBaseScope(memberWithBaseScope.getBaseScope(), memberWithBaseScope.getMember()));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = listNonSubsumed.iterator();
        while (it.hasNext()) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((MemberWithBaseScope) it.next()).getMember().getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return false;
            }
            linkedHashSet.add(symbol);
        }
        return linkedHashSet.size() == 1;
    }

    public static final boolean itOrExpectHasDefaultParameterValue(FirFunction firFunction, int i) {
        FirFunctionSymbol<?> singleMatchedExpectForActualOrNull;
        FirFunction firFunction2;
        firFunction.getClass();
        return containsDefaultValue(firFunction, i) || !((singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull((FirFunctionSymbol<?>) firFunction.getSymbol())) == null || (firFunction2 = (FirFunction) singleMatchedExpectForActualOrNull.getFir()) == null || !containsDefaultValue(firFunction2, i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final List<MemberWithBaseScope<FirCallableSymbol<?>>> nonSubsumed(Collection<? extends MemberWithBaseScope<? extends FirCallableSymbol<?>>> collection) {
        collection.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MemberWithBaseScope<? extends FirCallableSymbol<?>> memberWithBaseScope : collection) {
            FirCallableSymbol firCallableSymbolComponent1 = memberWithBaseScope.component1();
            FirTypeScope firTypeScopeComponent2 = memberWithBaseScope.getBaseScope();
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbolComponent1.getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            final FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return null;
            }
            Function1 function1 = new Function1() { // from class: le3
                public final Object invoke(Object obj) {
                    return DeclarationUtilsKt.d(symbol, linkedHashSet, (FirCallableSymbol) obj);
                }
            };
            if (firCallableSymbolComponent1 instanceof FirNamedFunctionSymbol) {
                FirTypeScopeKt.processOverriddenFunctions(firTypeScopeComponent2, (FirNamedFunctionSymbol) firCallableSymbolComponent1, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) function1);
            } else if (firCallableSymbolComponent1 instanceof FirPropertySymbol) {
                FirTypeScopeKt.processOverriddenProperties(firTypeScopeComponent2, (FirPropertySymbol) firCallableSymbolComponent1, (Function1<? super FirPropertySymbol, ? extends ProcessorAction>) function1);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) ((MemberWithBaseScope) obj).component1().getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                if (originalForSubstitutionOverrideAttr2 == null) {
                    break;
                }
                firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration2.getSymbol();
            if (symbol2 == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return null;
            }
            if (!linkedHashSet.contains(symbol2)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final FirConstructorSymbol primaryConstructorIfAny(FirClass firClass, FirSession firSession) {
        Object next;
        firClass.getClass();
        firSession.getClass();
        Iterator<T> it = constructors(firClass, firSession).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((FirConstructorSymbol) next).isPrimary()) {
                return (FirConstructorSymbol) next;
            }
        }
        next = null;
        return (FirConstructorSymbol) next;
    }

    public static final void processAllClassifiers(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, Function1<? super FirClassifierSymbol<?>, Unit> function1) {
        firClassSymbol.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        function1.getClass();
        FirContainingNamesAwareScopeKt.processAllClassifiers(FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClassSymbol, firResolvePhase), function1);
    }

    public static /* synthetic */ void processAllClassifiers$default(FirClassSymbol firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.STATUS;
        }
        processAllClassifiers(firClassSymbol, firSession, firResolvePhase, function1);
    }

    public static final void processAllDeclarations(FirClass firClass, FirSession firSession, FirResolvePhase firResolvePhase, Function1<? super FirBasedSymbol<?>, Unit> function1) {
        firClass.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        function1.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope = FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClass, firResolvePhase);
        FirContainingNamesAwareScopeKt.processAllClassifiers(firContainingNamesAwareScopeDeclaredMemberScope, function1);
        FirContainingNamesAwareScopeKt.processAllCallables(firContainingNamesAwareScopeDeclaredMemberScope, function1);
        firContainingNamesAwareScopeDeclaredMemberScope.processDeclaredConstructors(function1);
        for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
            if (firDeclaration instanceof FirAnonymousInitializer) {
                function1.invoke(((FirAnonymousInitializer) firDeclaration).getSymbol());
            }
        }
    }

    public static /* synthetic */ void processAllDeclarations$default(FirClass firClass, FirSession firSession, FirResolvePhase firResolvePhase, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.STATUS;
        }
        processAllDeclarations(firClass, firSession, firResolvePhase, (Function1<? super FirBasedSymbol<?>, Unit>) function1);
    }

    public static final void processAllDeclaredCallables(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, Function1<? super FirCallableSymbol<?>, Unit> function1) {
        firClassSymbol.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        function1.getClass();
        FirContainingNamesAwareScopeKt.processAllCallables(FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClassSymbol, firResolvePhase), function1);
    }

    public static /* synthetic */ void processAllDeclaredCallables$default(FirClassSymbol firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.STATUS;
        }
        processAllDeclaredCallables(firClassSymbol, firSession, firResolvePhase, function1);
    }

    public static /* synthetic */ void isJavaOrEnhancement$annotations(FirBasedSymbol firBasedSymbol) {
    }

    public static /* synthetic */ void processAllDeclarations$default(FirClassSymbol firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.STATUS;
        }
        processAllDeclarations((FirClassSymbol<?>) firClassSymbol, firSession, firResolvePhase, (Function1<? super FirBasedSymbol<?>, Unit>) function1);
    }

    public static final List<FirConstructorSymbol> constructors(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        final ArrayList arrayList = new ArrayList();
        FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClassSymbol, (FirResolvePhase) null).processDeclaredConstructors(new Function1() { // from class: ne3
            public final Object invoke(Object obj) {
                return DeclarationUtilsKt.b(arrayList, (FirConstructorSymbol) obj);
            }
        });
        return arrayList;
    }

    public static final FirConstructorSymbol primaryConstructorIfAny(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        Object next;
        firClassSymbol.getClass();
        firSession.getClass();
        Iterator<T> it = constructors(firClassSymbol, firSession).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((FirConstructorSymbol) next).isPrimary()) {
                return (FirConstructorSymbol) next;
            }
        }
        next = null;
        return (FirConstructorSymbol) next;
    }

    public static final List<FirEnumEntry> collectEnumEntries(FirClass firClass, FirSession firSession) {
        firClass.getClass();
        firSession.getClass();
        firClass.getClassKind();
        ClassKind classKind = ClassKind.CLASS;
        final ArrayList arrayList = new ArrayList();
        FirContainingNamesAwareScopeKt.processAllProperties(FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClass, (FirResolvePhase) null), new Function1() { // from class: ke3
            public final Object invoke(Object obj) {
                return DeclarationUtilsKt.e(arrayList, (FirVariableSymbol) obj);
            }
        });
        return arrayList;
    }

    public static final boolean isJavaOrEnhancement(FirDeclaration firDeclaration) {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        firDeclaration.getClass();
        FirDeclarationOrigin origin = firDeclaration.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            FirCallableDeclaration firCallableDeclaration = firDeclaration instanceof FirCallableDeclaration ? (FirCallableDeclaration) firDeclaration : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !isJavaOrEnhancement(original)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isJavaOrEnhancement(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        return (firDeclarationOrigin instanceof FirDeclarationOrigin.Java) || Intrinsics.areEqual(firDeclarationOrigin, FirDeclarationOrigin.Enhancement.INSTANCE);
    }

    public static final FirRegularClassSymbol fullyExpandedClass(SessionHolder sessionHolder, FirClassLikeSymbol<?> firClassLikeSymbol) {
        sessionHolder.getClass();
        firClassLikeSymbol.getClass();
        return fullyExpandedClass(firClassLikeSymbol, sessionHolder.getSession());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void processAllDeclarations(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase, Function1<? super FirBasedSymbol<?>, Unit> function1) {
        firClassSymbol.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        function1.getClass();
        processAllDeclarations((FirClass) firClassSymbol.getFir(), firSession, firResolvePhase, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ScopeFunctionRequiresPrewarm
    public static final List<FirCallableSymbol<?>> getNonSubsumedOverriddenSymbols(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirIntersectionCallableSymbol firIntersectionCallableSymbol) {
        sessionAndScopeSessionHolder.getClass();
        firIntersectionCallableSymbol.getClass();
        if (firIntersectionCallableSymbol instanceof FirCallableSymbol) {
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firIntersectionCallableSymbol;
            return getNonSubsumedOverriddenSymbols(new MemberWithBaseScope(firCallableSymbol, dispatchReceiverScope(sessionAndScopeSessionHolder, firCallableSymbol)));
        }
        w01.a("Failed requirement.");
        return null;
    }

    public static final List<FirEnumEntrySymbol> getComplementarySymbols(SessionHolder sessionHolder, FirEnumEntrySymbol firEnumEntrySymbol) {
        List<FirEnumEntrySymbol> listCollectEnumEntries;
        sessionHolder.getClass();
        firEnumEntrySymbol.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, firEnumEntrySymbol.getResolvedReturnType());
        if (regularClassSymbol == null || (listCollectEnumEntries = collectEnumEntries(regularClassSymbol, sessionHolder.getSession())) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listCollectEnumEntries) {
            if (!Intrinsics.areEqual((FirEnumEntrySymbol) obj, firEnumEntrySymbol)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
