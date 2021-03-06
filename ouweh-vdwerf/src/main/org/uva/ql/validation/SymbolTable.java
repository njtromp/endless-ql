package org.uva.ql.validation;

import org.uva.ql.ast.type.Type;

import java.util.HashMap;

public class SymbolTable {

    private final HashMap<String, Type> table;

    SymbolTable() {

        this.table = new HashMap<>();
    }

    public int size() {
        return this.table.size();
    }

    public void add(String name, Type type) {
        this.table.put(name, type);
    }

    public Type getTypeByName(String name) {
        return this.table.get(name);
    }

    public boolean contains(String name) {
        return this.table.containsKey(name);
    }

    public String toString() {
        return table.toString();
    }
}
