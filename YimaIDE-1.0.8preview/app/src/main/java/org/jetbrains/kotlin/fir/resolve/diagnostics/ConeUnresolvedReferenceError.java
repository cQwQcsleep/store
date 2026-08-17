package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedReferenceError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedError;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "qualifier", Argument.Delimiters.none, "getQualifier", "()Ljava/lang/String;", "reason", "getReason", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeUnresolvedReferenceError implements ConeUnresolvedError {
    private final Name name;

    public ConeUnresolvedReferenceError(Name name) {
        name.getClass();
        this.name = name;
    }

    public final Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedError
    public String getQualifier() {
        if (this.name.isSpecial()) {
            return "NO_NAME";
        }
        String strAsString = this.name.asString();
        strAsString.getClass();
        return strAsString;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Unresolved reference: " + this.name.asString();
    }
}
