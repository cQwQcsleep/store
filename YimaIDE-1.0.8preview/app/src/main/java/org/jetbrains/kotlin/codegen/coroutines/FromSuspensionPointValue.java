package org.jetbrains.kotlin.codegen.coroutines;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0096\u0082\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/FromSuspensionPointValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FromSuspensionPointValue extends BasicValue {
    public static final FromSuspensionPointValue INSTANCE = new FromSuspensionPointValue();

    private FromSuspensionPointValue() {
        super(AsmTypes.OBJECT_TYPE);
    }

    public boolean equals(Object other) {
        return other instanceof FromSuspensionPointValue;
    }
}
