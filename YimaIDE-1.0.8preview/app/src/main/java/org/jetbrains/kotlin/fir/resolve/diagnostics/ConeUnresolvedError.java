package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0004\u0006\u0007\b\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedError;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "qualifier", Argument.Delimiters.none, "getQualifier", "()Ljava/lang/String;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedNameError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedReferenceError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedSymbolError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedTypeQualifierError;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ConeUnresolvedError extends ConeDiagnostic {
    String getQualifier();
}
