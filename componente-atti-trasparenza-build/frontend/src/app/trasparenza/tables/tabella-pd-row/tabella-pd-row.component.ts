import { Component, OnInit, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { CommonUtils } from './../../utils/common-utils';

import { NgbModal, NgbActiveModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { DettaglioPdBModalComponent } from './../../modals/pd/modal-pd.component';

@Component({
  selector: '[app-tabella-pd-row]',
  templateUrl: './tabella-pd-row.component.html',
  styleUrls: ['./tabella-pd-row.component.scss']
})
export class TabellaPdRowComponent implements OnInit {
  @Input() json_data: any;
  @Input() columns: string[];

  constructor(public translateService: TranslateService,
              public utils: CommonUtils,
              private modalService: NgbModal) { }

  ngOnInit() {
  }


  open() {
    const modalRef = this.modalService.open(DettaglioPdBModalComponent, {
      size: 'lg',
      // backdrop: 'static',
      windowClass: 'modal-xl'
    });
    modalRef.componentInstance.content = this.json_data;
  }

}
