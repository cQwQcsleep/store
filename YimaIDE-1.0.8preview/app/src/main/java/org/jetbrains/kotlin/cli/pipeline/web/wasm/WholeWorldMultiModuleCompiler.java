package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.common.serialization.KotlinIrLinkerKt;
import org.jetbrains.kotlin.backend.wasm.LoweredIrWithExtraArtifacts;
import org.jetbrains.kotlin.backend.wasm.WasmIrModuleConfiguration;
import org.jetbrains.kotlin.backend.wasm.dce.DceKt;
import org.jetbrains.kotlin.backend.wasm.ic.IrFactoryImplForWasmIC;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.dce.DceDumpNameCache;
import org.jetbrains.kotlin.ir.backend.js.dce.UtilsKt;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.KotlinLibraryKt;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WholeWorldMultiModuleCompiler;", "Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WholeWorldCompilerBase;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "irFactory", "Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;)V", "getIrFactory", "()Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;", "compileIr", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/wasm/WasmIrModuleConfiguration;", "loweredIr", "Lorg/jetbrains/kotlin/backend/wasm/LoweredIrWithExtraArtifacts;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WholeWorldMultiModuleCompiler extends WholeWorldCompilerBase {
    private final IrFactoryImplForWasmIC irFactory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WholeWorldMultiModuleCompiler(CompilerConfiguration compilerConfiguration, IrFactoryImplForWasmIC irFactoryImplForWasmIC) {
        super(compilerConfiguration, true);
        compilerConfiguration.getClass();
        irFactoryImplForWasmIC.getClass();
        this.irFactory = irFactoryImplForWasmIC;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0061  */
    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public List<WasmIrModuleConfiguration> compileIr(LoweredIrWithExtraArtifacts loweredIr) {
        boolean z;
        loweredIr.getClass();
        List loweredIr2 = loweredIr.getLoweredIr();
        DceDumpNameCache dceDumpNameCache = new DceDumpNameCache();
        if (JSConfigurationKeysKt.getDce(getConfiguration())) {
            DceKt.eliminateDeadDeclarations(loweredIr.getLoweredIr(), loweredIr.getBackendContext(), dceDumpNameCache);
        }
        UtilsKt.dumpDeclarationIrSizesIfNeed(WasmConfigurationKeysKt.getDceDumpDeclarationIrSizesToFile(getConfiguration()), loweredIr2, dceDumpNameCache);
        List<IrModuleFragment> list = loweredIr2;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (IrModuleFragment irModuleFragment : list) {
            CompilerConfiguration configuration = getConfiguration();
            IrFactoryImplForWasmIC irFactory = getIrFactory();
            KotlinLibrary kotlinLibrary = KotlinIrLinkerKt.getKotlinLibrary(irModuleFragment);
            if (kotlinLibrary != null) {
                z = true;
                if (!KotlinLibraryKt.isWasmStdlib(kotlinLibrary)) {
                    z = false;
                }
            } else {
                z = false;
            }
            LoweredIrWithExtraArtifacts loweredIrWithExtraArtifacts = loweredIr;
            arrayList.add(KotlinIr2WasmIrCompilerKt.access$compileSingleModuleToWasmIr(configuration, loweredIrWithExtraArtifacts, irFactory, z, irModuleFragment, true));
            loweredIr = loweredIrWithExtraArtifacts;
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public IrFactoryImplForWasmIC getIrFactory() {
        return this.irFactory;
    }
}
