import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { delay, map } from 'rxjs/operators';

import { environment, filters } from '@env/environment';

import { TabellaAcComponent } from '../../tables/tabella-ac/tabella-ac.component';
import { TabellaAcRowComponent } from '../../tables/tabella-ac-row/tabella-ac-row.component';

@Component({
  selector: 'app-cerca-ac',
  templateUrl: './cerca-ac.component.html',
  styleUrls: ['./cerca-ac.component.scss']
})
export class CercaAcComponent implements OnInit {
  isLoading: boolean;
  queryJson: any = {};
  queryParams: any = {};
  sottotipi: string[] = [];
  stati: string[] = [];
  esiti: string[] = [];
  tipoAtti: any[] = [];
  assessori: string[] = [];
  commissioni: string[] = [];
  anniPresentazione: number[] = [];
  mesiPresentazione: number[] = [];
  anniApprovazione: number[] = [];
  mesiApprovazione: number[] = [];
  anniScadenza: number[] = [];
  mesiScadenza: number[] = [];
  processCount = 0;

  // models
  selectedTipoAtto: string = null;
  selectedAnnoAtto: number;
  selectedNumeroAtto: string = null;

  private defaultLstTipoAtto: string[] = [];

  @ViewChild('inputOggetto') inputOggetto: ElementRef;
  @ViewChild('notLoadIniziale') notLoadIniziale: ElementRef;
  @ViewChild('inputProponente') inputProponente: ElementRef;
  @ViewChild('inputNumeroAdozione') inputNumeroAdozione: ElementRef;

  // @ViewChild('selectSottotipi') selectSottotipi: ElementRef;
  @ViewChild('selectTipoAtto') selectTipoAtto: ElementRef;
  @ViewChild('selectStati') selectStati: ElementRef;
  @ViewChild('selectEsiti') selectEsiti: ElementRef;
  @ViewChild('selectAssessori') selectAssessori: ElementRef;
  @ViewChild('selectCommissioni') selectCommissioni: ElementRef;

  @ViewChild('selectAnnoPresentazione') selectAnnoPresentazione: ElementRef;
  @ViewChild('selectMesePresentazione') selectMesePresentazione: ElementRef;
  @ViewChild('selectAnnoApprovazione') selectAnnoApprovazione: ElementRef;
  @ViewChild('selectMeseApprovazione') selectMeseApprovazione: ElementRef;
  @ViewChild('selectAnnoScadenza') selectAnnoScadenza: ElementRef;
  @ViewChild('selectMeseScadenza') selectMeseScadenza: ElementRef;

  @ViewChild('searchBtn') searchBtn: ElementRef;
  @ViewChild('resetBtn') resetBtn: ElementRef;

  @ViewChild('tabellaAc') tabellaAc: TabellaAcComponent;

  // @Output() onSearch: EventEmitter<any> = new EventEmitter<any>();

  constructor(
              private http: HttpClient,
              public translateService: TranslateService,
              private route: ActivatedRoute) {
      this.route.queryParams.subscribe(params => {
          this.queryParams = params;
      });

      this.isLoading = true;

      for (const item of filters.select.attiConsiglio.tipoAtti) {
          this.defaultLstTipoAtto.push(item.codice);
      }

      const currentYear: number = new Date().getFullYear();
      for (let i = currentYear; i >= environment.minDeployYear; i--) {
          this.anniPresentazione.push(i);
          this.anniApprovazione.push(i);
          this.anniScadenza.push(i);
      }
      for (const month in this.translateService.instant('monthList')) {
          this.mesiPresentazione.push( this.translateService.instant('monthList')[month] );
          this.mesiApprovazione.push( this.translateService.instant('monthList')[month] );
          this.mesiScadenza.push( this.translateService.instant('monthList')[month] );
      }

      for (const item of filters.select.attiConsiglio.tipoAtti) {
          this.tipoAtti.push(item);
      }

      const postTipiAttoParam: any = {
          'tipiAtto' : this.defaultLstTipoAtto
      };
      /*this.http.get<string[]>(environment.endpoint.get.sottotipi).subscribe(
      data => {
        this.sottotipi = data;
      },
      err => {
        this.setErrorMessage(err);
        this.onComplete();
      },
      () => {
        this.onComplete();
      });*/

      this.http.post<string[]>(environment.endpoint.get.stati, postTipiAttoParam).subscribe(
      data => {
        this.stati = data;
      },
      err => {
        this.setErrorMessage(err);
        this.onComplete();
      },
      () => {
        this.onComplete();
      });

      this.http.post<string[]>(environment.endpoint.get.esiti, postTipiAttoParam).subscribe(
      data => {
        this.esiti = data;
      },
      err => {
        this.setErrorMessage(err);
        this.onComplete();
      },
      () => {
        this.onComplete();
      });

      this.http.post<string[]>(environment.endpoint.get.assessori, postTipiAttoParam).subscribe(
      data => {
        this.assessori = data;
      },
      err => {
        this.setErrorMessage(err);
        this.onComplete();
      },
      () => {
        this.onComplete();
      });

      this.http.post<string[]>(environment.endpoint.get.commissioni, postTipiAttoParam).subscribe(
      data => {
        this.commissioni = data;
      },
      err => {
        this.setErrorMessage(err);
        this.onComplete();
      },
      () => {
        this.onComplete();
      });
  }

