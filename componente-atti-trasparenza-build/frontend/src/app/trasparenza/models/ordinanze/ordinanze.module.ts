import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';

import { FormsModule } from '@angular/forms';

import { OrdinanzeRoutingModule } from './ordinanze-routing.module';
import { OrdinanzeComponent } from './ordinanze.component';

import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

import { DettaglioOrdComponent } from '../../modals/ord/dettaglio-ord.component';
import { DettaglioOrdBModalComponent } from '../../modals/ord/modal-ord.component';

import { TabellaOrdComponent } from '../../tables/tabella-ord/tabella-ord.component';
import { TabellaOrdRowComponent } from '../../tables/tabella-ord-row/tabella-ord-row.component';
import { CercaOrdComponent } from '../../filters/cerca-ord/cerca-ord.component';

import { SafeHtmlPipeModule } from '../../services/safe-html-pipe.module';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    CoreModule,
    SharedModule,
    FormsModule,
    DataTablesModule,
    OrdinanzeRoutingModule,
    SafeHtmlPipeModule
  ],
  declarations: [
    DettaglioOrdComponent,
    DettaglioOrdBModalComponent,

    OrdinanzeComponent,

    TabellaOrdComponent,
    TabellaOrdRowComponent,
    CercaOrdComponent
  ]
})
export class OrdinanzeModule { }
