package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/JvmKotlinType;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "kotlinType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)V", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getKotlinType", "()Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmKotlinType {
    private final KotlinTypeMarker kotlinType;
    private final Type type;

    public JvmKotlinType(Type type, KotlinTypeMarker kotlinTypeMarker) {
        type.getClass();
        this.type = type;
        this.kotlinType = kotlinTypeMarker;
    }

    public final KotlinTypeMarker getKotlinType() {
        return this.kotlinType;
    }

    public final Type getType() {
        return this.type;
    }

    public /* synthetic */ JvmKotlinType(Type type, KotlinTypeMarker kotlinTypeMarker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(type, (i & 2) != 0 ? null : kotlinTypeMarker);
    }
}
