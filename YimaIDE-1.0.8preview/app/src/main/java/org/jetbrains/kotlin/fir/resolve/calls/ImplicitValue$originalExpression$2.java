package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ImplicitValue$originalExpression$2 extends FunctionReferenceImpl implements Function0<FirExpression> {
    public ImplicitValue$originalExpression$2(Object obj) {
        super(0, obj, ImplicitValue.class, "computeOriginalExpression", "computeOriginalExpression()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", 0);
    }

    public final FirExpression invoke() {
        return ((ImplicitValue) ((CallableReference) this).receiver).computeOriginalExpression();
    }
}
