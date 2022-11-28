import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { delay, map } from 'rxjs/operators';

import { environment, comuneBaseUrl, filters } from '@env/environment';

import { TabellaPdComponent } from '../../tables/tabella-pd/tabella-pd.component';
import { TabellaPdRowComponent } from '../../tables/tabella-pd-row/tabella-pd-row.component';

@Component({
  selector: 'app-cerca-pd',
  templateUrl: './cerca-pd.component.html',
  styleUrls: ['./cerca-pd.component.scss']
})
export class CercaPdComponent implements OnInit {
  isLoading: boolean;
  queryJson: any = {};
  queryParams: any = {};
  uffici: string[] = [];
  relatori: string[] = [];
  anniAdozione: number[] = [];
  mesiAdozione: number[] = [];
  processCount = 0;

  // models
  selectedTipoAtto: string = null;
  selectedAnnoAtto: number;
  selectedNumeroAtto: string = null;

  private defaultLstTipoAtto: string[] =  [environment.atto.tipo.determinaDirigenziale.codice,
                                          environment.atto.tipo.determineLiquidazione.codice];

  @ViewChild('inputOggetto') inputOggetto: ElementRef;
  @ViewChild('notLoadIniziale') notLoadIniziale: ElementRef;
  @ViewChild('inputNumeroAdozione') inputNumeroAdozione: ElementRef;
  @ViewChild('selectUffici') selectUffici: ElementRef;
  @ViewChild('selectRelatori') selectRelatori: ElementRef;
  @ViewChild('selectCompetenza') selectCompetenza: ElementRef;
  @ViewChild('selectAnnoAdozione') selectAnnoAdozione: ElementRef;
  @ViewChild('selectMeseAdozione') selectMeseAdozione: ElementRef;

  @ViewChild('searchBtn') searchBtn: ElementRef;
  @ViewChild('resetBtn') resetBtn: ElementRef;

  @ViewChild('tabellaPd') tabellaPd: TabellaPdComponent;

  // @Output() onSearch: EventEmitter<any> = new EventEmitter<any>();

  constructor(
              private http: HttpClient,
              public translateService: TranslateService,
              private route: ActivatedRoute) {
      this.route.queryParams.subscribe(params => {
          this.queryParams = params;
      });

      this.isLoading = true;

      for (const item of filters.select.provvedimentiDirigenziali.tipoAtti) {
          this.defaultLstTipoAtto.push(item.codice);
      }

      const currentYear: number = new Date().getFullYear();
      for (let i = currentYear; i >= environment.minDeployYear; i--) {
          this.anniAdozione.push(i);
      }

      for (const month in this.translateService.instant('monthList')) {
          this.mesiAdozione.push( this.translateService.instant('monthList')[month] );
      }

      const postTipiAttoParam: any = {
          'tipiAtto' : this.defaultLstTipoAtto
      };
      this.http.post<string[]>(environment.endpoint.get.uffici, postTipiAttoParam).subscribe(
      data => {
        this.uffici = data;
      },
      err => {
        this.setErrorMessage(err);
        this.onComplete();
      },
      () => {
        this.onComplete();
      });

      this.http.post<string[]>(environment.endpoint.get.relatori, postTipiAttoParam).subscribe(
      data => {
        this.relatori = data;
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
      if (this.processCount >= 2) {
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
      this.queryJson['numeroAdozione'] = this.inputNumeroAdozione.nativeElement.value;
      if(this.selectUffici.nativeElement.selectedIndex && !isNaN(this.selectUffici.nativeElement.selectedIndex) && this.selectUffici.nativeElement.options[this.selectUffici.nativeElement.selectedIndex]){
      	this.queryJson['ufficio'] = this.selectUffici.nativeElement.options[this.selectUffici.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['ufficio'];
      }
      if(this.selectRelatori.nativeElement.selectedIndex && !isNaN(this.selectRelatori.nativeElement.selectedIndex) && this.selectRelatori.nativeElement.options[this.selectRelatori.nativeElement.selectedIndex]){
      	this.queryJson['relatore'] = this.selectRelatori.nativeElement.options[this.selectRelatori.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['relatore'];
      }
      if(this.selectCompetenza.nativeElement.selectedIndex && !isNaN(this.selectCompetenza.nativeElement.selectedIndex) && this.selectCompetenza.nativeElement.options[this.selectCompetenza.nativeElement.selectedIndex]){
      	this.queryJson['competenza'] = this.selectCompetenza.nativeElement.options[this.selectCompetenza.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['competenza'];
      }
      if(this.selectAnnoAdozione.nativeElement.selectedIndex && !isNaN(this.selectAnnoAdozione.nativeElement.selectedIndex) && this.selectAnnoAdozione.nativeElement.options[this.selectAnnoAdozione.nativeElement.selectedIndex]){
      	this.queryJson['annoAdozione'] = this.selectAnnoAdozione.nativeElement.options[this.selectAnnoAdozione.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['annoAdozione'];
      }
      if(this.selectMeseAdozione.nativeElement.selectedIndex && !isNaN(this.selectMeseAdozione.nativeElement.selectedIndex) && this.selectMeseAdozione.nativeElement.options[this.selectMeseAdozione.nativeElement.selectedIndex]){
      	this.queryJson['meseAdozione'] = this.selectMeseAdozione.nativeElement.options[this.selectMeseAdozione.nativeElement.selectedIndex].value;
      }else{
      	delete this.queryJson['meseAdozione'];
      }
	   	this.tabellaPd.onSearch(this.queryJson);
      	this.notLoadIniziale.nativeElement.value='ok';
  }

  reset(e: any): void {
      this.inputOggetto.nativeElement.value = '';
      this.inputNumeroAdozione.nativeElement.value = '';
      this.selectUffici.nativeElement.selectedIndex = 0;
      this.selectRelatori.nativeElement.selectedIndex = 0;
      this.selectCompetenza.nativeElement.selectedIndex = 0;
      this.selectAnnoAdozione.nativeElement.selectedIndex = 0;
      this.selectMeseAdozione.nativeElement.selectedIndex = 0;
      this.queryJson = {};
      this.tabellaPd.onReset(this.queryJson);
  }

  selectUfficiOnChange(e: any) {
  }

  selectRelatoriOnChange(e: any) {
  }

  selectCompetenzaOnChange(e: any) {
  }

  selectAnnoAdozioneOnChange(e: any) {
  }

  selectMeseAdozioneOnChange(e: any) {
  }

  inputNumeroAdozioneOnChange(e: any) {
        // if (e.type == 'paste') console.log('dal componente', e.clipboardData.getData('text/plain'));
        // else console.log('dal componente', e.target.value);
  }

  inputOggettoOnChange(e: any) {
        // if (e.type == 'paste') console.log('dal componente', e.clipboardData.getData('text/plain'));
        // else console.log('dal componente', e.target.value);
  }

  setFiltersbyQueryParams(): void {
    if (this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.tipo] != null) {
      this.selectedTipoAtto = this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.tipo];
    }
    if (this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.numero] != null) {
      this.selectedNumeroAtto = this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.numero];
    }
    if (this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.anno] != null) {
      this.selectedAnnoAtto = this.queryParams[environment.queryParameters.getAttoByTipoNumeroAnno.anno];
    }
  }

}
