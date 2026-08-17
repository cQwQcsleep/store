package org.jetbrains.kotlin.compiler.plugin;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/PluginCliOptionProcessingException;", "Lorg/jetbrains/kotlin/compiler/plugin/CliOptionProcessingException;", "pluginId", Argument.Delimiters.none, "options", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/AbstractCliOption;", "message", "cause", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/util/Collection;Ljava/lang/String;Ljava/lang/Throwable;)V", "getPluginId", "()Ljava/lang/String;", "getOptions", "()Ljava/util/Collection;", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PluginCliOptionProcessingException extends CliOptionProcessingException {
    private final Collection<AbstractCliOption> options;
    private final String pluginId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PluginCliOptionProcessingException(String str, Collection<? extends AbstractCliOption> collection, String str2, Throwable th) {
        super(str2, th);
        str.getClass();
        collection.getClass();
        str2.getClass();
        this.pluginId = str;
        this.options = collection;
    }

    public final Collection<AbstractCliOption> getOptions() {
        return this.options;
    }

    public final String getPluginId() {
        return this.pluginId;
    }

    public /* synthetic */ PluginCliOptionProcessingException(String str, Collection collection, String str2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, collection, str2, (i & 8) != 0 ? null : th);
    }
}
