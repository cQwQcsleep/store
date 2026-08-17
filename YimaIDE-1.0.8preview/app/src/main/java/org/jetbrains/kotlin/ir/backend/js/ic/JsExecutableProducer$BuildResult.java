package org.jetbrains.kotlin.ir.backend.js.ic;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.transformers.irToJs.CompilationOutputs;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/JsExecutableProducer$BuildResult;", Argument.Delimiters.none, "compilationOut", "Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;", "buildModules", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;Ljava/util/List;)V", "getCompilationOut", "()Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;", "getBuildModules", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JsExecutableProducer$BuildResult {
    private final List<String> buildModules;
    private final CompilationOutputs compilationOut;

    public JsExecutableProducer$BuildResult(CompilationOutputs compilationOutputs, List<String> list) {
        compilationOutputs.getClass();
        list.getClass();
        this.compilationOut = compilationOutputs;
        this.buildModules = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JsExecutableProducer$BuildResult copy$default(JsExecutableProducer$BuildResult jsExecutableProducer$BuildResult, CompilationOutputs compilationOutputs, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            compilationOutputs = jsExecutableProducer$BuildResult.compilationOut;
        }
        if ((i & 2) != 0) {
            list = jsExecutableProducer$BuildResult.buildModules;
        }
        return jsExecutableProducer$BuildResult.copy(compilationOutputs, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CompilationOutputs getCompilationOut() {
        return this.compilationOut;
    }

    public final List<String> component2() {
        return this.buildModules;
    }

    public final JsExecutableProducer$BuildResult copy(CompilationOutputs compilationOut, List<String> buildModules) {
        compilationOut.getClass();
        buildModules.getClass();
        return new JsExecutableProducer$BuildResult(compilationOut, buildModules);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsExecutableProducer$BuildResult)) {
            return false;
        }
        JsExecutableProducer$BuildResult jsExecutableProducer$BuildResult = (JsExecutableProducer$BuildResult) other;
        return Intrinsics.areEqual(this.compilationOut, jsExecutableProducer$BuildResult.compilationOut) && Intrinsics.areEqual(this.buildModules, jsExecutableProducer$BuildResult.buildModules);
    }

    public final List<String> getBuildModules() {
        return this.buildModules;
    }

    public final CompilationOutputs getCompilationOut() {
        return this.compilationOut;
    }

    public int hashCode() {
        return (this.compilationOut.hashCode() * 31) + this.buildModules.hashCode();
    }

    public String toString() {
        return "BuildResult(compilationOut=" + this.compilationOut + ", buildModules=" + this.buildModules + ')';
    }
}
