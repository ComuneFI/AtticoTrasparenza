import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';

import { FormsModule } from '@angular/forms';

import { DecretiRoutingModule } from './decreti-routing.module';
import { DecretiComponent } from './decreti.component';

import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

import { DettaglioDecComponent } from '../../modals/dec/dettaglio-dec.component';
import { DettaglioDecBModalComponent } from '../../modals/dec/modal-dec.component';

import { TabellaDecComponent } from '../../tables/tabella-dec/tabella-dec.component';
import { TabellaDecRowComponent } from '../../tables/tabella-dec-row/tabella-dec-row.component';
import { CercaDecComponent } from '../../filters/cerca-dec/cerca-dec.component';

import { SafeHtmlPipeModule } from '../../services/safe-html-pipe.module';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    CoreModule,
    SharedModule,
    FormsModule,
    DataTablesModule,
    DecretiRoutingModule,
    SafeHtmlPipeModule
  ],
  declarations: [
    DettaglioDecComponent,
    DettaglioDecBModalComponent,

    DecretiComponent,

    TabellaDecComponent,
    TabellaDecRowComponent,
    CercaDecComponent
  ]
})
export class DecretiModule { }
