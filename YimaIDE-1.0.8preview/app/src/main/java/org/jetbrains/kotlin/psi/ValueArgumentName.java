package org.jetbrains.kotlin.psi;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/ValueArgumentName;", "", "asName", "Lorg/jetbrains/kotlin/name/Name;", "getAsName", "()Lorg/jetbrains/kotlin/name/Name;", "referenceExpression", "Lorg/jetbrains/kotlin/psi/KtSimpleNameExpression;", "getReferenceExpression", "()Lorg/jetbrains/kotlin/psi/KtSimpleNameExpression;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface ValueArgumentName {
    Name getAsName();

    KtSimpleNameExpression getReferenceExpression();
}
