package org.jetbrains.kotlin.codegen.optimization.nullCheck;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0082\u0004J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/NotNullBasicValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;)V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NotNullBasicValue extends StrictBasicValue {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final NotNullBasicValue NOT_NULL_REFERENCE_VALUE = new NotNullBasicValue(StrictBasicValue.REFERENCE_VALUE.getType());

    public NotNullBasicValue(Type type) {
        super(type);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public boolean equals(Object other) {
        return (other instanceof NotNullBasicValue) && Intrinsics.areEqual(((NotNullBasicValue) other).getType(), getType());
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public int hashCode() {
        return 0;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/NotNullBasicValue$Companion;", Argument.Delimiters.none, "<init>", "()V", "NOT_NULL_REFERENCE_VALUE", "Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/NotNullBasicValue;", "getNOT_NULL_REFERENCE_VALUE", "()Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/NotNullBasicValue;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NotNullBasicValue getNOT_NULL_REFERENCE_VALUE() {
            return NotNullBasicValue.NOT_NULL_REFERENCE_VALUE;
        }

        private Companion() {
        }
    }
}
