package org.uva.qls.ast.DefaultStatement;

import org.uva.ql.ast.type.Type;
import org.uva.qls.ast.Style.Style;

public class DefaultStyleStatement extends DefaultStatement {

    private Type type;
    private Style style;

    public DefaultStyleStatement(Type type, Style style){
        this.type = type;
        this.style = style;
    }
}
