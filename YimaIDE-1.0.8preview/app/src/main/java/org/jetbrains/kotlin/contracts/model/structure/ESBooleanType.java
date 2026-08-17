package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.SimpleType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESBooleanType;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "<init>", "()V", "toKotlinType", "Lorg/jetbrains/kotlin/types/KotlinType;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESBooleanType extends ESType {
    public static final ESBooleanType INSTANCE = new ESBooleanType();

    private ESBooleanType() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.contracts.model.structure.ESType
    public KotlinType toKotlinType(KotlinBuiltIns builtIns) {
        builtIns.getClass();
        SimpleType booleanType = builtIns.getBooleanType();
        booleanType.getClass();
        return booleanType;
    }
}
