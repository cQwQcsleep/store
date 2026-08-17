package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeFunctionExpectedError;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "expression", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getExpression", "()Ljava/lang/String;", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "reason", "getReason", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeFunctionExpectedError implements ConeDiagnostic {
    private final String expression;
    private final ConeKotlinType type;

    public ConeFunctionExpectedError(String str, ConeKotlinType coneKotlinType) {
        str.getClass();
        coneKotlinType.getClass();
        this.expression = str;
        this.type = coneKotlinType;
    }

    public final String getExpression() {
        return this.expression;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Expression '" + this.expression + "' of type '" + this.type + "' cannot be invoked as a function";
    }

    public final ConeKotlinType getType() {
        return this.type;
    }
}
