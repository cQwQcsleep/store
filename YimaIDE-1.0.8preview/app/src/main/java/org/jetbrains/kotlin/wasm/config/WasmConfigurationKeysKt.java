package org.jetbrains.kotlin.wasm.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u001e\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007\"(\u0010\u000b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007\"(\u0010\u000f\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"(\u0010\u0014\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0005\"\u0004\b\u0016\u0010\u0007\"(\u0010\u0017\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007\"(\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0005\"\u0004\b\u001c\u0010\u0007\"(\u0010\u001d\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0005\"\u0004\b\u001f\u0010\u0007\",\u0010!\u001a\u0004\u0018\u00010 *\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010 8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\"(\u0010&\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0005\"\u0004\b(\u0010\u0007\"(\u0010)\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010\u0005\"\u0004\b+\u0010\u0007\"(\u0010,\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0005\"\u0004\b.\u0010\u0007\",\u0010/\u001a\u0004\u0018\u00010 *\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010 8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u0010#\"\u0004\b1\u0010%\"(\u00102\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b3\u0010\u0005\"\u0004\b4\u0010\u0007\"(\u00105\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b6\u0010\u0005\"\u0004\b7\u0010\u0007\",\u00108\u001a\u0004\u0018\u00010 *\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010 8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b9\u0010#\"\u0004\b:\u0010%\"(\u0010;\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b<\u0010\u0005\"\u0004\b=\u0010\u0007¨\u0006>"}, d2 = {"value", "", "wasmEnableArrayRangeChecks", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getWasmEnableArrayRangeChecks", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Z", "setWasmEnableArrayRangeChecks", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", "wasmEnableAsserts", "getWasmEnableAsserts", "setWasmEnableAsserts", "wasmGenerateWat", "getWasmGenerateWat", "setWasmGenerateWat", "Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "wasmTarget", "getWasmTarget", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "setWasmTarget", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;)V", "wasmUseTrapsInsteadOfExceptions", "getWasmUseTrapsInsteadOfExceptions", "setWasmUseTrapsInsteadOfExceptions", "wasmUseNewExceptionProposal", "getWasmUseNewExceptionProposal", "setWasmUseNewExceptionProposal", "wasmNoJsTag", "getWasmNoJsTag", "setWasmNoJsTag", "wasmDebug", "getWasmDebug", "setWasmDebug", "", "dceDumpDeclarationIrSizesToFile", "getDceDumpDeclarationIrSizesToFile", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/String;", "setDceDumpDeclarationIrSizesToFile", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)V", "wasmGenerateDwarf", "getWasmGenerateDwarf", "setWasmGenerateDwarf", "wasmForceDebugFriendlyCompilation", "getWasmForceDebugFriendlyCompilation", "setWasmForceDebugFriendlyCompilation", "wasmIncludedModuleOnly", "getWasmIncludedModuleOnly", "setWasmIncludedModuleOnly", "wasmDependencyResolutionMap", "getWasmDependencyResolutionMap", "setWasmDependencyResolutionMap", "wasmCommandModule", "getWasmCommandModule", "setWasmCommandModule", "wasmDisableCrossFileOptimisations", "getWasmDisableCrossFileOptimisations", "setWasmDisableCrossFileOptimisations", "wasmInternalLocalVariablePrefix", "getWasmInternalLocalVariablePrefix", "setWasmInternalLocalVariablePrefix", "wasmGenerateClosedWorldMultimodule", "getWasmGenerateClosedWorldMultimodule", "setWasmGenerateClosedWorldMultimodule", "org.jetbrains.kotlin:wasm.frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class WasmConfigurationKeysKt {
    public static final String getDceDumpDeclarationIrSizesToFile(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(WasmConfigurationKeys.DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE);
    }

    public static final boolean getWasmCommandModule(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_COMMAND_MODULE);
    }

    public static final boolean getWasmDebug(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_DEBUG);
    }

    public static final String getWasmDependencyResolutionMap(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(WasmConfigurationKeys.WASM_DEPENDENCY_RESOLUTION_MAP);
    }

    public static final boolean getWasmDisableCrossFileOptimisations(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_DISABLE_CROSS_FILE_OPTIMISATIONS);
    }

    public static final boolean getWasmEnableArrayRangeChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_ENABLE_ARRAY_RANGE_CHECKS);
    }

    public static final boolean getWasmEnableAsserts(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_ENABLE_ASSERTS);
    }

    public static final boolean getWasmForceDebugFriendlyCompilation(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_FORCE_DEBUG_FRIENDLY_COMPILATION);
    }

    public static final boolean getWasmGenerateClosedWorldMultimodule(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_GENERATE_CLOSED_WORLD_MULTIMODULE);
    }

    public static final boolean getWasmGenerateDwarf(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_GENERATE_DWARF);
    }

    public static final boolean getWasmGenerateWat(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_GENERATE_WAT);
    }

    public static final boolean getWasmIncludedModuleOnly(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_INCLUDED_MODULE_ONLY);
    }

    public static final String getWasmInternalLocalVariablePrefix(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(WasmConfigurationKeys.WASM_INTERNAL_LOCAL_VARIABLE_PREFIX);
    }

    public static final boolean getWasmNoJsTag(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_NO_JS_TAG);
    }

    public static final WasmTarget getWasmTarget(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (WasmTarget) compilerConfiguration.get(WasmConfigurationKeys.WASM_TARGET, WasmTarget.JS);
    }

    public static final boolean getWasmUseNewExceptionProposal(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_USE_NEW_EXCEPTION_PROPOSAL);
    }

    public static final boolean getWasmUseTrapsInsteadOfExceptions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(WasmConfigurationKeys.WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS);
    }

    public static final void setDceDumpDeclarationIrSizesToFile(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<String> compilerConfigurationKey = WasmConfigurationKeys.DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setWasmCommandModule(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_COMMAND_MODULE, Boolean.valueOf(z));
    }

    public static final void setWasmDebug(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_DEBUG, Boolean.valueOf(z));
    }

    public static final void setWasmDependencyResolutionMap(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<String> compilerConfigurationKey = WasmConfigurationKeys.WASM_DEPENDENCY_RESOLUTION_MAP;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setWasmDisableCrossFileOptimisations(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_DISABLE_CROSS_FILE_OPTIMISATIONS, Boolean.valueOf(z));
    }

    public static final void setWasmEnableArrayRangeChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_ENABLE_ARRAY_RANGE_CHECKS, Boolean.valueOf(z));
    }

    public static final void setWasmEnableAsserts(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_ENABLE_ASSERTS, Boolean.valueOf(z));
    }

    public static final void setWasmForceDebugFriendlyCompilation(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_FORCE_DEBUG_FRIENDLY_COMPILATION, Boolean.valueOf(z));
    }

    public static final void setWasmGenerateClosedWorldMultimodule(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_GENERATE_CLOSED_WORLD_MULTIMODULE, Boolean.valueOf(z));
    }

    public static final void setWasmGenerateDwarf(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_GENERATE_DWARF, Boolean.valueOf(z));
    }

    public static final void setWasmGenerateWat(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_GENERATE_WAT, Boolean.valueOf(z));
    }

    public static final void setWasmIncludedModuleOnly(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_INCLUDED_MODULE_ONLY, Boolean.valueOf(z));
    }

    public static final void setWasmInternalLocalVariablePrefix(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<String> compilerConfigurationKey = WasmConfigurationKeys.WASM_INTERNAL_LOCAL_VARIABLE_PREFIX;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setWasmNoJsTag(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_NO_JS_TAG, Boolean.valueOf(z));
    }

    public static final void setWasmTarget(CompilerConfiguration compilerConfiguration, WasmTarget wasmTarget) {
        compilerConfiguration.getClass();
        wasmTarget.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_TARGET, wasmTarget);
    }

    public static final void setWasmUseNewExceptionProposal(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_USE_NEW_EXCEPTION_PROPOSAL, Boolean.valueOf(z));
    }

    public static final void setWasmUseTrapsInsteadOfExceptions(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(WasmConfigurationKeys.WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS, Boolean.valueOf(z));
    }
}
