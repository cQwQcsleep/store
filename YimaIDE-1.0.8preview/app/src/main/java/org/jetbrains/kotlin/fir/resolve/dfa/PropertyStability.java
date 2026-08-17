package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.SmartcastStability;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CAPTURED_VARIABLE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/PropertyStability;", Argument.Delimiters.none, "inherentInstability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "checkModule", Argument.Delimiters.none, "checkReceiver", "<init>", "(Ljava/lang/String;ILorg/jetbrains/kotlin/types/SmartcastStability;ZZ)V", "getInherentInstability", "()Lorg/jetbrains/kotlin/types/SmartcastStability;", "getCheckModule", "()Z", "getCheckReceiver", "PRIVATE_OR_CONST_VAL", "PUBLIC_FINAL_VAL", "PUBLIC_OPEN_VAL", "CAPTURED_VARIABLE", "EXPECT_PROPERTY", "PROPERTY_WITH_GETTER", "MUTABLE_PROPERTY", "DELEGATED_PROPERTY", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertyStability {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PropertyStability[] $VALUES;
    public static final PropertyStability CAPTURED_VARIABLE;
    public static final PropertyStability DELEGATED_PROPERTY;
    public static final PropertyStability EXPECT_PROPERTY;
    public static final PropertyStability MUTABLE_PROPERTY;
    public static final PropertyStability PROPERTY_WITH_GETTER;
    private final boolean checkModule;
    private final boolean checkReceiver;
    private final SmartcastStability inherentInstability;
    public static final PropertyStability PRIVATE_OR_CONST_VAL = new PropertyStability("PRIVATE_OR_CONST_VAL", 0, null, false, false, 6, null);
    public static final PropertyStability PUBLIC_FINAL_VAL = new PropertyStability("PUBLIC_FINAL_VAL", 1, null, true, false, 4, null);
    public static final PropertyStability PUBLIC_OPEN_VAL = new PropertyStability("PUBLIC_OPEN_VAL", 2, null, true, true);

    private static final /* synthetic */ PropertyStability[] $values() {
        return new PropertyStability[]{PRIVATE_OR_CONST_VAL, PUBLIC_FINAL_VAL, PUBLIC_OPEN_VAL, CAPTURED_VARIABLE, EXPECT_PROPERTY, PROPERTY_WITH_GETTER, MUTABLE_PROPERTY, DELEGATED_PROPERTY};
    }

    static {
        boolean z = false;
        CAPTURED_VARIABLE = new PropertyStability("CAPTURED_VARIABLE", 3, SmartcastStability.CAPTURED_VARIABLE, false, z, 6, null);
        boolean z2 = false;
        EXPECT_PROPERTY = new PropertyStability("EXPECT_PROPERTY", 4, SmartcastStability.EXPECT_PROPERTY, z, z2, 6, null);
        boolean z3 = false;
        PROPERTY_WITH_GETTER = new PropertyStability("PROPERTY_WITH_GETTER", 5, SmartcastStability.PROPERTY_WITH_GETTER, z2, z3, 6, null);
        boolean z4 = false;
        MUTABLE_PROPERTY = new PropertyStability("MUTABLE_PROPERTY", 6, SmartcastStability.MUTABLE_PROPERTY, z3, z4, 6, null);
        DELEGATED_PROPERTY = new PropertyStability("DELEGATED_PROPERTY", 7, SmartcastStability.DELEGATED_PROPERTY, z4, false, 6, null);
        PropertyStability[] propertyStabilityArr$values = $values();
        $VALUES = propertyStabilityArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(propertyStabilityArr$values);
    }

    public /* synthetic */ PropertyStability(String str, int i, SmartcastStability smartcastStability, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, smartcastStability, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2);
    }

    public static EnumEntries<PropertyStability> getEntries() {
        return $ENTRIES;
    }

    public static PropertyStability valueOf(String str) {
        return (PropertyStability) Enum.valueOf(PropertyStability.class, str);
    }

    public static PropertyStability[] values() {
        return (PropertyStability[]) $VALUES.clone();
    }

    public final boolean getCheckModule() {
        return this.checkModule;
    }

    public final boolean getCheckReceiver() {
        return this.checkReceiver;
    }

    public final SmartcastStability getInherentInstability() {
        return this.inherentInstability;
    }

    private PropertyStability(String str, int i, SmartcastStability smartcastStability, boolean z, boolean z2) {
        super(str, i);
        this.inherentInstability = smartcastStability;
        this.checkModule = z;
        this.checkReceiver = z2;
    }
}
