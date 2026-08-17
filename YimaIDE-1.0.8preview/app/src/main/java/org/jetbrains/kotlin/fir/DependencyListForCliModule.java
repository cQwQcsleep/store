package org.jetbrains.kotlin.fir;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.LibraryPathFilter;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.MultipleModuleDataProvider;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B?\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0002\b\u000b¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", Argument.Delimiters.none, "regularDependencies", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirModuleData;", "dependsOnDependencies", "friendDependencies", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;)V", "Lorg/jetbrains/kotlin/fir/PrivateSessionConstructor;", "getRegularDependencies", "()Ljava/util/List;", "getDependsOnDependencies", "getFriendDependencies", "getModuleDataProvider", "()Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "Companion", "Builder", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DependencyListForCliModule {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<FirModuleData> dependsOnDependencies;
    private final List<FirModuleData> friendDependencies;
    private final ModuleDataProvider moduleDataProvider;
    private final List<FirModuleData> regularDependencies;

    /* JADX WARN: Multi-variable type inference failed */
    @PrivateSessionConstructor
    public DependencyListForCliModule(List<? extends FirModuleData> list, List<? extends FirModuleData> list2, List<? extends FirModuleData> list3, ModuleDataProvider moduleDataProvider) {
        list.getClass();
        list2.getClass();
        list3.getClass();
        moduleDataProvider.getClass();
        this.regularDependencies = list;
        this.dependsOnDependencies = list2;
        this.friendDependencies = list3;
        this.moduleDataProvider = moduleDataProvider;
    }

    public final List<FirModuleData> getDependsOnDependencies() {
        return this.dependsOnDependencies;
    }

    public final List<FirModuleData> getFriendDependencies() {
        return this.friendDependencies;
    }

    public final ModuleDataProvider getModuleDataProvider() {
        return this.moduleDataProvider;
    }

    public final List<FirModuleData> getRegularDependencies() {
        return this.regularDependencies;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\u00020\u00052\u0019\b\u0002\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0086\bø\u0001\u0000J3\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u001d\b\u0002\u0010\u0006\u001a\u0017\u0012\b\u0012\u00060\rR\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Companion;", Argument.Delimiters.none, "<init>", "()V", "build", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Builder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "mainModuleName", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Builder$BuilderForDefaultDependenciesModule;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ DependencyListForCliModule build$default(Companion companion, Name name, Function1 function1, int i, Object obj) {
            if ((i & 2) != 0) {
                function1 = new Function1<Builder.BuilderForDefaultDependenciesModule, Unit>() { // from class: org.jetbrains.kotlin.fir.DependencyListForCliModule$Companion$build$2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        invoke((DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule builderForDefaultDependenciesModule) {
                        builderForDefaultDependenciesModule.getClass();
                    }
                };
            }
            name.getClass();
            function1.getClass();
            Builder builder = new Builder();
            function1.invoke(new Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + name + '>'), builder.createData("<dependsOn dependencies of " + name + '>'), builder.createData("<friends dependencies of " + name + '>')));
            return builder.build();
        }

        public final DependencyListForCliModule build(Name mainModuleName, Function1<? super Builder.BuilderForDefaultDependenciesModule, Unit> init) {
            mainModuleName.getClass();
            init.getClass();
            Builder builder = new Builder();
            init.invoke(new Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + mainModuleName + '>'), builder.createData("<dependsOn dependencies of " + mainModuleName + '>'), builder.createData("<friends dependencies of " + mainModuleName + '>')));
            return builder.build();
        }

        private Companion() {
        }

        public final DependencyListForCliModule build(Function1<? super Builder, Unit> init) {
            init.getClass();
            Builder builder = new Builder();
            init.invoke(builder);
            return builder.build();
        }

        public static /* synthetic */ DependencyListForCliModule build$default(Companion companion, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = new Function1<Builder, Unit>() { // from class: org.jetbrains.kotlin.fir.DependencyListForCliModule$Companion$build$1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        invoke((DependencyListForCliModule.Builder) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(DependencyListForCliModule.Builder builder) {
                        builder.getClass();
                    }
                };
            }
            function1.getClass();
            Builder builder = new Builder();
            function1.invoke(builder);
            return builder.build();
        }
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u001c\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u001c\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J1\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u001b\u0010\u0017\u001a\u0017\u0012\b\u0012\u00060\u0019R\u00020\u0000\u0012\u0004\u0012\u00020\r0\u0018¢\u0006\u0002\b\u001aH\u0086\bø\u0001\u0000J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0011J,\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0006\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Builder;", Argument.Delimiters.none, "<init>", "()V", "allRegularDependencies", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;", "allFriendDependencies", "allDependsOnDependencies", "filtersMap", Argument.Delimiters.none, "Ljava/nio/file/Path;", "dependencies", Argument.Delimiters.none, "moduleData", "paths", Argument.Delimiters.none, Argument.Delimiters.none, "friendDependencies", "dependsOnDependencies", "defaultDependenciesSet", "mainModuleName", "Lorg/jetbrains/kotlin/name/Name;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Builder$BuilderForDefaultDependenciesModule;", "Lkotlin/ExtensionFunctionType;", "createData", ModuleXmlParser.NAME, "destination", "build", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "BuilderForDefaultDependenciesModule", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Builder {
        private final Set<FirBinaryDependenciesModuleData> allRegularDependencies = new LinkedHashSet();
        private final Set<FirBinaryDependenciesModuleData> allFriendDependencies = new LinkedHashSet();
        private final Set<FirBinaryDependenciesModuleData> allDependsOnDependencies = new LinkedHashSet();
        private final Map<FirBinaryDependenciesModuleData, Set<Path>> filtersMap = new LinkedHashMap();

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0014\u0010\u0011\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0014\u0010\u0012\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Builder$BuilderForDefaultDependenciesModule;", Argument.Delimiters.none, "regular", "Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;", "dependsOn", "friend", "<init>", "(Lorg/jetbrains/kotlin/fir/DependencyListForCliModule$Builder;Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;)V", "getRegular", "()Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;", "getDependsOn", "getFriend", "dependencies", Argument.Delimiters.none, "paths", Argument.Delimiters.none, Argument.Delimiters.none, "friendDependencies", "dependsOnDependencies", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public final class BuilderForDefaultDependenciesModule {
            private final FirBinaryDependenciesModuleData dependsOn;
            private final FirBinaryDependenciesModuleData friend;
            private final FirBinaryDependenciesModuleData regular;
            final /* synthetic */ Builder this$0;

            public BuilderForDefaultDependenciesModule(Builder builder, FirBinaryDependenciesModuleData firBinaryDependenciesModuleData, FirBinaryDependenciesModuleData firBinaryDependenciesModuleData2, FirBinaryDependenciesModuleData firBinaryDependenciesModuleData3) {
                firBinaryDependenciesModuleData.getClass();
                firBinaryDependenciesModuleData2.getClass();
                firBinaryDependenciesModuleData3.getClass();
                this.this$0 = builder;
                this.regular = firBinaryDependenciesModuleData;
                this.dependsOn = firBinaryDependenciesModuleData2;
                this.friend = firBinaryDependenciesModuleData3;
                builder.allRegularDependencies.add(firBinaryDependenciesModuleData);
                builder.allDependsOnDependencies.add(firBinaryDependenciesModuleData2);
                builder.allFriendDependencies.add(firBinaryDependenciesModuleData3);
            }

            public final void dependencies(Collection<String> paths) {
                paths.getClass();
                this.this$0.dependencies(this.regular, paths);
            }

            public final void dependsOnDependencies(Collection<String> paths) {
                paths.getClass();
                this.this$0.dependsOnDependencies(this.dependsOn, paths);
            }

            public final void friendDependencies(Collection<String> paths) {
                paths.getClass();
                this.this$0.friendDependencies(this.friend, paths);
            }

            public final FirBinaryDependenciesModuleData getDependsOn() {
                return this.dependsOn;
            }

            public final FirBinaryDependenciesModuleData getFriend() {
                return this.friend;
            }

            public final FirBinaryDependenciesModuleData getRegular() {
                return this.regular;
            }
        }

        private static final List<FirBinaryDependenciesModuleData> build$filterUsedModules(Collection<FirBinaryDependenciesModuleData> collection, Map<FirModuleData, LibraryPathFilter> map) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : collection) {
                if (map.containsKey((FirBinaryDependenciesModuleData) obj)) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }

        private final void dependencies(FirBinaryDependenciesModuleData moduleData, Collection<String> paths, Set<FirBinaryDependenciesModuleData> destination) {
            destination.add(moduleData);
            if (paths.isEmpty()) {
                return;
            }
            Map<FirBinaryDependenciesModuleData, Set<Path>> map = this.filtersMap;
            Set<Path> linkedHashSet = map.get(moduleData);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet<>();
                map.put(moduleData, linkedHashSet);
            }
            Set<Path> set = linkedHashSet;
            Iterator<T> it = paths.iterator();
            while (it.hasNext()) {
                set.add(Paths.get((String) it.next(), new String[0]));
            }
        }

        public final DependencyListForCliModule build() {
            Map<FirBinaryDependenciesModuleData, Set<Path>> map = this.filtersMap;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<FirBinaryDependenciesModuleData, Set<Path>> entry : map.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                linkedHashMap2.put(entry2.getKey(), new LibraryPathFilter.LibraryList((Set) entry2.getValue()));
            }
            List<Map.Entry> listSortedWith = CollectionsKt.sortedWith(linkedHashMap2.entrySet(), new Comparator() { // from class: org.jetbrains.kotlin.fir.DependencyListForCliModule$Builder$build$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int i;
                    FirBinaryDependenciesModuleData firBinaryDependenciesModuleData = (FirBinaryDependenciesModuleData) ((Map.Entry) t).getKey();
                    int i2 = 4;
                    if (this.this$0.allDependsOnDependencies.contains(firBinaryDependenciesModuleData)) {
                        i = 1;
                    } else if (this.this$0.allFriendDependencies.contains(firBinaryDependenciesModuleData)) {
                        i = 2;
                    } else {
                        i = this.this$0.allRegularDependencies.contains(firBinaryDependenciesModuleData) ? 3 : 4;
                    }
                    Integer numValueOf = Integer.valueOf(i);
                    FirBinaryDependenciesModuleData firBinaryDependenciesModuleData2 = (FirBinaryDependenciesModuleData) ((Map.Entry) t2).getKey();
                    if (this.this$0.allDependsOnDependencies.contains(firBinaryDependenciesModuleData2)) {
                        i2 = 1;
                    } else if (this.this$0.allFriendDependencies.contains(firBinaryDependenciesModuleData2)) {
                        i2 = 2;
                    } else if (this.this$0.allRegularDependencies.contains(firBinaryDependenciesModuleData2)) {
                        i2 = 3;
                    }
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(i2));
                }
            });
            LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10)), 16));
            for (Map.Entry entry3 : listSortedWith) {
                Pair pair = TuplesKt.to((FirBinaryDependenciesModuleData) entry3.getKey(), (LibraryPathFilter.LibraryList) entry3.getValue());
                linkedHashMap3.put(pair.getFirst(), pair.getSecond());
            }
            Map mutableMap = MapsKt.toMutableMap(linkedHashMap3);
            List<FirBinaryDependenciesModuleData> listBuild$filterUsedModules = build$filterUsedModules(this.allRegularDependencies, mutableMap);
            List<FirBinaryDependenciesModuleData> listBuild$filterUsedModules2 = build$filterUsedModules(this.allFriendDependencies, mutableMap);
            List<FirBinaryDependenciesModuleData> listBuild$filterUsedModules3 = build$filterUsedModules(this.allDependsOnDependencies, mutableMap);
            FirBinaryDependenciesModuleData firBinaryDependenciesModuleData = (FirBinaryDependenciesModuleData) CollectionsKt.singleOrNull(this.allRegularDependencies);
            if (firBinaryDependenciesModuleData != null) {
                mutableMap.putIfAbsent(firBinaryDependenciesModuleData, LibraryPathFilter.TakeAll.INSTANCE);
                if (!listBuild$filterUsedModules.contains(firBinaryDependenciesModuleData)) {
                    listBuild$filterUsedModules.add(firBinaryDependenciesModuleData);
                }
            }
            return new DependencyListForCliModule(listBuild$filterUsedModules, listBuild$filterUsedModules3, listBuild$filterUsedModules2, new MultipleModuleDataProvider(mutableMap, (FirModuleData) CollectionsKt.first(listBuild$filterUsedModules)));
        }

        public final FirBinaryDependenciesModuleData createData(String name) {
            name.getClass();
            Name nameSpecial = Name.special(name);
            nameSpecial.getClass();
            return new FirBinaryDependenciesModuleData(nameSpecial, null, 2, null);
        }

        public final void defaultDependenciesSet(Name mainModuleName, Function1<? super BuilderForDefaultDependenciesModule, Unit> init) {
            mainModuleName.getClass();
            init.getClass();
            init.invoke(new BuilderForDefaultDependenciesModule(this, createData("<regular dependencies of " + mainModuleName + '>'), createData("<dependsOn dependencies of " + mainModuleName + '>'), createData("<friends dependencies of " + mainModuleName + '>')));
        }

        public final void dependsOnDependencies(FirBinaryDependenciesModuleData moduleData, Collection<String> paths) {
            moduleData.getClass();
            paths.getClass();
            dependencies(moduleData, paths, this.allDependsOnDependencies);
        }

        public final void friendDependencies(FirBinaryDependenciesModuleData moduleData, Collection<String> paths) {
            moduleData.getClass();
            paths.getClass();
            dependencies(moduleData, paths, this.allFriendDependencies);
        }

        public final void dependencies(FirBinaryDependenciesModuleData moduleData, Collection<String> paths) {
            moduleData.getClass();
            paths.getClass();
            dependencies(moduleData, paths, this.allRegularDependencies);
        }
    }
}
