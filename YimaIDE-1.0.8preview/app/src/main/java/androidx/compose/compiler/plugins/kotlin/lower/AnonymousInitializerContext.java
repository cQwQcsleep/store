package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0015H\u0016J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0015H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/AnonymousInitializerContext;", "Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "composable", "", "getComposable", "()Z", "captures", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getCaptures", "()Ljava/util/Set;", "locals", "getLocals", "collectors", "", "Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "getCollectors", "()Ljava/util/List;", "setCollectors", "(Ljava/util/List;)V", "declareLocal", "", "local", "recordCapture", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "pushCollector", "collector", "popCollector", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class AnonymousInitializerContext extends DeclarationContext {
    private final Set<IrValueDeclaration> captures;
    private List<CaptureCollector> collectors;
    private final boolean composable;
    private final IrAnonymousInitializer declaration;
    private final Set<IrValueDeclaration> locals;

    public AnonymousInitializerContext(IrAnonymousInitializer irAnonymousInitializer) {
        irAnonymousInitializer.getClass();
        this.declaration = irAnonymousInitializer;
        this.captures = new LinkedHashSet();
        this.locals = new LinkedHashSet();
        this.collectors = new ArrayList();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void declareLocal(IrValueDeclaration local) {
        local.getClass();
        this.locals.add(local);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public Set<IrValueDeclaration> getCaptures() {
        return this.captures;
    }

    public final List<CaptureCollector> getCollectors() {
        return this.collectors;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public boolean getComposable() {
        return this.composable;
    }

    public final Set<IrValueDeclaration> getLocals() {
        return this.locals;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void popCollector(CaptureCollector collector) {
        collector.getClass();
        if (!Intrinsics.areEqual(CollectionsKt.lastOrNull(this.collectors), collector)) {
            w01.a("Failed requirement.");
        } else {
            List<CaptureCollector> list = this.collectors;
            list.remove(list.size() - 1);
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void pushCollector(CaptureCollector collector) {
        collector.getClass();
        this.collectors.add(collector);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void recordCapture(IrSymbolOwner local) {
        local.getClass();
        Set<IrValueDeclaration> set = getLocalDeclarationCaptures().get(local);
        for (CaptureCollector captureCollector : this.collectors) {
            captureCollector.recordCapture(local);
            if (set != null) {
                Iterator<IrValueDeclaration> it = set.iterator();
                while (it.hasNext()) {
                    captureCollector.recordCapture(it.next());
                }
            }
        }
    }

    public final void setCollectors(List<CaptureCollector> list) {
        list.getClass();
        this.collectors = list;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    /* JADX INFO: renamed from: getDeclaration, reason: from getter and merged with bridge method [inline-methods] */
    public IrAnonymousInitializer mo278getDeclaration() {
        return this.declaration;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public boolean recordCapture(IrValueDeclaration local) {
        local.getClass();
        boolean zContains = this.locals.contains(local);
        if (!this.collectors.isEmpty() && zContains) {
            Iterator<CaptureCollector> it = this.collectors.iterator();
            while (it.hasNext()) {
                it.next().recordCapture(local);
            }
        }
        if (!zContains) {
            getCaptures().add(local);
        }
        return zContains;
    }
}
