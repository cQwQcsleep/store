package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/SymbolOwnerContext;", "Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "composable", "", "getComposable", "()Z", "captures", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getCaptures", "()Ljava/util/Set;", "declareLocal", "", "local", "recordCapture", "pushCollector", "collector", "Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "popCollector", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SymbolOwnerContext extends DeclarationContext {
    private final IrSymbolOwner declaration;

    public SymbolOwnerContext(IrSymbolOwner irSymbolOwner) {
        irSymbolOwner.getClass();
        this.declaration = irSymbolOwner;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void declareLocal(IrValueDeclaration local) {
        local.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public Set<IrValueDeclaration> getCaptures() {
        return SetsKt.emptySet();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public boolean getComposable() {
        return false;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public IrSymbolOwner getDeclaration() {
        return this.declaration;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void popCollector(CaptureCollector collector) {
        collector.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void pushCollector(CaptureCollector collector) {
        collector.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public boolean recordCapture(IrValueDeclaration local) {
        local.getClass();
        return false;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void recordCapture(IrSymbolOwner local) {
        local.getClass();
    }
}
