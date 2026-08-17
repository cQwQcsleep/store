package org.jetbrains.kotlin.ir.declarations.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003¨\u0006\b"}, d2 = {"SCRIPT_ORIGIN", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOriginImpl;", "getSCRIPT_ORIGIN", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOriginImpl;", "SCRIPT_K2_ORIGIN", "getSCRIPT_K2_ORIGIN", "REPL_SNIPPET_ORIGIN", "getREPL_SNIPPET_ORIGIN", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ScriptAndSnippetDetailsKt {
    private static final IrDeclarationOriginImpl SCRIPT_ORIGIN = new IrDeclarationOriginImpl("SCRIPT", false, 2, null);
    private static final IrDeclarationOriginImpl SCRIPT_K2_ORIGIN = new IrDeclarationOriginImpl("SCRIPT_K2", false, 2, null);
    private static final IrDeclarationOriginImpl REPL_SNIPPET_ORIGIN = new IrDeclarationOriginImpl("REPL_SNIPPET", false, 2, null);

    public static final IrDeclarationOriginImpl getREPL_SNIPPET_ORIGIN() {
        return REPL_SNIPPET_ORIGIN;
    }

    public static final IrDeclarationOriginImpl getSCRIPT_K2_ORIGIN() {
        return SCRIPT_K2_ORIGIN;
    }

    public static final IrDeclarationOriginImpl getSCRIPT_ORIGIN() {
        return SCRIPT_ORIGIN;
    }
}
