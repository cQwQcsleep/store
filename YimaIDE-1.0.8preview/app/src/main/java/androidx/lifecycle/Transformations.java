package androidx.lifecycle;

import androidx.arch.core.util.Function;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.lifecycle.Transformations;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u001c\u0010\u0004\u001a\u0018\u0012\t\u0012\u0007H\u0003¢\u0006\u0002\b\u0006\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u00060\u0005H\u0007\u001a8\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\bH\u0007\u001aJ\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012$\u0010\u0004\u001a \u0012\t\u0012\u0007H\u0003¢\u0006\u0002\b\u0006\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001¢\u0006\u0002\b\u00060\u0005H\u0007\u001a>\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\bH\u0007\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007¨\u0006\f"}, d2 = {"map", "Landroidx/lifecycle/LiveData;", "Y", "X", "transform", "Lkotlin/Function1;", "Lkotlin/jvm/JvmSuppressWildcards;", "mapFunction", "Landroidx/arch/core/util/Function;", "switchMap", "switchMapFunction", "distinctUntilChanged", "lifecycle-livedata_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Transformations {

    /* JADX INFO: renamed from: androidx.lifecycle.Transformations$switchMap$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bR\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"androidx/lifecycle/Transformations$switchMap$2", "Landroidx/lifecycle/Observer;", "liveData", "Landroidx/lifecycle/LiveData;", "getLiveData", "()Landroidx/lifecycle/LiveData;", "setLiveData", "(Landroidx/lifecycle/LiveData;)V", "onChanged", "", "value", "(Ljava/lang/Object;)V", "lifecycle-livedata_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass2 implements Observer {
        final /* synthetic */ MediatorLiveData $result;
        final /* synthetic */ Function $switchMapFunction;
        private LiveData liveData;

        public AnonymousClass2(Function function, MediatorLiveData mediatorLiveData) {
            this.$switchMapFunction = function;
            this.$result = mediatorLiveData;
        }

        public static Unit a(MediatorLiveData mediatorLiveData, Object obj) {
            mediatorLiveData.setValue(obj);
            return Unit.INSTANCE;
        }

        public final LiveData getLiveData() {
            return this.liveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(Object value) {
            LiveData liveData = (LiveData) this.$switchMapFunction.apply(value);
            LiveData liveData2 = this.liveData;
            if (liveData2 == liveData) {
                return;
            }
            if (liveData2 != null) {
                MediatorLiveData mediatorLiveData = this.$result;
                liveData2.getClass();
                mediatorLiveData.removeSource(liveData2);
            }
            this.liveData = liveData;
            if (liveData != null) {
                MediatorLiveData mediatorLiveData2 = this.$result;
                liveData.getClass();
                final MediatorLiveData mediatorLiveData3 = this.$result;
                mediatorLiveData2.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: wje
                    public final Object invoke(Object obj) {
                        return Transformations.AnonymousClass2.a(mediatorLiveData3, obj);
                    }
                }));
            }
        }

        public final void setLiveData(LiveData liveData) {
            this.liveData = liveData;
        }
    }

    public static Unit a(MediatorLiveData mediatorLiveData, Function1 function1, Object obj) {
        mediatorLiveData.setValue(function1.invoke(obj));
        return Unit.INSTANCE;
    }

    public static Unit b(MediatorLiveData mediatorLiveData, Function function, Object obj) {
        mediatorLiveData.setValue(function.apply(obj));
        return Unit.INSTANCE;
    }

    public static Unit c(MediatorLiveData mediatorLiveData, Object obj) {
        mediatorLiveData.setValue(obj);
        return Unit.INSTANCE;
    }

    public static Unit d(MediatorLiveData mediatorLiveData, Ref.BooleanRef booleanRef, Object obj) {
        T value = mediatorLiveData.getValue();
        if (booleanRef.element || ((value == 0 && obj != null) || (value != 0 && !Intrinsics.areEqual(value, obj)))) {
            booleanRef.element = false;
            mediatorLiveData.setValue(obj);
        }
        return Unit.INSTANCE;
    }

    public static final <X> LiveData<X> distinctUntilChanged(LiveData<X> liveData) {
        final MediatorLiveData mediatorLiveData;
        liveData.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        if (liveData.isInitialized()) {
            booleanRef.element = false;
            mediatorLiveData = new MediatorLiveData(liveData.getValue());
        } else {
            mediatorLiveData = new MediatorLiveData();
        }
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: tje
            public final Object invoke(Object obj) {
                return Transformations.d(mediatorLiveData, booleanRef, obj);
            }
        }));
        return mediatorLiveData;
    }

    public static Unit e(Function1 function1, Ref.ObjectRef objectRef, final MediatorLiveData mediatorLiveData, Object obj) {
        LiveData liveData = (LiveData) function1.invoke(obj);
        Object obj2 = objectRef.element;
        if (obj2 != liveData) {
            if (obj2 != null) {
                mediatorLiveData.removeSource((LiveData) obj2);
            }
            objectRef.element = liveData;
            if (liveData != null) {
                mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: vje
                    public final Object invoke(Object obj3) {
                        return Transformations.c(mediatorLiveData, obj3);
                    }
                }));
            }
        }
        return Unit.INSTANCE;
    }

    public static final <X, Y> LiveData<Y> map(LiveData<X> liveData, final Function1<X, Y> function1) {
        liveData.getClass();
        function1.getClass();
        final MediatorLiveData mediatorLiveData = liveData.isInitialized() ? new MediatorLiveData(function1.invoke(liveData.getValue())) : new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: sje
            public final Object invoke(Object obj) {
                return Transformations.a(mediatorLiveData, function1, obj);
            }
        }));
        return mediatorLiveData;
    }

    public static final <X, Y> LiveData<Y> switchMap(LiveData<X> liveData, final Function1<X, LiveData<Y>> function1) {
        LiveData liveData2;
        liveData.getClass();
        function1.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final MediatorLiveData mediatorLiveData = (liveData.isInitialized() && (liveData2 = (LiveData) function1.invoke(liveData.getValue())) != null && liveData2.isInitialized()) ? new MediatorLiveData(liveData2.getValue()) : new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: rje
            public final Object invoke(Object obj) {
                return Transformations.e(function1, objectRef, mediatorLiveData, obj);
            }
        }));
        return mediatorLiveData;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    public static final /* synthetic */ LiveData map(LiveData liveData, final Function function) {
        liveData.getClass();
        function.getClass();
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: uje
            public final Object invoke(Object obj) {
                return Transformations.b(mediatorLiveData, function, obj);
            }
        }));
        return mediatorLiveData;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    public static final /* synthetic */ LiveData switchMap(LiveData liveData, Function function) {
        liveData.getClass();
        function.getClass();
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new AnonymousClass2(function, mediatorLiveData));
        return mediatorLiveData;
    }
}
