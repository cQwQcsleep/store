package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001:\u0001\rJ6\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\f\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "createKey", "Lorg/jetbrains/kotlin/fir/scopes/ConeSubstitutionScopeKey;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "dispatchReceiverLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "memberOwnerLookupTag", "memberOwnerClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "isFromExpectClass", Argument.Delimiters.none, "Default", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface SubstitutionScopeKeyFactory extends FirSessionComponent {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory$Default;", "Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory;", "<init>", "()V", "createKey", "Lorg/jetbrains/kotlin/fir/scopes/ConeSubstitutionScopeKey;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "dispatchReceiverLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "memberOwnerLookupTag", "memberOwnerClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "isFromExpectClass", Argument.Delimiters.none, "DefaultConeSubstitutionScopeKey", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default implements SubstitutionScopeKeyFactory {
        public static final Default INSTANCE = new Default();

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÄ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÄ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÄ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÄ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory$Default$DefaultConeSubstitutionScopeKey;", "Lorg/jetbrains/kotlin/fir/scopes/ConeSubstitutionScopeKey;", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "isFromExpectClass", Argument.Delimiters.none, "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "derivedClassLookupTag", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;ZLorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)V", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "()Z", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getDerivedClassLookupTag", "component1", "component2", "component3", "component4", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class DefaultConeSubstitutionScopeKey extends ConeSubstitutionScopeKey {
            private final ConeClassLikeLookupTag derivedClassLookupTag;
            private final boolean isFromExpectClass;
            private final ConeClassLikeLookupTag lookupTag;
            private final ConeSubstitutor substitutor;

            public DefaultConeSubstitutionScopeKey(ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, ConeSubstitutor coneSubstitutor, ConeClassLikeLookupTag coneClassLikeLookupTag2) {
                coneClassLikeLookupTag.getClass();
                coneSubstitutor.getClass();
                this.lookupTag = coneClassLikeLookupTag;
                this.isFromExpectClass = z;
                this.substitutor = coneSubstitutor;
                this.derivedClassLookupTag = coneClassLikeLookupTag2;
            }

            public static /* synthetic */ DefaultConeSubstitutionScopeKey copy$default(DefaultConeSubstitutionScopeKey defaultConeSubstitutionScopeKey, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, ConeSubstitutor coneSubstitutor, ConeClassLikeLookupTag coneClassLikeLookupTag2, int i, Object obj) {
                if ((i & 1) != 0) {
                    coneClassLikeLookupTag = defaultConeSubstitutionScopeKey.lookupTag;
                }
                if ((i & 2) != 0) {
                    z = defaultConeSubstitutionScopeKey.isFromExpectClass;
                }
                if ((i & 4) != 0) {
                    coneSubstitutor = defaultConeSubstitutionScopeKey.substitutor;
                }
                if ((i & 8) != 0) {
                    coneClassLikeLookupTag2 = defaultConeSubstitutionScopeKey.derivedClassLookupTag;
                }
                return defaultConeSubstitutionScopeKey.copy(coneClassLikeLookupTag, z, coneSubstitutor, coneClassLikeLookupTag2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final ConeClassLikeLookupTag getLookupTag() {
                return this.lookupTag;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getIsFromExpectClass() {
                return this.isFromExpectClass;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final ConeSubstitutor getSubstitutor() {
                return this.substitutor;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final ConeClassLikeLookupTag getDerivedClassLookupTag() {
                return this.derivedClassLookupTag;
            }

            public final DefaultConeSubstitutionScopeKey copy(ConeClassLikeLookupTag lookupTag, boolean isFromExpectClass, ConeSubstitutor substitutor, ConeClassLikeLookupTag derivedClassLookupTag) {
                lookupTag.getClass();
                substitutor.getClass();
                return new DefaultConeSubstitutionScopeKey(lookupTag, isFromExpectClass, substitutor, derivedClassLookupTag);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DefaultConeSubstitutionScopeKey)) {
                    return false;
                }
                DefaultConeSubstitutionScopeKey defaultConeSubstitutionScopeKey = (DefaultConeSubstitutionScopeKey) other;
                return Intrinsics.areEqual(this.lookupTag, defaultConeSubstitutionScopeKey.lookupTag) && this.isFromExpectClass == defaultConeSubstitutionScopeKey.isFromExpectClass && Intrinsics.areEqual(this.substitutor, defaultConeSubstitutionScopeKey.substitutor) && Intrinsics.areEqual(this.derivedClassLookupTag, defaultConeSubstitutionScopeKey.derivedClassLookupTag);
            }

            @Override // org.jetbrains.kotlin.fir.scopes.ConeSubstitutionScopeKey
            public ConeClassLikeLookupTag getDerivedClassLookupTag() {
                return this.derivedClassLookupTag;
            }

            @Override // org.jetbrains.kotlin.fir.scopes.ConeSubstitutionScopeKey
            public ConeClassLikeLookupTag getLookupTag() {
                return this.lookupTag;
            }

            @Override // org.jetbrains.kotlin.fir.scopes.ConeSubstitutionScopeKey
            public ConeSubstitutor getSubstitutor() {
                return this.substitutor;
            }

            public int hashCode() {
                int iHashCode = ((((this.lookupTag.hashCode() * 31) + Boolean.hashCode(this.isFromExpectClass)) * 31) + this.substitutor.hashCode()) * 31;
                ConeClassLikeLookupTag coneClassLikeLookupTag = this.derivedClassLookupTag;
                return iHashCode + (coneClassLikeLookupTag == null ? 0 : coneClassLikeLookupTag.hashCode());
            }

            @Override // org.jetbrains.kotlin.fir.scopes.ConeSubstitutionScopeKey
            public boolean isFromExpectClass() {
                return this.isFromExpectClass;
            }

            public String toString() {
                return "DefaultConeSubstitutionScopeKey(lookupTag=" + this.lookupTag + ", isFromExpectClass=" + this.isFromExpectClass + ", substitutor=" + this.substitutor + ", derivedClassLookupTag=" + this.derivedClassLookupTag + ')';
            }
        }

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.SubstitutionScopeKeyFactory
        public ConeSubstitutionScopeKey createKey(ConeSubstitutor substitutor, ConeClassLikeLookupTag dispatchReceiverLookupTag, ConeClassLikeLookupTag memberOwnerLookupTag, FirClassSymbol<?> memberOwnerClass, boolean isFromExpectClass) {
            substitutor.getClass();
            dispatchReceiverLookupTag.getClass();
            memberOwnerLookupTag.getClass();
            return new DefaultConeSubstitutionScopeKey(dispatchReceiverLookupTag, isFromExpectClass, substitutor, memberOwnerLookupTag);
        }
    }

    ConeSubstitutionScopeKey createKey(ConeSubstitutor substitutor, ConeClassLikeLookupTag dispatchReceiverLookupTag, ConeClassLikeLookupTag memberOwnerLookupTag, FirClassSymbol<?> memberOwnerClass, boolean isFromExpectClass);
}
