package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "", "<init>", "()V", "captures", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getCaptures", "()Ljava/util/Set;", "capturedDeclarations", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "getCapturedDeclarations", "hasCaptures", "", "getHasCaptures", "()Z", "recordCapture", "", "local", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class CaptureCollector {
    private final Set<IrValueDeclaration> captures = new LinkedHashSet();
    private final Set<IrSymbolOwner> capturedDeclarations = new LinkedHashSet();

    public final Set<IrSymbolOwner> getCapturedDeclarations() {
        return this.capturedDeclarations;
    }

    public final Set<IrValueDeclaration> getCaptures() {
        return this.captures;
    }

    public final boolean getHasCaptures() {
        return (this.captures.isEmpty() && this.capturedDeclarations.isEmpty()) ? false : true;
    }

    public final void recordCapture(IrValueDeclaration local) {
        local.getClass();
        this.captures.add(local);
    }

    public final void recordCapture(IrSymbolOwner local) {
        local.getClass();
        this.capturedDeclarations.add(local);
    }
}
