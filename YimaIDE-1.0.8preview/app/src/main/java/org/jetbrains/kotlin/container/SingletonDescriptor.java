package org.jetbrains.kotlin.container;

import defpackage.mdd;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.SingletonDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0017\u001a\u00020\nH\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0004J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH$J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/container/SingletonDescriptor;", "Lorg/jetbrains/kotlin/container/ComponentDescriptor;", "Ljava/io/Closeable;", "container", "Lorg/jetbrains/kotlin/container/ComponentContainer;", "<init>", "(Lorg/jetbrains/kotlin/container/ComponentContainer;)V", "getContainer", "()Lorg/jetbrains/kotlin/container/ComponentContainer;", "instance", Argument.Delimiters.none, "state", "Lorg/jetbrains/kotlin/container/ComponentState;", "getState", "()Lorg/jetbrains/kotlin/container/ComponentState;", "setState", "(Lorg/jetbrains/kotlin/container/ComponentState;)V", "disposableObjects", "Ljava/util/ArrayList;", "getDisposableObjects", "()Ljava/util/ArrayList;", "disposableObjects$delegate", "Lkotlin/Lazy;", "getValue", "registerDisposableObject", Argument.Delimiters.none, "ownedObject", "createInstance", "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "disposeImpl", "close", "shouldInjectProperties", Argument.Delimiters.none, "getShouldInjectProperties", "()Z", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class SingletonDescriptor implements Closeable, ComponentDescriptor {
    private final ComponentContainer container;

    /* JADX INFO: renamed from: disposableObjects$delegate, reason: from kotlin metadata */
    private final Lazy disposableObjects;
    private Object instance;
    private ComponentState state;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ComponentState.values().length];
            try {
                iArr[ComponentState.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ComponentState.Initializing.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ComponentState.Initialized.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ComponentState.Corrupted.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ComponentState.Disposing.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ComponentState.Disposed.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SingletonDescriptor(ComponentContainer componentContainer) {
        componentContainer.getClass();
        this.container = componentContainer;
        this.state = ComponentState.Null;
        this.disposableObjects = LazyKt.lazy(new Function0() { // from class: ndd
            public final Object invoke() {
                return SingletonDescriptor.c();
            }
        });
    }

    public static ArrayList c() {
        return new ArrayList();
    }

    private final void createInstance(ComponentContainer container) throws ContainerConsistencyException, IOException {
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
                try {
                    this.instance = createInstance(container.createResolveContext(this));
                    return;
                } catch (Throwable th) {
                    this.state = ComponentState.Corrupted;
                    Iterator<Closeable> it = getDisposableObjects().iterator();
                    it.getClass();
                    while (it.hasNext()) {
                        Closeable next = it.next();
                        next.getClass();
                        next.close();
                    }
                    throw th;
                }
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                mdd.a("Could not create the component ", this, " because it is being initialized. Do we have undetected circular dependency?");
                return;
            case 3:
                mdd.a("Could not get the component ", this, ". Instance is null in Initialized state");
                return;
            case 4:
                mdd.a("Could not get the component ", this, " because it is corrupted");
                return;
            case 5:
                mdd.a("Could not get the component ", this, " because it is being disposed");
                return;
            case 6:
                mdd.a("Could not get the component ", this, " because it is already disposed");
                return;
            default:
                bu8.a();
                return;
        }
    }

    private final void disposeImpl() {
        Object obj = this.instance;
        this.state = ComponentState.Disposing;
        this.instance = null;
        try {
            if (obj instanceof Closeable) {
                ((Closeable) obj).close();
            }
            Iterator<Closeable> it = getDisposableObjects().iterator();
            it.getClass();
            while (it.hasNext()) {
                Closeable next = it.next();
                next.getClass();
                next.close();
            }
            this.state = ComponentState.Disposed;
        } catch (Throwable th) {
            this.state = ComponentState.Corrupted;
            throw th;
        }
    }

    private final ArrayList<Closeable> getDisposableObjects() {
        return (ArrayList) this.disposableObjects.getValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws ContainerConsistencyException {
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
            case 4:
                return;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                throw new ContainerConsistencyException("The component is being initialized and cannot be disposed.");
            case 3:
                disposeImpl();
                return;
            case 5:
                throw new ContainerConsistencyException("The component is already in disposing state.");
            case 6:
                throw new ContainerConsistencyException("The component has already been destroyed.");
            default:
                bu8.a();
                return;
        }
    }

    public abstract Object createInstance(ValueResolveContext context);

    public final ComponentContainer getContainer() {
        return this.container;
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public boolean getShouldInjectProperties() {
        return true;
    }

    public final ComponentState getState() {
        return this.state;
    }

    @Override // org.jetbrains.kotlin.container.ValueDescriptor
    public Object getValue() throws ContainerConsistencyException, IOException {
        ComponentState componentState = this.state;
        if (componentState == ComponentState.Corrupted) {
            mdd.a("Component descriptor ", this, " is corrupted and cannot be accessed");
            return null;
        }
        if (componentState == ComponentState.Disposed) {
            mdd.a("Component descriptor ", this, " is disposed and cannot be accessed");
            return null;
        }
        if (this.instance == null) {
            createInstance(this.container);
        }
        Object obj = this.instance;
        obj.getClass();
        return obj;
    }

    public final void registerDisposableObject(Closeable ownedObject) {
        ownedObject.getClass();
        getDisposableObjects().add(ownedObject);
    }

    public final void setState(ComponentState componentState) {
        componentState.getClass();
        this.state = componentState;
    }
}
