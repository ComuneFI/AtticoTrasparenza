import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { DateFormatPipe } from './date-format-pipe';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule
  ],
  declarations: [
    DateFormatPipe
  ]
})
export class DateFormatPipeModule { }
