package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u0004\u0018\u00010\u0001¨\u0006\u0005"}, d2 = {"toESType", "Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "Lorg/jetbrains/kotlin/types/KotlinType;", "isBoolean", Argument.Delimiters.none, "org.jetbrains.kotlin:resolution"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypesKt {
    public static final boolean isBoolean(ESType eSType) {
        return eSType instanceof ESBooleanType;
    }

    public static final ESType toESType(KotlinType kotlinType) {
        kotlinType.getClass();
        if (KotlinBuiltIns.isBoolean(kotlinType)) {
            return ESBooleanType.INSTANCE;
        }
        if (KotlinBuiltIns.isAny(kotlinType)) {
            return ESAnyType.INSTANCE;
        }
        if (KotlinBuiltIns.isNullableAny(kotlinType)) {
            return ESNullableAnyType.INSTANCE;
        }
        if (KotlinBuiltIns.isNothing(kotlinType)) {
            return ESNothingType.INSTANCE;
        }
        return KotlinBuiltIns.isNullableNothing(kotlinType) ? ESNullableNothingType.INSTANCE : new ESKotlinType(kotlinType);
    }
}
