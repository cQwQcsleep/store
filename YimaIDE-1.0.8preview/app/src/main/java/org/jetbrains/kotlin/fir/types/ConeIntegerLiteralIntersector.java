package org.jetbrains.kotlin.fir.types;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007J\u001e\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0005H\u0002¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralIntersector;", Argument.Delimiters.none, "<init>", "()V", "findCommonIntersectionType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "types", Argument.Delimiters.none, "fold", "left", "right", "Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralType;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeIntegerLiteralIntersector {
    public static final ConeIntegerLiteralIntersector INSTANCE = new ConeIntegerLiteralIntersector();

    private ConeIntegerLiteralIntersector() {
    }

    private final ConeKotlinType fold(ConeKotlinType left, ConeKotlinType right) {
        if (left != null && right != null) {
            boolean z = left instanceof ConeIntegerLiteralType;
            if (z && (right instanceof ConeIntegerLiteralType)) {
                return fold((ConeIntegerLiteralType) left, (ConeIntegerLiteralType) right);
            }
            if (z) {
                return fold((ConeIntegerLiteralType) left, right);
            }
            if (right instanceof ConeIntegerLiteralType) {
                return fold((ConeIntegerLiteralType) right, left);
            }
        }
        return null;
    }

    public final ConeKotlinType findCommonIntersectionType(Collection<? extends ConeKotlinType> types) {
        types.getClass();
        if (types.isEmpty()) {
            return null;
        }
        Iterator<T> it = types.iterator();
        if (!it.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it.next();
        while (it.hasNext()) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) it.next();
            next = INSTANCE.fold((ConeKotlinType) next, coneKotlinType);
        }
        return (ConeKotlinType) next;
    }

    private final ConeKotlinType fold(ConeIntegerLiteralType left, ConeIntegerLiteralType right) {
        if (left.getPossibleTypes().containsAll(right.getPossibleTypes())) {
            return right;
        }
        if (right.getPossibleTypes().containsAll(left.getPossibleTypes())) {
            return left;
        }
        return null;
    }

    private final ConeKotlinType fold(ConeIntegerLiteralType left, ConeKotlinType right) {
        if (CollectionsKt.contains(left.getPossibleTypes(), right)) {
            return right;
        }
        return null;
    }
}
