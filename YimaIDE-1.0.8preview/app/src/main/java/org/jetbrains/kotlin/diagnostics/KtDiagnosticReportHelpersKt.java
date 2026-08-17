package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a0\u0010\f\u001a\u00020\u0001*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u001a9\u0010\f\u001a\u00020\u0001*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0012\u001aI\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00130\u00142\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0016\u001aM\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00130\u00142\u0006\u0010\u0015\u001a\u0002H\u00132\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0017\u001a]\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00180\u00192\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u001b\u001aa\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00180\u00192\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001c\u001aq\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010 \u001au\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010!\u001a\u0085\u0001\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d\"\u0004\b\u0003\u0010\"*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\"0#2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\u0006\u0010$\u001a\u0002H\"2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010%\u001a\u0089\u0001\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d\"\u0004\b\u0003\u0010\"*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\"0#2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\u0006\u0010$\u001a\u0002H\"2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010&\u001a\f\u0010'\u001a\u00020\u000e*\u0004\u0018\u00010\u000e\u001a0\u0010\f\u001a\u00020\u0001*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020(2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u001a9\u0010\f\u001a\u00020\u0001*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020(2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010)\u001aI\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00130*2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010+\u001aM\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00130*2\u0006\u0010\u0015\u001a\u0002H\u00132\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010,\u001a]\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00180-2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010.\u001aa\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00180-2\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010/\u001aq\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d002\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u00101\u001au\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d002\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u00102\u001a\u0085\u0001\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d\"\u0004\b\u0003\u0010\"*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\"032\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\u0006\u0010$\u001a\u0002H\"2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u00104\u001a\u0089\u0001\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u001d\"\u0004\b\u0003\u0010\"*\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\"032\u0006\u0010\u0015\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u0002H\u00182\u0006\u0010\u001f\u001a\u0002H\u001d2\u0006\u0010$\u001a\u0002H\"2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u00105\u001a+\u00106\u001a\u0002H7\"\b\b\u0000\u00107*\u000208*\b\u0012\u0004\u0012\u0002H709R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010:¨\u0006;"}, d2 = {"report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;)V", "reportOn", "source", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "a", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "B", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "b", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "C", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "c", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "D", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "d", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "requireNotNull", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation0;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation0;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation3;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation3;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation3;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation4;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation4;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation4;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)V", "chooseFactory", "F", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticReportHelpersKt {
    public static final <F extends KtDiagnosticFactoryN> F chooseFactory(DiagnosticContext diagnosticContext, KtDiagnosticFactoryForDeprecation<F> ktDiagnosticFactoryForDeprecation) {
        diagnosticContext.getClass();
        ktDiagnosticFactoryForDeprecation.getClass();
        return diagnosticContext.getLanguageVersionSettings().supportsFeature(ktDiagnosticFactoryForDeprecation.getDeprecatingFeature()) ? (F) ktDiagnosticFactoryForDeprecation.getErrorFactory() : (F) ktDiagnosticFactoryForDeprecation.getWarningFactory();
    }

    public static final void report(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktSourcelessDiagnosticFactory.getClass();
        str.getClass();
        diagnosticReporter.report(ktSourcelessDiagnosticFactory.create(str, compilerMessageSourceLocation, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void report$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
        if ((i & 8) != 0) {
            compilerMessageSourceLocation = null;
        }
        report(diagnosticContext, diagnosticReporter, ktSourcelessDiagnosticFactory, str, compilerMessageSourceLocation);
    }

    public static final <A, B, C, D> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation4<A, B, C, D> ktDiagnosticFactoryForDeprecation4, A a, B b, C c, D d, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation4.getClass();
        diagnosticContext.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory4) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation4), a, b, c, d, abstractSourceElementPositioningStrategy);
    }

    public static final AbstractKtSourceElement requireNotNull(AbstractKtSourceElement abstractKtSourceElement) {
        if (abstractKtSourceElement != null) {
            return abstractKtSourceElement;
        }
        w01.a("source must not be null");
        return null;
    }

    public static /* synthetic */ void reportOn$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory0 ktDiagnosticFactory0, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 8) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, ktDiagnosticFactory0, abstractSourceElementPositioningStrategy);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory1 ktDiagnosticFactory1, Object obj, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj2) {
        if ((i & 16) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory1<Object>) ktDiagnosticFactory1, obj, diagnosticContext, abstractSourceElementPositioningStrategy);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory1 ktDiagnosticFactory1, Object obj, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj2) {
        if ((i & 16) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory1<Object>) ktDiagnosticFactory1, obj, abstractSourceElementPositioningStrategy);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory2 ktDiagnosticFactory2, Object obj, Object obj2, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj3) {
        if ((i & 32) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory2<Object, Object>) ktDiagnosticFactory2, obj, obj2, diagnosticContext, abstractSourceElementPositioningStrategy);
    }

    public static final void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory0 ktDiagnosticFactory0, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactory0.getClass();
        diagnosticReporter.report(ktDiagnosticFactory0.on(requireNotNull(abstractKtSourceElement), abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory2 ktDiagnosticFactory2, Object obj, Object obj2, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj3) {
        if ((i & 32) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory2<Object, Object>) ktDiagnosticFactory2, obj, obj2, abstractSourceElementPositioningStrategy);
    }

    public static final <A> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory1<A> ktDiagnosticFactory1, A a, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactory1.getClass();
        diagnosticContext.getClass();
        diagnosticReporter.report(ktDiagnosticFactory1.on(requireNotNull(abstractKtSourceElement), a, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static final <A> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory1<A> ktDiagnosticFactory1, A a, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactory1.getClass();
        diagnosticReporter.report(ktDiagnosticFactory1.on(requireNotNull(abstractKtSourceElement), a, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static final <A, B> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory2<A, B> ktDiagnosticFactory2, A a, B b, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactory2.getClass();
        diagnosticContext.getClass();
        diagnosticReporter.report(ktDiagnosticFactory2.on(requireNotNull(abstractKtSourceElement), a, b, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory0 ktDiagnosticFactory0, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 8) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticReporter, abstractKtSourceElement, ktDiagnosticFactory0, diagnosticContext, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory2<A, B> ktDiagnosticFactory2, A a, B b, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactory2.getClass();
        diagnosticReporter.report(ktDiagnosticFactory2.on(requireNotNull(abstractKtSourceElement), a, b, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static final <A, B, C> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory3<A, B, C> ktDiagnosticFactory3, A a, B b, C c, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactory3.getClass();
        diagnosticContext.getClass();
        diagnosticReporter.report(ktDiagnosticFactory3.on(requireNotNull(abstractKtSourceElement), a, b, c, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation0 ktDiagnosticFactoryForDeprecation0, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 8) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticReporter, abstractKtSourceElement, ktDiagnosticFactoryForDeprecation0, diagnosticContext, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory3<A, B, C> ktDiagnosticFactory3, A a, B b, C c, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactory3.getClass();
        diagnosticReporter.report(ktDiagnosticFactory3.on(requireNotNull(abstractKtSourceElement), a, b, c, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation0 ktDiagnosticFactoryForDeprecation0, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 8) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, ktDiagnosticFactoryForDeprecation0, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C, D> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory4<A, B, C, D> ktDiagnosticFactory4, A a, B b, C c, D d, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactory4.getClass();
        diagnosticContext.getClass();
        diagnosticReporter.report(ktDiagnosticFactory4.on(requireNotNull(abstractKtSourceElement), a, b, c, d, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation1 ktDiagnosticFactoryForDeprecation1, Object obj, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj2) {
        if ((i & 16) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactoryForDeprecation1<Object>) ktDiagnosticFactoryForDeprecation1, obj, diagnosticContext, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C, D> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory4<A, B, C, D> ktDiagnosticFactory4, A a, B b, C c, D d, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactory4.getClass();
        diagnosticReporter.report(ktDiagnosticFactory4.on(requireNotNull(abstractKtSourceElement), a, b, c, d, abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation1 ktDiagnosticFactoryForDeprecation1, Object obj, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj2) {
        if ((i & 16) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactoryForDeprecation1<Object>) ktDiagnosticFactoryForDeprecation1, obj, abstractSourceElementPositioningStrategy);
    }

    public static final void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation0 ktDiagnosticFactoryForDeprecation0, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation0.getClass();
        diagnosticContext.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory0) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation0), abstractSourceElementPositioningStrategy);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation2 ktDiagnosticFactoryForDeprecation2, Object obj, Object obj2, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj3) {
        if ((i & 32) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactoryForDeprecation2<Object, Object>) ktDiagnosticFactoryForDeprecation2, obj, obj2, diagnosticContext, abstractSourceElementPositioningStrategy);
    }

    public static final void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation0 ktDiagnosticFactoryForDeprecation0, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation0.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory0) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation0), abstractSourceElementPositioningStrategy);
    }

    public static /* synthetic */ void reportOn$default(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation2 ktDiagnosticFactoryForDeprecation2, Object obj, Object obj2, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj3) {
        if ((i & 32) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactoryForDeprecation2<Object, Object>) ktDiagnosticFactoryForDeprecation2, obj, obj2, abstractSourceElementPositioningStrategy);
    }

    public static final <A> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation1<A> ktDiagnosticFactoryForDeprecation1, A a, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation1.getClass();
        diagnosticContext.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory1) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation1), a, abstractSourceElementPositioningStrategy);
    }

    public static final <A> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation1<A> ktDiagnosticFactoryForDeprecation1, A a, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation1.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory1) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation1), a, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation2<A, B> ktDiagnosticFactoryForDeprecation2, A a, B b, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation2.getClass();
        diagnosticContext.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory2) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation2), a, b, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation2<A, B> ktDiagnosticFactoryForDeprecation2, A a, B b, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation2.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory2) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation2), a, b, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C> void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation3<A, B, C> ktDiagnosticFactoryForDeprecation3, A a, B b, C c, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation3.getClass();
        diagnosticContext.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory3) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation3), a, b, c, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation3<A, B, C> ktDiagnosticFactoryForDeprecation3, A a, B b, C c, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation3.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory3) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation3), a, b, c, abstractSourceElementPositioningStrategy);
    }

    public static final void reportOn(DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactory0 ktDiagnosticFactory0, DiagnosticContext diagnosticContext, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticReporter.getClass();
        ktDiagnosticFactory0.getClass();
        diagnosticContext.getClass();
        diagnosticReporter.report(ktDiagnosticFactory0.on(requireNotNull(abstractKtSourceElement), abstractSourceElementPositioningStrategy, diagnosticContext), diagnosticContext);
    }

    public static final <A, B, C, D> void reportOn(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement, KtDiagnosticFactoryForDeprecation4<A, B, C, D> ktDiagnosticFactoryForDeprecation4, A a, B b, C c, D d, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        diagnosticContext.getClass();
        diagnosticReporter.getClass();
        ktDiagnosticFactoryForDeprecation4.getClass();
        reportOn(diagnosticContext, diagnosticReporter, abstractKtSourceElement, (KtDiagnosticFactory4) chooseFactory(diagnosticContext, ktDiagnosticFactoryForDeprecation4), a, b, c, d, abstractSourceElementPositioningStrategy);
    }
}
