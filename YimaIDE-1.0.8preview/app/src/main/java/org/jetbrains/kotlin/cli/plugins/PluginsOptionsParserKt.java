package org.jetbrains.kotlin.cli.plugins;

import com.intellij.util.containers.MultiMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentUtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption;
import org.jetbrains.kotlin.compiler.plugin.CliOptionProcessingException;
import org.jetbrains.kotlin.compiler.plugin.CliOptionValue;
import org.jetbrains.kotlin.compiler.plugin.CliOptionsKt;
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor;
import org.jetbrains.kotlin.compiler.plugin.PluginCliOptionProcessingException;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007\u001a\u000e\u0010\u0003\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0001\u001a,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004\u001a$\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00120\u00042\u0006\u0010\u000b\u001a\u00020\f\u001a\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"regularDelimiter", Argument.Delimiters.none, "classpathOptionsDelimiter", "extractPluginClasspathAndOptions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/plugins/PluginClasspathAndOptions;", "pluginConfigurations", Argument.Delimiters.none, "pluginConfiguration", "processCompilerPluginsOptions", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "pluginOptions", "commandLineProcessors", "Lorg/jetbrains/kotlin/compiler/plugin/CommandLineProcessor;", "processCompilerPluginOptions", "processor", "Lorg/jetbrains/kotlin/compiler/plugin/CliOptionValue;", "extractPluginOrderConstraint", "Lorg/jetbrains/kotlin/cli/plugins/PluginOrderConstraint;", "pluginOrderConstraint", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PluginsOptionsParserKt {
    public static final PluginClasspathAndOptions extractPluginClasspathAndOptions(String str) {
        List listEmptyList;
        List listSplit$default;
        str.getClass();
        String strSubstringBefore$default = StringsKt.substringBefore$default(str, "=", (String) null, 2, (Object) null);
        String strSubstringAfter = StringsKt.substringAfter(str, "=", Argument.Delimiters.none);
        List listSplit$default2 = StringsKt.split$default(strSubstringBefore$default, new String[]{Argument.Delimiters.default}, false, 0, 6, (Object) null);
        String str2 = !StringsKt.isBlank(strSubstringAfter) ? strSubstringAfter : null;
        if (str2 == null || (listSplit$default = StringsKt.split$default(str2, new String[]{Argument.Delimiters.default}, false, 0, 6, (Object) null)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList();
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                CliOptionValue modernPluginOption = CliOptionsKt.parseModernPluginOption((String) it.next());
                if (modernPluginOption != null) {
                    listEmptyList.add(modernPluginOption);
                }
            }
        }
        return new PluginClasspathAndOptions(str, listSplit$default2, listEmptyList);
    }

    public static final PluginOrderConstraint extractPluginOrderConstraint(String str) {
        str.getClass();
        List listSplit$default = StringsKt.split$default(str, new String[]{">"}, false, 0, 6, (Object) null);
        if (listSplit$default.size() != 2) {
            return null;
        }
        return new PluginOrderConstraint(StringsKt.trim((String) listSplit$default.get(0)).toString(), StringsKt.trim((String) listSplit$default.get(1)).toString(), str);
    }

    public static final void processCompilerPluginOptions(CommandLineProcessor commandLineProcessor, List<CliOptionValue> list, CompilerConfiguration compilerConfiguration) {
        commandLineProcessor.getClass();
        list.getClass();
        compilerConfiguration.getClass();
        Collection<AbstractCliOption> pluginOptions = commandLineProcessor.getPluginOptions();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(pluginOptions, 10)), 16));
        for (Object obj : pluginOptions) {
            linkedHashMap.put(((AbstractCliOption) obj).getOptionName(), obj);
        }
        MultiMap multiMap = new MultiMap();
        for (CliOptionValue cliOptionValue : list) {
            AbstractCliOption abstractCliOption = (AbstractCliOption) linkedHashMap.get(cliOptionValue.getOptionName());
            if (abstractCliOption == null) {
                throw new CliOptionProcessingException("Unsupported plugin option: " + cliOptionValue, null, 2, null);
            }
            multiMap.putValue(abstractCliOption, cliOptionValue);
        }
        for (AbstractCliOption abstractCliOption2 : commandLineProcessor.getPluginOptions()) {
            Collection collection = multiMap.get(abstractCliOption2);
            collection.getClass();
            if (abstractCliOption2.getRequired() && collection.isEmpty()) {
                throw new PluginCliOptionProcessingException(commandLineProcessor.getPluginId(), commandLineProcessor.getPluginOptions(), "Required plugin option not present: " + commandLineProcessor.getPluginId() + ':' + abstractCliOption2.getOptionName(), null, 8, null);
            }
            if (!abstractCliOption2.getAllowMultipleOccurrences() && collection.size() > 1) {
                throw new PluginCliOptionProcessingException(commandLineProcessor.getPluginId(), commandLineProcessor.getPluginOptions(), "Multiple values are not allowed for plugin option " + commandLineProcessor.getPluginId() + ':' + abstractCliOption2.getOptionName(), null, 8, null);
            }
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                commandLineProcessor.processOption(abstractCliOption2, ((CliOptionValue) it.next()).getValue(), compilerConfiguration);
            }
        }
    }

    public static final void processCompilerPluginsOptions(CompilerConfiguration compilerConfiguration, Iterable<String> iterable, List<? extends CommandLineProcessor> list) {
        Map mapEmptyMap;
        compilerConfiguration.getClass();
        list.getClass();
        if (iterable != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(CliOptionsKt.parseLegacyPluginOption(it.next()));
            }
            mapEmptyMap = new LinkedHashMap();
            for (Object obj : arrayList) {
                CliOptionValue cliOptionValue = (CliOptionValue) obj;
                if (cliOptionValue == null) {
                    StringBuilder sb = new StringBuilder("Wrong plugin option format: ");
                    sb.append(cliOptionValue);
                    String strValueDescription = ArgumentUtilsKt.getArgumentAnnotation(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.plugins.PluginsOptionsParserKt$processCompilerPluginsOptions$optionValuesByPlugin$2$1
                        public Object get(Object obj2) {
                            return ((CommonCompilerArguments) obj2).getPluginOptions();
                        }

                        public void set(Object obj2, Object obj3) {
                            ((CommonCompilerArguments) obj2).setPluginOptions((String[]) obj3);
                        }
                    }).valueDescription();
                    sb.append(", should be ");
                    sb.append(strValueDescription);
                    throw new CliOptionProcessingException(sb.toString(), null, 2, null);
                }
                String pluginId = cliOptionValue.getPluginId();
                Object arrayList2 = mapEmptyMap.get(pluginId);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    mapEmptyMap.put(pluginId, arrayList2);
                }
                ((List) arrayList2).add(obj);
            }
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        for (CommandLineProcessor commandLineProcessor : list) {
            List listEmptyList = (List) mapEmptyMap.get(commandLineProcessor.getPluginId());
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            listEmptyList.getClass();
            processCompilerPluginOptions(commandLineProcessor, listEmptyList, compilerConfiguration);
        }
    }

    public static final List<PluginClasspathAndOptions> extractPluginClasspathAndOptions(Iterable<String> iterable) {
        iterable.getClass();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(extractPluginClasspathAndOptions(it.next()));
        }
        return arrayList;
    }
}
