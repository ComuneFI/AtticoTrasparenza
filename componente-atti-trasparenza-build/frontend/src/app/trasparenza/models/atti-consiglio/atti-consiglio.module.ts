import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';

import { FormsModule } from '@angular/forms';

import { AttiConsiglioRoutingModule } from './atti-consiglio-routing.module';
import { AttiConsiglioComponent } from './atti-consiglio.component';

import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

import { DettaglioAcComponent } from '../../modals/ac/dettaglio-ac.component';
import { DettaglioAcBModalComponent } from '../../modals/ac/modal-ac.component';

import { TabellaAcComponent } from '../../tables/tabella-ac/tabella-ac.component';
import { TabellaAcRowComponent } from '../../tables/tabella-ac-row/tabella-ac-row.component';
import { CercaAcComponent } from '../../filters/cerca-ac/cerca-ac.component';

import { SafeHtmlPipeModule } from '../../services/safe-html-pipe.module';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    CoreModule,
    SharedModule,
    FormsModule,
    DataTablesModule,
    AttiConsiglioRoutingModule,
    SafeHtmlPipeModule
  ],
  declarations: [
    DettaglioAcComponent,
    DettaglioAcBModalComponent,

    AttiConsiglioComponent,

    TabellaAcComponent,
    TabellaAcRowComponent,
    CercaAcComponent
  ]
})
export class AttiConsiglioModule { }
