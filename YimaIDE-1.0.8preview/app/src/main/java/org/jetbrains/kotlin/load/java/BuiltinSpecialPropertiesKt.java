package org.jetbrains.kotlin.load.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0006"}, d2 = {"child", "Lorg/jetbrains/kotlin/name/FqName;", "name", "", "childSafe", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class BuiltinSpecialPropertiesKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName child(FqName fqName, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return fqName.child(nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName childSafe(FqNameUnsafe fqNameUnsafe, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return fqNameUnsafe.child(nameIdentifier).toSafe();
    }
}
