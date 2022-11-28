import { Component, OnInit, OnDestroy, Input, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { delay, map } from 'rxjs/operators';

import { Logger, I18nService } from '@app/core';
import { environment, comuneBaseUrl, filters } from '@env/environment';

import { DataTableDirective } from 'angular-datatables';

import { TranslateService, LangChangeEvent } from '@ngx-translate/core';

import * as moment from 'moment';

@Component({
  selector: 'app-tabella-dec',
  templateUrl: './tabella-dec.component.html',
  styleUrls: ['./tabella-dec.component.scss']
})
export class TabellaDecComponent implements OnInit, OnDestroy {
  json_list: any[] = [];
  columns: string[] = [];
  columnLabels: string[] = [];
  isLoading: boolean;
  queryJson: any = {};
  tipoAtto: string = null;
  numeroAtto: string = null;
  annoAtto: number;

  public dtOptions: DataTables.Settings = {};
  public dtTrigger: Subject<any[]> = new Subject(); // thus we ensure the data is fetched before rendering
  public dtInstance: any;

  @ViewChild(DataTableDirective) dtElement: DataTableDirective;
  @ViewChild('errorMessage') errorMessage: ElementRef;

  private defaultLstTipoAtto: string[] = [];

  constructor(private http: HttpClient,
              private translateService: TranslateService,
              private route: ActivatedRoute) {
        this.route.queryParams.subscribe(params => {
          this.tipoAtto = params[environment.queryParameters.getAttoByTipoNumeroAnno.tipo];
          this.numeroAtto = params[environment.queryParameters.getAttoByTipoNumeroAnno.numero];
          this.annoAtto = params[environment.queryParameters.getAttoByTipoNumeroAnno.anno];
        });

        this.columns = [
            'numeroAdozione',
            'dataAdozione',
            'ufficio',
            'oggetto',
            'statoAnnullamento',
            'atto',
            // 'allegati',
            'dettaglio'
        ];
        this.columnLabels = [
            this.translateService.instant('columnLabels')['adoptionNumber'],
            this.translateService.instant('columnLabels')['adoptionDate'],
            this.translateService.instant('columnLabels')['office'],
            this.translateService.instant('columnLabels')['subject'],
            this.translateService.instant('columnLabels')['stateCancel'],
            this.translateService.instant('columnLabels')['act'],
            // this.translateService.instant('columnLabels')['attachments'],
            this.translateService.instant('columnLabels')['detail']
        ];

        for (const item of filters.select.decreti.tipoAtti) {
            this.defaultLstTipoAtto.push(item.codice);
        }
  }

  public retrieveData(): number {
        this.dtOptions = {
          pagingType: 'full_numbers',
          pageLength: 10,
          info: true,
          paging: true,
          lengthChange: true,
          searching: false,
          responsive: {
            details: true
          },
          order: [[ 0, 'asc' ]],
          columnDefs: [
            {
              'type': 'date',
              'orderable': true,
              'targets': 1
            },
            { 'orderable': false, 'targets': this.columnLabels.length - 1 },
            { 'orderable': false, 'targets': this.columnLabels.length - 2 }
          ],
          lengthMenu: [[10, 25, 50, 100, -1], [10, 25, 50, 100, this.translateService.instant('all')]],
          language: {
            'emptyTable':     this.translateService.instant('datatable')['emptyTable'],
            'info':           this.translateService.instant('datatable')['info'],
            'infoEmpty':      this.translateService.instant('datatable')['infoFiltered'],
            'infoFiltered':   this.translateService.instant('datatable')['infoFiltered'],
            'infoPostFix':    this.translateService.instant('datatable')['infoPostFix'],
            'thousands':      this.translateService.instant('datatable')['thousands'],
            'lengthMenu':     this.translateService.instant('datatable')['lengthMenu'],
            'loadingRecords': this.translateService.instant('datatable')['loadingRecords'],
            'processing':     this.translateService.instant('datatable')['processing'],
            'search':         this.translateService.instant('datatable')['search'],
            'zeroRecords':    this.translateService.instant('datatable')['zeroRecords'],
            'paginate': {
                'first':      this.translateService.instant('datatable')['paginate']['first'],
                'previous':   this.translateService.instant('datatable')['paginate']['previous'],
                'next':       this.translateService.instant('datatable')['paginate']['next'],
                'last':       this.translateService.instant('datatable')['paginate']['last']
            },
            'aria': {
                'sortAscending':  this.translateService.instant('datatable')['aria']['sortAscending'],
                'sortDescending': this.translateService.instant('datatable')['aria']['sortDescending']
            }
          }
        };

        const httpOptions = {
          headers: new HttpHeaders({
            // 'Content-Type':  'application/json'
            // 'Authorization': 'my-auth-token'
          })
        };

        const postRequestData: any = {
            'oggetto' : '',
            'tipiAtto' : this.defaultLstTipoAtto
        };

        /*this.http.get(this.endpointGet)
          //.map(this.extractData)
          .subscribe(data => {
            // console.log(data);
            this.setData(data);
            //this.json_list = data;
            // Calling the DT trigger to manually render the table
            this.dtTrigger.next();
          });*/

        this.isLoading = true;

        if (this.tipoAtto != null && this.numeroAtto != null && this.annoAtto != null) {
          const params: HttpParams = new HttpParams()
                                      .set(environment.queryParameters.getAttoByTipoNumeroAnno.tipo, this.tipoAtto)
                                      .set(environment.queryParameters.getAttoByTipoNumeroAnno.numero, this.numeroAtto)
                                      .set(environment.queryParameters.getAttoByTipoNumeroAnno.anno, String(this.annoAtto))
                                      ;
          const getRequestData: any = { params };

          this.http.get(environment.endpoint.get.getAttoByTipoNumeroAnno, getRequestData).subscribe(
          data => {
              this.setData([data]);
          },
          err => {
              this.errorMessage.nativeElement.firstChild.innerHTML =  this.translateService.instant('error') + ': ' + err.statusText + ', ' +
                                                                      this.translateService.instant('errorCode') + ': '  + err.status + ', ' +
                                                                      this.translateService.instant('errorMessage') + ': '  + err.message;
              this.errorMessage.nativeElement.style.display = 'block';
              this.dtTrigger.next();
              this.isLoading = false;
          },
          () => {
              this.dtTrigger.next();
              this.isLoading = false;
          });
        } else {
          this.http.post(environment.endpoint.post.searchAtti, postRequestData).subscribe(
          data => {
              this.setData(data);
          },
          err => {
              this.errorMessage.nativeElement.firstChild.innerHTML =  this.translateService.instant('error') + ': ' + err.statusText + ', ' +
                                                                      this.translateService.instant('errorCode') + ': '  + err.status + ', ' +
                                                                      this.translateService.instant('errorMessage') + ': '  + err.message;
              this.errorMessage.nativeElement.style.display = 'block';
              this.dtTrigger.next();
              this.isLoading = false;
          },
          () => {
              this.dtTrigger.next();
              this.isLoading = false;
          });
        }

        return 0;
    }

    onSearch(data: any, params?: any) {
        // console.log('cliccato search btn: ', data);
        const httpOptions = {
          headers: new HttpHeaders({
          })
        };

        const postRequestData = data;
        postRequestData['tipiAtto'] = this.defaultLstTipoAtto;

        this.isLoading = true;
        this.http.post(environment.endpoint.post.searchAtti, postRequestData).subscribe(
        pdata => {
	            this.json_list = [];
            this.errorMessage.nativeElement.firstChild.innerHTML =  '';
            if(pdata!=null) {
               	this.errorMessage.nativeElement.style.display = 'none';
	               this.setData(pdata);
            }else{
            	   this.errorMessage.nativeElement.firstChild.innerHTML =  this.translateService.instant('tooMuch');
            	   this.errorMessage.nativeElement.style.display = 'block';
            }
            this.isLoading = false;
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
	              // Destroy the table first
	              dtInstance.destroy();
	              // Call the dtTrigger to rerender again
	              this.dtTrigger.next();
	            });
        },
        err => {
            this.errorMessage.nativeElement.firstChild.innerHTML =  this.translateService.instant('error') + ': ' + err.statusText + ', ' +
                                                                    this.translateService.instant('errorCode') + ': '  + err.status + ', ' +
                                                                    this.translateService.instant('errorMessage') + ': '  + err.message;
            this.errorMessage.nativeElement.style.display = 'block';
            this.isLoading = false;
        },
        () => {
            this.isLoading = false;
        });
    }

    onReset(data: any) {
        this.json_list = [];
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
           // Destroy the table first
           dtInstance.destroy();
           // Call the dtTrigger to rerender again
           this.dtTrigger.next();
         });
    }

    private extractData(res: Response) {
        const body = res.json();
        return body.data || {};
    }
    private setData(list: any): void {
        this.json_list = [];

        for (const i in list) {
          if (list[i] != null) {
            const obj: any = list[i];
            let new_obj: any = {};
            // console.log(obj);
            new_obj = obj;
            for (const key in obj) {
                 // console.log(key);
                 // new_obj[key] = obj[key];
                 // console.log(new_obj[key]);
                 if (key === 'allegati') {
                     new_obj['atto'] = this.getAttoLink(obj[key]);
                     new_obj['allegati'] = this.getAllegati(obj[key]);
                 } /*else if (key.toString().toLowerCase().indexOf('data') >= 0) {
                     let strDate = obj[key];
                     new_obj[key] = obj[key];
                 }*/
             }
             this.json_list.push(new_obj);
          }
        }
        // console.log(this.json_list);
    }

    private getAttoLink(list: any): any {
        for (const i in list) {
          if (list[i] != null) {
            const obj: any = list[i];
            if (obj.principale) {
              return obj;
            }
          }
        }
        return {};
    }

    private getAllegati(list: any): any {
        const ret: any[] = [];
        for (const i in list) {
          if (list[i] != null) {
            const obj: any = list[i];
            if (!obj.principale) {
              ret.push(obj);
            }
          }
        }
        return ret;
    }

  ngOnInit() {
    // custom datatable sort
    const patternDate = this.translateService.instant('patternDateMoment');
    jQuery.extend(jQuery.fn.dataTable.ext.oSort, {
        'date-pre': function(a: any) {
            const strDate = moment(a, patternDate).format(patternDate);
            const date = moment(strDate, patternDate).toDate();
            a = date;
            return a;
        },
        'date-asc': function (a: any, b: any) {
            return a - b;
        },
        'date-desc': function (a: any, b: any) {
            return b - a;
        }
    });

    // this.columns = this.attiService.getColumns();
    // this.json_list = this.attiService.getData();
    this.retrieveData();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}
