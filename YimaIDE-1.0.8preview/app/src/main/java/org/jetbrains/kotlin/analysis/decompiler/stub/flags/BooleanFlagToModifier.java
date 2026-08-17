package org.jetbrains.kotlin.analysis.decompiler.stub.flags;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.metadata.deserialization.Flags;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/flags/BooleanFlagToModifier;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/flags/FlagsToModifiers;", "flagField", "Lorg/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField;", "ktModifierKeywordToken", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField;Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;)V", "getModifiers", "flags", "", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class BooleanFlagToModifier extends FlagsToModifiers {
    private final Flags.BooleanFlagField flagField;
    private final KtModifierKeywordToken ktModifierKeywordToken;

    public BooleanFlagToModifier(Flags.BooleanFlagField booleanFlagField, KtModifierKeywordToken ktModifierKeywordToken) {
        booleanFlagField.getClass();
        ktModifierKeywordToken.getClass();
        this.flagField = booleanFlagField;
        this.ktModifierKeywordToken = ktModifierKeywordToken;
    }

    @Override // org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsToModifiers
    public KtModifierKeywordToken getModifiers(int flags) {
        if (this.flagField.get(flags).booleanValue()) {
            return this.ktModifierKeywordToken;
        }
        return null;
    }
}