  ngOnInit() {
      /*let date : Date = new Date();
      console.log( date.getFullYear() );*/

      this.setFiltersbyQueryParams();
  }

  isLoaded(): boolean {
      if (this.processCount >= 4) {
        return true;
      }
      return false;
  }

  onComplete(): void {
      ++this.processCount;
      if (this.isLoaded()) {
        this.isLoading = false;
      }
  }

  setErrorMessage(err: any): void {
  }

  search(e: any): void {
      e.preventDefault();
      e.stopImmediatePropagation();

      // console.log('carca: ', this.inputOggetto.nativeElement, this.selectUffici.nativeElement);
      // console.log('selezionato: ', this.selectUffici.nativeElement.options[this.selectUffici.nativeElement.selectedIndex].value);

      this.queryJson['oggetto'] = this.inputOggetto.nativeElement.value;
      this.queryJson['notLoadIniziale'] = this.notLoadIniziale.nativeElement.value;
      this.queryJson['proponente'] = this.inputProponente.nativeElement.value;
      this.queryJson['numeroAdozione'] = this.inputNumeroAdozione.nativeElement.value;
      // this.queryJson['sottotipo'] = this.selectSottotipi.nativeElement.options[this.selectSottotipi.nativeElement.selectedIndex].value;
      if (this.selectStati.nativeElement.selectedIndex &&
        !isNaN(this.selectStati.nativeElement.selectedIndex) && this.selectStati.nativeElement.options[this.selectStati.nativeElement.selectedIndex]) {
          this.queryJson['stato'] = this.selectStati.nativeElement.options[this.selectStati.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['stato'];
      }
      if (this.selectEsiti.nativeElement.selectedIndex &&
        !isNaN(this.selectEsiti.nativeElement.selectedIndex) && this.selectEsiti.nativeElement.options[this.selectEsiti.nativeElement.selectedIndex]) {
          this.queryJson['esito'] = this.selectEsiti.nativeElement.options[this.selectEsiti.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['esito'];
      }
      if (this.selectAssessori.nativeElement.selectedIndex &&
        !isNaN(this.selectAssessori.nativeElement.selectedIndex) && this.selectAssessori.nativeElement.options[this.selectAssessori.nativeElement.selectedIndex]) {
          this.queryJson['assessore'] = this.selectAssessori.nativeElement.options[this.selectAssessori.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['assessore'];
      }
      if (this.selectCommissioni.nativeElement.selectedIndex &&
        !isNaN(this.selectCommissioni.nativeElement.selectedIndex) && this.selectCommissioni.nativeElement.options[this.selectCommissioni.nativeElement.selectedIndex]) {
          this.queryJson['commissione'] = this.selectCommissioni.nativeElement.options[this.selectCommissioni.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['commissione'];
      }
      if (this.selectAnnoPresentazione.nativeElement.selectedIndex &&
        !isNaN(this.selectAnnoPresentazione.nativeElement.selectedIndex) && this.selectAnnoPresentazione.nativeElement.options[this.selectAnnoPresentazione.nativeElement.selectedIndex]) {
          this.queryJson['annoPresentazione'] = this.selectAnnoPresentazione.nativeElement.options[this.selectAnnoPresentazione.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['annoPresentazione'];
      }
      if (this.selectMesePresentazione.nativeElement.selectedIndex &&
        !isNaN(this.selectMesePresentazione.nativeElement.selectedIndex) && this.selectMesePresentazione.nativeElement.options[this.selectMesePresentazione.nativeElement.selectedIndex]) {
          this.queryJson['mesePresentazione'] = this.selectMesePresentazione.nativeElement.options[this.selectMesePresentazione.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['mesePresentazione'];
      }
      if (this.selectAnnoApprovazione.nativeElement.selectedIndex &&
        !isNaN(this.selectAnnoApprovazione.nativeElement.selectedIndex) && this.selectAnnoApprovazione.nativeElement.options[this.selectAnnoApprovazione.nativeElement.selectedIndex]) {
          this.queryJson['annoApprovazione'] = this.selectAnnoApprovazione.nativeElement.options[this.selectAnnoApprovazione.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['annoApprovazione'];
      }
      if (this.selectMeseApprovazione.nativeElement.selectedIndex &&
        !isNaN(this.selectMeseApprovazione.nativeElement.selectedIndex) && this.selectMeseApprovazione.nativeElement.options[this.selectMeseApprovazione.nativeElement.selectedIndex]) {
          this.queryJson['meseApprovazione'] = this.selectMeseApprovazione.nativeElement.options[this.selectMeseApprovazione.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['meseApprovazione'];
      }
      if (this.selectAnnoScadenza.nativeElement.selectedIndex &&
        !isNaN(this.selectAnnoScadenza.nativeElement.selectedIndex) && this.selectAnnoScadenza.nativeElement.options[this.selectAnnoScadenza.nativeElement.selectedIndex]) {
          this.queryJson['annoScadenza'] = this.selectAnnoScadenza.nativeElement.options[this.selectAnnoScadenza.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['annoScadenza'];
      }
      if (this.selectMeseScadenza.nativeElement.selectedIndex &&
        !isNaN(this.selectMeseScadenza.nativeElement.selectedIndex) && this.selectMeseScadenza.nativeElement.options[this.selectMeseScadenza.nativeElement.selectedIndex]) {
          this.queryJson['meseScadenza'] = this.selectMeseScadenza.nativeElement.options[this.selectMeseScadenza.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['meseScadenza'];
      }

      let tipoAtto = null;
      if (this.selectTipoAtto.nativeElement.selectedIndex &&
        !isNaN(this.selectTipoAtto.nativeElement.selectedIndex) && this.selectTipoAtto.nativeElement.options[this.selectTipoAtto.nativeElement.selectedIndex]) {
          tipoAtto = this.selectTipoAtto.nativeElement.options[this.selectTipoAtto.nativeElement.selectedIndex].value;
      }
      this.queryJson['tipiAtto'] = (tipoAtto && tipoAtto.length > 0) ? [tipoAtto] : this.tipoAtti.map((item) => item.codice);

      // this.onSearch.emit(this.queryJson);
      // console.log('output: ', this.queryJson);
      this.tabellaAc.onSearch(this.queryJson);
      this.notLoadIniziale.nativeElement.value='ok';
  }

  reset(e: any): void {
      this.inputOggetto.nativeElement.value = '';
      this.inputProponente.nativeElement.value = '';
      this.inputNumeroAdozione.nativeElement.value = '';
      // this.selectSottotipi.nativeElement.selectedIndex = 0;
      this.selectTipoAtto.nativeElement.selectedIndex = 0;
      this.selectStati.nativeElement.selectedIndex = 0;
      this.selectEsiti.nativeElement.selectedIndex = 0;
      this.selectAssessori.nativeElement.selectedIndex = 0;
      this.selectCommissioni.nativeElement.selectedIndex = 0;
      this.selectAnnoPresentazione.nativeElement.selectedIndex = 0;
      this.selectMesePresentazione.nativeElement.selectedIndex = 0;
      this.selectAnnoApprovazione.nativeElement.selectedIndex = 0;
      this.selectMeseApprovazione.nativeElement.selectedIndex = 0;
      this.selectAnnoScadenza.nativeElement.selectedIndex = 0;
      this.selectMeseScadenza.nativeElement.selectedIndex = 0;
      this.queryJson = {};
      this.queryJson['tipiAtto'] = this.tipoAtti.map((item) => item.codice);
      this.tabellaAc.onReset(this.queryJson);
  }

  setFiltersbyQueryParams(): void {
    if (this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.tipo] != null) {
      this.selectedTipoAtto = this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.tipo];
    } else {
      this.selectedTipoAtto = '';
    }
    if (this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.numero] != null) {
      this.selectedNumeroAtto = this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.numero];
    }
    if (this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.anno] != null) {
      this.selectedAnnoAtto = this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.anno];
    }
  }

}
