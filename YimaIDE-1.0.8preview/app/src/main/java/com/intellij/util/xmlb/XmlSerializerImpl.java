package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.JDOMExternalizableStringList;
import com.intellij.serialization.ClassUtil;
import com.intellij.serialization.MutableAccessor;
import com.intellij.util.xmlb.annotations.CollectionBean;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.XMap;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jdom.Content;
import org.jdom.Element;
import org.jdom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class XmlSerializerImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final XmlSerializer serializer = new XmlSerializer();

    public static final class XmlSerializer extends XmlSerializerBase {
        private Reference<Map<Type, Binding>> bindings;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 1 || i == 2) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[(i == 1 || i == 2) ? 3 : 2];
            if (i == 1) {
                objArr[0] = "aClass";
            } else if (i != 2) {
                objArr[0] = "com/intellij/util/xmlb/XmlSerializerImpl$XmlSerializer";
            } else {
                objArr[0] = "originalType";
            }
            if (i == 1 || i == 2) {
                objArr[1] = "com/intellij/util/xmlb/XmlSerializerImpl$XmlSerializer";
            } else if (i != 3) {
                objArr[1] = "getBindingCacheMap";
            } else {
                objArr[1] = "getRootBinding";
            }
            if (i == 1 || i == 2) {
                objArr[2] = "getRootBinding";
            }
            String str2 = String.format(str, objArr);
            if (i != 1 && i != 2) {
                throw new IllegalStateException(str2);
            }
            throw new IllegalArgumentException(str2);
        }

        private Map<Type, Binding> getBindingCacheMap() {
            Reference<Map<Type, Binding>> reference = this.bindings;
            Map<Type, Binding> map = reference == null ? null : reference.get();
            if (map != null) {
                return map;
            }
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            this.bindings = new SoftReference(concurrentHashMap);
            return concurrentHashMap;
        }

        @Override // com.intellij.util.xmlb.Serializer
        public synchronized Binding getRootBinding(Class<?> cls, Type type) {
            Binding bindingCreateClassBinding;
            if (cls == null) {
                try {
                    $$$reportNull$$$0(1);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (type == null) {
                $$$reportNull$$$0(2);
            }
            Map<Type, Binding> bindingCacheMap = getBindingCacheMap();
            bindingCreateClassBinding = bindingCacheMap.get(type);
            if (bindingCreateClassBinding == null) {
                bindingCreateClassBinding = XmlSerializerImpl.createClassBinding(cls, null, type, this);
                if (bindingCreateClassBinding == null) {
                    bindingCreateClassBinding = new BeanBinding(cls);
                }
                bindingCacheMap.put(type, bindingCreateClassBinding);
                try {
                    bindingCreateClassBinding.init(type, this);
                } catch (Error | RuntimeException e) {
                    bindingCacheMap.remove(type);
                    throw e;
                }
            }
            return bindingCreateClassBinding;
        }
    }

    public static abstract class XmlSerializerBase implements Serializer {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "aClass";
            } else {
                objArr[0] = "type";
            }
            objArr[1] = "com/intellij/util/xmlb/XmlSerializerImpl$XmlSerializerBase";
            objArr[2] = "getBinding";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.xmlb.Serializer
        public final Binding getBinding(Class<?> cls, Type type) {
            if (cls == null) {
                $$$reportNull$$$0(0);
            }
            if (type == null) {
                $$$reportNull$$$0(1);
            }
            if (ClassUtil.isPrimitive(cls)) {
                return null;
            }
            return getRootBinding(cls, type);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5 || i == 15 || i == 16 || i == 19 || i == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 5 || i == 15 || i == 16 || i == 19 || i == 20) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "originalType";
                break;
            case 2:
                objArr[0] = "serializer";
                break;
            case 3:
                objArr[0] = "object";
                break;
            case 4:
            case 5:
            case 15:
            case 16:
            case 19:
            case 20:
                objArr[0] = "com/intellij/util/xmlb/XmlSerializerImpl";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 13:
                objArr[0] = "valueClass";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
                objArr[0] = "host";
                break;
            case 8:
            case 12:
                objArr[0] = "accessor";
                break;
            case 11:
            case 14:
                objArr[0] = "value";
                break;
            case 17:
                objArr[0] = "element";
                break;
            case 18:
                objArr[0] = "defaultText";
                break;
            default:
                objArr[0] = "aClass";
                break;
        }
        if (i == 4 || i == 5) {
            objArr[1] = "serialize";
        } else if (i == 15 || i == 16) {
            objArr[1] = "convertToString";
        } else if (i == 19 || i == 20) {
            objArr[1] = "getTextValue";
        } else {
            objArr[1] = "com/intellij/util/xmlb/XmlSerializerImpl";
        }
        switch (i) {
            case 3:
                objArr[2] = "serialize";
                break;
            case 4:
            case 5:
            case 15:
            case 16:
            case 19:
            case 20:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "convert";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[2] = "doSet";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[2] = "callFromStringIfDefined";
                break;
            case 14:
                objArr[2] = "convertToString";
                break;
            case 17:
            case 18:
                objArr[2] = "getTextValue";
                break;
            default:
                objArr[2] = "createClassBinding";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5 && i != 15 && i != 16 && i != 19 && i != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private static boolean callFromStringIfDefined(Object obj, String str, MutableAccessor mutableAccessor, Class<?> cls) {
        if (obj == null) {
            $$$reportNull$$$0(10);
        }
        if (str == null) {
            $$$reportNull$$$0(11);
        }
        if (mutableAccessor == null) {
            $$$reportNull$$$0(12);
        }
        if (cls == null) {
            $$$reportNull$$$0(13);
        }
        try {
            Method method = cls.getMethod("fromText", String.class);
            try {
                method.setAccessible(true);
            } catch (SecurityException unused) {
            }
            mutableAccessor.set(obj, method.invoke(null, str));
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            return false;
        }
    }

    public static Object convert(String str, Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(6);
        }
        if (str == null) {
            return null;
        }
        if (cls == String.class) {
            return str;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return Double.valueOf(Double.parseDouble(str));
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return Float.valueOf(Float.parseFloat(str));
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return Long.valueOf(Long.parseLong(str));
        }
        if (!cls.isEnum()) {
            if (!Date.class.isAssignableFrom(cls)) {
                return str;
            }
            try {
                return new Date(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                return new Date(0L);
            }
        }
        for (Object obj : cls.getEnumConstants()) {
            if (obj.toString().equals(str)) {
                return obj;
            }
        }
        return null;
    }

    public static Binding createClassBinding(Class<?> cls, MutableAccessor mutableAccessor, Type type, Serializer serializer2) {
        MapAnnotation mapAnnotation;
        boolean z = false;
        if (cls == null) {
            $$$reportNull$$$0(0);
        }
        if (type == null) {
            $$$reportNull$$$0(1);
        }
        if (serializer2 == null) {
            $$$reportNull$$$0(2);
        }
        if (cls.isArray()) {
            return Element.class.isAssignableFrom(cls.getComponentType()) ? new JDOMElementBinding(mutableAccessor) : CollectionBindingKt.createCollectionBinding(serializer2, cls.getComponentType(), mutableAccessor, true);
        }
        if (Collection.class.isAssignableFrom(cls) && (type instanceof ParameterizedType)) {
            return (mutableAccessor == null || ((CollectionBean) mutableAccessor.getAnnotation(CollectionBean.class)) == null) ? CollectionBindingKt.createCollectionBinding(serializer2, ClassUtil.typeToClass(((ParameterizedType) type).getActualTypeArguments()[0]), mutableAccessor, false) : new CompactCollectionBinding(mutableAccessor);
        }
        XMap xMap = null;
        if (!Map.class.isAssignableFrom(cls) || !(type instanceof ParameterizedType)) {
            if (mutableAccessor != null) {
                if (Element.class.isAssignableFrom(cls)) {
                    return new JDOMElementBinding(mutableAccessor);
                }
                if (cls == JDOMExternalizableStringList.class) {
                    return new CompactCollectionBinding(mutableAccessor);
                }
            }
            return null;
        }
        if (mutableAccessor != null) {
            XMap xMap2 = (XMap) mutableAccessor.getAnnotation(XMap.class);
            mapAnnotation = xMap2 == null ? (MapAnnotation) mutableAccessor.getAnnotation(MapAnnotation.class) : null;
            xMap = xMap2;
        } else {
            mapAnnotation = null;
        }
        if (xMap == null && (mapAnnotation == null || mapAnnotation.surroundWithTag())) {
            z = true;
        }
        return new MapBinding(mapAnnotation, xMap, cls, z);
    }

    public static void doSet(Object obj, String str, MutableAccessor mutableAccessor, Class<?> cls) {
        Object objValueOf;
        if (obj == null) {
            $$$reportNull$$$0(7);
        }
        if (mutableAccessor == null) {
            $$$reportNull$$$0(8);
        }
        if (cls == null) {
            $$$reportNull$$$0(9);
        }
        if (str == null) {
            mutableAccessor.set(obj, null);
            return;
        }
        if (cls == String.class) {
            mutableAccessor.set(obj, str);
            return;
        }
        if (cls == Integer.TYPE) {
            mutableAccessor.setInt(obj, Integer.parseInt(str));
            return;
        }
        if (cls == Boolean.TYPE) {
            mutableAccessor.setBoolean(obj, Boolean.parseBoolean(str));
            return;
        }
        if (cls == Double.TYPE) {
            mutableAccessor.setDouble(obj, Double.parseDouble(str));
            return;
        }
        if (cls == Float.TYPE) {
            mutableAccessor.setFloat(obj, Float.parseFloat(str));
            return;
        }
        if (cls == Long.TYPE) {
            mutableAccessor.setLong(obj, Long.parseLong(str));
            return;
        }
        if (cls == Short.TYPE) {
            mutableAccessor.setShort(obj, Short.parseShort(str));
            return;
        }
        if (cls.isEnum()) {
            mutableAccessor.set(obj, ClassUtil.stringToEnum(str, cls, false));
            return;
        }
        if (Date.class.isAssignableFrom(cls)) {
            try {
                mutableAccessor.set(obj, new Date(Long.parseLong(str)));
                return;
            } catch (NumberFormatException unused) {
                mutableAccessor.set(obj, new Date(0L));
                return;
            }
        }
        if (cls == Boolean.class) {
            objValueOf = Boolean.valueOf(Boolean.parseBoolean(str));
        } else if (cls == Integer.class) {
            objValueOf = Integer.valueOf(Integer.parseInt(str));
        } else if (cls == Short.class) {
            objValueOf = Short.valueOf(Short.parseShort(str));
        } else if (cls == Long.class) {
            objValueOf = Long.valueOf(Long.parseLong(str));
        } else if (cls == Double.class) {
            objValueOf = Double.valueOf(Double.parseDouble(str));
        } else if (cls == Float.class) {
            objValueOf = Float.valueOf(Float.parseFloat(str));
        } else if (callFromStringIfDefined(obj, str, mutableAccessor, cls)) {
            objValueOf = str;
            return;
        }
        objValueOf = str;
        mutableAccessor.set(obj, objValueOf);
    }

    public static String getTextValue(Element element, String str) {
        if (element == null) {
            $$$reportNull$$$0(17);
        }
        if (str == null) {
            $$$reportNull$$$0(18);
        }
        List content = element.getContent();
        int size = content.size();
        StringBuilder sb = null;
        for (int i = 0; i < size; i++) {
            Content content2 = (Content) content.get(i);
            if (content2 instanceof Text) {
                String value = content2.getValue();
                if (sb == null && i == size - 1) {
                    if (value == null) {
                        $$$reportNull$$$0(19);
                    }
                    return value;
                }
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(value);
            }
        }
        if (sb != null) {
            str = sb.toString();
        }
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        return str;
    }
}
