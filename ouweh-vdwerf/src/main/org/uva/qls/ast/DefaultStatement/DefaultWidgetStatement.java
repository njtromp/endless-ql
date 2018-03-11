package org.uva.qls.ast.DefaultStatement;

import org.uva.ql.ast.type.Type;
import org.uva.qls.ast.Widget.Widget;

public class DefaultWidgetStatement extends DefaultStatement {

    private Type type;
    private Widget widget;

    public DefaultWidgetStatement(Type type, Widget widget){
        this.type = type;
        this.widget = widget;
    }
}
