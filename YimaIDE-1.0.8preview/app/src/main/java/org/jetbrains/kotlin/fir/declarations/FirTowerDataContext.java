package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueMapper;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueStorage;
import org.jetbrains.kotlin.fir.resolve.LocalVariableScopeStorage;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitContextParameterValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0003\u0012\u0010\u0010\t\u001a\f\u0012\u0004\u0012\u00020\n0\u0003j\u0002`\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010B\t\b\u0016¢\u0006\u0004\b\u000f\u0010\u0011J\u0016\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\nJ\u0014\u0010\"\u001a\u00020\u00002\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040$J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\nJ\u001c\u0010'\u001a\u00020\u00002\b\u0010(\u001a\u0004\u0018\u00010)2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+J\u001e\u0010,\u001a\u00020\u00002\b\u0010(\u001a\u0004\u0018\u00010)2\f\u0010*\u001a\b\u0012\u0002\b\u0003\u0018\u00010+J\u0018\u0010-\u001a\u00020\u00002\u0010\u0010.\u001a\f\u0012\u0004\u0012\u00020/0$j\u0002`0J\u000e\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u000203J\u0010\u00104\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u000106J\u000e\u00107\u001a\u00020\u00002\u0006\u00108\u001a\u000209J\u0010\u0010:\u001a\u00020\u00042\u0006\u00108\u001a\u000209H\u0002J\u000e\u0010;\u001a\u00020\u00002\u0006\u00105\u001a\u000206J\u0010\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0004H\u0002J\u0016\u0010>\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00040$H\u0002J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020BJ\"\u0010C\u001a\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u000f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0006HÆ\u0003J\u0013\u0010F\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0003HÆ\u0003J\u0013\u0010G\u001a\f\u0012\u0004\u0012\u00020\n0\u0003j\u0002`\u000bHÆ\u0003J\u000f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u000eHÆ\u0003Je\u0010J\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0012\b\u0002\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00032\u0012\b\u0002\u0010\t\u001a\f\u0012\u0004\u0012\u00020\n0\u0003j\u0002`\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÂ\u0001J\u0014\u0010K\u001a\u00020B2\b\u0010L\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010M\u001a\u00020NHÖ\u0081\u0004J\n\u0010O\u001a\u00020PHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001b\u0010\t\u001a\f\u0012\u0004\u0012\u00020\n0\u0003j\u0002`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006Q"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", Argument.Delimiters.none, "towerDataElements", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "implicitValueStorage", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "classesUnderInitialization", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "localScopes", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirLocalScopes;", "nonLocalTowerDataElements", "localVariableScopeStorage", "Lorg/jetbrains/kotlin/fir/resolve/LocalVariableScopeStorage;", "<init>", "(Lkotlinx/collections/immutable/PersistentList;Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;Lkotlinx/collections/immutable/PersistentList;Lkotlinx/collections/immutable/PersistentList;Lkotlinx/collections/immutable/PersistentList;Lorg/jetbrains/kotlin/fir/resolve/LocalVariableScopeStorage;)V", "()V", "getTowerDataElements", "()Lkotlinx/collections/immutable/PersistentList;", "getImplicitValueStorage", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getClassesUnderInitialization", "getLocalScopes", "getNonLocalTowerDataElements", "getLocalVariableScopeStorage", "()Lorg/jetbrains/kotlin/fir/resolve/LocalVariableScopeStorage;", "addLocalVariable", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "setLastLocalScope", "newLastScope", "addNonLocalTowerDataElements", "newElements", Argument.Delimiters.none, "addLocalScope", "localScope", "addReceiver", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "implicitReceiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "addReceiverIfNotNull", "addContextGroups", "contextParameterGroup", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitContextParameterValue;", "Lorg/jetbrains/kotlin/fir/declarations/ContextParameterGroup;", "addAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "addNonLocalScopeIfNotNull", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "addCompanionAndStaticScopes", "towerElementsForClass", "Lorg/jetbrains/kotlin/fir/declarations/TowerElementsForClass;", "towerDataElementForStaticScope", "addNonLocalScope", "addNonLocalScopeElement", "element", "addNonLocalScopeElements", "elements", "createSnapshot", "keepMutable", Argument.Delimiters.none, "replaceTowerDataElements", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirTowerDataContext {
    private final PersistentList<FirClassSymbol<?>> classesUnderInitialization;
    private final ImplicitValueStorage implicitValueStorage;
    private final PersistentList<FirLocalScope> localScopes;
    private final LocalVariableScopeStorage localVariableScopeStorage;
    private final PersistentList<FirTowerDataElement> nonLocalTowerDataElements;
    private final PersistentList<FirTowerDataElement> towerDataElements;

    public FirTowerDataContext() {
        this(ExtensionsKt.persistentListOf(), new ImplicitValueStorage(), ExtensionsKt.persistentListOf(), ExtensionsKt.persistentListOf(), ExtensionsKt.persistentListOf(), new LocalVariableScopeStorage());
    }

    private final FirTowerDataContext addNonLocalScopeElement(FirTowerDataElement element) {
        return copy$default(this, this.towerDataElements.add(element), null, null, null, this.nonLocalTowerDataElements.add(element), null, 46, null);
    }

    private final FirTowerDataContext addNonLocalScopeElements(List<FirTowerDataElement> elements) {
        List<FirTowerDataElement> list = elements;
        return copy$default(this, this.towerDataElements.addAll(list), null, null, null, this.nonLocalTowerDataElements.addAll(list), null, 46, null);
    }

    private final FirTowerDataContext copy(PersistentList<FirTowerDataElement> towerDataElements, ImplicitValueStorage implicitValueStorage, PersistentList<? extends FirClassSymbol<?>> classesUnderInitialization, PersistentList<FirLocalScope> localScopes, PersistentList<FirTowerDataElement> nonLocalTowerDataElements, LocalVariableScopeStorage localVariableScopeStorage) {
        return new FirTowerDataContext(towerDataElements, implicitValueStorage, classesUnderInitialization, localScopes, nonLocalTowerDataElements, localVariableScopeStorage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirTowerDataContext copy$default(FirTowerDataContext firTowerDataContext, PersistentList persistentList, ImplicitValueStorage implicitValueStorage, PersistentList persistentList2, PersistentList persistentList3, PersistentList persistentList4, LocalVariableScopeStorage localVariableScopeStorage, int i, Object obj) {
        if ((i & 1) != 0) {
            persistentList = firTowerDataContext.towerDataElements;
        }
        if ((i & 2) != 0) {
            implicitValueStorage = firTowerDataContext.implicitValueStorage;
        }
        if ((i & 4) != 0) {
            persistentList2 = firTowerDataContext.classesUnderInitialization;
        }
        if ((i & 8) != 0) {
            persistentList3 = firTowerDataContext.localScopes;
        }
        if ((i & 16) != 0) {
            persistentList4 = firTowerDataContext.nonLocalTowerDataElements;
        }
        if ((i & 32) != 0) {
            localVariableScopeStorage = firTowerDataContext.localVariableScopeStorage;
        }
        PersistentList persistentList5 = persistentList4;
        LocalVariableScopeStorage localVariableScopeStorage2 = localVariableScopeStorage;
        return firTowerDataContext.copy(persistentList, implicitValueStorage, persistentList2, persistentList3, persistentList5, localVariableScopeStorage2);
    }

    private final FirTowerDataElement towerDataElementForStaticScope(TowerElementsForClass towerElementsForClass) {
        return new FirTowerDataElement(towerElementsForClass.getStaticScope(), null, null, false, towerElementsForClass.getStaticScopeOwnerSymbol(), 4, null);
    }

    public final FirTowerDataContext addAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
        anonymousInitializer.getClass();
        FirBasedSymbol<?> containingDeclarationSymbol = anonymousInitializer.getContainingDeclarationSymbol();
        FirClassSymbol firClassSymbol = containingDeclarationSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingDeclarationSymbol : null;
        return firClassSymbol == null ? this : copy$default(this, null, null, this.classesUnderInitialization.add(firClassSymbol), null, null, null, 59, null);
    }

    public final FirTowerDataContext addCompanionAndStaticScopes(TowerElementsForClass towerElementsForClass) {
        towerElementsForClass.getClass();
        if (towerElementsForClass.getCompanionStaticScope() != null) {
            return towerElementsForClass.getHasStaticScopeOrOwnerSymbol() ? addNonLocalScopeElements(CollectionsKt.listOf(new FirTowerDataElement[]{ImplicitReceiverUtilsKt.asTowerDataElement(towerElementsForClass.getCompanionStaticScope(), false), towerDataElementForStaticScope(towerElementsForClass)})) : addNonLocalScope(towerElementsForClass.getCompanionStaticScope());
        }
        return towerElementsForClass.getHasStaticScopeOrOwnerSymbol() ? addNonLocalScopeElement(towerDataElementForStaticScope(towerElementsForClass)) : this;
    }

    public final FirTowerDataContext addContextGroups(List<ImplicitContextParameterValue> contextParameterGroup) {
        contextParameterGroup.getClass();
        if (contextParameterGroup.isEmpty()) {
            return this;
        }
        FirTowerDataElement firTowerDataElement = new FirTowerDataElement(null, null, contextParameterGroup, false, null, 16, null);
        return copy$default(this, this.towerDataElements.add(firTowerDataElement), this.implicitValueStorage.addAllContexts(contextParameterGroup), null, null, this.nonLocalTowerDataElements.add(firTowerDataElement), null, 44, null);
    }

    public final FirTowerDataContext addLocalScope(FirLocalScope localScope) {
        localScope.getClass();
        return copy$default(this, this.towerDataElements.add(ImplicitReceiverUtilsKt.asTowerDataElement(localScope, true)), null, null, this.localScopes.add(localScope), null, null, 54, null);
    }

    public final FirTowerDataContext addLocalVariable(FirVariable variable, FirSession session) {
        int iNextIndex;
        variable.getClass();
        session.getClass();
        FirLocalScope firLocalScope = (FirLocalScope) CollectionsKt.lastOrNull(this.localScopes);
        if (firLocalScope == null) {
            return this;
        }
        PersistentList<FirTowerDataElement> persistentList = this.towerDataElements;
        ListIterator listIterator = persistentList.listIterator(persistentList.size());
        while (listIterator.hasPrevious()) {
            if (((FirTowerDataElement) listIterator.previous()).getScope() == firLocalScope) {
                iNextIndex = listIterator.nextIndex();
                FirLocalScope firLocalScopeStoreVariable = firLocalScope.storeVariable(variable, session);
                PersistentList persistentList2 = this.towerDataElements.set(iNextIndex, ImplicitReceiverUtilsKt.asTowerDataElement(firLocalScopeStoreVariable, true));
                PersistentList<FirLocalScope> persistentList3 = this.localScopes;
                return copy$default(this, persistentList2, null, null, persistentList3.set(CollectionsKt.getLastIndex(persistentList3), firLocalScopeStoreVariable), null, this.localVariableScopeStorage.addLocalVariable(variable.getSymbol()), 22, null);
            }
        }
        iNextIndex = -1;
        FirLocalScope firLocalScopeStoreVariable2 = firLocalScope.storeVariable(variable, session);
        PersistentList persistentList4 = this.towerDataElements.set(iNextIndex, ImplicitReceiverUtilsKt.asTowerDataElement(firLocalScopeStoreVariable2, true));
        PersistentList<FirLocalScope> persistentList5 = this.localScopes;
        return copy$default(this, persistentList4, null, null, persistentList5.set(CollectionsKt.getLastIndex(persistentList5), firLocalScopeStoreVariable2), null, this.localVariableScopeStorage.addLocalVariable(variable.getSymbol()), 22, null);
    }

    public final FirTowerDataContext addNonLocalScope(FirScope scope) {
        scope.getClass();
        FirTowerDataElement firTowerDataElementAsTowerDataElement = ImplicitReceiverUtilsKt.asTowerDataElement(scope, false);
        return copy$default(this, this.towerDataElements.add(firTowerDataElementAsTowerDataElement), null, null, null, this.nonLocalTowerDataElements.add(firTowerDataElementAsTowerDataElement), null, 46, null);
    }

    public final FirTowerDataContext addNonLocalScopeIfNotNull(FirScope scope) {
        return scope == null ? this : addNonLocalScope(scope);
    }

    public final FirTowerDataContext addNonLocalTowerDataElements(List<FirTowerDataElement> newElements) {
        newElements.getClass();
        List<FirTowerDataElement> list = newElements;
        PersistentList persistentListAddAll = this.towerDataElements.addAll(list);
        ImplicitValueStorage implicitValueStorage = this.implicitValueStorage;
        List<FirTowerDataElement> list2 = newElements;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            ImplicitReceiverValue<?> implicitReceiver = ((FirTowerDataElement) it.next()).getImplicitReceiver();
            if (implicitReceiver != null) {
                arrayList.add(implicitReceiver);
            }
        }
        ImplicitValueStorage implicitValueStorageAddAllImplicitReceivers = implicitValueStorage.addAllImplicitReceivers(arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            List<ImplicitContextParameterValue> contextParameterGroup = ((FirTowerDataElement) it2.next()).getContextParameterGroup();
            if (contextParameterGroup == null) {
                contextParameterGroup = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList2, contextParameterGroup);
        }
        return copy$default(this, persistentListAddAll, implicitValueStorageAddAllImplicitReceivers.addAllContexts(arrayList2), null, null, this.nonLocalTowerDataElements.addAll(list), null, 44, null);
    }

    public final FirTowerDataContext addReceiver(Name name, ImplicitReceiverValue<?> implicitReceiverValue) {
        implicitReceiverValue.getClass();
        FirTowerDataElement firTowerDataElementAsTowerDataElement = ImplicitReceiverUtilsKt.asTowerDataElement(implicitReceiverValue);
        return copy$default(this, this.towerDataElements.add(firTowerDataElementAsTowerDataElement), this.implicitValueStorage.addImplicitReceiver(name, implicitReceiverValue), null, null, this.nonLocalTowerDataElements.add(firTowerDataElementAsTowerDataElement), null, 44, null);
    }

    public final FirTowerDataContext addReceiverIfNotNull(Name name, ImplicitReceiverValue<?> implicitReceiverValue) {
        return implicitReceiverValue == null ? this : addReceiver(name, implicitReceiverValue);
    }

    public final PersistentList<FirTowerDataElement> component1() {
        return this.towerDataElements;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ImplicitValueStorage getImplicitValueStorage() {
        return this.implicitValueStorage;
    }

    public final PersistentList<FirClassSymbol<?>> component3() {
        return this.classesUnderInitialization;
    }

    public final PersistentList<FirLocalScope> component4() {
        return this.localScopes;
    }

    public final PersistentList<FirTowerDataElement> component5() {
        return this.nonLocalTowerDataElements;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final LocalVariableScopeStorage getLocalVariableScopeStorage() {
        return this.localVariableScopeStorage;
    }

    public final FirTowerDataContext createSnapshot(final boolean keepMutable) {
        ImplicitValueMapper implicitValueMapper = new ImplicitValueMapper() { // from class: org.jetbrains.kotlin.fir.declarations.FirTowerDataContext$createSnapshot$implicitValueMapper$1
            private final HashMap<ImplicitValue<?>, ImplicitValue<?>> implicitValueCache = new HashMap<>();

            public final HashMap<ImplicitValue<?>, ImplicitValue<?>> getImplicitValueCache() {
                return this.implicitValueCache;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.ImplicitValueMapper
            public <S extends FirBasedSymbol<?>, T extends ImplicitValue<S>> T invoke(T value) {
                value.getClass();
                HashMap<ImplicitValue<?>, ImplicitValue<?>> map = this.implicitValueCache;
                boolean z = keepMutable;
                Object objCreateSnapshot = map.get(value);
                if (objCreateSnapshot == null) {
                    objCreateSnapshot = value.createSnapshot(z);
                    map.put((ImplicitValue<?>) value, (ImplicitValue<?>) objCreateSnapshot);
                }
                objCreateSnapshot.getClass();
                return (T) objCreateSnapshot;
            }
        };
        PersistentList<FirTowerDataElement> persistentList = this.towerDataElements;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(persistentList, 10));
        Iterator it = persistentList.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTowerDataElement) it.next()).createSnapshot$org_jetbrains_kotlin_semantics(keepMutable, implicitValueMapper));
        }
        PersistentList persistentList2 = ExtensionsKt.toPersistentList(arrayList);
        ImplicitValueStorage implicitValueStorageCreateSnapshot$org_jetbrains_kotlin_semantics = this.implicitValueStorage.createSnapshot$org_jetbrains_kotlin_semantics(implicitValueMapper);
        PersistentList persistentList3 = ExtensionsKt.toPersistentList(this.localScopes);
        PersistentList<FirTowerDataElement> persistentList4 = this.nonLocalTowerDataElements;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(persistentList4, 10));
        Iterator it2 = persistentList4.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((FirTowerDataElement) it2.next()).createSnapshot$org_jetbrains_kotlin_semantics(keepMutable, implicitValueMapper));
        }
        return copy$default(this, persistentList2, implicitValueStorageCreateSnapshot$org_jetbrains_kotlin_semantics, null, persistentList3, ExtensionsKt.toPersistentList(arrayList2), null, 36, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirTowerDataContext)) {
            return false;
        }
        FirTowerDataContext firTowerDataContext = (FirTowerDataContext) other;
        return Intrinsics.areEqual(this.towerDataElements, firTowerDataContext.towerDataElements) && Intrinsics.areEqual(this.implicitValueStorage, firTowerDataContext.implicitValueStorage) && Intrinsics.areEqual(this.classesUnderInitialization, firTowerDataContext.classesUnderInitialization) && Intrinsics.areEqual(this.localScopes, firTowerDataContext.localScopes) && Intrinsics.areEqual(this.nonLocalTowerDataElements, firTowerDataContext.nonLocalTowerDataElements) && Intrinsics.areEqual(this.localVariableScopeStorage, firTowerDataContext.localVariableScopeStorage);
    }

    public final PersistentList<FirClassSymbol<?>> getClassesUnderInitialization() {
        return this.classesUnderInitialization;
    }

    public final ImplicitValueStorage getImplicitValueStorage() {
        return this.implicitValueStorage;
    }

    public final PersistentList<FirLocalScope> getLocalScopes() {
        return this.localScopes;
    }

    public final LocalVariableScopeStorage getLocalVariableScopeStorage() {
        return this.localVariableScopeStorage;
    }

    public final PersistentList<FirTowerDataElement> getNonLocalTowerDataElements() {
        return this.nonLocalTowerDataElements;
    }

    public final PersistentList<FirTowerDataElement> getTowerDataElements() {
        return this.towerDataElements;
    }

    public int hashCode() {
        return (((((((((this.towerDataElements.hashCode() * 31) + this.implicitValueStorage.hashCode()) * 31) + this.classesUnderInitialization.hashCode()) * 31) + this.localScopes.hashCode()) * 31) + this.nonLocalTowerDataElements.hashCode()) * 31) + this.localVariableScopeStorage.hashCode();
    }

    public final FirTowerDataContext replaceTowerDataElements(PersistentList<FirTowerDataElement> towerDataElements, PersistentList<FirTowerDataElement> nonLocalTowerDataElements) {
        towerDataElements.getClass();
        nonLocalTowerDataElements.getClass();
        return copy$default(this, towerDataElements, null, null, null, nonLocalTowerDataElements, null, 46, null);
    }

    public final FirTowerDataContext setLastLocalScope(FirLocalScope newLastScope) {
        int iNextIndex;
        newLastScope.getClass();
        FirLocalScope firLocalScope = (FirLocalScope) CollectionsKt.last(this.localScopes);
        PersistentList<FirTowerDataElement> persistentList = this.towerDataElements;
        ListIterator listIterator = persistentList.listIterator(persistentList.size());
        while (listIterator.hasPrevious()) {
            if (((FirTowerDataElement) listIterator.previous()).getScope() == firLocalScope) {
                iNextIndex = listIterator.nextIndex();
                PersistentList persistentList2 = this.towerDataElements.set(iNextIndex, ImplicitReceiverUtilsKt.asTowerDataElement(newLastScope, true));
                PersistentList<FirLocalScope> persistentList3 = this.localScopes;
                return copy$default(this, persistentList2, null, null, persistentList3.set(CollectionsKt.getLastIndex(persistentList3), newLastScope), null, null, 54, null);
            }
        }
        iNextIndex = -1;
        PersistentList persistentList4 = this.towerDataElements.set(iNextIndex, ImplicitReceiverUtilsKt.asTowerDataElement(newLastScope, true));
        PersistentList<FirLocalScope> persistentList5 = this.localScopes;
        return copy$default(this, persistentList4, null, null, persistentList5.set(CollectionsKt.getLastIndex(persistentList5), newLastScope), null, null, 54, null);
    }

    public String toString() {
        return "FirTowerDataContext(towerDataElements=" + this.towerDataElements + ", implicitValueStorage=" + this.implicitValueStorage + ", classesUnderInitialization=" + this.classesUnderInitialization + ", localScopes=" + this.localScopes + ", nonLocalTowerDataElements=" + this.nonLocalTowerDataElements + ", localVariableScopeStorage=" + this.localVariableScopeStorage + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FirTowerDataContext(PersistentList<FirTowerDataElement> persistentList, ImplicitValueStorage implicitValueStorage, PersistentList<? extends FirClassSymbol<?>> persistentList2, PersistentList<FirLocalScope> persistentList3, PersistentList<FirTowerDataElement> persistentList4, LocalVariableScopeStorage localVariableScopeStorage) {
        this.towerDataElements = persistentList;
        this.implicitValueStorage = implicitValueStorage;
        this.classesUnderInitialization = persistentList2;
        this.localScopes = persistentList3;
        this.nonLocalTowerDataElements = persistentList4;
        this.localVariableScopeStorage = localVariableScopeStorage;
    }
}
