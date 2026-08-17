package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0007b\u0002\b\b¨\u0006\t"}, d2 = {"smartCastedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "isEq", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DfaInternals;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UtilKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.IDENTITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.NOT_EQ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.NOT_IDENTITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DfaInternals
    public static final boolean isEq(FirOperation firOperation) {
        firOperation.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firOperation.ordinal()];
        if (i == 1 || i == 2) {
            return true;
        }
        if (i == 3 || i == 4) {
            return false;
        }
        e4b.a(firOperation, " should not be there");
        return false;
    }

    public static final ConeKotlinType smartCastedType(TypeStatement typeStatement, ConeTypeContext coneTypeContext) {
        typeStatement.getClass();
        coneTypeContext.getClass();
        if (typeStatement.getUpperTypes().isEmpty()) {
            return typeStatement.getVariable().getOriginalType();
        }
        List mutableList = CollectionsKt.toMutableList(typeStatement.getUpperTypes());
        mutableList.add(typeStatement.getVariable().getOriginalType());
        return coneTypeContext.intersectTypes((Collection<? extends KotlinTypeMarker>) mutableList);
    }
}
