package org.jetbrains.kotlin.config;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\t\",\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\",\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007\"(\u0010\f\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\",\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007\"(\u0010\u0014\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010\"(\u0010\u0017\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010\"(\u0010\u001a\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010\"(\u0010\u001d\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010\"(\u0010 \u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010\",\u0010$\u001a\u0004\u0018\u00010#*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010#8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(\"(\u0010)\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010\u000e\"\u0004\b+\u0010\u0010\"(\u0010,\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\u000e\"\u0004\b.\u0010\u0010\",\u00100\u001a\u0004\u0018\u00010/*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010/8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104\"(\u00105\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b6\u0010\u000e\"\u0004\b7\u0010\u0010\",\u00109\u001a\u0004\u0018\u000108*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u0001088F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=\",\u0010>\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b?\u0010\u0005\"\u0004\b@\u0010\u0007\"4\u0010C\u001a\b\u0012\u0004\u0012\u00020B0A*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020B0A8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G\"4\u0010I\u001a\b\u0012\u0004\u0012\u00020H0A*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020H0A8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bJ\u0010E\"\u0004\bK\u0010G\"(\u0010L\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bM\u0010\u000e\"\u0004\bN\u0010\u0010\"(\u0010O\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bP\u0010\u000e\"\u0004\bQ\u0010\u0010\"(\u0010R\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bS\u0010\u000e\"\u0004\bT\u0010\u0010\"(\u0010U\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bV\u0010\u000e\"\u0004\bW\u0010\u0010\"4\u0010X\u001a\b\u0012\u0004\u0012\u00020H0A*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020H0A8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bY\u0010E\"\u0004\bZ\u0010G\"(\u0010[\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\\\u0010\u000e\"\u0004\b]\u0010\u0010\",\u0010_\u001a\u0004\u0018\u00010^*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010^8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u0010a\"\u0004\bb\u0010c\",\u0010e\u001a\u0004\u0018\u00010d*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bf\u0010g\"\u0004\bh\u0010i\",\u0010k\u001a\u0004\u0018\u00010j*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010j8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bl\u0010m\"\u0004\bn\u0010o\",\u0010p\u001a\u0004\u0018\u00010j*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010j8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bq\u0010m\"\u0004\br\u0010o\"4\u0010s\u001a\b\u0012\u0004\u0012\u00020H0A*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020H0A8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bt\u0010E\"\u0004\bu\u0010G\",\u0010w\u001a\u0004\u0018\u00010v*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010v8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{\"(\u0010|\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b}\u0010\u000e\"\u0004\b~\u0010\u0010\"*\u0010\u007f\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0080\u0001\u0010\u000e\"\u0005\b\u0081\u0001\u0010\u0010\"+\u0010\u0082\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0083\u0001\u0010\u000e\"\u0005\b\u0084\u0001\u0010\u0010\"+\u0010\u0085\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0086\u0001\u0010\u000e\"\u0005\b\u0087\u0001\u0010\u0010\"+\u0010\u0088\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0089\u0001\u0010\u000e\"\u0005\b\u008a\u0001\u0010\u0010\"+\u0010\u008b\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u008c\u0001\u0010\u000e\"\u0005\b\u008d\u0001\u0010\u0010\"+\u0010\u008e\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u008f\u0001\u0010\u000e\"\u0005\b\u0090\u0001\u0010\u0010\"+\u0010\u0091\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0092\u0001\u0010\u000e\"\u0005\b\u0093\u0001\u0010\u0010\"+\u0010\u0094\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0095\u0001\u0010\u000e\"\u0005\b\u0096\u0001\u0010\u0010\"+\u0010\u0097\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0098\u0001\u0010\u000e\"\u0005\b\u0099\u0001\u0010\u0010\"+\u0010\u009a\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u009b\u0001\u0010\u000e\"\u0005\b\u009c\u0001\u0010\u0010\"+\u0010\u009d\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u009e\u0001\u0010\u000e\"\u0005\b\u009f\u0001\u0010\u0010\"+\u0010 \u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¡\u0001\u0010\u000e\"\u0005\b¢\u0001\u0010\u0010\"+\u0010£\u0001\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¤\u0001\u0010\u000e\"\u0005\b¥\u0001\u0010\u0010\"1\u0010¦\u0001\u001a\u0004\u0018\u00010H*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010H8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0006\b©\u0001\u0010ª\u0001\"3\u0010¬\u0001\u001a\u0005\u0018\u00010«\u0001*\u00020\u00032\t\u0010\u0000\u001a\u0005\u0018\u00010«\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u00ad\u0001\u0010®\u0001\"\u0006\b¯\u0001\u0010°\u0001\"7\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020H0A*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020H0A8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b²\u0001\u0010E\"\u0005\b³\u0001\u0010G¨\u0006´\u0001"}, d2 = {"value", "Ljava/io/File;", "outputDirectory", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getOutputDirectory", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/io/File;", "setOutputDirectory", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/io/File;)V", "outputJar", "getOutputJar", "setOutputJar", Argument.Delimiters.none, "includeRuntime", "getIncludeRuntime", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Z", "setIncludeRuntime", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", "jdkHome", "getJdkHome", "setJdkHome", "noJdk", "getNoJdk", "setNoJdk", "disableStandardScriptDefinition", "getDisableStandardScriptDefinition", "setDisableStandardScriptDefinition", "disableCallAssertions", "getDisableCallAssertions", "setDisableCallAssertions", "disableReceiverAssertions", "getDisableReceiverAssertions", "setDisableReceiverAssertions", "disableParamAssertions", "getDisableParamAssertions", "setDisableParamAssertions", "Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", "assertionsMode", "getAssertionsMode", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", "setAssertionsMode", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/JVMAssertionsMode;)V", "disableOptimization", "getDisableOptimization", "setDisableOptimization", "useTypeTable", "getUseTypeTable", "setUseTypeTable", "Lorg/jetbrains/kotlin/config/JvmTarget;", "jvmTarget", "getJvmTarget", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/JvmTarget;", "setJvmTarget", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/JvmTarget;)V", "parametersMetadata", "getParametersMetadata", "setParametersMetadata", "Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCompilationComponents;", "incrementalCompilationComponents", "getIncrementalCompilationComponents", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCompilationComponents;", "setIncrementalCompilationComponents", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCompilationComponents;)V", "moduleXmlFile", "getModuleXmlFile", "setModuleXmlFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/modules/Module;", ModuleXmlParser.MODULES, "getModules", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", "setModules", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)V", Argument.Delimiters.none, "friendPaths", "getFriendPaths", "setFriendPaths", "usePsiClassFilesReading", "getUsePsiClassFilesReading", "setUsePsiClassFilesReading", "useFastJarFileSystem", "getUseFastJarFileSystem", "setUseFastJarFileSystem", "useJavac", "getUseJavac", "setUseJavac", "compileJava", "getCompileJava", "setCompileJava", "additionalJavaModules", "getAdditionalJavaModules", "setAdditionalJavaModules", "emitJvmTypeAnnotations", "getEmitJvmTypeAnnotations", "setEmitJvmTypeAnnotations", "Lorg/jetbrains/kotlin/config/JvmStringConcat;", "stringConcat", "getStringConcat", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/JvmStringConcat;", "setStringConcat", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/JvmStringConcat;)V", Argument.Delimiters.none, "jdkRelease", "getJdkRelease", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/Integer;", "setJdkRelease", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/Integer;)V", "Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;", "samConversions", "getSamConversions", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;", "setSamConversions", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;)V", "lambdas", "getLambdas", "setLambdas", "klibPaths", "getKlibPaths", "setKlibPaths", "Lorg/jetbrains/kotlin/config/JvmAbiStability;", "abiStability", "getAbiStability", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/JvmAbiStability;", "setAbiStability", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/JvmAbiStability;)V", "doNotClearBindingContext", "getDoNotClearBindingContext", "setDoNotClearBindingContext", "noResetJarTimestamps", "getNoResetJarTimestamps", "setNoResetJarTimestamps", "noUnifiedNullChecks", "getNoUnifiedNullChecks", "setNoUnifiedNullChecks", "noSourceDebugExtension", "getNoSourceDebugExtension", "setNoSourceDebugExtension", "useOldInlineClassesManglingScheme", "getUseOldInlineClassesManglingScheme", "setUseOldInlineClassesManglingScheme", "enableJvmPreview", "getEnableJvmPreview", "setEnableJvmPreview", "noReflect", "getNoReflect", "setNoReflect", "validateBytecode", "getValidateBytecode", "setValidateBytecode", "linkViaSignatures", "getLinkViaSignatures", "setLinkViaSignatures", "enableDebugMode", "getEnableDebugMode", "setEnableDebugMode", "enhancedCoroutinesDebugging", "getEnhancedCoroutinesDebugging", "setEnhancedCoroutinesDebugging", "noNewJavaAnnotationTargets", "getNoNewJavaAnnotationTargets", "setNoNewJavaAnnotationTargets", "useInlineScopesNumbers", "getUseInlineScopesNumbers", "setUseInlineScopesNumbers", "skipBodies", "getSkipBodies", "setSkipBodies", "expressionToEvaluate", "getExpressionToEvaluate", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/String;", "setExpressionToEvaluate", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)V", "Lorg/jetbrains/kotlin/config/JvmWhenGenerationScheme;", "whenGenerationScheme", "getWhenGenerationScheme", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/JvmWhenGenerationScheme;", "setWhenGenerationScheme", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/JvmWhenGenerationScheme;)V", "ignoredAnnotationsForBridges", "getIgnoredAnnotationsForBridges", "setIgnoredAnnotationsForBridges", "org.jetbrains.kotlin:config.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JVMConfigurationKeysKt {
    public static final JvmAbiStability getAbiStability(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JvmAbiStability) compilerConfiguration.get(JVMConfigurationKeys.ABI_STABILITY);
    }

    public static final List<String> getAdditionalJavaModules(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JVMConfigurationKeys.ADDITIONAL_JAVA_MODULES);
    }

    public static final JVMAssertionsMode getAssertionsMode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JVMAssertionsMode) compilerConfiguration.get(JVMConfigurationKeys.ASSERTIONS_MODE);
    }

    public static final boolean getCompileJava(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.COMPILE_JAVA);
    }

    public static final boolean getDisableCallAssertions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_CALL_ASSERTIONS);
    }

    public static final boolean getDisableOptimization(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_OPTIMIZATION);
    }

    public static final boolean getDisableParamAssertions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_PARAM_ASSERTIONS);
    }

    public static final boolean getDisableReceiverAssertions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_RECEIVER_ASSERTIONS);
    }

    public static final boolean getDisableStandardScriptDefinition(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_STANDARD_SCRIPT_DEFINITION);
    }

    public static final boolean getDoNotClearBindingContext(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.DO_NOT_CLEAR_BINDING_CONTEXT);
    }

    public static final boolean getEmitJvmTypeAnnotations(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.EMIT_JVM_TYPE_ANNOTATIONS);
    }

    public static final boolean getEnableDebugMode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.ENABLE_DEBUG_MODE);
    }

    public static final boolean getEnableJvmPreview(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.ENABLE_JVM_PREVIEW);
    }

    public static final boolean getEnhancedCoroutinesDebugging(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.ENHANCED_COROUTINES_DEBUGGING);
    }

    public static final String getExpressionToEvaluate(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JVMConfigurationKeys.EXPRESSION_TO_EVALUATE);
    }

    public static final List<String> getFriendPaths(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JVMConfigurationKeys.FRIEND_PATHS);
    }

    public static final List<String> getIgnoredAnnotationsForBridges(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JVMConfigurationKeys.IGNORED_ANNOTATIONS_FOR_BRIDGES);
    }

    public static final boolean getIncludeRuntime(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.INCLUDE_RUNTIME);
    }

    public static final IncrementalCompilationComponents getIncrementalCompilationComponents(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (IncrementalCompilationComponents) compilerConfiguration.get(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS);
    }

    public static final File getJdkHome(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(JVMConfigurationKeys.JDK_HOME);
    }

    public static final Integer getJdkRelease(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (Integer) compilerConfiguration.get(JVMConfigurationKeys.JDK_RELEASE);
    }

    public static final JvmTarget getJvmTarget(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JvmTarget) compilerConfiguration.get(JVMConfigurationKeys.JVM_TARGET);
    }

    public static final List<String> getKlibPaths(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JVMConfigurationKeys.KLIB_PATHS);
    }

    public static final JvmClosureGenerationScheme getLambdas(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JvmClosureGenerationScheme) compilerConfiguration.get(JVMConfigurationKeys.LAMBDAS);
    }

    public static final boolean getLinkViaSignatures(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.LINK_VIA_SIGNATURES);
    }

    public static final File getModuleXmlFile(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(JVMConfigurationKeys.MODULE_XML_FILE);
    }

    public static final List<Module> getModules(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JVMConfigurationKeys.MODULES);
    }

    public static final boolean getNoJdk(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_JDK);
    }

    public static final boolean getNoNewJavaAnnotationTargets(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_NEW_JAVA_ANNOTATION_TARGETS);
    }

    public static final boolean getNoReflect(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_REFLECT);
    }

    public static final boolean getNoResetJarTimestamps(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_RESET_JAR_TIMESTAMPS);
    }

    public static final boolean getNoSourceDebugExtension(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_SOURCE_DEBUG_EXTENSION);
    }

    public static final boolean getNoUnifiedNullChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_UNIFIED_NULL_CHECKS);
    }

    public static final File getOutputDirectory(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_DIRECTORY);
    }

    public static final File getOutputJar(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_JAR);
    }

    public static final boolean getParametersMetadata(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.PARAMETERS_METADATA);
    }

    public static final JvmClosureGenerationScheme getSamConversions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JvmClosureGenerationScheme) compilerConfiguration.get(JVMConfigurationKeys.SAM_CONVERSIONS);
    }

    public static final boolean getSkipBodies(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.SKIP_BODIES);
    }

    public static final JvmStringConcat getStringConcat(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JvmStringConcat) compilerConfiguration.get(JVMConfigurationKeys.STRING_CONCAT);
    }

    public static final boolean getUseFastJarFileSystem(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_FAST_JAR_FILE_SYSTEM);
    }

    public static final boolean getUseInlineScopesNumbers(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_INLINE_SCOPES_NUMBERS);
    }

    public static final boolean getUseJavac(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_JAVAC);
    }

    public static final boolean getUseOldInlineClassesManglingScheme(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_OLD_INLINE_CLASSES_MANGLING_SCHEME);
    }

    public static final boolean getUsePsiClassFilesReading(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_PSI_CLASS_FILES_READING);
    }

    public static final boolean getUseTypeTable(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_TYPE_TABLE);
    }

    public static final boolean getValidateBytecode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JVMConfigurationKeys.VALIDATE_BYTECODE);
    }

    public static final JvmWhenGenerationScheme getWhenGenerationScheme(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (JvmWhenGenerationScheme) compilerConfiguration.get(JVMConfigurationKeys.WHEN_GENERATION_SCHEME);
    }

    public static final void setAbiStability(CompilerConfiguration compilerConfiguration, JvmAbiStability jvmAbiStability) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JvmAbiStability> compilerConfigurationKey = JVMConfigurationKeys.ABI_STABILITY;
        if (jvmAbiStability != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmAbiStability);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setAdditionalJavaModules(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.ADDITIONAL_JAVA_MODULES, list);
    }

    public static final void setAssertionsMode(CompilerConfiguration compilerConfiguration, JVMAssertionsMode jVMAssertionsMode) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JVMAssertionsMode> compilerConfigurationKey = JVMConfigurationKeys.ASSERTIONS_MODE;
        if (jVMAssertionsMode != null) {
            compilerConfiguration.put(compilerConfigurationKey, jVMAssertionsMode);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setCompileJava(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.COMPILE_JAVA, Boolean.valueOf(z));
    }

    public static final void setDisableCallAssertions(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_CALL_ASSERTIONS, Boolean.valueOf(z));
    }

    public static final void setDisableOptimization(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_OPTIMIZATION, Boolean.valueOf(z));
    }

    public static final void setDisableParamAssertions(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_PARAM_ASSERTIONS, Boolean.valueOf(z));
    }

    public static final void setDisableReceiverAssertions(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_RECEIVER_ASSERTIONS, Boolean.valueOf(z));
    }

    public static final void setDisableStandardScriptDefinition(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_STANDARD_SCRIPT_DEFINITION, Boolean.valueOf(z));
    }

    public static final void setDoNotClearBindingContext(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.DO_NOT_CLEAR_BINDING_CONTEXT, Boolean.valueOf(z));
    }

    public static final void setEmitJvmTypeAnnotations(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.EMIT_JVM_TYPE_ANNOTATIONS, Boolean.valueOf(z));
    }

    public static final void setEnableDebugMode(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.ENABLE_DEBUG_MODE, Boolean.valueOf(z));
    }

    public static final void setEnableJvmPreview(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.ENABLE_JVM_PREVIEW, Boolean.valueOf(z));
    }

    public static final void setEnhancedCoroutinesDebugging(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.ENHANCED_COROUTINES_DEBUGGING, Boolean.valueOf(z));
    }

    public static final void setExpressionToEvaluate(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JVMConfigurationKeys.EXPRESSION_TO_EVALUATE, str);
    }

    public static final void setFriendPaths(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.FRIEND_PATHS, list);
    }

    public static final void setIgnoredAnnotationsForBridges(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.IGNORED_ANNOTATIONS_FOR_BRIDGES, list);
    }

    public static final void setIncludeRuntime(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.INCLUDE_RUNTIME, Boolean.valueOf(z));
    }

    public static final void setIncrementalCompilationComponents(CompilerConfiguration compilerConfiguration, IncrementalCompilationComponents incrementalCompilationComponents) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS, incrementalCompilationComponents);
    }

    public static final void setJdkHome(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = JVMConfigurationKeys.JDK_HOME;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setJdkRelease(CompilerConfiguration compilerConfiguration, Integer num) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<Integer> compilerConfigurationKey = JVMConfigurationKeys.JDK_RELEASE;
        if (num != null) {
            compilerConfiguration.put(compilerConfigurationKey, num);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setJvmTarget(CompilerConfiguration compilerConfiguration, JvmTarget jvmTarget) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JvmTarget> compilerConfigurationKey = JVMConfigurationKeys.JVM_TARGET;
        if (jvmTarget != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmTarget);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setKlibPaths(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.KLIB_PATHS, list);
    }

    public static final void setLambdas(CompilerConfiguration compilerConfiguration, JvmClosureGenerationScheme jvmClosureGenerationScheme) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JvmClosureGenerationScheme> compilerConfigurationKey = JVMConfigurationKeys.LAMBDAS;
        if (jvmClosureGenerationScheme != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmClosureGenerationScheme);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setLinkViaSignatures(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.LINK_VIA_SIGNATURES, Boolean.valueOf(z));
    }

    public static final void setModuleXmlFile(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = JVMConfigurationKeys.MODULE_XML_FILE;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setModules(CompilerConfiguration compilerConfiguration, List<? extends Module> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.MODULES, list);
    }

    public static final void setNoJdk(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.NO_JDK, Boolean.valueOf(z));
    }

    public static final void setNoNewJavaAnnotationTargets(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.NO_NEW_JAVA_ANNOTATION_TARGETS, Boolean.valueOf(z));
    }

    public static final void setNoReflect(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.NO_REFLECT, Boolean.valueOf(z));
    }

    public static final void setNoResetJarTimestamps(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.NO_RESET_JAR_TIMESTAMPS, Boolean.valueOf(z));
    }

    public static final void setNoSourceDebugExtension(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.NO_SOURCE_DEBUG_EXTENSION, Boolean.valueOf(z));
    }

    public static final void setNoUnifiedNullChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.NO_UNIFIED_NULL_CHECKS, Boolean.valueOf(z));
    }

    public static final void setOutputDirectory(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = JVMConfigurationKeys.OUTPUT_DIRECTORY;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setOutputJar(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = JVMConfigurationKeys.OUTPUT_JAR;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setParametersMetadata(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.PARAMETERS_METADATA, Boolean.valueOf(z));
    }

    public static final void setSamConversions(CompilerConfiguration compilerConfiguration, JvmClosureGenerationScheme jvmClosureGenerationScheme) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JvmClosureGenerationScheme> compilerConfigurationKey = JVMConfigurationKeys.SAM_CONVERSIONS;
        if (jvmClosureGenerationScheme != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmClosureGenerationScheme);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setSkipBodies(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.SKIP_BODIES, Boolean.valueOf(z));
    }

    public static final void setStringConcat(CompilerConfiguration compilerConfiguration, JvmStringConcat jvmStringConcat) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JvmStringConcat> compilerConfigurationKey = JVMConfigurationKeys.STRING_CONCAT;
        if (jvmStringConcat != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmStringConcat);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setUseFastJarFileSystem(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.USE_FAST_JAR_FILE_SYSTEM, Boolean.valueOf(z));
    }

    public static final void setUseInlineScopesNumbers(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.USE_INLINE_SCOPES_NUMBERS, Boolean.valueOf(z));
    }

    public static final void setUseJavac(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.USE_JAVAC, Boolean.valueOf(z));
    }

    public static final void setUseOldInlineClassesManglingScheme(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.USE_OLD_INLINE_CLASSES_MANGLING_SCHEME, Boolean.valueOf(z));
    }

    public static final void setUsePsiClassFilesReading(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.USE_PSI_CLASS_FILES_READING, Boolean.valueOf(z));
    }

    public static final void setUseTypeTable(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.USE_TYPE_TABLE, Boolean.valueOf(z));
    }

    public static final void setValidateBytecode(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.VALIDATE_BYTECODE, Boolean.valueOf(z));
    }

    public static final void setWhenGenerationScheme(CompilerConfiguration compilerConfiguration, JvmWhenGenerationScheme jvmWhenGenerationScheme) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<JvmWhenGenerationScheme> compilerConfigurationKey = JVMConfigurationKeys.WHEN_GENERATION_SCHEME;
        if (jvmWhenGenerationScheme != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmWhenGenerationScheme);
        } else {
            w01.a("nullable values are not allowed");
        }
    }
}
