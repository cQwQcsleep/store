package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001QB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010M\u001a\u00020\u00002\u0010\u0010N\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030P0OR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\rR\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\rR\u001d\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\rR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001d\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0013R\u001d\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020)0\u000f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0013R\u001d\u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0\u000f¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0013R\u001d\u0010/\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002010\u000f¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0013R\u001d\u00103\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002010\u000f¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0013R\u001d\u00106\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020&0\u000f¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0013R\u001d\u00109\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020&0\u000f¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0013R\u001d\u0010;\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020<0\u000f¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0013R\u001d\u0010>\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002010\u000f¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0013R\u001d\u0010@\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020B0\u000f¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0013R\u001d\u0010D\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u0002070\t¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\rR)\u0010G\u001a\u001a\u0012\u0004\u0012\u00020\u000b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020<0\t0\t¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\rR\u0017\u0010I\u001a\b\u0012\u0004\u0012\u00020\u001d0J¢\u0006\b\n\u0000\u001a\u0004\bK\u0010L¨\u0006R"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", Argument.Delimiters.none, "<init>", "()V", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "classCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getClassCache", "()Ljava/util/Map;", "notFoundClassCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getNotFoundClassCache", "()Ljava/util/concurrent/ConcurrentHashMap;", "typeParameterCache", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "getTypeParameterCache", "enumEntryCache", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/ir/symbols/IrEnumEntrySymbol;", "getEnumEntryCache", "localClassCache", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getLocalClassCache", "localCallableCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/Fir2IrScopeCache;", "getLocalCallableCache", "()Ljava/util/List;", "functionCache", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getFunctionCache", "dataClassGeneratedFunctionsCache", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$DataClassGeneratedFunctionsStorage;", "getDataClassGeneratedFunctionsCache", "constructorCache", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "getConstructorCache", "propertyCache", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getPropertyCache", "syntheticPropertyCache", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$PropertyCacheStorage$SyntheticPropertyKey;", "getSyntheticPropertyCache", "getterForPropertyCache", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "getGetterForPropertyCache", "setterForPropertyCache", "getSetterForPropertyCache", "backingFieldForPropertyCache", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "getBackingFieldForPropertyCache", "propertyForBackingFieldCache", "getPropertyForBackingFieldCache", "delegateVariableForPropertyCache", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrVariableSymbol;", "getDelegateVariableForPropertyCache", "irForFirSessionDependantDeclarationMap", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FakeOverrideIdentifier;", "getIrForFirSessionDependantDeclarationMap", "delegatedClassesInfo", "getDelegatedClassesInfo", "firClassesWithInheritanceByDelegation", Argument.Delimiters.none, "getFirClassesWithInheritanceByDelegation", "()Ljava/util/Set;", "cloneFilteringSymbols", "filterOutSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "DataValueClassGeneratedMembersInfo", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrCommonMemberStorage {
    private final IrLock lock = new IrLock();
    private final Map<FirRegularClass, IrClassSymbol> classCache = new LinkedHashMap();
    private final ConcurrentHashMap<ConeClassLikeLookupTag, IrClass> notFoundClassCache = new ConcurrentHashMap<>();
    private final Map<FirTypeParameter, IrTypeParameter> typeParameterCache = new LinkedHashMap();
    private final Map<FirEnumEntry, IrEnumEntrySymbol> enumEntryCache = new LinkedHashMap();
    private final Map<FirClass, IrClass> localClassCache = new LinkedHashMap();
    private final List<Fir2IrScopeCache> localCallableCache = new ArrayList();
    private final ConcurrentHashMap<FirFunction, IrSimpleFunctionSymbol> functionCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<FirClass, Fir2IrDeclarationStorage.DataClassGeneratedFunctionsStorage> dataClassGeneratedFunctionsCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<FirConstructor, IrConstructorSymbol> constructorCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<FirProperty, IrPropertySymbol> propertyCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Fir2IrDeclarationStorage.PropertyCacheStorage.SyntheticPropertyKey, IrPropertySymbol> syntheticPropertyCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IrSymbol, IrSimpleFunctionSymbol> getterForPropertyCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IrSymbol, IrSimpleFunctionSymbol> setterForPropertyCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IrPropertySymbol, IrFieldSymbol> backingFieldForPropertyCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IrFieldSymbol, IrPropertySymbol> propertyForBackingFieldCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IrLocalDelegatedPropertySymbol, IrVariableSymbol> delegateVariableForPropertyCache = new ConcurrentHashMap<>();
    private final Map<Fir2IrDeclarationStorage.FakeOverrideIdentifier, IrSymbol> irForFirSessionDependantDeclarationMap = new LinkedHashMap();
    private final Map<IrClassSymbol, Map<IrClassSymbol, IrFieldSymbol>> delegatedClassesInfo = new LinkedHashMap();
    private final Set<FirClass> firClassesWithInheritanceByDelegation = new LinkedHashSet();

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J7\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage$DataValueClassGeneratedMembersInfo;", Argument.Delimiters.none, "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "generatedFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Ljava/util/List;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "getFirClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getGeneratedFunctions", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DataValueClassGeneratedMembersInfo {
        private final Fir2IrComponents components;
        private final FirRegularClass firClass;
        private final List<IrSimpleFunction> generatedFunctions;
        private final IrDeclarationOrigin origin;

        public DataValueClassGeneratedMembersInfo(Fir2IrComponents fir2IrComponents, FirRegularClass firRegularClass, IrDeclarationOrigin irDeclarationOrigin, List<IrSimpleFunction> list) {
            fir2IrComponents.getClass();
            firRegularClass.getClass();
            irDeclarationOrigin.getClass();
            list.getClass();
            this.components = fir2IrComponents;
            this.firClass = firRegularClass;
            this.origin = irDeclarationOrigin;
            this.generatedFunctions = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DataValueClassGeneratedMembersInfo copy$default(DataValueClassGeneratedMembersInfo dataValueClassGeneratedMembersInfo, Fir2IrComponents fir2IrComponents, FirRegularClass firRegularClass, IrDeclarationOrigin irDeclarationOrigin, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                fir2IrComponents = dataValueClassGeneratedMembersInfo.components;
            }
            if ((i & 2) != 0) {
                firRegularClass = dataValueClassGeneratedMembersInfo.firClass;
            }
            if ((i & 4) != 0) {
                irDeclarationOrigin = dataValueClassGeneratedMembersInfo.origin;
            }
            if ((i & 8) != 0) {
                list = dataValueClassGeneratedMembersInfo.generatedFunctions;
            }
            return dataValueClassGeneratedMembersInfo.copy(fir2IrComponents, firRegularClass, irDeclarationOrigin, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Fir2IrComponents getComponents() {
            return this.components;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirRegularClass getFirClass() {
            return this.firClass;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final IrDeclarationOrigin getOrigin() {
            return this.origin;
        }

        public final List<IrSimpleFunction> component4() {
            return this.generatedFunctions;
        }

        public final DataValueClassGeneratedMembersInfo copy(Fir2IrComponents components, FirRegularClass firClass, IrDeclarationOrigin origin, List<IrSimpleFunction> generatedFunctions) {
            components.getClass();
            firClass.getClass();
            origin.getClass();
            generatedFunctions.getClass();
            return new DataValueClassGeneratedMembersInfo(components, firClass, origin, generatedFunctions);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DataValueClassGeneratedMembersInfo)) {
                return false;
            }
            DataValueClassGeneratedMembersInfo dataValueClassGeneratedMembersInfo = (DataValueClassGeneratedMembersInfo) other;
            return Intrinsics.areEqual(this.components, dataValueClassGeneratedMembersInfo.components) && Intrinsics.areEqual(this.firClass, dataValueClassGeneratedMembersInfo.firClass) && Intrinsics.areEqual(this.origin, dataValueClassGeneratedMembersInfo.origin) && Intrinsics.areEqual(this.generatedFunctions, dataValueClassGeneratedMembersInfo.generatedFunctions);
        }

        public final Fir2IrComponents getComponents() {
            return this.components;
        }

        public final FirRegularClass getFirClass() {
            return this.firClass;
        }

        public final List<IrSimpleFunction> getGeneratedFunctions() {
            return this.generatedFunctions;
        }

        public final IrDeclarationOrigin getOrigin() {
            return this.origin;
        }

        public int hashCode() {
            return (((((this.components.hashCode() * 31) + this.firClass.hashCode()) * 31) + this.origin.hashCode()) * 31) + this.generatedFunctions.hashCode();
        }

        public String toString() {
            return "DataValueClassGeneratedMembersInfo(components=" + this.components + ", firClass=" + this.firClass + ", origin=" + this.origin + ", generatedFunctions=" + this.generatedFunctions + ')';
        }
    }

    public final Fir2IrCommonMemberStorage cloneFilteringSymbols(Set<? extends FirBasedSymbol<?>> filterOutSymbols) {
        filterOutSymbols.getClass();
        Fir2IrCommonMemberStorage fir2IrCommonMemberStorage = new Fir2IrCommonMemberStorage();
        fir2IrCommonMemberStorage.classCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.classCache, filterOutSymbols));
        fir2IrCommonMemberStorage.notFoundClassCache.putAll(this.notFoundClassCache);
        fir2IrCommonMemberStorage.typeParameterCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.typeParameterCache, filterOutSymbols));
        fir2IrCommonMemberStorage.enumEntryCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.enumEntryCache, filterOutSymbols));
        fir2IrCommonMemberStorage.localClassCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.localClassCache, filterOutSymbols));
        List<Fir2IrScopeCache> list = fir2IrCommonMemberStorage.localCallableCache;
        List<Fir2IrScopeCache> list2 = this.localCallableCache;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((Fir2IrScopeCache) it.next()).cloneFilteringSymbols(filterOutSymbols));
        }
        list.addAll(arrayList);
        fir2IrCommonMemberStorage.functionCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.functionCache, filterOutSymbols));
        fir2IrCommonMemberStorage.dataClassGeneratedFunctionsCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.dataClassGeneratedFunctionsCache, filterOutSymbols));
        fir2IrCommonMemberStorage.constructorCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.constructorCache, filterOutSymbols));
        fir2IrCommonMemberStorage.propertyCache.putAll(VariousUtilsKt.filterOutSymbolsFromCache(this.propertyCache, filterOutSymbols));
        ConcurrentHashMap<Fir2IrDeclarationStorage.PropertyCacheStorage.SyntheticPropertyKey, IrPropertySymbol> concurrentHashMap = fir2IrCommonMemberStorage.syntheticPropertyCache;
        ConcurrentHashMap<Fir2IrDeclarationStorage.PropertyCacheStorage.SyntheticPropertyKey, IrPropertySymbol> concurrentHashMap2 = this.syntheticPropertyCache;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Fir2IrDeclarationStorage.PropertyCacheStorage.SyntheticPropertyKey, IrPropertySymbol> entry : concurrentHashMap2.entrySet()) {
            if (!filterOutSymbols.contains(entry.getKey().getOriginalFunction().getSymbol())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        concurrentHashMap.putAll(linkedHashMap);
        fir2IrCommonMemberStorage.getterForPropertyCache.putAll(this.getterForPropertyCache);
        fir2IrCommonMemberStorage.setterForPropertyCache.putAll(this.setterForPropertyCache);
        fir2IrCommonMemberStorage.backingFieldForPropertyCache.putAll(this.backingFieldForPropertyCache);
        fir2IrCommonMemberStorage.delegateVariableForPropertyCache.putAll(this.delegateVariableForPropertyCache);
        Map<Fir2IrDeclarationStorage.FakeOverrideIdentifier, IrSymbol> map = fir2IrCommonMemberStorage.irForFirSessionDependantDeclarationMap;
        Map<Fir2IrDeclarationStorage.FakeOverrideIdentifier, IrSymbol> map2 = this.irForFirSessionDependantDeclarationMap;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<Fir2IrDeclarationStorage.FakeOverrideIdentifier, IrSymbol> entry2 : map2.entrySet()) {
            if (!filterOutSymbols.contains(entry2.getKey().getOriginalSymbol())) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        map.putAll(linkedHashMap2);
        fir2IrCommonMemberStorage.delegatedClassesInfo.putAll(this.delegatedClassesInfo);
        Set<FirClass> set = fir2IrCommonMemberStorage.firClassesWithInheritanceByDelegation;
        Set<FirClass> set2 = this.firClassesWithInheritanceByDelegation;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : set2) {
            if (!filterOutSymbols.contains(((FirClass) obj).getSymbol())) {
                arrayList2.add(obj);
            }
        }
        set.addAll(arrayList2);
        return fir2IrCommonMemberStorage;
    }

    public final ConcurrentHashMap<IrPropertySymbol, IrFieldSymbol> getBackingFieldForPropertyCache() {
        return this.backingFieldForPropertyCache;
    }

    public final Map<FirRegularClass, IrClassSymbol> getClassCache() {
        return this.classCache;
    }

    public final ConcurrentHashMap<FirConstructor, IrConstructorSymbol> getConstructorCache() {
        return this.constructorCache;
    }

    public final ConcurrentHashMap<FirClass, Fir2IrDeclarationStorage.DataClassGeneratedFunctionsStorage> getDataClassGeneratedFunctionsCache() {
        return this.dataClassGeneratedFunctionsCache;
    }

    public final ConcurrentHashMap<IrLocalDelegatedPropertySymbol, IrVariableSymbol> getDelegateVariableForPropertyCache() {
        return this.delegateVariableForPropertyCache;
    }

    public final Map<IrClassSymbol, Map<IrClassSymbol, IrFieldSymbol>> getDelegatedClassesInfo() {
        return this.delegatedClassesInfo;
    }

    public final Map<FirEnumEntry, IrEnumEntrySymbol> getEnumEntryCache() {
        return this.enumEntryCache;
    }

    public final Set<FirClass> getFirClassesWithInheritanceByDelegation() {
        return this.firClassesWithInheritanceByDelegation;
    }

    public final ConcurrentHashMap<FirFunction, IrSimpleFunctionSymbol> getFunctionCache() {
        return this.functionCache;
    }

    public final ConcurrentHashMap<IrSymbol, IrSimpleFunctionSymbol> getGetterForPropertyCache() {
        return this.getterForPropertyCache;
    }

    public final Map<Fir2IrDeclarationStorage.FakeOverrideIdentifier, IrSymbol> getIrForFirSessionDependantDeclarationMap() {
        return this.irForFirSessionDependantDeclarationMap;
    }

    public final List<Fir2IrScopeCache> getLocalCallableCache() {
        return this.localCallableCache;
    }

    public final Map<FirClass, IrClass> getLocalClassCache() {
        return this.localClassCache;
    }

    public final IrLock getLock() {
        return this.lock;
    }

    public final ConcurrentHashMap<ConeClassLikeLookupTag, IrClass> getNotFoundClassCache() {
        return this.notFoundClassCache;
    }

    public final ConcurrentHashMap<FirProperty, IrPropertySymbol> getPropertyCache() {
        return this.propertyCache;
    }

    public final ConcurrentHashMap<IrFieldSymbol, IrPropertySymbol> getPropertyForBackingFieldCache() {
        return this.propertyForBackingFieldCache;
    }

    public final ConcurrentHashMap<IrSymbol, IrSimpleFunctionSymbol> getSetterForPropertyCache() {
        return this.setterForPropertyCache;
    }

    public final ConcurrentHashMap<Fir2IrDeclarationStorage.PropertyCacheStorage.SyntheticPropertyKey, IrPropertySymbol> getSyntheticPropertyCache() {
        return this.syntheticPropertyCache;
    }

    public final Map<FirTypeParameter, IrTypeParameter> getTypeParameterCache() {
        return this.typeParameterCache;
    }
}
