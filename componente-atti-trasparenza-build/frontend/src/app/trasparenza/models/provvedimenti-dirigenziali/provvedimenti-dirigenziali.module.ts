import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';

import { FormsModule } from '@angular/forms';

import { ProvvedimentiDirigenzialiRoutingModule } from './provvedimenti-dirigenziali-routing.module';
import { ProvvedimentiDirigenzialiComponent } from './provvedimenti-dirigenziali.component';

import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

import { DettaglioPdComponent } from '../../modals/pd/dettaglio-pd.component';
import { DettaglioPdBModalComponent } from '../../modals/pd/modal-pd.component';

import { TabellaPdComponent } from '../../tables/tabella-pd/tabella-pd.component';
import { TabellaPdRowComponent } from '../../tables/tabella-pd-row/tabella-pd-row.component';
import { CercaPdComponent } from '../../filters/cerca-pd/cerca-pd.component';

import { SafeHtmlPipeModule } from '../../services/safe-html-pipe.module';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    CoreModule,
    SharedModule,
    FormsModule,
    DataTablesModule,
    ProvvedimentiDirigenzialiRoutingModule,
    SafeHtmlPipeModule
  ],
  declarations: [
    DettaglioPdComponent,
    DettaglioPdBModalComponent,

    ProvvedimentiDirigenzialiComponent,

    TabellaPdComponent,
    TabellaPdRowComponent,
    CercaPdComponent
  ]
})
export class ProvvedimentiDirigenzialiModule { }
