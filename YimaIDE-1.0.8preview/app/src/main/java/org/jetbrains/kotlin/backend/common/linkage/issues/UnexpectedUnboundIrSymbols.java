package org.jetbrains.kotlin.backend.common.linkage.issues;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.fusesource.jansi.AnsiRenderer;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/UnexpectedUnboundIrSymbols;", "Lorg/jetbrains/kotlin/backend/common/linkage/issues/KotlinIrLinkerIssue;", "unboundSymbols", "", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "whenDetected", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Set;Ljava/lang/String;)V", "errorMessage", "getErrorMessage", "()Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnexpectedUnboundIrSymbols extends KotlinIrLinkerIssue {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String errorMessage;

    public UnexpectedUnboundIrSymbols(Set<? extends IrSymbol> set, String str) {
        String str2;
        set.getClass();
        str.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("There ");
        int size = set.size();
        if (size == 1) {
            str2 = "is still an unbound symbol";
        } else {
            str2 = "are still " + size + " unbound symbols";
        }
        sb.append(str2);
        sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
        sb.append(str);
        sb.append(":\n");
        Set<? extends IrSymbol> set2 = set;
        CollectionsKt.joinTo$default(set2, sb, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, TerminalTokens.TokenNameelse, (Object) null);
        sb.append("\n\nThis could happen if there are two libraries, where one library was compiled against the different version");
        sb.append(" of the other library than the one currently used in the project.");
        sb.append(" Please check that the project configuration is correct and has consistent versions of dependencies.");
        if (!(set2 instanceof Collection) || !set2.isEmpty()) {
            Iterator<T> it = set2.iterator();
            while (it.hasNext()) {
                if (INSTANCE.looksLikeEnumEntries(((IrSymbol) it.next()).getSignature())) {
                    sb.append("\n\nAnother possible reason is that some parts of the project are compiled with EnumEntries language feature enabled,");
                    sb.append(" but other parts or used libraries are compiled with EnumEntries language feature disabled.");
                    break;
                }
            }
        }
        this.errorMessage = sb.toString();
    }

    @Override // org.jetbrains.kotlin.backend.common.linkage.issues.KotlinIrLinkerIssue
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/UnexpectedUnboundIrSymbols$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "looksLikeEnumEntries", "", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean looksLikeEnumEntries(IdSignature signature) {
            if (signature instanceof IdSignature.AccessorSignature) {
                return looksLikeEnumEntries(((IdSignature.AccessorSignature) signature).getPropertySignature());
            }
            if (signature instanceof IdSignature.CompositeSignature) {
                return looksLikeEnumEntries(((IdSignature.CompositeSignature) signature).getInner());
            }
            if (signature instanceof IdSignature.CommonSignature) {
                return Intrinsics.areEqual(((IdSignature.CommonSignature) signature).getShortName(), "entries");
            }
            return false;
        }

        private Companion() {
        }
    }
}
