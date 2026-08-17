package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.jvm.JvmIrTypeSystemContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.IrBuiltIns;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class JvmCompilerPipelineKt$convertToIrAndActualizeForJvm$1 extends FunctionReferenceImpl implements Function1<IrBuiltIns, JvmIrTypeSystemContext> {
    public static final JvmCompilerPipelineKt$convertToIrAndActualizeForJvm$1 INSTANCE = new JvmCompilerPipelineKt$convertToIrAndActualizeForJvm$1();

    public JvmCompilerPipelineKt$convertToIrAndActualizeForJvm$1() {
        super(1, JvmIrTypeSystemContext.class, "<init>", "<init>(Lorg/jetbrains/kotlin/ir/IrBuiltIns;)V", 0);
    }

    public final JvmIrTypeSystemContext invoke(IrBuiltIns irBuiltIns) {
        irBuiltIns.getClass();
        return new JvmIrTypeSystemContext(irBuiltIns);
    }
}
