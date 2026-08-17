package org.jetbrains.kotlin.cli.js;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilationMode;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.js.config.JsGenerationGranularity;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData;", Argument.Delimiters.none, "<init>", "()V", "Js", "Wasm", "Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData$Js;", "Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData$Wasm;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class IcCachesConfigurationData {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData$Js;", "Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData;", "granularity", "Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "<init>", "(Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;)V", "getGranularity", "()Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Js extends IcCachesConfigurationData {
        private final JsGenerationGranularity granularity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Js(JsGenerationGranularity jsGenerationGranularity) {
            super(null);
            jsGenerationGranularity.getClass();
            this.granularity = jsGenerationGranularity;
        }

        public static /* synthetic */ Js copy$default(Js js, JsGenerationGranularity jsGenerationGranularity, int i, Object obj) {
            if ((i & 1) != 0) {
                jsGenerationGranularity = js.granularity;
            }
            return js.copy(jsGenerationGranularity);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JsGenerationGranularity getGranularity() {
            return this.granularity;
        }

        public final Js copy(JsGenerationGranularity granularity) {
            granularity.getClass();
            return new Js(granularity);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Js) && this.granularity == ((Js) other).granularity;
        }

        public final JsGenerationGranularity getGranularity() {
            return this.granularity;
        }

        public int hashCode() {
            return this.granularity.hashCode();
        }

        public String toString() {
            return "Js(granularity=" + this.granularity + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData$Wasm;", "Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData;", "wasmDebug", Argument.Delimiters.none, "generateWat", "generateDebugInformation", "mode", "Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmCompilationMode;", "<init>", "(ZZZLorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmCompilationMode;)V", "getWasmDebug", "()Z", "getGenerateWat", "getGenerateDebugInformation", "getMode", "()Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmCompilationMode;", "component1", "component2", "component3", "component4", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Wasm extends IcCachesConfigurationData {
        private final boolean generateDebugInformation;
        private final boolean generateWat;
        private final WasmCompilationMode mode;
        private final boolean wasmDebug;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Wasm(boolean z, boolean z2, boolean z3, WasmCompilationMode wasmCompilationMode) {
            super(null);
            wasmCompilationMode.getClass();
            this.wasmDebug = z;
            this.generateWat = z2;
            this.generateDebugInformation = z3;
            this.mode = wasmCompilationMode;
        }

        public static /* synthetic */ Wasm copy$default(Wasm wasm, boolean z, boolean z2, boolean z3, WasmCompilationMode wasmCompilationMode, int i, Object obj) {
            if ((i & 1) != 0) {
                z = wasm.wasmDebug;
            }
            if ((i & 2) != 0) {
                z2 = wasm.generateWat;
            }
            if ((i & 4) != 0) {
                z3 = wasm.generateDebugInformation;
            }
            if ((i & 8) != 0) {
                wasmCompilationMode = wasm.mode;
            }
            return wasm.copy(z, z2, z3, wasmCompilationMode);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getWasmDebug() {
            return this.wasmDebug;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getGenerateWat() {
            return this.generateWat;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getGenerateDebugInformation() {
            return this.generateDebugInformation;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final WasmCompilationMode getMode() {
            return this.mode;
        }

        public final Wasm copy(boolean wasmDebug, boolean generateWat, boolean generateDebugInformation, WasmCompilationMode mode) {
            mode.getClass();
            return new Wasm(wasmDebug, generateWat, generateDebugInformation, mode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Wasm)) {
                return false;
            }
            Wasm wasm = (Wasm) other;
            return this.wasmDebug == wasm.wasmDebug && this.generateWat == wasm.generateWat && this.generateDebugInformation == wasm.generateDebugInformation && this.mode == wasm.mode;
        }

        public final boolean getGenerateDebugInformation() {
            return this.generateDebugInformation;
        }

        public final boolean getGenerateWat() {
            return this.generateWat;
        }

        public final WasmCompilationMode getMode() {
            return this.mode;
        }

        public final boolean getWasmDebug() {
            return this.wasmDebug;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.wasmDebug) * 31) + Boolean.hashCode(this.generateWat)) * 31) + Boolean.hashCode(this.generateDebugInformation)) * 31) + this.mode.hashCode();
        }

        public String toString() {
            return "Wasm(wasmDebug=" + this.wasmDebug + ", generateWat=" + this.generateWat + ", generateDebugInformation=" + this.generateDebugInformation + ", mode=" + this.mode + ')';
        }
    }

    public /* synthetic */ IcCachesConfigurationData(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private IcCachesConfigurationData() {
    }
}
