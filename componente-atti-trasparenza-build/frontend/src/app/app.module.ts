import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ServiceWorkerModule } from '@angular/service-worker';
import { TranslateModule } from '@ngx-translate/core';
import { NgbModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { environment, comuneBaseUrl } from '@env/environment';
import { CoreModule } from '@app/core';
import { SharedModule } from '@app/shared';

import { DataTablesModule, DataTableDirective } from 'angular-datatables';

import { HomeModule } from './home/home.module';
import { AboutModule } from './about/about.module';
import { NotFoundModule } from './404/not-found.module';
import { DeliberazioniModule } from './trasparenza/models/deliberazioni/deliberazioni.module';
import { ProvvedimentiDirigenzialiModule } from './trasparenza/models/provvedimenti-dirigenziali/provvedimenti-dirigenziali.module';
import { AttiConsiglioModule } from './trasparenza/models/atti-consiglio/atti-consiglio.module';
import { DecretiModule } from './trasparenza/models/decreti/decreti.module';
import { OrdinanzeModule } from './trasparenza/models/ordinanze/ordinanze.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

// import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
// import { HTTP_INTERCEPTORS } from '@angular/common/http';
// import { APIInterceptor } from './services/api-interceptor';

import { CommonUtils } from './trasparenza/utils/common-utils';

import { DettaglioAcBModalComponent } from './trasparenza/modals/ac/modal-ac.component';
import { DettaglioDecBModalComponent } from './trasparenza/modals/dec/modal-dec.component';
import { DettaglioDelBModalComponent } from './trasparenza/modals/del/modal-del.component';
import { DettaglioOrdBModalComponent } from './trasparenza/modals/ord/modal-ord.component';
import { DettaglioPdBModalComponent } from './trasparenza/modals/pd/modal-pd.component';

@NgModule({
  imports: [
    BrowserModule,
    ServiceWorkerModule.register('./ngsw-worker.js', { enabled: environment.production }),
    FormsModule,
    HttpClientModule,
    TranslateModule.forRoot(),
    NgbModule.forRoot(),
    CoreModule,
    SharedModule,
    DataTablesModule,

    HomeModule,
    AboutModule,
    NotFoundModule,
    DeliberazioniModule,
    ProvvedimentiDirigenzialiModule,
    DecretiModule,
    OrdinanzeModule,
    AttiConsiglioModule,

    AppRoutingModule
  ],
  declarations: [
                    AppComponent
                ],
  providers: [
    /*{
      provide: HTTP_INTERCEPTORS,
      useClass: APIInterceptor,
      multi: true
    }*/

    NgbActiveModal,
    CommonUtils
  ],
  bootstrap: [
    AppComponent
  ],
  entryComponents: [
    DettaglioAcBModalComponent,
    DettaglioDecBModalComponent,
    DettaglioDelBModalComponent,
    DettaglioOrdBModalComponent,
    DettaglioPdBModalComponent
  ]
})
export class AppModule { }
