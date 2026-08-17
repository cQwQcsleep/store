package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", Argument.Delimiters.none, "<init>", "()V", "toKotlinType", "Lorg/jetbrains/kotlin/types/KotlinType;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESBooleanType;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESBuiltInType;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESKotlinType;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ESType {
    public /* synthetic */ ESType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract KotlinType toKotlinType(KotlinBuiltIns builtIns);

    private ESType() {
    }
}
