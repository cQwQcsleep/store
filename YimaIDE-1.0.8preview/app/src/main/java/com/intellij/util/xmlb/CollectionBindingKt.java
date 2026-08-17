package com.intellij.util.xmlb;

import com.intellij.serialization.MutableAccessor;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.XCollection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¨\u0006\n"}, d2 = {"createCollectionBinding", "Lcom/intellij/util/xmlb/RootBinding;", "serializer", "Lcom/intellij/util/xmlb/Serializer;", "itemType", "Ljava/lang/Class;", "accessor", "Lcom/intellij/serialization/MutableAccessor;", "isArray", "", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CollectionBindingKt {
    /* JADX WARN: Code duplicated, block: B:10:0x004c  */
    public static final RootBinding createCollectionBinding(Serializer serializer, Class<?> cls, MutableAccessor mutableAccessor, boolean z) {
        String strElementName;
        Class<?>[] clsArr;
        boolean z2;
        boolean z3;
        String strElementValueAttribute;
        serializer.getClass();
        cls.getClass();
        Class<?>[] clsArr2 = ArrayUtilRt.EMPTY_CLASS_ARRAY;
        if (mutableAccessor == null) {
            strElementName = "option";
            clsArr = clsArr2;
            z2 = true;
            z3 = true;
            strElementValueAttribute = "value";
        } else {
            XCollection xCollection = (XCollection) mutableAccessor.getAnnotation(XCollection.class);
            if (xCollection != null) {
                Class<?>[] elementTypes = XmlSerializerUtil.getElementTypes(xCollection);
                strElementName = xCollection.elementName();
                clsArr = elementTypes;
                z3 = true;
                strElementValueAttribute = xCollection.valueAttributeName();
                z2 = false;
            } else {
                AbstractCollection abstractCollection = (AbstractCollection) mutableAccessor.getAnnotation(AbstractCollection.class);
                if (abstractCollection != null) {
                    boolean zSurroundWithTag = abstractCollection.surroundWithTag();
                    boolean zSortOrderedSet = abstractCollection.sortOrderedSet();
                    Class<?>[] elementTypes2 = XmlSerializerUtil.getElementTypes(abstractCollection);
                    String strElementTag = abstractCollection.elementTag();
                    strElementValueAttribute = abstractCollection.elementValueAttribute();
                    z3 = zSortOrderedSet;
                    z2 = zSurroundWithTag;
                    clsArr = elementTypes2;
                    strElementName = strElementTag;
                } else {
                    strElementName = "option";
                    clsArr = clsArr2;
                    z2 = true;
                    z3 = true;
                    strElementValueAttribute = "value";
                }
            }
        }
        clsArr.getClass();
        return new CollectionBinding(strElementName, strElementValueAttribute, z2, z3, cls, clsArr, serializer, z ? ArrayStrategy.INSTANCE : CollectionStrategyImpl.INSTANCE);
    }
}
