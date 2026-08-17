package org.jetbrains.kotlin.analysis.decompiler.stub.flags;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/flags/FlagsToModifiers;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getModifiers", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "flags", "", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class FlagsToModifiers {
    public abstract KtModifierKeywordToken getModifiers(int flags);
}
