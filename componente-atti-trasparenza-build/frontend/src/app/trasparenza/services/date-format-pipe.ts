import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe extends DatePipe implements PipeTransform {
  private translateService: TranslateService;
  transform(value: any, args?: any): any {
    return super.transform(value, this.translateService.instant('patternDate'));
  }
}