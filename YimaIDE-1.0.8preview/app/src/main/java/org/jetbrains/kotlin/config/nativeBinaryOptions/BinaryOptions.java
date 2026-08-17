package org.jetbrains.kotlin.config.nativeBinaryOptions;

import java.util.List;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.PropertyDelegateProvider;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions;
import org.jetbrains.kotlin.config.nativeBinaryOptions.GC;
import org.jetbrains.kotlin.config.nativeBinaryOptions.GCSchedulerType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\n\u001a\u0004\b\r\u0010\bR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0011\u0010\bR!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0015\u0010\bR!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0018\u0010\bR!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001c\u0010\bR!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\n\u001a\u0004\b \u0010\bR!\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\n\u001a\u0004\b$\u0010\bR!\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\n\u001a\u0004\b(\u0010\bR!\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b,\u0010\bR!\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\n\u001a\u0004\b/\u0010\bR!\u00101\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\n\u001a\u0004\b2\u0010\bR!\u00104\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\n\u001a\u0004\b5\u0010\bR!\u00107\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\n\u001a\u0004\b8\u0010\bR!\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\n\u001a\u0004\b<\u0010\bR!\u0010>\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\n\u001a\u0004\b?\u0010\bR!\u0010A\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010\n\u001a\u0004\bB\u0010\bR!\u0010D\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bF\u0010\n\u001a\u0004\bE\u0010\bR!\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\n\u001a\u0004\bI\u0010\bR!\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\n\u001a\u0004\bM\u0010\bR!\u0010O\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\n\u001a\u0004\bP\u0010\bR!\u0010R\u001a\b\u0012\u0004\u0012\u00020S0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bU\u0010\n\u001a\u0004\bT\u0010\bR!\u0010V\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bX\u0010\n\u001a\u0004\bW\u0010\bR!\u0010Y\u001a\b\u0012\u0004\u0012\u00020S0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b[\u0010\n\u001a\u0004\bZ\u0010\bR!\u0010\\\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b^\u0010\n\u001a\u0004\b]\u0010\bR!\u0010_\u001a\b\u0012\u0004\u0012\u00020S0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\ba\u0010\n\u001a\u0004\b`\u0010\bR!\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\be\u0010\n\u001a\u0004\bd\u0010\bR!\u0010f\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bh\u0010\n\u001a\u0004\bg\u0010\bR!\u0010i\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bk\u0010\n\u001a\u0004\bj\u0010\bR!\u0010l\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bn\u0010\n\u001a\u0004\bm\u0010\bR!\u0010o\u001a\b\u0012\u0004\u0012\u00020p0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\br\u0010\n\u001a\u0004\bq\u0010\bR!\u0010s\u001a\b\u0012\u0004\u0012\u00020t0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bv\u0010\n\u001a\u0004\bu\u0010\bR!\u0010w\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\by\u0010\n\u001a\u0004\bx\u0010\bR!\u0010z\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b|\u0010\n\u001a\u0004\b{\u0010\bR!\u0010}\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u007f\u0010\n\u001a\u0004\b~\u0010\bR$\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0082\u0001\u0010\n\u001a\u0005\b\u0081\u0001\u0010\bR$\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00020S0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0085\u0001\u0010\n\u001a\u0005\b\u0084\u0001\u0010\bR$\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0088\u0001\u0010\n\u001a\u0005\b\u0087\u0001\u0010\bR+\u0010\u0089\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020;0\u008a\u00010\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008c\u0001\u0010\n\u001a\u0005\b\u008b\u0001\u0010\bR$\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008f\u0001\u0010\n\u001a\u0005\b\u008e\u0001\u0010\bR%\u0010\u0090\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0093\u0001\u0010\n\u001a\u0005\b\u0092\u0001\u0010\bR$\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0096\u0001\u0010\n\u001a\u0005\b\u0095\u0001\u0010\bR$\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0099\u0001\u0010\n\u001a\u0005\b\u0098\u0001\u0010\bR$\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u009c\u0001\u0010\n\u001a\u0005\b\u009b\u0001\u0010\bR$\u0010\u009d\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u009f\u0001\u0010\n\u001a\u0005\b\u009e\u0001\u0010\bR$\u0010 \u0001\u001a\b\u0012\u0004\u0012\u00020S0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¢\u0001\u0010\n\u001a\u0005\b¡\u0001\u0010\bR$\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¥\u0001\u0010\n\u001a\u0005\b¤\u0001\u0010\bR$\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¨\u0001\u0010\n\u001a\u0005\b§\u0001\u0010\bR$\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b«\u0001\u0010\n\u001a\u0005\bª\u0001\u0010\bR$\u0010¬\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b®\u0001\u0010\n\u001a\u0005\b\u00ad\u0001\u0010\bR%\u0010¯\u0001\u001a\t\u0012\u0005\u0012\u00030°\u00010\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b²\u0001\u0010\n\u001a\u0005\b±\u0001\u0010\bR$\u0010³\u0001\u001a\b\u0012\u0004\u0012\u00020;0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bµ\u0001\u0010\n\u001a\u0005\b´\u0001\u0010\bR$\u0010¶\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¸\u0001\u0010\n\u001a\u0005\b·\u0001\u0010\bR%\u0010¹\u0001\u001a\t\u0012\u0005\u0012\u00030º\u00010\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¼\u0001\u0010\n\u001a\u0005\b»\u0001\u0010\bR$\u0010½\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¿\u0001\u0010\n\u001a\u0005\b¾\u0001\u0010\b¨\u0006À\u0001"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOptions;", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOptionRegistry;", "<init>", "()V", "runtimeAssertionsMode", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/RuntimeAssertsMode;", "getRuntimeAssertionsMode", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "runtimeAssertionsMode$delegate", "Lkotlin/properties/ReadOnlyProperty;", "checkStateAtExternalCalls", Argument.Delimiters.none, "getCheckStateAtExternalCalls", "checkStateAtExternalCalls$delegate", "memoryModel", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/MemoryModel;", "getMemoryModel", "memoryModel$delegate", "freezing", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/Freezing;", "getFreezing", "freezing$delegate", "stripDebugInfoFromNativeLibs", "getStripDebugInfoFromNativeLibs", "stripDebugInfoFromNativeLibs$delegate", "sourceInfoType", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/SourceInfoType;", "getSourceInfoType", "sourceInfoType$delegate", "coreSymbolicationImageListType", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/CoreSymbolicationImageListType;", "getCoreSymbolicationImageListType", "coreSymbolicationImageListType$delegate", "androidProgramType", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/AndroidProgramType;", "getAndroidProgramType", "androidProgramType$delegate", "unitSuspendFunctionObjCExport", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/UnitSuspendFunctionObjCExport;", "getUnitSuspendFunctionObjCExport", "unitSuspendFunctionObjCExport$delegate", "objcExportSuspendFunctionLaunchThreadRestriction", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/ObjCExportSuspendFunctionLaunchThreadRestriction;", "getObjcExportSuspendFunctionLaunchThreadRestriction", "objcExportSuspendFunctionLaunchThreadRestriction$delegate", "objcExportDisableSwiftMemberNameMangling", "getObjcExportDisableSwiftMemberNameMangling", "objcExportDisableSwiftMemberNameMangling$delegate", "objcExportIgnoreInterfaceMethodCollisions", "getObjcExportIgnoreInterfaceMethodCollisions", "objcExportIgnoreInterfaceMethodCollisions$delegate", "objcExportReportNameCollisions", "getObjcExportReportNameCollisions", "objcExportReportNameCollisions$delegate", "objcExportErrorOnNameCollisions", "getObjcExportErrorOnNameCollisions", "objcExportErrorOnNameCollisions$delegate", "objcExportEntryPointsPath", Argument.Delimiters.none, "getObjcExportEntryPointsPath", "objcExportEntryPointsPath$delegate", "objcExportExplicitMethodFamily", "getObjcExportExplicitMethodFamily", "objcExportExplicitMethodFamily$delegate", "objcExportBlockExplicitParameterNames", "getObjcExportBlockExplicitParameterNames", "objcExportBlockExplicitParameterNames$delegate", "dumpObjcSelectorToSignatureMapping", "getDumpObjcSelectorToSignatureMapping", "dumpObjcSelectorToSignatureMapping$delegate", "gc", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/GC;", "getGc", "gc$delegate", "gcSchedulerType", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/GCSchedulerType;", "getGcSchedulerType", "gcSchedulerType$delegate", "gcMarkSingleThreaded", "getGcMarkSingleThreaded", "gcMarkSingleThreaded$delegate", "fixedBlockPageSize", "Lkotlin/UInt;", "getFixedBlockPageSize", "fixedBlockPageSize$delegate", "concurrentWeakSweep", "getConcurrentWeakSweep", "concurrentWeakSweep$delegate", "concurrentMarkMaxIterations", "getConcurrentMarkMaxIterations", "concurrentMarkMaxIterations$delegate", "gcMutatorsCooperate", "getGcMutatorsCooperate", "gcMutatorsCooperate$delegate", "auxGCThreads", "getAuxGCThreads", "auxGCThreads$delegate", "linkRuntime", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/RuntimeLinkageStrategy;", "getLinkRuntime", "linkRuntime$delegate", "bundleId", "getBundleId", "bundleId$delegate", "bundleShortVersionString", "getBundleShortVersionString", "bundleShortVersionString$delegate", "bundleVersion", "getBundleVersion", "bundleVersion$delegate", "appStateTracking", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/AppStateTracking;", "getAppStateTracking", "appStateTracking$delegate", "sanitizer", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/SanitizerKind;", "getSanitizer", "sanitizer$delegate", "compileBitcodeWithXcodeLlvm", "getCompileBitcodeWithXcodeLlvm", "compileBitcodeWithXcodeLlvm$delegate", "objcDisposeOnMain", "getObjcDisposeOnMain", "objcDisposeOnMain$delegate", "objcDisposeWithRunLoop", "getObjcDisposeWithRunLoop", "objcDisposeWithRunLoop$delegate", "disableMmap", "getDisableMmap", "disableMmap$delegate", "mmapTag", "getMmapTag", "mmapTag$delegate", "enableSafepointSignposts", "getEnableSafepointSignposts", "enableSafepointSignposts$delegate", "forceNativeThreadStateForFunctions", Argument.Delimiters.none, "getForceNativeThreadStateForFunctions", "forceNativeThreadStateForFunctions$delegate", "packFields", "getPackFields", "packFields$delegate", "cInterfaceMode", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/CInterfaceGenerationMode;", "getCInterfaceMode", "cInterfaceMode$delegate", "globalDataLazyInit", "getGlobalDataLazyInit", "globalDataLazyInit$delegate", "swiftExport", "getSwiftExport", "swiftExport$delegate", "genericSafeCasts", "getGenericSafeCasts", "genericSafeCasts$delegate", "smallBinary", "getSmallBinary", "smallBinary$delegate", "preCodegenInlineThreshold", "getPreCodegenInlineThreshold", "preCodegenInlineThreshold$delegate", "enableDebugTransparentStepping", "getEnableDebugTransparentStepping", "enableDebugTransparentStepping$delegate", "debugCompilationDir", "getDebugCompilationDir", "debugCompilationDir$delegate", "pagedAllocator", "getPagedAllocator", "pagedAllocator$delegate", "latin1Strings", "getLatin1Strings", "latin1Strings$delegate", "stackProtector", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/StackProtectorMode;", "getStackProtector", "stackProtector$delegate", "minidumpLocation", "getMinidumpLocation", "minidumpLocation$delegate", "minidumpOnSIGTERM", "getMinidumpOnSIGTERM", "minidumpOnSIGTERM$delegate", "cCallMode", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/CCallMode;", "getCCallMode", "cCallMode$delegate", "macabi", "getMacabi", "macabi$delegate", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BinaryOptions extends BinaryOptionRegistry {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public static final BinaryOptions INSTANCE;

    /* JADX INFO: renamed from: androidProgramType$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty androidProgramType;

    /* JADX INFO: renamed from: appStateTracking$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty appStateTracking;

    /* JADX INFO: renamed from: auxGCThreads$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty auxGCThreads;

    /* JADX INFO: renamed from: bundleId$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty bundleId;

    /* JADX INFO: renamed from: bundleShortVersionString$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty bundleShortVersionString;

    /* JADX INFO: renamed from: bundleVersion$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty bundleVersion;

    /* JADX INFO: renamed from: cCallMode$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty cCallMode;

    /* JADX INFO: renamed from: cInterfaceMode$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty cInterfaceMode;

    /* JADX INFO: renamed from: checkStateAtExternalCalls$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty checkStateAtExternalCalls;

    /* JADX INFO: renamed from: compileBitcodeWithXcodeLlvm$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty compileBitcodeWithXcodeLlvm;

    /* JADX INFO: renamed from: concurrentMarkMaxIterations$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty concurrentMarkMaxIterations;

    /* JADX INFO: renamed from: concurrentWeakSweep$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty concurrentWeakSweep;

    /* JADX INFO: renamed from: coreSymbolicationImageListType$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty coreSymbolicationImageListType;

    /* JADX INFO: renamed from: debugCompilationDir$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty debugCompilationDir;

    /* JADX INFO: renamed from: disableMmap$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty disableMmap;

    /* JADX INFO: renamed from: dumpObjcSelectorToSignatureMapping$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty dumpObjcSelectorToSignatureMapping;

    /* JADX INFO: renamed from: enableDebugTransparentStepping$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty enableDebugTransparentStepping;

    /* JADX INFO: renamed from: enableSafepointSignposts$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty enableSafepointSignposts;

    /* JADX INFO: renamed from: fixedBlockPageSize$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty fixedBlockPageSize;

    /* JADX INFO: renamed from: forceNativeThreadStateForFunctions$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty forceNativeThreadStateForFunctions;

    /* JADX INFO: renamed from: freezing$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty freezing;

    /* JADX INFO: renamed from: gc$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty gc;

    /* JADX INFO: renamed from: gcMarkSingleThreaded$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty gcMarkSingleThreaded;

    /* JADX INFO: renamed from: gcMutatorsCooperate$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty gcMutatorsCooperate;

    /* JADX INFO: renamed from: gcSchedulerType$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty gcSchedulerType;

    /* JADX INFO: renamed from: genericSafeCasts$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty genericSafeCasts;

    /* JADX INFO: renamed from: globalDataLazyInit$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty globalDataLazyInit;

    /* JADX INFO: renamed from: latin1Strings$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty latin1Strings;

    /* JADX INFO: renamed from: linkRuntime$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty linkRuntime;

    /* JADX INFO: renamed from: macabi$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty macabi;

    /* JADX INFO: renamed from: memoryModel$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty memoryModel;

    /* JADX INFO: renamed from: minidumpLocation$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty minidumpLocation;

    /* JADX INFO: renamed from: minidumpOnSIGTERM$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty minidumpOnSIGTERM;

    /* JADX INFO: renamed from: mmapTag$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty mmapTag;

    /* JADX INFO: renamed from: objcDisposeOnMain$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcDisposeOnMain;

    /* JADX INFO: renamed from: objcDisposeWithRunLoop$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcDisposeWithRunLoop;

    /* JADX INFO: renamed from: objcExportBlockExplicitParameterNames$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportBlockExplicitParameterNames;

    /* JADX INFO: renamed from: objcExportDisableSwiftMemberNameMangling$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportDisableSwiftMemberNameMangling;

    /* JADX INFO: renamed from: objcExportEntryPointsPath$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportEntryPointsPath;

    /* JADX INFO: renamed from: objcExportErrorOnNameCollisions$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportErrorOnNameCollisions;

    /* JADX INFO: renamed from: objcExportExplicitMethodFamily$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportExplicitMethodFamily;

    /* JADX INFO: renamed from: objcExportIgnoreInterfaceMethodCollisions$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportIgnoreInterfaceMethodCollisions;

    /* JADX INFO: renamed from: objcExportReportNameCollisions$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportReportNameCollisions;

    /* JADX INFO: renamed from: objcExportSuspendFunctionLaunchThreadRestriction$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty objcExportSuspendFunctionLaunchThreadRestriction;

    /* JADX INFO: renamed from: packFields$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty packFields;

    /* JADX INFO: renamed from: pagedAllocator$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty pagedAllocator;

    /* JADX INFO: renamed from: preCodegenInlineThreshold$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty preCodegenInlineThreshold;

    /* JADX INFO: renamed from: runtimeAssertionsMode$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty runtimeAssertionsMode;

    /* JADX INFO: renamed from: sanitizer$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty sanitizer;

    /* JADX INFO: renamed from: smallBinary$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty smallBinary;

    /* JADX INFO: renamed from: sourceInfoType$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty sourceInfoType;

    /* JADX INFO: renamed from: stackProtector$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty stackProtector;

    /* JADX INFO: renamed from: stripDebugInfoFromNativeLibs$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty stripDebugInfoFromNativeLibs;

    /* JADX INFO: renamed from: swiftExport$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty swiftExport;

    /* JADX INFO: renamed from: unitSuspendFunctionObjCExport$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty unitSuspendFunctionObjCExport;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(BinaryOptions.class, "runtimeAssertionsMode", "getRuntimeAssertionsMode()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "checkStateAtExternalCalls", "getCheckStateAtExternalCalls()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "memoryModel", "getMemoryModel()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "freezing", "getFreezing()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "stripDebugInfoFromNativeLibs", "getStripDebugInfoFromNativeLibs()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "sourceInfoType", "getSourceInfoType()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "coreSymbolicationImageListType", "getCoreSymbolicationImageListType()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "androidProgramType", "getAndroidProgramType()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "unitSuspendFunctionObjCExport", "getUnitSuspendFunctionObjCExport()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportSuspendFunctionLaunchThreadRestriction", "getObjcExportSuspendFunctionLaunchThreadRestriction()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportDisableSwiftMemberNameMangling", "getObjcExportDisableSwiftMemberNameMangling()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportIgnoreInterfaceMethodCollisions", "getObjcExportIgnoreInterfaceMethodCollisions()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportReportNameCollisions", "getObjcExportReportNameCollisions()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportErrorOnNameCollisions", "getObjcExportErrorOnNameCollisions()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportEntryPointsPath", "getObjcExportEntryPointsPath()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportExplicitMethodFamily", "getObjcExportExplicitMethodFamily()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcExportBlockExplicitParameterNames", "getObjcExportBlockExplicitParameterNames()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "dumpObjcSelectorToSignatureMapping", "getDumpObjcSelectorToSignatureMapping()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "gc", "getGc()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "gcSchedulerType", "getGcSchedulerType()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "gcMarkSingleThreaded", "getGcMarkSingleThreaded()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "fixedBlockPageSize", "getFixedBlockPageSize()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "concurrentWeakSweep", "getConcurrentWeakSweep()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "concurrentMarkMaxIterations", "getConcurrentMarkMaxIterations()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "gcMutatorsCooperate", "getGcMutatorsCooperate()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "auxGCThreads", "getAuxGCThreads()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "linkRuntime", "getLinkRuntime()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "bundleId", "getBundleId()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "bundleShortVersionString", "getBundleShortVersionString()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "bundleVersion", "getBundleVersion()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "appStateTracking", "getAppStateTracking()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "sanitizer", "getSanitizer()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "compileBitcodeWithXcodeLlvm", "getCompileBitcodeWithXcodeLlvm()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcDisposeOnMain", "getObjcDisposeOnMain()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "objcDisposeWithRunLoop", "getObjcDisposeWithRunLoop()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "disableMmap", "getDisableMmap()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "mmapTag", "getMmapTag()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "enableSafepointSignposts", "getEnableSafepointSignposts()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "forceNativeThreadStateForFunctions", "getForceNativeThreadStateForFunctions()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "packFields", "getPackFields()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "cInterfaceMode", "getCInterfaceMode()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "globalDataLazyInit", "getGlobalDataLazyInit()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "swiftExport", "getSwiftExport()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "genericSafeCasts", "getGenericSafeCasts()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "smallBinary", "getSmallBinary()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "preCodegenInlineThreshold", "getPreCodegenInlineThreshold()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "enableDebugTransparentStepping", "getEnableDebugTransparentStepping()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "debugCompilationDir", "getDebugCompilationDir()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "pagedAllocator", "getPagedAllocator()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "latin1Strings", "getLatin1Strings()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "stackProtector", "getStackProtector()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "minidumpLocation", "getMinidumpLocation()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "minidumpOnSIGTERM", "getMinidumpOnSIGTERM()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "cCallMode", "getCCallMode()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0), new PropertyReference1Impl<>(BinaryOptions.class, "macabi", "getMacabi()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", 0)};
        $$delegatedProperties = kPropertyArr;
        final BinaryOptions binaryOptions = new BinaryOptions();
        INSTANCE = binaryOptions;
        final BinaryOptions$special$$inlined$option$default$1 binaryOptions$special$$inlined$option$default$1 = new Function1<RuntimeAssertsMode, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$1
            public final String invoke(RuntimeAssertsMode runtimeAssertsMode) {
                runtimeAssertsMode.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$2 binaryOptions$special$$inlined$option$default$2 = new Function1<RuntimeAssertsMode, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$2
            public final Boolean invoke(RuntimeAssertsMode runtimeAssertsMode) {
                runtimeAssertsMode.getClass();
                return Boolean.FALSE;
            }
        };
        runtimeAssertionsMode = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$3
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(RuntimeAssertsMode.values()), binaryOptions$special$$inlined$option$default$1, binaryOptions$special$$inlined$option$default$2), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$3.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[0]);
        checkStateAtExternalCalls = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[1]);
        final BinaryOptions$special$$inlined$option$default$4 binaryOptions$special$$inlined$option$default$4 = new Function1<MemoryModel, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$4
            public final String invoke(MemoryModel memoryModel2) {
                memoryModel2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$5 binaryOptions$special$$inlined$option$default$5 = new Function1<MemoryModel, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$5
            public final Boolean invoke(MemoryModel memoryModel2) {
                memoryModel2.getClass();
                return Boolean.FALSE;
            }
        };
        memoryModel = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$6
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(MemoryModel.values()), binaryOptions$special$$inlined$option$default$4, binaryOptions$special$$inlined$option$default$5), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$6.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[2]);
        final BinaryOptions$special$$inlined$option$default$7 binaryOptions$special$$inlined$option$default$7 = new Function1<Freezing, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$7
            public final String invoke(Freezing freezing2) {
                freezing2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$8 binaryOptions$special$$inlined$option$default$8 = new Function1<Freezing, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$8
            public final Boolean invoke(Freezing freezing2) {
                freezing2.getClass();
                return Boolean.FALSE;
            }
        };
        freezing = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$9
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(Freezing.values()), binaryOptions$special$$inlined$option$default$7, binaryOptions$special$$inlined$option$default$8), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$9.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[3]);
        stripDebugInfoFromNativeLibs = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[4]);
        final BinaryOptions$special$$inlined$option$default$10 binaryOptions$special$$inlined$option$default$10 = new Function1<SourceInfoType, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$10
            public final String invoke(SourceInfoType sourceInfoType2) {
                sourceInfoType2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$11 binaryOptions$special$$inlined$option$default$11 = new Function1<SourceInfoType, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$11
            public final Boolean invoke(SourceInfoType sourceInfoType2) {
                sourceInfoType2.getClass();
                return Boolean.FALSE;
            }
        };
        sourceInfoType = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$12
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(SourceInfoType.values()), binaryOptions$special$$inlined$option$default$10, binaryOptions$special$$inlined$option$default$11), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$12.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[5]);
        final BinaryOptions$special$$inlined$option$default$13 binaryOptions$special$$inlined$option$default$13 = new Function1<CoreSymbolicationImageListType, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$13
            public final String invoke(CoreSymbolicationImageListType coreSymbolicationImageListType2) {
                coreSymbolicationImageListType2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$14 binaryOptions$special$$inlined$option$default$14 = new Function1<CoreSymbolicationImageListType, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$14
            public final Boolean invoke(CoreSymbolicationImageListType coreSymbolicationImageListType2) {
                coreSymbolicationImageListType2.getClass();
                return Boolean.FALSE;
            }
        };
        coreSymbolicationImageListType = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$15
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(CoreSymbolicationImageListType.values()), binaryOptions$special$$inlined$option$default$13, binaryOptions$special$$inlined$option$default$14), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$15.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[6]);
        final BinaryOptions$special$$inlined$option$default$16 binaryOptions$special$$inlined$option$default$16 = new Function1<AndroidProgramType, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$16
            public final String invoke(AndroidProgramType androidProgramType2) {
                androidProgramType2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$17 binaryOptions$special$$inlined$option$default$17 = new Function1<AndroidProgramType, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$17
            public final Boolean invoke(AndroidProgramType androidProgramType2) {
                androidProgramType2.getClass();
                return Boolean.FALSE;
            }
        };
        androidProgramType = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$18
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(AndroidProgramType.values()), binaryOptions$special$$inlined$option$default$16, binaryOptions$special$$inlined$option$default$17), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$18.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[7]);
        final BinaryOptions$special$$inlined$option$default$19 binaryOptions$special$$inlined$option$default$19 = new Function1<UnitSuspendFunctionObjCExport, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$19
            public final String invoke(UnitSuspendFunctionObjCExport unitSuspendFunctionObjCExport2) {
                unitSuspendFunctionObjCExport2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$20 binaryOptions$special$$inlined$option$default$20 = new Function1<UnitSuspendFunctionObjCExport, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$20
            public final Boolean invoke(UnitSuspendFunctionObjCExport unitSuspendFunctionObjCExport2) {
                unitSuspendFunctionObjCExport2.getClass();
                return Boolean.FALSE;
            }
        };
        unitSuspendFunctionObjCExport = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$21
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(UnitSuspendFunctionObjCExport.values()), binaryOptions$special$$inlined$option$default$19, binaryOptions$special$$inlined$option$default$20), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$21.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[8]);
        final BinaryOptions$special$$inlined$option$default$22 binaryOptions$special$$inlined$option$default$22 = new Function1<ObjCExportSuspendFunctionLaunchThreadRestriction, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$22
            public final String invoke(ObjCExportSuspendFunctionLaunchThreadRestriction objCExportSuspendFunctionLaunchThreadRestriction) {
                objCExportSuspendFunctionLaunchThreadRestriction.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$23 binaryOptions$special$$inlined$option$default$23 = new Function1<ObjCExportSuspendFunctionLaunchThreadRestriction, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$23
            public final Boolean invoke(ObjCExportSuspendFunctionLaunchThreadRestriction objCExportSuspendFunctionLaunchThreadRestriction) {
                objCExportSuspendFunctionLaunchThreadRestriction.getClass();
                return Boolean.FALSE;
            }
        };
        objcExportSuspendFunctionLaunchThreadRestriction = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$24
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(ObjCExportSuspendFunctionLaunchThreadRestriction.values()), binaryOptions$special$$inlined$option$default$22, binaryOptions$special$$inlined$option$default$23), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$24.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[9]);
        objcExportDisableSwiftMemberNameMangling = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[10]);
        objcExportIgnoreInterfaceMethodCollisions = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[11]);
        objcExportReportNameCollisions = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[12]);
        objcExportErrorOnNameCollisions = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[13]);
        objcExportEntryPointsPath = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[14]);
        objcExportExplicitMethodFamily = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[15]);
        objcExportBlockExplicitParameterNames = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[16]);
        dumpObjcSelectorToSignatureMapping = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[17]);
        final Function1 function1 = new Function1() { // from class: ev0
            public final Object invoke(Object obj) {
                return BinaryOptions.j((GC) obj);
            }
        };
        final BinaryOptions$special$$inlined$option$default$25 binaryOptions$special$$inlined$option$default$25 = new Function1<GC, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$25
            public final Boolean invoke(GC gc2) {
                gc2.getClass();
                return Boolean.FALSE;
            }
        };
        gc = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$26
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(GC.values()), function1, binaryOptions$special$$inlined$option$default$25), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$26.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[18]);
        final Function1 function2 = new Function1() { // from class: fv0
            public final Object invoke(Object obj) {
                return Boolean.valueOf(BinaryOptions.i((GCSchedulerType) obj));
            }
        };
        final BinaryOptions$special$$inlined$option$default$27 binaryOptions$special$$inlined$option$default$27 = new Function1<GCSchedulerType, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$27
            public final String invoke(GCSchedulerType gCSchedulerType) {
                gCSchedulerType.getClass();
                return null;
            }
        };
        gcSchedulerType = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$28
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(GCSchedulerType.values()), binaryOptions$special$$inlined$option$default$27, function2), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$28.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[19]);
        gcMarkSingleThreaded = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[20]);
        fixedBlockPageSize = binaryOptions.uintOption().provideDelegate(binaryOptions, kPropertyArr[21]);
        concurrentWeakSweep = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[22]);
        concurrentMarkMaxIterations = binaryOptions.uintOption().provideDelegate(binaryOptions, kPropertyArr[23]);
        gcMutatorsCooperate = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[24]);
        auxGCThreads = binaryOptions.uintOption().provideDelegate(binaryOptions, kPropertyArr[25]);
        final BinaryOptions$special$$inlined$option$default$29 binaryOptions$special$$inlined$option$default$29 = new Function1<RuntimeLinkageStrategy, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$29
            public final String invoke(RuntimeLinkageStrategy runtimeLinkageStrategy) {
                runtimeLinkageStrategy.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$30 binaryOptions$special$$inlined$option$default$30 = new Function1<RuntimeLinkageStrategy, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$30
            public final Boolean invoke(RuntimeLinkageStrategy runtimeLinkageStrategy) {
                runtimeLinkageStrategy.getClass();
                return Boolean.FALSE;
            }
        };
        linkRuntime = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$31
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(RuntimeLinkageStrategy.values()), binaryOptions$special$$inlined$option$default$29, binaryOptions$special$$inlined$option$default$30), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$31.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[26]);
        bundleId = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[27]);
        bundleShortVersionString = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[28]);
        bundleVersion = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[29]);
        final BinaryOptions$special$$inlined$option$default$32 binaryOptions$special$$inlined$option$default$32 = new Function1<AppStateTracking, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$32
            public final String invoke(AppStateTracking appStateTracking2) {
                appStateTracking2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$33 binaryOptions$special$$inlined$option$default$33 = new Function1<AppStateTracking, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$33
            public final Boolean invoke(AppStateTracking appStateTracking2) {
                appStateTracking2.getClass();
                return Boolean.FALSE;
            }
        };
        appStateTracking = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$34
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(AppStateTracking.values()), binaryOptions$special$$inlined$option$default$32, binaryOptions$special$$inlined$option$default$33), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$34.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[30]);
        final BinaryOptions$special$$inlined$option$default$35 binaryOptions$special$$inlined$option$default$35 = new Function1<SanitizerKind, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$35
            public final String invoke(SanitizerKind sanitizerKind) {
                sanitizerKind.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$36 binaryOptions$special$$inlined$option$default$36 = new Function1<SanitizerKind, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$36
            public final Boolean invoke(SanitizerKind sanitizerKind) {
                sanitizerKind.getClass();
                return Boolean.FALSE;
            }
        };
        sanitizer = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$37
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(SanitizerKind.values()), binaryOptions$special$$inlined$option$default$35, binaryOptions$special$$inlined$option$default$36), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$37.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[31]);
        compileBitcodeWithXcodeLlvm = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[32]);
        objcDisposeOnMain = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[33]);
        objcDisposeWithRunLoop = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[34]);
        disableMmap = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[35]);
        mmapTag = binaryOptions.uintOption().provideDelegate(binaryOptions, kPropertyArr[36]);
        enableSafepointSignposts = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[37]);
        forceNativeThreadStateForFunctions = (ReadOnlyProperty) binaryOptions.listOption(StringValueParser.INSTANCE).provideDelegate(binaryOptions, kPropertyArr[38]);
        packFields = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[39]);
        final BinaryOptions$special$$inlined$option$default$38 binaryOptions$special$$inlined$option$default$38 = new Function1<CInterfaceGenerationMode, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$38
            public final String invoke(CInterfaceGenerationMode cInterfaceGenerationMode) {
                cInterfaceGenerationMode.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$39 binaryOptions$special$$inlined$option$default$39 = new Function1<CInterfaceGenerationMode, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$39
            public final Boolean invoke(CInterfaceGenerationMode cInterfaceGenerationMode) {
                cInterfaceGenerationMode.getClass();
                return Boolean.FALSE;
            }
        };
        cInterfaceMode = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$40
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(CInterfaceGenerationMode.values()), binaryOptions$special$$inlined$option$default$38, binaryOptions$special$$inlined$option$default$39), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$40.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[40]);
        globalDataLazyInit = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[41]);
        swiftExport = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[42]);
        genericSafeCasts = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[43]);
        smallBinary = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[44]);
        preCodegenInlineThreshold = binaryOptions.uintOption().provideDelegate(binaryOptions, kPropertyArr[45]);
        enableDebugTransparentStepping = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[46]);
        debugCompilationDir = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[47]);
        pagedAllocator = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[48]);
        latin1Strings = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[49]);
        final BinaryOptions$special$$inlined$option$default$41 binaryOptions$special$$inlined$option$default$41 = new Function1<StackProtectorMode, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$41
            public final String invoke(StackProtectorMode stackProtectorMode) {
                stackProtectorMode.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$42 binaryOptions$special$$inlined$option$default$42 = new Function1<StackProtectorMode, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$42
            public final Boolean invoke(StackProtectorMode stackProtectorMode) {
                stackProtectorMode.getClass();
                return Boolean.FALSE;
            }
        };
        stackProtector = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$43
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(StackProtectorMode.values()), binaryOptions$special$$inlined$option$default$41, binaryOptions$special$$inlined$option$default$42), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$43.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[50]);
        minidumpLocation = binaryOptions.stringOption().provideDelegate(binaryOptions, kPropertyArr[51]);
        minidumpOnSIGTERM = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[52]);
        final BinaryOptions$special$$inlined$option$default$44 binaryOptions$special$$inlined$option$default$44 = new Function1<CCallMode, String>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$44
            public final String invoke(CCallMode cCallMode2) {
                cCallMode2.getClass();
                return null;
            }
        };
        final BinaryOptions$special$$inlined$option$default$45 binaryOptions$special$$inlined$option$default$45 = new Function1<CCallMode, Boolean>() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$45
            public final Boolean invoke(CCallMode cCallMode2) {
                cCallMode2.getClass();
                return Boolean.FALSE;
            }
        };
        cCallMode = (ReadOnlyProperty) new PropertyDelegateProvider() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$46
            @Override // kotlin.properties.PropertyDelegateProvider
            public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
                kProperty.getClass();
                final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new EnumValueParser(ArraysKt.toList(CCallMode.values()), binaryOptions$special$$inlined$option$default$44, binaryOptions$special$$inlined$option$default$45), null, 4, null);
                binaryOptions.register(binaryOption);
                return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptions$special$$inlined$option$default$46.1
                    @Override // kotlin.properties.ReadOnlyProperty
                    public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                        kProperty2.getClass();
                        return binaryOption.getCompilerConfigurationKey();
                    }

                    @Override // kotlin.properties.ReadOnlyProperty
                    public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                        return getValue(obj2, (KProperty<?>) kProperty2);
                    }
                };
            }

            @Override // kotlin.properties.PropertyDelegateProvider
            public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
                return provideDelegate(obj, (KProperty<?>) kProperty);
            }
        }.provideDelegate(binaryOptions, kPropertyArr[53]);
        macabi = binaryOptions.booleanOption().provideDelegate(binaryOptions, kPropertyArr[54]);
    }

    private BinaryOptions() {
    }

    public static boolean i(GCSchedulerType gCSchedulerType) {
        gCSchedulerType.getClass();
        return gCSchedulerType.getDeprecatedWithReplacement() != null;
    }

    public static String j(GC gc2) {
        gc2.getClass();
        return gc2.getShortcut();
    }

    public final CompilerConfigurationKey<AndroidProgramType> getAndroidProgramType() {
        return (CompilerConfigurationKey) androidProgramType.getValue(this, $$delegatedProperties[7]);
    }

    public final CompilerConfigurationKey<AppStateTracking> getAppStateTracking() {
        return (CompilerConfigurationKey) appStateTracking.getValue(this, $$delegatedProperties[30]);
    }

    public final CompilerConfigurationKey<UInt> getAuxGCThreads() {
        return (CompilerConfigurationKey) auxGCThreads.getValue(this, $$delegatedProperties[25]);
    }

    public final CompilerConfigurationKey<String> getBundleId() {
        return (CompilerConfigurationKey) bundleId.getValue(this, $$delegatedProperties[27]);
    }

    public final CompilerConfigurationKey<String> getBundleShortVersionString() {
        return (CompilerConfigurationKey) bundleShortVersionString.getValue(this, $$delegatedProperties[28]);
    }

    public final CompilerConfigurationKey<String> getBundleVersion() {
        return (CompilerConfigurationKey) bundleVersion.getValue(this, $$delegatedProperties[29]);
    }

    public final CompilerConfigurationKey<CCallMode> getCCallMode() {
        return (CompilerConfigurationKey) cCallMode.getValue(this, $$delegatedProperties[53]);
    }

    public final CompilerConfigurationKey<CInterfaceGenerationMode> getCInterfaceMode() {
        return (CompilerConfigurationKey) cInterfaceMode.getValue(this, $$delegatedProperties[40]);
    }

    public final CompilerConfigurationKey<Boolean> getCheckStateAtExternalCalls() {
        return (CompilerConfigurationKey) checkStateAtExternalCalls.getValue(this, $$delegatedProperties[1]);
    }

    public final CompilerConfigurationKey<Boolean> getCompileBitcodeWithXcodeLlvm() {
        return (CompilerConfigurationKey) compileBitcodeWithXcodeLlvm.getValue(this, $$delegatedProperties[32]);
    }

    public final CompilerConfigurationKey<UInt> getConcurrentMarkMaxIterations() {
        return (CompilerConfigurationKey) concurrentMarkMaxIterations.getValue(this, $$delegatedProperties[23]);
    }

    public final CompilerConfigurationKey<Boolean> getConcurrentWeakSweep() {
        return (CompilerConfigurationKey) concurrentWeakSweep.getValue(this, $$delegatedProperties[22]);
    }

    public final CompilerConfigurationKey<CoreSymbolicationImageListType> getCoreSymbolicationImageListType() {
        return (CompilerConfigurationKey) coreSymbolicationImageListType.getValue(this, $$delegatedProperties[6]);
    }

    public final CompilerConfigurationKey<String> getDebugCompilationDir() {
        return (CompilerConfigurationKey) debugCompilationDir.getValue(this, $$delegatedProperties[47]);
    }

    public final CompilerConfigurationKey<Boolean> getDisableMmap() {
        return (CompilerConfigurationKey) disableMmap.getValue(this, $$delegatedProperties[35]);
    }

    public final CompilerConfigurationKey<String> getDumpObjcSelectorToSignatureMapping() {
        return (CompilerConfigurationKey) dumpObjcSelectorToSignatureMapping.getValue(this, $$delegatedProperties[17]);
    }

    public final CompilerConfigurationKey<Boolean> getEnableDebugTransparentStepping() {
        return (CompilerConfigurationKey) enableDebugTransparentStepping.getValue(this, $$delegatedProperties[46]);
    }

    public final CompilerConfigurationKey<Boolean> getEnableSafepointSignposts() {
        return (CompilerConfigurationKey) enableSafepointSignposts.getValue(this, $$delegatedProperties[37]);
    }

    public final CompilerConfigurationKey<UInt> getFixedBlockPageSize() {
        return (CompilerConfigurationKey) fixedBlockPageSize.getValue(this, $$delegatedProperties[21]);
    }

    public final CompilerConfigurationKey<List<String>> getForceNativeThreadStateForFunctions() {
        return (CompilerConfigurationKey) forceNativeThreadStateForFunctions.getValue(this, $$delegatedProperties[38]);
    }

    public final CompilerConfigurationKey<Freezing> getFreezing() {
        return (CompilerConfigurationKey) freezing.getValue(this, $$delegatedProperties[3]);
    }

    public final CompilerConfigurationKey<GC> getGc() {
        return (CompilerConfigurationKey) gc.getValue(this, $$delegatedProperties[18]);
    }

    public final CompilerConfigurationKey<Boolean> getGcMarkSingleThreaded() {
        return (CompilerConfigurationKey) gcMarkSingleThreaded.getValue(this, $$delegatedProperties[20]);
    }

    public final CompilerConfigurationKey<Boolean> getGcMutatorsCooperate() {
        return (CompilerConfigurationKey) gcMutatorsCooperate.getValue(this, $$delegatedProperties[24]);
    }

    public final CompilerConfigurationKey<GCSchedulerType> getGcSchedulerType() {
        return (CompilerConfigurationKey) gcSchedulerType.getValue(this, $$delegatedProperties[19]);
    }

    public final CompilerConfigurationKey<Boolean> getGenericSafeCasts() {
        return (CompilerConfigurationKey) genericSafeCasts.getValue(this, $$delegatedProperties[43]);
    }

    public final CompilerConfigurationKey<Boolean> getGlobalDataLazyInit() {
        return (CompilerConfigurationKey) globalDataLazyInit.getValue(this, $$delegatedProperties[41]);
    }

    public final CompilerConfigurationKey<Boolean> getLatin1Strings() {
        return (CompilerConfigurationKey) latin1Strings.getValue(this, $$delegatedProperties[49]);
    }

    public final CompilerConfigurationKey<RuntimeLinkageStrategy> getLinkRuntime() {
        return (CompilerConfigurationKey) linkRuntime.getValue(this, $$delegatedProperties[26]);
    }

    public final CompilerConfigurationKey<Boolean> getMacabi() {
        return (CompilerConfigurationKey) macabi.getValue(this, $$delegatedProperties[54]);
    }

    public final CompilerConfigurationKey<MemoryModel> getMemoryModel() {
        return (CompilerConfigurationKey) memoryModel.getValue(this, $$delegatedProperties[2]);
    }

    public final CompilerConfigurationKey<String> getMinidumpLocation() {
        return (CompilerConfigurationKey) minidumpLocation.getValue(this, $$delegatedProperties[51]);
    }

    public final CompilerConfigurationKey<Boolean> getMinidumpOnSIGTERM() {
        return (CompilerConfigurationKey) minidumpOnSIGTERM.getValue(this, $$delegatedProperties[52]);
    }

    public final CompilerConfigurationKey<UInt> getMmapTag() {
        return (CompilerConfigurationKey) mmapTag.getValue(this, $$delegatedProperties[36]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcDisposeOnMain() {
        return (CompilerConfigurationKey) objcDisposeOnMain.getValue(this, $$delegatedProperties[33]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcDisposeWithRunLoop() {
        return (CompilerConfigurationKey) objcDisposeWithRunLoop.getValue(this, $$delegatedProperties[34]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcExportBlockExplicitParameterNames() {
        return (CompilerConfigurationKey) objcExportBlockExplicitParameterNames.getValue(this, $$delegatedProperties[16]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcExportDisableSwiftMemberNameMangling() {
        return (CompilerConfigurationKey) objcExportDisableSwiftMemberNameMangling.getValue(this, $$delegatedProperties[10]);
    }

    public final CompilerConfigurationKey<String> getObjcExportEntryPointsPath() {
        return (CompilerConfigurationKey) objcExportEntryPointsPath.getValue(this, $$delegatedProperties[14]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcExportErrorOnNameCollisions() {
        return (CompilerConfigurationKey) objcExportErrorOnNameCollisions.getValue(this, $$delegatedProperties[13]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcExportExplicitMethodFamily() {
        return (CompilerConfigurationKey) objcExportExplicitMethodFamily.getValue(this, $$delegatedProperties[15]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcExportIgnoreInterfaceMethodCollisions() {
        return (CompilerConfigurationKey) objcExportIgnoreInterfaceMethodCollisions.getValue(this, $$delegatedProperties[11]);
    }

    public final CompilerConfigurationKey<Boolean> getObjcExportReportNameCollisions() {
        return (CompilerConfigurationKey) objcExportReportNameCollisions.getValue(this, $$delegatedProperties[12]);
    }

    public final CompilerConfigurationKey<ObjCExportSuspendFunctionLaunchThreadRestriction> getObjcExportSuspendFunctionLaunchThreadRestriction() {
        return (CompilerConfigurationKey) objcExportSuspendFunctionLaunchThreadRestriction.getValue(this, $$delegatedProperties[9]);
    }

    public final CompilerConfigurationKey<Boolean> getPackFields() {
        return (CompilerConfigurationKey) packFields.getValue(this, $$delegatedProperties[39]);
    }

    public final CompilerConfigurationKey<Boolean> getPagedAllocator() {
        return (CompilerConfigurationKey) pagedAllocator.getValue(this, $$delegatedProperties[48]);
    }

    public final CompilerConfigurationKey<UInt> getPreCodegenInlineThreshold() {
        return (CompilerConfigurationKey) preCodegenInlineThreshold.getValue(this, $$delegatedProperties[45]);
    }

    public final CompilerConfigurationKey<RuntimeAssertsMode> getRuntimeAssertionsMode() {
        return (CompilerConfigurationKey) runtimeAssertionsMode.getValue(this, $$delegatedProperties[0]);
    }

    public final CompilerConfigurationKey<SanitizerKind> getSanitizer() {
        return (CompilerConfigurationKey) sanitizer.getValue(this, $$delegatedProperties[31]);
    }

    public final CompilerConfigurationKey<Boolean> getSmallBinary() {
        return (CompilerConfigurationKey) smallBinary.getValue(this, $$delegatedProperties[44]);
    }

    public final CompilerConfigurationKey<SourceInfoType> getSourceInfoType() {
        return (CompilerConfigurationKey) sourceInfoType.getValue(this, $$delegatedProperties[5]);
    }

    public final CompilerConfigurationKey<StackProtectorMode> getStackProtector() {
        return (CompilerConfigurationKey) stackProtector.getValue(this, $$delegatedProperties[50]);
    }

    public final CompilerConfigurationKey<Boolean> getStripDebugInfoFromNativeLibs() {
        return (CompilerConfigurationKey) stripDebugInfoFromNativeLibs.getValue(this, $$delegatedProperties[4]);
    }

    public final CompilerConfigurationKey<Boolean> getSwiftExport() {
        return (CompilerConfigurationKey) swiftExport.getValue(this, $$delegatedProperties[42]);
    }

    public final CompilerConfigurationKey<UnitSuspendFunctionObjCExport> getUnitSuspendFunctionObjCExport() {
        return (CompilerConfigurationKey) unitSuspendFunctionObjCExport.getValue(this, $$delegatedProperties[8]);
    }
}
