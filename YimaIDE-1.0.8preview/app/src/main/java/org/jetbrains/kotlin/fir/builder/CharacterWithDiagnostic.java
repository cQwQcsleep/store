package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\b\u0010\f\u001a\u0004\u0018\u00010\u0003R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/CharacterWithDiagnostic;", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;)V", "value", Argument.Delimiters.none, "(C)V", "getValue", "()Ljava/lang/Character;", "Ljava/lang/Character;", "getDiagnostic", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CharacterWithDiagnostic {
    private final DiagnosticKind diagnostic;
    private final Character value;

    public CharacterWithDiagnostic(char c) {
        this.diagnostic = null;
        this.value = Character.valueOf(c);
    }

    public final DiagnosticKind getDiagnostic() {
        return this.diagnostic;
    }

    public final Character getValue() {
        return this.value;
    }

    public CharacterWithDiagnostic(DiagnosticKind diagnosticKind) {
        diagnosticKind.getClass();
        this.diagnostic = diagnosticKind;
        this.value = null;
    }
}
