package org.jetbrains.kotlin.fir.scopes.impl;

import defpackage.dwe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirGeneratedDeclarationsUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.caches.FirLazyValue;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.GeneratedDeclarationValidationKt;
import org.jetbrains.kotlin.fir.extensions.DeclarationGenerationContext;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirGeneratedMemberDeclarationsStorage;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003()*B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J%\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0000¢\u0006\u0002\b\u0015JP\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0\u001f2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2'\u0010#\u001a#\u0012\u0004\u0012\u00020\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0%0$¢\u0006\u0002\b&H\u0082\bJ\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0!2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0016\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0019j\u0002`\u001a0\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u001b\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u001cj\u0002`\u001d0\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "getCallableStorage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$CallableStorage;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "regularDeclaredScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScope;", "scopeForGeneratedClass", Argument.Delimiters.none, "getCallableStorage$org_jetbrains_kotlin_providers", "getClassifierStorage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$ClassifierStorage;", "regularNestedClassifierScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "getClassifierStorage$org_jetbrains_kotlin_providers", "callableStorageByClass", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$StorageContext;", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;", "Lorg/jetbrains/kotlin/fir/extensions/MemberGenerationContext;", "classifierStorageByClass", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;", "Lorg/jetbrains/kotlin/fir/extensions/NestedClassGenerationContext;", "groupExtensionsByName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "nameExtractor", "Lkotlin/Function2;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "getExtensionsForClass", "StorageContext", "CallableStorage", "ClassifierStorage", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirGeneratedMemberDeclarationsStorage implements FirSessionComponent {
    private final FirCachesFactory cachesFactory;
    private final FirCache<FirClassSymbol<?>, CallableStorage, StorageContext<DeclarationGenerationContext.Member>> callableStorageByClass;
    private final FirCache<FirClassSymbol<?>, ClassifierStorage, StorageContext<DeclarationGenerationContext.Nested>> classifierStorageByClass;
    private final FirSession session;

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\b¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010%\u001a\u00020\tH\u0002J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\u0006\u0010%\u001a\u00020\tH\u0002J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00190\nH\u0002R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R+\u0010\u0014\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\n0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u0006\u0012\u0002\b\u00030!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$CallableStorage;", Argument.Delimiters.none, "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "generationContext", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;", "Lorg/jetbrains/kotlin/fir/extensions/MemberGenerationContext;", "extensionsByCallableName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;Ljava/util/Map;)V", "functionCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", Argument.Delimiters.none, "getFunctionCache", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "propertyCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getPropertyCache", "constructorCache", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getConstructorCache", "()Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "allCallableNames", Argument.Delimiters.none, "getAllCallableNames", "()Ljava/util/Set;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "generateMemberFunctions", ModuleXmlParser.NAME, "generateMemberProperties", "generateConstructors", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CallableStorage {
        private final FirLazyValue<List<FirConstructorSymbol>> constructorCache;
        private final Map<Name, List<FirDeclarationGenerationExtension>> extensionsByCallableName;
        private final FirCache functionCache;
        private final DeclarationGenerationContext.Member generationContext;
        private final FirCache propertyCache;

        /* JADX WARN: Multi-variable type inference failed */
        public CallableStorage(FirCachesFactory firCachesFactory, DeclarationGenerationContext.Member member, Map<Name, ? extends List<? extends FirDeclarationGenerationExtension>> map) {
            firCachesFactory.getClass();
            member.getClass();
            map.getClass();
            this.generationContext = member;
            this.extensionsByCallableName = map;
            this.functionCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirGeneratedMemberDeclarationsStorage$CallableStorage$special$$inlined$createCache$1
                public final List<? extends FirNamedFunctionSymbol> invoke(Name name, Void r2) {
                    name.getClass();
                    return this.this$0.generateMemberFunctions(name);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((Name) obj, (Void) obj2);
                }
            });
            this.propertyCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirGeneratedMemberDeclarationsStorage$CallableStorage$special$$inlined$createCache$2
                public final List<? extends FirPropertySymbol> invoke(Name name, Void r2) {
                    name.getClass();
                    return this.this$0.generateMemberProperties(name);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((Name) obj, (Void) obj2);
                }
            });
            this.constructorCache = firCachesFactory.createLazyValue(new Function0() { // from class: a85
                public final Object invoke() {
                    return FirGeneratedMemberDeclarationsStorage.CallableStorage.a(this.b);
                }
            });
        }

        public static List a(CallableStorage callableStorage) {
            return callableStorage.generateConstructors();
        }

        /* JADX WARN: Type inference failed for: r0v9, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        private final List<FirConstructorSymbol> generateConstructors() {
            List<FirDeclarationGenerationExtension> listEmptyList = this.extensionsByCallableName.get(SpecialNames.INIT);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = listEmptyList.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((FirDeclarationGenerationExtension) it.next()).generateConstructors(this.generationContext));
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                GeneratedDeclarationValidationKt.validate(((FirConstructorSymbol) it2.next()).getFir());
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r6v4, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        public final List<FirNamedFunctionSymbol> generateMemberFunctions(Name name) {
            if (Intrinsics.areEqual(name, SpecialNames.INIT)) {
                return CollectionsKt.emptyList();
            }
            List<FirDeclarationGenerationExtension> listEmptyList = this.extensionsByCallableName.get(name);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = listEmptyList.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((FirDeclarationGenerationExtension) it.next()).generateFunctions(new CallableId(getClassSymbol().getClassId(), name), this.generationContext));
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                GeneratedDeclarationValidationKt.validate(((FirNamedFunctionSymbol) it2.next()).getFir());
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r6v4, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        public final List<FirPropertySymbol> generateMemberProperties(Name name) {
            if (Intrinsics.areEqual(name, SpecialNames.INIT)) {
                return CollectionsKt.emptyList();
            }
            List<FirDeclarationGenerationExtension> listEmptyList = this.extensionsByCallableName.get(name);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = listEmptyList.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((FirDeclarationGenerationExtension) it.next()).generateProperties(new CallableId(getClassSymbol().getClassId(), name), this.generationContext));
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                GeneratedDeclarationValidationKt.validate(((FirPropertySymbol) it2.next()).getFir());
            }
            return arrayList;
        }

        private final FirClassSymbol<?> getClassSymbol() {
            return this.generationContext.getOwner();
        }

        public final Set<Name> getAllCallableNames() {
            return this.extensionsByCallableName.keySet();
        }

        public final FirLazyValue<List<FirConstructorSymbol>> getConstructorCache() {
            return this.constructorCache;
        }

        public final FirCache getFunctionCache() {
            return this.functionCache;
        }

        public final FirCache getPropertyCache() {
            return this.propertyCache;
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001b\u001a\u00020\u000bH\u0002R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$ClassifierStorage;", Argument.Delimiters.none, "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "generationContext", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;", "Lorg/jetbrains/kotlin/fir/extensions/NestedClassGenerationContext;", "extensionsByClassifierName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;Ljava/util/Map;)V", "classifiersCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", Argument.Delimiters.none, "getClassifiersCache", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "allClassifierNames", Argument.Delimiters.none, "getAllClassifierNames", "()Ljava/util/Set;", "generateNestedClassifier", ModuleXmlParser.NAME, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ClassifierStorage {
        private final FirClassSymbol<?> classSymbol;
        private final FirCache classifiersCache;
        private final Map<Name, List<FirDeclarationGenerationExtension>> extensionsByClassifierName;
        private final DeclarationGenerationContext.Nested generationContext;

        /* JADX WARN: Multi-variable type inference failed */
        public ClassifierStorage(FirCachesFactory firCachesFactory, FirClassSymbol<?> firClassSymbol, DeclarationGenerationContext.Nested nested, Map<Name, ? extends List<? extends FirDeclarationGenerationExtension>> map) {
            firCachesFactory.getClass();
            firClassSymbol.getClass();
            nested.getClass();
            map.getClass();
            this.classSymbol = firClassSymbol;
            this.generationContext = nested;
            this.extensionsByClassifierName = map;
            this.classifiersCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirGeneratedMemberDeclarationsStorage$ClassifierStorage$special$$inlined$createCache$1
                public final FirRegularClassSymbol invoke(Name name, Void r2) {
                    name.getClass();
                    return this.this$0.generateNestedClassifier(name);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((Name) obj, (Void) obj2);
                }
            });
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        public static CharSequence a(FirClassLikeSymbol firClassLikeSymbol) {
            firClassLikeSymbol.getClass();
            return UtilsKt.render(firClassLikeSymbol.getFir());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final FirRegularClassSymbol generateNestedClassifier(Name name) {
            FirRegularClassSymbol companionObjectSymbol;
            FirClassSymbol<?> firClassSymbol = this.classSymbol;
            if ((firClassSymbol instanceof FirRegularClassSymbol) && (companionObjectSymbol = ((FirRegularClassSymbol) firClassSymbol).getCompanionObjectSymbol()) != null && companionObjectSymbol.getOrigin().getGenerated() && Intrinsics.areEqual(companionObjectSymbol.getClassId().getShortClassName(), name)) {
                return companionObjectSymbol;
            }
            List<FirDeclarationGenerationExtension> list = this.extensionsByClassifierName.get(name);
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (FirDeclarationGenerationExtension firDeclarationGenerationExtension : list) {
                FirClassLikeSymbol<?> firClassLikeSymbolGenerateNestedClassLikeDeclaration = firDeclarationGenerationExtension.generateNestedClassLikeDeclaration(this.classSymbol, name, this.generationContext);
                if (firClassLikeSymbolGenerateNestedClassLikeDeclaration != null) {
                    FirGeneratedDeclarationsUtilsKt.setOwnerGenerator((FirClassLikeDeclaration) firClassLikeSymbolGenerateNestedClassLikeDeclaration.getFir(), firDeclarationGenerationExtension);
                    if (((FirClassLikeDeclaration) this.classSymbol.getFir()).isLocal()) {
                        ClassMembersKt.setContainingClassForLocalAttr((FirClassLikeDeclaration) firClassLikeSymbolGenerateNestedClassLikeDeclaration.getFir(), this.classSymbol.getLookupTag());
                    }
                } else {
                    firClassLikeSymbolGenerateNestedClassLikeDeclaration = null;
                }
                if (firClassLikeSymbolGenerateNestedClassLikeDeclaration != null) {
                    arrayList.add(firClassLikeSymbolGenerateNestedClassLikeDeclaration);
                }
            }
            int size = arrayList.size();
            if (size != 0) {
                if (size == 1) {
                    FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) CollectionsKt.first(arrayList);
                    if (firClassLikeSymbol instanceof FirRegularClassSymbol) {
                        return (FirRegularClassSymbol) firClassLikeSymbol;
                    }
                    w01.a("Only regular class are allowed as nested classes");
                    return null;
                }
                dwe.a(StringsKt.trimIndent("\n                     Multiple plugins generated nested class with same name " + name + " for class " + this.classSymbol.getClassId() + ":\n                    " + CollectionsKt.joinToString$default(arrayList, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: b85
                    public final Object invoke(Object obj) {
                        return FirGeneratedMemberDeclarationsStorage.ClassifierStorage.a((FirClassLikeSymbol) obj);
                    }
                }, 30, (Object) null) + "\n                "));
            }
            return null;
        }

        public final Set<Name> getAllClassifierNames() {
            return this.extensionsByClassifierName.keySet();
        }

        public final FirCache getClassifiersCache() {
            return this.classifiersCache;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0010\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\u001b\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005HÆ\u0003J:\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\u001a\b\u0002\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR#\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$StorageContext;", "C", Argument.Delimiters.none, "generationContext", "extensionsByName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "<init>", "(Ljava/lang/Object;Ljava/util/Map;)V", "getGenerationContext", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getExtensionsByName", "()Ljava/util/Map;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/util/Map;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$StorageContext;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class StorageContext<C> {
        private final Map<Name, List<FirDeclarationGenerationExtension>> extensionsByName;
        private final C generationContext;

        /* JADX WARN: Multi-variable type inference failed */
        public StorageContext(C c, Map<Name, ? extends List<? extends FirDeclarationGenerationExtension>> map) {
            map.getClass();
            this.generationContext = c;
            this.extensionsByName = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StorageContext copy$default(StorageContext storageContext, Object obj, Map map, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = storageContext.generationContext;
            }
            if ((i & 2) != 0) {
                map = storageContext.extensionsByName;
            }
            return storageContext.copy(obj, map);
        }

        public final C component1() {
            return this.generationContext;
        }

        public final Map<Name, List<FirDeclarationGenerationExtension>> component2() {
            return this.extensionsByName;
        }

        public final StorageContext<C> copy(C generationContext, Map<Name, ? extends List<? extends FirDeclarationGenerationExtension>> extensionsByName) {
            extensionsByName.getClass();
            return new StorageContext<>(generationContext, extensionsByName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StorageContext)) {
                return false;
            }
            StorageContext storageContext = (StorageContext) other;
            return Intrinsics.areEqual(this.generationContext, storageContext.generationContext) && Intrinsics.areEqual(this.extensionsByName, storageContext.extensionsByName);
        }

        public final Map<Name, List<FirDeclarationGenerationExtension>> getExtensionsByName() {
            return this.extensionsByName;
        }

        public final C getGenerationContext() {
            return this.generationContext;
        }

        public int hashCode() {
            C c = this.generationContext;
            return ((c == null ? 0 : c.hashCode()) * 31) + this.extensionsByName.hashCode();
        }

        public String toString() {
            return "StorageContext(generationContext=" + this.generationContext + ", extensionsByName=" + this.extensionsByName + ')';
        }
    }

    public FirGeneratedMemberDeclarationsStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.callableStorageByClass = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.c
            public final Object invoke(Object obj, Object obj2) {
                return FirGeneratedMemberDeclarationsStorage.a(this.b, (FirClassSymbol) obj, (FirGeneratedMemberDeclarationsStorage.StorageContext) obj2);
            }
        });
        this.classifierStorageByClass = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.d
            public final Object invoke(Object obj, Object obj2) {
                return FirGeneratedMemberDeclarationsStorage.b(this.b, (FirClassSymbol) obj, (FirGeneratedMemberDeclarationsStorage.StorageContext) obj2);
            }
        });
    }

    public static CallableStorage a(FirGeneratedMemberDeclarationsStorage firGeneratedMemberDeclarationsStorage, FirClassSymbol firClassSymbol, StorageContext storageContext) {
        firClassSymbol.getClass();
        storageContext.getClass();
        return new CallableStorage(firGeneratedMemberDeclarationsStorage.cachesFactory, (DeclarationGenerationContext.Member) storageContext.component1(), storageContext.component2());
    }

    public static ClassifierStorage b(FirGeneratedMemberDeclarationsStorage firGeneratedMemberDeclarationsStorage, FirClassSymbol firClassSymbol, StorageContext storageContext) {
        firClassSymbol.getClass();
        storageContext.getClass();
        return new ClassifierStorage(firGeneratedMemberDeclarationsStorage.cachesFactory, firClassSymbol, (DeclarationGenerationContext.Nested) storageContext.component1(), storageContext.component2());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirDeclarationGenerationExtension> getExtensionsForClass(FirClassSymbol<?> classSymbol) {
        if (this.session == classSymbol.getModuleData().getSession()) {
            if (!classSymbol.getOrigin().getGenerated() || ((FirClassLikeDeclaration) classSymbol.getFir()).isLocal()) {
                return FirDeclarationGenerationExtensionKt.getDeclarationGenerators(FirExtensionServiceKt.getExtensionService(this.session));
            }
            FirDeclarationGenerationExtension ownerGenerator = FirGeneratedDeclarationsUtilsKt.getOwnerGenerator((FirClassLikeDeclaration) classSymbol.getFir());
            ownerGenerator.getClass();
            return CollectionsKt.listOf(ownerGenerator);
        }
        StringBuilder sb = new StringBuilder("Class ");
        sb.append(classSymbol);
        sb.append(" is declared in ");
        sb.append(classSymbol.getModuleData().getSession());
        ywd.a(sb, ", but generated storage for it taken from ", this.session);
        return null;
    }

    public final CallableStorage getCallableStorage$org_jetbrains_kotlin_providers(FirClassSymbol<?> classSymbol, FirClassDeclaredMemberScope regularDeclaredScope, boolean scopeForGeneratedClass) {
        classSymbol.getClass();
        DeclarationGenerationContext.Member member = new DeclarationGenerationContext.Member(classSymbol, regularDeclaredScope);
        List<FirDeclarationGenerationExtension> extensionsForClass = getExtensionsForClass(classSymbol);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclarationGenerationExtension firDeclarationGenerationExtension : extensionsForClass) {
            for (Name name : firDeclarationGenerationExtension.getCallableNamesForClass(classSymbol, member)) {
                Collection arrayList = (List) linkedHashMap.get(name);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(name, arrayList);
                }
                arrayList.add(firDeclarationGenerationExtension);
            }
        }
        if (!linkedHashMap.isEmpty() || scopeForGeneratedClass) {
            return this.callableStorageByClass.getValue(classSymbol, new StorageContext<>(member, linkedHashMap));
        }
        return null;
    }

    public final ClassifierStorage getClassifierStorage$org_jetbrains_kotlin_providers(FirClassSymbol<?> classSymbol, FirNestedClassifierScope regularNestedClassifierScope) {
        classSymbol.getClass();
        DeclarationGenerationContext.Nested nested = new DeclarationGenerationContext.Nested(classSymbol, regularNestedClassifierScope);
        List<FirDeclarationGenerationExtension> extensionsForClass = getExtensionsForClass(classSymbol);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclarationGenerationExtension firDeclarationGenerationExtension : extensionsForClass) {
            for (Name name : firDeclarationGenerationExtension.getNestedClassifiersNames(classSymbol, nested)) {
                Collection arrayList = (List) linkedHashMap.get(name);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(name, arrayList);
                }
                arrayList.add(firDeclarationGenerationExtension);
            }
        }
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return this.classifierStorageByClass.getValue(classSymbol, new StorageContext<>(nested, linkedHashMap));
    }
}
