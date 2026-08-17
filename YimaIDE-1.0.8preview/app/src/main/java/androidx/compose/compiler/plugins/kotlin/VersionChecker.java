package androidx.compose.compiler.plugins.kotlin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/VersionChecker;", "", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)V", "getContext", "()Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "check", "Landroidx/compose/compiler/plugins/kotlin/VersionCheckerResult;", "skipIfRuntimeNotFound", "", "noRuntimeOnClasspathError", "", "outdatedRuntimeWithUnknownVersionNumber", "outdatedRuntime", "actualVersion", "", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class VersionChecker {
    private final IrPluginContext context;
    private final MessageCollector messageCollector;
    private static final int minimumRuntimeVersionInt = 3300;
    private static final String minimumRuntimeVersion = "1.0.0";
    private static final Map<Integer, String> runtimeVersionToMavenVersionTable = MapsKt.mapOf(new Pair[]{TuplesKt.to(1600, "0.1.0-dev16"), TuplesKt.to(1700, "1.0.0-alpha06"), TuplesKt.to(1800, "1.0.0-alpha07"), TuplesKt.to(1900, "1.0.0-alpha08"), TuplesKt.to(2000, "1.0.0-alpha09"), TuplesKt.to(2100, "1.0.0-alpha10"), TuplesKt.to(2200, "1.0.0-alpha11"), TuplesKt.to(2300, "1.0.0-alpha12"), TuplesKt.to(2400, "1.0.0-alpha13"), TuplesKt.to(2500, "1.0.0-beta04"), TuplesKt.to(2600, "1.0.0-beta05"), TuplesKt.to(2700, "1.0.0-beta06"), TuplesKt.to(2800, "1.0.0-beta07"), TuplesKt.to(2900, "1.0.0-beta08"), TuplesKt.to(3000, "1.0.0-beta09"), TuplesKt.to(3100, "1.0.0-rc01"), TuplesKt.to(3200, "1.0.0-rc02"), TuplesKt.to(Integer.valueOf(minimumRuntimeVersionInt), minimumRuntimeVersion)});

    public VersionChecker(IrPluginContext irPluginContext, MessageCollector messageCollector) {
        irPluginContext.getClass();
        messageCollector.getClass();
        this.context = irPluginContext;
        this.messageCollector = messageCollector;
    }

    public static /* synthetic */ VersionCheckerResult check$default(VersionChecker versionChecker, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return versionChecker.check(z);
    }

    private final Void noRuntimeOnClasspathError() throws IncompatibleComposeRuntimeVersionException {
        throw new IncompatibleComposeRuntimeVersionException(StringsKt.replace$default("The Compose Compiler requires the Compose Runtime to be on the class path, but\nnone could be found. The compose compiler plugin you are using expects a minimum\nruntime version of 1.0.0.", '\n', ' ', false, 4, (Object) null));
    }

    private final Void outdatedRuntime(String actualVersion) throws IncompatibleComposeRuntimeVersionException {
        throw new IncompatibleComposeRuntimeVersionException(StringsKt.replace$default(StringsKt.trimIndent("\n                You are using an outdated version of Compose Runtime that is not compatible with\n                the version of the Compose Compiler plugin you have installed. The compose\n                compiler plugin you are using expects a minimum runtime version of\n                1.0.0. The version of the runtime on the classpath currently is\n                " + actualVersion + ".\n            "), '\n', ' ', false, 4, (Object) null));
    }

    private final Void outdatedRuntimeWithUnknownVersionNumber() throws IncompatibleComposeRuntimeVersionException {
        throw new IncompatibleComposeRuntimeVersionException(StringsKt.replace$default("You are using an outdated version of Compose Runtime that is not compatible with\nthe version of the Compose Compiler plugin you have installed. The compose\ncompiler plugin you are using expects a minimum runtime version of\n1.0.0.", '\n', ' ', false, 4, (Object) null));
    }

    public final VersionCheckerResult check(boolean skipIfRuntimeNotFound) throws IncompatibleComposeRuntimeVersionException {
        Object next;
        IrField backingField;
        IrExpressionBody initializer;
        DeclarationFinder declarationFinderFinderForBuiltins = this.context.finderForBuiltins();
        ComposeClassIds composeClassIds = ComposeClassIds.INSTANCE;
        IrClassSymbol irClassSymbolFindClass = declarationFinderFinderForBuiltins.findClass(composeClassIds.getComposeVersion());
        if (irClassSymbolFindClass == null) {
            if (skipIfRuntimeNotFound) {
                MessageCollector.report$default(this.messageCollector, CompilerMessageSeverity.WARNING, StringsKt.replace$default("The Compose Compiler requires the Compose Runtime to be on the classpath, but\nnone could be found. Skipping transform because\nskipIrLoweringIfRuntimeNotFound flag was passed to the compiler.", '\n', ' ', false, 4, (Object) null), (CompilerMessageSourceLocation) null, 4, (Object) null);
                return VersionCheckerResult.NOT_FOUND;
            }
            if (this.context.finderForBuiltins().findClass(composeClassIds.getComposer()) != null) {
                outdatedRuntimeWithUnknownVersionNumber();
                wq6.a();
                return null;
            }
            noRuntimeOnClasspathError();
            wq6.a();
            return null;
        }
        if (!JvmPlatformKt.isJvm(this.context.getPlatform())) {
            return VersionCheckerResult.SUCCESS;
        }
        List<IrProperty> declarations = irClassSymbolFindClass.getOwner().getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (IrProperty irProperty : declarations) {
            IrProperty irProperty2 = irProperty instanceof IrProperty ? irProperty : null;
            if (irProperty2 != null) {
                arrayList.add(irProperty2);
            }
        }
        Iterator it = arrayList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((IrProperty) next).getName().asString(), "version"));
        IrProperty irProperty3 = (IrProperty) next;
        IrExpression expression = (irProperty3 == null || (backingField = irProperty3.getBackingField()) == null || (initializer = backingField.getInitializer()) == null) ? null : initializer.getExpression();
        IrConst irConst = expression instanceof IrConst ? (IrConst) expression : null;
        if (irConst == null || !Intrinsics.areEqual(irConst.getKind(), IrConstKind.Int.INSTANCE)) {
            outdatedRuntimeWithUnknownVersionNumber();
            wq6.a();
            return null;
        }
        Object value = irConst.getValue();
        value.getClass();
        Integer num = (Integer) value;
        if (num.intValue() >= minimumRuntimeVersionInt) {
            return VersionCheckerResult.SUCCESS;
        }
        String str = runtimeVersionToMavenVersionTable.get(num);
        if (str == null) {
            str = "<unknown>";
        }
        outdatedRuntime(str);
        wq6.a();
        return null;
    }

    public final IrPluginContext getContext() {
        return this.context;
    }
}
