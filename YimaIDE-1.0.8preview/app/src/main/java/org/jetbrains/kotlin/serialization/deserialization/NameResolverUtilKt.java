package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"getClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "index", "", "getName", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:deserialization.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver nameResolver, int i) {
        nameResolver.getClass();
        return ClassId.INSTANCE.fromString(nameResolver.getQualifiedClassName(i), nameResolver.isLocalClassName(i));
    }

    public static final Name getName(NameResolver nameResolver, int i) {
        nameResolver.getClass();
        Name nameGuessByFirstCharacter = Name.guessByFirstCharacter(nameResolver.getString(i));
        nameGuessByFirstCharacter.getClass();
        return nameGuessByFirstCharacter;
    }
}
