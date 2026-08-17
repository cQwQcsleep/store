package org.jetbrains.kotlin.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/extensions/ExtensionPointDescriptor;", "T", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "extensionClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/String;Ljava/lang/Class;)V", "getName", "()Ljava/lang/String;", "getExtensionClass", "()Ljava/lang/Class;", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ExtensionPointDescriptor<T> {
    private final Class<T> extensionClass;
    private final String name;

    public ExtensionPointDescriptor(String str, Class<T> cls) {
        str.getClass();
        cls.getClass();
        this.name = str;
        this.extensionClass = cls;
    }

    public final Class<T> getExtensionClass() {
        return this.extensionClass;
    }

    public final String getName() {
        return this.name;
    }
}
