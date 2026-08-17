package androidx.compose.runtime.saveable;

import androidx.compose.runtime.saveable.MapSaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001ax\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u00022:\u0010\u0004\u001a6\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n0\u0005¢\u0006\u0002\b\f2\"\u0010\r\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000e¨\u0006\u000f"}, d2 = {"mapSaver", "Landroidx/compose/runtime/saveable/Saver;", "T", "", "save", "Lkotlin/Function2;", "Landroidx/compose/runtime/saveable/SaverScope;", "Lkotlin/ParameterName;", "name", "value", "", "", "Lkotlin/ExtensionFunctionType;", "restore", "Lkotlin/Function1;", "runtime-saveable"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MapSaverKt {
    public static Object a(Function1 function1, List list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (list.size() % 2 != 0) {
            k2d.a("non-zero remainder");
            return null;
        }
        for (int i = 0; i < list.size(); i += 2) {
            Object obj = list.get(i);
            obj.getClass();
            linkedHashMap.put((String) obj, list.get(i + 1));
        }
        return function1.invoke(linkedHashMap);
    }

    public static List b(Function2 function2, SaverScope saverScope, Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : ((Map) function2.invoke(saverScope, obj)).entrySet()) {
            arrayList.add(entry.getKey());
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }

    public static final <T> Saver<T, Object> mapSaver(final Function2<? super SaverScope, ? super T, ? extends Map<String, ? extends Object>> function2, final Function1<? super Map<String, ? extends Object>, ? extends T> function1) {
        return ListSaverKt.listSaver(new Function2() { // from class: fu9
            public final Object invoke(Object obj, Object obj2) {
                return MapSaverKt.b(function2, (SaverScope) obj, obj2);
            }
        }, new Function1() { // from class: gu9
            public final Object invoke(Object obj) {
                return MapSaverKt.a(function1, (List) obj);
            }
        });
    }
}
