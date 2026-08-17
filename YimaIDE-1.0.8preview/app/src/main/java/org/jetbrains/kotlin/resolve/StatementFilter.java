package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.psi.KtExpression;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/StatementFilter;", "", "<init>", "()V", "filter", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "", "getFilter", "()Lkotlin/jvm/functions/Function1;", "Companion", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public class StatementFilter {
    public static final StatementFilter NONE = new StatementFilter() { // from class: org.jetbrains.kotlin.resolve.StatementFilter$Companion$NONE$1
        public String toString() {
            return "NONE";
        }
    };

    public Function1<KtExpression, Boolean> getFilter() {
        return null;
    }
}
