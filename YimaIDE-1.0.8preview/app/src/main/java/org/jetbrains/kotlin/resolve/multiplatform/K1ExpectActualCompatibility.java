package org.jetbrains.kotlin.resolve.multiplatform;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility;", "D", "", "<init>", "()V", "Incompatible", "Compatible", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Compatible;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class K1ExpectActualCompatibility<D> {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Compatible;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Compatible extends K1ExpectActualCompatibility {
        public static final Compatible INSTANCE = new Compatible();

        private Compatible() {
            super(null);
        }
    }

    public /* synthetic */ K1ExpectActualCompatibility(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:!\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()B\u0013\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002*+¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "D", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "WeakIncompatible", "StrongIncompatible", "CallableKind", "ParameterShape", "ParameterCount", "FunctionTypeParameterCount", "ClassTypeParameterCount", "ParameterTypes", "ReturnType", "ParameterNames", "TypeParameterNames", "ValueParameterVararg", "ValueParameterNoinline", "ValueParameterCrossinline", "FunctionModifiersDifferent", "FunctionModifiersNotSubset", "ActualFunctionWithDefaultParameters", "PropertyKind", "PropertyLateinitModifier", "PropertyConstModifier", "PropertySetterVisibility", "ClassKind", "ClassModifiers", "FunInterfaceModifier", "Supertypes", "ClassScopes", "EnumEntries", "Modality", "Visibility", "FunctionTypeParameterUpperBounds", "ClassTypeParameterUpperBounds", "TypeParameterVariance", "TypeParameterReified", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static abstract class Incompatible<D> extends K1ExpectActualCompatibility<D> {
        private final String reason;

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ActualFunctionWithDefaultParameters;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ActualFunctionWithDefaultParameters extends WeakIncompatible {
            public static final ActualFunctionWithDefaultParameters INSTANCE = new ActualFunctionWithDefaultParameters();

            private ActualFunctionWithDefaultParameters() {
                super("actual function cannot have default argument values, they should be declared in the expected function", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$CallableKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class CallableKind extends StrongIncompatible {
            public static final CallableKind INSTANCE = new CallableKind();

            private CallableKind() {
                super("callable kinds are different (function vs property)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ClassKind extends WeakIncompatible {
            public static final ClassKind INSTANCE = new ClassKind();

            private ClassKind() {
                super("class kinds are different (class, interface, object, enum, annotation)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassModifiers;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ClassModifiers extends WeakIncompatible {
            public static final ClassModifiers INSTANCE = new ClassModifiers();

            private ClassModifiers() {
                super("modifiers are different (companion, inner, inline, value)", null);
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u00120\u0010\u0003\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00028\u0002\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b0\u00060\u00050\u0004¢\u0006\u0004\b\t\u0010\nR;\u0010\u0003\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00028\u0002\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b0\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassScopes;", "D", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "unfulfilled", "", "Lkotlin/Pair;", "", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "", "<init>", "(Ljava/util/List;)V", "getUnfulfilled", "()Ljava/util/List;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ClassScopes<D> extends WeakIncompatible<D> {
            private final List<Pair<D, Map<Incompatible<D>, Collection<D>>>> unfulfilled;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ClassScopes(List<? extends Pair<? extends D, ? extends Map<Incompatible<D>, ? extends Collection<? extends D>>>> list) {
                super("some expected members have no actual ones", null);
                list.getClass();
                this.unfulfilled = list;
            }

            public final List<Pair<D, Map<Incompatible<D>, Collection<D>>>> getUnfulfilled() {
                return this.unfulfilled;
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassTypeParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ClassTypeParameterCount extends WeakIncompatible {
            public static final ClassTypeParameterCount INSTANCE = new ClassTypeParameterCount();

            private ClassTypeParameterCount() {
                super(FunctionTypeParameterCount.INSTANCE.getReason(), null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassTypeParameterUpperBounds;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ClassTypeParameterUpperBounds extends WeakIncompatible {
            public static final ClassTypeParameterUpperBounds INSTANCE = new ClassTypeParameterUpperBounds();

            private ClassTypeParameterUpperBounds() {
                super(FunctionTypeParameterUpperBounds.INSTANCE.getReason(), null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$EnumEntries;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class EnumEntries extends WeakIncompatible {
            public static final EnumEntries INSTANCE = new EnumEntries();

            private EnumEntries() {
                super("some entries from expected enum are missing in the actual enum", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunInterfaceModifier;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class FunInterfaceModifier extends WeakIncompatible {
            public static final FunInterfaceModifier INSTANCE = new FunInterfaceModifier();

            private FunInterfaceModifier() {
                super("actual declaration for fun expect interface is not a functional interface", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionModifiersDifferent;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class FunctionModifiersDifferent extends WeakIncompatible {
            public static final FunctionModifiersDifferent INSTANCE = new FunctionModifiersDifferent();

            private FunctionModifiersDifferent() {
                super("modifiers are different (suspend)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionModifiersNotSubset;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class FunctionModifiersNotSubset extends WeakIncompatible {
            public static final FunctionModifiersNotSubset INSTANCE = new FunctionModifiersNotSubset();

            private FunctionModifiersNotSubset() {
                super("some modifiers on expected declaration are missing on the actual one (infix, inline, operator)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionTypeParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class FunctionTypeParameterCount extends StrongIncompatible {
            public static final FunctionTypeParameterCount INSTANCE = new FunctionTypeParameterCount();

            private FunctionTypeParameterCount() {
                super("number of type parameters is different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionTypeParameterUpperBounds;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class FunctionTypeParameterUpperBounds extends StrongIncompatible {
            public static final FunctionTypeParameterUpperBounds INSTANCE = new FunctionTypeParameterUpperBounds();

            private FunctionTypeParameterUpperBounds() {
                super("upper bounds of type parameters are different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$Modality;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Modality extends WeakIncompatible {
            public static final Modality INSTANCE = new Modality();

            private Modality() {
                super("modality is different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ParameterCount extends StrongIncompatible {
            public static final ParameterCount INSTANCE = new ParameterCount();

            private ParameterCount() {
                super("number of value parameters is different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterNames;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ParameterNames extends WeakIncompatible {
            public static final ParameterNames INSTANCE = new ParameterNames();

            private ParameterNames() {
                super("parameter names are different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterShape;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ParameterShape extends StrongIncompatible {
            public static final ParameterShape INSTANCE = new ParameterShape();

            private ParameterShape() {
                super("parameter shapes are different (extension vs non-extension)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterTypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ParameterTypes extends StrongIncompatible {
            public static final ParameterTypes INSTANCE = new ParameterTypes();

            private ParameterTypes() {
                super("parameter types are different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertyConstModifier;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class PropertyConstModifier extends WeakIncompatible {
            public static final PropertyConstModifier INSTANCE = new PropertyConstModifier();

            private PropertyConstModifier() {
                super("modifiers are different (const)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertyKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class PropertyKind extends WeakIncompatible {
            public static final PropertyKind INSTANCE = new PropertyKind();

            private PropertyKind() {
                super("property kinds are different (val vs var)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertyLateinitModifier;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class PropertyLateinitModifier extends WeakIncompatible {
            public static final PropertyLateinitModifier INSTANCE = new PropertyLateinitModifier();

            private PropertyLateinitModifier() {
                super("modifiers are different (lateinit)", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertySetterVisibility;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class PropertySetterVisibility extends WeakIncompatible {
            public static final PropertySetterVisibility INSTANCE = new PropertySetterVisibility();

            private PropertySetterVisibility() {
                super("setter visibility is different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ReturnType;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ReturnType extends StrongIncompatible {
            public static final ReturnType INSTANCE = new ReturnType();

            private ReturnType() {
                super("return type is different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$Supertypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Supertypes extends WeakIncompatible {
            public static final Supertypes INSTANCE = new Supertypes();

            private Supertypes() {
                super("some supertypes are missing in the actual declaration", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$TypeParameterNames;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class TypeParameterNames extends WeakIncompatible {
            public static final TypeParameterNames INSTANCE = new TypeParameterNames();

            private TypeParameterNames() {
                super("names of type parameters are different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$TypeParameterReified;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class TypeParameterReified extends WeakIncompatible {
            public static final TypeParameterReified INSTANCE = new TypeParameterReified();

            private TypeParameterReified() {
                super("some type parameter is reified in one declaration and non-reified in the other", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$TypeParameterVariance;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class TypeParameterVariance extends WeakIncompatible {
            public static final TypeParameterVariance INSTANCE = new TypeParameterVariance();

            private TypeParameterVariance() {
                super("declaration-site variances of type parameters are different", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ValueParameterCrossinline;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ValueParameterCrossinline extends WeakIncompatible {
            public static final ValueParameterCrossinline INSTANCE = new ValueParameterCrossinline();

            private ValueParameterCrossinline() {
                super("some value parameter is crossinline in one declaration and not crossinline in the other", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ValueParameterNoinline;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ValueParameterNoinline extends WeakIncompatible {
            public static final ValueParameterNoinline INSTANCE = new ValueParameterNoinline();

            private ValueParameterNoinline() {
                super("some value parameter is noinline in one declaration and not noinline in the other", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ValueParameterVararg;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ValueParameterVararg extends WeakIncompatible {
            public static final ValueParameterVararg INSTANCE = new ValueParameterVararg();

            private ValueParameterVararg() {
                super("some value parameter is vararg in one declaration and non-vararg in the other", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$Visibility;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "", "<init>", "()V", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Visibility extends WeakIncompatible {
            public static final Visibility INSTANCE = new Visibility();

            private Visibility() {
                super("visibility is different", null);
            }
        }

        private Incompatible(String str) {
            super(null);
            this.reason = str;
        }

        public final String getReason() {
            return this.reason;
        }

        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0002\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u0082\u0001\u0007\u0007\b\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$StrongIncompatible;", "D", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "reason", "", "<init>", "(Ljava/lang/String;)V", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$CallableKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionTypeParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionTypeParameterUpperBounds;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterShape;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterTypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ReturnType;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static abstract class StrongIncompatible<D> extends Incompatible<D> {
            private StrongIncompatible(String str) {
                super(str, null);
            }

            public /* synthetic */ StrongIncompatible(String str, DefaultConstructorMarker defaultConstructorMarker) {
                this(str);
            }
        }

        @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0002\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u0082\u0001\u0018\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$WeakIncompatible;", "D", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "reason", "", "<init>", "(Ljava/lang/String;)V", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ActualFunctionWithDefaultParameters;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassModifiers;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassScopes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassTypeParameterCount;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ClassTypeParameterUpperBounds;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$EnumEntries;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunInterfaceModifier;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionModifiersDifferent;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$FunctionModifiersNotSubset;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$Modality;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ParameterNames;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertyConstModifier;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertyKind;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertyLateinitModifier;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$PropertySetterVisibility;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$Supertypes;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$TypeParameterNames;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$TypeParameterReified;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$TypeParameterVariance;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ValueParameterCrossinline;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ValueParameterNoinline;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$ValueParameterVararg;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible$Visibility;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static abstract class WeakIncompatible<D> extends Incompatible<D> {
            private WeakIncompatible(String str) {
                super(str, null);
            }

            public /* synthetic */ WeakIncompatible(String str, DefaultConstructorMarker defaultConstructorMarker) {
                this(str);
            }
        }

        public /* synthetic */ Incompatible(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }

    private K1ExpectActualCompatibility() {
    }
}
