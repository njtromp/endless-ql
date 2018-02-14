import { BooleanField } from "../rendering/components/field/BooleanField";
import { MoneyField } from "../rendering/components/field/MoneyField";
import { TextField } from "../rendering/components/field/TextField";
import BooleanWrapper from "../form/values/BooleanWrapper";
import MoneyWrapper from "../form/values/MoneyWrapper";
import StringWrapper from "../form/values/StringWrapper";

export const fieldComponentsMapping = [
  {
    value: BooleanWrapper,
    component: BooleanField
  },
  {
    value: MoneyWrapper,
    component: MoneyField
  },
  {
    value: StringWrapper,
    component: TextField
  }
];