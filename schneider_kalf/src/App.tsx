import * as React from 'react';
import { FormComponent } from './components/form_component/FormComponent';
import { sampleForm } from "./mock/sampleForm";
import 'bootstrap/dist/css/bootstrap.css';

const peg = require('pegjs');

class App extends React.Component {
  componentDidMount() {
    const javascriptGrammar = require('!raw-loader!./parsing/grammars/javascript.pegjs');
    const javascriptParser = peg.generate(javascriptGrammar);
    const ast = javascriptParser.parse("if(true && var1){alert(\"asd\");alert(\"fgh\");}else{return null;}");
    console.log(ast); // OUTPUT: AST
  }

  render() {
    return (
        <div className="app container">
          <FormComponent form={sampleForm}/>
        </div>
    );
  }
}

export default App;