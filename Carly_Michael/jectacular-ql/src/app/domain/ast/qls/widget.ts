import {WidgetType} from './widget-type';

export class Widget {
  constructor(public type: WidgetType, public labels: string[]) { }
}
