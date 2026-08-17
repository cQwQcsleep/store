package org.jetbrains.kotlin.wasm.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/wasm/config/WasmConfigurationKeys;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "WASM_ENABLE_ARRAY_RANGE_CHECKS", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "", "Lkotlin/jvm/JvmField;", "WASM_ENABLE_ASSERTS", "WASM_GENERATE_WAT", "WASM_TARGET", "Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS", "WASM_USE_NEW_EXCEPTION_PROPOSAL", "WASM_NO_JS_TAG", "WASM_DEBUG", "DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE", "", "WASM_GENERATE_DWARF", "WASM_FORCE_DEBUG_FRIENDLY_COMPILATION", "WASM_INCLUDED_MODULE_ONLY", "WASM_DEPENDENCY_RESOLUTION_MAP", "WASM_COMMAND_MODULE", "WASM_DISABLE_CROSS_FILE_OPTIMISATIONS", "WASM_INTERNAL_LOCAL_VARIABLE_PREFIX", "WASM_GENERATE_CLOSED_WORLD_MULTIMODULE", "org.jetbrains.kotlin:wasm.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmConfigurationKeys {
    public static final CompilerConfigurationKey<String> DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE;
    public static final WasmConfigurationKeys INSTANCE = new WasmConfigurationKeys();
    public static final CompilerConfigurationKey<Boolean> WASM_COMMAND_MODULE;
    public static final CompilerConfigurationKey<Boolean> WASM_DEBUG;
    public static final CompilerConfigurationKey<String> WASM_DEPENDENCY_RESOLUTION_MAP;
    public static final CompilerConfigurationKey<Boolean> WASM_DISABLE_CROSS_FILE_OPTIMISATIONS;
    public static final CompilerConfigurationKey<Boolean> WASM_ENABLE_ARRAY_RANGE_CHECKS;
    public static final CompilerConfigurationKey<Boolean> WASM_ENABLE_ASSERTS;
    public static final CompilerConfigurationKey<Boolean> WASM_FORCE_DEBUG_FRIENDLY_COMPILATION;
    public static final CompilerConfigurationKey<Boolean> WASM_GENERATE_CLOSED_WORLD_MULTIMODULE;
    public static final CompilerConfigurationKey<Boolean> WASM_GENERATE_DWARF;
    public static final CompilerConfigurationKey<Boolean> WASM_GENERATE_WAT;
    public static final CompilerConfigurationKey<Boolean> WASM_INCLUDED_MODULE_ONLY;
    public static final CompilerConfigurationKey<String> WASM_INTERNAL_LOCAL_VARIABLE_PREFIX;
    public static final CompilerConfigurationKey<Boolean> WASM_NO_JS_TAG;
    public static final CompilerConfigurationKey<WasmTarget> WASM_TARGET;
    public static final CompilerConfigurationKey<Boolean> WASM_USE_NEW_EXCEPTION_PROPOSAL;
    public static final CompilerConfigurationKey<Boolean> WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.Companion;
        WASM_ENABLE_ARRAY_RANGE_CHECKS = companion.create("WASM_ENABLE_ARRAY_RANGE_CHECKS");
        WASM_ENABLE_ASSERTS = companion.create("WASM_ENABLE_ASSERTS");
        WASM_GENERATE_WAT = companion.create("WASM_GENERATE_WAT");
        WASM_TARGET = companion.create("WASM_TARGET");
        WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS = companion.create("WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS");
        WASM_USE_NEW_EXCEPTION_PROPOSAL = companion.create("WASM_USE_NEW_EXCEPTION_PROPOSAL");
        WASM_NO_JS_TAG = companion.create("WASM_NO_JS_TAG");
        WASM_DEBUG = companion.create("WASM_DEBUG");
        DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE = companion.create("DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE");
        WASM_GENERATE_DWARF = companion.create("WASM_GENERATE_DWARF");
        WASM_FORCE_DEBUG_FRIENDLY_COMPILATION = companion.create("WASM_FORCE_DEBUG_FRIENDLY_COMPILATION");
        WASM_INCLUDED_MODULE_ONLY = companion.create("WASM_INCLUDED_MODULE_ONLY");
        WASM_DEPENDENCY_RESOLUTION_MAP = companion.create("WASM_DEPENDENCY_RESOLUTION_MAP");
        WASM_COMMAND_MODULE = companion.create("WASM_COMMAND_MODULE");
        WASM_DISABLE_CROSS_FILE_OPTIMISATIONS = companion.create("WASM_DISABLE_CROSS_FILE_OPTIMISATIONS");
        WASM_INTERNAL_LOCAL_VARIABLE_PREFIX = companion.create("WASM_INTERNAL_LOCAL_VARIABLE_PREFIX");
        WASM_GENERATE_CLOSED_WORLD_MULTIMODULE = companion.create("WASM_GENERATE_CLOSED_WORLD_MULTIMODULE");
    }

    private WasmConfigurationKeys() {
    }
}
