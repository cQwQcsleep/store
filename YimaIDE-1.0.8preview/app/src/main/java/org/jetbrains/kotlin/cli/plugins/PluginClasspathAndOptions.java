package org.jetbrains.kotlin.cli.plugins;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.compiler.plugin.CliOptionValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/plugins/PluginClasspathAndOptions;", Argument.Delimiters.none, "rawArgument", Argument.Delimiters.none, ModuleXmlParser.CLASSPATH, Argument.Delimiters.none, "options", "Lorg/jetbrains/kotlin/compiler/plugin/CliOptionValue;", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getRawArgument", "()Ljava/lang/String;", "getClasspath", "()Ljava/util/List;", "getOptions", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PluginClasspathAndOptions {
    private final List<String> classpath;
    private final List<CliOptionValue> options;
    private final String rawArgument;

    public PluginClasspathAndOptions(String str, List<String> list, List<CliOptionValue> list2) {
        str.getClass();
        list.getClass();
        list2.getClass();
        this.rawArgument = str;
        this.classpath = list;
        this.options = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PluginClasspathAndOptions copy$default(PluginClasspathAndOptions pluginClasspathAndOptions, String str, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pluginClasspathAndOptions.rawArgument;
        }
        if ((i & 2) != 0) {
            list = pluginClasspathAndOptions.classpath;
        }
        if ((i & 4) != 0) {
            list2 = pluginClasspathAndOptions.options;
        }
        return pluginClasspathAndOptions.copy(str, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRawArgument() {
        return this.rawArgument;
    }

    public final List<String> component2() {
        return this.classpath;
    }

    public final List<CliOptionValue> component3() {
        return this.options;
    }

    public final PluginClasspathAndOptions copy(String rawArgument, List<String> classpath, List<CliOptionValue> options) {
        rawArgument.getClass();
        classpath.getClass();
        options.getClass();
        return new PluginClasspathAndOptions(rawArgument, classpath, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginClasspathAndOptions)) {
            return false;
        }
        PluginClasspathAndOptions pluginClasspathAndOptions = (PluginClasspathAndOptions) other;
        return Intrinsics.areEqual(this.rawArgument, pluginClasspathAndOptions.rawArgument) && Intrinsics.areEqual(this.classpath, pluginClasspathAndOptions.classpath) && Intrinsics.areEqual(this.options, pluginClasspathAndOptions.options);
    }

    public final List<String> getClasspath() {
        return this.classpath;
    }

    public final List<CliOptionValue> getOptions() {
        return this.options;
    }

    public final String getRawArgument() {
        return this.rawArgument;
    }

    public int hashCode() {
        return (((this.rawArgument.hashCode() * 31) + this.classpath.hashCode()) * 31) + this.options.hashCode();
    }

    public String toString() {
        return "PluginClasspathAndOptions(rawArgument=" + this.rawArgument + ", classpath=" + this.classpath + ", options=" + this.options + ')';
    }
}
