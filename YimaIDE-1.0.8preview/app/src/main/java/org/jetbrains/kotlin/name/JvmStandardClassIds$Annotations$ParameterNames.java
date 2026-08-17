package org.jetbrains.kotlin.name;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/name/JvmStandardClassIds$Annotations$ParameterNames;", Argument.Delimiters.none, "<init>", "()V", "jvmExposeBoxedName", "Lorg/jetbrains/kotlin/name/Name;", "getJvmExposeBoxedName", "()Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmStandardClassIds$Annotations$ParameterNames {
    public static final JvmStandardClassIds$Annotations$ParameterNames INSTANCE = new JvmStandardClassIds$Annotations$ParameterNames();
    private static final Name jvmExposeBoxedName;

    static {
        Name nameIdentifier = Name.identifier("jvmName");
        nameIdentifier.getClass();
        jvmExposeBoxedName = nameIdentifier;
    }

    private JvmStandardClassIds$Annotations$ParameterNames() {
    }

    public final Name getJvmExposeBoxedName() {
        return jvmExposeBoxedName;
    }
}
