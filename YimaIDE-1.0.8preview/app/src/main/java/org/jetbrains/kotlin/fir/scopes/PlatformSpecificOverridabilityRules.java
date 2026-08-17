package org.jetbrains.kotlin.fir.scopes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\fH&¢\u0006\u0002\u0010\rJ&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H&J\u001a\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00000\u0018H\u0007b\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/PlatformSpecificOverridabilityRules;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "isOverriddenFunction", Argument.Delimiters.none, "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "baseDeclaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Ljava/lang/Boolean;", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "createComposed", "Lorg/jetbrains/kotlin/fir/scopes/PlatformSpecificOverridabilityRules$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Composed", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PlatformSpecificOverridabilityRules implements FirComposableSessionComponent<PlatformSpecificOverridabilityRules> {

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/PlatformSpecificOverridabilityRules$Composed;", "Lorg/jetbrains/kotlin/fir/scopes/PlatformSpecificOverridabilityRules;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "isOverriddenFunction", Argument.Delimiters.none, "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "baseDeclaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Ljava/lang/Boolean;", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends PlatformSpecificOverridabilityRules implements FirComposableSessionComponent.Composed<PlatformSpecificOverridabilityRules> {
        private final List<PlatformSpecificOverridabilityRules> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends PlatformSpecificOverridabilityRules> list) {
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules
        public Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol) {
            overrides.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                Visibility visibilityChooseIntersectionVisibility = ((PlatformSpecificOverridabilityRules) it.next()).chooseIntersectionVisibility(overrides, dispatchClassSymbol);
                if (visibilityChooseIntersectionVisibility != null) {
                    return visibilityChooseIntersectionVisibility;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<PlatformSpecificOverridabilityRules> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules
        public Boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration) {
            overrideCandidate.getClass();
            baseDeclaration.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                Boolean boolIsOverriddenFunction = ((PlatformSpecificOverridabilityRules) it.next()).isOverriddenFunction(overrideCandidate, baseDeclaration);
                if (boolIsOverriddenFunction != null) {
                    return boolIsOverriddenFunction;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules
        public Boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration) {
            overrideCandidate.getClass();
            baseDeclaration.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                Boolean boolIsOverriddenProperty = ((PlatformSpecificOverridabilityRules) it.next()).isOverriddenProperty(overrideCandidate, baseDeclaration);
                if (boolIsOverriddenProperty != null) {
                    return boolIsOverriddenProperty;
                }
            }
            return null;
        }
    }

    public abstract Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol);

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public final Composed createComposed(List<? extends PlatformSpecificOverridabilityRules> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract Boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration);

    public abstract Boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration);

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends PlatformSpecificOverridabilityRules>) list);
    }
}
