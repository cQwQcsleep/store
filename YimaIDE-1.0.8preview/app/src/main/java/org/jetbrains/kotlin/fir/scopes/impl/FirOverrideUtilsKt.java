package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.ScopeFunctionRequiresPrewarm;
import org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a,\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001H\u0007b\u0002\b\u0005\u001a,\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001H\u0007b\u0002\b\u0005\u001a{\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u00020\u0001\"\f\b\u0000\u0010\t*\u0006\u0012\u0002\b\u00030\n2\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u00020\u00012?\u0010\u000b\u001a;\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\t\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u000f0\fj\b\u0012\u0004\u0012\u0002H\t`\u0011¢\u0006\u0002\b\u0010H\u0007b\u0002\b\u0005\u001aj\u0010\u0012\u001a\u00020\u0013\"\f\b\u0000\u0010\t*\u0006\u0012\u0002\b\u00030\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\t0\u00022\u0006\u0010\u0015\u001a\u0002H\t29\u0010\u000b\u001a5\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\t\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u000f0\u0016\u0012\u0004\u0012\u00020\u000f0\fj\b\u0012\u0004\u0012\u0002H\t`\u0017¢\u0006\u0002\b\u0010¢\u0006\u0002\u0010\u0018\u001a:\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u00012\u0018\b\u0002\u0010\u001c\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u00130\u0016H\u0086\bø\u0001\u0000\u001aN\u0010\u0019\u001a\u0004\u0018\u00010\u001a\"\u0004\b\u0000\u0010\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\t0\u00012\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u0002H\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u00162\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00130\u0016H\u0086\bø\u0001\u0000\u001a:\u0010 \u001a\u0004\u0018\u00010\u001a\"\u0004\b\u0000\u0010\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\t0\u00012\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u0002H\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u0016H\u0086\bø\u0001\u0000\"\u0019\u0010\u001e\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"filterOutOverriddenFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "extractedOverridden", "Lorg/jetbrains/kotlin/fir/scopes/ScopeFunctionRequiresPrewarm;", "filterOutOverriddenProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "filterOutOverridden", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processAllOverridden", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "Lkotlin/ExtensionFunctionType;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessOverriddenWithBaseScope;", "overrides", Argument.Delimiters.none, "f", "gMember", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessAllOverridden;", "(Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function3;)Z", "chooseIntersectionVisibilityOrNull", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "nonSubsumedOverrides", "isAbstract", "toSymbol", "isAbstractAccordingToRawStatus", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "findMaxVisibilityOrNull", "extractedOverrides", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOverrideUtilsKt {

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt$filterOutOverriddenFunctions$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00631 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00631 INSTANCE = new C00631();

        public C00631() {
            super(3, FirTypeScope.class, "processDirectOverriddenFunctionsWithBaseScope", "processDirectOverriddenFunctionsWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firNamedFunctionSymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, function2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt$filterOutOverriddenProperties$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00641 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00641 INSTANCE = new C00641();

        public C00641() {
            super(3, FirTypeScope.class, "processDirectOverriddenPropertiesWithBaseScope", "processDirectOverriddenPropertiesWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firPropertySymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, function2);
        }
    }

    public static ProcessorAction b(FirCallableSymbol firCallableSymbol, Ref.BooleanRef booleanRef, FirCallableSymbol firCallableSymbol2) {
        firCallableSymbol2.getClass();
        if (!Intrinsics.areEqual(firCallableSymbol2, firCallableSymbol)) {
            return ProcessorAction.NEXT;
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <D> Visibility chooseIntersectionVisibilityOrNull(Collection<? extends D> collection, Function1<? super D, ? extends FirCallableSymbol<?>> function1, Function1<? super D, Boolean> function2) throws KotlinIllegalArgumentExceptionWithAttachments {
        collection.getClass();
        function1.getClass();
        function2.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (!((Boolean) function2.invoke(obj)).booleanValue() && !Intrinsics.areEqual(((FirCallableSymbol) function1.invoke(obj)).getCallableId(), StandardClassIds.Callables.INSTANCE.getClone())) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((FirCallableSymbol) function1.invoke(it.next())).getRawStatus().getVisibility());
            }
            return (Visibility) CollectionsKt.singleOrNull(linkedHashSet);
        }
        Visibility visibility = Visibilities.Private.INSTANCE;
        Iterator<? extends D> it2 = collection.iterator();
        while (it2.hasNext()) {
            D fir = ((FirCallableSymbol) function1.invoke(it2.next())).getFir();
            fir.getClass();
            Visibility visibility2 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
            Integer numCompare = Visibilities.INSTANCE.compare(visibility2, visibility);
            if (numCompare == null) {
                return null;
            }
            if (numCompare.intValue() > 0) {
                visibility = visibility2;
            }
        }
        return visibility;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Visibility chooseIntersectionVisibilityOrNull$default(Collection collection, Function1 function1, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 2) != 0) {
            function1 = new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt.chooseIntersectionVisibilityOrNull.1
                public Object get(Object obj2) {
                    return Boolean.valueOf(FirOverrideUtilsKt.isAbstractAccordingToRawStatus((FirCallableSymbol) obj2));
                }
            };
        }
        collection.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : collection) {
            if (!((Boolean) function1.invoke(obj2)).booleanValue() && !Intrinsics.areEqual(((FirCallableSymbol) obj2).getCallableId(), StandardClassIds.Callables.INSTANCE.getClone())) {
                arrayList.add(obj2);
            }
        }
        if (!arrayList.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((FirCallableSymbol) it.next()).getRawStatus().getVisibility());
            }
            return (Visibility) CollectionsKt.singleOrNull(linkedHashSet);
        }
        Visibility visibility = Visibilities.Private.INSTANCE;
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            D fir = ((FirCallableSymbol) it2.next()).getFir();
            fir.getClass();
            Visibility visibility2 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
            Integer numCompare = Visibilities.INSTANCE.compare(visibility2, visibility);
            if (numCompare == null) {
                return null;
            }
            if (numCompare.intValue() > 0) {
                visibility = visibility2;
            }
        }
        return visibility;
    }

    @ScopeFunctionRequiresPrewarm
    public static final <D extends FirCallableSymbol<?>> Collection<MemberWithBaseScope<D>> filterOutOverridden(Collection<? extends MemberWithBaseScope<? extends D>> collection, final Function3<? super FirTypeScope, ? super D, ? super Function2<? super D, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> function3) {
        collection.getClass();
        function3.getClass();
        Collection<? extends MemberWithBaseScope<? extends D>> collection2 = collection;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection2) {
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) obj;
            if (!collection2.isEmpty()) {
                Iterator<T> it = collection2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        MemberWithBaseScope memberWithBaseScope2 = (MemberWithBaseScope) it.next();
                        if (memberWithBaseScope != memberWithBaseScope2 && overrides(memberWithBaseScope2, memberWithBaseScope.getMember(), new Function3() { // from class: pb5
                            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                                return FirOverrideUtilsKt.filterOutOverridden$lambda$0$0$0(function3, (FirTypeScope) obj2, (FirCallableSymbol) obj3, (Function1) obj4);
                            }
                        })) {
                            break;
                        }
                    }
                }
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ProcessorAction filterOutOverridden$lambda$0$0$0(Function3 function3, FirTypeScope firTypeScope, FirCallableSymbol firCallableSymbol, Function1 function1) {
        firTypeScope.getClass();
        firCallableSymbol.getClass();
        function1.getClass();
        return FirTypeScopeKt.processAllOverriddenCallables(firTypeScope, firCallableSymbol, function1, function3);
    }

    @ScopeFunctionRequiresPrewarm
    public static final Collection<MemberWithBaseScope<FirNamedFunctionSymbol>> filterOutOverriddenFunctions(Collection<? extends MemberWithBaseScope<? extends FirNamedFunctionSymbol>> collection) {
        collection.getClass();
        return filterOutOverridden(collection, C00631.INSTANCE);
    }

    @ScopeFunctionRequiresPrewarm
    public static final Collection<MemberWithBaseScope<FirPropertySymbol>> filterOutOverriddenProperties(Collection<? extends MemberWithBaseScope<? extends FirPropertySymbol>> collection) {
        collection.getClass();
        return filterOutOverridden(collection, C00641.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <D> Visibility findMaxVisibilityOrNull(Collection<? extends D> collection, Function1<? super D, ? extends FirCallableSymbol<?>> function1) throws KotlinIllegalArgumentExceptionWithAttachments {
        collection.getClass();
        function1.getClass();
        Visibility visibility = Visibilities.Private.INSTANCE;
        Iterator<? extends D> it = collection.iterator();
        while (it.hasNext()) {
            D fir = ((FirCallableSymbol) function1.invoke(it.next())).getFir();
            fir.getClass();
            Visibility visibility2 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
            Integer numCompare = Visibilities.INSTANCE.compare(visibility2, visibility);
            if (numCompare == null) {
                return null;
            }
            if (numCompare.intValue() > 0) {
                visibility = visibility2;
            }
        }
        return visibility;
    }

    public static final boolean isAbstractAccordingToRawStatus(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirPropertyAccessorSymbol) {
            firCallableSymbol = ((FirPropertyAccessorSymbol) firCallableSymbol).getPropertySymbol();
        }
        if (firCallableSymbol.getRawStatus() instanceof FirResolvedDeclarationStatus) {
            return firCallableSymbol.getRawStatus().getModality() == Modality.ABSTRACT;
        }
        w01.a("Failed requirement.");
        return false;
    }

    public static final <D extends FirCallableSymbol<?>> boolean overrides(MemberWithBaseScope<? extends D> memberWithBaseScope, final D d, Function3<? super FirTypeScope, ? super D, ? super Function1<? super D, ? extends ProcessorAction>, ? extends ProcessorAction> function3) {
        memberWithBaseScope.getClass();
        d.getClass();
        function3.getClass();
        FirCallableSymbol firCallableSymbolComponent1 = memberWithBaseScope.component1();
        FirTypeScope baseScope = memberWithBaseScope.getBaseScope();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        function3.invoke(baseScope, firCallableSymbolComponent1, new Function1() { // from class: qb5
            public final Object invoke(Object obj) {
                return FirOverrideUtilsKt.b(d, booleanRef, (FirCallableSymbol) obj);
            }
        });
        return booleanRef.element;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Visibility chooseIntersectionVisibilityOrNull(Collection<? extends FirCallableSymbol<?>> collection, Function1<? super FirCallableSymbol<?>, Boolean> function1) throws KotlinIllegalArgumentExceptionWithAttachments {
        collection.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (!((Boolean) function1.invoke(obj)).booleanValue() && !Intrinsics.areEqual(((FirCallableSymbol) obj).getCallableId(), StandardClassIds.Callables.INSTANCE.getClone())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            Visibility visibility = Visibilities.Private.INSTANCE;
            Iterator<? extends FirCallableSymbol<?>> it = collection.iterator();
            while (it.hasNext()) {
                Object fir = it.next().getFir();
                fir.getClass();
                Visibility visibility2 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
                Integer numCompare = Visibilities.INSTANCE.compare(visibility2, visibility);
                if (numCompare == null) {
                    return null;
                }
                if (numCompare.intValue() > 0) {
                    visibility = visibility2;
                }
            }
            return visibility;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            linkedHashSet.add(((FirCallableSymbol) it2.next()).getRawStatus().getVisibility());
        }
        return (Visibility) CollectionsKt.singleOrNull(linkedHashSet);
    }
}
