package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u001a\u0010\u0006\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u001a\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0002\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"recordCapture", "", "", "Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "value", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "recordLocalDeclaration", "local", "recordLocalCapture", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "MAX_RESTART_ARGUMENT_COUNT", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposerLambdaMemoizationKt {
    private static final int MAX_RESTART_ARGUMENT_COUNT = 22;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void recordCapture(List<? extends DeclarationContext> list, IrValueDeclaration irValueDeclaration) {
        Iterator it = CollectionsKt.reversed(list).iterator();
        while (it.hasNext() && !((DeclarationContext) it.next()).recordCapture(irValueDeclaration)) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<IrValueDeclaration> recordLocalCapture(List<? extends DeclarationContext> list, IrSymbolOwner irSymbolOwner) {
        Set<IrValueDeclaration> set;
        List<? extends DeclarationContext> list2 = list;
        Iterator it = CollectionsKt.reversed(list2).iterator();
        do {
            if (!it.hasNext()) {
                set = null;
                break;
            }
            set = ((DeclarationContext) it.next()).getLocalDeclarationCaptures().get(irSymbolOwner);
        } while (set == null);
        if (set != null) {
            Iterator<T> it2 = set.iterator();
            while (it2.hasNext()) {
                recordCapture(list, (IrValueDeclaration) it2.next());
            }
            for (DeclarationContext declarationContext : CollectionsKt.reversed(list2)) {
                declarationContext.recordCapture(irSymbolOwner);
                if (declarationContext.getLocalDeclarationCaptures().containsKey(irSymbolOwner)) {
                    break;
                }
            }
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void recordLocalDeclaration(List<? extends DeclarationContext> list, DeclarationContext declarationContext) {
        Iterator it = CollectionsKt.reversed(list).iterator();
        while (it.hasNext()) {
            ((DeclarationContext) it.next()).recordLocalDeclaration(declarationContext);
        }
    }
}
