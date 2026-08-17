package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableSet;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.FirReceiversKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitContextParameterValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitDispatchReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.util.PersistentSetMultimap;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BO\b\u0002\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006\u0012\u001a\u0010\b\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\t¢\u0006\u0004\b\f\u0010\rB\t\b\u0016¢\u0006\u0004\b\f\u0010\u000eJ\u0018\u0010\u0017\u001a\u00020\u00002\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0010J\u001c\u0010\u0019\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u0004J\u0014\u0010\u001c\u001a\u00020\u00002\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0010JF\u0010\u001f\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\t*\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\t2\u0010\u0010\u001d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0010H\u0002JB\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006*\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002J\u001d\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\"2\b\u0010\u001a\u001a\u0004\u0018\u00010#H\u0086\u0002J\u0018\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\nJ\b\u0010&\u001a\u0004\u0018\u00010'J \u0010&\u001a\u0004\u0018\u00010'2\u0016\u0010(\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020*0)J\u0010\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0010J \u0010,\u001a\u00020-2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010.\u001a\u00020/H\u0007b\u0002\b0J\u0015\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\b4R\u0018\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", Argument.Delimiters.none, "implicitReceiverStack", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "implicitReceiversByLabel", "Lorg/jetbrains/kotlin/fir/util/PersistentSetMultimap;", "Lorg/jetbrains/kotlin/name/Name;", "implicitValuesBySymbol", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "<init>", "(Lkotlinx/collections/immutable/PersistentList;Lorg/jetbrains/kotlin/fir/util/PersistentSetMultimap;Lkotlinx/collections/immutable/PersistentMap;)V", "()V", "implicitReceivers", Argument.Delimiters.none, "getImplicitReceivers", "()Ljava/util/List;", "implicitValues", Argument.Delimiters.none, "getImplicitValues", "()Ljava/util/Collection;", "addAllImplicitReceivers", "receivers", "addImplicitReceiver", ModuleXmlParser.NAME, "value", "addAllContexts", "contextParameters", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitContextParameterValue;", "addAll", "putIfNameIsNotNull", "get", Argument.Delimiters.none, Argument.Delimiters.none, "getBySymbol", "symbol", "lastDispatchReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitDispatchReceiverValue;", "lookupCondition", "Lkotlin/Function1;", Argument.Delimiters.none, "receiversAsReversed", "replaceImplicitValueType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue$ImplicitValueInternals;", "createSnapshot", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueMapper;", "createSnapshot$org_jetbrains_kotlin_semantics", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitValueStorage {
    private final PersistentList<ImplicitReceiverValue<?>> implicitReceiverStack;
    private final PersistentSetMultimap<Name, ImplicitReceiverValue<?>> implicitReceiversByLabel;
    private final PersistentMap<FirBasedSymbol<?>, ImplicitValue<?>> implicitValuesBySymbol;

    public ImplicitValueStorage() {
        this(ExtensionsKt.persistentListOf(), new PersistentSetMultimap(), ExtensionsKt.persistentMapOf());
    }

    private final PersistentMap<FirBasedSymbol<?>, ImplicitValue<?>> addAll(PersistentMap<FirBasedSymbol<?>, ? extends ImplicitValue<?>> persistentMap, List<? extends ImplicitValue<?>> list) {
        Iterator<T> it = list.iterator();
        PersistentMap persistentMapPut = persistentMap;
        while (it.hasNext()) {
            ImplicitValue implicitValue = (ImplicitValue) it.next();
            persistentMapPut = persistentMapPut.put(implicitValue.getBoundSymbol(), implicitValue);
        }
        return persistentMapPut;
    }

    private final PersistentSetMultimap<Name, ImplicitReceiverValue<?>> putIfNameIsNotNull(PersistentSetMultimap<Name, ImplicitReceiverValue<?>> persistentSetMultimap, Name name, ImplicitReceiverValue<?> implicitReceiverValue) {
        return name != null ? persistentSetMultimap.put(name, implicitReceiverValue) : persistentSetMultimap;
    }

    public final ImplicitValueStorage addAllContexts(List<ImplicitContextParameterValue> contextParameters) {
        contextParameters.getClass();
        return contextParameters.isEmpty() ? this : new ImplicitValueStorage(this.implicitReceiverStack, this.implicitReceiversByLabel, addAll(this.implicitValuesBySymbol, contextParameters));
    }

    public final ImplicitValueStorage addAllImplicitReceivers(List<? extends ImplicitReceiverValue<?>> receivers) {
        receivers.getClass();
        Iterator<T> it = receivers.iterator();
        while (it.hasNext()) {
            this = this.addImplicitReceiver(null, (ImplicitReceiverValue) it.next());
        }
        return this;
    }

    public final ImplicitValueStorage addImplicitReceiver(Name name, ImplicitReceiverValue<?> value) {
        value.getClass();
        return new ImplicitValueStorage(this.implicitReceiverStack.add(value), putIfNameIsNotNull(this.implicitReceiversByLabel, name, value), this.implicitValuesBySymbol.put(value.getBoundSymbol(), value));
    }

    public final ImplicitValueStorage createSnapshot$org_jetbrains_kotlin_semantics(ImplicitValueMapper mapper) {
        mapper.getClass();
        PersistentList<ImplicitReceiverValue<?>> persistentList = this.implicitReceiverStack;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(persistentList, 10));
        Iterator it = persistentList.iterator();
        while (it.hasNext()) {
            arrayList.add((ImplicitReceiverValue) mapper.invoke((ImplicitReceiverValue) it.next()));
        }
        PersistentList persistentList2 = ExtensionsKt.toPersistentList(arrayList);
        ImmutableSet<Map.Entry<Name, PersistentSet<ImplicitReceiverValue<?>>>> entries = this.implicitReceiversByLabel.getEntries();
        PersistentSetMultimap persistentSetMultimap = new PersistentSetMultimap();
        Iterator it2 = entries.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            Name name = (Name) entry.getKey();
            Iterator it3 = ((PersistentSet) entry.getValue()).iterator();
            while (it3.hasNext()) {
                persistentSetMultimap = persistentSetMultimap.put(name, mapper.invoke((ImplicitReceiverValue) it3.next()));
            }
        }
        PersistentMap<FirBasedSymbol<?>, ImplicitValue<?>> persistentMap = this.implicitValuesBySymbol;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(persistentMap.size()));
        for (Map.Entry entry2 : persistentMap.entrySet()) {
            linkedHashMap.put(entry2.getKey(), mapper.invoke((ImplicitValue) entry2.getValue()));
        }
        return new ImplicitValueStorage(persistentList2, persistentSetMultimap, ExtensionsKt.toPersistentMap(linkedHashMap));
    }

    public final Set<ImplicitReceiverValue<?>> get(String name) {
        Object objPrevious;
        if (name == null) {
            PersistentList<ImplicitReceiverValue<?>> persistentList = this.implicitReceiverStack;
            ListIterator listIterator = persistentList.listIterator(persistentList.size());
            do {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                }
                objPrevious = listIterator.previous();
            } while (FirReceiversKt.producesInapplicableCandidate((ImplicitReceiverValue) objPrevious));
            ImplicitReceiverValue implicitReceiverValue = (ImplicitReceiverValue) objPrevious;
            if (implicitReceiverValue == null) {
                implicitReceiverValue = (ImplicitReceiverValue) CollectionsKt.lastOrNull(this.implicitReceiverStack);
            }
            Set<ImplicitReceiverValue<?>> of = implicitReceiverValue != null ? SetsKt.setOf(implicitReceiverValue) : null;
            return of == null ? SetsKt.emptySet() : of;
        }
        PersistentSetMultimap<Name, ImplicitReceiverValue<?>> persistentSetMultimap = this.implicitReceiversByLabel;
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        Set<ImplicitReceiverValue<?>> set = persistentSetMultimap.get(nameIdentifier);
        Set<ImplicitReceiverValue<?>> set2 = set;
        int i = 0;
        if (!(set2 instanceof Collection) || !set2.isEmpty()) {
            Iterator<T> it = set2.iterator();
            while (it.hasNext()) {
                if (FirReceiversKt.producesInapplicableCandidate((ImplicitReceiverValue) it.next()) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (i == set.size()) {
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : set) {
            if (!FirReceiversKt.producesInapplicableCandidate((ImplicitReceiverValue) obj)) {
                linkedHashSet.add(obj);
            }
        }
        return linkedHashSet;
    }

    public final ImplicitValue<?> getBySymbol(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        return (ImplicitValue) this.implicitValuesBySymbol.get(symbol);
    }

    public final List<ImplicitReceiverValue<?>> getImplicitReceivers() {
        return this.implicitReceiverStack;
    }

    public final Collection<ImplicitValue<?>> getImplicitValues() {
        return this.implicitValuesBySymbol.values();
    }

    public final ImplicitDispatchReceiverValue lastDispatchReceiver(Function1<? super ImplicitReceiverValue<?>, Boolean> lookupCondition) {
        Object objPrevious;
        lookupCondition.getClass();
        PersistentList<ImplicitReceiverValue<?>> persistentList = this.implicitReceiverStack;
        ArrayList arrayList = new ArrayList();
        for (Object obj : persistentList) {
            if (obj instanceof ImplicitDispatchReceiverValue) {
                arrayList.add(obj);
            }
        }
        ListIterator listIterator = arrayList.listIterator(arrayList.size());
        while (listIterator.hasPrevious()) {
            objPrevious = listIterator.previous();
            if (((Boolean) lookupCondition.invoke(objPrevious)).booleanValue()) {
                return (ImplicitDispatchReceiverValue) objPrevious;
            }
        }
        objPrevious = null;
        return (ImplicitDispatchReceiverValue) objPrevious;
    }

    public final List<ImplicitReceiverValue<?>> receiversAsReversed() {
        return CollectionsKt.asReversed(this.implicitReceiverStack);
    }

    @ImplicitValue.ImplicitValueInternals
    public final void replaceImplicitValueType(FirBasedSymbol<?> symbol, ConeKotlinType type) {
        symbol.getClass();
        type.getClass();
        ImplicitValue implicitValue = (ImplicitValue) this.implicitValuesBySymbol.get(symbol);
        if (implicitValue == null) {
            return;
        }
        implicitValue.updateTypeFromSmartcast(type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ImplicitValueStorage(PersistentList<? extends ImplicitReceiverValue<?>> persistentList, PersistentSetMultimap<Name, ImplicitReceiverValue<?>> persistentSetMultimap, PersistentMap<FirBasedSymbol<?>, ? extends ImplicitValue<?>> persistentMap) {
        this.implicitReceiverStack = persistentList;
        this.implicitReceiversByLabel = persistentSetMultimap;
        this.implicitValuesBySymbol = persistentMap;
    }

    public final ImplicitDispatchReceiverValue lastDispatchReceiver() {
        PersistentList<ImplicitReceiverValue<?>> persistentList = this.implicitReceiverStack;
        ArrayList arrayList = new ArrayList();
        for (Object obj : persistentList) {
            if (obj instanceof ImplicitDispatchReceiverValue) {
                arrayList.add(obj);
            }
        }
        return (ImplicitDispatchReceiverValue) CollectionsKt.lastOrNull(arrayList);
    }
}
