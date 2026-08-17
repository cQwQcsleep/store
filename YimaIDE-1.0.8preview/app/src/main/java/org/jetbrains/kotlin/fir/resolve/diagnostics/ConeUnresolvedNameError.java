package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\r¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedNameError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedError;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "operatorToken", Argument.Delimiters.none, "receiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getOperatorToken", "()Ljava/lang/String;", "getReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "qualifier", "getQualifier", "reason", "getReason", "prettyReference", "getPrettyReference", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeUnresolvedNameError implements ConeUnresolvedError {
    private final Name name;
    private final String operatorToken;
    private final ConeKotlinType receiverType;

    public /* synthetic */ ConeUnresolvedNameError(Name name, String str, ConeKotlinType coneKotlinType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : coneKotlinType);
    }

    private final String getPrettyReference() {
        String str = this.operatorToken;
        Name name = this.name;
        if (str == null) {
            String string = name.toString();
            string.getClass();
            return string;
        }
        return name + " (" + str + ')';
    }

    public final Name getName() {
        return this.name;
    }

    public final String getOperatorToken() {
        return this.operatorToken;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedError
    public String getQualifier() {
        String strAsString = this.name.asString();
        strAsString.getClass();
        return strAsString;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Unresolved name: " + getPrettyReference();
    }

    public final ConeKotlinType getReceiverType() {
        return this.receiverType;
    }

    public ConeUnresolvedNameError(Name name, String str, ConeKotlinType coneKotlinType) {
        name.getClass();
        this.name = name;
        this.operatorToken = str;
        this.receiverType = coneKotlinType;
    }
}
