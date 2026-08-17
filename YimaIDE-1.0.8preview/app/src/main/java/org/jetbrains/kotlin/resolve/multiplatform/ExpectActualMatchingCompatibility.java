package org.jetbrains.kotlin.resolve.multiplatform;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\f\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", "", "<init>", "()V", "toString", "", "Mismatch", "CallableKind", "ActualJavaField", "ParameterShape", "ParameterCount", "ContextParameterCount", "FunctionTypeParameterCount", "ParameterTypes", "ParameterNames", "ContextParameterTypes", "FunctionTypeParameterUpperBounds", "MatchedSuccessfully", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$MatchedSuccessfully;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public abstract class ExpectActualMatchingCompatibility {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ActualJavaField;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ActualJavaField extends Mismatch {
        public static final ActualJavaField INSTANCE = new ActualJavaField();

        private ActualJavaField() {
            super("actualization to Java field is prohibited", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$CallableKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class CallableKind extends Mismatch {
        public static final CallableKind INSTANCE = new CallableKind();

        private CallableKind() {
            super("callable kinds are different (function vs property)", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ContextParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ContextParameterCount extends Mismatch {
        public static final ContextParameterCount INSTANCE = new ContextParameterCount();

        private ContextParameterCount() {
            super("the number of context parameters is different", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ContextParameterTypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ContextParameterTypes extends Mismatch {
        public static final ContextParameterTypes INSTANCE = new ContextParameterTypes();

        private ContextParameterTypes() {
            super("context parameter types are different", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$FunctionTypeParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class FunctionTypeParameterCount extends Mismatch {
        public static final FunctionTypeParameterCount INSTANCE = new FunctionTypeParameterCount();

        private FunctionTypeParameterCount() {
            super("the number of type parameters is different", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$FunctionTypeParameterUpperBounds;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class FunctionTypeParameterUpperBounds extends Mismatch {
        public static final FunctionTypeParameterUpperBounds INSTANCE = new FunctionTypeParameterUpperBounds();

        private FunctionTypeParameterUpperBounds() {
            super("the upper bounds of type parameters are different", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$MatchedSuccessfully;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class MatchedSuccessfully extends ExpectActualMatchingCompatibility {
        public static final MatchedSuccessfully INSTANCE = new MatchedSuccessfully();

        private MatchedSuccessfully() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ParameterCount extends Mismatch {
        public static final ParameterCount INSTANCE = new ParameterCount();

        private ParameterCount() {
            super("the number of value parameters is different", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterNames;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ParameterNames extends Mismatch {
        public static final ParameterNames INSTANCE = new ParameterNames();

        private ParameterNames() {
            super("parameter names are different", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterShape;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ParameterShape extends Mismatch {
        public static final ParameterShape INSTANCE = new ParameterShape();

        private ParameterShape() {
            super("parameter shapes are different (extension vs non-extension)", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterTypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ParameterTypes extends Mismatch {
        public static final ParameterTypes INSTANCE = new ParameterTypes();

        private ParameterTypes() {
            super("parameter types are different", null);
        }
    }

    public /* synthetic */ ExpectActualMatchingCompatibility(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public String toString() {
        return String.valueOf(Reflection.getOrCreateKotlinClass(getClass()).getSimpleName());
    }

    private ExpectActualMatchingCompatibility() {
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\n\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ActualJavaField;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$CallableKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ContextParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ContextParameterTypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$FunctionTypeParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$FunctionTypeParameterUpperBounds;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterNames;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterShape;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$ParameterTypes;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static abstract class Mismatch extends ExpectActualMatchingCompatibility {
        private final String reason;

        private Mismatch(String str) {
            super(null);
            this.reason = str;
        }

        public final String getReason() {
            return this.reason;
        }

        public /* synthetic */ Mismatch(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }
}
