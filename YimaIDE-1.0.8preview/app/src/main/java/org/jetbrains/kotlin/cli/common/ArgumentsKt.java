package org.jetbrains.kotlin.cli.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.ArgumentsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentField;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentParseErrors;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentUtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfiguratorKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonToolArguments;
import org.jetbrains.kotlin.cli.common.arguments.Disables;
import org.jetbrains.kotlin.cli.common.arguments.Enables;
import org.jetbrains.kotlin.cli.common.arguments.ManualLanguageFeatureSetting;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.HmppCliModule;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.IrVerificationMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.FlexibleTypeImpl;
import org.jetbrains.kotlin.utils.DFS;
import org.jetbrains.kotlin.utils.KotlinPaths;
import org.jetbrains.kotlin.utils.KotlinPathsFromHomeDir;
import org.jetbrains.kotlin.utils.PathUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u001a(\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u0002\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0016\u001a\u0014\u0010\u0017\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0016H\u0002\u001a\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"setupCommonArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "createMetadataVersion", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "setupMetadataVersion", "fromConfiguration", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter$Companion;", "configuration", "setupLanguageVersionSettings", "checkRedundantArguments", "KOTLIN_HOME_PROPERTY", Argument.Delimiters.none, "computeKotlinPaths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "reportArgumentParseProblems", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "reportUnsafeInternalArgumentsIfAny", "FRAGMENTS_ARG_NAME", "FRAGMENT_REFINES_ARG_NAME", "FRAGMENT_SOURCES_ARG_NAME", "FRAGMENT_DEPENDENCIES_ARG_NAME", "FRAGMENT_FRIEND_DEPENDENCIES_ARG_NAME", "buildHmppModuleStructure", "Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentsKt {
    public static final String KOTLIN_HOME_PROPERTY = "kotlin.home";
    private static final String FRAGMENTS_ARG_NAME = ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt$FRAGMENTS_ARG_NAME$1
        public Object get(Object obj) {
            return ((CommonCompilerArguments) obj).getFragments();
        }

        public void set(Object obj, Object obj2) {
            ((CommonCompilerArguments) obj).setFragments((String[]) obj2);
        }
    });
    private static final String FRAGMENT_REFINES_ARG_NAME = ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt$FRAGMENT_REFINES_ARG_NAME$1
        public Object get(Object obj) {
            return ((CommonCompilerArguments) obj).getFragmentRefines();
        }

        public void set(Object obj, Object obj2) {
            ((CommonCompilerArguments) obj).setFragmentRefines((String[]) obj2);
        }
    });
    private static final String FRAGMENT_SOURCES_ARG_NAME = ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt$FRAGMENT_SOURCES_ARG_NAME$1
        public Object get(Object obj) {
            return ((CommonCompilerArguments) obj).getFragmentSources();
        }

        public void set(Object obj, Object obj2) {
            ((CommonCompilerArguments) obj).setFragmentSources((String[]) obj2);
        }
    });
    private static final String FRAGMENT_DEPENDENCIES_ARG_NAME = ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt$FRAGMENT_DEPENDENCIES_ARG_NAME$1
        public Object get(Object obj) {
            return ((CommonCompilerArguments) obj).getFragmentDependencies();
        }

        public void set(Object obj, Object obj2) {
            ((CommonCompilerArguments) obj).setFragmentDependencies((String[]) obj2);
        }
    });
    private static final String FRAGMENT_FRIEND_DEPENDENCIES_ARG_NAME = ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt$FRAGMENT_FRIEND_DEPENDENCIES_ARG_NAME$1
        public Object get(Object obj) {
            return ((CommonCompilerArguments) obj).getFragmentFriendDependencies();
        }

        public void set(Object obj, Object obj2) {
            ((CommonCompilerArguments) obj).setFragmentFriendDependencies((String[]) obj2);
        }
    });

    public static CharSequence a(HmppCliModule hmppCliModule) {
        hmppCliModule.getClass();
        return hmppCliModule.getName();
    }

    public static Iterable b(Map map, HmppCliModule hmppCliModule) {
        List listEmptyList = (List) map.get(hmppCliModule);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    private static final HmppCliModuleStructure buildHmppModuleStructure(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments) {
        Pair pair;
        int i;
        String[] fragments = commonCompilerArguments.getFragments();
        String[] fragmentSources = commonCompilerArguments.getFragmentSources();
        String[] fragmentRefines = commonCompilerArguments.getFragmentRefines();
        int i2 = 1;
        if (fragments.length == 0) {
            if (!(fragmentRefines.length == 0)) {
                buildHmppModuleStructure$reportError(compilerConfiguration, FRAGMENT_REFINES_ARG_NAME + " flag can not be used without " + FRAGMENTS_ARG_NAME);
            }
            return null;
        }
        if (!CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion().getUsesK2()) {
            buildHmppModuleStructure$reportWarning(compilerConfiguration, FRAGMENTS_ARG_NAME + " flag is not supported for language version < 2.0");
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(fragments.length), 16));
        for (String str : fragments) {
            linkedHashMap.put(str, new LinkedHashSet());
        }
        for (String str2 : fragmentSources) {
            List listSplit$default = StringsKt.split$default(str2, new String[]{":"}, false, 2, 2, (Object) null);
            if (listSplit$default.size() < 2) {
                buildHmppModuleStructure$reportError(compilerConfiguration, "Incorrect syntax for " + FRAGMENT_SOURCES_ARG_NAME + " argument. `<module name>:<source file>` expected but got `" + str2 + '`');
            } else {
                String str3 = (String) listSplit$default.get(0);
                String str4 = (String) listSplit$default.get(1);
                Object obj = linkedHashMap.get(str3);
                if (obj == null) {
                    buildHmppModuleStructure$reportError(compilerConfiguration, "Passed " + str2 + ", but fragment `" + str3 + "` of source file " + str4 + " is not specified in " + FRAGMENTS_ARG_NAME);
                } else {
                    ((Set) obj).add(str4);
                }
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new HmppCliModule((String) entry.getKey(), (Set) entry.getValue()));
        }
        int size = arrayList.size();
        int i3 = 0;
        boolean z = false;
        while (i3 < size) {
            HmppCliModule hmppCliModule = (HmppCliModule) arrayList.get(i3);
            i3++;
            int size2 = arrayList.size();
            int i4 = i3;
            while (i4 < size2) {
                HmppCliModule hmppCliModule2 = (HmppCliModule) arrayList.get(i4);
                Set setIntersect = CollectionsKt.intersect(hmppCliModule.getSources(), hmppCliModule2.getSources());
                if (!setIntersect.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    if (setIntersect.size() == i2) {
                        sb.append("File '" + ((String) CollectionsKt.single(setIntersect)) + '\'');
                    } else {
                        sb.append("Files ");
                        sb.append(CollectionsKt.joinToString$default(setIntersect, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: af0
                            public final Object invoke(Object obj2) {
                                return ArgumentsKt.buildHmppModuleStructure$lambda$3$0((String) obj2);
                            }
                        }, 30, (Object) null));
                    }
                    sb.append(" can be a part of only one module, but is listed as a source for both `" + hmppCliModule.getName() + "` and `" + hmppCliModule2.getName() + "`, please check you " + FRAGMENT_SOURCES_ARG_NAME + " options.");
                    buildHmppModuleStructure$reportError(compilerConfiguration, sb.toString());
                    z = true;
                }
                i4++;
                i2 = 1;
            }
        }
        for (String str5 : commonCompilerArguments.getFreeArgs()) {
            if (!StringsKt.endsWith$default(str5, ".java", false, 2, (Object) null)) {
                if (!arrayList.isEmpty()) {
                    Iterator it = arrayList.iterator();
                    do {
                        if (it.hasNext()) {
                        }
                    } while (!((HmppCliModule) it.next()).getSources().contains(str5));
                }
                buildHmppModuleStructure$reportError(compilerConfiguration, "Source '" + str5 + "' does not belong to any module");
                z = true;
                break;
            }
        }
        if (z) {
            return null;
        }
        if (arrayList.size() == 1) {
            if (!(fragmentRefines.length == 0)) {
                buildHmppModuleStructure$reportError(compilerConfiguration, FRAGMENT_REFINES_ARG_NAME + " flag is specified but there is only one module declared");
            }
            return new HmppCliModuleStructure(arrayList, MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            HmppCliModule hmppCliModule3 = (HmppCliModule) obj2;
            if (arrayList.isEmpty()) {
                i = 0;
            } else {
                Iterator it2 = arrayList.iterator();
                i = 0;
                while (it2.hasNext()) {
                    if (Intrinsics.areEqual(((HmppCliModule) it2.next()).getName(), hmppCliModule3.getName()) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (i > 1) {
                arrayList2.add(obj2);
            }
        }
        if (!arrayList2.isEmpty()) {
            buildHmppModuleStructure$reportError(compilerConfiguration, "There are multiple modules with same name(s): " + CollectionsKt.joinToString$default(CollectionsKt.distinct(arrayList2), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: bf0
                public final Object invoke(Object obj3) {
                    return ArgumentsKt.a((HmppCliModule) obj3);
                }
            }, 30, (Object) null));
            return null;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (Object obj3 : arrayList) {
            linkedHashMap2.put(((HmppCliModule) obj3).getName(), obj3);
        }
        ArrayList<Pair> arrayList3 = new ArrayList();
        for (String str6 : fragmentRefines) {
            List listSplit$default2 = StringsKt.split$default(str6, new String[]{":"}, false, 0, 6, (Object) null);
            if (listSplit$default2.size() != 2) {
                buildHmppModuleStructure$reportError(compilerConfiguration, "Incorrect syntax for " + FRAGMENT_REFINES_ARG_NAME + " argument. Expected <fromModuleName>:<onModuleName> but got `" + fragmentRefines + '`');
                pair = null;
            } else {
                String str7 = (String) listSplit$default2.get(0);
                String str8 = (String) listSplit$default2.get(1);
                HmppCliModule hmppCliModuleBuildHmppModuleStructure$lambda$8$findModule = buildHmppModuleStructure$lambda$8$findModule(linkedHashMap2, str6, compilerConfiguration, str7);
                HmppCliModule hmppCliModuleBuildHmppModuleStructure$lambda$8$findModule2 = buildHmppModuleStructure$lambda$8$findModule(linkedHashMap2, str6, compilerConfiguration, str8);
                pair = (hmppCliModuleBuildHmppModuleStructure$lambda$8$findModule == null || hmppCliModuleBuildHmppModuleStructure$lambda$8$findModule2 == null) ? null : TuplesKt.to(hmppCliModuleBuildHmppModuleStructure$lambda$8$findModule, hmppCliModuleBuildHmppModuleStructure$lambda$8$findModule2);
            }
            if (pair != null) {
                arrayList3.add(pair);
            }
        }
        final LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Pair pair2 : arrayList3) {
            HmppCliModule hmppCliModule4 = (HmppCliModule) pair2.getFirst();
            Object arrayList4 = linkedHashMap3.get(hmppCliModule4);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList();
                linkedHashMap3.put(hmppCliModule4, arrayList4);
            }
            ((List) arrayList4).add((HmppCliModule) pair2.getSecond());
        }
        List list = DFS.topologicalOrder(arrayList, new DFS.Neighbors() { // from class: cf0
            public final Iterable getNeighbors(Object obj4) {
                return ArgumentsKt.b(linkedHashMap3, (HmppCliModule) obj4);
            }
        });
        list.getClass();
        List listAsReversedMutable = CollectionsKt.asReversedMutable(list);
        int i5 = 0;
        for (Object obj4 : listAsReversedMutable) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            HmppCliModule hmppCliModule5 = (HmppCliModule) obj4;
            List listEmptyList = (List) linkedHashMap3.get(hmppCliModule5);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List listSubList = listAsReversedMutable.subList(0, i5);
            List list2 = listEmptyList;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it3 = list2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    if (!listSubList.contains((HmppCliModule) it3.next())) {
                        buildHmppModuleStructure$reportError(compilerConfiguration, "There is a cycle in dependencies of module `" + hmppCliModule5.getName() + '`');
                        break;
                    }
                }
            } else {
                break;
                break;
            }
            i5 = i6;
        }
        if (!(commonCompilerArguments.getFragmentDependencies().length == 0) && !commonCompilerArguments.getSeparateKmpCompilationScheme()) {
            buildHmppModuleStructure$reportError(compilerConfiguration, FRAGMENT_DEPENDENCIES_ARG_NAME + " flag could be used only with " + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt.buildHmppModuleStructure.5
                public Object get(Object obj5) {
                    return Boolean.valueOf(((CommonCompilerArguments) obj5).getSeparateKmpCompilationScheme());
                }

                public void set(Object obj5, Object obj6) {
                    ((CommonCompilerArguments) obj5).setSeparateKmpCompilationScheme(((Boolean) obj6).booleanValue());
                }
            }));
        }
        if (!(commonCompilerArguments.getFragmentFriendDependencies().length == 0) && !commonCompilerArguments.getSeparateKmpCompilationScheme()) {
            buildHmppModuleStructure$reportError(compilerConfiguration, FRAGMENT_FRIEND_DEPENDENCIES_ARG_NAME + " flag could be used only with " + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt.buildHmppModuleStructure.6
                public Object get(Object obj5) {
                    return Boolean.valueOf(((CommonCompilerArguments) obj5).getSeparateKmpCompilationScheme());
                }

                public void set(Object obj5, Object obj6) {
                    ((CommonCompilerArguments) obj5).setSeparateKmpCompilationScheme(((Boolean) obj6).booleanValue());
                }
            }));
        }
        return new HmppCliModuleStructure(listAsReversedMutable, linkedHashMap3, buildHmppModuleStructure$buildFragmentDependencyMap(linkedHashMap2, compilerConfiguration, commonCompilerArguments.getFragmentDependencies(), FRAGMENT_DEPENDENCIES_ARG_NAME), buildHmppModuleStructure$buildFragmentDependencyMap(linkedHashMap2, compilerConfiguration, commonCompilerArguments.getFragmentFriendDependencies(), FRAGMENT_FRIEND_DEPENDENCIES_ARG_NAME));
    }

    private static final Map<HmppCliModule, List<String>> buildHmppModuleStructure$buildFragmentDependencyMap(Map<String, HmppCliModule> map, CompilerConfiguration compilerConfiguration, String[] strArr, String str) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        if (strArr == null) {
            strArr = new String[0];
        }
        for (String str2 : strArr) {
            List listSplit$default = StringsKt.split$default(str2, new String[]{":"}, false, 2, 2, (Object) null);
            if (listSplit$default.size() != 2) {
                buildHmppModuleStructure$reportError(compilerConfiguration, "Incorrect syntax for " + str + " argument. Expected <moduleName>:<path> but got `" + str2 + '`');
            } else {
                String str3 = (String) listSplit$default.get(0);
                String str4 = (String) listSplit$default.get(1);
                HmppCliModule hmppCliModule = map.get(str3);
                if (hmppCliModule == null) {
                    buildHmppModuleStructure$reportError(compilerConfiguration, "Module `" + str3 + "` not found in " + FRAGMENTS_ARG_NAME + " arguments");
                } else {
                    Object arrayList = mapCreateMapBuilder.get(hmppCliModule);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        mapCreateMapBuilder.put(hmppCliModule, arrayList);
                    }
                    ((List) arrayList).add(str4);
                }
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildHmppModuleStructure$lambda$3$0(String str) {
        str.getClass();
        return "'" + str + '\'';
    }

    private static final HmppCliModule buildHmppModuleStructure$lambda$8$findModule(Map<String, HmppCliModule> map, String str, CompilerConfiguration compilerConfiguration, String str2) {
        HmppCliModule hmppCliModule = map.get(str2);
        if (hmppCliModule == null) {
            buildHmppModuleStructure$reportError(compilerConfiguration, "`" + FRAGMENT_REFINES_ARG_NAME + '=' + str + "` Fragment `" + str2 + "` not found in " + FRAGMENTS_ARG_NAME + " arguments");
        }
        return hmppCliModule;
    }

    private static final void buildHmppModuleStructure$reportError(CompilerConfiguration compilerConfiguration, String str) {
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), str, null, 4, null);
    }

    private static final void buildHmppModuleStructure$reportWarning(CompilerConfiguration compilerConfiguration, String str) {
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), str, null, 4, null);
    }

    private static final void checkRedundantArguments(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments) {
        Object objLastOrNull;
        Disables disables;
        Enables enables;
        LanguageVersion languageVersion = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion();
        for (Map.Entry<ArgumentField, List<Object>> entry : commonCompilerArguments.getExplicitArguments().entrySet()) {
            ArgumentField key = entry.getKey();
            List<Object> value = entry.getValue();
            if (key.getChangesLanguageFeatures() && (objLastOrNull = CollectionsKt.lastOrNull(value)) != null) {
                Iterator<T> it = key.getEnablesAnnotations().iterator();
                do {
                    if (!it.hasNext()) {
                        Iterator<T> it2 = key.getDisablesAnnotations().iterator();
                        do {
                            if (!it2.hasNext()) {
                                String strConcat = objLastOrNull instanceof String ? "=".concat((String) objLastOrNull) : Argument.Delimiters.none;
                                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getREDUNDANT_CLI_ARG(), "The argument '" + key.getArgument().value() + strConcat + "' is redundant for the current language version " + languageVersion + '.', null, 4, null);
                                break;
                            }
                            disables = (Disables) it2.next();
                        } while (!checkRedundantArguments$checkNecessity(objLastOrNull, compilerConfiguration, disables.feature(), disables.ifValueIs(), LanguageFeature.State.DISABLED));
                    } else {
                        enables = (Enables) it.next();
                    }
                } while (!checkRedundantArguments$checkNecessity(objLastOrNull, compilerConfiguration, enables.feature(), enables.ifValueIs(), LanguageFeature.State.ENABLED));
            }
        }
    }

    private static final boolean checkRedundantArguments$checkNecessity(Object obj, CompilerConfiguration compilerConfiguration, LanguageFeature languageFeature, String str, LanguageFeature.State state) {
        if (str.length() == 0) {
            obj.getClass();
            if (!((Boolean) obj).booleanValue()) {
                w01.a("Failed requirement.");
                return false;
            }
        } else {
            obj.getClass();
            if (!Intrinsics.areEqual((String) obj, str)) {
                return false;
            }
        }
        return (state == LanguageFeature.State.ENABLED) != LanguageVersionSettingsKt.isEnabledByDefault(CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), languageFeature);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x005c  */
    /* JADX WARN: Code duplicated, block: B:18:0x0072 A[RETURN] */
    /* JADX WARN: Instruction removed from duplicated block: B:16:0x005c, please report this as an issue */
    public static final KotlinPaths computeKotlinPaths(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments) {
        File file;
        CompilerConfiguration compilerConfiguration2;
        KotlinPaths kotlinPathsFromHomeDir;
        compilerConfiguration.getClass();
        commonCompilerArguments.getClass();
        String property = System.getProperty(KOTLIN_HOME_PROPERTY);
        if (commonCompilerArguments.getKotlinHome() != null) {
            String kotlinHome = commonCompilerArguments.getKotlinHome();
            kotlinHome.getClass();
            file = new File(kotlinHome);
        } else {
            file = property != null ? new File(property) : null;
        }
        if (file != null) {
            if (file.isDirectory()) {
                kotlinPathsFromHomeDir = new KotlinPathsFromHomeDir(file);
            } else {
                compilerConfiguration2 = compilerConfiguration;
                CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Kotlin home does not exist or is not a directory: " + file, null, 4, null);
                kotlinPathsFromHomeDir = null;
            }
            if (kotlinPathsFromHomeDir != null) {
                return null;
            }
            CliDiagnosticReportingKt.reportLog(compilerConfiguration2, "Using Kotlin home directory " + kotlinPathsFromHomeDir.getHomePath(), null);
            return kotlinPathsFromHomeDir;
        }
        kotlinPathsFromHomeDir = PathUtil.getKotlinPathsForCompiler();
        compilerConfiguration2 = compilerConfiguration;
        if (kotlinPathsFromHomeDir != null) {
            return null;
        }
        CliDiagnosticReportingKt.reportLog(compilerConfiguration2, "Using Kotlin home directory " + kotlinPathsFromHomeDir.getHomePath(), null);
        return kotlinPathsFromHomeDir;
    }

    public static CharSequence d(ManualLanguageFeatureSetting manualLanguageFeatureSetting) {
        manualLanguageFeatureSetting.getClass();
        return manualLanguageFeatureSetting.getStringRepresentation();
    }

    public static final CommonCompilerArgumentsConfigurator.Reporter fromConfiguration(final CommonCompilerArgumentsConfigurator.Reporter.Companion companion, final CompilerConfiguration compilerConfiguration) {
        companion.getClass();
        compilerConfiguration.getClass();
        return new CommonCompilerArgumentsConfigurator.Reporter() { // from class: org.jetbrains.kotlin.cli.common.ArgumentsKt.fromConfiguration.1
            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void info(String message) {
                message.getClass();
                CliDiagnosticReportingKt.reportInfo$default(compilerConfiguration, message, null, 2, null);
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void report(KtSourcelessDiagnosticFactory factory, String message) {
                factory.getClass();
                message.getClass();
                CliDiagnosticReportingKt.report$default(compilerConfiguration, factory, message, null, 4, null);
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void reportError(String message) {
                message.getClass();
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), message, null, 4, null);
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void reportWarning(String message) {
                message.getClass();
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), message, null, 4, null);
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public CommonCompilerArgumentsConfigurator.Reporter withLanguageVersionSettings(LanguageVersionSettings languageVersionSettings) {
                languageVersionSettings.getClass();
                CompilerConfiguration compilerConfigurationCopy = compilerConfiguration.copy();
                CommonConfigurationKeysKt.setLanguageVersionSettings(compilerConfigurationCopy, languageVersionSettings);
                return ArgumentsKt.fromConfiguration(companion, compilerConfigurationCopy);
            }
        };
    }

    public static final void reportArgumentParseProblems(MessageCollector messageCollector, CommonToolArguments commonToolArguments) {
        messageCollector.getClass();
        commonToolArguments.getClass();
        for (Map.Entry<ArgumentField, List<Object>> entry : commonToolArguments.getExplicitArguments().entrySet()) {
            ArgumentField key = entry.getKey();
            List<Object> value = entry.getValue();
            if (value.size() > 1) {
                List<Object> list = value;
                if (CollectionsKt.distinct(list).size() != 1) {
                    MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, "Argument '" + key.getArgument().value() + "' is passed multiple times: '" + CollectionsKt.joinToString$default(list, "', '", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "'. The last value will be used.", null, 4, null);
                }
            }
        }
        ArgumentParseErrors errors = commonToolArguments.getErrors();
        if (errors == null) {
            return;
        }
        for (String str : errors.getUnknownExtraFlags()) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, "Flag is not supported by this version of the compiler: " + str, null, 4, null);
        }
        for (String str2 : errors.getExtraArgumentsPassedInObsoleteForm()) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, "Advanced option value is passed in an obsolete form. Please use the '=' character to specify the value: " + str2 + "=...", null, 4, null);
        }
        for (Map.Entry<String, String> entry2 : errors.getDeprecatedArguments().entrySet()) {
            String key2 = entry2.getKey();
            String value2 = entry2.getValue();
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, "Argument " + key2 + " is deprecated. Please use " + value2 + " instead", null, 4, null);
        }
        Iterator<String> it = errors.getArgfileErrors().iterator();
        while (it.hasNext()) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, it.next(), null, 4, null);
        }
        reportUnsafeInternalArgumentsIfAny(messageCollector, commonToolArguments);
        for (Pair<CompilerMessageSeverity, String> pair : errors.getInternalArgumentsParsingProblems()) {
            MessageCollector.report$default(messageCollector, (CompilerMessageSeverity) pair.component1(), (String) pair.component2(), null, 4, null);
        }
    }

    private static final void reportUnsafeInternalArgumentsIfAny(MessageCollector messageCollector, CommonToolArguments commonToolArguments) {
        List<ManualLanguageFeatureSetting> internalArguments = commonToolArguments.getInternalArguments();
        ArrayList arrayList = new ArrayList();
        for (Object obj : internalArguments) {
            ManualLanguageFeatureSetting manualLanguageFeatureSetting = (ManualLanguageFeatureSetting) obj;
            if (!manualLanguageFeatureSetting.getLanguageFeature().getActuallyEnabledInProgressiveMode() || manualLanguageFeatureSetting.getState() != LanguageFeature.State.ENABLED) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, "\n", "\n", "\n\n", 0, (CharSequence) null, new Function1() { // from class: ze0
            public final Object invoke(Object obj2) {
                return ArgumentsKt.d((ManualLanguageFeatureSetting) obj2);
            }
        }, 24, (Object) null);
        MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, "ATTENTION!\nThis build uses unsafe internal compiler arguments:\n" + strJoinToString$default + "This mode is not recommended for production use,\nas no stability/compatibility guarantees are given on\ncompiler or generated code. Use it at your own risk!\n", null, 4, null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0053  */
    /* JADX WARN: Code duplicated, block: B:18:0x00c9  */
    public static final void setupCommonArguments(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments, Function1<? super int[], ? extends BinaryVersion> function1) {
        IrVerificationMode irVerificationModeResolveMode;
        compilerConfiguration.getClass();
        commonCompilerArguments.getClass();
        CLIConfigurationKeysKt.setTreatWarningsAsErrors(compilerConfiguration, commonCompilerArguments.getAllWarningsAsErrors());
        compilerConfiguration.put(CommonConfigurationKeys.DISABLE_INLINE, Boolean.valueOf(commonCompilerArguments.getNoInline()));
        compilerConfiguration.put(CommonConfigurationKeys.USE_FIR_EXTRA_CHECKERS, Boolean.valueOf(commonCompilerArguments.getExtraWarnings()));
        compilerConfiguration.put(CommonConfigurationKeys.METADATA_KLIB, Boolean.valueOf(commonCompilerArguments.getMetadataKlib()));
        String dumpArgumentsDir = commonCompilerArguments.getDumpArgumentsDir();
        if (dumpArgumentsDir == null || dumpArgumentsDir.length() <= 0) {
            dumpArgumentsDir = null;
        } else {
            File file = new File(dumpArgumentsDir);
            if (!file.isDirectory() || !file.canWrite()) {
                dumpArgumentsDir = null;
            }
        }
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.DUMP_MODEL, dumpArgumentsDir);
        compilerConfiguration.putIfNotNull(CLIConfigurationKeys.INTELLIJ_PLUGIN_ROOT, commonCompilerArguments.getIntellijPluginRoot());
        compilerConfiguration.put(CommonConfigurationKeys.REPORT_OUTPUT_FILES, Boolean.valueOf(commonCompilerArguments.getReportOutputFiles()));
        compilerConfiguration.put(CommonConfigurationKeys.INCREMENTAL_COMPILATION, Boolean.valueOf(UtilsKt.incrementalCompilationIsEnabled(commonCompilerArguments)));
        compilerConfiguration.put(CommonConfigurationKeys.ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS, Boolean.valueOf(commonCompilerArguments.getAllowAnyScriptsInSourceRoots()));
        compilerConfiguration.put(CommonConfigurationKeys.IGNORE_CONST_OPTIMIZATION_ERRORS, Boolean.valueOf(commonCompilerArguments.getIgnoreConstOptimizationErrors()));
        compilerConfiguration.put(CLIConfigurationKeys.RENDER_DIAGNOSTIC_INTERNAL_NAME, Boolean.valueOf(commonCompilerArguments.getRenderInternalDiagnosticNames()));
        String verifyIr = commonCompilerArguments.getVerifyIr();
        if (verifyIr == null) {
            irVerificationModeResolveMode = IrVerificationMode.NONE;
        } else {
            irVerificationModeResolveMode = IrVerificationMode.INSTANCE.resolveMode(verifyIr);
            if (irVerificationModeResolveMode == null) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unsupported IR verification mode ".concat(verifyIr), null, 4, null);
            }
            if (irVerificationModeResolveMode == null) {
                irVerificationModeResolveMode = IrVerificationMode.NONE;
            }
        }
        compilerConfiguration.put(CommonConfigurationKeys.VERIFY_IR, irVerificationModeResolveMode);
        if (commonCompilerArguments.getVerifyIrVisibility()) {
            compilerConfiguration.put(CommonConfigurationKeys.ENABLE_IR_VISIBILITY_CHECKS, Boolean.TRUE);
            if (irVerificationModeResolveMode == IrVerificationMode.NONE) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "'-Xverify-ir-visibility' has no effect unless '-Xverify-ir=warning' or '-Xverify-ir=error' is specified", null, 4, null);
            }
        }
        if (commonCompilerArguments.getVerifyIrNestedOffsets()) {
            compilerConfiguration.put(CommonConfigurationKeys.ENABLE_IR_NESTED_OFFSETS_CHECKS, Boolean.TRUE);
            if (irVerificationModeResolveMode == IrVerificationMode.NONE) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "'-Xverify-ir-nested-offsets' has no effect unless '-Xverify-ir=warning' or '-Xverify-ir=error' is specified", null, 4, null);
            }
        }
        if (commonCompilerArguments.getUseFirExperimentalCheckers()) {
            compilerConfiguration.put(CommonConfigurationKeys.USE_FIR_EXPERIMENTAL_CHECKERS, Boolean.TRUE);
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "'-Xuse-fir-experimental-checkers' is deprecated and will be removed in a future release", null, 4, null);
        }
        setupMetadataVersion(compilerConfiguration, commonCompilerArguments, function1);
        setupLanguageVersionSettings(compilerConfiguration, commonCompilerArguments);
        checkRedundantArguments(compilerConfiguration, commonCompilerArguments);
        compilerConfiguration.put(CommonConfigurationKeys.USE_FIR, Boolean.valueOf(CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion().getUsesK2()));
        compilerConfiguration.put(CommonConfigurationKeys.USE_LIGHT_TREE, Boolean.valueOf(commonCompilerArguments.getUseFirLT()));
        HmppCliModuleStructure hmppCliModuleStructureBuildHmppModuleStructure = buildHmppModuleStructure(compilerConfiguration, commonCompilerArguments);
        if (hmppCliModuleStructureBuildHmppModuleStructure != null) {
            compilerConfiguration.put(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE, hmppCliModuleStructureBuildHmppModuleStructure);
        }
        if (commonCompilerArguments.getDebugLevelCompilerChecks()) {
            FlexibleTypeImpl.RUN_SLOW_ASSERTIONS = true;
            AbstractTypeChecker.RUN_SLOW_ASSERTIONS = true;
        }
        compilerConfiguration.put(CommonConfigurationKeys.DONT_SORT_SOURCE_FILES, Boolean.valueOf(commonCompilerArguments.getDontSortSourceFiles()));
    }

    public static /* synthetic */ void setupCommonArguments$default(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        setupCommonArguments(compilerConfiguration, commonCompilerArguments, function1);
    }

    public static final void setupLanguageVersionSettings(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments) {
        compilerConfiguration.getClass();
        commonCompilerArguments.getClass();
        CommonConfigurationKeysKt.setLanguageVersionSettings(compilerConfiguration, CommonCompilerArgumentsConfiguratorKt.toLanguageVersionSettings(commonCompilerArguments, fromConfiguration(CommonCompilerArgumentsConfigurator.Reporter.INSTANCE, compilerConfiguration)));
    }

    public static final void setupMetadataVersion(CompilerConfiguration compilerConfiguration, CommonCompilerArguments commonCompilerArguments, Function1<? super int[], ? extends BinaryVersion> function1) {
        compilerConfiguration.getClass();
        commonCompilerArguments.getClass();
        String metadataVersion = commonCompilerArguments.getMetadataVersion();
        if (metadataVersion != null) {
            int[] versionArray = BinaryVersion.Companion.parseVersionArray(metadataVersion);
            if (versionArray == null) {
                CliDiagnosticReportingKt.report(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Invalid metadata version: ".concat(metadataVersion), null);
            } else if (function1 != null) {
                compilerConfiguration.put(CommonConfigurationKeys.METADATA_VERSION, function1.invoke(versionArray));
            } else {
                k2d.a("Unable to create metadata version: missing argument");
            }
        }
    }
}
