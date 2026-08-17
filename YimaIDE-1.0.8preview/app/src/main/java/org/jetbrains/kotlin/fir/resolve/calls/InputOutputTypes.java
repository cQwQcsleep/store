package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/InputOutputTypes;", Argument.Delimiters.none, "inputTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "outputType", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getInputTypes", "()Ljava/util/List;", "getOutputType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class InputOutputTypes {
    private final List<ConeKotlinType> inputTypes;
    private final ConeKotlinType outputType;

    public InputOutputTypes(List<? extends ConeKotlinType> list, ConeKotlinType coneKotlinType) {
        list.getClass();
        coneKotlinType.getClass();
        this.inputTypes = list;
        this.outputType = coneKotlinType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ InputOutputTypes copy$default(InputOutputTypes inputOutputTypes, List list, ConeKotlinType coneKotlinType, int i, Object obj) {
        if ((i & 1) != 0) {
            list = inputOutputTypes.inputTypes;
        }
        if ((i & 2) != 0) {
            coneKotlinType = inputOutputTypes.outputType;
        }
        return inputOutputTypes.copy(list, coneKotlinType);
    }

    public final List<ConeKotlinType> component1() {
        return this.inputTypes;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeKotlinType getOutputType() {
        return this.outputType;
    }

    public final InputOutputTypes copy(List<? extends ConeKotlinType> inputTypes, ConeKotlinType outputType) {
        inputTypes.getClass();
        outputType.getClass();
        return new InputOutputTypes(inputTypes, outputType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InputOutputTypes)) {
            return false;
        }
        InputOutputTypes inputOutputTypes = (InputOutputTypes) other;
        return Intrinsics.areEqual(this.inputTypes, inputOutputTypes.inputTypes) && Intrinsics.areEqual(this.outputType, inputOutputTypes.outputType);
    }

    public final List<ConeKotlinType> getInputTypes() {
        return this.inputTypes;
    }

    public final ConeKotlinType getOutputType() {
        return this.outputType;
    }

    public int hashCode() {
        return (this.inputTypes.hashCode() * 31) + this.outputType.hashCode();
    }

    public String toString() {
        return "InputOutputTypes(inputTypes=" + this.inputTypes + ", outputType=" + this.outputType + ')';
    }
}
