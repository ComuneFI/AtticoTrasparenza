import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { QuoteService } from './quote.service';

import { TestModule } from '../test/test.module';
import { ArticoloComponent } from '../articolo/articolo.component';
import { TableComponent } from '../table/table.component';
import { TableRowComponent } from '../table-row/table-row.component';

import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    CoreModule,
    SharedModule,
    TestModule,
    DataTablesModule,
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent,
    ArticoloComponent,
    TableRowComponent,
    TableComponent
  ],
  providers: [
    QuoteService
  ]
})
export class HomeModule { }
