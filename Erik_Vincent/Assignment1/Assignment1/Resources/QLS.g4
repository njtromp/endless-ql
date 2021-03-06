parser grammar QLS;

@header {
using Assignment1.Model.QLS;
using System.Drawing;
}

@parser::members
{
    private static string UnEscapeQLSString(string input) => input.Substring(1, input.Length - 2).Replace("\"\"", "\"");
}

options { tokenVocab=QLSLexer; }

// Add instructions to generate appropriate classes

stylesheet returns [Stylesheet result]
	: STYLESHEET ID OPEN_CB (page
		{$result.Pages.Add($page.result);}
		)+ CLOSE_CB
	;
page returns [Page result]
	: PAGE ID OPEN_CB (section
			{$result.Sections.Add($section.result);})*
		(default_style
			{})*
		CLOSE_CB
	;
section returns [Section result]
	: SECTION string questionStyle	//TODO: Check if this case is mandatory
		{$result.Contents.Add($questionStyle.result);}
	| SECTION string OPEN_CB (content
			{$result.Contents.Add($content.result);})*
		(default_style
			{})*
		CLOSE_CB
	;
content returns [IContent result]
	: section
		{$result = new Section();}
	| questionStyle
		{$result = $questionStyle.result;}
	;
questionStyle returns [QuestionStyle result]
	: QUESTION ID widget
		{$result = new QuestionStyle($ID.text);
		$result.Style.Widget = $widget.result;}
	| QUESTION ID OPEN_CB style CLOSE_CB
		{$result = new QuestionStyle($ID.text);
		 $result.Style = $style.result;}
	| QUESTION ID
		{$result = new QuestionStyle($ID.text);}
	;
style returns [Style result]
	: (WIDTH SEP NUMBER
		{$result.Width = int.Parse($NUMBER.text);}
	| FONT SEP string
		{$result.Label = $string.result;}
	| FONTSIZE SEP NUMBER
		{$result.FontSize = int.Parse($NUMBER.text);}
	| COLOR SEP HEXCOLORCODE
		{$result.Color = ColorTranslator.FromHtml($HEXCOLORCODE.text);}
	| widget)*	//TODO: Check if widget should have seperator too
	;
widget returns [Widget result]
	: WIDGET CHECKBOX
		{$result = new CheckBoxWidget();}
	| WIDGET RADIO OPEN_BR string COMMA string CLOSE_BR
		{$result = new RadioWidget(new []{""});}
	| WIDGET SLIDER
		{$result = new SliderWidget();}
	| WIDGET SPINBOX
		{$result = new SpinBoxWidget();}
	| WIDGET TEXTBOX
		{$result = new TextBoxWidget();}
	| WIDGET DROPDOWN
		{$result = new DropDownWidget();}
	;
default_style
	: DEFAULT type OPEN_CB style CLOSE_CB
	| DEFAULT type widget
	;
type
	: BOOLEAN_TYPE
	| DATE_TYPE
	| DECIMAL_TYPE
	| INTEGER_TYPE
	| MONEY_TYPE
	| STRING_TYPE
	;
string returns [string result]
	: STRING
		{$result = UnEscapeQLSString($STRING.text);}
	;
