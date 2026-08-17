package org.jetbrains.kotlin.cli.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.backend.common.LoadMetadataKlibsKt;
import org.jetbrains.kotlin.cli.common.SessionConstructionUtils;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.HmppCliModule;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSourceModuleData;
import org.jetbrains.kotlin.fir.analysis.checkers.CliOnlyLanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.checkers.CheckersContainersKt;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirBuiltinSyntheticFunctionInterfaceProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirExtensionSyntheticFunctionInterfaceProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.AbstractFirMetadataSessionFactory;
import org.jetbrains.kotlin.fir.session.FirMetadataSessionFactoryForHmppCompilation;
import org.jetbrains.kotlin.fir.session.FirSessionConfigurator;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.CommonPlatforms;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J§\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00060\u0005\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00052\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00100\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00100\u00162\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00100\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001c2!\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u001f0\u00162\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00070%2:\b\u0002\u0010&\u001a4\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u0005\u0018\u00010'j\u0004\u0018\u0001`,Ji\u0010-\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010/\u001a\u0002002\u0006\u0010\r\u001a\u00020\u000e2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002030\u0016¢\u0006\u0002\b42\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00070%H\u0002Ja\u00105\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002030\u0016¢\u0006\u0002\b42\f\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00070%H\u0002J{\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00060\u0005\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002030\u0016¢\u0006\u0002\b42\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00100\u00162\f\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00070%H\u0002J\u0089\u0001\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00060\u0005\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00109\u001a\u00020:2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002030\u0016¢\u0006\u0002\b42\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00100\u00192\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00070%H\u0002Jï\u0001\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00060\u0005\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00109\u001a\u00020:2\u0006\u0010=\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00052\u0006\u0010#\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000e2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002030\u0016¢\u0006\u0002\b42\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00100\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00070%28\u0010>\u001a4\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u0005\u0018\u00010'j\u0004\u0018\u0001`,H\u0002J\u0095\u0001\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00060\u0005\"\u0004\b\u0000\u0010\u00072\u0006\u00109\u001a\u00020:2\"\u0010@\u001a\u001e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u0002000Aj\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u000200`C2\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00100\u00192\f\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00070%2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002030\u0016¢\u0006\u0002\b4H\u0002¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/SessionConstructionUtils;", Argument.Delimiters.none, "<init>", "()V", "prepareSessions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/SessionWithSources;", "F", "files", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "rootModuleName", "Lorg/jetbrains/kotlin/name/Name;", "targetPlatform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "metadataCompilationMode", Argument.Delimiters.none, "libraryList", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "extensionRegistrars", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "isCommonSource", "Lkotlin/Function1;", "isScript", "fileBelongsToModule", "Lkotlin/Function2;", Argument.Delimiters.none, "createMetadataSessionFactoryContextForHmppCommonLibrarySession", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory$Context;", "createSharedLibrarySession", "Lorg/jetbrains/kotlin/fir/FirSession;", "createLibrarySession", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "sharedLibrarySession", "createSourceSession", "Lorg/jetbrains/kotlin/cli/common/FirSessionProducer;", "additionalProvidersForMetadataLibrarySessionsInHmppMode", "Lkotlin/Function4;", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "Lorg/jetbrains/kotlin/fir/session/AdditionalProvidersSupplier;", "createScriptsSession", "scripts", "lastModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "sessionConfigurator", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "createSingleSession", "sourceSessionProducer", "createSessionsForLegacyMppProject", "createSessionsForMppProject", "hmppModuleStructure", "Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", "createFirSession", "createSessionsForHierarchicalMppProject", "libraryListForLeafModule", "additionalProvidersForMetadataLibrarySessions", "createSourceSessionsForMppCompilation", "moduleDataForHmppModule", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/config/HmppCliModule;", "Lkotlin/collections/LinkedHashMap;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SessionConstructionUtils {
    public static final SessionConstructionUtils INSTANCE = new SessionConstructionUtils();

    private SessionConstructionUtils() {
    }

    public static Unit a(Function1 function1, FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        function1.invoke(firSessionConfigurator);
        firSessionConfigurator.useCheckers(CliOnlyLanguageVersionSettingsCheckers.INSTANCE);
        return Unit.INSTANCE;
    }

    public static Unit c(FirSession firSession, boolean z, boolean z2, FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.registerComponent(Reflection.getOrCreateKotlinClass(FirBuiltinSyntheticFunctionInterfaceProvider.class), FirExtensionSyntheticFunctionInterfaceProviderKt.getSyntheticFunctionInterfacesSymbolProvider(firSession));
        if (z) {
            CheckersContainersKt.registerExtraCommonCheckers(firSessionConfigurator);
        }
        if (z2) {
            CheckersContainersKt.registerExperimentalCheckers(firSessionConfigurator);
        }
        return Unit.INSTANCE;
    }

    private final <F> SessionWithSources<F> createScriptsSession(List<? extends F> scripts, Name rootModuleName, DependencyListForCliModule libraryList, FirModuleData lastModuleData, TargetPlatform targetPlatform, Function1<? super FirSessionConfigurator, Unit> sessionConfigurator, FirSessionProducer<F> createSourceSession) {
        Name nameIdentifier = Name.identifier(rootModuleName.asString() + "-scripts");
        nameIdentifier.getClass();
        return createSingleSession(scripts, nameIdentifier, new DependencyListForCliModule(libraryList.getRegularDependencies(), CollectionsKt.listOf(lastModuleData), libraryList.getFriendDependencies(), libraryList.getModuleDataProvider()), targetPlatform, sessionConfigurator, createSourceSession);
    }

    private final <F> List<SessionWithSources<F>> createSessionsForHierarchicalMppProject(List<? extends F> files, Name rootModuleName, HmppCliModuleStructure hmppModuleStructure, DependencyListForCliModule libraryListForLeafModule, CompilerConfiguration configuration, List<? extends FirExtensionRegistrar> extensionRegistrars, FirSession sharedLibrarySession, TargetPlatform targetPlatform, Function1<? super FirSessionConfigurator, Unit> sessionConfigurator, Function2<? super F, ? super String, Boolean> fileBelongsToModule, Function0<AbstractFirMetadataSessionFactory.Context> createMetadataSessionFactoryContextForHmppCommonLibrarySession, FirSessionProducer<F> createFirSession, Function4<? super FirSession, ? super ModuleDataProvider, ? super FirKotlinScopeProvider, ? super List<? extends KotlinLibrary>, ? extends List<? extends FirSymbolProvider>> additionalProvidersForMetadataLibrarySessions) {
        List listEmptyList;
        Name name;
        DependencyListForCliModule dependencyListForCliModuleBuild;
        LinkedHashMap<HmppCliModule, FirModuleData> linkedHashMap = new LinkedHashMap<>();
        AbstractFirMetadataSessionFactory.Context context = (AbstractFirMetadataSessionFactory.Context) createMetadataSessionFactoryContextForHmppCommonLibrarySession.invoke();
        int i = 0;
        for (HmppCliModule hmppCliModule : hmppModuleStructure.getModules()) {
            int i2 = i + 1;
            List<HmppCliModule> list = hmppModuleStructure.getSourceDependencies().get(hmppCliModule);
            if (list != null) {
                List<HmppCliModule> list2 = list;
                listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    listEmptyList.add((FirModuleData) MapsKt.getValue(linkedHashMap, (HmppCliModule) it.next()));
                }
            } else {
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List list3 = listEmptyList;
            if (i == CollectionsKt.getLastIndex(hmppModuleStructure.getModules())) {
                name = rootModuleName;
            } else {
                Name nameSpecial = Name.special("<" + hmppCliModule.getName() + '>');
                nameSpecial.getClass();
                name = nameSpecial;
            }
            boolean z = i == CollectionsKt.getLastIndex(hmppModuleStructure.getModules());
            if (z) {
                dependencyListForCliModuleBuild = libraryListForLeafModule;
            } else {
                List<String> listEmptyList2 = hmppModuleStructure.getModuleDependencies().get(hmppCliModule);
                if (listEmptyList2 == null) {
                    listEmptyList2 = CollectionsKt.emptyList();
                }
                Set mutableSet = CollectionsKt.toMutableSet(listEmptyList2);
                List<HmppCliModule> listEmptyList3 = hmppModuleStructure.getSourceDependencies().get(hmppCliModule);
                if (listEmptyList3 == null) {
                    listEmptyList3 = CollectionsKt.emptyList();
                }
                Iterator<HmppCliModule> it2 = listEmptyList3.iterator();
                while (it2.hasNext()) {
                    Set set = mutableSet;
                    List<String> listEmptyList4 = hmppModuleStructure.getModuleDependencies().get(it2.next());
                    if (listEmptyList4 == null) {
                        listEmptyList4 = CollectionsKt.emptyList();
                    }
                    CollectionsKt.removeAll(set, listEmptyList4);
                }
                List<String> listEmptyList5 = hmppModuleStructure.getFriendDependencies().get(hmppCliModule);
                if (listEmptyList5 == null) {
                    listEmptyList5 = CollectionsKt.emptyList();
                }
                Set mutableSet2 = CollectionsKt.toMutableSet(listEmptyList5);
                List<? extends KotlinLibrary> all = LoadMetadataKlibsKt.loadMetadataKlibs(CollectionsKt.plus(CollectionsKt.toList(mutableSet), mutableSet2), configuration).getAll();
                DependencyListForCliModule.Companion companion = DependencyListForCliModule.INSTANCE;
                DependencyListForCliModule.Builder builder = new DependencyListForCliModule.Builder();
                DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule builderForDefaultDependenciesModule = new DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + name + '>'), builder.createData("<dependsOn dependencies of " + name + '>'), builder.createData("<friends dependencies of " + name + '>'));
                builderForDefaultDependenciesModule.dependencies(mutableSet);
                builderForDefaultDependenciesModule.friendDependencies(mutableSet2);
                dependencyListForCliModuleBuild = builder.build();
                TargetPlatform targetPlatform2 = CommonConfigurationKeysKt.getTargetPlatform(configuration);
                if (targetPlatform2 == null) {
                    targetPlatform2 = CommonPlatforms.INSTANCE.getDefaultCommonPlatform();
                }
                new FirMetadataSessionFactoryForHmppCompilation(targetPlatform2).createLibrarySession(sharedLibrarySession, dependencyListForCliModuleBuild.getModuleDataProvider(), extensionRegistrars, null, all, CommonConfigurationKeysKt.getLanguageVersionSettings(configuration), context, additionalProvidersForMetadataLibrarySessions);
            }
            linkedHashMap.put(hmppCliModule, new FirSourceModuleData(name, dependencyListForCliModuleBuild.getRegularDependencies(), list3, dependencyListForCliModuleBuild.getFriendDependencies(), targetPlatform, !z));
            i = i2;
        }
        return createSourceSessionsForMppCompilation(hmppModuleStructure, linkedHashMap, files, fileBelongsToModule, createFirSession, sessionConfigurator);
    }

    private final <F> List<SessionWithSources<F>> createSessionsForLegacyMppProject(List<? extends F> files, Name rootModuleName, DependencyListForCliModule libraryList, TargetPlatform targetPlatform, final Function1<? super FirSessionConfigurator, Unit> sessionConfigurator, Function1<? super F, Boolean> isCommonSource, FirSessionProducer<F> sourceSessionProducer) {
        Name nameIdentifier = Name.identifier(rootModuleName.asString() + "-common");
        nameIdentifier.getClass();
        FirSourceModuleData firSourceModuleData = new FirSourceModuleData(nameIdentifier, libraryList.getRegularDependencies(), CollectionsKt.emptyList(), libraryList.getFriendDependencies(), targetPlatform, true);
        FirSourceModuleData firSourceModuleData2 = new FirSourceModuleData(rootModuleName, libraryList.getRegularDependencies(), CollectionsKt.listOf(firSourceModuleData), libraryList.getFriendDependencies(), targetPlatform, false);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (F f : files) {
            (((Boolean) isCommonSource.invoke(f)).booleanValue() ? arrayList : arrayList2).add(f);
        }
        return CollectionsKt.listOf(new SessionWithSources[]{new SessionWithSources(sourceSessionProducer.createSession(arrayList, firSourceModuleData, false, sessionConfigurator), arrayList), new SessionWithSources(sourceSessionProducer.createSession(arrayList2, firSourceModuleData2, false, new Function1() { // from class: i8d
            public final Object invoke(Object obj) {
                return SessionConstructionUtils.d(sessionConfigurator, (FirSessionConfigurator) obj);
            }
        }), arrayList2)});
    }

    private final <F> List<SessionWithSources<F>> createSessionsForMppProject(List<? extends F> files, Name rootModuleName, HmppCliModuleStructure hmppModuleStructure, DependencyListForCliModule libraryList, TargetPlatform targetPlatform, Function1<? super FirSessionConfigurator, Unit> sessionConfigurator, Function2<? super F, ? super String, Boolean> fileBelongsToModule, FirSessionProducer<F> createFirSession) {
        List listEmptyList;
        Name name;
        LinkedHashMap<HmppCliModule, FirModuleData> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        for (HmppCliModule hmppCliModule : hmppModuleStructure.getModules()) {
            int i2 = i + 1;
            List<HmppCliModule> list = hmppModuleStructure.getSourceDependencies().get(hmppCliModule);
            if (list != null) {
                List<HmppCliModule> list2 = list;
                listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    listEmptyList.add((FirModuleData) MapsKt.getValue(linkedHashMap, (HmppCliModule) it.next()));
                }
            } else {
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List list3 = listEmptyList;
            if (i == CollectionsKt.getLastIndex(hmppModuleStructure.getModules())) {
                name = rootModuleName;
            } else {
                Name nameSpecial = Name.special("<" + hmppCliModule.getName() + '>');
                nameSpecial.getClass();
                name = nameSpecial;
            }
            linkedHashMap.put(hmppCliModule, new FirSourceModuleData(name, libraryList.getRegularDependencies(), list3, libraryList.getFriendDependencies(), targetPlatform, i < hmppModuleStructure.getModules().size() - 1));
            i = i2;
        }
        return createSourceSessionsForMppCompilation(hmppModuleStructure, linkedHashMap, files, fileBelongsToModule, createFirSession, sessionConfigurator);
    }

    private final <F> SessionWithSources<F> createSingleSession(List<? extends F> files, Name rootModuleName, DependencyListForCliModule libraryList, TargetPlatform targetPlatform, final Function1<? super FirSessionConfigurator, Unit> sessionConfigurator, FirSessionProducer<F> sourceSessionProducer) {
        return new SessionWithSources<>(sourceSessionProducer.createSession(files, new FirSourceModuleData(rootModuleName, libraryList.getRegularDependencies(), libraryList.getDependsOnDependencies(), libraryList.getFriendDependencies(), targetPlatform, false, 32, null), false, new Function1() { // from class: h8d
            public final Object invoke(Object obj) {
                return SessionConstructionUtils.a(sessionConfigurator, (FirSessionConfigurator) obj);
            }
        }), files);
    }

    private final <F> List<SessionWithSources<F>> createSourceSessionsForMppCompilation(HmppCliModuleStructure hmppModuleStructure, LinkedHashMap<HmppCliModule, FirModuleData> moduleDataForHmppModule, List<? extends F> files, Function2<? super F, ? super String, Boolean> fileBelongsToModule, FirSessionProducer<F> sourceSessionProducer, final Function1<? super FirSessionConfigurator, Unit> sessionConfigurator) {
        List<HmppCliModule> modules = hmppModuleStructure.getModules();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(modules, 10));
        int i = 0;
        for (Object obj : modules) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            HmppCliModule hmppCliModule = (HmppCliModule) obj;
            FirModuleData firModuleData = (FirModuleData) MapsKt.getValue(moduleDataForHmppModule, hmppCliModule);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : files) {
                if (((Boolean) fileBelongsToModule.invoke(obj2, hmppCliModule.getName())).booleanValue()) {
                    arrayList2.add(obj2);
                }
            }
            final boolean z = i == CollectionsKt.getLastIndex(hmppModuleStructure.getModules());
            arrayList.add(new SessionWithSources(sourceSessionProducer.createSession(arrayList2, firModuleData, z, new Function1() { // from class: g8d
                public final Object invoke(Object obj3) {
                    return SessionConstructionUtils.createSourceSessionsForMppCompilation$lambda$0$1(sessionConfigurator, z, (FirSessionConfigurator) obj3);
                }
            }), arrayList2));
            i = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createSourceSessionsForMppCompilation$lambda$0$1(Function1 function1, boolean z, FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        function1.invoke(firSessionConfigurator);
        if (z) {
            firSessionConfigurator.useCheckers(CliOnlyLanguageVersionSettingsCheckers.INSTANCE);
        }
        return Unit.INSTANCE;
    }

    public static Unit d(Function1 function1, FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        function1.invoke(firSessionConfigurator);
        firSessionConfigurator.useCheckers(CliOnlyLanguageVersionSettingsCheckers.INSTANCE);
        return Unit.INSTANCE;
    }

    public final <F> List<SessionWithSources<F>> prepareSessions(List<? extends F> files, CompilerConfiguration configuration, Name rootModuleName, TargetPlatform targetPlatform, boolean metadataCompilationMode, DependencyListForCliModule libraryList, List<? extends FirExtensionRegistrar> extensionRegistrars, Function1<? super F, Boolean> isCommonSource, Function1<? super F, Boolean> isScript, Function2<? super F, ? super String, Boolean> fileBelongsToModule, Function0<AbstractFirMetadataSessionFactory.Context> createMetadataSessionFactoryContextForHmppCommonLibrarySession, Function0<? extends FirSession> createSharedLibrarySession, Function1<? super FirSession, ? extends FirSession> createLibrarySession, FirSessionProducer<F> createSourceSession, Function4<? super FirSession, ? super ModuleDataProvider, ? super FirKotlinScopeProvider, ? super List<? extends KotlinLibrary>, ? extends List<? extends FirSymbolProvider>> additionalProvidersForMetadataLibrarySessionsInHmppMode) {
        Pair pair;
        Function1<? super FirSessionConfigurator, Unit> function1;
        List<SessionWithSources<F>> listListOf;
        files.getClass();
        configuration.getClass();
        rootModuleName.getClass();
        targetPlatform.getClass();
        libraryList.getClass();
        extensionRegistrars.getClass();
        isCommonSource.getClass();
        isScript.getClass();
        fileBelongsToModule.getClass();
        createMetadataSessionFactoryContextForHmppCommonLibrarySession.getClass();
        createSharedLibrarySession.getClass();
        createLibrarySession.getClass();
        createSourceSession.getClass();
        LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(configuration);
        boolean dontCreateSeparateSessionForScripts = CommonConfigurationKeysKt.getDontCreateSeparateSessionForScripts(configuration);
        if (!dontCreateSeparateSessionForScripts) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : files) {
                if (((Boolean) isScript.invoke(obj)).booleanValue()) {
                    arrayList.add(obj);
                } else {
                    arrayList2.add(obj);
                }
            }
            pair = new Pair(arrayList, arrayList2);
        } else {
            if (!dontCreateSeparateSessionForScripts) {
                bu8.a();
                return null;
            }
            pair = TuplesKt.to(CollectionsKt.emptyList(), files);
        }
        List<? extends F> list = (List) pair.component1();
        List<? extends F> list2 = (List) pair.component2();
        boolean zSupportsFeature = languageVersionSettings.supportsFeature(LanguageFeature.MultiPlatformProjects);
        HmppCliModuleStructure hmppCliModuleStructure = (HmppCliModuleStructure) configuration.get(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE);
        final FirSession firSession = (FirSession) createSharedLibrarySession.invoke();
        createLibrarySession.invoke(firSession);
        final boolean useFirExtraCheckers = CommonConfigurationKeysKt.getUseFirExtraCheckers(configuration);
        final boolean useFirExperimentalCheckers = CommonConfigurationKeysKt.getUseFirExperimentalCheckers(configuration);
        Function1<? super FirSessionConfigurator, Unit> function2 = new Function1() { // from class: j8d
            public final Object invoke(Object obj2) {
                return SessionConstructionUtils.c(firSession, useFirExtraCheckers, useFirExperimentalCheckers, (FirSessionConfigurator) obj2);
            }
        };
        if (metadataCompilationMode || !zSupportsFeature) {
            SessionWithSources<F> sessionWithSourcesCreateSingleSession = createSingleSession(list2, rootModuleName, libraryList, targetPlatform, function2, createSourceSession);
            function1 = function2;
            listListOf = CollectionsKt.listOf(sessionWithSourcesCreateSingleSession);
        } else if (hmppCliModuleStructure == null) {
            listListOf = createSessionsForLegacyMppProject(list2, rootModuleName, libraryList, targetPlatform, function2, isCommonSource, createSourceSession);
            function1 = function2;
        } else if (((Boolean) languageVersionSettings.getFlag(AnalysisFlags.INSTANCE.getHierarchicalMultiplatformCompilation())).booleanValue()) {
            listListOf = createSessionsForHierarchicalMppProject(list2, rootModuleName, hmppCliModuleStructure, libraryList, configuration, extensionRegistrars, firSession, targetPlatform, function2, fileBelongsToModule, createMetadataSessionFactoryContextForHmppCommonLibrarySession, createSourceSession, additionalProvidersForMetadataLibrarySessionsInHmppMode);
            function1 = function2;
        } else {
            listListOf = createSessionsForMppProject(list2, rootModuleName, hmppCliModuleStructure, libraryList, targetPlatform, function2, fileBelongsToModule, createSourceSession);
            function1 = function2;
        }
        return list.isEmpty() ? listListOf : CollectionsKt.plus(listListOf, createScriptsSession(list, rootModuleName, libraryList, FirModuleDataKt.getModuleData(((SessionWithSources) CollectionsKt.last(listListOf)).getSession()), targetPlatform, function1, createSourceSession));
    }
}
