package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.types.model.AnnotationMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0010\b\u0000\u0010\u0001 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0007J\u0019\u0010\t\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0007J\u0017\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u000eH¦\u0080\u0004J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016R\u0014\u0010\u0010\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0012¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "T", "Lorg/jetbrains/kotlin/types/model/AnnotationMarker;", "<init>", "()V", "union", "other", "(Lorg/jetbrains/kotlin/fir/types/ConeAttribute;)Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "intersect", "add", "isSubtypeOf", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeAttribute;)Z", "toString", Argument.Delimiters.none, "renderForReadability", "implementsEquality", "getImplementsEquality", "()Z", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeAttribute<T extends ConeAttribute<? extends T>> implements AnnotationMarker {
    public abstract T add(T other);

    public boolean getImplementsEquality() {
        return false;
    }

    public abstract boolean getKeepInInferredDeclarationType();

    public abstract KClass<? extends T> getKey();

    public abstract T intersect(T other);

    public abstract boolean isSubtypeOf(T other);

    public String renderForReadability() {
        return null;
    }

    public abstract String toString();

    public abstract T union(T other);
}
