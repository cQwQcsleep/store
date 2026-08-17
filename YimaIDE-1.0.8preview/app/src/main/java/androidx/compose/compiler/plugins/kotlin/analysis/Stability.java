package androidx.compose.compiler.plugins.kotlin.analysis;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \f2\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000H\u0086\u0002J\u0017\u0010\u0004\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006H\u0086\u0002\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "", "<init>", "()V", "plus", "other", "", "Certain", "Runtime", "Unknown", "Parameter", "Combined", "Companion", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Certain;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Combined;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Parameter;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Runtime;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Unknown;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class Stability {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Stability Stable = new Certain(true);
    private static final Stability Unstable = new Certain(false);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Certain;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "stable", "", "<init>", "(Z)V", "getStable", "()Z", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Certain extends Stability {
        private final boolean stable;

        public Certain(boolean z) {
            super(null);
            this.stable = z;
        }

        public final boolean getStable() {
            return this.stable;
        }

        public String toString() {
            return this.stable ? "Stable" : "Unstable";
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Combined;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "elements", "", "<init>", "(Ljava/util/List;)V", "getElements", "()Ljava/util/List;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Combined extends Stability {
        private final List<Stability> elements;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Combined(List<? extends Stability> list) {
            super(null);
            list.getClass();
            this.elements = list;
        }

        public final List<Stability> getElements() {
            return this.elements;
        }

        public String toString() {
            return CollectionsKt.joinToString$default(this.elements, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Parameter;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "parameter", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;)V", "getParameter", "()Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Parameter extends Stability {
        private final IrTypeParameter parameter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Parameter(IrTypeParameter irTypeParameter) {
            super(null);
            irTypeParameter.getClass();
            this.parameter = irTypeParameter;
        }

        public final IrTypeParameter getParameter() {
            return this.parameter;
        }

        public String toString() {
            return "Parameter(" + this.parameter.getName().asString() + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Runtime;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Runtime extends Stability {
        private final IrClass declaration;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Runtime(IrClass irClass) {
            super(null);
            irClass.getClass();
            this.declaration = irClass;
        }

        public final IrClass getDeclaration() {
            return this.declaration;
        }

        public String toString() {
            return "Runtime(" + this.declaration.getName().asString() + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Unknown;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Unknown extends Stability {
        private final IrClass declaration;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unknown(IrClass irClass) {
            super(null);
            irClass.getClass();
            this.declaration = irClass;
        }

        public final IrClass getDeclaration() {
            return this.declaration;
        }

        public String toString() {
            return "Uncertain(" + this.declaration.getName().asString() + ')';
        }
    }

    public /* synthetic */ Stability(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x001f A[RETURN] */
    public final Stability plus(Stability other) {
        other.getClass();
        if (other instanceof Certain) {
            if (((Certain) other).getStable()) {
                return this;
            }
            return other;
        }
        if (!(this instanceof Certain)) {
            return new Combined(CollectionsKt.listOf(new Stability[]{this, other}));
        }
        if (((Certain) this).getStable()) {
            return other;
        }
        return this;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/Stability$Companion;", "", "<init>", "()V", "Stable", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "getStable", "()Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "Unstable", "getUnstable", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Stability getStable() {
            return Stability.Stable;
        }

        public final Stability getUnstable() {
            return Stability.Unstable;
        }

        private Companion() {
        }
    }

    private Stability() {
    }

    public final Stability plus(List<? extends Stability> other) {
        other.getClass();
        Iterator<? extends Stability> it = other.iterator();
        while (it.hasNext()) {
            this = this.plus(it.next());
        }
        return this;
    }
}
