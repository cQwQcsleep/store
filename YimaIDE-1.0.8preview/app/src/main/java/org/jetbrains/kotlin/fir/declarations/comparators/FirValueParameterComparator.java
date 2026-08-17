package org.jetbrains.kotlin.fir.declarations.comparators;

import java.util.Comparator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.FirTypeRefComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/comparators/FirValueParameterComparator;", "Ljava/util/Comparator;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/Comparator;", "<init>", "()V", "compare", Argument.Delimiters.none, "a", "b", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirValueParameterComparator implements Comparator<FirValueParameter> {
    public static final FirValueParameterComparator INSTANCE = new FirValueParameterComparator();

    private FirValueParameterComparator() {
    }

    @Override // java.util.Comparator
    public int compare(FirValueParameter a, FirValueParameter b) {
        a.getClass();
        b.getClass();
        int iCompareTo = a.getName().compareTo(b.getName());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompare = FirTypeRefComparator.INSTANCE.compare(a.getReturnTypeRef(), b.getReturnTypeRef());
        if (iCompare != 0) {
            return iCompare;
        }
        int i = (a.getDefaultValue() != null ? 1 : 0) - (b.getDefaultValue() != null ? 1 : 0);
        if (i != 0) {
            return i;
        }
        return (a.getIsVararg() ? 1 : 0) - (b.getIsVararg() ? 1 : 0);
    }
}
