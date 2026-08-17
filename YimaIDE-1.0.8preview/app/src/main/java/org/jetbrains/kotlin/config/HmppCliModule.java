package org.jetbrains.kotlin.config;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/config/HmppCliModule;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, ModuleXmlParser.SOURCES, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/util/Set;)V", "getName", "()Ljava/lang/String;", "getSources", "()Ljava/util/Set;", "toString", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class HmppCliModule {
    private final String name;
    private final Set<String> sources;

    public HmppCliModule(String str, Set<String> set) {
        str.getClass();
        set.getClass();
        this.name = str;
        this.sources = set;
    }

    public final String getName() {
        return this.name;
    }

    public final Set<String> getSources() {
        return this.sources;
    }

    public String toString() {
        return "Module " + this.name;
    }
}
