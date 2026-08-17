package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ScopesHolderForClass;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.storage.NotNullLazyValue;
import org.jetbrains.kotlin.storage.StorageKt;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.TypeConstructor;
import org.jetbrains.kotlin.types.checker.KotlinTypeRefiner;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u0016*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u0016B5\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\n¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00028\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/ScopesHolderForClass;", "T", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", Argument.Delimiters.none, "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "scopeFactory", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;", "kotlinTypeRefinerForOwnerModule", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;Lorg/jetbrains/kotlin/storage/StorageManager;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;)V", "scopeForOwnerModule", "getScopeForOwnerModule", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "scopeForOwnerModule$delegate", "Lorg/jetbrains/kotlin/storage/NotNullLazyValue;", "getScope", "kotlinTypeRefiner", "(Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;)Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "Companion", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopesHolderForClass<T extends MemberScope> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ScopesHolderForClass.class, "scopeForOwnerModule", "getScopeForOwnerModule()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", 0)};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ClassDescriptor classDescriptor;
    private final KotlinTypeRefiner kotlinTypeRefinerForOwnerModule;
    private final Function1<KotlinTypeRefiner, T> scopeFactory;

    /* JADX INFO: renamed from: scopeForOwnerModule$delegate, reason: from kotlin metadata */
    private final NotNullLazyValue scopeForOwnerModule;

    private ScopesHolderForClass(ClassDescriptor classDescriptor, StorageManager storageManager, Function1<? super KotlinTypeRefiner, ? extends T> function1, KotlinTypeRefiner kotlinTypeRefiner) {
        this.classDescriptor = classDescriptor;
        this.scopeFactory = function1;
        this.kotlinTypeRefinerForOwnerModule = kotlinTypeRefiner;
        this.scopeForOwnerModule = storageManager.createLazyValue(new Function0() { // from class: rxc
            public final Object invoke() {
                return ScopesHolderForClass.a(this.b);
            }
        });
    }

    public static MemberScope a(ScopesHolderForClass scopesHolderForClass) {
        return (MemberScope) scopesHolderForClass.scopeFactory.invoke(scopesHolderForClass.kotlinTypeRefinerForOwnerModule);
    }

    public static MemberScope b(ScopesHolderForClass scopesHolderForClass, KotlinTypeRefiner kotlinTypeRefiner) {
        return (MemberScope) scopesHolderForClass.scopeFactory.invoke(kotlinTypeRefiner);
    }

    private final T getScopeForOwnerModule() {
        return (T) StorageKt.getValue(this.scopeForOwnerModule, this, $$delegatedProperties[0]);
    }

    public final T getScope(final KotlinTypeRefiner kotlinTypeRefiner) {
        kotlinTypeRefiner.getClass();
        if (!kotlinTypeRefiner.isRefinementNeededForModule(DescriptorUtilsKt.getModule(this.classDescriptor))) {
            return (T) getScopeForOwnerModule();
        }
        TypeConstructor typeConstructor = this.classDescriptor.getTypeConstructor();
        typeConstructor.getClass();
        return !kotlinTypeRefiner.isRefinementNeededForTypeConstructor(typeConstructor) ? (T) getScopeForOwnerModule() : (T) kotlinTypeRefiner.getOrPutScopeForClass(this.classDescriptor, new Function0() { // from class: sxc
            public final Object invoke() {
                return ScopesHolderForClass.b(this.b, kotlinTypeRefiner);
            }
        });
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JB\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\u00060\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/ScopesHolderForClass$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/descriptors/ScopesHolderForClass;", "T", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "kotlinTypeRefinerForOwnerModule", "Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;", "scopeFactory", "Lkotlin/Function1;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T extends MemberScope> ScopesHolderForClass<T> create(ClassDescriptor classDescriptor, StorageManager storageManager, KotlinTypeRefiner kotlinTypeRefinerForOwnerModule, Function1<? super KotlinTypeRefiner, ? extends T> scopeFactory) {
            classDescriptor.getClass();
            storageManager.getClass();
            kotlinTypeRefinerForOwnerModule.getClass();
            scopeFactory.getClass();
            return new ScopesHolderForClass<>(classDescriptor, storageManager, scopeFactory, kotlinTypeRefinerForOwnerModule, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ ScopesHolderForClass(ClassDescriptor classDescriptor, StorageManager storageManager, Function1 function1, KotlinTypeRefiner kotlinTypeRefiner, DefaultConstructorMarker defaultConstructorMarker) {
        this(classDescriptor, storageManager, function1, kotlinTypeRefiner);
    }
}
