package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0003\"\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0003¨\u0006\t"}, d2 = {"isClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "(Lorg/jetbrains/kotlin/descriptors/ClassKind;)Z", "isInterface", "isEnumClass", "isEnumEntry", "isAnnotationClass", "isObject", "org.jetbrains.kotlin:compiler.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassKindKt {
    public static final boolean isAnnotationClass(ClassKind classKind) {
        classKind.getClass();
        return classKind == ClassKind.ANNOTATION_CLASS;
    }

    public static final boolean isClass(ClassKind classKind) {
        classKind.getClass();
        return classKind == ClassKind.CLASS;
    }

    public static final boolean isEnumClass(ClassKind classKind) {
        classKind.getClass();
        return classKind == ClassKind.ENUM_CLASS;
    }

    public static final boolean isEnumEntry(ClassKind classKind) {
        classKind.getClass();
        return classKind == ClassKind.ENUM_ENTRY;
    }

    public static final boolean isInterface(ClassKind classKind) {
        classKind.getClass();
        return classKind == ClassKind.INTERFACE;
    }

    public static final boolean isObject(ClassKind classKind) {
        classKind.getClass();
        return classKind == ClassKind.OBJECT;
    }
}
