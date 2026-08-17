package org.jetbrains.kotlin.contracts.model.functors;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aO\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00012\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0005\u001a\u0004\u0018\u0001H\u00032\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00010\u0007H\u0000¢\u0006\u0002\u0010\b\u001aW\u0010\t\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0002*\u0002H\u0001\"\b\b\u0001\u0010\u0003*\u0002H\u0001\"\u0004\b\u0002\u0010\u00012\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0005\u001a\u0004\u0018\u0001H\u00032\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00010\u0007H\u0000¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"applyIfBothNotNull", "R", "F", "S", "first", "second", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "applyWithDefault", "org.jetbrains.kotlin:resolution"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctorsUtilsKt {
    public static final <F, S, R> R applyIfBothNotNull(F f, S s, Function2<? super F, ? super S, ? extends R> function2) {
        function2.getClass();
        if (f == null || s == null) {
            return null;
        }
        return (R) function2.invoke(f, s);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <F extends R, S extends R, R> R applyWithDefault(F f, S s, Function2<? super F, ? super S, ? extends R> function2) {
        function2.getClass();
        if (f == 0 && s == 0) {
            return null;
        }
        if (f == 0) {
            return s;
        }
        return s == 0 ? f : (R) function2.invoke(f, s);
    }
}
