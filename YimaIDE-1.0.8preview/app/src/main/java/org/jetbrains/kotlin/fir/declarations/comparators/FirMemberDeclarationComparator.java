package org.jetbrains.kotlin.fir.declarations.comparators;

import java.util.Comparator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.types.FirTypeRefComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/comparators/FirMemberDeclarationComparator;", "Ljava/util/Comparator;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "Lkotlin/Comparator;", "<init>", "()V", "compare", Argument.Delimiters.none, "a", "b", "TypeAndNameComparator", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMemberDeclarationComparator implements Comparator<FirMemberDeclaration> {
    public static final FirMemberDeclarationComparator INSTANCE = new FirMemberDeclarationComparator();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/comparators/FirMemberDeclarationComparator$TypeAndNameComparator;", "Ljava/util/Comparator;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "Lkotlin/Comparator;", "<init>", "()V", "priority", Argument.Delimiters.none, "getPriority", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)I", "compare", "a", "b", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TypeAndNameComparator implements Comparator<FirMemberDeclaration> {
        public static final TypeAndNameComparator INSTANCE = new TypeAndNameComparator();

        private TypeAndNameComparator() {
        }

        private final int getPriority(FirMemberDeclaration firMemberDeclaration) {
            if (firMemberDeclaration instanceof FirEnumEntry) {
                return 7;
            }
            if (firMemberDeclaration instanceof FirConstructor) {
                return 6;
            }
            if (firMemberDeclaration instanceof FirProperty) {
                return 5;
            }
            if (firMemberDeclaration instanceof FirField) {
                return 4;
            }
            if (firMemberDeclaration instanceof FirFunction) {
                return 3;
            }
            if (firMemberDeclaration instanceof FirClass) {
                return 2;
            }
            if (firMemberDeclaration instanceof FirTypeAlias) {
                return 1;
            }
            if ((firMemberDeclaration instanceof FirValueParameter) || (firMemberDeclaration instanceof FirBackingField)) {
                return 0;
            }
            bu8.a();
            return 0;
        }

        @Override // java.util.Comparator
        public int compare(FirMemberDeclaration a, FirMemberDeclaration b) {
            a.getClass();
            b.getClass();
            int priority = getPriority(a) - getPriority(b);
            if (priority != 0) {
                return priority;
            }
            if (!(a instanceof FirEnumEntry)) {
                return FirDeclarationUtilKt.getNameOrSpecialName(a).compareTo(FirDeclarationUtilKt.getNameOrSpecialName(b));
            }
            if (b instanceof FirEnumEntry) {
                return 0;
            }
            rza.a("priority is inconsistent: ", UtilsKt.render(a), " v.s. ", UtilsKt.render(b));
            return 0;
        }
    }

    private FirMemberDeclarationComparator() {
    }

    @Override // java.util.Comparator
    public int compare(FirMemberDeclaration a, FirMemberDeclaration b) {
        a.getClass();
        b.getClass();
        if ((a instanceof FirCallableDeclaration) && (b instanceof FirCallableDeclaration)) {
            return FirCallableDeclarationComparator.INSTANCE.compare((FirCallableDeclaration) a, (FirCallableDeclaration) b);
        }
        int iCompare = TypeAndNameComparator.INSTANCE.compare(a, b);
        if (iCompare != 0) {
            return iCompare;
        }
        if (a instanceof FirClass) {
            if (b instanceof FirClass) {
                return FirDeclarationUtilKt.getClassId((FirClassLikeDeclaration) a).getPackageFqName().asString().compareTo(FirDeclarationUtilKt.getClassId((FirClassLikeDeclaration) b).getPackageFqName().asString());
            }
            rza.a("priority is inconsistent: ", UtilsKt.render(a), " v.s. ", UtilsKt.render(b));
            return 0;
        }
        if (!(a instanceof FirTypeAlias)) {
            s0g.a("Unsupported member declaration comparison: ", UtilsKt.render(a), " v.s. ", UtilsKt.render(b));
            return 0;
        }
        if (b instanceof FirTypeAlias) {
            return FirTypeRefComparator.INSTANCE.compare(((FirTypeAlias) a).getExpandedTypeRef(), ((FirTypeAlias) b).getExpandedTypeRef());
        }
        rza.a("priority is inconsistent: ", UtilsKt.render(a), " v.s. ", UtilsKt.render(b));
        return 0;
    }
}
