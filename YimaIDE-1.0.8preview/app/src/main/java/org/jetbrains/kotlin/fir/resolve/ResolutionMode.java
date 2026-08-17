package org.jetbrains.kotlin.fir.resolve;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00152\u00020\u0001:\n\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0001\b\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", Argument.Delimiters.none, "forceFullCompletion", Argument.Delimiters.none, "<init>", "(Z)V", "getForceFullCompletion", "()Z", "hintForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getHintForContextSensitiveResolution", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "ContextDependent", "Delegate", "ContextIndependent", "ReceiverResolution", "WithExpectedType", "ArrayLiteralPosition", "WithStatus", "UpdateImplicitTypeRef", "AssignmentLValue", "Companion", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$AssignmentLValue;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ContextDependent;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ContextIndependent;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$Delegate;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$UpdateImplicitTypeRef;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithStatus;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ResolutionMode {
    private static final Companion Companion = new Companion(null);
    private final boolean forceFullCompletion;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ArrayLiteralPosition;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "AnnotationArgument", "AnnotationParameter", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ArrayLiteralPosition {
        AnnotationArgument,
        AnnotationParameter;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ArrayLiteralPosition> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$AssignmentLValue;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;)V", "getVariableAssignment", "()Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AssignmentLValue extends ResolutionMode {
        private final FirVariableAssignment variableAssignment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AssignmentLValue(FirVariableAssignment firVariableAssignment) {
            super(true, null);
            firVariableAssignment.getClass();
            this.variableAssignment = firVariableAssignment;
        }

        public final FirVariableAssignment getVariableAssignment() {
            return this.variableAssignment;
        }

        public String toString() {
            return "AssignmentLValue: " + UtilsKt.render(this.variableAssignment);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ContextIndependent;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ContextIndependent extends ResolutionMode {
        public static final ContextIndependent INSTANCE = new ContextIndependent();

        private ContextIndependent() {
            super(true, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ContextIndependent);
        }

        public int hashCode() {
            return -242784130;
        }

        public String toString() {
            return "ContextIndependent";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$Delegate;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Delegate extends ResolutionMode {
        public static final Delegate INSTANCE = new Delegate();

        private Delegate() {
            super(false, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Delegate);
        }

        public int hashCode() {
            return -1984691336;
        }

        public String toString() {
            return "Delegate";
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$UpdateImplicitTypeRef;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "newTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "getNewTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UpdateImplicitTypeRef extends ResolutionMode {
        private final FirResolvedTypeRef newTypeRef;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UpdateImplicitTypeRef(FirResolvedTypeRef firResolvedTypeRef) {
            super(false, null);
            firResolvedTypeRef.getClass();
            this.newTypeRef = firResolvedTypeRef;
        }

        public final FirResolvedTypeRef getNewTypeRef() {
            return this.newTypeRef;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithStatus;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class WithStatus extends ResolutionMode {
        private final FirDeclarationStatus status;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WithStatus(FirDeclarationStatus firDeclarationStatus) {
            super(false, null);
            firDeclarationStatus.getClass();
            this.status = firDeclarationStatus;
        }

        public final FirDeclarationStatus getStatus() {
            return this.status;
        }

        public String toString() {
            return "WithStatus: " + UtilsKt.render(this.status);
        }
    }

    private ResolutionMode(boolean z) {
        this.forceFullCompletion = z;
    }

    public final boolean getForceFullCompletion() {
        return this.forceFullCompletion;
    }

    public ConeKotlinType getHintForContextSensitiveResolution() {
        return null;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0005*\u0004\u0018\u00010\u0006H\u0002¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$Companion;", Argument.Delimiters.none, "<init>", "()V", "prettyString", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String prettyString(FirTypeRef firTypeRef) {
            if (firTypeRef == null) {
                return "null";
            }
            FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            ConeKotlinType coneKotlinType = coneType != null ? coneType : null;
            return coneKotlinType == null ? UtilsKt.render(firTypeRef) : ConeTypeUtilsKt.renderForDebugging(coneKotlinType);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ContextDependent;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "hintForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getHintForContextSensitiveResolution", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static class ContextDependent extends ResolutionMode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final ConeKotlinType hintForContextSensitiveResolution;

        public ContextDependent(ConeKotlinType coneKotlinType) {
            super(false, null);
            this.hintForContextSensitiveResolution = coneKotlinType;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.ResolutionMode
        public ConeKotlinType getHintForContextSensitiveResolution() {
            return this.hintForContextSensitiveResolution;
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ContextDependent$Companion;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ContextDependent;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion extends ContextDependent {
            private Companion() {
                super(null);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0002\b\tB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "forCallableReference", Argument.Delimiters.none, "<init>", "(Z)V", "getForCallableReference", "()Z", "ForCallableReference", "Companion", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution$Companion;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution$ForCallableReference;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ReceiverResolution extends ResolutionMode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final boolean forCallableReference;

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution$ForCallableReference;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class ForCallableReference extends ReceiverResolution {
            public static final ForCallableReference INSTANCE = new ForCallableReference();

            private ForCallableReference() {
                super(true, null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof ForCallableReference);
            }

            public int hashCode() {
                return 1551852426;
            }

            public String toString() {
                return "ForCallableReference";
            }
        }

        private ReceiverResolution(boolean z) {
            super(true, null);
            this.forCallableReference = z;
        }

        public final boolean getForCallableReference() {
            return this.forCallableReference;
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution$Companion;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ReceiverResolution;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion extends ReceiverResolution {
            private Companion() {
                super(false, null);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public /* synthetic */ ReceiverResolution(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(z);
        }
    }

    public /* synthetic */ ResolutionMode(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(z);
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001fBE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ$\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005J\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004R \u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004r\u0002\b\u0012¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "expectedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "lastStatementInBlock", Argument.Delimiters.none, "fromCast", "arrayLiteralPosition", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ArrayLiteralPosition;", "hintForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "forceFullCompletion", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;ZZLorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ArrayLiteralPosition;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)V", "getExpectedTypeRef$annotations", "()V", "getExpectedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType$ExpectedTypeRefAccess;", "getLastStatementInBlock", "()Z", "getFromCast", "getArrayLiteralPosition", "()Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$ArrayLiteralPosition;", "getHintForContextSensitiveResolution", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "expectedType", "getExpectedType", "copy", "toString", Argument.Delimiters.none, "ExpectedTypeRefAccess", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class WithExpectedType extends ResolutionMode {
        private final ArrayLiteralPosition arrayLiteralPosition;
        private final FirResolvedTypeRef expectedTypeRef;
        private final boolean fromCast;
        private final ConeKotlinType hintForContextSensitiveResolution;
        private final boolean lastStatementInBlock;

        @Retention(RetentionPolicy.RUNTIME)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType$ExpectedTypeRefAccess;", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve", "Lkotlin/RequiresOptIn;", "message", "Accessing 'expectedTypeRef' is generally not necessary unless the caller needs access to its source. Prefer using 'expectedType' instead."}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public @interface ExpectedTypeRefAccess {
        }

        public /* synthetic */ WithExpectedType(FirResolvedTypeRef firResolvedTypeRef, boolean z, boolean z2, ArrayLiteralPosition arrayLiteralPosition, ConeKotlinType coneKotlinType, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(firResolvedTypeRef, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? null : arrayLiteralPosition, (i & 16) != 0 ? null : coneKotlinType, (i & 32) != 0 ? true : z3);
        }

        public static /* synthetic */ WithExpectedType copy$default(WithExpectedType withExpectedType, FirResolvedTypeRef firResolvedTypeRef, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                firResolvedTypeRef = withExpectedType.expectedTypeRef;
            }
            if ((i & 2) != 0) {
                z = withExpectedType.lastStatementInBlock;
            }
            if ((i & 4) != 0) {
                z2 = withExpectedType.getForceFullCompletion();
            }
            return withExpectedType.copy(firResolvedTypeRef, z, z2);
        }

        @ExpectedTypeRefAccess
        public static /* synthetic */ void getExpectedTypeRef$annotations() {
        }

        public final WithExpectedType copy(FirResolvedTypeRef expectedTypeRef, boolean lastStatementInBlock, boolean forceFullCompletion) {
            expectedTypeRef.getClass();
            return new WithExpectedType(expectedTypeRef, lastStatementInBlock, this.fromCast, this.arrayLiteralPosition, null, forceFullCompletion, 16, null);
        }

        public final ArrayLiteralPosition getArrayLiteralPosition() {
            return this.arrayLiteralPosition;
        }

        public final ConeKotlinType getExpectedType() {
            return this.expectedTypeRef.getConeType();
        }

        public final FirResolvedTypeRef getExpectedTypeRef() {
            return this.expectedTypeRef;
        }

        public final boolean getFromCast() {
            return this.fromCast;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.ResolutionMode
        public ConeKotlinType getHintForContextSensitiveResolution() {
            return this.hintForContextSensitiveResolution;
        }

        public final boolean getLastStatementInBlock() {
            return this.lastStatementInBlock;
        }

        public String toString() {
            return "WithExpectedType: " + ResolutionMode.Companion.prettyString(this.expectedTypeRef) + ", lastStatementInBlock=" + this.lastStatementInBlock + ", fromCast=" + this.fromCast + ", arrayLiteralPosition=" + this.arrayLiteralPosition + ", forceFullCompletion=" + getForceFullCompletion() + ", ";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WithExpectedType(FirResolvedTypeRef firResolvedTypeRef, boolean z, boolean z2, ArrayLiteralPosition arrayLiteralPosition, ConeKotlinType coneKotlinType, boolean z3) {
            super(z3, null);
            firResolvedTypeRef.getClass();
            this.expectedTypeRef = firResolvedTypeRef;
            this.lastStatementInBlock = z;
            this.fromCast = z2;
            this.arrayLiteralPosition = arrayLiteralPosition;
            this.hintForContextSensitiveResolution = coneKotlinType;
        }
    }
}
