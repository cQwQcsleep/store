package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import androidx.compose.compiler.plugins.kotlin.lower.IrSourcePrinterVisitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001]B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010B\u001a\u00020CH\u0016J\u001e\u0010D\u001a\u00020C2\u0006\u0010E\u001a\u00020F2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020I0HH\u0016J@\u0010J\u001a\u00020C2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0015H\u0016J:\u0010K\u001a\u00020C2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010S2\u0006\u0010T\u001a\u00020\u00152\u0006\u0010U\u001a\u00020\u0015H\u0016J\u0010\u0010V\u001a\u00020C2\u0006\u0010<\u001a\u00020\u000fH\u0016J\u001c\u0010W\u001a\u00020C2\n\u0010X\u001a\u00060Yj\u0002`Z2\u0006\u0010[\u001a\u00020\\H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u001d\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001a\u0010#\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001a\u0010&\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019R\u0014\u0010)\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0017R\u001a\u0010+\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0017\"\u0004\b,\u0010\u0019R\u001a\u0010-\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0017\"\u0004\b/\u0010\u0019R\u001a\u00100\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0017\"\u0004\b2\u0010\u0019R\u001a\u00103\u001a\u000204X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u000204X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00106\"\u0004\b;\u00108R\u001c\u0010<\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0011\"\u0004\b>\u0010\u0013R\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/FunctionMetricsImpl;", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "setFqName", "(Lorg/jetbrains/kotlin/name/FqName;)V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "composable", "", "getComposable", "()Z", "setComposable", "(Z)V", "skippable", "getSkippable", "setSkippable", "restartable", "getRestartable", "setRestartable", "readonly", "getReadonly", "setReadonly", "inline", "getInline", "setInline", "abstract", "getAbstract", "setAbstract", "open", "getOpen", "isLambda", "setLambda", "hasDefaults", "getHasDefaults", "setHasDefaults", "defaultsGroup", "getDefaultsGroup", "setDefaultsGroup", "groups", "", "getGroups", "()I", "setGroups", "(I)V", "calls", "getCalls", "setCalls", "scheme", "getScheme", "setScheme", "parameters", "", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetricsImpl$Param;", "recordGroup", "", "recordComposableCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "paramMeta", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "recordFunction", "recordParameter", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "default", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "defaultStatic", "used", "recordScheme", "print", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "src", "Landroidx/compose/compiler/plugins/kotlin/lower/IrSourcePrinterVisitor;", "Param", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FunctionMetricsImpl implements FunctionMetrics {
    private boolean abstract;
    private int calls;
    private boolean composable;
    private boolean defaultsGroup;
    private FqName fqName;
    private final IrFunction function;
    private int groups;
    private boolean hasDefaults;
    private boolean inline;
    private boolean isLambda;
    private String name;
    private final boolean open;
    private final List<Param> parameters;
    private boolean readonly;
    private boolean restartable;
    private String scheme;
    private boolean skippable;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u00060\u001dj\u0002`\u001e2\u0006\u0010\u001f\u001a\u00020 R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018¨\u0006!"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/FunctionMetricsImpl$Param;", "", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "default", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "defaultStatic", "", "used", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;Lorg/jetbrains/kotlin/ir/types/IrType;Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;ZZ)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "getType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "getStability", "()Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "getDefault", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "getDefaultStatic", "()Z", "getUsed", "print", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "src", "Landroidx/compose/compiler/plugins/kotlin/lower/IrSourcePrinterVisitor;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Param {
        private final IrValueParameter declaration;
        private final IrExpression default;
        private final boolean defaultStatic;
        private final Stability stability;
        private final IrType type;
        private final boolean used;

        public Param(IrValueParameter irValueParameter, IrType irType, Stability stability, IrExpression irExpression, boolean z, boolean z2) {
            irValueParameter.getClass();
            irType.getClass();
            stability.getClass();
            this.declaration = irValueParameter;
            this.type = irType;
            this.stability = stability;
            this.default = irExpression;
            this.defaultStatic = z;
            this.used = z2;
        }

        public final IrValueParameter getDeclaration() {
            return this.declaration;
        }

        public final IrExpression getDefault() {
            return this.default;
        }

        public final boolean getDefaultStatic() {
            return this.defaultStatic;
        }

        public final Stability getStability() {
            return this.stability;
        }

        public final IrType getType() {
            return this.type;
        }

        public final boolean getUsed() {
            return this.used;
        }

        public final void print(Appendable out, IrSourcePrinterVisitor src) throws IOException {
            out.getClass();
            src.getClass();
            if (!this.used) {
                out.append("unused ");
            }
            if (StabilityKt.knownStable(this.stability)) {
                out.append("stable ");
            } else if (StabilityKt.knownUnstable(this.stability)) {
                out.append("unstable ");
            }
            out.append(this.declaration.getName().asString());
            out.append(": ");
            out.append(src.printType(this.type));
            if (this.default != null) {
                out.append(" = ");
                if (this.defaultStatic) {
                    out.append("@static ");
                } else {
                    out.append("@dynamic ");
                }
                IrExpression irExpression = this.default;
                if ((irExpression instanceof IrConst) || (irExpression instanceof IrGetValue)) {
                    IrVisitorsKt.acceptVoid(irExpression, src);
                } else {
                    out.append("<expression>");
                }
            }
        }
    }

    public FunctionMetricsImpl(IrFunction irFunction) {
        irFunction.getClass();
        this.function = irFunction;
        this.fqName = AdditionalIrUtilsKt.getFqNameForIrSerialization(irFunction);
        String strAsString = irFunction.getName().asString();
        strAsString.getClass();
        this.name = strAsString;
        boolean z = false;
        this.abstract = (irFunction instanceof IrSimpleFunction) && ((IrSimpleFunction) irFunction).getModality() == Modality.ABSTRACT;
        if ((irFunction instanceof IrSimpleFunction) && ((IrSimpleFunction) irFunction).getModality() == Modality.OPEN) {
            z = true;
        }
        this.open = z;
        this.parameters = new ArrayList();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getAbstract() {
        return this.abstract;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public int getCalls() {
        return this.calls;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getComposable() {
        return this.composable;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getDefaultsGroup() {
        return this.defaultsGroup;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public FqName getFqName() {
        return this.fqName;
    }

    public final IrFunction getFunction() {
        return this.function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public int getGroups() {
        return this.groups;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getHasDefaults() {
        return this.hasDefaults;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getInline() {
        return this.inline;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public String getName() {
        return this.name;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getOpen() {
        return this.open;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getReadonly() {
        return this.readonly;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getRestartable() {
        return this.restartable;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public String getScheme() {
        return this.scheme;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public boolean getSkippable() {
        return this.skippable;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    /* JADX INFO: renamed from: isLambda, reason: from getter */
    public boolean getIsLambda() {
        return this.isLambda;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void print(Appendable out, IrSourcePrinterVisitor src) throws IOException {
        out.getClass();
        src.getClass();
        if (getRestartable()) {
            out.append("restartable ");
        }
        if (getSkippable()) {
            out.append("skippable ");
        }
        if (getReadonly()) {
            out.append("readonly ");
        }
        if (getInline()) {
            out.append("inline ");
        }
        if (getAbstract()) {
            out.append("abstract ");
        }
        if (getOpen()) {
            out.append("open ");
        }
        String scheme = getScheme();
        if (scheme != null) {
            out.append("scheme(\"" + scheme + "\") ");
        }
        out.append("fun ");
        out.append(getFqName().asString());
        if (this.parameters.isEmpty()) {
            out.append("()").append('\n');
            return;
        }
        out.append("(").append('\n');
        for (Param param : this.parameters) {
            out.append("  ");
            param.print(out, src);
            out.append('\n');
        }
        out.append(")");
        if (!ComposableFunctionBodyTransformerKt.isUnitOrNullableUnit(this.function.getReturnType())) {
            out.append(": ");
            out.append(src.printType(this.function.getReturnType()));
        }
        out.append('\n');
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordComposableCall(IrCall expression, List<ComposableFunctionBodyTransformer.CallArgumentMeta> paramMeta) {
        expression.getClass();
        paramMeta.getClass();
        setCalls(getCalls() + 1);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordFunction(boolean composable, boolean restartable, boolean skippable, boolean isLambda, boolean inline, boolean hasDefaults, boolean readonly) {
        setComposable(composable);
        setRestartable(restartable);
        setSkippable(skippable);
        setLambda(isLambda);
        setInline(inline);
        setHasDefaults(hasDefaults);
        setReadonly(readonly);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordGroup() {
        setGroups(getGroups() + 1);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordParameter(IrValueParameter declaration, IrType type, Stability stability, IrExpression irExpression, boolean defaultStatic, boolean used) {
        declaration.getClass();
        type.getClass();
        stability.getClass();
        this.parameters.add(new Param(declaration, type, stability, irExpression, defaultStatic, used));
    }

    @Override // androidx.compose.compiler.plugins.kotlin.FunctionMetrics
    public void recordScheme(String scheme) {
        scheme.getClass();
        setScheme(scheme);
    }

    public void setAbstract(boolean z) {
        this.abstract = z;
    }

    public void setCalls(int i) {
        this.calls = i;
    }

    public void setComposable(boolean z) {
        this.composable = z;
    }

    public void setDefaultsGroup(boolean z) {
        this.defaultsGroup = z;
    }

    public void setFqName(FqName fqName) {
        fqName.getClass();
        this.fqName = fqName;
    }

    public void setGroups(int i) {
        this.groups = i;
    }

    public void setHasDefaults(boolean z) {
        this.hasDefaults = z;
    }

    public void setInline(boolean z) {
        this.inline = z;
    }

    public void setLambda(boolean z) {
        this.isLambda = z;
    }

    public void setName(String str) {
        str.getClass();
        this.name = str;
    }

    public void setReadonly(boolean z) {
        this.readonly = z;
    }

    public void setRestartable(boolean z) {
        this.restartable = z;
    }

    public void setScheme(String str) {
        this.scheme = str;
    }

    public void setSkippable(boolean z) {
        this.skippable = z;
    }
}
