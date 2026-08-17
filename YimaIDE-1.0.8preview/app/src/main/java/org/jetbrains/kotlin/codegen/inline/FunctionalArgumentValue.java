package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgumentValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "functionalArgument", "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "basicValue", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;)V", "getFunctionalArgument", "()Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctionalArgumentValue extends BasicValue {
    private final FunctionalArgument functionalArgument;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionalArgumentValue(FunctionalArgument functionalArgument, BasicValue basicValue) {
        super(basicValue != null ? basicValue.getType() : null);
        functionalArgument.getClass();
        this.functionalArgument = functionalArgument;
    }

    public final FunctionalArgument getFunctionalArgument() {
        return this.functionalArgument;
    }

    public String toString() {
        return String.valueOf(this.functionalArgument);
    }
}
