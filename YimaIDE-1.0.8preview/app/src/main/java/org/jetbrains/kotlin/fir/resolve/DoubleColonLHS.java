package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Expression", "Type", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Expression;", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Type;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DoubleColonLHS {
    private final ConeKotlinType type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Expression;", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isObjectQualifier", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)V", "()Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Expression extends DoubleColonLHS {
        private final boolean isObjectQualifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Expression(ConeKotlinType coneKotlinType, boolean z) {
            super(coneKotlinType, null);
            coneKotlinType.getClass();
            this.isObjectQualifier = z;
        }

        /* JADX INFO: renamed from: isObjectQualifier, reason: from getter */
        public final boolean getIsObjectQualifier() {
            return this.isObjectQualifier;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Type;", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Type extends DoubleColonLHS {
        private final ConeDiagnostic diagnostic;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Type(ConeKotlinType coneKotlinType, ConeDiagnostic coneDiagnostic) {
            super(coneKotlinType, null);
            coneKotlinType.getClass();
            this.diagnostic = coneDiagnostic;
        }

        public final ConeDiagnostic getDiagnostic() {
            return this.diagnostic;
        }
    }

    private DoubleColonLHS(ConeKotlinType coneKotlinType) {
        this.type = coneKotlinType;
    }

    public final ConeKotlinType getType() {
        return this.type;
    }

    public /* synthetic */ DoubleColonLHS(ConeKotlinType coneKotlinType, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType);
    }
}
