package org.jetbrains.kotlin.cfg;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cfg/WhenExhaustivenessChecker;", "", "getMissingCases", "", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "expression", "Lorg/jetbrains/kotlin/psi/KtWhenExpression;", "context", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "subjectDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "nullable", "", "isApplicable", "subjectType", "Lorg/jetbrains/kotlin/types/KotlinType;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
interface WhenExhaustivenessChecker {
    List<WhenMissingCase> getMissingCases(KtWhenExpression expression, BindingContext context, ClassDescriptor subjectDescriptor, boolean nullable);

    default boolean isApplicable(KotlinType subjectType) {
        subjectType.getClass();
        return false;
    }
}
