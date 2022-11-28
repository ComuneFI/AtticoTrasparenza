import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';

import { FormsModule } from '@angular/forms';

import { DeliberazioniRoutingModule } from './deliberazioni-routing.module';
import { DeliberazioniComponent } from './deliberazioni.component';

import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

import { DettaglioDelComponent } from '../../modals/del/dettaglio-del.component';
import { DettaglioDelBModalComponent } from '../../modals/del/modal-del.component';

import { TabellaDelComponent } from '../../tables/tabella-del/tabella-del.component';
import { TabellaDelRowComponent } from '../../tables/tabella-del-row/tabella-del-row.component';
import { CercaDelComponent } from '../../filters/cerca-del/cerca-del.component';

import { SafeHtmlPipeModule } from '../../services/safe-html-pipe.module';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    CoreModule,
    SharedModule,
    FormsModule,
    DataTablesModule,
    DeliberazioniRoutingModule,
    SafeHtmlPipeModule
  ],
  declarations: [
    DettaglioDelComponent,
    DettaglioDelBModalComponent,

    DeliberazioniComponent,

    TabellaDelComponent,
    TabellaDelRowComponent,
    CercaDelComponent
  ]
})
export class DeliberazioniModule { }
