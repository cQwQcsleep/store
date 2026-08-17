package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001:\u0002\"#B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ7\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016R\u00020\u0006j\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\"\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u001a*\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J:\u0010\u001e\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u001b2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001fH\u0002¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnSealedClassExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "computeMissingCases", Argument.Delimiters.none, "c", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Collection;)V", "inferVariantsFromSubjectSmartCast", "subject", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "data", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnSealedClassExhaustivenessChecker$Info;", "collectAllSubclasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "collectAllSubclassesTo", Argument.Delimiters.none, "visited", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Info", "ConditionChecker", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class WhenOnSealedClassExhaustivenessChecker extends WhenExhaustivenessChecker {
    public static final WhenOnSealedClassExhaustivenessChecker INSTANCE = new WhenOnSealedClassExhaustivenessChecker();

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnSealedClassExhaustivenessChecker$Info;", Argument.Delimiters.none, "allSubclasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "checkedSubclasses", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Ljava/util/Set;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getAllSubclasses", "()Ljava/util/Set;", "getCheckedSubclasses", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Info {
        private final Set<FirClassSymbol<?>> allSubclasses;
        private final Set<FirClassSymbol<?>> checkedSubclasses;
        private final FirSession session;

        /* JADX WARN: Multi-variable type inference failed */
        public Info(Set<? extends FirClassSymbol<?>> set, Set<FirClassSymbol<?>> set2, FirSession firSession) {
            set.getClass();
            set2.getClass();
            firSession.getClass();
            this.allSubclasses = set;
            this.checkedSubclasses = set2;
            this.session = firSession;
        }

        public final Set<FirClassSymbol<?>> getAllSubclasses() {
            return this.allSubclasses;
        }

        public final Set<FirClassSymbol<?>> getCheckedSubclasses() {
            return this.checkedSubclasses;
        }

        public final FirSession getSession() {
            return this.session;
        }
    }

    private WhenOnSealedClassExhaustivenessChecker() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<FirClassSymbol<?>> collectAllSubclasses(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectAllSubclassesTo$default(INSTANCE, firClassSymbol, linkedHashSet, firSession, null, 4, null);
        return linkedHashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void collectAllSubclassesTo(FirClassSymbol<?> firClassSymbol, Set<FirClassSymbol<?>> set, FirSession firSession, Set<FirRegularClassSymbol> set2) {
        if (!(firClassSymbol instanceof FirRegularClassSymbol)) {
            set.add(firClassSymbol);
            return;
        }
        if (set2.add(firClassSymbol)) {
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) firClassSymbol;
            if (((FirMemberDeclaration) firRegularClassSymbol.getFir()).getStatus().getModality() != Modality.SEALED) {
                set.add(firClassSymbol);
                return;
            }
            if (Intrinsics.areEqual(ClassMembersKt.isJavaNonAbstractSealed((FirRegularClass) firRegularClassSymbol.getFir()), Boolean.TRUE)) {
                set.add(firClassSymbol);
            }
            Iterator<T> it = SealedClassInheritorsKt.getSealedClassInheritors((FirRegularClass) firRegularClassSymbol.getFir(), firSession).iterator();
            while (it.hasNext()) {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId((ClassId) it.next());
                FirRegularClassSymbol firRegularClassSymbol2 = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
                if (firRegularClassSymbol2 != null) {
                    INSTANCE.collectAllSubclassesTo(firRegularClassSymbol2, set, firSession, set2);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void collectAllSubclassesTo$default(WhenOnSealedClassExhaustivenessChecker whenOnSealedClassExhaustivenessChecker, FirClassSymbol firClassSymbol, Set set, FirSession firSession, Set set2, int i, Object obj) {
        if ((i & 4) != 0) {
            set2 = new LinkedHashSet();
        }
        whenOnSealedClassExhaustivenessChecker.collectAllSubclassesTo(firClassSymbol, set, firSession, set2);
    }

    private final void inferVariantsFromSubjectSmartCast(FirExpression subject, Info data) {
        if (subject instanceof FirSmartCastExpression) {
            for (DfaType dfaType : ((FirSmartCastExpression) subject).getLowerTypesFromSmartCast()) {
                FirBasedSymbol<?> symbol = dfaType instanceof DfaType.Cone ? ToSymbolUtilsKt.toSymbol(((DfaType.Cone) dfaType).getType(), data.getSession()) : dfaType instanceof DfaType.Symbol ? ((DfaType.Symbol) dfaType).getSymbol() : null;
                FirClassSymbol<?> firClassSymbol = symbol instanceof FirClassSymbol ? (FirClassSymbol) symbol : null;
                if (firClassSymbol != null) {
                    ConditionChecker.INSTANCE.processBranch(firClassSymbol, false, data);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public void computeMissingCases(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType, Collection<WhenMissingCase> collection) {
        Set<FirClassSymbol<?>> setCollectAllSubclasses;
        FirVariable subjectVariable;
        FirExpression initializer;
        sessionHolder.getClass();
        firWhenExpression.getClass();
        coneKotlinType.getClass();
        collection.getClass();
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(sessionHolder, coneKotlinType);
        if (classSymbol == null || (setCollectAllSubclasses = collectAllSubclasses(classSymbol, sessionHolder.getSession())) == null) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Info info = new Info(setCollectAllSubclasses, linkedHashSet, sessionHolder.getSession());
        if (LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.DataFlowBasedExhaustiveness) && (subjectVariable = firWhenExpression.getSubjectVariable()) != null && (initializer = subjectVariable.getInitializer()) != null) {
            INSTANCE.inferVariantsFromSubjectSmartCast(initializer, info);
        }
        firWhenExpression.accept(ConditionChecker.INSTANCE, info);
        Set setMinus = SetsKt.minus(setCollectAllSubclasses, linkedHashSet);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : setMinus) {
            if (((FirClassSymbol) obj).getClassKind() == ClassKind.ENUM_CLASS) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list = (List) pair.component1();
        List<FirClassSymbol> list2 = (List) pair.component2();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WhenOnEnumExhaustivenessChecker.INSTANCE.computeMissingCases(sessionHolder, firWhenExpression, ScopeUtilsKt.defaultType((FirClassSymbol) it.next()), collection);
        }
        for (FirClassSymbol firClassSymbol : list2) {
            collection.add(new WhenMissingCase.IsTypeCheckIsMissing(firClassSymbol.getClassId(), ((FirClass) firClassSymbol.getFir()).getClassKind().isSingleton(), firClassSymbol.getOwnTypeParameterSymbols().size()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public boolean isApplicable(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        FirRegularClass firRegularClass;
        sessionHolder.getClass();
        coneKotlinType.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, coneKotlinType);
        return ((regularClassSymbol == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null) ? null : firRegularClass.getStatus().getModality()) == Modality.SEALED;
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\"\u0010\r\u001a\u00020\u00062\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0002J$\u0010\u0012\u001a\u00020\u00062\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0002H\u0002J$\u0010\u0013\u001a\u00020\u00062\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0002H\u0002J*\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u0016*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0002H\u0002¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnSealedClassExhaustivenessChecker$ConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker$AbstractConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnSealedClassExhaustivenessChecker$Info;", "<init>", "()V", "visitEqualityOperatorCall", Argument.Delimiters.none, "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "data", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "processBranch", "symbolToCheck", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "isNegated", Argument.Delimiters.none, "processBranchUsingSubtyping", "processBranchUsingSealedInheritors", "info", "collectAllSuperclasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ConditionChecker extends WhenExhaustivenessChecker.AbstractConditionChecker<Info> {
        public static final ConditionChecker INSTANCE = new ConditionChecker();

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[FirOperation.values().length];
                try {
                    iArr[FirOperation.EQ.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FirOperation.IDENTITY.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[FirOperation.NOT_EQ.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[FirOperation.NOT_IDENTITY.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[FirOperation.IS.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[FirOperation.NOT_IS.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        private ConditionChecker() {
        }

        private final Set<FirClassSymbol<?>> collectAllSuperclasses(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, Info info) {
            if ((firBasedSymbol instanceof FirClassSymbol) && info.getAllSubclasses().contains(firBasedSymbol)) {
                ConeClassLikeLookupTag lookupTag = ((FirClassSymbol) firBasedSymbol).getLookupTag();
                Set<FirClassSymbol<?>> allSubclasses = info.getAllSubclasses();
                ArrayList arrayList = new ArrayList();
                for (Object obj : allSubclasses) {
                    if (obj instanceof FirRegularClassSymbol) {
                        arrayList.add(obj);
                    }
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (Object obj2 : arrayList) {
                    if (SupertypeUtilsKt.isSubclassOf((FirRegularClassSymbol) obj2, lookupTag, firSession, true, true)) {
                        linkedHashSet.add(obj2);
                    }
                }
                return linkedHashSet;
            }
            return SetsKt.emptySet();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0044 A[RETURN] */
        private final void processBranchUsingSealedInheritors(FirClassSymbol<?> symbolToCheck, boolean isNegated, Info info) {
            Set setCollectAllSubclasses = WhenOnSealedClassExhaustivenessChecker.INSTANCE.collectAllSubclasses(symbolToCheck, info.getSession());
            Set<FirClassSymbol<?>> setCollectAllSuperclasses = collectAllSuperclasses(symbolToCheck, info.getSession(), info);
            Set set = setCollectAllSubclasses;
            if (!(set instanceof Collection) || !set.isEmpty()) {
                Iterator it = set.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (info.getAllSubclasses().contains((FirClassSymbol) it.next())) {
                        }
                    } else if (setCollectAllSuperclasses.isEmpty()) {
                        return;
                    }
                }
            } else if (setCollectAllSuperclasses.isEmpty()) {
                return;
            }
            info.getCheckedSubclasses().addAll(isNegated ? SetsKt.minus(info.getAllSubclasses(), set) : SetsKt.plus(setCollectAllSubclasses, setCollectAllSuperclasses));
        }

        private final void processBranchUsingSubtyping(FirClassSymbol<?> symbolToCheck, boolean isNegated, Info data) {
            ArrayList arrayList;
            if (!isNegated) {
                Set<FirClassSymbol<?>> allSubclasses = data.getAllSubclasses();
                arrayList = new ArrayList();
                for (Object obj : allSubclasses) {
                    if (processBranchUsingSubtyping$isSubclassOf((FirClassSymbol) obj, data, symbolToCheck)) {
                        arrayList.add(obj);
                    }
                }
            } else {
                if (!isNegated) {
                    bu8.a();
                    return;
                }
                Set<FirClassSymbol<?>> allSubclasses2 = data.getAllSubclasses();
                arrayList = new ArrayList();
                for (Object obj2 : allSubclasses2) {
                    FirClassSymbol firClassSymbol = (FirClassSymbol) obj2;
                    if (!processBranchUsingSubtyping$isSubclassOf(firClassSymbol, data, symbolToCheck) && !processBranchUsingSubtyping$isSubclassOf(symbolToCheck, data, firClassSymbol)) {
                        arrayList.add(obj2);
                    }
                }
            }
            data.getCheckedSubclasses().addAll(arrayList);
        }

        private static final boolean processBranchUsingSubtyping$isSubclassOf(FirClassSymbol<?> firClassSymbol, Info info, FirClassSymbol<?> firClassSymbol2) {
            return SupertypeUtilsKt.isSubclassOf(firClassSymbol, firClassSymbol2.getLookupTag(), info.getSession(), false, true);
        }

        public final void processBranch(FirClassSymbol<?> symbolToCheck, boolean isNegated, Info data) {
            symbolToCheck.getClass();
            data.getClass();
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(data.getSession()).supportsFeature(LanguageFeature.ImprovedExhaustivenessChecksIn23)) {
                processBranchUsingSubtyping(symbolToCheck, isNegated, data);
            } else {
                processBranchUsingSealedInheritors(symbolToCheck, isNegated, data);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Info data) {
            boolean z;
            equalityOperatorCall.getClass();
            data.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[equalityOperatorCall.getOperation().ordinal()];
            if (i == 1 || i == 2) {
                z = false;
            } else if (i != 3 && i != 4) {
                return;
            } else {
                z = true;
            }
            FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(equalityOperatorCall.getArgumentList().getArguments().get(1));
            FirRegularClassSymbol companionObjectSymbol = null;
            FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
            if (firResolvedQualifier == null) {
                return;
            }
            FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
            FirRegularClassSymbol firRegularClassSymbol = symbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) symbol : null;
            FirRegularClass firRegularClass = firRegularClassSymbol != null ? (FirRegularClass) firRegularClassSymbol.getFir() : null;
            if ((firRegularClass != null ? firRegularClass.getClassKind() : null) == ClassKind.OBJECT) {
                companionObjectSymbol = firRegularClass.getSymbol();
            } else if (firRegularClass != null) {
                companionObjectSymbol = firRegularClass.getCompanionObjectSymbol();
            }
            if (companionObjectSymbol == null) {
                return;
            }
            processBranch(companionObjectSymbol, z, data);
        }

        public void visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, Info data) {
            boolean z;
            typeOperatorCall.getClass();
            data.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[typeOperatorCall.getOperation().ordinal()];
            if (i == 5) {
                z = false;
            } else if (i != 6) {
                return;
            } else {
                z = true;
            }
            FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(typeOperatorCall.getConversionTypeRef()), data.getSession(), (Function1) null, 2, (Object) null), data.getSession());
            if (classSymbol == null) {
                return;
            }
            processBranch(classSymbol, z, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitTypeOperatorCall(FirTypeOperatorCall firTypeOperatorCall, Object obj) {
            visitTypeOperatorCall(firTypeOperatorCall, (Info) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitEqualityOperatorCall(FirEqualityOperatorCall firEqualityOperatorCall, Object obj) {
            visitEqualityOperatorCall(firEqualityOperatorCall, (Info) obj);
            return Unit.INSTANCE;
        }
    }
}
