import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { Safe } from './safe-html-pipe';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule
  ],
  declarations: [
    Safe
  ],
  exports: [
    Safe
  ]
})
export class SafeHtmlPipeModule { }
