package com.sun.tools.javac.code;

import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Pair;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SymbolMetadata {
    private final Symbol sym;
    private static final List<Attribute.Compound> DECL_NOT_STARTED = List.of((Object) null);
    private static final List<Attribute.Compound> DECL_IN_PROGRESS = List.of((Object) null);
    private List<Attribute.Compound> attributes = DECL_NOT_STARTED;
    private List<Attribute.TypeCompound> type_attributes = List.nil();
    private List<Attribute.TypeCompound> init_type_attributes = List.nil();
    private List<Attribute.TypeCompound> clinit_type_attributes = List.nil();

    public SymbolMetadata(Symbol symbol) {
        this.sym = symbol;
    }

    private List<Attribute.Compound> filterDeclSentinels(List<Attribute.Compound> list) {
        return (list == DECL_IN_PROGRESS || list == DECL_NOT_STARTED) ? List.nil() : list;
    }

    private boolean isStarted() {
        return this.attributes != DECL_NOT_STARTED;
    }

    private List<Attribute.Compound> removeFromCompoundList(List<Attribute.Compound> list, Attribute.Compound compound) {
        ListBuffer listBuffer = new ListBuffer();
        for (Attribute.Compound compound2 : list) {
            if (compound2 != compound) {
                listBuffer.add(compound2);
            }
        }
        return listBuffer.toList();
    }

    public SymbolMetadata append(List<Attribute.Compound> list) {
        this.attributes = filterDeclSentinels(this.attributes);
        if (list.isEmpty()) {
            return this;
        }
        if (this.attributes.isEmpty()) {
            this.attributes = list;
            return this;
        }
        this.attributes = this.attributes.appendList(list);
        return this;
    }

    public SymbolMetadata appendClassInitTypeAttributes(List<Attribute.TypeCompound> list) {
        if (list.isEmpty()) {
            return this;
        }
        if (this.clinit_type_attributes.isEmpty()) {
            this.clinit_type_attributes = list;
            return this;
        }
        this.clinit_type_attributes = this.clinit_type_attributes.appendList(list);
        return this;
    }

    public SymbolMetadata appendInitTypeAttributes(List<Attribute.TypeCompound> list) {
        if (list.isEmpty()) {
            return this;
        }
        if (this.init_type_attributes.isEmpty()) {
            this.init_type_attributes = list;
            return this;
        }
        this.init_type_attributes = this.init_type_attributes.appendList(list);
        return this;
    }

    public SymbolMetadata appendUniqueTypes(List<Attribute.TypeCompound> list) {
        if (!list.isEmpty()) {
            if (this.type_attributes.isEmpty()) {
                this.type_attributes = list;
                return this;
            }
            for (Attribute.TypeCompound typeCompound : list) {
                if (!this.type_attributes.contains(typeCompound)) {
                    this.type_attributes = this.type_attributes.append(typeCompound);
                }
            }
        }
        return this;
    }

    public List<Attribute.TypeCompound> getClassInitTypeAttributes() {
        return this.clinit_type_attributes;
    }

    public List<Attribute.Compound> getDeclarationAttributes() {
        return filterDeclSentinels(this.attributes);
    }

    public List<Attribute.TypeCompound> getInitTypeAttributes() {
        return this.init_type_attributes;
    }

    public List<Attribute.TypeCompound> getTypeAttributes() {
        return this.type_attributes;
    }

    public boolean isEmpty() {
        return !isStarted() || pendingCompletion() || this.attributes.isEmpty();
    }

    public boolean isTypesEmpty() {
        return this.type_attributes.isEmpty();
    }

    public boolean pendingCompletion() {
        return this.attributes == DECL_IN_PROGRESS;
    }

    public SymbolMetadata prepend(List<Attribute.Compound> list) {
        this.attributes = filterDeclSentinels(this.attributes);
        if (list.isEmpty()) {
            return this;
        }
        if (this.attributes.isEmpty()) {
            this.attributes = list;
            return this;
        }
        this.attributes = this.attributes.prependList(list);
        return this;
    }

    public void removeDeclarationMetadata(Attribute.Compound compound) {
        boolean zContains = this.attributes.contains(compound);
        List<Attribute.Compound> list = this.attributes;
        if (zContains) {
            this.attributes = removeFromCompoundList(list, compound);
            return;
        }
        for (Attribute.Compound compound2 : list) {
            if (compound2.isSynthesized() && !compound2.values.isEmpty()) {
                Pair<Symbol.MethodSymbol, Attribute> pair = compound2.values.get(0);
                if (pair.fst.getSimpleName().contentEquals("value")) {
                    Attribute attribute = pair.snd;
                    if (attribute instanceof Attribute.Array) {
                        Attribute[] attributeArr = ((Attribute.Array) attribute).values;
                        if (attributeArr.length != 0) {
                            Attribute attribute2 = attributeArr[0];
                            if ((attribute2 instanceof Attribute.Compound) && attribute2.type == compound.type) {
                                this.attributes = removeFromCompoundList(this.attributes, compound2);
                            }
                        }
                    }
                }
            }
        }
    }

    public SymbolMetadata reset() {
        this.attributes = DECL_IN_PROGRESS;
        return this;
    }

    public void setAttributes(SymbolMetadata symbolMetadata) {
        symbolMetadata.getClass();
        setDeclarationAttributes(symbolMetadata.getDeclarationAttributes());
        if ((this.sym.flags() & Flags.BRIDGE) != 0) {
            Assert.check(symbolMetadata.sym.kind == Kinds.Kind.MTH);
            ListBuffer listBuffer = new ListBuffer();
            for (Attribute.TypeCompound typeCompound : symbolMetadata.getTypeAttributes()) {
                if (!typeCompound.position.type.isLocal()) {
                    listBuffer.append(typeCompound);
                }
            }
            setTypeAttributes(listBuffer.toList());
        } else {
            setTypeAttributes(symbolMetadata.getTypeAttributes());
        }
        if (this.sym.kind == Kinds.Kind.TYP) {
            setInitTypeAttributes(symbolMetadata.getInitTypeAttributes());
            setClassInitTypeAttributes(symbolMetadata.getClassInitTypeAttributes());
        }
    }

    public void setClassInitTypeAttributes(List<Attribute.TypeCompound> list) {
        list.getClass();
        this.clinit_type_attributes = list;
    }

    public void setDeclarationAttributes(List<Attribute.Compound> list) {
        Assert.check(pendingCompletion() || !isStarted());
        list.getClass();
        this.attributes = list;
    }

    public void setInitTypeAttributes(List<Attribute.TypeCompound> list) {
        list.getClass();
        this.init_type_attributes = list;
    }

    public void setTypeAttributes(List<Attribute.TypeCompound> list) {
        list.getClass();
        this.type_attributes = list;
    }
}
