package org.jetbrains.kotlin.cfg.pseudocode;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0017\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H¦\u0082\u0004¢\u0006\u0002\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/TypePredicate;", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/types/KotlinType;", "", "invoke", "typeToCheck", "(Lorg/jetbrains/kotlin/types/KotlinType;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface TypePredicate extends Function1<KotlinType, Boolean> {
    @Override // 
    Boolean invoke(KotlinType typeToCheck);
}
