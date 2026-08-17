package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.NameRenderingUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\t\n\u000b\f\r\u000eB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\b\u001a\u00020\u0005H\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0006\u000f\u0010\u0011\u0012\u0013\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", Argument.Delimiters.none, "<init>", "()V", "branchConditionText", Argument.Delimiters.none, "getBranchConditionText", "()Ljava/lang/String;", "toString", "Unknown", "ConditionTypeIsExpect", "NullIsMissing", "BooleanIsMissing", "IsTypeCheckIsMissing", "EnumCheckIsMissing", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$EnumCheckIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$IsTypeCheckIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$NullIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$Unknown;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class WhenMissingCase {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\f\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$EnumCheckIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;)V", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "branchConditionText", Argument.Delimiters.none, "getBranchConditionText", "()Ljava/lang/String;", "toString", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EnumCheckIsMissing extends WhenMissingCase {
        private final String branchConditionText;
        private final CallableId callableId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EnumCheckIsMissing(CallableId callableId) {
            super(null);
            callableId.getClass();
            this.callableId = callableId;
            this.branchConditionText = NameRenderingUtils.render(callableId.asSingleFqName());
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String getBranchConditionText() {
            return this.branchConditionText;
        }

        public final CallableId getCallableId() {
            return this.callableId;
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String toString() {
            return NameRenderingUtils.render$default(this.callableId.getCallableName(), false, 1, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0013\u001a\u00020\u0010H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$IsTypeCheckIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "isSingleton", Argument.Delimiters.none, "ownTypeParametersCount", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;ZI)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "()Z", "getOwnTypeParametersCount", "()I", "branchConditionText", Argument.Delimiters.none, "getBranchConditionText", "()Ljava/lang/String;", "toString", "typeArguments", "getTypeArguments", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IsTypeCheckIsMissing extends WhenMissingCase {
        private final String branchConditionText;
        private final ClassId classId;
        private final boolean isSingleton;
        private final int ownTypeParametersCount;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IsTypeCheckIsMissing(ClassId classId, boolean z, int i) {
            super(null);
            classId.getClass();
            this.classId = classId;
            this.isSingleton = z;
            this.ownTypeParametersCount = i;
            String str = NameRenderingUtils.render(classId.asSingleFqName()) + getTypeArguments();
            this.branchConditionText = z ? str : "is ".concat(str);
        }

        private final String getTypeArguments() {
            int i = this.ownTypeParametersCount;
            if (i == 0) {
                return Argument.Delimiters.none;
            }
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < i; i2++) {
                cArr[i2] = '*';
            }
            return ArraysKt.joinToString$default(cArr, (CharSequence) null, "<", ">", 0, (CharSequence) null, (Function1) null, 57, (Object) null);
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String getBranchConditionText() {
            return this.branchConditionText;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final int getOwnTypeParametersCount() {
            return this.ownTypeParametersCount;
        }

        /* JADX INFO: renamed from: isSingleton, reason: from getter */
        public final boolean getIsSingleton() {
            return this.isSingleton;
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String toString() {
            String str = NameRenderingUtils.render$default(this.classId.getShortClassName(), false, 1, (Object) null) + getTypeArguments();
            return this.isSingleton ? str : "is ".concat(str);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$NullIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "<init>", "()V", "branchConditionText", Argument.Delimiters.none, "getBranchConditionText", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NullIsMissing extends WhenMissingCase {
        public static final NullIsMissing INSTANCE = new NullIsMissing();
        private static final String branchConditionText = "null";

        private NullIsMissing() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String getBranchConditionText() {
            return branchConditionText;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004R\u0014\u0010\u0006\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$Unknown;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "<init>", "()V", "toString", Argument.Delimiters.none, "branchConditionText", "getBranchConditionText", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Unknown extends WhenMissingCase {
        public static final Unknown INSTANCE = new Unknown();
        private static final String branchConditionText = "else";

        private Unknown() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String getBranchConditionText() {
            return branchConditionText;
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String toString() {
            return "unknown";
        }
    }

    public /* synthetic */ WhenMissingCase(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getBranchConditionText();

    public String toString() {
        return getBranchConditionText();
    }

    private WhenMissingCase() {
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\n\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007\u0082\u0001\u0003\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "typeOfDeclaration", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getTypeOfDeclaration", "()Ljava/lang/String;", "branchConditionText", "getBranchConditionText", "toString", "SealedClass", "SealedInterface", "Enum", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect$Enum;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect$SealedClass;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect$SealedInterface;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ConditionTypeIsExpect extends WhenMissingCase {
        private final String branchConditionText;
        private final String typeOfDeclaration;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect$Enum;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Enum extends ConditionTypeIsExpect {
            public static final Enum INSTANCE = new Enum();

            private Enum() {
                super("enum", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect$SealedClass;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class SealedClass extends ConditionTypeIsExpect {
            public static final SealedClass INSTANCE = new SealedClass();

            private SealedClass() {
                super("sealed class", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect$SealedInterface;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$ConditionTypeIsExpect;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class SealedInterface extends ConditionTypeIsExpect {
            public static final SealedInterface INSTANCE = new SealedInterface();

            private SealedInterface() {
                super("sealed interface", null);
            }
        }

        private ConditionTypeIsExpect(String str) {
            super(null);
            this.typeOfDeclaration = str;
            this.branchConditionText = "else";
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String getBranchConditionText() {
            return this.branchConditionText;
        }

        public final String getTypeOfDeclaration() {
            return this.typeOfDeclaration;
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String toString() {
            return "unknown";
        }

        public /* synthetic */ ConditionTypeIsExpect(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "value", Argument.Delimiters.none, "<init>", "(Z)V", "getValue", "()Z", "branchConditionText", Argument.Delimiters.none, "getBranchConditionText", "()Ljava/lang/String;", "TrueIsMissing", "FalseIsMissing", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing$FalseIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing$TrueIsMissing;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class BooleanIsMissing extends WhenMissingCase {
        private final String branchConditionText;
        private final boolean value;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing$FalseIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class FalseIsMissing extends BooleanIsMissing {
            public static final FalseIsMissing INSTANCE = new FalseIsMissing();

            private FalseIsMissing() {
                super(false, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing$TrueIsMissing;", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase$BooleanIsMissing;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class TrueIsMissing extends BooleanIsMissing {
            public static final TrueIsMissing INSTANCE = new TrueIsMissing();

            private TrueIsMissing() {
                super(true, null);
            }
        }

        private BooleanIsMissing(boolean z) {
            super(null);
            this.value = z;
            this.branchConditionText = String.valueOf(z);
        }

        @Override // org.jetbrains.kotlin.diagnostics.WhenMissingCase
        public String getBranchConditionText() {
            return this.branchConditionText;
        }

        public final boolean getValue() {
            return this.value;
        }

        public /* synthetic */ BooleanIsMissing(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(z);
        }
    }
}
