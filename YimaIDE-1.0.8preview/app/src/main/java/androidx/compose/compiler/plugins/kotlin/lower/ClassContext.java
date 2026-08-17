package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u0016H\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u0016H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006#"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ClassContext;", "Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "composable", "", "getComposable", "()Z", "captures", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getCaptures", "()Ljava/util/Set;", "thisParam", "getThisParam", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "collectors", "", "Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "getCollectors", "()Ljava/util/List;", "setCollectors", "(Ljava/util/List;)V", "declareLocal", "", "local", "recordCapture", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "pushCollector", "collector", "popCollector", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ClassContext extends DeclarationContext {
    private final Set<IrValueDeclaration> captures;
    private List<CaptureCollector> collectors;
    private final boolean composable;
    private final IrClass declaration;
    private final IrValueDeclaration thisParam;

    public ClassContext(IrClass irClass) {
        irClass.getClass();
        this.declaration = irClass;
        this.captures = new LinkedHashSet();
        IrValueParameter thisReceiver = mo278getDeclaration().getThisReceiver();
        thisReceiver.getClass();
        this.thisParam = thisReceiver;
        this.collectors = new ArrayList();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void declareLocal(IrValueDeclaration local) {
        local.getClass();
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

    public final IrValueDeclaration getThisParam() {
        return this.thisParam;
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
        boolean zAreEqual = Intrinsics.areEqual(local, this.thisParam);
        IrConstructor parent = local.getParent();
        IrConstructor irConstructor = parent instanceof IrConstructor ? parent : null;
        boolean z = zAreEqual || ((irConstructor != null ? irConstructor.getParent() : null) == mo278getDeclaration());
        if (!this.collectors.isEmpty() && z) {
            Iterator<CaptureCollector> it = this.collectors.iterator();
            while (it.hasNext()) {
                it.next().recordCapture(local);
            }
        }
        if (AdditionalIrUtilsKt.isLocal(mo278getDeclaration()) && !z) {
            getCaptures().add(local);
        }
        return z;
    }

    public final void setCollectors(List<CaptureCollector> list) {
        list.getClass();
        this.collectors = list;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    /* JADX INFO: renamed from: getDeclaration, reason: from getter and merged with bridge method [inline-methods] */
    public IrClass mo278getDeclaration() {
        return this.declaration;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DeclarationContext
    public void recordCapture(IrSymbolOwner local) {
        local.getClass();
    }
}
