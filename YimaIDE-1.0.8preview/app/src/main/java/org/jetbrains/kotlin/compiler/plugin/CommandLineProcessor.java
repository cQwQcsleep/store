package org.jetbrains.kotlin.compiler.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0010!\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J3\u0010\u0011\u001a\u00020\f\"\u0004\b\u0000\u0010\u0012*\u00020\u00102\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u00140\u00132\u0006\u0010\u000e\u001a\u0002H\u0012H\u0016¢\u0006\u0002\u0010\u0015J4\u0010\u0011\u001a\u00020\f\"\u0004\b\u0000\u0010\u0012*\u00020\u00102\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u00140\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0016J4\u0010\u0017\u001a\u00020\f*\u00020\u00102\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00140\u00192\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00120\u001b\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CommandLineProcessor;", Argument.Delimiters.none, "pluginId", Argument.Delimiters.none, "getPluginId", "()Ljava/lang/String;", "pluginOptions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/AbstractCliOption;", "getPluginOptions", "()Ljava/util/Collection;", "processOption", Argument.Delimiters.none, "option", "value", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "appendList", "T", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;Ljava/lang/Object;)V", "values", "applyOptionsFrom", "map", Argument.Delimiters.none, "asMutableList", Argument.Delimiters.none, "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@ExperimentalCompilerApi
public interface CommandLineProcessor {
    /* JADX WARN: Multi-variable type inference failed */
    private default <T> List<T> asMutableList(List<? extends T> list) {
        return list instanceof ArrayList ? list : CollectionsKt.toMutableList(list);
    }

    default <T> void appendList(CompilerConfiguration compilerConfiguration, CompilerConfigurationKey<? extends List<? extends T>> compilerConfigurationKey, List<? extends T> list) {
        compilerConfiguration.getClass();
        compilerConfigurationKey.getClass();
        list.getClass();
        List<T> listAsMutableList = asMutableList(compilerConfiguration.getList(compilerConfigurationKey));
        listAsMutableList.addAll(list);
        compilerConfiguration.put(compilerConfigurationKey, listAsMutableList);
    }

    default void applyOptionsFrom(CompilerConfiguration compilerConfiguration, Map<String, ? extends List<String>> map, Collection<? extends AbstractCliOption> collection) {
        Object next;
        compilerConfiguration.getClass();
        map.getClass();
        collection.getClass();
        for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            Iterator<T> it = collection.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((AbstractCliOption) next).getOptionName(), key));
            AbstractCliOption abstractCliOption = (AbstractCliOption) next;
            if (abstractCliOption != null) {
                Iterator<String> it2 = value.iterator();
                while (it2.hasNext()) {
                    processOption(abstractCliOption, it2.next(), compilerConfiguration);
                }
            }
        }
    }

    String getPluginId();

    Collection<AbstractCliOption> getPluginOptions();

    default void processOption(AbstractCliOption option, String value, CompilerConfiguration configuration) throws CliOptionProcessingException {
        option.getClass();
        value.getClass();
        configuration.getClass();
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> void appendList(CommandLineProcessor commandLineProcessor, CompilerConfiguration compilerConfiguration, CompilerConfigurationKey<? extends List<? extends T>> compilerConfigurationKey, List<? extends T> list) {
            compilerConfiguration.getClass();
            compilerConfigurationKey.getClass();
            list.getClass();
            CommandLineProcessor.super.appendList(compilerConfiguration, (CompilerConfigurationKey) compilerConfigurationKey, (List) list);
        }

        @Deprecated
        public static void applyOptionsFrom(CommandLineProcessor commandLineProcessor, CompilerConfiguration compilerConfiguration, Map<String, ? extends List<String>> map, Collection<? extends AbstractCliOption> collection) {
            compilerConfiguration.getClass();
            map.getClass();
            collection.getClass();
            CommandLineProcessor.super.applyOptionsFrom(compilerConfiguration, map, collection);
        }

        @Deprecated
        public static void processOption(CommandLineProcessor commandLineProcessor, AbstractCliOption abstractCliOption, String str, CompilerConfiguration compilerConfiguration) throws CliOptionProcessingException {
            abstractCliOption.getClass();
            str.getClass();
            compilerConfiguration.getClass();
            CommandLineProcessor.super.processOption(abstractCliOption, str, compilerConfiguration);
        }

        @Deprecated
        public static <T> void appendList(CommandLineProcessor commandLineProcessor, CompilerConfiguration compilerConfiguration, CompilerConfigurationKey<? extends List<? extends T>> compilerConfigurationKey, T t) {
            compilerConfiguration.getClass();
            compilerConfigurationKey.getClass();
            CommandLineProcessor.super.appendList(compilerConfiguration, compilerConfigurationKey, t);
        }
    }

    default <T> void appendList(CompilerConfiguration compilerConfiguration, CompilerConfigurationKey<? extends List<? extends T>> compilerConfigurationKey, T t) {
        compilerConfiguration.getClass();
        compilerConfigurationKey.getClass();
        List<T> listAsMutableList = asMutableList(compilerConfiguration.getList(compilerConfigurationKey));
        listAsMutableList.add(t);
        compilerConfiguration.put(compilerConfigurationKey, listAsMutableList);
    }
}
