package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH&J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\bH&J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H&J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH&J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH&R#\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "", "<init>", "()V", "localDeclarationCaptures", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getLocalDeclarationCaptures", "()Ljava/util/Map;", "recordLocalDeclaration", "", "local", "composable", "", "getComposable", "()Z", "declaration", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "captures", "getCaptures", "()Ljava/util/Set;", "declareLocal", "recordCapture", "pushCollector", "collector", "Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "popCollector", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class DeclarationContext {
    private final Map<IrSymbolOwner, Set<IrValueDeclaration>> localDeclarationCaptures = new LinkedHashMap();

    public abstract void declareLocal(IrValueDeclaration local);

    public abstract Set<IrValueDeclaration> getCaptures();

    public abstract boolean getComposable();

    /* JADX INFO: renamed from: getDeclaration */
    public abstract IrSymbolOwner mo278getDeclaration();

    public final Map<IrSymbolOwner, Set<IrValueDeclaration>> getLocalDeclarationCaptures() {
        return this.localDeclarationCaptures;
    }

    public abstract void popCollector(CaptureCollector collector);

    public abstract void pushCollector(CaptureCollector collector);

    public abstract void recordCapture(IrSymbolOwner local);

    public abstract boolean recordCapture(IrValueDeclaration local);

    public final void recordLocalDeclaration(DeclarationContext local) {
        local.getClass();
        this.localDeclarationCaptures.put(local.mo278getDeclaration(), local.getCaptures());
    }
}
