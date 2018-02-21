import * as React from 'react';
import { FormGroup, Label, InputGroup, Input } from 'reactstrap';
import Field from "../../../form/nodes/fields/FieldNode";

export interface MoneyFieldProps {
  value: number;
  field: Field;
  onChange: (value: any) => void;
}

export const MoneyField: React.SFC<MoneyFieldProps> = (props) => {
  return (
      <FormGroup>
        <Label for={props.field.identifier}>{props.field.label}</Label>
        <InputGroup>
          <Input
              onChange={e => props.onChange(e.target.value)}
              name={props.field.identifier}
              type="number"
              step={0.01}
              value={props.value || ""}
          />
          <div className="input-group-append">
            <span className="input-group-text">€</span>
          </div>
        </InputGroup>
      </FormGroup>
  );
};