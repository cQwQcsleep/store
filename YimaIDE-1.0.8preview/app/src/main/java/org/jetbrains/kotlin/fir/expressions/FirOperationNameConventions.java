package org.jetbrains.kotlin.fir.expressions;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirOperationNameConventions;", Argument.Delimiters.none, "<init>", "()V", "ASSIGNMENTS", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "Lorg/jetbrains/kotlin/name/Name;", "getASSIGNMENTS", "()Ljava/util/Map;", "ASSIGNMENT_NAMES", "getASSIGNMENT_NAMES", "ASSIGNMENTS_TO_SIMPLE_OPERATOR", "getASSIGNMENTS_TO_SIMPLE_OPERATOR", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOperationNameConventions {
    private static final Map<FirOperation, Name> ASSIGNMENTS;
    private static final Map<FirOperation, Name> ASSIGNMENTS_TO_SIMPLE_OPERATOR;
    private static final Map<Name, FirOperation> ASSIGNMENT_NAMES;
    public static final FirOperationNameConventions INSTANCE = new FirOperationNameConventions();

    static {
        EnumMap enumMap = new EnumMap(MapsKt.mapOf(new Pair[]{TuplesKt.to(FirOperation.PLUS_ASSIGN, OperatorNameConventions.PLUS_ASSIGN), TuplesKt.to(FirOperation.MINUS_ASSIGN, OperatorNameConventions.MINUS_ASSIGN), TuplesKt.to(FirOperation.TIMES_ASSIGN, OperatorNameConventions.TIMES_ASSIGN), TuplesKt.to(FirOperation.DIV_ASSIGN, OperatorNameConventions.DIV_ASSIGN), TuplesKt.to(FirOperation.REM_ASSIGN, OperatorNameConventions.REM_ASSIGN)}));
        ASSIGNMENTS = enumMap;
        ArrayList arrayList = new ArrayList(enumMap.size());
        for (Map.Entry entry : enumMap.entrySet()) {
            arrayList.add(TuplesKt.to((Name) entry.getValue(), (FirOperation) entry.getKey()));
        }
        ASSIGNMENT_NAMES = MapsKt.toMap(arrayList);
        ASSIGNMENTS_TO_SIMPLE_OPERATOR = new EnumMap(MapsKt.mapOf(new Pair[]{TuplesKt.to(FirOperation.PLUS_ASSIGN, OperatorNameConventions.PLUS), TuplesKt.to(FirOperation.MINUS_ASSIGN, OperatorNameConventions.MINUS), TuplesKt.to(FirOperation.TIMES_ASSIGN, OperatorNameConventions.TIMES), TuplesKt.to(FirOperation.DIV_ASSIGN, OperatorNameConventions.DIV), TuplesKt.to(FirOperation.REM_ASSIGN, OperatorNameConventions.REM)}));
    }

    private FirOperationNameConventions() {
    }

    public final Map<FirOperation, Name> getASSIGNMENTS() {
        return ASSIGNMENTS;
    }

    public final Map<FirOperation, Name> getASSIGNMENTS_TO_SIMPLE_OPERATOR() {
        return ASSIGNMENTS_TO_SIMPLE_OPERATOR;
    }

    public final Map<Name, FirOperation> getASSIGNMENT_NAMES() {
        return ASSIGNMENT_NAMES;
    }
}
