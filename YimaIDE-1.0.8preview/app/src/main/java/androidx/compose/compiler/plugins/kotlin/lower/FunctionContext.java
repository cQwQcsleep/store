package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0014H\u0016J\u0010\u0010%\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0014H\u0016J\u0010\u0010%\u001a\u00020#2\u0006\u0010$\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u001bH\u0016J\u0010\u0010)\u001a\u00020#2\u0006\u0010(\u001a\u00020\u001bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b!\u0010\r¨\u0006*"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/FunctionContext;", "Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "composable", "", "enclosingTryCount", "", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;ZI)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getComposable", "()Z", "getEnclosingTryCount", "()I", "setEnclosingTryCount", "(I)V", "locals", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getLocals", "()Ljava/util/Set;", "captures", "getCaptures", "collectors", "", "Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "getCollectors", "()Ljava/util/List;", "setCollectors", "(Ljava/util/List;)V", "canRemember", "getCanRemember", "declareLocal", "", "local", "recordCapture", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "pushCollector", "collector", "popCollector", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FunctionContext extends DeclarationContext {
    private final Set<IrValueDeclaration> captures;
    private List<CaptureCollector> collectors;
    private final boolean composable;
    private final IrFunction declaration;
    private int enclosingTryCount;
    private final Set<IrValueDeclaration> locals;

    public FunctionContext(IrFunction irFunction, boolean z, int i) {
        irFunction.getClass();
        this.declaration = irFunction;
        this.composable = z;
        this.enclosingTryCount = i;
        this.locals = new LinkedHashSet();
        this.captures = new LinkedHashSet();
        this.collectors = new ArrayList();
        Iterator it = mo278getDeclaration().getParameters().iterator();
        while (it.hasNext()) {
            declareLocal((IrValueParameter) it.next());
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void declareLocal(IrValueDeclaration local) {
        local.getClass();
        this.locals.add(local);
    }

    public final boolean getCanRemember() {
        return getComposable() && this.enclosingTryCount == 0;
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

    public final int getEnclosingTryCount() {
        return this.enclosingTryCount;
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
    public boolean recordCapture(IrValueDeclaration local) {
        local.getClass();
        boolean zContains = this.locals.contains(local);
        if (!this.collectors.isEmpty() && zContains) {
            Iterator<CaptureCollector> it = this.collectors.iterator();
            while (it.hasNext()) {
                it.next().recordCapture(local);
            }
        }
        if (AdditionalIrUtilsKt.isLocal(mo278getDeclaration()) && !zContains) {
            getCaptures().add(local);
        }
        return zContains;
    }

    public final void setCollectors(List<CaptureCollector> list) {
        list.getClass();
        this.collectors = list;
    }

    public final void setEnclosingTryCount(int i) {
        this.enclosingTryCount = i;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    /* JADX INFO: renamed from: getDeclaration, reason: from getter and merged with bridge method [inline-methods] */
    public IrFunction mo278getDeclaration() {
        return this.declaration;
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

    public /* synthetic */ FunctionContext(IrFunction irFunction, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(irFunction, z, (i2 & 4) != 0 ? 0 : i);
    }
}
