package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.CsvBuilder;
import androidx.compose.compiler.plugins.kotlin.JsonBuilder;
import androidx.compose.compiler.plugins.kotlin.ModuleMetricsImpl;
import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.IrSourcePrinterVisitor;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001UBB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012)\u0010\u0006\u001a%\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000201H\u0016J \u00108\u001a\u0002062\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\fH\u0016J \u0010>\u001a\u0002062\u0006\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020<H\u0016J\u001e\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020G0FH\u0016J\u0010\u0010H\u001a\u0002062\u0006\u0010I\u001a\u00020\u0003H\u0016J\u0010\u0010J\u001a\u000206*\u00060Kj\u0002`LH\u0016J\u0010\u0010M\u001a\u000206*\u00060Kj\u0002`LH\u0016J\u0010\u0010N\u001a\u000206*\u00060Kj\u0002`LH\u0016J\u0010\u0010O\u001a\u000206*\u00060Kj\u0002`LH\u0016J\u0010\u0010P\u001a\u0002062\u0006\u0010Q\u001a\u00020\u0003H\u0016J\u0010\u0010R\u001a\u0002062\u0006\u0010Q\u001a\u00020\u0003H\u0016J\u0010\u0010S\u001a\u0002012\u0006\u00107\u001a\u00020TH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R4\u0010\u0006\u001a%\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020100X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u00102\u001a\f\u0012\b\u0012\u000603R\u00020\u000000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020\u000300X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ModuleMetricsImpl;", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "name", "", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "stabilityOf", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "Lkotlin/ParameterName;", "fileContainingDependent", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "<init>", "(Ljava/lang/String;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;Lkotlin/jvm/functions/Function2;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getFeatureFlags", "()Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "getStabilityOf", "()Lkotlin/jvm/functions/Function2;", "skippableComposables", "", "restartableComposables", "readonlyComposables", "totalComposables", "restartGroups", "totalGroups", "staticArguments", "certainArguments", "knownStableArguments", "knownUnstableArguments", "unknownStableArguments", "totalArguments", "markedStableClasses", "inferredStableClasses", "inferredUnstableClasses", "inferredUncertainClasses", "effectivelyStableClasses", "totalClasses", "memoizedLambdas", "singletonLambdas", "singletonComposableLambdas", "composableLambdas", "totalLambdas", "composables", "", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "classes", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetricsImpl$ClassMetrics;", "logMessages", "recordFunction", "", "function", "recordClass", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "marked", "", "stability", "recordLambda", "composable", "memoized", "singleton", "recordComposableCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "paramMeta", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "log", "message", "appendModuleJson", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendComposablesCsv", "appendComposablesTxt", "appendClassesTxt", "saveMetricsTo", "directory", "saveReportsTo", "makeFunctionMetrics", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "ClassMetrics", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ModuleMetricsImpl implements ModuleMetrics {
    private int certainArguments;
    private final List<ClassMetrics> classes;
    private int composableLambdas;
    private final List<FunctionMetrics> composables;
    private int effectivelyStableClasses;
    private final FeatureFlags featureFlags;
    private int inferredStableClasses;
    private int inferredUncertainClasses;
    private int inferredUnstableClasses;
    private int knownStableArguments;
    private int knownUnstableArguments;
    private final List<String> logMessages;
    private int markedStableClasses;
    private int memoizedLambdas;
    private String name;
    private int readonlyComposables;
    private int restartGroups;
    private int restartableComposables;
    private int singletonComposableLambdas;
    private int singletonLambdas;
    private int skippableComposables;
    private final Function2<IrType, IrFile, Stability> stabilityOf;
    private int staticArguments;
    private int totalArguments;
    private int totalClasses;
    private int totalComposables;
    private int totalGroups;
    private int totalLambdas;
    private int unknownStableArguments;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0007H\u0002J\u001e\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\n\u0010\u0015\u001a\u00060\u0013j\u0002`\u00142\u0006\u0010\u0016\u001a\u00020\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ModuleMetricsImpl$ClassMetrics;", "", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "marked", "", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/ModuleMetricsImpl;Lorg/jetbrains/kotlin/ir/declarations/IrClass;ZLandroidx/compose/compiler/plugins/kotlin/analysis/Stability;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getMarked", "()Z", "getStability", "()Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "simpleHumanReadable", "", "print", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "src", "Landroidx/compose/compiler/plugins/kotlin/lower/IrSourcePrinterVisitor;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public final class ClassMetrics {
        private final IrClass declaration;
        private final boolean marked;
        private final Stability stability;
        final /* synthetic */ ModuleMetricsImpl this$0;

        public ClassMetrics(ModuleMetricsImpl moduleMetricsImpl, IrClass irClass, boolean z, Stability stability) {
            irClass.getClass();
            stability.getClass();
            this.this$0 = moduleMetricsImpl;
            this.declaration = irClass;
            this.marked = z;
            this.stability = stability;
        }

        private final String simpleHumanReadable(Stability stability) {
            if (StabilityKt.knownStable(stability)) {
                return "stable";
            }
            return StabilityKt.knownUnstable(stability) ? "unstable" : "runtime";
        }

        public final IrClass getDeclaration() {
            return this.declaration;
        }

        public final boolean getMarked() {
            return this.marked;
        }

        public final Stability getStability() {
            return this.stability;
        }

        /* JADX WARN: Code duplicated, block: B:26:0x00a0  */
        /* JADX WARN: Code duplicated, block: B:27:0x00a3  */
        /* JADX WARN: Code duplicated, block: B:37:0x0080 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:42:0x0040 A[SYNTHETIC] */
        public final Appendable print(Appendable out, IrSourcePrinterVisitor src) throws IOException {
            IrField backingField;
            String str;
            out.getClass();
            src.getClass();
            ModuleMetricsImpl moduleMetricsImpl = this.this$0;
            out.append(simpleHumanReadable(this.stability));
            out.append(" class ");
            Name fqNameWhenAvailable = IrUtilsKt.getFqNameWhenAvailable(this.declaration);
            if (fqNameWhenAvailable == null) {
                fqNameWhenAvailable = this.declaration.getName();
            }
            out.append(fqNameWhenAvailable.toString());
            out.append(" {").append('\n');
            for (IrProperty irProperty : this.declaration.getDeclarations()) {
                boolean z = irProperty instanceof IrProperty;
                boolean zIsVar = z ? irProperty.isVar() : irProperty instanceof IrField;
                if (z) {
                    backingField = irProperty.getBackingField();
                    if (backingField != null) {
                        if (!Intrinsics.areEqual(backingField.getName(), ComposeNames.INSTANCE.getStabilityFlag())) {
                            out.append("  ");
                            out.append(simpleHumanReadable((Stability) moduleMetricsImpl.getStabilityOf().invoke(backingField.getType(), IrUtilsKt.getFileOrNull(backingField))));
                            if (zIsVar) {
                                str = " var ";
                            } else {
                                str = " val ";
                            }
                            out.append(str);
                            out.append(backingField.getName().asString());
                            out.append(": ");
                            out.append(src.printType(backingField.getType()));
                            out.append('\n');
                        }
                    }
                } else if (irProperty instanceof IrField) {
                    backingField = (IrField) irProperty;
                    if (!Intrinsics.areEqual(backingField.getName(), ComposeNames.INSTANCE.getStabilityFlag())) {
                        out.append("  ");
                        out.append(simpleHumanReadable((Stability) moduleMetricsImpl.getStabilityOf().invoke(backingField.getType(), IrUtilsKt.getFileOrNull(backingField))));
                        if (zIsVar) {
                            str = " var ";
                        } else {
                            str = " val ";
                        }
                        out.append(str);
                        out.append(backingField.getName().asString());
                        out.append(": ");
                        out.append(src.printType(backingField.getType()));
                        out.append('\n');
                    }
                }
            }
            if (!this.marked) {
                out.append("  <runtime stability> = " + this.stability).append('\n');
            }
            return out.append("}").append('\n');
        }
    }

    public ModuleMetricsImpl(String str, FeatureFlags featureFlags, Function2<? super IrType, ? super IrFile, ? extends Stability> function2) {
        str.getClass();
        featureFlags.getClass();
        function2.getClass();
        this.name = str;
        this.featureFlags = featureFlags;
        this.stabilityOf = function2;
        this.composables = new ArrayList();
        this.classes = new ArrayList();
        this.logMessages = new ArrayList();
    }

    public static Unit a(ModuleMetricsImpl moduleMetricsImpl, OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.getClass();
        moduleMetricsImpl.appendClassesTxt(outputStreamWriter);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit appendComposablesCsv$lambda$0$0(CsvBuilder csvBuilder) throws IOException {
        csvBuilder.getClass();
        csvBuilder.col("package");
        csvBuilder.col("name");
        csvBuilder.col("composable");
        csvBuilder.col("skippable");
        csvBuilder.col("restartable");
        csvBuilder.col("readonly");
        csvBuilder.col("inline");
        csvBuilder.col("isLambda");
        csvBuilder.col("hasDefaults");
        csvBuilder.col("defaultsGroup");
        csvBuilder.col("groups");
        csvBuilder.col("calls");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit appendComposablesCsv$lambda$0$1(FunctionMetrics functionMetrics, CsvBuilder csvBuilder) throws IOException {
        csvBuilder.getClass();
        csvBuilder.col(functionMetrics.getFqName().asString());
        csvBuilder.col(functionMetrics.getName());
        csvBuilder.col(functionMetrics.getComposable());
        csvBuilder.col(functionMetrics.getSkippable());
        csvBuilder.col(functionMetrics.getRestartable());
        csvBuilder.col(functionMetrics.getReadonly());
        csvBuilder.col(functionMetrics.getInline());
        csvBuilder.col(functionMetrics.getIsLambda());
        csvBuilder.col(functionMetrics.getHasDefaults());
        csvBuilder.col(functionMetrics.getDefaultsGroup());
        csvBuilder.col(functionMetrics.getGroups());
        csvBuilder.col(functionMetrics.getCalls());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit appendModuleJson$lambda$0$0(ModuleMetricsImpl moduleMetricsImpl, JsonBuilder jsonBuilder) throws IOException {
        jsonBuilder.getClass();
        for (FeatureFlag featureFlag : FeatureFlag.getEntries()) {
            jsonBuilder.entry(featureFlag.getFeatureName(), moduleMetricsImpl.featureFlags.isEnabled(featureFlag));
        }
        return Unit.INSTANCE;
    }

    public static Unit c(ModuleMetricsImpl moduleMetricsImpl, OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.getClass();
        Iterator<String> it = moduleMetricsImpl.logMessages.iterator();
        while (it.hasNext()) {
            outputStreamWriter.append((CharSequence) it.next()).append('\n');
        }
        return Unit.INSTANCE;
    }

    public static Unit d(ModuleMetricsImpl moduleMetricsImpl, OutputStreamWriter outputStreamWriter) {
        outputStreamWriter.getClass();
        moduleMetricsImpl.appendComposablesCsv(outputStreamWriter);
        return Unit.INSTANCE;
    }

    public static Unit f(ModuleMetricsImpl moduleMetricsImpl, CsvBuilder csvBuilder) throws IOException {
        csvBuilder.getClass();
        csvBuilder.row(new Function1() { // from class: h5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.appendComposablesCsv$lambda$0$0((CsvBuilder) obj);
            }
        });
        for (final FunctionMetrics functionMetrics : moduleMetricsImpl.composables) {
            csvBuilder.row(new Function1() { // from class: i5a
                public final Object invoke(Object obj) {
                    return ModuleMetricsImpl.appendComposablesCsv$lambda$0$1(functionMetrics, (CsvBuilder) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    public static Unit h(final ModuleMetricsImpl moduleMetricsImpl, JsonBuilder jsonBuilder) throws IOException {
        jsonBuilder.getClass();
        jsonBuilder.entry("skippableComposables", moduleMetricsImpl.skippableComposables);
        jsonBuilder.entry("restartableComposables", moduleMetricsImpl.restartableComposables);
        jsonBuilder.entry("readonlyComposables", moduleMetricsImpl.readonlyComposables);
        jsonBuilder.entry("totalComposables", moduleMetricsImpl.totalComposables);
        jsonBuilder.entry("restartGroups", moduleMetricsImpl.restartGroups);
        jsonBuilder.entry("totalGroups", moduleMetricsImpl.totalGroups);
        jsonBuilder.entry("staticArguments", moduleMetricsImpl.staticArguments);
        jsonBuilder.entry("certainArguments", moduleMetricsImpl.certainArguments);
        jsonBuilder.entry("knownStableArguments", moduleMetricsImpl.knownStableArguments);
        jsonBuilder.entry("knownUnstableArguments", moduleMetricsImpl.knownUnstableArguments);
        jsonBuilder.entry("unknownStableArguments", moduleMetricsImpl.unknownStableArguments);
        jsonBuilder.entry("totalArguments", moduleMetricsImpl.totalArguments);
        jsonBuilder.entry("markedStableClasses", moduleMetricsImpl.markedStableClasses);
        jsonBuilder.entry("inferredStableClasses", moduleMetricsImpl.inferredStableClasses);
        jsonBuilder.entry("inferredUnstableClasses", moduleMetricsImpl.inferredUnstableClasses);
        jsonBuilder.entry("inferredUncertainClasses", moduleMetricsImpl.inferredUncertainClasses);
        jsonBuilder.entry("effectivelyStableClasses", moduleMetricsImpl.effectivelyStableClasses);
        jsonBuilder.entry("totalClasses", moduleMetricsImpl.totalClasses);
        jsonBuilder.entry("memoizedLambdas", moduleMetricsImpl.memoizedLambdas);
        jsonBuilder.entry("singletonLambdas", moduleMetricsImpl.singletonLambdas);
        jsonBuilder.entry("singletonComposableLambdas", moduleMetricsImpl.singletonComposableLambdas);
        jsonBuilder.entry("composableLambdas", moduleMetricsImpl.composableLambdas);
        jsonBuilder.entry("totalLambdas", moduleMetricsImpl.totalLambdas);
        jsonBuilder.entry("featureFlags", new Function1() { // from class: j5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.appendModuleJson$lambda$0$0(this.b, (JsonBuilder) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit i(ModuleMetricsImpl moduleMetricsImpl, OutputStreamWriter outputStreamWriter) {
        outputStreamWriter.getClass();
        moduleMetricsImpl.appendComposablesTxt(outputStreamWriter);
        return Unit.INSTANCE;
    }

    public static Unit j(ModuleMetricsImpl moduleMetricsImpl, OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.getClass();
        moduleMetricsImpl.appendModuleJson(outputStreamWriter);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendClassesTxt(Appendable appendable) throws IOException {
        appendable.getClass();
        IrSourcePrinterVisitor irSourcePrinterVisitor = new IrSourcePrinterVisitor(appendable, (String) null, false, 6, (DefaultConstructorMarker) null);
        Iterator<ClassMetrics> it = this.classes.iterator();
        while (it.hasNext()) {
            it.next().print(appendable, irSourcePrinterVisitor);
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendComposablesCsv(Appendable appendable) {
        appendable.getClass();
        JsonBuilderKt.appendCsv(appendable, new Function1() { // from class: g5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.f(this.b, (CsvBuilder) obj);
            }
        });
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendComposablesTxt(Appendable appendable) {
        appendable.getClass();
        IrSourcePrinterVisitor irSourcePrinterVisitor = new IrSourcePrinterVisitor(appendable, (String) null, false, 6, (DefaultConstructorMarker) null);
        Iterator<FunctionMetrics> it = this.composables.iterator();
        while (it.hasNext()) {
            it.next().print(appendable, irSourcePrinterVisitor);
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void appendModuleJson(Appendable appendable) throws IOException {
        appendable.getClass();
        JsonBuilderKt.appendJson(appendable, new Function1() { // from class: f5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.h(this.b, (JsonBuilder) obj);
            }
        });
    }

    public final FeatureFlags getFeatureFlags() {
        return this.featureFlags;
    }

    public final String getName() {
        return this.name;
    }

    public final Function2<IrType, IrFile, Stability> getStabilityOf() {
        return this.stabilityOf;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void log(String message) {
        message.getClass();
        this.logMessages.add(message);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public FunctionMetrics makeFunctionMetrics(IrFunction function) {
        function.getClass();
        return new FunctionMetricsImpl(function);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordClass(IrClass declaration, boolean marked, Stability stability) {
        declaration.getClass();
        stability.getClass();
        this.classes.add(new ClassMetrics(this, declaration, marked, stability));
        this.totalClasses++;
        if (marked) {
            this.markedStableClasses++;
            this.effectivelyStableClasses++;
        } else if (StabilityKt.knownStable(stability)) {
            this.inferredStableClasses++;
            this.effectivelyStableClasses++;
        } else if (StabilityKt.knownUnstable(stability)) {
            this.inferredUnstableClasses++;
        } else {
            this.inferredUncertainClasses++;
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordComposableCall(IrCall expression, List<ComposableFunctionBodyTransformer.CallArgumentMeta> paramMeta) {
        expression.getClass();
        paramMeta.getClass();
        for (ComposableFunctionBodyTransformer.CallArgumentMeta callArgumentMeta : paramMeta) {
            this.totalArguments++;
            if (callArgumentMeta.isCertain()) {
                this.certainArguments++;
            }
            if (callArgumentMeta.isStatic()) {
                this.staticArguments++;
            }
            if (StabilityKt.knownStable(callArgumentMeta.getStability())) {
                this.knownStableArguments++;
            } else if (StabilityKt.knownUnstable(callArgumentMeta.getStability())) {
                this.knownUnstableArguments++;
            } else {
                this.unknownStableArguments++;
            }
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordFunction(FunctionMetrics function) {
        function.getClass();
        if (function.getComposable()) {
            this.totalComposables++;
            if (!function.getIsLambda()) {
                this.composables.add(function);
            }
            if (function.getReadonly()) {
                this.readonlyComposables++;
            }
            if (function.getSkippable()) {
                this.skippableComposables++;
            }
            if (function.getRestartable()) {
                this.restartableComposables++;
                this.restartGroups++;
            }
            this.totalGroups += function.getGroups();
        }
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void recordLambda(boolean composable, boolean memoized, boolean singleton) {
        this.totalLambdas++;
        if (composable) {
            this.composableLambdas++;
        }
        if (memoized) {
            this.memoizedLambdas++;
        }
        if (composable && singleton) {
            this.singletonComposableLambdas++;
        }
        if (composable || !singleton) {
            return;
        }
        this.singletonLambdas++;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void saveMetricsTo(String directory) throws IOException {
        directory.getClass();
        JsonBuilderKt.write(new File(new File(directory), StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(this.name, '.', '_', false, 4, (Object) null), "<", "", false, 4, (Object) null), ">", "", false, 4, (Object) null) + "-module.json"), new Function1() { // from class: e5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.j(this.b, (OutputStreamWriter) obj);
            }
        });
    }

    @Override // androidx.compose.compiler.plugins.kotlin.ModuleMetrics
    public void saveReportsTo(String directory) throws IOException {
        directory.getClass();
        File file = new File(directory);
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(this.name, '.', '_', false, 4, (Object) null), "<", "", false, 4, (Object) null), ">", "", false, 4, (Object) null);
        JsonBuilderKt.write(new File(file, strReplace$default + "-composables.csv"), new Function1() { // from class: k5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.d(this.b, (OutputStreamWriter) obj);
            }
        });
        JsonBuilderKt.write(new File(file, strReplace$default + "-composables.txt"), new Function1() { // from class: l5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.i(this.b, (OutputStreamWriter) obj);
            }
        });
        if (!this.logMessages.isEmpty()) {
            JsonBuilderKt.write(new File(file, strReplace$default + "-composables.log"), new Function1() { // from class: m5a
                public final Object invoke(Object obj) {
                    return ModuleMetricsImpl.c(this.b, (OutputStreamWriter) obj);
                }
            });
        }
        JsonBuilderKt.write(new File(file, strReplace$default + "-classes.txt"), new Function1() { // from class: n5a
            public final Object invoke(Object obj) {
                return ModuleMetricsImpl.a(this.b, (OutputStreamWriter) obj);
            }
        });
    }

    public final void setName(String str) {
        str.getClass();
        this.name = str;
    }
}
