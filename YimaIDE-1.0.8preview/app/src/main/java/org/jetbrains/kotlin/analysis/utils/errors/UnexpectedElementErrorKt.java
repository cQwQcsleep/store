package org.jetbrains.kotlin.analysis.utils.errors;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.analysis.utils.errors.UnexpectedElementErrorKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0006\u0018\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0086\b¨\u0006\u0007"}, d2 = {"unexpectedElementError", "", "elementName", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "", "ELEMENT", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UnexpectedElementErrorKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Void unexpectedElementError(String str, final Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        str.getClass();
        StringBuilder sb = new StringBuilder("Unexpected ");
        sb.append(str);
        sb.append(' ');
        sb.append(obj != null ? Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() : null);
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(sb.toString(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        exceptionAttachmentBuilder.withEntry(str, obj, new Function1() { // from class: c0f
            public final Object invoke(Object obj2) {
                return UnexpectedElementErrorKt.unexpectedElementError$lambda$1$0(obj, obj2);
            }
        });
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String unexpectedElementError$lambda$1$0(Object obj, Object obj2) {
        obj2.getClass();
        return String.valueOf(obj);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final /* synthetic */ <ELEMENT> Void unexpectedElementError(Object obj) throws KotlinNothingValueException, KotlinIllegalArgumentExceptionWithAttachments {
        Intrinsics.reifiedOperationMarker(4, "ELEMENT");
        String simpleName = Reflection.getOrCreateKotlinClass(Object.class).getSimpleName();
        if (simpleName == null) {
            Intrinsics.reifiedOperationMarker(4, "ELEMENT");
            simpleName = Object.class.getName();
        }
        unexpectedElementError(simpleName, obj);
        throw new KotlinNothingValueException();
    }
}
