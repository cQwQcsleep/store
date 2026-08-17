package defpackage;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class db8$a implements MessageCollector {
    public final /* synthetic */ Ref.BooleanRef a;
    public final /* synthetic */ Function1 b;

    public db8$a(Ref.BooleanRef booleanRef, Function1 function1) {
        this.a = booleanRef;
        this.b = function1;
    }

    public void clear() {
    }

    public boolean hasErrors() {
        return this.a.element;
    }

    public void report(CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        compilerMessageSeverity.getClass();
        str.getClass();
        String path = compilerMessageSourceLocation != null ? compilerMessageSourceLocation.getPath() : null;
        int line = compilerMessageSourceLocation != null ? compilerMessageSourceLocation.getLine() : 0;
        int column = compilerMessageSourceLocation != null ? compilerMessageSourceLocation.getColumn() : 0;
        if (compilerMessageSeverity.isError()) {
            this.a.element = true;
            this.b.invoke(t92.a.b(str, path, line, column) + "\n");
            return;
        }
        if (compilerMessageSeverity == CompilerMessageSeverity.STRONG_WARNING || compilerMessageSeverity == CompilerMessageSeverity.WARNING) {
            this.b.invoke(t92.a.k(str, path, line, column) + "\n");
        }
    }
}
