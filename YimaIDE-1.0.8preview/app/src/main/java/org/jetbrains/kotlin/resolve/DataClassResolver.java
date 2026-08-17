package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/resolve/DataClassResolver;", Argument.Delimiters.none, "<init>", "()V", "createComponentName", "Lorg/jetbrains/kotlin/name/Name;", "index", Argument.Delimiters.none, "getComponentIndex", "componentName", Argument.Delimiters.none, "isComponentLike", Argument.Delimiters.none, ModuleXmlParser.NAME, "isCopy", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DataClassResolver {
    public static final DataClassResolver INSTANCE = new DataClassResolver();

    private DataClassResolver() {
    }

    public final Name createComponentName(int index) {
        Name nameIdentifier = Name.identifier(StandardNames.DATA_CLASS_COMPONENT_PREFIX + index);
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    public final int getComponentIndex(String componentName) {
        componentName.getClass();
        return Integer.parseInt(componentName.substring(StandardNames.DATA_CLASS_COMPONENT_PREFIX.length()));
    }

    public final boolean isComponentLike(String name) {
        name.getClass();
        if (!StringsKt.startsWith$default(name, StandardNames.DATA_CLASS_COMPONENT_PREFIX, false, 2, (Object) null)) {
            return false;
        }
        try {
            getComponentIndex(name);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public final boolean isCopy(Name name) {
        name.getClass();
        return Intrinsics.areEqual(name, StandardNames.DATA_CLASS_COPY);
    }

    public final boolean isComponentLike(Name name) {
        name.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        return isComponentLike(strAsString);
    }
}
