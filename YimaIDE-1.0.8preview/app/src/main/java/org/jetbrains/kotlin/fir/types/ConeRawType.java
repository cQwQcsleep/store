package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeRawType;", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;Lorg/jetbrains/kotlin/fir/types/ConeRigidType;)V", "Companion", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeRawType extends ConeFlexibleType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    private ConeRawType(ConeRigidType coneRigidType, ConeRigidType coneRigidType2) {
        super(coneRigidType, coneRigidType2, false);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeRawType$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/types/ConeRawType;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConeRawType create(ConeRigidType lowerBound, ConeRigidType upperBound) {
            ConeRigidType coneClassLikeTypeImpl;
            lowerBound.getClass();
            upperBound.getClass();
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (!(lowerBound instanceof ConeClassLikeType) || !(upperBound instanceof ConeClassLikeType)) {
                zwd.a("Raw bounds are expected to be class-like types, but ", lowerBound, " and ", upperBound, " were found");
                return null;
            }
            ConeAttributes attributes = lowerBound.getAttributes();
            ConeAttribute<?> coneAttribute = CompilerConeAttributes.RawType.INSTANCE;
            if (attributes.contains(coneAttribute)) {
                coneClassLikeTypeImpl = (ConeClassLikeType) lowerBound;
            } else {
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) lowerBound;
                coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(coneClassLikeType.getLookupTag(), lowerBound.getTypeArguments(), coneClassLikeType.getIsMarkedNullable(), lowerBound.getAttributes().add(coneAttribute));
            }
            return new ConeRawType(coneClassLikeTypeImpl, upperBound, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ ConeRawType(ConeRigidType coneRigidType, ConeRigidType coneRigidType2, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneRigidType, coneRigidType2);
    }
}
