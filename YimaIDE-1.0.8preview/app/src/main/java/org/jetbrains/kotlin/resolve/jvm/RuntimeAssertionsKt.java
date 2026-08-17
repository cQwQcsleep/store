package org.jetbrains.kotlin.resolve.jvm;

import com.intellij.openapi.util.text.StringUtil;
import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtExpression;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001d\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"textForRuntimeAssertionInfo", "", "Lorg/jetbrains/annotations/NotNull;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "getTextForRuntimeAssertionInfo", "(Lorg/jetbrains/kotlin/psi/KtExpression;)Ljava/lang/String;", "org.jetbrains.kotlin:frontend.java"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class RuntimeAssertionsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String getTextForRuntimeAssertionInfo(KtExpression ktExpression) {
        String strTrimMiddle = StringUtil.trimMiddle(ktExpression.getText(), 50);
        strTrimMiddle.getClass();
        return strTrimMiddle;
    }
}
