package org.jetbrains.kotlin.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.tower.WrongResolutionToClassifier;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/tower/WrongResolutionToClassifier;", "", "message", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/Name;", "", "<init>", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;)V", "getMessage", "()Lkotlin/jvm/functions/Function1;", "TYPE_PARAMETER_AS_VALUE", "TYPE_PARAMETER_AS_FUNCTION", "INTERFACE_AS_VALUE", "INTERFACE_AS_FUNCTION", "EXPECT_CLASS_AS_FUNCTION", "CLASS_AS_VALUE", "INNER_CLASS_CONSTRUCTOR_NO_RECEIVER", "OBJECT_AS_FUNCTION", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum WrongResolutionToClassifier {
    TYPE_PARAMETER_AS_VALUE(new Function1() { // from class: avf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.t((Name) obj);
        }
    }),
    TYPE_PARAMETER_AS_FUNCTION(new Function1() { // from class: bvf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.m((Name) obj);
        }
    }),
    INTERFACE_AS_VALUE(new Function1() { // from class: cvf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.r((Name) obj);
        }
    }),
    INTERFACE_AS_FUNCTION(new Function1() { // from class: dvf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.i((Name) obj);
        }
    }),
    EXPECT_CLASS_AS_FUNCTION(new Function1() { // from class: evf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.d((Name) obj);
        }
    }),
    CLASS_AS_VALUE(new Function1() { // from class: fvf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.b((Name) obj);
        }
    }),
    INNER_CLASS_CONSTRUCTOR_NO_RECEIVER(new Function1() { // from class: gvf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.g((Name) obj);
        }
    }),
    OBJECT_AS_FUNCTION(new Function1() { // from class: hvf
        public final Object invoke(Object obj) {
            return WrongResolutionToClassifier.n((Name) obj);
        }
    });

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final Function1<Name, String> message;

    WrongResolutionToClassifier(Function1 function1) {
        this.message = function1;
    }

    public static String b(Name name) {
        name.getClass();
        return "Class " + name + " does not have companion object";
    }

    public static String d(Name name) {
        name.getClass();
        return "Expected class " + name + " does not have default constructor";
    }

    public static String g(Name name) {
        name.getClass();
        return "Constructor of inner class " + name + " can be called only with receiver of containing class";
    }

    public static EnumEntries<WrongResolutionToClassifier> getEntries() {
        return $ENTRIES;
    }

    public static String i(Name name) {
        name.getClass();
        return "Interface " + name + " does not have constructors";
    }

    public static String m(Name name) {
        name.getClass();
        return "Type parameter " + name + " cannot be called as function";
    }

    public static String n(Name name) {
        name.getClass();
        return "Function 'invoke()' is not found in object " + name;
    }

    public static String r(Name name) {
        name.getClass();
        return "Interface " + name + " does not have companion object";
    }

    public static String t(Name name) {
        name.getClass();
        return "Type parameter " + name + " cannot be used as value";
    }

    public final Function1<Name, String> getMessage() {
        return this.message;
    }
}
