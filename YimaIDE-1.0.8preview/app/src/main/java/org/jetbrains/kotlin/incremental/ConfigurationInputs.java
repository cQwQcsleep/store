package org.jetbrains.kotlin.incremental;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B+\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006HÆ\u0003J1\u0010\u000f\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0004HÖ\u0081\u0004R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ConfigurationInputs;", "Ljava/io/Serializable;", "icConfigurationInputsSnapshot", "", "", "compilerArgumentsInputsSnapshot", "", "<init>", "(Ljava/util/Map;Ljava/util/List;)V", "getIcConfigurationInputsSnapshot", "()Ljava/util/Map;", "getCompilerArgumentsInputsSnapshot", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ConfigurationInputs implements Serializable {
    private static final long serialVersionUID = 2;
    private final List<String> compilerArgumentsInputsSnapshot;
    private final Map<String, String> icConfigurationInputsSnapshot;

    public ConfigurationInputs(Map<String, String> map, List<String> list) {
        map.getClass();
        list.getClass();
        this.icConfigurationInputsSnapshot = map;
        this.compilerArgumentsInputsSnapshot = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConfigurationInputs copy$default(ConfigurationInputs configurationInputs, Map map, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            map = configurationInputs.icConfigurationInputsSnapshot;
        }
        if ((i & 2) != 0) {
            list = configurationInputs.compilerArgumentsInputsSnapshot;
        }
        return configurationInputs.copy(map, list);
    }

    public final Map<String, String> component1() {
        return this.icConfigurationInputsSnapshot;
    }

    public final List<String> component2() {
        return this.compilerArgumentsInputsSnapshot;
    }

    public final ConfigurationInputs copy(Map<String, String> icConfigurationInputsSnapshot, List<String> compilerArgumentsInputsSnapshot) {
        icConfigurationInputsSnapshot.getClass();
        compilerArgumentsInputsSnapshot.getClass();
        return new ConfigurationInputs(icConfigurationInputsSnapshot, compilerArgumentsInputsSnapshot);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigurationInputs)) {
            return false;
        }
        ConfigurationInputs configurationInputs = (ConfigurationInputs) other;
        return Intrinsics.areEqual(this.icConfigurationInputsSnapshot, configurationInputs.icConfigurationInputsSnapshot) && Intrinsics.areEqual(this.compilerArgumentsInputsSnapshot, configurationInputs.compilerArgumentsInputsSnapshot);
    }

    public final List<String> getCompilerArgumentsInputsSnapshot() {
        return this.compilerArgumentsInputsSnapshot;
    }

    public final Map<String, String> getIcConfigurationInputsSnapshot() {
        return this.icConfigurationInputsSnapshot;
    }

    public int hashCode() {
        return (this.icConfigurationInputsSnapshot.hashCode() * 31) + this.compilerArgumentsInputsSnapshot.hashCode();
    }

    public String toString() {
        return "ConfigurationInputs(icConfigurationInputsSnapshot=" + this.icConfigurationInputsSnapshot + ", compilerArgumentsInputsSnapshot=" + this.compilerArgumentsInputsSnapshot + ')';
    }
}
