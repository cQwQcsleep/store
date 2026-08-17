package org.jetbrains.kotlin.js.config;

import java.io.File;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.incremental.js.IncrementalNextRoundChecker;
import org.jetbrains.kotlin.incremental.js.IncrementalResultsConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010$\n\u0002\bH\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\",\u0010\t\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\"4\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u000e*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\b0\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"4\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u000e*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\b0\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013\"(\u0010\u0017\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007\"(\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0005\"\u0004\b\u001c\u0010\u0007\",\u0010\u001e\u001a\u0004\u0018\u00010\u001d*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u001d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\",\u0010$\u001a\u0004\u0018\u00010#*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010#8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(\",\u0010)\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010\u000b\"\u0004\b+\u0010\r\"4\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0\u000e*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\b0\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0011\"\u0004\b.\u0010\u0013\",\u00100\u001a\u0004\u0018\u00010/*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010/8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104\",\u00106\u001a\u0004\u0018\u000105*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u0001058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:\"(\u0010;\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b<\u0010\u0005\"\u0004\b=\u0010\u0007\",\u0010?\u001a\u0004\u0018\u00010>*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010>8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C\"(\u0010D\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u0010\u0005\"\u0004\bF\u0010\u0007\",\u0010H\u001a\u0004\u0018\u00010G*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010G8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010L\",\u0010N\u001a\u0004\u0018\u00010M*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010M8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010R\",\u0010T\u001a\u0004\u0018\u00010S*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010S8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bU\u0010V\"\u0004\bW\u0010X\"(\u0010Y\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bZ\u0010\u0005\"\u0004\b[\u0010\u0007\"(\u0010\\\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b]\u0010\u0005\"\u0004\b^\u0010\u0007\"(\u0010_\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u0010\u0005\"\u0004\ba\u0010\u0007\"(\u0010b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bc\u0010\u0005\"\u0004\bd\u0010\u0007\"(\u0010e\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bf\u0010\u0005\"\u0004\bg\u0010\u0007\",\u0010h\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\r\"(\u0010k\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bl\u0010\u0005\"\u0004\bm\u0010\u0007\"(\u0010n\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bo\u0010\u0005\"\u0004\bp\u0010\u0007\"(\u0010q\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\br\u0010\u0005\"\u0004\bs\u0010\u0007\"(\u0010t\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bu\u0010\u0005\"\u0004\bv\u0010\u0007\"(\u0010w\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bx\u0010\u0005\"\u0004\by\u0010\u0007\"@\u0010{\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0z*\u00020\u00032\u0012\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0z8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b|\u0010}\"\u0004\b~\u0010\u007f\"+\u0010\u0080\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0081\u0001\u0010\u0005\"\u0005\b\u0082\u0001\u0010\u0007\"/\u0010\u0083\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0084\u0001\u0010\u000b\"\u0005\b\u0085\u0001\u0010\r\"+\u0010\u0086\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0087\u0001\u0010\u0005\"\u0005\b\u0088\u0001\u0010\u0007\"+\u0010\u0089\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u008a\u0001\u0010\u0005\"\u0005\b\u008b\u0001\u0010\u0007\"+\u0010\u008c\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u008d\u0001\u0010\u0005\"\u0005\b\u008e\u0001\u0010\u0007\"+\u0010\u008f\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0090\u0001\u0010\u0005\"\u0005\b\u0091\u0001\u0010\u0007\"+\u0010\u0092\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0093\u0001\u0010\u0005\"\u0005\b\u0094\u0001\u0010\u0007\"+\u0010\u0095\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0096\u0001\u0010\u0005\"\u0005\b\u0097\u0001\u0010\u0007\"/\u0010\u0098\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0099\u0001\u0010\u000b\"\u0005\b\u009a\u0001\u0010\r\"+\u0010\u009b\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u009c\u0001\u0010\u0005\"\u0005\b\u009d\u0001\u0010\u0007\"+\u0010\u009e\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u009f\u0001\u0010\u0005\"\u0005\b \u0001\u0010\u0007\"/\u0010¡\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¢\u0001\u0010\u000b\"\u0005\b£\u0001\u0010\r\"7\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020\b0\u000e*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\b0\u000e8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¥\u0001\u0010\u0011\"\u0005\b¦\u0001\u0010\u0013\"+\u0010§\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¨\u0001\u0010\u0005\"\u0005\b©\u0001\u0010\u0007\"/\u0010ª\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b«\u0001\u0010\u000b\"\u0005\b¬\u0001\u0010\r\"+\u0010\u00ad\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b®\u0001\u0010\u0005\"\u0005\b¯\u0001\u0010\u0007\"/\u0010°\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b±\u0001\u0010\u000b\"\u0005\b²\u0001\u0010\r\"+\u0010³\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b´\u0001\u0010\u0005\"\u0005\bµ\u0001\u0010\u0007\"/\u0010¶\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b·\u0001\u0010\u000b\"\u0005\b¸\u0001\u0010\r\"/\u0010¹\u0001\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\bº\u0001\u0010\u000b\"\u0005\b»\u0001\u0010\r\"+\u0010¼\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b½\u0001\u0010\u0005\"\u0005\b¾\u0001\u0010\u0007\"+\u0010¿\u0001\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\bÀ\u0001\u0010\u0005\"\u0005\bÁ\u0001\u0010\u0007¨\u0006Â\u0001"}, d2 = {"value", Argument.Delimiters.none, "wasmCompilation", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getWasmCompilation", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Z", "setWasmCompilation", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", Argument.Delimiters.none, "outputName", "getOutputName", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/String;", "setOutputName", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)V", Argument.Delimiters.none, "libraries", "getLibraries", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", "setLibraries", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)V", "friendLibraries", "getFriendLibraries", "setFriendLibraries", "sourceMap", "getSourceMap", "setSourceMap", "useDebuggerCustomFormatters", "getUseDebuggerCustomFormatters", "setUseDebuggerCustomFormatters", "Lorg/jetbrains/kotlin/js/config/WebArtifactConfiguration;", "artifactConfiguration", "getArtifactConfiguration", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/js/config/WebArtifactConfiguration;", "setArtifactConfiguration", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/js/config/WebArtifactConfiguration;)V", "Ljava/io/File;", ModuleXmlParser.OUTPUT_DIR, "getOutputDir", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/io/File;", "setOutputDir", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/io/File;)V", "sourceMapPrefix", "getSourceMapPrefix", "setSourceMapPrefix", "sourceMapSourceRoots", "getSourceMapSourceRoots", "setSourceMapSourceRoots", "Lorg/jetbrains/kotlin/js/config/SourceMapSourceEmbedding;", "sourceMapEmbedSources", "getSourceMapEmbedSources", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/js/config/SourceMapSourceEmbedding;", "setSourceMapEmbedSources", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/js/config/SourceMapSourceEmbedding;)V", "Lorg/jetbrains/kotlin/js/config/SourceMapNamesPolicy;", "sourcemapNamesPolicy", "getSourcemapNamesPolicy", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/js/config/SourceMapNamesPolicy;", "setSourcemapNamesPolicy", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/js/config/SourceMapNamesPolicy;)V", "sourceMapIncludeMappingsFromUnavailableFiles", "getSourceMapIncludeMappingsFromUnavailableFiles", "setSourceMapIncludeMappingsFromUnavailableFiles", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "moduleKind", "getModuleKind", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/js/config/ModuleKind;", "setModuleKind", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/js/config/ModuleKind;)V", "jsIncrementalCompilationEnabled", "getJsIncrementalCompilationEnabled", "setJsIncrementalCompilationEnabled", "Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "incrementalDataProvider", "getIncrementalDataProvider", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "setIncrementalDataProvider", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;)V", "Lorg/jetbrains/kotlin/incremental/js/IncrementalResultsConsumer;", "incrementalResultsConsumer", "getIncrementalResultsConsumer", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/js/IncrementalResultsConsumer;", "setIncrementalResultsConsumer", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/js/IncrementalResultsConsumer;)V", "Lorg/jetbrains/kotlin/incremental/js/IncrementalNextRoundChecker;", "incrementalNextRoundChecker", "getIncrementalNextRoundChecker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/js/IncrementalNextRoundChecker;", "setIncrementalNextRoundChecker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/js/IncrementalNextRoundChecker;)V", "friendPathsDisabled", "getFriendPathsDisabled", "setFriendPathsDisabled", "metadataOnly", "getMetadataOnly", "setMetadataOnly", "developerMode", "getDeveloperMode", "setDeveloperMode", "generateCommentsWithFilePath", "getGenerateCommentsWithFilePath", "setGenerateCommentsWithFilePath", "generatePolyfills", "getGeneratePolyfills", "setGeneratePolyfills", "definePlatformMainFunctionArguments", "getDefinePlatformMainFunctionArguments", "setDefinePlatformMainFunctionArguments", "generateDts", "getGenerateDts", "setGenerateDts", "compileSuspendAsJsGenerator", "getCompileSuspendAsJsGenerator", "setCompileSuspendAsJsGenerator", "compileLambdasAsEs6ArrowFunctions", "getCompileLambdasAsEs6ArrowFunctions", "setCompileLambdasAsEs6ArrowFunctions", "compileLongAsBigint", "getCompileLongAsBigint", "setCompileLongAsBigint", "generateRegionComments", "getGenerateRegionComments", "setGenerateRegionComments", Argument.Delimiters.none, "filePathsPrefixMap", "getFilePathsPrefixMap", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/Map;", "setFilePathsPrefixMap", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/Map;)V", "printReachabilityInfo", "getPrintReachabilityInfo", "setPrintReachabilityInfo", "dumpReachabilityInfoToFile", "getDumpReachabilityInfoToFile", "setDumpReachabilityInfoToFile", "fakeOverrideValidator", "getFakeOverrideValidator", "setFakeOverrideValidator", "propertyLazyInitialization", "getPropertyLazyInitialization", "setPropertyLazyInitialization", "generateInlineAnonymousFunctions", "getGenerateInlineAnonymousFunctions", "setGenerateInlineAnonymousFunctions", "generateStrictImplicitExport", "getGenerateStrictImplicitExport", "setGenerateStrictImplicitExport", "optimizeGeneratedJs", "getOptimizeGeneratedJs", "setOptimizeGeneratedJs", "useEs6Classes", "getUseEs6Classes", "setUseEs6Classes", "includes", "getIncludes", "setIncludes", "produceKlibFile", "getProduceKlibFile", "setProduceKlibFile", "produceKlibDir", "getProduceKlibDir", "setProduceKlibDir", "perModuleOutputName", "getPerModuleOutputName", "setPerModuleOutputName", "keep", "getKeep", "setKeep", "dce", "getDce", "setDce", "dceRuntimeDiagnostic", "getDceRuntimeDiagnostic", "setDceRuntimeDiagnostic", "safeExternalBoolean", "getSafeExternalBoolean", "setSafeExternalBoolean", "safeExternalBooleanDiagnostic", "getSafeExternalBooleanDiagnostic", "setSafeExternalBooleanDiagnostic", "minimizedMemberNames", "getMinimizedMemberNames", "setMinimizedMemberNames", "callMainMode", "getCallMainMode", "setCallMainMode", "icCacheDirectory", "getIcCacheDirectory", "setIcCacheDirectory", "icCacheReadOnly", "getIcCacheReadOnly", "setIcCacheReadOnly", "preserveIcOrder", "getPreserveIcOrder", "setPreserveIcOrder", "org.jetbrains.kotlin:js.config"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JSConfigurationKeysKt {
    public static final WebArtifactConfiguration getArtifactConfiguration(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (WebArtifactConfiguration) compilerConfiguration.get(JSConfigurationKeys.ARTIFACT_CONFIGURATION);
    }

    public static final String getCallMainMode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.CALL_MAIN_MODE);
    }

    public static final boolean getCompileLambdasAsEs6ArrowFunctions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.COMPILE_LAMBDAS_AS_ES6_ARROW_FUNCTIONS);
    }

    public static final boolean getCompileLongAsBigint(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.COMPILE_LONG_AS_BIGINT);
    }

    public static final boolean getCompileSuspendAsJsGenerator(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.COMPILE_SUSPEND_AS_JS_GENERATOR);
    }

    public static final boolean getDce(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.DCE);
    }

    public static final String getDceRuntimeDiagnostic(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.DCE_RUNTIME_DIAGNOSTIC);
    }

    public static final String getDefinePlatformMainFunctionArguments(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.DEFINE_PLATFORM_MAIN_FUNCTION_ARGUMENTS);
    }

    public static final boolean getDeveloperMode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.DEVELOPER_MODE);
    }

    public static final String getDumpReachabilityInfoToFile(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.DUMP_REACHABILITY_INFO_TO_FILE);
    }

    public static final boolean getFakeOverrideValidator(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.FAKE_OVERRIDE_VALIDATOR);
    }

    public static final Map<String, String> getFilePathsPrefixMap(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getMap(JSConfigurationKeys.FILE_PATHS_PREFIX_MAP);
    }

    public static final List<String> getFriendLibraries(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JSConfigurationKeys.FRIEND_LIBRARIES);
    }

    public static final boolean getFriendPathsDisabled(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.FRIEND_PATHS_DISABLED);
    }

    public static final boolean getGenerateCommentsWithFilePath(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.GENERATE_COMMENTS_WITH_FILE_PATH);
    }

    public static final boolean getGenerateDts(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.GENERATE_DTS);
    }

    public static final boolean getGenerateInlineAnonymousFunctions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.GENERATE_INLINE_ANONYMOUS_FUNCTIONS);
    }

    public static final boolean getGeneratePolyfills(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.GENERATE_POLYFILLS);
    }

    public static final boolean getGenerateRegionComments(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.GENERATE_REGION_COMMENTS);
    }

    public static final boolean getGenerateStrictImplicitExport(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.GENERATE_STRICT_IMPLICIT_EXPORT);
    }

    public static final String getIcCacheDirectory(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.IC_CACHE_DIRECTORY);
    }

    public static final boolean getIcCacheReadOnly(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.IC_CACHE_READ_ONLY);
    }

    public static final String getIncludes(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.INCLUDES);
    }

    public static final IncrementalDataProvider getIncrementalDataProvider(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (IncrementalDataProvider) compilerConfiguration.get(JSConfigurationKeys.INCREMENTAL_DATA_PROVIDER);
    }

    public static final IncrementalNextRoundChecker getIncrementalNextRoundChecker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (IncrementalNextRoundChecker) compilerConfiguration.get(JSConfigurationKeys.INCREMENTAL_NEXT_ROUND_CHECKER);
    }

    public static final IncrementalResultsConsumer getIncrementalResultsConsumer(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (IncrementalResultsConsumer) compilerConfiguration.get(JSConfigurationKeys.INCREMENTAL_RESULTS_CONSUMER);
    }

    public static final boolean getJsIncrementalCompilationEnabled(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.JS_INCREMENTAL_COMPILATION_ENABLED);
    }

    public static final List<String> getKeep(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JSConfigurationKeys.KEEP);
    }

    public static final List<String> getLibraries(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JSConfigurationKeys.LIBRARIES);
    }

    public static final boolean getMetadataOnly(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.METADATA_ONLY);
    }

    public static final boolean getMinimizedMemberNames(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.MINIMIZED_MEMBER_NAMES);
    }

    public static final ModuleKind getModuleKind(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (ModuleKind) compilerConfiguration.get(JSConfigurationKeys.MODULE_KIND);
    }

    public static final boolean getOptimizeGeneratedJs(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.OPTIMIZE_GENERATED_JS);
    }

    public static final File getOutputDir(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(JSConfigurationKeys.OUTPUT_DIR);
    }

    public static final String getOutputName(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.OUTPUT_NAME);
    }

    public static final String getPerModuleOutputName(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.PER_MODULE_OUTPUT_NAME);
    }

    public static final boolean getPreserveIcOrder(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.PRESERVE_IC_ORDER);
    }

    public static final boolean getPrintReachabilityInfo(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.PRINT_REACHABILITY_INFO);
    }

    public static final boolean getProduceKlibDir(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.PRODUCE_KLIB_DIR);
    }

    public static final boolean getProduceKlibFile(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.PRODUCE_KLIB_FILE);
    }

    public static final boolean getPropertyLazyInitialization(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.PROPERTY_LAZY_INITIALIZATION);
    }

    public static final boolean getSafeExternalBoolean(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.SAFE_EXTERNAL_BOOLEAN);
    }

    public static final String getSafeExternalBooleanDiagnostic(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.SAFE_EXTERNAL_BOOLEAN_DIAGNOSTIC);
    }

    public static final boolean getSourceMap(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.SOURCE_MAP);
    }

    public static final SourceMapSourceEmbedding getSourceMapEmbedSources(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (SourceMapSourceEmbedding) compilerConfiguration.get(JSConfigurationKeys.SOURCE_MAP_EMBED_SOURCES);
    }

    public static final boolean getSourceMapIncludeMappingsFromUnavailableFiles(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.SOURCE_MAP_INCLUDE_MAPPINGS_FROM_UNAVAILABLE_FILES);
    }

    public static final String getSourceMapPrefix(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(JSConfigurationKeys.SOURCE_MAP_PREFIX);
    }

    public static final List<String> getSourceMapSourceRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(JSConfigurationKeys.SOURCE_MAP_SOURCE_ROOTS);
    }

    public static final SourceMapNamesPolicy getSourcemapNamesPolicy(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (SourceMapNamesPolicy) compilerConfiguration.get(JSConfigurationKeys.SOURCEMAP_NAMES_POLICY);
    }

    public static final boolean getUseDebuggerCustomFormatters(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.USE_DEBUGGER_CUSTOM_FORMATTERS);
    }

    public static final boolean getUseEs6Classes(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.USE_ES6_CLASSES);
    }

    public static final boolean getWasmCompilation(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(JSConfigurationKeys.WASM_COMPILATION);
    }

    public static final void setArtifactConfiguration(CompilerConfiguration compilerConfiguration, WebArtifactConfiguration webArtifactConfiguration) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.ARTIFACT_CONFIGURATION;
        if (webArtifactConfiguration != null) {
            compilerConfiguration.put(compilerConfigurationKey, webArtifactConfiguration);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setCallMainMode(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.CALL_MAIN_MODE;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setCompileLambdasAsEs6ArrowFunctions(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.COMPILE_LAMBDAS_AS_ES6_ARROW_FUNCTIONS, Boolean.valueOf(z));
    }

    public static final void setCompileLongAsBigint(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.COMPILE_LONG_AS_BIGINT, Boolean.valueOf(z));
    }

    public static final void setCompileSuspendAsJsGenerator(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.COMPILE_SUSPEND_AS_JS_GENERATOR, Boolean.valueOf(z));
    }

    public static final void setDce(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.DCE, Boolean.valueOf(z));
    }

    public static final void setDceRuntimeDiagnostic(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.DCE_RUNTIME_DIAGNOSTIC;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setDefinePlatformMainFunctionArguments(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.DEFINE_PLATFORM_MAIN_FUNCTION_ARGUMENTS;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setDeveloperMode(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.DEVELOPER_MODE, Boolean.valueOf(z));
    }

    public static final void setDumpReachabilityInfoToFile(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JSConfigurationKeys.DUMP_REACHABILITY_INFO_TO_FILE, str);
    }

    public static final void setFakeOverrideValidator(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.FAKE_OVERRIDE_VALIDATOR, Boolean.valueOf(z));
    }

    public static final void setFilePathsPrefixMap(CompilerConfiguration compilerConfiguration, Map<String, String> map) {
        compilerConfiguration.getClass();
        map.getClass();
        compilerConfiguration.put(JSConfigurationKeys.FILE_PATHS_PREFIX_MAP, map);
    }

    public static final void setFriendLibraries(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JSConfigurationKeys.FRIEND_LIBRARIES, list);
    }

    public static final void setFriendPathsDisabled(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.FRIEND_PATHS_DISABLED, Boolean.valueOf(z));
    }

    public static final void setGenerateCommentsWithFilePath(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.GENERATE_COMMENTS_WITH_FILE_PATH, Boolean.valueOf(z));
    }

    public static final void setGenerateDts(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.GENERATE_DTS, Boolean.valueOf(z));
    }

    public static final void setGenerateInlineAnonymousFunctions(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.GENERATE_INLINE_ANONYMOUS_FUNCTIONS, Boolean.valueOf(z));
    }

    public static final void setGeneratePolyfills(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.GENERATE_POLYFILLS, Boolean.valueOf(z));
    }

    public static final void setGenerateRegionComments(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.GENERATE_REGION_COMMENTS, Boolean.valueOf(z));
    }

    public static final void setGenerateStrictImplicitExport(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.GENERATE_STRICT_IMPLICIT_EXPORT, Boolean.valueOf(z));
    }

    public static final void setIcCacheDirectory(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JSConfigurationKeys.IC_CACHE_DIRECTORY, str);
    }

    public static final void setIcCacheReadOnly(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.IC_CACHE_READ_ONLY, Boolean.valueOf(z));
    }

    public static final void setIncludes(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.INCLUDES;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setIncrementalDataProvider(CompilerConfiguration compilerConfiguration, IncrementalDataProvider incrementalDataProvider) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JSConfigurationKeys.INCREMENTAL_DATA_PROVIDER, incrementalDataProvider);
    }

    public static final void setIncrementalNextRoundChecker(CompilerConfiguration compilerConfiguration, IncrementalNextRoundChecker incrementalNextRoundChecker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JSConfigurationKeys.INCREMENTAL_NEXT_ROUND_CHECKER, incrementalNextRoundChecker);
    }

    public static final void setIncrementalResultsConsumer(CompilerConfiguration compilerConfiguration, IncrementalResultsConsumer incrementalResultsConsumer) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JSConfigurationKeys.INCREMENTAL_RESULTS_CONSUMER, incrementalResultsConsumer);
    }

    public static final void setJsIncrementalCompilationEnabled(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.JS_INCREMENTAL_COMPILATION_ENABLED, Boolean.valueOf(z));
    }

    public static final void setKeep(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JSConfigurationKeys.KEEP, list);
    }

    public static final void setLibraries(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JSConfigurationKeys.LIBRARIES, list);
    }

    public static final void setMetadataOnly(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.METADATA_ONLY, Boolean.valueOf(z));
    }

    public static final void setMinimizedMemberNames(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.MINIMIZED_MEMBER_NAMES, Boolean.valueOf(z));
    }

    public static final void setModuleKind(CompilerConfiguration compilerConfiguration, ModuleKind moduleKind) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.MODULE_KIND;
        if (moduleKind != null) {
            compilerConfiguration.put(compilerConfigurationKey, moduleKind);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setOptimizeGeneratedJs(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.OPTIMIZE_GENERATED_JS, Boolean.valueOf(z));
    }

    public static final void setOutputDir(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.OUTPUT_DIR;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setOutputName(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.OUTPUT_NAME;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setPerModuleOutputName(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(JSConfigurationKeys.PER_MODULE_OUTPUT_NAME, str);
    }

    public static final void setPreserveIcOrder(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.PRESERVE_IC_ORDER, Boolean.valueOf(z));
    }

    public static final void setPrintReachabilityInfo(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.PRINT_REACHABILITY_INFO, Boolean.valueOf(z));
    }

    public static final void setProduceKlibDir(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.PRODUCE_KLIB_DIR, Boolean.valueOf(z));
    }

    public static final void setProduceKlibFile(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.PRODUCE_KLIB_FILE, Boolean.valueOf(z));
    }

    public static final void setPropertyLazyInitialization(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.PROPERTY_LAZY_INITIALIZATION, Boolean.valueOf(z));
    }

    public static final void setSafeExternalBoolean(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.SAFE_EXTERNAL_BOOLEAN, Boolean.valueOf(z));
    }

    public static final void setSafeExternalBooleanDiagnostic(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.SAFE_EXTERNAL_BOOLEAN_DIAGNOSTIC;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setSourceMap(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.SOURCE_MAP, Boolean.valueOf(z));
    }

    public static final void setSourceMapEmbedSources(CompilerConfiguration compilerConfiguration, SourceMapSourceEmbedding sourceMapSourceEmbedding) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.SOURCE_MAP_EMBED_SOURCES;
        if (sourceMapSourceEmbedding != null) {
            compilerConfiguration.put(compilerConfigurationKey, sourceMapSourceEmbedding);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setSourceMapIncludeMappingsFromUnavailableFiles(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.SOURCE_MAP_INCLUDE_MAPPINGS_FROM_UNAVAILABLE_FILES, Boolean.valueOf(z));
    }

    public static final void setSourceMapPrefix(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.SOURCE_MAP_PREFIX;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setSourceMapSourceRoots(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(JSConfigurationKeys.SOURCE_MAP_SOURCE_ROOTS, list);
    }

    public static final void setSourcemapNamesPolicy(CompilerConfiguration compilerConfiguration, SourceMapNamesPolicy sourceMapNamesPolicy) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey compilerConfigurationKey = JSConfigurationKeys.SOURCEMAP_NAMES_POLICY;
        if (sourceMapNamesPolicy != null) {
            compilerConfiguration.put(compilerConfigurationKey, sourceMapNamesPolicy);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setUseDebuggerCustomFormatters(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.USE_DEBUGGER_CUSTOM_FORMATTERS, Boolean.valueOf(z));
    }

    public static final void setUseEs6Classes(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.USE_ES6_CLASSES, Boolean.valueOf(z));
    }

    public static final void setWasmCompilation(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(JSConfigurationKeys.WASM_COMPILATION, Boolean.valueOf(z));
    }
}
