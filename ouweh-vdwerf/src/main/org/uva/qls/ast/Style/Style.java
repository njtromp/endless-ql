package org.uva.qls.ast.Style;

import org.uva.qls.ast.Style.StyleProperty.StyleProperty;
import org.uva.qls.ast.TreeNode;
import org.uva.qls.ast.Widget.Widget;

import java.util.List;

public class Style extends TreeNode {

    List<StyleProperty> styleProperties;
    Widget widget;

    public Style(List<StyleProperty> styleProperties, Widget widget){
        this.styleProperties = styleProperties;
        this.widget = widget;
    }
}
