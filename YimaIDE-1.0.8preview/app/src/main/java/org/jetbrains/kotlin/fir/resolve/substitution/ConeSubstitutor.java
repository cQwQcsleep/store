package org.jetbrains.kotlin.fir.resolve.substitution;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;", "<init>", "()V", "substituteOrSelf", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "substituteOrNull", "substituteArgument", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "projection", "index", Argument.Delimiters.none, "Empty", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeSubstitutor implements TypeSubstitutorMarker {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor$Empty;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "<init>", "()V", "substituteOrSelf", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "substituteOrNull", "substituteArgument", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "projection", "index", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Empty extends ConeSubstitutor {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
        public ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index) {
            projection.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
        public ConeKotlinType substituteOrNull(ConeKotlinType type) {
            type.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
        public ConeKotlinType substituteOrSelf(ConeKotlinType type) {
            type.getClass();
            return type;
        }

        public String toString() {
            return "Empty";
        }
    }

    public abstract ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index);

    public abstract ConeKotlinType substituteOrNull(ConeKotlinType type);

    public ConeKotlinType substituteOrSelf(ConeKotlinType type) {
        type.getClass();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = substituteOrNull(type);
        return coneKotlinTypeSubstituteOrNull == null ? type : coneKotlinTypeSubstituteOrNull;
    }
}
